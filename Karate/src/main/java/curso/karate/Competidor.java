package curso.karate;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public SexoCompetidorEnum getSexoEnum() {
        return sexoEnum;
    }

    public void setSexoEnum(SexoCompetidorEnum sexoEnum) {
        this.sexoEnum = sexoEnum;
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
    
}
