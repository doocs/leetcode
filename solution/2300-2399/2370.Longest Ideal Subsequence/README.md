# [2370. 最长理想子序列](https://leetcode.cn/problems/longest-ideal-subsequence)

[English Version](/solution/2300-2399/2370.Longest%20Ideal%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由小写字母组成的字符串 <code>s</code> ，和一个整数 <code>k</code> 。如果满足下述条件，则可以将字符串 <code>t</code> 视作是 <strong>理想字符串</strong> ：</p>

<ul>
	<li><code>t</code> 是字符串 <code>s</code> 的一个子序列。</li>
	<li><code>t</code> 中每两个 <strong>相邻</strong> 字母在字母表中位次的绝对差值小于或等于 <code>k</code> 。</li>
</ul>

<p>返回 <strong>最长</strong> 理想字符串的长度。</p>

<p>字符串的子序列同样是一个字符串，并且子序列还满足：可以经由其他字符串删除某些字符（也可以不删除）但不改变剩余字符的顺序得到。</p>

<p><strong>注意：</strong>字母表顺序不会循环。例如，<code>'a'</code> 和 <code>'z'</code> 在字母表中位次的绝对差值是 <code>25</code> ，而不是 <code>1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "acfgbd", k = 2
<strong>输出：</strong>4
<strong>解释：</strong>最长理想字符串是 "acbd" 。该字符串长度为 4 ，所以返回 4 。
注意 "acfgbd" 不是理想字符串，因为 'c' 和 'f' 的字母表位次差值为 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", k = 3
<strong>输出：</strong>4
<strong>解释：</strong>最长理想字符串是 "abcd" ，该字符串长度为 4 ，所以返回 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 25</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

设 $dp[i]$ 表示以字符 $s[i]$ 结尾的最长理想子序列的长度，利用哈希表 $d$ 记录每个字符最新出现的位置。初始时 $dp[0]=1$, $d[s[0]]=0$。

在 $[1,..n-1]$ 范围内的每个字符 $s[i]$，获取它所有前一个合法字符的位置 $j$，那么 $dp[i]=max(dp[i], dp[j]+1)$。

答案为 $dp$ 中的最大值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestIdealString(self, s: str, k: int) -> int:
        n = len(s)
        ans = 1
        dp = [1] * n
        d = {s[0]: 0}
        for i in range(1, n):
            a = ord(s[i])
            for b in ascii_lowercase:
                if abs(a - ord(b)) > k:
                    continue
                if b in d:
                    dp[i] = max(dp[i], dp[d[b]] + 1)
            d[s[i]] = i
        return max(dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int ans = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Map<Character, Integer> d = new HashMap<>(26);
        d.put(s.charAt(0), 0);
        for (int i = 1; i < n; ++i) {
            char a = s.charAt(i);
            for (char b = 'a'; b <= 'z'; ++b) {
                if (Math.abs(a - b) > k) {
                    continue;
                }
                if (d.containsKey(b)) {
                    dp[i] = Math.max(dp[i], dp[d.get(b)] + 1);
                }
            }
            d.put(a, i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestIdealString(string s, int k) {
        int n = s.size();
        int ans = 1;
        vector<int> dp(n, 1);
        unordered_map<char, int> d;
        d[s[0]] = 0;
        for (int i = 1; i < n; ++i) {
            char a = s[i];
            for (char b = 'a'; b <= 'z'; ++b) {
                if (abs(a - b) > k) continue;
                if (d.count(b)) dp[i] = max(dp[i], dp[d[b]] + 1);
            }
            d[a] = i;
            ans = max(ans, dp[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func longestIdealString(s string, k int) int {
	n := len(s)
	ans := 1
	dp := make([]int, n)
	for i := range dp {
		dp[i] = 1
	}
	d := map[byte]int{s[0]: 0}
	for i := 1; i < n; i++ {
		a := s[i]
		for b := byte('a'); b <= byte('z'); b++ {
			if int(a)-int(b) > k || int(b)-int(a) > k {
				continue
			}
			if v, ok := d[b]; ok {
				dp[i] = max(dp[i], dp[v]+1)
			}
		}
		d[a] = i
		ans = max(ans, dp[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function longestIdealString(s: string, k: number): number {
    const dp = new Array(26).fill(0);
    for (const c of s) {
        const x = c.charCodeAt(0) - 'a'.charCodeAt(0);
        let t = 0;
        for (let i = 0; i < 26; i++) {
            if (Math.abs(x - i) <= k) {
                t = Math.max(t, dp[i] + 1);
            }
        }
        dp[x] = Math.max(dp[x], t);
    }

    return dp.reduce((r, c) => Math.max(r, c), 0);
}
```

### **...**

```

```

<!-- tabs:end -->
