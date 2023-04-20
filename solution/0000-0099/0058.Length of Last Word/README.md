# [58. 最后一个单词的长度](https://leetcode.cn/problems/length-of-last-word)

[English Version](/solution/0000-0099/0058.Length%20of%20Last%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 <strong>最后一个</strong> 单词的长度。</p>

<p><strong>单词</strong> 是指仅由字母组成、不包含任何空格字符的最大子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Hello World"
<strong>输出：</strong>5
<strong>解释：</strong>最后一个单词是“World”，长度为5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "   fly me   to   the moon  "
<strong>输出：</strong>4<strong>
解释：</strong>最后一个单词是“moon”，长度为4。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "luffy is still joyboy"
<strong>输出：</strong>6
<strong>解释：</strong>最后一个单词是长度为6的“joyboy”。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅有英文字母和空格 <code>' '</code> 组成</li>
	<li><code>s</code> 中至少存在一个单词</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：逆向遍历 + 双指针**

我们从字符串 $s$ 末尾开始遍历，找到第一个不为空格的字符，即为最后一个单词的最后一个字符，下标记为 $i$。然后继续向前遍历，找到第一个为空格的字符，即为最后一个单词的第一个字符的前一个字符，记为 $j$。那么最后一个单词的长度即为 $i - j$。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        i = len(s) - 1
        while i >= 0 and s[i] == ' ':
            i -= 1
        j = i
        while j >= 0 and s[j] != ' ':
            j -= 1
        return i - j
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            --i;
        }
        int j = i;
        while (j >= 0 && s.charAt(j) != ' ') {
            --j;
        }
        return i - j;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int lengthOfLastWord(string s) {
        int i = s.size() - 1;
        while (~i && s[i] == ' ') {
            --i;
        }
        int j = i;
        while (~j && s[j] != ' ') {
            --j;
        }
        return i - j;
    }
};
```

### **Go**

```go
func lengthOfLastWord(s string) int {
	i := len(s) - 1
	for i >= 0 && s[i] == ' ' {
		i--
	}
	j := i
	for j >= 0 && s[j] != ' ' {
		j--
	}
	return i - j
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLastWord = function (s) {
    let i = s.length - 1;
    while (i >= 0 && s[i] === ' ') {
        --i;
    }
    let j = i;
    while (j >= 0 && s[j] !== ' ') {
        --j;
    }
    return i - j;
};
```

### **TypeScript**

```ts
function lengthOfLastWord(s: string): number {
    let i = s.length - 1;
    while (i >= 0 && s[i] === ' ') {
        --i;
    }
    let j = i;
    while (j >= 0 && s[j] !== ' ') {
        --j;
    }
    return i - j;
}
```

### **C#**

```cs
public class Solution {
    public int LengthOfLastWord(string s) {
        int i = s.Length - 1;
        while (i >= 0 && s[i] == ' ') {
            --i;
        }
        int j = i;
        while (j >= 0 && s[j] != ' ') {
            --j;
        }
        return i - j;
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn length_of_last_word(s: String) -> i32 {
        let s = s.trim_end();
        let n = s.len();
        for (i, c) in s.char_indices().rev() {
            if c == ' ' {
                return (n - i - 1) as i32;
            }
        }
        n as i32
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function lengthOfLastWord($s) {
        $count = 0;
        while ($s[strlen($s) - 1] == " ") {
            $s = substr($s, 0, -1);
        }
        while (strlen($s) != 0 && $s[strlen($s) - 1] != " ") {
            $count++;
            $s = substr($s, 0, -1);
        }
        return $count;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
