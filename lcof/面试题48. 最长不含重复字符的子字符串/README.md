# [面试题 48. 最长不含重复字符的子字符串](https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/)

## 题目描述

<p>请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入: </strong>&quot;abcabcbb&quot;
<strong>输出: </strong>3 
<strong>解释:</strong> 因为无重复字符的最长子串是 <code>&quot;abc&quot;，所以其</code>长度为 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>&quot;bbbbb&quot;
<strong>输出: </strong>1
<strong>解释: </strong>因为无重复字符的最长子串是 <code>&quot;b&quot;</code>，所以其长度为 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入: </strong>&quot;pwwkew&quot;
<strong>输出: </strong>3
<strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<code>&quot;wke&quot;</code>，所以其长度为 3。
&nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>&quot;pwke&quot;</code>&nbsp;是一个<em>子序列，</em>不是子串。
</pre>

<p>&nbsp;</p>

<p>提示：</p>

<ul>
	<li><code>s.length &lt;= 40000</code></li>
</ul>

<p>注意：本题与主站 3 题相同：<a href="https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/">https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/</a></p>

## 解法

“滑动窗口 + 哈希表”。

定义一个哈希表记录当前窗口内出现的字符，i、j 分别表示不重复子串的结束位置和开始位置，res 表示无重复字符子串的最大长度。

遍历 i，若 `[j, i - 1]` 窗口内存在 `s[i]`，则 j 循环向右移动，更新哈希表，直至 `[j, i - 1]` 窗口不存在 `s[i]`，循环结束。将 `s[i]` 加入哈希表中，此时 `[j, i]` 窗口内不含重复元素，更新 res 的最大值：`res = max(res, i - j + 1)`。

最后返回 res 即可。

<!-- tabs:start -->

### **Python3**

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

### **C++**

```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int res = 0;
        unordered_set<char> chars;
        for (int i = 0, j = 0; i < s.size(); ++i)
        {
            while (chars.count(s[i]))
            {
                chars.erase(s[j++]);
            }
            chars.insert(s[i]);
            res = max(res, i - j + 1);
        }
        return res;
    }
};
```

### **Go**

```go
func lengthOfLongestSubstring(s string) int {
	chars := make(map[byte]bool)
	res := 0
	for i, j := 0, 0; i < len(s); i++ {
		for chars[s[i]] {
			chars[s[j]] = false
			j++
		}
		chars[s[i]] = true
		res = max(res, i-j+1)
	}
	return res
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
    let res = 0;
    let l = 0;
    let set = new Set<string>();
    for (const c of s) {
        while (set.has(c)) {
            set.delete(s[l++]);
        }
        set.add(c);
        res = Math.max(res, set.size);
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashSet;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let mut res = 0;
        let mut i = 0;
        let mut set = HashSet::new();
        let chars = s.chars().collect::<Vec<char>>();
        for c in chars.iter() {
            while set.contains(c) {
                set.remove(&chars[i]);
                i += 1;
            }
            set.insert(c);
            res = res.max(set.len())
        }
        res as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
