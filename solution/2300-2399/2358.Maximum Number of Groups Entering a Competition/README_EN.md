---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2358.Maximum%20Number%20of%20Groups%20Entering%20a%20Competition/README_EN.md
rating: 1502
source: Weekly Contest 304 Q2
tags:
    - Greedy
    - Array
    - Math
    - Binary Search
---

<!-- problem:start -->

# [2358. Maximum Number of Groups Entering a Competition](https://leetcode.com/problems/maximum-number-of-groups-entering-a-competition)

[中文文档](/solution/2300-2399/2358.Maximum%20Number%20of%20Groups%20Entering%20a%20Competition/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer array <code>grades</code> which represents the grades of students in a university. You would like to enter <strong>all</strong> these students into a competition in <strong>ordered</strong> non-empty groups, such that the ordering meets the following conditions:</p>

<ul>
	<li>The sum of the grades of students in the <code>i<sup>th</sup></code> group is <strong>less than</strong> the sum of the grades of students in the <code>(i + 1)<sup>th</sup></code> group, for all groups (except the last).</li>
	<li>The total number of students in the <code>i<sup>th</sup></code> group is <strong>less than</strong> the total number of students in the <code>(i + 1)<sup>th</sup></code> group, for all groups (except the last).</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of groups that can be formed</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grades = [10,6,12,7,3,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The following is a possible way to form 3 groups of students:
- 1<sup>st</sup> group has the students with grades = [12]. Sum of grades: 12. Student count: 1
- 2<sup>nd</sup> group has the students with grades = [6,7]. Sum of grades: 6 + 7 = 13. Student count: 2
- 3<sup>rd</sup> group has the students with grades = [10,3,5]. Sum of grades: 10 + 3 + 5 = 18. Student count: 3
It can be shown that it is not possible to form more than 3 groups.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grades = [8,8]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can only form 1 group, since forming 2 groups would lead to an equal number of students in both groups.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grades.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grades[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Binary Search

Observing the conditions in the problem, the number of students in the $i$-th group must be less than that in the $(i+1)$-th group, and the total score of students in the $i$-th group must be less than that in the $(i+1)$-th group. We only need to sort the students by their scores in ascending order, and then assign $1$, $2$, ..., $k$ students to each group in order. If the last group does not have enough students for $k$, we can distribute these students to the previous last group.

Therefore, we need to find the largest $k$ such that $\frac{(1 + k) \times k}{2} \leq n$, where $n$ is the total number of students. We can use binary search to solve this.

We define the left boundary of binary search as $l = 1$ and the right boundary as $r = n$. Each time, the midpoint is $mid = \lfloor \frac{l + r + 1}{2} \rfloor$. If $(1 + mid) \times mid \gt 2 \times n$, it means $mid$ is too large, so we shrink the right boundary to $mid - 1$; otherwise, we increase the left boundary to $mid$.

Finally, we return $l$ as the answer.

The time complexity is $O(\log n)$ and the space complexity is $O(1)$, where $n$ is the total number of students.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumGroups(self, grades: List[int]) -> int:
        n = len(grades)
        return bisect_right(range(n + 1), n * 2, key=lambda x: x * x + x) - 1
```

#### Java

```java
class Solution {
    public int maximumGroups(int[] grades) {
        int n = grades.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (1L * mid * mid + mid > n * 2L) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumGroups(vector<int>& grades) {
        int n = grades.size();
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (1LL * mid * mid + mid > n * 2LL) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
};
```

#### Go

```go
func maximumGroups(grades []int) int {
	n := len(grades)
	return sort.Search(n, func(k int) bool {
		k++
		return k*k+k > n*2
	})
}
```

#### TypeScript

```ts
function maximumGroups(grades: number[]): number {
    const n = grades.length;
    let l = 1;
    let r = n;
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (mid * mid + mid > n * 2) {
            r = mid - 1;
        } else {
            l = mid;
        }
    }
    return l;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_groups(grades: Vec<i32>) -> i32 {
        let n = grades.len() as i64;
        let (mut l, mut r) = (0i64, n);
        while l < r {
            let mid = (l + r + 1) / 2;
            if mid * mid + mid > 2 * n {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        l as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
