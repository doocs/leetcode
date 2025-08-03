---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3575.Maximum%20Good%20Subtree%20Score/README_EN.md
rating: 2359
source: Biweekly Contest 158 Q4
tags:
    - Bit Manipulation
    - Tree
    - Depth-First Search
    - Array
    - Dynamic Programming
    - Bitmask
---

<!-- problem:start -->

# [3575. Maximum Good Subtree Score](https://leetcode.com/problems/maximum-good-subtree-score)

[中文文档](/solution/3500-3599/3575.Maximum%20Good%20Subtree%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given an undirected tree rooted at node 0 with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. Each node <code>i</code> has an integer value <code>vals[i]</code>, and its parent is given by <code>par[i]</code>.</p>

<p>A <strong>subset</strong> of nodes within the <strong>subtree</strong> of a node is called <strong>good</strong> if every digit from 0 to 9 appears <strong>at most</strong> once in the decimal representation of the values of the selected nodes.</p>

<p>The <strong>score</strong> of a good subset is the sum of the values of its nodes.</p>

<p>Define an array <code>maxScore</code> of length <code>n</code>, where <code>maxScore[u]</code> represents the <strong>maximum</strong> possible sum of values of a good subset of nodes that belong to the subtree rooted at node <code>u</code>, including <code>u</code> itself and all its descendants.</p>

<p>Return the sum of all values in <code>maxScore</code>.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">vals = [2,3], par = [-1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3575.Maximum%20Good%20Subtree%20Score/images/screenshot-2025-04-29-at-150754.png" style="height: 84px; width: 180px;" /></p>

<ul>
	<li>The subtree rooted at node 0 includes nodes <code>{0, 1}</code>. The subset <code>{2, 3}</code> is<i> </i>good as the digits 2 and 3 appear only once. The score of this subset is <code>2 + 3 = 5</code>.</li>
	<li>The subtree rooted at node 1 includes only node <code>{1}</code>. The subset <code>{3}</code> is<i> </i>good. The score of this subset is 3.</li>
	<li>The <code>maxScore</code> array is <code>[5, 3]</code>, and the sum of all values in <code>maxScore</code> is <code>5 + 3 = 8</code>. Thus, the answer is 8.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">vals = [1,5,2], par = [-1,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3575.Maximum%20Good%20Subtree%20Score/images/screenshot-2025-04-29-at-151408.png" style="width: 205px; height: 140px;" /></strong></p>

<ul>
	<li>The subtree rooted at node 0 includes nodes <code>{0, 1, 2}</code>. The subset <code>{1, 5, 2}</code> is<i> </i>good as the digits 1, 5 and 2 appear only once. The score of this subset is <code>1 + 5 + 2 = 8</code>.</li>
	<li>The subtree rooted at node 1 includes only node <code>{1}</code>. The subset <code>{5}</code> is<i> </i>good. The score of this subset is 5.</li>
	<li>The subtree rooted at node 2 includes only node <code>{2}</code>. The subset <code>{2}</code> is<i> </i>good. The score of this subset is 2.</li>
	<li>The <code>maxScore</code> array is <code>[8, 5, 2]</code>, and the sum of all values in <code>maxScore</code> is <code>8 + 5 + 2 = 15</code>. Thus, the answer is 15.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">vals = [34,1,2], par = [-1,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">42</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3575.Maximum%20Good%20Subtree%20Score/images/screenshot-2025-04-29-at-151747.png" style="height: 80px; width: 256px;" /></p>

<ul>
	<li>The subtree rooted at node 0 includes nodes <code>{0, 1, 2}</code>. The subset <code>{34, 1, 2}</code> is<i> </i>good as the digits 3, 4, 1 and 2 appear only once. The score of this subset is <code>34 + 1 + 2 = 37</code>.</li>
	<li>The subtree rooted at node 1 includes node <code>{1, 2}</code>. The subset <code>{1, 2}</code> is<i> </i>good as the digits 1 and 2 appear only once. The score of this subset is <code>1 + 2 = 3</code>.</li>
	<li>The subtree rooted at node 2 includes only node <code>{2}</code>. The subset <code>{2}</code> is<i> </i>good. The score of this subset is 2.</li>
	<li>The <code>maxScore</code> array is <code>[37, 3, 2]</code>, and the sum of all values in <code>maxScore</code> is <code>37 + 3 + 2 = 42</code>. Thus, the answer is 42.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">vals = [3,22,5], par = [-1,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">18</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The subtree rooted at node 0 includes nodes <code>{0, 1, 2}</code>. The subset <code>{3, 22, 5}</code> is<i> </i>not good, as digit 2 appears twice. Therefore, the subset <code>{3, 5}</code> is valid. The score of this subset is <code>3 + 5 = 8</code>.</li>
	<li>The subtree rooted at node 1 includes nodes <code>{1, 2}</code>. The subset <code>{22, 5}</code> is<i> </i>not good, as digit 2 appears twice. Therefore, the subset <code>{5}</code> is valid. The score of this subset is 5.</li>
	<li>The subtree rooted at node 2 includes <code>{2}</code>. The subset <code>{5}</code> is<i> </i>good. The score of this subset is 5.</li>
	<li>The <code>maxScore</code> array is <code>[8, 5, 5]</code>, and the sum of all values in <code>maxScore</code> is <code>8 + 5 + 5 = 18</code>. Thus, the answer is 18.</li>
</ul>

<ul>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == vals.length &lt;= 500</code></li>
	<li><code>1 &lt;= vals[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>par.length == n</code></li>
	<li><code>par[0] == -1</code></li>
	<li><code>0 &lt;= par[i] &lt; n</code> for <code>i</code> in <code>[1, n - 1]</code></li>
	<li>The input is generated such that the parent array <code>par</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
