using static System.Console;
namespace Pro;
public class Program
{
    public static void Main()
    {
        int[] test = new int[] { 56, 876, 34, 23, 45, 501, 2, 3, 4, 6, 5, 7, 8, 9, 11, 10, 12, 23, 34 };
        BubbleSortNums(test);
        foreach (var item in test)
        {
            WriteLine(item);
        }
        ReadLine();
    }
    public static void BubbleSortNums(int[] nums)
    {
        int numchange = 0;
        for (int initial = 0; initial < nums.Length - numchange; initial++)
        {
            WriteLine($"{initial} start ");
            // 记录此值 用于迭代开始位置
            bool changelog = false;
            for (int second_sortnum = initial; second_sortnum < nums.Length - 1; second_sortnum++)
            {
                if (nums[second_sortnum] > nums[second_sortnum + 1])
                {
                    swap(ref nums[second_sortnum], ref nums[second_sortnum + 1]);
                    if (!changelog)
                    {
                        // 记录转换的位置，让initial开始位置从转换位置前开始
                        initial = ((second_sortnum - 2) > 0) ? (second_sortnum - 2) : -1;
                        numchange += 1;
                    }
                    changelog = true;
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
