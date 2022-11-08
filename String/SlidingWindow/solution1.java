import java.util.Scanner;
public class Digit_Sum 
{
    public static void main(String args[])
    {
        int m = 12345;
        int n, sum = 0;

        //第一题这个答案应该是对的才对，你再查一下
        int count = 0;
        while(m > 0)
        {
            n = m % 10;
            if (count / 2 == 0) {
                sum = sum + n;
            } else {
                sum = sum - n;
            }
            m = m / 10;
            count++;
        }
        return sum;
    }


    // // 第三题
    //  // function to segregate 0s and 1s
    // static void segregate0and1(int arr[], int n)
    // {
    //     int count = 0; // counts the no of zeros in arr
     
    //     for (int i = 0; i < n; i++) {
    //         if (arr[i] == 0)
    //             count++;
    //     }
 
    //     // loop fills the arr with 0 until count
    //     for (int i = 0; i < count; i++)
    //         arr[i] = 0;
 
    //     // loop fills remaining arr space with 1
    //     for (int i = count; i < n; i++)
    //         arr[i] = 1;
    // }
    

}