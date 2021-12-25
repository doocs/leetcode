# [590. N-ary Tree Postorder Traversal](https://leetcode.com/problems/n-ary-tree-postorder-traversal)

[中文文档](/solution/0500-0599/0590.N-ary%20Tree%20Postorder%20Traversal/README.md)

## Description

<p>Given the <code>root</code> of an n-ary tree, return <em>the postorder traversal of its nodes&#39; values</em>.</p>

<p>Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0590.N-ary%20Tree%20Postorder%20Traversal/images/narytreeexample.png" style="width: 100%; max-width: 300px;" />
<pre>
<strong>Input:</strong> root = [1,null,3,2,4,null,5,6]
<strong>Output:</strong> [5,6,3,2,4,1]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0590.N-ary%20Tree%20Postorder%20Traversal/images/sample_4_964.png" style="width: 296px; height: 241px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>Output:</strong> [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>The height of the n-ary tree is less than or equal to <code>1000</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Recursive solution is trivial, could you do it iteratively?</p>

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
    def postorder(self, root: 'Node') -> List[int]:
        def dfs(root):
            if root is None:
                return
            for child in root.children:
                dfs(child)
            ans.append(root.val)

        ans = []
        dfs(root)
        return ans
```

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""

class Solution:
    def postorder(self, root: 'Node') -> List[int]:
        if root is None:
            return []
        stk = [root]
        ans = []
        while stk:
            node = stk.pop()
            ans.append(node.val)
            if node.children:
                for child in node.children:
                    stk.append(child)
        return ans[::-1]
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

    private List<Integer> ans;

    public List<Integer> postorder(Node root) {
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            dfs(child);
        }
        ans.add(root.val);
    }
}
```

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
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<Node> stk = new ArrayDeque<>();
        stk.offerLast(root);
        LinkedList<Integer> ans = new LinkedList<>();
        while (!stk.isEmpty()) {
            Node node = stk.pollLast();
            ans.addFirst(node.val);
            if (node.children != null) {
                for (Node child : node.children) {
                    stk.offerLast(child);
                }
            }
        }
        return ans;
    }
}
```

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
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<Node> stk = new ArrayDeque<>();
        stk.offerLast(root);
        LinkedList<Integer> ans = new LinkedList<>();
        while (!stk.isEmpty()) {
            Node node = stk.pollLast();
            ans.addFirst(node.val);
            if (node.children != null) {
                for (Node child : node.children) {
                    stk.offerLast(child);
                }
            }
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
    vector<int> postorder(Node* root) {
        vector<int> ans;
        dfs(root, ans);
        return ans;
    }

    void dfs(Node* root, vector<int>& ans) {
        if (!root) return;
        for (auto& child : root->children) dfs(child, ans);
        ans.push_back(root->val);
    }
};
```

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
    vector<int> postorder(Node* root) {
        if (!root) return {};
        stack<Node*> stk;
        stk.push(root);
        vector<int> ans;
        while (!stk.empty())
        {
            auto& node = stk.top();
            stk.pop();
            ans.push_back(node->val);
            for (auto& child : node->children)
                stk.push(child);
        }
        reverse(ans.begin(), ans.end());
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

func postorder(root *Node) []int {
	var ans []int
	var dfs func(root *Node)
	dfs = func(root *Node) {
		if root == nil {
			return
		}
		for _, child := range root.Children {
			dfs(child)
		}
		ans = append(ans, root.Val)
	}
	dfs(root)
	return ans
}
```

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func postorder(root *Node) []int {
	if root == nil {
		return []int{}
	}
	var stk []*Node
	var ans []int
	stk = append(stk, root)
	for len(stk) > 0 {
		node := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		ans = append([]int{node.Val}, ans...)
		for _, child := range node.Children {
			stk = append(stk, child)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
