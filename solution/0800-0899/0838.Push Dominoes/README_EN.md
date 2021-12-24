# [838. Push Dominoes](https://leetcode.com/problems/push-dominoes)

[中文文档](/solution/0800-0899/0838.Push%20Dominoes/README.md)

## Description

<p>There are<font face="monospace">&nbsp;<code>N</code></font> dominoes in a line, and we place each domino vertically upright.</p>

<p>In the beginning, we simultaneously push&nbsp;some of the dominoes either to the left or to the right.</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0838.Push%20Dominoes/images/domino.png" style="height: 160px;" /></p>

<p>After each second, each domino that is falling to the left pushes the adjacent domino on the left.</p>

<p>Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.</p>

<p>When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.</p>

<p>For the purposes of this question, we will consider that a falling domino&nbsp;expends no additional force to a falling or already fallen domino.</p>

<p>Given a string &quot;S&quot; representing the initial state.&nbsp;<code>S[i] = &#39;L&#39;</code>, if the i-th domino has been pushed to the left; <code>S[i] = &#39;R&#39;</code>, if the i-th domino has been pushed to the right; <code>S[i] = &#39;.&#39;</code>,&nbsp;if the <code>i</code>-th domino has not been pushed.</p>

<p>Return a string representing the final state.&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>&quot;.L.R...LR..L..&quot;

<strong>Output: </strong>&quot;LL.RR.LLRRLL..&quot;

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>&quot;RR.L&quot;

<strong>Output: </strong>&quot;RR.L&quot;

<strong>Explanation: </strong>The first domino expends no additional force on the second domino.

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= N&nbsp;&lt;= 10^5</code></li>
	<li>String&nbsp;<code>dominoes</code> contains only&nbsp;<code>&#39;L</code>&#39;, <code>&#39;R&#39;</code> and <code>&#39;.&#39;</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
