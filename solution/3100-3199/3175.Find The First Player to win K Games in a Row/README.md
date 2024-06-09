---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3175.Find%20The%20First%20Player%20to%20win%20K%20Games%20in%20a%20Row/README.md
---

<!-- problem:start -->

# [3175. 找到连续赢 K 场比赛的第一位玩家](https://leetcode.cn/problems/find-the-first-player-to-win-k-games-in-a-row)

[English Version](/solution/3100-3199/3175.Find%20The%20First%20Player%20to%20win%20K%20Games%20in%20a%20Row/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有&nbsp;<code>n</code>&nbsp;位玩家在进行比赛，玩家编号依次为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。</p>

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>skills</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;，其中&nbsp;<code>skills[i]</code>&nbsp;是第 <code>i</code>&nbsp;位玩家的技能等级。<code>skills</code>&nbsp;中所有整数 <strong>互不相同</strong>&nbsp;。</p>

<p>所有玩家从编号 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;排成一列。</p>

<p>比赛进行方式如下：</p>

<ul>
	<li>队列中最前面两名玩家进行一场比赛，技能等级 <strong>更高</strong>&nbsp;的玩家胜出。</li>
	<li>比赛后，获胜者保持在队列的开头，而失败者排到队列的末尾。</li>
</ul>

<p>这个比赛的赢家是 <strong>第一位连续</strong>&nbsp;赢下&nbsp;<code>k</code>&nbsp;场比赛的玩家。</p>

<p>请你返回这个比赛的赢家编号。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>skills = [4,2,6,3,9], k = 2</span></p>

<p><b>输出：</b>2</p>

<p><strong>解释：</strong></p>

<p>一开始，队列里的玩家为&nbsp;<code>[0,1,2,3,4]</code>&nbsp;。比赛过程如下：</p>

<ul>
	<li>玩家 0 和 1 进行一场比赛，玩家 0 的技能等级高于玩家 1 ，玩家 0 胜出，队列变为&nbsp;<code>[0,2,3,4,1]</code>&nbsp;。</li>
	<li>玩家 0 和 2 进行一场比赛，玩家 2 的技能等级高于玩家 0 ，玩家 2 胜出，队列变为&nbsp;<code>[2,3,4,1,0]</code>&nbsp;。</li>
	<li>玩家 2 和 3 进行一场比赛，玩家 2 的技能等级高于玩家 3 ，玩家 2 胜出，队列变为&nbsp;<code>[2,4,1,0,3]</code>&nbsp;。</li>
</ul>

<p>玩家 2 连续赢了&nbsp;<code>k = 2</code>&nbsp;场比赛，所以赢家是玩家 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>skills = [2,5,4], k = 3</span></p>

<p><b>输出：</b>1</p>

<p><strong>解释：</strong></p>

<p>一开始，队列里的玩家为&nbsp;<code>[0,1,2]</code>&nbsp;。比赛过程如下：</p>

<ul>
	<li>玩家 0 和 1 进行一场比赛，玩家 1 的技能等级高于玩家 0 ，玩家 1 胜出，队列变为&nbsp;<code>[1,2,0]</code>&nbsp;。</li>
	<li>玩家 1&nbsp;和 2&nbsp;进行一场比赛，玩家 1 的技能等级高于玩家 2&nbsp;，玩家 1 胜出，队列变为&nbsp;<code>[1,0,2]</code>&nbsp;。</li>
	<li>玩家 1&nbsp;和 0&nbsp;进行一场比赛，玩家 1 的技能等级高于玩家 0&nbsp;，玩家 1 胜出，队列变为&nbsp;<code>[1,2,0]</code>&nbsp;。</li>
</ul>

<p>玩家 1 连续赢了&nbsp;<code>k = 3</code>&nbsp;场比赛，所以赢家是玩家 1 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == skills.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= skills[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>skills</code>&nbsp;中的整数互不相同。</li>
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
