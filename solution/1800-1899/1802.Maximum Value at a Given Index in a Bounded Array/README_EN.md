---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1802.Maximum%20Value%20at%20a%20Given%20Index%20in%20a%20Bounded%20Array/README_EN.md
rating: 1929
source: Weekly Contest 233 Q3
tags:
    - Greedy
    - Math
    - Binary Search
---

<!-- problem:start -->

# [1802. Maximum Value at a Given Index in a Bounded Array](https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array)

[中文文档](/solution/1800-1899/1802.Maximum%20Value%20at%20a%20Given%20Index%20in%20a%20Bounded%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given three positive integers:&nbsp;<code>n</code>, <code>index</code>, and <code>maxSum</code>. You want to construct an array <code>nums</code> (<strong>0-indexed</strong>)<strong> </strong>that satisfies the following conditions:</p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>nums[i]</code> is a <strong>positive</strong> integer where <code>0 &lt;= i &lt; n</code>.</li>
	<li><code>abs(nums[i] - nums[i+1]) &lt;= 1</code> where <code>0 &lt;= i &lt; n-1</code>.</li>
	<li>The sum of all the elements of <code>nums</code> does not exceed <code>maxSum</code>.</li>
	<li><code>nums[index]</code> is <strong>maximized</strong>.</li>
</ul>

<p>Return <code>nums[index]</code><em> of the constructed array</em>.</p>

<p>Note that <code>abs(x)</code> equals <code>x</code> if <code>x &gt;= 0</code>, and <code>-x</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, index = 2,  maxSum = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> nums = [1,2,<u><strong>2</strong></u>,1] is one array that satisfies all the conditions.
There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6, index = 1,  maxSum = 10
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= maxSum &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= index &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

According to the problem description, if we determine the value of $nums[index]$ as $x$, we can find a minimum array sum. That is, the elements on the left side of $index$ in the array decrease from $x-1$ to $1$, and if there are remaining elements, the remaining elements are all $1$; similarly, the elements at $index$ and on the right side of the array decrease from $x$ to $1$, and if there are remaining elements, the remaining elements are all $1$.

In this way, we can calculate the sum of the array. If the sum is less than or equal to $maxSum$, then the current $x$ is valid. As $x$ increases, the sum of the array will also increase, so we can use the binary search method to find the maximum $x$ that meets the conditions.

To facilitate the calculation of the sum of the elements on the left and right sides of the array, we define a function $sum(x, cnt)$, which represents the sum of an array with $cnt$ elements and a maximum value of $x$. The function $sum(x, cnt)$ can be divided into two cases:

-   If $x \geq cnt$, then the sum of the array is $\frac{(x + x - cnt + 1) \times cnt}{2}$
-   If $x \lt cnt$, then the sum of the array is $\frac{(x + 1) \times x}{2} + cnt - x$

Next, define the left boundary of the binary search as $left = 1$, the right boundary as $right = maxSum$, and then binary search for the value $mid$ of $nums[index]$. If $sum(mid - 1, index) + sum(mid, n - index) \leq maxSum$, then the current $mid$ is valid, we can update $left$ to $mid$, otherwise we update $right$ to $mid - 1$.

Finally, return $left$ as the answer.

The time complexity is $O(\log M)$, where $M=maxSum$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValue(self, n: int, index: int, maxSum: int) -> int:
        def sum(x, cnt):
            return (
                (x + x - cnt + 1) * cnt // 2 if x >= cnt else (x + 1) * x // 2 + cnt - x
            )

        left, right = 1, maxSum
        while left < right:
            mid = (left + right + 1) >> 1
            if sum(mid - 1, index) + sum(mid, n - index) <= maxSum:
                left = mid
            else:
                right = mid - 1
        return left
```

#### Java

```java
class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private long sum(long x, int cnt) {
        return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxValue(int n, int index, int maxSum) {
        auto sum = [](long x, int cnt) {
            return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
        };
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

#### Go

```go
func maxValue(n int, index int, maxSum int) int {
	sum := func(x, cnt int) int {
		if x >= cnt {
			return (x + x - cnt + 1) * cnt / 2
		}
		return (x+1)*x/2 + cnt - x
	}
	return sort.Search(maxSum, func(x int) bool {
		x++
		return sum(x-1, index)+sum(x, n-index) > maxSum
	})
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
