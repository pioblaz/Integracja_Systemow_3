package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

public class Main extends JFrame {

    public static String[] naglowki = null;
    public static Object[][] dane = null;

    public Main() {
        setSize(1500, 700);
        setTitle("Integracja systemów Lab3 - Piotr Błażewicz");
    }

    public static void importuj() {
        FileReader fr = null;
        String linia = "";

        try {
            final String fileName = "src/com/company/katalog.txt";
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Blad przy otwieraniu pliku!");
        }

        BufferedReader br = new BufferedReader(fr);
        try {
            int j = 0;
            while (null != (linia = br.readLine())) {
                String[] words = linia.split(";", -1);

                int i = 0;
                for (String word : words) {
                    if (word.isEmpty() && i < words.length - 1)
                        dane[j][i] = "Brak informacji";
                    else if (!word.isEmpty())
                        dane[j][i] = word;

                    i++;
                }
                j++;
            }
        } catch (IOException e) {
            System.out.println("Blad odczytu pliku!");
        }

        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("Blad przy zamykaniu pliku!");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        naglowki = new String[]{"Producent",
                "Przekątna",
                "Rozdzielczość",
                "Rodzaj powierzchni ekranu",
                "Ekran dotykowy",
                "Procesor",
                "Liczba rdzeni",
                "Prędkość taktowania MHz",
                "RAM",
                "pojemność dysku",
                "rodzaj dysku",
                "Układ graficzny",
                "Pamięć GPU",
                "System operacyjny",
                "Napęd fizyczny"};
        dane = new Object[24][15];

        Main okienko = new Main();  //okienko
        okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button_eksport = new JButton("Eksportuj dane do txt");   //przycisk eksport
        button_eksport.setBounds(10, 450, 150, 50);
        button_eksport.setBackground(Color.ORANGE);
        button_eksport.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton button_import = new JButton("Importuj dane z txt");   //przycisk importu
        button_import.setBounds(170, 450, 150, 50);
        button_import.setBackground(Color.YELLOW);
        button_import.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton button_eksport_xml = new JButton("Eksportuj dane do xml");   //przycisk eksport xml
        button_eksport_xml.setBounds(330, 450, 150, 50);
        button_eksport_xml.setBackground(Color.CYAN);
        button_eksport_xml.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton button_import_xml = new JButton("Importuj dane z xml");   //przycisk importu xml
        button_import_xml.setBounds(490, 450, 150, 50);
        button_import_xml.setBackground(Color.GREEN);
        button_import_xml.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JTable table = new JTable(dane, naglowki) {
            @Override
            public void setValueAt(Object aValue, int row, int column) {    //edycja danych
                if (aValue.toString().trim().isEmpty()) {   //trim usuniecie bialych znakow - zeby spacje uznawalo jako puste
                    JOptionPane.showMessageDialog(okienko, "Pole nie może być puste!");
                    //System.out.println("Puste pole!");
                } else if ((column == 4 || column == 10) && aValue.toString().trim().length() != 3) {
                    JOptionPane.showMessageDialog(okienko, "Tekst musi miec 3 znaki!");
                } else if (column == 1 && !aValue.toString().endsWith("\"")) {
                    JOptionPane.showMessageDialog(okienko, "Pole musi się kończyć na \"");
                } else if (column == 2 & !aValue.toString().matches("[0-9]+x[0-9]+")) {
                    JOptionPane.showMessageDialog(okienko, "Wprowadź wartość według wzoru, np. 1920x1080");
                } else {
                    super.setValueAt(aValue, row, column);
                    dane[row][column] = aValue;
                }
            }
        };

        button_eksport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrintWriter zapis = null;
                try {
                    zapis = new PrintWriter("wynik.txt");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                for (int i = 0; i < 24; i++) {
                    for (int j = 0; j < 15; j++) {
                        zapis.print(dane[i][j] + ";");
                    }
                    if (i < 23)
                        zapis.print("\n");
                }
                zapis.close();
            }
        });

        button_import.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importuj();
                okienko.repaint();
            }
        });

        button_eksport_xml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DocumentBuilder builder;
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    builder = factory.newDocumentBuilder();
                    Document document = builder.newDocument();

                    Element laptops = document.createElement("laptops");
                    laptops.setAttribute("moddate", String.valueOf(new Date()));

                    int j = 0;
                    for (int i = 0; i < 24; i++) {
                        j = 0;
                        Element laptop = document.createElement("laptop");
                        laptop.setAttribute("id", String.valueOf(i + 1));

                        //PRODUCENT
                        Element manufacturer = document.createElement("manufacturer");
                        manufacturer.setTextContent((String) dane[i][j]);
                        j++;

                        //EKRAN
                        Element screen = document.createElement("screen");

                        Element size = document.createElement("size");
                        size.setTextContent((String) dane[i][j]);
                        j++;

                        Element resolution = document.createElement("resolution");
                        resolution.setTextContent((String) dane[i][j]);
                        j++;

                        Element type = document.createElement("type");
                        type.setTextContent((String) dane[i][j]);
                        j++;

                        screen.setAttribute("touch", (String) dane[i][j]);
                        j++;

                        //PROCESOR
                        Element processor = document.createElement("processor");

                        Element name = document.createElement("name");
                        name.setTextContent((String) dane[i][j]);
                        j++;

                        Element physical_cores = document.createElement("physical_cores");
                        physical_cores.setTextContent((String) dane[i][j]);
                        j++;

                        Element clock_speed = document.createElement("clock_speed");
                        clock_speed.setTextContent((String) dane[i][j]);
                        j++;

                        //RAM
                        Element ram = document.createElement("ram");
                        ram.setTextContent((String) dane[i][j]);
                        j++;

                        //DYSK
                        Element disc = document.createElement("disc");

                        Element storage = document.createElement("storage");
                        storage.setTextContent((String) dane[i][j]);
                        j++;

                        disc.setAttribute("type", (String) dane[i][j]);
                        j++;

                        //GPU
                        Element graphic_card = document.createElement("graphic_card");

                        Element nameGPU = document.createElement("name");
                        nameGPU.setTextContent((String) dane[i][j]);
                        j++;

                        Element memory = document.createElement("memory");
                        memory.setTextContent((String) dane[i][j]);
                        j++;

                        //SYSTEM OPERACYJNY
                        Element os = document.createElement("os");
                        os.setTextContent((String) dane[i][j]);
                        j++;

                        //NAPED
                        Element disc_reader = document.createElement("disc_reader");
                        disc_reader.setTextContent((String) dane[i][j]);

                        laptop.appendChild(manufacturer);

                        screen.appendChild(size);
                        screen.appendChild(resolution);
                        screen.appendChild(type);
                        laptop.appendChild(screen);

                        processor.appendChild(name);
                        processor.appendChild(physical_cores);
                        processor.appendChild(clock_speed);
                        laptop.appendChild(processor);

                        laptop.appendChild(ram);

                        disc.appendChild(storage);
                        laptop.appendChild(disc);

                        graphic_card.appendChild(nameGPU);
                        graphic_card.appendChild(memory);
                        laptop.appendChild(graphic_card);

                        laptop.appendChild(os);

                        laptop.appendChild(disc_reader);

                        laptops.appendChild(laptop);
                    }

                    document.appendChild(laptops);

                    Transformer t = TransformerFactory.newInstance().newTransformer();
                    t.setOutputProperty(OutputKeys.INDENT, "yes");
                    t.setOutputProperty(OutputKeys.METHOD, "xml");
                    t.transform(new DOMSource(document), new StreamResult(new FileOutputStream("laptopy.xml")));
                } catch (ParserConfigurationException | TransformerConfigurationException | FileNotFoundException parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                } catch (TransformerException transformerException) {
                    transformerException.printStackTrace();
                }
            }
        });

        button_import_xml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        table.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table);
        okienko.add(button_eksport);
        okienko.add(button_import);
        okienko.add(button_eksport_xml);
        okienko.add(button_import_xml);
        okienko.add(sp);
        okienko.setVisible(true);
    }
}
