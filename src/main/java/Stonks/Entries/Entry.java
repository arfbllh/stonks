package Stonks.Entries;

import javafx.scene.control.ListCell;

import java.io.Serializable;
import java.util.Vector;

public class Entry implements Serializable {
    private String name;
    public Vector<String> tags;
    private int amount, recordId, entryId;
    private boolean isCashIn;

    public Entry(String name, Vector<String> tags, int amount, boolean isCashIn, int recordId, int entryId){
        this.name= name;
        this.amount= amount;
        this.tags= tags;
        this.isCashIn= isCashIn;
        this.recordId= recordId;
        this.entryId= entryId;
    }

    public int getId(){
        return entryId;
    }

    public int getRecordId(){
        return recordId;
    }

    public String getName(){
        return name;
    }

    public int getAmount(){
        return amount;
    }

    public String getTag(){
        return tags.get(0);
    }

    public boolean getCashInStatus(){
        return isCashIn;
    }

    public void addTag(String tag){
        tags.add(tag);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "name='" + name + '\'' +
                ", tags=" + tags +
                ", amount=" + amount +
                ", recordId=" + recordId +
                ", entryId=" + entryId +
                ", isCashIn=" + isCashIn +
                '}';
    }
}
