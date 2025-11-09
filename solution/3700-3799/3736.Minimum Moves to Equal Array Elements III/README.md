---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3736.Minimum%20Moves%20to%20Equal%20Array%20Elements%20III/README.md
---

<!-- problem:start -->

# [3736. 最小操作次数使数组元素相等 III](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-iii)

[English Version](/solution/3700-3799/3736.Minimum%20Moves%20to%20Equal%20Array%20Elements%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>在一步操作中，你可以将任意单个元素 <code>nums[i]</code> 的值&nbsp;<strong>增加</strong> 1。</p>

<p>返回使数组中的所有元素都&nbsp;<strong>相等&nbsp;</strong>所需的&nbsp;<strong>最小总操作次数&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [2,1,3]</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<p>使所有元素相等的操作如下:</p>

<ul>
	<li>将 <code>nums[0] = 2</code> 增加 1, 变为 3。</li>
	<li>将 <code>nums[1] = 1</code> 增加 1, 变为 2。</li>
	<li>将 <code>nums[1] = 2</code> 增加 1, 变为 3。</li>
</ul>

<p>现在，<code>nums</code> 中的所有元素都等于 3。最小总操作次数为 <code>3</code>。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [4,4,5]</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<p>使所有元素相等的操作如下:</p>

<ul>
	<li>将 <code>nums[0] = 4</code> 增加 1, 变为 5。</li>
	<li>将 <code>nums[1] = 4</code> 增加 1, 变为 5。</li>
</ul>

<p>现在，<code>nums</code> 中的所有元素都等于 5。最小总操作次数为 <code>2</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一： 计算总和与最大值

本题要求将数组中的所有元素变为相等，且每次只能将某个元素增加 1。为了使操作次数最少，我们应当将所有元素都变为数组中的最大值。

因此，我们可以先计算数组的最大值 $\textit{mx}$ 和数组元素的总和 $\textit{s}$，那么将所有元素变为 $\textit{mx}$ 所需的操作次数即为 $\textit{mx} \times n - \textit{s}$，其中 $n$ 是数组的长度。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, nums: List[int]) -> int:
        n = len(nums)
        mx = max(nums)
        s = sum(nums)
        return mx * n - s
```

#### Java

```java
class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int mx = 0;
        int s = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
            s += x;
        }
        return mx * n - s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums) {
        int n = nums.size();
        int mx = 0;
        int s = 0;
        for (int x : nums) {
            mx = max(mx, x);
            s += x;
        }
        return mx * n - s;
    }
};
```

#### Go

```go
func minMoves(nums []int) int {
	mx, s := 0, 0
	for _, x := range nums {
		mx = max(mx, x)
		s += x
	}
	return mx*len(nums) - s
}
```

#### TypeScript

```ts
function minMoves(nums: number[]): number {
    const n = nums.length;
    const mx = Math.max(...nums);
    const s = nums.reduce((a, b) => a + b, 0);
    return mx * n - s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
