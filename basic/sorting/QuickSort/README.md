# 快速排序

快速排序也采用了分治的思想：把原始的数组筛选成较小和较大的两个子数组，然后递归地排序两个子数组。

**快速排序算法模板：**

```java
void quickSort(int[] nums, int left, int right) {
    if (left >= right) {
        return;
    }
    int i = left - 1, j = right + 1;
    int x = nums[left];
    while (i < j) {
        while (nums[++i] < x);
        while (nums[--j] > x);
        if (i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
    quickSort(nums, left, j);
    quickSort(nums, j + 1, right);
}
```

## 题目描述

给定你一个长度为 `n` 的整数数列。

请你使用快速排序对这个数列按照从小到大进行排序。

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


def quick_sort(nums, left, right):
    if left >= right:
        return
    i, j = left - 1, right + 1
    x = nums[(left + right) >> 1]
    while i < j:
        while 1:
            i += 1
            if nums[i] >= x:
                break
        while 1:
            j -= 1
            if nums[j] <= x:
                break
        if i < j:
            nums[i], nums[j] = nums[j], nums[i]
    quick_sort(nums, left, j)
    quick_sort(nums, j + 1, right)


quick_sort(nums, 0, N - 1)
print(' '.join(list(map(str, nums))))
```

### **Java**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = sc.nextInt();
        }
        quickSort(nums, 0, n - 1);
        for (int i = 0; i < n; ++i) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left - 1, j = right + 1;
        int x = nums[(left + right) >> 1];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        quickSort(nums, left, j);
        quickSort(nums, j + 1, right);
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

function quickSort(nums, left, right) {
    if (left >= right) {
        return;
    }

    let i = left - 1;
    let j = right + 1;
    let x = nums[(left + right) >> 1];
    while (i < j) {
        while (nums[++i] < x);
        while (nums[--j] > x);
        if (i < j) {
            const t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
    quickSort(nums, left, j);
    quickSort(nums, j + 1, right);
}

process.stdin.on('end', function () {
    buf.split('\n').forEach(function (line, lineIdx) {
        if (lineIdx % 2 === 1) {
            nums = getInputArgs(line);
            quickSort(nums, 0, nums.length - 1);
            console.log(nums.join(' '));
        }
    });
});
```

### **Go**

```go
package main

import "fmt"

func quickSort(nums []int, left, right int) {
	if left >= right {
		return
	}
	i, j := left-1, right+1
	x := nums[(left+right)>>1]
	for i < j {
		for {
			i++
			if nums[i] >= x {
				break
			}
		}
		for {
			j--
			if nums[j] <= x {
				break
			}
		}
		if i < j {
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
	quickSort(nums, left, j)
	quickSort(nums, j+1, right)
}

func main() {
	var n int
	fmt.Scanf("%d\n", &n)
	nums := make([]int, n)
	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &nums[i])
	}

	quickSort(nums, 0, n-1)

	for _, v := range nums {
		fmt.Printf("%d ", v)
	}
}
```

### **Rust**

```rust
use rand::Rng; // 0.7.2
use std::io;

fn quick_sort(nums: &mut Vec<i32>, left: usize, right: usize) {
    if left >= right {
        return;
    }

    let random_index = rand::thread_rng().gen_range(left, right + 1);
    let temp = nums[random_index];
    nums[random_index] = nums[left];
    nums[left] = temp;

    let pivot = nums[left];
    let mut i = left;
    let mut j = right;
    while i < j {
        while i < j && nums[j] >= pivot {
            j -= 1;
        }
        nums[i] = nums[j];
        while i < j && nums[i] < pivot {
            i += 1;
        }
        nums[j] = nums[i];
    }
    nums[i] = pivot;

    quick_sort(nums, left, i);
    quick_sort(nums, i + 1, right);
}

fn main() -> io::Result<()> {
    let mut n = String::new();
    io::stdin().read_line(&mut n)?;
    let n = n.trim().parse::<usize>().unwrap();

    let mut nums = String::new();
    io::stdin().read_line(&mut nums)?;
    let mut nums: Vec<i32> = nums.split(' ').map(|s| s.trim().parse().unwrap()).collect();

    quick_sort(&mut nums, 0, n - 1);
    for num in nums.iter() {
        print!("{} ", num);
    }

    Ok(())
}
```

### **C++**

```cpp
#include <iostream>

using namespace std;

const int N = 1e6 + 10;

int n;
int nums[N];

void quick_sort(int nums[], int left, int right) {
    if (left >= right) return;
    int i = left - 1, j = right + 1;
    int x = nums[left + right >> 1];
    while (i < j) {
        while (nums[++i] < x)
            ;
        while (nums[--j] > x)
            ;
        if (i < j) swap(nums[i], nums[j]);
    }
    quick_sort(nums, left, j);
    quick_sort(nums, j + 1, right);
}

int main() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) scanf("%d", &nums[i]);
    quick_sort(nums, 0, n - 1);
    for (int i = 0; i < n; ++i) printf("%d ", nums[i]);
}
```

<!-- tabs:end -->
