package curso.karate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CompetidorTest {
    
    private EntityManager em;

    private static Competidor comp1;
    private static Competidor comp2;
    private static Competidor comp3;
    private static Competidor comp4;
    private static Competidor comp5;
    private static Categoria cat1;
    private static Categoria cat2;
    private static Categoria cat3;
    private static Categoria cat4;

    @Before
    public void beforeTest() throws SQLException, ParseException {       
        Connection conn = ConnectionManager.getConnection();
        Statement stmt1 = conn.createStatement();
        stmt1.execute("TRUNCATE TABLE competidores");
        stmt1.close();

        conn.close();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KaratePU");
        em = emf.createEntityManager();

        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        cat1 = new Categoria(1L, "Cat 1", BigDecimal.valueOf(50), BigDecimal.valueOf(55), 16, 18, SexoCategoriaEnum.MIXTA, ModalidadEnum.KATA);
        cat2 = new Categoria(2L, "Cat 2", BigDecimal.valueOf(50), BigDecimal.valueOf(55), 16, 18, SexoCategoriaEnum.MIXTA, ModalidadEnum.KUMITE);
        cat3 = new Categoria(3L, "Cat 3", BigDecimal.valueOf(70), BigDecimal.valueOf(75), 16, 18, SexoCategoriaEnum.MIXTA, ModalidadEnum.KATA);
        cat4 = new Categoria(4L, "Cat 4", BigDecimal.valueOf(70), BigDecimal.valueOf(75), 16, 18, SexoCategoriaEnum.MIXTA, ModalidadEnum.KUMITE);

        List<Categoria> cat = new ArrayList<Categoria>();
        cat.add(cat1);
        cat.add(cat2);
        comp1 = new Competidor(1L, "Julian Estévez", BigDecimal.valueOf(52.5), 21, SexoCompetidorEnum.FEMENINO, "España", "C:\foto", cat);
        comp2 = new Competidor(2L, "Chin Champú", BigDecimal.valueOf(51.5), 21, SexoCompetidorEnum.FEMENINO, "Corea Sur", "C:\foto", cat);
        cat.clear();
        cat.add(cat3);
        cat.add(cat4);
        comp3 = new Competidor(3L, "Pacal Simpson", BigDecimal.valueOf(71.5), 21, SexoCompetidorEnum.MASCULINO, "Francia", "C:\foto", cat);
        comp4 = new Competidor(4L, "Bellini de la Rosa", BigDecimal.valueOf(72.5), 21, SexoCompetidorEnum.MASCULINO, "Italia", "C:\foto", cat);
        comp5 = new Competidor(5L, "Santos Miliki", BigDecimal.valueOf(73.5), 21, SexoCompetidorEnum.MASCULINO, "España", "C:\foto", cat);
    }

    @Test
    public void test_addCompetidor_CompNoExiste() {
        boolean result = comp1.create(em);
        result = result && comp2.create(em);
        assertTrue(result);

        assertTrue(Competidor.contains(em, comp1.getId()));
        assertEquals(2, Competidor.count(em));

        assertEquals(comp1, Competidor.findById(em, comp1.getId()));
    }

    @Test
    public void test_addCompetidor_CompExiste() {
        comp1.create(em);
        boolean result = comp1.create(em);
        assertFalse(result);
    }

    @Test
    public void test_removeCompetidor() {
        comp1.create(em);
        boolean result = comp1.remove(em);
        assertTrue(result);

        result = comp1.remove(em);
        assertFalse(result);

        assertFalse(Competidor.contains(em, comp1.getId()));
        assertEquals(0, Competidor.count(em));
    }

    @Test
    public void test_list() {
        comp1.create(em);
        comp2.create(em);
        comp3.create(em);

        Competidor[] expected = new Competidor[]{comp1, comp2, comp3};

        Competidor[] competidores = Competidor.findAll(em).toArray(new Competidor[0]);
        assertEquals(3, competidores.length);
        assertArrayEquals(expected, competidores);
    }

    @Test
    public void test_update() throws SQLException {
        comp1.create(em);
        comp2.create(em);
        comp3.create(em);
        comp4.create(em);
        comp5.create(em);

        comp1.setNombre("Pepe");
        comp1.update(em);

        Competidor competidor = Competidor.findById(em, comp1.getId());

        Assert.assertEquals(comp1.getNombre(), competidor.getNombre());
    }
        
//    @Test
//    public void test_findByISBN() {
//        ajkr.create(em);
//        ago.create(em);
//
//        bhp1.create(em);
//        bhp2.create(em);
//        b1984.create(em);
//
//        Book result = Book.findByISBN(em, b1984.getIsbn());
//
//        assertEquals(b1984, result);
//    }
//
//    @Test
//    public void test_findByYear() {
//        ajkr.create(em);
//        ago.create(em);
//        ada.create(em);
//        ame.create(em);
//
//        bhp1.create(em);
//        bhp2.create(em);
//        b1984.create(em);
//        bhi.create(em);
//        bga.create(em);
//
//        List<Book> result = Book.findByYear(em, 1979);
//
//        assertEquals(2, result.size());
//        assertTrue(result.contains(bhi));
//        assertTrue(result.contains(bga));
//    }
//
//    @Test
//    public void test_findByAuthor1() {
//        ajkr.create(em);
//        ago.create(em);
//        ada.create(em);
//        ame.create(em);
//
//        bhp1.create(em);
//        bhp2.create(em);
//        b1984.create(em);
//        bhi.create(em);
//        bga.create(em);
//
//        em.refresh(ajkr);
//        ajkr = Author.findById(em, ajkr.getId());
//        List<Book> result = ajkr.getBooks();
//
//        assertEquals(2, result.size());
//        assertTrue(result.contains(bhp1));
//        assertTrue(result.contains(bhp2));
//    }
//
//    @Test
//    public void test_findByAuthor2() {
//        ajkr.create(em);
//        ago.create(em);
//        ada.create(em);
//        ame.create(em);
//
//        bhp1.create(em);
//        bhp2.create(em);
//        b1984.create(em);
//        bhi.create(em);
//        bga.create(em);
//
//        List<Book> result = Book.findByAuthor(em, ajkr);
//
//        assertEquals(2, result.size());
//        assertTrue(result.contains(bhp1));
//        assertTrue(result.contains(bhp2));
//    }
//
//
//    @Test
//    public void test_refresh() throws SQLException {
//        ajkr.create(em);
//        ago.create(em);
//        ada.create(em);
//        ame.create(em);
//
//        bhp1.create(em);
//        bhp2.create(em);
//        b1984.create(em);
//        bhi.create(em);
//        bga.create(em);
//
//        String url = "jdbc:mysql://127.0.0.1/test";
//        String user = "root";
//        String pass = "";
//        Connection conn = DriverManager.getConnection(url, user, pass);
//        Statement stmt1 = conn.createStatement();
//        stmt1.execute("UPDATE authors SET name = 'FOO' WHERE id = 1");
//        stmt1.close();
//
//        System.out.println("BEFORE REFRESH:" + ajkr);
//        System.out.println("BEFORE REFRESH:" + ajkr.getBooks());
//
//        em.refresh(ajkr);
//
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {}
//
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss S");
//
//        System.out.println(sdf.format(new Date()) + " AFTER REFRESH:" + ajkr);
//        System.out.println(sdf.format(new Date()) + "AFTER REFRESH:" + ajkr.getBooks());
//
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {}
//
//        ajkr = Author.findById(em, 1l);
//
//        System.out.println(sdf.format(new Date()) + "AFTER FIND:" + ajkr);
//        System.out.println(sdf.format(new Date()) + "AFTER FIND:" + ajkr.getBooks());
//    }
}
