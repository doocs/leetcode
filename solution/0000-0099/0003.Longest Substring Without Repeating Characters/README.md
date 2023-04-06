# [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters)

[English Version](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的&nbsp;<strong>最长子串&nbsp;</strong>的长度。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>s = "abcabcbb"
<strong>输出: </strong>3 
<strong>解释:</strong> 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = "bbbbb"
<strong>输出: </strong>1
<strong>解释: </strong>因为无重复字符的最长子串是 "b"，所以其长度为 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>s = "pwwkew"
<strong>输出: </strong>3
<strong>解释: </strong>因为无重复字符的最长子串是 "wke"，所以其长度为 3。
请注意，你的答案必须是 <strong>子串 </strong>的长度，"pwke" 是一个<em>子序列，</em>不是子串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;由英文字母、数字、符号和空格组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针 + 哈希表**

定义一个哈希表记录当前窗口内出现的字符，记 $i$ 和 $j$ 分别表示不重复子串的开始位置和结束位置，无重复字符子串的最大长度记为 `ans`。

遍历字符串 `s` 的每个字符 $s[j]$，我们记为 $c$。若 $s[i..j-1]$ 窗口内存在 $c$，则 $i$ 循环向右移动，更新哈希表，直至 $s[i..j-1]$ 窗口不存在 `c`，循环结束。将 `c` 加入哈希表中，此时 $s[i..j]$ 窗口内不含重复元素，更新 `ans` 的最大值。

最后返回 `ans` 即可。

时间复杂度 $O(n)$，其中 $n$ 表示字符串 `s` 的长度。

双指针算法模板：

```java
for (int i = 0, j = 0; i < n; ++i) {
    while (j < i && check(j, i)) {
        ++j;
    }
    // 具体问题的逻辑
}
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        ss = set()
        i = ans = 0
        for j, c in enumerate(s):
            while c in ss:
                ss.remove(s[i])
                i += 1
            ss.add(c)
            ans = max(ans, j - i + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> ss = new HashSet<>();
        int i = 0, ans = 0;
        for (int j = 0; j < s.length(); ++j) {
            char c = s.charAt(j);
            while (ss.contains(c)) {
                ss.remove(s.charAt(i++));
            }
            ss.add(c);
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        unordered_set<char> ss;
        int i = 0, ans = 0;
        for (int j = 0; j < s.size(); ++j) {
            while (ss.count(s[j])) ss.erase(s[i++]);
            ss.insert(s[j]);
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func lengthOfLongestSubstring(s string) int {
	ss := map[byte]bool{}
	i, ans := 0, 0
	for j := 0; j < len(s); j++ {
		for ss[s[j]] {
			ss[s[i]] = false
			i++
		}
		ss[s[j]] = true
		ans = max(ans, j-i+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
    const ss = new Set();
    let i = 0;
    let ans = 0;
    for (let j = 0; j < s.length; ++j) {
        while (ss.has(s[j])) {
            ss.delete(s[i++]);
        }
        ss.add(s[j]);
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public int LengthOfLongestSubstring(string s) {
        var ss = new HashSet<char>();
        int i = 0, ans = 0;
        for (int j = 0; j < s.Length; ++j)
        {
            while (ss.Contains(s[j]))
            {
                ss.Remove(s[i++]);
            }
            ss.Add(s[j]);
            ans = Math.Max(ans, j - i + 1);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function lengthOfLongestSubstring(s: string): number {
    const ss = new Set();
    let i = 0;
    let ans = 0;
    for (let j = 0; j < s.length; ++j) {
        while (ss.has(s[j])) {
            ss.delete(s[i++]);
        }
        ss.add(s[j]);
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
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

### **Rust**

```rust
use std::collections::HashSet;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let mut set = HashSet::new();
        let mut i = 0;
        s.iter()
            .map(|c| {
                while set.contains(&c) {
                    set.remove(&s[i]);
                    i += 1;
                }
                set.insert(c);
                set.len()
            })
            .max()
            .unwrap_or(0) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
