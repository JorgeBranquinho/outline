package extensions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import extensionpoints.ISearchEvent;
import extensionpoints.ISearchEventListener;
import pa.iscde.outlaw.Activator;

public class DeepSearchEventImplementation {

	public DeepSearchEventImplementation() {
		exemplo();
	}
	
	private void exemplo(){
		final ISearchEvent event = Activator.getActivator().getSearch_event();
		event.addListener(new ISearchEventListener() {

			@Override
			public void widgetSelected(String text_Search, String text_SearchInCombo, String specificText_ComboSearchIn,
					String text_AdvancedCombo, ArrayList<String> buttonsSelected_AdvancedCombo) {

				if(!text_Search.isEmpty() && text_Search.equals("Outlaw"))
					System.out.println("o termo de procura é Outlaw");
				if(!text_SearchInCombo.isEmpty() && text_SearchInCombo.equals("Package")){
					System.out.println("o item a procurar está num Package");
					if(!specificText_ComboSearchIn.isEmpty())
						System.out.println("...mais especificamente o package: " + specificText_ComboSearchIn);
				}
				if(!buttonsSelected_AdvancedCombo.isEmpty()){
					Set<String> hs = new HashSet<>();//remover duplicados
					hs.addAll(buttonsSelected_AdvancedCombo);
					buttonsSelected_AdvancedCombo.clear();
					buttonsSelected_AdvancedCombo.addAll(hs);
					for(String s: buttonsSelected_AdvancedCombo)
						if(s.equals("public"))
							System.out.println("e é publico");
				}
			}
		});
	}
}
