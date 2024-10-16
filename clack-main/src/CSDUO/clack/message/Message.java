package CSDUO.clack.message;

//import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abstract base class for Clack messages.
 *
 * @author D. Tuinstra, adapted from work by Soumyabrata Dey.
 */
public abstract class Message
{
    //TODO: JavaDoc for each of these public "manifest constants"
    /**
     * MSGTYPE_ENCRYPTION a type that is involved in sending or receiving an encrypted message
     * MSGTYPE_FILE a type that handles anything related to files such as reading or writing
     * MSGTYPE_LISTUSERS a type responsible for keeping tabs on all active users of the server
     * MSGTYPE_LOGOUT is responsible for the termination of the users from the server
     * MSGTYPE_TEXT represents text message being sent or received by a user
     */
    public static int MSGTYPE_ENCRYPTION = 0;
    public static int MSGTYPE_FILE = 10;
    public static int MSGTYPE_LISTUSERS = 20;
    public static int MSGTYPE_LOGOUT = 30;
    public static int MSGTYPE_TEXT = 40;

    /**
     * msgType is responsible for determining the type of message being sent
     * timestamp is responsible for keeping track of time of when the message was sent or received
     * username is responsible for telling who sent or received the message
     */
    private final int msgType;
    private final LocalDateTime timestamp;
    private final String username;

    /**
     * Constructs a Message object with a given username.
     * <b><i>NOTE that the access level is "protected".</i></b></B>
     * This means that only subclasses of this class can call this
     * constructor. This is (in part) because only a concrete
     * subclass will know what argument to give for the msgType
     * parameter.
     *
     * @param username name of user creating this message.
     */
    protected Message(String username, int msgType)
    {
        this.timestamp = LocalDateTime.now();
        this.username = username;
        this.msgType = msgType;
    }

    /**
     * Get the message's msgType. This allows the possessor
     * of a Message object to know the concrete subclass the
     * object belongs to. This in turn allows the possessor
     * to cast the Message object to the appropriate subclass.
     * The constructor of each concrete Message subclass
     * should set this to the proper value for the subclass.
     *
     * @return the msgType for the object's concrete subclass.
     */
    public int getMsgType()
    {
        //TODO: implement this (return something other than null)
        return this.msgType;
    }

    /**
     * Gets the message's timestamp (as a LocalDate).
     *
     * @return the message's timestamp (as a LocalDate).
     */
    public LocalDateTime getTimestamp()
    {
        //TODO: implement this (return something other than null)
        return this.timestamp;
    }

    /**
     * Gets the message's username.
     *
     * @return the message's username.
     */
    public String getUsername()
    {
        //TODO: implement this (return something other than null)
        return this.username;
    }

    /**
     * Return this object's data in a String array. For objects
     * with no data, return an empty array.
     * This is an abstract method; each concrete subclass must
     * override this with a subclass-appropriate implementation.
     *
     * @return object data, in a String array.
     */
    public abstract String[] getData();

    /**
     * Equality comparison. Returns true iff the other object is of
     * the same class and all fields (including those inherited from
     * superclasses) are equal.
     * This is an abstract method; each concrete subclass must
     * override this with a subclass-appropriate implementation.
     *
     * @param o the object to test for equality.
     * @return whether o is of the same class as this, and all fields
     * are equal.
     */
    public abstract boolean equals(Object o);

    /**
     * Return this object's hash. In Message objects, this is simply the
     * hash of the string returned by this.toString().
     * This is an abstract method; each concrete subclass must
     * override this with a subclass-appropriate implementation.

     * @return hash of this object.
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Return a string representation of this object (note no spaces):
     * <br>
     *   <code>"timestamp=<i>the_timestamp</i>|username=<i>the_username</i>"</code>
     * <br>
     * No class name or enclosing brackets are included in the returned
     * string, to allow subclasses to use this method in their
     * toString() implementations.
     * <br><br>
     * @return String showing fields and field contents
     */
    public String toString()
    {
        //TODO: implement this (return what is described in the JavaDoc instead of null)
        return "timestamp="+ timestamp + "|username=" + username;
    }
}