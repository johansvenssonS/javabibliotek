import javax.swing.*;

public interface InputForm {
    String getTitle();
    JPanel buildForm();
    void sendForm();
}
