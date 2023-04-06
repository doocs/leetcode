# [LCP 71. 集水器](https://leetcode.cn/problems/kskhHQ)

## 题目描述

<!-- 这里写题目描述 -->

字符串数组 `shape` 描述了一个二维平面中的矩阵形式的集水器，`shape[i][j]` 表示集水器的第 `i` 行 `j` 列为：

-   `'l'`表示向左倾斜的隔板（即从左上到右下）；
-   `'r'`表示向右倾斜的隔板（即从左下到右上）；
-   `'.'` 表示此位置没有隔板
    <br><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2071.%20%E9%9B%86%E6%B0%B4%E5%99%A8/images/1664424667-wMnPja-image.png" style="width: 200px;" />

已知当隔板构成存储容器可以存水，每个方格代表的蓄水量为 `2`。集水器初始浸泡在水中，除内部密闭空间外，所有位置均被水填满。
现将其从水中竖直向上取出，请返回集水器最终的蓄水量。

**注意：**

-   隔板具有良好的透气性，因此空气可以穿过隔板，但水无法穿过

**示例 1：**

> 输入：
> `shape = ["....rl","l.lr.r",".l..r.","..lr.."]`
>
> 输出：`18`
>
> 解释：如下图所示，由于空气会穿过隔板，因此红框区域没有水
> <br><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2071.%20%E9%9B%86%E6%B0%B4%E5%99%A8/images/1664436239-eyYxeP-image.png" style="width: 280px;" />

**示例 2：**

> 输入：
> `shape = [".rlrlrlrl","ll..rl..r",".llrrllrr","..lr..lr."]`
> 输出：`18`
>
> 解释：如图所示。由于红框右侧未闭合，因此多余的水会从该处流走。
> <br><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2071.%20%E9%9B%86%E6%B0%B4%E5%99%A8/images/1664436082-SibVMv-image.png" style="width: 400px;" />

**示例 3：**

> 输入：
> `shape = ["rlrr","llrl","llr."]`
> 输出：`6`
>
> 解释：如图所示。
> <br><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2071.%20%E9%9B%86%E6%B0%B4%E5%99%A8/images/1664424855-dwpUHO-image.png" style="width: 230px;" />

**示例 4：**

> 输入：
> `shape = ["...rl...","..r..l..",".r.rl.l.","r.r..l.l","l.l..rl.",".l.lr.r.","..l..r..","...lr..."]`
>
> 输出：`30`
>
> 解释：如下图所示。由于中间为内部密闭空间，无法蓄水。
> <br><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2071.%20%E9%9B%86%E6%B0%B4%E5%99%A8/images/1664424894-mClEXh-image.png" style="width: 350px;" />

**提示**：

-   `1 <= shape.length <= 50`
-   `1 <= shape[i].length <= 50`
-   `shape[i][j]` 仅为 `'l'`、`'r'` 或 `'.'`

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
