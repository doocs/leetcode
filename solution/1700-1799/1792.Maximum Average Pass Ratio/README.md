# [1792. 最大平均通过率](https://leetcode.cn/problems/maximum-average-pass-ratio)

[English Version](/solution/1700-1799/1792.Maximum%20Average%20Pass%20Ratio/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组 <code>classes</code> ，其中 <code>classes[i] = [pass<sub>i</sub>, total<sub>i</sub>]</code> ，表示你提前知道了第 <code>i</code> 个班级总共有 <code>total<sub>i</sub></code> 个学生，其中只有 <code>pass<sub>i</sub></code> 个学生可以通过考试。</p>

<p>给你一个整数 <code>extraStudents</code> ，表示额外有 <code>extraStudents</code> 个聪明的学生，他们 <strong>一定</strong> 能通过任何班级的期末考。你需要给这 <code>extraStudents</code> 个学生每人都安排一个班级，使得 <strong>所有</strong> 班级的 <strong>平均</strong> 通过率 <strong>最大</strong> 。</p>

<p>一个班级的 <strong>通过率</strong> 等于这个班级通过考试的学生人数除以这个班级的总人数。<strong>平均通过率</strong> 是所有班级的通过率之和除以班级数目。</p>

<p>请你返回在安排这 <code><span style="">extraStudents</span></code> 个学生去对应班级后的 <strong>最大</strong> 平均通过率。与标准答案误差范围在 <code>10<sup>-5</sup></code> 以内的结果都会视为正确结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>classes = [[1,2],[3,5],[2,2]], <code>extraStudents</code> = 2
<b>输出：</b>0.78333
<b>解释：</b>你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>classes = [[2,4],[3,9],[4,5],[2,10]], <code>extraStudents</code> = 4
<strong>输出：</strong>0.53485
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= classes.length <= 10<sup>5</sup></code></li>
	<li><code>classes[i].length == 2</code></li>
	<li><code>1 <= pass<sub>i</sub> <= total<sub>i</sub> <= 10<sup>5</sup></code></li>
	<li><code>1 <= extraStudents <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（增量大根堆）**

假设一个班级当前的通过率为 $\frac{a}{b}$，那么如果我们将一个聪明的学生安排到此班级，那么班级的通过率就会变为 $\frac{a+1}{b+1}$。我们可以发现，通过率的增量为 $\frac{a+1}{b+1} - \frac{a}{b}$。

我们维护一个大根堆，堆中存储的是每个班级的通过率增量。

进行 `extraStudents` 次操作，每次从堆顶取出一个班级，将这个班级的人数和通过人数都加 $1$，然后将这个班级的通过率增量重新计算并放回堆中。重复这个过程，直到将所有的学生都分配完毕。

最后，我们将所有班级的通过率求和，然后除以班级数目，即为答案。

时间复杂度 $O(n\log n)$，其中 $n$ 为班级数目。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxAverageRatio(self, classes: List[List[int]], extraStudents: int) -> float:
        h = [(a / b - (a + 1) / (b + 1), a, b) for a, b in classes]
        heapify(h)
        for _ in range(extraStudents):
            _, a, b = heappop(h)
            a, b = a + 1, b + 1
            heappush(h, (a / b - (a + 1) / (b + 1), a, b))
        return sum(v[1] / v[2] for v in h) / len(classes)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            double x = (a[0] + 1) / (a[1] + 1) - a[0] / a[1];
            double y = (b[0] + 1) / (b[1] + 1) - b[0] / b[1];
            return Double.compare(y, x);
        });
        for (var e : classes) {
            pq.offer(new double[] {e[0], e[1]});
        }
        while (extraStudents-- > 0) {
            var e = pq.poll();
            double a = e[0] + 1, b = e[1] + 1;
            pq.offer(new double[] {a, b});
        }
        double ans = 0;
        while (!pq.isEmpty()) {
            var e = pq.poll();
            ans += e[0] / e[1];
        }
        return ans / classes.length;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double maxAverageRatio(vector<vector<int>>& classes, int extraStudents) {
        priority_queue<tuple<double, int, int>> pq;
        for(auto& e : classes) {
            int a = e[0], b = e[1];
            double x = (double) (a + 1) / (b + 1) - (double) a / b;
            pq.push({x, a, b});
        }
        while (extraStudents--) {
            auto [_, a, b] = pq.top();
            pq.pop();
            a++;
            b++;
            double x = (double) (a + 1) / (b + 1) - (double) a / b;
            pq.push({x, a, b});
        }
        double ans = 0;
        while (pq.size()) {
            auto [_, a, b] = pq.top();
            pq.pop();
            ans += (double) a / b;
        }
        return ans / classes.size();
    }
};
```

### **Go**

```go
func maxAverageRatio(classes [][]int, extraStudents int) float64 {
	pq := hp{}
	for _, e := range classes {
		a, b := e[0], e[1]
		x := float64(a+1)/float64(b+1) - float64(a)/float64(b)
		heap.Push(&pq, tuple{x, a, b})
	}
	for i := 0; i < extraStudents; i++ {
		e := heap.Pop(&pq).(tuple)
		a, b := e.a+1, e.b+1
		x := float64(a+1)/float64(b+1) - float64(a)/float64(b)
		heap.Push(&pq, tuple{x, a, b})
	}
	var ans float64
	for len(pq) > 0 {
		e := heap.Pop(&pq).(tuple)
		ans += float64(e.a) / float64(e.b)
	}
	return ans / float64(len(classes))
}

type tuple struct {
	x float64
	a int
	b int
}

type hp []tuple

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.x > b.x
}
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **...**

```

```

<!-- tabs:end -->
