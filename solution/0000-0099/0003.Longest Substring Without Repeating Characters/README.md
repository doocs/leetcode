# [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters)

[English Version](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，请你找出其中不含有重复字符的 <strong>最长子串 </strong>的长度。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>s = "abcabcbb"
<strong>输出: </strong>3
<strong>解释:</strong> 因为无重复字符的最长子串是 <code>"abc"，所以其</code>长度为 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = "bbbbb"
<strong>输出: </strong>1
<strong>解释: </strong>因为无重复字符的最长子串是 <code>"b"</code>，所以其长度为 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>s = "pwwkew"
<strong>输出: </strong>3
<strong>解释: </strong>因为无重复字符的最长子串是 <code>"wke"</code>，所以其长度为 3。
     请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>"pwke"</code> 是一个<em>子序列，</em>不是子串。
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入: </strong>s = ""
<strong>输出: </strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= s.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> 由英文字母、数字、符号和空格组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“滑动窗口 + 哈希表”。

定义一个哈希表记录当前窗口内出现的字符，i、j 分别表示不重复子串的开始位置和结束位置，ans 表示无重复字符子串的最大长度。

遍历 s 每个字符 c，若 `[i, j - 1]` 窗口内存在 `c`，则 i 循环向右移动，更新哈希表，直至 `[i, j - 1]` 窗口不存在 `c`，循环结束。将 `c` 加入哈希表中，此时 `[i, j]` 窗口内不含重复元素，更新 ans 的最大值：`res = max(ans, j - i + 1)`。

最后返回 ans 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
