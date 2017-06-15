package framework.algoritmosbusqueda;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
/**
 * @author http://stackoverflow.com/questions/18400210/java-8-where-is-trifunction-and-kin-in-java-util-function-or-what-is-the-alt
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <R>
 */
public interface TriFunction<A, B, C, R> {

	R apply(A a, B b, C c);

	default <V> TriFunction<A, B, C, V> andThen(Function<? super R, ? extends V> after) {
		Objects.requireNonNull(after);
		return (A a, B b, C c) -> after.apply(apply(a, b, c));
	}
}