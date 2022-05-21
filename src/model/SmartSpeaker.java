package model;


/**
 * Subclass of SmartDevice which implements a volume,
 *  a radio station and a brand name
 * 
 * 
 */
public class SmartSpeaker extends SmartDevice{
    /* Constants  enums and structs */
    public static final int MAX = 20;    

    /* Variables */
    private double daily_consumption;
    private int volume;
    private String radio;
    private String brand;
    private double brand_base_cons;


    /* Constructors */
    public SmartSpeaker(){
    }
    public SmartSpeaker(boolean mode, String id, int v, String r, String b, double bbp){
        super(mode, id);
        this.setVolume(v);
        this.setRadio(r);
        this.setBrand(b);
        this.setBrandBP(bbp);
        this.computeConsumption();
    }
    public SmartSpeaker(SmartSpeaker s){
        super(s.getMode(), s.getId());
        this.setVolume(s.getVolume());
        this.setRadio(s.getRadio());
        this.setBrand(s.getBrand());
        this.setBrandBP(s.getBrandBP());
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
    public double getBrandBP(){
        return this.brand_base_cons;
    }
    public double getConsumption(){
        computeConsumption();
        return this.daily_consumption;
    }


    /* Setters */
    public void setVolume(int v){
        if (v >= 0 && v <= 20){
        this.volume = v;
        this.computeConsumption();
        }
        if (v < 0){
            this.volume = 0;
            this.computeConsumption();
        }
        if (v > 20){
            this.volume = 20;
            this.computeConsumption();
        }
    }
    public void setRadio(String r){
        this.radio = r;
    }
    public void setBrand(String b){
        this.brand = b;
        this.computeConsumption();
    }
    public void setBrandBP(double bbp){
        this.brand_base_cons = bbp;
        this.computeConsumption();
    }

    /* Computations */
    public void computeConsumption(){
        if (this.getMode()){
            this.daily_consumption = .6*this.brand_base_cons*0.02 + .4*this.volume*0.005;
            this.daily_consumption *= 24; /* Computation */
        }
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
    public void incTone(){}
    public void decTone(){}

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
        return sb.append("( ")
          .append(this.getId())
          .append(": (ON: ")
          .append(this.getMode())
          .append(", VOLUME: ")
          .append(this.getVolume())
          .append(", RADIO: ")
          .append(this.getRadio())
          .append(" FM, ")
          .append(this.getBrand())
          .append(", ")
          .append(this.getConsumption())
          .append("KWh) )").toString();
    }
    @Override
    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }
}