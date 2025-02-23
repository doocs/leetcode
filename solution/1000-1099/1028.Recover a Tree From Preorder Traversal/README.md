---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/README.md
rating: 1797
source: 第 132 场周赛 Q4
tags:
    - 树
    - 深度优先搜索
    - 字符串
    - 二叉树
---

<!-- problem:start -->

# [1028. 从先序遍历还原二叉树](https://leetcode.cn/problems/recover-a-tree-from-preorder-traversal)

[English Version](/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们从二叉树的根节点 <code>root</code>&nbsp;开始进行深度优先搜索。</p>

<p>在遍历中的每个节点处，我们输出&nbsp;<code>D</code>&nbsp;条短划线（其中&nbsp;<code>D</code>&nbsp;是该节点的深度），然后输出该节点的值。（<em>如果节点的深度为 <code>D</code>，则其直接子节点的深度为 <code>D + 1</code>。根节点的深度为 <code>0</code>）。</em></p>

<p>如果节点只有一个子节点，那么保证该子节点为左子节点。</p>

<p>给出遍历输出&nbsp;<code>S</code>，还原树并返回其根节点&nbsp;<code>root</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/recover-a-tree-from-preorder-traversal.png" style="height: 200px; width: 320px;"></strong></p>

<pre><strong>输入：</strong>&quot;1-2--3--4-5--6--7&quot;
<strong>输出：</strong>[1,2,5,3,4,6,7]
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/screen-shot-2019-04-10-at-114101-pm.png" style="height: 250px; width: 256px;"></strong></p>

<pre><strong>输入：</strong>&quot;1-2--3---4-5--6---7&quot;
<strong>输出：</strong>[1,2,5,3,null,6,null,4,null,7]
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/screen-shot-2019-04-10-at-114955-pm.png" style="height: 250px; width: 276px;"></p>

<pre><strong>输入：</strong>&quot;1-401--349---90--88&quot;
<strong>输出：</strong>[1,401,null,349,88,90]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>原始树中的节点数介于 <code>1</code> 和 <code>1000</code> 之间。</li>
	<li>每个节点的值介于 <code>1</code> 和 <code>10 ^ 9</code> 之间。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
