# [1689. Partitioning Into Minimum Number Of Deci-Binary Numbers](https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers)

[中文文档](/solution/1600-1699/1689.Partitioning%20Into%20Minimum%20Number%20Of%20Deci-Binary%20Numbers/README.md)

## Description

<p>A decimal number is called <strong>deci-binary</strong> if each of its digits is either <code>0</code> or <code>1</code> without any leading zeros. For example, <code>101</code> and <code>1100</code> are <strong>deci-binary</strong>, while <code>112</code> and <code>3001</code> are not.</p>

<p>Given a string <code>n</code> that represents a positive decimal integer, return <em>the <strong>minimum</strong> number of positive <strong>deci-binary</strong> numbers needed so that they sum up to </em><code>n</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;32&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> 10 + 11 + 11 = 32
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;82734&quot;
<strong>Output:</strong> 8
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;27346209830709182346&quot;
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> consists of only digits.</li>
	<li><code>n</code> does not contain any leading zeros and represents a positive integer.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minPartitions(self, n: str) -> int:
        return int(max(n))
```

### **Java**

```java
class Solution {
    public int minPartitions(String n) {
        int res = 0;
        for (char c : n.toCharArray()) {
            res = Math.max(res, c - '0');
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function minPartitions(n: string): number {
    let nums = n.split('').map(d => parseInt(d));
    let ans = Math.max(...nums);
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int minPartitions(string n) {
        int res = 0;
        for (auto& c : n) {
            res = max(res, c - '0');
        }
        return res;
    }
};
```

### **Go**

```go
func minPartitions(n string) int {
	res := 0
	for _, c := range n {
		t := int(c - '0')
		if t > res {
			res = t
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
