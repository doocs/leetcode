# [1009. Complement of Base 10 Integer](https://leetcode.com/problems/complement-of-base-10-integer)

[中文文档](/solution/1000-1099/1009.Complement%20of%20Base%2010%20Integer/README.md)

## Description

<p>Every non-negative integer <code>N</code>&nbsp;has a binary representation.&nbsp; For example,&nbsp;<code>5</code> can be represented as <code>&quot;101&quot;</code>&nbsp;in binary, <code>11</code> as <code>&quot;1011&quot;</code>&nbsp;in binary, and so on.&nbsp; Note that except for <code>N = 0</code>, there are no leading zeroes in any&nbsp;binary representation.</p>

<p>The <em>complement</em>&nbsp;of a binary representation&nbsp;is the number in binary you get when changing every <code>1</code> to a <code>0</code> and <code>0</code> to a <code>1</code>.&nbsp; For example, the complement of <code>&quot;101&quot;</code> in binary is <code>&quot;010&quot;</code> in binary.</p>

<p>For a given number <code>N</code> in base-10, return the complement of it&#39;s binary representation as a&nbsp;base-10 integer.</p>

<p>&nbsp;</p>

<ol>
</ol>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-1-1">5</span>
<strong>Output: </strong><span id="example-output-1">2</span>
<strong>Explanation: </strong>5 is &quot;101&quot; in binary, with complement &quot;010&quot; in binary, which is 2 in base-10.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-2-1">7</span>
<strong>Output: </strong><span id="example-output-2">0</span>
<span id="example-output-1"><strong>Explanation: </strong>7 is &quot;111&quot; in binary, with complement &quot;000&quot; in binary, which is 0 in base-10.
</span></pre>

<div>
<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-3-1">10</span>
<strong>Output: </strong><span id="example-output-3">5</span>
<strong>Explanation: </strong>10 is &quot;1010&quot; in binary, with complement &quot;0101&quot; in binary, which is 5 in base-10.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= N &lt; 10^9</code></li>
	<li>This question is the same as 476:&nbsp;<a href="https://leetcode.com/problems/number-complement/">https://leetcode.com/problems/number-complement/</a></li>
</ol>
</div>
</div>
</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def bitwiseComplement(self, n: int) -> int:
        if n == 0:
            return 1
        ans = 0
        find = False
        for i in range(30, -1, -1):
            b = n & (1 << i)
            if not find and b == 0:
                continue
            find = True
            if b == 0:
                ans |= (1 << i)
        return ans
```

### **Java**

```java
class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0;
        boolean find = false;
        for (int i = 30; i >= 0; --i) {
            int b = n & (1 << i);
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
    int bitwiseComplement(int n) {
        if (n == 0) return 1;
        int ans = 0;
        bool find = false;
        for (int i = 30; i >= 0; --i)
        {
            int b = n & (1 << i);
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
func bitwiseComplement(n int) int {
	if n == 0 {
		return 1
	}
	ans := 0
	find := false
	for i := 30; i >= 0; i-- {
		b := n & (1 << i)
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
