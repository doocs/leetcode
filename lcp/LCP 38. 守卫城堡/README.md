# [LCP 38. 守卫城堡](https://leetcode.cn/problems/7rLGCR)

## 题目描述

<!-- 这里写题目描述 -->

城堡守卫游戏的胜利条件为使恶魔无法从出生点到达城堡。游戏地图可视作 `2*N` 的方格图，记作字符串数组 `grid`，其中：

-   `"."` 表示恶魔可随意通行的平地；
-   `"#"` 表示恶魔不可通过的障碍物，玩家可通过在 **平地** 上设置障碍物，即将 `"."` 变为 `"#"` 以阻挡恶魔前进；
-   `"S"` 表示恶魔出生点，将有大量的恶魔该点生成，恶魔可向上/向下/向左/向右移动，且无法移动至地图外；
-   `"P"` 表示瞬移点，移动到 `"P"` 点的恶魔可被传送至任意一个 `"P"` 点，也可选择不传送；
-   `"C"` 表示城堡。

然而在游戏中用于建造障碍物的金钱是有限的，请返回玩家最少需要放置几个障碍物才能获得胜利。若无论怎样放置障碍物均无法获胜，请返回 `-1`。

**注意：**

-   地图上可能有一个或多个出生点
-   地图上有且只有一个城堡

**示例 1**

> 输入：`grid = ["S.C.P#P.", ".....#.S"]`
>
> 输出：`3`
>
> 解释：至少需要放置三个障碍物
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2038.%20守卫城堡/images/1614828255-uuNdNJ-image.png)

**示例 2：**

> 输入：`grid = ["SP#P..P#PC#.S", "..#P..P####.#"]`
>
> 输出：`-1`
>
> 解释：无论怎样修筑障碍物，均无法阻挡最左侧出生的恶魔到达城堡位置
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2038.%20守卫城堡/images/1614828208-oFlpVs-image.png)

**示例 3：**

> 输入：`grid = ["SP#.C.#PS", "P.#...#.P"]`
>
> 输出：`0`
>
> 解释：无需放置障碍物即可获得胜利
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2038.%20守卫城堡/images/1614828242-oveClu-image.png)

**示例 4：**

> 输入：`grid = ["CP.#.P.", "...S..S"]`
>
> 输出：`4`
>
> 解释：至少需要放置 4 个障碍物，示意图为放置方法之一
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2038.%20守卫城堡/images/1614828218-sIAYkb-image.png)

**提示：**

-   `grid.length == 2`
-   `2 <= grid[0].length == grid[1].length <= 10^4`
-   `grid[i][j]` 仅包含字符 `"."`、`"#"`、`"C"`、`"P"`、`"S"`

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
