---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1552.Magnetic%20Force%20Between%20Two%20Balls/README.md
rating: 1919
source: 第 202 场周赛 Q3
tags:
    - 数组
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [1552. 两球之间的磁力](https://leetcode.cn/problems/magnetic-force-between-two-balls)

[English Version](/solution/1500-1599/1552.Magnetic%20Force%20Between%20Two%20Balls/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有&nbsp;<code>n</code>&nbsp;个空的篮子，第&nbsp;<code>i</code>&nbsp;个篮子的位置在&nbsp;<code>position[i]</code>&nbsp;，Morty&nbsp;想把&nbsp;<code>m</code>&nbsp;个球放到这些篮子里，使得任意两球间&nbsp;<strong>最小磁力</strong>&nbsp;最大。</p>

<p>已知两个球如果分别位于&nbsp;<code>x</code>&nbsp;和&nbsp;<code>y</code>&nbsp;，那么它们之间的磁力为&nbsp;<code>|x - y|</code>&nbsp;。</p>

<p>给你一个整数数组&nbsp;<code>position</code>&nbsp;和一个整数&nbsp;<code>m</code>&nbsp;，请你返回最大化的最小磁力。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1552.Magnetic%20Force%20Between%20Two%20Balls/images/q3v1.jpg" style="height: 195px; width: 562px;"></p>

<pre><strong>输入：</strong>position = [1,2,3,4,7], m = 3
<strong>输出：</strong>3
<strong>解释：</strong>将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>position = [5,4,3,2,1,1000000000], m = 2
<strong>输出：</strong>999999999
<strong>解释：</strong>我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == position.length</code></li>
	<li><code>2 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= position[i] &lt;= 10^9</code></li>
	<li>所有&nbsp;<code>position</code>&nbsp;中的整数 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>2 &lt;= m &lt;= position.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，任意两球间的最小磁力越大，能够放入的球的数量就越少，这存在着单调性。我们可以使用二分查找，找到最大的最小磁力，使得能够放入的球的数量不小于 $m$。

我们首先对篮子的位置进行排序，然后使用二分查找的方法，定义二分查找的左边界 $l = 1$，右边界 $r = \textit{position}[n - 1]$，其中 $n$ 为篮子的数量。在每次二分查找的过程中，我们计算取中值 $m = (l + r + 1) / 2$，然后判断是否存在一种放置球的方法，使得能够放入的球的数量不小于 $m$。

问题转换为判断一个给定的最小磁力 $f$ 是否能够放入 $m$ 个球。我们可以从左到右遍历篮子的位置，如果上一个球的位置与当前篮子的位置的距离大于等于 $f$，则说明可以在当前篮子放置一个球。最后判断放置的球的数量是否不小于 $m$ 即可。

时间复杂度 $O(n \times \log n + n \times \log M)$，空间复杂度 $O(\log n)$。其中 $n$ 和 $M$ 分别为篮子的数量和篮子的位置的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, position: List[int], m: int) -> int:
        def check(f: int) -> bool:
            prev = -inf
            cnt = 0
            for curr in position:
                if curr - prev >= f:
                    prev = curr
                    cnt += 1
            return cnt < m

        position.sort()
        l, r = 1, position[-1]
        return bisect_left(range(l, r + 1), True, key=check)
```

#### Java

```java
class Solution {
    private int[] position;

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        this.position = position;
        int l = 1, r = position[position.length - 1];
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (count(mid) >= m) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private int count(int f) {
        int prev = position[0];
        int cnt = 1;
        for (int curr : position) {
            if (curr - prev >= f) {
                ++cnt;
                prev = curr;
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDistance(vector<int>& position, int m) {
        ranges::sort(position);
        int l = 1, r = position.back();
        auto count = [&](int f) {
            int prev = position[0];
            int cnt = 1;
            for (int& curr : position) {
                if (curr - prev >= f) {
                    prev = curr;
                    cnt++;
                }
            }
            return cnt;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (count(mid) >= m) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func maxDistance(position []int, m int) int {
	sort.Ints(position)
	return sort.Search(position[len(position)-1], func(f int) bool {
		prev := position[0]
		cnt := 1
		for _, curr := range position {
			if curr-prev >= f {
				cnt++
				prev = curr
			}
		}
		return cnt < m
	}) - 1
}
```

#### TypeScript

```ts
function maxDistance(position: number[], m: number): number {
    position.sort((a, b) => a - b);
    let [l, r] = [1, position.at(-1)!];
    const count = (f: number): number => {
        let cnt = 1;
        let prev = position[0];
        for (const curr of position) {
            if (curr - prev >= f) {
                cnt++;
                prev = curr;
            }
        }
        return cnt;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (count(mid) >= m) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

#### JavaScript

```js
/**
 * @param {number[]} position
 * @param {number} m
 * @return {number}
 */
var maxDistance = function (position, m) {
    position.sort((a, b) => a - b);
    let [l, r] = [1, position.at(-1)];
    const count = f => {
        let cnt = 1;
        let prev = position[0];
        for (const curr of position) {
            if (curr - prev >= f) {
                cnt++;
                prev = curr;
            }
        }
        return cnt;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (count(mid) >= m) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
