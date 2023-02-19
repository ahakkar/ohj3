public class ValueNode extends Node {
    private String value;
    private Double value_double;
    private Boolean value_bool;

    public ValueNode() {
        this.value = null;
    }

    public ValueNode(double value) {
        this.value_double = value;
    }

    public ValueNode(boolean value) {
        this.value_bool = value;
    }

    public ValueNode(String value) {
        this.value = value;
    }

    public Object getNull () {
        return null;
    }

    public double getNumber() {
        return value_double;
    }

    public boolean getBoolean() {
        return value_bool;
    }

    public String getString() {
        return value;
    }

    public boolean isNumber() {
        if (value_double != null) {
            return true;
        }
        return false;
    }
    public boolean isBoolean() {
        if (value_bool != null) {
            return true;
        }
        return false;
    } 
    public boolean isString() {
        if (value != null) {
            return true;
        }
        return false;     
    } 
    public boolean isNull() {
        if (value == null) {
            return true;
        }
        return false;
    }
}
