---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2272.Substring%20With%20Largest%20Variance/README.md
rating: 2515
source: 第 78 场双周赛 Q4
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [2272. 最大波动的子字符串](https://leetcode.cn/problems/substring-with-largest-variance)

[English Version](/solution/2200-2299/2272.Substring%20With%20Largest%20Variance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>字符串的 <strong>波动</strong>&nbsp;定义为子字符串中出现次数 <strong>最多</strong>&nbsp;的字符次数与出现次数 <strong>最少</strong>&nbsp;的字符次数之差。</p>

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它只包含小写英文字母。请你返回 <code>s</code>&nbsp;里所有 <strong>子字符串的</strong>&nbsp;<strong>最大波动</strong>&nbsp;值。</p>

<p><strong>子字符串</strong> 是一个字符串的一段连续字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aababbb"
<b>输出：</b>3
<strong>解释：</strong>
所有可能的波动值和它们对应的子字符串如以下所示：
- 波动值为 0 的子字符串："a" ，"aa" ，"ab" ，"abab" ，"aababb" ，"ba" ，"b" ，"bb" 和 "bbb" 。
- 波动值为 1 的子字符串："aab" ，"aba" ，"abb" ，"aabab" ，"ababb" ，"aababbb" 和 "bab" 。
- 波动值为 2 的子字符串："aaba" ，"ababbb" ，"abbb" 和 "babb" 。
- 波动值为 3 的子字符串 "babbb" 。
所以，最大可能波动值为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "abcde"
<b>输出：</b>0
<strong>解释：</strong>
s 中没有字母出现超过 1 次，所以 s 中每个子字符串的波动值都是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp; 只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 动态规划

由于字符集只包含小写字母，我们可以考虑枚举出现次数最多的字符 $a$ 以及出现次数最少的字符 $b$。对于一个子串来说，这两种字符出现的次数之差就是子串的波动值。

具体实现上，我们使用双重循环枚举 $a$ 和 $b$，用 $f[0]$ 记录以当前字符结尾的连续出现字符 $a$ 的个数，用 $f[1]$ 记录以当前字符结尾的，并且包含 $a$ 和 $b$ 的子串的波动值。迭代取 $f[1]$ 的最大值即可。

递推公式如下：

1. 如果当前字符为 $a$，则 $f[0]$ 和 $f[1]$ 都加 $1$；
1. 如果当前字符为 $b$，则 $f[1] = \max(f[1] - 1, f[0] - 1)$，而 $f[0] = 0$；
1. 否则，无需考虑。

注意，初始时将 $f[1]$ 赋值为一个负数最大值，可以保证更新答案时是合法的。

时间复杂度 $O(n \times |\Sigma|^2)$，其中 $n$ 是字符串长度，而 $|\Sigma|$ 是字符集大小。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestVariance(self, s: str) -> int:
        ans = 0
        for a, b in permutations(ascii_lowercase, 2):
            if a == b:
                continue
            f = [0, -inf]
            for c in s:
                if c == a:
                    f[0], f[1] = f[0] + 1, f[1] + 1
                elif c == b:
                    f[1] = max(f[1] - 1, f[0] - 1)
                    f[0] = 0
                if ans < f[1]:
                    ans = f[1]
        return ans
```

#### Java

```java
class Solution {
    public int largestVariance(String s) {
        int n = s.length();
        int ans = 0;
        for (char a = 'a'; a <= 'z'; ++a) {
            for (char b = 'a'; b <= 'z'; ++b) {
                if (a == b) {
                    continue;
                }
                int[] f = new int[] {0, -n};
                for (int i = 0; i < n; ++i) {
                    if (s.charAt(i) == a) {
                        f[0]++;
                        f[1]++;
                    } else if (s.charAt(i) == b) {
                        f[1] = Math.max(f[0] - 1, f[1] - 1);
                        f[0] = 0;
                    }
                    ans = Math.max(ans, f[1]);
                }
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
    int largestVariance(string s) {
        int n = s.size();
        int ans = 0;
        for (char a = 'a'; a <= 'z'; ++a) {
            for (char b = 'a'; b <= 'z'; ++b) {
                if (a == b) {
                    continue;
                }
                int f[2] = {0, -n};
                for (char c : s) {
                    if (c == a) {
                        f[0]++;
                        f[1]++;
                    } else if (c == b) {
                        f[1] = max(f[1] - 1, f[0] - 1);
                        f[0] = 0;
                    }
                    ans = max(ans, f[1]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func largestVariance(s string) int {
	ans, n := 0, len(s)
	for a := 'a'; a <= 'z'; a++ {
		for b := 'a'; b <= 'z'; b++ {
			if a == b {
				continue
			}
			f := [2]int{0, -n}
			for _, c := range s {
				if c == a {
					f[0]++
					f[1]++
				} else if c == b {
					f[1] = max(f[1]-1, f[0]-1)
					f[0] = 0
				}
				ans = max(ans, f[1])
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function largestVariance(s: string): number {
    const n: number = s.length;
    let ans: number = 0;
    for (let a = 97; a <= 122; ++a) {
        for (let b = 97; b <= 122; ++b) {
            if (a === b) {
                continue;
            }
            const f: number[] = [0, -n];
            for (let i = 0; i < n; ++i) {
                if (s.charCodeAt(i) === a) {
                    f[0]++;
                    f[1]++;
                } else if (s.charCodeAt(i) === b) {
                    f[1] = Math.max(f[0] - 1, f[1] - 1);
                    f[0] = 0;
                }
                ans = Math.max(ans, f[1]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
