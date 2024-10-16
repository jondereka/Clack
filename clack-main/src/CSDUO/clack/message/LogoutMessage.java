package CSDUO.clack.message;

import java.util.Objects;

/**
 * This class represents a command to the server, asking for
 * termination of the connection.
 *
 * @author D. Tuinstra, adapted from work by Soumyabrata Dey.
 */
public class LogoutMessage extends Message
{
    /**
     * Constructs a LogoutMessage with a given username.
     * @param username the user sending this message.
     */
    public LogoutMessage(String username)
    {
        //TODO: Implement this. Use ListUsersMessage class as an example);
        super(username, MSGTYPE_LOGOUT);
    }

    /**
     * Return this objects data in a String array. For objects
     * with no data, return an empty array.
     * @return object data, in a String array.
     */
    @Override
    public String[] getData()
    {
        //TODO: Implement this according to JavaDoc. Use ListUsersMessage for an example.
        return new String[0];
    }

    /**
     * Equality comparison. Returns true iff the other object is of
     * the same class and all fields (including those inherited from
     * superclasses) are equal.
     *
     * @param o the object to test for equality.
     * @return whether o is of the same class as this, and all fields
     * are equal.
     */
    @Override
    public boolean equals(Object o)
    {
        //TODO: Implement this according to JavaDoc. Use ListUsersMessage for an example.
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        LogoutMessage that = (LogoutMessage) o;
        return Objects.equals(this.getTimestamp(), that.getTimestamp())
                && Objects.equals(this.getUsername(), that.getUsername());
    }

    /**
     * Return this object's hash. In Message objects, this is simply the
     * hash of the string returned by this.toString().
     *
     * @return hash of this object.
     */
    @Override
    public int hashCode()
    {
        //TODO: Implement this according to JavaDoc. Use ListUsersMessage for an example.
        return this.toString().hashCode();
    }

    /**
     * Constructs a string representation of this object:
     *   "{class=LogoutMessage|" + super.toString() + "}"
     *
     * @return String showing fields and field contents
     */
    @Override
    public String toString()
    {
        //TODO: Implement this according to JavaDoc. Use ListUsersMessage for an example.
        return "{class=LogoutMessage|" + super.toString() + "}";
    }
}
