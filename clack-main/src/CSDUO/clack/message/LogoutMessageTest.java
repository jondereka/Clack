package CSDUO.clack.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.time.Duration.between;
import static org.junit.jupiter.api.Assertions.*;

class LogoutMessageTest {
    LogoutMessage msg;
    LocalDateTime now;

    final String USERNAME = "the user";

    @BeforeEach
    void setUp() {
        msg = new LogoutMessage(USERNAME);
    }

    @Test
    void getMsgType() {
        assertEquals(Message.MSGTYPE_LOGOUT, msg.getMsgType());
    }

    @Test
    void getTimestamp() {
        now = LocalDateTime.now();
        Duration duration = between(msg.getTimestamp(), now);
        long timeDiff = Math.abs(duration.toSeconds());
        assertTrue(timeDiff <= 1);
    }

    @Test
    void getUsername() {
        assertEquals(USERNAME, msg.getUsername());
    }

    @Test
    void testHashCode() {
        assertNotEquals(msg.hashCode(), new LogoutMessage("other user"));
    }

    @Test
    void testToString() {
        String msgStr = "{class=LogoutMessage|"
                + "timestamp=" + msg.getTimestamp()
                + "|username=" + USERNAME
                + "}";
        assertEquals(msgStr, msg.toString());
    }

    @Test
    void getData() {
        assertEquals(0, msg.getData().length);
    }

    @Test
    void testEquals() {
        assertTrue(msg.equals(msg));
        assertFalse(msg.equals(null));
        assertFalse(msg.equals(new ListUsersMessage("another user")));
    }
}