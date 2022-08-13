# [1942. 最小未被占据椅子的编号](https://leetcode.cn/problems/the-number-of-the-smallest-unoccupied-chair)

[English Version](/solution/1900-1999/1942.The%20Number%20of%20the%20Smallest%20Unoccupied%20Chair/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个朋友在举办一个派对，这些朋友从 <code>0</code> 到 <code>n - 1</code> 编号。派对里有 <strong>无数</strong> 张椅子，编号为 <code>0</code> 到 <code>infinity</code> 。当一个朋友到达派对时，他会占据 <strong>编号最小</strong> 且未被占据的椅子。</p>

<ul>
	<li>比方说，当一个朋友到达时，如果椅子 <code>0</code> ，<code>1</code> 和 <code>5</code> 被占据了，那么他会占据 <code>2</code> 号椅子。</li>
</ul>

<p>当一个朋友离开派对时，他的椅子会立刻变成未占据状态。如果同一时刻有另一个朋友到达，可以立即占据这张椅子。</p>

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>times</code> ，其中 <code>times[i] = [arrival<sub>i</sub>, leaving<sub>i</sub>]</code> 表示第 <code>i</code> 个朋友到达和离开的时刻，同时给你一个整数 <code>targetFriend</code> 。所有到达时间 <strong>互不相同</strong> 。</p>

<p>请你返回编号为 <code>targetFriend</code> 的朋友占据的 <strong>椅子编号</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>times = [[1,4],[2,3],[4,6]], targetFriend = 1
<b>输出：</b>1
<b>解释：</b>
- 朋友 0 时刻 1 到达，占据椅子 0 。
- 朋友 1 时刻 2 到达，占据椅子 1 。
- 朋友 1 时刻 3 离开，椅子 1 变成未占据。
- 朋友 0 时刻 4 离开，椅子 0 变成未占据。
- 朋友 2 时刻 4 到达，占据椅子 0 。
朋友 1 占据椅子 1 ，所以返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>times = [[3,10],[1,5],[2,6]], targetFriend = 0
<b>输出：</b>2
<b>解释：</b>
- 朋友 1 时刻 1 到达，占据椅子 0 。
- 朋友 2 时刻 2 到达，占据椅子 1 。
- 朋友 0 时刻 3 到达，占据椅子 2 。
- 朋友 1 时刻 5 离开，椅子 0 变成未占据。
- 朋友 2 时刻 6 离开，椅子 1 变成未占据。
- 朋友 0 时刻 10 离开，椅子 2 变成未占据。
朋友 0 占据椅子 2 ，所以返回 2 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == times.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>times[i].length == 2</code></li>
	<li><code>1 &lt;= arrival<sub>i</sub> &lt; leaving<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= targetFriend &lt;= n - 1</code></li>
	<li>每个 <code>arrival<sub>i</sub></code> 时刻 <strong>互不相同</strong> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（最小堆）**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestChair(self, times: List[List[int]], targetFriend: int) -> int:
        n = len(times)
        h = list(range(n))
        heapify(h)
        for i in range(n):
            times[i].append(i)
        times.sort()
        busy = []
        for a, b, i in times:
            while busy and busy[0][0] <= a:
                heappush(h, heappop(busy)[1])
            c = heappop(h)
            if i == targetFriend:
                return c
            heappush(busy, (b, c))
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] ts = new int[n][3];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; ++i) {
            ts[i] = new int[]{times[i][0], times[i][1], i};
            q.offer(i);
        }
        Arrays.sort(ts, (a, b) -> a[0] - b[0]);
        for (int[] t : ts) {
            int a = t[0], b = t[1], i = t[2];
            while (!busy.isEmpty() && busy.peek()[0] <= a) {
                q.offer(busy.poll()[1]);
            }
            int c = q.poll();
            if (i == targetFriend) {
                return c;
            }
            busy.offer(new int[]{b, c});
        }
        return -1;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    int smallestChair(vector<vector<int>>& times, int targetFriend) {
        priority_queue<int, vector<int>, greater<int>> q;
        priority_queue<pii, vector<pii>, greater<pii>> busy;
        int n = times.size();
        for (int i = 0; i < n; ++i) {
            times[i].push_back(i);
            q.push(i);
        }
        sort(times.begin(), times.end());
        for (auto& t : times) {
            int a = t[0], b = t[1], i = t[2];
            while (!busy.empty() && busy.top().first <= a) {
                q.push(busy.top().second);
                busy.pop();
            }
            int c = q.top();
            q.pop();
            if (i == targetFriend) return c;
            busy.push({b, c});
        }
        return -1;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
