# [1361. Validate Binary Tree Nodes](https://leetcode.com/problems/validate-binary-tree-nodes)

[中文文档](/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/README.md)

<!-- tags:Tree,Depth-First Search,Breadth-First Search,Union Find,Graph,Binary Tree -->

## Description

<p>You have <code>n</code> binary tree nodes numbered from <code>0</code> to <code>n - 1</code> where node <code>i</code> has two children <code>leftChild[i]</code> and <code>rightChild[i]</code>, return <code>true</code> if and only if <strong>all</strong> the given nodes form <strong>exactly one</strong> valid binary tree.</p>

<p>If node <code>i</code> has no left child then <code>leftChild[i]</code> will equal <code>-1</code>, similarly for the right child.</p>

<p>Note that the nodes have no values and that we only use the node numbers in this problem.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex1.png" style="width: 195px; height: 287px;" />
<pre>
<strong>Input:</strong> n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex2.png" style="width: 183px; height: 272px;" />
<pre>
<strong>Input:</strong> n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex3.png" style="width: 82px; height: 174px;" />
<pre>
<strong>Input:</strong> n = 2, leftChild = [1,0], rightChild = [-1,-1]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == leftChild.length == rightChild.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-1 &lt;= leftChild[i], rightChild[i] &lt;= n - 1</code></li>
</ul>

## Solutions

### Solution 1: Union-Find

We can traverse each node $i$ and its corresponding left and right children $l$, $r$, using an array $vis$ to record whether the node has a parent:

-   If the child node already has a parent, it means there are multiple fathers, which does not meet the condition, so we return `false` directly.
-   If the child node and the parent node are already in the same connected component, it means a cycle will be formed, which does not meet the condition, so we return `false` directly.
-   Otherwise, we perform a union operation, set the corresponding position of the $vis$ array to `true`, and decrease the number of connected components by $1$.

After the traversal, we check whether the number of connected components in the union-find set is $1$. If it is, we return `true`, otherwise, we return `false`.

The time complexity is $O(n \times \alpha(n))$, and the space complexity is $O(n)$. Where $n$ is the number of nodes, and $\alpha(n)$ is the inverse Ackermann function, which is less than $5$.

<!-- tabs:start -->

```python
class Solution:
    def validateBinaryTreeNodes(
        self, n: int, leftChild: List[int], rightChild: List[int]
    ) -> bool:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        vis = [False] * n
        for i, (a, b) in enumerate(zip(leftChild, rightChild)):
            for j in (a, b):
                if j != -1:
                    if vis[j] or find(i) == find(j):
                        return False
                    p[find(i)] = find(j)
                    vis[j] = True
                    n -= 1
        return n == 1
```

```java
class Solution {
    private int[] p;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        boolean[] vis = new boolean[n];
        for (int i = 0, m = n; i < m; ++i) {
            for (int j : new int[] {leftChild[i], rightChild[i]}) {
                if (j != -1) {
                    if (vis[j] || find(i) == find(j)) {
                        return false;
                    }
                    p[find(i)] = find(j);
                    vis[j] = true;
                    --n;
                }
            }
        }
        return n == 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    bool validateBinaryTreeNodes(int n, vector<int>& leftChild, vector<int>& rightChild) {
        int p[n];
        iota(p, p + n, 0);
        bool vis[n];
        memset(vis, 0, sizeof(vis));
        function<int(int)> find = [&](int x) {
            return p[x] == x ? x : p[x] = find(p[x]);
        };
        for (int i = 0, m = n; i < m; ++i) {
            for (int j : {leftChild[i], rightChild[i]}) {
                if (j != -1) {
                    if (vis[j] || find(i) == find(j)) {
                        return false;
                    }
                    p[find(i)] = find(j);
                    vis[j] = true;
                    --n;
                }
            }
        }
        return n == 1;
    }
};
```

```go
func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	vis := make([]bool, n)
	for i, a := range leftChild {
		for _, j := range []int{a, rightChild[i]} {
			if j != -1 {
				if vis[j] || find(i) == find(j) {
					return false
				}
				p[find(i)] = find(j)
				vis[j] = true
				n--
			}
		}
	}
	return n == 1
}
```

<!-- tabs:end -->

<!-- end -->
