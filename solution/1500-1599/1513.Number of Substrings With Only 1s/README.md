---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1513.Number%20of%20Substrings%20With%20Only%201s/README.md
rating: 1351
source: 第 197 场周赛 Q2
tags:
    - 数学
    - 字符串
---

<!-- problem:start -->

# [1513. 仅含 1 的子串数](https://leetcode.cn/problems/number-of-substrings-with-only-1s)

[English Version](/solution/1500-1599/1513.Number%20of%20Substrings%20With%20Only%201s/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串 <code>s</code>（仅由 &#39;0&#39; 和 &#39;1&#39; 组成的字符串）。</p>

<p>返回所有字符都为 1 的子字符串的数目。</p>

<p>由于答案可能很大，请你将它对 10^9 + 7 取模后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;0110111&quot;
<strong>输出</strong>：9
<strong>解释：</strong>共有 9 个子字符串仅由 &#39;1&#39; 组成
&quot;1&quot; -&gt; 5 次
&quot;11&quot; -&gt; 3 次
&quot;111&quot; -&gt; 1 次</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;101&quot;
<strong>输出：</strong>2
<strong>解释：</strong>子字符串 &quot;1&quot; 在 s 中共出现 2 次
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;111111&quot;
<strong>输出：</strong>21
<strong>解释：</strong>每个子字符串都仅由 &#39;1&#39; 组成
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;000&quot;
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s[i] == &#39;0&#39;</code> 或 <code>s[i] == &#39;1&#39;</code></li>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历计数

我们遍历字符串 $s$，用一个变量 $\textit{cur}$ 记录当前连续的 1 的个数，用变量 $\textit{ans}$ 记录答案。当遍历到字符 $s[i]$ 时，如果 $s[i] = 0$，则 $\textit{cur}$ 置 0，否则 $\textit{cur}$ 自增 1，然后 $\textit{ans}$ 自增 $\textit{cur}$，并对 $10^9 + 7$ 取模。

遍历结束，返回 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

相似题目：

- [413. 等差数列划分](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0413.Arithmetic%20Slices/README.md)
- [2348. 全 0 子数组的数目](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2348.Number%20of%20Zero-Filled%20Subarrays/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSub(self, s: str) -> int:
        mod = 10**9 + 7
        ans = cur = 0
        for c in s:
            if c == "0":
                cur = 0
            else:
                cur += 1
                ans = (ans + cur) % mod
        return ans
```

#### Java

```java
class Solution {
    public int numSub(String s) {
        final int mod = 1_000_000_007;
        int ans = 0, cur = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                cur = 0;
            } else {
                cur++;
                ans = (ans + cur) % mod;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numSub(string s) {
        const int mod = 1e9 + 7;
        int ans = 0, cur = 0;
        for (char c : s) {
            if (c == '0') {
                cur = 0;
            } else {
                cur++;
                ans = (ans + cur) % mod;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numSub(s string) (ans int) {
	const mod int = 1e9 + 7
	cur := 0
	for _, c := range s {
		if c == '0' {
			cur = 0
		} else {
			cur++
			ans = (ans + cur) % mod
		}
	}
	return
}
```

#### TypeScript

```ts
function numSub(s: string): number {
    const mod = 1_000_000_007;
    let [ans, cur] = [0, 0];
    for (const c of s) {
        if (c === '0') {
            cur = 0;
        } else {
            cur++;
            ans = (ans + cur) % mod;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_sub(s: String) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut ans: i32 = 0;
        let mut cur: i32 = 0;
        for c in s.chars() {
            if c == '0' {
                cur = 0;
            } else {
                cur += 1;
                ans = (ans + cur) % MOD;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
