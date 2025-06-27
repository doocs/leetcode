---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2014.Longest%20Subsequence%20Repeated%20k%20Times/README_EN.md
rating: 2558
source: Weekly Contest 259 Q4
tags:
    - Greedy
    - String
    - Backtracking
    - Counting
    - Enumeration
---

<!-- problem:start -->

# [2014. Longest Subsequence Repeated k Times](https://leetcode.com/problems/longest-subsequence-repeated-k-times)

[中文文档](/solution/2000-2099/2014.Longest%20Subsequence%20Repeated%20k%20Times/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code>, and an integer <code>k</code>. You are tasked to find the <strong>longest subsequence repeated</strong> <code>k</code> times in string <code>s</code>.</p>

<p>A <strong>subsequence</strong> is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.</p>

<p>A subsequence <code>seq</code> is <strong>repeated</strong> <code>k</code> times in the string <code>s</code> if <code>seq * k</code> is a subsequence of <code>s</code>, where <code>seq * k</code> represents a string constructed by concatenating <code>seq</code> <code>k</code> times.</p>

<ul>
	<li>For example, <code>&quot;bba&quot;</code> is repeated <code>2</code> times in the string <code>&quot;bababcba&quot;</code>, because the string <code>&quot;bbabba&quot;</code>, constructed by concatenating <code>&quot;bba&quot;</code> <code>2</code> times, is a subsequence of the string <code>&quot;<strong><u>b</u></strong>a<strong><u>bab</u></strong>c<strong><u>ba</u></strong>&quot;</code>.</li>
</ul>

<p>Return <em>the <strong>longest subsequence repeated</strong> </em><code>k</code><em> times in string </em><code>s</code><em>. If multiple such subsequences are found, return the <strong>lexicographically largest</strong> one. If there is no such subsequence, return an <strong>empty</strong> string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="example 1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2014.Longest%20Subsequence%20Repeated%20k%20Times/images/longest-subsequence-repeat-k-times.png" style="width: 457px; height: 99px;" />
<pre>
<strong>Input:</strong> s = &quot;letsleetcode&quot;, k = 2
<strong>Output:</strong> &quot;let&quot;
<strong>Explanation:</strong> There are two longest subsequences repeated 2 times: &quot;let&quot; and &quot;ete&quot;.
&quot;let&quot; is the lexicographically largest one.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bb&quot;, k = 2
<strong>Output:</strong> &quot;b&quot;
<strong>Explanation:</strong> The longest subsequence repeated 2 times is &quot;b&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ab&quot;, k = 2
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There is no subsequence repeated 2 times. Empty string is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>2 &lt;= n, k &lt;= 2000</code></li>
	<li><code>2 &lt;= n &lt; k * 8</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

We can first count the occurrences of each character in the string, and then store the characters that appear at least $k$ times in a list $\textit{cs}$ in ascending order. Next, we can use BFS to enumerate all possible subsequences.

We define a queue $\textit{q}$, initially putting the empty string into the queue. Then, we take out a string $\textit{cur}$ from the queue and try to append each character $c \in \textit{cs}$ to the end of $\textit{cur}$ to form a new string $\textit{nxt}$. If $\textit{nxt}$ is a subsequence that can be repeated $k$ times, we add it to the answer and put $\textit{nxt}$ back into the queue for further processing.

We need an auxiliary function $\textit{check(t, k)}$ to determine whether the string $\textit{t}$ is a repeated $k$ times subsequence of string $s$. Specifically, we can use two pointers to traverse $s$ and $\textit{t}$. If we can find all characters of $\textit{t}$ in $s$ and repeat this process $k$ times, then return $\textit{true}$; otherwise, return $\textit{false}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubsequenceRepeatedK(self, s: str, k: int) -> str:
        def check(t: str, k: int) -> bool:
            i = 0
            for c in s:
                if c == t[i]:
                    i += 1
                    if i == len(t):
                        k -= 1
                        if k == 0:
                            return True
                        i = 0
            return False

        cnt = Counter(s)
        cs = [c for c in ascii_lowercase if cnt[c] >= k]
        q = deque([""])
        ans = ""
        while q:
            cur = q.popleft()
            for c in cs:
                nxt = cur + c
                if check(nxt, k):
                    ans = nxt
                    q.append(nxt)
        return ans
```

#### Java

```java
class Solution {
    private char[] s;

    public String longestSubsequenceRepeatedK(String s, int k) {
        this.s = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : this.s) {
            cnt[c - 'a']++;
        }

        List<Character> cs = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; ++c) {
            if (cnt[c - 'a'] >= k) {
                cs.add(c);
            }
        }
        Deque<String> q = new ArrayDeque<>();
        q.offer("");
        String ans = "";
        while (!q.isEmpty()) {
            String cur = q.poll();
            for (char c : cs) {
                String nxt = cur + c;
                if (check(nxt, k)) {
                    ans = nxt;
                    q.offer(nxt);
                }
            }
        }
        return ans;
    }

    private boolean check(String t, int k) {
        int i = 0;
        for (char c : s) {
            if (c == t.charAt(i)) {
                i++;
                if (i == t.length()) {
                    if (--k == 0) {
                        return true;
                    }
                    i = 0;
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string longestSubsequenceRepeatedK(string s, int k) {
        auto check = [&](const string& t, int k) -> bool {
            int i = 0;
            for (char c : s) {
                if (c == t[i]) {
                    i++;
                    if (i == t.size()) {
                        if (--k == 0) {
                            return true;
                        }
                        i = 0;
                    }
                }
            }
            return false;
        };
        int cnt[26] = {};
        for (char c : s) {
            cnt[c - 'a']++;
        }

        vector<char> cs;
        for (char c = 'a'; c <= 'z'; ++c) {
            if (cnt[c - 'a'] >= k) {
                cs.push_back(c);
            }
        }

        queue<string> q;
        q.push("");
        string ans;
        while (!q.empty()) {
            string cur = q.front();
            q.pop();
            for (char c : cs) {
                string nxt = cur + c;
                if (check(nxt, k)) {
                    ans = nxt;
                    q.push(nxt);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubsequenceRepeatedK(s string, k int) string {
	check := func(t string, k int) bool {
		i := 0
		for _, c := range s {
			if byte(c) == t[i] {
				i++
				if i == len(t) {
					k--
					if k == 0 {
						return true
					}
					i = 0
				}
			}
		}
		return false
	}

	cnt := [26]int{}
	for i := 0; i < len(s); i++ {
		cnt[s[i]-'a']++
	}

	cs := []byte{}
	for c := byte('a'); c <= 'z'; c++ {
		if cnt[c-'a'] >= k {
			cs = append(cs, c)
		}
	}

	q := []string{""}
	ans := ""
	for len(q) > 0 {
		cur := q[0]
		q = q[1:]
		for _, c := range cs {
			nxt := cur + string(c)
			if check(nxt, k) {
				ans = nxt
				q = append(q, nxt)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestSubsequenceRepeatedK(s: string, k: number): string {
    const check = (t: string, k: number): boolean => {
        let i = 0;
        for (const c of s) {
            if (c === t[i]) {
                i++;
                if (i === t.length) {
                    k--;
                    if (k === 0) {
                        return true;
                    }
                    i = 0;
                }
            }
        }
        return false;
    };

    const cnt = new Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }

    const cs: string[] = [];
    for (let i = 0; i < 26; ++i) {
        if (cnt[i] >= k) {
            cs.push(String.fromCharCode(97 + i));
        }
    }

    const q: string[] = [''];
    let ans = '';
    while (q.length > 0) {
        const cur = q.shift()!;
        for (const c of cs) {
            const nxt = cur + c;
            if (check(nxt, k)) {
                ans = nxt;
                q.push(nxt);
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
