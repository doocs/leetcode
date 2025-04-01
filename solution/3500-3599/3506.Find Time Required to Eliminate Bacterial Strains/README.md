---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3506.Find%20Time%20Required%20to%20Eliminate%20Bacterial%20Strains/README.md
---

<!-- problem:start -->

# [3506. 查找消除细菌菌株所需时间 II 🔒](https://leetcode.cn/problems/find-time-required-to-eliminate-bacterial-strains)

[English Version](/solution/3500-3599/3506.Find%20Time%20Required%20to%20Eliminate%20Bacterial%20Strains/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>timeReq</code>&nbsp;和一个整数&nbsp;<code>splitTime</code>。</p>

<p>在人体微观世界中，免疫系统面临着一项非凡的挑战：对抗快速繁殖的细菌群落，这对身体的生存构成威胁。</p>

<p>最初，只部署一个 <strong>白细胞</strong>（<strong>WBC</strong>）来消除细菌。然而，单独的白细胞很快意识到它无法跟上细菌的生长速度。</p>

<p>WBC制定了一种巧妙的策略来对抗细菌：</p>

<ul>
	<li>第 <code>i</code> 个细菌菌株需要 <code>timeReq[i]</code> 个时间单位来被消除。</li>
	<li>单个白细胞只能消除 <strong>一种</strong> 细菌菌株。之后，白细胞耗尽，无法执行任何其他任务。</li>
	<li>一个白细胞可以将自身分裂为两个白细胞，但这需要&nbsp;<code>splitTime</code>&nbsp;单位时间。一旦分裂，两个白细胞就可以 <strong>并行</strong> 消灭细菌。</li>
	<li>仅有一个白细胞可以针对一个单一细菌菌株工作。多个白细胞不能同时攻击一个菌株。</li>
</ul>

<p>您必须确定消除所有细菌菌株所需的 <strong>最短</strong> 时间。</p>

<p><strong>注意</strong>，细菌菌株可以按任何顺序消除。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>timeReq = [10,4,5], splitTime = 2</span></p>

<p><span class="example-io"><b>输出：</b>12</span></p>

<p><b>解释：</b></p>

<p>消除过程如下：</p>

<ul>
	<li>最初，有一个白细胞。经过 2 个时间单位后，白细胞分裂成 2 个白细胞。</li>
	<li>其中一个白细胞在&nbsp;<code>t = 2 + 10 = 12</code>&nbsp;时间内消除菌株 0。另一个白细胞使用 2 个单位时间再次分裂。</li>
	<li>2 个新的白细胞消灭细菌的时间是 <code>t = 2 + 2 + 4</code> 和&nbsp;<code>t = 2 + 2 + 5</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>timeReq = [10,4], splitTime = 5</span></p>

<p><b>输出：</b>5</p>

<p><strong>解释：</strong></p>

<p>消除过程如下：</p>

<ul>
	<li>最初，有一个白细胞。经过 5 个时间单位后，白细胞分裂成 2 个白细胞。</li>
	<li>2 个新的白细胞消灭细菌的时间是&nbsp;<code>t = 5 + 10</code> 和&nbsp;<code>t = 5 + 4</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= timeReq.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= timeReq[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= splitTime &lt;= 10<sup>9</sup></code></li>
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
