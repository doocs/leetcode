# 堆排序

**堆排序算法模板：**

```java
// h存储堆中的值，h[1]是堆顶，h[x]的左儿子是2x，右儿子是2x+1
int[] h = new int[N];

// 向下调整
void down(int u) {
    int t = u;
    if (u * 2 <= size && h[u * 2] < h[t]) {
        t = u * 2;
    }
    if (u * 2 + 1 <= size && h[u * 2 + 1] < h[t]) {
        t = u * 2 + 1;
    }
    if (t != u) {
        swap(t, u);
        down(t);
    }
}

// 向上调整
void up(int u) {
    while (u / 2 > 0 && h[u / 2] > h[u]) {
        swap(u / 2, u);
        u /= 2;
    }
}

// O(n) 建堆
for (int i = n / 2; i > 0; --i) {
    down(i);
}
```

## 题目描述

输入一个长度为 n 的整数数列，从小到大输出前 m 小的数。

**输入格式**

第一行包含整数 n 和 m。

第二行包含 n 个整数，表示整数数列

**输出格式**

共一行，包含 m 个整数，表示整数数列中前 m 小的数。

**数据范围**

-   1 ≤ m ≤ n ≤ 10^5
-   1 ≤ 数列中元素 ≤ 10^9

**输入样例：**

```
5 3
4 5 1 3 2
```

**输出样例：**

```
1 2 3
```

## 代码实现

<!-- tabs:start -->

### **Python3**

```python
n, m = list(map(int, input().split(" ")))
h = [0] + list(map(int, input().split(" ")))

size = n


def down(u):
    t = u
    if u * 2 <= size and h[u * 2] < h[t]:
        t = u * 2
    if u * 2 + 1 <= size and h[u * 2 + 1] < h[t]:
        t = u * 2 + 1
    if t != u:
        h[t], h[u] = h[u], h[t]
        down(t)


def up(u):
    while u // 2 > 0 and h[u // 2] > h[u]:
        h[u // 2], h[u] = h[u], h[u // 2]
        u //= 2


for i in range(n // 2, 0, -1):
    down(i)

res = []
for i in range(m):
    res.append(h[1])
    h[1] = h[size]
    size -= 1
    down(1)

print(' '.join(list(map(str, res))))
```

### **Java**

```java
import java.util.Scanner;

public class Main {
    private static int[] h = new int[100010];
    private static int size;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        for (int i = 1; i <= n; ++i) {
            h[i] = sc.nextInt();
        }
        size = n;
        for (int i = n / 2; i > 0; --i) {
            down(i);
        }
        while (m-- > 0) {
            System.out.print(h[1] + " ");
            h[1] = h[size--];
            down(1);
        }
    }

    public static void down(int u) {
        int t = u;
        if (u * 2 <= size && h[u * 2] < h[t]) {
            t = u * 2;
        }
        if (u * 2 + 1 <= size && h[u * 2 + 1] < h[t]) {
            t = u * 2 + 1;
        }
        if (t != u) {
            swap(t, u);
            down(t);
        }
    }

    public static void up(int u) {
        while (u / 2 > 0 && h[u / 2] > h[u]) {
            swap(u / 2, u);
            u /= 2;
        }
    }

    public static void swap(int i, int j) {
        int t = h[i];
        h[i] = h[j];
        h[j] = t;
    }
}
```

### **Rust**

```rust
use std::io;

fn heap_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    for i in (0..n / 2).rev() {
        sink(nums, i, n);
    }
    for i in (1..n).rev() {
        let temp = nums[0];
        nums[0] = nums[i];
        nums[i] = temp;
        sink(nums, 0, i);
    }
}

fn sink(nums: &mut Vec<i32>, mut i: usize, n: usize) {
    loop {
        let left = i * 2 + 1;
        let right = left + 1;
        let mut largest = i;
        if left < n && nums[left] > nums[largest] {
            largest = left;
        }
        if right < n && nums[right] > nums[largest] {
            largest = right;
        }
        if largest == i {
            break;
        }
        let temp = nums[i];
        nums[i] = nums[largest];
        nums[largest] = temp;
        i = largest;
    }
}

fn main() -> io::Result<()> {
    let mut s = String::new();
    io::stdin().read_line(&mut s)?;
    let s: Vec<usize> = s.split(' ').map(|s| s.trim().parse().unwrap()).collect();
    // let n = s[0];
    let m = s[1];

    let mut nums = String::new();
    io::stdin().read_line(&mut nums)?;
    let mut nums: Vec<i32> = nums.split(' ').map(|s| s.trim().parse().unwrap()).collect();

    heap_sort(&mut nums);
    for num in nums.iter().take(m) {
        print!("{} ", num);
    }

    Ok(())
}
```

### **Go**

```go
package main

import "fmt"

const N = 100010

var (
	size int
	h    []int
)

func up(u int) {
	for u/2 != 0 && h[u/2] > h[u] { //父节点比当前结点小
		h[u/2], h[u] = h[u], h[u/2] //交换
		u /= 2
	}
}
func down(u int) {
	t := u                            //t 最小值
	if u*2 <= size && h[2*u] < h[t] { //左孩子存在且小于t
		t = u * 2
	}
	if u*2+1 <= size && h[2*u+1] < h[t] { //右孩子存在且小于t
		t = 2*u + 1
	}
	if u != t {
		h[u], h[t] = h[t], h[u]
		down(t) //递归处理
	}
}
func main() {
	var n, m int
	h = make([]int, N)
	fmt.Scanf("%d%d", &n, &m)
	//创建一维数组1
	for i := 1; i <= n; i++ {
		fmt.Scanf("%d", &h[i])
	}
	size = n
	// 一维数组变为小根堆
	for i := n / 2; i != 0; i-- {
		down(i)
	}

	for ; m != 0; m-- {
		fmt.Printf("%d ", h[1])
		h[1] = h[size]
		size--
		down(1)
	}
}
```

<!-- tabs:end -->
