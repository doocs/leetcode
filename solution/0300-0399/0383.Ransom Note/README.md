# [383. 赎金信](https://leetcode.cn/problems/ransom-note)

[English Version](/solution/0300-0399/0383.Ransom%20Note/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串：<code>ransomNote</code> 和 <code>magazine</code> ，判断 <code>ransomNote</code> 能不能由 <code>magazine</code> 里面的字符构成。</p>

<p>如果可以，返回 <code>true</code> ；否则返回 <code>false</code> 。</p>

<p><code>magazine</code> 中的每个字符只能在 <code>ransomNote</code> 中使用一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>ransomNote = "a", magazine = "b"
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>ransomNote = "aa", magazine = "ab"
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>ransomNote = "aa", magazine = "aab"
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= ransomNote.length, magazine.length &lt;= 10<sup>5</sup></code></li>
	<li><code>ransomNote</code> 和 <code>magazine</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表或数组**

我们可以用一个哈希表或长度为 $26$ 的数组 $cnt$ 记录字符串 `magazine` 中所有字符出现的次数。然后遍历字符串 `ransomNote`，对于其中的每个字符 $c$，我们将其从 $cnt$ 的次数减 $1$，如果减 $1$ 之后的次数小于 $0$，说明 $c$ 在 `magazine` 中出现的次数不够，因此无法构成 `ransomNote`，返回 $false$ 即可。

否则，遍历结束后，说明 `ransomNote` 中的每个字符都可以在 `magazine` 中找到对应的字符，因此返回 $true$。

时间复杂度 $O(m + n)$，空间复杂度 $O(C)$。其中 $m$ 和 $n$ 分别为字符串 `ransomNote` 和 `magazine` 的长度；而 $C$ 为字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        cnt = Counter(magazine)
        for c in ransomNote:
            cnt[c] -= 1
            if cnt[c] < 0:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for (int i = 0; i < magazine.length(); ++i) {
            ++cnt[magazine.charAt(i) - 'a'];
        }
        for (int i = 0; i < ransomNote.length(); ++i) {
            if (--cnt[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canConstruct(string ransomNote, string magazine) {
        int cnt[26]{};
        for (char& c : magazine) {
            ++cnt[c - 'a'];
        }
        for (char& c : ransomNote) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func canConstruct(ransomNote string, magazine string) bool {
	cnt := [26]int{}
	for _, c := range magazine {
		cnt[c-'a']++
	}
	for _, c := range ransomNote {
		cnt[c-'a']--
		if cnt[c-'a'] < 0 {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
function canConstruct(ransomNote: string, magazine: string): boolean {
    const cnt = new Array(26).fill(0);
    for (const c of magazine) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    for (const c of ransomNote) {
        if (--cnt[c.charCodeAt(0) - 97] < 0) {
            return false;
        }
    }
    return true;
}
```

### **C#**

```cs
public class Solution {
    public bool CanConstruct(string ransomNote, string magazine) {
        int[] cnt = new int[26];
        foreach (var c in magazine) {
            ++cnt[c - 'a'];
        }
        foreach (var c in ransomNote) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $ransomNote
     * @param String $magazine
     * @return Boolean
     */
    function canConstruct($ransomNote, $magazine) {
        $arrM = str_split($magazine);
        for ($i = 0; $i < strlen($magazine); $i++) {
            $hashtable[$arrM[$i]] += 1;
        }
        for ($j = 0; $j < strlen($ransomNote); $j++) {
            if (!isset($hashtable[$ransomNote[$j]]) || $hashtable[$ransomNote[$j]] == 0) return false;
            else $hashtable[$ransomNote[$j]] -= 1;
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
