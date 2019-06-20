/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	funcDefinition:definition -> name:String  params:definition*  defs:definition*  sentence:sentence*  type:type

public class FuncDefinition extends AbstractDefinition {

	public FuncDefinition(String name, List<Definition> params, List<Definition> defs, List<Sentence> sentence, Type type) {
		this.name = name;
		this.params = params;
		this.defs = defs;
		this.sentence = sentence;
		this.type = type;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(params, defs, sentence, type);
	}

	@SuppressWarnings("unchecked")
	public FuncDefinition(Object name, Object params, Object defs, Object sentence, Object type) {
		this.name = (name instanceof Token) ? ((Token)name).getText() : (String) name;
		this.params = (List<Definition>) params;
		this.defs = (List<Definition>) defs;
		this.sentence = (List<Sentence>) sentence;
		this.type = (Type) ((type instanceof ParserRuleContext) ? getAST(type) : type);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(name, params, defs, sentence, type);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Definition> getParams() {
		return params;
	}
	public void setParams(List<Definition> params) {
		this.params = params;
	}

	public List<Definition> getDefs() {
		return defs;
	}
	public void setDefs(List<Definition> defs) {
		this.defs = defs;
	}

	public List<Sentence> getSentence() {
		return sentence;
	}
	public void setSentence(List<Sentence> sentence) {
		this.sentence = sentence;
	}
	@Override
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String name;
	private List<Definition> params;
	private List<Definition> defs;
	private List<Sentence> sentence;
	private Type type;
	
	public int getParamSize(){
		int paramSize = 0;
		for(Definition def:params)
			paramSize += def.getType().getSize();
		return paramSize;
	}

	public int getDefsSize(){
		int defSize = 0;
		for(Definition def:defs)
			defSize += def.getType().getSize();
		return defSize;
	}
	public String toString() {
       return "{name:" + getName() + ", params:" + getParams() + ", defs:" + getDefs() + ", sentence:" + getSentence() + ", type:" + getType() + "}";
   }
}
