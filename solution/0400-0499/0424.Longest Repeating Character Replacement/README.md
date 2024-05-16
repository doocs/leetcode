---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0424.Longest%20Repeating%20Character%20Replacement/README.md
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [424. 替换后的最长重复字符](https://leetcode.cn/problems/longest-repeating-character-replacement)

[English Version](/solution/0400-0499/0424.Longest%20Repeating%20Character%20Replacement/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 <code>k</code> 次。</p>

<p>在执行上述操作后，返回 <em>包含相同字母的最长子字符串的长度。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ABAB", k = 2
<strong>输出：</strong>4
<strong>解释：</strong>用两个'A'替换为两个'B',反之亦然。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "AABABBA", k = 1
<strong>输出：</strong>4
<strong>解释：</strong>
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
可能存在其他的方法来得到同样的结果。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由大写英文字母组成</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        counter = [0] * 26
        i = j = maxCnt = 0
        while i < len(s):
            counter[ord(s[i]) - ord('A')] += 1
            maxCnt = max(maxCnt, counter[ord(s[i]) - ord('A')])
            if i - j + 1 > maxCnt + k:
                counter[ord(s[j]) - ord('A')] -= 1
                j += 1
            i += 1
        return i - j
```

```java
class Solution {
    public int characterReplacement(String s, int k) {
        int[] counter = new int[26];
        int i = 0;
        int j = 0;
        for (int maxCnt = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            ++counter[c - 'A'];
            maxCnt = Math.max(maxCnt, counter[c - 'A']);
            if (i - j + 1 - maxCnt > k) {
                --counter[s.charAt(j) - 'A'];
                ++j;
            }
        }
        return i - j;
    }
}
```

```cpp
class Solution {
public:
    int characterReplacement(string s, int k) {
        vector<int> counter(26);
        int i = 0, j = 0, maxCnt = 0;
        for (char& c : s) {
            ++counter[c - 'A'];
            maxCnt = max(maxCnt, counter[c - 'A']);
            if (i - j + 1 > maxCnt + k) {
                --counter[s[j] - 'A'];
                ++j;
            }
            ++i;
        }
        return i - j;
    }
};
```

```go
func characterReplacement(s string, k int) int {
	counter := make([]int, 26)
	j, maxCnt := 0, 0
	for i := range s {
		c := s[i] - 'A'
		counter[c]++
		if maxCnt < counter[c] {
			maxCnt = counter[c]
		}
		if i-j+1 > maxCnt+k {
			counter[s[j]-'A']--
			j++
		}
	}
	return len(s) - j
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
