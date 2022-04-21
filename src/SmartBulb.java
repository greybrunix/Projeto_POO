package src;
public class SmartBulb{
    /* Constants Enums Structs */
    public static final int Consumption_Val = 132;

    public enum Mode{
        ON,
        OFF
    };
    public enum Tone {
        Neutral,
        Warm,
        Cold
    };
    /**/

    /* Variables */
    private Mode mode;
    private Tone tone; /* Enum Tone */
    private int dimensions; /* in centimetres */
    private double daily_consumption; /* formula is group specific */
    /* */


    /* Constructors */
    public SmartBulb(){
        this.mode = Mode.OFF;
        this.tone = Tone.Neutral;
        this.dimensions = 1;
        this.computeConsumption();
    }

    public SmartBulb(Mode mode, Tone tone, int dimensions, double daily_consumption){
        this.setMode(mode);
        this.setTone(tone);
        this.setDimensions(dimensions);
        this.computeConsumption();
    }

    public SmartBulb(SmartBulb b){
        this.setMode(b.getMode());
        this.setTone(b.getTone());
        this.setDimensions(b.getDimensions());
        this.computeConsumption();
    }
    /* */


    /* Getters */
    public Tone getTone(){
        return this.tone;
    }
    public Mode getMode(){
        return this.mode;
    }

    public int getDimensions(){
        return this.dimensions;
    }

    public double getConsumption(){
        return this.daily_consumption;
    }



    /* Setters */
    private void setMode(Mode mode){
        this.mode =  mode;
    }
    public void setTone(Tone tone){
        this.tone = tone;
        this.computeConsumption();
    }
    public void setDimensions(int dimensions){
        this.dimensions = dimensions;
    }


    /* Computations */
    public void computeConsumption(){
        if (this.getMode() == Mode.ON)
            switch(this.getTone()){
                case Cold: 
                    this.daily_consumption = Consumption_Val + Consumption_Val*0.5;
                    break;
                case Neutral:
                    this.daily_consumption = Consumption_Val + Consumption_Val*1;
                    break;
                case Warm:
                    this.daily_consumption = Consumption_Val + Consumption_Val*2;
                    break;
            }
        else
            this.daily_consumption = 0;
    }



    /* Regular Methods */
    public void setON(){
        if (this.getMode() == Mode.OFF){
            this.setMode(Mode.ON);
            this.computeConsumption();
        }
    }

    public void setOFF(){
        if (this.getMode() == Mode.ON){
            this.setMode(Mode.OFF);
            this.computeConsumption();
        }
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