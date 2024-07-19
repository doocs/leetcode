---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3216.Lexicographically%20Smallest%20String%20After%20a%20Swap/README_EN.md
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [3216. Lexicographically Smallest String After a Swap](https://leetcode.com/problems/lexicographically-smallest-string-after-a-swap)

[中文文档](/solution/3200-3299/3216.Lexicographically%20Smallest%20String%20After%20a%20Swap/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> containing only digits, return the <span data-keyword="lexicographically-smaller-string">lexicographically smallest string</span> that can be obtained after swapping <strong>adjacent</strong> digits in <code>s</code> with the same <strong>parity</strong> at most <strong>once</strong>.</p>

<p>Digits have the same parity if both are odd or both are even. For example, 5 and 9, as well as 2 and 4, have the same parity, while 6 and 9 do not.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;45320&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;43520&quot;</span></p>

<p><strong>Explanation: </strong></p>

<p><code>s[1] == &#39;5&#39;</code> and <code>s[2] == &#39;3&#39;</code> both have the same parity, and swapping them results in the lexicographically smallest string.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;001&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;001&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no need to perform a swap because <code>s</code> is already the lexicographically smallest.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Simulation

We can traverse the string $\textit{s}$ from left to right. For each pair of adjacent digits, if they have the same parity and the previous digit is greater than the next digit, then we swap these two digits to make the lexicographical order of the string $\textit{s}$ smaller, and then return the swapped string.

After the traversal, if no swappable pair of digits is found, it means the string $\textit{s}$ is already in its smallest lexicographical order, and we can return it directly.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getSmallestString(self, s: str) -> str:
        for i, (a, b) in enumerate(pairwise(map(ord, s))):
            if (a + b) % 2 == 0 and a > b:
                return s[:i] + s[i + 1] + s[i] + s[i + 2 :]
        return s
```

#### Java

```java
class Solution {
    public String getSmallestString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 1; i < n; ++i) {
            char a = cs[i - 1], b = cs[i];
            if (a > b && a % 2 == b % 2) {
                cs[i] = a;
                cs[i - 1] = b;
                return new String(cs);
            }
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string getSmallestString(string s) {
        int n = s.length();
        for (int i = 1; i < n; ++i) {
            char a = s[i - 1], b = s[i];
            if (a > b && a % 2 == b % 2) {
                s[i - 1] = b;
                s[i] = a;
                break;
            }
        }
        return s;
    }
};
```

#### Go

```go
func getSmallestString(s string) string {
	cs := []byte(s)
	n := len(cs)
	for i := 1; i < n; i++ {
		a, b := cs[i-1], cs[i]
		if a > b && a%2 == b%2 {
			cs[i-1], cs[i] = b, a
			return string(cs)
		}
	}
	return s
}
```

#### TypeScript

```ts
function getSmallestString(s: string): string {
    const n = s.length;
    const cs: string[] = s.split('');
    for (let i = 1; i < n; ++i) {
        const a = cs[i - 1];
        const b = cs[i];
        if (a > b && +a % 2 === +b % 2) {
            cs[i - 1] = b;
            cs[i] = a;
            return cs.join('');
        }
    }
    return s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
