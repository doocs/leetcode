---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2532.Time%20to%20Cross%20a%20Bridge/README_EN.md
rating: 2588
source: Weekly Contest 327 Q4
tags:
    - Array
    - Simulation
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2532. Time to Cross a Bridge](https://leetcode.com/problems/time-to-cross-a-bridge)

[中文文档](/solution/2500-2599/2532.Time%20to%20Cross%20a%20Bridge/README.md)

## Description

<!-- description:start -->

<p>There are <code>k</code> workers who want to move <code>n</code> boxes from the right (old) warehouse to the left (new) warehouse. You are given the two integers <code>n</code> and <code>k</code>, and a 2D integer array <code>time</code> of size <code>k x 4</code> where <code>time[i] = [right<sub>i</sub>, pick<sub>i</sub>, left<sub>i</sub>, put<sub>i</sub>]</code>.</p>

<p>The warehouses are separated by a river and connected by a bridge. Initially, all <code>k</code> workers are waiting on the left side of the bridge. To move the boxes, the <code>i<sup>th</sup></code> worker can do the following:</p>

<ul>
	<li>Cross the bridge to the right side in <code>right<sub>i</sub></code> minutes.</li>
	<li>Pick a box from the right warehouse in <code>pick<sub>i</sub></code> minutes.</li>
	<li>Cross the bridge to the left side in <code>left<sub>i</sub></code> minutes.</li>
	<li>Put the box into the left warehouse in <code>put<sub>i</sub></code> minutes.</li>
</ul>

<p>The <code>i<sup>th</sup></code> worker is <strong>less efficient</strong> than the j<code><sup>th</sup></code> worker if either condition is met:</p>

<ul>
	<li><code>left<sub>i</sub> + right<sub>i</sub> &gt; left<sub>j</sub> + right<sub>j</sub></code></li>
	<li><code>left<sub>i</sub> + right<sub>i</sub> == left<sub>j</sub> + right<sub>j</sub></code> and <code>i &gt; j</code></li>
</ul>

<p>The following rules regulate the movement of the workers through the bridge:</p>

<ul>
	<li>Only one worker can use the bridge at a time.</li>
	<li>When the bridge is unused prioritize the <strong>least efficient</strong> worker on the right side to cross. If there are no workers on the right side, prioritize the <strong>least efficient</strong> worker on the left side to cross.</li>
	<li>If enough workers have already been dispatched from the left side to pick up all the remaining boxes, <strong>no more</strong> workers will be sent from the left side.</li>
</ul>

<p>Return the <strong>elapsed minutes</strong> at which the last box reaches the <strong>left side of the bridge</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<pre>
From 0 to 1 minutes: worker 2 crosses the bridge to the right.
From 1 to 2 minutes: worker 2 picks up a box from the right warehouse.
From 2 to 6 minutes: worker 2 crosses the bridge to the left.
From 6 to 7 minutes: worker 2 puts a box at the left warehouse.
The whole process ends after 7 minutes. We return 6 because the problem asks for the instance of time at which the last worker reaches the left side of the bridge.
</pre>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]</span></p>

<p><strong>Output:</strong> <span class="example-io">50</span></p>

<p><strong>Explanation:</strong></p>

<pre>
From 0  to 10: worker 1 crosses the bridge to the right.
From 10 to 20: worker 1 picks up a box from the right warehouse.
From 10 to 11: worker 0 crosses the bridge to the right.
From 11 to 20: worker 0 picks up a box from the right warehouse.
From 20 to 30: worker 1 crosses the bridge to the left.
From 30 to 40: worker 1 puts a box at the left warehouse.
From 30 to 31: worker 0 crosses the bridge to the left.
From 31 to 39: worker 0 puts a box at the left warehouse.
From 39 to 40: worker 0 crosses the bridge to the right.
From 40 to 49: worker 0 picks up a box from the right warehouse.
From 49 to 50: worker 0 crosses the bridge to the left.
</pre>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 10<sup>4</sup></code></li>
	<li><code>time.length == k</code></li>
	<li><code>time[i].length == 4</code></li>
	<li><code>1 &lt;= leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub> &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findCrossingTime(self, n: int, k: int, time: List[List[int]]) -> int:
        time.sort(key=lambda x: x[0] + x[2])
        cur = 0
        wait_in_left, wait_in_right = [], []
        work_in_left, work_in_right = [], []
        for i in range(k):
            heappush(wait_in_left, -i)
        while 1:
            while work_in_left:
                t, i = work_in_left[0]
                if t > cur:
                    break
                heappop(work_in_left)
                heappush(wait_in_left, -i)
            while work_in_right:
                t, i = work_in_right[0]
                if t > cur:
                    break
                heappop(work_in_right)
                heappush(wait_in_right, -i)
            left_to_go = n > 0 and wait_in_left
            right_to_go = bool(wait_in_right)
            if not left_to_go and not right_to_go:
                nxt = inf
                if work_in_left:
                    nxt = min(nxt, work_in_left[0][0])
                if work_in_right:
                    nxt = min(nxt, work_in_right[0][0])
                cur = nxt
                continue
            if right_to_go:
                i = -heappop(wait_in_right)
                cur += time[i][2]
                if n == 0 and not wait_in_right and not work_in_right:
                    return cur
                heappush(work_in_left, (cur + time[i][3], i))
            else:
                i = -heappop(wait_in_left)
                cur += time[i][0]
                n -= 1
                heappush(work_in_right, (cur + time[i][1], i))
