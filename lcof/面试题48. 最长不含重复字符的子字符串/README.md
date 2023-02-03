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

**方法一：双指针 + 哈希表**

我们用双指针 $j$ 和 $i$ 分别表示子串的左右边界，其中 $j$ 是滑动窗口的左边界，$i$ 是滑动窗口的右边界，用哈希表 $vis$ 记录每个字符是否出现过。

遍历字符串 $s$，如果此时 $s[i]$ 在哈希表 $vis$ 中存在，说明 $s[i]$ 重复了，我们需要将左边界 $j$ 右移，直到 $s[i]$ 不在哈希表 $vis$ 中为止，然后将 $s[i]$ 加入哈希表 $vis$ 中。此时，我们更新无重复字符子串的最大长度，即 $ans = max(ans, i - j + 1)$。

遍历结束后，我们返回 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 $s$ 的长度；而 $C$ 是字符集的大小。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        cnt = Counter()
        ans = j = 0
        for i, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 1:
                cnt[s[j]] -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        vis = set()
        ans = j = 0
        for i, c in enumerate(s):
            while c in vis:
                vis.remove(s[j])
                j += 1
            vis.add(c)
            ans = max(ans, i - j + 1)
        return ans
```

### **Java**

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0, j = 0;
        Set<Character> vis = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            while (vis.contains(s.charAt(i))) {
                vis.remove(s.charAt(j++));
            }
            vis.add(s.charAt(i));
            ans = Math.max(ans, i - j + 1);
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
        int ans = 0;
        unordered_set<char> vis;
        for (int i = 0, j = 0; i < s.size(); ++i) {
            while (vis.count(s[i])) {
                vis.erase(s[j++]);
            }
            vis.insert(s[i]);
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func lengthOfLongestSubstring(s string) (ans int) {
	vis := map[byte]bool{}
	j := 0
	for i := range s {
		for vis[s[i]] {
			vis[s[j]] = false
			j++
		}
		vis[s[i]] = true
		ans = max(ans, i-j+1)
	}
	return
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
    let ans = 0;
    let vis = new Set();
    for (let i = 0, j = 0; i < s.length; ++i) {
        while (vis.has(s[i])) {
            vis.delete(s[j++]);
        }
        vis.add(s[i]);
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
};
```

### **TypeScript**

```ts
function lengthOfLongestSubstring(s: string): number {
    let ans = 0;
    let vis = new Set<string>();
    for (let i = 0, j = 0; i < s.length; ++i) {
        while (vis.has(s[i])) {
            vis.delete(s[j++]);
        }
        vis.add(s[i]);
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
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
        var vis = new HashSet<char>();
        int ans = 0;
        for (int i = 0, j = 0; i < s.Length; ++i) {
            while (vis.Contains(s[i])) {
                vis.Remove(s[j++]);
            }
            vis.Add(s[i]);
            ans = Math.Max(ans, i - j + 1);
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
