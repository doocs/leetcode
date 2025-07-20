---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0630.Course%20Schedule%20III/README.md
tags:
    - 贪心
    - 数组
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [630. 课程表 III](https://leetcode.cn/problems/course-schedule-iii)

[English Version](/solution/0600-0699/0630.Course%20Schedule%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>这里有 <code>n</code> 门不同的在线课程，按从 <code>1</code> 到 <code>n</code>&nbsp;编号。给你一个数组 <code>courses</code> ，其中 <code>courses[i] = [duration<sub>i</sub>, lastDay<sub>i</sub>]</code> 表示第 <code>i</code> 门课将会 <strong>持续</strong> 上 <code>duration<sub>i</sub></code> 天课，并且必须在不晚于 <code>lastDay<sub>i</sub></code> 的时候完成。</p>

<p>你的学期从第 <code>1</code> 天开始。且不能同时修读两门及两门以上的课程。</p>

<p>返回你最多可以修读的课程数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
<strong>输出：</strong>3
<strong>解释：</strong>
这里一共有 4 门课程，但是你最多可以修 3 门：
首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>courses = [[1,2]]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>courses = [[3,2],[4,3]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= courses.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= duration<sub>i</sub>, lastDay<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（大根堆）

我们可以按照课程的结束时间进行升序排序，每次选择结束时间最早的课程进行上课。

如果已选择的课程的总时间 $s$ 超过了当前课程的结束时间 $last$，那么我们就将此前选择的课程中耗时最长的课程去掉，直到能够满足当前课程的结束时间为止。这里我们使用一个优先队列（大根堆） $pq$ 来维护当前已经选择的课程的耗时，每次我们都从优先队列中取出耗时最长的课程进行去除。

最后，优先队列中的元素个数即为我们能够选择的课程数目。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是课程数目。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

```ts
function scheduleCourse(courses: number[][]): number {
    courses.sort((a, b) => a[1] - b[1]);
    const pq = new MaxPriorityQueue<number>();
    let s = 0;
    for (const [duration, last] of courses) {
        pq.enqueue(duration);
        s += duration;
        while (s > last) {
            s -= pq.dequeue();
        }
    }
    return pq.size();
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
