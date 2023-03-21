# [1483. Kth Ancestor of a Tree Node](https://leetcode.com/problems/kth-ancestor-of-a-tree-node)

[中文文档](/solution/1400-1499/1483.Kth%20Ancestor%20of%20a%20Tree%20Node/README.md)

## Description

<p>You are given a tree with <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> in the form of a parent array <code>parent</code> where <code>parent[i]</code> is the parent of <code>i<sup>th</sup></code> node. The root of the tree is node <code>0</code>. Find the <code>k<sup>th</sup></code> ancestor of a given node.</p>

<p>The <code>k<sup>th</sup></code> ancestor of a tree node is the <code>k<sup>th</sup></code> node in the path from that node to the root node.</p>

<p>Implement the <code>TreeAncestor</code> class:</p>

<ul>
	<li><code>TreeAncestor(int n, int[] parent)</code> Initializes the object with the number of nodes in the tree and the parent array.</li>
	<li><code>int getKthAncestor(int node, int k)</code> return the <code>k<sup>th</sup></code> ancestor of the given node <code>node</code>. If there is no such ancestor, return <code>-1</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1483.Kth%20Ancestor%20of%20a%20Tree%20Node/images/1528_ex1.png" style="width: 396px; height: 262px;" />
<pre>
<strong>Input</strong>
[&quot;TreeAncestor&quot;, &quot;getKthAncestor&quot;, &quot;getKthAncestor&quot;, &quot;getKthAncestor&quot;]
[[7, [-1, 0, 0, 1, 1, 2, 2]], [3, 1], [5, 2], [6, 3]]
<strong>Output</strong>
[null, 1, 0, -1]

<strong>Explanation</strong>
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
treeAncestor.getKthAncestor(3, 1); // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2); // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3); // returns -1 because there is no such ancestor</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>parent.length == n</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>0 &lt;= parent[i] &lt; n</code> for all <code>0 &lt; i &lt; n</code></li>
	<li><code>0 &lt;= node &lt; n</code></li>
	<li>There will be at most <code>5 * 10<sup>4</sup></code> queries.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class TreeAncestor:

    def __init__(self, n: int, parent: List[int]):
        self.p = [[-1] * 18 for _ in range(n)]
        for i, fa in enumerate(parent):
            self.p[i][0] = fa
        for i in range(n):
            for j in range(1, 18):
                if self.p[i][j - 1] == -1:
                    continue
                self.p[i][j] = self.p[self.p[i][j - 1]][j - 1]

    def getKthAncestor(self, node: int, k: int) -> int:
        for i in range(17, -1, -1):
            if k >> i & 1:
                node = self.p[node][i]
                if node == -1:
                    break
        return node


# Your TreeAncestor object will be instantiated and called as such:
# obj = TreeAncestor(n, parent)
# param_1 = obj.getKthAncestor(node,k)
```

### **Java**

```java
class TreeAncestor {
    private int[][] p;

    public TreeAncestor(int n, int[] parent) {
        p = new int[n][18];
        for (var e : p) {
            Arrays.fill(e, -1);
        }
        for (int i = 0; i < n; ++i) {
            p[i][0] = parent[i];
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < 18; ++j) {
                if (p[i][j - 1] == -1) {
                    continue;
                }
                p[i][j] = p[p[i][j - 1]][j - 1];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (int i = 17; i >= 0; --i) {
            if (((k >> i) & 1) == 1) {
                node = p[node][i];
                if (node == -1) {
                    break;
                }
            }
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
```

### **C++**

```cpp
class TreeAncestor {
public:
    TreeAncestor(int n, vector<int>& parent) {
        p = vector<vector<int>>(n, vector<int>(18, -1));
        for (int i = 0; i < n; ++i) {
            p[i][0] = parent[i];
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < 18; ++j) {
                if (p[i][j - 1] == -1) {
                    continue;
                }
                p[i][j] = p[p[i][j - 1]][j - 1];
            }
        }
    }

    int getKthAncestor(int node, int k) {
        for (int i = 17; ~i; --i) {
            if (k >> i & 1) {
                node = p[node][i];
                if (node == -1) {
                    break;
                }
            }
        }
        return node;
    }

private:
    vector<vector<int>> p;
};

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor* obj = new TreeAncestor(n, parent);
 * int param_1 = obj->getKthAncestor(node,k);
 */
```

### **Go**

```go
type TreeAncestor struct {
	p [][18]int
}

func Constructor(n int, parent []int) TreeAncestor {
	p := make([][18]int, n)
	for i, fa := range parent {
		p[i][0] = fa
		for j := 1; j < 18; j++ {
			p[i][j] = -1
		}
	}
	for i := range p {
		for j := 1; j < 18; j++ {
			if p[i][j-1] == -1 {
				continue
			}
			p[i][j] = p[p[i][j-1]][j-1]
		}
	}
	return TreeAncestor{p}
}

func (this *TreeAncestor) GetKthAncestor(node int, k int) int {
	for i := 17; i >= 0; i-- {
		if k>>i&1 == 1 {
			node = this.p[node][i]
			if node == -1 {
				break
			}
		}
	}
	return node
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * obj := Constructor(n, parent);
 * param_1 := obj.GetKthAncestor(node,k);
 */
```

### **...**

```

```

<!-- tabs:end -->
