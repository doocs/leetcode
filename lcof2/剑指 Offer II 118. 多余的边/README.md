---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20118.%20%E5%A4%9A%E4%BD%99%E7%9A%84%E8%BE%B9/README.md
---

<!-- problem:start -->

# [剑指 Offer II 118. 多余的边](https://leetcode.cn/problems/7LpjUW)

## 题目描述

<!-- description:start -->

<p>树可以看成是一个连通且 <strong>无环&nbsp;</strong>的&nbsp;<strong>无向&nbsp;</strong>图。</p>

<p>给定往一棵&nbsp;<code>n</code> 个节点 (节点值&nbsp;<code>1～n</code>) 的树中添加一条边后的图。添加的边的两个顶点包含在 <code>1</code> 到 <code>n</code>&nbsp;中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 <code>n</code> 的二维数组 <code>edges</code>&nbsp;，<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示图中在 <code>ai</code> 和 <code>bi</code> 之间存在一条边。</p>

<p>请找出一条可以删去的边，删除后可使得剩余部分是一个有着 <code>n</code> 个节点的树。如果有多个答案，则返回数组&nbsp;<code>edges</code>&nbsp;中最后出现的边。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20118.%20%E5%A4%9A%E4%BD%99%E7%9A%84%E8%BE%B9/images/1626676174-hOEVUL-image.png" style="width: 152px; " /></p>

<pre>
<strong>输入:</strong> edges = [[1,2],[1,3],[2,3]]
<strong>输出:</strong> [2,3]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20118.%20%E5%A4%9A%E4%BD%99%E7%9A%84%E8%BE%B9/images/1626676179-kGxcmu-image.png" style="width: 250px; " /></p>

<pre>
<strong>输入:</strong> edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
<strong>输出:</strong> [1,4]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>3 &lt;= n &lt;= 1000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= ai&nbsp;&lt; bi&nbsp;&lt;= edges.length</code></li>
	<li><code>ai != bi</code></li>
	<li><code>edges</code> 中无重复元素</li>
	<li>给定的图是连通的&nbsp;</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 684&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/redundant-connection/">https://leetcode.cn/problems/redundant-connection/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(1010))
        for a, b in edges:
            if find(a) == find(b):
                return [a, b]
            p[find(a)] = find(b)
        return []
```

#### Java

```java
class Solution {
    private int[] p;

    public int[] findRedundantConnection(int[][] edges) {
        p = new int[1010];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) {
                return e;
            }
            p[find(a)] = find(b);
        }
        return null;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> p;

    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        p.resize(1010);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) return e;
            p[find(a)] = find(b);
        }
        return {};
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

#### Go

```go
func findRedundantConnection(edges [][]int) []int {
	p := make([]int, 1010)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		if find(a) == find(b) {
			return []int{a, b}
		}
		p[find(a)] = find(b)
	}
	return []int{}
}
```

#### Swift

```swift
class Solution {
    private var parent: [Int] = []

    func findRedundantConnection(_ edges: [[Int]]) -> [Int] {
        parent = Array(0..<1010)

        for edge in edges {
            let a = edge[0]
            let b = edge[1]

            if find(a) == find(b) {
                return edge
            }
            parent[find(a)] = find(b)
        }
        return []
    }

    private func find(_ x: Int) -> Int {
        if parent[x] != x {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
