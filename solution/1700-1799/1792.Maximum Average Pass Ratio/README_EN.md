---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1792.Maximum%20Average%20Pass%20Ratio/README_EN.md
rating: 1817
source: Weekly Contest 232 Q3
tags:
    - Greedy
    - Array
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1792. Maximum Average Pass Ratio](https://leetcode.com/problems/maximum-average-pass-ratio)

[中文文档](/solution/1700-1799/1792.Maximum%20Average%20Pass%20Ratio/README.md)

## Description

<!-- description:start -->

<p>There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array <code>classes</code>, where <code>classes[i] = [pass<sub>i</sub>, total<sub>i</sub>]</code>. You know beforehand that in the <code>i<sup>th</sup></code> class, there are <code>total<sub>i</sub></code> total students, but only <code>pass<sub>i</sub></code> number of students will pass the exam.</p>

<p>You are also given an integer <code>extraStudents</code>. There are another <code>extraStudents</code> brilliant students that are <strong>guaranteed</strong> to pass the exam of any class they are assigned to. You want to assign each of the <code>extraStudents</code> students to a class in a way that <strong>maximizes</strong> the <strong>average</strong> pass ratio across <strong>all</strong> the classes.</p>

<p>The <strong>pass ratio</strong> of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The <strong>average pass ratio</strong> is the sum of pass ratios of all the classes divided by the number of the classes.</p>

<p>Return <em>the <strong>maximum</strong> possible average pass ratio after assigning the </em><code>extraStudents</code><em> students. </em>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> classes = [[1,2],[3,5],[2,2]], <code>extraStudents</code> = 2
<strong>Output:</strong> 0.78333
<strong>Explanation:</strong> You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> classes = [[2,4],[3,9],[4,5],[2,10]], <code>extraStudents</code> = 4
<strong>Output:</strong> 0.53485
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= classes.length &lt;= 10<sup>5</sup></code></li>
	<li><code>classes[i].length == 2</code></li>
	<li><code>1 &lt;= pass<sub>i</sub> &lt;= total<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= extraStudents &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Max-Heap of Increment)

Suppose a class currently has a pass rate of $\frac{a}{b}$. If we arrange a smart student into this class, then the pass rate of the class will become $\frac{a+1}{b+1}$. We can find that the increment of the pass rate is $\frac{a+1}{b+1} - \frac{a}{b}$.

We maintain a max-heap, which stores the increment of the pass rate for each class.

Perform `extraStudents` operations, each time taking a class from the top of the heap, adding $1$ to both the number of students and the number of passes in this class, then recalculating the increment of the pass rate of this class and putting it back into the heap. Repeat this process until all students are allocated.

Finally, we sum up the pass rates of all classes, and then divide by the number of classes to get the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of classes.

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
