# [1850. Minimum Adjacent Swaps to Reach the Kth Smallest Number](https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number)

[中文文档](/solution/1800-1899/1850.Minimum%20Adjacent%20Swaps%20to%20Reach%20the%20Kth%20Smallest%20Number/README.md)

## Description

<p>You are given a string <code>num</code>, representing a large integer, and an integer <code>k</code>.</p>

<p>We call some integer <strong>wonderful</strong> if it is a <strong>permutation</strong> of the digits in <code>num</code> and is <strong>greater in value</strong> than <code>num</code>. There can be many wonderful integers. However, we only care about the <strong>smallest-valued</strong> ones.</p>

<ul>
	<li>For example, when <code>num = &quot;5489355142&quot;</code>:

    <ul>
    	<li>The 1<sup>st</sup> smallest wonderful integer is <code>&quot;5489355214&quot;</code>.</li>
    	<li>The 2<sup>nd</sup> smallest wonderful integer is <code>&quot;5489355241&quot;</code>.</li>
    	<li>The 3<sup>rd</sup> smallest wonderful integer is <code>&quot;5489355412&quot;</code>.</li>
    	<li>The 4<sup>th</sup> smallest wonderful integer is <code>&quot;5489355421&quot;</code>.</li>
    </ul>
    </li>

</ul>

<p>Return <em>the <strong>minimum number of adjacent digit swaps</strong> that needs to be applied to </em><code>num</code><em> to reach the </em><code>k<sup>th</sup></code><em><strong> smallest wonderful</strong> integer</em>.</p>

<p>The tests are generated in such a way that <code>k<sup>th</sup></code>&nbsp;smallest wonderful integer exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;5489355142&quot;, k = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> The 4<sup>th</sup> smallest wonderful number is &quot;5489355421&quot;. To get this number:
- Swap index 7 with index 8: &quot;5489355<u>14</u>2&quot; -&gt; &quot;5489355<u>41</u>2&quot;
- Swap index 8 with index 9: &quot;54893554<u>12</u>&quot; -&gt; &quot;54893554<u>21</u>&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;11112&quot;, k = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong> The 4<sup>th</sup> smallest wonderful number is &quot;21111&quot;. To get this number:
- Swap index 3 with index 4: &quot;111<u>12</u>&quot; -&gt; &quot;111<u>21</u>&quot;
- Swap index 2 with index 3: &quot;11<u>12</u>1&quot; -&gt; &quot;11<u>21</u>1&quot;
- Swap index 1 with index 2: &quot;1<u>12</u>11&quot; -&gt; &quot;1<u>21</u>11&quot;
- Swap index 0 with index 1: &quot;<u>12</u>111&quot; -&gt; &quot;<u>21</u>111&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;00123&quot;, k = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> The 1<sup>st</sup> smallest wonderful number is &quot;00132&quot;. To get this number:
- Swap index 3 with index 4: &quot;001<u>23</u>&quot; -&gt; &quot;001<u>32</u>&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= num.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>num</code> only consists of digits.</li>
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
