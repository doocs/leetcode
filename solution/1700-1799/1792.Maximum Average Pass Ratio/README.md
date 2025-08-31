---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1792.Maximum%20Average%20Pass%20Ratio/README.md
rating: 1817
source: 第 232 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [1792. 最大平均通过率](https://leetcode.cn/problems/maximum-average-pass-ratio)

[English Version](/solution/1700-1799/1792.Maximum%20Average%20Pass%20Ratio/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（增量大根堆）

假设一个班级当前的通过率为 $\frac{a}{b}$，那么如果我们将一个聪明的学生安排到此班级，那么班级的通过率就会变为 $\frac{a+1}{b+1}$。我们可以发现，通过率的增量为 $\frac{a+1}{b+1} - \frac{a}{b}$。

我们维护一个大根堆，堆中存储的是每个班级的通过率增量。

进行 `extraStudents` 次操作，每次从堆顶取出一个班级，将这个班级的人数和通过人数都加 $1$，然后将这个班级的通过率增量重新计算并放回堆中。重复这个过程，直到将所有的学生都分配完毕。

最后，我们将所有班级的通过率求和，然后除以班级数目，即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为班级数目。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    double maxAverageRatio(vector<vector<int>>& classes, int extraStudents) {
        priority_queue<tuple<double, int, int>> pq;
        for (auto& e : classes) {
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

#### Go

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
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function maxAverageRatio(classes: number[][], extraStudents: number): number {
    function calcGain(a: number, b: number): number {
        return (a + 1) / (b + 1) - a / b;
    }
    const pq = new PriorityQueue<[number, number]>(
        (p, q) => calcGain(q[0], q[1]) - calcGain(p[0], p[1]),
    );
    for (const [a, b] of classes) {
        pq.enqueue([a, b]);
    }
    while (extraStudents-- > 0) {
        const item = pq.dequeue();
        const [a, b] = item;
        pq.enqueue([a + 1, b + 1]);
    }
    let ans = 0;
    while (!pq.isEmpty()) {
        const item = pq.dequeue()!;
        const [a, b] = item;
        ans += a / b;
    }
    return ans / classes.length;
}
```

#### Rust

```rust
use std::cmp::Ordering;
use std::collections::BinaryHeap;

impl Solution {
    pub fn max_average_ratio(classes: Vec<Vec<i32>>, extra_students: i32) -> f64 {
        struct Node {
            gain: f64,
            a: i32,
            b: i32,
        }

        impl PartialEq for Node {
            fn eq(&self, other: &Self) -> bool {
                self.gain == other.gain
            }
        }
        impl Eq for Node {}

        impl PartialOrd for Node {
            fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
                self.gain.partial_cmp(&other.gain)
            }
        }
        impl Ord for Node {
            fn cmp(&self, other: &Self) -> Ordering {
                self.partial_cmp(other).unwrap()
            }
        }

        fn calc_gain(a: i32, b: i32) -> f64 {
            (a + 1) as f64 / (b + 1) as f64 - a as f64 / b as f64
        }

        let n = classes.len() as f64;
        let mut pq = BinaryHeap::new();

        for c in classes {
            let a = c[0];
            let b = c[1];
            pq.push(Node { gain: calc_gain(a, b), a, b });
        }

        let mut extra = extra_students;
        while extra > 0 {
            if let Some(mut node) = pq.pop() {
                node.a += 1;
                node.b += 1;
                node.gain = calc_gain(node.a, node.b);
                pq.push(node);
            }
            extra -= 1;
        }

        let mut sum = 0.0;
        while let Some(node) = pq.pop() {
            sum += node.a as f64 / node.b as f64;
        }

        sum / n
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
