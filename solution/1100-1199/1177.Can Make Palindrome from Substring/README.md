# [1177. 构建回文串检测](https://leetcode.cn/problems/can-make-palindrome-from-substring)

[English Version](/solution/1100-1199/1177.Can%20Make%20Palindrome%20from%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>，请你对&nbsp;<code>s</code>&nbsp;的子串进行检测。</p>

<p>每次检测，待检子串都可以表示为&nbsp;<code>queries[i] = [left, right, k]</code>。我们可以 <strong>重新排列</strong> 子串&nbsp;<code>s[left], ..., s[right]</code>，并从中选择 <strong>最多</strong> <code>k</code>&nbsp;项替换成任何小写英文字母。&nbsp;</p>

<p>如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为&nbsp;<code>true</code>，否则结果为&nbsp;<code>false</code>。</p>

<p>返回答案数组&nbsp;<code>answer[]</code>，其中&nbsp;<code>answer[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个待检子串&nbsp;<code>queries[i]</code>&nbsp;的检测结果。</p>

<p>注意：在替换时，子串中的每个字母都必须作为 <strong>独立的</strong> 项进行计数，也就是说，如果&nbsp;<code>s[left..right] = &quot;aaa&quot;</code>&nbsp;且&nbsp;<code>k = 2</code>，我们只能替换其中的两个字母。（另外，任何检测都不会修改原始字符串 <code>s</code>，可以认为每次检测都是独立的）</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>s = &quot;abcda&quot;, queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
<strong>输出：</strong>[true,false,false,true,true]
<strong>解释：</strong>
queries[0] : 子串 = &quot;d&quot;，回文。
queries[1] :&nbsp;子串 = &quot;bc&quot;，不是回文。
queries[2] :&nbsp;子串 = &quot;abcd&quot;，只替换 1 个字符是变不成回文串的。
queries[3] :&nbsp;子串 = &quot;abcd&quot;，可以变成回文的 &quot;abba&quot;。 也可以变成 &quot;baab&quot;，先重新排序变成 &quot;bacd&quot;，然后把 &quot;cd&quot; 替换为 &quot;ab&quot;。
queries[4] :&nbsp;子串 = &quot;abcda&quot;，可以变成回文的 &quot;abcba&quot;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length,&nbsp;queries.length&nbsp;&lt;= 10^5</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;= queries[i][1] &lt;&nbsp;s.length</code></li>
	<li><code>0 &lt;= queries[i][2] &lt;= s.length</code></li>
	<li><code>s</code> 中只有小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和**

我们可以使用前缀和的思想，预处理出字符串 $s$ 中每个位置的字符出现次数，即 $cnt[i][j]$ 表示字符串 $s$ 中前 $i$ 个字符中第 $j$ 个字母出现的次数。

对于每个查询 $[left, right, k]$，我们可以利用前缀和计算出 $s[left..right]$ 中每个字母出现的次数，统计出现次数为奇数的字母个数 $x$，则需要替换的次数为 $\frac{x}{2}$，如果 $\frac{x}{2} \leq k$，则可以将 $s[left..right]$ 变成回文串。

时间复杂度 $O((n + m) \times C)$，空间复杂度 $O(n \times C)$，其中 $n$ 和 $m$ 分别为字符串 $s$ 和查询数组 $queries$ 的长度，而 $C$ 为字符集大小。本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canMakePaliQueries(self, s: str, queries: List[List[int]]) -> List[bool]:
        n = len(s)
        cnt = [[0] * 26]
        for i, c in enumerate(s, 1):
            j = ord(c) - ord('a')
            t = cnt[-1][:]
            t[j] += 1
            cnt.append(t)
        ans = []
        for left, right, k in queries:
            x = sum((b - a) & 1 for a, b in zip(cnt[right + 1], cnt[left]))
            ans.append(x // 2 <= k)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] cnt = new int[n + 1][26];
        for (int i = 1; i <= n; ++i) {
            int j = s.charAt(i - 1) - 'a';
            for (int k = 0; k < 26; ++k) {
                cnt[i][k] = cnt[i - 1][k];
            }
            cnt[i][j]++;
        }
        List<Boolean> ans = new ArrayList<>();
        for (var q : queries) {
            int left = q[0], right = q[1], k = q[2];
            int x = 0;
            for (int j = 0; j < 26; ++j) {
                x += (cnt[right + 1][j] - cnt[left][j]) & 1;
            }
            ans.add(x / 2 <= k);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<bool> canMakePaliQueries(string s, vector<vector<int>>& queries) {
        int n = s.size();
        int cnt[n + 1][26];
        memset(cnt, 0, sizeof cnt);
        for (int i = 1; i <= n; ++i) {
            int j = s[i - 1] - 'a';
            for (int k = 0; k < 26; ++k) {
                cnt[i][k] = cnt[i - 1][k];
            }
            cnt[i][j]++;
        }
        vector<bool> ans;
        for (auto& q : queries) {
            int left = q[0], right = q[1], k = q[2];
            int x = 0;
            for (int j = 0; j < 26; ++j) {
                x += (cnt[right + 1][j] - cnt[left][j]) & 1;
            }
            ans.emplace_back(x / 2 <= k);
        }
        return ans;
    }
};
```

### **Go**

```go
func canMakePaliQueries(s string, queries [][]int) (ans []bool) {
	n := len(s)
	cnt := make([][26]int, n+1)
	for i := 1; i <= n; i++ {
		j := s[i-1] - 'a'
		for k := 0; k < 26; k++ {
			cnt[i][k] = cnt[i-1][k]
		}
		cnt[i][j]++
	}
	for _, q := range queries {
		left, right, k := q[0], q[1], q[2]
		x := 0
		for j := 0; j < 26; j++ {
			x += (cnt[right+1][j] - cnt[left][j]) & 1
		}
		ans = append(ans, x/2 <= k)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
