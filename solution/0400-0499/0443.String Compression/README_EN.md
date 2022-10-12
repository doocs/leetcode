# [443. String Compression](https://leetcode.com/problems/string-compression)

[中文文档](/solution/0400-0499/0443.String%20Compression/README.md)

## Description

<p>Given an array of characters <code>chars</code>, compress it using the following algorithm:</p>

<p>Begin with an empty string <code>s</code>. For each group of <strong>consecutive repeating characters</strong> in <code>chars</code>:</p>

<ul>
	<li>If the group&#39;s length is <code>1</code>, append the character to <code>s</code>.</li>
	<li>Otherwise, append the character followed by the group&#39;s length.</li>
</ul>

<p>The compressed string <code>s</code> <strong>should not be returned separately</strong>, but instead, be stored <strong>in the input character array <code>chars</code></strong>. Note that group lengths that are <code>10</code> or longer will be split into multiple characters in <code>chars</code>.</p>

<p>After you are done <strong>modifying the input array,</strong> return <em>the new length of the array</em>.</p>

<p>You must write an algorithm that uses only constant extra space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;c&quot;,&quot;c&quot;,&quot;c&quot;]
<strong>Output:</strong> Return 6, and the first 6 characters of the input array should be: [&quot;a&quot;,&quot;2&quot;,&quot;b&quot;,&quot;2&quot;,&quot;c&quot;,&quot;3&quot;]
<strong>Explanation:</strong> The groups are &quot;aa&quot;, &quot;bb&quot;, and &quot;ccc&quot;. This compresses to &quot;a2b2c3&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;]
<strong>Output:</strong> Return 1, and the first character of the input array should be: [&quot;a&quot;]
<strong>Explanation:</strong> The only group is &quot;a&quot;, which remains uncompressed since it&#39;s a single character.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;]
<strong>Output:</strong> Return 4, and the first 4 characters of the input array should be: [&quot;a&quot;,&quot;b&quot;,&quot;1&quot;,&quot;2&quot;].
<strong>Explanation:</strong> The groups are &quot;a&quot; and &quot;bbbbbbbbbbbb&quot;. This compresses to &quot;ab12&quot;.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= chars.length &lt;= 2000</code></li>
	<li><code>chars[i]</code> is a lowercase English letter, uppercase English letter, digit, or symbol.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def compress(self, chars: List[str]) -> int:
        i, k, n = 0, 0, len(chars)
        while i < n:
            j = i + 1
            while j < n and chars[j] == chars[i]:
                j += 1
            chars[k] = chars[i]
            k += 1
            if j - i > 1:
                cnt = str(j - i)
                for c in cnt:
                    chars[k] = c
                    k += 1
            i = j
        return k
```

### **Java**

```java
class Solution {
    public int compress(char[] chars) {
        int k = 0, n = chars.length;
        for (int i = 0, j = i + 1; i < n;) {
            while (j < n && chars[j] == chars[i]) {
                ++j;
            }
            chars[k++] = chars[i];
            if (j - i > 1) {
                String cnt = String.valueOf(j - i);
                for (char c : cnt.toCharArray()) {
                    chars[k++] = c;
                }
            }
            i = j;
        }
        return k;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int compress(vector<char>& chars) {
        int k = 0, n = chars.size();
        for (int i = 0, j = i + 1; i < n;) {
            while (j < n && chars[j] == chars[i])
                ++j;
            chars[k++] = chars[i];
            if (j - i > 1) {
                for (char c : to_string(j - i)) {
                    chars[k++] = c;
                }
            }
            i = j;
        }
        return k;
    }
};
```

### **Go**

```go
func compress(chars []byte) int {
	i, k, n := 0, 0, len(chars)
	for i < n {
		j := i + 1
		for j < n && chars[j] == chars[i] {
			j++
		}
		chars[k] = chars[i]
		k++
		if j-i > 1 {
			cnt := strconv.Itoa(j - i)
			for _, c := range cnt {
				chars[k] = byte(c)
				k++
			}
		}
		i = j
	}
	return k
}
```

### **...**

```

```

<!-- tabs:end -->
