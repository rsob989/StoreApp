package pl.store.data;

import pl.store.data.format.CsvFileManager;
import pl.store.data.format.DataTypeOptions;
import pl.store.data.format.SerializableFileManager;
import pl.store.logic.ContactWithUser;

public class DataManager {

    ContactWithUser cwu;

    public DataManager(ContactWithUser cwu) {
        this.cwu = cwu;
    }

    public FileManager chooseDataType(){
        FileManager fm = null;
        cwu.printer("Choose data type: ");
        for(DataTypeOptions d : DataTypeOptions.values()){
            cwu.printer(d.name());
        }
        String choice = cwu.getString().toUpperCase();
        DataTypeOptions dataType = DataTypeOptions.valueOf(choice);
        switch(dataType){
            case CSV:
                fm = new CsvFileManager();
                break;
            case SERIALIZABLE:
                fm = new SerializableFileManager();
                break;
            default:
        }
        return fm;
    }

}
