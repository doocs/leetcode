# [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring)

[中文文档](/solution/0000-0099/0076.Minimum%20Window%20Substring/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code> of lengths <code>m</code> and <code>n</code> respectively, return <em>the <strong>minimum window substring</strong> of </em><code>s</code><em> such that every character in </em><code>t</code><em> (<strong>including duplicates</strong>) is included in the window. If there is no such substring</em><em>, return the empty string </em><code>&quot;&quot;</code><em>.</em></p>

<p>The testcases will be generated such that the answer is <strong>unique</strong>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within the string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ADOBECODEBANC&quot;, t = &quot;ABC&quot;
<strong>Output:</strong> &quot;BANC&quot;
<strong>Explanation:</strong> The minimum window substring &quot;BANC&quot; includes &#39;A&#39;, &#39;B&#39;, and &#39;C&#39; from string t.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;, t = &quot;a&quot;
<strong>Output:</strong> &quot;a&quot;
<strong>Explanation:</strong> The entire string s is the minimum window.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;, t = &quot;aa&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> Both &#39;a&#39;s from t must be included in the window.
Since the largest window of s only has one &#39;a&#39;, return empty string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == s.length</code></li>
	<li><code>n == t.length</code></li>
	<li><code>1 &lt;= m, n&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of uppercase and lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you find an algorithm that runs in <code>O(m + n)</code> time?

## Solutions

<!-- tabs:start -->

### **Python3**

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
