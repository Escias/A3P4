package org.projet4.javadomo;

import javax.swing.*;

public class GetScroll {
    JPanel takescroll;
    JPanel getscroll;

    public void TakeScroll(JPanel pscroll){
        takescroll = pscroll;
        takescroll.add(new JLabel("        "));
        getscroll = takescroll;
    }

    public JPanel GetScroll(){
        return takescroll;
    }
}
