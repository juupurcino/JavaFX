import java.util.ArrayList;

public class List implements Set{

    private ArrayList<Integer> listExist;
    private ArrayList<Integer> listUnity;
    private ArrayList<Integer> listIntersection;
    private ArrayList<Integer> listDifference;
    
    public List() {
        this.listExist =  new ArrayList<>();
        this.listUnity = new ArrayList<>();
        this.listIntersection = new ArrayList<>();
        this.listDifference = new ArrayList<>();
    }
    
    @Override
    public void add(int value) {
        
        listExist.add(value);
    }
    
    @Override
    public boolean exist(int value) {
        
        return listExist.contains(value);
    }
    
    @Override
    public ArrayList<Integer> unity(List list1, List list2) {
        
        for (int i = 0; i < list1.getList().size(); i++) {
            int element = list1.getList().get(i);
            listUnity.add(element);
        }
        
        for (int i = 0; i < list2.getList().size(); i++) {
            int element = list2.getList().get(i);
            
            if (!list1.exist(element)) {
                
                listUnity.add(element);
            }
        }
        
        return listUnity;
    }
    
    @Override
    public ArrayList<Integer> intersection(List list1, List list2) {
        
        for (int i = 0; i < list1.getList().size(); i++) {
            int element = list1.getList().get(i);
            
            if (list2.exist(element)) {
                
                listIntersection.add(element);
            }
        }
        
        return listIntersection;
    }
    
    @Override
    public ArrayList<Integer> difference(List list1, List list2) {

        for (int i = 0; i < list1.getList().size(); i++) {
            int element = list1.getList().get(i);
            
            if (!list2.exist(element)) {
                
                listDifference.add(element);
            }
        }
        
        return listDifference;
        
    }
    
    public ArrayList<Integer> getList() {
        return listExist;
    }
    
    public void setList(ArrayList<Integer> list) {
        this.listExist = list;
    }
    
    public ArrayList<Integer> getListUnity() {
        return listUnity;
    }
    
    public void setListUnity(ArrayList<Integer> listUnity) {
        this.listUnity = listUnity;
    }
    
    public ArrayList<Integer> getListIntersection() {
        return listIntersection;
    }
    
    public void setListIntersection(ArrayList<Integer> listIntersection) {
        this.listIntersection = listIntersection;
    }

    public ArrayList<Integer> getListDifference() {
        return listDifference;
    }
    
    public void setListDifference(ArrayList<Integer> listDifference) {
        this.listDifference = listDifference;
    }

}


