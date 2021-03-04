package testutil;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestTool {
	
	public static void prn(Object o) {
		System.out.println(o.toString());
	}	
	
	public static void printf(String format, Object... args) {
		System.out.printf(format, args);
	}	
	
	public static <T, S> List<S> map(List<T> list, Function<T, S> mapFct) {
		return
			list.stream()
				.map(mapFct)
				.collect(Collectors.toList());
	}

}

