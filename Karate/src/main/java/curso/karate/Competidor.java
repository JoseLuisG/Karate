package curso.karate;

import java.beans.Transient;
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
    
}
