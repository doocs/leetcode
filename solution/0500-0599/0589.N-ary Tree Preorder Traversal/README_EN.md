---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0589.N-ary%20Tree%20Preorder%20Traversal/README_EN.md
tags:
    - Stack
    - Tree
    - Depth-First Search
---

# [589. N-ary Tree Preorder Traversal](https://leetcode.com/problems/n-ary-tree-preorder-traversal)

[中文文档](/solution/0500-0599/0589.N-ary%20Tree%20Preorder%20Traversal/README.md)

## Description

<p>Given the <code>root</code> of an n-ary tree, return <em>the preorder traversal of its nodes&#39; values</em>.</p>

<p>Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0589.N-ary%20Tree%20Preorder%20Traversal/images/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,3,2,4,null,5,6]
<strong>Output:</strong> [1,3,5,6,2,4]
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0589.N-ary%20Tree%20Preorder%20Traversal/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>Output:</strong> [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
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

### Solution 1: Recursion

We can recursively traverse the entire tree. For each node, we first add the node's value to the answer, then recursively call the function for each of the node's children.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def preorder(self, root: "Node") -> List[int]:
        def dfs(root):
            if root is None:
                return
            ans.append(root.val)
            for child in root.children:
                dfs(child)

        ans = []
        dfs(root)
        return ans
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
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        dfs(root);
        return ans;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        for (Node child : root.children) {
            dfs(child);
        }
    }
}
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
    vector<int> preorder(Node* root) {
        vector<int> ans;
        function<void(Node*)> dfs = [&](Node* root) {
            if (!root) {
                return;
            }
            ans.push_back(root->val);
            for (auto& child : root->children) {
                dfs(child);
            }
        };
        dfs(root);
        return ans;
    }
};
```

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func preorder(root *Node) (ans []int) {
	var dfs func(*Node)
	dfs = func(root *Node) {
		if root == nil {
			return
		}
		ans = append(ans, root.Val)
		for _, child := range root.Children {
			dfs(child)
		}
	}
	dfs(root)
	return
}
```

```ts
/**
 * Definition for node.
 * class Node {
 *     val: number
 *     children: Node[]
 *     constructor(val?: number) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.children = []
 *     }
 * }
 */

function preorder(root: Node | null): number[] {
    const ans: number[] = [];
    const dfs = (root: Node | null) => {
        if (!root) {
            return;
        }
        ans.push(root.val);
        for (const child of root.children) {
            dfs(child);
        }
    };
    dfs(root);
    return ans;
}
```

```c
/**
 * Definition for a Node.
 * struct Node {
 *     int val;
 *     int numChildren;
 *     struct Node** children;
 * };
 */

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

void dfs(struct Node* root, int* ans, int* i) {
    if (!root) {
        return;
    }
    ans[(*i)++] = root->val;
    for (int j = 0; j < root->numChildren; j++) {
        dfs(root->children[j], ans, i);
    }
}

int* preorder(struct Node* root, int* returnSize) {
    int* ans = malloc(sizeof(int) * 10000);
    *returnSize = 0;
    dfs(root, ans, returnSize);
    return ans;
}
```

<!-- tabs:end -->

### Solution 2: Iteration (Stack Implementation)

We can also solve this problem iteratively.

We use a stack to help us get the pre-order traversal. We first push the root node into the stack. Since the pre-order traversal is root, left subtree, right subtree, and the characteristic of the stack is first in last out, we first add the node's value to the answer, then push each of the node's children into the stack in the order from right to left. We continue this process until the stack is empty.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def preorder(self, root: 'Node') -> List[int]:
        ans = []
        if root is None:
            return ans
        stk = [root]
        while stk:
            node = stk.pop()
            ans.append(node.val)
            for child in node.children[::-1]:
                stk.append(child)
        return ans
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
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> ans = new ArrayList<>();
        Deque<Node> stk = new ArrayDeque<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            Node node = stk.pop();
            ans.add(node.val);
            List<Node> children = node.children;
            for (int i = children.size() - 1; i >= 0; --i) {
                stk.push(children.get(i));
            }
        }
        return ans;
    }
}
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
    vector<int> preorder(Node* root) {
        if (!root) return {};
        vector<int> ans;
        stack<Node*> stk;
        stk.push(root);
        while (!stk.empty()) {
            Node* node = stk.top();
            ans.push_back(node->val);
            stk.pop();
            auto children = node->children;
            for (int i = children.size() - 1; i >= 0; --i) stk.push(children[i]);
        }
        return ans;
    }
};
```

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func preorder(root *Node) (ans []int) {
	if root == nil {
		return
	}
	stk := []*Node{root}
	for len(stk) > 0 {
		node := stk[len(stk)-1]
		ans = append(ans, node.Val)
		stk = stk[:len(stk)-1]
		children := node.Children
		for i := len(children) - 1; i >= 0; i-- {
			stk = append(stk, children[i])
		}
	}
	return
}
```

```ts
/**
 * Definition for node.
 * class Node {
 *     val: number
 *     children: Node[]
 *     constructor(val?: number) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.children = []
 *     }
 * }
 */

function preorder(root: Node | null): number[] {
    const ans: number[] = [];
    if (!root) {
        return ans;
    }
    const stk: Node[] = [root];
    while (stk.length) {
        const { val, children } = stk.pop()!;
        ans.push(val);
        for (let i = children.length - 1; i >= 0; i--) {
            stk.push(children[i]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
