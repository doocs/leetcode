# [1606. 找到处理最多请求的服务器](https://leetcode.cn/problems/find-servers-that-handled-most-number-of-requests)

[English Version](/solution/1600-1699/1606.Find%20Servers%20That%20Handled%20Most%20Number%20of%20Requests/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有 <code>k</code>&nbsp;个服务器，编号为 <code>0</code>&nbsp;到 <code>k-1</code>&nbsp;，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 <strong>不能同时处理超过一个请求</strong>&nbsp;。请求分配到服务器的规则如下：</p>

<ul>
	<li>第&nbsp;<code>i</code>&nbsp;（序号从 0 开始）个请求到达。</li>
	<li>如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。</li>
	<li>如果第&nbsp;<code>(i % k)</code>&nbsp;个服务器空闲，那么对应服务器会处理该请求。</li>
	<li>否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 <code>i</code>&nbsp;个服务器在忙，那么会查看第 <code>(i+1)</code>&nbsp;个服务器，第 <code>(i+2)</code>&nbsp;个服务器等等。</li>
</ul>

<p>给你一个 <strong>严格递增</strong>&nbsp;的正整数数组&nbsp;<code>arrival</code>&nbsp;，表示第&nbsp;<code>i</code>&nbsp;个任务的到达时间，和另一个数组&nbsp;<code>load</code>&nbsp;，其中&nbsp;<code>load[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个请求的工作量（也就是服务器完成它所需要的时间）。你的任务是找到 <strong>最繁忙的服务器</strong>&nbsp;。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。</p>

<p>请你返回包含所有&nbsp;<strong>最繁忙服务器</strong>&nbsp;序号的列表，你可以以任意顺序返回这个列表。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1606.Find%20Servers%20That%20Handled%20Most%20Number%20of%20Requests/images/load-1.png" style="height: 221px; width: 389px;" /></p>

<pre>
<strong>输入：</strong>k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3] 
<strong>输出：</strong>[1] 
<strong>解释：</strong>
所有服务器一开始都是空闲的。
前 3 个请求分别由前 3 台服务器依次处理。
请求 3 进来的时候，服务器 0 被占据，所以它被安排到下一台空闲的服务器，也就是服务器 1 。
请求 4 进来的时候，由于所有服务器都被占据，该请求被舍弃。
服务器 0 和 2 分别都处理了一个请求，服务器 1 处理了两个请求。所以服务器 1 是最忙的服务器。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
<strong>输出：</strong>[0]
<strong>解释：</strong>
前 3 个请求分别被前 3 个服务器处理。
请求 3 进来，由于服务器 0 空闲，它被服务器 0 处理。
服务器 0 处理了两个请求，服务器 1 和 2 分别处理了一个请求。所以服务器 0 是最忙的服务器。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>k = 3, arrival = [1,2,3], load = [10,12,11]
<strong>输出：</strong>[0,1,2]
<strong>解释：</strong>每个服务器分别处理了一个请求，所以它们都是最忙的服务器。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>k = 3, arrival = [1,2,3,4,8,9,10], load = [5,2,10,3,1,2,2]
<strong>输出：</strong>[1]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>k = 1, arrival = [1], load = [1]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arrival.length, load.length &lt;= 10<sup>5</sup></code></li>
	<li><code>arrival.length == load.length</code></li>
	<li><code>1 &lt;= arrival[i], load[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>arrival</code>&nbsp;保证 <strong>严格递增</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：有序集合 + 优先队列**

题目求的是最繁忙的服务器列表，因此可以想到用**哈希表**记录每个服务器处理的任务数，然后获取所有处理了最大任务数 mx 的服务器列表即可。关键的问题就在于，求出每个任务分配给了哪台服务器处理。

我们用 有序集合 free 存放所有的空闲服务器，优先队列 busy 存放正在处理请求的服务器的处理结束时间和对应的服务器编号，即二元组 `(end, server)`，优先队列满足队首元素的处理结束时间最小，用一个哈希表 cnt 记录每台服务器处理的任务数。

当第 i 个请求到达时，如果 busy 不为空，我们循环判断 busy 队首的任务结束时间是否小于等于当前请求的到达时间 `arrival[i]`，即 start。如果是，说明队首任务在此时刻已经处理结束，可以从 busy 队列中移出，循环判断。

接下来，如果 free 为空，说明当前没有空闲服务器能够处理第 i 个请求，直接 continue 丢弃；否则，查找 free 中大于等于 `i % k` 的第一个服务器，如果查找成功，那么由该服务器来处理该请求，否则，由 free 的第一个服务器（编号最小）来处理。假设该服务器是 server, 那么 `cnt[server]` 加 1，同时将二元组 `(end, server)` 放入优先队列 busy 中，并且将该 server 中有序集合 free 中移出。

最后，只需要获取 cnt 中的最大值 mx，找出处理了 mx 个任务数的服务器列表，即为答案。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedList


class Solution:
    def busiestServers(self, k: int, arrival: List[int], load: List[int]) -> List[int]:
        free = SortedList(range(k))
        busy = []
        cnt = [0] * k
        for i, (start, t) in enumerate(zip(arrival, load)):
            while busy and busy[0][0] <= start:
                free.add(busy[0][1])
                heappop(busy)
            if not free:
                continue
            j = free.bisect_left(i % k)
            if j == len(free):
                j = 0
            server = free[j]
            cnt[server] += 1
            heappush(busy, (start + t, server))
            free.remove(server)

        mx = max(cnt)
        return [i for i, v in enumerate(cnt) if v == mx]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] cnt = new int[k];
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; ++i) {
            free.add(i);
        }
        for (int i = 0; i < arrival.length; ++i) {
            int start = arrival[i];
            int end = start + load[i];
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                free.add(busy.poll()[1]);
            }
            if (free.isEmpty()) {
                continue;
            }
            Integer server = free.ceiling(i % k);
            if (server == null) {
                server = free.first();
            }
            ++cnt[server];
            busy.offer(new int[] {end, server});
            free.remove(server);
        }
        int mx = 0;
        for (int v : cnt) {
            mx = Math.max(mx, v);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            if (cnt[i] == mx) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

### **Go**

```go
func busiestServers(k int, arrival, load []int) []int {
	free := redblacktree.NewWithIntComparator()
	for i := 0; i < k; i++ {
		free.Put(i, nil)
	}
	busy := hp{}
	cnt := make([]int, k)
	for i, t := range arrival {
		for len(busy) > 0 && busy[0].end <= t {
			free.Put(busy[0].server, nil)
			heap.Pop(&busy)
		}
		if free.Size() == 0 {
			continue
		}
		p, _ := free.Ceiling(i % k)
		if p == nil {
			p = free.Left()
		}
		server := p.Key.(int)
		cnt[server]++
		heap.Push(&busy, pair{t + load[i], server})
		free.Remove(server)
	}
	mx := 0
	for _, v := range cnt {
		if v > mx {
			mx = v
		}
	}
	var ans []int
	for i, v := range cnt {
		if v == mx {
			ans = append(ans, i)
		}
	}
	return ans
}

type pair struct{ end, server int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].end < h[j].end }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **...**

```

```

<!-- tabs:end -->
