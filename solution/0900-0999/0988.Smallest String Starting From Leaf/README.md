# [988. 从叶结点开始的最小字符串](https://leetcode-cn.com/problems/smallest-string-starting-from-leaf)

[English Version](/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一颗根结点为&nbsp;<code>root</code>&nbsp;的二叉树，树中的每一个结点都有一个从&nbsp;<code>0</code> 到&nbsp;<code>25</code>&nbsp;的值，分别代表字母&nbsp;<code>&#39;a&#39;</code> 到&nbsp;<code>&#39;z&#39;</code>：值&nbsp;<code>0</code> 代表&nbsp;<code>&#39;a&#39;</code>，值&nbsp;<code>1</code>&nbsp;代表&nbsp;<code>&#39;b&#39;</code>，依此类推。</p>

<p>找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。</p>

<p><em>（小贴士：字符串中任何较短的前缀在字典序上都是较小的：例如，在字典序上&nbsp;<code>&quot;ab&quot;</code> 比&nbsp;<code>&quot;aba&quot;</code>&nbsp;要小。叶结点是指没有子结点的结点。）</em></p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/images/tree1.png" style="height: 107px; width: 160px;"></strong></p>

<pre><strong>输入：</strong>[0,1,2,3,4,3,4]
<strong>输出：</strong>&quot;dba&quot;
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/images/tree2.png" style="height: 107px; width: 160px;"></strong></p>

<pre><strong>输入：</strong>[25,1,3,1,3,0,2]
<strong>输出：</strong>&quot;adz&quot;
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/images/tree3.png" style="height: 180px; width: 268px;"></strong></p>

<pre><strong>输入：</strong>[2,2,1,null,1,0,null,0]
<strong>输出：</strong>&quot;abc&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>给定树的结点数介于&nbsp;<code>1</code> 和&nbsp;<code>8500</code>&nbsp;之间。</li>
	<li>树中的每个结点都有一个介于&nbsp;<code>0</code>&nbsp;和&nbsp;<code>25</code>&nbsp;之间的值。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

本题不能用这种以下这种方式实现：

```python
class Solution:
    def smallestFromLeaf(self, root: TreeNode) -> str:
        if root is None:
            return ''
        left = self.smallestFromLeaf(root.left)
        right = self.smallestFromLeaf(root.right)
        val = chr(ord('a') + root.val)
        return min(left + val, right + val)
```

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/images/image_1551131779.png" style="height: 180px; width: 172px;"></strong></p>

我们举个例子来说明，对于上面这棵二叉树，正确答案应该是 "ababz"，但是我们采用以上实现方式得到的答案是 "abz"。

问题就在于，当 `str(x) < str(y)`，并不能保证 `str(x) + a < str(y) + a`，例如 `"ab" < "abab"`，但是 `"abz" > "ababz"`。

本题可以用 DFS 解决，每次到达一个叶子节点时，翻转此路径上的字符串，并与 ans 比较大小，取二者较小值。

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
    def smallestFromLeaf(self, root: TreeNode) -> str:
        ans = chr(ord('z') + 1)

        def dfs(root, path):
            nonlocal ans
            if root:
                path.append(chr(ord('a') + root.val))
                if root.left is None and root.right is None:
                    ans = min(ans, ''.join(reversed(path)))
                dfs(root.left, path)
                dfs(root.right, path)
                path.pop()

        dfs(root, [])
        return ans
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
    private StringBuilder path;
    private String ans;

    public String smallestFromLeaf(TreeNode root) {
        path = new StringBuilder();
        ans = String.valueOf((char) ('z' + 1));
        dfs(root, path);
        return ans;
    }

    private void dfs(TreeNode root, StringBuilder path) {
        if (root != null) {
            path.append((char) ('a' + root.val));
            if (root.left == null && root.right == null) {
                String t = path.reverse().toString();
                if (t.compareTo(ans) < 0) {
                    ans = t;
                }
                path.reverse();
            }
            dfs(root.left, path);
            dfs(root.right, path);
            path.deleteCharAt(path.length() - 1);
        }
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
    string ans = "";

    string smallestFromLeaf(TreeNode* root) {
        string path = "";
        dfs(root, path);
        return ans;
    }

    void dfs(TreeNode* root, string& path) {
        if (!root) return;
        path += 'a' + root->val;
        if (!root->left && !root->right)
        {
            string t = path;
            reverse(t.begin(), t.end());
            if (ans == "" || t < ans) ans = t;
        }
        dfs(root->left, path);
        dfs(root->right, path);
        path.pop_back();
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
func smallestFromLeaf(root *TreeNode) string {
	ans := ""
	var dfs func(root *TreeNode, path string)
	dfs = func(root *TreeNode, path string) {
		if root == nil {
			return
		}
		path = string('a'+root.Val) + path
		if root.Left == nil && root.Right == nil {
			if ans == "" || path < ans {
				ans = path
			}
			return
		}
		dfs(root.Left, path)
		dfs(root.Right, path)
	}

	dfs(root, "")
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
