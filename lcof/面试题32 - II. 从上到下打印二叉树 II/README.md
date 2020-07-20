# [面试题32 - II. 从上到下打印二叉树 II](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/)

## 题目描述
从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

**例如:**

给定二叉树: `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其层次遍历结果：

```
[
  [3],
  [9,20],
  [15,7]
]
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
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        q = Queue()
        q.put(root)
        cnt = 1
        res = []
        while not q.empty():
            t = []
            num = 0
            for _ in range(cnt):
                node = q.get()
                t.append(node.val)
                if node.left:
                    q.put(node.left)
                    num += 1
                if node.right:
                    q.put(node.right)
                    num += 1
            res.append(t)
            cnt = num
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> q = new LinkedList<>();
        int cnt = 1;
        q.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            int num = 0;
            while (cnt-- > 0) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                    ++num;
                }
                if (node.right != null) {
                    q.offer(node.right);
                    ++num;
                }
            }
            res.add(t);
            cnt = num;
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
 * @return {number[][]}
 */
var levelOrder = function(root) {
    if(!root) return []
    let queue = [root]
    let res = []
    let depth = 0
    while(queue.length) {
        let len = queue.length
        for(let i=0;i<len;i++) {
            let node = queue.shift()
            if(!node) continue
            if(!res[depth]) res[depth] = []
            res[depth].push(node.val)
            queue.push(node.left,node.right)
        }
        depth++
    }
    return res
};
```

### Go

```go
func levelOrder(root *TreeNode) [][]int {
    if root == nil {
        return nil
    }
    res := [][]int{}
    queue := []*TreeNode{}
    queue = append(queue,root)
    for len(queue) != 0 {
        size := len(queue)
        ans := []int{}
        //利用一个变量记录每层大小
        for size > 0 {
            cur := queue[0]
            ans = append(ans, cur.Val)
            queue = queue[1:]
            size--
            if cur.Left != nil {
                queue = append(queue, cur.Left)
            }
            if cur.Right != nil {
                queue = append(queue, cur.Right)
            }
        }
        res = append(res, ans)
    }
    return res
}
```



### ...

```

```
