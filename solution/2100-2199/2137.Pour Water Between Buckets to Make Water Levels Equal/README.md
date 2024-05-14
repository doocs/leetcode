---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2137.Pour%20Water%20Between%20Buckets%20to%20Make%20Water%20Levels%20Equal/README.md
tags:
    - 数组
    - 二分查找
---

# [2137. 通过倒水操作让所有的水桶所含水量相等 🔒](https://leetcode.cn/problems/pour-water-between-buckets-to-make-water-levels-equal)

[English Version](/solution/2100-2199/2137.Pour%20Water%20Between%20Buckets%20to%20Make%20Water%20Levels%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有 <code>n</code> 个水桶，每个水桶中所含的水量用一个 <b>下标从 0 开始</b>&nbsp;的数组 <code>buckets</code> 给出，第 <code>i</code> 个水桶中有 <code>buckets[i]</code> 升水。</p>

<p>你想让所有的水桶中所含的水量相同。你可以从一个水桶向其它任意一个水桶倒任意数量的水（可以不是整数）。但是，你每倒 <code>k</code> 升水，<strong>百分之</strong> <code>loss</code> 的水会洒掉。</p>

<p>请返回经过倒水操作，所有水桶中的水量相同时，每个水桶中的 <strong>最大</strong> 水量。如果你的答案和标准答案的误差不超过 <code>10<sup>-5</sup></code>，那么答案将被通过。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> buckets = [1,2,7], loss = 80
<strong>输出:</strong> 2.00000
<strong>解释:</strong> 从水桶 2 向水桶 0 倒 5 升水。
5 * 80% = 4 升水会洒掉，水桶 0 只会获得 5 - 4 = 1 升水。
此时所有的水桶中都含有 2 升水，所以返回 2。</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> buckets = [2,4,6], loss = 50
<strong>输出:</strong> 3.50000
<strong>解释:</strong> 从水桶 1 向水桶 0 倒 0.5 升水。
0.5 * 50% = 0.25 升水会洒掉，水桶 0 只会获得 0.5 - 0.25 = 0.25 升水。
此时, buckets = [2.25, 3.5, 6].

从水桶 2 向水桶 0 倒 2.5 升水。
2.5 * 50% = 1.25 升水会洒掉，水桶 0 只会获得 2.5 - 1.25 = 1.25 升水。
此时所有的水桶中都含有 3.5 升水，所以返回 3.5。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> buckets = [3,3,3,3], loss = 40
<strong>输出:</strong> 3.00000
<strong>解释:</strong> 所有的水桶已经含有相同的水量。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= buckets.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= buckets[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= loss &lt;= 99</code></li>
</ul>

## 解法

### 方法一：二分查找（浮点数二分）

我们注意到，如果一个水量 $x$ 满足条件，那么所有小于 $x$ 的水量也满足条件。因此我们可以使用二分查找的方法找到最大的满足条件的水量。

我们定义二分查找的左边界 $l=0$，右边界 $r=\max(buckets)$。每次二分查找时，我们取 $l$ 和 $r$ 的中点 $mid$，判断 $mid$ 是否满足条件。如果满足条件，那么我们将 $l$ 更新为 $mid$，否则我们将 $r$ 更新为 $m$。在二分查找结束后，最大的满足条件的水量即为 $l$。

问题的关键转换为如果判断一个水量 $v$ 是否满足条件。我们可以遍历所有水桶，对于每个水桶，如果其水量大于 $v$，那么需要倒出 $x-v$ 的水量；如果其水量小于 $v$，那么需要向其中倒入 $(v-x)\times\frac{100}{100-\textit{loss}}$ 的水量。如果倒出的水量大于等于倒入的水量，那么说明 $v$ 满足条件。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组 $buckets$ 的长度和最大值。二分查找的时间复杂度为 $O(\log M)$，每次二分查找需要遍历数组 $buckets$，时间复杂度为 $O(n)$。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def equalizeWater(self, buckets: List[int], loss: int) -> float:
        def check(v):
            a = b = 0
            for x in buckets:
                if x >= v:
                    a += x - v
                else:
                    b += (v - x) * 100 / (100 - loss)
            return a >= b

        l, r = 0, max(buckets)
        while r - l > 1e-5:
            mid = (l + r) / 2
            if check(mid):
                l = mid
            else:
                r = mid
        return l
```

```java
class Solution {
    public double equalizeWater(int[] buckets, int loss) {
        double l = 0, r = Arrays.stream(buckets).max().getAsInt();
        while (r - l > 1e-5) {
            double mid = (l + r) / 2;
            if (check(buckets, loss, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private boolean check(int[] buckets, int loss, double v) {
        double a = 0;
        double b = 0;
        for (int x : buckets) {
            if (x > v) {
                a += x - v;
            } else {
                b += (v - x) * 100 / (100 - loss);
            }
        }
        return a >= b;
    }
}
```

```cpp
class Solution {
public:
    double equalizeWater(vector<int>& buckets, int loss) {
        double l = 0, r = *max_element(buckets.begin(), buckets.end());
        auto check = [&](double v) {
            double a = 0, b = 0;
            for (int x : buckets) {
                if (x > v) {
                    a += x - v;
                } else {
                    b += (v - x) * 100 / (100 - loss);
                }
            }
            return a >= b;
        };
        while (r - l > 1e-5) {
            double mid = (l + r) / 2;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }
};
```

```go
func equalizeWater(buckets []int, loss int) float64 {
	check := func(v float64) bool {
		var a, b float64
		for _, x := range buckets {
			if float64(x) >= v {
				a += float64(x) - v
			} else {
				b += (v - float64(x)) * 100 / float64(100-loss)
			}
		}
		return a >= b
	}

	l, r := float64(0), float64(slices.Max(buckets))
	for r-l > 1e-5 {
		mid := (l + r) / 2
		if check(mid) {
			l = mid
		} else {
			r = mid
		}
	}
	return l
}
```

```ts
function equalizeWater(buckets: number[], loss: number): number {
    let l = 0;
    let r = Math.max(...buckets);
    const check = (v: number): boolean => {
        let [a, b] = [0, 0];
        for (const x of buckets) {
            if (x >= v) {
                a += x - v;
            } else {
                b += ((v - x) * 100) / (100 - loss);
            }
        }
        return a >= b;
    };
    while (r - l > 1e-5) {
        const mid = (l + r) / 2;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- end -->
