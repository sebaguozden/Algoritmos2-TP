package mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Flag
{
	
	Control control() default Control.text;
	
	String onOpen() default " ";
	
	String name() default "";
	
	String onClose() default " ";
	
	String post() default "";

	Class<? extends FlagValidator> validator() default FlagValidator.class;

	String post2() default "";

	Class<? extends Conversor> conversor() default Conversor.class;
	
}
