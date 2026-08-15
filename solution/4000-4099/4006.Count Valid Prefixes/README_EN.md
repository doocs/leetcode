---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4006.Count%20Valid%20Prefixes/README_EN.md
rating: 1242
source: Biweekly Contest 188 Q1
---

<!-- problem:start -->

# [4006. Count Valid Prefixes](https://leetcode.com/problems/count-valid-prefixes)

[中文文档](/solution/4000-4099/4006.Count%20Valid%20Prefixes/README.md)

## Description

<!-- description:start -->

<p>You are given a <span data-keyword="binary-string">binary string</span> <code>s</code>.</p>

<p>A <span data-keyword="string-prefix">prefix</span> of <code>s</code> is considered <strong>valid</strong> if its characters can be rearranged to form an <strong>alternating</strong> string.</p>

<p>Return the number of valid prefixes of <code>s</code>.</p>

<p>A string is considered <strong>alternating</strong> if no two adjacent characters are equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;00101&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid prefixes are:</p>

<ul>
	<li><code>&quot;0&quot;</code>: It is already an alternating string.</li>
	<li><code>&quot;001&quot;</code>: It can be rearranged into <code>&quot;010&quot;</code>, which is an alternating string.</li>
	<li><code>&quot;00101&quot;</code>: It can be rearranged into <code>&quot;01010&quot;</code>, which is an alternating string.</li>
</ul>

<p>Thus, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;101&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>All prefixes of <code>s = &quot;101&quot;</code> are already alternating strings. Thus, the answer is 3.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

A string can be rearranged into an alternating string if and only if the counts of `'0'` and `'1'` in it differ by at most $1$.

Therefore, we traverse the string $s$ and maintain a variable $t$ equal to the number of `'1'`s minus the number of `'0'`s in the current prefix (increment by one on `'1'`, decrement by one on `'0'`). If $|t| \leq 1$, the current prefix is valid, and we add one to the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countValidPrefixes(self, s: str) -> int:
        ans = t = 0
        for c in s:
            t += 1 if c == '1' else -1
            ans += 1 if abs(t) <= 1 else 0
        return ans
```

#### Java

```java
class Solution {
    public int countValidPrefixes(String s) {
        int ans = 0, t = 0;
        for (char c : s.toCharArray()) {
            t += c == '1' ? 1 : -1;
            if (Math.abs(t) <= 1) {
                ans++;
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
    int countValidPrefixes(string s) {
        int ans = 0, t = 0;
        for (char c : s) {
            t += c == '1' ? 1 : -1;
            if (abs(t) <= 1) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countValidPrefixes(s string) int {
	ans, t := 0, 0
	for _, c := range s {
		if c == '1' {
			t++
		} else {
			t--
		}
		if t >= -1 && t <= 1 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countValidPrefixes(s: string): number {
    let ans = 0;
    let t = 0;
    for (const c of s) {
        t += c === '1' ? 1 : -1;
        if (Math.abs(t) <= 1) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
