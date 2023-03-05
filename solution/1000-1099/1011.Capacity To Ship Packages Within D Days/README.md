# [1011. 在 D 天内送达包裹的能力](https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days)

[English Version](/solution/1000-1099/1011.Capacity%20To%20Ship%20Packages%20Within%20D%20Days/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>传送带上的包裹必须在 <code>days</code> 天内从一个港口运送到另一个港口。</p>

<p>传送带上的第 <code>i</code>&nbsp;个包裹的重量为&nbsp;<code>weights[i]</code>。每一天，我们都会按给出重量（<code>weights</code>）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。</p>

<p>返回能在 <code>days</code> 天内将传送带上的所有包裹送达的船的最低运载能力。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>weights = [1,2,3,4,5,6,7,8,9,10], days = 5
<strong>输出：</strong>15
<strong>解释：</strong>
船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
第 1 天：1, 2, 3, 4, 5
第 2 天：6, 7
第 3 天：8
第 4 天：9
第 5 天：10

请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>weights = [3,2,2,4,1,4], days = 3
<strong>输出：</strong>6
<strong>解释：</strong>
船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
第 1 天：3, 2
第 2 天：2, 4
第 3 天：1, 4
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>weights = [1,2,3,1,1], days = 4
<strong>输出：</strong>3
<strong>解释：</strong>
第 1 天：1
第 2 天：2
第 3 天：3
第 4 天：1, 1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= days &lt;= weights.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= weights[i] &lt;= 500</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们注意到，如果运载能力 $x$ 能够在 $days$ 天内运送完所有包裹，那么运载能力 $x + 1$ 也能在 $days$ 天内运送完所有包裹。也即是说，随着运载能力的增加，运送天数只会减少，不会增加。这存在一个单调性，因此我们可以使用二分查找的方法来寻找最小的运载能力。

我们定义二分查找的左边界 $left= \max\limits_{i=0}^{n-1} weights[i]$，右边界 $right = \sum\limits_{i=0}^{n-1} weights[i]$。然后二分枚举运载能力 $x$，判断是否能在 $days$ 天内运送完所有包裹。如果能，那么我们将右边界调整为 $x$，否则将左边界调整为 $x + 1$。

判断是否能在 $days$ 天内运送完所有包裹的方法是，我们从左到右遍历包裹，将当前包裹加入当前运载能力的船上，如果当前船的运载能力超过了 $x$，那么我们将当前包裹放到下一天的船上，同时天数加一。如果天数超过了 $days$，那么我们返回 $false$，否则返回 $true$。

时间复杂度 $O(n \times \log \sum\limits_{i=0}^{n-1} weights[i])$，空间复杂度 $O(1)$。其中 $n$ 为包裹数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shipWithinDays(self, weights: List[int], days: int) -> int:
        def check(mx):
            ws, cnt = 0, 1
            for w in weights:
                ws += w
                if ws > mx:
                    cnt += 1
                    ws = w
            return cnt <= days

        left, right = max(weights), sum(weights) + 1
        return left + bisect_left(range(left, right), True, key=check)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid, weights, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int mx, int[] weights, int days) {
        int ws = 0, cnt = 1;
        for (int w : weights) {
            ws += w;
            if (ws > mx) {
                ws = w;
                ++cnt;
            }
        }
        return cnt <= days;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shipWithinDays(vector<int>& weights, int days) {
        int left = 0, right = 0;
        for (auto& w : weights) {
            left = max(left, w);
            right += w;
        }
        auto check = [&](int mx) {
            int ws = 0, cnt = 1;
            for (auto& w : weights) {
                ws += w;
                if (ws > mx) {
                    ws = w;
                    ++cnt;
                }
            }
            return cnt <= days;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func shipWithinDays(weights []int, days int) int {
	var left, right int
	for _, w := range weights {
		if left < w {
			left = w
		}
		right += w
	}
	return left + sort.Search(right, func(mx int) bool {
		mx += left
		ws, cnt := 0, 1
		for _, w := range weights {
			ws += w
			if ws > mx {
				ws = w
				cnt++
			}
		}
		return cnt <= days
	})
}
```

### **TypeScript**

```ts
function shipWithinDays(weights: number[], days: number): number {
    let left = 0;
    let right = 0;
    for (const w of weights) {
        left = Math.max(left, w);
        right += w;
    }
    const check = (mx: number) => {
        let ws = 0;
        let cnt = 1;
        for (const w of weights) {
            ws += w;
            if (ws > mx) {
                ws = w;
                ++cnt;
            }
        }
        return cnt <= days;
    };
    while (left < right) {
        const mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
