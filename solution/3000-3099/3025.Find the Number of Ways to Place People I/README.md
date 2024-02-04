# [3025. 人员站位的方案数 I](https://leetcode.cn/problems/find-the-number-of-ways-to-place-people-i)

[English Version](/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;&nbsp;<code>n x 2</code>&nbsp;的二维数组 <code>points</code>&nbsp;，它表示二维平面上的一些点坐标，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;。</p>

<p>我们定义 x 轴的正方向为 <strong>右</strong>&nbsp;（<strong>x 轴递增的方向</strong>），x 轴的负方向为 <strong>左</strong>&nbsp;（<strong>x 轴递减的方向</strong>）。类似的，我们定义 y 轴的正方向为 <strong>上</strong>&nbsp;（<strong>y 轴递增的方向</strong>），y 轴的负方向为 <strong>下</strong>&nbsp;（<strong>y 轴递减的方向</strong>）。</p>

<p>你需要安排这 <code>n</code>&nbsp;个人的站位，这 <code>n</code>&nbsp;个人中包括&nbsp;liupengsay 和小羊肖恩&nbsp;。你需要确保每个点处&nbsp;<strong>恰好</strong>&nbsp;有&nbsp;<strong>一个</strong>&nbsp;人。同时，liupengsay 想跟小羊肖恩单独玩耍，所以&nbsp;liupengsay&nbsp;会以 liupengsay<b>&nbsp;</b>的坐标为 <strong>左上角</strong>&nbsp;，小羊肖恩的坐标为 <strong>右下角</strong>&nbsp;建立一个矩形的围栏（<strong>注意</strong>，围栏可能&nbsp;<strong>不</strong> 包含任何区域，也就是说围栏可能是一条线段）。如果围栏的 <strong>内部</strong>&nbsp;或者 <strong>边缘</strong>&nbsp;上有任何其他人，liupengsay 都会难过。</p>

<p>请你在确保 liupengsay&nbsp;<strong>不会</strong> 难过的前提下，返回 liupengsay 和小羊肖恩可以选择的 <strong>点对</strong>&nbsp;数目。</p>

<p><b>注意</b>，liupengsay 建立的围栏必须确保 liupengsay 的位置是矩形的左上角，小羊肖恩的位置是矩形的右下角。比方说，以&nbsp;<code>(1, 1)</code>&nbsp;，<code>(1, 3)</code>&nbsp;，<code>(3, 1)</code>&nbsp;和&nbsp;<code>(3, 3)</code>&nbsp;为矩形的四个角，给定下图的两个输入，liupengsay 都不能建立围栏，原因如下：</p>

<ul>
	<li>图一中，liupengsay 在&nbsp;<code>(3, 3)</code>&nbsp;且小羊肖恩在&nbsp;<code>(1, 1)</code>&nbsp;，liupengsay 的位置不是左上角且小羊肖恩的位置不是右下角。</li>
	<li>图二中，liupengsay 在&nbsp;<code>(1, 3)</code>&nbsp;且小羊肖恩在&nbsp;<code>(1, 1)</code>&nbsp;，小羊肖恩的位置不是在围栏的右下角。</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/example0alicebob-1.png" style="width: 750px; height: 308px;padding: 10px; background: #fff; border-radius: .5rem;" />
<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/example1alicebob.png" style="width: 376px; height: 308px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" /></p>

<pre>
<b>输入：</b>points = [[1,1],[2,2],[3,3]]
<b>输出：</b>0
<strong>解释：</strong>没有办法可以让 liupengsay 的围栏以 liupengsay 的位置为左上角且小羊肖恩的位置为右下角。所以我们返回 0 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><strong class="example"><a href="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/1706880313-YelabI-example2.jpeg"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/1706880313-YelabI-example2.jpeg" style="width: 900px; height: 247px;" /></a></strong></p>

<pre>
<b>输入：</b>points = [[6,2],[4,4],[2,6]]
<b>输出：</b>2
<b>解释：</b>总共有 2 种方案安排 liupengsay 和小羊肖恩的位置，使得 liupengsay 不会难过：
- liupengsay 站在 (4, 4) ，小羊肖恩站在 (6, 2) 。
- liupengsay 站在 (2, 6) ，小羊肖恩站在 (4, 4) 。
不能安排 liupengsay 站在 (2, 6) 且小羊肖恩站在 (6, 2) ，因为站在 (4, 4) 的人处于围栏内。
</pre>

<p><strong class="example">示例 3：</strong></p>

<p><strong class="example"><a href="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/1706880311-mtPGYC-example3.jpeg"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/1706880311-mtPGYC-example3.jpeg" style="width: 900px; height: 247px;" /></a></strong></p>

<pre>
<b>输入：</b>points = [[3,1],[1,3],[1,1]]
<b>输出：</b>2
<b>解释：</b>总共有 2 种方案安排 liupengsay 和小羊肖恩的位置，使得 liupengsay 不会难过：
- liupengsay 站在 (1, 1) ，小羊肖恩站在 (3, 1) 。
- liupengsay 站在 (1, 3) ，小羊肖恩站在 (1, 1) 。
不能安排 liupengsay 站在 (1, 3) 且小羊肖恩站在 (3, 1) ，因为站在 (1, 1) 的人处于围栏内。
注意围栏是可以不包含任何面积的，上图中第一和第二个围栏都是合法的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= points[i][0], points[i][1] &lt;= 50</code></li>
	<li><code>points[i]</code>&nbsp;点对两两不同。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
