# [2646. 最小化旅行的价格总和](https://leetcode.cn/problems/minimize-the-total-price-of-the-trips)

[English Version](/solution/2600-2699/2646.Minimize%20the%20Total%20Price%20of%20the%20Trips/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一棵无向、无根的树，树中有 <code>n</code> 个节点，按从 <code>0</code> 到 <code>n - 1</code> 编号。给你一个整数 <code>n</code> 和一个长度为 <code>n - 1</code> 的二维整数数组 <code>edges</code> ，其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示树中节点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间存在一条边。</p>

<p>每个节点都关联一个价格。给你一个整数数组 <code>price</code> ，其中 <code>price[i]</code> 是第 <code>i</code> 个节点的价格。</p>

<p>给定路径的 <strong>价格总和</strong> 是该路径上所有节点的价格之和。</p>

<p>另给你一个二维整数数组 <code>trips</code> ，其中 <code>trips[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 表示您从节点 <code>start<sub>i</sub></code> 开始第 <code>i</code> 次旅行，并通过任何你喜欢的路径前往节点 <code>end<sub>i</sub></code> 。</p>

<p>在执行第一次旅行之前，你可以选择一些 <strong>非相邻节点</strong> 并将价格减半。</p>

<p>返回执行所有旅行的最小价格总和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2646.Minimize%20the%20Total%20Price%20of%20the%20Trips/images/diagram2.png" style="width: 541px; height: 181px;">
<pre><strong>输入：</strong>n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
<strong>输出：</strong>23
<strong>解释：
</strong>上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2646.Minimize%20the%20Total%20Price%20of%20the%20Trips/images/diagram3.png" style="width: 456px; height: 111px;">
<pre><strong>输入：</strong>n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
<strong>输出：</strong>1
<strong>解释：</strong>
上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。 
第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。 
所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>edges</code> 表示一棵有效的树</li>
	<li><code>price.length == n</code></li>
	<li><code>price[i]</code> 是一个偶数</li>
	<li><code>1 &lt;= price[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= trips.length &lt;= 100</code></li>
	<li><code>0 &lt;= start<sub>i</sub>, end<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumTotalPrice(self, n: int, edges: List[List[int]], price: List[int], trips: List[List[int]]) -> int:
        def dfs(i: int, fa: int, k: int) -> bool:
            cnt[i] += 1
            if i == k:
                return True
            ok = any(j != fa and dfs(j, i, k) for j in g[i])
            if not ok:
                cnt[i] -= 1
            return ok

        @cache
        def dfs2(i: int, fa: int = -1, div: int = 2) -> int:
            s = cnt[i] * price[i] // div
            for j in g[i]:
                if j != fa:
                    x = dfs2(j, i, 1)
                    if div == 1:
                        x = min(x, dfs2(j, i, 2))
                    s += x
            return s

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        cnt = Counter()
        for start, end in trips:
            dfs(start, -1, end)
        return min(dfs2(i) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
