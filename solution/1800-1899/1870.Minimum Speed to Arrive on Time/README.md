---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1870.Minimum%20Speed%20to%20Arrive%20on%20Time/README.md
rating: 1675
source: 第 242 场周赛 Q2
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [1870. 准时到达的列车最小时速](https://leetcode.cn/problems/minimum-speed-to-arrive-on-time)

[English Version](/solution/1800-1899/1870.Minimum%20Speed%20to%20Arrive%20on%20Time/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个浮点数 <code>hour</code> ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 <code>n</code> 趟列车。另给你一个长度为 <code>n</code> 的整数数组 <code>dist</code> ，其中 <code>dist[i]</code> 表示第 <code>i</code> 趟列车的行驶距离（单位是千米）。</p>

<p>每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。</p>

<ul>
	<li>例如，第 <code>1</code> 趟列车需要 <code>1.5</code> 小时，那你必须再等待 <code>0.5</code> 小时，搭乘在第 2 小时发车的第 <code>2</code> 趟列车。</li>
</ul>

<p>返回能满足你准时到达办公室所要求全部列车的<strong> 最小正整数 </strong>时速（单位：千米每小时），如果无法准时到达，则返回 <code>-1</code> 。</p>

<p>生成的测试用例保证答案不超过 <code>10<sup>7</sup></code> ，且 <code>hour</code> 的 <strong>小数点后最多存在两位数字</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,3,2], hour = 6
<strong>输出：</strong>1
<strong>解释：</strong>速度为 1 时：
- 第 1 趟列车运行需要 1/1 = 1 小时。
- 由于是在整数时间到达，可以立即换乘在第 1 小时发车的列车。第 2 趟列车运行需要 3/1 = 3 小时。
- 由于是在整数时间到达，可以立即换乘在第 4 小时发车的列车。第 3 趟列车运行需要 2/1 = 2 小时。
- 你将会恰好在第 6 小时到达。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,3,2], hour = 2.7
<strong>输出：</strong>3
<strong>解释：</strong>速度为 3 时：
- 第 1 趟列车运行需要 1/3 = 0.33333 小时。
- 由于不是在整数时间到达，故需要等待至第 1 小时才能搭乘列车。第 2 趟列车运行需要 3/3 = 1 小时。
- 由于是在整数时间到达，可以立即换乘在第 2 小时发车的列车。第 3 趟列车运行需要 2/3 = 0.66667 小时。
- 你将会在第 2.66667 小时到达。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,3,2], hour = 1.9
<strong>输出：</strong>-1
<strong>解释：</strong>不可能准时到达，因为第 3 趟列车最早是在第 2 小时发车。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == dist.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= dist[i] <= 10<sup>5</sup></code></li>
	<li><code>1 <= hour <= 10<sup>9</sup></code></li>
	<li><code>hours</code> 中，小数点后最多存在两位数字</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，如果一个速度值 $v$ 能够使得我们在规定时间内到达，那么对于任意 $v' > v$，我们也一定能在规定时间内到达。这存在着单调性，因此我们可以使用二分查找，找到最小的满足条件的速度值。

在二分查找之前，我们需要先判断是否有可能在规定时间内到达。如果列车数量大于向上取整的规定时间，那么一定无法在规定时间内到达，直接返回 $-1$。

接下来，我们定义二分的左右边界为 $l = 1$, $r = 10^7 + 1$，然后我们每次取中间值 $\textit{mid} = \frac{l + r}{2}$，判断是否满足条件。如果满足条件，我们将右边界移动到 $\textit{mid}$，否则将左边界移动到 $\textit{mid} + 1$。

问题转化为判断一个速度值 $v$ 是否能够在规定时间内到达。我们可以遍历每一趟列车，计算每一趟列车的运行时间 $t = \frac{d}{v}$，如果是最后一趟列车，我们直接加上 $t$，否则我们向上取整加上 $t$。最后判断总时间是否小于等于规定时间，如果是则说明满足条件。

