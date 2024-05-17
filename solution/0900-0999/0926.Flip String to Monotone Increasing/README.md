---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0926.Flip%20String%20to%20Monotone%20Increasing/README.md
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [926. 将字符串翻转到单调递增](https://leetcode.cn/problems/flip-string-to-monotone-increasing)

[English Version](/solution/0900-0999/0926.Flip%20String%20to%20Monotone%20Increasing/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果一个二进制字符串，是以一些 <code>0</code>（可能没有 <code>0</code>）后面跟着一些 <code>1</code>（也可能没有 <code>1</code>）的形式组成的，那么该字符串是 <strong>单调递增 </strong>的。</p>

<p>给你一个二进制字符串 <code>s</code>，你可以将任何 <code>0</code> 翻转为 <code>1</code> 或者将 <code>1</code> 翻转为 <code>0</code> 。</p>

<p>返回使 <code>s</code> 单调递增的最小翻转次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "00110"
<strong>输出：</strong>1
<strong>解释：</strong>翻转最后一位得到 00111.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "010110"
<strong>输出：</strong>2
<strong>解释：</strong>翻转得到 011111，或者是 000111。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "00011000"
<strong>输出：</strong>2
<strong>解释：</strong>翻转得到 00000000。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 枚举

我们可以先统计字符串 $s$ 中 $0$ 的个数，记为 $tot$。定义一个答案变量 $ans$，初始时 $ans = tot$，表示将所有 $0$ 变成 $1$ 的翻转次数。

然后，我们可以枚举每个位置 $i$，将位置 $i$ 及其左边的所有 $1$ 变成 $0$，将位置 $i$ 右边的所有 $0$ 变成 $1$，计算这种情况下的翻转次数，即 $i + 1 - cur + tot - cur$，其中 $cur$ 表示位置 $i$ 及其左边的 $0$ 的个数，更新答案 $ans = \min(ans, i + 1 - cur + tot - cur)$。

最后返回答案 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        tot = s.count("0")
        ans, cur = tot, 0
        for i, c in enumerate(s, 1):
            cur += int(c == "0")
            ans = min(ans, i - cur + tot - cur)
        return ans
```

#### Java

```java
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int tot = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '0') {
                ++tot;
            }
        }
        int ans = tot, cur = 0;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) == '0') {
                ++cur;
            }
            ans = Math.min(ans, i - cur + tot - cur);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minFlipsMonoIncr(string s) {
        int tot = count(s.begin(), s.end(), '0');
        int ans = tot, cur = 0;
        for (int i = 1; i <= s.size(); ++i) {
            cur += s[i - 1] == '0';
            ans = min(ans, i - cur + tot - cur);
        }
        return ans;
    }
};
```

#### Go

```go
func minFlipsMonoIncr(s string) int {
	tot := strings.Count(s, "0")
	ans, cur := tot, 0
	for i, c := range s {
		if c == '0' {
			cur++
		}
		ans = min(ans, i+1-cur+tot-cur)
	}
	return ans
}
```

#### TypeScript

```ts
function minFlipsMonoIncr(s: string): number {
    let tot = 0;
    for (const c of s) {
        tot += c === '0' ? 1 : 0;
    }
    let [ans, cur] = [tot, 0];
    for (let i = 1; i <= s.length; ++i) {
        cur += s[i - 1] === '0' ? 1 : 0;
        ans = Math.min(ans, i - cur + tot - cur);
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var minFlipsMonoIncr = function (s) {
    let tot = 0;
    for (const c of s) {
        tot += c === '0' ? 1 : 0;
    }
    let [ans, cur] = [tot, 0];
    for (let i = 1; i <= s.length; ++i) {
        cur += s[i - 1] === '0' ? 1 : 0;
        ans = Math.min(ans, i - cur + tot - cur);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
