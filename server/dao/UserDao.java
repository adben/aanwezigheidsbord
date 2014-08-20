import java.util.List;

public interface UserDao {
	void checkInUser(String userName);

	void checkOutUser(String userName);

	List<String> getCheckedInUsers();
}
