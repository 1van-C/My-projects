class Burbuja{
    public static void main(String []args) {
        int temp;
        int ciclos = 1;
        int arreglo[] = {48, 0, 5, -8, 1, 4, 78, 3, 10};

        for (int i = 0; i < arreglo.length-1; i++) {
            for (int j = 0; j < arreglo.length - i - 1; j++) {
                if (arreglo[j] < arreglo[j+1]){
                    temp = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = temp;
                }
            }
            ciclos += 1;
        }

        System.out.println("El arreglo en orden descendiente, despues de " + ciclos + " iteraciones, es:"); 
        for (int i:arreglo)
            System.out.println(i);
        }
}
