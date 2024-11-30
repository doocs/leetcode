---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0453.Minimum%20Moves%20to%20Equal%20Array%20Elements/README_EN.md
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [453. Minimum Moves to Equal Array Elements](https://leetcode.com/problems/minimum-moves-to-equal-array-elements)

[中文文档](/solution/0400-0499/0453.Minimum%20Moves%20to%20Equal%20Array%20Elements/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> of size <code>n</code>, return <em>the minimum number of moves required to make all array elements equal</em>.</p>

<p>In one move, you can increment <code>n - 1</code> elements of the array by <code>1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Only three moves are needed (remember each move increments two elements):
[1,2,3]  =&gt;  [2,3,3]  =&gt;  [3,4,3]  =&gt;  [4,4,4]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The answer is guaranteed to fit in a <strong>32-bit</strong> integer.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

Let the minimum value of the array $\textit{nums}$ be $\textit{mi}$, the sum of the array be $\textit{s}$, and the length of the array be $\textit{n}$.

Assume the minimum number of operations is $\textit{k}$, and the final value of all elements in the array is $\textit{x}$. Then we have:

$$
\begin{aligned}
\textit{s} + (\textit{n} - 1) \times \textit{k} &= \textit{n} \times \textit{x} \\
\textit{x} &= \textit{mi} + \textit{k} \\
\end{aligned}
$$

Substituting the second equation into the first equation, we get:

$$
\begin{aligned}
\textit{s} + (\textit{n} - 1) \times \textit{k} &= \textit{n} \times (\textit{mi} + \textit{k}) \\
\textit{s} + (\textit{n} - 1) \times \textit{k} &= \textit{n} \times \textit{mi} + \textit{n} \times \textit{k} \\
\textit{k} &= \textit{s} - \textit{n} \times \textit{mi} \\
\end{aligned}
$$

Therefore, the minimum number of operations is $\textit{s} - \textit{n} \times \textit{mi}$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, nums: List[int]) -> int:
        return sum(nums) - min(nums) * len(nums)
```

#### Java

```java
class Solution {
    public int minMoves(int[] nums) {
        return Arrays.stream(nums).sum() - Arrays.stream(nums).min().getAsInt() * nums.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums) {
        int s = 0;
        int mi = 1 << 30;
        for (int x : nums) {
            s += x;
            mi = min(mi, x);
        }
        return s - mi * nums.size();
    }
};
```

#### Go

```go
func minMoves(nums []int) int {
	mi := 1 << 30
	s := 0
	for _, x := range nums {
		s += x
		if x < mi {
			mi = x
		}
	}
	return s - mi*len(nums)
}
```

#### TypeScript

```ts
function minMoves(nums: number[]): number {
    let mi = 1 << 30;
    let s = 0;
    for (const x of nums) {
        s += x;
        mi = Math.min(mi, x);
    }
    return s - mi * nums.length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
