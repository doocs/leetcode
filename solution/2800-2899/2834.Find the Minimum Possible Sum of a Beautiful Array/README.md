---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2834.Find%20the%20Minimum%20Possible%20Sum%20of%20a%20Beautiful%20Array/README.md
rating: 1409
source: 第 360 场周赛 Q2
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [2834. 找出美丽数组的最小和](https://leetcode.cn/problems/find-the-minimum-possible-sum-of-a-beautiful-array)

[English Version](/solution/2800-2899/2834.Find%20the%20Minimum%20Possible%20Sum%20of%20a%20Beautiful%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数：<code>n</code> 和 <code>target</code> 。</p>

<p>如果数组 <code>nums</code> 满足下述条件，则称其为 <strong>美丽数组</strong> 。</p>

<ul>
	<li><code>nums.length == n</code>.</li>
	<li><code>nums</code> 由两两互不相同的正整数组成。</li>
	<li>在范围 <code>[0, n-1]</code> 内，<strong>不存在 </strong>两个 <strong>不同</strong> 下标 <code>i</code> 和 <code>j</code> ，使得 <code>nums[i] + nums[j] == target</code> 。</li>
</ul>

<p>返回符合条件的美丽数组所可能具备的 <strong>最小</strong> 和，并对结果进行取模 <code>10<sup>9</sup>&nbsp;+ 7</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2, target = 3
<strong>输出：</strong>4
<strong>解释：</strong>nums = [1,3] 是美丽数组。
- nums 的长度为 n = 2 。
- nums 由两两互不相同的正整数组成。
- 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
可以证明 4 是符合条件的美丽数组所可能具备的最小和。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, target = 3
<strong>输出：</strong>8
<strong>解释：</strong>
nums = [1,3,4] 是美丽数组。 
- nums 的长度为 n = 3 。 
- nums 由两两互不相同的正整数组成。 
- 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
可以证明 8 是符合条件的美丽数组所可能具备的最小和。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1, target = 1
<strong>输出：</strong>1
<strong>解释：</strong>nums = [1] 是美丽数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 数学

我们可以贪心地从 $x = 1$ 开始构造数组 $nums$，每次选择 $x$，并且排除 $target - x$。

我们不妨记 $m = \left\lfloor \frac{target}{2} \right\rfloor$。

如果 $x <= m$，那么我们可以选择的数有 $1, 2, \cdots, n$，所以数组的和为 $\left\lfloor \frac{(1+n)n}{2} \right\rfloor$。

如果 $x > m$，那么我们可以选择的数有 $1, 2, \cdots, m$，共 $m$ 个数，以及 $n - m$ 个从 $target$ 开始的数，所以数组的和为 $\left\lfloor \frac{(1+m)m}{2} \right\rfloor + \left\lfloor \frac{(target + target + n - m - 1)(n-m)}{2} \right\rfloor$。

注意，我们需要对结果取模 $10^9 + 7$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumPossibleSum(self, n: int, target: int) -> int:
        mod = 10**9 + 7
        m = target // 2
        if n <= m:
            return ((1 + n) * n // 2) % mod
        return ((1 + m) * m // 2 + (target + target + n - m - 1) * (n - m) // 2) % mod
```

```java
class Solution {
    public int minimumPossibleSum(int n, int target) {
        final int mod = (int) 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (int) ((1L + n) * n / 2 % mod);
        }
        long a = (1L + m) * m / 2 % mod;
        long b = ((1L * target + target + n - m - 1) * (n - m) / 2) % mod;
        return (int) ((a + b) % mod);
    }
}
```

```cpp
class Solution {
public:
    int minimumPossibleSum(int n, int target) {
        const int mod = 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (1LL + n) * n / 2 % mod;
        }
        long long a = (1LL + m) * m / 2 % mod;
        long long b = (1LL * target + target + n - m - 1) * (n - m) / 2 % mod;
        return (a + b) % mod;
    }
};
```

```go
func minimumPossibleSum(n int, target int) int {
	const mod int = 1e9 + 7
	m := target / 2
	if n <= m {
		return (n + 1) * n / 2 % mod
	}
	a := (m + 1) * m / 2 % mod
	b := (target + target + n - m - 1) * (n - m) / 2 % mod
	return (a + b) % mod
}
```

```ts
function minimumPossibleSum(n: number, target: number): number {
    const mod = 10 ** 9 + 7;
    const m = target >> 1;
    if (n <= m) {
        return (((1 + n) * n) / 2) % mod;
    }
    return (((1 + m) * m) / 2 + ((target + target + n - m - 1) * (n - m)) / 2) % mod;
}
```

```cs
public class Solution {
    public int MinimumPossibleSum(int n, int target) {
        const int mod = (int) 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (int) ((1L + n) * n / 2 % mod);
        }
        long a = (1L + m) * m / 2 % mod;
        long b = ((1L * target + target + n - m - 1) * (n - m) / 2) % mod;
        return (int) ((a + b) % mod);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
