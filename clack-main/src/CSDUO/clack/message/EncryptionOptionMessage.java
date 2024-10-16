package CSDUO.clack.message;

import java.util.Objects;

public class EncryptionOptionMessage extends Message
{

    private final String option;

    /**
     *
     * @param username takes username of the user
     * @param option turns on or off of encryption of text for that user
     */
    public EncryptionOptionMessage(String username, String option)
    {
        super(username, MSGTYPE_ENCRYPTION);

        if (!option.equals("ON") && !option.equals("OFF")){
            throw new IllegalArgumentException("option must be ON or OFF");
        }
        else {
            this.option = option;
        }
    }

    /**
     *
     * @return option to be on or off
     */
    public String getOption() {return option;}

    @Override
    public String[] getData()
    {
        return new String[]{option};
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {return true;}

        if (!(o == null || o.getClass() != this.getClass())) {return false;}

        EncryptionOptionMessage that = (EncryptionOptionMessage) o;
        return option.equals(that.option);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), option);
    }

    @Override
    public String toString()
    {
        return "EncryptionOptionMessage{" +
                "option='" + option + '\'' +
                '}';
    }
}
