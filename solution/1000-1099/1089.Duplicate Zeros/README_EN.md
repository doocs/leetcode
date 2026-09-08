---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1089.Duplicate%20Zeros/README_EN.md
rating: 1262
source: Weekly Contest 141 Q1
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [1089. Duplicate Zeros](https://leetcode.com/problems/duplicate-zeros)

[中文文档](/solution/1000-1099/1089.Duplicate%20Zeros/README.md)

## Description

<!-- description:start -->

<p>Given a fixed-length integer array <code>arr</code>, duplicate each occurrence of zero, shifting the remaining elements to the right.</p>

<p><strong>Note</strong> that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,0,2,3,0,4,5,0]
<strong>Output:</strong> [1,0,0,2,3,0,0,4]
<strong>Explanation:</strong> After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3]
<strong>Output:</strong> [1,2,3]
<strong>Explanation:</strong> After calling your function, the input array is modified to: [1,2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

Scan from left to right to see how far the original array can go after zeros are duplicated. Pointer $i$ is the last source index that still fits, and $k$ is the virtual length after duplication: add $1$ for a nonzero value and $2$ for a zero. Stop when $k \ge n$.

Let $j = n - 1$ be the write index. If the last kept value is a zero that would overflow ($k = n + 1$), write that single zero at $arr[j]$ and decrement both $i$ and $j$.

Then fill from right to left. Copy $arr[i]$ once into $arr[j]$ if it is nonzero, or twice if it is zero.

The time complexity is $O(n)$ and the space complexity is $O(1)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def duplicateZeros(self, arr: List[int]) -> None:
        """
        Do not return anything, modify arr in-place instead.
        """
        n = len(arr)
        i, k = -1, 0
        while k < n:
            i += 1
            k += 1 if arr[i] else 2
        j = n - 1
        if k == n + 1:
            arr[j] = 0
            i, j = i - 1, j - 1
        while ~j:
            if arr[i] == 0:
                arr[j] = arr[j - 1] = arr[i]
                j -= 1
            else:
                arr[j] = arr[i]
            i, j = i - 1, j - 1
```

#### Java

```java
class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int i = -1, k = 0;
        while (k < n) {
            ++i;
            k += arr[i] > 0 ? 1 : 2;
        }
        int j = n - 1;
        if (k == n + 1) {
            arr[j--] = 0;
            --i;
        }
        while (j >= 0) {
            arr[j] = arr[i];
            if (arr[i] == 0) {
                arr[--j] = arr[i];
            }
            --i;
            --j;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void duplicateZeros(vector<int>& arr) {
        int n = arr.size();
        int i = -1, k = 0;
        while (k < n) {
            ++i;
            k += arr[i] ? 1 : 2;
        }
        int j = n - 1;
        if (k == n + 1) {
            arr[j--] = 0;
            --i;
        }
        while (~j) {
            arr[j] = arr[i];
            if (arr[i] == 0) arr[--j] = arr[i];
            --i;
            --j;
        }
    }
};
```

#### Go

```go
func duplicateZeros(arr []int) {
	n := len(arr)
	i, k := -1, 0
	for k < n {
		i, k = i+1, k+1
		if arr[i] == 0 {
			k++
		}
	}
	j := n - 1
	if k == n+1 {
		arr[j] = 0
		i, j = i-1, j-1
	}
	for j >= 0 {
		arr[j] = arr[i]
		if arr[i] == 0 {
			j--
			arr[j] = arr[i]
		}
		i, j = i-1, j-1
	}
}
```

#### Rust

```rust
impl Solution {
    pub fn duplicate_zeros(arr: &mut Vec<i32>) {
        let n = arr.len();
        let mut i = 0;
        let mut j = 0;
        while j < n {
            if arr[i] == 0 {
                j += 1;
            }
            j += 1;
            i += 1;
        }
        while i > 0 {
            if arr[i - 1] == 0 {
                if j <= n {
                    arr[j - 1] = arr[i - 1];
                }
                j -= 1;
            }
            arr[j - 1] = arr[i - 1];
            i -= 1;
            j -= 1;
        }
    }
}
```

#### C

```c
void duplicateZeros(int* arr, int arrSize) {
    int i = 0;
    int j = 0;
    while (j < arrSize) {
        if (arr[i] == 0) {
            j++;
        }
        i++;
        j++;
    }
    i--;
    j--;
    while (i >= 0) {
        if (arr[i] == 0) {
            if (j < arrSize) {
                arr[j] = arr[i];
            }
            j--;
        }
        arr[j] = arr[i];
        i--;
        j--;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
