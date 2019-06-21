/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	charConstant:expression -> value:String

public class CharConstant extends AbstractExpression {

	public CharConstant(String value) {
		this.value = value;
	}

	public CharConstant(Object value) {
		this.value = (value instanceof Token) ? ((Token)value).getText() : (String) value;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(value);
	}

	public String getValue() {
		if(value.length() > 3)
			return parseSpecialChar();
		else
		    return value.substring(1,value.length()-1);
	}

	private String parseSpecialChar() {
		if(value.contains("n"))
			return "\n";
		if(value.contains("t"))
			return "\t";
		if(value.contains("r"))
			return "\r";
		if(value.contains("b"))
		    return "\b";
		return value.substring(1,value.length()-1);
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String value;

	public String toString() {
       return "{value:" + getValue() + "}";
   }
}
