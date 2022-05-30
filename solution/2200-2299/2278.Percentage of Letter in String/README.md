# [2278. 字母在字符串中的百分比](https://leetcode.cn/problems/percentage-of-letter-in-string)

[English Version](/solution/2200-2299/2278.Percentage%20of%20Letter%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个字符 <code>letter</code> ，返回在 <code>s</code> 中等于&nbsp;<code>letter</code>&nbsp;字符所占的 <strong>百分比</strong> ，向下取整到最接近的百分比。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "foobar", letter = "o"
<strong>输出：</strong>33
<strong>解释：</strong>
等于字母 'o' 的字符在 s 中占到的百分比是 2 / 6 * 100% = 33% ，向下取整，所以返回 33 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "jjjj", letter = "k"
<strong>输出：</strong>0
<strong>解释：</strong>
等于字母 'k' 的字符在 s 中占到的百分比是 0% ，所以返回 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>letter</code> 是一个小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def percentageLetter(self, s: str, letter: str) -> int:
        return s.count(letter) * 100 // len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int percentageLetter(String s, char letter) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == letter) {
                ++cnt;
            }
        }
        return cnt * 100 / s.length();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int percentageLetter(string s, char letter) {
        int cnt = 0;
        for (char& c : s) cnt += c == letter;
        return cnt * 100 / s.size();
    }
};
```

### **Go**

```go
func percentageLetter(s string, letter byte) int {
	cnt := 0
	for i := range s {
		if s[i] == letter {
			cnt++
		}
	}
	return cnt * 100 / len(s)
}
```

### **TypeScript**

```ts
function percentageLetter(s: string, letter: string): number {
    let count = 0;
    let total = s.length;
    for (let i of s) {
        if (i === letter) count++;
    }
    return Math.floor((count / total) * 100);
}
```

### **Rust**

```rust
impl Solution {
    pub fn percentage_letter(s: String, letter: char) -> i32 {
        let mut count = 0;
        for c in s.chars() {
            if c == letter {
                count += 1;
            }
        }
        (count * 100 / s.len()) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
