---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0759.Employee%20Free%20Time/README.md
tags:
    - 数组
    - 排序
    - 扫描线
    - 堆（优先队列）
---

<!-- problem:start -->

# [759. 员工空闲时间 🔒](https://leetcode.cn/problems/employee-free-time)

[English Version](/solution/0700-0799/0759.Employee%20Free%20Time/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定员工的 <code>schedule</code> 列表，表示每个员工的工作时间。</p>

<p>每个员工都有一个非重叠的时间段&nbsp; <code>Intervals</code> 列表，这些时间段已经排好序。</p>

<p>返回表示 <em>所有 </em>员工的 <strong>共同，正数长度的空闲时间 </strong>的有限时间段的列表，同样需要排好序。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
<strong>输出：</strong>[[3,4]]
<strong>解释：</strong>
共有 3 个员工，并且所有共同的
空间时间段是 [-inf, 1], [3, 4], [10, inf]。
我们去除所有包含 inf 的时间段，因为它们不是有限的时间段。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
<strong>输出：</strong>[[5,6],[7,9]]
</pre>

<p>&nbsp;</p>

<p>（尽管我们用 <code>[x, y]</code> 的形式表示 <code>Intervals</code> ，内部的对象是 <code>Intervals</code> 而不是列表或数组。例如，<code>schedule[0][0].start = 1, schedule[0][0].end = 2</code>，并且 <code>schedule[0][0][0]</code>&nbsp;是未定义的）</p>

<p>而且，答案中不包含 [5, 5] ，因为长度为 0。</p>

<p>&nbsp;</p>

<p><strong>注：</strong></p>

<ol>
	<li><code>schedule</code> 和&nbsp;<code>schedule[i]</code>&nbsp;为长度范围在&nbsp;<code>[1, 50]</code>的列表。</li>
	<li><code>0 &lt;= schedule[i].start &lt; schedule[i].end &lt;= 10^8</code>。</li>
</ol>

<p><strong>注：</strong>输入类型于&nbsp;2019 年&nbsp;4 月 15 日 改变。请重置为默认代码的定义以获取新方法。</p>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：区间合并

<!-- thinking:start -->

> **思考**
>
> 求所有员工都空闲的交集空隙。每人的区间已各自有序，但跨人之后整体并不有序。人数与区间总数有限，全部摊平再处理即可。
>
> 全体都忙的时段是所有忙区间的并。并集之间的空隙即所有人都空闲的时间。
>
> 按起点排序后做一次区间合并，再取相邻合并段之间 $[a.end,b.start)$。

<!-- thinking:end -->

我们可以将所有员工的工作时间区间合并成一个列表，然后对该列表进行排序并合并重叠的区间。最后，遍历合并后的区间列表，找出相邻区间之间的空闲时间段。

时间复杂度 $O(m \times n \times \log(m \times n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为员工数量和每个员工的工作时间区间数量。

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for an Interval.
class Interval:
    def __init__(self, start: int = None, end: int = None):
        self.start = start
        self.end = end
"""


class Solution:
    def employeeFreeTime(self, schedule: "[[Interval]]") -> "[Interval]":
        intervals = []
        for e in schedule:
            intervals.extend(e)
        intervals.sort(key=lambda x: (x.start, x.end))
        merged = [intervals[0]]
        for x in intervals[1:]:
            if merged[-1].end < x.start:
                merged.append(x)
            else:
                merged[-1].end = max(merged[-1].end, x.end)
        ans = []
        for a, b in pairwise(merged):
            ans.append(Interval(a.end, b.start))
        return ans
```

#### Java

```java
/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervals = new ArrayList<>();
        for (List<Interval> e : schedule) {
            intervals.addAll(e);
        }

        intervals.sort((a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);

        List<Interval> merged = new ArrayList<>();
        merged.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); ++i) {
            Interval last = merged.get(merged.size() - 1);
            Interval cur = intervals.get(i);
            if (last.end < cur.start) {
                merged.add(cur);
            } else {
                last.end = Math.max(last.end, cur.end);
            }
        }

        List<Interval> ans = new ArrayList<>();
        for (int i = 1; i < merged.size(); ++i) {
            Interval a = merged.get(i - 1);
            Interval b = merged.get(i);
            ans.add(new Interval(a.end, b.start));
        }

        return ans;
    }
}
```

#### C++

```cpp
/*
// Definition for an Interval.
class Interval {
public:
    int start;
    int end;

    Interval() {}

    Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
public:
    vector<Interval> employeeFreeTime(vector<vector<Interval>> schedule) {
        vector<Interval> intervals;
        for (auto& e : schedule) {
            intervals.insert(intervals.end(), e.begin(), e.end());
        }

        sort(intervals.begin(), intervals.end(), [](const Interval& a, const Interval& b) {
            if (a.start == b.start) return a.end < b.end;
            return a.start < b.start;
        });

        vector<Interval> merged;
        merged.push_back(intervals[0]);
        for (int i = 1; i < intervals.size(); ++i) {
            auto& last = merged.back();
            auto& cur = intervals[i];
            if (last.end < cur.start) {
                merged.push_back(cur);
            } else {
                last.end = max(last.end, cur.end);
            }
        }

        vector<Interval> ans;
        for (int i = 1; i < merged.size(); ++i) {
            auto& a = merged[i - 1];
            auto& b = merged[i];
            ans.emplace_back(a.end, b.start);
        }

        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for an Interval.
 * type Interval struct {
 *     Start int
 *     End   int
 * }
 */

func employeeFreeTime(schedule [][]*Interval) []*Interval {
	var intervals []*Interval
	for _, e := range schedule {
		intervals = append(intervals, e...)
	}

	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i].Start == intervals[j].Start {
			return intervals[i].End < intervals[j].End
		}
		return intervals[i].Start < intervals[j].Start
	})

	merged := []*Interval{intervals[0]}
	for _, cur := range intervals[1:] {
		last := merged[len(merged)-1]
		if last.End < cur.Start {
			merged = append(merged, cur)
		} else if cur.End > last.End {
			last.End = cur.End
		}
	}

	var ans []*Interval
	for i := 1; i < len(merged); i++ {
		a, b := merged[i-1], merged[i]
		ans = append(ans, &Interval{Start: a.End, End: b.Start})
	}

	return ans
}
```

#### TypeScript

```ts
/**
 * // Definition for an Interval.
 * class Interval {
 *    start: number;
 *    end: number;
 *    constructor(start: number, end: number) {
 *        this.start = start;
 *        this.end = end;
 *    }
 * }
 */
function employeeFreeTime(schedule: Interval[][]): Interval[] {
    const intervals: Interval[] = [];
    for (const e of schedule) {
        intervals.push(...e);
    }

    intervals.sort((a, b) => (a.start === b.start ? a.end - b.end : a.start - b.start));

    const merged: Interval[] = [intervals[0]];
    for (let i = 1; i < intervals.length; ++i) {
        const last = merged[merged.length - 1];
        const cur = intervals[i];
        if (last.end < cur.start) {
            merged.push(cur);
        } else {
            last.end = Math.max(last.end, cur.end);
        }
    }

    const ans: Interval[] = [];
    for (let i = 1; i < merged.length; ++i) {
        const a = merged[i - 1];
        const b = merged[i];
        ans.push(new Interval(a.end, b.start));
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
