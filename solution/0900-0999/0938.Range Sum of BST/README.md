# [938. 二叉搜索树的范围和](https://leetcode-cn.com/problems/range-sum-of-bst)

[English Version](/solution/0900-0999/0938.Range%20Sum%20of%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定二叉搜索树的根结点 <code>root</code>，返回值位于范围 <em><code>[low, high]</code></em> 之间的所有结点的值的和。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0938.Range%20Sum%20of%20BST/images/bst1.jpg" style="width: 400px; height: 222px;" />
<pre>
<strong>输入：</strong>root = [10,5,15,3,7,null,18], low = 7, high = 15
<strong>输出：</strong>32
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0938.Range%20Sum%20of%20BST/images/bst2.jpg" style="width: 400px; height: 335px;" />
<pre>
<strong>输入：</strong>root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
<strong>输出：</strong>23
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[1, 2 * 10<sup>4</sup>]</code> 内</li>
	<li><code>1 <= Node.val <= 10<sup>5</sup></code></li>
	<li><code>1 <= low <= high <= 10<sup>5</sup></code></li>
	<li>所有 <code>Node.val</code> <strong>互不相同</strong></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
