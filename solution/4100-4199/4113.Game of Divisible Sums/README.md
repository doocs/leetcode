---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4113. 整除和博弈](https://leetcode.cn/problems/game-of-divisible-sums)

[English Version](/solution/4100-4199/4113.Game%20of%20Divisible%20Sums/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 正在用一个整数数组 <code>nums</code> 玩游戏。Alice 先手。</p>

<p>每个回合，当前玩家可以取走并移除<strong>&nbsp;最左边的一个元素</strong>&nbsp;，或者在至少还剩两个元素时，取走并移除&nbsp;<strong>最左边的两个元素</strong>。</p>

<p>本回合取走的元素之&nbsp;<strong>和&nbsp;</strong>决定接下来由谁行动：</p>

<ul>
	<li>如果该总和能被 3 整除，则接下来由另一位玩家行动。</li>
	<li>否则，当前玩家继续行动。</li>
</ul>

<p>一位玩家的&nbsp;<strong>得分</strong>&nbsp;是其取走的所有元素的值之和。</p>

<p>两位玩家都采用最优策略，各自力求<strong>&nbsp;最大化&nbsp;</strong>自己的得分。</p>

<p>当所有元素都被取走时，游戏结束。</p>

<p>返回 Alice 的得分&nbsp;<strong>减去</strong> Bob 的得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,6,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的回合序列如下：</p>

<ol>
	<li>Alice 取走最左边的两个元素。它们的总和为 <code>3 + 6 = 9</code>，能被 3 整除，因此接下来由 Bob 行动。此时 Alice 的得分为 9，剩余数组为 <code>[4]</code>。</li>
	<li>Bob 取走唯一剩余的元素。他的得分为 4，游戏结束。</li>
</ol>

<p>Alice 的得分减去 Bob 的得分为 <code>9 - 4 = 5</code>，因此答案为 5。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,1,2,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的回合序列如下：</p>

<ol>
	<li>Alice 取走最左边的两个元素。它们的总和为 <code>6 + 1 = 7</code>，不能被 3 整除，因此 Alice 继续行动。此时她的得分为 7，剩余数组为 <code>[2, 1, 2]</code>。</li>
	<li>Alice 取走最左边的一个元素。它的值为 2，不能被 3 整除，因此 Alice 继续行动。此时她的得分为 <code>7 + 2 = 9</code>，剩余数组为 <code>[1, 2]</code>。</li>
	<li>Alice 取走剩余的两个元素。此时她的得分为 <code>9 + 1 + 2 = 12</code>，游戏结束。</li>
</ol>

<p>Bob 没有取走任何元素，因此他的得分为 0。Alice 的得分减去 Bob 的得分为 <code>12 - 0 = 12</code>，因此答案为 12。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,4,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">-3</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的回合序列如下：</p>

<ol>
	<li>Alice 取走最左边的两个元素。它们的总和为 <code>3 + 3 = 6</code>，能被 3 整除，因此接下来由 Bob 行动。此时 Alice 的得分为 6，剩余数组为 <code>[4, 5]</code>。</li>
	<li>Bob 取走剩余的两个元素。他的得分为 <code>4 + 5 = 9</code>，游戏结束。</li>
</ol>

<p>Alice 的得分减去 Bob 的得分为 <code>6 - 9 = -3</code>，因此答案为 -3。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
