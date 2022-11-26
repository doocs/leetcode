# [LCP 67. 装饰树](https://leetcode.cn/problems/KnLfVT)

## 题目描述

<!-- 这里写题目描述 -->

力扣嘉年华上的 DIY 手工展位准备了一棵缩小版的 **二叉** 装饰树 `root` 和灯饰，你需要将灯饰逐一插入装饰树中，要求如下：

-   完成装饰的二叉树根结点与 `root` 的根结点值相同
-   若一个节点拥有父节点，则在该节点和他的父节点之间插入一个灯饰（即插入一个值为 `-1` 的节点）。具体地：
    -   在一个 父节点 x 与其左子节点 y 之间添加 -1 节点， 节点 -1、节点 y 为各自父节点的左子节点，
    -   在一个 父节点 x 与其右子节点 y 之间添加 -1 节点， 节点 -1、节点 y 为各自父节点的右子节点，

现给定二叉树的根节点 `root` ，请返回完成装饰后的树的根节点。
**示例 1：**

> 输入：
> `root = [7,5,6]`
>
> 输出：`[7,-1,-1,5,null,null,6]`
>
> 解释：如下图所示，
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2067.%20%E8%A3%85%E9%A5%B0%E6%A0%91/images/1663575757-yRLGaq-image.png)

**示例 2：**

> 输入：
> `root = [3,1,7,3,8,null,4]`
>
> 输出：`[3,-1,-1,1,null,null,7,-1,-1,null,-1,3,null,null,8,null,4]`
>
> 解释：如下图所示
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2067.%20%E8%A3%85%E9%A5%B0%E6%A0%91/images/1663577920-sjrAYH-image.png)

**提示：**

> `0 <= root.Val <= 1000` >`root` 节点数量范围为 `[1, 10^5]`

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
