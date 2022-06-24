public class LengthEvaluator {


    public static void LengthEvaluator(){

    }

    public HavingLength findWithMaxLength(HavingLength[] objects){
        HavingLength max = objects[0];
        for(int i = 0; i < objects.length; i++){
            if(objects[i].length() > max.length()){
                max = objects[i];
            }
        }
        return max;
    }
}
