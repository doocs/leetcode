---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4088. 峰值与谷值的差](https://leetcode.cn/problems/difference-between-peaks-and-valleys)

[English Version](/solution/4000-4099/4088.Difference%20Between%20Peaks%20and%20Valleys/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>如果满足以下&nbsp;<strong>所有&nbsp;</strong>条件，则下标志 <code>i</code> 被称为&nbsp;<strong>峰值&nbsp;</strong>：</p>

<ul>
	<li><code>0 &lt; i &lt; n - 1</code></li>
	<li><code>nums[i] &gt; nums[i - 1]</code></li>
	<li><code>nums[i] &gt; nums[i + 1]</code></li>
</ul>

<p>如果满足以下&nbsp;<strong>所有&nbsp;</strong>条件，则下标志 <code>i</code> 被称为&nbsp;<strong>谷值&nbsp;</strong>：</p>

<ul>
	<li><code>0 &lt; i &lt; n - 1</code></li>
	<li><code>nums[i] &lt; nums[i - 1]</code></li>
	<li><code>nums[i] &lt; nums[i + 1]</code></li>
</ul>

<p>返回一个整数，表示所有峰值之和与所有谷值之和的&nbsp;<strong>差值&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,5,2,4,1,3,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>峰值分别是 <code>nums[1] = 5</code>、<code>nums[3] = 4</code> 和 <code>nums[5] = 3</code>。</li>
	<li>谷值分别是 <code>nums[2] = 2</code> 和 <code>nums[4] = 1</code>。</li>
	<li>峰值之和与谷值之和的差值为 <code>(5 + 4 + 3) - (2 + 1) = 9</code>。因此，答案是 9。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-5,-6,-1,-2,5,3,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>峰值分别是 <code>nums[2] = -1</code> 和 <code>nums[4] = 5</code>。</li>
	<li>谷值分别是 <code>nums[1] = -6</code> 和 <code>nums[3] = -2</code>。</li>
	<li>峰值之和与谷值之和的差值为 <code>(-1 + 5) - (-6 + (-2)) = 12</code>。因此，答案是 12。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,5,2,5,5,3,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">-5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数组中没有峰值。</li>
	<li>谷值分别是 <code>nums[2] = 2</code> 和 <code>nums[5] = 3</code>。</li>
	<li>峰值之和与谷值之和的差值为 <code>(0) - (2 + 3) = -5</code>。因此，答案是 -5。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

<!-- thinking:start -->

> **思考**
>
> $n$ 不超过 $100$，把每个内部下标和左右邻居比一次就够了。峰值和谷值都只由相邻三个位置决定，扫描时就可以把差值累加出来。
>
> 相邻元素相等时，两种定义都不成立。只有严格大于两侧的位置贡献正值，严格小于两侧的位置贡献负值。
>
> 所求差值就是这些贡献之和。从左到右扫一遍，峰值加上 $\textit{nums}[i]$，谷值减去 $\textit{nums}[i]$。

<!-- thinking:end -->

下标 $i$ 是峰值，当且仅当 $0<i<n-1$ 且 $\textit{nums}[i]$ 严格大于 $\textit{nums}[i-1]$ 与 $\textit{nums}[i+1]$。谷值把这两个不等号同时换成小于。端点不参与判断。

从 $i=1$ 扫到 $i=n-2$。当前元素同时大于左右邻居时，把它加进答案；同时小于左右邻居时，从答案里减去它。其余位置答案不变。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def peakValleyDifference(self, nums: list[int]) -> int:
        ans = 0
        for i in range(1, len(nums) - 1):
            if nums[i - 1] < nums[i] > nums[i + 1]:
                ans += nums[i]
            elif nums[i - 1] > nums[i] < nums[i + 1]:
                ans -= nums[i]
        return ans
```

#### Java

```java
class Solution {
    public int peakValleyDifference(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                ans += nums[i];
            } else if (nums[i - 1] > nums[i] && nums[i] < nums[i + 1]) {
                ans -= nums[i];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int peakValleyDifference(vector<int>& nums) {
        int ans = 0;
        for (int i = 1; i < nums.size() - 1; i++) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                ans += nums[i];
            } else if (nums[i - 1] > nums[i] && nums[i] < nums[i + 1]) {
                ans -= nums[i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func peakValleyDifference(nums []int) int {
	ans := 0
	for i := 1; i < len(nums)-1; i++ {
		if nums[i-1] < nums[i] && nums[i] > nums[i+1] {
			ans += nums[i]
		} else if nums[i-1] > nums[i] && nums[i] < nums[i+1] {
			ans -= nums[i]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function peakValleyDifference(nums: number[]): number {
    let ans = 0;
    for (let i = 1; i < nums.length - 1; i++) {
        if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
            ans += nums[i];
        } else if (nums[i - 1] > nums[i] && nums[i] < nums[i + 1]) {
            ans -= nums[i];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
