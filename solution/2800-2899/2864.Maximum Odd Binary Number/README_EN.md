---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2864.Maximum%20Odd%20Binary%20Number/README_EN.md
rating: 1237
source: Weekly Contest 364 Q1
tags:
    - Greedy
    - Math
    - String
---

<!-- problem:start -->

# [2864. Maximum Odd Binary Number](https://leetcode.com/problems/maximum-odd-binary-number)

[中文文档](/solution/2800-2899/2864.Maximum%20Odd%20Binary%20Number/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>binary</strong> string <code>s</code> that contains at least one <code>&#39;1&#39;</code>.</p>

<p>You have to <strong>rearrange</strong> the bits in such a way that the resulting binary number is the <strong>maximum odd binary number</strong> that can be created from this combination.</p>

<p>Return <em>a string representing the maximum odd binary number that can be created from the given combination.</em></p>

<p><strong>Note </strong>that the resulting string <strong>can</strong> have leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;010&quot;
<strong>Output:</strong> &quot;001&quot;
<strong>Explanation:</strong> Because there is just one &#39;1&#39;, it must be in the last position. So the answer is &quot;001&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0101&quot;
<strong>Output:</strong> &quot;1001&quot;
<strong>Explanation: </strong>One of the &#39;1&#39;s must be in the last position. The maximum number that can be made with the remaining digits is &quot;100&quot;. So the answer is &quot;1001&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
	<li><code>s</code> contains at least one <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

First, we count the number of '1's in the string $s$, denoted as $cnt$. Then, we place $cnt - 1$ '1's at the highest position, followed by the remaining $|s| - cnt$ '0's, and finally add one '1'.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def maximumOddBinaryNumber(self, s: str) -> str:
        cnt = s.count("1")
        return "1" * (cnt - 1) + (len(s) - cnt) * "0" + "1"
```

```java
class Solution {
    public String maximumOddBinaryNumber(String s) {
        int cnt = s.length() - s.replace("1", "").length();
        return "1".repeat(cnt - 1) + "0".repeat(s.length() - cnt) + "1";
    }
}
```

```cpp
class Solution {
public:
    string maximumOddBinaryNumber(string s) {
        int cnt = count(s.begin(), s.end(), '1');
        return string(cnt - 1, '1') + string(s.size() - cnt, '0') + '1';
    }
};
```

```go
func maximumOddBinaryNumber(s string) string {
	cnt := strings.Count(s, "1")
	return strings.Repeat("1", cnt-1) + strings.Repeat("0", len(s)-cnt) + "1"
}
```

```ts
function maximumOddBinaryNumber(s: string): string {
    const cnt = s.length - s.replace(/1/g, '').length;
    return '1'.repeat(cnt - 1) + '0'.repeat(s.length - cnt) + '1';
}
```

```rust
impl Solution {
    pub fn maximum_odd_binary_number(s: String) -> String {
        let cnt = s
            .chars()
            .filter(|&c| c == '1')
            .count();
        "1".repeat(cnt - 1) + &"0".repeat(s.len() - cnt) + "1"
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
