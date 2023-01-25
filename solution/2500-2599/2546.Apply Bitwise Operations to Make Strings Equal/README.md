# [2546. 执行逐位运算使字符串相等](https://leetcode.cn/problems/apply-bitwise-operations-to-make-strings-equal)

[English Version](/solution/2500-2599/2546.Apply%20Bitwise%20Operations%20to%20Make%20Strings%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong> 开始的 <strong>二元</strong> 字符串 <code>s</code> 和 <code>target</code> ，两个字符串的长度均为 <code>n</code> 。你可以对 <code>s</code> 执行下述操作 <strong>任意</strong> 次：</p>

<ul>
	<li>选择两个 <strong>不同</strong> 的下标 <code>i</code> 和 <code>j</code> ，其中 <code>0 &lt;= i, j &lt; n</code> 。</li>
	<li>同时，将 <code>s[i]</code> 替换为 (<code>s[i]</code> <strong>OR</strong> <code>s[j]</code>) ，<code>s[j]</code> 替换为 (<code>s[i]</code> <strong>XOR</strong> <code>s[j]</code>) 。</li>
</ul>

<p>例如，如果 <code>s = "0110"</code> ，你可以选择 <code>i = 0</code> 和 <code>j = 2</code>，然后同时将 <code>s[0]</code> 替换为 (<code>s[0]</code> <strong>OR</strong> <code>s[2]</code> = <code>0</code> <strong>OR</strong> <code>1</code> = <code>1</code>)，并将 <code>s[2]</code> 替换为 (<code>s[0]</code> <strong>XOR</strong> <code>s[2]</code> = <code>0</code> <strong>XOR</strong> <code>1</code> = <code>1</code>)，最终得到 <code>s = "1110"</code> 。</p>

<p>如果可以使 <code>s</code> 等于 <code>target</code> ，返回 <code>true</code> ，否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "1010", target = "0110"
<strong>输出：</strong>true
<strong>解释：</strong>可以执行下述操作：
- 选择 i = 2 和 j = 0 ，得到 s = "<em><strong>0</strong></em>0<em><strong>1</strong></em>0".
- 选择 i = 2 和 j = 1 ，得到 s = "0<em><strong>11</strong></em>0".
可以使 s 等于 target ，返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "11", target = "00"
<strong>输出：</strong>false
<strong>解释：</strong>执行任意次操作都无法使 s 等于 target 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == s.length == target.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>target</code> 仅由数字 <code>0</code> 和 <code>1</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

注意到 $1$ 其实是数字转换的“工具”，因此只要两个字符串中都有 $1$ 或者都没有 $1$，那么就可以通过操作使得两个字符串相等。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeStringsEqual(self, s: str, target: str) -> bool:
        return ("1" in s) == ("1" in target)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
