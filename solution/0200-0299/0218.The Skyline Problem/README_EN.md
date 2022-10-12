# [218. The Skyline Problem](https://leetcode.com/problems/the-skyline-problem)

[中文文档](/solution/0200-0299/0218.The%20Skyline%20Problem/README.md)

## Description

<p>A city&#39;s <strong>skyline</strong> is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return <em>the <strong>skyline</strong> formed by these buildings collectively</em>.</p>

<p>The geometric information of each building is given in the array <code>buildings</code> where <code>buildings[i] = [left<sub>i</sub>, right<sub>i</sub>, height<sub>i</sub>]</code>:</p>

<ul>
	<li><code>left<sub>i</sub></code> is the x coordinate of the left edge of the <code>i<sup>th</sup></code> building.</li>
	<li><code>right<sub>i</sub></code> is the x coordinate of the right edge of the <code>i<sup>th</sup></code> building.</li>
	<li><code>height<sub>i</sub></code> is the height of the <code>i<sup>th</sup></code> building.</li>
</ul>

<p>You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height <code>0</code>.</p>

<p>The <strong>skyline</strong> should be represented as a list of &quot;key points&quot; <strong>sorted by their x-coordinate</strong> in the form <code>[[x<sub>1</sub>,y<sub>1</sub>],[x<sub>2</sub>,y<sub>2</sub>],...]</code>. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate <code>0</code> and is used to mark the skyline&#39;s termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline&#39;s contour.</p>

<p><b>Note:</b> There must be no consecutive horizontal lines of equal height in the output skyline. For instance, <code>[...,[2 3],[4 5],[7 5],[11 5],[12 7],...]</code> is not acceptable; the three lines of height 5 should be merged into one in the final output as such: <code>[...,[2 3],[4 5],[12 7],...]</code></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0218.The%20Skyline%20Problem/images/merged.jpg" style="width: 800px; height: 331px;" />
<pre>
<strong>Input:</strong> buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
<strong>Output:</strong> [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
<strong>Explanation:</strong>
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> buildings = [[0,2,3],[2,5,3]]
<strong>Output:</strong> [[0,3],[5,0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= left<sub>i</sub> &lt; right<sub>i</sub> &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= height<sub>i</sub> &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>buildings</code> is sorted by <code>left<sub>i</sub></code> in&nbsp;non-decreasing order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from queue import PriorityQueue


class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        skys, lines, pq = [], [], PriorityQueue()
        for build in buildings:
            lines.extend([build[0], build[1]])
        lines.sort()
        city, n = 0, len(buildings)
        for line in lines:
            while city < n and buildings[city][0] <= line:
                pq.put([-buildings[city][2], buildings[city]
                       [0], buildings[city][1]])
                city += 1
            while not pq.empty() and pq.queue[0][2] <= line:
                pq.get()
            high = 0
            if not pq.empty():
                high = -pq.queue[0][0]
            if len(skys) > 0 and skys[-1][1] == high:
                continue
            skys.append([line, high])
        return skys
```

### **Go**

```go
type Matrix struct{ left, right, height int }
type Queue []Matrix

func (q Queue) Len() int            { return len(q) }
func (q Queue) Top() Matrix         { return q[0] }
func (q Queue) Swap(i, j int)       { q[i], q[j] = q[j], q[i] }
func (q Queue) Less(i, j int) bool  { return q[i].height > q[j].height }
func (q *Queue) Push(x interface{}) { *q = append(*q, x.(Matrix)) }
func (q *Queue) Pop() interface{} {
	old, x := *q, (*q)[len(*q)-1]
	*q = old[:len(old)-1]
	return x
}

func getSkyline(buildings [][]int) [][]int {
	skys, lines, pq := make([][]int, 0), make([]int, 0), &Queue{}
	heap.Init(pq)
	for _, v := range buildings {
		lines = append(lines, v[0], v[1])
	}
	sort.Ints(lines)
	city, n := 0, len(buildings)
	for _, line := range lines {
		// 将所有符合条件的矩形加入队列
		for ; city < n && buildings[city][0] <= line && buildings[city][1] > line; city++ {
			v := Matrix{left: buildings[city][0], right: buildings[city][1], height: buildings[city][2]}
			heap.Push(pq, v)
		}
		// 从队列移除不符合条件的矩形
		for pq.Len() > 0 && pq.Top().right <= line {
			heap.Pop(pq)
		}
		high := 0
		// 队列为空说明是最右侧建筑物的终点，其轮廓点为 (line, 0)
		if pq.Len() != 0 {
			high = pq.Top().height
		}
		// 如果该点高度和前一个轮廓点一样的话，直接忽略
		if len(skys) > 0 && skys[len(skys)-1][1] == high {
			continue
		}
		skys = append(skys, []int{line, high})
	}
	return skys
}
```

### **C++**

```cpp
class Solution {
public:
    vector<pair<int, int>> getSkyline(vector<vector<int>>& buildings) {
        set<int> poss;
        map<int, int> m;
        for (auto v : buildings) {
            poss.insert(v[0]);
            poss.insert(v[1]);
        }

        int i = 0;
        for (int pos : poss)
            m.insert(pair<int, int>(pos, i++));

        vector<int> highs(m.size(), 0);
        for (auto v : buildings) {
            const int b = m[v[0]], e = m[v[1]];
            for (int i = b; i < e; ++i)
                highs[i] = max(highs[i], v[2]);
        }

        vector<pair<int, int>> res;
        vector<int> mm(poss.begin(), poss.end());
        for (int i = 0; i < highs.size(); ++i) {
            if (highs[i] != highs[i + 1])
                res.push_back(pair<int, int>(mm[i], highs[i]));
            else {
                const int start = i;
                res.push_back(pair<int, int>(mm[start], highs[i]));
                while (highs[i] == highs[i + 1])
                    ++i;
            }
        }
        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
