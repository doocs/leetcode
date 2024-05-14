---
comment: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/10.01.Sorted%20Merge/README_EN.md
---

# [10.01. Sorted Merge](https://leetcode.cn/problems/sorted-merge-lcci)

[中文文档](/lcci/10.01.Sorted%20Merge/README.md)

## Description

<p>You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a method to merge B into A in sorted order.</p>

<p>Initially the number of elements in A and B are&nbsp;<em>m</em>&nbsp;and&nbsp;<em>n</em> respectively.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong>

A = [1,2,3,0,0,0], m = 3

B = [2,5,6],       n = 3



<strong>Output:</strong>&nbsp;[1,2,2,3,5,6]</pre>

## Solutions

### Solution 1: Two Pointers

We use two pointers $i$ and $j$ to point to the end of arrays $A$ and $B$ respectively, and a pointer $k$ to point to the end of array $A$. Then we traverse arrays $A$ and $B$ from back to front, each time putting the larger element into $A[k]$, then moving pointer $k$ and the pointer of the array with the larger element forward by one position.

The time complexity is $O(m + n)$, and the space complexity is $O(1)$.

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
