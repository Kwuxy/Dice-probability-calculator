package fr.guenin.vincent.dice_probability_calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RpnConverterTest {
    private RpnConverter rpnConverter;

    public RpnConverterTest() {
        List<Operator> library = new LinkedList<>();
        library.add(new Plus());

        this.rpnConverter = new RpnConverter(library);
    }

    @Test
    public void should_convert_simple_addition() {
        assertThat(rpnConverter.arithmeticExpressionToRpn("1 + 2"))
                .isEqualTo("1 2 +");
    }

    @Test
    public void should_convert_complex_addition() {
        assertThat(rpnConverter.arithmeticExpressionToRpn("3 + 4 + 5 + 10 + 8 + -5 + 9"))
                .isEqualTo("3 4 + 5 + 10 + 8 + -5 + 9 +");
    }

    @Test
    public void should_convert_addition_with_parenthesis() {
        assertThat(rpnConverter.arithmeticExpressionToRpn("1 + ( 2 + 3 ) + 4"))
                .isEqualTo("1 2 3 + + 4 +");
    }

    @Test
    public void should_convert_addition_with_multiple_parenthesis() {
        assertThat(rpnConverter.arithmeticExpressionToRpn("1 + ( 2 + ( 3 + 4 ) ) + 5"))
                .isEqualTo("1 2 3 4 + + + 5 +");
    }

}
