/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	while:sentence -> condition:expression  sentences:sentence*

public class While extends AbstractSentence {

	public While(Expression condition, List<Sentence> sentences) {
		this.condition = condition;
		this.sentences = sentences;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(condition, sentences);
	}

	@SuppressWarnings("unchecked")
	public While(Object condition, Object sentences) {
		this.condition = (Expression) ((condition instanceof ParserRuleContext) ? getAST(condition) : condition);
		this.sentences = (List<Sentence>) sentences;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(condition, sentences);
	}

	public Expression getCondition() {
		return condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}
	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression condition;
	private List<Sentence> sentences;

	public String toString() {
       return "{condition:" + getCondition() + ", sentences:" + getSentences() + "}";
   }
}
