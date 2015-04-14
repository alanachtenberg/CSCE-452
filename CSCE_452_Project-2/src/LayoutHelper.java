import java.awt.*;

/**
 * Created by Alan on 4/14/2015.
 * Must be used in conjunction with GridBagLayout
 */
public class LayoutHelper {
    GridBagConstraints constraints;
    public static final int BOTH=GridBagConstraints.BOTH;
    public static final int HORIZONTAL=GridBagConstraints.HORIZONTAL;
    public static final int VERTICAL= GridBagConstraints.VERTICAL;

    LayoutHelper(){
        constraints=new GridBagConstraints();
        constraints.weightx=1;//defaults the white space to be evenly distributed
        constraints.weighty=1;
    }
    public void setPosition(int x, int y){
        constraints.gridx=x;
        constraints.gridy=y;
    }
    public void setSize(int w, int h){
        constraints.gridwidth=w;
        constraints.gridheight=h;
    }
    public void setFill(int type){
        constraints.fill=type;
    }
    public void setWeights(int weightx, int weighty){
        constraints.weightx=weightx;
        constraints.weighty=weighty;
    }
    public GridBagConstraints getConstraints(){
        return constraints;
    }
}
