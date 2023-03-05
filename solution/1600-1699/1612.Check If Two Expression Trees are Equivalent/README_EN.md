# [1612. Check If Two Expression Trees are Equivalent](https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent)

[中文文档](/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/README.md)

## Description

<p>A <strong><a href="https://en.wikipedia.org/wiki/Binary_expression_tree" target="_blank">binary expression tree</a></strong> is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (variables), and internal nodes (nodes with two children) correspond to the operators. In this problem, we only consider the <code>&#39;+&#39;</code> operator (i.e. addition).</p>

<p>You are given the roots of two binary expression trees, <code>root1</code> and <code>root2</code>. Return <code>true</code><em> if the two binary expression trees are equivalent</em>. Otherwise, return <code>false</code>.</p>

<p>Two binary expression trees are equivalent if they <strong>evaluate to the same value</strong> regardless of what the variables are set to.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> root1 = [x], root2 = [x]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/images/tree1.png" style="width: 211px; height: 131px;" /></strong></p>

<pre>
<strong>Input:</strong> root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,c]
<strong>Output:</strong> true
<strong>Explaination:</strong> <code>a + (b + c) == (b + c) + a</code></pre>

<p><strong class="example">Example 3:</strong></p>

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

### **JavaScript**

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

### **...**

```

```

<!-- tabs:end -->
