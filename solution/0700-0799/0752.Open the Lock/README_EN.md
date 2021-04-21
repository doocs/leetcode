# [752. Open the Lock](https://leetcode.com/problems/open-the-lock)

[中文文档](/solution/0700-0799/0752.Open%20the%20Lock/README.md)

## Description

<p>You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: <code>&#39;0&#39;, &#39;1&#39;, &#39;2&#39;, &#39;3&#39;, &#39;4&#39;, &#39;5&#39;, &#39;6&#39;, &#39;7&#39;, &#39;8&#39;, &#39;9&#39;</code>. The wheels can rotate freely and wrap around: for example we can turn <code>&#39;9&#39;</code> to be <code>&#39;0&#39;</code>, or <code>&#39;0&#39;</code> to be <code>&#39;9&#39;</code>. Each move consists of turning one wheel one slot.</p>

<p>The lock initially starts at <code>&#39;0000&#39;</code>, a string representing the state of the 4 wheels.</p>

<p>You are given a list of <code>deadends</code> dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.</p>

<p>Given a <code>target</code> representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;0201&quot;,&quot;0101&quot;,&quot;0102&quot;,&quot;1212&quot;,&quot;2002&quot;], target = &quot;0202&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong>
A sequence of valid moves would be &quot;0000&quot; -&gt; &quot;1000&quot; -&gt; &quot;1100&quot; -&gt; &quot;1200&quot; -&gt; &quot;1201&quot; -&gt; &quot;1202&quot; -&gt; &quot;0202&quot;.
Note that a sequence like &quot;0000&quot; -&gt; &quot;0001&quot; -&gt; &quot;0002&quot; -&gt; &quot;0102&quot; -&gt; &quot;0202&quot; would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end &quot;0102&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;8888&quot;], target = &quot;0009&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can turn the last wheel in reverse to move from &quot;0000&quot; -&gt; &quot;0009&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;8887&quot;,&quot;8889&quot;,&quot;8878&quot;,&quot;8898&quot;,&quot;8788&quot;,&quot;8988&quot;,&quot;7888&quot;,&quot;9888&quot;], target = &quot;8888&quot;
<strong>Output:</strong> -1
Explanation:
We can&#39;t reach the target without getting stuck.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;0000&quot;], target = &quot;8888&quot;
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;deadends.length &lt;= 500</code></li>
	<li><code><font face="monospace">deadends[i].length == 4</font></code></li>
	<li><code><font face="monospace">target.length == 4</font></code></li>
	<li>target <strong>will not be</strong> in the list <code>deadends</code>.</li>
	<li><code>target</code> and <code>deadends[i]</code> consist of digits only.</li>
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
