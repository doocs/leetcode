---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1849.Splitting%20a%20String%20Into%20Descending%20Consecutive%20Values/README_EN.md
rating: 1746
source: Weekly Contest 239 Q2
tags:
    - String
    - Backtracking
    - Enumeration
---

<!-- problem:start -->

# [1849. Splitting a String Into Descending Consecutive Values](https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values)

[中文文档](/solution/1800-1899/1849.Splitting%20a%20String%20Into%20Descending%20Consecutive%20Values/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> that consists of only digits.</p>

<p>Check if we can split <code>s</code> into <strong>two or more non-empty substrings</strong> such that the <strong>numerical values</strong> of the substrings are in <strong>descending order</strong> and the <strong>difference</strong> between numerical values of every two <strong>adjacent</strong> <strong>substrings</strong> is equal to <code>1</code>.</p>

<ul>
	<li>For example, the string <code>s = &quot;0090089&quot;</code> can be split into <code>[&quot;0090&quot;, &quot;089&quot;]</code> with numerical values <code>[90,89]</code>. The values are in descending order and adjacent values differ by <code>1</code>, so this way is valid.</li>
	<li>Another example, the string <code>s = &quot;001&quot;</code> can be split into <code>[&quot;0&quot;, &quot;01&quot;]</code>, <code>[&quot;00&quot;, &quot;1&quot;]</code>, or <code>[&quot;0&quot;, &quot;0&quot;, &quot;1&quot;]</code>. However all the ways are invalid because they have numerical values <code>[0,1]</code>, <code>[0,1]</code>, and <code>[0,0,1]</code> respectively, all of which are not in descending order.</li>
</ul>

<p>Return <code>true</code> <em>if it is possible to split</em> <code>s</code>​​​​​​ <em>as described above</em><em>, or </em><code>false</code><em> otherwise.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1234&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no valid way to split s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;050043&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> s can be split into [&quot;05&quot;, &quot;004&quot;, &quot;3&quot;] with numerical values [5,4,3].
The values are in descending order with adjacent values differing by 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;9080701&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no valid way to split s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> only consists of digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We can start from the first character of the string and try to split it into one or more substrings, then recursively process the remaining part.

Specifically, we design a function $\textit{dfs}(i, x)$, where $i$ represents the current position being processed, and $x$ represents the last split value. Initially, $x = -1$, indicating that we have not split out any value yet.

In $\textit{dfs}(i, x)$, we first calculate the current split value $y$. If $x = -1$, or $x - y = 1$, then we can try to use $y$ as the next value and continue to recursively process the remaining part. If the result of the recursion is $\textit{true}$, we have found a valid split method and return $\textit{true}$.

After traversing all possible split methods, if no valid split method is found, we return $\textit{false}$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def splitString(self, s: str) -> bool:
        def dfs(i: int, x: int) -> bool:
            if i >= len(s):
                return True
            y = 0
            r = len(s) - 1 if x < 0 else len(s)
            for j in range(i, r):
                y = y * 10 + int(s[j])
                if (x < 0 or x - y == 1) and dfs(j + 1, y):
                    return True
            return False

        return dfs(0, -1)
```

#### Java

```java
class Solution {
    private char[] s;

    public boolean splitString(String s) {
        this.s = s.toCharArray();
        return dfs(0, -1);
    }

    private boolean dfs(int i, long x) {
        if (i >= s.length) {
            return true;
        }
        long y = 0;
        int r = x < 0 ? s.length - 1 : s.length;
        for (int j = i; j < r; ++j) {
            y = y * 10 + s[j] - '0';
            if ((x < 0 || x - y == 1) && dfs(j + 1, y)) {
                return true;
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
    bool splitString(string s) {
        auto dfs = [&](this auto&& dfs, int i, long long x) -> bool {
            if (i >= s.size()) {
                return true;
            }
            long long y = 0;
            int r = x < 0 ? s.size() - 1 : s.size();
            for (int j = i; j < r; ++j) {
                y = y * 10 + s[j] - '0';
                if (y > 1e10) {
                    break;
                }
                if ((x < 0 || x - y == 1) && dfs(j + 1, y)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(0, -1);
    }
};
```

#### Go

```go
func splitString(s string) bool {
	var dfs func(i, x int) bool
	dfs = func(i, x int) bool {
		if i >= len(s) {
			return true
		}
		y := 0
		r := len(s)
		if x < 0 {
			r--
		}
		for j := i; j < r; j++ {
			y = y*10 + int(s[j]-'0')
			if (x < 0 || x-y == 1) && dfs(j+1, y) {
				return true
			}
		}
		return false
	}
	return dfs(0, -1)
}
```

#### TypeScript

```ts
function splitString(s: string): boolean {
    const dfs = (i: number, x: number): boolean => {
        if (i >= s.length) {
            return true;
        }
        let y = 0;
        const r = x < 0 ? s.length - 1 : s.length;
        for (let j = i; j < r; ++j) {
            y = y * 10 + +s[j];
            if ((x < 0 || x - y === 1) && dfs(j + 1, y)) {
                return true;
            }
        }
        return false;
    };
    return dfs(0, -1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
