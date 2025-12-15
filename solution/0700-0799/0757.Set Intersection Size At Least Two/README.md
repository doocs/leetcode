---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0757.Set%20Intersection%20Size%20At%20Least%20Two/README.md
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [757. 设置交集大小至少为2](https://leetcode.cn/problems/set-intersection-size-at-least-two)

[English Version](/solution/0700-0799/0757.Set%20Intersection%20Size%20At%20Least%20Two/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>intervals</code> ，其中 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 表示从 <code>start<sub>i</sub></code> 到 <code>end<sub>i</sub></code> 的所有整数，包括 <code>start<sub>i</sub></code> 和 <code>end<sub>i</sub></code> 。</p>

<p><strong>包含集合</strong> 是一个名为 <code>nums</code> 的数组，并满足 <code>intervals</code> 中的每个区间都 <strong>至少</strong> 有 <strong>两个</strong> 整数在 <code>nums</code> 中。</p>

<ul>
	<li>例如，如果 <code>intervals = [[1,3], [3,7], [8,9]]</code> ，那么 <code>[1,2,4,7,8,9]</code> 和 <code>[2,3,4,8,9]</code> 都符合 <strong>包含集合</strong> 的定义。</li>
</ul>

<p>返回包含集合可能的最小大小。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[3,7],[8,9]]
<strong>输出：</strong>5
<strong>解释：</strong>nums = [2, 3, 4, 8, 9].
可以证明不存在元素数量为 4 的包含集合。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[1,4],[2,5],[3,5]]
<strong>输出：</strong>3
<strong>解释：</strong>nums = [2, 3, 4].
可以证明不存在元素数量为 2 的包含集合。 
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,2],[2,3],[2,4],[4,5]]
<strong>输出：</strong>5
<strong>解释：</strong>nums = [1, 2, 3, 4, 5].
可以证明不存在元素数量为 4 的包含集合。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 3000</code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心

我们希望在数轴上选出尽可能少的整数点，使得每个区间都至少包含两个点。一个经典而有效的策略是按照区间的右端点进行排序，并尽量让已选取的点位于区间的右侧，以便这些点能覆盖更多后续区间。

首先将所有区间按照如下规则排序：

1. 按右端点从小到大；
2. 若右端点相同，按左端点从大到小。

这样排序的原因是：右端点越小的区间“可操作空间”越少，应优先满足；当右端点相同时，左端点更大的区间更窄，更应优先被处理。

随后，我们使用两个变量 $s$ 和 $e$ 分别记录当前所有已处理区间所共同拥有的 **倒数第二个点** 和 **最后一个点**。初始时 $s = e = -1$，表示还没有放置任何点。

接下来依次处理排序后的区间 $[a, b]$，根据它与 $\{s, e\}$ 的关系分三种情况讨论：

1. **若 $a \leq s$**：
   当前区间已包含 $s$ 和 $e$ 两个点，无需额外放点。

2. **若 $s < a \leq e$**：
   当前区间只包含一个点（即 $e$），还需要补一个点。为了让新点对后续区间最有帮助，我们选择在区间最右侧的点 $b$。此时更新 $\textit{ans} = \textit{ans} + 1$，并将新的两点设为 $\{e, b\}$。

3. **若 $a > e$**：
   当前区间完全不包含已有的两个点，需要补两个点。最优选择是在区间最右侧放置 $\{b - 1, b\}$。此时更新 $\textit{ans} = \textit{ans} + 2$，并将新的两点设为 $\{b - 1, b\}$。

最终返回总共放置的点数 $\textit{ans}$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为区间的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def intersectionSizeTwo(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[1], -x[0]))
        s = e = -1
        ans = 0
        for a, b in intervals:
            if a <= s:
                continue
            if a > e:
                ans += 2
                s, e = b - 1, b
            else:
                ans += 1
                s, e = e, b
        return ans
```

#### Java

```java
class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
        int ans = 0;
        int s = -1, e = -1;
        for (int[] v : intervals) {
            int a = v[0], b = v[1];
            if (a <= s) {
                continue;
            }
            if (a > e) {
                ans += 2;
                s = b - 1;
                e = b;
            } else {
                ans += 1;
                s = e;
                e = b;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int intersectionSizeTwo(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [&](vector<int>& a, vector<int>& b) {
            return a[1] == b[1] ? a[0] > b[0] : a[1] < b[1];
        });
        int ans = 0;
        int s = -1, e = -1;
        for (auto& v : intervals) {
            int a = v[0], b = v[1];
            if (a <= s) continue;
            if (a > e) {
                ans += 2;
                s = b - 1;
                e = b;
            } else {
                ans += 1;
                s = e;
                e = b;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func intersectionSizeTwo(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool {
		a, b := intervals[i], intervals[j]
		if a[1] == b[1] {
			return a[0] > b[0]
		}
		return a[1] < b[1]
	})
	ans := 0
	s, e := -1, -1
	for _, v := range intervals {
		a, b := v[0], v[1]
		if a <= s {
			continue
		}
		if a > e {
			ans += 2
			s, e = b-1, b
		} else {
			ans += 1
			s, e = e, b
		}
	}
	return ans
}
```

#### TypeScript

```ts
function intersectionSizeTwo(intervals: number[][]): number {
    intervals.sort((a, b) => (a[1] !== b[1] ? a[1] - b[1] : b[0] - a[0]));
    let s = -1;
    let e = -1;
    let ans = 0;
    for (const [a, b] of intervals) {
        if (a <= s) {
            continue;
        }
        if (a > e) {
            ans += 2;
            s = b - 1;
            e = b;
        } else {
            ans += 1;
            s = e;
            e = b;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
