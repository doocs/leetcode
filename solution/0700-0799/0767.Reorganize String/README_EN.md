# [767. Reorganize String](https://leetcode.com/problems/reorganize-string)

[中文文档](/solution/0700-0799/0767.Reorganize%20String/README.md)

## Description

<p>Given a string <code>s</code>, rearrange the characters of <code>s</code> so that any two adjacent characters are not the same.</p>

<p>Return <em>any possible rearrangement of</em> <code>s</code> <em>or return</em> <code>&quot;&quot;</code> <em>if not possible</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "aab"
<strong>Output:</strong> "aba"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "aaab"
<strong>Output:</strong> ""
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reorganizeString(self, s: str) -> str:
        n = len(s)
        cnt = Counter(s)
        mx = max(cnt.values())
        if mx > (n + 1) // 2:
            return ''
        i = 0
        ans = [None] * n
        for k, v in cnt.most_common():
            while v:
                ans[i] = k
                v -= 1
                i += 2
                if i >= n:
                    i = 1
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String reorganizeString(String s) {
        int[] cnt = new int[26];
        int mx = 0;
        for (char c : s.toCharArray()) {
            int t = c - 'a';
            ++cnt[t];
            mx = Math.max(mx, cnt[t]);
        }
        int n = s.length();
        if (mx > (n + 1) / 2) {
            return "";
        }
        int k = 0;
        for (int v : cnt) {
            if (v > 0) {
                ++k;
            }
        }
        int[][] m = new int[k][2];
        k = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                m[k++] = new int[]{cnt[i], i};
            }
        }
        Arrays.sort(m, (a, b) -> b[0] - a[0]);
        k = 0;
        StringBuilder ans = new StringBuilder(s);
        for (int[] e : m) {
            int v = e[0], i = e[1];
            while (v-- > 0) {
                ans.setCharAt(k, (char) ('a' + i));
                k += 2;
                if (k >= n) {
                    k = 1;
                }
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reorganizeString(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        int mx = *max_element(cnt.begin(), cnt.end());
        int n = s.size();
        if (mx > (n + 1) / 2) return "";
        vector<vector<int>> m;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i]) m.push_back({cnt[i], i});
        }
        sort(m.begin(), m.end());
        reverse(m.begin(), m.end());
        string ans = s;
        int k = 0;
        for (auto& e : m) {
            int v = e[0], i = e[1];
            while (v--) {
                ans[k] = 'a' + i;
                k += 2;
                if (k >= n) k = 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func reorganizeString(s string) string {
	cnt := make([]int, 26)
	mx := 0
	for _, c := range s {
		t := c - 'a'
		cnt[t]++
		mx = max(mx, cnt[t])
	}
	n := len(s)
	if mx > (n+1)/2 {
		return ""
	}
	m := [][]int{}
	for i, v := range cnt {
		if v > 0 {
			m = append(m, []int{v, i})
		}
	}
	sort.Slice(m, func(i, j int) bool {
		return m[i][0] > m[j][0]
	})
	ans := make([]byte, n)
	k := 0
	for _, e := range m {
		v, i := e[0], e[1]
		for v > 0 {
			ans[k] = byte('a' + i)
			k += 2
			if k >= n {
				k = 1
			}
			v--
		}
	}
	return string(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
