# [3096. 得到更多分数的最少关卡数目](https://leetcode.cn/problems/minimum-levels-to-gain-more-points)

[English Version](/solution/3000-3099/3096.Minimum%20Levels%20to%20Gain%20More%20Points/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的二进制数组&nbsp;<code>possible</code>&nbsp;。</p>

<p>莉叩酱和冬坂五百里正在玩一个有 <code>n</code> 个关卡的游戏，游戏中有一些关卡是 <strong>困难</strong>&nbsp;模式，其他的关卡是 <strong>简单</strong>&nbsp;模式。如果&nbsp;<code>possible[i] == 0</code>&nbsp;，那么第&nbsp;<code>i</code> 个关卡是 <strong>困难</strong>&nbsp;模式。一个玩家通过一个简单模式的关卡可以获得 <code>1</code>&nbsp;分，通过困难模式的关卡将失去 <code>1</code>&nbsp;分。</p>

<p>游戏的一开始，莉叩酱将从第 <code>0</code>&nbsp;级开始 <strong>按顺序</strong> 完成一些关卡，然后冬坂五百里会完成剩下的所有关卡。</p>

<p>假设两名玩家都采取最优策略，目的是&nbsp;<strong>最大化</strong>&nbsp;自己的得分，莉叩酱想知道自己&nbsp;<strong>最少</strong> 需要完成多少个关卡，才能获得比冬坂五百里更多的分数。</p>

<p>请你返回莉叩酱获得比冬坂五百里更多的分数所需要完成的 <strong>最少</strong> 关卡数目，如果 <strong>无法</strong>&nbsp;达成，那么返回 <code>-1</code>&nbsp;。</p>

<p><strong>注意</strong>，每个玩家都至少需要完成&nbsp;<code>1</code> 个关卡。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>possible = [1,0,1,0]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>我们来看一下莉叩酱可以完成的关卡数目：</p>

<ul>
	<li>如果莉叩酱只完成关卡 0 ，冬坂五百里完成剩下的所有关卡，那么莉叩酱获得 1 分，冬坂五百里获得 -1 + 1 - 1 = -1 分。</li>
	<li>如果莉叩酱完成到关卡 1 ，冬坂五百里完成剩下的所有关卡，那么莉叩酱获得&nbsp;1 - 1 = 0 分，冬坂五百里获得 1 - 1 = 0 分。</li>
	<li>如果莉叩酱完成到关卡 2 ，冬坂五百里完成剩下的所有关卡，那么莉叩酱获得&nbsp;1 - 1 + 1 = 1 分，冬坂五百里获得 -1 分。</li>
</ul>

<p>莉叩酱需要完成至少一个关卡获得更多的分数。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>possible = [1,1,1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>我们来看一下莉叩酱可以完成的关卡数目：</p>

<ul>
	<li>如果莉叩酱只完成关卡 0 ，冬坂五百里完成剩下的所有关卡，那么莉叩酱获得 1 分，冬坂五百里获得 4 分。</li>
	<li>如果莉叩酱完成到关卡 1 ，冬坂五百里完成剩下的所有关卡，那么莉叩酱获得&nbsp;2 分，冬坂五百里获得 3 分。</li>
	<li>如果莉叩酱完成到关卡 2 ，冬坂五百里完成剩下的所有关卡，那么莉叩酱获得&nbsp;3 分，冬坂五百里获得 2&nbsp;分。</li>
	<li>如果莉叩酱完成到关卡 3&nbsp;，冬坂五百里完成剩下的所有关卡，那么莉叩酱获得 4&nbsp;分，冬坂五百里获得 1&nbsp;分。</li>
</ul>

<p>莉叩酱需要完成至少三个关卡获得更多的分数。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>possible = [0,0]</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><strong>解释：</strong></p>

<p>两名玩家只能各完成 1 个关卡，莉叩酱完成关卡 0 得到 -1 分，冬坂五百里完成关卡 1 得到 -1 分。两名玩家得分相同，所以莉叩酱无法得到更多分数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == possible.length &lt;= 10<sup>5</sup></code></li>
	<li><code>possible[i]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code> 。</li>
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
