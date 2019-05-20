/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	structType:type -> name:String

public class StructType extends AbstractType {

	public StructType(String name) {
		this.name = name;
		this.size = 0;
	}

	public StructType(Object name) {
		this.name = (name instanceof Token) ? ((Token)name).getText() : (String) name;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(name);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String name;
	private int size;
	
	public String toString() {
       return "{name:" + getName() + "}";
   }
	
	public int getSize() {
		return size;
	}
	
	public void addSize(int size) {
		this.size += size;
	}

	@Override
	public char getSuffix() {
		return 's';
	}

	@Override
	public String getMAPLName() {
		return "struct";
	}
}
