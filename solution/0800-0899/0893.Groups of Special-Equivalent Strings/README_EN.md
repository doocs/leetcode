# [893. Groups of Special-Equivalent Strings](https://leetcode.com/problems/groups-of-special-equivalent-strings)

[中文文档](/solution/0800-0899/0893.Groups%20of%20Special-Equivalent%20Strings/README.md)

## Description

<p>You are given an array of strings of the same length <code>words</code>.</p>

<p>In one <strong>move</strong>, you can swap any two even indexed characters or any two odd indexed characters of a string <code>words[i]</code>.</p>

<p>Two strings <code>words[i]</code> and <code>words[j]</code> are <strong>special-equivalent</strong> if after any number of moves, <code>words[i] == words[j]</code>.</p>

<ul>
	<li>For example, <code>words[i] = &quot;zzxy&quot;</code> and <code>words[j] = &quot;xyzz&quot;</code> are <strong>special-equivalent</strong> because we may make the moves <code>&quot;zzxy&quot; -&gt; &quot;xzzy&quot; -&gt; &quot;xyzz&quot;</code>.</li>
</ul>

<p>A <strong>group of special-equivalent strings</strong> from <code>words</code> is a non-empty subset of words such that:</p>

<ul>
	<li>Every pair of strings in the group are special equivalent, and</li>
	<li>The group is the largest size possible (i.e., there is not a string <code>words[i]</code> not in the group such that <code>words[i]</code> is special-equivalent to every string in the group).</li>
</ul>

<p>Return <em>the number of <strong>groups of special-equivalent strings</strong> from </em><code>words</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;cdab&quot;,&quot;cbad&quot;,&quot;xyzz&quot;,&quot;zzxy&quot;,&quot;zzyx&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
One group is [&quot;abcd&quot;, &quot;cdab&quot;, &quot;cbad&quot;], since they are all pairwise special equivalent, and none of the other strings is all pairwise special equivalent to these.
The other two groups are [&quot;xyzz&quot;, &quot;zzxy&quot;] and [&quot;zzyx&quot;].
Note that in particular, &quot;zzxy&quot; is not special equivalent to &quot;zzyx&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abc&quot;,&quot;acb&quot;,&quot;bac&quot;,&quot;bca&quot;,&quot;cab&quot;,&quot;cba&quot;]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code> consist of lowercase English letters.</li>
	<li>All the strings are of the same length.</li>
</ul>

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