```

#### Java

```java
class Solution {
    public int findCrossingTime(int n, int k, int[][] time) {
        int[][] t = new int[k][5];
        for (int i = 0; i < k; ++i) {
            int[] x = time[i];
            t[i] = new int[] {x[0], x[1], x[2], x[3], i};
        }
        Arrays.sort(t, (a, b) -> {
            int x = a[0] + a[2], y = b[0] + b[2];
            return x == y ? a[4] - b[4] : x - y;
        });
        int cur = 0;
        PriorityQueue<Integer> waitInLeft = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> waitInRight = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<int[]> workInLeft = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> workInRight = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < k; ++i) {
            waitInLeft.offer(i);
        }
        while (true) {
            while (!workInLeft.isEmpty()) {
                int[] p = workInLeft.peek();
                if (p[0] > cur) {
                    break;
                }
                waitInLeft.offer(workInLeft.poll()[1]);
            }
            while (!workInRight.isEmpty()) {
                int[] p = workInRight.peek();
                if (p[0] > cur) {
                    break;
                }
                waitInRight.offer(workInRight.poll()[1]);
            }
            boolean leftToGo = n > 0 && !waitInLeft.isEmpty();
            boolean rightToGo = !waitInRight.isEmpty();
            if (!leftToGo && !rightToGo) {
                int nxt = 1 << 30;
                if (!workInLeft.isEmpty()) {
                    nxt = Math.min(nxt, workInLeft.peek()[0]);
                }
                if (!workInRight.isEmpty()) {
                    nxt = Math.min(nxt, workInRight.peek()[0]);
                }
                cur = nxt;
                continue;
            }
            if (rightToGo) {
                int i = waitInRight.poll();
                cur += t[i][2];
                if (n == 0 && waitInRight.isEmpty() && workInRight.isEmpty()) {
                    return cur;
                }
                workInLeft.offer(new int[] {cur + t[i][3], i});
            } else {
                int i = waitInLeft.poll();
                cur += t[i][0];
                --n;
                workInRight.offer(new int[] {cur + t[i][1], i});
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findCrossingTime(int n, int k, vector<vector<int>>& time) {
        using pii = pair<int, int>;
        for (int i = 0; i < k; ++i) {
            time[i].push_back(i);
        }
        sort(time.begin(), time.end(), [](auto& a, auto& b) {
            int x = a[0] + a[2], y = b[0] + b[2];
            return x == y ? a[4] < b[4] : x < y;
        });
        int cur = 0;
        priority_queue<int> waitInLeft, waitInRight;
        priority_queue<pii, vector<pii>, greater<pii>> workInLeft, workInRight;
        for (int i = 0; i < k; ++i) {
            waitInLeft.push(i);
        }
        while (true) {
            while (!workInLeft.empty()) {
                auto [t, i] = workInLeft.top();
                if (t > cur) {
                    break;
                }
                workInLeft.pop();
                waitInLeft.push(i);
            }
            while (!workInRight.empty()) {
                auto [t, i] = workInRight.top();
                if (t > cur) {
                    break;
                }
                workInRight.pop();
                waitInRight.push(i);
            }
            bool leftToGo = n > 0 && !waitInLeft.empty();
            bool rightToGo = !waitInRight.empty();
            if (!leftToGo && !rightToGo) {
                int nxt = 1 << 30;
                if (!workInLeft.empty()) {
                    nxt = min(nxt, workInLeft.top().first);
                }
                if (!workInRight.empty()) {
                    nxt = min(nxt, workInRight.top().first);
                }
                cur = nxt;
                continue;
            }
            if (rightToGo) {
                int i = waitInRight.top();
                waitInRight.pop();
                cur += time[i][2];
                if (n == 0 && waitInRight.empty() && workInRight.empty()) {
                    return cur;
                }
                workInLeft.push({cur + time[i][3], i});
            } else {
                int i = waitInLeft.top();
                waitInLeft.pop();
                cur += time[i][0];
                --n;
                workInRight.push({cur + time[i][1], i});
            }
        }
    }
};
```

#### Go

```go
func findCrossingTime(n int, k int, time [][]int) int {
	sort.SliceStable(time, func(i, j int) bool { return time[i][0]+time[i][2] < time[j][0]+time[j][2] })
	waitInLeft := hp{}
	waitInRight := hp{}
	workInLeft := hp2{}
	workInRight := hp2{}
	for i := range time {
		heap.Push(&waitInLeft, i)
	}
	cur := 0
	for {
		for len(workInLeft) > 0 {
			if workInLeft[0].t > cur {
				break
			}
			heap.Push(&waitInLeft, heap.Pop(&workInLeft).(pair).i)
		}
		for len(workInRight) > 0 {
			if workInRight[0].t > cur {
				break
			}
			heap.Push(&waitInRight, heap.Pop(&workInRight).(pair).i)
		}
		leftToGo := n > 0 && waitInLeft.Len() > 0
		rightToGo := waitInRight.Len() > 0
		if !leftToGo && !rightToGo {
			nxt := 1 << 30
			if len(workInLeft) > 0 {
				nxt = min(nxt, workInLeft[0].t)
			}
			if len(workInRight) > 0 {
				nxt = min(nxt, workInRight[0].t)
			}
			cur = nxt
			continue
		}
		if rightToGo {
			i := heap.Pop(&waitInRight).(int)
			cur += time[i][2]
			if n == 0 && waitInRight.Len() == 0 && len(workInRight) == 0 {
				return cur
			}
			heap.Push(&workInLeft, pair{cur + time[i][3], i})
		} else {
			i := heap.Pop(&waitInLeft).(int)
			cur += time[i][0]
			n--
			heap.Push(&workInRight, pair{cur + time[i][1], i})
		}
	}
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

type pair struct{ t, i int }
type hp2 []pair

func (h hp2) Len() int           { return len(h) }
func (h hp2) Less(i, j int) bool { return h[i].t < h[j].t }
func (h hp2) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
