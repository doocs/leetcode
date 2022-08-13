# [面试题 54. 二叉搜索树的第 k 大节点](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/)

## 题目描述

<p>给定一棵二叉搜索树，请找出其中第 <code>k</code> 大的节点的值。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
&nbsp;  2
<strong>输出:</strong> 4</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
<strong>输出:</strong> 4</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li>1 ≤ k ≤ 二叉搜索树元素个数</li>
</ul>

## 解法

朴素解法：

1.  中序遍历，并使用数组存储遍历结果。
2.  遍历结束，返回 `arr[arr.length - k]`。

_优化_：

其中，只关注**第 k 大节点的值**，可以选择倒序遍历，记录当前遍历节点的数量，当数量为 `k` 时，记录当前节点值做为返回值即可，而无需记录所有的遍历结果。

> 中序遍历的顺序是从小到大，倒序的中序遍历便是从大到小。

常规中序遍历：

```txt
IN-ORDER(R)
    IN-ORDER(R.left)
    print(R.val)
    In-ORDER(R.right)
```

倒序中序遍历：

```txt
IN-ORDER-REVERSE(R)
    In-ORDER-REVERSE(R.right)
    print(R.val)
    IN-ORDER-REVERSE(R.left)
```

若是抬杠说，可能每次 `k` 都是节点数量，那么倒序就毫无意义并且加长时间消耗。这些情况是无法假设的，如果 `k = 1`，那又该怎么说，选择倒序先得最大值，才是符合题意的精神。

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

function kthLargest(root: TreeNode | null, k: number): number {
    let res = 0;
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return;
        }
        dfs(root.right);
        if (--k === 0) {
            res = root.val;
            return;
        }
        dfs(root.left);
    };
    dfs(root);
    return res;
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, arr: &mut Vec<i32>) {
        if let Some(node) = root {
            let node = node.borrow();
            Solution::dfs(&node.right, arr);
            arr.push(node.val);
            Solution::dfs(&node.left, arr)
        }
    }
    pub fn kth_largest(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> i32 {
        let mut arr = vec![];
        Solution::dfs(&root, &mut arr);
        arr[(k - 1) as usize]
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
    public int KthLargest(TreeNode root, int k) {
        List<int> list = new List<int>();
        list = postorder(root, list);
        return list[list.Count() - k];
    }

    public List<int> postorder(TreeNode root, List<int> list) {
        if (root == null) {
            return list;
        } else {
            postorder(root.left, list);
            list.Add(root.val);
            postorder(root.right, list);
        }
        return list;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
