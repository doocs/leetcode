# [2351. 第一个出现两次的字母](https://leetcode.cn/problems/first-letter-to-appear-twice)

[English Version](/solution/2300-2399/2351.First%20Letter%20to%20Appear%20Twice/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由小写英文字母组成的字符串 <code>s</code> ，请你找出并返回第一个出现 <strong>两次</strong> 的字母。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>如果 <code>a</code> 的 <strong>第二次</strong> 出现比 <code>b</code> 的 <strong>第二次</strong> 出现在字符串中的位置更靠前，则认为字母 <code>a</code> 在字母 <code>b</code> 之前出现两次。</li>
	<li><code>s</code> 包含至少一个出现两次的字母。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abccbaacz"
<strong>输出：</strong>"c"
<strong>解释：</strong>
字母 'a' 在下标 0 、5 和 6 处出现。
字母 'b' 在下标 1 和 4 处出现。
字母 'c' 在下标 2 、3 和 7 处出现。
字母 'z' 在下标 8 处出现。
字母 'c' 是第一个出现两次的字母，因为在所有字母中，'c' 第二次出现的下标是最小的。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abcdd"
<strong>输出：</strong>"d"
<strong>解释：</strong>
只有字母 'd' 出现两次，所以返回 'd' 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>s</code> 包含至少一个重复字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表**

遍历字符串 $s$，用数组或哈希表 `cnt` 记录每个字母出现的次数，当某个字母出现两次时，返回该字母。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度，而 $C$ 为字符集大小。本题中 $C = 26$。

**方法二：位运算**

我们也可以用一个整数 `mask` 记录每个字母是否出现过，其中 `mask` 的第 $i$ 位表示第 $i$ 个字母是否出现过。当某个字母出现两次时，返回该字母。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def repeatedCharacter(self, s: str) -> str:
        cnt = Counter()
        for c in s:
            cnt[c] += 1
            if cnt[c] == 2:
                return c
```

```python
class Solution:
    def repeatedCharacter(self, s: str) -> str:
        mask = 0
        for c in s:
            i = ord(c) - ord('a')
            if mask >> i & 1:
                return c
            mask |= 1 << i
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public char repeatedCharacter(String s) {
        int[] cnt = new int[26];
        for (int i = 0; ; ++i) {
            char c = s.charAt(i);
            if (++cnt[c - 'a'] == 2) {
                return c;
            }
        }
    }
}
```

```java
class Solution {
    public char repeatedCharacter(String s) {
        int mask = 0;
        for (int i = 0;; ++i) {
            char c = s.charAt(i);
            if ((mask >> (c - 'a') & 1) == 1) {
                return c;
            }
            mask |= 1 << (c - 'a');
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    char repeatedCharacter(string s) {
        int cnt[26]{};
        for (int i = 0; ; ++i) {
            if (++cnt[s[i] - 'a'] == 2) {
                return s[i];
            }
        }
    }
};
```

```cpp
class Solution {
public:
    char repeatedCharacter(string s) {
        int mask = 0;
        for (int i = 0; ; ++i) {
            if (mask >> (s[i] - 'a') & 1) {
                return s[i];
            }
            mask |= 1 << (s[i] - 'a');
        }
    }
};
```

### **Go**

```go
func repeatedCharacter(s string) byte {
	cnt := [26]int{}
	for i := 0; ; i++ {
		cnt[s[i]-'a']++
		if cnt[s[i]-'a'] == 2 {
			return s[i]
		}
	}
}
```

```go
func repeatedCharacter(s string) byte {
	mask := 0
	for i := 0; ; i++ {
		if mask>>(s[i]-'a')&1 == 1 {
			return s[i]
		}
		mask |= 1 << (s[i] - 'a')
	}
}
```

### **TypeScript**

```ts
function repeatedCharacter(s: string): string {
    const vis = new Array(26).fill(false);
    for (const c of s) {
        const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
        if (vis[i]) {
            return c;
        }
        vis[i] = true;
    }
    return ' ';
}
```

```ts
function repeatedCharacter(s: string): string {
    let mask = 0;
    for (const c of s) {
        const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
        if (mask & (1 << i)) {
            return c;
        }
        mask |= 1 << i;
    }
    return ' ';
}
```

### **Rust**

```rust
impl Solution {
    pub fn repeated_character(s: String) -> char {
        let mut vis = [false; 26];
        for &c in s.as_bytes() {
            if vis[(c - b'a') as usize] {
                return c as char;
            }
            vis[(c - b'a') as usize] = true;
        }
        ' '
    }
}
```

```rust
impl Solution {
    pub fn repeated_character(s: String) -> char {
        let mut mask = 0;
        for &c in s.as_bytes() {
            if mask & 1 << (c - b'a') as i32 != 0 {
                return c as char;
            }
            mask |= 1 << (c - b'a') as i32;
        }
        ' '
    }
}
```

### **C**

```c
char repeatedCharacter(char *s) {
    int vis[26] = {0};
    for (int i = 0; s[i]; i++) {
        if (vis[s[i] - 'a']) {
            return s[i];
        }
        vis[s[i] - 'a']++;
    }
    return ' ';
}
```

```c
char repeatedCharacter(char *s) {
    int mask = 0;
    for (int i = 0; s[i]; i++) {
        if (mask & (1 << s[i] - 'a')) {
            return s[i];
        }
        mask |= 1 << s[i] - 'a';
    }
    return ' ';
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $s
     * @return String
     */
    function repeatedCharacter($s) {
        for ($i = 0;; $i++) {
            $hashtable[$s[$i]] += 1;
            if ($hashtable[$s[$i]] == 2) return $s[$i];
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
