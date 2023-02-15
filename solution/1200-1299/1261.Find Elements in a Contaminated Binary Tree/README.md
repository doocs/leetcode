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

**方法一：DFS + 哈希表**

我们先通过 DFS 遍历二叉树，将节点值恢复为原来的值，然后再通过哈希表存储所有节点值，这样在查找时就可以直接判断目标值是否存在于哈希表中。

时间复杂度方面，初始化时需要遍历二叉树，时间复杂度为 $O(n)$，查找时只需要判断哈希表中是否存在目标值，时间复杂度为 $O(1)$。空间复杂度 $O(n)$。其中 $n$ 为二叉树节点个数。

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

    def __init__(self, root: Optional[TreeNode]):
        def dfs(root):
            self.vis.add(root.val)
            if root.left:
                root.left.val = root.val * 2 + 1
                dfs(root.left)
            if root.right:
                root.right.val = root.val * 2 + 2
                dfs(root.right)

        root.val = 0
        self.vis = set()
        dfs(root)

    def find(self, target: int) -> bool:
        return target in self.vis


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
    private Set<Integer> vis = new HashSet<>();

    public FindElements(TreeNode root) {
        root.val = 0;
        dfs(root);
    }

    private void dfs(TreeNode root) {
        vis.add(root.val);
        if (root.left != null) {
            root.left.val = root.val * 2 + 1;
            dfs(root.left);
        }
        if (root.right != null) {
            root.right.val = root.val * 2 + 2;
            dfs(root.right);
        }
    }

    public boolean find(int target) {
        return vis.contains(target);
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
    FindElements(TreeNode* root) {
        root->val = 0;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            vis.insert(root->val);
            if (root->left) {
                root->left->val = root->val * 2 + 1;
                dfs(root->left);
            }
            if (root->right) {
                root->right->val = root->val * 2 + 2;
                dfs(root->right);
            }
        };
        dfs(root);
    }

    bool find(int target) {
        return vis.count(target);
    }

private:
    unordered_set<int> vis;
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
	vis map[int]bool
}

func Constructor(root *TreeNode) FindElements {
	root.Val = 0
	vis := map[int]bool{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		vis[root.Val] = true
		if root.Left != nil {
			root.Left.Val = root.Val*2 + 1
			dfs(root.Left)
		}
		if root.Right != nil {
			root.Right.Val = root.Val*2 + 2
			dfs(root.Right)
		}
	}
	dfs(root)
	return FindElements{vis}
}

func (this *FindElements) Find(target int) bool {
	return this.vis[target]
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
