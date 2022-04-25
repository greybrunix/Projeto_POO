package src;

/**
 * Subclass of SmartDevice which implements a volume,
 *  a radio station and a brand name
 */
public class SmartSpeaker extends SmartDevice {
    /* Constants  enums and structs */
    public static final int MAX = 20;    

    /* Variables */
    private double daily_consumption;
    private int volume;
    private String radio;
    private String brand;


    /* Constructors */
    public SmartSpeaker(){// Might remove this
        super();
        this.setVolume(0);
        this.setRadio("");
        this.setBrand("");
        this.computeConsumption();
    }
    public SmartSpeaker(boolean mode, String id, int v, String r, String b){
        super();
        this.setVolume(v);
        this.setRadio(r);
        this.setBrand(b);
        this.computeConsumption();
    }
    public SmartSpeaker(SmartSpeaker s){
        super();
        this.setVolume(s.getVolume());
        this.setRadio(s.getRadio());
        this.setBrand(s.getBrand());
        this.computeConsumption();
    }


    /* Getters */
    public int getVolume(){
        return this.volume;
    }
    public String getRadio(){
        return this.radio;
    }
    public String getBrand(){
        return this.brand;
    }
    public double getConsumption(){
        return this.daily_consumption;
    }


    /* Setters */
    public void setVolume(int v){
        this.volume = v;
        this.computeConsumption();
    }
    public void setRadio(String r){
        this.radio = r;
    }
    public void setBrand(String b){
        this.brand = b;
        this.computeConsumption();
    }


    /* Computations */
    public void computeConsumption(){
        if (this.getMode())
            this.daily_consumption = 1; /* Computation */
        else
            this.daily_consumption = 0;
    }
    public void volumeUP(){
        if (this.getVolume() < MAX){
            this.setVolume(this.volume++);
        }
    }
    public void volumeDOWN(){
        if (this.getVolume() > 0){
            this.setVolume(this.volume--);
        }
    }

    public void setON(){
        this.setMode(true);
        computeConsumption();
    }
    public void setOFF(){
        this.setMode(false);
        computeConsumption();
    }

    /* Overrides */
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        SmartSpeaker speaker = (SmartSpeaker) o;
        return (this.getVolume() == speaker.getVolume()
            && this.getRadio().equals(speaker.getRadio())
            && this.getBrand().equals(speaker.getBrand()));
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Speaker with id:\t")
          .append(this.getId())
          .append("Is this Speaker on?\t")
          .append(this.getMode())
          .append("Speaker with Volume:\t")
          .append(this.getVolume())
          .append("\nPlaying the radio station:\t")
          .append(this.getRadio())
          .append("\nThis speaker is from the brand:\t")
          .append(this.getBrand())
          .append("\nThis speaker is consuming:\t")
          .append(this.getConsumption())
          .append('\n');
        return sb.toString();
    }
    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }
}
// TODO: Check the computation for the Speaker's daily consumption 