# [458. Poor Pigs](https://leetcode.com/problems/poor-pigs)

[中文文档](/solution/0400-0499/0458.Poor%20Pigs/README.md)

## Description

<p>There are <code>buckets</code> buckets of liquid, where <strong>exactly one</strong> of the buckets is poisonous. To figure out which one is poisonous, you feed some number of (poor) pigs the liquid to see whether they will die or not. Unfortunately, you only have <code>minutesToTest</code> minutes to determine which bucket is poisonous.</p>

<p>You can feed the pigs according to these steps:</p>

<ol>
	<li>Choose some live pigs to feed.</li>
	<li>For each pig, choose which buckets to feed it. The pig will consume all the chosen buckets simultaneously and will take no time. Each pig can feed from any number of buckets, and each bucket can be fed from by any number of pigs.</li>
	<li>Wait for <code>minutesToDie</code> minutes. You may <strong>not</strong> feed any other pigs during this time.</li>
	<li>After <code>minutesToDie</code> minutes have passed, any pigs that have been fed the poisonous bucket will die, and all others will survive.</li>
	<li>Repeat this process until you run out of time.</li>
</ol>

<p>Given <code>buckets</code>, <code>minutesToDie</code>, and <code>minutesToTest</code>, return <em>the <strong>minimum</strong> number of pigs needed to figure out which bucket is poisonous within the allotted time</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> buckets = 4, minutesToDie = 15, minutesToTest = 15
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can determine the poisonous bucket as follows:
At time 0, feed the first pig buckets 1 and 2, and feed the second pig buckets 2 and 3.
At time 15, there are 4 possible outcomes:
- If only the first pig dies, then bucket 1 must be poisonous.
- If only the second pig dies, then bucket 3 must be poisonous.
- If both pigs die, then bucket 2 must be poisonous.
- If neither pig dies, then bucket 4 must be poisonous.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> buckets = 4, minutesToDie = 15, minutesToTest = 30
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can determine the poisonous bucket as follows:
At time 0, feed the first pig bucket 1, and feed the second pig bucket 2.
At time 15, there are 2 possible outcomes:
- If either pig dies, then the poisonous bucket is the one it was fed.
- If neither pig dies, then feed the first pig bucket 3, and feed the second pig bucket 4.
At time 30, one of the two pigs must die, and the poisonous bucket is the one it was fed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= buckets &lt;= 1000</code></li>
	<li><code>1 &lt;=&nbsp;minutesToDie &lt;=&nbsp;minutesToTest &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def poorPigs(self, buckets: int, minutesToDie: int, minutesToTest: int) -> int:
        base = minutesToTest // minutesToDie + 1
        res, p = 0, 1
        while p < buckets:
            p *= base
            res += 1
        return res
```

### **Java**

```java
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int base = minutesToTest / minutesToDie + 1;
        int res = 0;
        for (int p = 1; p < buckets; p *= base) {
            ++res;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int base = minutesToTest / minutesToDie + 1;
        int res = 0;
        for (int p = 1; p < buckets; p *= base) ++res;
        return res;
    }
};
```

### **Go**

```go
func poorPigs(buckets int, minutesToDie int, minutesToTest int) int {
	base := minutesToTest/minutesToDie + 1
	res := 0
	for p := 1; p < buckets; p *= base {
		res++
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
