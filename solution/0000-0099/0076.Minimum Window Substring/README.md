# [76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring)

[English Version](/solution/0000-0099/0076.Minimum%20Window%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 、一个字符串 <code>t</code> 。返回 <code>s</code> 中涵盖 <code>t</code> 所有字符的最小子串。如果 <code>s</code> 中不存在涵盖 <code>t</code> 所有字符的子串，则返回空字符串 <code>""</code> 。</p>

<p> </p>

<p><strong>注意：</strong></p>

<ul>
	<li>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</li>
	<li>如果 <code>s</code> 中存在这样的子串，我们保证它是唯一的答案。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
<strong>输出：</strong>"BANC"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a", t = "a"
<strong>输出：</strong>"a"
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "a", t = "aa"
<strong>输出:</strong> ""
<strong>解释:</strong> t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length, t.length <= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

<p> </p>
<strong>进阶：</strong>你能设计一个在 <code>o(n)</code> 时间内解决此问题的算法吗？

## 解法

滑动窗口

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        ans = ''
        m, n = len(s), len(t)
        if m < n:
            return ans
        need = Counter(t)
        window = Counter()
        i, cnt, mi = 0, 0, inf
        for j, c in enumerate(s):
            window[c] += 1
            if need[c] >= window[c]:
                cnt += 1
            while cnt == n:
                if j - i + 1 < mi:
                    mi = j - i + 1
                    ans = s[i : j + 1]
                c = s[i]
                if need[c] >= window[c]:
                    cnt -= 1
                window[c] -= 1
                i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> mp = new HashMap<>();
        int begin = 0, end = 0, counter = t.length(), minLen = Integer.MAX_VALUE, minStart = 0, size = s.length();

        for (char c : s.toCharArray())
            mp.put(c, 0);
        for (char c : t.toCharArray()) {
            if (mp.containsKey(c))
                mp.put(c, mp.get(c) + 1);
            else
                return "";
        }

        while (end < size) {
            if (mp.get(s.charAt(end)) > 0)
                counter--;

            mp.put(s.charAt(end), mp.get(s.charAt(end)) - 1);

            end++;

            while (counter == 0) {
                if (end - begin < minLen) {
                    minStart = begin;
                    minLen = end - begin;
                }
                mp.put(s.charAt(begin), mp.get(s.charAt(begin)) + 1);

                if (mp.get(s.charAt(begin)) > 0) {
                    counter++;
                }

                begin++;
            }
        }

        if (minLen != Integer.MAX_VALUE) {
            return s.substring(minStart, minStart + minLen);
        }
        return "";
    }
}
```

### **TypeScript**

```ts
function minWindow(s: string, t: string): string {
    let n1 = s.length,
        n2 = t.length;
    if (n1 < n2) return '';
    let need = new Array(128).fill(0);
    let window = new Array(128).fill(0);
    for (let i = 0; i < n2; ++i) {
        ++need[t.charCodeAt(i)];
    }

    let left = 0,
        right = 0;
    let res = '';
    let count = 0;
    let min = n1 + 1;
    while (right < n1) {
        let cur = s.charCodeAt(right);
        ++window[cur];
        if (need[cur] > 0 && need[cur] >= window[cur]) {
            ++count;
        }
        while (count == n2) {
            cur = s.charCodeAt(left);
            if (need[cur] > 0 && need[cur] >= window[cur]) {
                --count;
            }
            if (right - left + 1 < min) {
                min = right - left + 1;
                res = s.slice(left, right + 1);
            }
            --window[cur];
            ++left;
        }
        ++right;
    }
    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> m;
        int begin = 0, end = 0, minlen = INT_MAX, minStart = 0, size = s.size(), counter = t.size();
        for (auto c : t)
            m[c]++;

        while (end < size) {
            if (m[s[end]] > 0)
                counter--;

            m[s[end]]--;

            end++;

            while (counter == 0) {
                if (end - begin < minlen) {
                    minStart = begin;
                    minlen = end - begin;
                }

                m[s[begin]]++;
                if (m[s[begin]] > 0)
                    counter++;

                begin++;
            }
        }

        if (minlen != INT_MAX) {
            return s.substr(minStart, minlen);
        }
        return "";
    }
};
```

### **Go**

```go
func minWindow(s string, t string) string {
	ans := ""
	m, n := len(s), len(t)
	if m < n {
		return ans
	}
	need := make([]int, 128)
	for _, c := range t {
		need[c] += 1
	}
	window := make([]int, 128)
	i, cnt, mi := 0, 0, m+1
	for j, c := range s {
		window[c]++
		if need[c] >= window[c] {
			cnt++
		}
		for cnt == n {
			if j-i+1 < mi {
				mi = j - i + 1
				ans = s[i : j+1]
			}
			c = rune(s[i])
			if need[c] >= window[c] {
				cnt--
			}
			window[c]--
			i++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
