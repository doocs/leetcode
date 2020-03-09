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

### ...
```

```
