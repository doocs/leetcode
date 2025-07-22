---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1593.Split%20a%20String%20Into%20the%20Max%20Number%20of%20Unique%20Substrings/README_EN.md
rating: 1739
source: Weekly Contest 207 Q2
tags:
    - Hash Table
    - String
    - Backtracking
---

<!-- problem:start -->

# [1593. Split a String Into the Max Number of Unique Substrings](https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings)

[中文文档](/solution/1500-1599/1593.Split%20a%20String%20Into%20the%20Max%20Number%20of%20Unique%20Substrings/README.md)

## Description

<!-- description:start -->

<p>Given a string&nbsp;<code>s</code><var>,</var>&nbsp;return <em>the maximum&nbsp;number of unique substrings that the given string can be split into</em>.</p>

<p>You can split string&nbsp;<code>s</code> into any list of&nbsp;<strong>non-empty substrings</strong>, where the concatenation of the substrings forms the original string.&nbsp;However, you must split the substrings such that all of them are <strong>unique</strong>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ababccc&quot;
<strong>Output:</strong> 5
<strong>Explanation</strong>: One way to split maximally is [&#39;a&#39;, &#39;b&#39;, &#39;ab&#39;, &#39;c&#39;, &#39;cc&#39;]. Splitting like [&#39;a&#39;, &#39;b&#39;, &#39;a&#39;, &#39;b&#39;, &#39;c&#39;, &#39;cc&#39;] is not valid as you have &#39;a&#39; and &#39;b&#39; multiple times.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aba&quot;
<strong>Output:</strong> 2
<strong>Explanation</strong>: One way to split maximally is [&#39;a&#39;, &#39;ba&#39;].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aa&quot;
<strong>Output:</strong> 1
<strong>Explanation</strong>: It is impossible to split the string any further.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>
	<p><code>1 &lt;= s.length&nbsp;&lt;= 16</code></p>
	</li>
	<li>
	<p><code>s</code> contains&nbsp;only lower case English letters.</p>
	</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Backtracking + Pruning

We define a hash table $\textit{st}$ to store the currently split substrings. Then we use a depth-first search approach to try to split the string $\textit{s}$ into several unique substrings.

Specifically, we design a function $\text{dfs}(i)$, which means we are considering splitting $\textit{s}[i:]$.

In the function $\text{dfs}(i)$, we first check if the number of substrings already split plus the remaining characters is less than or equal to the current answer. If so, there is no need to continue splitting, and we return directly. If $i \geq n$, it means we have completed the splitting of the entire string, and we update the answer to the maximum of the current number of substrings and the answer. Otherwise, we enumerate the end position $j$ (exclusive) of the current substring and check if $\textit{s}[i..j)$ has already been split. If not, we add it to the hash table $\textit{st}$ and continue to recursively consider splitting the remaining part. After the recursive call, we need to remove $\textit{s}[i..j)$ from the hash table $\textit{st}$.

Finally, we return the answer.

The time complexity is $O(n^2 \times 2^n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $\textit{s}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxUniqueSplit(self, s: str) -> int:
        def dfs(i: int):
            nonlocal ans
            if len(st) + len(s) - i <= ans:
                return
            if i >= len(s):
                ans = max(ans, len(st))
                return
            for j in range(i + 1, len(s) + 1):
                if s[i:j] not in st:
                    st.add(s[i:j])
                    dfs(j)
                    st.remove(s[i:j])

        ans = 0
        st = set()
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private Set<String> st = new HashSet<>();
    private int ans;
    private String s;

    public int maxUniqueSplit(String s) {
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (st.size() + s.length() - i <= ans) {
            return;
        }
        if (i >= s.length()) {
            ans = Math.max(ans, st.size());
            return;
        }
        for (int j = i + 1; j <= s.length(); ++j) {
            String t = s.substring(i, j);
            if (st.add(t)) {
                dfs(j);
                st.remove(t);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxUniqueSplit(string s) {
        unordered_set<string> st;
        int n = s.size();
        int ans = 0;
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (st.size() + n - i <= ans) {
                return;
            }
            if (i >= n) {
                ans = max(ans, (int) st.size());
                return;
            }
            for (int j = i + 1; j <= n; ++j) {
                string t = s.substr(i, j - i);
                if (!st.contains(t)) {
                    st.insert(t);
                    dfs(j);
                    st.erase(t);
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func maxUniqueSplit(s string) (ans int) {
	st := map[string]bool{}
	n := len(s)
	var dfs func(int)
	dfs = func(i int) {
		if len(st)+n-i <= ans {
			return
		}
		if i >= n {
			ans = max(ans, len(st))
			return
		}
		for j := i + 1; j <= n; j++ {
			if t := s[i:j]; !st[t] {
				st[t] = true
				dfs(j)
				delete(st, t)
			}
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function maxUniqueSplit(s: string): number {
    const n = s.length;
    const st = new Set<string>();
    let ans = 0;
    const dfs = (i: number): void => {
        if (st.size + n - i <= ans) {
            return;
        }
        if (i >= n) {
            ans = Math.max(ans, st.size);
            return;
        }
        for (let j = i + 1; j <= n; ++j) {
            const t = s.slice(i, j);
            if (!st.has(t)) {
                st.add(t);
                dfs(j);
                st.delete(t);
            }
        }
    };
    dfs(0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
