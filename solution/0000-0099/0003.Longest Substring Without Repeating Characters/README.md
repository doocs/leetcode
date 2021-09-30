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

定义一个哈希表记录当前窗口内出现的字符，i、j 分别表示不重复子串的结束位置和开始位置，res 表示无重复字符子串的最大长度。

遍历 i，若 `[j, i - 1]` 窗口内存在 `s[i]`，则 j 循环向右移动，更新哈希表，直至 `[j, i - 1]` 窗口不存在 `s[i]`，循环结束。将 `s[i]` 加入哈希表中，此时 `[j, i]` 窗口内不含重复元素，更新 res 的最大值：`res = max(res, i - j + 1)`。

最后返回 res 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        i = j = res = 0
        chars = set()
        while i < len(s):
            while s[i] in chars:
                if s[j] in chars:
                    chars.remove(s[j])
                j += 1
            chars.add(s[i])
            res = max(res, i - j + 1)
            i += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0, j = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            while (set.contains(c)) {
                set.remove(s.charAt(j++));
            }
            set.add(c);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
 var lengthOfLongestSubstring = function(s) {
    let res = 0;
    let chars = new Set();
    for (let i = 0, j = 0; i < s.length; ++i) {
        while (chars.has(s[i])) {
            chars.delete(s[j++]);
        }
        chars.add(s[i]);
        res = Math.max(res, i - j + 1);
    }
    return res;
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
  };
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
