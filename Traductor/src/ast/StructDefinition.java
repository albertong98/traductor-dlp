/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	structDefinition:definition -> structtype:structType  definition:definition*

public class StructDefinition extends AbstractDefinition {

	public StructDefinition(StructType structtype, List<Definition> definition) {
		this.structtype = structtype;
		this.definition = definition;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(structtype, definition);
	}

	@SuppressWarnings("unchecked")
	public StructDefinition(Object structtype, Object definition) {
		this.structtype = (StructType) ((structtype instanceof ParserRuleContext) ? getAST(structtype) : structtype);
		this.definition = (List<Definition>) definition;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(structtype, definition);
	}

	public StructType getStructtype() {
		return structtype;
	}
	public void setStructtype(StructType structtype) {
		this.structtype = structtype;
	}

	public List<Definition> getDefinition() {
		return definition;
	}
	public void setDefinition(List<Definition> definition) {
		this.definition = definition;
	}
	
	@Override
	public Type getType() {
		return structtype;
	}
	
	@Override
	public void setType(Type type) {
		this.structtype = (StructType) type;
	}
	
	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}
	
	public boolean buscaCampo(String nombre) {
		return false;
	}

	private StructType structtype;
	private List<Definition> definition;

	public String toString() {
		  StringBuffer sb = new StringBuffer();
		  sb.append("{\n");
		  for(Definition def: definition){
			  sb.append(((Campo)def).getName() + ":" + ((Campo) def).getType());
			  sb.append("\n");
		  }
		  sb.append("}");
		  return sb.toString();
   }
}
