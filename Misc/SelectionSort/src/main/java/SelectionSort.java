public class SelectionSort{
  public static void main (String args[]){
    int[] array = {6,2,20,16,10,44};
    int pos;
    int aux;
    int cuantos=0;
   
    for(int i=0;i<array.length;i++){
      pos=i;
      aux=array[i];
       
        while((pos>0) && (array[pos-1]>aux)){
       
        array[pos]=array[pos-1];
          pos--;
          cuantos ++;
           
     
      }
      array[pos]=aux;
    }
    for (int i=0;i<array.length;i++){
    System.out.println(array[i] +"   ");
    System.out.println(cuantos);
    }
 
  }
}
