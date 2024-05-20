---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1542.Find%20Longest%20Awesome%20Substring/README_EN.md
rating: 2221
source: Biweekly Contest 32 Q4
tags:
    - Bit Manipulation
    - Hash Table
    - String
---

<!-- problem:start -->

# [1542. Find Longest Awesome Substring](https://leetcode.com/problems/find-longest-awesome-substring)

[中文文档](/solution/1500-1599/1542.Find%20Longest%20Awesome%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code>. An <strong>awesome</strong> substring is a non-empty substring of <code>s</code> such that we can make any number of swaps in order to make it a palindrome.</p>

<p>Return <em>the length of the maximum length <strong>awesome substring</strong> of</em> <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3242415&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> &quot;24241&quot; is the longest awesome substring, we can form the palindrome &quot;24142&quot; with some swaps.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;12345678&quot;
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;213123&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> &quot;213123&quot; is the longest awesome substring, we can form the palindrome &quot;231132&quot; with some swaps.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + Prefix Sum

According to the problem description, the characters in the "super awesome substring" can be swapped to obtain a palindrome string. Therefore, there is at most one digit character in the "super awesome substring" that appears an odd number of times, and the rest of the digit characters appear an even number of times.

We can use an integer $st$ to represent the parity of the digit characters in the current prefix string, where the $i$-th bit of $st$ represents the parity of the digit character $i$, i.e., the $i$-th bit of $st$ is $1$ means that the digit character $i$ appears an odd number of times, and $0$ means that the digit character $i$ appears an even number of times.

If the substring $s[j,..i]$ is a "super awesome string", then the state $st$ of the prefix string $s[0,..i]$ and the state $st'$ of the prefix string $s[0,..j-1]$ differ by at most one bit in binary. This is because, if the binary bits are different, it means that the parity is different, and if the parity is different, it means that the number of times the digit appears in the substring $s[j,..i]$ is odd.

So, we can use a hash table or array to record the first occurrence of all states $st$. If the state $st$ of the current prefix string already exists in the hash table, it means that all bits in the binary of the state $st$ of the current prefix string and the state $st'$ of the prefix string $s[0,..j-1]$ are the same, i.e., the substring $s[j,..i]$ is a "super awesome string", and we update the maximum value of the answer. Or, we can enumerate each bit, flip the $i$-th bit of the state $st$ of the current prefix string, i.e., $st \oplus 2^i$, and then check whether $st \oplus 2^i$ is in the hash table. If it is, it means that only the $i$-th bit in the binary of the state $st$ of the current prefix string and the state $st' \oplus 2^i$ of the prefix string $s[0,..j-1]$ is different, i.e., the substring $s[j,..i]$ is a "super awesome string", and we update the maximum value of the answer.

Finally, return the answer.

The time complexity is $O(n \times C)$, and the space complexity is $O(2^C)$. Where $n$ and $C$ are the length of the string $s$ and the number of types of digit characters, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestAwesome(self, s: str) -> int:
        st = 0
        d = {0: -1}
        ans = 1
        for i, c in enumerate(s):
            v = int(c)
            st ^= 1 << v
            if st in d:
                ans = max(ans, i - d[st])
            else:
                d[st] = i
            for v in range(10):
                if st ^ (1 << v) in d:
                    ans = max(ans, i - d[st ^ (1 << v)])
        return ans
```

#### Java

```java
class Solution {
    public int longestAwesome(String s) {
        int[] d = new int[1024];
        int st = 0, ans = 1;
        Arrays.fill(d, -1);
        d[0] = 0;
        for (int i = 1; i <= s.length(); ++i) {
            int v = s.charAt(i - 1) - '0';
            st ^= 1 << v;
            if (d[st] >= 0) {
                ans = Math.max(ans, i - d[st]);
            } else {
                d[st] = i;
            }
            for (v = 0; v < 10; ++v) {
                if (d[st ^ (1 << v)] >= 0) {
                    ans = Math.max(ans, i - d[st ^ (1 << v)]);
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestAwesome(string s) {
        vector<int> d(1024, -1);
        d[0] = 0;
        int st = 0, ans = 1;
        for (int i = 1; i <= s.size(); ++i) {
            int v = s[i - 1] - '0';
            st ^= 1 << v;
            if (~d[st]) {
                ans = max(ans, i - d[st]);
            } else {
                d[st] = i;
            }
            for (v = 0; v < 10; ++v) {
                if (~d[st ^ (1 << v)]) {
                    ans = max(ans, i - d[st ^ (1 << v)]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestAwesome(s string) int {
	d := [1024]int{}
	d[0] = 1
	st, ans := 0, 1
	for i, c := range s {
		i += 2
		st ^= 1 << (c - '0')
		if d[st] > 0 {
			ans = max(ans, i-d[st])
		} else {
			d[st] = i
		}
		for v := 0; v < 10; v++ {
			if d[st^(1<<v)] > 0 {
				ans = max(ans, i-d[st^(1<<v)])
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestAwesome(s: string): number {
    const d: number[] = Array(1024).fill(-1);
    let [st, ans] = [0, 1];
    d[0] = 0;

    for (let i = 1; i <= s.length; ++i) {
        const v = s.charCodeAt(i - 1) - '0'.charCodeAt(0);
        st ^= 1 << v;

        if (d[st] >= 0) {
            ans = Math.max(ans, i - d[st]);
        } else {
            d[st] = i;
        }

        for (let v = 0; v < 10; ++v) {
            if (d[st ^ (1 << v)] >= 0) {
                ans = Math.max(ans, i - d[st ^ (1 << v)]);
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
