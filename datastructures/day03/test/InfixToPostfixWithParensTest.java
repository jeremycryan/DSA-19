import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfixToPostfixWithParensTest {

    @Test
    public void infixToPostfixCaseOne() {
        System.out.println("( 1 + 2 )");
        assertEquals("1 2 +", Problems.infixToPostfix("( 1 + 2 )"));
    }


    @Test
    public void infixToPostfixCaseTwo() {
        System.out.println("( ( 2 * 3 ) + 1 )");
        assertEquals("2 3 * 1 +", Problems.infixToPostfix("( ( 2 * 3 ) + 1 )"));
    }


    @Test
    public void infixToPostfixCaseThree() {
        System.out.println("( 2 * ( 3 + 1 ) )");
        assertEquals("2 3 1 + *", Problems.infixToPostfix("( 2 * ( 3 + 1 ) )"));
    }


    @Test
    public void infixToPostfixCaseFour() {
        System.out.println("( ( 2 * 3 ) + 1 )");
        assertEquals("2 3 * 1 +", Problems.infixToPostfix("( ( 2 * 3 ) + 1 )"));
    }

    @Test
    public void infixToPostfixCaseFive() {
        System.out.println("( ( 1 * ( 2 + 4 ) ) * 3 )");
        assertEquals("1 2 4 + * 3 *", Problems.infixToPostfix("( ( 1 * ( 2 + 4 ) ) * 3 )"));
    }


    @Test
    public void infixToPostfixCaseSix() {
        System.out.println("( ( 1 * ( 6 * ( 2 + 4 ) ) ) + 3 )");
        assertEquals("1 6 2 4 + * * 3 +", Problems.infixToPostfix("( ( 1 * ( 6 * ( 2 + 4 ) ) ) + 3 )"));
    }
}
