# [1483. 树节点的第 K 个祖先](https://leetcode.cn/problems/kth-ancestor-of-a-tree-node)

[English Version](/solution/1400-1499/1483.Kth%20Ancestor%20of%20a%20Tree%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵树，树上有 <code>n</code> 个节点，按从 <code>0</code> 到 <code>n-1</code> 编号。树以父节点数组的形式给出，其中 <code>parent[i]</code> 是节点 <code>i</code> 的父节点。树的根节点是编号为 <code>0</code> 的节点。</p>

<p>树节点的第 <em><code>k</code> </em>个祖先节点是从该节点到根节点路径上的第 <code>k</code> 个节点。</p>

<p>实现 <code>TreeAncestor</code> 类：</p>

<ul>
	<li><code>TreeAncestor（int n， int[] parent）</code> 对树和父数组中的节点数初始化对象。</li>
	<li><code>getKthAncestor</code><code>(int node, int k)</code> 返回节点 <code>node</code> 的第 <code>k</code> 个祖先节点。如果不存在这样的祖先节点，返回 <code>-1</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1483.Kth%20Ancestor%20of%20a%20Tree%20Node/images/1528_ex1.png" /></strong></p>

<pre>
<strong>输入：</strong>
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]

<strong>输出：</strong>
[null,1,0,-1]

<strong>解释：</strong>
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);

treeAncestor.getKthAncestor(3, 1);  // 返回 1 ，它是 3 的父节点
treeAncestor.getKthAncestor(5, 2);  // 返回 0 ，它是 5 的祖父节点
treeAncestor.getKthAncestor(6, 3);  // 返回 -1 因为不存在满足要求的祖先节点
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>parent[0] == -1</code> 表示编号为 <code>0</code> 的节点是根节点。</li>
	<li>对于所有的 <code>0 &lt;&nbsp;i &lt; n</code> ，<code>0 &lt;= parent[i] &lt; n</code> 总成立</li>
	<li><code>0 &lt;= node &lt; n</code></li>
	<li>至多查询&nbsp;<code>5 * 10<sup>4</sup></code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划 + 倍增**

题目要我们寻找节点 `node` 的第 $k$ 个祖先节点，如果暴力求解，需要从 `node` 开始向上遍历 $k$ 次，时间复杂度为 $O(k)$，显然会超时。

我们可以使用动态规划的思想，结合倍增的思想来处理。

我们定义 $p[i][j]$ 表示节点 $i$ 的第 $2^j$ 个祖先节点，即 $p[i][j]$ 表示节点 $i$ 向上走 $2^j$ 步的节点。那么我们可以得到状态转移方程：

$$
p[i][j] = p[p[i][j-1]][j-1]
$$

即：要想找到节点 $i$ 的第 $2^j$ 个祖先节点，我们可以先找到节点 $i$ 的第 $2^{j-1}$ 个祖先节点，然后再找到该节点的第 $2^{j-1}$ 个祖先节点即可。所以，我们要找到每个节点的距离为 $2^j$ 的祖先节点，直到达到树的最大高度。

之后对于每次查询，我们可以把 $k$ 拆成二进制的表示形式，然后根据二进制中 $1$ 的位置，累计向上查询，最终得到节点 $node$ 的第 $k$ 个祖先节点。

时间复杂度：初始化为 $O(n \times \log n)$，查询为 $O(\log n)$。空间复杂度：$O(n \times \log n)$。其中 $n$ 为树的节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
