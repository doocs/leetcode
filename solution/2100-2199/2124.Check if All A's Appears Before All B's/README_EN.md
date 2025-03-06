---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2124.Check%20if%20All%20A%27s%20Appears%20Before%20All%20B%27s/README_EN.md
rating: 1201
source: Weekly Contest 274 Q1
tags:
    - String
---

<!-- problem:start -->

# [2124. Check if All A's Appears Before All B's](https://leetcode.com/problems/check-if-all-as-appears-before-all-bs)

[中文文档](/solution/2100-2199/2124.Check%20if%20All%20A%27s%20Appears%20Before%20All%20B%27s/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> consisting of <strong>only</strong> the characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>, return <code>true</code> <em>if <strong>every</strong> </em><code>&#39;a&#39;</code> <em>appears before <strong>every</strong> </em><code>&#39;b&#39;</code><em> in the string</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabbb&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
The &#39;a&#39;s are at indices 0, 1, and 2, while the &#39;b&#39;s are at indices 3, 4, and 5.
Hence, every &#39;a&#39; appears before every &#39;b&#39; and we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abab&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong>
There is an &#39;a&#39; at index 2 and a &#39;b&#39; at index 1.
Hence, not every &#39;a&#39; appears before every &#39;b&#39; and we return false.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbb&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
There are no &#39;a&#39;s, hence, every &#39;a&#39; appears before every &#39;b&#39; and we return true.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s[i]</code> is either <code>&#39;a&#39;</code> or <code>&#39;b&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

According to the problem statement, the string $s$ consists only of characters `a` and `b`.

To ensure that all `a`s appear before all `b`s, the condition that must be met is that `b` should not appear before `a`. In other words, the substring "ba" should not be present in the string $s$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkString(self, s: str) -> bool:
        return "ba" not in s
```

#### Java

```java
class Solution {
    public boolean checkString(String s) {
        return !s.contains("ba");
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkString(string s) {
        return !s.contains("ba");
    }
};
```

#### Go

```go
func checkString(s string) bool {
	return !strings.Contains(s, "ba")
}
```

#### TypeScript

```ts
function checkString(s: string): boolean {
    return !s.includes('ba');
}
```

#### Rust

```rust
impl Solution {
    pub fn check_string(s: String) -> bool {
        !s.contains("ba")
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
