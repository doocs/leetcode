# 插入排序

与冒泡排序对比：

- 在冒泡排序中，经过每一轮的排序处理后，数组后端的数是排好序的。
- 在插入排序中，经过每一轮的排序处理后，数组前端的数是排好序的。

插入排序的算法思想是：不断将尚未排好序的数插入到已经排好序的部分。

## 代码示例

<!-- tabs:start -->

### **Python3**

### **Java**

```java
import java.util.Arrays;

public class InsertionSort {

    private static void insertionSort(int[] nums) {
        for (int i = 1, j, n = nums.length; i < n; ++i) {
            int num = nums[i];
            for (j = i - 1; j >=0 && nums[j] > num; --j) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = num;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 9, 5, 8};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```

<!-- tabs:end -->

## 算法分析

空间复杂度 O(1)，时间复杂度 O(n²)。

分情况讨论：

1. 给定的数组按照顺序排好序：只需要进行 n-1 次比较，两两交换次数为 0，时间复杂度为 O(n)，这是最好的情况。
2. 给定的数组按照逆序排列：需要进行 `n*(n-1)/2` 次比较，时间复杂度为 O(n²)，这是最坏的情况。
3. 给定的数组杂乱无章：在这种情况下，平均时间复杂度是 O(n²)。

因此，时间复杂度是 O(n²)，这也是一种稳定的排序算法。
