# [LCP 76. 魔法棋盘](https://leetcode.cn/problems/1ybDKD)

## 题目描述

<!-- 这里写题目描述 -->

在大小为 `n * m` 的棋盘中，有两种不同的棋子：黑色，红色。当两颗颜色不同的棋子同时满足以下两种情况时，将会产生魔法共鸣：
- 两颗异色棋子在同一行或者同一列
- 两颗异色棋子之间恰好只有一颗棋子

由于棋盘上被施加了魔法禁制，棋盘上的部分格子变成问号。`chessboard[i][j]` 表示棋盘第 `i` 行 `j` 列的状态：
- 若为 `.` ，表示当前格子确定为空
- 若为 `B` ，表示当前格子确定为 黑棋
- 若为 `R` ，表示当前格子确定为 红棋
- 若为 `?` ，表示当前格子待定

现在，探险家小扣的任务是确定所有问号位置的状态（留空/放黑棋/放红棋），使最终的棋盘上，任意两颗棋子间都 **无法** 产生共鸣。请返回可以满足上述条件的放置方案数量。

**示例1：**
> 输入：`n = 3, m = 3, chessboard = ["..R","..B","?R?"]`
>
> 输出：`5`
>
> 解释：给定的棋盘如图：
>![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2076.%20%E9%AD%94%E6%B3%95%E6%A3%8B%E7%9B%98/images/1681714583-unbRox-image.png){:height=150px}
> 所有符合题意的最终局面如图：
>![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2076.%20%E9%AD%94%E6%B3%95%E6%A3%8B%E7%9B%98/images/1681714596-beaOHK-image.png){:height=150px}

**示例2：**
> 输入：`n = 3, m = 3, chessboard = ["?R?","B?B","?R?"]`
>
> 输出：`105`

**提示：**
- `n == chessboard.length`
- `m == chessboard[i].length`
- `1 <= n*m <= 30`
- `chessboard` 中仅包含 `"."、"B"、"R"、"?"`

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
