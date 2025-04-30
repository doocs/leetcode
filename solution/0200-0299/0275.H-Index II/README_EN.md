---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0275.H-Index%20II/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [275. H-Index II](https://leetcode.com/problems/h-index-ii)

[中文文档](/solution/0200-0299/0275.H-Index%20II/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>citations</code> where <code>citations[i]</code> is the number of citations a researcher received for their <code>i<sup>th</sup></code> paper and <code>citations</code> is sorted in <strong>non-descending order</strong>, return <em>the researcher&#39;s h-index</em>.</p>

<p>According to the <a href="https://en.wikipedia.org/wiki/H-index" target="_blank">definition of h-index on Wikipedia</a>: The h-index is defined as the maximum value of <code>h</code> such that the given researcher has published at least <code>h</code> papers that have each been cited at least <code>h</code> times.</p>

<p>You must write an algorithm that runs in logarithmic time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> citations = [0,1,3,5,6]
<strong>Output:</strong> 3
<strong>Explanation:</strong> [0,1,3,5,6] means the researcher has 5 papers in total and each of them had received 0, 1, 3, 5, 6 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> citations = [1,2,100]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == citations.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= citations[i] &lt;= 1000</code></li>
	<li><code>citations</code> is sorted in <strong>ascending order</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that if there are at least $x$ papers with citation counts greater than or equal to $x$, then for any $y \lt x$, its citation count must also be greater than or equal to $y$. This exhibits monotonicity.

Therefore, we use binary search to enumerate $h$ and obtain the maximum $h$ that satisfies the condition. Since we need to satisfy that $h$ papers are cited at least $h$ times, we have $citations[n - mid] \ge mid$.

The time complexity is $O(\log n)$, where $n$ is the length of the array $citations$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        left, right = 0, n
        while left < right:
            mid = (left + right + 1) >> 1
            if citations[n - mid] >= mid:
                left = mid
            else:
                right = mid - 1
        return left
```

#### Java

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (citations[mid] >= n - mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int hIndex(vector<int>& citations) {
        int n = citations.size();
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (citations[n - mid] >= mid)
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }
};
```

#### Go

```go
func hIndex(citations []int) int {
	n := len(citations)
	left, right := 0, n
	for left < right {
		mid := (left + right + 1) >> 1
		if citations[n-mid] >= mid {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

#### TypeScript

```ts
function hIndex(citations: number[]): number {
    const n = citations.length;
    let left = 0,
        right = n;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (citations[n - mid] >= mid) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

#### Rust

```rust
impl Solution {
    pub fn h_index(citations: Vec<i32>) -> i32 {
        let n = citations.len();
        let (mut left, mut right) = (0, n);
        while left < right {
            let mid = ((left + right + 1) >> 1) as usize;
            if citations[n - mid] >= (mid as i32) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        left as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int HIndex(int[] citations) {
        int n = citations.Length;
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (citations[n - mid] >= mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
