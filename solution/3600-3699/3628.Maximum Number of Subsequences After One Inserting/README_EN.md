---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3628.Maximum%20Number%20of%20Subsequences%20After%20One%20Inserting/README_EN.md
rating: 1753
source: Weekly Contest 460 Q2
tags:
    - Greedy
    - String
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3628. Maximum Number of Subsequences After One Inserting](https://leetcode.com/problems/maximum-number-of-subsequences-after-one-inserting)

[中文文档](/solution/3600-3699/3628.Maximum%20Number%20of%20Subsequences%20After%20One%20Inserting/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of uppercase English letters.</p>

<p>You are allowed to insert <strong>at most one</strong> uppercase English letter at <strong>any</strong> position (including the beginning or end) of the string.</p>

<p>Return the <strong>maximum</strong> number of <code>&quot;LCT&quot;</code> <span data-keyword="subsequence-string-nonempty">subsequences</span> that can be formed in the resulting string after <strong>at most one insertion</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;LMCT&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can insert a <code>&quot;L&quot;</code> at the beginning of the string s to make <code>&quot;LLMCT&quot;</code>, which has 2 subsequences, at indices [0, 3, 4] and [1, 3, 4].</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;LCCT&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>We can insert a <code>&quot;L&quot;</code> at the beginning of the string s to make <code>&quot;LLCCT&quot;</code>, which has 4 subsequences, at indices [0, 2, 4], [0, 3, 4], [1, 2, 4] and [1, 3, 4].</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;L&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Since it is not possible to obtain the subsequence <code>&quot;LCT&quot;</code> by inserting a single letter, the result is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of uppercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can first calculate the number of "LCT" subsequences in the original string, then consider the case of inserting one letter.

The number of "LCT" subsequences can be calculated by traversing the string. We can enumerate the middle "C" and use two variables $l$ and $r$ to maintain the counts of "L" on the left and "T" on the right respectively. For each "C", we can calculate the number of "L"s on its left and the number of "T"s on its right, thus obtaining the number of "LCT" subsequences with this "C" as the middle character as $l \times r$, and accumulate it to the total count.

Next, we need to consider the case of inserting one letter. Consider inserting an "L", "C", or "T":

- Insert an "L": we only need to count the number of "CT" subsequences in the original string.
- Insert a "T": we only need to count the number of "LC" subsequences in the original string.
- Insert a "C": we only need to count the number of "LT" subsequences in the original string. In this case, during the enumeration process above, we can maintain a variable $\textit{mx}$ representing the current maximum value of $l \times r$.

Finally, we add the number of "LCT" subsequences in the original string to the maximum number of subsequences after inserting one letter to get the final result.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numOfSubsequences(self, s: str) -> int:
        def calc(t: str) -> int:
            cnt = a = 0
            for c in s:
                if c == t[1]:
                    cnt += a
                a += int(c == t[0])
            return cnt

        l, r = 0, s.count("T")
        ans = mx = 0
        for c in s:
            r -= int(c == "T")
            if c == "C":
                ans += l * r
            l += int(c == "L")
            mx = max(mx, l * r)
        mx = max(mx, calc("LC"), calc("CT"))
        ans += mx
        return ans
```

#### Java

```java
class Solution {
    private char[] s;

    public long numOfSubsequences(String S) {
        s = S.toCharArray();
        int l = 0, r = 0;
        for (char c : s) {
            if (c == 'T') {
                ++r;
            }
        }
        long ans = 0, mx = 0;
        for (char c : s) {
            r -= c == 'T' ? 1 : 0;
            if (c == 'C') {
                ans += 1L * l * r;
            }
            l += c == 'L' ? 1 : 0;
            mx = Math.max(mx, 1L * l * r);
        }
        mx = Math.max(mx, Math.max(calc("LC"), calc("CT")));
        ans += mx;
        return ans;
    }

    private long calc(String t) {
        long cnt = 0;
        int a = 0;
        for (char c : s) {
            if (c == t.charAt(1)) {
                cnt += a;
            }
            a += c == t.charAt(0) ? 1 : 0;
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numOfSubsequences(string s) {
        auto calc = [&](string t) {
            long long cnt = 0, a = 0;
            for (char c : s) {
                if (c == t[1]) {
                    cnt += a;
                }
                a += (c == t[0]);
            }
            return cnt;
        };

        long long l = 0, r = count(s.begin(), s.end(), 'T');
        long long ans = 0, mx = 0;
        for (char c : s) {
            r -= (c == 'T');
            if (c == 'C') {
                ans += l * r;
            }
            l += (c == 'L');
            mx = max(mx, l * r);
        }
        mx = max(mx, calc("LC"));
        mx = max(mx, calc("CT"));
        ans += mx;
        return ans;
    }
};
```

#### Go

```go
func numOfSubsequences(s string) int64 {
	calc := func(t string) int64 {
		cnt, a := int64(0), int64(0)
		for _, c := range s {
			if c == rune(t[1]) {
				cnt += a
			}
			if c == rune(t[0]) {
				a++
			}
		}
		return cnt
	}

	l, r := int64(0), int64(0)
	for _, c := range s {
		if c == 'T' {
			r++
		}
	}

	ans, mx := int64(0), int64(0)
	for _, c := range s {
		if c == 'T' {
			r--
		}
		if c == 'C' {
			ans += l * r
		}
		if c == 'L' {
			l++
		}
		mx = max(mx, l*r)
	}
	mx = max(mx, calc("LC"), calc("CT"))
	ans += mx
	return ans
}
```

#### TypeScript

```ts
function numOfSubsequences(s: string): number {
    const calc = (t: string): number => {
        let [cnt, a] = [0, 0];
        for (const c of s) {
            if (c === t[1]) cnt += a;
            if (c === t[0]) a++;
        }
        return cnt;
    };

    let [l, r] = [0, 0];
    for (const c of s) {
        if (c === 'T') r++;
    }

    let [ans, mx] = [0, 0];
    for (const c of s) {
        if (c === 'T') r--;
        if (c === 'C') ans += l * r;
        if (c === 'L') l++;
        mx = Math.max(mx, l * r);
    }

    mx = Math.max(mx, calc('LC'));
    mx = Math.max(mx, calc('CT'));
    ans += mx;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
