package curso.karate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

    private EntityManager em;
 
    
    @Before
    public void emptyTables() {
        try {
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("TRUNCATE TABLE usuarios");
        } catch (Exception e) {
            System.err.println("Error Truncating");
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaratePU");
        em = emf.createEntityManager();
    }

    @After
    public void after() {
        em.close();
    }

    private Usuario usuario1 = new Usuario(1L, "userGolo", "ug", "Golo", RolEnum.ADMINISTRADOR, "España" );
    private Usuario usuario2 = new Usuario(2L, "userDodo", "ud", "Dodo", RolEnum.COLABORADOR, "Australia");
    private Usuario usuario3 = new Usuario(3L, "userWilly", "uW", "Willy",RolEnum.COLABORADOR, "EEUU");
    private Usuario usuario4 = new Usuario(4L, "userHuesitos", "uH", "Huesitos", RolEnum.COLABORADOR, "Portugal");
    private Usuario usuario5 = new Usuario(5L, "userMarie", "uM", "Marie", RolEnum.COLABORADOR, "Francia");

    @Test
    public void test_create() throws SQLException {
        boolean result = usuario1.create(em);
        Assert.assertTrue(result);
        
        Usuario usuario = Usuario.findById(em, usuario1.getId());
        Assert.assertEquals(usuario1.getId(), usuario.getId());
    }

    @Test
    public void test_findByUsuario() throws SQLException {
        usuario1.create(em);
        usuario2.create(em);
        usuario3.create(em);
        usuario4.create(em);
        usuario5.create(em);
        Usuario usuario = Usuario.findById(em, 2L);
        Assert.assertEquals(usuario2, usuario);
    }

    @Test
    public void test_count() throws SQLException {
        usuario1.create(em);
        usuario2.create(em);
        usuario3.create(em);
        usuario4.create(em);
        usuario5.create(em);
        long count = Usuario.count(em);
        Assert.assertEquals(5, count);
    }

    @Test
    public void test_update() throws SQLException {
        usuario1.create(em);
        usuario2.create(em);
        usuario3.create(em);
        usuario4.create(em);
        usuario5.create(em);
        
        usuario2.setNombre("Manolo");
        usuario2.update(em);
        
        Usuario usuario = Usuario.findById(em, usuario2.getId());
        System.out.println("************************* Nombre modificado:" + usuario.getNombre());
        Assert.assertEquals(usuario2.getNombre(), usuario.getNombre());
    }

    @Test
    public void test_delete() throws SQLException {
        usuario1.create(em);
        usuario2.create(em);
        usuario3.create(em);
        usuario4.create(em);
        usuario5.create(em);
        usuario3.delete(em);
        Usuario usuario = Usuario.findById(em, usuario3.getId());
        Assert.assertNull(usuario);
    }

}
