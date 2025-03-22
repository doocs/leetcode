---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0253.Meeting%20Rooms%20II/README_EN.md
tags:
    - Greedy
    - Array
    - Two Pointers
    - Prefix Sum
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [253. Meeting Rooms II 🔒](https://leetcode.com/problems/meeting-rooms-ii)

[中文文档](/solution/0200-0299/0253.Meeting%20Rooms%20II/README.md)

## Description

<!-- description:start -->

<p>Given an array of meeting time intervals <code>intervals</code> where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>, return <em>the minimum number of conference rooms required</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> intervals = [[0,30],[5,10],[15,20]]
<strong>Output:</strong> 2
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> intervals = [[7,10],[2,4]]
<strong>Output:</strong> 1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

We can implement this using a difference array.

First, we find the maximum end time of all the meetings, denoted as $m$. Then, we create a difference array $d$ of length $m + 1$. For each meeting, we add to the corresponding positions in the difference array: $d[l] = d[l] + 1$ for the start time, and $d[r] = d[r] - 1$ for the end time.

Next, we calculate the prefix sum of the difference array and find the maximum value of the prefix sum, which represents the minimum number of meeting rooms required.

The time complexity is $O(n + m)$ and the space complexity is $O(m)$, where $n$ is the number of meetings and $m$ is the maximum end time.

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

### Solution 2: Difference (Hash Map)

If the meeting times span a large range, we can use a hash map instead of a difference array.

First, we create a hash map $d$, where we add to the corresponding positions for each meeting's start time and end time: $d[l] = d[l] + 1$ for the start time, and $d[r] = d[r] - 1$ for the end time.

Then, we sort the hash map by its keys, calculate the prefix sum of the hash map, and find the maximum value of the prefix sum, which represents the minimum number of meeting rooms required.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the number of meetings.

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
