# [1404. Number of Steps to Reduce a Number in Binary Representation to One](https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one)

[中文文档](/solution/1400-1499/1404.Number%20of%20Steps%20to%20Reduce%20a%20Number%20in%20Binary%20Representation%20to%20One/README.md)

## Description

<p>Given the binary representation of an integer as a string <code>s</code>, return <em>the number of steps to reduce it to </em><code>1</code><em> under the following rules</em>:</p>

<ul>
	<li>
	<p>If the current number is even, you have to divide it by <code>2</code>.</p>
	</li>
	<li>
	<p>If the current number is odd, you have to add <code>1</code> to it.</p>
	</li>
</ul>

<p>It is guaranteed that you can always reach one for all test cases.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1101&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> &quot;1101&quot; corressponds to number 13 in their decimal representation.
Step 1) 13 is odd, add 1 and obtain 14.&nbsp;
Step 2) 14 is even, divide by 2 and obtain 7.
Step 3) 7 is odd, add 1 and obtain 8.
Step 4) 8 is even, divide by 2 and obtain 4.&nbsp; 
Step 5) 4 is even, divide by 2 and obtain 2.&nbsp;
Step 6) 2 is even, divide by 2 and obtain 1.&nbsp; 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> &quot;10&quot; corressponds to number 2 in their decimal representation.
Step 1) 2 is even, divide by 2 and obtain 1.&nbsp; 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1&quot;
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length&nbsp;&lt;= 500</code></li>
	<li><code>s</code> consists of characters &#39;0&#39; or &#39;1&#39;</li>
	<li><code>s[0] == &#39;1&#39;</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSteps(self, s: str) -> int:
        carry = False
        ans = 0
        for c in s[:0:-1]:
            if carry:
                if c == '0':
                    c = '1'
                    carry = False
                else:
                    c = '0'
            if c == '1':
                ans += 1
                carry = True
            ans += 1
        if carry:
            ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int numSteps(String s) {
        boolean carry = false;
        int ans = 0;
        for (int i = s.length() - 1; i > 0; --i) {
            char c = s.charAt(i);
            if (carry) {
                if (c == '0') {
                    c = '1';
                    carry = false;
                } else {
                    c = '0';
                }
            }
            if (c == '1') {
                ++ans;
                carry = true;
            }
            ++ans;
        }
        if (carry) {
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSteps(string s) {
        int ans = 0;
        bool carry = false;
        for (int i = s.size() - 1; i; --i) {
            char c = s[i];
            if (carry) {
                if (c == '0') {
                    c = '1';
                    carry = false;
                } else
                    c = '0';
            }
            if (c == '1') {
                ++ans;
                carry = true;
            }
            ++ans;
        }
        if (carry) ++ans;
        return ans;
    }
};
```

### **Go**

```go
func numSteps(s string) int {
	ans := 0
	carry := false
	for i := len(s) - 1; i > 0; i-- {
		c := s[i]
		if carry {
			if c == '0' {
				c = '1'
				carry = false
			} else {
				c = '0'
			}
		}
		if c == '1' {
			ans++
			carry = true
		}
		ans++
	}
	if carry {
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
