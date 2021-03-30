# 二分查找

二分查找是一种非常高效的查找算法，高效到什么程度呢？我们来分析一下它的时间复杂度。

假设数据大小是 n，每次查找后数据都会缩小为原来的一半，也就是会除以 2。最坏情况下，直到查找区间被缩小为空，才停止。

被查找区间的大小变化为：

```
n, n/2, n/4, n/8, ..., n/(2^k)
```

可以看出来，这是一个等比数列。其中 `n/(2^k)=1` 时，k 的值就是总共缩小的次数。而每一次缩小操作只涉及两个数据的大小比较，所以，经过了 k 次区间缩小操作，时间复杂度就是 O(k)。通过 `n/(2^k)=1`，我们可以求得 `k=log2n`，所以时间复杂度就是 O(logn)。

## 代码示例

注意容易出错的 3 个地方。

1. 循环退出条件是 `low <= high`，而不是 `low < high`；
1. mid 的取值，可以是 `mid = (low + high) / 2`，但是如果 low 和 high 比较大的话，`low + high` 可能会溢出，所以这里写为 `mid = (low + high) >>> 1`；
1. low 和 high 的更新分别为 `low = mid + 1`、`high = mid - 1`。

<!-- tabs:start -->

### **Java**

非递归实现：

```java
public class BinarySearch {

    private static int search(int[] nums, int low, int high, int val) {
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] == val) {
                return mid;
            } else if (nums[mid] < val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找(非递归)
     *
     * @param nums 有序数组
     * @param val 要查找的值
     * @return 要查找的值在数组中的索引位置
     */
    private static int search(int[] nums, int val) {
        return search(nums, 0, nums.length - 1, val);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 7, 8, 9};

        // 非递归查找
        int r1 = search(nums, 7);
        System.out.println(r1);
    }
}
```

递归实现：

```java
public class BinarySearch {

    private static int searchRecursive(int[] nums, int low, int high, int val) {
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] == val) {
                return mid;
            } else if (nums[mid] < val) {
                return searchRecursive(nums, mid + 1, high, val);
            } else {
                return searchRecursive(nums, low, mid - 1, val);
            }
        }
        return -1;
    }

    /**
     * 二分查找(递归)
     *
     * @param nums 有序数组
     * @param val 要查找的值
     * @return 要查找的值在数组中的索引位置
     */
    private static int searchRecursive(int[] nums, int val) {
        return searchRecursive(nums, 0, nums.length - 1, val);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 7, 8, 9};

        // 递归查找
        int r2 = searchRecursive(nums, 7);
        System.out.println(r2);
    }
}
```

<!-- tabs:end -->
