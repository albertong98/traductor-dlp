/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	intType:type -> 

public class IntType extends AbstractType {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}


	public String toString() {
       return "IntType";
	}
	
	public int getSize() {
		return 2;
	}


	@Override
	public char getSuffix() {
		return 'i';
	}


	@Override
	public String getMAPLName() {
		return "int";
	}
}
