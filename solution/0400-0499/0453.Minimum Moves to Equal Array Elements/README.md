---
comments: true
difficulty: 中等
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [453. 最小操作次数使数组元素相等](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements)

[English Version](/solution/0400-0499/0453.Minimum%20Moves%20to%20Equal%20Array%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组，每次操作将会使 <code>n - 1</code> 个元素增加 <code>1</code> 。返回让数组所有元素相等的最小操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>3
<strong>解释：</strong>
只需要3次操作（注意每次操作会增加两个元素的值）：
[1,2,3]  =&gt;  [2,3,3]  =&gt;  [3,4,3]  =&gt;  [4,4,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>答案保证符合 <strong>32-bit</strong> 整数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

<!-- thinking:start -->

> **思考**
>
> 每次把 $n-1$ 个数加一，等价于把剩下那个数减一，直到全部等于最小值。模拟加一会改动整个数组。
>
> 因此答案是每个数减到最小值的次数之和，即 $\sum nums - n\cdot\min(nums)$。
>
> 一次求最小与总和即可，不必真的执行操作。

<!-- thinking:end -->

我们不妨记数组 $\textit{nums}$ 的最小值为 $\textit{mi}$，数组的和为 $\textit{s}$，数组的长度为 $\textit{n}$。

假设最小操作次数为 $\textit{k}$，最终数组的所有元素都为 $\textit{x}$，则有：

$$
\begin{aligned}
\textit{s} + (\textit{n} - 1) \times \textit{k} &= \textit{n} \times \textit{x} \\
\textit{x} &= \textit{mi} + \textit{k} \\
\end{aligned}
$$

将第二个式子代入第一个式子，得到：

$$
\begin{aligned}
\textit{s} + (\textit{n} - 1) \times \textit{k} &= \textit{n} \times (\textit{mi} + \textit{k}) \\
\textit{s} + (\textit{n} - 1) \times \textit{k} &= \textit{n} \times \textit{mi} + \textit{n} \times \textit{k} \\
\textit{k} &= \textit{s} - \textit{n} \times \textit{mi} \\
\end{aligned}
$$

因此，最小操作次数为 $\textit{s} - \textit{n} \times \textit{mi}$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

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
