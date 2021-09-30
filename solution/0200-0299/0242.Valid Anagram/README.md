# [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram)

[English Version](/solution/0200-0299/0242.Valid%20Anagram/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <em>s</em> 和 <em>t</em> ，编写一个函数来判断 <em>t</em> 是否是 <em>s</em> 的字母异位词。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> <em>s</em> = &quot;anagram&quot;, <em>t</em> = &quot;nagaram&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> <em>s</em> = &quot;rat&quot;, <em>t</em> = &quot;car&quot;
<strong>输出: </strong>false</pre>

<p><strong>说明:</strong><br>
你可以假设字符串只包含小写字母。</p>

<p><strong>进阶:</strong><br>
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？</p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

数组或哈希表累加 s 中每个字符出现的次数，再减去 t 中对应的每个字符出现的次数。遍历结束后，若字符中出现次数不为 0 的情况，返回 false，否则返回 true。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        n = len(s)
        chars = [0] * 26
        for i in range(n):
            chars[ord(s[i]) - ord('a')] += 1
            chars[ord(t[i]) - ord('a')] -= 1
        for c in chars:
            if c != 0:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int n;
        if ((n = s.length()) != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (int i = 0; i < n; ++i) {
            ++chars[s.charAt(i) - 'a'];
            --chars[t.charAt(i) - 'a'];
        }
        for (int c : chars) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function isAnagram(s: string, t: string): boolean {
    if (s.length != t.length) return false;
    let record = new Array(26).fill(0);
    let base = 'a'.charCodeAt(0);
    for (let i = 0; i < s.length; ++i) {
        ++record[s.charCodeAt(i) - base];
        --record[t.charCodeAt(i) - base];
    }
    return record.every(v => v == 0);
};
```

### **C++**

```cpp
class Solution {
public:
    bool isAnagram(string s, string t) {
        if (s.size() != t.size())
            return false;
        vector<int> chars(26, 0);
        for (int i = 0, n = s.size(); i < n; ++i)
        {
            ++chars[s[i] - 'a'];
            --chars[t[i] - 'a'];
        }
        for (int c : chars)
        {
            if (c != 0)
                return false;
        }
        return true;
    }
};
```

### **Go**

```go
func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}
	var chars [26]int
	for i := 0; i < len(s); i++ {
		chars[s[i]-'a']++
		chars[t[i]-'a']--
	}
	for _, c := range chars {
		if c != 0 {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
