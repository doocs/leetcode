---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/README_EN.md
rating: 1797
source: Weekly Contest 132 Q4
tags:
    - Tree
    - Depth-First Search
    - String
    - Binary Tree
---

# [1028. Recover a Tree From Preorder Traversal](https://leetcode.com/problems/recover-a-tree-from-preorder-traversal)

[中文文档](/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/README.md)

## Description

<p>We run a&nbsp;preorder&nbsp;depth-first search (DFS) on the <code>root</code> of a binary tree.</p>

<p>At each node in this traversal, we output <code>D</code> dashes (where <code>D</code> is the depth of this node), then we output the value of this node.&nbsp; If the depth of a node is <code>D</code>, the depth of its immediate child is <code>D + 1</code>.&nbsp; The depth of the <code>root</code> node is <code>0</code>.</p>

<p>If a node has only one child, that child is guaranteed to be <strong>the left child</strong>.</p>

<p>Given the output <code>traversal</code> of this traversal, recover the tree and return <em>its</em> <code>root</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/recover-a-tree-from-preorder-traversal.png" style="width: 320px; height: 200px;" />
<pre>
<strong>Input:</strong> traversal = &quot;1-2--3--4-5--6--7&quot;
<strong>Output:</strong> [1,2,5,3,4,6,7]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/screen-shot-2019-04-10-at-114101-pm.png" style="width: 256px; height: 250px;" />
<pre>
<strong>Input:</strong> traversal = &quot;1-2--3---4-5--6---7&quot;
<strong>Output:</strong> [1,2,5,3,null,6,null,4,null,7]
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/screen-shot-2019-04-10-at-114955-pm.png" style="width: 276px; height: 250px;" />
<pre>
<strong>Input:</strong> traversal = &quot;1-401--349---90--88&quot;
<strong>Output:</strong> [1,401,null,349,88,90]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the original tree is in the range <code>[1, 1000]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* recoverFromPreorder(string S) {
        stack<TreeNode*> st;
        int depth = 0;
        int num = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S[i] == '-') {
                depth++;
            } else {
                num = 10 * num + S[i] - '0';
            }
            if (i + 1 >= S.length() || (isdigit(S[i]) && S[i + 1] == '-')) {
                TreeNode* newNode = new TreeNode(num);
                while (st.size() > depth) {
                    st.pop();
                }
                if (!st.empty()) {
                    if (st.top()->left == nullptr) {
                        st.top()->left = newNode;
                    } else {
                        st.top()->right = newNode;
                    }
                }
                st.push(newNode);
                depth = 0;
                num = 0;
            }
        }
        TreeNode* res;
        while (!st.empty()) {
            res = st.top();
            st.pop();
        }
        return res;
    }
};
```

<!-- tabs:end -->

<!-- end -->
