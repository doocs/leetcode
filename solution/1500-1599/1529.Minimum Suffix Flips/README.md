---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1529.Minimum%20Suffix%20Flips/README.md
rating: 1392
source: 第 199 场周赛 Q2
tags:
    - 贪心
    - 字符串
---

<!-- problem:start -->

# [1529. 最少的后缀翻转次数](https://leetcode.cn/problems/minimum-suffix-flips)

[English Version](/solution/1500-1599/1529.Minimum%20Suffix%20Flips/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 、下标从 <strong>0</strong> 开始的二进制字符串 <code>target</code> 。你自己有另一个长度为 <code>n</code> 的二进制字符串 <code>s</code> ，最初每一位上都是 0 。你想要让 <code>s</code> 和 <code>target</code> 相等。</p>

<p>在一步操作，你可以选择下标 <code>i</code>（<code>0 &lt;= i &lt; n</code>）并翻转在 <strong>闭区间</strong> <code>[i, n - 1]</code> 内的所有位。翻转意味着 <code>'0'</code> 变为 <code>'1'</code> ，而 <code>'1'</code> 变为 <code>'0'</code> 。</p>

<div class="original__bRMd">
<div>
<p>返回使<em> </em><code>s</code><em> </em>与<em> </em><code>target</code> 相等需要的最少翻转次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = "10111"
<strong>输出：</strong>3
<strong>解释：</strong>最初，s = "00000" 。
选择下标 i = 2: "00<em><strong>000</strong></em>" -&gt; "00<em><strong>111</strong></em>"
选择下标 i = 0: "<em><strong>00111</strong></em>" -&gt; "<em><strong>11000</strong></em>"
选择下标 i = 1: "1<em><strong>1000</strong></em>" -&gt; "1<em><strong>0111</strong></em>"
要达成目标，需要至少 3 次翻转。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = "101"
<strong>输出：</strong>3
<strong>解释：</strong>最初，s = "000" 。
选择下标 i = 0: "<em><strong>000</strong></em>" -&gt; "<em><strong>111</strong></em>"
选择下标 i = 1: "1<em><strong>11</strong></em>" -&gt; "1<em><strong>00</strong></em>"
选择下标 i = 2: "10<em><strong>0</strong></em>" -&gt; "10<em><strong>1</strong></em>"
要达成目标，需要至少 3 次翻转。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = "00000"
<strong>输出：</strong>0
<strong>解释：</strong>由于 s 已经等于目标，所以不需要任何操作
</pre>
</div>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>target[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们从左到右遍历字符串 $\textit{target}$，用变量 $\textit{ans}$ 记录翻转次数。 当遍历到下标 $i$ 时，如果当前的翻转次数 $\textit{ans}$ 的奇偶性与 $\textit{target}[i]$ 不同，则需要在下标 $i$ 处进行一次翻转操作，将 $\textit{ans}$ 加 $1$。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minFlips(self, target: str) -> int:
        ans = 0
        for v in target:
            if (ans & 1) ^ int(v):
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minFlips(String target) {
        int ans = 0;
        for (int i = 0; i < target.length(); ++i) {
            int v = target.charAt(i) - '0';
            if (((ans & 1) ^ v) != 0) {
                ++ans;
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
    int minFlips(string target) {
        int ans = 0;
        for (char c : target) {
            int v = c - '0';
            if ((ans & 1) ^ v) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minFlips(target string) int {
	ans := 0
	for _, c := range target {
		v := int(c - '0')
		if ((ans & 1) ^ v) != 0 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minFlips(target: string): number {
    let ans = 0;
    for (const c of target) {
        if (ans % 2 !== +c) {
            ++ans;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_flips(target: String) -> i32 {
        let mut ans = 0;
        for c in target.chars() {
            let bit = (c as u8 - b'0') as i32;
            if ans % 2 != bit {
                ans += 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
