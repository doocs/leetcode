---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3138.Minimum%20Length%20of%20Anagram%20Concatenation/README_EN.md
rating: 1979
source: Weekly Contest 396 Q3
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [3138. Minimum Length of Anagram Concatenation](https://leetcode.com/problems/minimum-length-of-anagram-concatenation)

[中文文档](/solution/3100-3199/3138.Minimum%20Length%20of%20Anagram%20Concatenation/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code>, which is known to be a concatenation of <strong>anagrams</strong> of some string <code>t</code>.</p>

<p>Return the <strong>minimum</strong> possible length of the string <code>t</code>.</p>

<p>An <strong>anagram</strong> is formed by rearranging the letters of a string. For example, &quot;aab&quot;, &quot;aba&quot;, and, &quot;baa&quot; are anagrams of &quot;aab&quot;.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One possible string <code>t</code> could be <code>&quot;ba&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cdef&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One possible string <code>t</code> could be <code>&quot;cdef&quot;</code>, notice that <code>t</code> can be equal to <code>s</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

Based on the problem description, the length of string $\text{t}$ must be a factor of the length of string $\text{s}$. We can enumerate the length $k$ of string $\text{t}$ from small to large, and then check if it meets the requirements of the problem. If it does, we return. Thus, the problem is transformed into how to check whether the length $k$ of string $\text{t}$ meets the requirements.

First, we count the occurrence of each character in string $\text{s}$ and record it in an array or hash table $\text{cnt}$.

Next, we define a function $\text{check}(k)$ to check whether the length $k$ of string $\text{t}$ meets the requirements. We can traverse string $\text{s}$, taking a substring of length $k$ each time, and then count the occurrence of each character. If the occurrence of each character multiplied by $\frac{n}{k}$ does not equal the value in $\text{cnt}$, then return $\text{false}$. If all checks pass by the end of the traversal, return $\text{true}$.

The time complexity is $O(n \times A)$, where $n$ is the length of string $\text{s}$, and $A$ is the number of factors of $n$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set, which in this case is the set of lowercase letters.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAnagramLength(self, s: str) -> int:
        def check(k: int) -> bool:
            for i in range(0, n, k):
                cnt1 = Counter(s[i : i + k])
                for c, v in cnt.items():
                    if cnt1[c] * (n // k) != v:
                        return False
            return True

        cnt = Counter(s)
        n = len(s)
        for i in range(1, n + 1):
            if n % i == 0 and check(i):
                return i
```

#### Java

```java
class Solution {
    private int n;
    private char[] s;
    private int[] cnt = new int[26];

    public int minAnagramLength(String s) {
        n = s.length();
        this.s = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            ++cnt[this.s[i] - 'a'];
        }
        for (int i = 1;; ++i) {
            if (n % i == 0 && check(i)) {
                return i;
            }
        }
    }

    private boolean check(int k) {
        for (int i = 0; i < n; i += k) {
            int[] cnt1 = new int[26];
            for (int j = i; j < i + k; ++j) {
                ++cnt1[s[j] - 'a'];
            }
            for (int j = 0; j < 26; ++j) {
                if (cnt1[j] * (n / k) != cnt[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minAnagramLength(string s) {
        int n = s.size();
        int cnt[26]{};
        for (char c : s) {
            cnt[c - 'a']++;
        }
        auto check = [&](int k) {
            for (int i = 0; i < n; i += k) {
                int cnt1[26]{};
                for (int j = i; j < i + k; ++j) {
                    cnt1[s[j] - 'a']++;
                }
                for (int j = 0; j < 26; ++j) {
                    if (cnt1[j] * (n / k) != cnt[j]) {
                        return false;
                    }
                }
            }
            return true;
        };
        for (int i = 1;; ++i) {
            if (n % i == 0 && check(i)) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func minAnagramLength(s string) int {
	n := len(s)
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	check := func(k int) bool {
		for i := 0; i < n; i += k {
			cnt1 := [26]int{}
			for j := i; j < i+k; j++ {
				cnt1[s[j]-'a']++
			}
			for j, v := range cnt {
				if cnt1[j]*(n/k) != v {
					return false
				}
			}
		}
		return true
	}
	for i := 1; ; i++ {
		if n%i == 0 && check(i) {
			return i
		}
	}
}
```

#### TypeScript

```ts
function minAnagramLength(s: string): number {
    const n = s.length;
    const cnt: Record<string, number> = {};
    for (let i = 0; i < n; i++) {
        cnt[s[i]] = (cnt[s[i]] || 0) + 1;
    }
    const check = (k: number): boolean => {
        for (let i = 0; i < n; i += k) {
            const cnt1: Record<string, number> = {};
            for (let j = i; j < i + k; j++) {
                cnt1[s[j]] = (cnt1[s[j]] || 0) + 1;
            }
            for (const [c, v] of Object.entries(cnt)) {
                if (cnt1[c] * ((n / k) | 0) !== v) {
                    return false;
                }
            }
        }
        return true;
    };
    for (let i = 1; ; ++i) {
        if (n % i === 0 && check(i)) {
            return i;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
