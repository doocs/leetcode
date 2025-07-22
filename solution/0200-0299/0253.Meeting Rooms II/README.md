---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0253.Meeting%20Rooms%20II/README.md
tags:
    - 贪心
    - 数组
    - 双指针
    - 前缀和
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [253. 会议室 II 🔒](https://leetcode.cn/problems/meeting-rooms-ii)

[English Version](/solution/0200-0299/0253.Meeting%20Rooms%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个会议时间安排的数组 <code>intervals</code> ，每个会议时间都会包括开始和结束的时间 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> ，返回 <em>所需会议室的最小数量</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[0,30],[5,10],[15,20]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[7,10],[2,4]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

我们可以用差分数组来实现。

我们首先找到所有会议的最大结束时间，记为 $m$，然后创建一个长度为 $m + 1$ 的差分数组 $d$，将每个会议的开始时间和结束时间分别加到差分数组的对应位置上，即 $d[l] = d[l] + 1$，而 $d[r] = d[r] - 1$。

然后，我们计算差分数组的前缀和，找出前缀和的最大值，即为所需会议室的最小数量。

时间复杂度 $O(n + m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为会议数量和最大结束时间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        m = max(e[1] for e in intervals)
        d = [0] * (m + 1)
        for l, r in intervals:
            d[l] += 1
            d[r] -= 1
        ans = s = 0
        for v in d:
            s += v
            ans = max(ans, s)
        return ans
```

#### Java

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int m = 0;
        for (var e : intervals) {
            m = Math.max(m, e[1]);
        }
        int[] d = new int[m + 1];
        for (var e : intervals) {
            ++d[e[0]];
            --d[e[1]];
        }
        int ans = 0, s = 0;
        for (int v : d) {
            s += v;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMeetingRooms(vector<vector<int>>& intervals) {
        int m = 0;
        for (const auto& e : intervals) {
            m = max(m, e[1]);
        }
        vector<int> d(m + 1);
        for (const auto& e : intervals) {
            d[e[0]]++;
            d[e[1]]--;
        }
        int ans = 0, s = 0;
        for (int v : d) {
            s += v;
            ans = max(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func minMeetingRooms(intervals [][]int) (ans int) {
	m := 0
	for _, e := range intervals {
		m = max(m, e[1])
	}

	d := make([]int, m+1)
	for _, e := range intervals {
		d[e[0]]++
		d[e[1]]--
	}

	s := 0
	for _, v := range d {
		s += v
		ans = max(ans, s)
	}
	return
}
```

#### TypeScript

```ts
function minMeetingRooms(intervals: number[][]): number {
    const m = Math.max(...intervals.map(([_, r]) => r));
    const d: number[] = Array(m + 1).fill(0);
    for (const [l, r] of intervals) {
        d[l]++;
        d[r]--;
    }
    let [ans, s] = [0, 0];
    for (const v of d) {
        s += v;
        ans = Math.max(ans, s);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_meeting_rooms(intervals: Vec<Vec<i32>>) -> i32 {
        let mut m = 0;
        for e in &intervals {
            m = m.max(e[1]);
        }

        let mut d = vec![0; (m + 1) as usize];
        for e in intervals {
            d[e[0] as usize] += 1;
            d[e[1] as usize] -= 1;
        }

        let mut ans = 0;
        let mut s = 0;
        for v in d {
            s += v;
            ans = ans.max(s);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：差分（哈希表）

如果题目中的会议时间跨度很大，那么我们可以使用哈希表来代替差分数组。

我们首先创建一个哈希表 $d$，将每个会议的开始时间和结束时间分别加到哈希表的对应位置上，即 $d[l] = d[l] + 1$，而 $d[r] = d[r] - 1$。

然后，我们将哈希表按照键进行排序，计算哈希表的前缀和，找出前缀和的最大值，即为所需会议室的最小数量。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为会议数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        d = defaultdict(int)
        for l, r in intervals:
            d[l] += 1
            d[r] -= 1
        ans = s = 0
        for _, v in sorted(d.items()):
            s += v
            ans = max(ans, s)
        return ans
```

#### Java

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Map<Integer, Integer> d = new TreeMap<>();
        for (var e : intervals) {
            d.merge(e[0], 1, Integer::sum);
            d.merge(e[1], -1, Integer::sum);
        }
        int ans = 0, s = 0;
        for (var e : d.values()) {
            s += e;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMeetingRooms(vector<vector<int>>& intervals) {
        map<int, int> d;
        for (const auto& e : intervals) {
            d[e[0]]++;
            d[e[1]]--;
        }
        int ans = 0, s = 0;
        for (auto& [_, v] : d) {
            s += v;
            ans = max(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func minMeetingRooms(intervals [][]int) (ans int) {
	d := make(map[int]int)
	for _, e := range intervals {
		d[e[0]]++
		d[e[1]]--
	}

	keys := make([]int, 0, len(d))
	for k := range d {
		keys = append(keys, k)
	}
	sort.Ints(keys)

	s := 0
	for _, k := range keys {
		s += d[k]
		ans = max(ans, s)
	}
	return
}
```

#### TypeScript

```ts
function minMeetingRooms(intervals: number[][]): number {
    const d: { [key: number]: number } = {};
    for (const [l, r] of intervals) {
        d[l] = (d[l] || 0) + 1;
        d[r] = (d[r] || 0) - 1;
    }

    let [ans, s] = [0, 0];
    const keys = Object.keys(d)
        .map(Number)
        .sort((a, b) => a - b);
    for (const k of keys) {
        s += d[k];
        ans = Math.max(ans, s);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_meeting_rooms(intervals: Vec<Vec<i32>>) -> i32 {
        let mut d: HashMap<i32, i32> = HashMap::new();
        for interval in intervals {
            let (l, r) = (interval[0], interval[1]);
            *d.entry(l).or_insert(0) += 1;
            *d.entry(r).or_insert(0) -= 1;
        }

        let mut times: Vec<i32> = d.keys().cloned().collect();
        times.sort();

        let mut ans = 0;
        let mut s = 0;
        for time in times {
            s += d[&time];
            ans = ans.max(s);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
