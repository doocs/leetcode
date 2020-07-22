# [851. 喧闹和富有](https://leetcode-cn.com/problems/loud-and-rich)

## 题目描述
<!-- 这里写题目描述 -->
<p>在一组 N 个人（编号为&nbsp;<code>0, 1, 2, ..., N-1</code>）中，每个人都有不同数目的钱，以及不同程度的安静（quietness）。</p>

<p>为了方便起见，我们将编号为&nbsp;<code>x</code>&nbsp;的人简称为 &quot;person&nbsp;<code>x</code>&nbsp;&quot;。</p>

<p>如果能够肯定 person&nbsp;<code>x</code>&nbsp;比 person&nbsp;<code>y</code>&nbsp;更有钱的话，我们会说&nbsp;<code>richer[i] = [x, y]</code>&nbsp;。注意&nbsp;<code>richer</code>&nbsp;可能只是有效观察的一个子集。</p>

<p>另外，如果 person&nbsp;<code>x</code>&nbsp;的安静程度为&nbsp;<code>q</code>&nbsp;，我们会说&nbsp;<code>quiet[x] = q</code>&nbsp;。</p>

<p>现在，返回答案&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[x] = y</code>&nbsp;的前提是，在所有拥有的钱不少于&nbsp;person&nbsp;<code>x</code>&nbsp;的人中，person&nbsp;<code>y</code>&nbsp;是最安静的人（也就是安静值&nbsp;<code>quiet[y]</code>&nbsp;最小的人）。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
<strong>输出：</strong>[5,5,2,5,4,5,6,7]
<strong>解释： </strong>
answer[0] = 5，
person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
但是目前还不清楚他是否比 person 0 更有钱。

answer[7] = 7，
在所有拥有的钱肯定不少于 person 7 的人中(这可能包括 person 3，4，5，6 以及 7)，
最安静(有较低安静值 quiet[x])的人是 person 7。

其他的答案也可以用类似的推理来解释。
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= quiet.length = N &lt;= 500</code></li>
	<li><code>0 &lt;= quiet[i] &lt; N</code>，所有&nbsp;<code>quiet[i]</code>&nbsp;都不相同。</li>
	<li><code>0 &lt;= richer.length &lt;= N * (N-1) / 2</code></li>
	<li><code>0 &lt;= richer[i][j] &lt; N</code></li>
	<li><code>richer[i][0] != richer[i][1]</code></li>
	<li><code>richer[i]</code>&nbsp;都是不同的。</li>
	<li>对&nbsp;<code>richer</code>&nbsp;的观察在逻辑上是一致的。</li>
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