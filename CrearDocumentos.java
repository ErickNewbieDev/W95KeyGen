/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llaveswindows;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author legot
 */
public class CrearDocumentos {
    
    private Paragraph parrafo;
    private PdfPTable tabla;
    private String texto,claves[][];
    
    public CrearDocumentos(){
        parrafo = parrafo = new Paragraph();
        tabla = null;
        claves = null;
        texto = "";
        
    }
    
    public void generarPDF(){
        try {
            // Crear el documento
            Document documento = new Document();

            // Crear el escritor PDF
            PdfWriter.getInstance(documento, new FileOutputStream("claves.pdf"));

            // Abrir el documento
            documento.open();

            // Agregar contenido al documento
            texto = "Lista de claves generadas";
            parrafo = new Paragraph(texto);

            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 32)); // Establecer tamaño de fuente
            parrafo.setSpacingAfter(20);

            documento.add(parrafo);

            
            tabla = new PdfPTable(2);
            // Establecer alineación horizontal de las celdas
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            // Establecer color de borde de las celdas
            tabla.getDefaultCell().setBorderColor(BaseColor.GREEN);
            
            PdfPTable tabla = new PdfPTable(2); // Crear una tabla con 2 columnas

            // Agregar encabezados de columna
            PdfPCell celdaEncabezado1 = new PdfPCell(new Phrase("Índice"));
            celdaEncabezado1.setHorizontalAlignment(Element.ALIGN_CENTER); // Centrar el texto en la celda
            celdaEncabezado1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(celdaEncabezado1);

            PdfPCell celdaEncabezado2 = new PdfPCell(new Phrase("Valor"));
            celdaEncabezado2.setHorizontalAlignment(Element.ALIGN_CENTER); // Centrar el texto en la celda
            celdaEncabezado2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(celdaEncabezado2);

            // Agregar datos de las claves a la tabla
            for (String[] clave : claves) {
                PdfPCell celdaIndice = new PdfPCell(new Phrase(String.valueOf(clave[0])));
                celdaIndice.setHorizontalAlignment(Element.ALIGN_CENTER); // Centrar el texto en la celda
                tabla.addCell(celdaIndice);

                PdfPCell celdaValor = new PdfPCell(new Phrase(clave[1]));
                celdaValor.setHorizontalAlignment(Element.ALIGN_CENTER); // Centrar el texto en la celda
                tabla.addCell(celdaValor);
            }

            documento.add(tabla); // Agregar la tabla al documento
            
            // Cerrar el documento
            documento.close();

            } catch (DocumentException | FileNotFoundException e) {
        }
    }
    
    public void setClaves(String c[]){
        claves = new String[c.length][2];
        for (int i = 0; i < c.length; i++) {
            claves[i][0] = String.valueOf(i + 1);  // Índice + 1
            claves[i][1] = c[i];   // Valor del arreglo
        }
    }
}
