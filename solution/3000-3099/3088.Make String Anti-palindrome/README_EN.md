# [3088. Make String Anti-palindrome](https://leetcode.com/problems/make-string-anti-palindrome)

[中文文档](/solution/3000-3099/3088.Make%20String%20Anti-palindrome/README.md)

<!-- tags: -->

## Description

<p>We call a string <code>s</code> of <strong>even</strong> length <code>n</code> an <strong>anti-palindrome</strong> if for each index <code>0 &lt;= i &lt; n</code>, <code>s[i] != s[n - i - 1]</code>.</p>

<p>Given a string <code>s</code>, your task is to make <code>s</code> an <strong>anti-palindrome</strong> by doing <strong>any</strong> number of operations (including zero).</p>

<p>In one operation, you can select two characters from <code>s</code> and swap them.</p>

<p>Return <em>the resulting string. If multiple strings meet the conditions, return the <span data-keyword="lexicographically-smaller-string">lexicographically smallest</span> one. If it can&#39;t be made into an anti-palindrome, return </em><code>&quot;-1&quot;</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abca&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aabc&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><code>&quot;aabc&quot;</code> is an anti-palindrome string since <code>s[0] != s[3]</code> and <code>s[1] != s[2]</code>. Also, it is a rearrangement of <code>&quot;abca&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aabb&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><code>&quot;aabb&quot;</code> is an anti-palindrome string since <code>s[0] != s[3]</code> and <code>s[1] != s[2]</code>. Also, it is a rearrangement of <code>&quot;abba&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cccd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;-1&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>You can see that no matter how you rearrange the characters of <code>&quot;cccd&quot;</code>, either <code>s[0] == s[3]</code> or <code>s[1] == s[2]</code>. So it can not form an anti-palindrome string.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s.length % 2 == 0</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Greedy + Sorting

The problem asks us to transform the string $s$ into the lexicographically smallest non-palindrome string. We might as well sort the string $s$ first.

Next, we only need to compare whether the two middle characters $s[m]$ and $s[m-1]$ are equal. If they are equal, we find the first character $s[i]$ in the second half that is not equal to $s[m]$, use a pointer $j$ to point to $m$, and then swap $s[i]$ and $s[j]$. If we can't find such a character $s[i]$, it means that the string $s$ cannot be transformed into a non-palindrome string, return `"1"`. Otherwise, perform the swap operation, move $i$ and $j$ to the right, compare whether $s[j]$ and $s[n-j-1]$ are equal, if they are equal, continue to perform the swap operation until $i$ exceeds the length of the string.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def makeAntiPalindrome(self, s: str) -> str:
        cs = sorted(s)
        n = len(cs)
        m = n // 2
        if cs[m] == cs[m - 1]:
            i = m
            while i < n and cs[i] == cs[i - 1]:
                i += 1
            j = m
            while j < n and cs[j] == cs[n - j - 1]:
                if i >= n:
                    return "-1"
                cs[i], cs[j] = cs[j], cs[i]
                i, j = i + 1, j + 1
        return "".join(cs)
```

```java
class Solution {
    public String makeAntiPalindrome(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        int n = cs.length;
        int m = n / 2;
        if (cs[m] == cs[m - 1]) {
            int i = m;
            while (i < n && cs[i] == cs[i - 1]) {
                ++i;
            }
            for (int j = m; j < n && cs[j] == cs[n - j - 1]; ++i, ++j) {
                if (i >= n) {
                    return "-1";
                }
                char t = cs[i];
                cs[i] = cs[j];
                cs[j] = t;
            }
        }
        return new String(cs);
    }
}
```

```cpp
class Solution {
public:
    string makeAntiPalindrome(string s) {
        sort(s.begin(), s.end());
        int n = s.length();
        int m = n / 2;
        if (s[m] == s[m - 1]) {
            int i = m;
            while (i < n && s[i] == s[i - 1]) {
                ++i;
            }
            for (int j = m; j < n && s[j] == s[n - j - 1]; ++i, ++j) {
                if (i >= n) {
                    return "-1";
                }
                swap(s[i], s[j]);
            }
        }
        return s;
    }
};
```

```go
func makeAntiPalindrome(s string) string {
	cs := []byte(s)
	sort.Slice(cs, func(i, j int) bool { return cs[i] < cs[j] })
	n := len(cs)
	m := n / 2
	if cs[m] == cs[m-1] {
		i := m
		for i < n && cs[i] == cs[i-1] {
			i++
		}
		for j := m; j < n && cs[j] == cs[n-j-1]; i, j = i+1, j+1 {
			if i >= n {
				return "-1"
			}
			cs[i], cs[j] = cs[j], cs[i]
		}
	}
	return string(cs)
}
```

```ts
function makeAntiPalindrome(s: string): string {
    const cs: string[] = s.split('').sort();
    const n: number = cs.length;
    const m = n >> 1;
    if (cs[m] === cs[m - 1]) {
        let i = m;
        for (; i < n && cs[i] === cs[i - 1]; i++);
        for (let j = m; j < n && cs[j] === cs[n - j - 1]; ++i, ++j) {
            if (i >= n) {
                return '-1';
            }
            [cs[j], cs[i]] = [cs[i], cs[j]];
        }
    }
    return cs.join('');
}
```

<!-- tabs:end -->

<!-- end -->
