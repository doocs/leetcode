# [2003. 每棵子树内缺失的最小基因值](https://leetcode.cn/problems/smallest-missing-genetic-value-in-each-subtree)

[English Version](/solution/2000-2099/2003.Smallest%20Missing%20Genetic%20Value%20in%20Each%20Subtree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一棵根节点为 <code>0</code>&nbsp;的 <strong>家族树</strong>&nbsp;，总共包含 <code>n</code>&nbsp;个节点，节点编号为 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;。给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组 <code>parents</code>&nbsp;，其中&nbsp;<code>parents[i]</code>&nbsp;是节点 <code>i</code>&nbsp;的父节点。由于节点 <code>0</code>&nbsp;是 <strong>根</strong>&nbsp;，所以&nbsp;<code>parents[0] == -1</code>&nbsp;。</p>

<p>总共有&nbsp;<code>10<sup>5</sup></code>&nbsp;个基因值，每个基因值都用 <strong>闭区间</strong>&nbsp;<code>[1, 10<sup>5</sup>]</code>&nbsp;中的一个整数表示。给你一个下标从&nbsp;<strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;是节点 <code>i</code>&nbsp;的基因值，且基因值 <strong>互不相同</strong>&nbsp;。</p>

<p>请你返回一个数组<em>&nbsp;</em><code>ans</code>&nbsp;，长度为&nbsp;<code>n</code>&nbsp;，其中&nbsp;<code>ans[i]</code>&nbsp;是以节点&nbsp;<code>i</code>&nbsp;为根的子树内 <b>缺失</b>&nbsp;的&nbsp;<strong>最小</strong>&nbsp;基因值。</p>

<p>节点 <code>x</code>&nbsp;为根的 <strong>子树&nbsp;</strong>包含节点 <code>x</code>&nbsp;和它所有的 <strong>后代</strong>&nbsp;节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2003.Smallest%20Missing%20Genetic%20Value%20in%20Each%20Subtree/images/case-1.png" style="width: 204px; height: 167px;"></p>

<pre><b>输入：</b>parents = [-1,0,0,2], nums = [1,2,3,4]
<b>输出：</b>[5,1,1,1]
<b>解释：</b>每个子树答案计算结果如下：
- 0：子树包含节点 [0,1,2,3] ，基因值分别为 [1,2,3,4] 。5 是缺失的最小基因值。
- 1：子树只包含节点 1 ，基因值为 2 。1 是缺失的最小基因值。
- 2：子树包含节点 [2,3] ，基因值分别为 [3,4] 。1 是缺失的最小基因值。
- 3：子树只包含节点 3 ，基因值为 4 。1是缺失的最小基因值。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2003.Smallest%20Missing%20Genetic%20Value%20in%20Each%20Subtree/images/case-2.png" style="width: 247px; height: 168px;"></p>

<pre><b>输入：</b>parents = [-1,0,1,0,3,3], nums = [5,4,6,2,1,3]
<b>输出：</b>[7,1,1,4,2,1]
<b>解释：</b>每个子树答案计算结果如下：
- 0：子树内包含节点 [0,1,2,3,4,5] ，基因值分别为 [5,4,6,2,1,3] 。7 是缺失的最小基因值。
- 1：子树内包含节点 [1,2] ，基因值分别为 [4,6] 。 1 是缺失的最小基因值。
- 2：子树内只包含节点 2 ，基因值为 6 。1 是缺失的最小基因值。
- 3：子树内包含节点 [3,4,5] ，基因值分别为 [2,1,3] 。4 是缺失的最小基因值。
- 4：子树内只包含节点 4 ，基因值为 1 。2 是缺失的最小基因值。
- 5：子树内只包含节点 5 ，基因值为 3 。1 是缺失的最小基因值。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>parents = [-1,2,3,0,2,4,1], nums = [2,3,4,5,6,7,8]
<b>输出：</b>[1,1,1,1,1,1,1]
<b>解释：</b>所有子树都缺失基因值 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == parents.length == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li>对于&nbsp;<code>i != 0</code>&nbsp;，满足&nbsp;<code>0 &lt;= parents[i] &lt;= n - 1</code></li>
	<li><code>parents[0] == -1</code></li>
	<li><code>parents</code>&nbsp;表示一棵合法的树。</li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code>&nbsp;互不相同。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
