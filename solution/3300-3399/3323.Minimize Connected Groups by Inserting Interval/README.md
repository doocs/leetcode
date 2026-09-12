---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3323.Minimize%20Connected%20Groups%20by%20Inserting%20Interval/README.md
tags:
    - 数组
    - 二分查找
    - 排序
    - 滑动窗口
---

<!-- problem:start -->

# [3323. 通过插入区间最小化连通组 🔒](https://leetcode.cn/problems/minimize-connected-groups-by-inserting-interval)

[English Version](/solution/3300-3399/3323.Minimize%20Connected%20Groups%20by%20Inserting%20Interval/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 2 维数组&nbsp;<code>intervals</code>，其中&nbsp;<code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;表示区间&nbsp;<code>i</code>&nbsp;的开头和结尾。另外还给定一个整数&nbsp;<code>k</code>。</p>

<p>你必须向数组 <strong>恰好添加一个</strong>&nbsp;新的区间&nbsp;<code>[start<sub>new</sub>, end<sub>new</sub>]</code>&nbsp;使得：</p>

<ul>
	<li>新区间的长度，<code>end<sub>new</sub> - start<sub>new</sub></code>&nbsp;最多为&nbsp;<code>k</code>。</li>
	<li>在添加之后，<code>intervals</code>&nbsp;中 <strong>连通组</strong>&nbsp;的数量 <strong>最少</strong>。</li>
</ul>

<p>区间的 <strong>连通组</strong>&nbsp;是一起覆盖了从最小点到最大点的连续范围，中间没有间隙的区间的最大集合。下面是一些例子：</p>

<ul>
	<li>区间组&nbsp;<code>[[1, 2], [2, 5], [3, 3]]</code>&nbsp;是连通的，因为它们一起覆盖了 1 到 5 的范围，中间没有任何间隔。</li>
	<li>然而，区间组&nbsp;<code>[[1, 2], [3, 4]]</code>&nbsp;不是连通的，因为&nbsp;<code>(2, 3)</code>&nbsp;段没有被覆盖。</li>
</ul>

<p>返回在数组&nbsp;<strong>恰好添加一个</strong> 新区间后，连通组的 <strong>最小</strong> 数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>intervals = [[1,3],[5,6],[8,10]], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>在添加区间&nbsp;<code>[3, 5]</code>&nbsp;后，我们有两个连通组：<code>[[1, 3], [3, 5], [5, 6]]</code> 和&nbsp;<code>[[8, 10]]</code>。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>intervals = [[5,10],[1,1],[3,3]], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>在添加区间&nbsp;<code>[1, 1]</code>&nbsp;后，我们有三个连通组：<code>[[1, 1], [1, 1]]</code>，<code>[[3, 3]]</code>，和&nbsp;<code>[[5, 10]]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i] == [start<sub>i</sub>, end<sub>i</sub>]</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

<!-- thinking:start -->

> **思考**
>
> 插入一条长度至多为 $k$ 的区间，使合并后的连通块尽量少。$n \le 10^5$，需先把相交区间合并，再考虑一次插入能「桥接」多少段。
>
> 合并后各段互不相交。以第 $i$ 段的右端 $e$ 为起点，第一条左端 $\ge e+k+1$ 的段之前都可以被这条长为 $k$ 的桥连上。
>
> 对每个 $i$ 二分该右端 $j$，连通块变为 $|\textit{merged}|-(j-i-1)$，取最小即可。

<!-- thinking:end -->

首先，我们对给定的区间集合 $\textit{intervals}$ 按照区间的左端点进行排序，然后合并所有相交的区间，得到一个新的区间集合 $\textit{merged}$。

那么我们可以将初始答案设为 $\textit{merged}$ 的长度。

接下来，我们枚举 $\textit{merged}$ 中的每一个区间 $[\_, e]$，我们可以通过二分查找，在 $\textit{merged}$ 中找到第一个左端点大于等于 $e + k + 1$ 的区间，设其下标为 $j$，那么我们可以将答案更新，即 $\textit{ans} = \min(\textit{ans}, |\textit{merged}| - (j - i - 1))$。

最终，我们返回答案 $\textit{ans}$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为区间的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minConnectedGroups(self, intervals: List[List[int]], k: int) -> int:
        intervals.sort()
        merged = [intervals[0]]
        for s, e in intervals[1:]:
            if merged[-1][1] < s:
                merged.append([s, e])
            else:
                merged[-1][1] = max(merged[-1][1], e)
        ans = len(merged)
        for i, (_, e) in enumerate(merged):
            j = bisect_left(merged, [e + k + 1, 0])
            ans = min(ans, len(merged) - (j - i - 1))
        return ans
```

#### Java

```java
class Solution {
    public int minConnectedGroups(int[][] intervals, int k) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            int[] last = merged.get(merged.size() - 1);
            if (last[1] < interval[0]) {
                merged.add(interval);
            } else {
                last[1] = Math.max(last[1], interval[1]);
            }
        }

        int ans = merged.size();
        for (int i = 0; i < merged.size(); i++) {
            int[] interval = merged.get(i);
            int j = binarySearch(merged, interval[1] + k + 1);
            ans = Math.min(ans, merged.size() - (j - i - 1));
        }

        return ans;
    }

    private int binarySearch(List<int[]> nums, int x) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums.get(mid)[0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
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
    int minConnectedGroups(vector<vector<int>>& intervals, int k) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> merged;
        for (const auto& interval : intervals) {
            int s = interval[0], e = interval[1];
            if (merged.empty() || merged.back()[1] < s) {
                merged.emplace_back(interval);
            } else {
                merged.back()[1] = max(merged.back()[1], e);
            }
        }
        int ans = merged.size();
        for (int i = 0; i < merged.size(); ++i) {
            auto& interval = merged[i];
            int j = lower_bound(merged.begin(), merged.end(), vector<int>{interval[1] + k + 1, 0}) - merged.begin();
            ans = min(ans, (int) merged.size() - (j - i - 1));
        }
        return ans;
    }
};
```

#### Go

```go
func minConnectedGroups(intervals [][]int, k int) int {
	sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
	merged := [][]int{}
	for _, interval := range intervals {
		s, e := interval[0], interval[1]
		if len(merged) == 0 || merged[len(merged)-1][1] < s {
			merged = append(merged, interval)
		} else {
			merged[len(merged)-1][1] = max(merged[len(merged)-1][1], e)
		}
	}
	ans := len(merged)
	for i, interval := range merged {
		j := sort.Search(len(merged), func(j int) bool { return merged[j][0] >= interval[1]+k+1 })
		ans = min(ans, len(merged)-(j-i-1))
	}
	return ans
}
```

#### TypeScript

```ts
function minConnectedGroups(intervals: number[][], k: number): number {
    intervals.sort((a, b) => a[0] - b[0]);
    const merged: number[][] = [];
    for (const interval of intervals) {
        const [s, e] = interval;
        if (merged.length === 0 || merged.at(-1)![1] < s) {
            merged.push(interval);
        } else {
            merged.at(-1)![1] = Math.max(merged.at(-1)![1], e);
        }
    }
    const search = (x: number): number => {
        let [l, r] = [0, merged.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (merged[mid][0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    let ans = merged.length;
    for (let i = 0; i < merged.length; ++i) {
        const j = search(merged[i][1] + k + 1);
        ans = Math.min(ans, merged.length - (j - i - 1));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
