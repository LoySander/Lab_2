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
    private double mem1,mem2,mem3;
    private double sum;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    int formulaId= 1;
    private JTextField memoryTextField;
    private int memoryId= 1;
    double result = 0;
    private ButtonGroup radioMemoryButtons = new ButtonGroup();
    private Box hboxMemoryType = Box.createHorizontalBox();
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
    private void addMemoryRadioButton (String buttonName, int memoryId)	{         // радиокнопки для памяти
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)	{
                MainFrame.this.memoryId = memoryId;
                if (memoryId == 1)	{
                    memoryTextField.setText(toString().format("%-10.3f%n", (mem1)));
                }
                if (memoryId == 2){
                    memoryTextField.setText(toString().format("%-10.3f%n", (mem2)));
                }
                if (memoryId == 3){
                    memoryTextField.setText(toString().format("%-10.3f%n", (mem3)));
                }
            }
        });

        radioMemoryButtons.add(button);
        hboxMemoryType.add(button);
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
        Box actions=Box.createHorizontalBox(); // область действий
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
        actions.add(Box.createHorizontalGlue());
        actions.add(buttonCalculation);
        actions.add(Box.createHorizontalStrut(10));
        actions.add(buttonReset);
        actions.add(Box.createHorizontalGlue());
        hboxMemoryType.add(Box.createHorizontalGlue());

        addMemoryRadioButton("Память 1",1);
        addMemoryRadioButton("Память 2",2);
        addMemoryRadioButton("Память 3",3);
        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());


        Box memory_result=Box.createHorizontalBox();
        memory_result.add(Box.createHorizontalGlue());
        JButton MC=new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memoryId == 1){
                    mem1 = 0;
                }
                if (memoryId == 2)	{
                    mem2 = 0;
                }
                if (memoryId == 3)	{
                    mem3 = 0;
                }
                memoryTextField.setText("0");
            }
        });
        JButton M_plus=new JButton("M+");
        M_plus.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ev) {
                try{
                    Double result = Double.parseDouble(textFieldResult.getText());

                    if (memoryId == 1) 	{
                        mem1 += result;
                        memoryTextField.setText(toString().format("%-10.3f%n", (mem1)));
                    }
                    if (memoryId == 2)	{
                        mem2 += result;
                        memoryTextField.setText(toString().format("%-10.3f%n", (mem2)));
                    }
                    if (memoryId == 3)	{
                        mem3 += result;
                        memoryTextField.setText(toString().format("%-10.3f%n", (mem3)));
                    }

                }catch (NumberFormatException ex)
                { JOptionPane.showMessageDialog(MainFrame.this,
                        "Ошибка в формате записи числа с плавающей точкой", "" +
                                "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        memoryTextField = new JTextField("0",10);
        memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());
        memory_result.add(MC);
        memory_result.add(Box.createHorizontalStrut(10));
        memory_result.add(memoryTextField);
        memory_result.add(Box.createHorizontalStrut(10));
        memory_result.add(M_plus);
        memory_result.add(Box.createHorizontalGlue());

        Box contentBox= Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        // Добавить контейнер с переменными
        contentBox.add(hboxVariables);
        contentBox.add(Box.createVerticalGlue());
        // Добавить контейнер с результатом вычислений
        contentBox.add(hboxResult);
        contentBox.add(Box.createVerticalGlue());
        // Добавить контейнер с кнопками
        contentBox.add(actions);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(memory_result);
        // Добавить «клей»V2 снизу
        contentBox.add(Box.createVerticalGlue());
        // Установить «вертикальную коробку» в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}
