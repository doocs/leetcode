---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0536.Construct%20Binary%20Tree%20from%20String/README.md
tags:
    - 栈
    - 树
    - 深度优先搜索
    - 字符串
    - 二叉树
---

<!-- problem:start -->

# [536. 从字符串生成二叉树 🔒](https://leetcode.cn/problems/construct-binary-tree-from-string)

[English Version](/solution/0500-0599/0536.Construct%20Binary%20Tree%20from%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你需要用一个包括括号和整数的字符串构建一棵二叉树。</p>

<p>输入的字符串代表一棵二叉树。它包括整数和随后的 0 、1 或 2 对括号。整数代表根的值，一对括号内表示同样结构的子树。</p>

<p>若存在子结点，则从<strong>左子结点</strong>开始构建。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0536.Construct%20Binary%20Tree%20from%20String/images/butree.jpg" style="height: 322px; width: 382px;" />
<pre>
<strong>输入：</strong> s = "4(2(3)(1))(6(5))"
<strong>输出：</strong> [4,2,6,3,1,5]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong> s = "4(2(3)(1))(6(5)(7))"
<strong>输出：</strong> [4,2,6,3,1,5,7]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong> s = "-4(2(3)(1))(6(5)(7))"
<strong>输出： </strong>[-4,2,6,3,1,5,7]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li>输入字符串中只包含&nbsp;<code>'('</code>, <code>')'</code>, <code>'-'</code>&nbsp;和&nbsp;<code>'0'</code> ~ <code>'9'</code>&nbsp;</li>
	<li>树中所有数字的值 <strong>最多</strong> 不超过&nbsp;<code>2<sup>30</sup></code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def str2tree(self, s: str) -> TreeNode:
        def dfs(s):
            if not s:
                return None
            p = s.find('(')
            if p == -1:
                return TreeNode(int(s))
            root = TreeNode(int(s[:p]))
            start = p
            cnt = 0
            for i in range(p, len(s)):
                if s[i] == '(':
                    cnt += 1
                elif s[i] == ')':
                    cnt -= 1
                if cnt == 0:
                    if start == p:
                        root.left = dfs(s[start + 1 : i])
                        start = i + 1
                    else:
                        root.right = dfs(s[start + 1 : i])
            return root

        return dfs(s)
```

#### Java

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
    public TreeNode str2tree(String s) {
        return dfs(s);
    }

    private TreeNode dfs(String s) {
        if ("".equals(s)) {
            return null;
        }
        int p = s.indexOf("(");
        if (p == -1) {
            return new TreeNode(Integer.parseInt(s));
        }
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, p)));
        int start = p;
        int cnt = 0;
        for (int i = p; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++cnt;
            } else if (s.charAt(i) == ')') {
                --cnt;
            }
            if (cnt == 0) {
                if (start == p) {
                    root.left = dfs(s.substring(start + 1, i));
                    start = i + 1;
                } else {
                    root.right = dfs(s.substring(start + 1, i));
                }
            }
        }
        return root;
    }
}
```

#### C++

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
    TreeNode* str2tree(string s) {
        return dfs(s);
    }

    TreeNode* dfs(string s) {
        if (s == "") return nullptr;
        int p = s.find("(");
        if (p == s.npos) return new TreeNode(stoi(s));
        TreeNode* root = new TreeNode(stoi(s.substr(0, p)));
        int start = p;
        int cnt = 0;
        for (int i = p; i < s.size(); ++i) {
            if (s[i] == '(')
                ++cnt;
            else if (s[i] == ')')
                --cnt;
            if (cnt == 0) {
                if (start == p) {
                    root->left = dfs(s.substr(start + 1, i - start - 1));
                    start = i + 1;
                } else
                    root->right = dfs(s.substr(start + 1, i - start - 1));
            }
        }
        return root;
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func str2tree(s string) *TreeNode {
	var dfs func(s string) *TreeNode
	dfs = func(s string) *TreeNode {
		if s == "" {
			return nil
		}
		p := strings.IndexAny(s, "(")
		if p == -1 {
			v, _ := strconv.Atoi(s)
			return &TreeNode{Val: v}
		}
		v, _ := strconv.Atoi(s[:p])
		root := &TreeNode{Val: v}
		start := p
		cnt := 0
		for i := p; i < len(s); i++ {
			if s[i] == '(' {
				cnt++
			} else if s[i] == ')' {
				cnt--
			}
			if cnt == 0 {
				if p == start {
					root.Left = dfs(s[start+1 : i])
					start = i + 1
				} else {
					root.Right = dfs(s[start+1 : i])
				}
			}
		}
		return root
	}
	return dfs(s)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
