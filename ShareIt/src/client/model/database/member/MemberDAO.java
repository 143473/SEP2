package client.model.database.member;

import ground_classes.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO {
    Member create(String username, String password, String emailAddress, String otherInformation, String addressStreet, String addressNo, int addressPostalCode, String addressCity) throws SQLException;
    List<Member> readByUsername(String username) throws SQLException;
    void update(Member member) throws SQLException;
    void delete(Member member) throws SQLException;
}