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

用一个数组或字典 chars 存放 magazine 中每个字母出现的次数。遍历 ransomNote 中每个字母，判断 chars 是否包含即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        counter = Counter(magazine)
        for c in ransomNote:
            if counter[c] <= 0:
                return False
            counter[c] -= 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] counter = new int[26];
        for (char c : magazine.toCharArray()) {
            ++counter[c - 'a'];
        }
        for (char c : ransomNote.toCharArray()) {
            if (counter[c - 'a'] <= 0) {
                return false;
            }
            --counter[c - 'a'];
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function canConstruct(ransomNote: string, magazine: string): boolean {
    let counter = new Array(26).fill(0);
    let base = 'a'.charCodeAt(0);
    for (let s of magazine) {
        ++counter[s.charCodeAt(0) - base];
    }
    for (let s of ransomNote) {
        let idx = s.charCodeAt(0) - base;
        if (counter[idx] == 0) return false;
        --counter[idx];
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    bool canConstruct(string ransomNote, string magazine) {
        vector<int> counter(26);
        for (char c : magazine) ++counter[c - 'a'];
        for (char c : ransomNote) {
            if (counter[c - 'a'] <= 0) return false;
            --counter[c - 'a'];
        }
        return true;
    }
};
```

### **Go**

```go
func canConstruct(ransomNote string, magazine string) bool {
	rc := make([]int, 26)
	for _, b := range ransomNote {
		rc[b-'a']++
	}

	mc := make([]int, 26)
	for _, b := range magazine {
		mc[b-'a']++
	}

	for i := 0; i < 26; i++ {
		if rc[i] > mc[i] {
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
