package model;

/**
 * Subclass of SmartDevice which implements a volume,
 *  a radio station and a brand name
 * 
 * 
 */
public class SmartSpeaker extends SmartDevice {
    /* Constants  enums and structs */
    public static final int MAX = 20;    

    /* Variables */
    private double daily_consumption;
    private int volume;
    private String radio;
    private String brand;
    private double brand_base_price;


    /* Constructors */
    public SmartSpeaker(){
    }
    public SmartSpeaker(boolean mode, String id, int v, String r, String b){
        super(mode, id);
        this.setVolume(v);
        this.setRadio(r);
        this.setBrand(b);
        this.computeConsumption();
    }
    public SmartSpeaker(SmartSpeaker s){
        super(s.getMode(), s.getId());
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
            this.daily_consumption = .6*brand_base_price + .4*volume; /* Computation */
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
        setMode(true);
        computeConsumption();
    }
    public void setOFF(){
        setMode(false);
        computeConsumption();
    }

    /* Overrides */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        SmartSpeaker speaker = (SmartSpeaker) o;
        return ( super.equals(speaker)
            && this.getVolume() == speaker.getVolume()
            && this.getRadio().equals(speaker.getRadio())
            && this.getBrand().equals(speaker.getBrand()));
    }
    @Override
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
    @Override
    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }
}
// TODO: Check the computation for the Speaker's daily consumption 