# [387. 字符串中的第一个唯一字符](https://leetcode.cn/problems/first-unique-character-in-a-string)

[English Version](/solution/0300-0399/0387.First%20Unique%20Character%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串&nbsp;<code>s</code>&nbsp;，找到 <em>它的第一个不重复的字符，并返回它的索引</em> 。如果不存在，则返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "leetcode"
<strong>输出:</strong> 0
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "loveleetcode"
<strong>输出:</strong> 2
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "aabb"
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表**

我们可以用数组或哈希表 $cnt$ 记录字符串 $s$ 中每个字符出现的次数。

然后我们再遍历字符串 $s$，当遍历到某个字符 $c$ 时，如果 $cnt[c]=1$，则说明 $c$ 是第一个不重复的字符，返回它的索引即可。

如果遍历完字符串 $s$ 仍然没有找到不重复的字符，返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(\Sigma)$，其中 $\Sigma$ 是字符集的大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def firstUniqChar(self, s: str) -> int:
        cnt = Counter(s)
        for i, c in enumerate(s):
            if cnt[c] == 1:
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < n; ++i) {
            if (cnt[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int firstUniqChar(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (cnt[s[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func firstUniqChar(s string) int {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for i, c := range s {
		if cnt[c-'a'] == 1 {
			return i
		}
	}
	return -1
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var firstUniqChar = function (s) {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt() - 'a'.charCodeAt()];
    }
    for (let i = 0; i < s.length; ++i) {
        if (cnt[s[i].charCodeAt() - 'a'.charCodeAt()] === 1) {
            return i;
        }
    }
    return -1;
};
```

### **TypeScript**

```ts
function firstUniqChar(s: string): number {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (let i = 0; i < s.length; i++) {
        if (cnt[s.charCodeAt(i) - 97] === 1) {
            return i;
        }
    }
    return -1;
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function firstUniqChar($s) {
        for ($i = 0; $i < strlen($s); $i++) {
            $hashtable[$s[$i]]++;
        }
        for ($i = 0; $i < strlen($s); $i++) {
            if ($hashtable[$s[$i]] == 1) {
                return $i;
            }
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
