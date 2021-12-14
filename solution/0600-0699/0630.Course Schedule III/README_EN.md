# [630. Course Schedule III](https://leetcode.com/problems/course-schedule-iii)

[中文文档](/solution/0600-0699/0630.Course%20Schedule%20III/README.md)

## Description

<p>There are <code>n</code> different online courses numbered from <code>1</code> to <code>n</code>. Each course has some duration(course length) <code>t</code> and closed on <code>d<sub>th</sub></code> day. A course should be taken <b>continuously</b> for <code>t</code> days and must be finished before or on the <code>d<sub>th</sub></code> day. You will start at the <code>1<sub>st</sub></code> day.</p>

<p>Given <code>n</code> online courses represented by pairs <code>(t,d)</code>, your task is to find the maximal number of courses that can be taken.</p>

<p><b>Example:</b></p>

<pre>

<b>Input:</b> [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]

<b>Output:</b> 3

<b>Explanation:</b> 

There&#39;re totally 4 courses, but you can take 3 courses at most:

First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.

Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day. 

Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 

The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>The integer 1 &lt;= d, t, n &lt;= 10,000.</li>
	<li>You can&#39;t take two courses simultaneously.</li>
</ol>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
