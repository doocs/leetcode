# [1313. 解压缩编码列表](https://leetcode-cn.com/problems/decompress-run-length-encoded-list)

[English Version](/solution/1300-1399/1313.Decompress%20Run-Length%20Encoded%20List/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给你一个以行程长度编码压缩的整数列表&nbsp;<code>nums</code>&nbsp;。</p>

<p>考虑每对相邻的两个元素 <code>freq, val] = [nums[2*i], nums[2*i+1]]</code>&nbsp;（其中&nbsp;<code>i &gt;= 0</code>&nbsp;），每一对都表示解压后子列表中有 <code>freq</code>&nbsp;个值为&nbsp;<code>val</code>&nbsp;的元素，你需要从左到右连接所有子列表以生成解压后的列表。</p>

<p>请你返回解压后的列表。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>[2,4,4,4]
<strong>解释：</strong>第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,2,3]
<strong>输出：</strong>[1,3,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length % 2 == 0</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
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