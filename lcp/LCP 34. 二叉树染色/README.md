# [LCP 34. 二叉树染色](https://leetcode.cn/problems/er-cha-shu-ran-se-UGC)

## 题目描述

<!-- 这里写题目描述 -->

小扣有一个根结点为 `root` 的二叉树模型，初始所有结点均为白色，可以用蓝色染料给模型结点染色，模型的每个结点有一个 `val` 价值。小扣出于美观考虑，希望最后二叉树上每个蓝色相连部分的结点个数不能超过 `k` 个，求所有染成蓝色的结点价值总和最大是多少？

**示例 1：**

> 输入：`root = [5,2,3,4], k = 2`
>
> 输出：`12`
>
> 解释：`结点 5、3、4 染成蓝色，获得最大的价值 5+3+4=12` > ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2034.%20二叉树染色/images/1616126267-BqaCRj-image.png)

**示例 2：**

> 输入：`root = [4,1,3,9,null,null,2], k = 2`
>
> 输出：`16`
>
> 解释：结点 4、3、9 染成蓝色，获得最大的价值 4+3+9=16
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2034.%20二叉树染色/images/1616126301-gJbhba-image.png)

**提示：**

-   `1 <= k <= 10`
-   `1 <= val <= 10000`
-   `1 <= 结点数量 <= 10000`

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def maxValue(self, root: TreeNode, k: int) -> int:
        def dfs(root):
            ans = [0] * (k + 1)
            if root is None:
                return ans
            l, r = dfs(root.left), dfs(root.right)
            for i in range(k):
                for j in range(k - i):
                    ans[i + j + 1] = max(ans[i + j + 1], l[i] + r[j] + root.val)
            for i in range(k + 1):
                for j in range(k + 1):
                    ans[0] = max(ans[0], l[i] + r[j])
            return ans

        return max(dfs(root))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public int maxValue(TreeNode root, int k) {
        int[] t = dfs(root, k);
        int ans = 0;
        for (int v : t) {
            ans = Math.max(ans, v);
        }
        return ans;
    }

    private int[] dfs(TreeNode root, int k) {
        int[] ans = new int[k + 1];
        if (root == null) {
            return ans;
        }
        int[] l = dfs(root.left, k);
        int[] r = dfs(root.right, k);
        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < k - i; ++j) {
                ans[i + j + 1] = Math.max(ans[i + j + 1], l[i] + r[j] + root.val);
            }
        }
        for (int i = 0; i <= k; ++i) {
            for (int j = 0; j <= k; ++j) {
                ans[0] = Math.max(ans[0], l[i] + r[j]);
            }
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
