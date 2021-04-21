# [1529. Bulb Switcher IV](https://leetcode.com/problems/bulb-switcher-iv)

[中文文档](/solution/1500-1599/1529.Bulb%20Switcher%20IV/README.md)

## Description

<p>There is a room with <code>n</code>&nbsp;bulbs, numbered from <code>0</code> to&nbsp;<code>n-1</code>,&nbsp;arranged in a row from left to right. Initially all the bulbs are <strong>turned off</strong>.</p>

<p>Your task is to obtain the configuration represented by <code>target</code> where&nbsp;<code>target[i]</code> is &#39;1&#39; if the i-th bulb is turned on and is &#39;0&#39; if it is turned off.</p>

<p>You have a switch&nbsp;to flip the state of the bulb,&nbsp;a flip operation is defined as follows:</p>

<ul>
	<li>Choose <strong>any</strong> bulb (index&nbsp;<code>i</code>)&nbsp;of your current configuration.</li>
	<li>Flip each bulb from index&nbsp;<code>i</code> to&nbsp;<code>n-1</code>.</li>
</ul>

<p>When any bulb is flipped it means that if it is 0 it changes to 1 and if it is 1 it changes to 0.</p>

<p>Return the <strong>minimum</strong> number of flips required to form <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;10111&quot;
<strong>Output:</strong> 3
<strong>Explanation: </strong>Initial configuration &quot;00000&quot;.
flip from the third bulb:  &quot;00000&quot; -&gt; &quot;00111&quot;
flip from the first bulb:  &quot;00111&quot; -&gt; &quot;11000&quot;
flip from the second bulb:  &quot;11000&quot; -&gt; &quot;10111&quot;
We need at least 3 flip operations to form target.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;101&quot;
<strong>Output:</strong> 3
<strong>Explanation: </strong>&quot;000&quot; -&gt; &quot;111&quot; -&gt; &quot;100&quot; -&gt; &quot;101&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;00000&quot;
<strong>Output:</strong> 0
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;001011101&quot;
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 10^5</code></li>
	<li><code>target[i] == &#39;0&#39;</code>&nbsp;or <code>target[i] == &#39;1&#39;</code></li>
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
