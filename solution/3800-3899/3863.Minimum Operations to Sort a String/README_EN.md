---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3863.Minimum%20Operations%20to%20Sort%20a%20String/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [3863. Minimum Operations to Sort a String](https://leetcode.com/problems/minimum-operations-to-sort-a-string)

[中文文档](/solution/3800-3899/3863.Minimum%20Operations%20to%20Sort%20a%20String/README.md)

## Description

<!-- description:start -->

<p data-end="244" data-start="156">You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>In one operation, you can select any <strong><span data-keyword="substring-nonempty">substring</span></strong> of <code>s</code> that is <strong>not</strong> the entire string and <strong>sort</strong> it in <strong>non-descending alphabetical</strong> order.</p>

<p>Return the <strong>minimum</strong> number of operations required to make <code>s</code> sorted in <strong>non-descending</strong> order. If it is not possible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;dog&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Sort substring <code>&quot;og&quot;</code> to <code>&quot;go&quot;</code>.</li>
	<li>Now, <code>s = &quot;dgo&quot;</code>, which is sorted in ascending order. Thus, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;card&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Sort substring <code>&quot;car&quot;</code> to <code>&quot;acr&quot;</code>, so <code>s = &quot;acrd&quot;</code>.</li>
	<li>Sort substring <code>&quot;rd&quot;</code> to <code>&quot;dr&quot;</code>, making <code>s = &quot;acdr&quot;</code>, which is sorted in ascending order. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;gf&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>It is impossible to sort <code>s</code> under the given constraints. Thus, the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis

We first check whether the string is already sorted in ascending order; if so, return 0.

Otherwise, if the string has length 2, since we cannot choose the entire string to sort, it is impossible to sort the string, so we return -1.

Next, we find the minimum character $mn$ and the maximum character $mx$ in the string. If the first character of the string equals $mn$, or the last character equals $mx$, then one operation on the remaining substring is sufficient to sort the entire string, so we return 1.

Otherwise, if some character in the middle of the string equals $mn$ or $mx$, we need one operation to move that character to the beginning or the end of the string, and then one more operation to sort the rest, so we return 2.

Finally, if none of the above cases apply, we need one operation on the substring containing both $mn$ and $mx$, followed by one more operation on the remaining substring, so we return 3.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, s: str) -> int:
        if all(a <= b for a, b in pairwise(s)):
            return 0
        if len(s) == 2:
            return -1
        mn, mx = min(s), max(s)
        if s[0] == mn or s[-1] == mx:
            return 1
        if any(c in [mn, mx] for c in s[1:-1]):
            return 2
        return 3
```

#### Java

```java
class Solution {
    public int minOperations(String s) {
        boolean isSorted = true;
        char[] cs = s.toCharArray();
        int n = cs.length;
        char mn = cs[0], mx = cs[0];
        for (int i = 1; i < n; ++i) {
            mn = (char) Math.min(mn, cs[i]);
            mx = (char) Math.max(mx, cs[i]);
            if (cs[i] < cs[i - 1]) {
                isSorted = false;
            }
        }
        if (isSorted) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        if (cs[0] == mn || cs[n - 1] == mx) {
            return 1;
        }
        for (int i = 1; i < n - 1; ++i) {
            if (cs[i] == mn || cs[i] == mx) {
                return 2;
            }
        }
        return 3;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(string s) {
        int n = s.size();
        bool sorted = true;

        for (int i = 1; i < n; ++i) {
            if (s[i] < s[i - 1]) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        char mn = *min_element(s.begin(), s.end());
        char mx = *max_element(s.begin(), s.end());

        if (s[0] == mn || s[n - 1] == mx) {
            return 1;
        }

        for (int i = 1; i < n - 1; ++i) {
            if (s[i] == mn || s[i] == mx) {
                return 2;
            }
        }

        return 3;
    }
};
```

#### Go

```go
func minOperations(s string) int {
	n := len(s)

	sorted := true
	for i := 1; i < n; i++ {
		if s[i] < s[i-1] {
			sorted = false
			break
		}
	}

	if sorted {
		return 0
	}

	if n == 2 {
		return -1
	}

	mn, mx := s[0], s[0]
	for i := 1; i < n; i++ {
		if s[i] < mn {
			mn = s[i]
		}
		if s[i] > mx {
			mx = s[i]
		}
	}

	if s[0] == mn || s[n-1] == mx {
		return 1
	}

	for i := 1; i < n-1; i++ {
		if s[i] == mn || s[i] == mx {
			return 2
		}
	}

	return 3
}
```

#### TypeScript

```ts
function minOperations(s: string): number {
    const n = s.length;

    let sorted = true;
    for (let i = 1; i < n; i++) {
        if (s[i] < s[i - 1]) {
            sorted = false;
            break;
        }
    }

    if (sorted) {
        return 0;
    }

    if (n === 2) {
        return -1;
    }

    let mn = s[0];
    let mx = s[0];

    for (const c of s) {
        if (c < mn) {
            mn = c;
        }
        if (c > mx) {
            mx = c;
        }
    }

    if (s[0] === mn || s[n - 1] === mx) {
        return 1;
    }

    for (let i = 1; i < n - 1; i++) {
        if (s[i] === mn || s[i] === mx) {
            return 2;
        }
    }

    return 3;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
