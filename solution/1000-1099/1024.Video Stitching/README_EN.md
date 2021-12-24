# [1024. Video Stitching](https://leetcode.com/problems/video-stitching)

[中文文档](/solution/1000-1099/1024.Video%20Stitching/README.md)

## Description

<p>You are given a series of video clips from a sporting event that lasted <code>T</code> seconds.&nbsp;&nbsp;These video clips can be overlapping with each other and have varied lengths.</p>

<p>Each video clip <code>clips[i]</code>&nbsp;is an interval: it starts at time <code>clips[i][0]</code> and ends at time <code>clips[i][1]</code>.&nbsp; We can cut these clips into segments freely: for example, a clip <code>[0, 7]</code> can be cut into segments&nbsp;<code>[0, 1] +&nbsp;[1, 3] + [3, 7]</code>.</p>

<p>Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event (<code>[0, T]</code>).&nbsp; If the task is impossible, return <code>-1</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>clips = <span id="example-input-1-1">[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]</span>, T = <span id="example-input-1-2">10</span>
<strong>Output: </strong><span id="example-output-1">3</span>
<strong>Explanation: </strong>
We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
Then, we can reconstruct the sporting event as follows:
We cut [1,9] into segments [1,2] + [2,8] + [8,9].
Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>clips = <span id="example-input-2-1">[[0,1],[1,2]]</span>, T = <span id="example-input-2-2">5</span>
<strong>Output: </strong><span id="example-output-2">-1</span>
<strong>Explanation: </strong>
We can&#39;t cover [0,5] with only [0,1] and [1,2].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong>clips = <span id="example-input-3-1">[[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]]</span>, T = <span id="example-input-3-2">9</span>
<strong>Output: </strong><span id="example-output-3">3</span>
<strong>Explanation: </strong>
We can take clips [0,4], [4,7], and [6,9].
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input: </strong>clips = <span id="example-input-4-1">[[0,4],[2,8]]</span>, T = <span id="example-input-4-2">5</span>
<strong>Output: </strong><span id="example-output-4">2</span>
<strong>Explanation: </strong>
Notice you can have extra video after the event ends.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= clips.length &lt;= 100</code></li>
	<li><code>0 &lt;= clips[i][0] &lt;=&nbsp;clips[i][1] &lt;= 100</code></li>
	<li><code>0 &lt;= T &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
