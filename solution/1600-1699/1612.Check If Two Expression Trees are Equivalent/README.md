---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/README.md
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

# [1612. 检查两棵二叉表达式树是否等价 🔒](https://leetcode.cn/problems/check-if-two-expression-trees-are-equivalent)

[English Version](/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong><a href="https://en.wikipedia.org/wiki/Binary_expression_tree" target="_blank">二叉表达式树</a></strong>是一种表达算术表达式的二叉树。二叉表达式树中的每一个节点都有零个或两个子节点。&nbsp;叶节点（有 0 个子节点的节点）表示操作数，非叶节点（有 2 个子节点的节点）表示运算符。在本题中，我们只考虑 <code>'+'</code> 运算符（即加法）。</p>

<p>给定两棵二叉表达式树的根节点&nbsp;<code>root1</code>&nbsp;和&nbsp;<code>root2</code>&nbsp;。<em>如果两棵二叉表达式树等价</em>，返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>当两棵二叉搜索树中的变量取任意值，<strong>分别求得的值都相等</strong>时，我们称这两棵二叉表达式树是等价的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入：</b> root1 = [x], root2 = [x]
<b>输出：</b> true
</pre>

<p><strong>示例 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/images/tree1.png" style="width: 211px; height: 131px;" /></strong></p>

<pre>
<b>输入：</b>root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,c]
<b>输出：</b>true
<code><span style=""><b>解释：</b></span>a + (b + c) == (b + c) + a</code></pre>

<p><strong>示例 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/images/tree2.png" style="width: 211px; height: 131px;" /></strong></p>

<pre>
<b>输入：</b> root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,d]
<b>输出：</b> false
<b>解释：</b> <code>a + (b + c) != (b + d) + a</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>两棵树中的节点个数相等，且节点个数为范围&nbsp;<code>[1, 4999]</code>&nbsp;内的奇数。</li>
	<li><code>Node.val</code>&nbsp;是&nbsp;<code>'+'</code>&nbsp;或小写英文字母。</li>
	<li>给定的树<strong>保证</strong>是有效的二叉表达式树。</li>
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b>当你的答案需同时支持&nbsp;<code>'-'</code>&nbsp;运算符（减法）时，你该如何修改你的答案</p>

## 解法

### 方法一：递归

我们定义一个计数器 $cnt$，用于统计每个字母出现的次数。

然后我们分别对两棵二叉表达式树进行深度优先搜索，如果字母出现在左子树，则 $cnt$ 中对应的字母的值加 $1$，如果出现在右子树，则 $cnt$ 中对应的字母的值减 $1$。

最后，我们遍历 $cnt$，如果所有字母的值都为 $0$，则返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉表达式树的节点个数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class Node(object):
#     def __init__(self, val=" ", left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def checkEquivalence(self, root1: 'Node', root2: 'Node') -> bool:
        def dfs(root, v):
            if root is None:
                return
            if root.val != '+':
                cnt[root.val] += v
            dfs(root.left, v)
            dfs(root.right, v)

        cnt = Counter()
        dfs(root1, 1)
        dfs(root2, -1)
        return all(x == 0 for x in cnt.values())
```

```java
/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int[] cnt = new int[26];

    public boolean checkEquivalence(Node root1, Node root2) {
        dfs(root1, 1);
        dfs(root2, -1);
        for (int x : cnt) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }

    private void dfs(Node root, int v) {
        if (root == null) {
            return;
        }
        if (root.val != '+') {
            cnt[root.val - 'a'] += v;
        }
        dfs(root.left, v);
        dfs(root.right, v);
    }
}
```

```cpp
/**
 * Definition for a binary tree node.
 * struct Node {
 *     char val;
 *     Node *left;
 *     Node *right;
 *     Node() : val(' '), left(nullptr), right(nullptr) {}
 *     Node(char x) : val(x), left(nullptr), right(nullptr) {}
 *     Node(char x, Node *left, Node *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool checkEquivalence(Node* root1, Node* root2) {
        int cnt[26]{};
        function<void(Node*, int)> dfs = [&](Node* root, int v) {
            if (!root) {
                return;
            }
            if (root->val != '+') {
                cnt[root->val - 'a'] += v;
            }
            dfs(root->left, v);
            dfs(root->right, v);
        };
        dfs(root1, 1);
        dfs(root2, -1);
        for (int& x : cnt) {
            if (x) {
                return false;
            }
        }
        return true;
    }
};
```

```js
/**
 * Definition for a binary tree node.
 * function Node(val, left, right) {
 *     this.val = (val===undefined ? " " : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {Node} root1
 * @param {Node} root2
 * @return {boolean}
 */
