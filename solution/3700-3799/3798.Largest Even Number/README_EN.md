---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3798.Largest%20Even%20Number/README_EN.md
rating: 1365
source: Weekly Contest 483 Q1
tags:
    - String
---

<!-- problem:start -->

# [3798. Largest Even Number](https://leetcode.com/problems/largest-even-number)

[中文文档](/solution/3700-3799/3798.Largest%20Even%20Number/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting only of the characters <code>&#39;1&#39;</code> and <code>&#39;2&#39;</code>.</p>

<p>You may delete any number of characters from <code>s</code> without changing the order of the remaining characters.</p>

<p>Return the <strong>largest possible resultant string</strong> that represents an <strong>even</strong> integer. If there is no such string, return the empty string <code>&quot;&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1112&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1112&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The string already represents the largest possible even number, so no deletions are needed.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;221&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;22&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Deleting <code>&#39;1&#39;</code> results in the largest possible even number which is equal to 22.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no way to get an even number.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of the characters <code>&#39;1&#39;</code> and <code>&#39;2&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestEven(self, s: str) -> str:
        return s.rstrip("1")
```

#### Java

```java
class Solution {
    public String largestEven(String s) {
        int i = s.length();
        while (i > 0 && s.charAt(i - 1) == '1') {
            i--;
        }
        return s.substring(0, i);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string largestEven(string s) {
        while (!s.empty() && s.back() == '1') {
            s.pop_back();
        }
        return s;
    }
};
```

#### Go

```go
func largestEven(s string) string {
	return strings.TrimRight(s, "1")
}
```

#### TypeScript

```ts
function largestEven(s: string): string {
    return s.replace(/1+$/, '');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
