package FragmentPages;

/*
 * Created by sean on 1/31/2015.
 *
*/

public class TeaData {
    private int size;
    private String flavor;
    private String tapiocaType;
    private int id;

    public TeaData(){}

    public TeaData(int size, String flavor, String tapiocaType, int id) {
        super();
        this.size = size;
        this.flavor = flavor;
        this.tapiocaType = tapiocaType;
        this.id = id;
    }

    public int getSize() {
        return this.size;
    }

    public String getTapiocaType() {
        return this.tapiocaType;
    }

    public String getFlavor() {
        return this.flavor;
    }

    public int getId() {
        return this.id;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTapiocaType(String tapiocaType) {
        this.tapiocaType = tapiocaType;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "size = " + size + " oz\nflavor = " + flavor + "\ntapioca type = " + tapiocaType + "\n";
    }
}
