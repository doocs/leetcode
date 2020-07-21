# [面试题33. 二叉搜索树的后序遍历序列](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 `true`，否则返回 `false`。假设输入的数组的任意两个数字都互不相同。

参考以下这棵二叉搜索树：

```
     5
    / \
   2   6
  / \
 1   3
```

**示例 1：**

```
输入: [1,6,3,2,5]
输出: false
```

**示例 2：**

```
输入: [1,3,2,6,5]
输出: true
```

**提示：**

- `数组长度 <= 1000`

## 解法
<!-- 这里可写通用的实现逻辑 -->
二叉搜索树的后序遍历序列是 `[左子树, 右子树, 根结点]`，且左子树结点值均小于根结点，右子树结点值均大于根结点，递归判断即可。


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        n = len(postorder)
        if n < 2:
            return True
        for i in range(n):
            if postorder[i] > postorder[-1]:
                break
        for j in range(i + 1, n - 1):
            if postorder[j] < postorder[-1]:
                return False
        return (i == 0 or self.verifyPostorder(postorder[:i])) and (i == n - 1 or self.verifyPostorder(postorder[i:-1]))
        
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return true;
        }
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int from, int to) {
        if (from == to) {
            return true;
        }
        int i = from, j = from;
        for (; i < to; ++i) {
            if (postorder[i] > postorder[to]) {
                break;
            }
        }
        for (j = i + 1; j < to; ++j) {
            if (postorder[j] < postorder[to]) {
                return false;
            }
        }
        return (i == from || verifyPostorder(postorder, from, i - 1)) && (i == to || verifyPostorder(postorder, i, to - 1));


    }
}
```

### JavaScript
```js
/**
 * @param {number[]} postorder
 * @return {boolean}
 */
var verifyPostorder = function(postorder) {
    if(!postorder || postorder.length < 2) return true
    let mid = 0
    let root = postorder[postorder.length-1]
    for(let i=0;i<postorder.length-1 && postorder[i] < root;i++) {
        mid++
    }
    for(let i=mid+1;i<postorder.length-1;i++) {
        if(postorder[i] < root) return false
    }
    return verifyPostorder(postorder.slice(0,mid)) && verifyPostorder(postorder.slice(mid+1,postorder.length - 1))
};
```

### Go

```go
func verifyPostorder(postorder []int) bool {
    if len(postorder) < 2 {
        return true
    }
    return helper(postorder, 0, len(postorder)-1)
}
//递归
func helper(postorder []int , left,right int) bool {
    if left >= right {
        return true
    }
    //最后一位即根
    rootValue := postorder[right]
    //从左开始往右遍历，直到大于根停止,小于部分是左子树
    i := left
    for i < right && postorder[i] < rootValue {
        i++
    }
    //剩下部分是右子树，检查是否都大于根值
    for j := i; j < right; j++ {
        if postorder[j] < rootValue {
            return false
        }
    }
    l := helper(postorder,left,i-1) //检查左子树，左子树i要减一
    r := helper(postorder,i,right-1)//检查右子树，剔除最后一位是根
    return l && r
}
```



### ...

```

```
