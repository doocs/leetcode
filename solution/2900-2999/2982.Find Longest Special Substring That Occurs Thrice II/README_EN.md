---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2982.Find%20Longest%20Special%20Substring%20That%20Occurs%20Thrice%20II/README_EN.md
rating: 1772
source: Weekly Contest 378 Q3
tags:
    - Hash Table
    - String
    - Binary Search
    - Counting
    - Sliding Window
---

# [2982. Find Longest Special Substring That Occurs Thrice II](https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii)

[中文文档](/solution/2900-2999/2982.Find%20Longest%20Special%20Substring%20That%20Occurs%20Thrice%20II/README.md)

## Description

<p>You are given a string <code>s</code> that consists of lowercase English letters.</p>

<p>A string is called <strong>special</strong> if it is made up of only a single character. For example, the string <code>&quot;abc&quot;</code> is not special, whereas the strings <code>&quot;ddd&quot;</code>, <code>&quot;zz&quot;</code>, and <code>&quot;f&quot;</code> are special.</p>

<p>Return <em>the length of the <strong>longest special substring</strong> of </em><code>s</code> <em>which occurs <strong>at least thrice</strong></em>, <em>or </em><code>-1</code><em> if no special substring occurs at least thrice</em>.</p>

<p>A <strong>substring</strong> is a contiguous <strong>non-empty</strong> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaa&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest special substring which occurs thrice is &quot;aa&quot;: substrings &quot;<u><strong>aa</strong></u>aa&quot;, &quot;a<u><strong>aa</strong></u>a&quot;, and &quot;aa<u><strong>aa</strong></u>&quot;.
It can be shown that the maximum length achievable is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdef&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> There exists no special substring which occurs at least thrice. Hence return -1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcaba&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The longest special substring which occurs thrice is &quot;a&quot;: substrings &quot;<u><strong>a</strong></u>bcaba&quot;, &quot;abc<u><strong>a</strong></u>ba&quot;, and &quot;abcab<u><strong>a</strong></u>&quot;.
It can be shown that the maximum length achievable is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Binary Search + Sliding Window Counting

We notice that if there exists a special substring of length $x$ that appears at least three times, then a special substring of length $x-1$ must also exist. This exhibits a monotonicity, so we can use binary search to find the longest special substring.

We define the left boundary of the binary search as $l = 0$ and the right boundary as $r = n$, where $n$ is the length of the string. In each binary search, we take $mid = \lfloor \frac{l + r + 1}{2} \rfloor$. If a special substring of length $mid$ exists, we update the left boundary to $mid$. Otherwise, we update the right boundary to $mid - 1$. During the binary search, we use a sliding window to count the number of special substrings.

Specifically, we design a function $check(x)$ to indicate whether a special substring of length $x$ that appears at least three times exists.

In the function $check(x)$, we define a hash table or an array of length $26$ named $cnt$, where $cnt[i]$ represents the count of special substrings of length $x$ composed of the $i$-th lowercase letter. We traverse the string $s$. If the current character is $s[i]$, we move the pointer $j$ to the right until $s[j] \neq s[i]$. At this point, $s[i \cdots j-1]$ is a special substring of length $x$. We increase $cnt[s[i]]$ by $\max(0, j - i - x + 1)$, and then update the pointer $i$ to $j$.

After the traversal, we go through the array $cnt$. If there exists $cnt[i] \geq 3$, it means a special substring of length $x$ that appears at least three times exists, so we return $true$. Otherwise, we return $false$.

The time complexity is $O((n + |\Sigma|) \times \log n)$, and the space complexity is $O(|\Sigma|)$, where $n$ is the length of the string $s$, and $|\Sigma|$ represents the size of the character set. In this problem, the character set is lowercase English letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

```python
class Solution:
    def maximumLength(self, s: str) -> int:
        def check(x: int) -> bool:
            cnt = defaultdict(int)
            i = 0
            while i < n:
                j = i + 1
                while j < n and s[j] == s[i]:
                    j += 1
                cnt[s[i]] += max(0, j - i - x + 1)
                i = j
            return max(cnt.values()) >= 3

        n = len(s)
        l, r = 0, n
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return -1 if l == 0 else l
```

```java
class Solution {
    private String s;
    private int n;

    public int maximumLength(String s) {
        this.s = s;
        n = s.length();
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l == 0 ? -1 : l;
    }

    private boolean check(int x) {
        int[] cnt = new int[26];
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int k = s.charAt(i) - 'a';
            cnt[k] += Math.max(0, j - i - x + 1);
            if (cnt[k] >= 3) {
                return true;
            }
            i = j;
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    int maximumLength(string s) {
        int n = s.size();
        int l = 0, r = n;
        auto check = [&](int x) {
            int cnt[26]{};
            for (int i = 0; i < n;) {
                int j = i + 1;
                while (j < n && s[j] == s[i]) {
                    ++j;
                }
                int k = s[i] - 'a';
                cnt[k] += max(0, j - i - x + 1);
                if (cnt[k] >= 3) {
                    return true;
                }
                i = j;
            }
            return false;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l == 0 ? -1 : l;
    }
};
```

```go
func maximumLength(s string) int {
	n := len(s)
	l, r := 0, n
	check := func(x int) bool {
		cnt := [26]int{}
		for i := 0; i < n; {
			j := i + 1
			for j < n && s[j] == s[i] {
				j++
			}
			k := s[i] - 'a'
			cnt[k] += max(0, j-i-x+1)
			if cnt[k] >= 3 {
				return true
			}
			i = j
		}
		return false
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	if l == 0 {
		return -1
	}
	return l
}
```

```ts
function maximumLength(s: string): number {
    const n = s.length;
    let [l, r] = [0, n];
    const check = (x: number): boolean => {
        const cnt: number[] = Array(26).fill(0);
        for (let i = 0; i < n; ) {
            let j = i + 1;
            while (j < n && s[j] === s[i]) {
                j++;
            }
            const k = s[i].charCodeAt(0) - 'a'.charCodeAt(0);
            cnt[k] += Math.max(0, j - i - x + 1);
            if (cnt[k] >= 3) {
                return true;
            }
            i = j;
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l === 0 ? -1 : l;
}
```

<!-- tabs:end -->

<!-- end -->
