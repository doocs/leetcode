# [1203. 项目管理](https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies)

[English Version](/solution/1200-1299/1203.Sort%20Items%20by%20Groups%20Respecting%20Dependencies/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>公司共有&nbsp;<code>n</code>&nbsp;个项目和 &nbsp;<code>m</code>&nbsp;个小组，每个项目要不没有归属，要不就由其中的一个小组负责。</p>

<p>我们用&nbsp;<code>group[i]</code>&nbsp;代表第&nbsp;<code>i</code>&nbsp;个项目所属的小组，如果这个项目目前无人接手，那么&nbsp;<code>group[i]</code> 就等于&nbsp;<code>-1</code>。（项目和小组都是从零开始编号的）</p>

<p>请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：</p>

<ul>
	<li>同一小组的项目，排序后在列表中彼此相邻。</li>
	<li>项目之间存在一定的依赖关系，我们用一个列表 <code>beforeItems</code>&nbsp;来表示，其中&nbsp;<code>beforeItems[i]</code>&nbsp;表示在进行第&nbsp;<code>i</code>&nbsp;个项目前（位于第 <code>i</code>&nbsp;个项目左侧）应该完成的所有项目。</li>
</ul>

<p><strong>结果要求：</strong></p>

<p>如果存在多个解决方案，只需要返回其中任意一个即可。</p>

<p>如果没有合适的解决方案，就请返回一个 <strong>空列表</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/09/22/1359_ex1.png" style="height: 181px; width: 191px;"></strong></p>

<pre><strong>输入：</strong>n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
<strong>输出：</strong>[6,3,4,1,5,2,0,7]
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
<strong>输出：</strong>[]
<strong>解释：</strong>与示例 1 大致相同，但是在排序后的列表中，4 必须放在 6 的前面。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= n &lt;= 3*10^4</code></li>
	<li><code>group.length == beforeItems.length == n</code></li>
	<li><code>-1 &lt;= group[i] &lt;= m-1</code></li>
	<li><code>0 &lt;= beforeItems[i].length &lt;= n-1</code></li>
	<li><code>0 &lt;= beforeItems[i][j] &lt;= n-1</code></li>
	<li><code>i != beforeItems[i][j]</code></li>
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