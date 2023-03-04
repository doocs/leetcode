# [1573. 分割字符串的方案数](https://leetcode.cn/problems/number-of-ways-to-split-a-string)

[English Version](/solution/1500-1599/1573.Number%20of%20Ways%20to%20Split%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制串&nbsp;<code>s</code>&nbsp; （一个只包含 0 和 1 的字符串），我们可以将 <code>s</code>&nbsp;分割成 3 个 <strong>非空</strong>&nbsp;字符串 s1, s2, s3 （s1 + s2 + s3 = s）。</p>

<p>请你返回分割&nbsp;<code>s</code>&nbsp;的方案数，满足 s1，s2 和 s3 中字符 &#39;1&#39; 的数目相同。</p>

<p>由于答案可能很大，请将它对 10^9 + 7 取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;10101&quot;
<strong>输出：</strong>4
<strong>解释：</strong>总共有 4 种方法将 s 分割成含有 &#39;1&#39; 数目相同的三个子字符串。
&quot;1|010|1&quot;
&quot;1|01|01&quot;
&quot;10|10|1&quot;
&quot;10|1|01&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;1001&quot;
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;0000&quot;
<strong>输出：</strong>3
<strong>解释：</strong>总共有 3 种分割 s 的方法。
&quot;0|0|00&quot;
&quot;0|00|0&quot;
&quot;00|0|0&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;100100010100110&quot;
<strong>输出：</strong>12
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s[i] == &#39;0&#39;</code>&nbsp;或者&nbsp;<code>s[i] == &#39;1&#39;</code></li>
	<li><code>3 &lt;= s.length &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们先遍历字符串 $s$，统计其中字符 $1$ 的个数 $cnt$，如果 $cnt$ 不能被 $3$ 整除，那么无法分割，直接返回 $0$。如果 $cnt$ 为 $0$，说明字符串中没有字符 $1$，我们可以在 $n-1$ 个位置中任意选择两个位置，将字符串分割成三个子串，那么方案数就是 $C_{n-1}^2$。

如果 $cnt \gt 0$，我们将 $cnt$ 更新为 $\frac{cnt}{3}$，即每个子串中字符 $1$ 的个数。

接下来我们找到第一个子字符串的右边界的最小下标，记为 $i_1$，第一个子字符串的右边界的最大下标（不包含），记为 $i_2$；第二个子字符串的右边界的最小下标，记为 $j_1$，第二个子字符串的右边界的最大下标（不包含），记为 $j_2$。那么方案数就是 $(i_2-i_1) \times (j_2-j_1)$。

注意答案可能很大，需要对 $10^9+7$ 取模。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

相似题目：

-   [927. 三等分](/solution/0900-0999/0927.Three%20Equal%20Parts/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numWays(self, s: str) -> int:
        def find(x):
            t = 0
            for i, c in enumerate(s):
                t += int(c == '1')
                if t == x:
                    return i
        cnt, m = divmod(sum(c == '1' for c in s), 3)
        if m:
            return 0
        n = len(s)
        mod = 10**9 + 7
        if cnt == 0:
            return ((n - 1) * (n - 2) // 2) % mod
        i1, i2 = find(cnt), find(cnt + 1)
        j1, j2 = find(cnt * 2), find(cnt * 2 + 1)
        return (i2 - i1) * (j2 - j1) % (10**9 + 7)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String s;

    public int numWays(String s) {
        this.s = s;
        int cnt = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++cnt;
            }
        }
        int m = cnt % 3;
        if (m != 0) {
            return 0;
        }
        final int mod = (int) 1e9 + 7;
        if (cnt == 0) {
            return (int) (((n - 1L) * (n - 2) / 2) % mod);
        }
        cnt /= 3;
        long i1 = find(cnt), i2 = find(cnt + 1);
        long j1 = find(cnt * 2), j2 = find(cnt * 2 + 1);
        return (int) ((i2 - i1) * (j2 - j1) % mod);
    }

    private int find(int x) {
        int t = 0;
        for (int i = 0;; ++i) {
            t += s.charAt(i) == '1' ? 1 : 0;
            if (t == x) {
                return i;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numWays(string s) {
        int cnt = 0;
        for (char& c : s) {
            cnt += c == '1';
        }
        int m = cnt % 3;
        if (m) {
            return 0;
        }
        const int mod = 1e9 + 7;
        int n = s.size();
        if (cnt == 0) {
            return (n - 1LL) * (n - 2) / 2 % mod;
        }
        cnt /= 3;
        auto find = [&](int x) {
            int t = 0;
            for (int i = 0; ; ++i) {
                t += s[i] == '1';
                if (t == x) {
                    return i;
                }
            }
        };
        int i1 = find(cnt), i2 = find(cnt + 1);
        int j1 = find(cnt * 2), j2 = find(cnt * 2 + 1);
        return (1LL * (i2 - i1) * (j2 - j1)) % mod;
    }
};
```

### **Go**

```go
func numWays(s string) int {
	cnt := 0
	for _, c := range s {
		if c == '1' {
			cnt++
		}
	}
	m := cnt % 3
	if m != 0 {
		return 0
	}
	const mod = 1e9 + 7
	n := len(s)
	if cnt == 0 {
		return (n - 1) * (n - 2) / 2 % mod
	}
	cnt /= 3
	find := func(x int) int {
		t := 0
		for i := 0; ; i++ {
			if s[i] == '1' {
				t++
				if t == x {
					return i
				}
			}
		}
	}
	i1, i2 := find(cnt), find(cnt+1)
	j1, j2 := find(cnt*2), find(cnt*2+1)
	return (i2 - i1) * (j2 - j1) % mod
}
```

### **...**

```

```

<!-- tabs:end -->
