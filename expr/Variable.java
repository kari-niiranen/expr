// Variables associate values with names.
// Copyright 1996 by Darius Bacon; see the file COPYING.

package expr;

import java.util.Hashtable;

/**
 * A variable is a simple expression with a name (like "x") and a
 * settable value.
 * 
 * If the {@link #make(String)} method is used to create the variable
 * it is added to the global scope. To add Parser specific variables,
 * create the variable using the constructor and allow the variable
 * using {@link Parser#allow(Variable)}. Parser specific variables
 * will override global values.
 */
public class Variable extends Expr {
    private static Hashtable variables = new Hashtable();
    
    /**
     * Return a unique variable named `name'.  There can be only one
     * variable with the same name returned by this method; that is,
     * make(s1) == make(s2) if and only if s1.equals(s2).
     * @param name the variable's name
     * @return the variable; create it initialized to 0 if it doesn't
     *         yet exist */
    static public synchronized Variable make(String name) {
	Variable result = (Variable) variables.get(name);
	if (result == null)
	    variables.put(name, result = new Variable(name));
	return result;
    }

    private String name;
    private double val;

    /**
     * Create a new variable, with initial value 0. Note that if 
     * this is called directly, the variable is not added to the
     * global variable scope or the parsers scope automatically.
     * For global scope usage, one should call {@link #make(String)}
     * method instead.
     *
     * The variable can be added to the parser specific scope by
     * calling {@link Parser#allow(Variable)}.
     * @param name the variable's name
     */
    public Variable(String name) { 
	this.name = name; val = 0; 
    }

    /** Return the name. */
    public String toString() { return name; }
    
    /** Return the name. */
    public String getName() { return name; }

    /** Get the value.
     * @return the current value */
    public double value() { 
	return val; 
    }
    /** Set the value.
     * @param value the new value */
    public void setValue(double value) { 
	val = value; 
    }
}
