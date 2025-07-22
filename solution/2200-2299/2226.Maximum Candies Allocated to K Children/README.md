---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2226.Maximum%20Candies%20Allocated%20to%20K%20Children/README.md
rating: 1646
source: 第 287 场周赛 Q3
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [2226. 每个小孩最多能分到多少糖果](https://leetcode.cn/problems/maximum-candies-allocated-to-k-children)

[English Version](/solution/2200-2299/2226.Maximum%20Candies%20Allocated%20to%20K%20Children/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>下标从 0 开始</strong> 的整数数组 <code>candies</code> 。数组中的每个元素表示大小为 <code>candies[i]</code> 的一堆糖果。你可以将每堆糖果分成任意数量的 <strong>子堆</strong> ，但 <strong>无法</strong> 再将两堆合并到一起。</p>

<p>另给你一个整数 <code>k</code> 。你需要将这些糖果分配给 <code>k</code> 个小孩，使每个小孩分到 <strong>相同</strong> 数量的糖果。每个小孩可以拿走 <strong>至多一堆</strong> 糖果，有些糖果可能会不被分配。</p>

<p>返回每个小孩可以拿走的 <strong>最大糖果数目</strong><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>candies = [5,8,6], k = 3
<strong>输出：</strong>5
<strong>解释：</strong>可以将 candies[1] 分成大小分别为 5 和 3 的两堆，然后把 candies[2] 分成大小分别为 5 和 1 的两堆。现在就有五堆大小分别为 5、5、3、5 和 1 的糖果。可以把 3 堆大小为 5 的糖果分给 3 个小孩。可以证明无法让每个小孩得到超过 5 颗糖果。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>candies = [2,5], k = 11
<strong>输出：</strong>0
<strong>解释：</strong>总共有 11 个小孩，但只有 7 颗糖果，但如果要分配糖果的话，必须保证每个小孩至少能得到 1 颗糖果。因此，最后每个小孩都没有得到糖果，答案是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= candies.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candies[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>12</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，如果每个小孩能分到糖果数 $v$，那么对于任意 $v' \lt v$，每个小孩也能分到 $v'$ 颗糖果。因此，我们可以使用二分查找的方法找到最大的 $v$，使得每个小孩能分到 $v$ 颗糖果。

我们定义二分查找的左边界 $l = 0$，右边界 $r = \max(\text{candies})$，其中 $\max(\text{candies})$ 表示数组 $\text{candies}$ 中的最大值。在二分查找的过程中，我们每次取 $v$ 的中间值 $v = \left\lfloor \frac{l + r + 1}{2} \right\rfloor$，然后计算每个小孩能分到的糖果数 $v$ 的总和，如果总和大于等于 $k$，则说明每个小孩能分到 $v$ 颗糖果，此时我们更新左边界 $l = v$，否则我们更新右边界 $r = v - 1$。最终，当 $l = r$ 时，我们找到了最大的 $v$。

时间复杂度 $O(n \times \log M)$，其中 $n$ 表示数组 $\text{candies}$ 的长度，而 $M$ 表示数组 $\text{candies}$ 中的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumCandies(self, candies: List[int], k: int) -> int:
        l, r = 0, max(candies)
        while l < r:
            mid = (l + r + 1) >> 1
            if sum(x // mid for x in candies) >= k:
                l = mid
            else:
                r = mid - 1
        return l
```

#### Java

```java
class Solution {
    public int maximumCandies(int[] candies, long k) {
        int l = 0, r = Arrays.stream(candies).max().getAsInt();
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            long cnt = 0;
            for (int x : candies) {
                cnt += x / mid;
            }
            if (cnt >= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumCandies(vector<int>& candies, long long k) {
        int l = 0, r = ranges::max(candies);
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            long long cnt = 0;
            for (int x : candies) {
                cnt += x / mid;
            }
            if (cnt >= k) {
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
func maximumCandies(candies []int, k int64) int {
	return sort.Search(1e7, func(v int) bool {
		v++
		var cnt int64
		for _, x := range candies {
			cnt += int64(x / v)
		}
		return cnt < k
	})
}
```

#### TypeScript

```ts
function maximumCandies(candies: number[], k: number): number {
    let [l, r] = [0, Math.max(...candies)];
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        const cnt = candies.reduce((acc, cur) => acc + Math.floor(cur / mid), 0);
        if (cnt >= k) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
