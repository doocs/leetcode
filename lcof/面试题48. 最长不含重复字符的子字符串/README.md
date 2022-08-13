# [面试题 48. 最长不含重复字符的子字符串](https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/)

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

<p>注意：本题与主站 3 题相同：<a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">https://leetcode.cn/problems/longest-substring-without-repeating-characters/</a></p>

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
        for (int i = 0, j = 0; i < s.size(); ++i) {
            while (chars.count(s[i])) {
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
    const n = s.length;
    const set = new Set<string>();
    let res = 0;
    let i = 0;
    for (let j = 0; j < n; j++) {
        const c = s[j];
        while (set.has(c)) {
            set.delete(s[i++]);
        }
        set.add(c);
        res = Math.max(res, set.size);
    }
    return res;
}
```

```ts
function lengthOfLongestSubstring(s: string): number {
    const map = new Map<string, number>();
    const n = s.length;
    let res = 0;
    let i = -1;
    for (let j = 0; j < n; j++) {
        if (map.has(s[j])) {
            i = Math.max(i, map.get(s[j]));
        }
        map.set(s[j], j);
        res = Math.max(res, j - i);
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut set = HashSet::new();
        let mut res = 0;
        let mut i = 0;
        for j in 0..n {
            while set.contains(&s[j]) {
                set.remove(&s[i]);
                i += 1;
            }
            set.insert(s[j]);
            res = res.max(set.len());
        }
        res as i32
    }
}
```

```rust
use std::collections::HashMap;
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut map = HashMap::new();
        let mut res = 0;
        let mut i = -1;
        for j in 0..n {
            let c = s[j];
            let j = j as i32;
            if map.contains_key(&c) {
                i = i.max(*map.get(&c).unwrap());
            }
            map.insert(c, j);
            res = res.max(j - i);
        }
        res
    }
}
```

### **C#**

```cs
public class Solution {
    public int LengthOfLongestSubstring(string s) {
        var set = new HashSet<char>();
        int ans = 0;
        for (int l=0, r=0; r < s.Length; r++) {
            while (set.Contains(s[r]))
            {
                set.Remove(s[l++]);
            }
            ans = Math.Max(r - l + 1, ans);
            set.Add(s[r]);
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
