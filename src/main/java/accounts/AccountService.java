package accounts;

import dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountService implements  IAccountService {
    private final Map<String, UsersDataSet> sessionIdToUser;
    private int usersLimit;

    public AccountService()
    {
        sessionIdToUser = new HashMap<>();
        usersLimit = 10;
    }

    public void addSession(String sessionId, UsersDataSet userProfile) {
        sessionIdToUser.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToUser.remove(sessionId);
    }

    public int getUsersLimit() { return usersLimit; }

    public void setUsersLimit(int usersLimit) { this.usersLimit = usersLimit; }
}
