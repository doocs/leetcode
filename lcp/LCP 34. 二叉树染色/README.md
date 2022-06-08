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
class Solution:
    def maxValue(self, root: TreeNode, k: int) -> int:
        def dp(node):
            if node.left:
                if node.right: return cg(node.val, dp(node.left), dp(node.right))  # 左右都存在
                else: return cg(node.val, dp(node.left))  # 单左存在
            elif node.right:
                return cg(node.val, dp(node.right))  # 单右存在
            else: return [0] + [node.val] * k  # 左右都不存在
        def cg(v,lis0,lis1=None):
            nlis = [0] * (k + 1)
            if lis1:
                nlis[0] = lis0[-1]+lis1[-1]
                for i in range(k):
                    nlis[i+1] = nlis[i]
                    for j in range(i+1):
                        nlis[i+1] = max(nlis[i+1],lis0[j]+lis1[i-j]+v)
            else:
                nlis[0] = lis0[-1]
                for i in range(k):
                    nlis[i+1] = max(nlis[i],lis0[i]+v)
            return nlis
        v = root.val
        if root.left:
            if root.right:  # 左右都存在
                left = dp(root.left)
                right = dp(root.right)
                mx = left[-1]+right[-1]
                for i in range(k):
                    mx = max(mx, left[i]+right[k-i-1]+v)
                return mx
            else:  # 单左存在
                *_,x,y = dp(root.left)
        elif root.right: *_,x,y = dp(root.right)    # 单右存在
        else: return v  # 左右都不存在
        return max(y, v+x)  # 单孩子情况的返回值
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    Map<TreeNode,int[]> map=new HashMap<>();
    public int maxValue(TreeNode root, int k) {
        calclulateMaxScore(root,k);
        int ans=0;
        int a[]=map.get(root);
        for(int i=0;i<=k;i++){ans=Math.max(ans,a[i]);}
        return ans;
    }
    public void calclulateMaxScore(TreeNode t,int k){
        int a[]=new int[12];
        if(t.left==t.right){a[1]=t.val;}
        else if(t.left==null){
            calclulateMaxScore(t.right,k);
            int r[]=map.get(t.right);
            for(int i=1;i<=k;i++){a[i]=r[i-1]+t.val;}
            for(int i=0;i<=k;i++){a[0]=Math.max(a[0],r[i]);}
        }
        else if(t.right==null){
            calclulateMaxScore(t.left,k);
            int l[]=map.get(t.left);
            for(int i=1;i<=k;i++){a[i]=l[i-1]+t.val;}
            for(int i=0;i<=k;i++){a[0]=Math.max(a[0],l[i]);}
        }
        else{
            calclulateMaxScore(t.left,k);
            calclulateMaxScore(t.right,k);
            int l[]=map.get(t.left);
            int r[]=map.get(t.right);
            //左右加起来的节点数为i-1，
            for(int i=1;i<=k;i++){for(int j=0;j<i;j++){a[i]=Math.max(a[i],t.val+l[j]+r[i-1-j]);}}
            int lMax=0,rMax=0;
            for(int i=0;i<=k;i++){
                lMax=Math.max(lMax,l[i]);
                rMax=Math.max(rMax,r[i]);
            }
            a[0]=lMax+rMax;
        }
        map.put(t,a);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
