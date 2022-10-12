# [1869. Longer Contiguous Segments of Ones than Zeros](https://leetcode.com/problems/longer-contiguous-segments-of-ones-than-zeros)

[中文文档](/solution/1800-1899/1869.Longer%20Contiguous%20Segments%20of%20Ones%20than%20Zeros/README.md)

## Description

<p>Given a binary string <code>s</code>, return <code>true</code><em> if the <strong>longest</strong> contiguous segment of </em><code>1</code>&#39;<em>s is <strong>strictly longer</strong> than the <strong>longest</strong> contiguous segment of </em><code>0</code>&#39;<em>s in </em><code>s</code>, or return <code>false</code><em> otherwise</em>.</p>

<ul>
	<li>For example, in <code>s = &quot;<u>11</u>01<u>000</u>10&quot;</code> the longest continuous segment of <code>1</code>s has length <code>2</code>, and the longest continuous segment of <code>0</code>s has length <code>3</code>.</li>
</ul>

<p>Note that if there are no <code>0</code>&#39;s, then the longest continuous segment of <code>0</code>&#39;s is considered to have a length <code>0</code>. The same applies if there is no <code>1</code>&#39;s.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1101&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
The longest contiguous segment of 1s has length 2: &quot;<u>11</u>01&quot;
The longest contiguous segment of 0s has length 1: &quot;11<u>0</u>1&quot;
The segment of 1s is longer, so return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;111000&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong>
The longest contiguous segment of 1s has length 3: &quot;<u>111</u>000&quot;
The longest contiguous segment of 0s has length 3: &quot;111<u>000</u>&quot;
The segment of 1s is not longer, so return false.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;110100010&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong>
The longest contiguous segment of 1s has length 2: &quot;<u>11</u>0100010&quot;
The longest contiguous segment of 0s has length 3: &quot;1101<u>000</u>10&quot;
The segment of 1s is not longer, so return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkZeroOnes(self, s: str) -> bool:
        n0 = n1 = 0
        t0 = t1 = 0
        for c in s:
            if c == '0':
                t0 += 1
                t1 = 0
            else:
                t0 = 0
                t1 += 1
            n0 = max(n0, t0)
            n1 = max(n1, t1)
        return n1 > n0
```

### **Java**

```java
class Solution {
    public boolean checkZeroOnes(String s) {
        int n0 = 0, n1 = 0;
        int t0 = 0, t1 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                ++t0;
                t1 = 0;
            } else {
                ++t1;
                t0 = 0;
            }
            n0 = Math.max(n0, t0);
            n1 = Math.max(n1, t1);
        }
        return n1 > n0;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var checkZeroOnes = function (s) {
    let max0 = 0,
        max1 = 0;
    let t0 = 0,
        t1 = 0;
    for (let char of s) {
        if (char == '0') {
            t0++;
            t1 = 0;
        } else {
            t1++;
            t0 = 0;
        }
        max0 = Math.max(max0, t0);
        max1 = Math.max(max1, t1);
    }
    return max1 > max0;
};
```

### **C++**

```cpp
class Solution {
public:
    bool checkZeroOnes(string s) {
        int n0 = 0, n1 = 0;
        int t0 = 0, t1 = 0;
        for (auto c : s) {
            if (c == '0') {
                ++t0;
                t1 = 0;
            } else {
                ++t1;
                t0 = 0;
            }
            n0 = max(n0, t0);
            n1 = max(n1, t1);
        }
        return n1 > n0;
    }
};
```

### **Go**

```go
func checkZeroOnes(s string) bool {
	n0, n1 := 0, 0
	t0, t1 := 0, 0
	for _, c := range s {
		if c == '0' {
			t0++
			t1 = 0
		} else {
			t1++
			t0 = 0
		}
		n0 = max(n0, t0)
		n1 = max(n1, t1)
	}
	return n1 > n0
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
