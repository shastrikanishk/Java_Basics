public class polymorphism_conecpt {


    public static void Polimorconcept(){
        int[] ar_l = new int[3];
        ar_l[0]=2;
        ar_l[1]=3;
        ar_l[2]=4;
        int sum=0;
        for (int i=0;i<3;i++){


            System.out.print("Arraylist values - "+ar_l[i]);
            System.out.print("\n declared i variable values - "+i);

            sum = i+sum;
            System.out.println("\n sum of i+sum - "+sum);


        }

    }

    public static void main(String[] args) {
        Polimorconcept();  // Call the method inside main
    }

}
