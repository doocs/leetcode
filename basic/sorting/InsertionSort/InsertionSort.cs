using System.Diagnostics;
using static System.Console;
namespace Pro;
public class Program
{
    public static void Main()
    {
        int[] test = new int[] { 31, 12, 10, 5, 6, 7, 8, 10, 23, 34, 56, 43, 32, 21 };
        InsertSortNums(test);
        foreach (var item in test)
        {
            WriteLine(item);
        }
    }
    public static void InsertSortNums(int[] nums)
    {
        for (int initial = 1; initial < nums.Length; initial++)
        {
            for (int second_sort = 0; second_sort < initial; second_sort++)
            {
                if (nums[second_sort] > nums[initial])
                {
                    swap(ref nums[second_sort], ref nums[initial]);
                }
            }
        }
    }

    private static void swap(ref int compare_left, ref int compare_right)
    {
        int temp = compare_left;
        compare_left = compare_right;
        compare_right = temp;
    }
}