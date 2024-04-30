# [3068. 最大节点价值之和](https://leetcode.cn/problems/find-the-maximum-sum-of-node-values)

[English Version](/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/README_EN.md)

<!-- tags:贪心,位运算,树,数组,动态规划,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵 <code>n</code>&nbsp;个节点的 <strong>无向</strong>&nbsp;树，节点从 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;编号。树以长度为 <code>n - 1</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的二维整数数组 <code>edges</code>&nbsp;的形式给你，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示树中节点&nbsp;<code>u<sub>i</sub></code>&nbsp;和&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条边。同时给你一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;和一个长度为 <code>n</code>&nbsp;下标从&nbsp;<strong>0</strong>&nbsp;开始的&nbsp;<strong>非负</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;表示节点 <code>i</code>&nbsp;的 <strong>价值</strong>&nbsp;。</p>

<p>Alice&nbsp;想 <strong>最大化</strong>&nbsp;树中所有节点价值之和。为了实现这一目标，Alice 可以执行以下操作 <strong>任意</strong>&nbsp;次（<strong>包括</strong><strong>&nbsp;0 次</strong>）：</p>

<ul>
	<li>选择连接节点&nbsp;<code>u</code>&nbsp;和&nbsp;<code>v</code>&nbsp;的边&nbsp;<code>[u, v]</code>&nbsp;，并将它们的值更新为：

    <ul>
    	<li><code>nums[u] = nums[u] XOR k</code></li>
    	<li><code>nums[v] = nums[v] XOR k</code></li>
    </ul>
    </li>

</ul>

<p>请你返回 Alice 通过执行以上操作 <strong>任意次</strong>&nbsp;后，可以得到所有节点 <strong>价值之和</strong>&nbsp;的 <strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2023-11-10-012513.png" style="width: 300px; height: 277px;padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<pre>
<b>输入：</b>nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
<b>输出：</b>6
<b>解释：</b>Alice 可以通过一次操作得到最大价值和 6 ：
- 选择边 [0,2] 。nums[0] 和 nums[2] 都变为：1 XOR 3 = 2 ，数组 nums 变为：[1,2,1] -&gt; [2,2,2] 。
所有节点价值之和为 2 + 2 + 2 = 6 。
6 是可以得到最大的价值之和。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2024-01-09-220017.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 300px; height: 239px;" /></p>

<pre>
<b>输入：</b>nums = [2,3], k = 7, edges = [[0,1]]
<b>输出：</b>9
<b>解释：</b>Alice 可以通过一次操作得到最大和 9 ：
- 选择边 [0,1] 。nums[0] 变为：2 XOR 7 = 5 ，nums[1] 变为：3 XOR 7 = 4 ，数组 nums 变为：[2,3] -&gt; [5,4] 。
所有节点价值之和为 5 + 4 = 9 。
9 是可以得到最大的价值之和。
</pre>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2023-11-10-012641.png" style="width: 600px; height: 233px;padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<pre>
<b>输入：</b>nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
<b>输出：</b>42
<b>解释：</b>Alice 不需要执行任何操作，就可以得到最大价值之和 42 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li>输入保证&nbsp;<code>edges</code>&nbsp;构成一棵合法的树。</li>
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
