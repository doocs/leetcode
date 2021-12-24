# [1090. 受标签影响的最大值](https://leetcode-cn.com/problems/largest-values-from-labels)

[English Version](/solution/1000-1099/1090.Largest%20Values%20From%20Labels/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有一个项的集合，其中第&nbsp;<code>i</code>&nbsp;项的值为&nbsp;<code>values[i]</code>，标签为&nbsp;<code>labels[i]</code>。</p>

<p>我们从这些项中选出一个子集&nbsp;<code>S</code>，这样一来：</p>

<ul>
	<li><code>|S| &lt;= num_wanted</code></li>
	<li>对于任意的标签 <code>L</code>，子集 <code>S</code> 中标签为 <code>L</code>&nbsp;的项的数目总满足&nbsp;<code>&lt;= use_limit</code>。</li>
</ul>

<p>返回子集&nbsp;<code>S</code>&nbsp;的最大可能的&nbsp;<strong>和</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>values = [5,4,3,2,1], labels = [1,1,2,2,3], <code>num_wanted </code>= 3, use_limit = 1
<strong>输出：</strong>9
<strong>解释：</strong>选出的子集是第一项，第三项和第五项。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>values = [5,4,3,2,1], labels = [1,3,3,3,2], <code>num_wanted </code>= 3, use_limit = 2
<strong>输出：</strong>12
<strong>解释：</strong>选出的子集是第一项，第二项和第三项。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>values = [9,8,8,7,6], labels = [0,0,0,1,1], <code>num_wanted </code>= 3, use_limit = 1
<strong>输出：</strong>16
<strong>解释：</strong>选出的子集是第一项和第四项。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>values = [9,8,8,7,6], labels = [0,0,0,1,1], <code>num_wanted </code>= 3, use_limit = 2
<strong>输出：</strong>24
<strong>解释：</strong>选出的子集是第一项，第二项和第四项。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= values.length == labels.length &lt;= 20000</code></li>
	<li><code>0 &lt;= values[i], labels[i]&nbsp;&lt;= 20000</code></li>
	<li><code>1 &lt;= num_wanted, use_limit&nbsp;&lt;= values.length</code></li>
</ol>

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
