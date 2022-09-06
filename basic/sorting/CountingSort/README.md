# 计数排序

计数排序是一个非基于比较的排序算法，是一种空间换时间的算法，是通过元素的值来确定元素的位置， 适用于非负整数的排序（如果负数需要排序，那么需要使所有元素都添加上 `0-min` 之后再减去该值）

算法描述:

-   给定原数组中元素值的范围 `[min, max]`
-   创建一个新数组 `c` ，其长度是 `max-min+1`，其元素默认值都是 `0`
-   遍历原数组中的元素，以原数组中的元素作为 `c` 数组的索引，以原数组中的元素出现次数作为 `c` 数组的元素值。
-   创建结果数组 `r`，起始索引 `i`
-   遍历数组 `c`，找出其中元素大于 `0` 的元素，将其对应的索引作为元素值填充到 `r` 数组中，每处理一次，`c` 中的元素值减 `1`，直到该元素值不大于 `0`，依次处理 `c` 中剩下的元素

## 代码示例

<!-- tabs:start -->

### **Java**

```java
public static void sort(int[] nums, int min, int max) {
    int[] c = new int[max - min + 1];
    for (int v : nums) {
        c[v - min]++;
    }
    for (int i = 1; i < c.length; i++) {
        c[i] += c[i - 1];
    }
    int[] r = new int[nums.length];
    for (int i = nums.length - 1; i >= 0; i--) {
        int v = nums[i];
        int a = c[v];
        r[a - 1] = v + min;
        c[v]--;
    }
    System.arraycopy(r, 0, nums, 0, r.length);
}
```

### **Go**

```go
func CountingSort(nums []int, min, max int) {
	n := len(nums)
	k := max - min + 1
	c := make([]int, k)
	for _, v := range nums {
		c[v-min]++
	}

	for i := 1; i < k; i++ {
		c[i] += c[i-1]
	}

	r := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		v := nums[i]
		a := c[v]
		r[a-1] = v + min
		c[v]--
	}

	for i, v := range r {
		nums[i] = v
	}
}
```

<!-- tabs:end -->

## 算法分析

-   时间复杂度 `O(n+k)`， 其中 `n` 为排序数组长度，`k` 为排序数组中数值的取值范围，当 `k < n` 时，时间复杂度为 `O(n)`
-   空间复杂度 `O(n+k)`， 其中 `n` 为排序数组长度，`k` 为排序数组中数值的取值范围，当 `k < n` 时，空间复杂度为 `O(n)`
