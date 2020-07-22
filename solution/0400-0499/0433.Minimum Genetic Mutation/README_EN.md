# [433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation)

## Description
<p>A gene string can be represented by an 8-character long string, with choices from <code>&quot;A&quot;</code>, <code>&quot;C&quot;</code>, <code>&quot;G&quot;</code>, <code>&quot;T&quot;</code>.</p>



<p>Suppose we need to investigate about a mutation (mutation from &quot;start&quot; to &quot;end&quot;), where ONE mutation is defined as ONE single character changed in the gene string.</p>



<p>For example, <code>&quot;AACCGGTT&quot;</code> -&gt; <code>&quot;AACCGGTA&quot;</code> is 1 mutation.</p>



<p>Also, there is a given gene &quot;bank&quot;, which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.</p>



<p>Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from &quot;start&quot; to &quot;end&quot;. If there is no such a mutation, return -1.</p>



<p><b>Note:</b></p>



<ol>

	<li>Starting point is assumed to be valid, so it might not be included in the bank.</li>

	<li>If multiple mutations are needed, all mutations during in the sequence must be valid.</li>

	<li>You may assume start and end string is not the same.</li>

</ol>



<p>&nbsp;</p>



<p><b>Example 1:</b></p>



<pre>

start: &quot;AACCGGTT&quot;

end:   &quot;AACCGGTA&quot;

bank: [&quot;AACCGGTA&quot;]



return: 1

</pre>



<p>&nbsp;</p>



<p><b>Example 2:</b></p>



<pre>

start: &quot;AACCGGTT&quot;

end:   &quot;AAACGGTA&quot;

bank: [&quot;AACCGGTA&quot;, &quot;AACCGCTA&quot;, &quot;AAACGGTA&quot;]



return: 2

</pre>



<p>&nbsp;</p>



<p><b>Example 3:</b></p>



<pre>

start: &quot;AAAAACCC&quot;

end:   &quot;AACCCCCC&quot;

bank: [&quot;AAAACCCC&quot;, &quot;AAACCCCC&quot;, &quot;AACCCCCC&quot;]



return: 3

</pre>



<p>&nbsp;</p>




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