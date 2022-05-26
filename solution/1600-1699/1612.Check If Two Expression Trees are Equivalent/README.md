# [1612. 检查两棵二叉表达式树是否等价](https://leetcode.cn/problems/check-if-two-expression-trees-are-equivalent)

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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class Node(object):
#     def __init__(self, val=" ", left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def checkEquivalence(self, root1: 'Node', root2: 'Node') -> bool:
        counter = [0] * 26

        def dfs(root, incr):
            if root:
                dfs(root.left, incr)
                dfs(root.right, incr)
                if root.val != '+':
                    counter[ord(root.val) - ord('a')] += incr

        dfs(root1, 1)
        dfs(root2, -1)
        return counter.count(0) == 26
```

```python
# Definition for a binary tree node.
# class Node(object):
#     def __init__(self, val=" ", left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def checkEquivalence(self, root1: 'Node', root2: 'Node') -> bool:
        def calc(ans, left, right, op):
            for i in range(26):
                if op == '+':
                    ans[i] = left[i] + right[i]
                else:
                    ans[i] = left[i] - right[i]

        def dfs(root):
            ans = [0] * 26
            if not root:
                return ans
            if root.val in ['+', '-']:
                left, right = dfs(root.left), dfs(root.right)
                calc(ans, left, right, root.val)
            else:
                ans[ord(root.val) - ord('a')] += 1
            return ans

        return dfs(root1) == dfs(root2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    private int[] counter;

    public boolean checkEquivalence(Node root1, Node root2) {
        counter = new int[26];
        dfs(root1, 1);
        dfs(root2, -1);
        for (int n : counter) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }

    private void dfs(Node root, int incr) {
        if (root == null) {
            return;
        }
        dfs(root.left, incr);
        dfs(root.right, incr);
        if (root.val != '+') {
            counter[root.val - 'a'] += incr;
        }
    }
}
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
        int[] ans1 = dfs(root1);
        int[] ans2 = dfs(root2);
        for (int i = 0; i < 26; ++i) {
            if (ans1[i] != ans2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] dfs(Node root) {
        int[] ans = new int[26];
        if (root == null) {
            return ans;
        }
        if (root.val == '+' || root.val == '-') {
            int[] left = dfs(root.left);
            int[] right = dfs(root.right);
            calc(ans, left, right, root.val);
        } else {
            ++ans[root.val - 'a'];
        }
        return ans;
    }

    private void calc(int[] ans, int[] left, int[] right, char op) {
        for (int i = 0; i < 26; ++i) {
            ans[i] = op == '+' ? left[i] + right[i] : left[i] - right[i];
        }
    }
}
```

### **C++**

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
    vector<int> counter;

    bool checkEquivalence(Node* root1, Node* root2) {
        counter.resize(26);
        dfs(root1, 1);
        dfs(root2, -1);
        return count(counter.begin(), counter.end(), 0) == 26;
    }

    void dfs(Node* root, int incr) {
        if (!root) return;
        dfs(root->left, incr);
        dfs(root->right, incr);
        if (root->val != '+') counter[root->val - 'a'] += incr;
    }
};
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
        return dfs(root1) == dfs(root2);
    }

    vector<int> dfs(Node* root) {
        vector<int> ans(26);
        if (!root) return ans;
        if (root->val == '+' || root->val == '-')
        {
            auto left = dfs(root->left);
            auto right = dfs(root->right);
            calc(ans, left, right, root->val);
            return ans;
        }
        ++ans[root->val - 'a'];
        return ans;
    }

    void calc(vector<int>& ans, vector<int>& left, vector<int>& right, char op) {
        for (int i = 0; i < 26; ++i)
            ans[i] = op == '+' ? left[i] + right[i] : left[i] - right[i];
    }
};
```

### **...**

```

```

<!-- tabs:end -->
