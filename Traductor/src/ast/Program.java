/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	program -> definitions:definition*

public class Program extends AbstractAST  {

	public Program(List<Definition> definitions) {
		this.definitions = definitions;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(definitions);
	}

	@SuppressWarnings("unchecked")
	public Program(Object definitions) {
		this.definitions = (List<Definition>) definitions;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(definitions);
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}
	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private List<Definition> definitions;

	public String toString() {
       return "{definitions:" + getDefinitions() + "}";
   }
}
