# [1952. Three Divisors](https://leetcode.com/problems/three-divisors)

[中文文档](/solution/1900-1999/1952.Three%20Divisors/README.md)

## Description

<p>Given an integer <code>n</code>, return <code>true</code><em> if </em><code>n</code><em> has <strong>exactly three positive divisors</strong>. Otherwise, return </em><code>false</code>.</p>

<p>An integer <code>m</code> is a <strong>divisor</strong> of <code>n</code> if there exists an integer <code>k</code> such that <code>n = k * m</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> false
<strong>Explantion:</strong> 2 has only two divisors: 1 and 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> true
<strong>Explantion:</strong> 4 has three divisors: 1, 2, and 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isThree(self, n: int) -> bool:
        return sum(n % i == 0 for i in range(2, n)) == 1
```

### **Java**

```java
class Solution {
    public boolean isThree(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isThree(int n) {
        int cnt = 0;
        for (int i = 2; i < n; ++i)
            cnt += n % i == 0;
        return cnt == 1;
    }
};
```

### **Go**

```go
func isThree(n int) bool {
	cnt := 0
	for i := 2; i < n; i++ {
		if n%i == 0 {
			cnt++
		}
	}
	return cnt == 1
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isThree = function (n) {
    let cnt = 0;
    for (let i = 2; i < n; ++i) {
        if (n % i == 0) {
            ++cnt;
        }
    }
    return cnt == 1;
};
```

### **...**

```

```

<!-- tabs:end -->
