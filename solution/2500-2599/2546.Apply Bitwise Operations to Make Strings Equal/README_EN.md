# [2546. Apply Bitwise Operations to Make Strings Equal](https://leetcode.com/problems/apply-bitwise-operations-to-make-strings-equal)

[中文文档](/solution/2500-2599/2546.Apply%20Bitwise%20Operations%20to%20Make%20Strings%20Equal/README.md)

## Description

<p>You are given two <strong>0-indexed binary</strong> strings <code>s</code> and <code>target</code> of the same length <code>n</code>. You can do the following operation on <code>s</code> <strong>any</strong> number of times:</p>

<ul>
	<li>Choose two <strong>different</strong> indices <code>i</code> and <code>j</code> where <code>0 &lt;= i, j &lt; n</code>.</li>
	<li>Simultaneously, replace <code>s[i]</code> with (<code>s[i]</code> <strong>OR</strong> <code>s[j]</code>) and <code>s[j]</code> with (<code>s[i]</code> <strong>XOR</strong> <code>s[j]</code>).</li>
</ul>

<p>For example, if <code>s = &quot;0110&quot;</code>, you can choose <code>i = 0</code> and <code>j = 2</code>, then simultaneously replace <code>s[0]</code> with (<code>s[0]</code> <strong>OR</strong> <code>s[2]</code> = <code>0</code> <strong>OR</strong> <code>1</code> = <code>1</code>), and <code>s[2]</code> with (<code>s[0]</code> <strong>XOR</strong> <code>s[2]</code> = <code>0</code> <strong>XOR</strong> <code>1</code> = <code>1</code>), so we will have <code>s = &quot;1110&quot;</code>.</p>

<p>Return <code>true</code> <em>if you can make the string </em><code>s</code><em> equal to </em><code>target</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1010&quot;, target = &quot;0110&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> We can do the following operations:
- Choose i = 2 and j = 0. We have now s = &quot;<strong><u>0</u></strong>0<strong><u>1</u></strong>0&quot;.
- Choose i = 2 and j = 1. We have now s = &quot;0<strong><u>11</u></strong>0&quot;.
Since we can make s equal to target, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;11&quot;, target = &quot;00&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is not possible to make s equal to target with any number of operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length == target.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>target</code> consist of only the digits <code>0</code> and <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def makeStringsEqual(self, s: str, target: str) -> bool:
        return ("1" in s) == ("1" in target)
```

### **Java**

```java
class Solution {
    public boolean makeStringsEqual(String s, String target) {
        return s.contains("1") == target.contains("1");
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool makeStringsEqual(string s, string target) {
        auto a = count(s.begin(), s.end(), '1') > 0;
        auto b = count(target.begin(), target.end(), '1') > 0;
        return a == b;
    }
};
```

### **Go**

```go
func makeStringsEqual(s string, target string) bool {
	return strings.Contains(s, "1") == strings.Contains(target, "1")
}
```

### **TypeScript**

```ts
function makeStringsEqual(s: string, target: string): boolean {
    return s.includes('1') === target.includes('1');
}
```

### **Rust**

```rust
impl Solution {
    pub fn make_strings_equal(s: String, target: String) -> bool {
        s.contains('1') == target.contains('1')
    }
}
```

### **C**

```c
bool makeStringsEqual(char *s, char *target) {
    int count = 0;
    for (int i = 0; s[i]; i++) {
        if (s[i] == '1') {
            count++;
            break;
        }
    }
    for (int i = 0; target[i]; i++) {
        if (target[i] == '1') {
            count++;
            break;
        }
    }
    return !(count & 1);
}
```

### **...**

```

```

<!-- tabs:end -->
