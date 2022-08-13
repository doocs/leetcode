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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def merge(self, A: List[int], m: int, B: List[int], n: int) -> None:
        """
        Do not return anything, modify A in-place instead.
        """
        i, j = m - 1, n - 1
        for k in range(len(A) - 1, -1, -1):
            if j < 0 or (i >= 0 and A[i] >= B[j]):
                A[k] = A[i]
                i -= 1
            else:
                A[k] = B[j]
                j -= 1
```

### **Java**

```java
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1, j = n - 1;
        for (int k = A.length - 1; k >= 0; --k) {
            if (j < 0 || (i >= 0 && A[i] >= B[j])) {
                A[k] = A[i--];
            } else {
                A[k] = B[j--];
            }
        }
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} A
 * @param {number} m
 * @param {number[]} B
 * @param {number} n
 * @return {void} Do not return anything, modify A in-place instead.
 */
var merge = function (A, m, B, n) {
    let i = m - 1,
        j = n - 1;
    for (let k = A.length - 1; k >= 0; k--) {
        if (k == i) return;
        if (i < 0 || A[i] <= B[j]) {
            A[k] = B[j];
            j--;
        } else {
            A[k] = A[i];
            i--;
        }
    }
};
```

### **TypeScript**

```ts
/**
 Do not return anything, modify A in-place instead.
 */
function merge(A: number[], m: number, B: number[], n: number): void {
    for (let i = n + m - 1; i >= 0; i--) {
        const x = A[m - 1] ?? -Infinity;
        const y = B[n - 1] ?? -Infinity;
        if (x > y) {
            A[i] = x;
            m--;
        } else {
            A[i] = y;
            n--;
        }
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn merge(a: &mut Vec<i32>, m: i32, b: &mut Vec<i32>, n: i32) {
        let mut m = m as usize;
        let mut n = n as usize;
        for i in (0..n + m).rev() {
            let x = if m != 0 { a[m - 1] } else { i32::MIN };
            let y = if n != 0 { b[n - 1] } else { i32::MIN };
            if x > y {
                a[i] = x;
                m -= 1;
            } else {
                a[i] = y;
                n -= 1;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void merge(vector<int>& A, int m, vector<int>& B, int n) {
        int i = m - 1, j = n - 1;
        for (int k = A.size() - 1; k >= 0; --k) {
            if (j < 0 || (i >= 0 && A[i] >= B[j]))
                A[k] = A[i--];
            else
                A[k] = B[j--];
        }
    }
};
```

### **Go**

```go
func merge(A []int, m int, B []int, n int) {
	i, j := m-1, n-1
	for k := len(A) - 1; k >= 0; k-- {
		if j < 0 || (i >= 0 && A[i] >= B[j]) {
			A[k] = A[i]
			i--
		} else {
			A[k] = B[j]
			j--
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
