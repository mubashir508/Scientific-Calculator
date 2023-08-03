import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{
    //Frames,TextField and Buttons Declaration
    JFrame frame;
    JTextField displayscreen;
    JButton[] num=new JButton[10];
    JButton[] opr=new JButton[10];
    JButton dec,equ,del,clr,add,sub,mul,div,prc,off;

    //Fonts Declaration
    Font disp_font=new Font("Digital-7",Font.BOLD,48);
    Font butt_font=new Font("Arial",Font.PLAIN,32);
    Font opr_font=new Font("Arial",Font.PLAIN,12);
    Font off_font=new Font("Arial",Font.BOLD,12);
    double num1=0,num2=0,result=0;
    char operator;
    Calculator(){
        //Initializing Frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        //Initializing DisplayScreen
        displayscreen=new JTextField();
        displayscreen.setBounds(25,25,355,80);
        displayscreen.setFont(disp_font);
        displayscreen.setHorizontalAlignment(JTextField.RIGHT);
        displayscreen.setEditable(false);

        //Assigning values to Operation Buttons
        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("x");
        div = new JButton("÷");
        dec = new JButton(".");
        equ = new JButton("=");
        del = new JButton("DEL");
        clr = new JButton("CLR");
        off = new JButton("OFF");
        prc = new JButton("%");

        //Appending Buttons to FunctionalButton Arrays
        opr[0]=add;
        opr[1]=sub;
        opr[2]=mul;
        opr[3]=div;
        opr[4]=dec;
        opr[5]=equ;
        opr[6]=del;
        opr[7]=clr;
        opr[8]=off;
        opr[9]=prc;

        //Operational Buttons
        for(int i=0;i<10;i++){
            opr[i].addActionListener(this);
            opr[i].setFont(opr_font);
            opr[i].setFocusable(false);
            opr[i].setBackground(Color.BLACK);
            opr[i].setForeground(Color.WHITE);

            //only off button styling
            off.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            off.setForeground(Color.RED);
            off.setFont(off_font);
        }

        //Numeric Buttons
        for(int i=0;i<10;i++){
            num[i] =new JButton(String.valueOf(i));
            num[i].addActionListener(this);
            num[i].setFont(butt_font);
            num[i].setFocusable(false);
            num[i].setBackground(Color.BLACK);
            num[i].setForeground(Color.WHITE);

        }

        //InitializingPanel
        JPanel panel =new JPanel();
        panel.setBounds(35,140,330,300);
        panel.setLayout(new GridLayout(5,4,10,10));
        panel.setBackground(Color.DARK_GRAY);

        //Adding Buttons to panel
        panel.add(off);
        panel.add(clr);
        panel.add(del);
        panel.add(div);
        panel.add(num[7]);
        panel.add(num[8]);
        panel.add(num[9]);
        panel.add(mul);
        panel.add(num[4]);
        panel.add(num[5]);
        panel.add(num[6]);
        panel.add(sub);
        panel.add(num[1]);
        panel.add(num[2]);
        panel.add(num[3]);
        panel.add(add);
        panel.add(prc);
        panel.add(num[0]);
        panel.add(dec);
        panel.add(equ);

        //Adding Panel and Display Screen to Frame
        frame.add(displayscreen);
        frame.add(panel);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
    Calculator cal1=new Calculator();       //Constructor Called

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Displaying Numeric Words on Display Screen
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == num[i]) {
                JButton btn = (JButton) e.getSource(); // Get the button that was pressed
                String buttonText = btn.getText(); // Get the text on the button
                String currentText = displayscreen.getText(); // Get the current text on the displayscreen
                String updatedText = currentText + buttonText; // Append the buttonText at the beginning of the current text
                displayscreen.setText(updatedText); // Set the updated text to the displayscreen
            }
        }

        //Operation button working
        if(e.getSource()==off){
            System.exit(0);
        }
        if(e.getSource()==clr){
            displayscreen.setText("");
        }
        if(e.getSource()==del){
            String current= displayscreen.getText();
            displayscreen.setText("");
            for(int i=0;i<current.length()-1;i++){
                displayscreen.setText(displayscreen.getText()+current.charAt(i));
            }
        }
        if(e.getSource()==dec){
            displayscreen.setText(displayscreen.getText().concat("."));
        }
        if(e.getSource()==add){
            num1=Double.parseDouble(displayscreen.getText());
            operator='+';
            displayscreen.setText("");
        }
        if(e.getSource()==sub) {
            num1 = Double.parseDouble(displayscreen.getText());
            operator = '-';
            displayscreen.setText("");
        }
        if(e.getSource()==mul) {
            num1 = Double.parseDouble(displayscreen.getText());
            operator = 'x';
            displayscreen.setText("");
        }
        if(e.getSource()==div) {
            num1 = Double.parseDouble(displayscreen.getText());
            operator = '÷';
            displayscreen.setText("");
        }
        if(e.getSource()==prc) {
            num1 = Double.parseDouble(displayscreen.getText());
            operator = '%';
            displayscreen.setText("");
        }
        if(e.getSource()==equ) {
            num2 = Double.parseDouble(displayscreen.getText());
            switch (operator){
                case '+':
                    result=num1+num2;
                    break;
                case '-':
                    result=num1-num2;
                    break;
                case 'x':
                    result=num1*num2;
                    break;
                case '÷':
                    if(num2==0){
                        displayscreen.setText("MATH ERROR");
                        return;
                    }
                    else {
                        result = num1 / num2;
                    }
                    break;
                case '%':
                    result=num1*(num2/100);
                    break;
            }

            displayscreen.setText(String.valueOf(result));
            num1=result;
        }
    }
}