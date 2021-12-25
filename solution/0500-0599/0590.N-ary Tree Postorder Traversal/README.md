# [590. N 叉树的后序遍历](https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal)

[English Version](/solution/0500-0599/0590.N-ary%20Tree%20Postorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 N 叉树，返回其节点值的<strong> 后序遍历</strong> 。</p>

<p>N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 <code>null</code> 分隔（请参见示例）。</p>

<div class="original__bRMd">
<div>
<p> </p>

<p><strong>进阶：</strong></p>

<p>递归法很简单，你可以使用迭代法完成此题吗?</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0590.N-ary%20Tree%20Postorder%20Traversal/images/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
<strong>输出：</strong>[5,6,3,2,4,1]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0590.N-ary%20Tree%20Postorder%20Traversal/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出：</strong>[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>N 叉树的高度小于或等于 <code>1000</code></li>
	<li>节点总数在范围 <code>[0, 10^4]</code> 内</li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

递归：

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

迭代：

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

递归：

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

迭代：

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

递归：

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

迭代：

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

递归：

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

迭代：

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
