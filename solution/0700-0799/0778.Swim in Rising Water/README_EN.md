# [778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water)

[中文文档](/solution/0700-0799/0778.Swim%20in%20Rising%20Water/README.md)

## Description

<p>On an N x N <code>grid</code>, each square <code>grid[i][j]</code> represents the elevation at that point <code>(i,j)</code>.</p>

<p>Now rain starts to fall. At time <code>t</code>, the depth of the water everywhere is <code>t</code>. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are&nbsp;at most&nbsp;<code>t</code>. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.</p>

<p>You start at the top left square <code>(0, 0)</code>. What is the least time until you can reach the bottom right square <code>(N-1, N-1)</code>?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [[0,2],[1,3]]

<strong>Output:</strong> 3

<strong>Explanation:</strong>

At time <code>0</code>, you are in grid location <code>(0, 0)</code>.

You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.



You cannot reach point <code>(1, 1)</code> until time <code>3</code>.

When the depth of water is <code>3</code>, we can swim anywhere inside the grid.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]

<strong>Output:</strong> 16

<strong>Explanation:</strong>

<strong> 0  1  2  3  4</strong>

24 23 22 21  <strong>5</strong>

<strong>12 13 14 15 16</strong>

<strong>11</strong> 17 18 19 20

<strong>10  9  8  7  6</strong>



The final route is marked in bold.

We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

</pre>

<p><strong>Note:</strong></p>

<ol>
    <li><code>2 &lt;= N &lt;= 50</code>.</li>
    <li>grid[i][j] is a permutation of [0, ..., N*N - 1].</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
