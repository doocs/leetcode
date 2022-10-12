# [476. Number Complement](https://leetcode.com/problems/number-complement)

[中文文档](/solution/0400-0499/0476.Number%20Complement/README.md)

## Description

<p>The <strong>complement</strong> of an integer is the integer you get when you flip all the <code>0</code>&#39;s to <code>1</code>&#39;s and all the <code>1</code>&#39;s to <code>0</code>&#39;s in its binary representation.</p>

<ul>
	<li>For example, The integer <code>5</code> is <code>&quot;101&quot;</code> in binary and its <strong>complement</strong> is <code>&quot;010&quot;</code> which is the integer <code>2</code>.</li>
</ul>

<p>Given an integer <code>num</code>, return <em>its complement</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt; 2<sup>31</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as 1009: <a href="https://leetcode.com/problems/complement-of-base-10-integer/" target="_blank">https://leetcode.com/problems/complement-of-base-10-integer/</a></p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findComplement(self, num: int) -> int:
        ans = 0
        find = False
        for i in range(30, -1, -1):
            b = num & (1 << i)
            if not find and b == 0:
                continue
            find = True
            if b == 0:
                ans |= 1 << i
        return ans
```

### **Java**

```java
class Solution {
    public int findComplement(int num) {
        int ans = 0;
        boolean find = false;
        for (int i = 30; i >= 0; --i) {
            int b = num & (1 << i);
            if (!find && b == 0) {
                continue;
            }
            find = true;
            if (b == 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findComplement(int num) {
        int full = pow(2, int(log2(num)) + 1) - 1;
        return full ^ num;
    }
};
```

```cpp
class Solution {
public:
    int findComplement(int num) {
        int ans = 0;
        bool find = false;
        for (int i = 30; i >= 0; --i)
        {
            int b = num & (1 << i);
            if (!find && b == 0) continue;
            find = true;
            if (b == 0) ans |= (1 << i);
        }
        return ans;
    }
};
```

### **Go**

```go
func findComplement(num int) int {
	ans := 0
	find := false
	for i := 30; i >= 0; i-- {
		b := num & (1 << i)
		if !find && b == 0 {
			continue
		}
		find = true
		if b == 0 {
			ans |= (1 << i)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
