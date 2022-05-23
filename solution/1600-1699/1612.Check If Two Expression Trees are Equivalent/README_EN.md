# [1612. Check If Two Expression Trees are Equivalent](https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent)

[中文文档](/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/README.md)

## Description

<p>A <strong><a href="https://en.wikipedia.org/wiki/Binary_expression_tree" target="_blank">binary expression tree</a></strong> is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (variables), and internal nodes (nodes with two children) correspond to the operators. In this problem, we only consider the <code>&#39;+&#39;</code> operator (i.e. addition).</p>

<p>You are given the roots of two binary expression trees, <code>root1</code> and <code>root2</code>. Return <code>true</code><em> if the two binary expression trees are equivalent</em>. Otherwise, return <code>false</code>.</p>

<p>Two binary expression trees are equivalent if they <strong>evaluate to the same value</strong> regardless of what the variables are set to.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> root1 = [x], root2 = [x]
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/images/tree1.png" style="width: 211px; height: 131px;" /></strong></p>

<pre>
<strong>Input:</strong> root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,c]
<strong>Output:</strong> true
<strong>Explaination:</strong> <code>a + (b + c) == (b + c) + a</code></pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/images/tree2.png" style="width: 211px; height: 131px;" /></strong></p>

<pre>
<strong>Input:</strong> root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,d]
<strong>Output:</strong> false
<strong>Explaination:</strong> <code>a + (b + c) != (b + d) + a</code>
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in both trees are equal, odd and, in the range <code>[1, 4999]</code>.</li>
	<li><code>Node.val</code> is <code>&#39;+&#39;</code> or a lower-case English letter.</li>
	<li>It&#39;s <strong>guaranteed</strong> that the tree given is a valid binary expression tree.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What will you change in your solution if the tree also supports the <code>&#39;-&#39;</code> operator (i.e. subtraction)?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
