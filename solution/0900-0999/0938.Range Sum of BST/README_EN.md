# [938. Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst)

[中文文档](/solution/0900-0999/0938.Range%20Sum%20of%20BST/README.md)

## Description

<p>Given the <code>root</code> node of a binary search tree, return <em>the sum of values of all nodes with a value in the range <code>[low, high]</code></em>.</p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0938.Range%20Sum%20of%20BST/images/bst1.jpg" style="width: 400px; height: 222px;" />

<pre>

<strong>Input:</strong> root = [10,5,15,3,7,null,18], low = 7, high = 15

<strong>Output:</strong> 32

</pre>



<p><strong>Example 2:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0938.Range%20Sum%20of%20BST/images/bst2.jpg" style="width: 400px; height: 335px;" />

<pre>

<strong>Input:</strong> root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10

<strong>Output:</strong> 23

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 2 * 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= low &lt;= high &lt;= 10<sup>5</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rangeSumBST(self, root, L, R):
        """
        :type root: TreeNode
        :type L: int
        :type R: int
        :rtype: int
        """
        def searchBST(node):
            if not node:
                return
            if L <= node.val <= R:
                self.ans += node.val
                searchBST(node.right)
                searchBST(node.left)
            elif node.val < L:
                searchBST(node.right)
            elif node.val > R:
                searchBST(node.left)
        self.ans = 0
        searchBST(root)
        return self.ans
```

### **Java**

```java
class Solution {
    private int res = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return res;
        }
        
        if (root.val < L) {
            rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            rangeSumBST(root.left, L, R);
        } else {
            res += root.val;
            rangeSumBST(root.left, L, R);
            rangeSumBST(root.right, L, R);
        }
        return res;
        
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int rangeSumBST(TreeNode* root, int L, int R) {
        if (nullptr == root)
            return 0 ;
        stack<TreeNode *> s ;
        s.push(root) ;
        int sum = 0 ;
        while (!s.empty())
        {
            TreeNode *node = s.top() ;
            s.pop() ;
            
            if (nullptr == node)
                continue ;
            
            if (node->val > R)
                s.push(node->left) ;
            else if (node->val < L)
                s.push(node->right) ;
            else
            {
                sum += node->val ;
                s.push(node->left) ;
                s.push(node->right) ;
            }
        }
        
        return sum ;
    }
};

static int x = []()
{
    ios::sync_with_stdio(false); 
    cin.tie(nullptr); 
    return 0; 
}() ;
```

### **...**

```

```

<!-- tabs:end -->
