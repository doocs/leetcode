---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3628.Maximum%20Number%20of%20Subsequences%20After%20One%20Inserting/README.md
rating: 1753
source: 第 460 场周赛 Q2
tags:
    - 贪心
    - 字符串
    - 动态规划
    - 前缀和
---

<!-- problem:start -->

# [3628. 插入一个字母的最大子序列数](https://leetcode.cn/problems/maximum-number-of-subsequences-after-one-inserting)

[English Version](/solution/3600-3699/3628.Maximum%20Number%20of%20Subsequences%20After%20One%20Inserting/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由大写英文字母组成的字符串 <code>s</code>。</p>

<p>你可以在字符串的&nbsp;<strong>任意&nbsp;</strong>位置（包括字符串的开头或结尾）<strong>最多插入一个&nbsp;</strong>大写英文字母。</p>

<p>返回在&nbsp;<strong>最多插入一个字母&nbsp;</strong>后，字符串中可以形成的 <code>"LCT"</code> 子序列的&nbsp;<strong>最大&nbsp;</strong>数量。</p>

<p><strong>子序列&nbsp;</strong>是从另一个字符串中删除某些字符（可以不删除）且不改变剩余字符顺序后得到的一个&nbsp;<strong>非空&nbsp;</strong>字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "LMCT"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>可以在字符串 <code>s</code> 的开头插入一个 <code>"L"</code>，变为 <code>"LLMCT"</code>，其中包含 2 个子序列，分别位于下标&nbsp;[0, 3, 4] 和 [1, 3, 4]。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "LCCT"</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>可以在字符串 <code>s</code> 的开头插入一个 <code>"L"</code>，变为 <code>"LLCCT"</code>，其中包含 4 个子序列，分别位于下标&nbsp;[0, 2, 4]、[0, 3, 4]、[1, 2, 4] 和 [1, 3, 4]。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "L"</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>插入一个字母无法获得子序列 <code>"LCT"</code>，结果为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由大写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以先计算出原字符串中 "LCT" 的子序列数量，然后考虑插入一个字母的情况。

计算 "LCT" 子序列的数量可以通过遍历字符串来实现。我们可以枚举中间的 "C"，用两个变量 $l$ 和 $r$ 分别维护左右两侧的 "L" 和 "T" 的数量。对于每个 "C"，我们可以计算出它左侧的 "L" 的数量和右侧的 "T" 的数量，从而得到以该 "C" 为中间的 "LCT" 子序列数量为 $l \times r$，累加到总数中。

接下来，我们需要考虑插入一个字母的情况。考虑到插入一个 "L" 或 "C" 或 "T" 的情况：

-   插入一个 "L"，那么我们只需要统计原字符串中 "CT" 的子序列数量。
-   插入一个 "T"，那么我们只需要统计原字符串中 "LC" 的子序列数量。
-   插入一个 "C"，那么我们只需要统计原字符串中 "LT" 的子序列数量，这种情况下，我们可以在前面枚举的过程中，维护一个变量 $\textit{mx}$，表示当前最大的 $l \times r$ 的值。

最后，我们将原字符串中 "LCT" 的子序列数量加上插入一个字母后的最大子序列数量，得到最终结果。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numOfSubsequences(self, s: str) -> int:
        def calc(t: str) -> int:
            cnt = a = 0
            for c in s:
                if c == t[1]:
                    cnt += a
                a += int(c == t[0])
            return cnt

        l, r = 0, s.count("T")
        ans = mx = 0
        for c in s:
            r -= int(c == "T")
            if c == "C":
                ans += l * r
            l += int(c == "L")
            mx = max(mx, l * r)
        mx = max(mx, calc("LC"), calc("CT"))
        ans += mx
        return ans
```

#### Java

```java
class Solution {
    private char[] s;

    public long numOfSubsequences(String S) {
        s = S.toCharArray();
        int l = 0, r = 0;
        for (char c : s) {
            if (c == 'T') {
                ++r;
            }
        }
        long ans = 0, mx = 0;
        for (char c : s) {
            r -= c == 'T' ? 1 : 0;
            if (c == 'C') {
                ans += 1L * l * r;
            }
            l += c == 'L' ? 1 : 0;
            mx = Math.max(mx, 1L * l * r);
        }
        mx = Math.max(mx, Math.max(calc("LC"), calc("CT")));
        ans += mx;
        return ans;
    }

    private long calc(String t) {
        long cnt = 0;
        int a = 0;
        for (char c : s) {
            if (c == t.charAt(1)) {
                cnt += a;
            }
            a += c == t.charAt(0) ? 1 : 0;
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numOfSubsequences(string s) {
        auto calc = [&](string t) {
            long long cnt = 0, a = 0;
            for (char c : s) {
                if (c == t[1]) {
                    cnt += a;
                }
                a += (c == t[0]);
            }
            return cnt;
        };

        long long l = 0, r = count(s.begin(), s.end(), 'T');
        long long ans = 0, mx = 0;
        for (char c : s) {
            r -= (c == 'T');
            if (c == 'C') {
                ans += l * r;
            }
            l += (c == 'L');
            mx = max(mx, l * r);
        }
        mx = max(mx, calc("LC"));
        mx = max(mx, calc("CT"));
        ans += mx;
        return ans;
    }
};
```

#### Go

```go
func numOfSubsequences(s string) int64 {
	calc := func(t string) int64 {
		cnt, a := int64(0), int64(0)
		for _, c := range s {
			if c == rune(t[1]) {
				cnt += a
			}
			if c == rune(t[0]) {
				a++
			}
		}
		return cnt
	}

	l, r := int64(0), int64(0)
	for _, c := range s {
		if c == 'T' {
			r++
		}
	}

	ans, mx := int64(0), int64(0)
	for _, c := range s {
		if c == 'T' {
			r--
		}
		if c == 'C' {
			ans += l * r
		}
		if c == 'L' {
			l++
		}
		mx = max(mx, l*r)
	}
	mx = max(mx, calc("LC"), calc("CT"))
	ans += mx
	return ans
}
```

#### TypeScript

```ts
function numOfSubsequences(s: string): number {
    const calc = (t: string): number => {
        let [cnt, a] = [0, 0];
        for (const c of s) {
            if (c === t[1]) cnt += a;
            if (c === t[0]) a++;
        }
        return cnt;
    };

    let [l, r] = [0, 0];
    for (const c of s) {
        if (c === 'T') r++;
    }

    let [ans, mx] = [0, 0];
    for (const c of s) {
        if (c === 'T') r--;
        if (c === 'C') ans += l * r;
        if (c === 'L') l++;
        mx = Math.max(mx, l * r);
    }

    mx = Math.max(mx, calc('LC'));
    mx = Math.max(mx, calc('CT'));
    ans += mx;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
