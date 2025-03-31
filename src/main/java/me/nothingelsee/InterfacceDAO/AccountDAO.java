package me.nothingelsee.InterfacceDAO;

/**
 * The interface Account dao.
 */
public interface AccountDAO {
    /**
     * Is in boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean isIn(String username, String password);
}
