using static System.Console;
namespace Pro;
public class Program
{
    public static void Main()
    {
        int[] test = new int[] {90, 12, 77, 9, 0, 2, 23, 23, 3, 57, 80};
        SelectionSortNums(test);
        foreach (var item in test)
        {
            WriteLine(item);
        }
    }
    public static void SelectionSortNums(int[] nums)
    {        
        for (int initial = 0; initial < nums.Length; initial++)
        {
            for (int second_sort = initial; second_sort < nums.Length; second_sort++)
            {
                if (nums[initial] > nums[second_sort])
                {
                    swap(ref nums[initial], ref nums[second_sort]);
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
