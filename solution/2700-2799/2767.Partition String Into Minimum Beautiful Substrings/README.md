---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2767.Partition%20String%20Into%20Minimum%20Beautiful%20Substrings/README.md
rating: 1864
source: 第 108 场双周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 动态规划
    - 回溯
---

# [2767. 将字符串分割为最少的美丽子字符串](https://leetcode.cn/problems/partition-string-into-minimum-beautiful-substrings)

[English Version](/solution/2700-2799/2767.Partition%20String%20Into%20Minimum%20Beautiful%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串&nbsp;<code>s</code>&nbsp;，你需要将字符串分割成一个或者多个&nbsp;<strong>子字符串</strong>&nbsp;&nbsp;，使每个子字符串都是 <strong>美丽</strong>&nbsp;的。</p>

<p>如果一个字符串满足以下条件，我们称它是 <strong>美丽</strong>&nbsp;的：</p>

<ul>
	<li>它不包含前导 0 。</li>
	<li>它是 <code>5</code>&nbsp;的幂的 <strong>二进制</strong>&nbsp;表示。</li>
</ul>

<p>请你返回分割后的子字符串的 <strong>最少</strong>&nbsp;数目。如果无法将字符串&nbsp;<code>s</code>&nbsp;分割成美丽子字符串，请你返回 <code>-1</code>&nbsp;。</p>

<p>子字符串是一个字符串中一段连续的字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "1011"
<b>输出：</b>2
<b>解释：</b>我们可以将输入字符串分成 ["101", "1"] 。
- 字符串 "101" 不包含前导 0 ，且它是整数 5<sup>1</sup> = 5 的二进制表示。
- 字符串 "1" 不包含前导 0 ，且它是整数 5<sup>0</sup> = 1 的二进制表示。
最少可以将 s 分成 2 个美丽子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "111"
<b>输出：</b>3
<b>解释：</b>我们可以将输入字符串分成 ["1", "1", "1"] 。
- 字符串 "1" 不包含前导 0 ，且它是整数 5<sup>0</sup> = 1 的二进制表示。
最少可以将 s 分成 3 个美丽子字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>s = "0"
<b>输出：</b>-1
<b>解释：</b>无法将给定字符串分成任何美丽子字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 15</code></li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'0'</code>&nbsp;要么是&nbsp;<code>'1'</code> 。</li>
</ul>

## 解法

### 方法一：记忆化搜索

题目中需要判断一个字符串是否是 $5$ 的幂的二进制表示，因此，我们不妨先预处理出所有 $5$ 的幂的数字，记录在哈希表 $ss$ 中。

接下来，我们设计一个函数 $dfs(i)$，表示从字符串 $s$ 的第 $i$ 个字符开始，到字符串末尾的最少分割次数。那么答案就是 $dfs(0)$。

函数 $dfs(i)$ 的计算方法如下：

-   如果 $i \geq n$，表示已经处理完了所有字符，那么答案就是 $0$；
-   如果 $s[i] = 0$，表示当前字符串包含前导 $0$，不符合美丽字符串的定义，因此答案是无穷大；
-   否则，我们从 $i$ 开始枚举子字符串的结束位置 $j$，用 $x$ 表示子字符串 $s[i..j]$ 的十进制值，如果 $x$ 在哈希表 $ss$ 中，那么我们就可以将 $s[i..j]$ 作为一个美丽子字符串，此时答案就是 $1 + dfs(j + 1)$。我们需要枚举所有可能的 $j$，并取所有答案中的最小值。

为了避免重复计算，我们可以使用记忆化搜索的方法。

在主函数中，我们首先预处理出所有 $5$ 的幂的数字，然后调用 $dfs(0)$，如果返回值为无穷大，那么说明无法将字符串 $s$ 分割成美丽子字符串，答案返回 $-1$，否则返回 $dfs(0)$ 的值。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumBeautifulSubstrings(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            if s[i] == "0":
                return inf
            x = 0
            ans = inf
            for j in range(i, n):
                x = x << 1 | int(s[j])
                if x in ss:
                    ans = min(ans, 1 + dfs(j + 1))
            return ans

        n = len(s)
        x = 1
        ss = {x}
        for i in range(n):
            x *= 5
            ss.add(x)
        ans = dfs(0)
        return -1 if ans == inf else ans
```

```java
class Solution {
    private Integer[] f;
    private String s;
    private Set<Long> ss = new HashSet<>();
    private int n;

    public int minimumBeautifulSubstrings(String s) {
        n = s.length();
        this.s = s;
        f = new Integer[n];
        long x = 1;
        for (int i = 0; i <= n; ++i) {
            ss.add(x);
            x *= 5;
        }
        int ans = dfs(0);
        return ans > n ? -1 : ans;
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (s.charAt(i) == '0') {
            return n + 1;
        }
        if (f[i] != null) {
            return f[i];
        }
        long x = 0;
        int ans = n + 1;
        for (int j = i; j < n; ++j) {
            x = x << 1 | (s.charAt(j) - '0');
            if (ss.contains(x)) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        return f[i] = ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumBeautifulSubstrings(string s) {
        unordered_set<long long> ss;
        int n = s.size();
        long long x = 1;
        for (int i = 0; i <= n; ++i) {
            ss.insert(x);
            x *= 5;
        }
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (s[i] == '0') {
                return n + 1;
            }
            if (f[i] != -1) {
                return f[i];
            }
            long long x = 0;
            int ans = n + 1;
            for (int j = i; j < n; ++j) {
                x = x << 1 | (s[j] - '0');
                if (ss.count(x)) {
                    ans = min(ans, 1 + dfs(j + 1));
                }
            }
            return f[i] = ans;
        };
        int ans = dfs(0);
        return ans > n ? -1 : ans;
    }
};
```

```go
func minimumBeautifulSubstrings(s string) int {
	ss := map[int]bool{}
	n := len(s)
	x := 1
	f := make([]int, n+1)
	for i := 0; i <= n; i++ {
		ss[x] = true
		x *= 5
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if s[i] == '0' {
			return n + 1
		}
		if f[i] != -1 {
			return f[i]
		}
		f[i] = n + 1
		x := 0
		for j := i; j < n; j++ {
			x = x<<1 | int(s[j]-'0')
			if ss[x] {
				f[i] = min(f[i], 1+dfs(j+1))
			}
		}
		return f[i]
	}
	ans := dfs(0)
	if ans > n {
		return -1
	}
	return ans
}
```

```ts
function minimumBeautifulSubstrings(s: string): number {
    const ss: Set<number> = new Set();
    const n = s.length;
    const f: number[] = new Array(n).fill(-1);
    for (let i = 0, x = 1; i <= n; ++i) {
        ss.add(x);
        x *= 5;
    }
    const dfs = (i: number): number => {
        if (i === n) {
            return 0;
        }
        if (s[i] === '0') {
            return n + 1;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        f[i] = n + 1;
        for (let j = i, x = 0; j < n; ++j) {
            x = (x << 1) | (s[j] === '1' ? 1 : 0);
            if (ss.has(x)) {
                f[i] = Math.min(f[i], 1 + dfs(j + 1));
            }
        }
        return f[i];
    };
    const ans = dfs(0);
    return ans > n ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- end -->
