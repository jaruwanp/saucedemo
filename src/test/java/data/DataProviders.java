package data;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "nav buttons")
    public Object[] data(){
        Object[] obj = new String[] {"All Items","About","Logout","Reset App State"};
        return obj;
    }


}
