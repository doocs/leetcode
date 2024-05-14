---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0630.Course%20Schedule%20III/README_EN.md
tags:
    - Greedy
    - Array
    - Sorting
    - Heap (Priority Queue)
---

# [630. Course Schedule III](https://leetcode.com/problems/course-schedule-iii)

[中文文档](/solution/0600-0699/0630.Course%20Schedule%20III/README.md)

## Description

<p>There are <code>n</code> different online courses numbered from <code>1</code> to <code>n</code>. You are given an array <code>courses</code> where <code>courses[i] = [duration<sub>i</sub>, lastDay<sub>i</sub>]</code> indicate that the <code>i<sup>th</sup></code> course should be taken <b>continuously</b> for <code>duration<sub>i</sub></code> days and must be finished before or on <code>lastDay<sub>i</sub></code>.</p>

<p>You will start on the <code>1<sup>st</sup></code> day and you cannot take two or more courses simultaneously.</p>

<p>Return <em>the maximum number of courses that you can take</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
<strong>Output:</strong> 3
Explanation: 
There are totally 4 courses, but you can take 3 courses at most:
First, take the 1<sup>st</sup> course, it costs 100 days so you will finish it on the 100<sup>th</sup> day, and ready to take the next course on the 101<sup>st</sup> day.
Second, take the 3<sup>rd</sup> course, it costs 1000 days so you will finish it on the 1100<sup>th</sup> day, and ready to take the next course on the 1101<sup>st</sup> day. 
Third, take the 2<sup>nd</sup> course, it costs 200 days so you will finish it on the 1300<sup>th</sup> day. 
The 4<sup>th</sup> course cannot be taken now, since you will finish it on the 3300<sup>th</sup> day, which exceeds the closed date.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> courses = [[1,2]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> courses = [[3,2],[4,3]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= courses.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= duration<sub>i</sub>, lastDay<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def scheduleCourse(self, courses: List[List[int]]) -> int:
        courses.sort(key=lambda x: x[1])
        pq = []
        s = 0
        for duration, last in courses:
            heappush(pq, -duration)
            s += duration
            while s > last:
                s += heappop(pq)
        return len(pq)
```

```java
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int s = 0;
        for (var e : courses) {
            int duration = e[0], last = e[1];
            pq.offer(duration);
            s += duration;
            while (s > last) {
                s -= pq.poll();
            }
        }
        return pq.size();
    }
}
```

```cpp
class Solution {
public:
    int scheduleCourse(vector<vector<int>>& courses) {
        sort(courses.begin(), courses.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        priority_queue<int> pq;
        int s = 0;
        for (auto& e : courses) {
            int duration = e[0], last = e[1];
            pq.push(duration);
            s += duration;
            while (s > last) {
                s -= pq.top();
                pq.pop();
            }
        }
        return pq.size();
    }
};
```

```go
func scheduleCourse(courses [][]int) int {
	sort.Slice(courses, func(i, j int) bool { return courses[i][1] < courses[j][1] })
	pq := &hp{}
	s := 0
	for _, e := range courses {
		duration, last := e[0], e[1]
		s += duration
		pq.push(duration)
		for s > last {
			s -= pq.pop()
		}
	}
	return pq.Len()
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

```ts
function scheduleCourse(courses: number[][]): number {
    courses.sort((a, b) => a[1] - b[1]);
    const pq = new MaxPriorityQueue();
    let s = 0;
    for (const [duration, last] of courses) {
        pq.enqueue(duration);
        s += duration;
        while (s > last) {
            s -= pq.dequeue().element;
        }
    }
    return pq.size();
}
```

<!-- tabs:end -->

<!-- end -->
