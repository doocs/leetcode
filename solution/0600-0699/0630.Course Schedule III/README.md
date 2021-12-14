# [630. 课程表 III](https://leetcode-cn.com/problems/course-schedule-iii)

[English Version](/solution/0600-0699/0630.Course%20Schedule%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>这里有 <code>n</code> 门不同的在线课程，他们按从 <code>1</code> 到 <code>n</code>&nbsp;编号。每一门课程有一定的持续上课时间（课程时间）<code>t</code> 以及关闭时间第 d<sub>&nbsp;</sub>天。一门课要持续学习 <code>t</code> 天直到第 d<span style="font-size:10.5px"> </span>天时要完成，你将会从第 1 天开始。</p>

<p>给出 <code>n</code> 个在线课程用 <code>(t, d)</code> 对表示。你的任务是找出最多可以修几门课。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入:</strong> [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
<strong>输出:</strong> 3
<strong>解释:</strong> 
这里一共有 4 门课程, 但是你最多可以修 3 门:
首先, 修第一门课时, 它要耗费 100 天，你会在第 100 天完成, 在第 101 天准备下门课。
第二, 修第三门课时, 它会耗费 1000 天，所以你将在第 1100 天的时候完成它, 以及在第 1101 天开始准备下门课程。
第三, 修第二门课时, 它会耗时 200 天，所以你将会在第 1300 天时完成它。
第四门课现在不能修，因为你将会在第 3300 天完成它，这已经超出了关闭日期。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ol>
	<li>整数 1 &lt;= d, t, n &lt;= 10,000 。</li>
	<li>你不能同时修两门课程。</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

贪心 + 优先队列。

先根据「结束时间」对 `courses` 升序排列，从前往后考虑每个课程，过程中维护一个总时长 s，对于某个课程 `courses[i]` 而言，根据如果学习该课程，是否满足「最晚完成时间」条件进行讨论：

- 学习该课程后，满足「最晚完成时间」要求，即 s + `courses[i][0]` <= `courses[i][1]`，则进行学习；
- 学习该课程后，不满足「最晚完成时间」要求，此时从过往学习的课程中找出「持续时间」最长的课程进行「回退」操作（这个持续时长最长的课程也有可能是当前课程）。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def scheduleCourse(self, courses: List[List[int]]) -> int:
        courses.sort(key=lambda x: x[1])
        pq = []
        s = 0
        for d, e in courses:
            heapq.heappush(pq, -d)
            s += d
            if s > e:
                s += heapq.heappop(pq)
        return len(pq)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int s = 0;
        for (int[] course : courses) {
            int duration = course[0], lastDay = course[1];
            pq.offer(duration);
            s += duration;
            if (s > lastDay) {
                s -= pq.poll();
            }
        }
        return pq.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int scheduleCourse(vector<vector<int>>& courses) {
        sort(courses.begin(), courses.end(), [](const auto& c0, const auto& c1) {
            return c0[1] < c1[1];
        });
        int s = 0;
        priority_queue<int> pq;
        for (auto& course : courses)
        {
            int d = course[0], e = course[1];
            pq.push(d);
            s += d;
            if (s > e)
            {
                s -= pq.top();
                pq.pop();
            }
        }
        return pq.size();
    }
};
```

### **Go**

```go
func scheduleCourse(courses [][]int) int {
	sort.Slice(courses, func(i, j int) bool {
		return courses[i][1] < courses[j][1]
	})

	h := &Heap{}
	s := 0
	for _, course := range courses {
		if d := course[0]; s+d <= course[1] {
			s += d
			heap.Push(h, d)
		} else if h.Len() > 0 && d < h.IntSlice[0] {
			s += d - h.IntSlice[0]
			h.IntSlice[0] = d
			heap.Fix(h, 0)
		}
	}
	return h.Len()
}

type Heap struct {
	sort.IntSlice
}

func (h Heap) Less(i, j int) bool {
	return h.IntSlice[i] > h.IntSlice[j]
}

func (h *Heap) Push(x interface{}) {
	h.IntSlice = append(h.IntSlice, x.(int))
}

func (h *Heap) Pop() interface{} {
	a := h.IntSlice
	x := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
