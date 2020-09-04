package pl.store.data;

import pl.store.data.format.CsvFileManager;
import pl.store.app.menus.DataTypeMenu;
import pl.store.data.format.SerializableFileManager;
import pl.store.logic.ContactWithUser;

public class FileManagerBuilder {

    ContactWithUser cwu;

    public FileManagerBuilder(ContactWithUser cwu) {
        this.cwu = cwu;
    }

    public FileManager chooseDataType(){
        FileManager fm = null;
        DataTypeMenu dataType = cwu.choice(DataTypeMenu.class);
        switch(dataType){
            case CSV:
                fm = new CsvFileManager();
                break;
            case SERIALIZABLE:
                fm = new SerializableFileManager();
                break;
        }
        return fm;
    }

}
