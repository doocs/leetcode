# [875. 爱吃香蕉的珂珂](https://leetcode.cn/problems/koko-eating-bananas)

[English Version](/solution/0800-0899/0875.Koko%20Eating%20Bananas/README_EN.md)

<!-- tags:数组,二分查找 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>珂珂喜欢吃香蕉。这里有 <code>n</code> 堆香蕉，第 <code>i</code> 堆中有&nbsp;<code>piles[i]</code>&nbsp;根香蕉。警卫已经离开了，将在 <code>h</code> 小时后回来。</p>

<p>珂珂可以决定她吃香蕉的速度 <code>k</code> （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 <code>k</code> 根。如果这堆香蕉少于 <code>k</code> 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。&nbsp;&nbsp;</p>

<p>珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。</p>

<p>返回她可以在 <code>h</code> 小时内吃掉所有香蕉的最小速度 <code>k</code>（<code>k</code> 为整数）。</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [3,6,7,11], h = 8
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>piles = [30,11,23,4,20], h = 5
<strong>输出：</strong>30
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>piles = [30,11,23,4,20], h = 6
<strong>输出：</strong>23
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10<sup>4</sup></code></li>
	<li><code>piles.length &lt;= h &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：二分查找

我们注意到，如果珂珂能够以 $k$ 的速度在 $h$ 小时内吃完所有香蕉，那么她也可以以 $k' > k$ 的速度在 $h$ 小时内吃完所有香蕉。这存在着单调性，因此我们可以使用二分查找，找到最小的满足条件的 $k$。

我们定义二分查找的左边界 $l = 1$，右边界 $r = \max(\text{piles})$。每一次二分，我们取中间值 $mid = \frac{l + r}{2}$，然后计算以 $mid$ 的速度吃香蕉需要的时间 $s$。如果 $s \leq h$，说明 $mid$ 的速度可以满足条件，我们将右边界 $r$ 更新为 $mid$；否则，我们将左边界 $l$ 更新为 $mid + 1$。最终，当 $l = r$ 时，我们找到了最小的满足条件的 $k$。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组 `piles` 的长度和最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        def check(k: int) -> bool:
            return sum((x + k - 1) // k for x in piles) <= h

        return 1 + bisect_left(range(1, max(piles) + 1), True, key=check)
```

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = (int) 1e9;
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            for (int x : piles) {
                s += (x + mid - 1) / mid;
            }
            if (s <= h) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int minEatingSpeed(vector<int>& piles, int h) {
        int l = 1, r = ranges::max(piles);
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            for (int x : piles) {
                s += (x + mid - 1) / mid;
            }
            if (s <= h) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

```go
func minEatingSpeed(piles []int, h int) int {
	return 1 + sort.Search(slices.Max(piles), func(k int) bool {
		k++
		s := 0
		for _, x := range piles {
			s += (x + k - 1) / k
		}
		return s <= h
	})
}
```

```ts
function minEatingSpeed(piles: number[], h: number): number {
    let [l, r] = [1, Math.max(...piles)];
    while (l < r) {
        const mid = (l + r) >> 1;
        const s = piles.map(x => Math.ceil(x / mid)).reduce((a, b) => a + b);
        if (s <= h) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

```rust
impl Solution {
    pub fn min_eating_speed(piles: Vec<i32>, h: i32) -> i32 {
        let mut l = 1;
        let mut r = *piles.iter().max().unwrap_or(&0);
        while l < r {
            let mid = (l + r) >> 1;
            let mut s = 0;
            for x in piles.iter() {
                s += (x + mid - 1) / mid;
            }
            if s <= h {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
```

```cs
public class Solution {
    public int MinEatingSpeed(int[] piles, int h) {
        int l = 1, r = (int) 1e9;
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            foreach (int x in piles) {
                s += (x + mid - 1) / mid;
            }
            if (s <= h) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

<!-- tabs:end -->

<!-- end -->
