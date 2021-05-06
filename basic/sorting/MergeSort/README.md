# 归并排序

归并排序的核心思想是分治，把一个复杂问题拆分成若干个子问题来求解。

归并排序的算法思想是：把数组从中间划分为两个子数组，一直递归地把子数组划分成更小的数组，直到子数组里面只有一个元素的时候开始排序。排序的方法就是按照大小顺序合并两个元素。接着依次按照递归的顺序返回，不断合并排好序的数组，直到把整个数组排好序。

## 代码示例

<!-- tabs:start -->

### **Java**

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

        System.arraycopy(tmp, low, nums, low, high - low + 1);
    }

    private static void mergeSort(int[] nums, int low, int high, int[] temp) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) >>> 1;
        mergeSort(nums, low, mid, temp);
        mergeSort(nums, mid + 1, high, temp);
        merge(nums, low, mid, high, temp);
    }

    private static void mergeSort(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        mergeSort(nums, 0, n - 1, temp);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 4, 5, 3};
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```

### **JavaScript**

```js
function mergeSort(arr) {
    if (arr.length < 2) return arr;
    let mid = Math.ceil(arr.length / 2);
    let arrLeft = mergeSort(arr.splice(0, mid));
    let arrRight = mergeSort(arr.splice(-mid));
    return merge(arrLeft, arrRight);
}

function merge(arr1, arr2) {
    let arr = [];
    while (arr1.length && arr2.length) {
        if (arr1[0] < arr2[0]) {
            arr.push(arr1.shift());
        } else {
            arr.push(arr2.shift());
        }
    }
    return [...arr, ...arr1, ...arr2];

}

arr = [3, 5, 6, 2, 1, 7, 4];
console.log(mergeSort(arr));
```

### **Go**

```go
package main

import "fmt"

func merge(nums, temp []int, low, mid, high int) {
	for i, j, k := low, mid+1, low; k <= high; k++ {
		if j > high || nums[i] < nums[j] {
			temp[k] = nums[i]
			i++
		} else {
			temp[k] = nums[j]
			j++
		}
	}
	for i := low; i <= high; i++ {
		nums[i] = temp[i]
	}
}

func _mergeSort(nums, temp []int, low, high int) {
	if low >= high {
		return
	}
	mid := low + (high-low)/2
	_mergeSort(nums, temp, low, mid)
	_mergeSort(nums, temp, mid+1, high)
	merge(nums, temp, low, mid, high)
}

func mergeSort(nums []int) {
	n := len(nums)
	temp := make([]int, n)
	_mergeSort(nums, temp, 0, n-1)
}

func main() {
	nums := []int{1, 2, 7, 4, 5, 3}
	mergeSort(nums)
	fmt.Println(nums)
}
```


<!-- tabs:end -->

## 算法分析

空间复杂度 O(n)，时间复杂度 O(nlogn)。

对于规模为 n 的问题，一共要进行 log(n) 次的切分，每一层的合并复杂度都是 O(n)，所以整体时间复杂度为 O(nlogn)。

由于合并 n 个元素需要分配一个大小为 n 的额外数组，所以空间复杂度为 O(n)。

这是一种稳定的排序算法。
