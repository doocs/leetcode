# [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)

[中文文档](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README.md)

## Description

<p>Given a string <code>s</code>, find the length of the <b>longest substring</b> without repeating characters.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcbb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;abc&quot;, with the length of 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbbbb&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The answer is &quot;b&quot;, with the length of 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;pwwkew&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;wke&quot;, with the length of 3.
Notice that the answer must be a substring, &quot;pwke&quot; is a subsequence and not a substring.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;&quot;
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of English letters, digits, symbols and spaces.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        i = ans = 0
        chars = set()
        for j, c in enumerate(s):
            while c in chars:
                chars.remove(s[i])
                i += 1
            chars.add(c)
            ans = max(ans, j - i + 1)
        return ans
```

### **Java**

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, ans = 0;
        Set<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) {
            while (chars.contains(c)) {
                chars.remove(s.charAt(i++));
            }
            chars.add(c);
            ans = Math.max(ans, j - i + 1);
            ++j;
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
    let i = 0,
        j = 0,
        ans = 0;
    let chars = new Set();
    for (let c of s) {
        while (chars.has(c)) {
            chars.delete(s[i++]);
        }
        chars.add(c);
        ans = Math.max(ans, j - i + 1);
        ++j;
    }
    return ans;
};
```

### **TypeScript**

```ts
function lengthOfLongestSubstring(s: string): number {
    // 滑动窗口+哈希表
    let left = -1;
    let maxLen = 0;
    let hashTable = new Map();
    for (let right = 0; right < s.length; right++) {
        let cur = s.charAt(right);
        if (hashTable.has(cur)) {
            left = Math.max(left, hashTable.get(cur));
        }
        hashTable.set(cur, right);
        maxLen = Math.max(maxLen, right - left);
    }
    return maxLen;
}
```

### **Swift**

```swift
class Solution {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        var map = [Character: Int]()
        var currentStartingIndex = 0
        var i = 0
        var maxLength = 0
        for char in s {
            if map[char] != nil {
                if map[char]! >= currentStartingIndex {
                    maxLength = max(maxLength, i - currentStartingIndex)
                    currentStartingIndex = map[char]! + 1
                }
            }
            map[char] = i
            i += 1
        }
        return max(maxLength, i - currentStartingIndex)
    }
}
```

### **Go**

```go
func lengthOfLongestSubstring(s string) int {
	window := make(map[byte]int)
	n := len(s)
	ans := 0
	left, right := 0, 0
	for right < n {
		b := s[right]
		right++
		window[b]++
		for window[b] > 1 {
			window[s[left]]--
			left++
		}
		ans = max(ans, right-left)
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

### **C++**

```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int i = 0, j = 0, ans = 0;
        unordered_set<char> chars;
        for (char& c : s)
        {
            while (chars.count(c)) chars.erase(s[i++]);
            chars.insert(c);
            ans = max(ans, j - i + 1);
            ++j;
        }
        return ans;

    }
};
```

### **Nim**

```nim
proc lengthOfLongestSubstring(s: string): int =
  var
    i = 0
    j = 0
    res = 0
    literals: set[char] = {}

  while i < s.len:
    while s[i] in literals:
      if s[j] in literals:
        excl(literals, s[j])
      j += 1
    literals.incl(s[i]) # Uniform Function Call Syntax f(x) = x.f
    res = max(res, i - j + 1)
    i += 1

  result = res # result has the default return value
```

### **...**

```

```

<!-- tabs:end -->
