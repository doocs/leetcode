---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0252.Meeting%20Rooms/README.md
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [252. 会议室 🔒](https://leetcode.cn/problems/meeting-rooms)

[English Version](/solution/0200-0299/0252.Meeting%20Rooms/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个会议时间安排的数组 <code>intervals</code> ，每个会议时间都会包括开始和结束的时间 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> ，请你判断一个人是否能够参加这里面的全部会议。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[0,30],[5,10],[15,20]]
<strong>输出</strong>：false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[7,10],[2,4]]
<strong>输出</strong>：true
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= intervals.length <= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 <= start<sub>i</sub> < end<sub>i</sub> <= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们将会议按照开始时间进行排序，然后遍历排序后的会议，如果当前会议的开始时间小于前一个会议的结束时间，则说明两个会议有重叠，返回 $\text{false}$，否则继续遍历。

如果遍历结束都没有发现重叠的会议，则返回 $\text{true}$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为会议数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
        intervals.sort()
        return all(a[1] <= b[0] for a, b in pairwise(intervals))
```

#### Java

```java
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canAttendMeetings(vector<vector<int>>& intervals) {
        ranges::sort(intervals, [](const auto& a, const auto& b) {
            return a[0] < b[0];
        });
        for (int i = 1; i < intervals.size(); ++i) {
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func canAttendMeetings(intervals [][]int) bool {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	for i := 1; i < len(intervals); i++ {
		if intervals[i][0] < intervals[i-1][1] {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function canAttendMeetings(intervals: number[][]): boolean {
    intervals.sort((a, b) => a[0] - b[0]);
    for (let i = 1; i < intervals.length; ++i) {
        if (intervals[i][0] < intervals[i - 1][1]) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn can_attend_meetings(mut intervals: Vec<Vec<i32>>) -> bool {
        intervals.sort_by(|a, b| a[0].cmp(&b[0]));
        for i in 1..intervals.len() {
            if intervals[i - 1][1] > intervals[i][0] {
                return false;
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
