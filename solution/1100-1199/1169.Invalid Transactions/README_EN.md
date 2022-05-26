# [1169. Invalid Transactions](https://leetcode.com/problems/invalid-transactions)

[中文文档](/solution/1100-1199/1169.Invalid%20Transactions/README.md)

## Description

<p>A transaction is <em>possibly invalid</em> if:</p>

<ul>
	<li>the amount exceeds $1000, or;</li>
	<li>if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.</li>
</ul>

<p>Each transaction string <code>transactions[i]</code>&nbsp;consists of&nbsp;comma separated values representing&nbsp;the name, time (in minutes), amount, and city of the transaction.</p>

<p>Given a list of <code>transactions</code>,&nbsp;return a list of transactions that are possibly invalid.&nbsp; You may return the answer in any order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> transactions = [&quot;alice,20,800,mtv&quot;,&quot;alice,50,100,beijing&quot;]
<strong>Output:</strong> [&quot;alice,20,800,mtv&quot;,&quot;alice,50,100,beijing&quot;]
<strong>Explanation:</strong> The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> transactions = [&quot;alice,20,800,mtv&quot;,&quot;alice,50,1200,mtv&quot;]
<strong>Output:</strong> [&quot;alice,50,1200,mtv&quot;]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> transactions = [&quot;alice,20,800,mtv&quot;,&quot;bob,50,1200,mtv&quot;]
<strong>Output:</strong> [&quot;bob,50,1200,mtv&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>transactions.length &lt;= 1000</code></li>
	<li>Each <code>transactions[i]</code> takes the form <code>&quot;{name},{time},{amount},{city}&quot;</code></li>
	<li>Each <code>{name}</code> and <code>{city}</code>&nbsp;consist of&nbsp;lowercase English letters, and have lengths between <code>1</code> and <code>10</code>.</li>
	<li>Each <code>{time}</code> consist of&nbsp;digits, and represent an integer between <code>0</code> and <code>1000</code>.</li>
	<li>Each <code>{amount}</code>&nbsp;consist of&nbsp;digits, and represent an integer between <code>0</code> and <code>2000</code>.</li>
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
