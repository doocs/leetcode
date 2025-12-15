---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2892.Minimizing%20Array%20After%20Replacing%20Pairs%20With%20Their%20Product/README.md
tags:
    - 贪心
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [2892. 将相邻元素相乘后得到最小化数组 🔒](https://leetcode.cn/problems/minimizing-array-after-replacing-pairs-with-their-product)

[English Version](/solution/2800-2899/2892.Minimizing%20Array%20After%20Replacing%20Pairs%20With%20Their%20Product/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>，你可以对数组执行以下操作任意次数：</p>

<ul>
	<li>选择数组中的两个 <b>相邻</b>&nbsp;元素，例如&nbsp;<code>x</code>&nbsp;和&nbsp;<code>y</code>，使得&nbsp;<code>x * y &lt;= k</code>&nbsp;，并用一个值为&nbsp;<code>x * y</code>&nbsp;的 <b>单个元素</b>&nbsp;替换它们（例如，在一次操作中，数组&nbsp;<code>[1, 2, 2, 3]</code>，其中&nbsp;<code>k = 5</code> 可以变为&nbsp;<code>[1, 4, 3]</code>&nbsp;或&nbsp;<code>[2, 2, 3]</code>，但不能变为&nbsp;<code>[1, 2, 6]</code>）。</li>
</ul>

<p>返回 <em>经过任意次数的操作后，&nbsp;</em><code>nums</code><em>&nbsp;的 <strong>最小</strong> 可能长度。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,3,7,3,5], k = 20
<strong>输出：</strong>3
<strong>解释：</strong>我们执行以下操作：
1. [<u>2,3</u>,3,7,3,5] -&gt; [<u>6</u>,3,7,3,5]
2. [<u>6,3</u>,7,3,5] -&gt; [<u>18</u>,7,3,5]
3. [18,7,<u>3,5</u>] -&gt; [18,7,<u>15</u>]
可以证明，在执行给定操作后，最小可能长度为3.</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3,3,3], k = 6
<strong>输出：</strong>4
<strong>解释：</strong>由于每两个相邻元素的乘积都大于 6，所以无法执行任何操作。因此，答案为 4。</pre>

<p>&nbsp;</p>

<p><strong>约束条件：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们用一个变量 $ans$ 记录当前数组的长度，用一个变量 $y$ 记录当前数组的乘积，初始时 $ans = 1$, $y = nums[0]$。

我们从数组的第二个元素开始遍历，设当前元素为 $x$：

- 如果 $x = 0$，那么整个数组的乘积为 $0 \le k$，因此答案数组的最小长度为 $1$，直接返回即可。
- 如果 $x \times y \le k$，那么我们可以将 $x$ 与 $y$ 合并，即 $y = x \times y$。
- 如果 $x \times y \gt k$，那么我们无法将 $x$ 与 $y$ 合并，因此我们需要将 $x$ 单独作为一个元素，即 $ans = ans + 1$，并且 $y = x$。

最终答案即为 $ans$。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minArrayLength(self, nums: List[int], k: int) -> int:
        ans, y = 1, nums[0]
        for x in nums[1:]:
            if x == 0:
                return 1
            if x * y <= k:
                y *= x
            else:
                y = x
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minArrayLength(int[] nums, int k) {
        int ans = 1;
        long y = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int x = nums[i];
            if (x == 0) {
                return 1;
            }
            if (x * y <= k) {
                y *= x;
            } else {
                y = x;
                ++ans;
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
    int minArrayLength(vector<int>& nums, int k) {
        int ans = 1;
        long long y = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i];
            if (x == 0) {
                return 1;
            }
            if (x * y <= k) {
                y *= x;
            } else {
                y = x;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minArrayLength(nums []int, k int) int {
	ans, y := 1, nums[0]
	for _, x := range nums[1:] {
		if x == 0 {
			return 1
		}
		if x*y <= k {
			y *= x
		} else {
			y = x
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minArrayLength(nums: number[], k: number): number {
    let [ans, y] = [1, nums[0]];
    for (const x of nums.slice(1)) {
        if (x === 0) {
            return 1;
        }
        if (x * y <= k) {
            y *= x;
        } else {
            y = x;
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
