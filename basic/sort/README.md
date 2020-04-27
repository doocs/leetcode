# 常见的排序算法

## 冒泡排序

定义一个布尔变量 `hasChange`，用来标记每轮是否进行了交换。在每轮遍历开始时，将 `hasChange` 设置为 false。

### 代码示例

```java
import java.util.Arrays;

public class BubbleSort {
    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private static void bubbleSort(int[] nums) {
        boolean hasChange = true;
        for (int i = 0; i < nums.length - 1 && hasChange; ++i) {
            hasChange = false;
            for (int j = 0; j < nums.length - 1 - i; ++j) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    hasChange = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 9, 5, 8};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```

### 算法分析

空间复杂度 O(1)、时间复杂度 O(n^2)。

分情况讨论：

1. 给定的数组按照顺序已经排好：只需要进行 `n-1` 次比较，两两交换次数为 0，时间复杂度为 O(n)，这是最好的情况。
2. 给定的数组按照逆序排列：需要进行 `n*(n-1)/2` 次比较，时间复杂度为 O(n^2)，这是最坏的情况。
3. 给定的数组杂乱无章。在这种情况下，平均时间复杂度 O(n^2)。

因此，时间复杂度是 O(n^2)，这是一种稳定的排序算法。

> 稳定是指，两个相等的数，在排序过后，相对位置保持不变。

## 插入排序

与冒泡排序对比：

- 在冒泡排序中，经过每一轮的排序处理后，数组后端的数是排好序的。
- 在插入排序中，经过每一轮的排序处理后，数组前端的数是拍好序的。

插入排序的算法思想是：不断将尚未排好序的数插入到已经排好序的部分。

### 代码示例

```java
import java.util.Arrays;

public class InsertionSort {
    private static void insertionSort(int[] nums) {
        for (int i = 1, j, current; i < nums.length; ++i) {
            current = nums[i];
            for (j = i - 1; j >= 0 && nums[j] > current; --j) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = current;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = { 1, 2, 7, 9, 5, 8 };
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```

### 算法分析

空间复杂度 O(1)，时间复杂度 O(n^2)。

分情况讨论：

1. 给定的数组按照顺序排好序：只需要进行 n-1 次比较，两两交换次数为 0，时间复杂度为 O(n)，这是最好的情况。
2. 给定的数组按照逆序排列：需要进行 `n*(n-1)/2` 次比较，时间复杂度为 O(n^2)，这是最坏的情况。
3. 给定的数组杂乱无章：在这种情况下，平均时间复杂度是 O(n^2)。

因此，时间复杂度是 O(n^2)，这也是一种稳定的排序算法。

## 归并排序

归并排序的核心思想是分治，把一个复杂问题拆分成若干个子问题来求解。

归并排序的算法思想是：把数组从中间划分为两个子数组，一直递归地把子数组划分成更小的数组，直到子数组里面只有一个元素的时候开始排序。排序的方法就是按照大小顺序合并两个元素。接着依次按照递归的顺序返回，不断合并排好序的数组，直到把整个数组排好序。

### 代码示例

```java
import java.util.Arrays;

public class MergeSort {

    private static void merge(int[] nums, int low, int mid, int high, int[] temp) {
        int i = low, j = mid + 1, k = low;
        while (k <= high) {
            if (i > mid) {
                temp[k++] = nums[j++];
            } else if (j > high) {
                temp[k++] = nums[i++];
            } else if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        for (i = low; i <= high; ++i) {
            nums[i] = temp[i];
        }
    }

    private static void mergeSort(int[] nums, int low, int high, int[] temp) {
        if (low >= high) {
            return;
        }
        int mid = low + ((high - low) >> 1);
        mergeSort(nums, low, mid, temp);
        mergeSort(nums, mid + 1, high, temp);
        merge(nums, low, mid, high, temp);
    }

    private static void mergeSort(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 4, 5, 3};
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```
