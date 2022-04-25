package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;
import java.util.Scanner;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.VariableDefinition;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	private static int i; 
	
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		
		//System.out.println("public static void print(IExampleStatemachines) {");
		
		System.out.println("import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;");
		System.out.println("import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;");
		System.out.println("import hu.bme.mit.yakindu.analysis.RuntimeService;");
		System.out.println("import hu.bme.mit.yakindu.analysis.TimerService;\n\n\n");
		
		System.out.println("public class RunStatechart {\n"
				+ "public static void main(String[] args) throws IOException {\n\n"
				+ "ExampleStatemachine s = new ExampleStatemachine();\n"
				+ "s.setTimer(new TimerService());\n"
				+ "RuntimeService.getInstance().registerStatemachine(s, 200);\n"
				+ "s.init();\n"
				+ "s.enter();");
		
		
		System.out.println("		\r\n" + 
				"		Scanner scan = new Scanner(System.in);\r\n" + 
				"		\r\n" + 
				"		while(true) {\r\n" + 
				"		\r\n" + 
				"			switch(scan.nextLine()) {");
		
		
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			/*if(content instanceof State) {
				State state = (State) content;
				System.out.println(state.getName());
			}*/
			
			/*if(content instanceof Transition) {
				Transition transition = (Transition) content;
				System.out.println(transition.getSource().getName() + "->" + transition.getTarget().getName());
			}
			
			if(content instanceof State) {
				State state = (State) content;
				if(state.getOutgoingTransitions().isEmpty())
					System.out.println(state.getName());
			}
			
			if(content instanceof State) {
				State state = (State) content;
				if(state.getName().isEmpty()) {
					System.out.println("State new Name: State" + i );
					i++;
				}	
			}*/
			
			/*if(content instanceof VariableDefinition) {
				VariableDefinition vd = (VariableDefinition) content;
				//System.out.println("Variablel: " + vd.getName());
				char v = Character.toUpperCase(vd.getName().charAt(0));
				
				System.out.println("	System.out.println(\""+ v + " = \" + s.getSCInterface().get" + vd.getName()+ "());");
				//System.out.println("B = " + s.getSCInterface().get<Utolsó változó neve>());
				
				
				
 			}
			
			if(content instanceof EventDefinition) {
				EventDefinition vd = (EventDefinition) content;
				//System.out.println("Event: " + vd.getName());
				
				char v = Character.toUpperCase(vd.getName().charAt(0));
				
				System.out.println("	System.out.println(\""+ v + " = \" + s.getSCInterface().get" + vd.getName()+ "());");
 			}*/
			
			if(content instanceof EventDefinition) {
				EventDefinition vd = (EventDefinition) content;
				//System.out.println("Event: " + vd.getName());
				
				char v = Character.toUpperCase(vd.getName().charAt(0));
				String q = vd.getName();
				q = v + q.substring(1);
				
				
				System.out.println("				case \"" + vd.getName() + "\": \r\n" + 
						"					s.raise+" + q +"();\r\n" + 
						"					s.runCycle();\r\n" + 
						"					System.out.println(s.getSCInterface().getWhiteTime() + \" - \" + s.getSCInterface().getBlackTime());\r\n" + 
						"					break;");
 			}
			
			
				
			
			
		}
		
		System.out.println("				default:\n"
			+ "			System.out.println(s.getSCInterface().getWhiteTime() + \"- \" + s.getSCInterface().getBlackTime());\n"
			+ "			break;\n"
			+"		}\n"
			+ "	}\n"
			+ "}");
		
		//System.out.println("}");
		
		
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}
