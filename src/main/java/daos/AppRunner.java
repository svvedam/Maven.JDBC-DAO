package daos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        Integer id =2;

        Connection connection = ConnectionFactory.getConnection();
        DAOImplementation daoImpl= new DAOImplementation(connection);

        //Read
        DTO myDto = daoImpl.findById(id);
        System.out.println("Read one value");
        System.out.println(myDto);
        System.out.println("========================================");

        //ReadAll
        List<DTOImplementation> myCarList= new ArrayList<>();
        myCarList = daoImpl.findAll();

        System.out.println("Read multiple values");
        for(DTOImplementation dtoImplementation: myCarList){
            System.out.println(dtoImplementation);
        }
        System.out.println("========================================");

       //Update
        DTOImplementation myDto1 = new DTOImplementation();
        myDto1.setId(1);
        myDto1.setMake("Honda");
        myDto1.setModel("Pilot");
        System.out.println("Update one record");
        DTO myDto2= daoImpl.update(myDto1);
        System.out.println(myDto2);
        System.out.println("========================================");

        //Delete
       /* int id1 = 5;
        daoImpl.delete(id1);
*/
        DTO myDto3 = daoImpl.findById(5);
        System.out.println("Read deleted record #5");
        System.out.println(myDto3);

        //Create
        DTOImplementation myDto4 = new DTOImplementation();

        myDto4.setMake("Nissan");
        myDto4.setModel("Sentra");
        myDto4.setYear(2010);
        myDto4.setColor("Blue");
        myDto4.setVin(6000);
        DTO myDto5= daoImpl.create(myDto4);

        System.out.println("Created one record");
        System.out.println(myDto5);
        System.out.println("========================================");

    }
}
