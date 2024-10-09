---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2207.Maximize%20Number%20of%20Subsequences%20in%20a%20String/README_EN.md
rating: 1550
source: Biweekly Contest 74 Q2
tags:
    - Greedy
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [2207. Maximize Number of Subsequences in a String](https://leetcode.com/problems/maximize-number-of-subsequences-in-a-string)

[中文文档](/solution/2200-2299/2207.Maximize%20Number%20of%20Subsequences%20in%20a%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string <code>text</code> and another <strong>0-indexed</strong> string <code>pattern</code> of length <code>2</code>, both of which consist of only lowercase English letters.</p>

<p>You can add <strong>either</strong> <code>pattern[0]</code> <strong>or</strong> <code>pattern[1]</code> anywhere in <code>text</code> <strong>exactly once</strong>. Note that the character can be added even at the beginning or at the end of <code>text</code>.</p>

<p>Return <em>the <strong>maximum</strong> number of times</em> <code>pattern</code> <em>can occur as a <strong>subsequence</strong> of the modified </em><code>text</code>.</p>

<p>A <b>subsequence</b> is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;abdcdbc&quot;, pattern = &quot;ac&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong>
If we add pattern[0] = &#39;a&#39; in between text[1] and text[2], we get &quot;ab<u><strong>a</strong></u>dcdbc&quot;. Now, the number of times &quot;ac&quot; occurs as a subsequence is 4.
Some other strings which have 4 subsequences &quot;ac&quot; after adding a character to text are &quot;<u><strong>a</strong></u>abdcdbc&quot; and &quot;abd<u><strong>a</strong></u>cdbc&quot;.
However, strings such as &quot;abdc<u><strong>a</strong></u>dbc&quot;, &quot;abd<u><strong>c</strong></u>cdbc&quot;, and &quot;abdcdbc<u><strong>c</strong></u>&quot;, although obtainable, have only 3 subsequences &quot;ac&quot; and are thus suboptimal.
It can be shown that it is not possible to get more than 4 subsequences &quot;ac&quot; by adding only one character.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;aabb&quot;, pattern = &quot;ab&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong>
Some of the strings which can be obtained from text and have 6 subsequences &quot;ab&quot; are &quot;<u><strong>a</strong></u>aabb&quot;, &quot;aa<u><strong>a</strong></u>bb&quot;, and &quot;aab<u><strong>b</strong></u>b&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10<sup>5</sup></code></li>
	<li><code>pattern.length == 2</code></li>
	<li><code>text</code> and <code>pattern</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal + Counting

We can use two variables $x$ and $y$ to record the current counts of $\textit{pattern}[0]$ and $\textit{pattern}[1]$ in the string, respectively.

Then, traverse the string $\textit{text}$. For the current character $c$:

-   If $c$ equals $\textit{pattern}[1]$, increment $y$ by one. At this point, all previously encountered $\textit{pattern}[0]$ can form a $\textit{pattern}$ subsequence with the current $c$, so add $x$ to the answer.
-   If $c$ equals $\textit{pattern}[0]$, increment $x$ by one.

After the traversal, since we can insert one character, if we add $\textit{pattern}[0]$ at the beginning of the string, we can get $y$ $\textit{pattern}$ subsequences. If we add $\textit{pattern}[1]$ at the end of the string, we can get $x$ $\textit{pattern}$ subsequences. Therefore, we add the larger value of $x$ and $y$ to the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{text}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSubsequenceCount(self, text: str, pattern: str) -> int:
        ans = x = y = 0
        for c in text:
            if c == pattern[1]:
                y += 1
                ans += x
            if c == pattern[0]:
                x += 1
        ans += max(x, y)
        return ans
```

#### Java

```java
class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        long ans = 0;
        int x = 0, y = 0;
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == pattern.charAt(1)) {
                ++y;
                ans += x;
            }
            if (text.charAt(i) == pattern.charAt(0)) {
                ++x;
            }
        }
        ans += Math.max(x, y);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumSubsequenceCount(string text, string pattern) {
        long long ans = 0;
        int x = 0, y = 0;
        for (char& c : text) {
            if (c == pattern[1]) {
                ++y;
                ans += x;
            }
            if (c == pattern[0]) {
                ++x;
            }
        }
        ans += max(x, y);
        return ans;
    }
};
```

#### Go

```go
func maximumSubsequenceCount(text string, pattern string) (ans int64) {
	x, y := 0, 0
	for _, c := range text {
		if byte(c) == pattern[1] {
			y++
			ans += int64(x)
		}
		if byte(c) == pattern[0] {
			x++
		}
	}
	ans += int64(max(x, y))
	return
}
```

#### TypeScript

```ts
function maximumSubsequenceCount(text: string, pattern: string): number {
    let ans = 0;
    let [x, y] = [0, 0];
    for (const c of text) {
        if (c === pattern[1]) {
            ++y;
            ans += x;
        }
        if (c === pattern[0]) {
            ++x;
        }
    }
    ans += Math.max(x, y);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