二分结束后，如果左边界超过了 $10^7$，说明无法在规定时间内到达，返回 $-1$，否则返回左边界。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别为列车数量和速度的上界。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def check(v: int) -> bool:
            s = 0
            for i, d in enumerate(dist):
                t = d / v
                s += t if i == len(dist) - 1 else ceil(t)
            return s <= hour

        if len(dist) > ceil(hour):
            return -1
        r = 10**7 + 1
        ans = bisect_left(range(1, r), True, key=check) + 1
        return -1 if ans == r else ans
```

#### Java

```java
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }
        final int m = (int) 1e7;
        int l = 1, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(dist, mid, hour)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }

    private boolean check(int[] dist, int v, double hour) {
        double s = 0;
        int n = dist.length;
        for (int i = 0; i < n; ++i) {
            double t = dist[i] * 1.0 / v;
            s += i == n - 1 ? t : Math.ceil(t);
        }
        return s <= hour;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSpeedOnTime(vector<int>& dist, double hour) {
        if (dist.size() > ceil(hour)) {
            return -1;
        }
        const int m = 1e7;
        int l = 1, r = m + 1;
        int n = dist.size();
        auto check = [&](int v) {
            double s = 0;
            for (int i = 0; i < n; ++i) {
                double t = dist[i] * 1.0 / v;
                s += i == n - 1 ? t : ceil(t);
            }
            return s <= hour;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }
};
```

#### Go

```go
func minSpeedOnTime(dist []int, hour float64) int {
	if float64(len(dist)) > math.Ceil(hour) {
		return -1
	}
	const m int = 1e7
	n := len(dist)
	ans := sort.Search(m+1, func(v int) bool {
		v++
		s := 0.0
		for i, d := range dist {
			t := float64(d) / float64(v)
			if i == n-1 {
				s += t
			} else {
				s += math.Ceil(t)
			}
		}
		return s <= hour
	}) + 1
	if ans > m {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minSpeedOnTime(dist: number[], hour: number): number {
    if (dist.length > Math.ceil(hour)) {
        return -1;
    }
    const n = dist.length;
    const m = 10 ** 7;
    const check = (v: number): boolean => {
        let s = 0;
        for (let i = 0; i < n; ++i) {
            const t = dist[i] / v;
            s += i === n - 1 ? t : Math.ceil(t);
        }
        return s <= hour;
    };
    let [l, r] = [1, m + 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > m ? -1 : l;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_speed_on_time(dist: Vec<i32>, hour: f64) -> i32 {
        if dist.len() as f64 > hour.ceil() {
            return -1;
        }
        const M: i32 = 10_000_000;
        let (mut l, mut r) = (1, M + 1);
        let n = dist.len();
        let check = |v: i32| -> bool {
            let mut s = 0.0;
            for i in 0..n {
                let t = dist[i] as f64 / v as f64;
                s += if i == n - 1 { t } else { t.ceil() };
            }
            s <= hour
        };
        while l < r {
            let mid = (l + r) / 2;
            if check(mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if l > M {
            -1
        } else {
            l
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} dist
 * @param {number} hour
 * @return {number}
 */
var minSpeedOnTime = function (dist, hour) {
    if (dist.length > Math.ceil(hour)) {
        return -1;
    }
    const n = dist.length;
    const m = 10 ** 7;
    const check = v => {
        let s = 0;
        for (let i = 0; i < n; ++i) {
            const t = dist[i] / v;
            s += i === n - 1 ? t : Math.ceil(t);
        }
        return s <= hour;
    };
    let [l, r] = [1, m + 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > m ? -1 : l;
};
```

#### Kotlin

```kotlin
class Solution {
    fun minSpeedOnTime(dist: IntArray, hour: Double): Int {
        val n = dist.size
        if (n > Math.ceil(hour)) {
            return -1
        }
        val m = 1e7.toInt()
        var left = 1
        var right = m + 1
        while (left < right) {
            val middle = (left + right) / 2
            var time = 0.0
            dist.forEachIndexed { i, item ->
                val t = item.toDouble() / middle
                time += if (i == n - 1) t else Math.ceil(t)
            }
            if (time > hour) {
                left = middle + 1
            } else {
                right = middle
            }
        }
        return if (left > m) -1 else left
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
