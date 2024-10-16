---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3301.Maximize%20the%20Total%20Height%20of%20Unique%20Towers/README_EN.md
rating: 1448
source: Biweekly Contest 140 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3301. Maximize the Total Height of Unique Towers](https://leetcode.com/problems/maximize-the-total-height-of-unique-towers)

[中文文档](/solution/3300-3399/3301.Maximize%20the%20Total%20Height%20of%20Unique%20Towers/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>maximumHeight</code>, where <code>maximumHeight[i]</code> denotes the <strong>maximum</strong> height the <code>i<sup>th</sup></code> tower can be assigned.</p>

<p>Your task is to assign a height to each tower so that:</p>

<ol>
	<li>The height of the <code>i<sup>th</sup></code> tower is a positive integer and does not exceed <code>maximumHeight[i]</code>.</li>
	<li>No two towers have the same height.</li>
</ol>

<p>Return the <strong>maximum</strong> possible total sum of the tower heights. If it&#39;s not possible to assign heights, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> maximumHeight<span class="example-io"> = [2,3,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>We can assign heights in the following way: <code>[1, 2, 4, 3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> maximumHeight<span class="example-io"> = [15,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">25</span></p>

<p><strong>Explanation:</strong></p>

<p>We can assign heights in the following way: <code>[15, 10]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> maximumHeight<span class="example-io"> = [2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It&#39;s impossible to assign positive heights to each index so that no two towers have the same height.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= maximumHeight.length&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= maximumHeight[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Greedy

We can sort the maximum heights of the towers in descending order, then allocate the heights one by one starting from the maximum height. Use a variable $mx$ to record the current maximum allocated height.

If the current height $x$ is greater than $mx - 1$, update $x$ to $mx - 1$. If $x$ is less than or equal to $0$, it means the height cannot be allocated, and we directly return $-1$. Otherwise, we add $x$ to the answer and update $mx$ to $x$.

Finally, return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $\textit{maximumHeight}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTotalSum(self, maximumHeight: List[int]) -> int:
        maximumHeight.sort()
        ans, mx = 0, inf
        for x in maximumHeight[::-1]:
            x = min(x, mx - 1)
            if x <= 0:
                return -1
            ans += x
            mx = x
        return ans
```

#### Java

```java
class Solution {
    public long maximumTotalSum(int[] maximumHeight) {
        long ans = 0;
        int mx = 1 << 30;
        Arrays.sort(maximumHeight);
        for (int i = maximumHeight.length - 1; i >= 0; --i) {
            int x = Math.min(maximumHeight[i], mx - 1);
            if (x <= 0) {
                return -1;
            }
            ans += x;
            mx = x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTotalSum(vector<int>& maximumHeight) {
        ranges::sort(maximumHeight, greater<int>());
        long long ans = 0;
        int mx = 1 << 30;
        for (int x : maximumHeight) {
            x = min(x, mx - 1);
            if (x <= 0) {
                return -1;
            }
            ans += x;
            mx = x;
        }
        return ans;
    }
};
```

#### Go

```go
func maximumTotalSum(maximumHeight []int) int64 {
	slices.SortFunc(maximumHeight, func(a, b int) int { return b - a })
	ans := int64(0)
	mx := 1 << 30
	for _, x := range maximumHeight {
		x = min(x, mx-1)
		if x <= 0 {
			return -1
		}
		ans += int64(x)
		mx = x
	}
	return ans
}
```

#### TypeScript

```ts
function maximumTotalSum(maximumHeight: number[]): number {
    maximumHeight.sort((a, b) => a - b).reverse();
    let ans: number = 0;
    let mx: number = Infinity;
    for (let x of maximumHeight) {
        x = Math.min(x, mx - 1);
        if (x <= 0) {
            return -1;
        }
        ans += x;
        mx = x;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
