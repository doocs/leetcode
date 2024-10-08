---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3296.Minimum%20Number%20of%20Seconds%20to%20Make%20Mountain%20Height%20Zero/README.md
rating: 1694
source: 第 416 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 数学
    - 二分查找
    - 堆（优先队列）
---

<!-- problem:start -->

# [3296. 移山所需的最少秒数](https://leetcode.cn/problems/minimum-number-of-seconds-to-make-mountain-height-zero)

[English Version](/solution/3200-3299/3296.Minimum%20Number%20of%20Seconds%20to%20Make%20Mountain%20Height%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>mountainHeight</code> 表示山的高度。</p>

<p>同时给你一个整数数组 <code>workerTimes</code>，表示工人们的工作时间（单位：<strong>秒</strong>）。</p>

<p>工人们需要 <strong>同时 </strong>进行工作以 <strong>降低 </strong>山的高度。对于工人 <code>i</code> :</p>

<ul>
	<li>山的高度降低 <code>x</code>，需要花费 <code>workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x</code> 秒。例如：

    <ul>
    	<li>山的高度降低 1，需要 <code>workerTimes[i]</code> 秒。</li>
    	<li>山的高度降低 2，需要 <code>workerTimes[i] + workerTimes[i] * 2</code> 秒，依此类推。</li>
    </ul>
    </li>

</ul>

<p>返回一个整数，表示工人们使山的高度降低到 0 所需的 <strong>最少</strong> 秒数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">mountainHeight = 4, workerTimes = [2,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>将山的高度降低到 0 的一种方式是：</p>

<ul>
	<li>工人 0 将高度降低 1，花费 <code>workerTimes[0] = 2</code> 秒。</li>
	<li>工人 1 将高度降低 2，花费 <code>workerTimes[1] + workerTimes[1] * 2 = 3</code> 秒。</li>
	<li>工人 2 将高度降低 1，花费 <code>workerTimes[2] = 1</code> 秒。</li>
</ul>

<p>因为工人同时工作，所需的最少时间为 <code>max(2, 3, 1) = 3</code> 秒。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">mountainHeight = 10, workerTimes = [3,2,2,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>工人 0 将高度降低 2，花费 <code>workerTimes[0] + workerTimes[0] * 2 = 9</code> 秒。</li>
	<li>工人 1 将高度降低 3，花费 <code>workerTimes[1] + workerTimes[1] * 2 + workerTimes[1] * 3 = 12</code> 秒。</li>
	<li>工人 2 将高度降低 3，花费 <code>workerTimes[2] + workerTimes[2] * 2 + workerTimes[2] * 3 = 12</code> 秒。</li>
	<li>工人 3 将高度降低 2，花费 <code>workerTimes[3] + workerTimes[3] * 2 = 12</code> 秒。</li>
</ul>

<p>所需的最少时间为 <code>max(9, 12, 12, 12) = 12</code> 秒。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">mountainHeight = 5, workerTimes = [1]</span></p>

<p><strong>输出：</strong> <span class="example-io">15</span></p>

<p><strong>解释：</strong></p>

<p>这个示例中只有一个工人，所以答案是 <code>workerTimes[0] + workerTimes[0] * 2 + workerTimes[0] * 3 + workerTimes[0] * 4 + workerTimes[0] * 5 = 15</code> 秒。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= mountainHeight &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= workerTimes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= workerTimes[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，如果所有的工人能在 $t$ 秒内将山的高度降低到 $0$，那么对于任意 $t' > t$，工人们也能在 $t'$ 秒内将山的高度降低到 $0$。因此，我们可以使用二分查找的方法找到最小的 $t$，使得工人们能在 $t$ 秒内将山的高度降低到 $0$。

我们定义一个函数 $\textit{check}(t)$，表示工人们能否在 $t$ 秒内将山的高度降低到 $0$。具体地，我们遍历每一个工人，对于当前工人 $\textit{workerTimes}[i]$，假设他在 $t$ 秒内降低了 $h'$ 的高度，那么我们可以得到一个不等式：

<!-- (1+h')h'/2 wt <= t -->

$$
\left(1 + h'\right) \cdot \frac{h'}{2} \cdot \textit{workerTimes}[i] \leq t
$$

解不等式得到：

$$
h' \leq \left\lfloor \sqrt{\frac{2t}{\textit{workerTimes}[i]} + \frac{1}{4}} - \frac{1}{2} \right\rfloor
$$

我们可以将所有工人的 $h'$ 相加，得到总共降低的高度 $h$，如果 $h \geq \textit{mountainHeight}$，那么说明工人们能在 $t$ 秒内将山的高度降低到 $0$。

接下来，我们确定二分查找的左边界 $l = 1$，由于最少有一个工作，且每个工人的工作时间不超过 $10^6$，要想使山的高度降低到 $0$，最少需要 $(1 + \textit{mountainHeight}) \cdot \textit{mountainHeight} / 2 \cdot \textit{workerTimes}[i] \leq 10^{16}$ 秒，因此我们可以确定二分查找的右边界 $r = 10^{16}$。然后我们不断地将区间 $[l, r]$ 二分，直到 $l = r$，此时 $l$ 即为答案。

时间复杂度 $O(n \times \log M)$，其中 $n$ 为工人的数量，而 $M$ 为二分查找的右边界，本题中 $M = 10^{16}$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minNumberOfSeconds(self, mountainHeight: int, workerTimes: List[int]) -> int:
        def check(t: int) -> bool:
            h = 0
            for wt in workerTimes:
                h += int(sqrt(2 * t / wt + 1 / 4) - 1 / 2)
            return h >= mountainHeight

        return bisect_left(range(10**16), True, key=check)
```

#### Java

```java
class Solution {
    private int mountainHeight;
    private int[] workerTimes;

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        this.mountainHeight = mountainHeight;
        this.workerTimes = workerTimes;
        long l = 1, r = (long) 1e16;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(long t) {
        long h = 0;
        for (int wt : workerTimes) {
            h += (long) (Math.sqrt(t * 2.0 / wt + 0.25) - 0.5);
        }
        return h >= mountainHeight;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minNumberOfSeconds(int mountainHeight, vector<int>& workerTimes) {
        using ll = long long;
        ll l = 1, r = 1e16;
        auto check = [&](ll t) -> bool {
            ll h = 0;
            for (int& wt : workerTimes) {
                h += (long long) (sqrt(t * 2.0 / wt + 0.25) - 0.5);
            }
            return h >= mountainHeight;
        };
        while (l < r) {
            ll mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func minNumberOfSeconds(mountainHeight int, workerTimes []int) int64 {
	return int64(sort.Search(1e16, func(t int) bool {
		var h int64
		for _, wt := range workerTimes {
			h += int64(math.Sqrt(float64(t)*2.0/float64(wt)+0.25) - 0.5)
		}
		return h >= int64(mountainHeight)
	}))
}
```

#### TypeScript

```ts
function minNumberOfSeconds(mountainHeight: number, workerTimes: number[]): number {
    const check = (t: bigint): boolean => {
        let h = BigInt(0);
        for (const wt of workerTimes) {
            h += BigInt(Math.floor(Math.sqrt((Number(t) * 2.0) / wt + 0.25) - 0.5));
        }
        return h >= BigInt(mountainHeight);
    };

    let l = BigInt(1);
    let r = BigInt(1e16);

    while (l < r) {
        const mid = (l + r) >> BigInt(1);
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }

    return Number(l);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
