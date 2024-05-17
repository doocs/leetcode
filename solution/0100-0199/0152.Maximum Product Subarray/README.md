---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0152.Maximum%20Product%20Subarray/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [152. 乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray)

[English Version](/solution/0100-0199/0152.Maximum%20Product%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>&nbsp;，请你找出数组中乘积最大的非空连续<span data-keyword="subarray-nonempty">子数组</span>（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。</p>

<p>测试用例的答案是一个&nbsp;<strong>32-位</strong> 整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,-2,4]
<strong>输出:</strong> <code>6</code>
<strong>解释:</strong>&nbsp;子数组 [2,3] 有最大乘积 6。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [-2,0,-1]
<strong>输出:</strong> 0
<strong>解释:</strong>&nbsp;结果不能为 2, 因为 [-2,-1] 不是子数组。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li><code>nums</code> 的任何前缀或后缀的乘积都 <strong>保证</strong>&nbsp;是一个 <strong>32-位</strong> 整数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义两个变量 $f$ 和 $g$，其中 $f$ 表示以 $nums[i]$ 结尾的乘积最大子数组的乘积，而 $g$ 表示以 $nums[i]$ 结尾的乘积最小子数组的乘积。初始时 $f$ 和 $g$ 都等于 $nums[0]$。答案为所有 $f$ 中的最大值。

从 $i=1$ 开始，我们可以考虑将第 $i$ 个数 $nums[i]$ 添加到前面的乘积最大或者乘积最小的子数组乘积的后面，或者单独作为一个子数组乘积（即此时子数组长度只有 $1$）。我们将此前的乘积最大值记为 $ff$，乘积最小值记为 $gg$，那么 $f = \max(f, ff \times nums[i], gg \times nums[i])$，而 $g = \min(g, ff \times nums[i], gg \times nums[i])$。接下来，我们更新答案 $ans = \max(ans, f)$，然后继续计算下一个位置。

最后的答案即为 $ans$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。我们只需要遍历数组一次即可求得答案。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        ans = f = g = nums[0]
        for x in nums[1:]:
            ff, gg = f, g
            f = max(x, ff * x, gg * x)
            g = min(x, ff * x, gg * x)
            ans = max(ans, f)
        return ans
```

#### Java

```java
class Solution {
    public int maxProduct(int[] nums) {
        int f = nums[0], g = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int ff = f, gg = g;
            f = Math.max(nums[i], Math.max(ff * nums[i], gg * nums[i]));
            g = Math.min(nums[i], Math.min(ff * nums[i], gg * nums[i]));
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int f = nums[0], g = nums[0], ans = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int ff = f, gg = g;
            f = max({nums[i], ff * nums[i], gg * nums[i]});
            g = min({nums[i], ff * nums[i], gg * nums[i]});
            ans = max(ans, f);
        }
        return ans;
    }
};
```

#### Go

```go
func maxProduct(nums []int) int {
	f, g, ans := nums[0], nums[0], nums[0]
	for _, x := range nums[1:] {
		ff, gg := f, g
		f = max(x, max(ff*x, gg*x))
		g = min(x, min(ff*x, gg*x))
		ans = max(ans, f)
	}
	return ans
}
```

#### TypeScript

```ts
function maxProduct(nums: number[]): number {
    let [f, g, ans] = [nums[0], nums[0], nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        const [ff, gg] = [f, g];
        f = Math.max(nums[i], ff * nums[i], gg * nums[i]);
        g = Math.min(nums[i], ff * nums[i], gg * nums[i]);
        ans = Math.max(ans, f);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut f = nums[0];
        let mut g = nums[0];
        let mut ans = nums[0];
        for &x in nums.iter().skip(1) {
            let (ff, gg) = (f, g);
            f = x.max(x * ff).max(x * gg);
            g = x.min(x * ff).min(x * gg);
            ans = ans.max(f);
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxProduct = function (nums) {
    let [f, g, ans] = [nums[0], nums[0], nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        const [ff, gg] = [f, g];
        f = Math.max(nums[i], ff * nums[i], gg * nums[i]);
        g = Math.min(nums[i], ff * nums[i], gg * nums[i]);
        ans = Math.max(ans, f);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxProduct(int[] nums) {
        int f = nums[0], g = nums[0], ans = nums[0];
        for (int i = 1; i < nums.Length; ++i) {
            int ff = f, gg = g;
            f = Math.Max(nums[i], Math.Max(ff * nums[i], gg * nums[i]));
            g = Math.Min(nums[i], Math.Min(ff * nums[i], gg * nums[i]));
            ans = Math.Max(ans, f);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
