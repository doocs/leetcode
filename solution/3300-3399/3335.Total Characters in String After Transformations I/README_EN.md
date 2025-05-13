---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3335.Total%20Characters%20in%20String%20After%20Transformations%20I/README_EN.md
rating: 1806
source: Weekly Contest 421 Q2
tags:
    - Hash Table
    - Math
    - String
    - Dynamic Programming
    - Counting
---

<!-- problem:start -->

# [3335. Total Characters in String After Transformations I](https://leetcode.com/problems/total-characters-in-string-after-transformations-i)

[中文文档](/solution/3300-3399/3335.Total%20Characters%20in%20String%20After%20Transformations%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>t</code>, representing the number of <strong>transformations</strong> to perform. In one <strong>transformation</strong>, every character in <code>s</code> is replaced according to the following rules:</p>

<ul>
	<li>If the character is <code>&#39;z&#39;</code>, replace it with the string <code>&quot;ab&quot;</code>.</li>
	<li>Otherwise, replace it with the <strong>next</strong> character in the alphabet. For example, <code>&#39;a&#39;</code> is replaced with <code>&#39;b&#39;</code>, <code>&#39;b&#39;</code> is replaced with <code>&#39;c&#39;</code>, and so on.</li>
</ul>

<p>Return the <strong>length</strong> of the resulting string after <strong>exactly</strong> <code>t</code> transformations.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong><!-- notionvc: eb142f2b-b818-4064-8be5-e5a36b07557a --> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcyy&quot;, t = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>First Transformation (t = 1)</strong>:

    <ul>
    	<li><code>&#39;a&#39;</code> becomes <code>&#39;b&#39;</code></li>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code></li>
    	<li><code>&#39;c&#39;</code> becomes <code>&#39;d&#39;</code></li>
    	<li><code>&#39;y&#39;</code> becomes <code>&#39;z&#39;</code></li>
    	<li><code>&#39;y&#39;</code> becomes <code>&#39;z&#39;</code></li>
    	<li>String after the first transformation: <code>&quot;bcdzz&quot;</code></li>
    </ul>
    </li>
    <li><strong>Second Transformation (t = 2)</strong>:
    <ul>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code></li>
    	<li><code>&#39;c&#39;</code> becomes <code>&#39;d&#39;</code></li>
    	<li><code>&#39;d&#39;</code> becomes <code>&#39;e&#39;</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&quot;ab&quot;</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&quot;ab&quot;</code></li>
    	<li>String after the second transformation: <code>&quot;cdeabab&quot;</code></li>
    </ul>
    </li>
    <li><strong>Final Length of the string</strong>: The string is <code>&quot;cdeabab&quot;</code>, which has 7 characters.</li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;azbk&quot;, t = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>First Transformation (t = 1)</strong>:

    <ul>
    	<li><code>&#39;a&#39;</code> becomes <code>&#39;b&#39;</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&quot;ab&quot;</code></li>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code></li>
    	<li><code>&#39;k&#39;</code> becomes <code>&#39;l&#39;</code></li>
    	<li>String after the first transformation: <code>&quot;babcl&quot;</code></li>
    </ul>
    </li>
    <li><strong>Final Length of the string</strong>: The string is <code>&quot;babcl&quot;</code>, which has 5 characters.</li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= t &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions
To solve this problem efficiently, we cannot simulate every transformation by building the actual string (as the length may grow exponentially). Instead, we track the length of each character's transformation result using dynamic programming.

💡 Idea:

    Let dp[i][c] represent the length of the result string after i transformations of character c.

    If c == 'z', after one transformation, it becomes "ab" → so length becomes:

        dp[i][z] = dp[i-1][a] + dp[i-1][b]

    If c != 'z', then:

        dp[i][c] = dp[i-1][next(c)]

✅ Steps:

    Initialize dp[0][c] = 1 for all characters, because 0 transformation means the character stays as-is.

    For each transformation step i from 1 to t, compute the length for each character.

    For the final result, sum dp[t][s.charAt(i)] for all characters in s.
<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthAfterTransformations(self, s: str, t: int) -> int:
        MOD = 10**9 + 7

        # dp[i][c] will be the length of character c ('a' to 'z') after i transformations
        dp = [[0] * 26 for _ in range(t + 1)]

        # Base case: 0 transformations, each character remains as itself
        for c in range(26):
            dp[0][c] = 1

        # Build up dp table for 1 to t transformations
        for i in range(1, t + 1):
            for c in range(26):
                if c == 25:  # 'z'
                    dp[i][c] = (dp[i-1][0] + dp[i-1][1]) % MOD  # 'z' â 'a' + 'b'
                else:
                    dp[i][c] = dp[i-1][c + 1]  # shift to next character

        # Calculate the total length after t transformations for input string s
        result = 0
        for ch in s:
            result = (result + dp[t][ord(ch) - ord('a')]) % MOD

        return result
```

#### Java

```java
class Solution {

     private static final int MOD = 1_000_000_007;
     
    public int lengthAfterTransformations(String s, int t) {
         int[][] dp = new int[t + 1][26]; // dp[i][c] = length of result string after i transformations of char c

        // Base case: 0 transformations â length is 1 for each character
        for (int c = 0; c < 26; c++) {
            dp[0][c] = 1;
        }

        // Fill DP table
        for (int i = 1; i <= t; i++) {
            for (int c = 0; c < 26; c++) {
                if (c == 25) { // 'z'
                    dp[i][c] = (dp[i - 1][0] + dp[i - 1][1]) % MOD; // 'z' â "ab"
                } else {
                    dp[i][c] = dp[i - 1][c + 1]; // next character
                }
            }
        }

        // Compute total length after t transformations
        long result = 0;
        for (char ch : s.toCharArray()) {
            result = (result + dp[t][ch - 'a']) % MOD;
        }

        return (int) result;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lengthAfterTransformations(string s, int t) {
        const int MOD = 1e9 + 7;
        vector<vector<int>> dp(t + 1, vector<int>(26, 0));

        // Base case: 0 transformations, each character has length 1
        for (int c = 0; c < 26; ++c) {
            dp[0][c] = 1;
        }

        // Fill dp table for 1 to t transformations
        for (int i = 1; i <= t; ++i) {
            for (int c = 0; c < 26; ++c) {
                if (c == 25) { // 'z'
                    dp[i][c] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
                } else {
                    dp[i][c] = dp[i - 1][c + 1];
                }
            }
        }

        // Calculate total length after t transformations
        long long result = 0;
        for (char ch : s) {
            result = (result + dp[t][ch - 'a']) % MOD;
        }

        return static_cast<int>(result);
    }
};
```

#### Go

```go
func lengthAfterTransformations(s string, t int) int {
    const MOD = 1_000_000_007

    // dp[i][c] = length of character 'a'+c after i transformations
    dp := make([][]int, t+1)
    for i := range dp {
        dp[i] = make([]int, 26)
    }

    // Base case: 0 transformations â each character has length 1
    for c := 0; c < 26; c++ {
        dp[0][c] = 1
    }

    // Build DP table for 1 to t transformations
    for i := 1; i <= t; i++ {
        for c := 0; c < 26; c++ {
            if c == 25 { // 'z'
                dp[i][c] = (dp[i-1][0] + dp[i-1][1]) % MOD
            } else {
                dp[i][c] = dp[i-1][c+1]
            }
        }
    }

    // Compute the total length after t transformations
    result := 0
    for _, ch := range s {
        result = (result + dp[t][ch-'a']) % MOD
    }

    return result
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