var checkEquivalence = function (root1, root2) {
    const cnt = new Array(26).fill(0);
    const dfs = (root, v) => {
        if (!root) {
            return;
        }
        if (root.val !== '+') {
            cnt[root.val.charCodeAt(0) - 'a'.charCodeAt(0)] += v;
        }
        dfs(root.left, v);
        dfs(root.right, v);
    };
    dfs(root1, 1);
    dfs(root2, -1);
    for (const x of cnt) {
        if (x) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class Node(object):
#     def __init__(self, val=" ", left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def checkEquivalence(self, root1: 'Node', root2: 'Node') -> bool:
        def dfs(root):
            cnt = [0] * 26
            if root is None:
                return cnt
            if root.val in '+-':
                l, r = dfs(root.left), dfs(root.right)
                k = 1 if root.val == '+' else -1
                for i in range(26):
                    cnt[i] += l[i] + r[i] * k
            else:
                cnt[ord(root.val) - ord('a')] += 1
            return cnt

        return dfs(root1) == dfs(root2)
```

```java
/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] cnt1 = dfs(root1);
        int[] cnt2 = dfs(root2);
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] != cnt2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] dfs(Node root) {
        int[] cnt = new int[26];
        if (root == null) {
            return cnt;
        }
        if (root.val == '+' || root.val == '-') {
            int[] l = dfs(root.left);
            int[] r = dfs(root.right);
            int k = root.val == '+' ? 1 : -1;
            for (int i = 0; i < 26; ++i) {
                cnt[i] += l[i] + r[i] * k;
            }
        } else {
            cnt[root.val - 'a']++;
        }
        return cnt;
    }
}
```

```cpp
/**
 * Definition for a binary tree node.
 * struct Node {
 *     char val;
 *     Node *left;
 *     Node *right;
 *     Node() : val(' '), left(nullptr), right(nullptr) {}
 *     Node(char x) : val(x), left(nullptr), right(nullptr) {}
 *     Node(char x, Node *left, Node *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool checkEquivalence(Node* root1, Node* root2) {
        function<vector<int>(Node*)> dfs = [&](Node* root) -> vector<int> {
            vector<int> cnt(26);
            if (!root) {
                return cnt;
            }
            if (root->val == '+' || root->val == '-') {
                auto l = dfs(root->left);
                auto r = dfs(root->right);
                int k = root->val == '+' ? 1 : -1;
                for (int i = 0; i < 26; ++i) {
                    cnt[i] += l[i] + r[i] * k;
                }
            } else {
                cnt[root->val - 'a']++;
            }
            return cnt;
        };
        return dfs(root1) == dfs(root2);
    }
};
```

```js
/**
 * Definition for a binary tree node.
 * function Node(val, left, right) {
 *     this.val = (val===undefined ? " " : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {Node} root1
 * @param {Node} root2
 * @return {boolean}
 */
var checkEquivalence = function (root1, root2) {
    const dfs = root => {
        const cnt = new Array(26).fill(0);
        if (!root) {
            return cnt;
        }
        if (root.val === '+' || root.val === '-') {
            const l = dfs(root.left);
            const r = dfs(root.right);
            const k = root.val === '+' ? 1 : -1;
            for (let i = 0; i < 26; ++i) {
                cnt[i] = l[i] + k * r[i];
            }
        } else {
            cnt[root.val.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        return cnt;
    };
    const cnt1 = dfs(root1);
    const cnt2 = dfs(root2);
    for (let i = 0; i < 26; ++i) {
        if (cnt1[i] !== cnt2[i]) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- end -->
