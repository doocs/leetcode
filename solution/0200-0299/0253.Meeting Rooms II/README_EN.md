# [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii)

[中文文档](/solution/0200-0299/0253.Meeting%20Rooms%20II/README.md)

## Description

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

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        delta = [0] * 1000010
        for start, end in intervals:
            delta[start] += 1
            delta[end] -= 1
        return max(accumulate(delta))
```

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int n = 1000010;
        int[] delta = new int[n];
        for (int[] e : intervals) {
            ++delta[e[0]];
            --delta[e[1]];
        }
        int res = delta[0];
        for (int i = 1; i < n; ++i) {
            delta[i] += delta[i - 1];
            res = Math.max(res, delta[i]);
        }
        return res;
    }
}
```

```cpp
class Solution {
public:
    int minMeetingRooms(vector<vector<int>>& intervals) {
        int n = 1000010;
        vector<int> delta(n);
        for (auto e : intervals) {
            ++delta[e[0]];
            --delta[e[1]];
        }
        for (int i = 0; i < n - 1; ++i) {
            delta[i + 1] += delta[i];
        }
        return *max_element(delta.begin(), delta.end());
    }
};
```

```go
func minMeetingRooms(intervals [][]int) int {
	n := 1000010
	delta := make([]int, n)
	for _, e := range intervals {
		delta[e[0]]++
		delta[e[1]]--
	}
	for i := 1; i < n; i++ {
		delta[i] += delta[i-1]
	}
	return slices.Max(delta)
}
```

```rust
use std::{ collections::BinaryHeap, cmp::Reverse };

impl Solution {
    #[allow(dead_code)]
    pub fn min_meeting_rooms(intervals: Vec<Vec<i32>>) -> i32 {
        // The min heap that stores the earliest ending time among all meeting rooms
        let mut pq = BinaryHeap::new();
        let mut intervals = intervals;
        let n = intervals.len();

        // Let's first sort the intervals vector
        intervals.sort_by(|lhs, rhs| { lhs[0].cmp(&rhs[0]) });

        // Push the first end time to the heap
        pq.push(Reverse(intervals[0][1]));

        // Traverse the intervals vector
        for i in 1..n {
            // Get the current top element from the heap
            if let Some(Reverse(end_time)) = pq.pop() {
                if end_time <= intervals[i][0] {
                    // If the end time is early than the current begin time
                    let new_end_time = intervals[i][1];
                    pq.push(Reverse(new_end_time));
                } else {
                    // Otherwise, push the end time back and we also need a new room
                    pq.push(Reverse(end_time));
                    pq.push(Reverse(intervals[i][1]));
                }
            }
        }

        pq.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
