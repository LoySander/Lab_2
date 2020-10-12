package com.content3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


@SuppressWarnings("serial")

public class MainFrame extends JFrame {
    private static final int Width = 400;
    private static final int Height = 400;
    private  JTextField textFieldResult;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    int formulaId= 1;
    double result = 0;
    public double calculate1 (double x, double y,double z ){
        return x+y+z; // например
    }
    public double calculate2 (double x, double y,double z ){
        return x-y-z; // например
    }
    private void addRadioButton(String buttonName, final int formulaId){ // buttonName–текст рядом с кнопкой, formulaId–идентификатор формулы
        JRadioButton button = new JRadioButton(buttonName); // Создатьэкземпляр радио-кнопки с заданным текстом
        button.addActionListener (new ActionListener(){ // Определить и зарегистрировать обработчик
            // Который будет устанавливать идентификатор выбранной
            // формулы в классе Formula равным formulaId
            public void actionPerformed (ActionEvent ev){
                MainFrame.this.formulaId= formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button); // Добавить радио-кнопку в контейнер
        // Для этого ссылка на контейнер сделана полем данных класса
    }
    public MainFrame(){
        super("Вычисление формулы");
        setSize(Width, Height);
        Toolkit kit= Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width-Width)/2, (kit.getScreenSize().height-Height)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());// Добавить «клей»C1-H1 с левой стороны
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());// Добавить «клей» C1-H2с правой стороны
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        JLabel labelForX = new JLabel("X:");
        textFieldX= new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY= new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ= new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        // Создать контейнер «коробка с горизонтальной укладкой»
        Box hboxVariables= Box.createHorizontalBox(); // Задать рамку для коробки с помощью класса BorderFactory
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());// Добавить в контейнер ряд объектов // Добавить «клей»C2-H1 –для максимального удаления от левого кра
        hboxVariables.add(labelForX);
        // Добавить «распорку»C2-H2 шириной 10 пикселов для отступа между // надписью и текстовым полем для ввода значенияX
        hboxVariables.add(Box.createHorizontalStrut(10));
        // Добавить само текстовое поле для ввода Х
        hboxVariables.add(textFieldX);
        // Добавить «распорку»C2-H3 шириной 100 пикселов для отступа между // текстовым полем для ввода Xи подписью для Y
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        // Добавить само текстовое поле для ввода Y
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        // Добавить «клей»C2-H5 для максимального удаления от правого края
        hboxVariables.add(Box.createHorizontalGlue());
        // Создать подпись для поля с результатом
        JLabel labelForResult= new JLabel("Результат:");
        textFieldResult= new JTextField("0",10);
        // Создать контейнер «коробка с горизонтальной укладкой»
        Box hboxResult= Box.createHorizontalBox();
        // Добавить в контейнер ряд объектов// Добавить «клей»C3-H1 для отступа от левого края
        hboxResult.add(Box.createHorizontalGlue());
        // Добавить«распорку»C3-H2 в 10 пикселов между подписью и полем // результата
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        // Задать рамку для контейнера
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        // Создать кнопку «Вычислить»
        JButton buttonCalculation= new JButton("Вычислить");
        // Определить и зарегистрировать обработчик нажатия на кнопку
        buttonCalculation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // Преобразование введенных строк в числа с плавающей точкой может
                // спровоцировать исключительную ситуацию при неправильном формате чисел,
                // поэтому необходим блок try-catch
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    if(formulaId==1){
                        result = calculate1(x, y,z);
                    }
                    else
                        result= calculate2(x, y,z);
                    String str = Double.toString(result);//
                    textFieldResult.setText(str);

                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // Создать кнопку «Очистить поля»
        JButton buttonReset= new JButton("Очистить поля");
        // Определить и зарегистрировать обработчик нажатия на кнопку
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        // Создать коробку с горизонтальной укладкой
        Box hboxButtons = Box.createHorizontalBox();
        // Добавить «клей» C4-H1 с левой стороны
        hboxButtons.add(Box.createHorizontalGlue());
        // Добавить кнопку «Вычислить» в компоновку
        hboxButtons.add(buttonCalculation);
        // Добавить распоркув 30 пикселов C4-H2 между кнопками
        hboxButtons.add(Box.createHorizontalStrut(30));
        // Добавить кнопку «Очистить поля» в компоновку
        hboxButtons.add(buttonReset);
        // Добавить «клей» C4-H3 с правой стороны
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        // Создать контейнер «коробка с вертикальной укладкой»
        Box contentBox= Box.createVerticalBox();
        // Добавить «клей»V1 сверху
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        // Добавить контейнер с переменными
        contentBox.add(hboxVariables);
        // Добавить контейнер с результатом вычислений
        contentBox.add(hboxResult);
        // Добавить контейнер с кнопками
        contentBox.add(hboxButtons);
        // Добавить «клей»V2 снизу
        contentBox.add(Box.createVerticalGlue());
        // Установить «вертикальную коробку» в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}
