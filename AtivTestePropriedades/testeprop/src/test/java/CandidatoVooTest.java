import com.bcopstein.CandidatoVoo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.arbitraries.IntegerArbitrary;
import net.jqwik.api.constraints.FloatRange;

public class CandidatoVooTest {
    
    @Property
    void pass (@ForAll @FloatRange(min = 50.1f, max = 100.9f, maxIncluded = true) float peso,
               @ForAll @FloatRange(min = 1.521f, max = 1.929f, maxIncluded = true) float altura,
               @ForAll @FloatRange(max = 79.9, maxIncluded = true)) {
                
               }

    @Provide
    private Arbitrary<Float> pesoInvalido() {
        return Arbitraries.oneOf(
            Arbitraries.floats().lessOrEqual(50.0f),
            Arbitraries.floats().greaterOrEqual(101.0f));
    }

    @Provide
    private Arbitrary<Float> alturaInvalida() {
        return Arbitraries.oneOf(
            Arbitraries.floats().lessOrEqual(1.52f),
            Arbitraries.floats().greaterOrEqual(1.93f));
    }
}
