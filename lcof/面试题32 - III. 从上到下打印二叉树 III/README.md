# [面试题32 - III. 从上到下打印二叉树 III](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/)

## 题目描述
请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

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
  [20,9],
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
        level = 0
        while not q.empty():
            level += 1
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
            if (level & 1) == 0:
                t.reverse()
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
        q.offer(root);
        int cnt = 1;
        int level = 0;
        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            ++level;
            int num = 0;
            List<Integer> t = new ArrayList<>();
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
            if ((level & 1) == 0) {
                Collections.reverse(t);
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
    let dir = true
    while(queue.length) {
        let len = queue.length
        for(let i=0;i<len;i++) {
            let node = queue.shift()
            if(!node) continue
            if(!res[depth]) res[depth] = []
            if(dir) {
                res[depth].push(node.val)
            } else {
                res[depth].unshift(node.val)
            }
            queue.push(node.left,node.right)
        }
        depth++
        dir = !dir
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
    level := 0
    for len(queue) != 0 {
        size := len(queue)
        ans := []int{}
        //size记录每层大小,level记录层数
        for size > 0 {
            cur := queue[0]
            if level & 1 == 0 {
                ans = append(ans, cur.Val)
            } else {
                ans = append([]int{cur.Val},ans...)
            }
            
            queue = queue[1:]
            size--
            if cur.Left != nil {
                queue = append(queue, cur.Left)
            }
            if cur.Right != nil {
                queue = append(queue, cur.Right)
            }
        }
        level++
        res = append(res, ans)
    }
    return res
}
```



### ...

```

```
