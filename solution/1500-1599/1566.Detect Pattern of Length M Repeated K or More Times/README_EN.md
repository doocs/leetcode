---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1566.Detect%20Pattern%20of%20Length%20M%20Repeated%20K%20or%20More%20Times/README_EN.md
rating: 1486
source: Weekly Contest 204 Q1
tags:
    - Array
    - Enumeration
---

<!-- problem:start -->

# [1566. Detect Pattern of Length M Repeated K or More Times](https://leetcode.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times)

[中文文档](/solution/1500-1599/1566.Detect%20Pattern%20of%20Length%20M%20Repeated%20K%20or%20More%20Times/README.md)

## Description

<!-- description:start -->

<p>Given an array of positive integers <code>arr</code>, find a pattern of length <code>m</code> that is repeated <code>k</code> or more times.</p>

<p>A <strong>pattern</strong> is a subarray (consecutive sub-sequence) that consists of one or more values, repeated multiple times <strong>consecutively </strong>without overlapping. A pattern is defined by its length and the number of repetitions.</p>

<p>Return <code>true</code> <em>if there exists a pattern of length</em> <code>m</code> <em>that is repeated</em> <code>k</code> <em>or more times, otherwise return</em> <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,4,4,4,4], m = 1, k = 3
<strong>Output:</strong> true
<strong>Explanation: </strong>The pattern <strong>(4)</strong> of length 1 is repeated 4 consecutive times. Notice that pattern can be repeated k or more times but not less.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
<strong>Output:</strong> true
<strong>Explanation: </strong>The pattern <strong>(1,2)</strong> of length 2 is repeated 2 consecutive times. Another valid pattern <strong>(2,1) is</strong> also repeated 2 times.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,1,2,1,3], m = 2, k = 3
<strong>Output:</strong> false
<strong>Explanation: </strong>The pattern (1,2) is of length 2 but is repeated only 2 times. There is no pattern of length 2 that is repeated 3 or more times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>2 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Traversal

First, if the length of the array is less than $m \times k$, then there is definitely no pattern of length $m$ that repeats at least $k$ times, so we directly return $\textit{false}$.

Next, we define a variable $\textit{cnt}$ to record the current count of consecutive repetitions. If there are $(k - 1) \times m$ consecutive elements $a_i$ in the array such that $a_i = a_{i - m}$, then we have found a pattern of length $m$ that repeats at least $k$ times, and we return $\textit{true}$. Otherwise, we reset $\textit{cnt}$ to $0$ and continue traversing the array.

Finally, if we finish traversing the array without finding a pattern that meets the conditions, we return $\textit{false}$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def containsPattern(self, arr: List[int], m: int, k: int) -> bool:
        if len(arr) < m * k:
            return False
        cnt, target = 0, (k - 1) * m
        for i in range(m, len(arr)):
            if arr[i] == arr[i - m]:
                cnt += 1
                if cnt == target:
                    return True
            else:
                cnt = 0
        return False
```

#### Java

```java
class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {
        if (arr.length < m * k) {
            return false;
        }
        int cnt = 0, target = (k - 1) * m;
        for (int i = m; i < arr.length; ++i) {
            if (arr[i] == arr[i - m]) {
                if (++cnt == target) {
                    return true;
                }
            } else {
                cnt = 0;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool containsPattern(vector<int>& arr, int m, int k) {
        if (arr.size() < m * k) {
            return false;
        }
        int cnt = 0, target = (k - 1) * m;
        for (int i = m; i < arr.size(); ++i) {
            if (arr[i] == arr[i - m]) {
                if (++cnt == target) {
                    return true;
                }
            } else {
                cnt = 0;
            }
        }
        return false;
    }
};
```

#### Go

```go
func containsPattern(arr []int, m int, k int) bool {
	cnt, target := 0, (k-1)*m
	for i := m; i < len(arr); i++ {
		if arr[i] == arr[i-m] {
			cnt++
			if cnt == target {
				return true
			}
		} else {
			cnt = 0
		}
	}
	return false
}
```

#### TypeScript

```ts
function containsPattern(arr: number[], m: number, k: number): boolean {
    if (arr.length < m * k) {
        return false;
    }
    const target = (k - 1) * m;
    let cnt = 0;
    for (let i = m; i < arr.length; ++i) {
        if (arr[i] === arr[i - m]) {
            if (++cnt === target) {
                return true;
            }
        } else {
            cnt = 0;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
