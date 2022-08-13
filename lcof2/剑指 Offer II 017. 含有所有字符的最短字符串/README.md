# [剑指 Offer II 017. 含有所有字符的最短字符串](https://leetcode.cn/problems/M1oyTv)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <code>s</code> 和&nbsp;<code>t</code> 。返回 <code>s</code> 中包含&nbsp;<code>t</code>&nbsp;的所有字符的最短子字符串。如果 <code>s</code> 中不存在符合条件的子字符串，则返回空字符串 <code>&quot;&quot;</code> 。</p>

<p>如果 <code>s</code> 中存在多个符合条件的子字符串，返回任意一个。</p>

<p>&nbsp;</p>

<p><strong>注意： </strong>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;ADOBECODEBANC&quot;, t = &quot;ABC&quot;
<strong>输出：</strong>&quot;BANC&quot;
<strong>解释：</strong>最短子字符串 &quot;BANC&quot; 包含了字符串 t 的所有字符 &#39;A&#39;、&#39;B&#39;、&#39;C&#39;</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;a&quot;, t = &quot;a&quot;
<strong>输出：</strong>&quot;a&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;a&quot;, t = &quot;aa&quot;
<strong>输出：</strong>&quot;&quot;
<strong>解释：</strong>t 中两个字符 &#39;a&#39; 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能设计一个在 <code>o(n)</code> 时间内解决此问题的算法吗？</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 76&nbsp;题相似（本题答案不唯一）：<a href="https://leetcode.cn/problems/minimum-window-substring/">https://leetcode.cn/problems/minimum-window-substring/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

滑动窗口，当窗口包含全部需要的的字符后，进行收缩，以求得最小长度

进阶解法：利用 `count` 变量避免重复对 `need` 和 `window` 进行扫描

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        m, n = len(s), len(t)
        if n > m:
            return ""
        need, window = defaultdict(int), defaultdict(int)
        for c in t:
            need[c] += 1
        start, minLen = 0, inf
        left, right = 0, 0
        while right < m:
            window[s[right]] += 1
            right += 1
            while self.check(need, window):
                if right - left < minLen:
                    minLen = right - left
                    start = left
                window[s[left]] -= 1
                left += 1
        return "" if minLen == inf else s[start : start + minLen]

    def check(self, need, window):
        for k, v in need.items():
            if window[k] < v:
                return False
        return True
```

进阶解法

```python
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        m, n = len(s), len(t)
        if n > m:
            return ""
        need, window = defaultdict(int), defaultdict(int)
        needCount, windowCount = 0, 0
        for c in t:
            if need[c] == 0:
                needCount += 1
            need[c] += 1
        start, minLen = 0, inf
        left, right = 0, 0
        while right < m:
            ch = s[right]
            right += 1
            if ch in need:
                window[ch] += 1
                if window[ch] == need[ch]:
                    windowCount += 1
            while windowCount == needCount:
                if right - left < minLen:
                    minLen = right - left
                    start = left
                ch = s[left]
                left += 1
                if ch in need:
                    if window[ch] == need[ch]:
                        windowCount -= 1
                    window[ch] -= 1
        return "" if minLen == inf else s[start:start + minLen]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }
        int start = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        while (right < m) {
            window.merge(s.charAt(right++), 1, Integer::sum);
            while (check(need, window)) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                window.merge(s.charAt(left++), -1, Integer::sum);
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    private boolean check(Map<Character, Integer> need, Map<Character, Integer> window) {
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            if (window.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
```

进阶解法

```java
class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int needCount = 0, windowCount = 0;
        for (char ch : t.toCharArray()) {
            if (!need.containsKey(ch)) {
                needCount++;
            }
            need.merge(ch, 1, Integer::sum);
        }
        int start = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        while (right < m) {
            char ch = s.charAt(right++);
            if (need.containsKey(ch)) {
                int val = window.getOrDefault(ch, 0) + 1;
                if (val == need.get(ch)) {
                    windowCount++;
                }
                window.put(ch, val);
            }
            while (windowCount == needCount) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                ch = s.charAt(left++);
                if (need.containsKey(ch)) {
                    int val = window.get(ch);
                    if (val == need.get(ch)) {
                        windowCount--;
                    }
                    window.put(ch, val - 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
```

### **Go**

```go
func minWindow(s string, t string) string {
	m, n := len(s), len(t)
	if n > m {
		return ""
	}
	need, window := make(map[byte]int), make(map[byte]int)
	for _, r := range t {
		need[byte(r)]++
	}
	start, minLen := 0, math.MaxInt32
	left, right := 0, 0
	for right < m {
		window[s[right]]++
		right++
		for check(need, window) {
			if right-left < minLen {
				minLen = right - left
				start = left
			}
			window[s[left]]--
			left++
		}
	}
	if minLen == math.MaxInt32 {
		return ""
	}
	return s[start : start+minLen]
}

func check(need, window map[byte]int) bool {
	for k, v := range need {
		if window[k] < v {
			return false
		}
	}
	return true
}
```

进阶解法

```go
func minWindow(s string, t string) string {
	m, n := len(s), len(t)
	if n > m {
		return ""
	}
	need, window := make(map[byte]int), make(map[byte]int)
	needCount, windowCount := 0, 0
	for _, r := range t {
		if need[byte(r)] == 0 {
			needCount++
		}
		need[byte(r)]++
	}
	start, minLen := 0, math.MaxInt32
	left, right := 0, 0
	for right < m {
		ch := s[right]
		right++
		if v, ok := need[ch]; ok {
			window[ch]++
			if window[ch] == v {
				windowCount++
			}
		}
		for windowCount == needCount {
			if right-left < minLen {
				minLen = right - left
				start = left
			}
			ch = s[left]
			left++
			if v, ok := need[ch]; ok {
				if window[ch] == v {
					windowCount--
				}
				window[ch]--
			}
		}
	}
	if minLen == math.MaxInt32 {
		return ""
	}
	return s[start : start+minLen]
}
```

### **...**

```

```

<!-- tabs:end -->
