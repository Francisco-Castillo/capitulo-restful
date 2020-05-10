package com.fcastillo.capitulo.rest.utilidades;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//</editor-fold>

@Named(value = "utilidades")
@RequestScoped
public class Utilidades {

    //<editor-fold defaultstate="collapsed" desc="mostrarFechaActual()">
    public String mostrarFechaActual() {
        LocalDate localDate = LocalDate.now();
        return getFormattedDate(localDate);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="nombreMes()">
    public static String nombreMes(int numeroDeMes) {
        String mes;
        switch (numeroDeMes) {
            case 1:
                mes = "Enero";
                break;
            case 2:
                mes = "Febrero";
                break;
            case 3:
                mes = "Marzo";
                break;
            case 4:
                mes = "Abril";
                break;
            case 5:
                mes = "Mayo";
                break;
            case 6:
                mes = "Junio";
                break;
            case 7:
                mes = "Julio";
                break;
            case 8:
                mes = "Agosto";
                break;
            case 9:
                mes = "Septiembre";
                break;
            case 10:
                mes = "octubre";
                break;
            case 11:
                mes = "noviembre";
                break;
            case 12:
                mes = "diciembre";
                break;
            default:
                mes = "Mes no válido";
                break;
        }
        return mes;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="nombreDia()">
    public static String nombreDia(int numeroDelDia) {
        String dia = "";
        switch (numeroDelDia) {
            case 0:
                dia = "Domingo ";
                break;
            case 1:
                dia = "Lunes ";
                break;
            case 2:
                dia = "Martes ";
                break;
            case 3:
                dia = "Miércoles ";
                break;
            case 4:
                dia = "Jueves ";
                break;
            case 5:
                dia = "Viernes ";
                break;
            case 6:
                dia = "Sábado ";
                break;
            default:
                dia = "No valido ";
                break;
        }
        return dia;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="fechaMenor()">
    public static boolean fechaMenor(Date startDate, Date endDate) {
        boolean esValida = true;

        if (startDate.after(endDate)) {
            esValida = false;
        }
        return esValida;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="estaEnArray()">
    public static boolean estaEnArray(Object[] arreglo, int numero) {
        return Arrays.asList(arreglo).contains(numero);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="calcularEdad()">
    public String calcularEdad(Date fnacimiento) {
        if (fnacimiento != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate localDate = Instant.ofEpochMilli(fnacimiento.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(localDate, ahora);
            return "" + periodo.getYears() + " años y " + periodo.getMonths() + " meses";
        }
        return "";

    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="dateDiff()">
    public String dateDiff(Date startDate) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date endDate = new Date();
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startDate.toString());
            d2 = format.parse(endDate.toString());

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            return "" + diffDays + "días" + diffHours + "horas " + diffMinutes + " minutos" + diffSeconds + " segundos";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="infoFlashMessage()">
    public static void infoFlashMessage(String message) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getFlash().setKeepMessages(true);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }//</editor-fold>

    //<editor-fold defaultstate="collpased" desc="addHoursToDate()">
    public static Date addHoursToDate(Date fecha, int horas) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.HOUR, horas);
        return c.getTime();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getOnlyDate()">
    public static String getOnlyDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getOnlyHour()">
    public static String getOnlyHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDateHour()">
    public static String getDateHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return dateFormat.format(date);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDayOfWeek()">
    public static int getDayOfWeek(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDayOfMonth()">
    public static int getDayOfMonth(LocalDate date) {
        return date.getDayOfMonth();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getMonth()">
    public static int getMonth(LocalDate date) {
        return date.getMonthValue();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getYear()">
    public static int getYear(LocalDate date) {
        return date.getYear();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getStringDate()">
    public static String getFormattedDate(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append(nombreDia(getDayOfWeek(date)));
        sb.append(getDayOfMonth(date));
        sb.append(" de ");
        sb.append(nombreMes(getMonth(date)));

        return sb.toString();

    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="asLocalDate()">
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="totalFinalSpaces()">
    public static Integer totalFinalSpaces(String text) {
        Integer count = 0;
        int finalPos = text.length() - 1;
        boolean jump = false;

        while (finalPos > 0 && jump == false) {
            if (text.charAt(finalPos) == ' ') {
                count++;
            } else {
                jump = true;
            }
            finalPos--;
        }
        System.out.println("cantidad: " + count);
        return count;
    }
    //</editor-fold>

    public static String ISO8601(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    public static Date stringToDate(String fechaString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date fecha = null;
        try {

            fecha = df.parse(fechaString);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }
        return fecha;

    }
    
    public static LocalTime stringToTime(String horaString){
       LocalTime hora = LocalTime.parse(horaString);
        return hora;
    
    }
    
}
    
  
