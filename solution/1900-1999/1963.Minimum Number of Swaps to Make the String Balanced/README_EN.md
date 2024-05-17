---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1963.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20String%20Balanced/README_EN.md
rating: 1688
source: Weekly Contest 253 Q3
tags:
    - Stack
    - Greedy
    - Two Pointers
    - String
---

<!-- problem:start -->

# [1963. Minimum Number of Swaps to Make the String Balanced](https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced)

[中文文档](/solution/1900-1999/1963.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20String%20Balanced/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string <code>s</code> of <strong>even</strong> length <code>n</code>. The string consists of <strong>exactly</strong> <code>n / 2</code> opening brackets <code>&#39;[&#39;</code> and <code>n / 2</code> closing brackets <code>&#39;]&#39;</code>.</p>

<p>A string is called <strong>balanced</strong> if and only if:</p>

<ul>
	<li>It is the empty string, or</li>
	<li>It can be written as <code>AB</code>, where both <code>A</code> and <code>B</code> are <strong>balanced</strong> strings, or</li>
	<li>It can be written as <code>[C]</code>, where <code>C</code> is a <strong>balanced</strong> string.</li>
</ul>

<p>You may swap the brackets at <strong>any</strong> two indices <strong>any</strong> number of times.</p>

<p>Return <em>the <strong>minimum</strong> number of swaps to make </em><code>s</code> <em><strong>balanced</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;][][&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can make the string balanced by swapping index 0 with index 3.
The resulting string is &quot;[[]]&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;]]][[[&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can do the following to make the string balanced:
- Swap index 0 with index 4. s = &quot;[]][][&quot;.
- Swap index 1 with index 5. s = &quot;[[][]]&quot;.
The resulting string is &quot;[[][]]&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;[]&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The string is already balanced.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>n</code> is even.</li>
	<li><code>s[i]</code> is either <code>&#39;[&#39; </code>or <code>&#39;]&#39;</code>.</li>
	<li>The number of opening brackets <code>&#39;[&#39;</code> equals <code>n / 2</code>, and the number of closing brackets <code>&#39;]&#39;</code> equals <code>n / 2</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We use a variable $x$ to record the current number of unmatched left brackets. We traverse the string $s$, for each character $c$:

-   If $c$ is a left bracket, then we increment $x$ by one;
-   If $c$ is a right bracket, then we need to check whether $x$ is greater than zero. If it is, we match the current right bracket with the nearest unmatched left bracket on the left, i.e., decrement $x$ by one.

After the traversal, we will definitely get a string of the form `"]]]...[[[..."`. We then greedily swap the brackets at both ends each time, which can eliminate $2$ unmatched left brackets at a time. Therefore, the total number of swaps needed is $\left\lfloor \frac{x + 1}{2} \right\rfloor$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minSwaps(self, s: str) -> int:
        x = 0
        for c in s:
            if c == "[":
                x += 1
            elif x:
                x -= 1
        return (x + 1) >> 1
```

```java
class Solution {
    public int minSwaps(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '[') {
                ++x;
            } else if (x > 0) {
                --x;
            }
        }
        return (x + 1) / 2;
    }
}
```

```cpp
class Solution {
public:
    int minSwaps(string s) {
        int x = 0;
        for (char& c : s) {
            if (c == '[') {
                ++x;
            } else if (x) {
                --x;
            }
        }
        return (x + 1) / 2;
    }
};
```

```go
func minSwaps(s string) int {
	x := 0
	for _, c := range s {
		if c == '[' {
			x++
		} else if x > 0 {
			x--
		}
	}
	return (x + 1) / 2
}
```

```ts
function minSwaps(s: string): number {
    let x = 0;
    for (const c of s) {
        if (c === '[') {
            ++x;
        } else if (x) {
            --x;
        }
    }
    return (x + 1) >> 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
