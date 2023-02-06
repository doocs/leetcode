# [2532. 过桥的时间](https://leetcode.cn/problems/time-to-cross-a-bridge)

[English Version](/solution/2500-2599/2532.Time%20to%20Cross%20a%20Bridge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>共有 <code>k</code> 位工人计划将 <code>n</code> 个箱子从旧仓库移动到新仓库。给你两个整数 <code>n</code> 和 <code>k</code>，以及一个二维整数数组 <code>time</code> ，数组的大小为 <code>k x 4</code> ，其中 <code>time[i] = [leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub>]</code> 。</p>

<p>一条河将两座仓库分隔，只能通过一座桥通行。旧仓库位于河的右岸，新仓库在河的左岸。开始时，所有 <code>k</code> 位工人都在桥的左侧等待。为了移动这些箱子，第 <code>i</code> 位工人（下标从 <strong>0</strong> 开始）可以：</p>

<ul>
	<li>从左岸（新仓库）跨过桥到右岸（旧仓库），用时 <code>leftToRight<sub>i</sub></code> 分钟。</li>
	<li>从旧仓库选择一个箱子，并返回到桥边，用时 <code>pickOld<sub>i</sub></code> 分钟。不同工人可以同时搬起所选的箱子。</li>
	<li>从右岸（旧仓库）跨过桥到左岸（新仓库），用时 <code>rightToLeft<sub>i</sub></code> 分钟。</li>
	<li>将箱子放入新仓库，并返回到桥边，用时 <code>putNew<sub>i</sub></code> 分钟。不同工人可以同时放下所选的箱子。</li>
</ul>

<p>如果满足下面任一条件，则认为工人 <code>i</code> 的 <strong>效率低于</strong> 工人 <code>j</code> ：</p>

<ul>
	<li><code>leftToRight<sub>i</sub> + rightToLeft<sub>i</sub> &gt; leftToRight<sub>j</sub> + rightToLeft<sub>j</sub></code></li>
	<li><code>leftToRight<sub>i</sub> + rightToLeft<sub>i</sub> == leftToRight<sub>j</sub> + rightToLeft<sub>j</sub></code> 且 <code>i &gt; j</code></li>
</ul>

<p>工人通过桥时需要遵循以下规则：</p>

<ul>
	<li>如果工人 <code>x</code> 到达桥边时，工人 <code>y</code> 正在过桥，那么工人 <code>x</code> 需要在桥边等待。</li>
	<li>如果没有正在过桥的工人，那么在桥右边等待的工人可以先过桥。如果同时有多个工人在右边等待，那么 <strong>效率最低</strong> 的工人会先过桥。</li>
	<li>如果没有正在过桥的工人，且桥右边也没有在等待的工人，同时旧仓库还剩下至少一个箱子需要搬运，此时在桥左边的工人可以过桥。如果同时有多个工人在左边等待，那么 <strong>效率最低</strong> 的工人会先过桥。</li>
</ul>

<p>所有 <code>n</code> 个盒子都需要放入新仓库，<span class="text-only" data-eleid="8" style="white-space: pre;">请你返回最后一个搬运箱子的工人 </span><strong><span class="text-only" data-eleid="9" style="white-space: pre;">到达河左岸</span></strong><span class="text-only" data-eleid="10" style="white-space: pre;"> 的时间。</span></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
<strong>输出：</strong>6
<strong>解释：</strong>
从 0 到 1 ：工人 2 从左岸过桥到达右岸。
从 1 到 2 ：工人 2 从旧仓库搬起一个箱子。
从 2 到 6 ：工人 2 从右岸过桥到达左岸。
从 6 到 7 ：工人 2 将箱子放入新仓库。
整个过程在 7 分钟后结束。因为问题关注的是最后一个工人到达左岸的时间，所以返回 6 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
<strong>输出：</strong>50
<strong>解释：</strong>
从 0 到 10 ：工人 1 从左岸过桥到达右岸。
从 10 到 20 ：工人 1 从旧仓库搬起一个箱子。
从 10 到 11 ：工人 0 从左岸过桥到达右岸。
从 11 到 20 ：工人 0 从旧仓库搬起一个箱子。
从 20 到 30 ：工人 1 从右岸过桥到达左岸。
从 30 到 40 ：工人 1 将箱子放入新仓库。
从 30 到 31 ：工人 0 从右岸过桥到达左岸。
从 31 到 39 ：工人 0 将箱子放入新仓库。
从 39 到 40 ：工人 0 从左岸过桥到达右岸。
从 40 到 49 ：工人 0 从旧仓库搬起一个箱子。
从 49 到 50 ：工人 0 从右岸过桥到达左岸。
从 50 到 58 ：工人 0 将箱子放入新仓库。
整个过程在 58 分钟后结束。因为问题关注的是最后一个工人到达左岸的时间，所以返回 50 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 10<sup>4</sup></code></li>
	<li><code>time.length == k</code></li>
	<li><code>time[i].length == 4</code></li>
	<li><code>1 &lt;= leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub> &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（大小根堆） + 模拟**

我们先将工人按照效率从高到底排序，这样，下标越大的工人，效率越低。

接下来，我们用四个优先队列模拟工人的状态：

-   `wait_in_left`：大根堆，存储当前在左岸等待的工人的下标；
-   `wait_in_right`：大根堆，存储当前在右岸等待的工人的下标；
-   `work_in_left`：小根堆，存储当前在左岸工作的工人放好箱子的时间以及工人的下标；
-   `work_in_right`：小根堆，存储当前在右岸工作的工人拿好箱子的时间以及工人的下标。

初始时，所有工人都在左岸，因此 `wait_in_left` 中存储所有工人的下标。用变量 `cur` 记录当前时间。

然后，我们模拟整个过程。我们先判断当前时刻，`work_in_left` 是否有工人已经放好箱子，如果有，我们将工人放入 `wait_in_left` 中，然后将工人从 `work_in_left` 中移除。同理，我们再判断 `work_in_right` 是否有工人已经放好箱子，如果有，我们将工人放入 `wait_in_right` 中，然后将工人从 `work_in_right` 中移除。

接着，我们判断当前时刻是否有工人在左岸等待，记为 `left_to_go`，同时，我们判断当前时刻是否有工人在右岸等待，记为 `right_to_go`。如果不存在等待过岸的工人，我们直接将 `cur` 更新为下一个工人放好箱子的时间，然后继续模拟过程。

如果 `right_to_go` 为 `true`，我们从 `wait_in_right` 中取出一个工人，更新 `cur` 为当前时间加上该工人从右岸过左岸的时间，如果此时所有工人都已经过了右岸，我们直接将 `cur` 作为答案返回；否则，我们将该工人放入 `work_in_left` 中。

如果 `left_to_go` 为 `true`，我们从 `wait_in_left` 中取出一个工人，更新 `cur` 为当前时间加上该工人从左岸过右岸的时间，然后将该工人放入 `work_in_right` 中，并且将箱子数量减一。

循环上述过程，直到箱子数量为零，此时 `cur` 即为答案。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(k)$。其中 $n$ 和 $k$ 分别为工人数量和箱子数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
