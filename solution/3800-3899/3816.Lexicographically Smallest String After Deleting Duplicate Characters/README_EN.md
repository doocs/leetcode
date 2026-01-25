---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3816.Lexicographically%20Smallest%20String%20After%20Deleting%20Duplicate%20Characters/README_EN.md
rating: 2376
source: Weekly Contest 485 Q4
---

<!-- problem:start -->

# [3816. Lexicographically Smallest String After Deleting Duplicate Characters](https://leetcode.com/problems/lexicographically-smallest-string-after-deleting-duplicate-characters)

[中文文档](/solution/3800-3899/3816.Lexicographically%20Smallest%20String%20After%20Deleting%20Duplicate%20Characters/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> that consists of lowercase English letters.</p>

<p>You can perform the following operation any number of times (possibly zero times):</p>

<ul>
	<li>Choose any letter that appears <strong>at least twice</strong> in the current string <code>s</code> and delete any <strong>one</strong> occurrence.</li>
</ul>

<p>Return the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> resulting string that can be formed this way.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaccb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aacb&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>We can form the strings <code>&quot;acb&quot;</code>, <code>&quot;aacb&quot;</code>, <code>&quot;accb&quot;</code>, and <code>&quot;aaccb&quot;</code>. <code>&quot;aacb&quot;</code> is the lexicographically smallest one.</p>

<p>For example, we can obtain <code>&quot;aacb&quot;</code> by choosing <code>&#39;c&#39;</code> and deleting its first occurrence.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;z&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;z&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>We cannot perform any operations. The only string we can form is <code>&quot;z&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains lowercase English letters only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Monotonic Stack

We can use a stack $\textit{stk}$ to store the characters of the result string, and a hash table $\textit{cnt}$ to record the number of occurrences of each character in string $s$.

First, we initialize $\textit{cnt}$ to count the occurrences of each character in string $s$. Then, we iterate through each character $c$ in string $s$:

- If the stack is not empty, the top character of the stack is greater than $c$, and the top character will appear again in string $s$, we pop the top character and decrement its count in $\textit{cnt}$.
- Push character $c$ into the stack.

Finally, if there are duplicate characters in the stack, we continue to pop the top character until the count of the top character in $\textit{cnt}$ is 1.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lexSmallestAfterDeletion(self, s: str) -> str:
        cnt = Counter(s)
        stk = []
        for c in s:
            while stk and stk[-1] > c and cnt[stk[-1]] > 1:
                cnt[stk.pop()] -= 1
            stk.append(c)
        while stk and cnt[stk[-1]] > 1:
            cnt[stk.pop()] -= 1
        return "".join(stk)
```

#### Java

```java
class Solution {
    public String lexSmallestAfterDeletion(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        StringBuilder stk = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            while (stk.length() > 0 && stk.charAt(stk.length() - 1) > c
                && cnt[stk.charAt(stk.length() - 1) - 'a'] > 1) {
                --cnt[stk.charAt(stk.length() - 1) - 'a'];
                stk.setLength(stk.length() - 1);
            }
            stk.append(c);
        }
        while (cnt[stk.charAt(stk.length() - 1) - 'a'] > 1) {
            --cnt[stk.charAt(stk.length() - 1) - 'a'];
            stk.setLength(stk.length() - 1);
        }
        return stk.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string lexSmallestAfterDeletion(string s) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        string stk;
        for (char c : s) {
            while (stk.size() && stk.back() > c && cnt[stk.back() - 'a'] > 1) {
                --cnt[stk.back() - 'a'];
                stk.pop_back();
            }
            stk.push_back(c);
        }
        while (cnt[stk.back() - 'a'] > 1) {
            --cnt[stk.back() - 'a'];
            stk.pop_back();
        }
        return stk;
    }
};
```

#### Go

```go
func lexSmallestAfterDeletion(s string) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	stk := []byte{}
	for _, c := range s {
		for len(stk) > 0 && stk[len(stk)-1] > byte(c) && cnt[stk[len(stk)-1]-'a'] > 1 {
			cnt[stk[len(stk)-1]-'a']--
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, byte(c))
	}
	for cnt[stk[len(stk)-1]-'a'] > 1 {
		cnt[stk[len(stk)-1]-'a']--
		stk = stk[:len(stk)-1]
	}
	return string(stk)
}
```

#### TypeScript

```ts
function lexSmallestAfterDeletion(s: string): string {
    const cnt: number[] = new Array(26).fill(0);
    const n = s.length;
    const a = 'a'.charCodeAt(0);
    for (let i = 0; i < n; ++i) {
        ++cnt[s.charCodeAt(i) - a];
    }
    const stk: string[] = [];
    for (let i = 0; i < n; ++i) {
        const c = s[i];
        while (
            stk.length > 0 &&
            stk[stk.length - 1] > c &&
            cnt[stk[stk.length - 1].charCodeAt(0) - a] > 1
        ) {
            --cnt[stk.pop()!.charCodeAt(0) - a];
        }
        stk.push(c);
    }
    while (cnt[stk[stk.length - 1].charCodeAt(0) - a] > 1) {
        --cnt[stk.pop()!.charCodeAt(0) - a];
    }
    return stk.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
