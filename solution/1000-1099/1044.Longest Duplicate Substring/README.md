# [1044. 最长重复子串](https://leetcode-cn.com/problems/longest-duplicate-substring)

[English Version](/solution/1000-1099/1044.Longest%20Duplicate%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个字符串&nbsp;<code>S</code>，考虑其所有<strong>重复子串</strong>（<code>S</code> 的连续子串，出现两次或多次，可能会有重叠）。</p>

<p>返回<strong>任何</strong>具有最长可能长度的重复子串。（如果 <code>S</code>&nbsp;不含重复子串，那么答案为&nbsp;<code>&quot;&quot;</code>。）</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;banana&quot;
<strong>输出：</strong>&quot;ana&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;abcd&quot;
<strong>输出：</strong>&quot;&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>2 &lt;= S.length &lt;= 10^5</code></li>
	<li><code>S</code> 由小写英文字母组成。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

字符串哈希 + 二分查找。

字符串哈希用于计算字符串哈希值，快速判断两个字符串是否相等。二分枚举长度，找到满足条件的最大长度即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestDupSubstring(self, s: str) -> str:
        n = len(s)

        def check(l):
            seen = set()
            for i in range(n - l + 1):
                t = s[i: i + l]
                if t in seen:
                    return t
                seen.add(t)
            return ''

        left, right = 0, n
        ans = ''
        while left < right:
            mid = (left + right + 1) >> 1
            t = check(mid)
            ans = t or ans
            if len(t) > 0:
                left = mid
            else:
                right = mid - 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private long[] p;
    private long[] h;

    public String longestDupSubstring(String s) {
        int base = 131;
        int n = s.length();
        p = new long[n + 10];
        h = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s.charAt(i);
        }
        String ans = "";
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            String t = check(s, mid);
            if (t.length() > 0) {
                left = mid;
                ans = t;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private String check(String s, int len) {
        int n = s.length();
        Set<Long> seen = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; ++i) {
            int j = i + len - 1;
            long t = h[j] - h[i - 1] * p[j - i + 1];
            if (seen.contains(t)) {
                return s.substring(i - 1, j);
            }
            seen.add(t);
        }
        return "";
    }
}
```

### **C++**

```cpp
typedef unsigned long long ULL;

class Solution {
public:
    ULL p[30010];
    ULL h[30010];
    string longestDupSubstring(string s) {
        int base = 131, n = s.size();
        p[0] = 1;
        for (int i = 0; i < n; ++i)
        {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s[i];
        }
        int left = 0, right = n;
        string ans = "";
        while (left < right)
        {
            int mid = (left + right + 1) >> 1;
            string t = check(s, mid);
            if (t.empty()) right = mid - 1;
            else
            {
                left = mid;
                ans = t;
            }
        }
        return ans;
    }

    string check(string s, int len) {
        int n = s.size();
        unordered_set<ULL> seen;
        for (int i = 1; i + len - 1 <= n; ++i)
        {
            int j = i + len - 1;
            ULL t = h[j] - h[i - 1] * p[j - i + 1];
            if (seen.count(t)) return s.substr(i - 1, len);
            seen.insert(t);
        }
        return "";
    }
};
```

### **Go**

```go
func longestDupSubstring(s string) string {
	base, n := 131, len(s)
	p := make([]int64, n+10)
	h := make([]int64, n+10)
	p[0] = 1
	for i := 0; i < n; i++ {
		p[i+1] = p[i] * int64(base)
		h[i+1] = h[i]*int64(base) + int64(s[i])
	}
	check := func(l int) string {
		seen := make(map[int64]bool)
		for i := 1; i+l-1 <= n; i++ {
			j := i + l - 1
			t := h[j] - h[i-1]*p[j-i+1]
			if seen[t] {
				return s[i-1 : j]
			}
			seen[t] = true
		}
		return ""
	}
	left, right := 0, n
	ans := ""
	for left < right {
		mid := (left + right + 1) >> 1
		t := check(mid)
		if len(t) > 0 {
			left = mid
			ans = t
		} else {
			right = mid - 1
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
