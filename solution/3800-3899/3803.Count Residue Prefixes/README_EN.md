---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3803.Count%20Residue%20Prefixes/README_EN.md
---

<!-- problem:start -->

# [3803. Count Residue Prefixes](https://leetcode.com/problems/count-residue-prefixes)

[中文文档](/solution/3800-3899/3803.Count%20Residue%20Prefixes/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting only of lowercase English letters.</p>

<p>A <strong>prefix</strong> of <code>s</code> is called a <strong>residue</strong> if the number of <strong>distinct characters</strong> in the <strong>prefix</strong> is equal to <code>len(prefix) % 3</code>.</p>

<p>Return the count of <strong>residue</strong> prefixes in <code>s</code>.</p>
A <strong>prefix</strong> of a string is a <strong>non-empty substring</strong> that starts from the beginning of the string and extends to any point within it.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Prefix <code>&quot;a&quot;</code> has 1 distinct character and length modulo 3 is 1, so it is a residue.</li>
	<li>Prefix <code>&quot;ab&quot;</code> has 2 distinct characters and length modulo 3 is 2, so it is a residue.</li>
	<li>Prefix <code>&quot;abc&quot;</code> does not satisfy the condition. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;dd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Prefix <code>&quot;d&quot;</code> has 1 distinct character and length modulo 3 is 1, so it is a residue.</li>
	<li>Prefix <code>&quot;dd&quot;</code> has 1 distinct character but length modulo 3 is 2, so it is not a residue. Thus, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;bob&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Prefix <code>&quot;b&quot;</code> has 1 distinct character and length modulo 3 is 1, so it is a residue.</li>
	<li>Prefix <code>&quot;bo&quot;</code> has 2 distinct characters and length mod 3 is 2, so it is a residue. Thus, the answer is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{st}$ to record the set of distinct characters that have appeared in the current prefix. We iterate through each character $c$ in the string $s$, add it to the set $\textit{st}$, and then check if the length of the current prefix modulo $3$ equals the size of the set $\textit{st}$. If they are equal, it means the current prefix is a residue prefix, and we increment the answer by $1$.

After the iteration, we return the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def residuePrefixes(self, s: str) -> int:
        st = set()
        ans = 0
        for i, c in enumerate(s, 1):
            st.add(c)
            if len(st) == i % 3:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int residuePrefixes(String s) {
        Set<Character> st = new HashSet<>();
        int ans = 0;
        for (int i = 1; i <= s.length(); i++) {
            char c = s.charAt(i - 1);
            st.add(c);
            if (st.size() == i % 3) {
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
    int residuePrefixes(string s) {
        unordered_set<char> st;
        int ans = 0;
        for (int i = 1; i <= s.size(); i++) {
            char c = s[i - 1];
            st.insert(c);
            if (st.size() == i % 3) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func residuePrefixes(s string) int {
	st := make(map[rune]struct{})
	ans := 0
	for i, c := range s {
		idx := i + 1
		st[c] = struct{}{}
		if len(st) == idx%3 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function residuePrefixes(s: string): number {
    const st = new Set<string>();
    let ans = 0;
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        st.add(c);
        if (st.size === (i + 1) % 3) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
