package aptrakov.insaf;

import org.junit.jupiter.api.*;

public class SimpleJUnitTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("### @BeforeAll\n ");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("### @AfterAll ");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("###   @BeforeEach ");
    }

    @AfterEach
    void afterEach(){
        System.out.println("###   @AfterEach\n ");
    }

    @Test
    void firstTest(){
        System.out.println("###    @Test firstTest\n");
        Assertions.assertTrue(2 * 2 == 4);
    }

    @Test
    void secondTest(){
        System.out.println("###    @Test secondTest\n ");
        Assertions.assertTrue(2 + 2 == 4);
    }
}
