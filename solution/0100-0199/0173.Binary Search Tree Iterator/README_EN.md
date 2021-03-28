# [173. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator)

[中文文档](/solution/0100-0199/0173.Binary%20Search%20Tree%20Iterator/README.md)

## Description

<p>Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.</p>

<p>Calling <code>next()</code> will return the next smallest number in the BST.</p>

<p>&nbsp;</p>

<ul>

</ul>

<p><strong>Example:</strong></p>

![](./images/bst-tree.png)

<pre>

BSTIterator iterator = new BSTIterator(root);

iterator.next();    // return 3

iterator.next();    // return 7

iterator.hasNext(); // return true

iterator.next();    // return 9

iterator.hasNext(); // return true

iterator.next();    // return 15

iterator.hasNext(); // return true

iterator.next();    // return 20

iterator.hasNext(); // return false

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ul>
	<li><code>next()</code> and <code>hasNext()</code> should run in average O(1) time and uses O(<i>h</i>) memory, where <i>h</i> is the height of the tree.</li>
	<li>You may assume that&nbsp;<code>next()</code>&nbsp;call&nbsp;will always be valid, that is, there will be at least a next smallest number in the BST when <code>next()</code> is called.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class BSTIterator:

    def __init__(self, root: TreeNode):
        def inorder(root):
            if root is None:
                return
            inorder(root.left)
            self.vals.append(root.val)
            inorder(root.right)

        self.cur = 0
        self.vals = []
        inorder(root)

    def next(self) -> int:
        res = self.vals[self.cur]
        self.cur += 1
        return res

    def hasNext(self) -> bool:
        return self.cur < len(self.vals)


# Your BSTIterator object will be instantiated and called as such:
# obj = BSTIterator(root)
# param_1 = obj.next()
# param_2 = obj.hasNext()
```

### **Java**

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
class BSTIterator {

    private List<Integer> vals;
    private int next;

    public BSTIterator(TreeNode root) {
        next = 0;
        vals = new ArrayList<>();
        inorder(root);
    }

    public int next() {
        return vals.get(next++);
    }

    public boolean hasNext() {
        return next < vals.size();
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        vals.add(root.val);
        inorder(root.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

### **...**

```

```

<!-- tabs:end -->
