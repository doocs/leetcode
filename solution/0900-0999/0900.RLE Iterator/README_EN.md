# [900. RLE Iterator](https://leetcode.com/problems/rle-iterator)

[中文文档](/solution/0900-0999/0900.RLE%20Iterator/README.md)

## Description

<p>Write an iterator that iterates through a run-length encoded sequence.</p>

<p>The iterator is initialized by <code>RLEIterator(int[] A)</code>, where <code>A</code> is a run-length encoding of some&nbsp;sequence.&nbsp; More specifically,&nbsp;for all even <code>i</code>,&nbsp;<code>A[i]</code> tells us the number of times that the non-negative integer value <code>A[i+1]</code> is repeated in the sequence.</p>

<p>The iterator supports one function:&nbsp;<code>next(int n)</code>, which exhausts the next <code>n</code> elements&nbsp;(<code>n &gt;= 1</code>) and returns the last element exhausted in this way.&nbsp; If there is no element left to exhaust, <code>next</code>&nbsp;returns <code>-1</code> instead.</p>

<p>For example, we start with <code>A = [3,8,0,9,2,5]</code>, which is a run-length encoding of the sequence <code>[8,8,8,5,5]</code>.&nbsp; This is because the sequence can be read as&nbsp;&quot;three eights, zero nines, two fives&quot;.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[&quot;RLEIterator&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;]</span>, <span id="example-input-1-2">[[[3,8,0,9,2,5]],[2],[1],[1],[2]]</span>

<strong>Output: </strong><span id="example-output-1">[null,8,8,5,-1]</span>

<strong>Explanation: </strong>

RLEIterator is initialized with RLEIterator([3,8,0,9,2,5]).

This maps to the sequence [8,8,8,5,5].

RLEIterator.next is then called 4 times:



.next(2) exhausts 2 terms of the sequence, returning 8.  The remaining sequence is now [8, 5, 5].



.next(1) exhausts 1 term of the sequence, returning 8.  The remaining sequence is now [5, 5].



.next(1) exhausts 1 term of the sequence, returning 5.  The remaining sequence is now [5].



.next(2) exhausts 2 terms, returning -1.  This is because the first term exhausted was 5,

but the second term did not exist.  Since the last term exhausted does not exist, we return -1.



</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= A.length &lt;= 1000</code></li>
	<li><code>A.length</code>&nbsp;is an even integer.</li>
	<li><code>0 &lt;= A[i] &lt;= 10^9</code></li>
	<li>There are at most <code>1000</code> calls to <code>RLEIterator.next(int n)</code> per test case.</li>
	<li>Each call to&nbsp;<code>RLEIterator.next(int n)</code>&nbsp;will have <code>1 &lt;= n &lt;= 10^9</code>.</li>
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
