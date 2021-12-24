# [面试题 54. 二叉搜索树的第 k 大节点](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/)

## 题目描述

给定一棵二叉搜索树，请找出其中第 k 大的节点。

**示例 1:**

```
输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 4
```

**示例 2:**

```
输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 4
```

**限制：**

- `1 ≤ k ≤ 二叉搜索树元素个数`

## 解法

先遍历右子树，访问根节点，再遍历左子树。遍历到第 k 个结点时，存储结果。

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
    def kthLargest(self, root: TreeNode, k: int) -> int:
        def inorder(root):
            if root is None:
                return
            inorder(root.right)
            self.cur -= 1
            if self.cur == 0:
                self.res = root.val
                return
            inorder(root.left)

        self.cur = k
        inorder(root)
        return self.res
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
    private int cur;
    private int res;

    public int kthLargest(TreeNode root, int k) {
        cur = k;
        res = 0;
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.right);
        --cur;
        if (cur == 0) {
            res = root.val;
            return;
        }
        inorder(root.left);
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
 * @param {TreeNode} root
 * @param {number} k
 * @return {number}
 */
var kthLargest = function (root, k) {
    const inorder = root => {
        if (!root) {
            return;
        }
        inorder(root.right);
        --cur;
        if (cur == 0) {
            res = root.val;
            return;
        }
        inorder(root.left);
    };
    let res = 0;
    let cur = k;
    inorder(root);
    return res;
};
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
    int kthLargest(TreeNode* root, int k) {
        cur = k;
        inorder(root);
        return res;
    }

private:
    int cur, res;

    void inorder(TreeNode* root) {
        if (!root) {
            return;
        }
        inorder(root->right);
        --cur;
        if (cur == 0) {
            res = root->val;
            return;
        }
        inorder(root->left);
    }
};
```

### **Go**

利用 Go 的特性，中序遍历“生产”的数字传到 `channel`，返回第 `k` 个。

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func kthLargest(root *TreeNode, k int) int {
	ch := make(chan int)
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	go inorder(ctx, root, ch)
	for ; k > 1; k-- {
		<-ch
	}
	return <-ch
}

func inorder(ctx context.Context, cur *TreeNode, ch chan<- int) {
	if cur != nil {
		inorder(ctx, cur.Right, ch)
		select {
		case ch <- cur.Val:
		case <-ctx.Done():
			return
		}
		inorder(ctx, cur.Left, ch)
	}
}
```

### **...**

```

```

<!-- tabs:end -->
