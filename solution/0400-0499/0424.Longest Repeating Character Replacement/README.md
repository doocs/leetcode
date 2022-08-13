# [424. 替换后的最长重复字符](https://leetcode.cn/problems/longest-repeating-character-replacement)

[English Version](/solution/0400-0499/0424.Longest%20Repeating%20Character%20Replacement/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 <code>k</code> 次。</p>

<p>在执行上述操作后，返回包含相同字母的最长子字符串的长度。</p>

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
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由大写英文字母组成</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

我们维护一个数组 `int[26]` 来存储当前窗口中各个字母的出现次数，j 表示窗口的左边界，i 表示窗口右边界。

-   窗口扩张：j 不变，i++
-   窗口滑动：j++，i++

`maxCnt` 保存滑动窗口内相同字母出现次数的**历史最大值**，通过判断窗口宽度 `i - j + 1` 是否大于 `maxCnt + k` 来决定窗口是否做滑动，否则窗口就扩张。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
