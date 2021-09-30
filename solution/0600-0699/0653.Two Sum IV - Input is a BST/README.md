# [653. 两数之和 IV - 输入 BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst)

[English Version](/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。</p>

<p><strong>案例 1:</strong></p>

<pre>
<strong>输入:</strong> 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

<strong>输出:</strong> True
</pre>

<p>&nbsp;</p>

<p><strong>案例 2:</strong></p>

<pre>
<strong>输入:</strong> 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

<strong>输出:</strong> False
</pre>

<p>&nbsp;</p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

用哈希表记录访问过的节点。

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
class Solution:
    def findTarget(self, root: TreeNode, k: int) -> bool:
        def find(node):
            if not node:
                return False
            if k - node.val in nodes:
                return True
            nodes.add(node.val)
            return find(node.left) or find(node.right)

        nodes = set()
        return find(root)
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
class Solution {
    private Set<Integer> nodes;

    public boolean findTarget(TreeNode root, int k) {
        nodes = new HashSet<>();
        return find(root, k);
    }

    private boolean find(TreeNode node, int k) {
        if (node == null) {
            return false;
        }
        if (nodes.contains(k - node.val)) {
            return true;
        }
        nodes.add(node.val);
        return find(node.left, k) || find(node.right, k);
    }
}
```

### **TypeScript**

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function findTarget(root: TreeNode | null, k: number): boolean {
    let set: Set<number> = new Set();
    return find(root, k, set);
};


function find(root: TreeNode | null, k: number, set: Set<number>): boolean {
    if (!root) return false;
    if (set.has(k - root.val)) return true;
    set.add(root.val);
    return find(root.left, k, set) || find(root.right, k, set);
}
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
class Solution {
public:
    unordered_set<int> nodes;

    bool findTarget(TreeNode* root, int k) {
        return find(root, k);    
    }

    bool find(TreeNode* node, int k) {
        if (node == nullptr) {
            return false;
        }
        if (nodes.count(k - node->val)) {
            return true;
        }
        nodes.insert(node->val);
        return find(node->left, k) || find(node->right, k);
    }
};
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
func findTarget(root *TreeNode, k int) bool {
	nodes := make(map[int]bool)
	var find func(node *TreeNode, k int) bool
	find = func(node *TreeNode, k int) bool {
		if node == nil {
			return false
		}
		if nodes[k-node.Val] {
			return true
		}
		nodes[node.Val] = true
		return find(node.Left, k) || find(node.Right, k)
	}
	return find(root, k)

}
```

### **...**

```

```

<!-- tabs:end -->
