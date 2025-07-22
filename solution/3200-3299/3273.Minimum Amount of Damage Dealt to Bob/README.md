---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3273.Minimum%20Amount%20of%20Damage%20Dealt%20to%20Bob/README.md
rating: 2012
source: 第 138 场双周赛 Q4
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [3273. 对 Bob 造成的最少伤害](https://leetcode.cn/problems/minimum-amount-of-damage-dealt-to-bob)

[English Version](/solution/3200-3299/3273.Minimum%20Amount%20of%20Damage%20Dealt%20to%20Bob/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>power</code>&nbsp;和两个整数数组&nbsp;<code>damage</code> 和&nbsp;<code>health</code>&nbsp;，两个数组的长度都为&nbsp;<code>n</code>&nbsp;。</p>

<p>Bob 有&nbsp;<code>n</code>&nbsp;个敌人，如果第&nbsp;<code>i</code>&nbsp;个敌人还活着（也就是健康值&nbsp;<code>health[i] &gt; 0</code>&nbsp;的时候），每秒钟会对 Bob 造成&nbsp;<code>damage[i]</code>&nbsp;<strong>点</strong>&nbsp;伤害。</p>

<p>每一秒中，在敌人对 Bob 造成伤害 <strong>之后</strong>&nbsp;，Bob 会选择 <strong>一个</strong>&nbsp;还活着的敌人进行攻击，该敌人的健康值减少 <code>power</code>&nbsp;。</p>

<p>请你返回 Bob 将 <strong>所有</strong>&nbsp;<code>n</code>&nbsp;个敌人都消灭之前，<strong>最少</strong>&nbsp;会受到多少伤害。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>power = 4, damage = [1,2,3,4], health = [4,5,6,8]</span></p>

<p><span class="example-io"><b>输出：</b>39</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>最开始 2 秒内都攻击敌人 3 ，然后敌人 3 会被消灭，这段时间内对 Bob 的总伤害是&nbsp;<code>10 + 10 = 20</code>&nbsp;点。</li>
	<li>接下来 2 秒内都攻击敌人 2 ，然后敌人 2 会被消灭，这段时间内对 Bob 的总伤害是&nbsp;<code>6 + 6 = 12</code>&nbsp;点。</li>
	<li>接下来 1 秒内都攻击敌人 0 ，然后敌人 0 会被消灭，这段时间内对 Bob 的总伤害是&nbsp;<code>3</code>&nbsp;点。</li>
	<li>接下来 2 秒内都攻击敌人 1 ，然后敌人 1 会被消灭，这段时间内对 Bob 的总伤害是&nbsp;<code>2 + 2 = 4</code>&nbsp;点。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>power = 1, damage = [1,1,1,1], health = [1,2,3,4]</span></p>

<p><span class="example-io"><b>输出：</b>20</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>最开始 1 秒内都攻击敌人 0 ，然后敌人 0 会被消灭，这段时间对 Bob 的总伤害是&nbsp;<code>4</code>&nbsp;点。</li>
	<li>接下来 2 秒内都攻击敌人 1 ，然后敌人 1 会被消灭，这段时间对 Bob 的总伤害是&nbsp;<code>3 + 3 = 6</code>&nbsp;点。</li>
	<li>接下来 3 秒内都攻击敌人 2 ，然后敌人 2 会被消灭，这段时间对 Bob 的总伤害是&nbsp;<code>2 + 2 + 2 = 6</code>&nbsp;点。</li>
	<li>接下来 4 秒内都攻击敌人 3 ，然后敌人 3 会被消灭，这段时间对 Bob 的总伤害是&nbsp;<code>1 + 1 + 1 + 1 = 4</code>&nbsp;点。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>power = 8, damage = [40], health = [59]</span></p>

<p><span class="example-io"><b>输出：</b>320</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= power &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= n == damage.length == health.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= damage[i], health[i] &lt;= 10<sup>4</sup></code></li>
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
