package accounts;

import dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, UsersDataSet> sessionIdToUser;

    public AccountService()
    {
        sessionIdToUser = new HashMap<>();
    }

    public void addSession(String sessionId, UsersDataSet userProfile) {
        sessionIdToUser.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToUser.remove(sessionId);
    }
}
