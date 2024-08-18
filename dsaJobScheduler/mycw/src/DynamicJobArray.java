import java.util.*;

public class DynamicJobArray {
    int i=0;

    private Job[] array;


    public DynamicJobArray() {
        this.array = new Job[10]; // Initial capacity
    }

    public DynamicJobArray(int capacity) {
        this.array = new Job[capacity];
    }

    public Job get(int index) {
        if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return array[index];
    }

    public void add(Job job) {

        if (job.id >= array.length) {
            resize(job.id);
        }
        array[i++] = job;
    }

    private void resize(int newCapacity) {
        Job[] newArray = new Job[newCapacity + 1]; // +1 to handle zero indexing
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }


    public int size() {
        return array.length;
    }

    public boolean isEmpty() {
        for (Job job : array) {
            if (job != null) {
                return false;
            }
        }
        return true;
    }

    public void show() {
        for(Job element : array) {
            if (element != null) {
                System.out.println("Job Id:"+ element.getId() +" Job Name:" + element.getJobName());
            }
        }
    }

    public int getIndex(int id) {
        for(int i=0;i<size();i++){
            if(array[i].getId()==id){
                return i;
            }
        }
        return -1;
    }

}
