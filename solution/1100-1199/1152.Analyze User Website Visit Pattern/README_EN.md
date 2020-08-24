# [1152. Analyze User Website Visit Pattern](https://leetcode.com/problems/analyze-user-website-visit-pattern)

[中文文档](/solution/1100-1199/1152.Analyze%20User%20Website%20Visit%20Pattern/README.md)

## Description
<p>We are given some website visits: the user with name <code>username[i]</code> visited the website <code>website[i]</code> at time <code>timestamp[i]</code>.</p>

<p>A <em>3-sequence</em> is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)</p>

<p>Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.</p>

<p> </p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>username = <span>["joe","joe","joe","james","james","james","james","mary","mary","mary"]</span>, timestamp = <span id="example-input-1-2">[1,2,3,4,5,6,7,8,9,10]</span>, website = <span id="example-input-1-3">["home","about","career","home","cart","maps","home","home","about","career"]</span>
<strong>Output: </strong><span id="example-output-1">["home","about","career"]</span>
<strong>Explanation: </strong>
The tuples in this example are:
["joe", 1, "home"]
["joe", 2, "about"]
["joe", 3, "career"]
["james", 4, "home"]
["james", 5, "cart"]
["james", 6, "maps"]
["james", 7, "home"]
["mary", 8, "home"]
["mary", 9, "about"]
["mary", 10, "career"]
The 3-sequence ("home", "about", "career") was visited at least once by <strong>2</strong> users.
The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
</pre>

<p> </p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>3 <= N = username.length = timestamp.length = website.length <= 50</code></li>
	<li><code>1 <= username[i].length <= 10</code></li>
	<li><code>0 <= timestamp[i] <= 10^9</code></li>
	<li><code>1 <= website[i].length <= 10</code></li>
	<li>Both <code>username[i]</code> and <code>website[i]</code> contain only lowercase characters.</li>
	<li>It is guaranteed that there is at least one user who visited at least 3 websites.</li>
	<li>No user visits two websites at the same time.</li>
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