# [522. Longest Uncommon Subsequence II](https://leetcode.com/problems/longest-uncommon-subsequence-ii)

[中文文档](/solution/0500-0599/0522.Longest%20Uncommon%20Subsequence%20II/README.md)

## Description

<p>Given an array of strings <code>strs</code>, return <em>the length of the <strong>longest uncommon subsequence</strong> between them</em>. If the longest uncommon subsequence does not exist, return <code>-1</code>.</p>

<p>An <strong>uncommon subsequence</strong> between an array of strings is a string that is a <strong>subsequence of one string but not the others</strong>.</p>

<p>A <strong>subsequence</strong> of a string <code>s</code> is a string that can be obtained after deleting any number of characters from <code>s</code>.</p>

<ul>
	<li>For example, <code>&quot;abc&quot;</code> is a subsequence of <code>&quot;aebdc&quot;</code> because you can delete the underlined characters in <code>&quot;a<u>e</u>b<u>d</u>c&quot;</code> to get <code>&quot;abc&quot;</code>. Other subsequences of <code>&quot;aebdc&quot;</code> include <code>&quot;aebdc&quot;</code>, <code>&quot;aeb&quot;</code>, and <code>&quot;&quot;</code> (empty string).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> strs = ["aba","cdc","eae"]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> strs = ["aaa","aaa","aa"]
<strong>Output:</strong> -1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= strs.length &lt;= 50</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 10</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLUSlength(self, strs: List[str]) -> int:
        def check(a, b):
            i = j = 0
            while i < len(a) and j < len(b):
                if a[i] == b[j]:
                    j += 1
                i += 1
            return j == len(b)

        n = len(strs)
        ans = -1

        for i in range(n):
            j = 0
            while j < n:
                if i == j or not check(strs[j], strs[i]):
                    j += 1
                else:
                    break
            if j == n:
                ans = max(ans, len(strs[i]))
        return ans
```

### **Java**

```java
class Solution {
    public int findLUSlength(String[] strs) {
        int ans = -1;
        for (int i = 0, j = 0, n = strs.length; i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                if (check(strs[j], strs[i])) {
                    break;
                }
            }
            if (j == n) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    private boolean check(String a, String b) {
        int j = 0;
        for (int i = 0; i < a.length() && j < b.length(); ++i) {
            if (a.charAt(i) == b.charAt(j)) {
                ++j;
            }
        }
        return j == b.length();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLUSlength(vector<string>& strs) {
        int ans = -1;
        for (int i = 0, j = 0, n = strs.size(); i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) continue;
                if (check(strs[j], strs[i])) break;
            }
            if (j == n) ans = max(ans, (int)strs[i].size());
        }
        return ans;
    }

    bool check(string a, string b) {
        int j = 0;
        for (int i = 0; i < a.size() && j < b.size(); ++i)
            if (a[i] == b[j]) ++j;
        return j == b.size();
    }
};
```

### **Go**

```go
func findLUSlength(strs []string) int {
	check := func(a, b string) bool {
		j := 0
		for i := 0; i < len(a) && j < len(b); i++ {
			if a[i] == b[j] {
				j++
			}
		}
		return j == len(b)
	}

	ans := -1
	for i, j, n := 0, 0, len(strs); i < n; i++ {
		for j = 0; j < n; j++ {
			if i == j {
				continue
			}
			if check(strs[j], strs[i]) {
				break
			}
		}
		if j == n && ans < len(strs[i]) {
			ans = len(strs[i])
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
