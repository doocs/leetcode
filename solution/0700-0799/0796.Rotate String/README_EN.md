---
comments: true
difficulty: Easy
tags:
    - String
    - String Matching
---

<!-- problem:start -->

# [796. Rotate String](https://leetcode.com/problems/rotate-string)

[中文文档](/solution/0700-0799/0796.Rotate%20String/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>s</code> and <code>goal</code>, return <code>true</code> <em>if and only if</em> <code>s</code> <em>can become</em> <code>goal</code> <em>after some number of <strong>shifts</strong> on</em> <code>s</code>.</p>

<p>A <strong>shift</strong> on <code>s</code> consists of moving the leftmost character of <code>s</code> to the rightmost position.</p>

<ul>
	<li>For example, if <code>s = &quot;abcde&quot;</code>, then it will be <code>&quot;bcdea&quot;</code> after one shift.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcde&quot;, goal = &quot;cdeab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>Rotating <code>s</code> to the left by 2 positions moves <code>&quot;ab&quot;</code> to the end, resulting in <code>&quot;cdeab&quot;</code>, which is equal to <code>goal</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcde&quot;, goal = &quot;abced&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>No sequence of rotations of <code>s</code> can produce <code>&quot;abced&quot;</code>. The characters appear in a different relative order, so <code>goal</code> is not a rotation of <code>s</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, goal.length &lt;= 100</code></li>
	<li><code>s</code> and <code>goal</code> consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Is $goal$ a rotation of $s$? Unequal lengths fail; otherwise $goal$ is a substring of $s+s$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        return len(s) == len(goal) and goal in s + s
```

#### Java

```java
class Solution {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool rotateString(string s, string goal) {
        return s.size() == goal.size() && strstr((s + s).data(), goal.data());
    }
};
```

#### Go

```go
func rotateString(s string, goal string) bool {
	return len(s) == len(goal) && strings.Contains(s+s, goal)
}
```

#### TypeScript

```ts
function rotateString(s: string, goal: string): boolean {
    return s.length === goal.length && (goal + goal).includes(s);
}
```

#### Rust

```rust
impl Solution {
    pub fn rotate_string(s: String, goal: String) -> bool {
        s.len() == goal.len() && (s.clone() + &s).contains(&goal)
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @param String $goal
     * @return Boolean
     */
    function rotateString($s, $goal) {
        return strlen($goal) === strlen($s) && strpos($s . $s, $goal) !== false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
