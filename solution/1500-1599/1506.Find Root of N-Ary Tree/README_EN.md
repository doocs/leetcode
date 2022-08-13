# [1506. Find Root of N-Ary Tree](https://leetcode.com/problems/find-root-of-n-ary-tree)

[中文文档](/solution/1500-1599/1506.Find%20Root%20of%20N-Ary%20Tree/README.md)

## Description

<p>You are given all the nodes of an <strong><a href="https://leetcode.com/articles/introduction-to-n-ary-trees/">N-ary tree</a></strong> as an array of <code>Node</code> objects, where each node has a <strong>unique value</strong>.</p>

<p>Return <em>the <strong>root</strong> of the N-ary tree</em>.</p>

<p><strong>Custom testing:</strong></p>

<p>An N-ary tree can be serialized as represented in its level order traversal where each group of children is separated by the <code>null</code> value (see examples).</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1506.Find%20Root%20of%20N-Ary%20Tree/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<p>For example, the above tree is serialized as <code>[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]</code>.</p>

<p>The testing will be done in the following way:</p>

<ol>
	<li>The <strong>input data</strong> should be provided as a serialization of the tree.</li>
	<li>The driver code will construct the tree from the serialized input data and put each <code>Node</code> object into an array <strong>in an arbitrary order</strong>.</li>
	<li>The driver code will pass the array to <code>findRoot</code>, and your function should find and return the root <code>Node</code> object in the array.</li>
	<li>The driver code will take the returned <code>Node</code> object and serialize it. If the serialized value and the input data are the <strong>same</strong>, the test <strong>passes</strong>.</li>
</ol>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1506.Find%20Root%20of%20N-Ary%20Tree/images/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>Input:</strong> tree = [1,null,3,2,4,null,5,6]
<strong>Output:</strong> [1,null,3,2,4,null,5,6]
<strong>Explanation:</strong> The tree from the input data is shown above.
The driver code creates the tree and gives findRoot the Node objects in an arbitrary order.
For example, the passed array could be [Node(5),Node(4),Node(3),Node(6),Node(2),Node(1)] or [Node(2),Node(6),Node(1),Node(3),Node(5),Node(4)].
The findRoot function should return the root Node(1), and the driver code will serialize it and compare with the input data.
The input data and serialized Node(1) are the same, so the test passes.
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1506.Find%20Root%20of%20N-Ary%20Tree/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>Input:</strong> tree = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>Output:</strong> [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The total number of nodes is between <code>[1, 5 * 10<sup>4</sup>]</code>.</li>
	<li>Each node has a <strong>unique</strong> value.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Could you solve this problem in constant space complexity with a linear time algorithm?</li>
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
        self.children = children if children is not None else []
"""


class Solution:
    def findRoot(self, tree: List['Node']) -> 'Node':
        xorsum = 0
        for node in tree:
            xorsum ^= node.val
            for child in node.children:
                xorsum ^= child.val

        for node in tree:
            if node.val == xorsum:
                return node
```

### **Java**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public Node findRoot(List<Node> tree) {
        int xor = 0;
        for (Node node : tree) {
            xor ^= node.val;
            for (Node child : node.children) {
                xor ^= child.val;
            }
        }
        for (Node node :tree) {
            if (node.val == xor) {
                return node;
            }
        }
        return null;
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
    Node* findRoot(vector<Node*> tree) {
        int xorsum = 0;
        for (auto& node : tree) {
            xorsum ^= node->val;
            for (auto& child : node->children) {
                xorsum ^= child->val;
            }
        }
        for (auto& node : tree) {
            if (node->val == xorsum) {
                return node;
            }
        }
        return nullptr;
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

func findRoot(tree []*Node) *Node {
	xorsum := 0
	for _, node := range tree {
		xorsum ^= node.Val
		for _, child := range node.Children {
			xorsum ^= child.Val
		}
	}
	for _, node := range tree {
		if node.Val == xorsum {
			return node
		}
	}
	return nil
}
```

### **...**

```

```

<!-- tabs:end -->
