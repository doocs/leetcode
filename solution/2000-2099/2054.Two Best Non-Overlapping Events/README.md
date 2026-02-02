---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/README.md
rating: 1883
source: 第 64 场双周赛 Q2
tags:
    - 数组
    - 二分查找
    - 动态规划
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2054. 两个最好的不重叠活动](https://leetcode.cn/problems/two-best-non-overlapping-events)

[English Version](/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>events</code>&nbsp;，其中&nbsp;<code>events[i] = [startTime<sub>i</sub>, endTime<sub>i</sub>, value<sub>i</sub>]</code>&nbsp;。第&nbsp;<code>i</code>&nbsp;个活动开始于&nbsp;<code>startTime<sub>i</sub></code>&nbsp;，结束于&nbsp;<code>endTime<sub>i</sub></code>&nbsp;，如果你参加这个活动，那么你可以得到价值&nbsp;<code>value<sub>i</sub></code>&nbsp;。你 <strong>最多</strong>&nbsp;可以参加&nbsp;<strong>两个时间不重叠</strong>&nbsp;活动，使得它们的价值之和 <strong>最大</strong>&nbsp;。</p>

<p>请你返回价值之和的 <strong>最大值</strong>&nbsp;。</p>

<p>注意，活动的开始时间和结束时间是 <strong>包括</strong>&nbsp;在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。更具体的，如果你参加一个活动，且结束时间为 <code>t</code>&nbsp;，那么下一个活动必须在&nbsp;<code>t + 1</code>&nbsp;或之后的时间开始。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/untitled-diagramdrawio.png" style="width: 400px; height: 86px;" /></p>

<pre>
<b>输入：</b>events = [[1,3,2],[4,5,2],[2,4,3]]
<b>输出：</b>4
<strong>解释：</strong>选择绿色的活动 0 和 1 ，价值之和为 2 + 2 = 4 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="Example 1 Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/2054b.png" style="width: 400px; height: 86px;" /></p>

<pre>
<b>输入：</b>events = [[1,3,2],[4,5,2],[1,5,5]]
<b>输出：</b>5
<strong>解释：</strong>选择活动 2 ，价值和为 5 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/2054c.png" style="width: 400px; height: 74px;" /></p>

<pre>
<b>输入：</b>events = [[1,5,3],[1,5,1],[6,6,5]]
<b>输出：</b>8
<strong>解释：</strong>选择活动 0 和 2 ，价值之和为 3 + 5 = 8 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= events.length &lt;= 10<sup>5</sup></code></li>
	<li><code>events[i].length == 3</code></li>
	<li><code>1 &lt;= startTime<sub>i</sub> &lt;= endTime<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= value<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

我们可以讲活动按照开始排序，然后预处理出以每个活动为作为开始的最大价值，即 $f[i]$ 表示从第 $i$ 个活动开始，到最后一个活动结束，选择其中一个活动的最大价值。

然后我们枚举每个活动，对于每个活动，我们使用二分查找找到第一个开始时间大于当前活动结束时间的活动，下标记为 $\textit{idx}$，那么以当前活动为开始的最大价值就是 $f[\textit{idx}]$，加上当前活动的价值，即为以当前活动为第一个活动，最终能获得的最大价值。求最大值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为活动的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        events.sort()
        n = len(events)
        f = [events[-1][2]] * n
        for i in range(n - 2, -1, -1):
            f[i] = max(f[i + 1], events[i][2])
        ans = 0
        for _, e, v in events:
            idx = bisect_right(events, e, key=lambda x: x[0])
            if idx < n:
                v += f[idx]
            ans = max(ans, v)
        return ans
```

#### Java

```java
class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = Math.max(f[i + 1], events[i][2]);
        }
        int ans = 0;
        for (int[] e : events) {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left < n) {
                v += f[left];
            }
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        ranges::sort(events);
        int n = events.size();
        vector<int> f(n + 1);
        for (int i = n - 1; ~i; --i) {
            f[i] = max(f[i + 1], events[i][2]);
        }
        int ans = 0;
        for (const auto& e : events) {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left < n) {
                v += f[left];
            }
            ans = max(ans, v);
        }
        return ans;
    }
};
```

#### Go

```go
func maxTwoEvents(events [][]int) int {
	sort.Slice(events, func(i, j int) bool {
		return events[i][0] < events[j][0]
	})
	n := len(events)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		f[i] = max(f[i+1], events[i][2])
	}
	ans := 0
	for _, e := range events {
		v := e[2]
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if events[mid][0] > e[1] {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if left < n {
			v += f[left]
		}
		ans = max(ans, v)
	}
	return ans
}
```

#### TypeScript

```ts
function maxTwoEvents(events: number[][]): number {
    events.sort((a, b) => a[0] - b[0]);
    const n = events.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; ~i; --i) {
        f[i] = Math.max(f[i + 1], events[i][2]);
    }
    let ans = 0;
    for (const [_, end, v] of events) {
        let [left, right] = [0, n];
        while (left < right) {
            const mid = (left + right) >> 1;
            if (events[mid][0] > end) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        const t = left < n ? f[left] : 0;
        ans = Math.max(ans, t + v);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_two_events(mut events: Vec<Vec<i32>>) -> i32 {
        events.sort_by(|a, b| a[0].cmp(&b[0]));

        let n: usize = events.len();
        let mut f: Vec<i32> = vec![0; n + 1];

        for i in (0..n).rev() {
            f[i] = f[i + 1].max(events[i][2]);
        }

        let mut ans: i32 = 0;

        for e in &events {
            let mut v: i32 = e[2];

            let mut left: usize = 0;
            let mut right: usize = n;
            while left < right {
                let mid = (left + right) >> 1;
                if events[mid][0] > e[1] {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if left < n {
                v += f[left];
            }

            ans = ans.max(v);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
