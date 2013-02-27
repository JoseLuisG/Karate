package curso.karate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

@Entity
@Table(name="Usuario")
public class Usuario 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(unique=true, length=32, name="nombre_usuario")
    private String nombreUsuario;    
    
    @Column(length=24, name="password")
    private String password;    
    
    @Column(length=128, name="nombre")
    private String nombre;    
    
    @Column(length=128, name="pais")
    private String pais;
    
    @Transient
    private RolEnum rol;
    @Column(name = "rol")
    private String rolString;
    
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rolString = rol.toString();
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if ((this.nombreUsuario == null) ? (other.nombreUsuario != null) : !this.nombreUsuario.equals(other.nombreUsuario)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.pais == null) ? (other.pais != null) : !this.pais.equals(other.pais)) {
            return false;
        }
        if (this.rol != other.rol) {
            return false;
        }
        return true;
    }

    //   ********************
    //   *  Active Record   *
    //   ********************
    
    
    public static long count(EntityManager em) {
        String sql = "SELECT Count(x) FROM Usuario x";
        TypedQuery<Long> query = em.createQuery(sql, Long.class);
        return query.getSingleResult();
    }

    // Modifications

    public boolean create(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            createNoTransaction(em);
            et.commit();
            return true;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            return false;
        }
    }

    public void createNoTransaction(EntityManager em) {
        em.persist(this);
        em.flush();
    }
    
    public boolean delete(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            deleteNoTransaction(em);
            et.commit();
            return true;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            return false;
        }
    }

    public void deleteNoTransaction(EntityManager em) {
        em.remove(this);
        em.flush();
    }
    
        public Usuario update(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Usuario usuario = updateNoTransaction(em);
            et.commit();
            return usuario;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            return null;
        }
    }

    public Usuario updateNoTransaction(EntityManager em) {
        Usuario usuario = em.merge(this);
        em.flush();
        return usuario;
    }
    
    public static Usuario findByUsuario(EntityManager em, String usuario) {
        return em.find(Usuario.class, usuario);
    }

}



//
//@Transient
//private String password;
//private String passwordSHA;
//setPassword(String password) {
//  this.password = password;
//  setPasswordSHA(sha(Password))
//};
//}