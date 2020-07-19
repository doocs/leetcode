# [面试题 04.10. 检查子树](https://leetcode-cn.com/problems/check-subtree-lcci)

[English Version](/lcci/04.10.Check%20SubTree/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。</p>

<p>如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：t1 = [1, 2, 3], t2 = [2]
<strong> 输出</strong>：true
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：t1 = [1, null, 2, 4], t2 = [3, 2]
<strong> 输出</strong>：false
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li>树的节点数目范围为[0, 20000]。</li>
</ol>


## 解法
<!-- 这里可写通用的实现逻辑 -->
先找t1中t2结点,找到后进行DFS，确认子树和t2的子树完全相同，否则返回FALSE。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkSubTree(self,t1,t2):
        if t1 == None:
            return False
        if t2 == None:
            return True
        return self.dfs(t1,t2) or self.checkSubTree(t1.left,t2) or self.checkSubTree(t1.right,t2)
    
    def dfs(self,t1,t2):
        if not t1 and t2 :
            return False
        if not t2 and not t1:
            return True
        if t1.val != t2.val:
            return False
        else:
            return self.dfs(t1.left,t2.left) and self.dfs(t1.right,t2.right)
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null)
            return true;
        if (t1 == null)
            return false;
        return isSubTree(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    public boolean isSubTree(TreeNode t1, TreeNode t2){
        if (t2 == null)
            return true;
        if (t1 == null)
            return false;
        if (t1.val != t2.val)
            return false;
        return isSubTree(t1.left,t2.left) && isSubTree(t1.right,t2.right);
    }
}
```

### ...
```

```
