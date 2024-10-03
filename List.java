import java.util.ArrayList;

public class List implements Set{

    private ArrayList<Integer> list;
    
    public List() {
        this.list =  new ArrayList<>();
    }
    
    @Override
    public void add(int value) {
    
        list.add(value);
    }
    
    @Override
    public boolean exist(int value) {
        
        return list.contains(value);
    }
    
    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }
    
}


