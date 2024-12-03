---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0809.Expressive%20Words/README_EN.md
tags:
    - Array
    - Two Pointers
    - String
---

<!-- problem:start -->

# [809. Expressive Words](https://leetcode.com/problems/expressive-words)

[中文文档](/solution/0800-0899/0809.Expressive%20Words/README.md)

## Description

<!-- description:start -->

<p>Sometimes people repeat letters to represent extra feeling. For example:</p>

<ul>
	<li><code>&quot;hello&quot; -&gt; &quot;heeellooo&quot;</code></li>
	<li><code>&quot;hi&quot; -&gt; &quot;hiiii&quot;</code></li>
</ul>

<p>In these strings like <code>&quot;heeellooo&quot;</code>, we have groups of adjacent letters that are all the same: <code>&quot;h&quot;</code>, <code>&quot;eee&quot;</code>, <code>&quot;ll&quot;</code>, <code>&quot;ooo&quot;</code>.</p>

<p>You are given a string <code>s</code> and an array of query strings <code>words</code>. A query word is <strong>stretchy</strong> if it can be made to be equal to <code>s</code> by any number of applications of the following extension operation: choose a group consisting of characters <code>c</code>, and add some number of characters <code>c</code> to the group so that the size of the group is <strong>three or more</strong>.</p>

<ul>
	<li>For example, starting with <code>&quot;hello&quot;</code>, we could do an extension on the group <code>&quot;o&quot;</code> to get <code>&quot;hellooo&quot;</code>, but we cannot get <code>&quot;helloo&quot;</code> since the group <code>&quot;oo&quot;</code> has a size less than three. Also, we could do another extension like <code>&quot;ll&quot; -&gt; &quot;lllll&quot;</code> to get <code>&quot;helllllooo&quot;</code>. If <code>s = &quot;helllllooo&quot;</code>, then the query word <code>&quot;hello&quot;</code> would be <strong>stretchy</strong> because of these two extension operations: <code>query = &quot;hello&quot; -&gt; &quot;hellooo&quot; -&gt; &quot;helllllooo&quot; = s</code>.</li>
</ul>

<p>Return <em>the number of query strings that are <strong>stretchy</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;heeellooo&quot;, words = [&quot;hello&quot;, &quot;hi&quot;, &quot;helo&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can extend &quot;e&quot; and &quot;o&quot; in the word &quot;hello&quot; to get &quot;heeellooo&quot;.
We can&#39;t extend &quot;helo&quot; to get &quot;heeellooo&quot; because the group &quot;ll&quot; is not size 3 or more.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;zzzzzyyyyy&quot;, words = [&quot;zzyy&quot;,&quot;zy&quot;,&quot;zyy&quot;]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>s</code> and <code>words[i]</code> consist of lowercase letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal Counting + Two Pointers

We can traverse the array $\textit{words}$, and for each word $t$ in the array, check if $t$ can be expanded to obtain $s$. If it can, increment the answer by one.

Therefore, the key problem is to determine if the word $t$ can be expanded to obtain $s$. We use a function $\textit{check}(s, t)$ to determine this. The implementation logic of the function is as follows:

First, check the length relationship between $s$ and $t$. If the length of $t$ is greater than $s$, return $\textit{false}$ directly. Otherwise, use two pointers $i$ and $j$ to point to $s$ and $t$, respectively, both initially set to $0$.

If the characters pointed to by $i$ and $j$ are different, then $t$ cannot be expanded to obtain $s$, and we return $\textit{false}$. Otherwise, we need to check the relationship between the consecutive occurrence counts of the characters pointed to by $i$ and $j$, denoted as $c_1$ and $c_2$. If $c_1 \lt c_2$ or $c_1 \lt 3$ and $c_1 \neq c_2$, then $t$ cannot be expanded to obtain $s$, and we return $\textit{false}$. Otherwise, move $i$ and $j$ to the right by $c_1$ and $c_2$ times, respectively, and continue checking.

If both $i$ and $j$ reach the end of the strings, then $t$ can be expanded to obtain $s$, and we return $\textit{true}$. Otherwise, we return $\textit{false}$.

The time complexity is $O(n \times m + \sum_{i=0}^{m-1} w_i)$, where $n$ and $m$ are the lengths of the string $s$ and the array $\textit{words}$, respectively, and $w_i$ is the length of the $i$-th word in the array $\textit{words}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def expressiveWords(self, s: str, words: List[str]) -> int:
        def check(s, t):
            m, n = len(s), len(t)
            if n > m:
                return False
            i = j = 0
            while i < m and j < n:
                if s[i] != t[j]:
                    return False
                k = i
                while k < m and s[k] == s[i]:
                    k += 1
                c1 = k - i
                i, k = k, j
                while k < n and t[k] == t[j]:
                    k += 1
                c2 = k - j
                j = k
                if c1 < c2 or (c1 < 3 and c1 != c2):
                    return False
            return i == m and j == n

        return sum(check(s, t) for t in words)
```

#### Java

```java
class Solution {
    public int expressiveWords(String s, String[] words) {
        int ans = 0;
        for (String t : words) {
            if (check(s, t)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) {
            return false;
        }
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            int k = i;
            while (k < m && s.charAt(k) == s.charAt(i)) {
                ++k;
            }
            int c1 = k - i;
            i = k;
            k = j;
            while (k < n && t.charAt(k) == t.charAt(j)) {
                ++k;
            }
            int c2 = k - j;
            j = k;
            if (c1 < c2 || (c1 < 3 && c1 != c2)) {
                return false;
            }
        }
        return i == m && j == n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int expressiveWords(string s, vector<string>& words) {
        auto check = [](string& s, string& t) -> int {
            int m = s.size(), n = t.size();
            if (n > m) return 0;
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (s[i] != t[j]) return 0;
                int k = i;
                while (k < m && s[k] == s[i]) ++k;
                int c1 = k - i;
                i = k, k = j;
                while (k < n && t[k] == t[j]) ++k;
                int c2 = k - j;
                j = k;
                if (c1 < c2 || (c1 < 3 && c1 != c2)) return 0;
            }
            return i == m && j == n;
        };

        int ans = 0;
        for (string& t : words) ans += check(s, t);
        return ans;
    }
};
```

#### Go

```go
func expressiveWords(s string, words []string) (ans int) {
	check := func(s, t string) bool {
		m, n := len(s), len(t)
		if n > m {
			return false
		}
		i, j := 0, 0
		for i < m && j < n {
			if s[i] != t[j] {
				return false
			}
			k := i
			for k < m && s[k] == s[i] {
				k++
			}
			c1 := k - i
			i, k = k, j
			for k < n && t[k] == t[j] {
				k++
			}
			c2 := k - j
			j = k
			if c1 < c2 || (c1 != c2 && c1 < 3) {
				return false
			}
		}
		return i == m && j == n
	}
	for _, t := range words {
		if check(s, t) {
			ans++
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
