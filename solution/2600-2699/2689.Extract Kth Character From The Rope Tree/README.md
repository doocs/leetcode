# [2689. 从 Rope 树中提取第 K 个字符 🔒](https://leetcode.cn/problems/extract-kth-character-from-the-rope-tree)

[English Version](/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/README_EN.md)

<!-- tags:树,深度优先搜索,二叉树 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树的根节点 <code>root</code> 和整数 <code>k</code>。除了左右孩子之外，该树的每个节点还有另外两个属性：一个仅包含小写英文字母（可能为空）的 <strong>字符串</strong> <code>node.val</code> 和一个非负整数 <code>node.len</code>。这棵树中有两种类型的节点：</p>

<ul>
	<li><strong>叶子节点</strong>：这些节点没有子节点，<code>node.len = 0</code>，<code>node.val</code> 是一个 <strong>非空</strong> 字符串。</li>
	<li><strong>内部节点</strong>：这些节点至少有一个子节点（最多两个子节点），<code>node.len &gt; 0</code>，<code>node.val</code> 是一个 <strong>空</strong> 字符串。</li>
</ul>

<p>上述描述的树被称为 Rope 二叉树。现在我们用以下递归方式定义 <code>S[node]</code>：</p>

<ul>
	<li>如果 <code>node</code> 是一个叶子节点，则 <code>S[node] = node.val</code>，</li>
	<li>否则，如果 <code>node</code> 是一个内部节点，则 <code>S[node] = concat(S[node.left], S[node.right])</code>，且 <code>S[node].length = node.len</code>。</li>
</ul>

<p>返回字符串 <code>S[root]</code> 的第 <code>k</code> 个字符。</p>

<p><strong>注意</strong>：如果 <code>s</code> 和 <code>p</code> 是两个字符串，则 <code>concat(s, p)</code> 是将字符串 <code>p</code> 连接到 <code>s</code> 后面的字符串。例如，<code>concat("ab", "zz") = "abzz"</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>root = [10,4,"abcpoe","g","rta"], k = 6
<b>输出：</b>"b"
<b>解释：</b>在下面的图片中，我们在内部节点上放置一个表示 <code>node.len</code> 的整数，在叶子节点上放置一个表示 <code>node.val</code> 的字符串。 你可以看到，<code>S[root] = concat(concat("g", "rta"), "abcpoe") = "grtaabcpoe"</code>。因此，<code>S[root][5]</code>，表示它的第6个字符，等于 "b"。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example1.png" style="width: 300px; height: 213px; margin-left: 280px; margin-right: 280px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>root = [12,6,6,"abc","efg","hij","klm"], k = 3
<b>输出：</b>"c"
<b>解释：</b>在下面的图片中，我们在内部节点上放置一个表示 <code>node.len</code> 的整数，在叶子节点上放置一个表示 <code>node.val</code> 的字符串。 你可以看到，<code>S[root] = concat(concat("abc", "efg"), concat("hij", "klm")) = "abcefghijklm"</code>。因此，<code>S[root][2]</code>，表示它的第3个字符，等于 "c"。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example2.png" style="width: 400px; height: 232px; margin-left: 255px; margin-right: 255px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>root = ["ropetree"], k = 8
<b>输出：</b>"e"
<b>解释：</b>在下面的图片中，我们在内部节点上放置一个表示 <code>node.len</code> 的整数，在叶子节点上放置一个表示 <code>node.val</code> 的字符串。 你可以看到，<code>S[root] = "ropetree"</code>。因此，<code>S[root][7]</code>，表示它的第8个字符，等于 "e"。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example3.png" style="width: 80px; height: 78px; margin-left: 400px; margin-right: 400px;" /></p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>这棵树的节点数量在区间&nbsp;<code>[1, 10<sup>3</sup>]</code></li>
	<li><code>node.val</code> 仅包含小写英文字母</li>
	<li><code>0 &lt;= node.val.length &lt;= 50</code></li>
	<li><code>0 &lt;= node.len &lt;= 10<sup>4</sup></code></li>
	<li>对于叶子节点， <code>node.len = 0</code> 且&nbsp;<code>node.val</code> 是非空的</li>
	<li>对于内部节点， <code>node.len &gt; 0 </code>&nbsp;且&nbsp;<code>node.val</code> 为空</li>
	<li><code>1 &lt;= k &lt;= S[root].length</code></li>
