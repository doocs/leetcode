# 二分查找 II

前面讲的二分查找算法，是最为简单的一种，在不存在重复元素的有序数组中，查找值等于给定值的元素。

接下来，我们来看看二分查找算法四种常见的变形问题，分别是：

1. 查找第一个值等于给定值的元素
1. 查找最后一个值等于给定值的元素
1. 查找第一个大于等于给定值的元素
1. 查找最后一个小于等于给定值的元素

## 1. 查找第一个值等于给定值的元素

<!-- tabs:start -->

### **Java**

```java
public static int search(int[] nums, int val) {
    int n = nums.length;
    int low = 0, high = n - 1;
    while (low <= high) {
        int mid = low + ((high - low) >> 1);
        if (nums[mid] < val) {
            low = mid + 1;
        } else if (nums[mid] > val) {
            high = mid - 1;
        } else {
            // 如果nums[mid]是第一个元素，或者nums[mid-1]不等于val
            // 说明nums[mid]就是第一个值为给定值的元素
            if (mid == 0 || nums[mid - 1] != val) {
                return mid;
            }
            high = mid - 1;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

## 2. 查找最后一个值等于给定值的元素

<!-- tabs:start -->

### **Java**

```java
public static int search(int[] nums, int val) {
    int n = nums.length;
    int low = 0, high = n - 1;
    while (low <= high) {
        int mid = low + ((high - low) >> 1);
        if (nums[mid] < val) {
            low = mid + 1;
        } else if (nums[mid] > val) {
            high = mid - 1;
        } else {
            // 如果nums[mid]是最后一个元素，或者nums[mid+1]不等于val
            // 说明nums[mid]就是最后一个值为给定值的元素
            if (mid == n - 1 || nums[mid + 1] != val) {
                return mid;
            }
            low = mid + 1;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

## 3. 查找第一个大于等于给定值的元素

<!-- tabs:start -->

### **Java**

```java
public static int search(int[] nums, int val) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
        int mid = low + ((high - low) >> 1);
        if (nums[mid] < val) {
            low = mid + 1;
        } else {
            // 如果nums[mid]是第一个元素，或者nums[mid-1]小于val
            // 说明nums[mid]就是第一个大于等于给定值的元素
            if (mid == 0 || nums[mid - 1] < val) {
                return mid;
            }
            high = mid - 1;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

## 4. 查找最后一个小于等于给定值的元素

<!-- tabs:start -->

### **Java**

```java
public static int search(int[] nums, int val) {
    int n = nums.length;
    int low = 0, high = n - 1;
    while (low <= high) {
        int mid = low + ((high - low) >> 1);
        if (nums[mid] > val) {
            high = mid - 1;
        } else {
            // 如果nums[mid]是最后一个元素，或者nums[mid+1]大于val
            // 说明nums[mid]就是最后一个小于等于给定值的元素
            if (mid == n - 1 || nums[mid + 1] > val) {
                return mid;
            }
            low = mid + 1;
        }
    }
    return -1;
}
```

<!-- tabs:end -->
