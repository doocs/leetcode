# [387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string)

[中文文档](/solution/0300-0399/0387.First%20Unique%20Character%20in%20a%20String/README.md)

## Description

<p>Given a string <code>s</code>, <em>find the first non-repeating character in it and return its index</em>. If it does not exist, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "leetcode"
<strong>Output:</strong> 0
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "loveleetcode"
<strong>Output:</strong> 2
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> s = "aabb"
<strong>Output:</strong> -1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def firstUniqChar(self, s: str) -> int:
        counter = Counter(s)
        for i, c in enumerate(s):
            if counter[c] == 1:
                return i
        return -1
```

### **Java**

```java
class Solution {
    public int firstUniqChar(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (counter[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
```

### **TypeScript**

```ts
function firstUniqChar(s: string): number {
    let record = new Map();
    for (let cur of [...s]) {
        record.set(cur, record.has(cur));
    }
    for (let i = 0; i < s.length; i++) {
        if (!record.get(s[i])) return i;
    }
    return -1;
}
```

### **C++**

```cpp
class Solution {
public:
    int firstUniqChar(string s) {
        vector<int> counter(26);
        for (char& c : s) ++counter[c - 'a'];
        for (int i = 0; i < s.size(); ++i)
            if (counter[s[i] - 'a'] == 1)
                return i;
        return -1;
    }
};
```

### **Go**

```go
func firstUniqChar(s string) int {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	for i, c := range s {
		if counter[c-'a'] == 1 {
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
    const counter = new Map();
    for (let c of s) {
        counter[c] = (counter[c] || 0) + 1;
    }
    for (let i = 0; i < s.length; ++i) {
        if (counter[s[i]] == 1) {
            return i;
        }
    }
    return -1;
};
```

### **...**

```

```

<!-- tabs:end -->
