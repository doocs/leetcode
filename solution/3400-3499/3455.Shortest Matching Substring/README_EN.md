---
comments: true
difficulty: Hard
rating: 2303
source: Biweekly Contest 150 Q4
tags:
    - Two Pointers
    - String
    - Binary Search
    - String Matching
---

<!-- problem:start -->

# [3455. Shortest Matching Substring](https://leetcode.com/problems/shortest-matching-substring)

[中文文档](/solution/3400-3499/3455.Shortest%20Matching%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and a pattern string <code>p</code>, where <code>p</code> contains <strong>exactly two</strong> <code>&#39;*&#39;</code> characters.</p>

<p>The <code>&#39;*&#39;</code> in <code>p</code> matches any sequence of zero or more characters.</p>

<p>Return the length of the <strong>shortest</strong> <span data-keyword="substring">substring</span> in <code>s</code> that matches <code>p</code>. If there is no such substring, return -1.</p>
<strong>Note:</strong> The empty substring is considered valid.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abaacbaecebce&quot;, p = &quot;ba*c*ce&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The shortest matching substring of <code>p</code> in <code>s</code> is <code>&quot;<u><strong>ba</strong></u>e<u><strong>c</strong></u>eb<u><strong>ce</strong></u>&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;baccbaadbc&quot;, p = &quot;cc*baa*adb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no matching substring in <code>s</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;a&quot;, p = &quot;**&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The empty substring is the shortest matching substring.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;madlogic&quot;, p = &quot;*adlogi*&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The shortest matching substring of <code>p</code> in <code>s</code> is <code>&quot;<strong><u>adlogi</u></strong>&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= p.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
	<li><code>p</code> contains only lowercase English letters and exactly two <code>&#39;*&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> $p$ contains exactly two stars and splits into three literals $a$, $b$, $c$. $|s|,|p|\le 10^5$ forbids a naive search from every start.
>
> The shortest match is determined by occurrence positions: after one $a$, take the earliest later $b$, then the earliest later $c$.
>
> KMP or Z-algorithm lists every occurrence of the three pieces. A two-pointer sweep over $a$'s starts advances $b$ and $c$. The length is the right end of $c$ minus the left end of $a$, or $-1$ if none exists.

<!-- thinking:end -->

Split the pattern on its two stars into literals $a$, $b$, and $c$. Any of them may be empty. KMP lists every starting index of each literal in $s$. An empty literal matches at every index $0,1,\ldots,n$.

For each start $i$ of $a$, advance a pointer to the earliest start $j$ of $b$ with $j\ge i+|a|$, then to the earliest start $k$ of $c$ with $k\ge j+|b|$. That match has length $k+|c|-i$. The minimum over all starts is the answer, or $-1$ when no match exists.

A later $b$ can only push $c$ further right, so the earliest $b$ and the earliest $c$ are optimal for a fixed $i$.

The time complexity is $O(n+m)$ and the space complexity is $O(n)$, where $n$ and $m$ are the lengths of $s$ and $p$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestMatchingSubstring(self, s: str, p: str) -> int:
        def starts(pat: str):
            if not pat:
                return list(range(len(s) + 1))
            m = len(pat)
            lps = [0] * m
            length = 0
            i = 1
            while i < m:
                if pat[i] == pat[length]:
                    length += 1
                    lps[i] = length
                    i += 1
                elif length:
                    length = lps[length - 1]
                else:
                    i += 1
            res = []
            i = j = 0
            n = len(s)
            while i < n:
                if s[i] == pat[j]:
                    i += 1
                    j += 1
                    if j == m:
                        res.append(i - m)
                        j = lps[j - 1]
                elif j:
                    j = lps[j - 1]
                else:
                    i += 1
            return res

        a, b, c = p.split('*')
        A, B, C = starts(a), starts(b), starts(c)
        la, lb, lc = len(a), len(b), len(c)
        ans = len(s) + 1
        j = k = 0
        for i in A:
            while j < len(B) and B[j] < i + la:
                j += 1
            if j == len(B):
                break
            while k < len(C) and C[k] < B[j] + lb:
                k += 1
            if k == len(C):
                break
            ans = min(ans, C[k] + lc - i)
        return -1 if ans > len(s) else ans
```

#### Java

```java
class Solution {
    public int shortestMatchingSubstring(String s, String p) {
        int star = p.indexOf('*');
        int star2 = p.indexOf('*', star + 1);
        String a = p.substring(0, star);
        String b = p.substring(star + 1, star2);
        String c = p.substring(star2 + 1);
        int[] A = starts(s, a);
        int[] B = starts(s, b);
        int[] C = starts(s, c);
        int ans = s.length() + 1;
        int j = 0, k = 0;
        for (int i : A) {
            while (j < B.length && B[j] < i + a.length()) {
                ++j;
            }
            if (j == B.length) {
                break;
            }
            while (k < C.length && C[k] < B[j] + b.length()) {
                ++k;
            }
            if (k == C.length) {
                break;
            }
            ans = Math.min(ans, C[k] + c.length() - i);
        }
        return ans > s.length() ? -1 : ans;
    }

    private int[] starts(String s, String pat) {
        int n = s.length();
        if (pat.isEmpty()) {
            int[] res = new int[n + 1];
            for (int i = 0; i <= n; ++i) {
                res[i] = i;
            }
            return res;
        }
        int m = pat.length();
        int[] lps = new int[m];
        for (int i = 1, len = 0; i < m;) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                ++i;
            }
        }
        int[] tmp = new int[n];
        int cnt = 0;
        for (int i = 0, j = 0; i < n;) {
            if (s.charAt(i) == pat.charAt(j)) {
                ++i;
                ++j;
                if (j == m) {
                    tmp[cnt++] = i - m;
                    j = lps[j - 1];
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                ++i;
            }
        }
        int[] res = new int[cnt];
        System.arraycopy(tmp, 0, res, 0, cnt);
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int shortestMatchingSubstring(string s, string p) {
        int star = p.find('*');
        int star2 = p.find('*', star + 1);
        string a = p.substr(0, star);
        string b = p.substr(star + 1, star2 - star - 1);
        string c = p.substr(star2 + 1);
        vector<int> A = starts(s, a), B = starts(s, b), C = starts(s, c);
        int ans = s.size() + 1;
        int j = 0, k = 0;
        for (int i : A) {
            while (j < (int) B.size() && B[j] < i + (int) a.size()) {
                ++j;
            }
            if (j == (int) B.size()) {
                break;
            }
            while (k < (int) C.size() && C[k] < B[j] + (int) b.size()) {
                ++k;
            }
            if (k == (int) C.size()) {
                break;
            }
            ans = min(ans, C[k] + (int) c.size() - i);
        }
        return ans > (int) s.size() ? -1 : ans;
    }

private:
    vector<int> starts(const string& s, const string& pat) {
        int n = s.size();
        if (pat.empty()) {
            vector<int> res(n + 1);
            iota(res.begin(), res.end(), 0);
            return res;
        }
        int m = pat.size();
        vector<int> lps(m);
        for (int i = 1, len = 0; i < m;) {
            if (pat[i] == pat[len]) {
                lps[i++] = ++len;
            } else if (len) {
                len = lps[len - 1];
            } else {
                ++i;
            }
        }
        vector<int> res;
        for (int i = 0, j = 0; i < n;) {
            if (s[i] == pat[j]) {
                ++i;
                ++j;
                if (j == m) {
                    res.push_back(i - m);
                    j = lps[j - 1];
                }
            } else if (j) {
                j = lps[j - 1];
            } else {
                ++i;
            }
        }
        return res;
    }
};
```

#### Go

```go
func shortestMatchingSubstring(s string, p string) int {
	star := 0
	for p[star] != '*' {
		star++
	}
	star2 := star + 1
	for p[star2] != '*' {
		star2++
	}
	a, b, c := p[:star], p[star+1:star2], p[star2+1:]
	A, B, C := matchStarts(s, a), matchStarts(s, b), matchStarts(s, c)
	ans := len(s) + 1
	j, k := 0, 0
	for _, i := range A {
		for j < len(B) && B[j] < i+len(a) {
			j++
		}
		if j == len(B) {
			break
		}
		for k < len(C) && C[k] < B[j]+len(b) {
			k++
		}
		if k == len(C) {
			break
		}
		ans = min(ans, C[k]+len(c)-i)
	}
	if ans > len(s) {
		return -1
	}
	return ans
}

func matchStarts(s, pat string) []int {
	n := len(s)
	if pat == "" {
		res := make([]int, n+1)
		for i := 0; i <= n; i++ {
			res[i] = i
		}
		return res
	}
	m := len(pat)
	lps := make([]int, m)
	for i, length := 1, 0; i < m; {
		if pat[i] == pat[length] {
			length++
			lps[i] = length
			i++
		} else if length > 0 {
			length = lps[length-1]
		} else {
			i++
		}
	}
	res := make([]int, 0)
	for i, j := 0, 0; i < n; {
		if s[i] == pat[j] {
			i++
			j++
			if j == m {
				res = append(res, i-m)
				j = lps[j-1]
			}
		} else if j > 0 {
			j = lps[j-1]
		} else {
			i++
		}
	}
	return res
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
