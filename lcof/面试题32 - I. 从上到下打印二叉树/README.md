# [面试题 32 - I. 从上到下打印二叉树](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/)

## 题目描述

从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

**例如:**

给定二叉树: `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

**返回：**

```
[3,9,20,15,7]
```

**提示：**

- `节点总数 <= 1000`

## 解法

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
    def levelOrder(self, root: TreeNode) -> List[int]:
        if root is None:
            return []
        q = deque()
        q.append(root)
        res = []
        while q:
            size = len(q)
            for _ in range(size):
                node = q.popleft()
                res.append(node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
        return res
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
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};
        Deque<TreeNode> q = new ArrayDeque<>();
        List<Integer> t = new ArrayList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        int i = 0, n = t.size();
        int[] res = new int[n];
        for (Integer e : t) {
            res[i++] = e;
        }
        return res;
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
 * @return {number[]}
 */
var levelOrder = function (root) {
    if (!root) return [];
    let queue = [root];
    let res = [];
    while (queue.length) {
        let node = queue.shift();
        if (!node) continue;
        res.push(node.val);
        queue.push(node.left, node.right);
    }
    return res;
};
```

### **Go**

```go
func levelOrder(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	q := []*TreeNode{}
	q = append(q, root)
	// 层序遍历,用队列,遍历到谁,就把谁的左右结点加入队列
	res := []int{}
	for len(q) != 0 {
		tmp := q[0]
		q = q[1:]
		res = append(res, tmp.Val)
		if tmp.Left != nil {
			q = append(q, tmp.Left)
		}
		if tmp.Right != nil {
			q = append(q, tmp.Right)
		}
	}
	return res
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> levelOrder(TreeNode* root) {
        vector<int> ret;
        if (!root) {
            return ret;
        }

        queue<TreeNode *> q;
        q.push(root);

        while (!q.empty()) {
            auto head = q.front();
            q.pop();
            ret.push_back(head->val);
            if (head->left) {
                q.push(head->left);
            }

            if (head->right) {
                q.push(head->right);
            }
        }

        return ret;
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

function levelOrder(root: TreeNode | null): number[] {
    const res = [];
    if (root == null) {
        return res;
    }

    const arr = [root];
    let i = 0;
    while (i < arr.length) {
        const { val, left, right } = arr[i];
        res.push(val);
        left && arr.push(left);
        right && arr.push(right);
        i++;
    }
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
use std::collections::VecDeque;

impl Solution {
    pub fn level_order(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut res = Vec::new();
        let mut queue = VecDeque::new();
        if let Some(node) = root {
            queue.push_back(node);
        }
        while let Some(node) = queue.pop_front() {
            let mut node = node.borrow_mut();
            res.push(node.val);
            if let Some(l) = node.left.take() {
                queue.push_back(l);
            }
            if let Some(r) = node.right.take() {
                queue.push_back(r);
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
