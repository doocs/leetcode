# [427. 建立四叉树](https://leetcode.cn/problems/construct-quad-tree)

[English Version](/solution/0400-0499/0427.Construct%20Quad%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n * n</code> 矩阵 <code>grid</code> ，矩阵由若干 <code>0</code> 和 <code>1</code> 组成。请你用四叉树表示该矩阵 <code>grid</code> 。</p>

<p>你需要返回能表示矩阵的 四叉树 的根结点。</p>

<p>注意，当 <code>isLeaf</code> 为 <strong>False </strong>时，你可以把 <strong>True</strong> 或者 <strong>False</strong> 赋值给节点，两种值都会被判题机制 <strong>接受</strong> 。</p>

<p>四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：</p>

<ul>
	<li><code>val</code>：储存叶子结点所代表的区域的值。1 对应 <strong>True</strong>，0 对应 <strong>False</strong>；</li>
	<li><code>isLeaf</code>: 当这个节点是一个叶子结点时为 <strong>True</strong>，如果它有 4 个子节点则为 <strong>False</strong> 。</li>
</ul>

<pre>class Node {
    public boolean val;
&nbsp; &nbsp; public boolean isLeaf;
&nbsp; &nbsp; public Node topLeft;
&nbsp; &nbsp; public Node topRight;
&nbsp; &nbsp; public Node bottomLeft;
&nbsp; &nbsp; public Node bottomRight;
}</pre>

<p>我们可以按以下步骤为二维区域构建四叉树：</p>

<ol>
	<li>如果当前网格的值相同（即，全为 <code>0</code> 或者全为 <code>1</code>），将 <code>isLeaf</code> 设为 True ，将 <code>val</code> 设为网格相应的值，并将四个子节点都设为 Null 然后停止。</li>
	<li>如果当前网格的值不同，将 <code>isLeaf</code> 设为 False， 将 <code>val</code> 设为任意值，然后如下图所示，将当前网格划分为四个子网格。</li>
	<li>使用适当的子网格递归每个子节点。</li>
</ol>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0427.Construct%20Quad%20Tree/images/new_top.png" style="height: 181px; width: 777px;"></p>

<p>如果你想了解更多关于四叉树的内容，可以参考 <a href="https://en.wikipedia.org/wiki/Quadtree">wiki</a> 。</p>

<p><strong>四叉树格式：</strong></p>

<p>输出为使用层序遍历后四叉树的序列化形式，其中 <code>null</code> 表示路径终止符，其下面不存在节点。</p>

<p>它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 <code>[isLeaf, val]</code> 。</p>

<p>如果 <code>isLeaf</code> 或者 <code>val</code> 的值为 True ，则表示它在列表&nbsp;<code>[isLeaf, val]</code> 中的值为 <strong>1</strong> ；如果 <code>isLeaf</code> 或者 <code>val</code> 的值为 False ，则表示值为 <strong>0 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0427.Construct%20Quad%20Tree/images/grid1.png" style="height: 99px; width: 777px;"></p>

<pre><strong>输入：</strong>grid = [[0,1],[1,0]]
<strong>输出：</strong>[[0,1],[1,0],[1,1],[1,1],[1,0]]
<strong>解释：</strong>此示例的解释如下：
请注意，在下面四叉树的图示中，0 表示 false，1 表示 True 。
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0427.Construct%20Quad%20Tree/images/e1tree.png" style="height: 186px; width: 777px;">
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0427.Construct%20Quad%20Tree/images/e2mat.png" style="height: 343px; width: 777px;"></p>

<pre><strong>输入：</strong>grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
<strong>输出：</strong>[[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
<strong>解释：</strong>网格中的所有值都不相同。我们将网格划分为四个子网格。
topLeft，bottomLeft 和 bottomRight 均具有相同的值。
topRight 具有不同的值，因此我们将其再分为 4 个子网格，这样每个子网格都具有相同的值。
解释如下图所示：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0427.Construct%20Quad%20Tree/images/e2tree.png" style="height: 328px; width: 777px;">
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1],[1,1]]
<strong>输出：</strong>[[1,1]]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>grid = [[0]]
<strong>输出：</strong>[[1,0]]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,0,0],[1,1,0,0],[0,0,1,1],[0,0,1,1]]
<strong>输出：</strong>[[0,1],[1,1],[1,0],[1,0],[1,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>n == 2^x</code> 其中 <code>0 &lt;= x &lt;= 6</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

DFS 递归遍历 grid，先判断 grid 是否为叶子节点，是则返回叶子节点相关信息；否则递归 grid 4 个子节点。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
"""
# Definition for a QuadTree node.
class Node:
    def __init__(self, val, isLeaf, topLeft, topRight, bottomLeft, bottomRight):
        self.val = val
        self.isLeaf = isLeaf
        self.topLeft = topLeft
        self.topRight = topRight
        self.bottomLeft = bottomLeft
        self.bottomRight = bottomRight
"""


class Solution:
    def construct(self, grid: List[List[int]]) -> 'Node':
        def dfs(a, b, c, d):
            zero = one = 0
            for i in range(a, c + 1):
                for j in range(b, d + 1):
                    if grid[i][j] == 0:
                        zero = 1
                    else:
                        one = 1
            isLeaf = zero + one == 1
            val = isLeaf and one
            if isLeaf:
                return Node(grid[a][b], True)
            topLeft = dfs(a, b, (a + c) // 2, (b + d) // 2)
            topRight = dfs(a, (b + d) // 2 + 1, (a + c) // 2, d)
            bottomLeft = dfs((a + c) // 2 + 1, b, c, (b + d) // 2)
            bottomRight = dfs((a + c) // 2 + 1, (b + d) // 2 + 1, c, d)
            return Node(val, isLeaf, topLeft, topRight, bottomLeft, bottomRight)

        return dfs(0, 0, len(grid) - 1, len(grid[0]) - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/

class Solution {
    public Node construct(int[][] grid) {
        return dfs(0, 0, grid.length - 1, grid[0].length - 1, grid);
    }

    private Node dfs(int a, int b, int c, int d, int[][] grid) {
        int zero = 0, one = 0;
        for (int i = a; i <= c; ++i) {
            for (int j = b; j <= d; ++j) {
                if (grid[i][j] == 0) {
                    zero = 1;
                } else {
                    one = 1;
                }
            }
        }
        boolean isLeaf = zero + one == 1;
        boolean val = isLeaf && one == 1;
        Node node = new Node(val, isLeaf);
        if (isLeaf) {
            return node;
        }
        node.topLeft = dfs(a, b, (a + c) / 2, (b + d) / 2, grid);
        node.topRight = dfs(a, (b + d) / 2 + 1, (a + c) / 2, d, grid);
        node.bottomLeft = dfs((a + c) / 2 + 1, b, c, (b + d) / 2, grid);
        node.bottomRight = dfs((a + c) / 2 + 1, (b + d) / 2 + 1, c, d, grid);
        return node;
    }
}
```

### **C++**

```cpp
/*
// Definition for a QuadTree node.
class Node {
public:
    bool val;
    bool isLeaf;
    Node* topLeft;
    Node* topRight;
    Node* bottomLeft;
    Node* bottomRight;

    Node() {
        val = false;
        isLeaf = false;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }

    Node(bool _val, bool _isLeaf) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }

    Node(bool _val, bool _isLeaf, Node* _topLeft, Node* _topRight, Node* _bottomLeft, Node* _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/

class Solution {
public:
    Node* construct(vector<vector<int>>& grid) {
        return dfs(0, 0, grid.size() - 1, grid[0].size() - 1, grid);
    }

    Node* dfs(int a, int b, int c, int d, vector<vector<int>>& grid) {
        int zero = 0, one = 0;
        for (int i = a; i <= c; ++i) {
            for (int j = b; j <= d; ++j) {
                if (grid[i][j])
                    one = 1;
                else
                    zero = 1;
            }
        }
        bool isLeaf = zero + one == 1;
        bool val = isLeaf && one;
        Node* node = new Node(val, isLeaf);
        if (isLeaf) return node;
        node->topLeft = dfs(a, b, (a + c) / 2, (b + d) / 2, grid);
        node->topRight = dfs(a, (b + d) / 2 + 1, (a + c) / 2, d, grid);
        node->bottomLeft = dfs((a + c) / 2 + 1, b, c, (b + d) / 2, grid);
        node->bottomRight = dfs((a + c) / 2 + 1, (b + d) / 2 + 1, c, d, grid);
        return node;
    }
};
```

### **Go**

```go
/**
 * Definition for a QuadTree node.
 * type Node struct {
 *     Val bool
 *     IsLeaf bool
 *     TopLeft *Node
 *     TopRight *Node
 *     BottomLeft *Node
 *     BottomRight *Node
 * }
 */

func construct(grid [][]int) *Node {
	var dfs func(a, b, c, d int) *Node
	dfs = func(a, b, c, d int) *Node {
		zero, one := 0, 0
		for i := a; i <= c; i++ {
			for j := b; j <= d; j++ {
				if grid[i][j] == 0 {
					zero = 1
				} else {
					one = 1
				}
			}
		}
		isLeaf := zero+one == 1
		val := isLeaf && one == 1
		node := &Node{Val: val, IsLeaf: isLeaf}
		if isLeaf {
			return node
		}
		node.TopLeft = dfs(a, b, (a+c)/2, (b+d)/2)
		node.TopRight = dfs(a, (b+d)/2+1, (a+c)/2, d)
		node.BottomLeft = dfs((a+c)/2+1, b, c, (b+d)/2)
		node.BottomRight = dfs((a+c)/2+1, (b+d)/2+1, c, d)
		return node
	}
	return dfs(0, 0, len(grid)-1, len(grid[0])-1)
}
```

### **...**

```

```

<!-- tabs:end -->
