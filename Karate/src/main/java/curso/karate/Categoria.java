package curso.karate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(name="categorias")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(length=128, nullable=false)
    private String nombre;
    
    @Column(name="peso_Min", precision=5,scale=2)
    private BigDecimal pesoMin;
    @Column(name="peso_Max", precision=5,scale=2)
    private BigDecimal pesoMax;
    @Column(name="edad_Min")
    private int edadMin;
    @Column(name="edad_Max")
    private int edadMax;
    
    private String sexo;
    
    private String modalidad;
    
    //===========================================Constructors
    
    public Categoria() {
        this.sexo = SexoCategoriaEnum.FEMENINA.toString();
        this.modalidad = ModalidadEnum.KATA.toString();
    }

    public Categoria(long id, String nombre, BigDecimal pesoMin, BigDecimal pesoMax, int edadMin, int edadMax, SexoCategoriaEnum sexo, ModalidadEnum modalidad) {
        this.id = id;
        this.nombre = nombre;
        this.pesoMin = pesoMin;
        this.pesoMax = pesoMax;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.sexo = sexo.toString();
        this.modalidad = modalidad.toString();
    }
    
    //===========================================Getters/setters

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

    public BigDecimal getPesoMin() {
        return pesoMin;
    }

    public void setPesoMin(BigDecimal pesoMin) {
        this.pesoMin = pesoMin;
    }

    public BigDecimal getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(BigDecimal pesoMax) {
        this.pesoMax = pesoMax;
    }

    public int getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(int edadMin) {
        this.edadMin = edadMin;
    }

    public int getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(int edadMax) {
        this.edadMax = edadMax;
    }


    public SexoCategoriaEnum getSexo() {
        return SexoCategoriaEnum.valueOf(sexo);
    }

    public void setSexo(SexoCategoriaEnum sexoE) {
        this.sexo = sexo.toString();
    }

    public ModalidadEnum getModalidad() {
        return ModalidadEnum.valueOf(modalidad);
    }

    public void setModalidad(ModalidadEnum modalidad) {
        this.modalidad = modalidad.toString();
    }
    //===========================================General methods

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + ("Categoria".hashCode());
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
        final Categoria other = (Categoria) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", pesoMin=" + pesoMin + ", pesoMax=" + pesoMax + ", edadMin=" + edadMin + ", edadMax=" + edadMax + ", sexo=" + sexo + ", modalidad=" + modalidad + '}';
    }
    
        
    //===========================================Queries
    
    public static Categoria findById(EntityManager em, long id) {
        return em.find(Categoria.class, id);
    }

    public static boolean containsCategoria(EntityManager em, long id) {
        return em.find(Categoria.class, id) != null;
    }

    public static List<Categoria> findAll(EntityManager em) {
        String sql = "SELECT x FROM Categoria x ORDER BY x.nombre";
        TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
        return query.getResultList();
    }

    public static List<Categoria> findBySexo(EntityManager em, SexoCategoriaEnum sexo) {
        String sql = "SELECT x FROM Categoria x WHERE x.sexo = :sexo";
        TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
        query.setParameter("sexo", sexo.toString());
        return query.getResultList();
    }

    public static List<Categoria> findByModalidad(EntityManager em, ModalidadEnum modalidad) {
        String sql = "SELECT x FROM Categoria x WHERE x.modalidad = :modalidad";
        TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
        query.setParameter("modalidad", modalidad.toString());
        return query.getResultList();
    }

    public static long count(EntityManager em) {
        String sql = "SELECT COUNT(x) FROM Categoria x";
        TypedQuery<Long> query = em.createQuery(sql, Long.class);
        Long count = query.getSingleResult();
        return count;
    }

    public static long countBySexo(EntityManager em, SexoCategoriaEnum sexo) {
        String sql = "SELECT COUNT(x) FROM Categoria x WHERE x.sexo = :sexo";
        TypedQuery<Long> query = em.createQuery(sql, Long.class);
        query.setParameter("sexo", sexo.toString());
        Long count = query.getSingleResult();
        return count;
    }

    public static long countByModalidad(EntityManager em, ModalidadEnum modalidad) {
        String sql = "SELECT COUNT(x) FROM Categoria x WHERE x.modalidad = :modalidad";
        TypedQuery<Long> query = em.createQuery(sql, Long.class);
        query.setParameter("modalidad", modalidad.toString());
        Long count = query.getSingleResult();
        return count;
    }

    //===========================================Modify

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
    public Categoria update(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Categoria competidor = updateNoTransaction(em);
            et.commit();
            return competidor;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            return null;
        }
    }

    public Categoria updateNoTransaction(EntityManager em) {
        Categoria competidor = em.merge(this);
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
        if (em.find(Categoria.class, this.getId()) != null) {
            em.remove(this);
            em.flush();
            return true;
        } else {
            return false;
        }
    }
  
}
