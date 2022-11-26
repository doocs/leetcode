# [2242. 节点序列的最大得分](https://leetcode.cn/problems/maximum-score-of-a-node-sequence)

[English Version](/solution/2200-2299/2242.Maximum%20Score%20of%20a%20Node%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>n</code>&nbsp;个节点的&nbsp;<strong>无向图</strong>&nbsp;，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。</p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>scores</code>&nbsp;，其中&nbsp;<code>scores[i]</code>&nbsp;是第 <code>i</code>&nbsp;个节点的分数。同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;，表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条&nbsp;<strong>无向</strong>&nbsp;边。</p>

<p>一个合法的节点序列如果满足以下条件，我们称它是 <strong>合法的</strong>&nbsp;：</p>

<ul>
	<li>序列中每&nbsp;<b>相邻</b>&nbsp;节点之间有边相连。</li>
	<li>序列中没有节点出现超过一次。</li>
</ul>

<p>节点序列的分数定义为序列中节点分数之 <strong>和</strong> 。</p>

<p>请你返回一个长度为 <code>4</code>&nbsp;的合法节点序列的最大分数。如果不存在这样的序列，请你返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2242.Maximum%20Score%20of%20a%20Node%20Sequence/images/ex1new3.png" style="width: 290px; height: 215px;" /></p>

<pre>
<b>输入：</b>scores = [5,2,9,8,4], edges = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
<b>输出：</b>24
<b>解释：</b>上图为输入的图，节点序列为 [0,1,2,3] 。
节点序列的分数为 5 + 2 + 9 + 8 = 24 。
观察可知，没有其他节点序列得分和超过 24 。
注意节点序列 [3,1,2,0] 和 [1,0,2,3] 也是合法的，且分数为 24 。
序列 [0,3,2,4] 不是合法的，因为没有边连接节点 0 和 3 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2242.Maximum%20Score%20of%20a%20Node%20Sequence/images/ex2.png" style="width: 333px; height: 151px;" /></p>

<pre>
<b>输入：</b>scores = [9,20,6,4,11,12], edges = [[0,3],[5,3],[2,4],[1,3]]
<b>输出：</b>-1
<b>解释：</b>上图为输入的图。
没有长度为 4 的合法序列，所以我们返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == scores.length</code></li>
	<li><code>4 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= scores[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>不会有重边。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举中间边**

枚举中间边 $(a, b)$，假设与 $a$ 相邻的点为 $c$，与 $b$ 相邻的点为 $d$。对于相邻点，取分数最大的三个点进行枚举。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumScore(self, scores: List[int], edges: List[List[int]]) -> int:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        for k in g.keys():
            g[k] = nlargest(3, g[k], key=lambda x: scores[x])
        ans = -1
        for a, b in edges:
            for c in g[a]:
                for d in g[b]:
                    if b != c != d != a:
                        t = scores[a] + scores[b] + scores[c] + scores[d]
                        ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            g[i].sort((a, b) -> scores[b] - scores[a]);
            g[i] = g[i].subList(0, Math.min(3, g[i].size()));
        }
        int ans = -1;
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            for (int c : g[a]) {
                for (int d : g[b]) {
                    if (c != b && c != d && a != d) {
                        int t = scores[a] + scores[b] + scores[c] + scores[d];
                        ans = Math.max(ans, t);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
