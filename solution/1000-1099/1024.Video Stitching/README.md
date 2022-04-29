# [1024. 视频拼接](https://leetcode.cn/problems/video-stitching)

[English Version](/solution/1000-1099/1024.Video%20Stitching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你将会获得一系列视频片段，这些片段来自于一项持续时长为&nbsp;<code>time</code>&nbsp;秒的体育赛事。这些片段可能有所重叠，也可能长度不一。</p>

<p>使用数组&nbsp;<code>clips</code> 描述所有的视频片段，其中 <code>clips[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 表示：某个视频片段开始于&nbsp;<code>start<sub>i</sub></code>&nbsp;并于&nbsp;<code>end<sub>i</sub></code>&nbsp;结束。</p>

<p>甚至可以对这些片段自由地再剪辑：</p>

<ul>
	<li>例如，片段&nbsp;<code>[0, 7]</code>&nbsp;可以剪切成&nbsp;<code>[0, 1] +&nbsp;[1, 3] + [3, 7]</code>&nbsp;三部分。</li>
</ul>

<p>我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（<code>[0, time]</code>）。返回所需片段的最小数目，如果无法完成该任务，则返回&nbsp;<code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
<strong>输出：</strong>3
<strong>解释：</strong>
选中 [0,2], [8,10], [1,9] 这三个片段。
然后，按下面的方案重制比赛片段：
将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>clips = [[0,1],[1,2]], time = 5
<strong>输出：</strong>-1
<strong>解释：</strong>
无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
<strong>输出：</strong>3
<strong>解释： </strong>
选取片段 [0,4], [4,7] 和 [6,9] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= clips.length &lt;= 100</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 100</code></li>
	<li><code>1 &lt;= time &lt;= 100</code></li>
</ul>

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
