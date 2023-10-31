# [2781. 最长合法子字符串的长度](https://leetcode.cn/problems/length-of-the-longest-valid-substring)

[English Version](/solution/2700-2799/2781.Length%20of%20the%20Longest%20Valid%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>word</code>&nbsp;和一个字符串数组&nbsp;<code>forbidden</code>&nbsp;。</p>

<p>如果一个字符串不包含&nbsp;<code>forbidden</code>&nbsp;中的任何字符串，我们称这个字符串是&nbsp;<strong>合法</strong>&nbsp;的。</p>

<p>请你返回字符串 <code>word</code>&nbsp;的一个 <strong>最长合法子字符串</strong>&nbsp;的长度。</p>

<p><strong>子字符串</strong> 指的是一个字符串中一段连续的字符，它可以为空。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>word = "cbaaaabc", forbidden = ["aaa","cb"]
<b>输出：</b>4
<b>解释：</b>总共有 11 个合法子字符串："c", "b", "a", "ba", "aa", "bc", "baa", "aab", "ab", "abc" 和 "aabc"。最长合法子字符串的长度为 4 。
其他子字符串都要么包含 "aaa" ，要么包含 "cb" 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>word = "leetcode", forbidden = ["de","le","e"]
<strong>输出：</strong>4
<b>解释：</b>总共有 11 个合法子字符串："l" ，"t" ，"c" ，"o" ，"d" ，"tc" ，"co" ，"od" ，"tco" ，"cod" 和 "tcod" 。最长合法子字符串的长度为 4 。
所有其他子字符串都至少包含 "de" ，"le" 和 "e" 之一。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code>&nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= forbidden.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= forbidden[i].length &lt;= 10</code></li>
	<li><code>forbidden[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 双指针**

我们用哈希表 $s$ 记录所有禁止的字符串，然后用双指针 $i$ 和 $j$ 遍历字符串 $word$，其中 $i$ 和 $j$ 分别表示当前合法子字符串的左右边界。

接下来，我们枚举子字符串的右端点 $j$，判断此时 $word[k..j]$ 是否合法，如果不合法，那么我们更新 $i = k + 1$。接下来更新答案 $ans = \max(ans, j - i + 1)$。

时间复杂度 $O(n \times \max(|forbidden[i]|^2) + m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别表示字符串 $word$ 和 $forbidden$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestValidSubstring(self, word: str, forbidden: List[str]) -> int:
        s = set(forbidden)
        ans = i = 0
        for j in range(len(word)):
            for k in range(j, max(j - 10, i - 1), -1):
                if word[k : j + 1] in s:
                    i = k + 1
                    break
            ans = max(ans, j - i + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestValidSubstring(String word, List<String> forbidden) {
        var s = new HashSet<>(forbidden);
        int ans = 0, n = word.length();
        for (int i = 0, j = 0; j < n; ++j) {
            for (int k = j; k > Math.max(j - 10, i - 1); --k) {
                if (s.contains(word.substring(k, j + 1))) {
                    i = k + 1;
                    break;
                }
            }
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
    int longestValidSubstring(string word, vector<string>& forbidden) {
        unordered_set<string> s(forbidden.begin(), forbidden.end());
        int ans = 0, n = word.size();
        for (int i = 0, j = 0; j < n; ++j) {
            for (int k = j; k > max(j - 10, i - 1); --k) {
                if (s.count(word.substr(k, j - k + 1))) {
                    i = k + 1;
                    break;
                }
            }
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func longestValidSubstring(word string, forbidden []string) (ans int) {
	s := map[string]bool{}
	for _, x := range forbidden {
		s[x] = true
	}
	n := len(word)
	for i, j := 0, 0; j < n; j++ {
		for k := j; k > max(j-10, i-1); k-- {
			if s[word[k:j+1]] {
				i = k + 1
				break
			}
		}
		ans = max(ans, j-i+1)
	}
	return
}
```

### **TypeScript**

```ts
function longestValidSubstring(word: string, forbidden: string[]): number {
    const s: Set<string> = new Set(forbidden);
    const n = word.length;
    let ans = 0;
    for (let i = 0, j = 0; j < n; ++j) {
        for (let k = j; k > Math.max(j - 10, i - 1); --k) {
            if (s.has(word.substring(k, j + 1))) {
                i = k + 1;
                break;
            }
        }
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
