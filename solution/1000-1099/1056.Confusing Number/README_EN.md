# [1056. Confusing Number](https://leetcode.com/problems/confusing-number)

[中文文档](/solution/1000-1099/1056.Confusing%20Number/README.md)

## Description

<p>A <strong>confusing number</strong> is a number that when rotated <code>180</code> degrees becomes a different number with <strong>each digit valid</strong>.</p>

<p>We can rotate digits of a number by <code>180</code> degrees to form new digits.</p>

<ul>
	<li>When <code>0</code>, <code>1</code>, <code>6</code>, <code>8</code>, and <code>9</code> are rotated <code>180</code> degrees, they become <code>0</code>, <code>1</code>, <code>9</code>, <code>8</code>, and <code>6</code> respectively.</li>
	<li>When <code>2</code>, <code>3</code>, <code>4</code>, <code>5</code>, and <code>7</code> are rotated <code>180</code> degrees, they become <strong>invalid</strong>.</li>
</ul>

<p>Note that after rotating a number, we can ignore leading zeros.</p>

<ul>
	<li>For example, after rotating <code>8000</code>, we have <code>0008</code> which is considered as just <code>8</code>.</li>
</ul>

<p>Given an integer <code>n</code>, return <code>true</code><em> if it is a <strong>confusing number</strong>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_1.png" style="width: 281px; height: 121px;" />
<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> true
<strong>Explanation:</strong> We get 9 after rotating 6, 9 is a valid number, and 9 != 6.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_2.png" style="width: 312px; height: 121px;" />
<pre>
<strong>Input:</strong> n = 89
<strong>Output:</strong> true
<strong>Explanation:</strong> We get 68 after rotating 89, 68 is a valid number and 68 != 89.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_3.png" style="width: 301px; height: 121px;" />
<pre>
<strong>Input:</strong> n = 11
<strong>Output:</strong> false
<strong>Explanation:</strong> We get 11 after rotating 11, 11 is a valid number but the value remains the same, thus 11 is not a confusing number
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
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
