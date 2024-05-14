---
comment: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/10.01.Sorted%20Merge/README.md
---

# [面试题 10.01. 合并排序的数组](https://leetcode.cn/problems/sorted-merge-lcci)

[English Version](/lcci/10.01.Sorted%20Merge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。</p>

<p>初始化&nbsp;A 和 B 的元素数量分别为&nbsp;<em>m</em> 和 <em>n</em>。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>
A = [1,2,3,0,0,0], m = 3
B = [2,5,6],       n = 3

<strong>输出:</strong>&nbsp;[1,2,2,3,5,6]</pre>

<p><strong>说明:</strong></p>

<ul>
	<li><code>A.length == n + m</code></li>
</ul>

## 解法

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 分别指向数组 $A$ 和 $B$ 的末尾，用一个指针 $k$ 指向数组 $A$ 的末尾。然后从后往前遍历数组 $A$ 和 $B$，每次将较大的元素放到 $A[k]$，然后指针 $k$ 和较大元素所在的数组的指针向前移动一位。

时间复杂度 $O(m + n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def merge(self, A: List[int], m: int, B: List[int], n: int) -> None:
        i, j = m - 1, n - 1
        for k in reversed(range(m + n)):
            if j < 0 or i >= 0 and A[i] > B[j]:
                A[k] = A[i]
                i -= 1
            else:
                A[k] = B[j]
                j -= 1
```

```java
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1, j = n - 1;
        for (int k = A.length - 1; k >= 0; --k) {
            if (j < 0 || (i >= 0 && A[i] > B[j])) {
                A[k] = A[i--];
            } else {
                A[k] = B[j--];
            }
        }
    }
}
```

```cpp
class Solution {
public:
    void merge(vector<int>& A, int m, vector<int>& B, int n) {
        int i = m - 1, j = n - 1;
        for (int k = A.size() - 1; ~k; --k) {
            if (j < 0 || (i >= 0 && A[i] > B[j])) {
                A[k] = A[i--];
            } else {
                A[k] = B[j--];
            }
        }
    }
};
```

```go
func merge(A []int, m int, B []int, n int) {
	i, j := m-1, n-1
	for k := len(A) - 1; k >= 0; k-- {
		if j < 0 || (i >= 0 && A[i] > B[j]) {
			A[k] = A[i]
			i--
		} else {
			A[k] = B[j]
			j--
		}
	}
}
```

```ts
/**
 Do not return anything, modify A in-place instead.
 */
function merge(A: number[], m: number, B: number[], n: number): void {
    let [i, j] = [m - 1, n - 1];
    for (let k = A.length - 1; ~k; --k) {
        if (j < 0 || (i >= 0 && A[i] > B[j])) {
            A[k] = A[i--];
        } else {
            A[k] = B[j--];
        }
    }
}
```

```rust
impl Solution {
    pub fn merge(a: &mut Vec<i32>, m: i32, b: &mut Vec<i32>, n: i32) {
        let (mut i, mut j) = (m - 1, n - 1);
        for k in (0..m + n).rev() {
            if j < 0 || (i >= 0 && a[i as usize] > b[j as usize]) {
                a[k as usize] = a[i as usize];
                i -= 1;
            } else {
                a[k as usize] = b[j as usize];
                j -= 1;
            }
        }
    }
}
```

```js
/**
 * @param {number[]} A
 * @param {number} m
 * @param {number[]} B
 * @param {number} n
 * @return {void} Do not return anything, modify A in-place instead.
 */
var merge = function (A, m, B, n) {
    let [i, j] = [m - 1, n - 1];
    for (let k = A.length - 1; ~k; --k) {
        if (j < 0 || (i >= 0 && A[i] > B[j])) {
            A[k] = A[i--];
        } else {
            A[k] = B[j--];
        }
    }
};
```

```swift
class Solution {
    func merge(_ A: inout [Int], _ m: Int, _ B: [Int], _ n: Int) {
        var i = m - 1, j = n - 1
        for k in stride(from: m + n - 1, through: 0, by: -1) {
            if j < 0 || (i >= 0 && A[i] > B[j]) {
                A[k] = A[i]
                i -= 1
            } else {
                A[k] = B[j]
                j -= 1
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
