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
    t, res = 0, -1
    def kthLargest(self, root: TreeNode, k: int) -> int:
        self.t = k
        self._traverse(root)
        return self.res

    def _traverse(self, node):
        if node:
            self._traverse(node.right)
            self.t -= 1
            if self.t == 0:
                self.res = node.val
                return
            self._traverse(node.left)
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
    private int t;
    private int res;
    public int kthLargest(TreeNode root, int k) {
        t = k;
        traverse(root);
        return res;
    }

    private void traverse(TreeNode node) {
        if (node != null) {
            traverse(node.right);
            --t;
            if (t == 0) {
                res = node.val;
                return;
            }
            traverse(node.left);
        }
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
  let res;
  let t = 0;
  function traversal(node) {
    if (!node) return;
    traversal(node.right);
    if (++t === k) res = node.val;
    traversal(node.left);
  }
  traversal(root);
  return res;
};
```

### **C++**

```cpp
class Solution {
public:
    int kthLargest(TreeNode* root, int k) {
        cur = k;
        inOrder(root);
        return res;
    }

private:
    int cur, res;

    void inOrder(TreeNode* root) {
        if (root) {
            inOrder(root->right);
            --cur;
            if (cur == 0) {
                res = root->val;
                return;
            }
            inOrder(root->left);
        }
    }
};
```

### **Go**

利用 Go 的特性，中序遍历“生产”的数字传到 `channel`，返回第 `k` 个。

```go
func kthLargest(root *TreeNode, k int) int {
	ch := make(chan int)
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	go inOrder(ctx, root, ch)
	for ; k > 1; k-- {
		<-ch
	}
	return <-ch
}

func inOrder(ctx context.Context, cur *TreeNode, ch chan<- int) {
	if cur != nil {
		inOrder(ctx, cur.Right, ch)
		select {
		case ch <- cur.Val:
		case <-ctx.Done():
			return
		}
		inOrder(ctx, cur.Left, ch)
	}
}
```

### **...**

```

```

<!-- tabs:end -->
