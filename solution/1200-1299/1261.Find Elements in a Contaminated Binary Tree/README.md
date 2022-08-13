# [1261. 在受污染的二叉树中查找元素](https://leetcode.cn/problems/find-elements-in-a-contaminated-binary-tree)

[English Version](/solution/1200-1299/1261.Find%20Elements%20in%20a%20Contaminated%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个满足下述规则的二叉树：</p>

<ol>
	<li><code>root.val == 0</code></li>
	<li>如果 <code>treeNode.val == x</code> 且&nbsp;<code>treeNode.left != null</code>，那么&nbsp;<code>treeNode.left.val == 2 * x + 1</code></li>
	<li>如果 <code>treeNode.val == x</code> 且 <code>treeNode.right != null</code>，那么&nbsp;<code>treeNode.right.val == 2 * x + 2</code></li>
</ol>

<p>现在这个二叉树受到「污染」，所有的&nbsp;<code>treeNode.val</code>&nbsp;都变成了&nbsp;<code>-1</code>。</p>

<p>请你先还原二叉树，然后实现&nbsp;<code>FindElements</code>&nbsp;类：</p>

<ul>
	<li><code>FindElements(TreeNode* root)</code>&nbsp;用受污染的二叉树初始化对象，你需要先把它还原。</li>
	<li><code>bool find(int target)</code>&nbsp;判断目标值&nbsp;<code>target</code>&nbsp;是否存在于还原后的二叉树中并返回结果。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1261.Find%20Elements%20in%20a%20Contaminated%20Binary%20Tree/images/untitled-diagram-4-1.jpg" style="height: 119px; width: 320px;"></strong></p>

<pre><strong>输入：</strong>
[&quot;FindElements&quot;,&quot;find&quot;,&quot;find&quot;]
[[[-1,null,-1]],[1],[2]]
<strong>输出：</strong>
[null,false,true]
<strong>解释：</strong>
FindElements findElements = new FindElements([-1,null,-1]); 
findElements.find(1); // return False 
findElements.find(2); // return True </pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1261.Find%20Elements%20in%20a%20Contaminated%20Binary%20Tree/images/untitled-diagram-4.jpg" style="height: 198px; width: 400px;"></strong></p>

<pre><strong>输入：</strong>
[&quot;FindElements&quot;,&quot;find&quot;,&quot;find&quot;,&quot;find&quot;]
[[[-1,-1,-1,-1,-1]],[1],[3],[5]]
<strong>输出：</strong>
[null,true,true,false]
<strong>解释：</strong>
FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
findElements.find(1); // return True
findElements.find(3); // return True
findElements.find(5); // return False</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1261.Find%20Elements%20in%20a%20Contaminated%20Binary%20Tree/images/untitled-diagram-4-1-1.jpg" style="height: 274px; width: 306px;"></strong></p>

<pre><strong>输入：</strong>
[&quot;FindElements&quot;,&quot;find&quot;,&quot;find&quot;,&quot;find&quot;,&quot;find&quot;]
[[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
<strong>输出：</strong>
[null,true,false,false,true]
<strong>解释：</strong>
FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
findElements.find(2); // return True
findElements.find(3); // return False
findElements.find(4); // return False
findElements.find(5); // return True
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>TreeNode.val == -1</code></li>
	<li>二叉树的高度不超过&nbsp;<code>20</code></li>
	<li>节点的总数在&nbsp;<code>[1,&nbsp;10^4]</code>&nbsp;之间</li>
	<li>调用&nbsp;<code>find()</code>&nbsp;的总次数在&nbsp;<code>[1,&nbsp;10^4]</code>&nbsp;之间</li>
	<li><code>0 &lt;= target &lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        if (root->left) {
            root->left->val = root->val * 2 + 1;
            nodes.insert(root->left->val);
        }
        if (root->right) {
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
