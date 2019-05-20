/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	if:sentence -> condition:expression  ifSentences:sentence*  elseSentences:sentence*

public class If extends AbstractSentence {

	public If(Expression condition, List<Sentence> ifSentences, List<Sentence> elseSentences) {
		this.condition = condition;
		this.ifSentences = ifSentences;
		this.elseSentences = elseSentences;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(condition, ifSentences, elseSentences);
	}

	@SuppressWarnings("unchecked")
	public If(Object condition, Object ifSentences, Object elseSentences) {
		this.condition = (Expression) ((condition instanceof ParserRuleContext) ? getAST(condition) : condition);
		this.ifSentences = (List<Sentence>) ifSentences;
		this.elseSentences = (List<Sentence>) elseSentences;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(condition, ifSentences, elseSentences);
	}

	public Expression getCondition() {
		return condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Sentence> getIfSentences() {
		return ifSentences;
	}
	public void setIfSentences(List<Sentence> ifSentences) {
		this.ifSentences = ifSentences;
	}

	public List<Sentence> getElseSentences() {
		return elseSentences;
	}
	public void setElseSentences(List<Sentence> elseSentences) {
		this.elseSentences = elseSentences;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression condition;
	private List<Sentence> ifSentences;
	private List<Sentence> elseSentences;

	public String toString() {
       return "{condition:" + getCondition() + ", ifSentences:" + getIfSentences() + ", elseSentences:" + getElseSentences() + "}";
   }
}
