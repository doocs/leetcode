# [3146. Permutation Difference between Two Strings](https://leetcode.com/problems/permutation-difference-between-two-strings)

[中文文档](/solution/3100-3199/3146.Permutation%20Difference%20between%20Two%20Strings/README.md)

<!-- tags: -->

<!-- difficulty:Easy -->

## Description

<p>You are given two strings <code>s</code> and <code>t</code> such that every character occurs at most once in <code>s</code> and <code>t</code> is a permutation of <code>s</code>.</p>

<p>The <strong>permutation difference</strong> between <code>s</code> and <code>t</code> is defined as the <strong>sum</strong> of the absolute difference between the index of the occurrence of each character in <code>s</code> and the index of the occurrence of the same character in <code>t</code>.</p>

<p>Return the <strong>permutation difference</strong> between <code>s</code> and <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, t = &quot;bac&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>For <code>s = &quot;abc&quot;</code> and <code>t = &quot;bac&quot;</code>, the permutation difference of <code>s</code> and <code>t</code> is equal to the sum of:</p>

<ul>
	<li>The absolute difference between the index of the occurrence of <code>&quot;a&quot;</code> in <code>s</code> and the index of the occurrence of <code>&quot;a&quot;</code> in <code>t</code>.</li>
	<li>The absolute difference between the index of the occurrence of <code>&quot;b&quot;</code> in <code>s</code> and the index of the occurrence of <code>&quot;b&quot;</code> in <code>t</code>.</li>
	<li>The absolute difference between the index of the occurrence of <code>&quot;c&quot;</code> in <code>s</code> and the index of the occurrence of <code>&quot;c&quot;</code> in <code>t</code>.</li>
</ul>

<p>That is, the permutation difference between <code>s</code> and <code>t</code> is equal to <code>|0 - 1| + |2 - 2| + |1 - 0| = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcde&quot;, t = &quot;edbac&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong> The permutation difference between <code>s</code> and <code>t</code> is equal to <code>|0 - 3| + |1 - 2| + |2 - 4| + |3 - 1| + |4 - 0| = 12</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 26</code></li>
	<li>Each character occurs at most once in <code>s</code>.</li>
	<li><code>t</code> is a permutation of <code>s</code>.</li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def findPermutationDifference(self, s: str, t: str) -> int:
        d = {c: i for i, c in enumerate(s)}
        return sum(abs(d[c] - i) for i, c in enumerate(t))
```

```java
class Solution {
    public int findPermutationDifference(String s, String t) {
        int[] d = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            d[s.charAt(i) - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.abs(d[t.charAt(i) - 'a'] - i);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findPermutationDifference(string s, string t) {
        int d[26]{};
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            d[s[i] - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += abs(d[t[i] - 'a'] - i);
        }
        return ans;
    }
};
```

```go
func findPermutationDifference(s string, t string) (ans int) {
	d := [26]int{}
	for i, c := range s {
		d[c-'a'] = i
	}
	for i, c := range t {
		ans += max(d[c-'a']-i, i-d[c-'a'])
	}
	return
}
```

```ts
function findPermutationDifference(s: string, t: string): number {
    const d: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        d[s.charCodeAt(i) - 'a'.charCodeAt(0)] = i;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.abs(d[t.charCodeAt(i) - 'a'.charCodeAt(0)] - i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
