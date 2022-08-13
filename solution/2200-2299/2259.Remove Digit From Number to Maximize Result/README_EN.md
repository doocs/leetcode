# [2259. Remove Digit From Number to Maximize Result](https://leetcode.com/problems/remove-digit-from-number-to-maximize-result)

[中文文档](/solution/2200-2299/2259.Remove%20Digit%20From%20Number%20to%20Maximize%20Result/README.md)

## Description

<p>You are given a string <code>number</code> representing a <strong>positive integer</strong> and a character <code>digit</code>.</p>

<p>Return <em>the resulting string after removing <strong>exactly one occurrence</strong> of </em><code>digit</code><em> from </em><code>number</code><em> such that the value of the resulting string in <strong>decimal</strong> form is <strong>maximized</strong></em>. The test cases are generated such that <code>digit</code> occurs at least once in <code>number</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;123&quot;, digit = &quot;3&quot;
<strong>Output:</strong> &quot;12&quot;
<strong>Explanation:</strong> There is only one &#39;3&#39; in &quot;123&quot;. After removing &#39;3&#39;, the result is &quot;12&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;1231&quot;, digit = &quot;1&quot;
<strong>Output:</strong> &quot;231&quot;
<strong>Explanation:</strong> We can remove the first &#39;1&#39; to get &quot;231&quot; or remove the second &#39;1&#39; to get &quot;123&quot;.
Since 231 &gt; 123, we return &quot;231&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;551&quot;, digit = &quot;5&quot;
<strong>Output:</strong> &quot;51&quot;
<strong>Explanation:</strong> We can remove either the first or second &#39;5&#39; from &quot;551&quot;.
Both result in the string &quot;51&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= number.length &lt;= 100</code></li>
	<li><code>number</code> consists of digits from <code>&#39;1&#39;</code> to <code>&#39;9&#39;</code>.</li>
	<li><code>digit</code> is a digit from <code>&#39;1&#39;</code> to <code>&#39;9&#39;</code>.</li>
	<li><code>digit</code> occurs at least once in <code>number</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeDigit(self, number: str, digit: str) -> str:
        return max(
            number[:i] + number[i + 1 :] for i, d in enumerate(number) if d == digit
        )
```

### **Java**

```java
class Solution {
    public String removeDigit(String number, char digit) {
        String ans = "0";
        for (int i = 0, n = number.length(); i < n; ++i) {
            char d = number.charAt(i);
            if (d == digit) {
                String t = number.substring(0, i) + number.substring(i + 1);
                if (ans.compareTo(t) < 0) {
                    ans = t;
                }
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
    string removeDigit(string number, char digit) {
        string ans = "0";
        for (int i = 0, n = number.size(); i < n; ++i) {
            char d = number[i];
            if (d == digit) {
                string t = number.substr(0, i) + number.substr(i + 1, n - i);
                if (ans < t) ans = t;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func removeDigit(number string, digit byte) string {
	ans := "0"
	for i, d := range number {
		if d == rune(digit) {
			t := number[:i] + number[i+1:]
			if strings.Compare(ans, t) < 0 {
				ans = t
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function removeDigit(number: string, digit: string): string {
    let nums = number.split('');
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (nums[i] != digit) continue;
        ans = i;
        if (i + 1 < n && nums[i + 1] > nums[i]) break;
    }
    nums.splice(ans, 1);
    return nums.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
