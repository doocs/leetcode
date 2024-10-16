---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3300.Minimum%20Element%20After%20Replacement%20With%20Digit%20Sum/README.md
rating: 1181
source: 第 140 场双周赛 Q1
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [3300. 替换为数位和以后的最小元素](https://leetcode.cn/problems/minimum-element-after-replacement-with-digit-sum)

[English Version](/solution/3300-3399/3300.Minimum%20Element%20After%20Replacement%20With%20Digit%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>请你将 <code>nums</code>&nbsp;中每一个元素都替换为它的各个数位之 <strong>和</strong>&nbsp;。</p>

<p>请你返回替换所有元素以后 <code>nums</code>&nbsp;中的 <strong>最小</strong>&nbsp;元素。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [10,12,13,14]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;替换后变为&nbsp;<code>[1, 3, 4, 5]</code>&nbsp;，最小元素为 1 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p><code>nums</code>&nbsp;替换后变为&nbsp;<code>[1, 2, 3, 4]</code>&nbsp;，最小元素为 1 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [999,19,199]</span></p>

<p><span class="example-io"><b>输出：</b>10</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;替换后变为&nbsp;<code>[27, 10, 19]</code>&nbsp;，最小元素为 10 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以遍历数组 $\textit{nums}$，对于每个数 $x$，我们计算其各个数位之和 $y$，取所有 $y$ 中的最小值即为答案。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组 $\textit{nums}$ 的长度和数组中的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minElement(self, nums: List[int]) -> int:
        return min(sum(int(b) for b in str(x)) for x in nums)
```

#### Java

```java
class Solution {
    public int minElement(int[] nums) {
        int ans = 100;
        for (int x : nums) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y += x % 10;
            }
            ans = Math.min(ans, y);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minElement(vector<int>& nums) {
        int ans = 100;
        for (int x : nums) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y += x % 10;
            }
            ans = min(ans, y);
        }
        return ans;
    }
};
```

#### Go

```go
func minElement(nums []int) int {
	ans := 100
	for _, x := range nums {
		y := 0
		for ; x > 0; x /= 10 {
			y += x % 10
		}
		ans = min(ans, y)
	}
	return ans
}
```

#### TypeScript

```ts
function minElement(nums: number[]): number {
    let ans: number = 100;
    for (let x of nums) {
        let y = 0;
        for (; x; x = Math.floor(x / 10)) {
            y += x % 10;
        }
        ans = Math.min(ans, y);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
