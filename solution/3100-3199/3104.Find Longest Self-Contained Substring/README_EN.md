---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3104.Find%20Longest%20Self-Contained%20Substring/README_EN.md
tags:
    - Hash Table
    - String
    - Binary Search
    - Prefix Sum
---

<!-- problem:start -->

# [3104. Find Longest Self-Contained Substring 🔒](https://leetcode.com/problems/find-longest-self-contained-substring)

[中文文档](/solution/3100-3199/3104.Find%20Longest%20Self-Contained%20Substring/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, your task is to find the length of the <strong>longest self-contained</strong> <span data-keyword="substring-nonempty">substring</span> of <code>s</code>.</p>

<p>A substring <code>t</code> of a string <code>s</code> is called <strong>self-contained </strong>if <code>t != s</code> and for every character in <code>t</code>, it doesn&#39;t exist in the <em>rest</em> of <code>s</code>.</p>

<p>Return the length of the <em>longest<strong> </strong>self-contained </em>substring of <code>s</code> if it exists, otherwise, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong><br />
Let&#39;s check the substring <code>&quot;bb&quot;</code>. You can see that no other <code>&quot;b&quot;</code> is outside of this substring. Hence the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong><br />
Every substring we choose does not satisfy the described property (there is some character which is inside and outside of that substring). So the answer would be -1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abacd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong><br />
Let&#39;s check the substring <code>&quot;<span class="example-io">abac</span>&quot;</code>. There is only one character outside of this substring and that is <code>&quot;d&quot;</code>. There is no <code>&quot;d&quot;</code> inside the chosen substring, so it satisfies the condition and the answer is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We notice that the start of a substring that meets the conditions must be the position where a character appears for the first time.

Therefore, we can use two arrays or hash tables `first` and `last` to record the positions where each character appears for the first time and the last time, respectively.

Next, we enumerate each character `c`. Suppose the position where `c` first appears is $i$, and the position where it last appears is $mx$. Then we can start traversing from $i$. For each position $j$, we find the position $a$ where $s[j]$ first appears and the position $b$ where it last appears. If $a < i$, it means that $s[j]$ is on the left of $c$, which does not meet the enumeration conditions, and we can exit the loop directly. Otherwise, we update $mx = \max(mx, b)$. If $mx = j$ and $j - i + 1 < n$, we update the answer to $ans = \max(ans, j - i + 1)$.

Finally, return the answer.

The time complexity is $O(n \times |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Where $n$ is the length of the string $s$; and $|\Sigma|$ is the size of the character set. In this problem, the character set is lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

```python
class Solution:
    def maxSubstringLength(self, s: str) -> int:
        first, last = {}, {}
        for i, c in enumerate(s):
            if c not in first:
                first[c] = i
            last[c] = i
        ans, n = -1, len(s)
        for c, i in first.items():
            mx = last[c]
            for j in range(i, n):
                a, b = first[s[j]], last[s[j]]
                if a < i:
                    break
                mx = max(mx, b)
                if mx == j and j - i + 1 < n:
                    ans = max(ans, j - i + 1)
        return ans
```

```java
class Solution {
    public int maxSubstringLength(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int j = s.charAt(i) - 'a';
            if (first[j] == -1) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = -1;
        for (int k = 0; k < 26; ++k) {
            int i = first[k];
            if (i == -1) {
                continue;
            }
            int mx = last[k];
            for (int j = i; j < n; ++j) {
                int a = first[s.charAt(j) - 'a'];
                int b = last[s.charAt(j) - 'a'];
                if (a < i) {
                    break;
                }
                mx = Math.max(mx, b);
                if (mx == j && j - i + 1 < n) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxSubstringLength(string s) {
        vector<int> first(26, -1);
        vector<int> last(26);
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int j = s[i] - 'a';
            if (first[j] == -1) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = -1;
        for (int k = 0; k < 26; ++k) {
            int i = first[k];
            if (i == -1) {
                continue;
            }
            int mx = last[k];
            for (int j = i; j < n; ++j) {
                int a = first[s[j] - 'a'];
                int b = last[s[j] - 'a'];
                if (a < i) {
                    break;
                }
                mx = max(mx, b);
                if (mx == j && j - i + 1 < n) {
                    ans = max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
};
```

```go
func maxSubstringLength(s string) int {
	first := [26]int{}
	last := [26]int{}
	for i := range first {
		first[i] = -1
	}
	n := len(s)
	for i, c := range s {
		j := int(c - 'a')
		if first[j] == -1 {
			first[j] = i
		}
		last[j] = i
	}
	ans := -1
	for k := 0; k < 26; k++ {
		i := first[k]
		if i == -1 {
			continue
		}
		mx := last[k]
		for j := i; j < n; j++ {
			a, b := first[s[j]-'a'], last[s[j]-'a']
			if a < i {
				break
			}
			mx = max(mx, b)
			if mx == j && j-i+1 < n {
				ans = max(ans, j-i+1)
			}
		}
	}
	return ans
}
```

```ts
function maxSubstringLength(s: string): number {
    const first: number[] = Array(26).fill(-1);
    const last: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        const j = s.charCodeAt(i) - 97;
        if (first[j] === -1) {
            first[j] = i;
        }
        last[j] = i;
    }
    let ans = -1;
    for (let k = 0; k < 26; ++k) {
        const i = first[k];
        if (i === -1) {
            continue;
        }
        let mx = last[k];
        for (let j = i; j < n; ++j) {
            const a = first[s.charCodeAt(j) - 97];
            if (a < i) {
                break;
            }
            const b = last[s.charCodeAt(j) - 97];
            mx = Math.max(mx, b);
            if (mx === j && j - i + 1 < n) {
                ans = Math.max(ans, j - i + 1);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