</ul>

## 解法

### 方法一：DFS

我们可以使用深度优先搜索的方法，定义一个函数 $dfs(root)$，表示从根节点开始搜索，返回以 $root$ 为根节点的子树的字符串。那么答案就是 $dfs(root)[k-1]$。

函数 $dfs(root)$ 的执行逻辑如下：

-   如果 $root$ 为空，返回空字符串；
-   如果 $root$ 是叶子节点，返回 $root.val$；
-   否则，返回 $dfs(root.left) + dfs(root.right)$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是树中节点的个数。

<!-- tabs:start -->

```python
# Definition for a rope tree node.
# class RopeTreeNode(object):
#     def __init__(self, len=0, val="", left=None, right=None):
#         self.len = len
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getKthCharacter(self, root: Optional[object], k: int) -> str:
        def dfs(root):
            if root is None:
                return ""
            if root.len == 0:
                return root.val
            return dfs(root.left) + dfs(root.right)

        return dfs(root)[k - 1]
```

```java
/**
 * Definition for a rope tree node.
 * class RopeTreeNode {
 *     int len;
 *     String val;
 *     RopeTreeNode left;
 *     RopeTreeNode right;
 *     RopeTreeNode() {}
 *     RopeTreeNode(String val) {
 *         this.len = 0;
 *         this.val = val;
 *     }
 *     RopeTreeNode(int len) {
 *         this.len = len;
 *         this.val = "";
 *     }
 *     RopeTreeNode(int len, TreeNode left, TreeNode right) {
 *         this.len = len;
 *         this.val = "";
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public char getKthCharacter(RopeTreeNode root, int k) {
        return dfs(root).charAt(k - 1);
    }

    private String dfs(RopeTreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.val.length() > 0) {
            return root.val;
        }
        String left = dfs(root.left);
        String right = dfs(root.right);
        return left + right;
    }
}
```

```cpp
/**
 * Definition for a rope tree node.
 * struct RopeTreeNode {
 *     int len;
 *     string val;
 *     RopeTreeNode *left;
 *     RopeTreeNode *right;
 *     RopeTreeNode() : len(0), val(""), left(nullptr), right(nullptr) {}
 *     RopeTreeNode(string s) : len(0), val(std::move(s)), left(nullptr), right(nullptr) {}
 *     RopeTreeNode(int x) : len(x), val(""), left(nullptr), right(nullptr) {}
 *     RopeTreeNode(int x, RopeTreeNode *left, RopeTreeNode *right) : len(x), val(""), left(left), right(right) {}
 * };
 */
class Solution {
public:
    char getKthCharacter(RopeTreeNode* root, int k) {
        function<string(RopeTreeNode * root)> dfs = [&](RopeTreeNode* root) -> string {
            if (root == nullptr) {
                return "";
            }
            if (root->len == 0) {
                return root->val;
            }
            string left = dfs(root->left);
            string right = dfs(root->right);
            return left + right;
        };
        return dfs(root)[k - 1];
    }
};
```

```go
/**
 * Definition for a rope tree node.
 * type RopeTreeNode struct {
 * 	   len   int
 * 	   val   string
 * 	   left  *RopeTreeNode
 * 	   right *RopeTreeNode
 * }
 */
func getKthCharacter(root *RopeTreeNode, k int) byte {
	var dfs func(root *RopeTreeNode) string
	dfs = func(root *RopeTreeNode) string {
		if root == nil {
			return ""
		}
		if root.len == 0 {
			return root.val
		}
		left, right := dfs(root.left), dfs(root.right)
		return left + right
	}
	return dfs(root)[k-1]
}
```

<!-- tabs:end -->

<!-- end -->
