
# 快速排序

快速排序也采用了分治的思想：把原始的数组筛选成较小和较大的两个子数组，然后递归地排序两个子数组。

## 代码示例

<!-- tabs:start -->

### **Python3**

### **Java**

```java
import java.util.Arrays;

public class QuickSort {

    private static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int[] p = partition(nums, low, high);
        quickSort(nums, low, p[0] - 1);
        quickSort(nums, p[0] + 1, high);
    }

    private static int[] partition(int[] nums, int low, int high) {
        int less = low - 1, more = high;
        while (low < more) {
            if (nums[low] < nums[high]) {
                swap(nums, ++less, low++);
            } else if (nums[low] > nums[high]) {
                swap(nums, --more, low);
            } else {
                ++low;
            }
        }
        swap(nums, more, high);
        return new int[] {less + 1, more};
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 4, 5, 3};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```

<!-- tabs:end -->

## 算法分析

空间复杂度 O(logn)，时间复杂度 O(nlogn)。

对于规模为 n 的问题，一共要进行 log(n) 次的切分，和基准值进行 n-1 次比较，n-1 次比较的时间复杂度是 O(n)，所以快速排序的时间复杂度为 O(nlogn)。

但是，如果每次在选择基准值的时候，都不幸地选择了子数组里的最大或最小值。即每次把把数组分成了两个更小长度的数组，其中一个长度为 1，另一个的长度是子数组的长度减 1。这样的算法复杂度变成 O(n²)。

和归并排序不同，快速排序在每次递归的过程中，只需要开辟 O(1) 的存储空间来完成操作来实现对数组的修改；而递归次数为 logn，所以它的整体空间复杂度完全取决于压堆栈的次数。