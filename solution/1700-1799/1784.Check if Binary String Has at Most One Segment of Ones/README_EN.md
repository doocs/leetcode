---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1784.Check%20if%20Binary%20String%20Has%20at%20Most%20One%20Segment%20of%20Ones/README_EN.md
rating: 1206
source: Weekly Contest 231 Q1
tags:
    - String
---

<!-- problem:start -->

# [1784. Check if Binary String Has at Most One Segment of Ones](https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones)

[中文文档](/solution/1700-1799/1784.Check%20if%20Binary%20String%20Has%20at%20Most%20One%20Segment%20of%20Ones/README.md)

## Description

<!-- description:start -->

<p>Given a binary string <code>s</code> <strong>​​​​​without leading zeros</strong>, return <code>true</code>​​​ <em>if </em><code>s</code><em> contains <strong>at most one contiguous segment of ones</strong></em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1001&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>The ones do not form a contiguous segment.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;110&quot;
<strong>Output:</strong> true</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s[i]</code>​​​​ is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>s[0]</code> is&nbsp;<code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: No '1' After '0'

Notice that the string $s$ does not contain leading zeros, which means $s$ starts with '1'.

If the string $s$ contains the substring "01", then $s$ must be a string like "1...01...", in which case $s$ has at least two consecutive '1' segments, which does not satisfy the problem condition, so we return `false`.

If the string $s$ does not contain the substring "01", then $s$ can only be a string like "1..1000...", in which case $s$ has only one consecutive '1' segment, which satisfies the problem condition, so we return `true`.

Therefore, we only need to judge whether the string $s$ contains the substring "01".

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def checkOnesSegment(self, s: str) -> bool:
        return '01' not in s
```

```java
class Solution {
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}
```

```cpp
class Solution {
public:
    bool checkOnesSegment(string s) {
        return s.find("01") == -1;
    }
};
```

```go
func checkOnesSegment(s string) bool {
	return !strings.Contains(s, "01")
}
```

```ts
function checkOnesSegment(s: string): boolean {
    let pre = s[0];
    for (const c of s) {
        if (pre !== c && c === '1') {
            return false;
        }
        pre = c;
    }
    return true;
}
```

```rust
impl Solution {
    pub fn check_ones_segment(s: String) -> bool {
        !s.contains("01")
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```ts
function checkOnesSegment(s: string): boolean {
    return !s.includes('01');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
