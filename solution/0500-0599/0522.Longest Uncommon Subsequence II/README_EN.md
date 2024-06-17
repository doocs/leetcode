---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0522.Longest%20Uncommon%20Subsequence%20II/README_EN.md
tags:
    - Array
    - Hash Table
    - Two Pointers
    - String
    - Sorting
---

<!-- problem:start -->

# [522. Longest Uncommon Subsequence II](https://leetcode.com/problems/longest-uncommon-subsequence-ii)

[中文文档](/solution/0500-0599/0522.Longest%20Uncommon%20Subsequence%20II/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Subsequence Judgment

We define a function $check(s, t)$ to determine whether string $s$ is a subsequence of string $t$. We can use a two-pointer approach, initializing two pointers $i$ and $j$ to point to the beginning of strings $s$ and $t$ respectively, then continuously move pointer $j$. If $s[i]$ equals $t[j]$, then move pointer $i$. Finally, check if $i$ equals the length of $s$. If $i$ equals the length of $s$, it means $s$ is a subsequence of $t$.

To determine if string $s$ is unique, we only need to take string $s$ itself and compare it with other strings in the list. If there exists a string for which $s$ is a subsequence, then $s$ is not unique. Otherwise, string $s$ is unique. We take the longest string among all unique strings.

The time complexity is $O(n^2 \times m)$, where $n$ is the length of the list of strings, and $m$ is the average length of the strings. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLUSlength(self, strs: List[str]) -> int:
        def check(s: str, t: str):
            i = j = 0
            while i < len(s) and j < len(t):
                if s[i] == t[j]:
                    i += 1
                j += 1
            return i == len(s)

        ans = -1
        for i, s in enumerate(strs):
            for j, t in enumerate(strs):
                if i != j and check(s, t):
                    break
            else:
                ans = max(ans, len(s))
        return ans
```

#### Java

```java
class Solution {
    public int findLUSlength(String[] strs) {
        int ans = -1;
        int n = strs.length;
        for (int i = 0, j; i < n; ++i) {
            int x = strs[i].length();
            for (j = 0; j < n; ++j) {
                if (i != j && check(strs[i], strs[j])) {
                    x = -1;
                    break;
                }
            }
            ans = Math.max(ans, x);
        }
        return ans;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0;
        for (int j = 0; i < m && j < n; ++j) {
            if (s.charAt(i) == t.charAt(j)) {
                ++i;
            }
        }
        return i == m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLUSlength(vector<string>& strs) {
        int ans = -1;
        int n = strs.size();
        auto check = [&](const string& s, const string& t) {
            int m = s.size(), n = t.size();
            int i = 0;
            for (int j = 0; i < m && j < n; ++j) {
                if (s[i] == t[j]) {
                    ++i;
                }
            }
            return i == m;
        };
        for (int i = 0, j; i < n; ++i) {
            int x = strs[i].size();
            for (j = 0; j < n; ++j) {
                if (i != j && check(strs[i], strs[j])) {
                    x = -1;
                    break;
                }
            }
            ans = max(ans, x);
        }
        return ans;
    }
};
```

#### Go

```go
func findLUSlength(strs []string) int {
	ans := -1
	check := func(s, t string) bool {
		m, n := len(s), len(t)
		i := 0
		for j := 0; i < m && j < n; j++ {
			if s[i] == t[j] {
				i++
			}
		}
		return i == m
	}
	for i, s := range strs {
		x := len(s)
		for j, t := range strs {
			if i != j && check(s, t) {
				x = -1
				break
			}
		}
		ans = max(ans, x)
	}
	return ans
}
```

#### TypeScript

```ts
function findLUSlength(strs: string[]): number {
    const n = strs.length;
    let ans = -1;
    const check = (s: string, t: string): boolean => {
        const [m, n] = [s.length, t.length];
        let i = 0;
        for (let j = 0; i < m && j < n; ++j) {
            if (s[i] === t[j]) {
                ++i;
            }
        }
        return i === m;
    };
    for (let i = 0; i < n; ++i) {
        let x = strs[i].length;
        for (let j = 0; j < n; ++j) {
            if (i !== j && check(strs[i], strs[j])) {
                x = -1;
                break;
            }
        }
        ans = Math.max(ans, x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
