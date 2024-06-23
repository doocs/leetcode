---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3190.Find%20Minimum%20Operations%20to%20Make%20All%20Elements%20Divisible%20by%20Three/README.md
---

<!-- problem:start -->

# [3190. 使所有元素都可以被 3 整除的最少操作数](https://leetcode.cn/problems/find-minimum-operations-to-make-all-elements-divisible-by-three)

[English Version](/solution/3100-3199/3190.Find%20Minimum%20Operations%20to%20Make%20All%20Elements%20Divisible%20by%20Three/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。一次操作中，你可以将&nbsp;<code>nums</code>&nbsp;中的&nbsp;<strong>任意</strong>&nbsp;一个元素增加或者减少 1 。</p>

<p>请你返回将 <code>nums</code>&nbsp;中所有元素都可以被 3 整除的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>通过以下 3 个操作，数组中的所有元素都可以被 3 整除：</p>

<ul>
	<li>将 1 减少 1 。</li>
	<li>将 2 增加 1 。</li>
	<li>将 4 减少 1 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,6,9]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们直接遍历数组 $\text{nums}$，对于每个元素 $x$，我们计算 $x$ 除以 3 的余数 $x \bmod 3$，如果余数不为 0，我们需要将 $x$ 变为能被 3 整除且操作次数最少，那么我们可以选择将 $x$ 减少 $x \bmod 3$ 或者增加 $3 - x \bmod 3$，取两者的最小值累加到答案中。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\text{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        ans = 0
        for x in nums:
            if mod := x % 3:
                ans += min(mod, 3 - mod)
        return ans
```

#### Java

```java
class Solution {
    public int minimumOperations(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            int mod = x % 3;
            if (mod != 0) {
                ans += Math.min(mod, 3 - mod);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
    public int minimumOperations(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            int mod = x % 3;
            if (mod != 0) {
                ans += Math.min(mod, 3 - mod);
            }
        }
        return ans;
    }
}
```

#### Go

```go
func minimumOperations(nums []int) (ans int) {
	for _, x := range nums {
		if mod := x % 3; mod > 0 {
			ans += min(mod, 3-mod)
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumOperations(nums: number[]): number {
    let ans = 0;
    for (const x of nums) {
        const mod = x % 3;
        if (mod) {
            ans += Math.min(mod, 3 - mod);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
