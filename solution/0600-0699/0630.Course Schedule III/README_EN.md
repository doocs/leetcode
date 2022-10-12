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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def scheduleCourse(self, courses: List[List[int]]) -> int:
        courses.sort(key=lambda x: x[1])
        pq = []
        s = 0
        for d, e in courses:
            heappush(pq, -d)
            s += d
            if s > e:
                s += heappop(pq)
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
        for (auto& course : courses) {
            int d = course[0], e = course[1];
            pq.push(d);
            s += d;
            if (s > e) {
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
