package mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Flag
{
	
	String name() default "";
	
	Control control() default Control.text;
	
	String onOpen() default " ";
	
	String onClose() default " ";
	
}
