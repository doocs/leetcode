# [3084. Count Substrings Starting and Ending with Given Character](https://leetcode.com/problems/count-substrings-starting-and-ending-with-given-character)

[中文文档](/solution/3000-3099/3084.Count%20Substrings%20Starting%20and%20Ending%20with%20Given%20Character/README.md)

<!-- tags: -->

## Description

<p>You are given a string <code>s</code> and a character <code>c</code>. Return <em>the total number of <span data-keyword="substring-nonempty">substrings</span> of </em><code>s</code><em> that start and end with </em><code>c</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = &quot;abada&quot;, c = &quot;a&quot;</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">6</span></p>

<p><strong>Explanation:</strong> Substrings starting and ending with <code>&quot;a&quot;</code> are: <code>&quot;<strong><u>a</u></strong>bada&quot;</code>, <code>&quot;<u><strong>aba</strong></u>da&quot;</code>, <code>&quot;<u><strong>abada</strong></u>&quot;</code>, <code>&quot;ab<u><strong>a</strong></u>da&quot;</code>, <code>&quot;ab<u><strong>ada</strong></u>&quot;</code>, <code>&quot;abad<u><strong>a</strong></u>&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = &quot;zzz&quot;, c = &quot;z&quot;</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">6</span></p>

<p><strong>Explanation:</strong> There are a total of <code>6</code> substrings in <code>s</code> and all start and end with <code>&quot;z&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>c</code> consist&nbsp;only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Mathematics

First, we can count the number of character $c$ in string $s$, denoted as $cnt$.

Each character $c$ can form a substring on its own, so there are $cnt$ substrings that meet the condition. Each character $c$ can form a substring with other $c$ characters, so there are $\frac{cnt \times (cnt - 1)}{2}$ substrings that meet the condition.

Therefore, the answer is $cnt + \frac{cnt \times (cnt - 1)}{2}$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def countSubstrings(self, s: str, c: str) -> int:
        cnt = s.count(c)
        return cnt + cnt * (cnt - 1) // 2
```

```java
class Solution {
    public long countSubstrings(String s, char c) {
        long cnt = s.chars().filter(ch -> ch == c).count();
        return cnt + cnt * (cnt - 1) / 2;
    }
}
```

```cpp
class Solution {
public:
    long long countSubstrings(string s, char c) {
        long long cnt = ranges::count(s, c);
        return cnt + cnt * (cnt - 1) / 2;
    }
};
```

```go
func countSubstrings(s string, c byte) int64 {
	cnt := int64(strings.Count(s, string(c)))
	return cnt + cnt*(cnt-1)/2
}
```

```ts
function countSubstrings(s: string, c: string): number {
    const cnt = s.split('').filter(ch => ch === c).length;
    return cnt + Math.floor((cnt * (cnt - 1)) / 2);
}
```

<!-- tabs:end -->

<!-- end -->
