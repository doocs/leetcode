# [剑指 Offer II 015. 字符串中的所有变位词](https://leetcode.cn/problems/VabMRr)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和<b>&nbsp;</b><code>p</code>，找到&nbsp;<code>s</code><strong>&nbsp;</strong>中所有 <code>p</code> 的&nbsp;<strong>变位词&nbsp;</strong>的子串，返回这些子串的起始索引。不考虑答案输出的顺序。</p>

<p><strong>变位词 </strong>指字母相同，但排列不同的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>s = &quot;cbaebabacd&quot;, p = &quot;abc&quot;
<strong>输出: </strong>[0,6]
<strong>解释:</strong>
起始索引等于 0 的子串是 &quot;cba&quot;, 它是 &quot;abc&quot; 的变位词。
起始索引等于 6 的子串是 &quot;bac&quot;, 它是 &quot;abc&quot; 的变位词。
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = &quot;abab&quot;, p = &quot;ab&quot;
<strong>输出: </strong>[0,1,2]
<strong>解释:</strong>
起始索引等于 0 的子串是 &quot;ab&quot;, 它是 &quot;ab&quot; 的变位词。
起始索引等于 1 的子串是 &quot;ba&quot;, 它是 &quot;ab&quot; 的变位词。
起始索引等于 2 的子串是 &quot;ab&quot;, 它是 &quot;ab&quot; 的变位词。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;和 <code>p</code> 仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 438&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/" style="background-color: rgb(255, 255, 255);">https://leetcode.cn/problems/find-all-anagrams-in-a-string/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

和上一题一样的思路，利用固定长度滑动窗口

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        m, n = len(s), len(p)
        if n > m:
            return []
        window, ans = [0 for _ in range(26)], []
        for i in range(n):
            window[ord(p[i]) - ord('a')] += 1
            window[ord(s[i]) - ord('a')] -= 1
        if self.check(window):
            ans.append(0)
        for i in range(n, m):
            window[ord(s[i]) - ord('a')] -= 1
            window[ord(s[i - n]) - ord('a')] += 1
            if self.check(window):
                ans.append(i - n + 1)
        return ans

    def check(self, window: List[int]) -> bool:
        return all([cnt == 0 for cnt in window])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int m = s.length(), n = p.length();
        if (n > m) {
            return ans;
        }
        int[] window = new int[26];
        for (int i = 0; i < n; i++) {
            window[p.charAt(i) - 'a']++;
            window[s.charAt(i) - 'a']--;
        }
        if (check(window)) {
            ans.add(0);
        }
        for (int i = n; i < m; i++) {
            window[s.charAt(i) - 'a']--;
            window[s.charAt(i - n) - 'a']++;
            if (check(window)) {
                ans.add(i - n + 1);
            }
        }
        return ans;
    }

    private boolean check(int[] window) {
        return Arrays.stream(window).allMatch(cnt -> cnt == 0);
    }
}
```

### **Go**

```go
func findAnagrams(s string, p string) []int {
	m, n := len(s), len(p)
	if n > m {
		return []int{}
	}
	ans := make([]int, 0)
	window := make([]int, 26)
	for i := 0; i < n; i++ {
		window[p[i]-'a']++
		window[s[i]-'a']--
	}
	if check(window) {
		ans = append(ans, 0)
	}
	for i := n; i < m; i++ {
		window[s[i]-'a']--
		window[s[i-n]-'a']++
		if check(window) {
			ans = append(ans, i-n+1)
		}
	}
	return ans
}

func check(window []int) bool {
	for _, cnt := range window {
		if cnt != 0 {
			return false
		}
	}
	return true
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        vector<int> res;
        vector<int> hash(26, 0), zero(26, 0);

        if (p.size() > s.size())
            return res;

        for (int i = 0; i < p.size(); i++) {
            hash[p[i] - 'a']++;
            hash[s[i] - 'a']--;
        }

        if (hash == zero)
            res.push_back(0);

        for (int i = p.size(); i < s.size(); i++) {
            hash[s[i] - 'a']--;
            hash[s[i - p.size()] - 'a']++;

            if (hash == zero)
                res.push_back(i - p.size() + 1);
        }

        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
