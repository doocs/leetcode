# [559. Maximum Depth of N-ary Tree](https://leetcode.com/problems/maximum-depth-of-n-ary-tree)

[中文文档](/solution/0500-0599/0559.Maximum%20Depth%20of%20N-ary%20Tree/README.md)

## Description

<p>Given a n-ary tree, find its maximum depth.</p>

<p>The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.</p>

<p><em>Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0559.Maximum%20Depth%20of%20N-ary%20Tree/images/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,3,2,4,null,5,6]
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0559.Maximum%20Depth%20of%20N-ary%20Tree/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The total number of nodes is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li>The depth of the n-ary tree is less than or equal to <code>1000</code>.</li>
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
        if root is None:
            return 0
        return 1 + max([self.maxDepth(child) for child in root.children], default=0)
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
        int ans = 1;
        for (Node child : root.children) {
            ans = Math.max(ans, 1 + maxDepth(child));
        }
        return ans;
    }
}
```

### **C++**

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    int maxDepth(Node* root) {
        if (!root) return 0;
        int ans = 1;
        for (auto& child : root->children) ans = max(ans, 1 + maxDepth(child));
        return ans;
    }
};
```

### **Go**

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func maxDepth(root *Node) int {
	if root == nil {
		return 0
	}
	ans := 1
	for _, child := range root.Children {
		ans = max(ans, 1+maxDepth(child))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
