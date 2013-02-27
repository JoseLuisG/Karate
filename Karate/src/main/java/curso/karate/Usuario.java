package curso.karate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Usuario 
{
    private long id;
    private String nombre;
    private String pais;
    private RolEnum rol;

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
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + '}';
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