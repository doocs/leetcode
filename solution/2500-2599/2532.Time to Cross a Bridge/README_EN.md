# [2532. Time to Cross a Bridge](https://leetcode.com/problems/time-to-cross-a-bridge)

[中文文档](/solution/2500-2599/2532.Time%20to%20Cross%20a%20Bridge/README.md)

## Description

<p>There are <code>k</code> workers who want to move <code>n</code> boxes from an old warehouse to a new one. You are given the two integers <code>n</code> and <code>k</code>, and a 2D integer array <code>time</code> of size <code>k x 4</code> where <code>time[i] = [leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub>]</code>.</p>

<p>The warehouses are separated by a river and connected by a bridge. The old warehouse is on the right bank of the river, and the new warehouse is on the left bank of the river. Initially, all <code>k</code> workers are waiting on the left side of the bridge. To move the boxes, the <code>i<sup>th</sup></code> worker (<strong>0-indexed</strong>) can :</p>

<ul>
	<li>Cross the bridge from the left bank (new warehouse) to the right bank (old warehouse) in <code>leftToRight<sub>i</sub></code> minutes.</li>
	<li>Pick a box from the old warehouse and return to the bridge in <code>pickOld<sub>i</sub></code> minutes. Different workers can pick up their boxes simultaneously.</li>
	<li>Cross the bridge from the right bank (old warehouse) to the left bank (new warehouse) in <code>rightToLeft<sub>i</sub></code> minutes.</li>
	<li>Put the box in the new warehouse and return to the bridge in <code>putNew<sub>i</sub></code> minutes. Different workers can put their boxes simultaneously.</li>
</ul>

<p>A worker <code>i</code> is <strong>less efficient</strong> than a worker <code>j</code> if either condition is met:</p>

<ul>
	<li><code>leftToRight<sub>i</sub> + rightToLeft<sub>i</sub> &gt; leftToRight<sub>j</sub> + rightToLeft<sub>j</sub></code></li>
	<li><code>leftToRight<sub>i</sub> + rightToLeft<sub>i</sub> == leftToRight<sub>j</sub> + rightToLeft<sub>j</sub></code> and <code>i &gt; j</code></li>
</ul>

<p>The following rules regulate the movement of the workers through the bridge :</p>

<ul>
	<li>If a worker <code>x</code> reaches the bridge while another worker <code>y</code> is crossing the bridge, <code>x</code> waits at their side of the bridge.</li>
	<li>If the bridge is free, the worker waiting on the right side of the bridge gets to cross the bridge. If more than one worker is waiting on the right side, the one with <strong>the lowest efficiency</strong> crosses first.</li>
	<li>If the bridge is free and no worker is waiting on the right side, and at least one box remains at the old warehouse, the worker on the left side of the river gets to cross the bridge. If more than one worker is waiting on the left side, the one with <strong>the lowest efficiency</strong> crosses first.</li>
</ul>

<p>Return <em>the instance of time at which the last worker <strong>reaches the left bank</strong> of the river after all n boxes have been put in the new warehouse</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
<strong>Output:</strong> 6
<strong>Explanation: </strong>
From 0 to 1: worker 2 crosses the bridge from the left bank to the right bank.
From 1 to 2: worker 2 picks up a box from the old warehouse.
From 2 to 6: worker 2 crosses the bridge from the right bank to the left bank.
From 6 to 7: worker 2 puts a box at the new warehouse.
The whole process ends after 7 minutes. We return 6 because the problem asks for the instance of time at which the last worker reaches the left bank.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
<strong>Output:</strong> 50
<strong>Explanation:</strong> 
From 0 &nbsp;to 10: worker 1 crosses the bridge from the left bank to the right bank.
From 10 to 20: worker 1 picks up a box from the old warehouse.
From 10 to 11: worker 0 crosses the bridge from the left bank to the right bank.
From 11 to 20: worker 0 picks up a box from the old warehouse.
From 20 to 30: worker 1 crosses the bridge from the right bank to the left bank.
From 30 to 40: worker 1 puts a box at the new warehouse.
From 30 to 31: worker 0 crosses the bridge from the right bank to the left bank.
From 31 to 39: worker 0 puts a box at the new warehouse.
From 39 to 40: worker 0 crosses the bridge from the left bank to the right bank.
From 40 to 49: worker 0 picks up a box from the old warehouse.
From 49 to 50: worker 0 crosses the bridge from the right bank to the left bank.
From 50 to 58: worker 0 puts a box at the new warehouse.
The whole process ends after 58 minutes. We return 50 because the problem asks for the instance of time at which the last worker reaches the left bank.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 10<sup>4</sup></code></li>
	<li><code>time.length == k</code></li>
	<li><code>time[i].length == 4</code></li>
	<li><code>1 &lt;= leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub> &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

type pair struct{ t, i int }
type hp2 []pair

func (h hp2) Len() int            { return len(h) }
func (h hp2) Less(i, j int) bool  { return h[i].t < h[j].t }
func (h hp2) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
