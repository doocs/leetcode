---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3506.Find%20Time%20Required%20to%20Eliminate%20Bacterial%20Strains%20II/README.md
---

<!-- problem:start -->

# [3506. Find Time Required to Eliminate Bacterial Strains II 🔒](https://leetcode.cn/problems/find-time-required-to-eliminate-bacterial-strains-ii)

[English Version](/solution/3500-3599/3506.Find%20Time%20Required%20to%20Eliminate%20Bacterial%20Strains%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer array <code>timeReq</code> and an integer <code>splitTime</code>.</p>

<p>In the microscopic world of the human body, the immune system faces an extraordinary challenge: combatting a rapidly multiplying bacterial colony that threatens the body&#39;s survival.</p>

<p>Initially, only one <strong>white blood cell</strong> (<strong>WBC</strong>) is deployed to eliminate the bacteria. However, the lone WBC quickly realizes it cannot keep up with the bacterial growth rate.</p>

<p>The WBC devises a clever strategy to fight the bacteria:</p>

<ul>
	<li>The <code>i<sup>th</sup></code> bacterial strain takes <code>timeReq[i]</code> units of time to be eliminated.</li>
	<li>A single WBC can eliminate <strong>only one</strong> bacterial strain. Afterwards, the WBC is exhausted and cannot perform any other tasks.</li>
	<li>A WBC can split itself into two WBCs, but this requires <code>splitTime</code> units of time. Once split, the two WBCs can work in <strong>parallel</strong> on eliminating the bacteria.</li>
	<li><em>Only one</em> WBC can work on a single bacterial strain. Multiple WBCs <strong>cannot</strong> attack one strain in parallel.</li>
</ul>

<p>You must determine the <strong>minimum</strong> time required to eliminate all the bacterial strains.</p>

<p><strong>Note</strong> that the bacterial strains can be eliminated in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">timeReq = [10,4,5], splitTime = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The elimination process goes as follows:</p>

<ul>
	<li>Initially, there is a single WBC. The WBC splits into 2 WBCs after 2 units of time.</li>
	<li>One of the WBCs eliminates strain 0 at a time <code>t = 2 + 10 = 12.</code> The other WBC splits again, using 2 units of time.</li>
	<li>The 2 new WBCs eliminate the bacteria at times <code>t = 2 + 2 + 4</code> and <code>t = 2 + 2 + 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">timeReq = [10,4], splitTime = 5</span></p>

<p><strong>Output:</strong>15</p>

<p><strong>Explanation:</strong></p>

<p>The elimination process goes as follows:</p>

<ul>
	<li>Initially, there is a single WBC. The WBC splits into 2 WBCs after 5 units of time.</li>
	<li>The 2 new WBCs eliminate the bacteria at times <code>t = 5 + 10</code> and <code>t = 5 + 4</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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
