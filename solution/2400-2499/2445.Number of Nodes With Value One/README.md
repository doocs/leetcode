# [2445. 值为 1 的节点数](https://leetcode.cn/problems/number-of-nodes-with-value-one)

[English Version](/solution/2400-2499/2445.Number%20of%20Nodes%20With%20Value%20One/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个&nbsp;<strong>无向&nbsp;</strong>树，有 <code>n</code> 个节点，节点标记为从 <code>1</code> 到 <code>n</code>&nbsp;，还有&nbsp;<code>n - 1</code> 条边。给定整数 <code>n</code>。标记为 <code>v</code> 的节点的父节点是标记为&nbsp;<code>floor (v / 2)</code>&nbsp;的节点。树的根节点是标记为 <code>1</code> 的节点。</p>

<ul>
	<li>例如，如果 <code>n = 7</code>，那么标记为 <code>3</code> 的节点将标记&nbsp;<code>floor(3 / 2) = 1</code> 的节点作为其父节点，标记为 <code>7</code> 的节点将标记&nbsp;<code>floor(7 / 2) = 3</code> 的节点作为其父节点。</li>
</ul>

<p>你还得到一个整数数组 <code>queries</code>。最初，每个节点的值都是 <code>0</code>。对于每个查询 <code>queries[i]</code>，您应该翻转节点标记为&nbsp;<code>queries[i]</code> 的子树中的所有值。</p>

<p>在&nbsp;<strong>处理完所有查询后</strong>，返回<em>值为 <code>1</code> 的节点总数。</em></p>

<p><b>注意</b>:</p>

<ul>
	<li>翻转节点的值意味着值为 <code>0</code> 的节点变为 <code>1</code>，反之亦然。</li>
	<li><code>floor(x)</code>&nbsp;相当于将 <code>x</code> 舍入到最接近的整数。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2445.Number%20of%20Nodes%20With%20Value%20One/images/ex1.jpg" style="width: 600px; height: 297px;" />
<pre>
<strong>输入:</strong> n = 5 , queries = [1,2,5]
<strong>输出:</strong> 3
<strong>解释:</strong> 上图显示了执行查询后的树结构及其状态。蓝色节点表示值 0，红色节点表示值 1。
在处理查询之后，有三个红色节点 (值为 1 的节点): 1、3、5。
</pre>

<p><strong class="example">示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2445.Number%20of%20Nodes%20With%20Value%20One/images/ex2.jpg" style="width: 650px; height: 88px;" />
<pre>
<strong>输入:</strong> n = 3, queries = [2,3,3]
<strong>输出:</strong> 1
<strong>解释:</strong> 上图显示了执行查询后的树结构及其状态。蓝色节点表示值 0，红色节点表示值 1。
在处理查询之后，有一个红色节点 (值为 1 的节点): 2。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意，我们可以模拟每次查询的过程，即将查询节点及其子树的节点值反转。最后统计节点值为 1 的节点个数即可。

这里有一个优化点，每个节点及其对应的子树，如果经过了偶数次查询，那么节点值不会发生变化，因此我们可以记录每个节点的查询次数，对于奇数次查询的节点及其子树，才进行反转。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfNodes(self, n: int, queries: List[int]) -> int:
        def dfs(i):
            if i > n:
                return
            tree[i] ^= 1
            dfs(i << 1)
            dfs(i << 1 | 1)

        tree = [0] * (n + 1)
        cnt = Counter(queries)
        for i, v in cnt.items():
            if v & 1:
                dfs(i)
        return sum(tree)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] tree;

    public int numberOfNodes(int n, int[] queries) {
        tree = new int[n + 1];
        int[] cnt = new int[n + 1];
        for (int v : queries) {
            ++cnt[v];
        }
        for (int i = 0; i < n + 1; ++i) {
            if (cnt[i] % 2 == 1) {
                dfs(i);
            }
        }
        int ans = 0;
        for (int i = 0; i < n + 1; ++i) {
            ans += tree[i];
        }
        return ans;
    }

    private void dfs(int i) {
        if (i >= tree.length) {
            return;
        }
        tree[i] ^= 1;
        dfs(i << 1);
        dfs(i << 1 | 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfNodes(int n, vector<int>& queries) {
        vector<int> tree(n + 1);
        vector<int> cnt(n + 1);
        for (int v : queries) ++cnt[v];
        function<void(int)> dfs = [&](int i) {
            if (i > n) return;
            tree[i] ^= 1;
            dfs(i << 1);
            dfs(i << 1 | 1);
        };
        for (int i = 0; i < n + 1; ++i) {
            if (cnt[i] & 1) {
                dfs(i);
            }
        }
        return accumulate(tree.begin(), tree.end(), 0);
    }
};
```

### **Go**

```go
func numberOfNodes(n int, queries []int) int {
	tree := make([]int, n+1)
	cnt := make([]int, n+1)
	for _, v := range queries {
		cnt[v]++
	}
	var dfs func(int)
	dfs = func(i int) {
		if i > n {
			return
		}
		tree[i] ^= 1
		dfs(i << 1)
		dfs(i<<1 | 1)
	}
	for i, v := range cnt {
		if v%2 == 1 {
			dfs(i)
		}
	}
	ans := 0
	for _, v := range tree {
		ans += v
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
