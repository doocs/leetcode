# 归并排序

归并排序的核心思想是分治，把一个复杂问题拆分成若干个子问题来求解。

归并排序的算法思想是：把数组从中间划分为两个子数组，一直递归地把子数组划分成更小的数组，直到子数组里面只有一个元素的时候开始排序。排序的方法就是按照大小顺序合并两个元素。接着依次按照递归的顺序返回，不断合并排好序的数组，直到把整个数组排好序。

**归并排序算法模板：**

```java
void mergeSort(int[] nums, int left, int right) {
    if (left >= right) {
        return;
    }
    int mid = (left + right) >>> 1;
    mergeSort(nums, left, mid);
    mergeSort(nums, mid + 1, right);
    int i = left, j = mid + 1, k = 0;
    while (i <= mid && j <= right) {
        if (nums[i] <= nums[j]) {
            tmp[k++] = nums[i++];
        } else {
            tmp[k++] = nums[j++];
        }
    }
    while (i <= mid) {
        tmp[k++] = nums[i++];
    }
    while (j <= right) {
        tmp[k++] = nums[j++];
    }
    for (i = left, j = 0; i <= right; ++i, ++j) {
        nums[i] = tmp[j];
    }
}
```

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

## 代码实现

<!-- tabs:start -->

### **Python3**

```python
N = int(input())
nums = list(map(int, input().split()))


def merge_sort(nums, left, right):
    if left >= right:
        return
    mid = (left + right) >> 1
    merge_sort(nums, left, mid)
    merge_sort(nums, mid + 1, right)
    tmp = []
    i, j = left, mid + 1
    while i <= mid and j <= right:
        if nums[i] <= nums[j]:
            tmp.append(nums[i])
            i += 1
        else:
            tmp.append(nums[j])
            j += 1
    while i <= mid:
        tmp.append(nums[i])
        i += 1
    while j <= right:
        tmp.append(nums[j])
        j += 1

    j = 0
    for i in range(left, right + 1):
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

    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >>> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        for (i = left, j = 0; i <= right; ++i, ++j) {
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
    return line
        .split(' ')
        .filter(s => s !== '')
        .map(x => parseInt(x));
};

function mergeSort(nums, left, right) {
    if (left >= right) {
        return;
    }

    const mid = (left + right) >> 1;
    mergeSort(nums, left, mid);
    mergeSort(nums, mid + 1, right);
    let i = left;
    let j = mid + 1;
    let tmp = [];
    while (i <= mid && j <= right) {
        if (nums[i] <= nums[j]) {
            tmp.push(nums[i++]);
        } else {
            tmp.push(nums[j++]);
        }
    }
    while (i <= mid) {
        tmp.push(nums[i++]);
    }
    while (j <= right) {
        tmp.push(nums[j++]);
    }
    for (i = left, j = 0; i <= right; ++i, ++j) {
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

func mergeSort(nums []int, left, right int) {
	if left >= right {
		return
	}
	mid := (left + right) >> 1
	mergeSort(nums, left, mid)
	mergeSort(nums, mid+1, right)
	i, j := left, mid+1
	tmp := make([]int, 0)
	for i <= mid && j <= right {
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
	for j <= right {
		tmp = append(tmp, nums[j])
		j++
	}
	for i, j = left, 0; i <= right; i, j = i+1, j+1 {
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

### **C++**

```cpp
#include <iostream>

using namespace std;

const int N = 1e6 + 10;

int n;
int nums[N];
int tmp[N];

void merge_sort(int nums[], int left, int right) {
    if (left >= right) return;
    int mid = (left + right) >> 1;
    merge_sort(nums, left, mid);
    merge_sort(nums, mid + 1, right);
    int i = left, j = mid + 1, k = 0;
    while (i <= mid && j <= right) {
        if (nums[i] <= nums[j])
            tmp[k++] = nums[i++];
        else
            tmp[k++] = nums[j++];
    }
    while (i <= mid) tmp[k++] = nums[i++];
    while (j <= right) tmp[k++] = nums[j++];
    for (i = left, j = 0; i <= right; ++i, ++j) nums[i] = tmp[j];
}

int main() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) scanf("%d", &nums[i]);
    merge_sort(nums, 0, n - 1);
    for (int i = 0; i < n; ++i) printf("%d ", nums[i]);
}
```

### **Rust**

```rust
use std::io;

fn merge_sort(nums: &mut Vec<i32>, left: usize, right: usize) {
    if left >= right {
        return;
    }

    let mid = left + (right - left) / 2;
    merge_sort(nums, left, mid);
    merge_sort(nums, mid + 1, right);

    let mut temp = Vec::new();
    let mut i = left;
    let mut j = mid + 1;

    while i <= mid && j <= right {
        if nums[i] < nums[j] {
            temp.push(nums[i]);
            i += 1;
        } else {
            temp.push(nums[j]);
            j += 1;
        }
    }
    while i <= mid {
        temp.push(nums[i]);
        i += 1;
    }
    while j <= right {
        temp.push(nums[j]);
        j += 1;
    }

    for i in left..=right {
        nums[i] = temp[i - left];
    }
}

fn main() -> io::Result<()> {
    let mut n = String::new();
    io::stdin().read_line(&mut n)?;
    let n = n.trim().parse::<usize>().unwrap();

    let mut nums = String::new();
    io::stdin().read_line(&mut nums)?;
    let mut nums: Vec<i32> = nums.split(' ').map(|s| s.trim().parse().unwrap()).collect();

    merge_sort(&mut nums, 0, n - 1);
    for num in nums.iter() {
        print!("{} ", num);
    }

    Ok(())
}
```

<!-- tabs:end -->
