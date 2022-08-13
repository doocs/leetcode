# [面试题 07. 重建二叉树](https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/)

## 题目描述

<p>输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。</p>

<p>假设输入的前序遍历和中序遍历的结果中都不含重复的数字。</p>

<p> </p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9807.%20%E9%87%8D%E5%BB%BA%E4%BA%8C%E5%8F%89%E6%A0%91/images/tree.jpg" />
<pre>
<strong>Input:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
<strong>Output:</strong> [3,9,20,null,null,15,7]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>Input:</strong> preorder = [-1], inorder = [-1]
<strong>Output:</strong> [-1]
</pre>

<p> </p>

<p><strong>限制：</strong></p>

<p><code>0 <= 节点个数 <= 5000</code></p>

<p> </p>

<p><strong>注意</strong>：本题与主站 105 题重复：<a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/</a></p>

## 解法

前序序列的第一个结点 `preorder[0]` 为根节点，我们在中序序列中找到根节点的位置 `i`，可以将中序序列划分为左子树 `inorder[:i]` 和右子树 `inorder[i + 1:]`。

通过左右子树的区间，可以计算出左、右子树节点的个数，假设为 m、n。然后在前序节点中，从根节点往后的 m 个节点为左子树，再往后的 n 个节点为右子树。

递归求解即可。

> 前序遍历：先遍历根节点，再遍历左子树，最后遍历右子树；
>
> 中序遍历：先遍历左子树，再遍历根节点，最后遍历右子树。

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        if not preorder:
            return None
        v = preorder[0]
        root = TreeNode(val=v)
        i = inorder.index(v)
        root.left = self.buildTree(preorder[1 : 1 + i], inorder[:i])
        root.right = self.buildTree(preorder[1 + i :], inorder[i + 1 :])
        return root
```

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Map<Integer, Integer> indexes = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; ++i) {
            indexes.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, 0, preorder.length);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = preorder[i];
        int k = indexes.get(v);
        TreeNode root = new TreeNode(v);
        root.left = dfs(preorder, inorder, i + 1, j, k - j);
        root.right = dfs(preorder, inorder, i + 1 + k - j, k + 1, n - k + j - 1);
        return root;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
var buildTree = function (preorder, inorder) {
    if (preorder.length == 0) return null;
    const v = preorder[0];
    const root = new TreeNode(v);
    const i = inorder.indexOf(v);
    root.left = buildTree(preorder.slice(1, 1 + i), inorder.slice(0, i));
    root.right = buildTree(preorder.slice(1 + i), inorder.slice(1 + i));
    return root;
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
func buildTree(preorder []int, inorder []int) *TreeNode {
	indexes := make(map[int]int)
	for i, v := range inorder {
		indexes[v] = i
	}
	var dfs func(i, j, n int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		v := preorder[i]
		k := indexes[v]
		root := &TreeNode{Val: v}
		root.Left = dfs(i+1, j, k-j)
		root.Right = dfs(i+1+k-j, k+1, n-k+j-1)
		return root
	}
	return dfs(0, 0, len(inorder))
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    unordered_map<int, int> indexes;

    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        for (int i = 0; i < inorder.size(); ++i) indexes[inorder[i]] = i;
        return dfs(preorder, inorder, 0, 0, inorder.size());
    }

    TreeNode* dfs(vector<int>& preorder, vector<int>& inorder, int i, int j, int n) {
        if (n <= 0) return nullptr;
        int v = preorder[i];
        int k = indexes[v];
        TreeNode* root = new TreeNode(v);
        root->left = dfs(preorder, inorder, i + 1, j, k - j);
        root->right = dfs(preorder, inorder, i + 1 + k - j, k + 1, n - k + j - 1);
        return root;
    }
};
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

function buildTree(preorder: number[], inorder: number[]): TreeNode | null {
    if (preorder.length == 0) return null;
    let val: number = preorder[0];
    let node: TreeNode = new TreeNode(val);
    let index: number = inorder.indexOf(val);
    node.left = buildTree(
        preorder.slice(1, index + 1),
        inorder.slice(0, index),
    );
    node.right = buildTree(preorder.slice(index + 1), inorder.slice(index + 1));
    return node;
}
```

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

function buildTree(preorder: number[], inorder: number[]): TreeNode | null {
    if (inorder.length === 0) {
        return null;
    }
    const val = preorder[0];
    const i = inorder.indexOf(val);
    return new TreeNode(
        val,
        buildTree(preorder.slice(1, i + 1), inorder.slice(0, i)),
        buildTree(preorder.slice(i + 1), inorder.slice(i + 1)),
    );
}
```

### **Rust**

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    fn help(preorder: &[i32], inorder: &[i32]) -> Option<Rc<RefCell<TreeNode>>> {
        if inorder.is_empty() {
            return None;
        }
        let val = preorder[0];
        let i = inorder.iter().position(|num| *num == val).unwrap();
        Some(Rc::new(RefCell::new(TreeNode {
            val,
            left: Self::help(&preorder[1..i + 1], &inorder[..i]),
            right: Self::help(&preorder[i + 1..], &inorder[i + 1..]),
        })))
    }

    pub fn build_tree(preorder: Vec<i32>, inorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        Self::help(&preorder, &inorder)
    }
}
```

### **C#**

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode BuildTree(int[] preorder, int[] inorder) {
        if (preorder.Length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int idx = Array.IndexOf(inorder, root.val);
        root.left = BuildTree(preorder[1..(index+1)], inorder[0..idx]);
        root.right = BuildTree(preorder[(index+1)..], inorder[(idx+1)..]);
        return root;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
