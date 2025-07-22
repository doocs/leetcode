---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1960.Maximum%20Product%20of%20the%20Length%20of%20Two%20Palindromic%20Substrings/README.md
rating: 2690
source: 第 58 场双周赛 Q4
tags:
    - 字符串
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [1960. 两个回文子字符串长度的最大乘积](https://leetcode.cn/problems/maximum-product-of-the-length-of-two-palindromic-substrings)

[English Version](/solution/1900-1999/1960.Maximum%20Product%20of%20the%20Length%20of%20Two%20Palindromic%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;，你需要找到两个 <strong>不重叠</strong><strong>的回文&nbsp;</strong>子字符串，它们的长度都必须为 <strong>奇数</strong>&nbsp;，使得它们长度的乘积最大。</p>

<p>更正式地，你想要选择四个整数&nbsp;<code>i</code>&nbsp;，<code>j</code>&nbsp;，<code>k</code>&nbsp;，<code>l</code>&nbsp;，使得&nbsp;<code>0 &lt;= i &lt;= j &lt; k &lt;= l &lt; s.length</code>&nbsp;，且子字符串&nbsp;<code>s[i...j]</code> 和&nbsp;<code>s[k...l]</code>&nbsp;都是回文串且长度为奇数。<code>s[i...j]</code>&nbsp;表示下标从 <code>i</code>&nbsp;到 <code>j</code>&nbsp;且 <strong>包含</strong>&nbsp;两端下标的子字符串。</p>

<p>请你返回两个不重叠回文子字符串长度的 <strong>最大</strong>&nbsp;乘积。</p>

<p><strong>回文字符串</strong>&nbsp;指的是一个从前往后读和从后往前读一模一样的字符串。<strong>子字符串</strong>&nbsp;指的是一个字符串中一段连续字符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "ababbb"
<b>输出：</b>9
<b>解释：</b>子字符串 "aba" 和 "bbb" 为奇数长度的回文串。乘积为 3 * 3 = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "zaaaxbbby"
<b>输出：</b>9
<b>解释：</b>子字符串 "aaa" 和 "bbb" 为奇数长度的回文串。乘积为 3 * 3 = 9 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProduct(self, s: str) -> int:
        n = len(s)
        hlen = [0] * n
        center = right = 0

        for i in range(n):
            if i < right:
                hlen[i] = min(right - i, hlen[2 * center - i])
            while (
                0 <= i - 1 - hlen[i]
                and i + 1 + hlen[i] < len(s)
                and s[i - 1 - hlen[i]] == s[i + 1 + hlen[i]]
            ):
                hlen[i] += 1
            if right < i + hlen[i]:
                center, right = i, i + hlen[i]

        prefix = [0] * n
        suffix = [0] * n

        for i in range(n):
            prefix[i + hlen[i]] = max(prefix[i + hlen[i]], 2 * hlen[i] + 1)
            suffix[i - hlen[i]] = max(suffix[i - hlen[i]], 2 * hlen[i] + 1)

        for i in range(1, n):
            prefix[~i] = max(prefix[~i], prefix[~i + 1] - 2)
            suffix[i] = max(suffix[i], suffix[i - 1] - 2)

        for i in range(1, n):
            prefix[i] = max(prefix[i - 1], prefix[i])
            suffix[~i] = max(suffix[~i], suffix[~i + 1])

        return max(prefix[i - 1] * suffix[i] for i in range(1, n))
```

#### Java

```java
class Solution {
    public long maxProduct(String s) {
        int n = s.length();
        if (n == 2) return 1;
        int[] len = manachers(s);
        long[] left = new long[n];
        int max = 1;
        left[0] = max;
        for (int i = 1; i <= n - 1; i++) {
            if (len[(i - max - 1 + i) / 2] > max) max += 2;
            left[i] = max;
        }
        max = 1;
        long[] right = new long[n];
        right[n - 1] = max;
        for (int i = n - 2; i >= 0; i--) {
            if (len[(i + max + 1 + i) / 2] > max) max += 2;
            right[i] = max;
        }
        long res = 1;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, left[i - 1] * right[i]);
        }
        return res;
    }
    private int[] manachers(String s) {
        int len = s.length();
        int[] P = new int[len];
        int c = 0;
        int r = 0;
        for (int i = 0; i < len; i++) {
            int mirror = (2 * c) - i;
            if (i < r) {
                P[i] = Math.min(r - i, P[mirror]);
            }
            int a = i + (1 + P[i]);
            int b = i - (1 + P[i]);
            while (a < len && b >= 0 && s.charAt(a) == s.charAt(b)) {
                P[i]++;
                a++;
                b--;
            }
            if (i + P[i] > r) {
                c = i;
                r = i + P[i];
            }
        }
        for (int i = 0; i < len; i++) {
            P[i] = 1 + 2 * P[i];
        }
        return P;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxProduct(string s) {
        long long res = 0, l = 0, n = s.size();
        vector<int> m(n), r(n);

        for (int i = 0, l = 0, r = -1; i < n; ++i) {
            int k = (i > r) ? 1 : min(m[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < n && s[i - k] == s[i + k])
                k++;
            m[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }

        queue<array<int, 2>> q, q1;

        for (int i = n - 1; i >= 0; --i) {
            while (!q.empty() && q.front()[0] - q.front()[1] > i - 1)
                q.pop();
            r[i] = 1 + (q.empty() ? 0 : (q.front()[0] - i) * 2);
            q.push({i, m[i]});
        }

        for (int i = 0; i < n - 1; i++) {
            while (!q1.empty() && q1.front()[0] + q1.front()[1] < i + 1)
                q1.pop();
            l = max(l, 1ll + (q1.empty() ? 0 : (i - q1.front()[0]) * 2));
            res = max(res, l * r[i + 1]);
            q1.push({i, m[i]});
        }

        return res;
    }
};
```

#### Go

```go
func maxProduct(s string) int64 {
	n := len(s)
	hlen := make([]int, n)
	center, right := 0, 0

	for i := 0; i < n; i++ {
		if i < right {
			mirror := 2*center - i
			if mirror >= 0 && mirror < n {
				hlen[i] = min(right-i, hlen[mirror])
			}
		}
		for i-1-hlen[i] >= 0 && i+1+hlen[i] < n && s[i-1-hlen[i]] == s[i+1+hlen[i]] {
			hlen[i]++
		}
		if i+hlen[i] > right {
			center = i
			right = i + hlen[i]
		}
	}

	prefix := make([]int, n)
	suffix := make([]int, n)

	for i := 0; i < n; i++ {
		r := i + hlen[i]
		if r < n {
			prefix[r] = max(prefix[r], 2*hlen[i]+1)
		}
		l := i - hlen[i]
		if l >= 0 {
			suffix[l] = max(suffix[l], 2*hlen[i]+1)
		}
	}

	for i := 1; i < n; i++ {
		if n-i-1 >= 0 {
			prefix[n-i-1] = max(prefix[n-i-1], prefix[n-i]-2)
		}
		suffix[i] = max(suffix[i], suffix[i-1]-2)
	}

	for i := 1; i < n; i++ {
		prefix[i] = max(prefix[i-1], prefix[i])
		suffix[n-i-1] = max(suffix[n-i], suffix[n-i-1])
	}

	var res int64
	for i := 1; i < n; i++ {
		prod := int64(prefix[i-1]) * int64(suffix[i])
		if prod > res {
			res = prod
		}
	}

	return res
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
