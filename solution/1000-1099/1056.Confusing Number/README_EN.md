# [1056. Confusing Number](https://leetcode.com/problems/confusing-number)

[中文文档](/solution/1000-1099/1056.Confusing%20Number/README.md)

## Description

<p>Given a number <code>N</code>, return <code>true</code> if and only if it is a <em>confusing number</em>, which satisfies the following condition:</p>

<p>We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A <em>confusing number</em> is a number that when rotated 180 degrees becomes a <strong>different</strong> number with each digit valid.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_1.png" style="width: 180px; height: 90px;" /></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">6</span>

<strong>Output: </strong><span id="example-output-1">true</span>

<strong>Explanation: </strong>

We get <code>9</code> after rotating <code>6</code>, <code>9</code> is a valid number and <code>9!=6</code>.

</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_2.png" style="width: 180px; height: 90px;" /></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">89</span>

<strong>Output: </strong><span id="example-output-2">true</span>

<strong>Explanation: </strong>

We get <code>68</code> after rotating <code>89</code>, <code>86</code> is a valid number and <code>86!=89</code>.

</pre>

<p><strong>Example 3:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_3.png" style="width: 301px; height: 121px;" /></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">11</span>

<strong>Output: </strong><span id="example-output-3">false</span>

<strong>Explanation: </strong>

We get <code>11</code> after rotating <code>11</code>, <code>11</code> is a valid number but the value remains the same, thus <code>11</code> is not a confusing number.

</pre>

<p><strong>Example 4:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_4.png" style="width: 180px; height: 90px;" /></p>

<pre>

<strong>Input: </strong><span id="example-input-4-1">25</span>

<strong>Output: </strong><span id="example-output-4">false</span>

<strong>Explanation: </strong>

We get an invalid number after rotating <code>25</code>.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= N &lt;= 10^9</code></li>
	<li>After the rotation we can ignore leading zeros, for example if after rotation we have <code>0008</code>&nbsp;then this number is considered as just <code>8</code>.</li>
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
