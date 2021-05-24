# [559. Maximum Depth of N-ary Tree](https://leetcode.com/problems/maximum-depth-of-n-ary-tree)

[中文文档](/solution/0500-0599/0559.Maximum%20Depth%20of%20N-ary%20Tree/README.md)

## Description

<p>Given a n-ary tree, find its maximum depth.</p>

<p>The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.</p>

<p><em>Nary-Tree input serialization&nbsp;is represented in their level order traversal, each group of children is separated by the null value (See examples).</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0559.Maximum%20Depth%20of%20N-ary%20Tree/images/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,3,2,4,null,5,6]
<strong>Output:</strong> 3
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0559.Maximum%20Depth%20of%20N-ary%20Tree/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The depth of the n-ary tree is less than or equal to <code>1000</code>.</li>
	<li>The total number of nodes is between <code>[0,&nbsp;10<sup>4</sup>]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""

class Solution:
    def maxDepth(self, root: 'Node') -> int:
        if not root:
            return 0
        max_depth = 1
        for child in root.children:
            max_depth = max(max_depth, 1 + self.maxDepth(child))
        return max_depth
```

### **Java**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int maxDepth = 1;
        for (Node child : root.children) {
            maxDepth = Math.max(maxDepth, 1 + maxDepth(child));
        }
        return maxDepth;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
