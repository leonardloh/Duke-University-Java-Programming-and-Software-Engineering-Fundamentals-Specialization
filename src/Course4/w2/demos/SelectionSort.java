package Course4.w2.demos;

public class SelectionSort {

    public static void selectSort(int[] array){
        for (int i = 0; i < array.length -1; i++)
        {
            int minIdx = i;
            for (int j = i+1 ; j < array.length; j++)
            {
                if (array[j] < array[minIdx])
                {
                    minIdx = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIdx];
            array[minIdx] = temp;
        }

        for (int i : array)
        {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        selectSort(new int[] {10 ,6 ,8, 3});
    }
}
