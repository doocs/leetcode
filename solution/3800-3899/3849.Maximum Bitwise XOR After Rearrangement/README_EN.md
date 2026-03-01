---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3849.Maximum%20Bitwise%20XOR%20After%20Rearrangement/README_EN.md
rating: 1556
source: Weekly Contest 490 Q3
tags:
    - Greedy
    - Bit Manipulation
    - String
---

<!-- problem:start -->

# [3849. Maximum Bitwise XOR After Rearrangement](https://leetcode.com/problems/maximum-bitwise-xor-after-rearrangement)

[中文文档](/solution/3800-3899/3849.Maximum%20Bitwise%20XOR%20After%20Rearrangement/README.md)

## Description

<!-- description:start -->

<p>You are given two binary strings <code>s</code> and <code>t</code>​​​​​​​, each of length <code>n</code>.</p>

<p>You may <strong>rearrange</strong> the characters of <code>t</code> in any order, but <code>s</code> <strong>must remain unchanged</strong>.</p>

<p>Return a <strong>binary string</strong> of length <code>n</code> representing the <strong>maximum</strong> integer value obtainable by taking the bitwise <strong>XOR</strong> of <code>s</code> and rearranged <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;101&quot;, t = &quot;011&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;110&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>One optimal rearrangement of <code>t</code> is <code>&quot;011&quot;</code>.</li>
	<li>The bitwise XOR of <code>s</code> and rearranged <code>t</code> is <code>&quot;101&quot; XOR &quot;011&quot; = &quot;110&quot;</code>, which is the maximum possible.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0110&quot;, t = &quot;1110&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1101&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>One optimal rearrangement of <code>t</code> is <code>&quot;1011&quot;</code>.</li>
	<li>The bitwise XOR of <code>s</code> and rearranged <code>t</code> is <code>&quot;0110&quot; XOR &quot;1011&quot; = &quot;1101&quot;</code>, which is the maximum possible.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0101&quot;, t = &quot;1001&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1111&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>One optimal rearrangement of <code>t</code> is <code>&quot;1010&quot;</code>.</li>
	<li>The bitwise XOR of <code>s</code> and rearranged <code>t</code> is <code>&quot;0101&quot; XOR &quot;1010&quot; = &quot;1111&quot;</code>, which is the maximum possible.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length == t.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s[i]</code> and <code>t[i]</code> are either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We use an array $\textit{cnt}$ of length $2$ to count the number of character '0' and character '1' in string $t$.

Then we iterate through string $s$. For each character $s[i]$, we want to find a character in string $t$ that is different from $s[i]$ to perform the XOR operation, in order to get a larger result. If we find such a character, we set the $i$-th bit of the answer to '1' and decrement the count of that character by one; otherwise, we can only use a character that is the same as $s[i]$ for the XOR operation, the $i$-th bit of the answer remains '0', and we decrement the count of that character by one. Finally, we return the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumXor(self, s: str, t: str) -> str:
        cnt = [0, 0]
        for c in t:
            cnt[int(c)] += 1
        ans = ['0'] * len(s)
        for i, c in enumerate(s):
            x = int(c)
            if cnt[x ^ 1]:
                cnt[x ^ 1] -= 1
                ans[i] = '1'
            else:
                cnt[x] -= 1
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String maximumXor(String s, String t) {
        int[] cnt = new int[2];
        for (char c : t.toCharArray()) {
            cnt[c - '0']++;
        }

        char[] ans = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - '0';
            if (cnt[x ^ 1] > 0) {
                cnt[x ^ 1]--;
                ans[i] = '1';
            } else {
                cnt[x]--;
                ans[i] = '0';
            }
        }

        return new String(ans);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string maximumXor(string s, string t) {
        int cnt[2]{};
        for (char c : t) {
            cnt[c - '0']++;
        }

        string ans(s.size(), '0');
        for (int i = 0; i < s.size(); i++) {
            int x = s[i] - '0';
            if (cnt[x ^ 1] > 0) {
                cnt[x ^ 1]--;
                ans[i] = '1';
            } else {
                cnt[x]--;
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maximumXor(s string, t string) string {
	cnt := make([]int, 2)
	for _, c := range t {
		cnt[c-'0']++
	}

	ans := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		x := s[i] - '0'
		if cnt[x^1] > 0 {
			cnt[x^1]--
			ans[i] = '1'
		} else {
			cnt[x]--
			ans[i] = '0'
		}
	}

	return string(ans)
}
```

#### TypeScript

```ts
function maximumXor(s: string, t: string): string {
    const cnt = [0, 0];

    for (const c of t) {
        cnt[Number(c)]++;
    }

    const ans: string[] = new Array(s.length).fill('0');

    for (let i = 0; i < s.length; i++) {
        const x = Number(s[i]);
        if (cnt[x ^ 1] > 0) {
            cnt[x ^ 1]--;
            ans[i] = '1';
        } else {
            cnt[x]--;
        }
    }

    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
