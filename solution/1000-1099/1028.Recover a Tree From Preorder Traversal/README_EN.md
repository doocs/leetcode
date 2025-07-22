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

<!-- problem:start -->

# [1028. Recover a Tree From Preorder Traversal](https://leetcode.com/problems/recover-a-tree-from-preorder-traversal)

[中文文档](/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/README.md)

## Description

<!-- description:start -->

<p>We run a&nbsp;preorder&nbsp;depth-first search (DFS) on the <code>root</code> of a binary tree.</p>

<p>At each node in this traversal, we output <code>D</code> dashes (where <code>D</code> is the depth of this node), then we output the value of this node.&nbsp; If the depth of a node is <code>D</code>, the depth of its immediate child is <code>D + 1</code>.&nbsp; The depth of the <code>root</code> node is <code>0</code>.</p>

<p>If a node has only one child, that child is guaranteed to be <strong>the left child</strong>.</p>

<p>Given the output <code>traversal</code> of this traversal, recover the tree and return <em>its</em> <code>root</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/recover_tree_ex1.png" style="width: 423px; height: 200px;" />
<pre>
<strong>Input:</strong> traversal = &quot;1-2--3--4-5--6--7&quot;
<strong>Output:</strong> [1,2,5,3,4,6,7]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/recover_tree_ex2.png" style="width: 432px; height: 250px;" />
<pre>
<strong>Input:</strong> traversal = &quot;1-2--3---4-5--6---7&quot;
<strong>Output:</strong> [1,2,5,3,null,6,null,4,null,7]
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/recover_tree_ex3.png" style="width: 305px; height: 250px;" />
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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

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
    public TreeNode recoverFromPreorder(String traversal) {
        Stack<TreeNode> stack = new Stack<>();
        int i = 0;

        while (i < traversal.length()) {
            int depth = 0;
            while (i < traversal.length() && traversal.charAt(i) == '-') {
                depth++;
                i++;
            }

            int num = 0;
            while (i < traversal.length() && Character.isDigit(traversal.charAt(i))) {
                num = num * 10 + (traversal.charAt(i) - '0');
                i++;
            }

            // Create the new node
            TreeNode newNode = new TreeNode(num);

            while (stack.size() > depth) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = newNode;
                } else {
                    stack.peek().right = newNode;
                }
            }

            stack.push(newNode);
        }
        return stack.isEmpty() ? null : stack.get(0);
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

#### TypeScript

```ts
function recoverFromPreorder(traversal: string): TreeNode | null {
    const stack: TreeNode[] = [];
    let i = 0;

    while (i < traversal.length) {
        let depth = 0;
        while (i < traversal.length && traversal[i] === '-') {
            depth++;
            i++;
        }

        let num = 0;
        while (i < traversal.length && !Number.isNaN(+traversal[i])) {
            num = num * 10 + +traversal[i];
            i++;
        }

        // Create the new node
        const newNode = new TreeNode(num);

        while (stack.length > depth) {
            stack.pop();
        }

        if (stack.length > 0) {
            const i = stack.length - 1;
            if (stack[i].left === null) {
                stack[i].left = newNode;
            } else {
                stack[i].right = newNode;
            }
        }

        stack.push(newNode);
    }

    return stack.length ? stack[0] : null;
}
```

#### JavaScript

```js
function recoverFromPreorder(traversal) {
    const stack = [];
    let i = 0;

    while (i < traversal.length) {
        let depth = 0;
        while (i < traversal.length && traversal[i] === '-') {
            depth++;
            i++;
        }

        let num = 0;
        while (i < traversal.length && !Number.isNaN(+traversal[i])) {
            num = num * 10 + +traversal[i];
            i++;
        }

        // Create the new node
        const newNode = new TreeNode(num);

        while (stack.length > depth) {
            stack.pop();
        }

        if (stack.length > 0) {
            const i = stack.length - 1;
            if (stack[i].left === null) {
                stack[i].left = newNode;
            } else {
                stack[i].right = newNode;
            }
        }

        stack.push(newNode);
    }

    return stack.length ? stack[0] : null;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
