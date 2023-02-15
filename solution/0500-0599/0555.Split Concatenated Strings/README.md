# [555. 分割连接字符串](https://leetcode.cn/problems/split-concatenated-strings)

[English Version](/solution/0500-0599/0555.Split%20Concatenated%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串列表&nbsp;<code>strs</code>，你可以将这些字符串连接成一个循环字符串，对于每个字符串，你可以选择是否翻转它。在所有可能的循环字符串中，你需要分割循环字符串（这将使循环字符串变成一个常规的字符串），然后找到字典序最大的字符串。</p>

<p>具体来说，要找到字典序最大的字符串，你需要经历两个阶段：</p>

<ol>
	<li>将所有字符串连接成一个循环字符串，你可以选择是否翻转某些字符串，并按照给定的顺序连接它们。</li>
	<li>在循环字符串的某个位置分割它，这将使循环字符串从分割点变成一个常规的字符串。</li>
</ol>

<p>你的工作是在所有可能的常规字符串中找到字典序最大的一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> strs = ["abc","xyz"]
<strong>输出:</strong> "zyxcba"
<strong>解释:</strong> 你可以得到循环字符串 "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-"，其中 '-' 代表循环状态。 
答案字符串来自第四个循环字符串， 你可以从中间字符 'a' 分割开然后得到 "zyxcba"。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> strs = ["abc"]
<strong>输出:</strong> "cba"
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 1000</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(strs[i].length) &lt;= 1000</code></li>
	<li><code>strs[i]</code>&nbsp;只包含小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们先遍历字符串数组 `strs`，对于每个字符串 $s$，如果 $s$ 的反转字符串 $t$ 比 $s$ 大，那么我们就将 $s$ 替换为 $t$。

然后我们再枚举字符串数组 `strs` 的每个位置 $i$ 作为分割点，将字符串数组 `strs` 拆成两部分，分别为 $strs[i + 1:]$ 和 $strs[:i]$，然后将这两部分拼接起来，得到一个新的字符串 $t$。接下来，我们枚举当前字符串 $strs[i]$ 的每个位置 $j$，其后缀部分为 $a=strs[i][j:]$，前缀部分为 $b=strs[i][:j]$，那么我们可以将 $a$, $t$ 和 $b$ 拼接起来，得到一个新的字符串 $cur$，如果 $cur$ 比当前答案大，那么我们就更新答案。这是将 $strs[i]$ 翻转后的情况，我们还需要考虑 $strs[i]$ 不翻转的情况，即将 $a$, $t$ 和 $b$ 的顺序反过来拼接，得到一个新的字符串 $cur$，如果 $cur$ 比当前答案大，那么我们就更新答案。

最后，我们返回答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为字符串数组 `strs` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def splitLoopedString(self, strs: List[str]) -> str:
        strs = [s[::-1] if s[::-1] > s else s for s in strs]
        ans = ''.join(strs)
        for i, s in enumerate(strs):
            t = ''.join(strs[i + 1:]) + ''.join(strs[: i])
            for j in range(len(s)):
                a = s[j:]
                b = s[:j]
                ans = max(ans, a + t + b)
                ans = max(ans, b[::-1] + t + a[::-1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String splitLoopedString(String[] strs) {
        int n = strs.length;
        for (int i = 0; i < n; ++i) {
            String s = strs[i];
            String t = new StringBuilder(s).reverse().toString();
            if (s.compareTo(t) < 0) {
                strs[i] = t;
            }
        }
        String ans = "";
        for (int i = 0; i < n; ++i) {
            String s = strs[i];
            StringBuilder sb = new StringBuilder();
            for (int j = i + 1; j < n; ++j) {
                sb.append(strs[j]);
            }
            for (int j = 0; j < i; ++j) {
                sb.append(strs[j]);
            }
            String t = sb.toString();
            for (int j = 0; j < s.length(); ++j) {
                String a = s.substring(j);
                String b = s.substring(0, j);
                String cur = a + t + b;
                if (ans.compareTo(cur) < 0) {
                    ans = cur;
                }
                cur = new StringBuilder(b)
                          .reverse()
                          .append(t)
                          .append(new StringBuilder(a).reverse().toString())
                          .toString();
                if (ans.compareTo(cur) < 0) {
                    ans = cur;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string splitLoopedString(vector<string>& strs) {
        for (auto& s : strs) {
            string t{s.rbegin(), s.rend()};
            s = max(s, t);
        }
        int n = strs.size();
        string ans = "";
        for (int i = 0; i < strs.size(); ++i) {
            auto& s = strs[i];
            string t;
            for (int j = i + 1; j < n; ++j) {
                t += strs[j];
            }
            for (int j = 0; j < i; ++j) {
                t += strs[j];
            }
            for (int j = 0; j < s.size(); ++j) {
                auto a = s.substr(j);
                auto b = s.substr(0, j);
                auto cur = a + t + b;
                if (ans < cur) {
                    ans = cur;
                }
                reverse(a.begin(), a.end());
                reverse(b.begin(), b.end());
                cur = b + t + a;
                if (ans < cur) {
                    ans = cur;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func splitLoopedString(strs []string) (ans string) {
	for i, s := range strs {
		t := reverse(s)
		if s < t {
			strs[i] = t
		}
	}
	for i, s := range strs {
		sb := &strings.Builder{}
		for _, w := range strs[i+1:] {
			sb.WriteString(w)
		}
		for _, w := range strs[:i] {
			sb.WriteString(w)
		}
		t := sb.String()
		for j := 0; j < len(s); j++ {
			a, b := s[j:], s[0:j]
			cur := a + t + b
			if ans < cur {
				ans = cur
			}
			cur = reverse(b) + t + reverse(a)
			if ans < cur {
				ans = cur
			}
		}
	}
	return ans
}

func reverse(s string) string {
	t := []byte(s)
	for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
		t[i], t[j] = t[j], t[i]
	}
	return string(t)
}
```

### **...**

```

```

<!-- tabs:end -->
