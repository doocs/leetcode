# [1882. 使用服务器处理任务](https://leetcode.cn/problems/process-tasks-using-servers)

[English Version](/solution/1800-1899/1882.Process%20Tasks%20Using%20Servers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>下标从 0 开始</strong> 的整数数组 <code>servers</code> 和 <code>tasks</code> ，长度分别为 <code>n</code>​​​​​​ 和 <code>m</code>​​​​​​ 。<code>servers[i]</code> 是第 <code>i<sup>​​​​​​</sup></code>​​​​ 台服务器的 <strong>权重</strong> ，而 <code>tasks[j]</code> 是处理第 <code>j<sup>​​​​​​</sup></code> 项任务 <strong>所需要的时间</strong>（单位：秒）。</p>

<p>你正在运行一个仿真系统，在处理完所有任务后，该系统将会关闭。每台服务器只能同时处理一项任务。第 <code>0</code> 项任务在第 <code>0</code> 秒可以开始处理，相应地，第 <code>j</code> 项任务在第 <code>j</code> 秒可以开始处理。处理第 <code>j</code> 项任务时，你需要为它分配一台 <strong>权重最小</strong> 的空闲服务器。如果存在多台相同权重的空闲服务器，请选择 <strong>下标最小</strong> 的服务器。如果一台空闲服务器在第 <code>t</code> 秒分配到第 <code>j</code> 项任务，那么在 <code>t + tasks[j]</code> 时它将恢复空闲状态。</p>

<p>如果没有空闲服务器，则必须等待，直到出现一台空闲服务器，并 <strong>尽可能早</strong> 地处理剩余任务。 如果有多项任务等待分配，则按照 <strong>下标递增</strong> 的顺序完成分配。</p>

<p>如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。</p>

<p>构建长度为 <code>m</code> 的答案数组 <code>ans</code> ，其中 <code>ans[j]</code> 是第 <code>j</code> 项任务分配的服务器的下标。</p>

<p>返回答案数组<em> </em><code>ans</code>​​​​ 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>servers = [3,3,2], tasks = [1,2,3,2,1,2]
<strong>输出：</strong>[2,2,0,2,1,2]
<strong>解释：</strong>事件按时间顺序如下：
- 0 秒时，第 0 项任务加入到任务队列，使用第 2 台服务器处理到 1 秒。
- 1 秒时，第 2 台服务器空闲，第 1 项任务加入到任务队列，使用第 2 台服务器处理到 3 秒。
- 2 秒时，第 2 项任务加入到任务队列，使用第 0 台服务器处理到 5 秒。
- 3 秒时，第 2 台服务器空闲，第 3 项任务加入到任务队列，使用第 2 台服务器处理到 5 秒。
- 4 秒时，第 4 项任务加入到任务队列，使用第 1 台服务器处理到 5 秒。
- 5 秒时，所有服务器都空闲，第 5 项任务加入到任务队列，使用第 2 台服务器处理到 7 秒。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
<strong>输出：</strong>[1,4,1,4,1,3,2]
<strong>解释：</strong>事件按时间顺序如下：
- 0 秒时，第 0 项任务加入到任务队列，使用第 1 台服务器处理到 2 秒。
- 1 秒时，第 1 项任务加入到任务队列，使用第 4 台服务器处理到 2 秒。
- 2 秒时，第 1 台和第 4 台服务器空闲，第 2 项任务加入到任务队列，使用第 1 台服务器处理到 4 秒。
- 3 秒时，第 3 项任务加入到任务队列，使用第 4 台服务器处理到 7 秒。
- 4 秒时，第 1 台服务器空闲，第 4 项任务加入到任务队列，使用第 1 台服务器处理到 9 秒。
- 5 秒时，第 5 项任务加入到任务队列，使用第 3 台服务器处理到 7 秒。
- 6 秒时，第 6 项任务加入到任务队列，使用第 2 台服务器处理到 7 秒。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>servers.length == n</code></li>
	<li><code>tasks.length == m</code></li>
	<li><code>1 <= n, m <= 2 * 10<sup>5</sup></code></li>
	<li><code>1 <= servers[i], tasks[j] <= 2 * 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“优先队列”实现。

定义两个优先级队列，分别表示空闲服务器、使用中的服务器。其中：空闲服务器 `idle` 依据**权重、下标**排序；而使用中的服务器 `busy` 依据**结束时间、权重、下标**排序。

遍历任务：

-   若有使用中的服务器小于任务开始时间，将其加入到空闲服务器队列 `idle` 中；
-   若当前有空闲服务器，那么在空闲队列 `idle` 中取出权重最小的服务器，将其加入使用中的队列 `busy` 中；
-   若当前没有空闲服务器，那么在使用队列 `busy` 中找出最早结束时间且权重最小的服务器，重新加入使用中的队列 `busy` 中。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        idle, busy = [], []
        for i, weight in enumerate(servers):
            heappush(idle, (weight, i))
        res = []
        for start, cost in enumerate(tasks):
            while busy and busy[0][0] <= start:
                _, s, i = heappop(busy)
                heappush(idle, (s, i))
            if idle:
                s, i = heappop(idle)
                heappush(busy, (start + cost, s, i))
            else:
                t, s, i = heappop(busy)
                heappush(busy, (t + cost, s, i))
            res.append(i)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int m = tasks.length, n = servers.length;
        PriorityQueue<int[]> idle = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] == b[1] ? a[2] - b[2] : a[1] - b[1];
            }
            return a[0] - b[0];
        });
        for (int i = 0; i < n; ++i) {
            idle.offer(new int[]{servers[i], i});
        }
        int[] res = new int[m];
        int j = 0;
        for (int start = 0; start < m; ++start) {
            int cost = tasks[start];
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                int[] item = busy.poll();
                idle.offer(new int[]{item[1], item[2]});
            }
            if (!idle.isEmpty()) {
                int[] item = idle.poll();
                res[j++] = item[1];
                busy.offer(new int[]{start + cost, item[0], item[1]});
            } else {
                int[] item = busy.poll();
                res[j++] = item[2];
                busy.offer(new int[]{item[0] + cost, item[1], item[2]});
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
