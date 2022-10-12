# [267. Palindrome Permutation II](https://leetcode.com/problems/palindrome-permutation-ii)

[中文文档](/solution/0200-0299/0267.Palindrome%20Permutation%20II/README.md)

## Description

<p>Given a string s, return <em>all the palindromic permutations (without duplicates) of it</em>.</p>

<p>You may return the answer in <strong>any order</strong>. If <code>s</code> has no palindromic permutation, return an empty list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "aabb"
<strong>Output:</strong> ["abba","baab"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "abc"
<strong>Output:</strong> []
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 16</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generatePalindromes(self, s: str) -> List[str]:
        def dfs(t):
            if len(t) == len(s):
                ans.append(t)
                return
            for c, v in cnt.items():
                if v > 1:
                    cnt[c] -= 2
                    dfs(c + t + c)
                    cnt[c] += 2

        cnt = Counter(s)
        mid = ''
        for c, v in cnt.items():
            if v & 1:
                if mid:
                    return []
                mid = c
                cnt[c] -= 1
        ans = []
        dfs(mid)
        return ans
```

### **Java**

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private int[] cnt = new int[26];
    private int n;

    public List<String> generatePalindromes(String s) {
        n = s.length();
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        String mid = "";
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] % 2 == 1) {
                if (!"".equals(mid)) {
                    return ans;
                }
                mid = String.valueOf((char) (i + 'a'));
            }
        }
        dfs(mid);
        return ans;
    }

    private void dfs(String t) {
        if (t.length() == n) {
            ans.add(t);
            return;
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 1) {
                String c = String.valueOf((char) (i + 'a'));
                cnt[i] -= 2;
                dfs(c + t + c);
                cnt[i] += 2;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;
    vector<string> ans;
    unordered_map<char, int> cnt;

    vector<string> generatePalindromes(string s) {
        n = s.size();
        for (char c : s) ++cnt[c];
        string mid = "";
        for (auto& [k, v] : cnt) {
            if (v & 1) {
                if (mid != "") {
                    return ans;
                }
                mid += k;
            }
        }
        dfs(mid);
        return ans;
    }

    void dfs(string t) {
        if (t.size() == n) {
            ans.push_back(t);
            return;
        }
        for (auto& [k, v] : cnt) {
            if (v > 1) {
                v -= 2;
                dfs(k + t + k);
                v += 2;
            }
        }
    }
};
```

### **Go**

```go
func generatePalindromes(s string) []string {
	cnt := map[byte]int{}
	for i := range s {
		cnt[s[i]]++
	}
	mid := ""
	ans := []string{}
	for k, v := range cnt {
		if v%2 == 1 {
			if mid != "" {
				return ans
			}
			mid = string(k)
		}
	}
	var dfs func(t string)
	dfs = func(t string) {
		if len(t) == len(s) {
			ans = append(ans, t)
			return
		}
		for k, v := range cnt {
			if v > 1 {
				cnt[k] -= 2
				c := string(k)
				dfs(c + t + c)
				cnt[k] += 2
			}
		}
	}
	dfs(mid)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
