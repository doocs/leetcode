---
comments: true
difficulty: 中等
rating: 1696
source: 第 191 场双周赛 Q3
---

<!-- problem:start -->

# [4050. 得到恰好 N 分的最少天数](https://leetcode.cn/problems/minimum-days-to-score-exactly-n-points)

[English Version](/solution/4000-4099/4050.Minimum%20Days%20to%20Score%20Exactly%20N%20Points/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，表示目标分数。</p>

<p>你的分数初始为 0，每天你既可以&nbsp;<strong>获得&nbsp;</strong>分数，也可以&nbsp;<strong>跳过&nbsp;</strong>。</p>

<p>分数是在连胜期间获得的。在连胜的第一天，你获得 1 分，第二天获得 2 分，第三天获得 3 分，依此类推。<strong>跳过&nbsp;</strong>一天将获得&nbsp;<strong>零分&nbsp;</strong>并&nbsp;<strong>重置&nbsp;</strong>连胜，因此下一次你获得分数时，将再次从 1 开始。</p>

<p>返回达到&nbsp;<strong>恰好&nbsp;</strong>为 <code>n</code> 的分数所需的&nbsp;<strong>最少&nbsp;</strong>天数（包括所有跳过的天数）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 1 天：获得 1 分。分数为 1。</li>
	<li>第 2 天：跳过，这会重置连胜。如果今天获得分数会加上 2 分，从而使分数超过 <code>n = 2</code>。</li>
	<li>第 3 天：连胜已重置，因此获得 1 分。在 3 天内分数恰好达到 <code>n = 2</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 9</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 1 到 3 天：获得 1、2 和 3 分。分数为 <code>1 + 2 + 3 = 6</code>。</li>
	<li>第 4 天：跳过，这会重置连胜。</li>
	<li>第 5 和 6 天：获得 1 和 2 分。在 6 天内分数恰好达到 <code>6 + 1 + 2 = 9</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 12</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong>​​​​​​​</p>

<ul>
	<li>第 1 到 3 天：获得 1、2 和 3 分。分数为 <code>1 + 2 + 3 = 6</code>。</li>
	<li>第 4 天：跳过，这会重置连胜。</li>
	<li>第 5 到 7 天：获得 1、2 和 3 分。在 7 天内分数恰好达到 <code>6 + 1 + 2 + 3 = 12</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 得分由若干段连胜拼成：一段长度为 $j$ 的连胜贡献三角数 $j(j+1)/2$。段与段之间必须插入一天跳过才能重置；最后一段之后不必再跳。
>
> $n = 10^5$，按天模拟或搜索分割方案都不合适。把「得到恰好 $i$ 分」做成完全背包：每段连胜是一件物品，费用为天数。
>
> 令 $f[0] = -1$，转移时统一加上 $j + 1$（含一次跳过）。最后一段多算的那次跳过被 $f[0] = -1$ 抵消，答案就是 $f[n]$。

<!-- thinking:end -->

一次连胜持续 $j$ 天，得分是三角数 $s = \frac{j(j+1)}{2}$。两段连胜之间需要恰好一天跳过以重置连胜，而最后一段连胜之后不必再跳过。

定义 $f[i]$ 表示得到恰好 $i$ 分的最少天数。令 $f[0] = -1$，其余位置初始化为 $+\infty$。枚举最后一段连胜的长度 $j$（对应得分 $s$），则

$$
f[i] = \min\bigl(f[i],\, f[i - s] + j + 1\bigr)
$$

其中 $j + 1$ 包含这段连胜的 $j$ 天以及一天跳过。$f[0] = -1$ 使得最后一段连胜不会多算一天跳过：若只用一段长度为 $j$ 的连胜得到 $n$ 分，则 $f[n] = f[0] + j + 1 = j$。

由于 $n \le 10^5$，我们预处理到上限后即可 $O(1)$ 回答询问。$j$ 最大约为 $\sqrt{2n}$。

时间复杂度 $O(n \times \sqrt{n})$（预处理），空间复杂度 $O(n)$。单次询问为 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
mx = 10**5 + 1
f = [inf] * mx
f[0] = -1
for i in range(1, mx):
    j = 1
    while (s := (1 + j) * j // 2) <= i:
        f[i] = min(f[i], f[i - s] + j + 1)
        j += 1


class Solution:
    def minDays(self, n: int) -> int:
        return f[n]
```

#### Java

```java
class Solution {
    private static final int MX = 100001;
    private static final int[] f = new int[MX];

    static {
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = -1;

        for (int i = 1; i < MX; i++) {
            for (int j = 1; j * (j + 1) / 2 <= i; j++) {
                int s = j * (j + 1) / 2;
                f[i] = Math.min(f[i], f[i - s] + j + 1);
            }
        }
    }

    public int minDays(int n) {
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDays(int n) {
        static const auto f = [] {
            constexpr int mx = 100001;
            vector<int> f(mx, INT_MAX);

            f[0] = -1;

            for (int i = 1; i < mx; i++) {
                for (int j = 1; j * (j + 1) / 2 <= i; j++) {
                    int s = j * (j + 1) / 2;
                    f[i] = std::min(f[i], f[i - s] + j + 1);
                }
            }

            return f;
        }();

        return f[n];
    }
};
```

#### Go

```go
const mx = 100001

var f = func() []int {
	f := make([]int, mx)

	for i := range f {
		f[i] = int(^uint(0) >> 1)
	}

	f[0] = -1

	for i := 1; i < mx; i++ {
		for j := 1; j*(j+1)/2 <= i; j++ {
			s := j * (j + 1) / 2
			f[i] = min(f[i], f[i-s]+j+1)
		}
	}

	return f
}()

func minDays(n int) int {
	return f[n]
}
```

#### TypeScript

```ts
const MX = 100001;

const f = new Array<number>(MX).fill(Infinity);

f[0] = -1;

for (let i = 1; i < MX; i++) {
    for (let j = 1; (j * (j + 1)) / 2 <= i; j++) {
        const s = (j * (j + 1)) / 2;
        f[i] = Math.min(f[i], f[i - s] + j + 1);
    }
}

function minDays(n: number): number {
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
