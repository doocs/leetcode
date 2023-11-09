# [2710. Remove Trailing Zeros From a String](https://leetcode.com/problems/remove-trailing-zeros-from-a-string)

[中文文档](/solution/2700-2799/2710.Remove%20Trailing%20Zeros%20From%20a%20String/README.md)

## Description

<p>Given a <strong>positive</strong> integer <code>num</code> represented as a string, return <em>the integer </em><code>num</code><em> without trailing zeros as a string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;51230100&quot;
<strong>Output:</strong> &quot;512301&quot;
<strong>Explanation:</strong> Integer &quot;51230100&quot; has 2 trailing zeros, we remove them and return integer &quot;512301&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;123&quot;
<strong>Output:</strong> &quot;123&quot;
<strong>Explanation:</strong> Integer &quot;123&quot; has no trailing zeros, we return integer &quot;123&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 1000</code></li>
	<li><code>num</code> consists&nbsp;of only digits.</li>
	<li><code>num</code> doesn&#39;t&nbsp;have any leading zeros.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeTrailingZeros(self, num: str) -> str:
        return num.rstrip("0")
```

### **Java**

```java
class Solution {
    public String removeTrailingZeros(String num) {
        int i = num.length() - 1;
        while (num.charAt(i) == '0') {
            --i;
        }
        return num.substring(0, i + 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string removeTrailingZeros(string num) {
        while (num.back() == '0') {
            num.pop_back();
        }
        return num;
    }
};
```

### **Go**

```go
func removeTrailingZeros(num string) string {
	i := len(num) - 1
	for num[i] == '0' {
		i--
	}
	return num[:i+1]
}
```

### **TypeScript**

```ts
function removeTrailingZeros(num: string): string {
    let i = num.length - 1;
    while (num[i] === '0') {
        --i;
    }
    return num.substring(0, i + 1);
}
```

### **Rust**

```rust
impl Solution {
    pub fn remove_trailing_zeros(num: String) -> String {
        let mut i = num.len() - 1;

        while num.chars().nth(i) == Some('0') {
            i -= 1;
        }

        num[..i + 1].to_string()
    }
}
```

```rust
impl Solution {
    pub fn remove_trailing_zeros(num: String) -> String {
        num.chars()
            .rev()
            .skip_while(|&c| c == '0')
            .collect::<String>()
            .chars()
            .rev()
            .collect::<String>()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
