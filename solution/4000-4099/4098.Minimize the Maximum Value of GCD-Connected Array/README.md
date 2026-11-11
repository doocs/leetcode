---
comments: true
difficulty: 中等
rating: 1729
source: 第 526 场周赛 Q2
---

<!-- problem:start -->

# [4098. 最小化 GCD 连通数组的最大值](https://leetcode.cn/problems/minimize-the-maximum-value-of-gcd-connected-array)

[English Version](/solution/4000-4099/4098.Minimize%20the%20Maximum%20Value%20of%20GCD-Connected%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数 <code>n</code>、<code>index</code> 和 <code>value</code>。</p>

<p>如果长度为 <code>n</code> 的整数数组 <code>nums</code> 满足以下条件，则称其为 <strong>GCD 连通数组</strong>：</p>

<ul>
	<li>对于 <code>0 &lt;= i &lt;= n - 1</code>，有 <code>nums[i] &gt; 0</code>。</li>
	<li>对于 <code>0 &lt;= i &lt;= n - 2</code>，有 <code>nums[i] &lt; nums[i + 1]</code>。</li>
	<li><code>nums[index] = value</code>。</li>
	<li>对于 <code>0 &lt;= i &lt;= n - 2</code>，有 <code>gcd(nums[i], nums[i + 1]) &gt; 1</code>。</li>
</ul>

<p>在所有 GCD 连通数组 <code>nums</code> 中，返回最大元素 <code>nums[n - 1]</code> 可能的<strong>最小值</strong>。如果不存在这样的数组，返回 -1。</p>
其中，<code>gcd(a, b)</code> 表示 <code>a</code> 和 <code>b</code> 的<strong>最大公约数</strong>。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, index = 2, value = 8</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>由于 <code>nums[2] = 8</code>，GCD 连通数组的形式为：<code>nums = [?, ?, 8, ?]</code>。</li>
	<li>由于 <code>nums[3] &gt; 8</code> 且 <code>gcd(8, 9) = 1</code>，所有合法的 GCD 连通数组都必须满足 <code>nums[3] &gt;= 10</code>。</li>
	<li>一个满足所有条件的最优数组是 <code>[2, 4, 8, 10]</code>。</li>
	<li>因此，<code>nums[3]</code> 可能的最小值为 10。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, index = 1, value = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>由于 <code>nums[1] = 3</code>，GCD 连通数组的形式为：<code>nums = [?, 3]</code>。</li>
	<li>由于需要满足 <code>0 &lt; nums[0] &lt; nums[1] = 3</code>，只有两个可能的数组：<code>[1, 3]</code> 和 <code>[2, 3]</code>。它们都不满足 <code>gcd(nums[0], nums[1]) &gt; 1</code>。</li>
	<li>因此，不存在合法的 GCD 连通数组，答案为 -1。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, index = 0, value = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>由于 <code>nums[0] = 3</code>，GCD 连通数组的形式为：<code>nums = [3, ?]</code>。</li>
	<li>由于 <code>nums[1] &gt; 3</code> 且 <code>gcd(3, 4) = gcd(3, 5) = 1</code>，所有合法的 GCD 连通数组都必须满足 <code>nums[1] &gt;= 6</code>。</li>
	<li>一个最优数组是 <code>[3, 6]</code>。</li>
	<li>因此，<code>nums[1]</code> 可能的最小值为 6。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= value &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= index &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学 + 贪心

<!-- thinking:start -->

> **思考**
>
> $n$ 和 $\textit{value}$ 高达 $10^9$，不能逐一构造数组元素，需要直接推导出首尾元素的紧界。
>
> 相邻两项满足 $\textit{nums}[i] < \textit{nums}[i + 1]$ 且 $\gcd(\textit{nums}[i], \textit{nums}[i + 1]) > 1$。由于最大公约数整除两数之差，任意相邻两项的差至少为 $2$；而与 $\textit{nums}[\textit{index}] = \textit{value}$ 直接相邻的项，其与 $\textit{value}$ 的差还必须是 $\textit{value}$ 的某个大于 $1$ 的因子之倍数，因此最小差值为 $\textit{value}$ 的最小质因子 $p$。
>
> 注意到无论 $\textit{value}$ 是偶数（此时 $p = 2$）还是奇合数或奇质数（此时 $p$ 也为奇数）， $\textit{value} - p$ 和 $\textit{value} + p$ 都必然是偶数。既然进入偶数，其余每一步只需增减 $2$ 就能始终保持相邻两项都是偶数（从而最大公约数至少为 $2$），恰好取到差值的理论下界。据此可以直接检查左侧是否有足够的正整数空间，并算出右侧末尾 $\textit{nums}[n - 1]$ 的最小值。

<!-- thinking:end -->

如果 $\textit{value} = 1$，由于 $1$ 与任何正整数的最大公约数都是 $1$，无法满足条件，直接返回 $-1$。

当 $\textit{value} > 1$ 时，我们先通过试除法求出 $\textit{value}$ 的最小质因子 $p$。由于任意相邻两项的最大公约数大于 $1$，相邻两项之差至少为 $2$；而与 $\textit{nums}[\textit{index}] = \textit{value}$ 相邻的元素，和 $\textit{value}$ 的差至少为 $p$。又因为 $\textit{value}$ 与其最小质因子 $p$ 同奇偶，所以 $\textit{value} - p$ 和 $\textit{value} + p$ 均为偶数，后续相邻元素只需每次相差 $2$ 即可保证均为偶数且最大公约数大于 $1$：

- 对于下标 $\textit{index}$ 左侧的部分，为了使 $\textit{nums}[0]$ 尽可能大，应当令 $\textit{nums}[\textit{index} - 1] = \textit{value} - p$，再向左每次递减 $2$，此时 $\textit{nums}[0] = \textit{value} - p - (\textit{index} - 1) \times 2$。若该值小于等于 $0$（当 $\textit{index} = 0$ 时， $\textit{value} - p + 2 \ge 2 > 0$ 恒成立），说明左侧无法填入合法的正整数，返回 $-1$。
- 对于下标 $\textit{index}$ 右侧的部分，若 $\textit{index} = n - 1$，则最大元素即为 $\textit{value}$；否则令 $\textit{nums}[\textit{index} + 1] = \textit{value} + p$，再向右每次递增 $2$，得到 $\textit{nums}[n - 1]$ 的最小可能值为 $\textit{value} + p + (n - 2 - \textit{index}) \times 2$。

时间复杂度 $O(\sqrt{\textit{value}})$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMaxValue(self, n: int, index: int, value: int) -> int:
        if value == 1:
            return -1
        p = value
        for x in range(2, isqrt(value) + 1):
            if value % x == 0:
                p = x
                break
        if value - p - (index - 1) * 2 <= 0:
            return -1
        if index == n - 1:
            return value
        return value + p + (n - 2 - index) * 2
```

#### Java

```java
class Solution {
    public long minimumMaxValue(int n, int index, int value) {
        if (value == 1) {
            return -1;
        }

        long p = value;
        for (long x = 2; x * x <= value; x++) {
            if (value % x == 0) {
                p = x;
                break;
            }
        }

        if ((long) value - p - ((long) index - 1) * 2 <= 0) {
            return -1;
        }
        if (index == n - 1) {
            return value;
        }
        return (long) value + p + ((long) n - 2 - index) * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumMaxValue(int n, int index, int value) {
        if (value == 1) {
            return -1;
        }

        long long p = value;
        for (long long x = 2; x * x <= value; ++x) {
            if (value % x == 0) {
                p = x;
                break;
            }
        }

        if ((long long) value - p - ((long long) index - 1) * 2 <= 0) {
            return -1;
        }
        if (index == n - 1) {
            return value;
        }
        return (long long) value + p + ((long long) n - 2 - index) * 2;
    }
};
```

#### Go

```go
func minimumMaxValue(n int, index int, value int) int64 {
	if value == 1 {
		return -1
	}

	p := int64(value)
	for x := int64(2); x*x <= int64(value); x++ {
		if int64(value)%x == 0 {
			p = x
			break
		}
	}

	if int64(value)-p-(int64(index)-1)*2 <= 0 {
		return -1
	}
	if index == n-1 {
		return int64(value)
	}
	return int64(value) + p + (int64(n)-2-int64(index))*2
}
```

#### TypeScript

```ts
function minimumMaxValue(n: number, index: number, value: number): number {
    if (value === 1) {
        return -1;
    }

    let p = value;
    for (let x = 2; x * x <= value; x++) {
        if (value % x === 0) {
            p = x;
            break;
        }
    }

    if (value - p - (index - 1) * 2 <= 0) {
        return -1;
    }
    if (index === n - 1) {
        return value;
    }
    return value + p + (n - 2 - index) * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
