---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3577.Count%20the%20Number%20of%20Computer%20Unlocking%20Permutations/README.md
---

<!-- problem:start -->

# [3577. 统计计算机解锁顺序排列数](https://leetcode.cn/problems/count-the-number-of-computer-unlocking-permutations)

[English Version](/solution/3500-3599/3577.Count%20the%20Number%20of%20Computer%20Unlocking%20Permutations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的数组 <code>complexity</code>。</p>

<p>在房间里有 <code>n</code> 台&nbsp;<strong>上锁的&nbsp;</strong>计算机，这些计算机的编号为 0 到 <code>n - 1</code>，每台计算机都有一个&nbsp;<strong>唯一&nbsp;</strong>的密码。编号为 <code>i</code> 的计算机的密码复杂度为 <code>complexity[i]</code>。</p>

<p>编号为 0 的计算机密码已经&nbsp;<strong>解锁&nbsp;</strong>，并作为根节点。其他所有计算机必须通过它或其他已经解锁的计算机来解锁，具体规则如下：</p>

<ul>
	<li>可以使用编号为 <code>j</code> 的计算机的密码解锁编号为 <code>i</code> 的计算机，其中 <code>j</code> 是任何小于 <code>i</code> 的整数，且满足 <code>complexity[j] &lt; complexity[i]</code>（即 <code>j &lt; i</code> 并且 <code>complexity[j] &lt; complexity[i]</code>）。</li>
	<li>要解锁编号为 <code>i</code> 的计算机，你需要事先解锁一个编号为 <code>j</code> 的计算机，满足 <code>j &lt; i</code> 并且 <code>complexity[j] &lt; complexity[i]</code>。</li>
</ul>

<p>求共有多少种 <code>[0, 1, 2, ..., (n - 1)]</code> 的排列方式，能够表示从编号为 0 的计算机（唯一初始解锁的计算机）开始解锁所有计算机的有效顺序。</p>

<p>由于答案可能很大，返回结果需要对 <strong>10<sup>9</sup> + 7</strong> 取余数。</p>

<p><strong>注意：</strong>编号为 0 的计算机的密码已解锁，而&nbsp;<strong>不是&nbsp;</strong>排列中第一个位置的计算机密码已解锁。</p>

<p><strong>排列&nbsp;</strong>是一个数组中所有元素的重新排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">complexity = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>有效的排列有：</p>

<ul>
	<li>[0, 1, 2]
	<ul>
		<li>首先使用根密码解锁计算机 0。</li>
		<li>使用计算机 0 的密码解锁计算机 1，因为 <code>complexity[0] &lt; complexity[1]</code>。</li>
		<li>使用计算机 1 的密码解锁计算机 2，因为 <code>complexity[1] &lt; complexity[2]</code>。</li>
	</ul>
	</li>
	<li>[0, 2, 1]
	<ul>
		<li>首先使用根密码解锁计算机 0。</li>
		<li>使用计算机 0 的密码解锁计算机 2，因为 <code>complexity[0] &lt; complexity[2]</code>。</li>
		<li>使用计算机 0 的密码解锁计算机 1，因为 <code>complexity[0] &lt; complexity[1]</code>。</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">complexity = [3,3,3,4,4,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>没有任何排列能够解锁所有计算机。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= complexity.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= complexity[i] &lt;= 10<sup>9</sup></code></li>
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
