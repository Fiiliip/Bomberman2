package sk.uniza.fri.gui;

/**
 * Trieda, ktorá zobrazuje ľubovoľné číslo pomocou segmentového znaku na plátne v rozsahu od 0 do 9 vrátane.
 * 
 * Táto trieda bola vytvorená na cvičeniach v škole a upravená mimo cvičení pre použitie na aktuálnu semestrálnu prácu!
 * 
 * @author Michal Mrena
 * @author Filip Kubica
 */
public class SegmentovyZnak {
    
    /*
     *   __A__
     *  -     -
     *  F     B
     *  -     -
     *   --G--
     *  -     -
     *  E     C
     *  -     -
     *   __D__
     */
    
    private Obdlznik a;
    private Obdlznik b;
    private Obdlznik c;
    private Obdlznik d;
    private Obdlznik e;
    private Obdlznik f;
    private Obdlznik g;
    
    private int sirkaObdlznika = 2;
    private int dlzkaObdlznika = 8;
    
    /**
     * Vytvorí segmentový znak.
     * @param x súradnica X
     * @param y súradnica Y
     */
    public SegmentovyZnak(int x, int y) {
        this.a = new Obdlznik();
        this.b = new Obdlznik();
        this.c = new Obdlznik();
        this.d = new Obdlznik();
        this.e = new Obdlznik();
        this.f = new Obdlznik();
        this.g = new Obdlznik();
        
        this.a.posunVodorovne(-60 + x);
        this.a.posunZvisle(-50 + y);
        this.b.posunVodorovne(-60 + x);
        this.b.posunZvisle(-50 + y);
        this.c.posunVodorovne(-60 + x);
        this.c.posunZvisle(-50 + y);
        this.d.posunVodorovne(-60 + x);
        this.d.posunZvisle(-50 + y);
        this.e.posunVodorovne(-60 + x);
        this.e.posunZvisle(-50 + y);
        this.f.posunVodorovne(-60 + x);
        this.f.posunZvisle(-50 + y);
        this.g.posunVodorovne(-60 + x);
        this.g.posunZvisle(-50 + y);
        
        this.a.zmenStrany(this.dlzkaObdlznika, this.sirkaObdlznika);
        this.g.zmenStrany(this.dlzkaObdlznika, this.sirkaObdlznika);
        this.d.zmenStrany(this.dlzkaObdlznika, this.sirkaObdlznika);
        
        this.b.zmenStrany(this.sirkaObdlznika, this.dlzkaObdlznika);
        this.c.zmenStrany(this.sirkaObdlznika, this.dlzkaObdlznika);
        this.e.zmenStrany(this.sirkaObdlznika, this.dlzkaObdlznika);
        this.f.zmenStrany(this.sirkaObdlznika, this.dlzkaObdlznika);
        
        this.a.posunVodorovne(this.sirkaObdlznika);
        this.f.posunZvisle(this.sirkaObdlznika);
        this.b.posunZvisle(this.sirkaObdlznika);
        this.b.posunVodorovne(this.sirkaObdlznika + this.dlzkaObdlznika);
        this.g.posunVodorovne(this.sirkaObdlznika);
        this.g.posunZvisle(this.sirkaObdlznika + this.dlzkaObdlznika);
        this.c.posunZvisle(2 * this.sirkaObdlznika + this.dlzkaObdlznika);
        this.c.posunVodorovne(this.sirkaObdlznika + this.dlzkaObdlznika);
        this.e.posunZvisle(2 * this.sirkaObdlznika + this.dlzkaObdlznika);
        this.d.posunVodorovne(this.sirkaObdlznika);
        this.d.posunZvisle(2 * this.sirkaObdlznika + 2 * this.dlzkaObdlznika);
        
        /*
        this.a.zobraz();
        this.b.zobraz();
        this.c.zobraz();
        this.d.zobraz();
        this.e.zobraz();
        this.f.zobraz();
        this.g.zobraz();
        */
    }
    
    /**
     * Zobrazí zadanú číslicu.
     * @param cislica číslo od 0 do 9 vrátane
     */
    public void zobrazCislicu(int cislica) {
        this.a.skry();
        this.b.skry();
        this.c.skry();
        this.d.skry();
        this.e.skry();
        this.f.skry();
        this.g.skry();
        
        switch (cislica) {
            case 0:
                this.a.zobraz();
                this.b.zobraz();
                this.c.zobraz();
                this.d.zobraz();
                this.e.zobraz();
                this.f.zobraz();
                break;
            case 1:
                this.b.zobraz();
                this.c.zobraz();
                break;
            case 2:
                this.a.zobraz();
                this.b.zobraz();
                this.d.zobraz();
                this.e.zobraz();
                this.g.zobraz();
                break;
            case 3:
                this.a.zobraz();
                this.b.zobraz();
                this.c.zobraz();
                this.d.zobraz();
                this.g.zobraz();
                break;
            case 4:
                this.b.zobraz();
                this.c.zobraz();
                this.f.zobraz();
                this.g.zobraz();
                break;
            case 5:
                this.a.zobraz();
                this.c.zobraz();
                this.d.zobraz();
                this.f.zobraz();
                this.g.zobraz();
                break;
            case 6:
                this.a.zobraz();
                this.c.zobraz();
                this.d.zobraz();
                this.e.zobraz();
                this.f.zobraz();
                this.g.zobraz();
                break;
            case 7:
                this.a.zobraz();
                this.b.zobraz();
                this.c.zobraz();
                break;
            case 8:
                this.a.zobraz();
                this.b.zobraz();
                this.c.zobraz();
                this.d.zobraz();
                this.e.zobraz();
                this.f.zobraz();
                this.g.zobraz();
                break;
            case 9:
                this.a.zobraz();
                this.b.zobraz();
                this.c.zobraz();
                this.d.zobraz();
                this.f.zobraz();
                this.g.zobraz();
                break;
        }
    }
    
    /**
     * Vráti šírku segmentového znaku.
     * @return šírka segmentového znaku
     */
    public int getSirka() {
        return (2 * this.sirkaObdlznika + this.dlzkaObdlznika);
    }
    
    /**
     * Nastaví farbu segmentového znaku.
     * @param farba text s farbou znaku
     */
    public void setFarba(String farba) {
        this.a.zmenFarbu(farba);
        this.b.zmenFarbu(farba);
        this.c.zmenFarbu(farba);
        this.d.zmenFarbu(farba);
        this.e.zmenFarbu(farba);
        this.f.zmenFarbu(farba);
        this.g.zmenFarbu(farba);
    }
}
