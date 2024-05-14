# [2937. Make Three Strings Equal](https://leetcode.com/problems/make-three-strings-equal)

[中文文档](/solution/2900-2999/2937.Make%20Three%20Strings%20Equal/README.md)

<!-- tags:String -->

<!-- difficulty:Easy -->

## Description

<p>You are given three strings: <code>s1</code>, <code>s2</code>, and <code>s3</code>. In one operation you can choose one of these strings and delete its <strong>rightmost</strong> character. Note that you <strong>cannot</strong> completely empty a string.</p>

<p>Return the <em>minimum number of operations</em> required to make the strings equal<em>. </em>If it is impossible to make them equal, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s1 = &quot;abc&quot;, s2 = &quot;abb&quot;, s3 = &quot;ab&quot;</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">2</span></p>

<p><strong>Explanation:&nbsp;</strong>Deleting the rightmost character from both <code>s1</code> and <code>s2</code> will result in three equal strings.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s1 = &quot;dac&quot;, s2 = &quot;bac&quot;, s3 = &quot;cac&quot;</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">-1</span></p>

<p><strong>Explanation:</strong> Since the first letters of <code>s1</code> and <code>s2</code> differ, they cannot be made equal.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length, s3.length &lt;= 100</code></li>
	<li><font face="monospace"><code>s1</code>,</font> <code><font face="monospace">s2</font></code><font face="monospace"> and</font> <code><font face="monospace">s3</font></code> consist only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Enumeration

According to the problem description, we know that if the three strings are equal after deleting characters, then they have a common prefix of length greater than $1$. Therefore, we can enumerate the position $i$ of the common prefix. If the three characters at the current index $i$ are not all equal, then the length of the common prefix is $i$. At this point, we check if $i$ is $0$. If it is, return $-1$. Otherwise, return $s - 3 \times i$, where $s$ is the sum of the lengths of the three strings.

The time complexity is $O(n)$, where $n$ is the minimum length of the three strings. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findMinimumOperations(self, s1: str, s2: str, s3: str) -> int:
        s = len(s1) + len(s2) + len(s3)
        n = min(len(s1), len(s2), len(s3))
        for i in range(n):
            if not s1[i] == s2[i] == s3[i]:
                return -1 if i == 0 else s - 3 * i
        return s - 3 * n
```

```java
class Solution {
    public int findMinimumOperations(String s1, String s2, String s3) {
        int s = s1.length() + s2.length() + s3.length();
        int n = Math.min(Math.min(s1.length(), s2.length()), s3.length());
        for (int i = 0; i < n; ++i) {
            if (!(s1.charAt(i) == s2.charAt(i) && s2.charAt(i) == s3.charAt(i))) {
                return i == 0 ? -1 : s - 3 * i;
            }
        }
        return s - 3 * n;
    }
}
```

```cpp
class Solution {
public:
    int findMinimumOperations(string s1, string s2, string s3) {
        int s = s1.size() + s2.size() + s3.size();
        int n = min({s1.size(), s2.size(), s3.size()});
        for (int i = 0; i < n; ++i) {
            if (!(s1[i] == s2[i] && s2[i] == s3[i])) {
                return i == 0 ? -1 : s - 3 * i;
            }
        }
        return s - 3 * n;
    }
};
```

```go
func findMinimumOperations(s1 string, s2 string, s3 string) int {
	s := len(s1) + len(s2) + len(s3)
	n := min(len(s1), len(s2), len(s3))
	for i := range s1[:n] {
		if !(s1[i] == s2[i] && s2[i] == s3[i]) {
			if i == 0 {
				return -1
			}
			return s - 3*i
		}
	}
	return s - 3*n
}
```

```ts
function findMinimumOperations(s1: string, s2: string, s3: string): number {
    const s = s1.length + s2.length + s3.length;
    const n = Math.min(s1.length, s2.length, s3.length);
    for (let i = 0; i < n; ++i) {
        if (!(s1[i] === s2[i] && s2[i] === s3[i])) {
            return i === 0 ? -1 : s - 3 * i;
        }
    }
    return s - 3 * n;
}
```

<!-- tabs:end -->

<!-- end -->
