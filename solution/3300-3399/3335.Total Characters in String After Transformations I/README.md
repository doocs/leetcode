---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3335.Total%20Characters%20in%20String%20After%20Transformations%20I/README.md
rating: 1806
source: 第 421 场周赛 Q2
tags:
    - 哈希表
    - 数学
    - 字符串
    - 动态规划
    - 计数
---

<!-- problem:start -->

# [3335. 字符串转换后的长度 I](https://leetcode.cn/problems/total-characters-in-string-after-transformations-i)

[English Version](/solution/3300-3399/3335.Total%20Characters%20in%20String%20After%20Transformations%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>t</code>，表示要执行的<strong> 转换 </strong>次数。每次 <strong>转换 </strong>需要根据以下规则替换字符串 <code>s</code> 中的每个字符：</p>

<ul>
	<li>如果字符是 <code>'z'</code>，则将其替换为字符串 <code>"ab"</code>。</li>
	<li>否则，将其替换为字母表中的<strong>下一个</strong>字符。例如，<code>'a'</code> 替换为 <code>'b'</code>，<code>'b'</code> 替换为 <code>'c'</code>，依此类推。</li>
</ul>

<p>返回<strong> 恰好 </strong>执行 <code>t</code> 次转换后得到的字符串的 <strong>长度</strong>。</p>

<p>由于答案可能非常大，返回其对 <code>10<sup>9</sup> + 7</code> 取余的结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abcyy", t = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>第一次转换 (t = 1)</strong>

    <ul>
    	<li><code>'a'</code> 变为 <code>'b'</code></li>
    	<li><code>'b'</code> 变为 <code>'c'</code></li>
    	<li><code>'c'</code> 变为 <code>'d'</code></li>
    	<li><code>'y'</code> 变为 <code>'z'</code></li>
    	<li><code>'y'</code> 变为 <code>'z'</code></li>
    	<li>第一次转换后的字符串为：<code>"bcdzz"</code></li>
    </ul>
    </li>
    <li><strong>第二次转换 (t = 2)</strong>
    <ul>
    	<li><code>'b'</code> 变为 <code>'c'</code></li>
    	<li><code>'c'</code> 变为 <code>'d'</code></li>
    	<li><code>'d'</code> 变为 <code>'e'</code></li>
    	<li><code>'z'</code> 变为 <code>"ab"</code></li>
    	<li><code>'z'</code> 变为 <code>"ab"</code></li>
    	<li>第二次转换后的字符串为：<code>"cdeabab"</code></li>
    </ul>
    </li>
    <li><strong>最终字符串长度</strong>：字符串为 <code>"cdeabab"</code>，长度为 7 个字符。</li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "azbk", t = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>第一次转换 (t = 1)</strong>

    <ul>
    	<li><code>'a'</code> 变为 <code>'b'</code></li>
    	<li><code>'z'</code> 变为 <code>"ab"</code></li>
    	<li><code>'b'</code> 变为 <code>'c'</code></li>
    	<li><code>'k'</code> 变为 <code>'l'</code></li>
    	<li>第一次转换后的字符串为：<code>"babcl"</code></li>
    </ul>
    </li>
    <li><strong>最终字符串长度</strong>：字符串为 <code>"babcl"</code>，长度为 5 个字符。</li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
	<li><code>1 &lt;= t &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递推

我们定义 $f[i][j]$ 表示经过 $i$ 次转换后，字母表中第 $j$ 个字母的个数。初始时 $f[0][j]$ 为字符串 $s$ 中字母表中第 $j$ 个字母的个数。

每次转换后，字母表中第 $j$ 个字母的个数可以通过以下方式计算：

$$
\begin{align*}
f[i][0] &= f[i - 1][25] \\
f[i][1] &= f[i - 1][0] + f[i - 1][25] \\
f[i][2] &= f[i - 1][1] \\
f[i][3] &= f[i - 1][2] \\
&\vdots \\
f[i][25] &= f[i - 1][24]
\end{align*}
$$

答案为 $f[t][0] + f[t][1] + \ldots + f[t][25]$。

由于答案可能非常大，我们需要对 $10^9 + 7$ 取模。

时间复杂度 $O(t \times |\Sigma|)$，空间复杂度 $O(t \times |\Sigma|)$，其中 $|\Sigma|$ 为字母表的大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthAfterTransformations(self, s: str, t: int) -> int:
        f = [[0] * 26 for _ in range(t + 1)]
        for c in s:
            f[0][ord(c) - ord("a")] += 1
        for i in range(1, t + 1):
            f[i][0] = f[i - 1][25]
            f[i][1] = f[i - 1][0] + f[i - 1][25]
            for j in range(2, 26):
                f[i][j] = f[i - 1][j - 1]
        mod = 10**9 + 7
        return sum(f[t]) % mod
```

#### Java

```java
class Solution {
    public int lengthAfterTransformations(String s, int t) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[t + 1][26];
        for (char c : s.toCharArray()) {
            f[0][c - 'a']++;
        }
        for (int i = 1; i <= t; ++i) {
            f[i][0] = f[i - 1][25] % mod;
            f[i][1] = (f[i - 1][0] + f[i - 1][25]) % mod;
            for (int j = 2; j < 26; j++) {
                f[i][j] = f[i - 1][j - 1] % mod;
            }
        }

        int ans = 0;
        for (int j = 0; j < 26; ++j) {
            ans = (ans + f[t][j]) % mod;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lengthAfterTransformations(string s, int t) {
        const int mod = 1e9 + 7;
        vector<vector<int>> f(t + 1, vector<int>(26, 0));

        for (char c : s) {
            f[0][c - 'a']++;
        }

        for (int i = 1; i <= t; ++i) {
            f[i][0] = f[i - 1][25] % mod;
            f[i][1] = (f[i - 1][0] + f[i - 1][25]) % mod;
            for (int j = 2; j < 26; ++j) {
                f[i][j] = f[i - 1][j - 1] % mod;
            }
        }

        int ans = 0;
        for (int j = 0; j < 26; ++j) {
            ans = (ans + f[t][j]) % mod;
        }

        return ans;
    }
};
```

#### Go

```go
func lengthAfterTransformations(s string, t int) int {
	const mod = 1_000_000_007
	f := make([][]int, t+1)
	for i := range f {
		f[i] = make([]int, 26)
	}

	for _, c := range s {
		f[0][c-'a']++
	}

	for i := 1; i <= t; i++ {
		f[i][0] = f[i-1][25] % mod
		f[i][1] = (f[i-1][0] + f[i-1][25]) % mod
		for j := 2; j < 26; j++ {
			f[i][j] = f[i-1][j-1] % mod
		}
	}

	ans := 0
	for j := 0; j < 26; j++ {
		ans = (ans + f[t][j]) % mod
	}
	return ans
}
```

#### TypeScript

```ts
function lengthAfterTransformations(s: string, t: number): number {
    const mod = 1_000_000_007;
    const f: number[][] = Array.from({ length: t + 1 }, () => Array(26).fill(0));

    for (const c of s) {
        f[0][c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
    }

    for (let i = 1; i <= t; i++) {
        f[i][0] = f[i - 1][25] % mod;
        f[i][1] = (f[i - 1][0] + f[i - 1][25]) % mod;
        for (let j = 2; j < 26; j++) {
            f[i][j] = f[i - 1][j - 1] % mod;
        }
    }

    let ans = 0;
    for (let j = 0; j < 26; j++) {
        ans = (ans + f[t][j]) % mod;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
