# [2445. Number of Nodes With Value One](https://leetcode.com/problems/number-of-nodes-with-value-one)

[中文文档](/solution/2400-2499/2445.Number%20of%20Nodes%20With%20Value%20One/README.md)

## Description

<p>There is an <strong>undirected</strong> connected tree with <code>n</code> nodes labeled from <code>1</code> to <code>n</code> and <code>n - 1</code> edges. You are given the integer <code>n</code>. The parent node of a node with a label <code>v</code> is the node with the label <code>floor (v / 2)</code>. The root of the tree is the node with the label <code>1</code>.</p>

<ul>
	<li>For example, if <code>n = 7</code>, then the node with the label <code>3</code> has the node with the label <code>floor(3 / 2) = 1</code> as its parent, and the node with the label <code>7</code> has the node with the label <code>floor(7 / 2) = 3</code> as its parent.</li>
</ul>

<p>You are also given an integer array <code>queries</code>. Initially, every node has a value <code>0</code> on it. For each query <code>queries[i]</code>, you should flip all values in the subtree of the node with the label <code>queries[i]</code>.</p>

<p>Return <em>the total number of nodes with the value </em><code>1</code><em> <strong>after processing all the queries</strong></em>.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>Flipping the value of a node means that the node with the value <code>0</code> becomes <code>1</code> and vice versa.</li>
	<li><code>floor(x)</code> is equivalent to rounding <code>x</code> down to the nearest integer.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2445.Number%20of%20Nodes%20With%20Value%20One/images/ex1.jpg" style="width: 600px; height: 297px;" />
<pre>
<strong>Input:</strong> n = 5 , queries = [1,2,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The diagram above shows the tree structure and its status after performing the queries. The blue node represents the value 0, and the red node represents the value 1.
After processing the queries, there are three red nodes (nodes with value 1): 1, 3, and 5.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2445.Number%20of%20Nodes%20With%20Value%20One/images/ex2.jpg" style="width: 650px; height: 88px;" />
<pre>
<strong>Input:</strong> n = 3, queries = [2,3,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The diagram above shows the tree structure and its status after performing the queries. The blue node represents the value 0, and the red node represents the value 1.
After processing the queries, there are one red node (node with value 1): 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
