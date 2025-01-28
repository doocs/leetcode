---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3433.Count%20Mentions%20Per%20User/README.md
tags:
    - 数组
    - 数学
    - 排序
    - 模拟
---

<!-- problem:start -->

# [3433. 统计用户被提及情况](https://leetcode.cn/problems/count-mentions-per-user)

[English Version](/solution/3400-3499/3433.Count%20Mentions%20Per%20User/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>numberOfUsers</code>&nbsp;表示用户总数，另有一个大小为 <code>n x 3</code> 的数组&nbsp;<code>events</code>&nbsp;。</p>

<p>每个&nbsp;<code inline="">events[i]</code>&nbsp;都属于下述两种类型之一：</p>

<ol>
	<li><strong>消息事件（Message Event）：</strong><code>["MESSAGE", "timestamp<sub>i</sub>", "mentions_string<sub>i</sub>"]</code>

    <ul>
    	<li>事件表示在&nbsp;<code>timestamp<sub>i</sub></code>&nbsp;时，一组用户被消息提及。</li>
    	<li><code>mentions_string<sub>i</sub></code>&nbsp;字符串包含下述标识符之一：
    	<ul>
    		<li><code>id&lt;number&gt;</code>：其中&nbsp;<code>&lt;number&gt;</code>&nbsp;是一个区间&nbsp;<code>[0,numberOfUsers - 1]</code>&nbsp;内的整数。可以用单个空格分隔&nbsp;<strong>多个</strong> id ，并且 id 可能重复。此外，这种形式可以提及离线用户。</li>
    		<li><code>ALL</code>：提及 <strong>所有</strong> 用户。</li>
    		<li><code>HERE</code>：提及所有 <strong>在线</strong> 用户。</li>
    	</ul>
    	</li>
    </ul>
    </li>
    <li><strong>离线事件（Offline Event）：</strong><code>["OFFLINE", "timestamp<sub>i</sub>", "id<sub>i</sub>"]</code>
    <ul>
    	<li>事件表示用户&nbsp;<code>id<sub>i</sub></code>&nbsp;在&nbsp;<code>timestamp<sub>i</sub></code>&nbsp;时变为离线状态 <strong>60 个单位时间</strong>。用户会在&nbsp;<code>timestamp<sub>i</sub> + 60</code>&nbsp;时自动再次上线。</li>
    </ul>
    </li>

</ol>

<p>返回数组&nbsp;<code>mentions</code>&nbsp;，其中&nbsp;<code>mentions[i]</code>&nbsp;表示 &nbsp;id 为 &nbsp;<code>i</code>&nbsp;的用户在所有&nbsp;<code>MESSAGE</code>&nbsp;事件中被提及的次数。</p>

<p>最初所有用户都处于在线状态，并且如果某个用户离线或者重新上线，其对应的状态变更将会在所有相同时间发生的消息事件之前进行处理和同步。</p>

<p><strong>注意 </strong>在单条消息中，同一个用户可能会被提及多次。每次提及都需要被 <strong>分别</strong> 统计。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]</span></p>

<p><span class="example-io"><b>输出：</b>[2,2]</span></p>

<p><b>解释：</b></p>

<p>最初，所有用户都在线。</p>

<p>时间戳 10 ，<code>id1</code>&nbsp;和&nbsp;<code>id0</code>&nbsp;被提及，<code>mentions = [1,1]</code></p>

<p>时间戳 11 ，<code>id0</code>&nbsp;<strong>离线</strong> 。</p>

<p>时间戳 71 ，<code>id0</code>&nbsp;再次 <strong>上线</strong>&nbsp;并且&nbsp;<code>"HERE"</code>&nbsp;被提及，<code>mentions = [2,2]</code></p>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","12","ALL"]]</span></p>

<p><span class="example-io"><b>输出：</b>[2,2]</span></p>

<p><b>解释：</b></p>

<p>最初，所有用户都在线。</p>

<p>时间戳 10 ，<code>id1</code>&nbsp;和&nbsp;<code>id0</code>&nbsp;被提及，<code>mentions = [1,1]</code></p>

<p>时间戳 11 ，<code>id0</code>&nbsp;<strong>离线</strong> 。</p>

<p>时间戳 12 ，<code>"ALL"</code>&nbsp;被提及。这种方式将会包括所有离线用户，所以&nbsp;<code>id0</code>&nbsp;和&nbsp;<code>id1</code>&nbsp;都被提及，<code>mentions = [2,2]</code></p>
</div>

<p><b>示例 3：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>numberOfUsers = 2, events = [["OFFLINE","10","0"],["MESSAGE","12","HERE"]]</span></p>

<p><span class="example-io"><b>输出：</b>[0,1]</span></p>

<p><b>解释：</b></p>

<p>最初，所有用户都在线。</p>

<p>时间戳 10 ，<code>id0</code>&nbsp;<strong>离线</strong>&nbsp;<b>。</b></p>

<p>时间戳 12 ，<code>"HERE"</code>&nbsp;被提及。由于&nbsp;<code>id0</code>&nbsp;仍处于离线状态，其将不会被提及，<code>mentions = [0,1]</code></p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= numberOfUsers &lt;= 100</code></li>
	<li><code>1 &lt;= events.length &lt;= 100</code></li>
	<li><code>events[i].length == 3</code></li>
	<li><code>events[i][0]</code>&nbsp;的值为&nbsp;<code>MESSAGE</code>&nbsp;或&nbsp;<code>OFFLINE</code>&nbsp;。</li>
	<li><code>1 &lt;= int(events[i][1]) &lt;= 10<sup>5</sup></code></li>
	<li>在任意 <code>"MESSAGE"</code>&nbsp;事件中，以&nbsp;<code>id&lt;number&gt;</code>&nbsp;形式提及的用户数目介于&nbsp;<code>1</code>&nbsp;和&nbsp;<code>100</code>&nbsp;之间。</li>
	<li><code>0 &lt;= &lt;number&gt; &lt;= numberOfUsers - 1</code></li>
	<li>题目保证 <code>OFFLINE</code>&nbsp;引用的用户 id 在事件发生时处于 <strong>在线</strong> 状态。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
