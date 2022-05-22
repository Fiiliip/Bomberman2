package sk.uniza.fri.gui.numberdisplay;

/**
 * Trieda, ktorá zobrazuje ľubovoľné číslo na číselnom displeji, skladajúceho sa zo segmentových znakoch v rozsahu od 0 do 99 vrátane.
 * 
 * Táto trieda bola vytvorená na cvičeniach v škole a upravená mimo cvičení pre použitie na aktuálnu semestrálnu prácu!
 * 
 * @author Michal Mrena
 * @author Filip Kubica
 */
public class CiselnyDisplej {
    
    private int hranica;
    private int hodnota;
    
    private SegmentovyZnak desiatky;
    private SegmentovyZnak jednotky;
    
    /**
     *  Zobrazuje hodnoty z celočíselného 
     *  intervalu <0, hranica).
     */
    public CiselnyDisplej(int hranica, int lavyHornyX, int lavyHornyY) {
        this.hranica = 100;
        if (hranica <= 100 && hranica > 0) {
            this.hranica = hranica;
        }
        this.hodnota = 0;
        
        this.desiatky = new SegmentovyZnak(lavyHornyX, lavyHornyY);
        this.jednotky = new SegmentovyZnak(lavyHornyX + this.desiatky.getSirka() + 3, lavyHornyY);
        
        this.zobraz();
    }
    
    /**
     * Nastaví novú hodnotu.
     * @param novaHodnota nová hodnota, ktorá sa má zobraziť
     */
    public void setHodnota(int novaHodnota) {
        if (novaHodnota < this.hranica && novaHodnota >= 0) {
            this.hodnota = novaHodnota; 
        }
        
        this.zobraz();
    }
    
    /**
     *  Vráti dvojciferné číslo s prefixom 0 ak je aktuálna
     *  hodnota jednociferná.
     */
    public String getFormatovanaHodnota() {
        if (this.hodnota < 10) {
            return "0" + this.hodnota;
        } else {
            return "" + this.hodnota;
        }
    }
    
    /**
     * Zobrazí číselný displej s nastavenou hodnotou.
     */
    public void zobraz() {
        if (this.desiatky != null && this.jednotky != null) {
            this.desiatky.zobrazCislicu(this.hodnota / 10);
            this.jednotky.zobrazCislicu(this.hodnota % 10);
        }
    }
}
