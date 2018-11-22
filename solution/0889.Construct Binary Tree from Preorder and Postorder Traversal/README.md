## 根据前序和后序遍历构造二叉树
### 题目描述

返回与给定的前序和后序遍历匹配的任何二叉树。

 `pre` 和 `post` 遍历中的值是不同的正整数。


示例 ：
```
输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
输出：[1,2,3,4,5,6,7]
```


提示：

- 1 <= pre.length == post.length <= 30
- pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
- 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。

### 解法
由前序可以知道哪个是根节点（第一个节点即为根节点），由后序可以知道根节点的子节点有哪些（根节点之前的节点都是其子节点），递归可求。

```python
class Solution:
    def constructFromPrePost(self, pre, post):
        if pre:
            root = TreeNode(pre[0])
            if len(pre) == 1:
                return root
            else:
                for i in range(0, len(pre) - 1):
                    if post[i] == pre[1]:
                        root.left = self.constructFromPrePost(
                            pre[1:i + 1 + 1], post[:i + 1])
                        root.right = self.constructFromPrePost(
                            pre[i + 1 + 1:], post[i + 1:-1])
                        break
                return root
        else:
            return None
```
