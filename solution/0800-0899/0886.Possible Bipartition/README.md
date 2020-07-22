# [886. 可能的二分法](https://leetcode-cn.com/problems/possible-bipartition)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一组&nbsp;<code>N</code>&nbsp;人（编号为&nbsp;<code>1, 2, ..., N</code>），&nbsp;我们想把每个人分进<strong>任意</strong>大小的两组。</p>

<p>每个人都可能不喜欢其他人，那么他们不应该属于同一组。</p>

<p>形式上，如果 <code>dislikes[i] = [a, b]</code>，表示不允许将编号为 <code>a</code> 和 <code>b</code> 的人归入同一组。</p>

<p>当可以用这种方法将每个人分进两组时，返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>N = 4, dislikes = [[1,2],[1,3],[2,4]]
<strong>输出：</strong>true
<strong>解释：</strong>group1 [1,4], group2 [2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>N = 3, dislikes = [[1,2],[1,3],[2,3]]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 2000</code></li>
	<li><code>0 &lt;= dislikes.length &lt;= 10000</code></li>
	<li><code>1 &lt;= dislikes[i][j] &lt;= N</code></li>
	<li><code>dislikes[i][0] &lt; dislikes[i][1]</code></li>
	<li>对于&nbsp;<code>dislikes[i] == dislikes[j]</code>&nbsp;不存在&nbsp;<code>i != j</code>&nbsp;</li>
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