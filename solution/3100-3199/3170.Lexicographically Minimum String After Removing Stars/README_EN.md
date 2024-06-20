---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3170.Lexicographically%20Minimum%20String%20After%20Removing%20Stars/README_EN.md
rating: 1772
source: Weekly Contest 400 Q3
tags:
    - Stack
    - Greedy
    - Hash Table
    - String
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3170. Lexicographically Minimum String After Removing Stars](https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars)

[中文文档](/solution/3100-3199/3170.Lexicographically%20Minimum%20String%20After%20Removing%20Stars/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code>. It may contain any number of <code>&#39;*&#39;</code> characters. Your task is to remove all <code>&#39;*&#39;</code> characters.</p>

<p>While there is a <code>&#39;*&#39;</code>, do the following operation:</p>

<ul>
	<li>Delete the leftmost <code>&#39;*&#39;</code> and the <strong>smallest</strong> non-<code>&#39;*&#39;</code> character to its <em>left</em>. If there are several smallest characters, you can delete any of them.</li>
</ul>

<p>Return the <span data-keyword="lexicographically-smaller-string">lexicographically smallest</span> resulting string after removing all <code>&#39;*&#39;</code> characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaba*&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aab&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>We should delete one of the <code>&#39;a&#39;</code> characters with <code>&#39;*&#39;</code>. If we choose <code>s[3]</code>, <code>s</code> becomes the lexicographically smallest.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abc&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no <code>&#39;*&#39;</code> in the string.<!-- notionvc: ff07e34f-b1d6-41fb-9f83-5d0ba3c1ecde --></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters and <code>&#39;*&#39;</code>.</li>
	<li>The input is generated such that it is possible to delete all <code>&#39;*&#39;</code> characters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Record Indices by Character

We define an array $g$ to record the index list of each character, and a boolean array $rem$ of length $n$ to record whether each character needs to be deleted.

We traverse the string $s$:

If the current character is an asterisk, we need to delete it, so we mark $rem[i]$ as deleted. At the same time, we need to delete the character with the smallest lexicographical order and the largest index at this time. We traverse the 26 lowercase letters in ascending order. If $g[a]$ is not empty, we delete the last index in $g[a]$ and set the corresponding index in $rem$ as deleted.

If the current character is not an asterisk, we add the index of the current character to $g$.

Finally, we traverse $s$ and concatenate the undeleted characters.

The time complexity is $O(n \times |\Sigma|)$, and the space complexity is $O(n)$. Where $n$ is the length of the string $s$, and $|\Sigma|$ is the size of the character set. In this problem, $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def clearStars(self, s: str) -> str:
        g = defaultdict(list)
        n = len(s)
        rem = [False] * n
        for i, c in enumerate(s):
            if c == "*":
                rem[i] = True
                for a in ascii_lowercase:
                    if g[a]:
                        rem[g[a].pop()] = True
                        break
            else:
                g[c].append(i)
        return "".join(c for i, c in enumerate(s) if not rem[i])
```

#### Java

```java
class Solution {
    public String clearStars(String s) {
        Deque<Integer>[] g = new Deque[26];
        Arrays.setAll(g, k -> new ArrayDeque<>());
        int n = s.length();
        boolean[] rem = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '*') {
                rem[i] = true;
                for (int j = 0; j < 26; ++j) {
                    if (!g[j].isEmpty()) {
                        rem[g[j].pop()] = true;
                        break;
                    }
                }
            } else {
                g[s.charAt(i) - 'a'].push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (!rem[i]) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string clearStars(string s) {
        stack<int> g[26];
        int n = s.length();
        vector<bool> rem(n);
        for (int i = 0; i < n; ++i) {
            if (s[i] == '*') {
                rem[i] = true;
                for (int j = 0; j < 26; ++j) {
                    if (!g[j].empty()) {
                        rem[g[j].top()] = true;
                        g[j].pop();
                        break;
                    }
                }
            } else {
                g[s[i] - 'a'].push(i);
            }
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            if (!rem[i]) {
                ans.push_back(s[i]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func clearStars(s string) string {
	g := make([][]int, 26)
	n := len(s)
	rem := make([]bool, n)
	for i, c := range s {
		if c == '*' {
			rem[i] = true
			for j := 0; j < 26; j++ {
				if len(g[j]) > 0 {
					rem[g[j][len(g[j])-1]] = true
					g[j] = g[j][:len(g[j])-1]
					break
				}
			}
		} else {
			g[c-'a'] = append(g[c-'a'], i)
		}
	}
	ans := []byte{}
	for i := range s {
		if !rem[i] {
			ans = append(ans, s[i])
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function clearStars(s: string): string {
    const g: number[][] = Array.from({ length: 26 }, () => []);
    const n = s.length;
    const rem: boolean[] = Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        if (s[i] === '*') {
            rem[i] = true;
            for (let j = 0; j < 26; ++j) {
                if (g[j].length) {
                    rem[g[j].pop()!] = true;
                    break;
                }
            }
        } else {
            g[s.charCodeAt(i) - 97].push(i);
        }
    }
    return s
        .split('')
        .filter((_, i) => !rem[i])
        .join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
