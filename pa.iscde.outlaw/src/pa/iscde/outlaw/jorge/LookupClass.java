package pa.iscde.outlaw.jorge;

import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class LookupClass extends AbstractProcessor implements OutlineLookup{

	private Multimap<String, String> children = ArrayListMultimap.create();
	
	@Override
	public List<String> getChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getVisibility() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
		Messager messager = processingEnv.getMessager();
		Set<? extends Element> elements = env.getElementsAnnotatedWith(OutlineCLass.class);

		for(Element e : elements) {
			if(e.getKind() == ElementKind.CLASS){
				
				ExecutableElement ex= (ExecutableElement) e;
//				if(ex.getModifiers().contains(Modifier.))
				
				
				List<? extends Element> children = e.getEnclosedElements();
				for(Element c: children){
					if(e.getKind() == ElementKind.METHOD){
						ExecutableElement ex2= (ExecutableElement) c;
						if(ex2.getModifiers().contains(Modifier.STATIC)){
							messager.printMessage(Kind.ERROR, "must be static", ex2);
						}
					}
				}
			}
		}
		return true;
	}

}
//services.parseFile(f, new )
