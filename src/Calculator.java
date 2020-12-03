//  Write a Java program that works as a simple calculator using Frame. 
// Arrange Buttons for digits and the + - * 
// % operations properly and perform the calculations.

import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {

    TextField display;
    Button number1;
    Button number2;
    Button number3;
    Button number4;
    Button number5;
    Button number6;
    Button number7;
    Button number8;
    Button number9;
    Button number0;
    Button decimalPoint;
    Button equals;
    Button clear;
    Button add;
    Button subtract;
    Button divide;
    Button multiply;
    Button mod;

    String expression;


    Calculator() {
        setSize(480, 640);
        setVisible(true);
        setTitle("Calculator");
        setLayout(null);

        expression = "";

        display = new TextField("");
        number1 = new Button("1");
        number2 = new Button("2");
        number3 = new Button("3");
        number4 = new Button("4");
        number5 = new Button("5");
        number6 = new Button("6");
        number7 = new Button("7");
        number8 = new Button("8");
        number9 = new Button("9");
        number0 = new Button("0");
        equals = new Button("=");
        clear = new Button("CLEAR");
        add = new Button("+");
        subtract = new Button("-");
        divide = new Button("/");
        multiply = new Button("*");
        mod = new Button("%");

        display.setEditable(false);

        Button buttons[] = new Button[17];
        buttons[0] = number7;
        buttons[1] = number8;
        buttons[2] = number9;
        buttons[3] = add;
        buttons[4] = number4;
        buttons[5] = number5;
        buttons[6] = number6;
        buttons[7] = subtract;
        buttons[8] = number1;
        buttons[9] = number2;
        buttons[10] = number3;
        buttons[11] = multiply;
        buttons[12] = divide;
        buttons[13] = number0;
        buttons[14] = mod;
        buttons[15] = equals;
        buttons[16] = clear;

        display.setFont(new Font("montserrat", Font.PLAIN, 34));
        display.setBounds(40, 50, 400, 50);
        add(display);

        int topMargin = 200;
        int leftMargin = 20;
        int padding = 40;
        int size = 60;
        Font font = new Font("montserrat", Font.PLAIN, 26);

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setFont(font);
            add(button);
            button.addActionListener(this);

            if(i == 16) {
                button.setBounds(300, topMargin - size, size * 2, size);
                continue;
            }
            int x = i % 4;
            int y = i / 4;
            button.setBounds(leftMargin + padding + x * (padding + size), topMargin + padding + y * (padding + size), size, size);
        }
       
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        if(button == clear) {
            changeDisplayText("");
        } else if(button == equals) {
            calculate();
        } else {
            String text = button.getLabel();
            addToDisplay(text);
        }
    }

    private void addToDisplay(String text) {
        expression += text;
        display.setText(expression);
    }

    private void changeDisplayText(String text) {
        expression = text;
        display.setText(expression);
    }

    private void calculate() {
        expression += " ";
        int result = 0;
        int term = 0;
        char lastOperand = ' ';
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if(Character.isDigit(ch)) {
                term = term * 10 + Integer.parseInt(Character.toString(ch));
            } else {
                result = operate(result, term, lastOperand);
                lastOperand = ch;
                term = 0;
            }
        }
        changeDisplayText(Integer.toString(result));
    }

    private int operate(int lastTerm, int term, char operand) {
        switch(operand) {
            case '+':
                return lastTerm + term;
            case '-':
                return lastTerm - term;
            case '*':
                return lastTerm * term;
            case '/':
                return lastTerm / term;
            case '%':
                return lastTerm % term;
            default:
                return term;
        }
    }
     
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
}
