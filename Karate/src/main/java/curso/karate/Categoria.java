package curso.karate;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

@Entity
@Table(name="books")
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
    
    public Categoria() {
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

    public ModalidadEnum getModalidadE() {
        return ModalidadEnum.valueOf(modalidad);
    }

    public void setModalidadE(ModalidadEnum modalidad) {
        this.modalidad = modalidad.toString();
    }

}
    
    // =====================================================================================
    //  A PARTIR DE AQUI -->  pendiente de adaptar
    // Active Record
    //======================================================================================
/* 
    // Queries
    public static List<Book> findAll(EntityManager em) {
        String sql = "SELECT x FROM Book x ORDER BY x.title";
        TypedQuery<Book> query = em.createQuery(sql, Book.class);
        return query.getResultList();
    }

    public static Book findById(EntityManager em, long id) {
        return em.find(Book.class, id);
    }

    public static Book findByISBN(EntityManager em, String isbn) {
        String sql = "SELECT x FROM Book x WHERE x.isbn = :isbn";
        TypedQuery<Book> query = em.createQuery(sql, Book.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    public static List<Book> findByYear(EntityManager em, int year) {
        String sql = "SELECT x FROM Book x WHERE x.year = :year ORDER BY x.title";
        TypedQuery<Book> query = em.createQuery(sql, Book.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    public static List<Book> findByAuthor(EntityManager em, Author author) {
        String sql = "SELECT x FROM Book x WHERE x.author = :author";
        TypedQuery<Book> query = em.createQuery(sql, Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    public static boolean containsBook(EntityManager em, long id) {
        return em.find(Book.class, id) != null;
    }

    public static long count(EntityManager em) {
        String sql = "SELECT COUNT(x) FROM Book x";
        TypedQuery<Long> query = em.createQuery(sql, Long.class);
        Long count = query.getSingleResult();
        return count;
    }

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
        if (em.find(Book.class, this.getId()) != null) {
            em.remove(this);
            em.flush();
            return true;
        } else {
            return false;
        }
    }
  
}
*/
