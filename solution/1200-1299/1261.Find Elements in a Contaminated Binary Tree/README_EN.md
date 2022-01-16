# [1261. Find Elements in a Contaminated Binary Tree](https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree)

[中文文档](/solution/1200-1299/1261.Find%20Elements%20in%20a%20Contaminated%20Binary%20Tree/README.md)

## Description

<p>Given a&nbsp;binary tree with the following rules:</p>

<ol>
	<li><code>root.val == 0</code></li>
	<li>If <code>treeNode.val == x</code> and <code>treeNode.left != null</code>, then <code>treeNode.left.val == 2 * x + 1</code></li>
	<li>If <code>treeNode.val == x</code> and <code>treeNode.right != null</code>, then <code>treeNode.right.val == 2 * x + 2</code></li>
</ol>

<p>Now the binary tree is contaminated, which means all&nbsp;<code>treeNode.val</code>&nbsp;have&nbsp;been changed to <code>-1</code>.</p>

<p>You need to first recover the binary tree and then implement the <code>FindElements</code> class:</p>

<ul>
	<li><code>FindElements(TreeNode* root)</code>&nbsp;Initializes the object with a&nbsp;contamined binary tree, you need to recover it first.</li>
	<li><code>bool find(int target)</code>&nbsp;Return if the <code>target</code> value exists in the recovered binary tree.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1261.Find%20Elements%20in%20a%20Contaminated%20Binary%20Tree/images/untitled-diagram-4-1.jpg" style="width: 320px; height: 119px;" /></strong></p>

<pre>
<strong>Input</strong>
[&quot;FindElements&quot;,&quot;find&quot;,&quot;find&quot;]
[[[-1,null,-1]],[1],[2]]
<strong>Output</strong>
[null,false,true]
<strong>Explanation</strong>
FindElements findElements = new FindElements([-1,null,-1]); 
findElements.find(1); // return False 
findElements.find(2); // return True </pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1261.Find%20Elements%20in%20a%20Contaminated%20Binary%20Tree/images/untitled-diagram-4.jpg" style="width: 400px; height: 198px;" /></strong></p>

<pre>
<strong>Input</strong>
[&quot;FindElements&quot;,&quot;find&quot;,&quot;find&quot;,&quot;find&quot;]
[[[-1,-1,-1,-1,-1]],[1],[3],[5]]
<strong>Output</strong>
[null,true,true,false]
<strong>Explanation</strong>
FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
findElements.find(1); // return True
findElements.find(3); // return True
findElements.find(5); // return False</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1261.Find%20Elements%20in%20a%20Contaminated%20Binary%20Tree/images/untitled-diagram-4-1-1.jpg" style="width: 306px; height: 274px;" /></strong></p>

<pre>
<strong>Input</strong>
[&quot;FindElements&quot;,&quot;find&quot;,&quot;find&quot;,&quot;find&quot;,&quot;find&quot;]
[[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
<strong>Output</strong>
[null,true,false,false,true]
<strong>Explanation</strong>
FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
findElements.find(2); // return True
findElements.find(3); // return False
findElements.find(4); // return False
findElements.find(5); // return True
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>TreeNode.val == -1</code></li>
	<li>The height of the binary tree is less than or equal to <code>20</code></li>
	<li>The total number of nodes is between <code>[1,&nbsp;10^4]</code></li>
	<li>Total calls of <code>find()</code> is between <code>[1,&nbsp;10^4]</code></li>
	<li><code>0 &lt;= target &lt;= 10^6</code></li>
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
class FindElements:

    def __init__(self, root: TreeNode):
        root.val = 0
        self.nodes = {0}

        def dfs(root):
            if root is None:
                return
            if root.left:
                root.left.val = root.val * 2 + 1
                self.nodes.add(root.left.val)
            if root.right:
                root.right.val = root.val * 2 + 2
                self.nodes.add(root.right.val)
            dfs(root.left)
            dfs(root.right)

        dfs(root)

    def find(self, target: int) -> bool:
        return target in self.nodes


# Your FindElements object will be instantiated and called as such:
# obj = FindElements(root)
# param_1 = obj.find(target)
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
class FindElements {
    private Set<Integer> nodes;

    public FindElements(TreeNode root) {
        nodes = new HashSet<>();
        root.val = 0;
        nodes.add(0);
        dfs(root);
    }

    public boolean find(int target) {
        return nodes.contains(target);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            root.left.val = root.val * 2 + 1;
            nodes.add(root.left.val);
        }
        if (root.right != null) {
            root.right.val = root.val * 2 + 2;
            nodes.add(root.right.val);
        }
        dfs(root.left);
        dfs(root.right);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class FindElements {
public:
    unordered_set<int> nodes;

    FindElements(TreeNode* root) {
        root->val = 0;
        nodes.clear();
        nodes.insert(0);
        dfs(root);
    }

    bool find(int target) {
        return nodes.count(target);
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        if (root->left)
        {
            root->left->val = root->val * 2 + 1;
            nodes.insert(root->left->val);
        }
        if (root->right)
        {
            root->right->val = root->val * 2 + 2;
            nodes.insert(root->right->val);
        }
        dfs(root->left);
        dfs(root->right);
    }
};

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements* obj = new FindElements(root);
 * bool param_1 = obj->find(target);
 */
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type FindElements struct {
	nodes map[int]bool
}

func Constructor(root *TreeNode) FindElements {
	root.Val = 0
	nodes := make(map[int]bool)
	nodes[0] = true
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		if root.Left != nil {
			root.Left.Val = root.Val*2 + 1
			nodes[root.Left.Val] = true
		}
		if root.Right != nil {
			root.Right.Val = root.Val*2 + 2
			nodes[root.Right.Val] = true
		}
		dfs(root.Left)
		dfs(root.Right)
	}
	dfs(root)
	return FindElements{nodes}
}

func (this *FindElements) Find(target int) bool {
	return this.nodes[target]
}

/**
 * Your FindElements object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Find(target);
 */
```

### **...**

```

```

<!-- tabs:end -->
