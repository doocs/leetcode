# [710. Random Pick with Blacklist](https://leetcode.com/problems/random-pick-with-blacklist)

[中文文档](/solution/0700-0799/0710.Random%20Pick%20with%20Blacklist/README.md)

## Description

<p>Given a blacklist&nbsp;<code>B</code> containing unique integers&nbsp;from <code>[0, N)</code>, write a function to return a uniform random integer from <code>[0, N)</code> which is <strong>NOT</strong>&nbsp;in <code>B</code>.</p>

<p>Optimize it such that it minimizes the call to system&rsquo;s <code>Math.random()</code>.</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 1000000000</code></li>
	<li><code>0 &lt;= B.length &lt; min(100000, N)</code></li>
	<li><code>[0, N)</code>&nbsp;does NOT include N. See <a href="https://en.wikipedia.org/wiki/Interval_(mathematics)" target="_blank">interval notation</a>.</li>
</ol>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: 

</strong><span id="example-input-1-1">[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]

</span><span id="example-input-1-2">[[1,[]],[],[],[]]</span>

<strong>Output: </strong><span id="example-output-1">[null,0,0,0]</span>

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: 

</strong><span id="example-input-2-1">[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]

</span><span id="example-input-2-2">[[2,[]],[],[],[]]</span>

<strong>Output: </strong><span id="example-output-2">[null,1,1,1]</span>

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: 

</strong><span id="example-input-3-1">[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]

</span><span id="example-input-3-2">[[3,[1]],[],[],[]]</span>

<strong>Output: </strong><span id="example-output-3">[null,0,0,2]</span>

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input: 

</strong><span id="example-input-4-1">[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]

</span><span id="example-input-4-2">[[4,[2]],[],[],[]]</span>

<strong>Output: </strong><span id="example-output-4">[null,1,3,1]</span>

</pre>

<p><strong>Explanation of Input Syntax:</strong></p>

<p>The input is two lists:&nbsp;the subroutines called&nbsp;and their&nbsp;arguments.&nbsp;<code>Solution</code>&#39;s&nbsp;constructor has two arguments,&nbsp;<code>N</code> and the blacklist <code>B</code>. <code>pick</code> has no arguments.&nbsp;Arguments&nbsp;are&nbsp;always wrapped with a list, even if there aren&#39;t any.</p>

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
