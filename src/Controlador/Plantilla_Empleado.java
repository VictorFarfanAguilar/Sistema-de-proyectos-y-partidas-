/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Cliente;
import Modelo.DAO.ClienteDAO;
import Modelo.Empleado;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Plantilla_Empleado {
    List<Empleado> Lista;
    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;

    public Plantilla_Empleado(List<Empleado> Lista) {
        this.Lista = Lista;
        documento = new Document();
        titulo = new Paragraph("SISTEMA DE REPORTE DE EMPLEADO ", FontFactory.getFont("Helvetica", 24, Font.BOLD, BaseColor.BLUE));
    }

    public void crearPlantilla() {
        try {
            System.out.println("Creando archivo PDF...");
            archivo = new FileOutputStream("reporte_gerencial_farfan_nuevo.pdf");
            PdfWriter.getInstance(documento, archivo);

            documento.open();
            System.out.println("Documento abierto...");

            // Añadir la imagen en la parte superior derecha
            System.out.println("Añadiendo imagen...");
            Image header = Image.getInstance("src/image/registraCliente.png");
            header.scaleToFit(120, 120); // Tamaño reducido para que sea acorde a un documento empresarial
            header.setAlignment(Image.ALIGN_RIGHT);

            // Formato de título del documento
            titulo.setAlignment(Element.ALIGN_CENTER);

            // Añadir imagen y título a la cabecera
            PdfPTable cabecera = new PdfPTable(2);
            cabecera.setWidthPercentage(100);
            cabecera.setWidths(new float[]{80, 20});

            PdfPCell celdaTitulo = new PdfPCell(titulo);
            celdaTitulo.setBorder(PdfPCell.NO_BORDER);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_TOP);

            PdfPCell celdaImagen = new PdfPCell(header);
            celdaImagen.setBorder(PdfPCell.NO_BORDER);
            celdaImagen.setVerticalAlignment(Element.ALIGN_TOP);

            cabecera.addCell(celdaTitulo);
            cabecera.addCell(celdaImagen);

            // Descripción del informe
            Paragraph descripcion = new Paragraph("Reporte Gerencial de Empleados Registrados", FontFactory.getFont("Times New Roman", 18, Font.BOLD, BaseColor.BLACK));
            descripcion.setAlignment(Element.ALIGN_CENTER);

            // Párrafo introductorio
            Paragraph introduccion = new Paragraph(
                    "Este informe presenta una lista detallada de los clientes registrados en el sistema de la empresa. "
                    + "El objetivo es proporcionar una visión general del estado actual de los clientes, incluyendo su código, descripción, "
                    + "RUC y estado de vigencia. Este reporte es esencial para la toma de decisiones estratégicas y la evaluación de la base "
                    + "de clientes de la empresa.",
                    FontFactory.getFont("Times New Roman", 14, BaseColor.BLACK));
            introduccion.setAlignment(Element.ALIGN_JUSTIFIED);

            // Título de la tabla
            Paragraph subtitulo = new Paragraph("Lista de Empleados", FontFactory.getFont("Helvetica", 20, Font.BOLD, BaseColor.BLACK));
            subtitulo.setAlignment(Element.ALIGN_CENTER);

            // Crear la tabla con formato gerencial
            PdfPTable tabla = new PdfPTable(8);
            tabla.setWidthPercentage(100);

            PdfPCell codigoHeader = new PdfPCell(new Phrase("Código", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)));
            codigoHeader.setBackgroundColor(BaseColor.GRAY);
            codigoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell descripcionHeader = new PdfPCell(new Phrase("DescPersona", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)));
            descripcionHeader.setBackgroundColor(BaseColor.GRAY);
            descripcionHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell rucHeader = new PdfPCell(new Phrase("DNI", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)));
            rucHeader.setBackgroundColor(BaseColor.GRAY);
            rucHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell vigenteHeader = new PdfPCell(new Phrase("CIP", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)));
            vigenteHeader.setBackgroundColor(BaseColor.GRAY);
            vigenteHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           
            PdfPCell naciHeader = new PdfPCell(new Phrase("FechNacimiento", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)));
            naciHeader.setBackgroundColor(BaseColor.GRAY);
            naciHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell licHeader = new PdfPCell(new Phrase("LicCond", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)));
            licHeader.setBackgroundColor(BaseColor.GRAY);
            licHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell celHeader = new PdfPCell(new Phrase("Celular", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)));
            celHeader.setBackgroundColor(BaseColor.GRAY);
            celHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell activoHeader = new PdfPCell(new Phrase("Vigente", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)));
            activoHeader.setBackgroundColor(BaseColor.GRAY);
            activoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

            tabla.addCell(codigoHeader);
            tabla.addCell(descripcionHeader);
            tabla.addCell(rucHeader);
            tabla.addCell(vigenteHeader);
            tabla.addCell(naciHeader);

            tabla.addCell(licHeader);

            tabla.addCell(celHeader);

            tabla.addCell(activoHeader);


            for (Empleado empleado : Lista) {
                tabla.addCell(new Phrase(String.valueOf(empleado.getCodPersona()), FontFactory.getFont("Arial", 12)));
                tabla.addCell(new Phrase(empleado.getDesPersona(), FontFactory.getFont("Arial", 12)));
                tabla.addCell(new Phrase(empleado.getDni(), FontFactory.getFont("Arial", 12)));
                                tabla.addCell(new Phrase(empleado.getDni(), FontFactory.getFont("Arial", 12)));
                tabla.addCell(new Phrase(empleado.getNroCIP(), FontFactory.getFont("Arial", 12)));
                tabla.addCell(new Phrase(String.valueOf(empleado.getFecNac()), FontFactory.getFont("Arial", 12)));

                tabla.addCell(new Phrase(String.valueOf(empleado.getLicCond()), FontFactory.getFont("Arial", 12)));
                tabla.addCell(new Phrase(String.valueOf(empleado.getVigente()), FontFactory.getFont("Arial", 12)));

            }

            // Conclusión del informe
            Paragraph conclusionTitulo = new Paragraph("Conclusiones", FontFactory.getFont("Helvetica", 20, Font.BOLD, BaseColor.BLACK));
            conclusionTitulo.setAlignment(Element.ALIGN_CENTER);

            Paragraph conclusion = new Paragraph(
                    "El presente informe proporciona una visión detallada de los Empleados registrados en el sistema de la empresa. "
                    + "La información presentada es crucial para el análisis y la toma de decisiones estratégicas. Es recomendable realizar "
                    + "revisiones periódicas de la base de empleados  para asegurar la vigencia y precisión de los datos.",
                    FontFactory.getFont("Times New Roman", 14, BaseColor.BLACK));
            conclusion.setAlignment(Element.ALIGN_JUSTIFIED);

            // Añadir elementos al documento en el orden correcto
            documento.add(cabecera);
            documento.add(Chunk.NEWLINE);
            documento.add(descripcion);
            documento.add(Chunk.NEWLINE);
            documento.add(introduccion);
            documento.add(Chunk.NEWLINE);
            documento.add(subtitulo);
            documento.add(Chunk.NEWLINE);
            documento.add(tabla);
            documento.add(Chunk.NEWLINE);
            documento.add(conclusionTitulo);
            documento.add(Chunk.NEWLINE);
            documento.add(conclusion);

            documento.close();
            System.out.println("Archivo creado correctamente");

        } catch (FileNotFoundException | DocumentException e) {
            System.err.println(e.getMessage());
            System.out.println("Error al crear archivo");
        } catch (IOException e) {
            System.out.println("Error en la imagen");
        }
    }
}