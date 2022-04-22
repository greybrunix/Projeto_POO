package src;

public class SmartSpeaker extends Device {
    /* Constants  enums and structs */
    public static final int MAX = 20;    

    /* Variables */
    private double daily_consumption;
    private int volume;
    private String radio;
    private String brand;


    /* Constructors */
    public SmartSpeaker(){
        super();
        this.setVolume(0);
        this.setRadio("");
        this.setBrand("");
        this.computeConsumption();
    }
    public SmartSpeaker(Mode mode, int v, String r, String b, double dc){
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
        if (this.getMode() == Mode.ON)
            this.daily_consumption = 1; /* Computation */
        else
            this.daily_consumption = 0;
    }

    public void setON(){
        if (this.getMode() == Mode.OFF){
            this.setMode(Mode.ON);
            computeConsumption();
        }
    }
    public void setOFF(){
        if (this.getMode() == Mode.ON){
            this.setMode(Mode.OFF);
            computeConsumption();
        }
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
        sb.append("Speaker with Volume:\t")
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