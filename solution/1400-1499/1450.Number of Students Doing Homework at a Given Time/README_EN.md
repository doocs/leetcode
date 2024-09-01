---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1450.Number%20of%20Students%20Doing%20Homework%20at%20a%20Given%20Time/README_EN.md
rating: 1129
source: Weekly Contest 189 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1450. Number of Students Doing Homework at a Given Time](https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time)

[中文文档](/solution/1400-1499/1450.Number%20of%20Students%20Doing%20Homework%20at%20a%20Given%20Time/README.md)

## Description

<!-- description:start -->

<p>Given two integer arrays <code>startTime</code> and <code>endTime</code> and given an integer <code>queryTime</code>.</p>

<p>The <code>ith</code> student started doing their homework at the time <code>startTime[i]</code> and finished it at time <code>endTime[i]</code>.</p>

<p>Return <em>the number of students</em> doing their homework at time <code>queryTime</code>. More formally, return the number of students where <code>queryTime</code> lays in the interval <code>[startTime[i], endTime[i]]</code> inclusive.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> We have 3 students where:
The first student started doing homework at time 1 and finished at time 3 and wasn&#39;t doing anything at time 4.
The second student started doing homework at time 2 and finished at time 2 and also wasn&#39;t doing anything at time 4.
The third student started doing homework at time 3 and finished at time 7 and was the only student doing homework at time 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> startTime = [4], endTime = [4], queryTime = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only student was doing their homework at the queryTime.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>startTime.length == endTime.length</code></li>
	<li><code>1 &lt;= startTime.length &lt;= 100</code></li>
	<li><code>1 &lt;= startTime[i] &lt;= endTime[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= queryTime &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Traversal

We can directly traverse the two arrays. For each student, we check if $\textit{queryTime}$ is within their homework time interval. If it is, we increment the answer by one.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the number of students. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def busyStudent(
        self, startTime: List[int], endTime: List[int], queryTime: int
    ) -> int:
        return sum(x <= queryTime <= y for x, y in zip(startTime, endTime))
```

#### Java

```java
class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ans = 0;
        for (int i = 0; i < startTime.length; ++i) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ++ans;
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
    int busyStudent(vector<int>& startTime, vector<int>& endTime, int queryTime) {
        int ans = 0;
        for (int i = 0; i < startTime.size(); ++i) {
            ans += startTime[i] <= queryTime && queryTime <= endTime[i];
        }
        return ans;
    }
};
```

#### Go

```go
func busyStudent(startTime []int, endTime []int, queryTime int) (ans int) {
	for i, x := range startTime {
		if x <= queryTime && queryTime <= endTime[i] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function busyStudent(startTime: number[], endTime: number[], queryTime: number): number {
    const n = startTime.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
            ans++;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn busy_student(start_time: Vec<i32>, end_time: Vec<i32>, query_time: i32) -> i32 {
        let mut ans = 0;
        for i in 0..start_time.len() {
            if start_time[i] <= query_time && end_time[i] >= query_time {
                ans += 1;
            }
        }
        ans
    }
}
```

#### C

```c
int busyStudent(int* startTime, int startTimeSize, int* endTime, int endTimeSize, int queryTime) {
    int ans = 0;
    for (int i = 0; i < startTimeSize; i++) {
        if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
