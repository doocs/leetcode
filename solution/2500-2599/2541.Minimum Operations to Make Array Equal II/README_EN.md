---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2541.Minimum%20Operations%20to%20Make%20Array%20Equal%20II/README_EN.md
rating: 1619
source: Biweekly Contest 96 Q2
tags:
    - Greedy
    - Array
    - Math
---

<!-- problem:start -->

# [2541. Minimum Operations to Make Array Equal II](https://leetcode.com/problems/minimum-operations-to-make-array-equal-ii)

[中文文档](/solution/2500-2599/2541.Minimum%20Operations%20to%20Make%20Array%20Equal%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> of equal length <code>n</code> and an integer <code>k</code>. You can perform the following operation on <code>nums1</code>:</p>

<ul>
	<li>Choose two indexes <code>i</code> and <code>j</code> and increment <code>nums1[i]</code> by <code>k</code> and decrement <code>nums1[j]</code> by <code>k</code>. In other words, <code>nums1[i] = nums1[i] + k</code> and <code>nums1[j] = nums1[j] - k</code>.</li>
</ul>

<p><code>nums1</code> is said to be <strong>equal</strong> to <code>nums2</code> if for all indices <code>i</code> such that <code>0 &lt;= i &lt; n</code>, <code>nums1[i] == nums2[i]</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of operations required to make </em><code>nums1</code><em> equal to </em><code>nums2</code>. If it is impossible to make them equal, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> In 2 operations, we can transform nums1 to nums2.
1<sup>st</sup> operation: i = 2, j = 0. After applying the operation, nums1 = [1,3,4,4].
2<sup>nd</sup> operation: i = 2, j = 3. After applying the operation, nums1 = [1,3,7,1].
One can prove that it is impossible to make arrays equal in fewer operations.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be proved that it is impossible to make the two arrays equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We use two variables $a$ and $b$ to record the number of times elements in $\textit{nums1}$ are increased by $k$ and decreased by $k$, respectively.

We iterate over both arrays. If the two elements at the current position are equal, we continue. Otherwise, if $k$ equals $0$ or the difference between the two elements is not divisible by $k$, we return $-1$. Otherwise, we compute $t = (x - y) / k$. If $t < 0$, we add $-t$ to $a$; otherwise, we add $t$ to $b$.

Finally, if $a$ equals $b$, we return $a$; otherwise, we return $-1$.

The time complexity is $O(n)$, where $n$ is the length of the two arrays. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int], k: int) -> int:
        a = b = 0
        for x, y in zip(nums1, nums2):
            if x == y:
                continue
            if k == 0 or (x - y) % k:
                return -1
            t = (x - y) // k
            if t < 0:
                a += -t
            else:
                b += t
        return a if a == b else -1
```

#### Java

```java
class Solution {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        long a = 0, b = 0;
        for (int i = 0; i < nums1.length; ++i) {
            int x = nums1[i], y = nums2[i];
            if (x == y) {
                continue;
            }
            if (k == 0 || (x - y) % k != 0) {
                return -1;
            }
            int t = (x - y) / k;
            if (t < 0) {
                a += -t;
            } else {
                b += t;
            }
        }
        return a == b ? a : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2, int k) {
        long long a = 0, b = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            int x = nums1[i], y = nums2[i];
            if (x == y) {
                continue;
            }
            if (k == 0 || (x - y) % k != 0) {
                return -1;
            }
            int t = (x - y) / k;
            if (t < 0) {
                a += -t;
            } else {
                b += t;
            }
        }
        return a == b ? a : -1;
    }
};
```

#### Go

```go
func minOperations(nums1 []int, nums2 []int, k int) int64 {
	var a, b int64
	for i, x := range nums1 {
		y := nums2[i]
		if x == y {
			continue
		}
		if k == 0 || (x-y)%k != 0 {
			return -1
		}
		t := (x - y) / k
		if t < 0 {
			a += int64(-t)
		} else {
			b += int64(t)
		}
	}
	if a == b {
		return a
	}
	return -1
}
```

#### TypeScript

```ts
function minOperations(nums1: number[], nums2: number[], k: number): number {
    let [a, b] = [0, 0];
    for (let i = 0; i < nums1.length; ++i) {
        const [x, y] = [nums1[i], nums2[i]];
        if (x === y) {
            continue;
        }
        if (k === 0 || (x - y) % k !== 0) {
            return -1;
        }
        const t = (x - y) / k;
        if (t < 0) {
            a += -t;
        } else {
            b += t;
        }
    }
    return a === b ? a : -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_operations(nums1: Vec<i32>, nums2: Vec<i32>, k: i32) -> i64 {
        let mut a: i64 = 0;
        let mut b: i64 = 0;
        for (&x, &y) in nums1.iter().zip(nums2.iter()) {
            if x == y {
                continue;
            }
            if k == 0 || (x - y) % k != 0 {
                return -1;
            }
            let t = (x - y) / k;
            if t < 0 {
                a += (-t) as i64;
            } else {
                b += t as i64;
            }
        }
        if a == b {
            a
        } else {
            -1
        }
    }
}
```

#### C

```c
long long minOperations(int* nums1, int nums1Size, int* nums2, int nums2Size, int k) {
    long long a = 0, b = 0;
    for (int i = 0; i < nums1Size; ++i) {
        int x = nums1[i], y = nums2[i];
        if (x == y) {
            continue;
        }
        if (k == 0 || (x - y) % k != 0) {
            return -1;
        }
        int t = (x - y) / k;
        if (t < 0) {
            a += -t;
        } else {
            b += t;
        }
    }
    return a == b ? a : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
