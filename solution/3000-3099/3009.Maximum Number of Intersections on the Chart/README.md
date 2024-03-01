# [3009. 折线图上的最大交点数量](https://leetcode.cn/problems/maximum-number-of-intersections-on-the-chart)

[English Version](/solution/3000-3099/3009.Maximum%20Number%20of%20Intersections%20on%20the%20Chart/README_EN.md)

<!-- tags:树状数组,几何,数组,数学 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>有一条由 <code>n</code> 个点连接而成的折线图。给定一个 <strong>下标从 1 开始&nbsp;</strong>的整数数组 <code>y</code>，第&nbsp;<code>k</code>&nbsp;个点的坐标是 <code>(k, y[k])</code>。图中没有水平线，即没有两个相邻的点有相同的 y 坐标。</p>

<p>假设在图中任意画一条无限长的水平线。请返回这条水平线与折线相交的最多交点数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<strong><a href="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3009.Maximum%20Number%20of%20Intersections%20on%20the%20Chart/images/20231208-020549.jpeg"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3009.Maximum%20Number%20of%20Intersections%20on%20the%20Chart/images/20231208-020549.jpeg" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; height: 217px; width: 600px;" /></a></strong>

<pre>
<b>输入：</b>y = [1,2,1,2,1,3,2]
<b>输出：</b>5
<b>解释：</b>如上图所示，水平线 y = 1.5 与折线相交了 5 次（用红叉表示）。水平线 y = 2 与折线相交了 4 次（用红叉表示）。可以证明没有其他水平线可以与折线有超过 5 个点相交。因此，答案是 5。
</pre>

<p><strong class="example">示例 2：</strong></p>
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3009.Maximum%20Number%20of%20Intersections%20on%20the%20Chart/images/20231208-020557.jpeg" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 400px; height: 404px;" /></strong>

<pre>
<b>输入：</b>y = [2,1,3,4,5]
<b>输出：</b>2
<b>解释：</b>如上图所示，水平线 y=1.5 与折线相交了 2 次（用红叉表示）。水平线 y=2 与折线相交了 2 次（用红叉表示）。可以证明没有其他水平线可以与折线有超过 2 个点相交。因此，答案是 2。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= y.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= y[i] &lt;= 10<sup>9</sup></code></li>
	<li>对于范围&nbsp;<code>[1, n - 1]</code> 内的所有&nbsp;<code>i</code>，都有 <code>y[i] != y[i + 1]</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java
class Solution {
    public int maxIntersectionCount(int[] y) {
        final int n = y.length;
        int ans = 0;
        int intersectionCount = 0;
        TreeMap<Integer, Integer> line = new TreeMap<>();

        for (int i = 1; i < n; ++i) {
            final int start = 2 * y[i - 1];
            final int end = 2 * y[i] + (i == n - 1 ? 0 : y[i] > y[i - 1] ? -1 : 1);
            line.merge(Math.min(start, end), 1, Integer::sum);
            line.merge(Math.max(start, end) + 1, -1, Integer::sum);
        }

        for (final int count : line.values()) {
            intersectionCount += count;
            ans = Math.max(ans, intersectionCount);
        }

        return ans;
    }
}
```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
