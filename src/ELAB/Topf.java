package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Topf {
    private int id;
    private String name;
    private float sollbestand;
    private float istbestand;
    private ArrayList<Rechnung> rechnungen;
    private String kasse;
    private Timestamp zeitstempel;


    public Timestamp getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Timestamp zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public Topf(int id, String name, float sollbestand, float istbestand, String kasse) {
        this.id = id;
        this.name = name;
        this.sollbestand = sollbestand;
        this.istbestand = istbestand;
        this.kasse = kasse;
        this.zeitstempel = new Timestamp(System.currentTimeMillis());
        this.rechnungen = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSollbestand() {
        return sollbestand;
    }

    public void setSollbestand(float sollbestand) {
        this.sollbestand = sollbestand;
    }

    public float getIstbestand() {
        return istbestand;
    }

    public void setIstbestand(float istbestand) {
        this.istbestand = istbestand;
    }

    public ArrayList<Rechnung> getRechnungen() {
        this.reloadRechnung(this.getId());
        return rechnungen;
    }

    public void setRechnungen(ArrayList<Rechnung> rechnungen) {
        this.rechnungen = rechnungen;
    }

    public void fuegeRechnungHinzu(Rechnung r) {
        rechnungen.add(r);
    }

    public String getKasse() {
        return kasse;
    }


    // Methoden F�r Rechnungen in dem Topf
    private Personenverwaltung personenVerwaltung = new Personenverwaltung();

    private void reloadRechnung(int topfID) {
        Db db = new Db();
        Personenverwaltung pw = new Personenverwaltung();
        this.rechnungen.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Rechnung WHERE TopfID=" + topfID);
            while (rs.next()) {
                Rechnung r = new Rechnung(rs.getInt("ID"), rs.getString("Name"), rs.getFloat("Betrag"), rs.getString("Bezahlart"),
                        rs.getBoolean("inBearbeitung"), rs.getTimestamp("statusZeitstempel_inBearbeitung"),
                        rs.getBoolean("eingereicht"), rs.getTimestamp("statusZeitstempel_eingereicht"),
                        rs.getBoolean("abgewickelt"), rs.getTimestamp("statusZeitstempel_abgewickelt"),
                        rs.getBoolean("ausstehend"), rs.getTimestamp("statusZeitstempel_ausstehend"));
                r.setZeitstempel(rs.getTimestamp("Zeitstempel"));

                System.out.println(rs.getBoolean("inBearbeitung"));// Fukan, hier ist der Status schon false
                System.out.println(rs.getTimestamp("statusZeitstempel_inBearbeitung"));
                
                try {
                    Person geber = personenVerwaltung.getPersonByName(rs.getString("AuftragGeber"));
                    r.setAuftraggeber(geber);

                    Person ansprechPartner = personenVerwaltung.getPersonByName(rs.getString("AnsprechPartner"));
                    r.setAnsprechpartner(ansprechPartner);

                } catch (ElabException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                this.rechnungen.add(r);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void addRechnung(String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException {

        float BetragFloat;
        try {
            BetragFloat = Float.parseFloat(betrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Betrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }

        Personenverwaltung person = new Personenverwaltung();

        for (String namen : auftraggeber.split("\n")) {
            if (!person.personAlreadyExists(namen)) {
                throw new ElabException("Auftraggeber " + namen + " existiert nicht!");
            }
        }

        for (String namen : ansprechpartner.split("\n")) {
            if (!person.personAlreadyExists(namen)) {
                throw new ElabException("Ansprechpartner " + namen + " existiert nicht!");
            }
        }

        ArrayList<String> personen = new ArrayList<>();
        personen.add(auftraggeber);
        personen.add(ansprechpartner);

        Db db = new Db();
        zeitstempel = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO Rechnung (Datum, Name, AuftragGeber, AnsprechPartner, TopfID, Betrag, Bezahlart, Zeitstempel) "
                + "VALUES ('"
                + zeitstempel + "','"
                + name + "','"
                + auftraggeber + "','"
                + ansprechpartner + "',"
                + this.id + ","
                + BetragFloat + ",'"
                + bezahlart + "','"
                + zeitstempel + "')";

        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRechnung(int id) throws ElabException {
        Db db = new Db();
        String sql = "DELETE FROM Rechnung WHERE ID = " + id + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRechnung(int id, String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException {

        Db db = new Db();
        String sql = "UPDATE Rechnung SET Name = '" + name + "', AuftragGeber = '" + auftraggeber
                + "', AnsprechPartner = '" + ansprechpartner + "', Betrag = '" + betrag + "', Bezahlart = '" + bezahlart
                + "' WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
