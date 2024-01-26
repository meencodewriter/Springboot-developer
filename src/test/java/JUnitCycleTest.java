import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleTest {

    /**
     * 전체 테스트를 실행하기 전 1회 실행하는 메서드 -> static으로 선언
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll()");
    }

    @Test()
    public void test1() {
        System.out.println("Test1");
    }
    @Test()
    public void test2() {
        System.out.println("Test2");
    }
    @Test()
    public void test3() {
        System.out.println("Test3");
    }
    @AfterAll()
    static void afterAll() {
        System.out.println("@AfterAll()");
    }
    @BeforeEach()
    public void beforeEach() {
        System.out.println("@BeforeEach()");
    }
}
