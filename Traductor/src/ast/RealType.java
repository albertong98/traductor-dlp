/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	realType:type -> 

public class RealType extends AbstractType {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}


	public String toString() {
       return "RealType";
   }
	
	public int getSize() {
		return 4;
	}


	@Override
	public char getSuffix() {
		return 'f';
	}


	@Override
	public String getMAPLName() {
		return "float";
	}
}
