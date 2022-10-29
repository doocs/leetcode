# [2450. Number of Distinct Binary Strings After Applying Operations](https://leetcode.com/problems/number-of-distinct-binary-strings-after-applying-operations)

[中文文档](/solution/2400-2499/2450.Number%20of%20Distinct%20Binary%20Strings%20After%20Applying%20Operations/README.md)

## Description

<p>You are given a <strong>binary</strong> string <code>s</code> and a positive integer <code>k</code>.</p>

<p>You can apply the following operation on the string <strong>any</strong> number of times:</p>

<ul>
	<li>Choose any substring of size <code>k</code> from <code>s</code> and <strong>flip</strong> all its characters, that is, turn all <code>1</code>&#39;s into <code>0</code>&#39;s, and all <code>0</code>&#39;s into <code>1</code>&#39;s.</li>
</ul>

<p>Return <em>the number of <strong>distinct</strong> strings you can obtain</em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>A binary string is a string that consists <strong>only</strong> of the characters <code>0</code> and <code>1</code>.</li>
	<li>A substring is a contiguous part of a string.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1001&quot;, k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can obtain the following strings:
- Applying no operation on the string gives s = &quot;1001&quot;.
- Applying one operation on the substring starting at index 0 gives s = &quot;<u><strong>011</strong></u>1&quot;.
- Applying one operation on the substring starting at index 1 gives s = &quot;1<u><strong>110</strong></u>&quot;.
- Applying one operation on both the substrings starting at indices 0 and 1 gives s = &quot;<u><strong>0000</strong></u>&quot;.
It can be shown that we cannot obtain any other string, so the answer is 4.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10110&quot;, k = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can obtain the following strings:
- Applying no operation on the string gives s = &quot;10110&quot;.
- Applying one operation on the whole string gives s = &quot;01001&quot;.
It can be shown that we cannot obtain any other string, so the answer is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countDistinctStrings(self, s: str, k: int) -> int:
        return pow(2, len(s) - k + 1) % (10**9 + 7)
```

### **Java**

```java
class Solution {
    public static final int MOD = (int) 1e9 + 7;

    public int countDistinctStrings(String s, int k) {
        int ans = 1;
        for (int i = 0; i < s.length() - k + 1; ++i) {
            ans = (ans * 2) % MOD;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countDistinctStrings(string s, int k) {
        int ans = 1;
        for (int i = 0; i < s.size() - k + 1; ++i) {
            ans = (ans * 2) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func countDistinctStrings(s string, k int) int {
	const mod int = 1e9 + 7
	ans := 1
	for i := 0; i < len(s)-k+1; i++ {
		ans = (ans * 2) % mod
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
