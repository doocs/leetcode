# [面试题32 - I. 从上到下打印二叉树](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/)

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
### Python3
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from queue import Queue

class Solution:
    def levelOrder(self, root: TreeNode) -> List[int]:
        if root is None:
            return []
        s = Queue()
        res = []
        s.put(root)
        while not s.empty():
            node = s.get()
            res.append(node.val)
            if node.left:
                s.put(node.left)
            if node.right:
                s.put(node.right)
        return res            
```

### Java
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
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> s = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            s.offer(node.val);
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
        int[] res = new int[s.size()];

        for (int i = 0, len = s.size(); i < len; ++i) {
            res[i] = s.poll();
        }
        return res;

    }
}
```

### JavaScript
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
var levelOrder = function(root) {
    if(!root) return []
    let queue = [root]
    let res = []
    while(queue.length) {
        let node = queue.shift()
        if(!node) continue
        res.push(node.val)
        queue.push(node.left,node.right)
    }
    return res
};
```

### Go

```go
func levelOrder(root *TreeNode) []int {
    if root == nil {
        return []int{}
    }
    q := []*TreeNode{}
    q = append(q,root)
    //层序遍历,用队列,遍历到谁,就把谁的左右结点加入队列
    res := []int{}
    for len(q) != 0 {
        tmp := q[0]
        q = q[1:]
        res = append(res,tmp.Val)
        if tmp.Left != nil {
            q = append(q,tmp.Left)
        }
        if tmp.Right != nil {
            q = append(q,tmp.Right)
        }
    }
    return res
}
```



### ...

```

```
