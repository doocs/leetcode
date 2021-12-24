# [936. Stamping The Sequence](https://leetcode.com/problems/stamping-the-sequence)

[中文文档](/solution/0900-0999/0936.Stamping%20The%20Sequence/README.md)

## Description

<p>You want to form a <code>target</code>&nbsp;string of <strong>lowercase letters</strong>.</p>

<p>At the beginning, your sequence is <code>target.length</code>&nbsp;<code>&#39;?&#39;</code> marks.&nbsp; You also have a <code>stamp</code>&nbsp;of lowercase letters.</p>

<p>On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.&nbsp; You can make up to <code>10 * target.length</code> turns.</p>

<p>For example, if the initial sequence is <font face="monospace">&quot;?????&quot;</font>, and your stamp is <code>&quot;abc&quot;</code>,&nbsp; then you may make <font face="monospace">&quot;abc??&quot;, &quot;?abc?&quot;, &quot;??abc&quot;&nbsp;</font>in the first turn.&nbsp; (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)</p>

<p>If the sequence is possible to stamp, then return an array of&nbsp;the index of the left-most letter being stamped at each turn.&nbsp; If the sequence is not possible to stamp, return an empty array.</p>

<p>For example, if the sequence is <font face="monospace">&quot;ababc&quot;</font>, and the stamp is <code>&quot;abc&quot;</code>, then we could return the answer <code>[0, 2]</code>, corresponding to the moves <font face="monospace">&quot;?????&quot; -&gt; &quot;abc??&quot; -&gt; &quot;ababc&quot;</font>.</p>

<p>Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within <code>10 * target.length</code>&nbsp;moves.&nbsp; Any answers specifying more than this number of moves&nbsp;will not be accepted.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>stamp = <span id="example-input-1-1">&quot;abc&quot;</span>, target = <span id="example-input-1-2">&quot;ababc&quot;</span>

<strong>Output: </strong><span id="example-output-1">[0,2]</span>

([1,0,2] would also be accepted as an answer, as well as some other answers.)

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>stamp = <span id="example-input-2-1">&quot;</span><span id="example-input-2-2">abca</span><span>&quot;</span>, target = <span id="example-input-2-2">&quot;</span><span>aabcaca&quot;</span>

<strong>Output: </strong><span id="example-output-2">[3,0,1]</span>

</pre>

<div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

</div>

</div>

<ol>
	<li><code>1 &lt;= stamp.length &lt;= target.length &lt;= 1000</code></li>
	<li><code>stamp</code> and <code>target</code> only contain lowercase letters.</li>
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
