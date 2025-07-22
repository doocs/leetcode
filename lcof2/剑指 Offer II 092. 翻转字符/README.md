---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20092.%20%E7%BF%BB%E8%BD%AC%E5%AD%97%E7%AC%A6/README.md
---

<!-- problem:start -->

# [剑指 Offer II 092. 翻转字符](https://leetcode.cn/problems/cyJERH)

## 题目描述

<!-- description:start -->

<p>如果一个由&nbsp;<code>&#39;0&#39;</code> 和 <code>&#39;1&#39;</code>&nbsp;组成的字符串，是以一些 <code>&#39;0&#39;</code>（可能没有 <code>&#39;0&#39;</code>）后面跟着一些 <code>&#39;1&#39;</code>（也可能没有 <code>&#39;1&#39;</code>）的形式组成的，那么该字符串是&nbsp;<strong>单调递增&nbsp;</strong>的。</p>

<p>我们给出一个由字符 <code>&#39;0&#39;</code> 和 <code>&#39;1&#39;</code>&nbsp;组成的字符串 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="caret-color: rgb(199, 37, 78); font-size: 12.600000381469727px; background-color: rgb(249, 242, 244);">s</span></font>，我们可以将任何&nbsp;<code>&#39;0&#39;</code> 翻转为&nbsp;<code>&#39;1&#39;</code>&nbsp;或者将&nbsp;<code>&#39;1&#39;</code>&nbsp;翻转为&nbsp;<code>&#39;0&#39;</code>。</p>

<p>返回使 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="caret-color: rgb(199, 37, 78); font-size: 12.600000381469727px; background-color: rgb(249, 242, 244);">s</span></font>&nbsp;<strong>单调递增&nbsp;</strong>的最小翻转次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s =<strong> </strong>&quot;00110&quot;
<strong>输出：</strong>1
<strong>解释：</strong>我们翻转最后一位得到 00111.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s =<strong> </strong>&quot;010110&quot;
<strong>输出：</strong>2
<strong>解释：</strong>我们翻转得到 011111，或者是 000111。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s =<strong> </strong>&quot;00011000&quot;
<strong>输出：</strong>2
<strong>解释：</strong>我们翻转得到 00000000。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20000</code></li>
	<li><font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="caret-color: rgb(199, 37, 78); font-size: 12.600000381469727px; background-color: rgb(249, 242, 244);">s</span></font> 中只包含字符&nbsp;<code>&#39;0&#39;</code>&nbsp;和&nbsp;<code>&#39;1&#39;</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 926&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/flip-string-to-monotone-increasing/">https://leetcode.cn/problems/flip-string-to-monotone-increasing/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们先预处理得到右侧的 $0$ 的个数，记为 $right0$，初始化一个变量 $left0$，表示左侧的 $0$ 的个数。如果最终字符串变成全 `'0'` 或者全 `'1'`，那么答案为 $ans= \min(right0, n - right0)$。

接下来，我们枚举每个位置作为 `'0'` 和 `'1'` 的分界点（约定分界点为 `'0'`），计算出以当前位置为分界点的答案，最后取最小值即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        left0, right0 = 0, s.count("0")
        n = len(s)
        ans = min(right0, n - right0)
        for i, c in enumerate(s, 1):
            x = int(c)
            right0 -= x ^ 1
            left0 += x ^ 1
            ans = min(ans, i - left0 + right0)
        return ans
```

#### Java

```java
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int left0 = 0, right0 = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '0') {
                ++right0;
            }
        }
        int ans = Math.min(right0, n - right0);
        for (int i = 1; i <= n; ++i) {
            int x = s.charAt(i - 1) == '0' ? 0 : 1;
            right0 -= x ^ 1;
            left0 += x ^ 1;
            ans = Math.min(ans, i - left0 + right0);
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
        int n = s.size();
        int left0 = 0, right0 = 0;
        for (char& c : s) {
            right0 += c == '0';
        }
        int ans = min(right0, n - right0);
        for (int i = 1; i <= n; ++i) {
            int x = s[i - 1] == '1';
            right0 -= x ^ 1;
            left0 += x ^ 1;
            ans = min(ans, i - left0 + right0);
        }
        return ans;
    }
};
```

#### Go

```go
func minFlipsMonoIncr(s string) int {
	n := len(s)
	left0, right0 := 0, 0
	for _, c := range s {
		if c == '0' {
			right0++
		}
	}
	ans := min(right0, n-right0)
	for i, c := range s {
		x := 0
		if c == '1' {
			x = 1
		}
		right0 -= x ^ 1
		left0 += x ^ 1
		ans = min(ans, i+1-left0+right0)
	}
	return ans
}
```

#### TypeScript

```ts
function minFlipsMonoIncr(s: string): number {
    const n = s.length;
    let [left0, right0] = [0, 0];
    for (const c of s) {
        right0 += c === '0' ? 1 : 0;
    }
    let ans = Math.min(right0, n - right0);
    for (let i = 1; i <= n; ++i) {
        const x = s[i - 1] === '0' ? 0 : 1;
        right0 -= x ^ 1;
        left0 += x ^ 1;
        ans = Math.min(ans, i - left0 + right0);
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func minFlipsMonoIncr(_ s: String) -> Int {
        let n = s.count
        var left0 = 0, right0 = 0
        let chars = Array(s)

        for char in chars {
            if char == "0" {
                right0 += 1
            }
        }

        var ans = min(right0, n - right0)

        for i in 1...n {
            let x = chars[i - 1] == "0" ? 0 : 1
            right0 -= x ^ 1
            left0 += x ^ 1
            ans = min(ans, i - left0 + right0)
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
