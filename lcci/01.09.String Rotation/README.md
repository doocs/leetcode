# [面试题 01.09. 字符串轮转](https://leetcode.cn/problems/string-rotation-lcci)

[English Version](/lcci/01.09.String%20Rotation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>字符串轮转。给定两个字符串<code>s1</code>和<code>s2</code>，请编写代码检查<code>s2</code>是否为<code>s1</code>旋转而成（比如，<code>waterbottle</code>是<code>erbottlewat</code>旋转后的字符串）。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：s1 = &quot;waterbottle&quot;, s2 = &quot;erbottlewat&quot;
<strong> 输出</strong>：True
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：s1 = &quot;aa&quot;, &quot;aba&quot;
<strong> 输出</strong>：False
</pre>

<ol>
</ol>

<p><strong>提示：</strong></p>

<ol>
	<li>字符串长度在[0, 100000]范围内。</li>
</ol>

<p><strong>说明:</strong></p>

<ol>
	<li>你能只调用一次检查子串的方法吗？</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：字符串匹配**

首先，如果字符串 $s1$ 和 $s2$ 长度不相等，那么肯定不是旋转字符串。

其次，如果字符串 $s1$ 和 $s2$ 长度相等，那么将两个 $s1$ 连接，得到的 $s1 + s1$ 这个字符串一定包含了 $s1$ 旋转的所有情况，这时候我们只要判断 $s2$ 是否是 $s1 + s1$ 的子串即可。

```bash
# 成立
s1 = "aba"
s2 = "baa"
s1 + s1 = "abaaba"
            ^^^

# 不成立
s1 = "aba"
s2 = "bab"
s1 + s1 = "abaaba"
```

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s1$ 的长度。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isFlipedString(self, s1: str, s2: str) -> bool:
        return len(s1) == len(s2) and s2 in s1 * 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isFlipedString(string s1, string s2) {
        return s1.size() == s2.size() && (s1 + s1).find(s2) != string::npos;
    }
};
```

### **Go**

```go
func isFlipedString(s1 string, s2 string) bool {
	return len(s1) == len(s2) && strings.Contains(s1+s1, s2)
}
```

### **TypeScript**

```ts
function isFlipedString(s1: string, s2: string): boolean {
    return s1.length === s2.length && (s2 + s2).indexOf(s1) !== -1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_fliped_string(s1: String, s2: String) -> bool {
        s1.len() == s2.len() && (s2.clone() + &s2).contains(&s1)
    }
}
```

原始写法：

```rust
impl Solution {
    pub fn is_fliped_string(s1: String, s2: String) -> bool {
        if s1 == s2 {
            return true;
        }
        if s1.len() != s2.len() {
            return false;
        }
        let s2: Vec<char> = (s2.clone() + &s2).chars().collect();
        let n = s1.len();
        let m = s2.len();
        for i in 0..m - n {
            let mut is_pass = true;
            for (j, c) in s1.chars().enumerate() {
                if c != s2[i + j] {
                    is_pass = false;
                    break;
                }
            }
            if is_pass {
                return true;
            };
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
