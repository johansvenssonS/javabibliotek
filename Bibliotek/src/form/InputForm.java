package form;

import javax.swing.*;

public interface InputForm {
    String getTitle();
    JPanel buildForm();
    boolean sendForm();
}
//ändrade boolean på sendform för loginFormuläret
// kanske är onödigt ?