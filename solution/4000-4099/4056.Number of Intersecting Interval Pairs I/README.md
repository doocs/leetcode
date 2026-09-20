---
comments: true
difficulty: 简单
source: 第 520 场周赛 Q1
---

<!-- problem:start -->

# [4056. 统计相交区间对 I](https://leetcode.cn/problems/number-of-intersecting-interval-pairs-i)

[English Version](/solution/4000-4099/4056.Number%20of%20Intersecting%20Interval%20Pairs%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个包含 <code>n</code> 个元素的二维整数数组 <code>intervals</code>，其中 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 表示从 <code>start<sub>i</sub></code> 到 <code>end<sub>i</sub></code> 的<strong>&nbsp;闭区间</strong>&nbsp;。</p>

<p>返回满足 <code>0 &lt;= i &lt; j &lt; n</code>，且 <code>intervals[i]</code> 与 <code>intervals[j]</code> <strong>相交</strong>&nbsp;的下标对 <code>(i, j)</code> 的数量。</p>

<p>如果两个区间至少有一个公共点，则称它们&nbsp;<strong>相交</strong>。仅共享一个端点的情况也视为相交。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">intervals = [[1,2],[2,3],[3,4]]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>共有 2 对相交区间：</p>

<ul>
	<li>区间 <code>[1, 2]</code> 和 <code>[2, 3]</code> 在点 2 处相交。</li>
	<li>区间 <code>[2, 3]</code> 和 <code>[3, 4]</code> 在点 3 处相交。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">intervals = [[1,5],[2,4],[3,6]]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>共有 3 对相交区间：</p>

<ul>
	<li><code>[1, 5]</code> 和 <code>[2, 4]</code> 的交集为 <code>[2, 4]</code>。</li>
	<li><code>[1, 5]</code> 和 <code>[3, 6]</code> 的交集为 <code>[3, 5]</code>。</li>
	<li><code>[2, 4]</code> 和 <code>[3, 6]</code> 的交集为 <code>[3, 4]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">intervals = [[1,2],[3,4],[5,6]]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不存在相交的区间对。因此，答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= intervals.length &lt;= 100</code></li>
	<li><code>intervals[i] == [start<sub>i</sub>, end<sub>i</sub>]</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 双指针

<!-- thinking:start -->

> **思考**
>
> $n \le 100$，枚举全部下标对判断是否相交就能通过。两个闭区间不相交，当且仅当其中一个的右端点严格小于另一个的左端点。
>
> 直接枚举要对每个对写两端判断。从 $\frac{n(n-1)}{2}$ 里减去不相交对更干净：对每个左端点，统计有多少个区间已经在它开始之前结束。
>
> 左右端点分别排序后，指针只向右移动，一次扫描就能得到每个起点对应的不相交个数。

<!-- thinking:end -->

两个闭区间 $[l_1, r_1]$、$[l_2, r_2]$ 不相交，当且仅当 $r_1 < l_2$ 或 $r_2 < l_1$。

区间对的总数为 $\frac{n(n-1)}{2}$。我们统计不相交的对数，再从总数中减去。

将所有左端点、右端点分别升序排列。从左到右枚举每个左端点 $s$，用指针 $i$ 维护满足 $\textit{ends}[i] < s$ 的区间个数，这些区间与当前区间不相交，从答案中减去。

每个不相交对恰好被统计一次：右端点更小的那个区间，会在扫描另一个区间的左端点时被计入。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是区间的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countIntersectingIntervals(self, intervals: list[list[int]]) -> int:
        n = len(intervals)
        starts = sorted(s for s, _ in intervals)
        ends = sorted(e for _, e in intervals)
        ans = n * (n - 1) // 2
        i = 0
        for start in starts:
            while i < n and ends[i] < start:
                i += 1
            ans -= i
        return ans
```

#### Java

```java
class Solution {
    public int countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int ans = n * (n - 1) / 2;
        int i = 0;
        for (int start : starts) {
            while (i < n && ends[i] < start) {
                i++;
            }
            ans -= i;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countIntersectingIntervals(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<int> starts(n), ends(n);
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        sort(starts.begin(), starts.end());
        sort(ends.begin(), ends.end());
        int ans = n * (n - 1) / 2;
        int i = 0;
        for (int start : starts) {
            while (i < n && ends[i] < start) {
                i++;
            }
            ans -= i;
        }
        return ans;
    }
};
```

#### Go

```go
func countIntersectingIntervals(intervals [][]int) int {
	n := len(intervals)
	starts := make([]int, n)
	ends := make([]int, n)
	for i, p := range intervals {
		starts[i] = p[0]
		ends[i] = p[1]
	}
	slices.Sort(starts)
	slices.Sort(ends)
	ans := n * (n - 1) / 2
	i := 0
	for _, start := range starts {
		for i < n && ends[i] < start {
			i++
		}
		ans -= i
	}
	return ans
}
```

#### TypeScript

```ts
function countIntersectingIntervals(intervals: number[][]): number {
    const n = intervals.length;
    const starts = intervals.map(([s]) => s).sort((a, b) => a - b);
    const ends = intervals.map(([, e]) => e).sort((a, b) => a - b);
    let ans = (n * (n - 1)) / 2;
    let i = 0;
    for (const start of starts) {
        while (i < n && ends[i] < start) {
            i++;
        }
        ans -= i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
