# 快速排序

快速排序也采用了分治的思想：把原始的数组筛选成较小和较大的两个子数组，然后递归地排序两个子数组。

## 代码示例

<!-- tabs:start -->

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
### **JavaScript**

```js
function quickSort(arr) {
    let len = arr.length;
    return qSort(arr, 0, len - 1);
}

function qSort(arr, left, right) {
    if (left < right) {
        let index = partition(arr, left, right);
        qSort(arr, left, index - 1);
        qSort(arr, index + 1, right);
    }
    return arr;
}

function partition(arr, left, right) {
    let temp = arr[left];
    while (left < right) {
        while (left < right && arr[right] > temp) {
            right--;
        }
        arr[left] = arr[right];
        while (left < right && arr[left] <= temp) {
            left++;
        }
        arr[right] = arr[left];
    }
    arr[left] = temp;
    console.log(arr)
    return left;
}

arr = [3, 5, 6, 2, 1, 7, 4];
console.log(quickSort(arr))

```

<!-- tabs:end -->

## 算法分析

空间复杂度 O(logn)，时间复杂度 O(nlogn)。

对于规模为 n 的问题，一共要进行 log(n) 次的切分，和基准值进行 n-1 次比较，n-1 次比较的时间复杂度是 O(n)，所以快速排序的时间复杂度为 O(nlogn)。

但是，如果每次在选择基准值的时候，都不幸地选择了子数组里的最大或最小值。即每次把把数组分成了两个更小长度的数组，其中一个长度为 1，另一个的长度是子数组的长度减 1。这样的算法复杂度变成 O(n²)。

和归并排序不同，快速排序在每次递归的过程中，只需要开辟 O(1) 的存储空间来完成操作来实现对数组的修改；而递归次数为 logn，所以它的整体空间复杂度完全取决于压堆栈的次数。

## 如何优化快速排序？

前面讲到，最坏情况下快速排序的时间复杂度是 O(n²)，实际上，这种 O(n²) 时间复杂度出现的主要原因还是因为我们基准值选得不够合理。最理想的基准点是：**被基准点分开的两个子数组中，数据的数量差不多**。

如果很粗暴地直接选择第一个或者最后一个数据作为基准值，不考虑数据的特点，肯定会出现之前讲的那样，在某些情况下，排序的最坏情况时间复杂度是 O(n²)。

有两个比较常用的分区算法。

### 1. 三数取中法

我们从区间的首、尾、中间，分别取出一个数，然后对比大小，取这 3 个数的中间值作为分区点。这样每间隔某个固定的长度，取数据出来比较，将中间值作为分区点的分区算法，肯定要比单纯取某一个数据更好。但是，如果要排序的数组比较大，那“三数取中”可能就不够了，可能要“五数取中”或者“十数取中”。

### 2. 随机法

随机法就是每次从要排序的区间中，随机选择一个元素作为分区点。这种方法并不能保证每次分区点都选的比较好，但是从概率的角度来看，也不大可能会出现每次分区点都选的很差的情况，所以平均情况下，这样选的分区点是比较好的。时间复杂度退化为最糟糕的 O(n²) 的情况，出现的可能性不大。
