package src.classes;
/**
 * Subclass of SmartDevice which implements a tone of light,
 * the dimensions of the bulb in centimetres and the daily consumption
 */
public class SmartBulb extends SmartDevice{
    /* Constants */
    public static final int Consumption_Val = 132;
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;

    /* Variables */
    private int tone; /* WARM NEUTRAL COLD */
    private int dimensions; /* in centimetres */
    private double daily_consumption; /* formula is group specific */

    /* Constructors */
    public SmartBulb(){ // Might remove this
        super();
        this.tone = NEUTRAL;
        this.dimensions = 1;
        this.computeConsumption();
    }
    public SmartBulb(boolean mode,String id,int tone, int dimensions, double daily_consumption){
        super();
        this.setTone(tone);
        this.setDimensions(dimensions);
        this.computeConsumption();
    }
    public SmartBulb(SmartBulb b){
        super();
        this.setTone(b.getTone());
        this.setDimensions(b.getDimensions());
        this.computeConsumption();
    }

    /* Getters */
    public int getTone(){
        return this.tone;
    }
    public int getDimensions(){
        return this.dimensions;
    }
    public double getConsumption(){
        return this.daily_consumption;
    }

    /* Setters */
    public void setTone(int t){
        if (t > WARM) this.tone = WARM;
        else if (t < COLD) this.tone = COLD;
        else this.tone = t;
        this.computeConsumption();
    }
    public void setDimensions(int dimensions){
        this.dimensions = dimensions;
    }

    /* Computations */
    public void computeConsumption(){
        if (this.getMode())
            switch(this.getTone()){
                case COLD: 
                    this.daily_consumption = Consumption_Val + Consumption_Val*0.5;
                    break;
                case NEUTRAL:
                    this.daily_consumption = Consumption_Val + Consumption_Val*1;
                    break;
                case WARM:
                    this.daily_consumption = Consumption_Val + Consumption_Val*2;
                    break;
            }
        else
            this.daily_consumption = 0;
    }
    public void setON(){
        setMode(true);
        this.computeConsumption();
    }
    public void setOFF(){
        setMode(false);
        this.computeConsumption();
    }

    /* Overrides */
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        SmartBulb bulb = (SmartBulb) o;
        return (this.getMode() == bulb.getMode()
                && this.getDimensions() == bulb.getDimensions()
                && this.getTone() == bulb.getTone());
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("This lightbulb is turned ")
          .append(this.getMode())
          .append("\nThis lightbulb is in tone:\t")
          .append(this.getTone())
          .append("\nThis lightbulb has dimensions in cm:\t")
          .append(this.getDimensions())
          .append("\nThis lightbulb is consuming:\t")
          .append(this.getConsumption())
          .append('\n');
        return sb.toString();
    }
    public SmartBulb clone(){
        return new SmartBulb(this);
    }
}

// TODO: Alter the computation for the bulb when the formula is announced