package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImplementation extends DAO{

    protected final Connection connection;
    private static final String GET_ONE="SELECT * FROM car where id =?";
    private static final String GET_ALL="SELECT * FROM car";
    private static final String UPDATE="UPDATE car SET MODEL = ? where make =?";
    private static final String DELETE ="DELETE FROM car where id=?";
    private static final String INSERT= "INSERT INTO car (make, model, year, color, vin)"
            + " values (?, ?, ?, ?, ?)";
    public DAOImplementation(Connection connection) {

        this.connection = connection;
    }

    public DTO findById(int id) {
        DTOImplementation dtoImpl = null;
        ResultSet rs=null;
        try(PreparedStatement pstmt = connection.prepareStatement(GET_ONE);){
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            dtoImpl = new DTOImplementation();

            while(rs.next()){
                dtoImpl.setId(rs.getInt("id"));
                dtoImpl.setMake(rs.getString("make"));
                dtoImpl.setModel(rs.getString("model"));
                dtoImpl.setYear(rs.getInt("year"));
                dtoImpl.setColor(rs.getString("color"));
                dtoImpl.setVin(rs.getInt("vin"));
            }

        }catch (Exception e){
           e.printStackTrace();
        }
        return dtoImpl;
    }

    public List findAll() {
        List<DTOImplementation> myList = new ArrayList<>();

        ResultSet rs=null;
        try(PreparedStatement pstmt = connection.prepareStatement(GET_ALL);){
            rs = pstmt.executeQuery();
            rs=pstmt.getResultSet();

            while(rs.next()){
                DTOImplementation dtoImpl = new DTOImplementation();
                dtoImpl.setId(rs.getInt("id"));
                dtoImpl.setMake(rs.getString("make"));
                dtoImpl.setModel(rs.getString("model"));
                dtoImpl.setYear(rs.getInt("year"));
                dtoImpl.setColor(rs.getString("color"));
                dtoImpl.setVin(rs.getInt("vin"));
                myList.add(dtoImpl);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return myList;
    }

    public DTO update(DTO dto) {
        ResultSet rs=null;
        DTO returnValue = new DTOImplementation();

        try(PreparedStatement pstmt = connection.prepareStatement(UPDATE);){
            pstmt.setString(1,dto.getModel());
            pstmt.setString(2,dto.getMake());
            int i = pstmt.executeUpdate();
            returnValue = findById(1);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return returnValue;
    }

    public DTO create(DTO dto) {

            ResultSet rs=null;
            DTO returnValue = new DTOImplementation();

            try(PreparedStatement pstmt = connection.prepareStatement(INSERT);){
                pstmt.setString(1,dto.getMake());
                pstmt.setString(2,dto.getModel());
                pstmt.setInt(3,dto.getYear());
                pstmt.setString(4,dto.getColor());
                pstmt.setInt(5,dto.getVin());
                pstmt.executeUpdate();
                returnValue = findById(6);

            }catch (SQLException e){
                e.printStackTrace();
            }
            return returnValue;
    }

    public void delete(int id) {

        DTO returnValue = new DTOImplementation();
        ResultSet rs=null;
        try(PreparedStatement pstmt = connection.prepareStatement(DELETE);){
            pstmt.setInt(1,id);
            int i = pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
