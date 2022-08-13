# [383. Ransom Note](https://leetcode.com/problems/ransom-note)

[中文文档](/solution/0300-0399/0383.Ransom%20Note/README.md)

## Description

<p>Given two strings <code>ransomNote</code> and <code>magazine</code>, return <code>true</code><em> if </em><code>ransomNote</code><em> can be constructed by using the letters from </em><code>magazine</code><em> and </em><code>false</code><em> otherwise</em>.</p>

<p>Each letter in <code>magazine</code> can only be used once in <code>ransomNote</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> ransomNote = "a", magazine = "b"
<strong>Output:</strong> false
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> ransomNote = "aa", magazine = "ab"
<strong>Output:</strong> false
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> ransomNote = "aa", magazine = "aab"
<strong>Output:</strong> true
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ransomNote.length, magazine.length &lt;= 10<sup>5</sup></code></li>
	<li><code>ransomNote</code> and <code>magazine</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
