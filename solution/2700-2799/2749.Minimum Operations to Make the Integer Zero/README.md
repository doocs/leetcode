---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2749.Minimum%20Operations%20to%20Make%20the%20Integer%20Zero/README.md
rating: 2132
source: 第 351 场周赛 Q2
tags:
    - 位运算
    - 脑筋急转弯
    - 枚举
---

<!-- problem:start -->

# [2749. 得到整数零需要执行的最少操作数](https://leetcode.cn/problems/minimum-operations-to-make-the-integer-zero)

[English Version](/solution/2700-2799/2749.Minimum%20Operations%20to%20Make%20the%20Integer%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数：<code>num1</code> 和 <code>num2</code> 。</p>

<p>在一步操作中，你需要从范围&nbsp;<code>[0, 60]</code> 中选出一个整数 <code>i</code> ，并从 <code>num1</code> 减去 <code>2<sup>i</sup> + num2</code> 。</p>

<p>请你计算，要想使 <code>num1</code> 等于 <code>0</code> 需要执行的最少操作数，并以整数形式返回。</p>

<p>如果无法使 <code>num1</code> 等于 <code>0</code> ，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num1 = 3, num2 = -2
<strong>输出：</strong>3
<strong>解释：</strong>可以执行下述步骤使 3 等于 0 ：
- 选择 i = 2 ，并从 3 减去 2<sup>2</sup> + (-2) ，num1 = 3 - (4 + (-2)) = 1 。
- 选择 i = 2 ，并从 1 减去 2<sup>2</sup> + (-2) ，num1 = 1 - (4 + (-2)) = -1 。
- 选择 i = 0 ，并从 -1 减去 2<sup>0</sup>&nbsp;+ (-2) ，num1 = (-1) - (1 + (-2)) = 0 。
可以证明 3 是需要执行的最少操作数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num1 = 5, num2 = 7
<strong>输出：</strong>-1
<strong>解释：</strong>可以证明，执行操作无法使 5 等于 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num1 &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= num2 &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

如果我们操作了 $k$ 次，那么问题实际上就变成了：判断 $\textit{num1} - k \times \textit{num2}$ 能否拆分成 $k$ 个 $2^i$ 之和。

我们不妨假设 $x = \textit{num1} - k \times \textit{num2}$，接下来分类讨论：

-   如果 $x \lt 0$，那么 $x$ 无法拆分成 $k$ 个 $2^i$ 之和，因为 $2^i \gt 0$，显然无解；
-   如果 $x$ 的二进制表示中 $1$ 的个数大于 $k$，此时也是无解；
-   否则，对于当前 $k$，一定存在一个拆分方案。

因此，我们从 $1$ 开始枚举 $k$，一旦找到一个满足条件的 $k$，就可以直接返回答案。

时间复杂度 $O(\log x)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makeTheIntegerZero(self, num1: int, num2: int) -> int:
        for k in count(1):
            x = num1 - k * num2
            if x < 0:
                break
            if x.bit_count() <= k <= x:
                return k
        return -1
```

#### Java

```java
class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (long k = 1;; ++k) {
            long x = num1 - k * num2;
            if (x < 0) {
                break;
            }
            if (Long.bitCount(x) <= k && k <= x) {
                return (int) k;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int makeTheIntegerZero(int num1, int num2) {
        using ll = long long;
        for (ll k = 1;; ++k) {
            ll x = num1 - k * num2;
            if (x < 0) {
                break;
            }
            if (__builtin_popcountll(x) <= k && k <= x) {
                return k;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func makeTheIntegerZero(num1 int, num2 int) int {
	for k := 1; ; k++ {
		x := num1 - k*num2
		if x < 0 {
			break
		}
		if bits.OnesCount(uint(x)) <= k && k <= x {
			return k
		}
	}
	return -1
}
```

#### TypeScript

```ts
function makeTheIntegerZero(num1: number, num2: number): number {
    for (let k = 1; ; ++k) {
        let x = num1 - k * num2;
        if (x < 0) {
            break;
        }
        if (x.toString(2).replace(/0/g, '').length <= k && k <= x) {
            return k;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
