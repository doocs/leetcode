---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2266.Count%20Number%20of%20Texts/README_EN.md
rating: 1856
source: Weekly Contest 292 Q3
tags:
    - Hash Table
    - Math
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [2266. Count Number of Texts](https://leetcode.com/problems/count-number-of-texts)

[中文文档](/solution/2200-2299/2266.Count%20Number%20of%20Texts/README.md)

## Description

<!-- description:start -->

<p>Alice is texting Bob using her phone. The <strong>mapping</strong> of digits to letters is shown in the figure below.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2266.Count%20Number%20of%20Texts/images/1200px-telephone-keypad2svg.png" style="width: 200px; height: 162px;" />
<p>In order to <strong>add</strong> a letter, Alice has to <strong>press</strong> the key of the corresponding digit <code>i</code> times, where <code>i</code> is the position of the letter in the key.</p>

<ul>
	<li>For example, to add the letter <code>&#39;s&#39;</code>, Alice has to press <code>&#39;7&#39;</code> four times. Similarly, to add the letter <code>&#39;k&#39;</code>, Alice has to press <code>&#39;5&#39;</code> twice.</li>
	<li>Note that the digits <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code> do not map to any letters, so Alice <strong>does not</strong> use them.</li>
</ul>

<p>However, due to an error in transmission, Bob did not receive Alice&#39;s text message but received a <strong>string of pressed keys</strong> instead.</p>

<ul>
	<li>For example, when Alice sent the message <code>&quot;bob&quot;</code>, Bob received the string <code>&quot;2266622&quot;</code>.</li>
</ul>

<p>Given a string <code>pressedKeys</code> representing the string received by Bob, return <em>the <strong>total number of possible text messages</strong> Alice could have sent</em>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> pressedKeys = &quot;22233&quot;
<strong>Output:</strong> 8
<strong>Explanation:</strong>
The possible text messages Alice could have sent are:
&quot;aaadd&quot;, &quot;abdd&quot;, &quot;badd&quot;, &quot;cdd&quot;, &quot;aaae&quot;, &quot;abe&quot;, &quot;bae&quot;, and &quot;ce&quot;.
Since there are 8 possible messages, we return 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> pressedKeys = &quot;222222222222222222222222222222222222&quot;
<strong>Output:</strong> 82876089
<strong>Explanation:</strong>
There are 2082876103 possible text messages Alice could have sent.
Since we need to return the answer modulo 10<sup>9</sup> + 7, we return 2082876103 % (10<sup>9</sup> + 7) = 82876089.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pressedKeys.length &lt;= 10<sup>5</sup></code></li>
	<li><code>pressedKeys</code> only consists of digits from <code>&#39;2&#39;</code> - <code>&#39;9&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping + Dynamic Programming

According to the problem description, for consecutive identical characters in the string $\textit{pressedKeys}$, we can group them together and then calculate the number of ways for each group. Finally, we multiply the number of ways for all groups.

The key problem is how to calculate the number of ways for each group.

If a group of characters is '7' or '9', we can consider the last $1$, $2$, $3$, or $4$ characters of the group as one letter, then reduce the size of the group and transform it into a smaller subproblem.

Similarly, if a group of characters is '2', '3', '4', '5', '6', or '8', we can consider the last $1$, $2$, or $3$ characters of the group as one letter, then reduce the size of the group and transform it into a smaller subproblem.

Therefore, we define $f[i]$ to represent the number of ways for a group of length $i$ with identical characters that are not '7' or '9', and $g[i]$ to represent the number of ways for a group of length $i$ with identical characters that are '7' or '9'.

Initially, $f[0] = f[1] = 1$, $f[2] = 2$, $f[3] = 4$, $g[0] = g[1] = 1$, $g[2] = 2$, $g[3] = 4$.

For $i \ge 4$, we have:

$$
\begin{aligned}
f[i] & = f[i-1] + f[i-2] + f[i-3] \\
g[i] & = g[i-1] + g[i-2] + g[i-3] + g[i-4]
\end{aligned}
$$

Finally, we traverse $\textit{pressedKeys}$, group consecutive identical characters, calculate the number of ways for each group, and multiply the number of ways for all groups.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $\textit{pressedKeys}$.

<!-- tabs:start -->

#### Python3

```python
mod = 10**9 + 7
f = [1, 1, 2, 4]
g = [1, 1, 2, 4]
for _ in range(100000):
    f.append((f[-1] + f[-2] + f[-3]) % mod)
    g.append((g[-1] + g[-2] + g[-3] + g[-4]) % mod)


class Solution:
    def countTexts(self, pressedKeys: str) -> int:
        ans = 1
        for c, s in groupby(pressedKeys):
            m = len(list(s))
            ans = ans * (g[m] if c in "79" else f[m]) % mod
        return ans
```

#### Java

```java
class Solution {
    private static final int N = 100010;
    private static final int MOD = (int) 1e9 + 7;
    private static long[] f = new long[N];
    private static long[] g = new long[N];
    static {
        f[0] = f[1] = 1;
        f[2] = 2;
        f[3] = 4;
        g[0] = g[1] = 1;
        g[2] = 2;
        g[3] = 4;
        for (int i = 4; i < N; ++i) {
            f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % MOD;
            g[i] = (g[i - 1] + g[i - 2] + g[i - 3] + g[i - 4]) % MOD;
        }
    }

    public int countTexts(String pressedKeys) {
        long ans = 1;
        for (int i = 0, n = pressedKeys.length(); i < n; ++i) {
            char c = pressedKeys.charAt(i);
            int j = i;
            while (j + 1 < n && pressedKeys.charAt(j + 1) == c) {
                ++j;
            }
            int cnt = j - i + 1;
            ans = c == '7' || c == '9' ? ans * g[cnt] : ans * f[cnt];
            ans %= MOD;
            i = j;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
const int mod = 1e9 + 7;
const int n = 1e5 + 10;
long long f[n], g[n];

int init = []() {
    f[0] = g[0] = 1;
    f[1] = g[1] = 1;
    f[2] = g[2] = 2;
    f[3] = g[3] = 4;
    for (int i = 4; i < n; ++i) {
        f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % mod;
        g[i] = (g[i - 1] + g[i - 2] + g[i - 3] + g[i - 4]) % mod;
    }
    return 0;
}();

class Solution {
public:
    int countTexts(string pressedKeys) {
        long long ans = 1;
        for (int i = 0, n = pressedKeys.length(); i < n; ++i) {
            char c = pressedKeys[i];
            int j = i;
            while (j + 1 < n && pressedKeys[j + 1] == c) {
                ++j;
            }
            int cnt = j - i + 1;
            ans = c == '7' || c == '9' ? ans * g[cnt] : ans * f[cnt];
            ans %= mod;
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
const mod int = 1e9 + 7
const n int = 1e5 + 10

var f = [n]int{1, 1, 2, 4}
var g = f

func init() {
	for i := 4; i < n; i++ {
		f[i] = (f[i-1] + f[i-2] + f[i-3]) % mod
		g[i] = (g[i-1] + g[i-2] + g[i-3] + g[i-4]) % mod
	}
}

func countTexts(pressedKeys string) int {
	ans := 1
	for i, j, n := 0, 0, len(pressedKeys); i < n; i++ {
		c := pressedKeys[i]
		j = i
		for j+1 < n && pressedKeys[j+1] == c {
			j++
		}
		cnt := j - i + 1
		if c == '7' || c == '9' {
			ans = ans * g[cnt] % mod
		} else {
			ans = ans * f[cnt] % mod
		}
		i = j
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
