# [2864. Maximum Odd Binary Number](https://leetcode.com/problems/maximum-odd-binary-number)

[中文文档](/solution/2800-2899/2864.Maximum%20Odd%20Binary%20Number/README.md)

## Description

<p>You are given a <strong>binary</strong> string <code>s</code> that contains at least one <code>&#39;1&#39;</code>.</p>

<p>You have to <strong>rearrange</strong> the bits in such a way that the resulting binary number is the <strong>maximum odd binary number</strong> that can be created from this combination.</p>

<p>Return <em>a string representing the maximum odd binary number that can be created from the given combination.</em></p>

<p><strong>Note </strong>that the resulting string <strong>can</strong> have leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;010&quot;
<strong>Output:</strong> &quot;001&quot;
<strong>Explanation:</strong> Because there is just one &#39;1&#39;, it must be in the last position. So the answer is &quot;001&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0101&quot;
<strong>Output:</strong> &quot;1001&quot;
<strong>Explanation: </strong>One of the &#39;1&#39;s must be in the last position. The maximum number that can be made with the remaining digits is &quot;100&quot;. So the answer is &quot;1001&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
	<li><code>s</code> contains at least one <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumOddBinaryNumber(self, s: str) -> str:
        cnt = s.count("1")
        return "1" * (cnt - 1) + (len(s) - cnt) * "0" + "1"
```

### **Java**

```java
class Solution {
    public String maximumOddBinaryNumber(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                ++cnt;
            }
        }
        return "1".repeat(cnt - 1) + "0".repeat(s.length() - cnt) + "1";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string maximumOddBinaryNumber(string s) {
        int cnt = count_if(s.begin(), s.end(), [](char c) { return c == '1'; });
        string ans;
        for (int i = 1; i < cnt; ++i) {
            ans.push_back('1');
        }
        for (int i = 0; i < s.size() - cnt; ++i) {
            ans.push_back('0');
        }
        ans.push_back('1');
        return ans;
    }
};
```

### **Go**

```go
func maximumOddBinaryNumber(s string) string {
	cnt := strings.Count(s, "1")
	return strings.Repeat("1", cnt-1) + strings.Repeat("0", len(s)-cnt) + "1"
}
```

### **TypeScript**

```ts
function maximumOddBinaryNumber(s: string): string {
    let cnt = 0;
    for (const c of s) {
        cnt += c === '1' ? 1 : 0;
    }
    return '1'.repeat(cnt - 1) + '0'.repeat(s.length - cnt) + '1';
}
```

### **...**

```

```

<!-- tabs:end -->
