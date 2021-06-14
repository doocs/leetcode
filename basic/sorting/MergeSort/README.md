# 归并排序

## 题目描述

给定你一个长度为 `n` 的整数数列。

请你使用归并排序对这个数列按照从小到大进行排序。

并将排好序的数列按顺序输出。

**输入格式**

输入共两行，第一行包含整数 n。

第二行包含 n 个整数（所有整数均在 1∼10^9 范围内），表示整个数列。

**输出格式**

输出共一行，包含 n 个整数，表示排好序的数列。

**数据范围**

1≤n≤100000

**输入样例：**

```
5
3 1 2 4 5
```

**输出样例：**

```
1 2 3 4 5
```

## 解法

归并排序的核心思想是分治，把一个复杂问题拆分成若干个子问题来求解。

归并排序的算法思想是：把数组从中间划分为两个子数组，一直递归地把子数组划分成更小的数组，直到子数组里面只有一个元素的时候开始排序。排序的方法就是按照大小顺序合并两个元素。接着依次按照递归的顺序返回，不断合并排好序的数组，直到把整个数组排好序。

归并排序模板：

```java
void mergeSort(int[] nums, int low, int high) {
    if (low >= high) {
        return;
    }
    int mid = (low + high) >>> 1;
    mergeSort(nums, low, mid);
    mergeSort(nums, mid + 1, high);
    int i = low, j = mid + 1, k = 0;
    while (i <= mid && j <= high) {
        if (nums[i] <= nums[j]) {
            tmp[k++] = nums[i++];
        } else {
            tmp[k++] = nums[j++];
        }
    }
    while (i <= mid) {
        tmp[k++] = nums[i++];
    }
    while (j <= high) {
        tmp[k++] = nums[j++];
    }
    for (i = low, j = 0; i <= high; ++i, ++j) {
        nums[i] = tmp[j];
    }
}
```

<!-- tabs:start -->

### **Python3**

```python
N = int(input())
nums = list(map(int, input().split()))


def merge_sort(nums, low, high):
    if low >= high:
        return
    mid = (low + high) >> 1
    merge_sort(nums, low, mid)
    merge_sort(nums, mid + 1, high)
    tmp = []
    i, j = low, mid + 1
    while i <= mid and j <= high:
        if nums[i] <= nums[j]:
            tmp.append(nums[i])
            i += 1
        else:
            tmp.append(nums[j])
            j += 1
    while i <= mid:
        tmp.append(nums[i])
        i += 1
    while j <= high:
        tmp.append(nums[j])
        j += 1
    
    j = 0
    for i in range(low, high + 1):
        nums[i] = tmp[j]
        j += 1


merge_sort(nums, 0, N - 1)
print(' '.join(list(map(str, nums))))
```

### **Java**

```java
import java.util.Scanner;

public class Main {
    private static int[] tmp = new int[100010];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = sc.nextInt();
        }
        mergeSort(nums, 0, n - 1);
        for (int i = 0; i < n; ++i) {
            System.out.printf("%d ", nums[i]);
        }
    }
    
    public static void mergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) >>> 1;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        int i = low, j = mid + 1, k = 0;
        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= high) {
            tmp[k++] = nums[j++];
        }
        for (i = low, j = 0; i <= high; ++i, ++j) {
            nums[i] = tmp[j];
        }
    }
}
```

### **JavaScript**

```js
var buf = '';

process.stdin.on('readable', function () {
    var chunk = process.stdin.read();
    if (chunk) buf += chunk.toString();
});

let getInputArgs = line => {
    return line.split(' ').filter(s => s !== '').map(x => parseInt(x));
}

function mergeSort(nums, low, high) {
    if (low >= high) {
        return;
    }
    
    const mid = (low + high) >> 1;
    mergeSort(nums, low, mid);
    mergeSort(nums, mid + 1, high);
    let i = low;
    let j = mid + 1;
    let tmp = [];
    while (i <= mid && j <= high) {
        if (nums[i] <= nums[j]) {
            tmp.push(nums[i++]);
        } else {
            tmp.push(nums[j++]);
        }
    }
    while (i <= mid) {
        tmp.push(nums[i++]);
    }
    while (j <= high) {
        tmp.push(nums[j++]);
    }
    for (i = low, j = 0; i <= high; ++i, ++j) {
        nums[i] = tmp[j];
    }
}



process.stdin.on('end', function () {
    buf.split('\n').forEach(function (line, lineIdx) {
        if (lineIdx % 2 === 1) {
            nums = getInputArgs(line);
            mergeSort(nums, 0, nums.length - 1);
            console.log(nums.join(' '));
        }

    });
});
```

### **Go**

```go
package main

import "fmt"

func mergeSort(nums []int, low, high int) {
	if low >= high {
		return
	}
	mid := (low + high) >> 1
	mergeSort(nums, low, mid)
	mergeSort(nums, mid+1, high)
	i, j := low, mid+1
	tmp := make([]int, 0)
	for i <= mid && j <= high {
		if nums[i] <= nums[j] {
			tmp = append(tmp, nums[i])
			i++
		} else {
			tmp = append(tmp, nums[j])
			j++
		}
	}
	for i <= mid {
		tmp = append(tmp, nums[i])
		i++
	}
	for j <= high {
		tmp = append(tmp, nums[j])
		j++
	}
	for i, j = low, 0; i <= high; i, j = i+1, j+1 {
		nums[i] = tmp[j]
	}
}

func main() {
	var n int
	fmt.Scanf("%d\n", &n)
	nums := make([]int, n)
	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &nums[i])
	}

	mergeSort(nums, 0, n-1)

	for _, v := range nums {
		fmt.Printf("%d ", v)
	}
}
```


<!-- tabs:end -->
