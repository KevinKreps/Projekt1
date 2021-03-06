package ELAB;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Auftrag {
    private int id;
    private String titel;
    private String fertigungsart;
    private String dateiname;
    private String dateiort;
    private float kosten;
    private Timestamp statusZeitstempel_angenommen;
    private Timestamp statusZeitstempel_gefertigt;
    private Timestamp statusZeitstempel_kosten_kalkuliert;
    private Timestamp statusZeitstempel_abgeholt;
    private Timestamp statusZeitstempel_abgerechnet;
    private Timestamp statusZeitstempel_wartenAufMaterial;
    private Timestamp statusZeitstempel_fertigungFehlgeschlagen;
    private Timestamp zeitstempel;
    private Person auftraggeber;
    private boolean angenommen;
    private boolean gefertigt;
    private boolean kosten_kalkuliert;
    private boolean abgeholt;
    private boolean abgerechnet;
    private boolean wartenAufMaterial;
    private boolean fertigungFehlgeschlagen;
    private ArrayList<Person> auftragbearbeiter;

    public Auftrag(int id, String titel, String fertigungsart, String dateiname, String dateiort,
                   float kosten, boolean angenommen, Timestamp statusZeitstempel_angenommen, boolean gefertigt, Timestamp statusZeitstempel_gefertigt,
                   boolean kosten_kalkuliert, Timestamp statusZeitstempel_kosten_kalkuliert, boolean abgeholt, Timestamp statusZeitstempel_abgeholt,
                   boolean abgerechnet, Timestamp statusZeitstempel_abgerechnet, boolean wartenAufMaterial, Timestamp statusZeitstempel_wartenAufMaterial,
                   boolean fertigungFehlgeschlagen, Timestamp statusZeitstempel_fertigungFehlgeschlagen, Person auftraggeber, ArrayList<Person> auftragbearbeiter) {

        this.id = id;
        this.titel = titel;
        this.fertigungsart = fertigungsart;
        this.dateiname = dateiname;
        this.dateiort = dateiort;
        this.kosten = kosten;
        this.angenommen = angenommen;
        this.gefertigt = gefertigt;
        this.kosten_kalkuliert = kosten_kalkuliert;
        this.abgeholt = abgeholt;
        this.abgerechnet = abgerechnet;
        this.wartenAufMaterial = wartenAufMaterial;
        this.fertigungFehlgeschlagen = fertigungFehlgeschlagen;
        this.statusZeitstempel_angenommen = statusZeitstempel_angenommen;
        this.statusZeitstempel_gefertigt = statusZeitstempel_gefertigt;
        this.statusZeitstempel_kosten_kalkuliert = statusZeitstempel_kosten_kalkuliert;
        this.statusZeitstempel_abgeholt = statusZeitstempel_abgeholt;
        this.statusZeitstempel_abgerechnet = statusZeitstempel_abgerechnet;
        this.statusZeitstempel_wartenAufMaterial = statusZeitstempel_wartenAufMaterial;
        this.statusZeitstempel_fertigungFehlgeschlagen = statusZeitstempel_fertigungFehlgeschlagen;
        this.zeitstempel = new Timestamp(System.currentTimeMillis());
        this.auftraggeber = auftraggeber;
        this.auftragbearbeiter = auftragbearbeiter;
    }


    public String getStatusZeitstempel_angenommenString() {
        return zeitstempelToString(statusZeitstempel_angenommen);
    }

    public void setStatusZeitstempel_angenommen(Timestamp statusZeitstempel_angenommen) {
        this.statusZeitstempel_angenommen = statusZeitstempel_angenommen;
    }

    public String getStatusZeitstempel_gefertigtString() {
        return zeitstempelToString(statusZeitstempel_gefertigt);
    }

    public void setStatusZeitstempel_gefertigt(Timestamp statusZeitstempel_gefertigt) {
        this.statusZeitstempel_gefertigt = statusZeitstempel_gefertigt;
    }

    public String getStatusZeitstempel_kosten_kalkuliertString() {
        return zeitstempelToString(statusZeitstempel_kosten_kalkuliert);
    }

    public void setStatusZeitstempel_kosten_kalkuliert(Timestamp statusZeitstempel_kosten_kalkuliert) {
        this.statusZeitstempel_kosten_kalkuliert = statusZeitstempel_kosten_kalkuliert;
    }

    public String getStatusZeitstempel_abgeholtString() {
        return zeitstempelToString(statusZeitstempel_abgeholt);
    }

    public void setStatusZeitstempel_abgeholt(Timestamp statusZeitstempel_abgeholt) {
        this.statusZeitstempel_abgeholt = statusZeitstempel_abgeholt;
    }

    public String getStatusZeitstempel_abgerechnetString() {
        return zeitstempelToString(statusZeitstempel_abgerechnet);
    }

    public void setStatusZeitstempel_abgerechnet(Timestamp statusZeitstempel_abgerechnet) {
        this.statusZeitstempel_abgerechnet = statusZeitstempel_abgerechnet;
    }

    public String getStatusZeitstempel_wartenAufMaterialString() {
        return zeitstempelToString(statusZeitstempel_wartenAufMaterial);
    }

    public void setStatusZeitstempel_wartenAufMaterial(Timestamp statusZeitstempel_wartenAufMaterial) {
        this.statusZeitstempel_wartenAufMaterial = statusZeitstempel_wartenAufMaterial;
    }

    public String getStatusZeitstempel_fertigungFehlgeschlagenString() {
        return zeitstempelToString(statusZeitstempel_fertigungFehlgeschlagen);
    }

    private String zeitstempelToString(Timestamp t) {
        if (t != null) {
            Date date = new Date();
            date.setTime(t.getTime());
            return new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(date);
        }
        return "Null"; // f�r den Fall, dass der Zeitstempel null ist
    }


    public void setStatusZeitstempel_fertigungFehlgeschlagen(Timestamp statusZeitstempel_fertigungFehlgeschlagen) {
        this.statusZeitstempel_fertigungFehlgeschlagen = statusZeitstempel_fertigungFehlgeschlagen;
    }

    public boolean isAngenommen() {
        return angenommen;
    }

    public void setAngenommen(boolean angenommen) {
        this.angenommen = angenommen;
    }

    public boolean isGefertigt() {
        return gefertigt;
    }

    public void setGefertigt(boolean gefertigt) {
        this.gefertigt = gefertigt;
    }

    public boolean isKosten_kalkuliert() {
        return kosten_kalkuliert;
    }

    public void setKosten_kalkuliert(boolean kosten_kalkuliert) {
        this.kosten_kalkuliert = kosten_kalkuliert;
    }

    public boolean isAbgeholt() {
        return abgeholt;
    }

    public void setAbgeholt(boolean abgeholt) {
        this.abgeholt = abgeholt;
    }

    public boolean isAbgerechnet() {
        return abgerechnet;
    }

    public void setAbgerechnet(boolean abgerechnet) {
        this.abgerechnet = abgerechnet;
    }

    public boolean isWartenAufMaterial() {
        return wartenAufMaterial;
    }

    public void setWartenAufMaterial(boolean wartenAufMaterial) {
        this.wartenAufMaterial = wartenAufMaterial;
    }

    public boolean isFertigungFehlgeschlagen() {
        return fertigungFehlgeschlagen;
    }

    public void setFertigungFehlgeschlagen(boolean fertigungFehlgeschlagen) {
        this.fertigungFehlgeschlagen = fertigungFehlgeschlagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public String getFertigungsart() {
        return fertigungsart;
    }

    public String getDateiname() {
        return dateiname;
    }

    public String getDateiort() {
        return dateiort;
    }

    public float getKosten() {
        return kosten;
    }

    public String getZeitstempelString() {
        return this.zeitstempelToString(this.zeitstempel);
    }

    public void setZeitstempel(Timestamp zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public Person getAuftraggeber() {
        return auftraggeber;
    }

    public ArrayList<Person> getAuftragbearbeiter() {
        return auftragbearbeiter;
    }
}
