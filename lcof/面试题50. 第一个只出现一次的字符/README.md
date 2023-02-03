# [面试题 50. 第一个只出现一次的字符](https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/)

## 题目描述

<p>在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。</p>

<p><strong>示例 1:</strong></p>

<pre>
输入：s = "abaccdeff"
输出：'b'
</pre>

<p><strong>示例 2:</strong></p>

<pre>
输入：s = "" 
输出：' '
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= s 的长度 &lt;= 50000</code></p>

## 解法

**方法一：数组或哈希表**

我们可以使用哈希表或数组来统计每个字符出现的次数，然后再遍历一遍字符串，找到第一个出现次数为 $1$ 的字符。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串长度；而 $C$ 为字符集大小，本题中 $C=26$。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def firstUniqChar(self, s: str) -> str:
        cnt = Counter(s)
        for c in s:
            if cnt[c] == 1:
                return c
        return " "
```

### **Java**

```java
class Solution {
    public char firstUniqChar(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    char firstUniqChar(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        for (char&c : s) {
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
};
```

### **Go**

```go
func firstUniqChar(s string) byte {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, c := range s {
		if cnt[c-'a'] == 1 {
			return byte(c)
		}
	}
	return ' '
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {character}
 */
var firstUniqChar = function (s) {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (const c of s) {
        if (cnt[c.charCodeAt(0) - 97] === 1) {
            return c;
        }
    }
    return ' ';
};
```

### **TypeScript**

```ts
function firstUniqChar(s: string): string {
    const map = new Map();
    for (const c of s) {
        map.set(c, !map.has(c));
    }
    for (const c of s) {
        if (map.get(c)) {
            return c;
        }
    }
    return ' ';
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn first_uniq_char(s: String) -> char {
        let mut map = HashMap::new();
        for c in s.as_bytes() {
            map.insert(c, !map.contains_key(c));
        }
        for c in s.as_bytes() {
            if map[c] {
                return char::from(*c);
            }
        }
        ' '
    }
}
```

### **C#**

```cs
public class Solution {
    public char FirstUniqChar(string s) {
        var cnt = new int[26];
        foreach(var c in s) {
            cnt[c - 'a'] ++;
        }
        foreach(var c in s) {
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
```

### **...**

```

```

<!-- tabs:end -->
