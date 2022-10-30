# [2458. 移除子树后的二叉树高度](https://leetcode.cn/problems/height-of-binary-tree-after-subtree-removal-queries)

[English Version](/solution/2400-2499/2458.Height%20of%20Binary%20Tree%20After%20Subtree%20Removal%20Queries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵 <strong>二叉树</strong> 的根节点 <code>root</code> ，树中有 <code>n</code> 个节点。每个节点都可以被分配一个从 <code>1</code> 到 <code>n</code> 且互不相同的值。另给你一个长度为 <code>m</code> 的数组 <code>queries</code> 。</p>

<p>你必须在树上执行 <code>m</code> 个 <strong>独立</strong> 的查询，其中第 <code>i</code> 个查询你需要执行以下操作：</p>

<ul>
	<li>从树中 <strong>移除</strong> 以 <code>queries[i]</code> 的值作为根节点的子树。题目所用测试用例保证 <code>queries[i]</code> <strong>不</strong> 等于根节点的值。</li>
</ul>

<p>返回一个长度为 <code>m</code> 的数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer[i]</code><em> </em>是执行第 <code>i</code> 个查询后树的高度。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>查询之间是独立的，所以在每个查询执行后，树会回到其 <strong>初始</strong> 状态。</li>
	<li>树的高度是从根到树中某个节点的 <strong>最长简单路径中的边数</strong> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2458.Height%20of%20Binary%20Tree%20After%20Subtree%20Removal%20Queries/images/binaryytreeedrawio-1.png" style="width: 495px; height: 281px;" /></p>

<pre>
<strong>输入：</strong>root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
<strong>输出：</strong>[2]
<strong>解释：</strong>上图展示了从树中移除以 4 为根节点的子树。
树的高度是 2（路径为 1 -&gt; 3 -&gt; 2）。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2458.Height%20of%20Binary%20Tree%20After%20Subtree%20Removal%20Queries/images/binaryytreeedrawio-2.png" style="width: 301px; height: 284px;" /></p>

<pre>
<strong>输入：</strong>root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
<strong>输出：</strong>[3,2,3,2]
<strong>解释：</strong>执行下述查询：
- 移除以 3 为根节点的子树。树的高度变为 3（路径为 5 -&gt; 8 -&gt; 2 -&gt; 4）。
- 移除以 2 为根节点的子树。树的高度变为 2（路径为 5 -&gt; 8 -&gt; 1）。
- 移除以 4 为根节点的子树。树的高度变为 3（路径为 5 -&gt; 8 -&gt; 2 -&gt; 6）。
- 移除以 8 为根节点的子树。树的高度变为 2（路径为 5 -&gt; 9 -&gt; 3）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目是 <code>n</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= n</code></li>
	<li>树中的所有值 <strong>互不相同</strong></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= m &lt;= min(n, 10<sup>4</sup>)</code></li>
	<li><code>1 &lt;= queries[i] &lt;= n</code></li>
	<li><code>queries[i] != root.val</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次 DFS**

我们先通过一次 DFS 遍历的深度，存放在哈希表 $d$ 中，其中 $d[x]$ 表示节点 $x$ 的深度。

然后我们设计一个函数 $dfs(root, depth, rest)$，其中：

-   `root` 表示当前节点；
-   `depth` 表示当前节点的深度；
-   `rest` 表示删除当前节点后，树的高度。

函数的计算逻辑如下：

如果节点为空，直接返回。否则，我们将 `depth` 加 $1$，然后将 `rest` 保存在 `res` 中。

接着递归遍历左右子树。

递归左子树前，我们计算从根节点到当前节点右子树最深节点的就，即 $depth+d[root.right]$，然后将其与 `rest` 比较，取较大值作为左子树的 `rest`。

递归右子树前，我们计算从根节点到当前节点左子树最深节点的就，即 $depth+d[root.left]$，然后将其与 `rest` 比较，取较大值作为右子树的 `rest`。

最后返回每个查询节点对应的结果值即可。

时间复杂度 $O(n+m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是树的节点数和查询数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def treeQueries(self, root: Optional[TreeNode], queries: List[int]) -> List[int]:
        def f(root):
            if root is None:
                return 0
            l, r = f(root.left), f(root.right)
            d[root] = 1 + max(l, r)
            return d[root]

        def dfs(root, depth, rest):
            if root is None:
                return
            depth += 1
            res[root.val] = rest
            dfs(root.left, depth, max(rest, depth + d[root.right]))
            dfs(root.right, depth, max(rest, depth + d[root.left]))

        d = defaultdict(int)
        f(root)
        res = [0] * (len(d) + 1)
        dfs(root, -1, 0)
        return [res[v] for v in queries]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Map<TreeNode, Integer> d = new HashMap<>();
    private int[] res;

    public int[] treeQueries(TreeNode root, int[] queries) {
        f(root);
        res = new int[d.size() + 1];
        d.put(null, 0);
        dfs(root, -1, 0);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = res[queries[i]];
        }
        return ans;
    }

    private void dfs(TreeNode root, int depth, int rest) {
        if (root == null) {
            return;
        }
        ++depth;
        res[root.val] = rest;
        dfs(root.left, depth, Math.max(rest, depth + d.get(root.right)));
        dfs(root.right, depth, Math.max(rest, depth + d.get(root.left)));
    }

    private int f(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = f(root.left), r = f(root.right);
        d.put(root, 1 + Math.max(l, r));
        return d.get(root);
    }
}
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<int> treeQueries(TreeNode* root, vector<int>& queries) {
        unordered_map<TreeNode*, int> d;
        function<int(TreeNode*)> f = [&](TreeNode* root) -> int {
            if (!root) return 0;
            int l = f(root->left), r = f(root->right);
            d[root] = 1 + max(l, r);
            return d[root];
        };
        f(root);
        vector<int> res(d.size() + 1);
        function<void(TreeNode*, int, int)> dfs = [&](TreeNode* root, int depth, int rest) {
            if (!root) return;
            ++depth;
            res[root->val] = rest;
            dfs(root->left, depth, max(rest, depth + d[root->right]));
            dfs(root->right, depth, max(rest, depth + d[root->left]));
        };
        dfs(root, -1, 0);
        vector<int> ans;
        for (int v : queries) ans.emplace_back(res[v]);
        return ans;
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func treeQueries(root *TreeNode, queries []int) (ans []int) {
	d := map[*TreeNode]int{}
	var f func(*TreeNode) int
	f = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := f(root.Left), f(root.Right)
		d[root] = 1 + max(l, r)
		return d[root]
	}
	f(root)
	res := make([]int, len(d)+1)
	var dfs func(*TreeNode, int, int)
	dfs = func(root *TreeNode, depth, rest int) {
		if root == nil {
			return
		}
		depth++
		res[root.Val] = rest
		dfs(root.Left, depth, max(rest, depth+d[root.Right]))
		dfs(root.Right, depth, max(rest, depth+d[root.Left]))
	}
	dfs(root, -1, 0)
	for _, v := range queries {
		ans = append(ans, res[v])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
