# [2819. Minimum Relative Loss After Buying Chocolates](https://leetcode.com/problems/minimum-relative-loss-after-buying-chocolates)

[中文文档](/solution/2800-2899/2819.Minimum%20Relative%20Loss%20After%20Buying%20Chocolates/README.md)

## Description

<p>You are given an integer array <code>prices</code>, which shows the chocolate prices and a 2D integer array <code>queries</code>, where <code>queries[i] = [k<sub>i</sub>, m<sub>i</sub>]</code>.</p>

<p>Alice and Bob went to buy some chocolates, and Alice suggested a way to pay for them, and Bob agreed.</p>

<p>The terms for each query are as follows:</p>

<ul>
	<li>If the price of a chocolate is <strong>less than or equal to</strong> <code>k<sub>i</sub></code>, Bob pays for it.</li>
	<li>Otherwise, Bob pays <code>k<sub>i</sub></code> of it, and Alice pays the <strong>rest</strong>.</li>
</ul>

<p>Bob wants to select <strong>exactly</strong> <code>m<sub>i</sub></code> chocolates such that his <strong>relative loss</strong> is <strong>minimized</strong>, more formally, if, in total, Alice has paid <code>a<sub>i</sub></code> and Bob has paid <code>b<sub>i</sub></code>, Bob wants to minimize <code>b<sub>i</sub> - a<sub>i</sub></code>.</p>

<p>Return <em>an integer array</em> <code>ans</code> <em>where</em> <code>ans[i]</code> <em>is Bob&#39;s <strong>minimum relative loss </strong>possible for</em> <code>queries[i]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,9,22,10,19], queries = [[18,4],[5,2]]
<strong>Output:</strong> [34,-21]
<strong>Explanation:</strong> For the 1<sup>st</sup> query Bob selects the chocolates with prices [1,9,10,22]. He pays 1 + 9 + 10 + 18 = 38 and Alice pays 0 + 0 + 0 + 4 = 4. So Bob&#39;s relative loss is 38 - 4 = 34.
For the 2<sup>nd</sup> query Bob selects the chocolates with prices [19,22]. He pays 5 + 5 = 10 and Alice pays 14 + 17 = 31. So Bob&#39;s relative loss is 10 - 31 = -21.
It can be shown that these are the minimum possible relative losses.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,5,4,3,7,11,9], queries = [[5,4],[5,7],[7,3],[4,5]]
<strong>Output:</strong> [4,16,7,1]
<strong>Explanation:</strong> For the 1<sup>st</sup> query Bob selects the chocolates with prices [1,3,9,11]. He pays 1 + 3 + 5 + 5 = 14 and Alice pays 0 + 0 + 4 + 6 = 10. So Bob&#39;s relative loss is 14 - 10 = 4.
For the 2<sup>nd</sup> query Bob has to select all the chocolates. He pays 1 + 5 + 4 + 3 + 5 + 5 + 5 = 28 and Alice pays 0 + 0 + 0 + 0 + 2 + 6 + 4 = 12. So Bob&#39;s relative loss is 28 - 12 = 16.
For the 3<sup>rd</sup> query Bob selects the chocolates with prices [1,3,11] and he pays 1 + 3 + 7 = 11 and Alice pays 0 + 0 + 4 = 4. So Bob&#39;s relative loss is 11 - 4 = 7.
For the 4<sup>th</sup> query Bob selects the chocolates with prices [1,3,7,9,11] and he pays 1 + 3 + 4 + 4 + 4 = 16 and Alice pays 0 + 0 + 3 + 5 + 7 = 15. So Bob&#39;s relative loss is 16 - 15 = 1.
It can be shown that these are the minimum possible relative losses.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [5,6,7], queries = [[10,1],[5,3],[3,3]]
<strong>Output:</strong> [5,12,0]
<strong>Explanation:</strong> For the 1<sup>st</sup> query Bob selects the chocolate with price 5 and he pays 5 and Alice pays 0. So Bob&#39;s relative loss is 5 - 0 = 5.
For the 2<sup>nd</sup> query Bob has to select all the chocolates. He pays 5 + 5 + 5 = 15 and Alice pays 0 + 1 + 2 = 3. So Bob&#39;s relative loss is 15 - 3 = 12.
For the 3<sup>rd</sup> query Bob has to select all the chocolates. He pays 3 + 3 + 3 = 9 and Alice pays 2 + 3 + 4 = 9. So Bob&#39;s relative loss is 9 - 9 = 0.
It can be shown that these are the minimum possible relative losses.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m<sub>i</sub> &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
