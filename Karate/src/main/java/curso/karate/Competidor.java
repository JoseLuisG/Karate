package curso.karate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="competidores")
public class Competidor implements Serializable {
   
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    
    @Column(length=128, nullable=false)
    private String nombre;
    
    @Column(precision=5, scale=2, nullable=false)
    private BigDecimal peso;
    
    @Column(nullable=false)
    private int edad;
    
    @Column(nullable=false)
    private String sexo;
    
    private transient SexoCompetidorEnum sexoEnum;
    
    @Column(length=50, nullable=false)
    private String pais;
    
    @Column(length=255, nullable=false)
    private String foto;
    
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
      name="categorias_competidores",
      joinColumns={@JoinColumn(name="id_com", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="id_cat", referencedColumnName="id")})
    private List<Categoria> categorias;

    public Competidor() {
    }

    public Competidor(long id, String nombre, BigDecimal peso, int edad, String sexo, SexoCompetidorEnum sexoEnum, String pais, String foto, List<Categoria> categorias) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.edad = edad;
        this.sexo = sexo;
        this.sexoEnum = sexoEnum;
        this.pais = pais;
        this.foto = foto;
        this.categorias = categorias;
    }
    
    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

//    public String getSexo() {
//        return sexo;
//    }
//    public void setSexo(String sexo) {
//        this.sexo = sexo;
//    }

    public SexoCompetidorEnum getSexoEnum() {
        return sexoEnum.valueOf(sexo);
    }

    public void setSexoEnum(SexoCompetidorEnum sexoEnum) {
        this.sexoEnum = sexoEnum;
        this.sexo = sexoEnum.toString();
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    // Equals & hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Competidor other = (Competidor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Competidor - id=%d, nombre=%s", getId(), getNombre());
    }
    
    //
    // Active Record
    //

    // Queries
    
    
    
    // Modifying
    public boolean create(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            boolean res = createNoTransaction(em);
            et.commit();
            return res;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
//            throw new Exception("Error saving user");
            e.printStackTrace();
            return false;
        }
    }

    public boolean createNoTransaction(EntityManager em) {
        if (em.contains(this)) {
            return false;
        } else {
            em.persist(this);
            em.flush();
            return true;
        }
    }
    public Competidor update(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Competidor competidor = updateNoTransaction(em);
            et.commit();
            return competidor;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            return null;
        }
    }

    public Competidor updateNoTransaction(EntityManager em) {
        Competidor competidor = em.merge(this);
        em.flush();
        return competidor;
    }
    
    public boolean remove(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            boolean res = removeNoTransaction(em);
            et.commit();
            return res;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
//            throw new Exception("Error saving user");
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeNoTransaction(EntityManager em) {
        if (em.find(Competidor.class, this.getId()) != null) {
            em.remove(this);
            em.flush();
            return true;
        } else {
            return false;
        }
    }
}
