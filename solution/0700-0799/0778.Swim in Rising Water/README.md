# [778. 水位上升的泳池中游泳](https://leetcode-cn.com/problems/swim-in-rising-water)

[English Version](/solution/0700-0799/0778.Swim%20in%20Rising%20Water/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个 N x N 的坐标方格 <code>grid</code> 中，每一个方格的值 <code>grid[i][j]</code> 表示在位置 <code>(i,j)</code> 的平台高度。</p>

<p>现在开始下雨了。当时间为 <code>t</code> 时，此时雨水导致水池中任意位置的水位为 <code>t</code> 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。</p>

<p>你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 <code>(N-1, N-1)</code>？</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [[0,2],[1,3]]
<strong>输出:</strong> 3
<strong>解释:</strong>
时间为0时，你位于坐标方格的位置为 <code>(0, 0)。</code>
此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
</pre>

<p><strong>示例2:</strong></p>

<pre>
<strong>输入:</strong> [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
<strong>输出:</strong> 16
<strong>解释:</strong>
<strong> 0  1  2  3  4</strong>
24 23 22 21  <strong>5</strong>
<strong>12 13 14 15 16</strong>
<strong>11</strong> 17 18 19 20
<strong>10  9  8  7  6</strong>

最终的路线用加粗进行了标记。
我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ol>
	<li><code>2 <= N <= 50</code>.</li>
	<li><code>grid[i][j]</code> 是 <code>[0, ..., N*N - 1]</code> 的排列。</li>
</ol>


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
class Solution {
    // x、y方向向量
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    /**
     * https://blog.csdn.net/fuxuemingzhu/article/details/82926674
     * <p>
     * 参考这篇文章的第二种解题方法做的
     * <p>
     * 通过优先级队列找寻局部最优解  最终的得到的结果就是全局最优解
     *
     * @param grid
     * @return
     */
    // 以grid左上角为原点，横向为X轴，纵向为Y轴
    public int swimInWater(int[][] grid) {
        // 定义一个优先级队列  按照h从小到大排列
        Queue<Pair<Integer, Pair<Integer, Integer>>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        queue.add(new Pair<>(grid[0][0], new Pair<>(0, 0)));
        // 已经遍历过的点
        Set<Pair<Integer, Integer>> visitSet = new HashSet<>();
        visitSet.add(new Pair<>(0, 0));

        int res = 0;
        int length = grid.length;

        while (!queue.isEmpty()) {
            Pair<Integer, Pair<Integer, Integer>> top = queue.poll();
            Integer x = top.getValue().getKey();
            Integer y = top.getValue().getValue();
            res = Math.max(res, top.getKey());
            // 2 <= N <= 50 这个范围内可以直接使用==进行Integer的比较
            if (x == top.getValue().getValue() && y == length - 1) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newY = y + dy[i];
                int newX = x + dx[i];
                if (newX < 0 || newY < 0 || newX >= length || newY >= length || visitSet.contains(new Pair<>(newX, newY))) {
                    // 直接忽略
                    continue;
                }
                queue.add(new Pair<>(grid[newX][newY], new Pair<>(newX, newY)));
                visitSet.add(new Pair<>(newX, newY));
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
