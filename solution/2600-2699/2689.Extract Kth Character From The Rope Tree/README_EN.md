---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [2689. Extract Kth Character From The Rope Tree 🔒](https://leetcode.com/problems/extract-kth-character-from-the-rope-tree)

[中文文档](/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a binary tree and an integer <code>k</code>. Besides the left and right children, every node of this tree has two other properties, a <strong>string</strong> <code>node.val</code> containing only lowercase English letters (possibly empty) and a non-negative integer <code>node.len</code>. There are two types of nodes in this tree:</p>

<ul>
	<li><strong>Leaf</strong>: These nodes have no children, <code>node.len = 0</code>, and <code>node.val</code> is some <strong>non-empty</strong> string.</li>
	<li><strong>Internal</strong>: These nodes have at least one child (also at most two children), <code>node.len &gt; 0</code>, and <code>node.val</code> is an <strong>empty</strong> string.</li>
</ul>

<p>The tree described above is called a <em>Rope</em> binary tree. Now we define <code>S[node]</code> recursively as follows:</p>

<ul>
	<li>If <code>node</code> is some leaf node, <code>S[node] = node.val</code>,</li>
	<li>Otherwise if <code>node</code> is some internal node, <code>S[node] = concat(S[node.left], S[node.right])</code> and <code>S[node].length = node.len</code>.</li>
</ul>

<p>Return<em> k-th character of the string</em> <code>S[root]</code>.</p>

<p><strong>Note:</strong> If <code>s</code> and <code>p</code> are two strings, <code>concat(s, p)</code> is a string obtained by concatenating <code>p</code> to <code>s</code>. For example, <code>concat(&quot;ab&quot;, &quot;zz&quot;) = &quot;abzz&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> root = [10,4,&quot;abcpoe&quot;,&quot;g&quot;,&quot;rta&quot;], k = 6
<strong>Output:</strong> &quot;b&quot;
<strong>Explanation:</strong> In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
You can see that S[root] = concat(concat(&quot;g&quot;, &quot;rta&quot;), &quot;abcpoe&quot;) = &quot;grtaabcpoe&quot;. So S[root][5], which represents 6th character of it, is equal to &quot;b&quot;.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example1.png" style="width: 300px; height: 213px; margin-left: 280px; margin-right: 280px;" /></p>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [12,6,6,&quot;abc&quot;,&quot;efg&quot;,&quot;hij&quot;,&quot;klm&quot;], k = 3
<strong>Output:</strong> &quot;c&quot;
<strong>Explanation:</strong> In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
You can see that S[root] = concat(concat(&quot;abc&quot;, &quot;efg&quot;), concat(&quot;hij&quot;, &quot;klm&quot;)) = &quot;abcefghijklm&quot;. So S[root][2], which represents the 3rd character of it, is equal to &quot;c&quot;.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example2.png" style="width: 400px; height: 232px; margin-left: 255px; margin-right: 255px;" /></p>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [&quot;ropetree&quot;], k = 8
<strong>Output:</strong> &quot;e&quot;
<strong>Explanation:</strong> In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
You can see that S[root] = &quot;ropetree&quot;. So S[root][7], which represents 8th character of it, is equal to &quot;e&quot;.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example3.png" style="width: 80px; height: 78px; margin-left: 400px; margin-right: 400px;" /></p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>3</sup>]</code></li>
	<li><code>node.val</code> contains only lowercase English letters</li>
	<li><code>0 &lt;= node.val.length &lt;= 50</code></li>
	<li><code>0 &lt;= node.len &lt;= 10<sup>4</sup></code></li>
	<li>for leaf nodes, <code>node.len = 0</code> and <code>node.val</code> is non-empty</li>
	<li>for internal nodes, <code>node.len &gt; 0</code> and <code>node.val</code> is empty</li>
	<li><code>1 &lt;= k &lt;= S[root].length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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

<!-- solution:end -->

<!-- problem:end -->
