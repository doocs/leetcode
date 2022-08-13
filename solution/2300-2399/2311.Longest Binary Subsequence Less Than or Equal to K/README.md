# [2311. 小于等于 K 的最长二进制子序列](https://leetcode.cn/problems/longest-binary-subsequence-less-than-or-equal-to-k)

[English Version](/solution/2300-2399/2311.Longest%20Binary%20Subsequence%20Less%20Than%20or%20Equal%20to%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串&nbsp;<code>s</code>&nbsp;和一个正整数&nbsp;<code>k</code>&nbsp;。</p>

<p>请你返回 <code>s</code>&nbsp;的 <strong>最长</strong>&nbsp;子序列，且该子序列对应的 <strong>二进制</strong>&nbsp;数字小于等于 <code>k</code>&nbsp;。</p>

<p>注意：</p>

<ul>
	<li>子序列可以有 <strong>前导 0</strong>&nbsp;。</li>
	<li>空字符串视为&nbsp;<code>0</code>&nbsp;。</li>
	<li><strong>子序列</strong>&nbsp;是指从一个字符串中删除零个或者多个字符后，不改变顺序得到的剩余字符序列。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "1001010", k = 5
<b>输出：</b>5
<b>解释：</b>s 中小于等于 5 的最长子序列是 "00010" ，对应的十进制数字是 2 。
注意 "00100" 和 "00101" 也是可行的最长子序列，十进制分别对应 4 和 5 。
最长子序列的长度为 5 ，所以返回 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "00101001", k = 1
<b>输出：</b>6
<b>解释：</b>"000001" 是 s 中小于等于 1 的最长子序列，对应的十进制数字是 1 。
最长子序列的长度为 6 ，所以返回 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> 要么是&nbsp;<code>'0'</code>&nbsp;，要么是&nbsp;<code>'1'</code> 。</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

最长二进制子序列必然包含原字符串中所有的 $0$，在此基础上，我们从右到左遍历 $s$，若遇到 $1$，判断子序列能否添加 $1$，使得子序列对应的二进制数字 $v<=k$。

时间复杂度 $O(n)$，其中 $n$ 表示字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestSubsequence(self, s: str, k: int) -> int:
        n = len(s)
        ans = s.count('0')
        v = 0
        for i in range(n - 1, -1, -1):
            if s[i] == '1':
                if v + (1 << (n - i - 1)) > k:
                    break
                ans += 1
                v += 1 << (n - i - 1)
        return ans
```

```python
class Solution:
    def longestSubsequence(self, s: str, k: int) -> int:
        ans = v = 0
        for c in s[::-1]:
            if c == '0':
                ans += 1
            elif v + (1 << ans) <= k:
                v += 1 << ans
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestSubsequence(String s, int k) {
        int ans = 0;
        long v = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                ++ans;
            } else {
                if (ans < 32 && v + (1L << ans) <= k) {
                    v += 1L << ans;
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestSubsequence(string s, int k) {
        int ans = 0;
        long long v = 0;
        for (int i = s.size() - 1; ~i; --i) {
            if (s[i] == '0')
                ++ans;
            else if (ans < 32 && v + (1ll << ans) <= k) {
                v += 1ll << ans;
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestSubsequence(s string, k int) int {
	ans := 0
	v := 0
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] == '0' {
			ans++
		} else if ans < 32 && v+(1<<ans) <= k {
			v += 1 << ans
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function longestSubsequence(s: string, k: number): number {
    let numStr = '';
    const n = s.length,
        m = s.split('').reduce((a, c) => a + Number(c), 0);
    for (let i = n - 1; i >= 0; i--) {
        const cur = s.charAt(i).concat(numStr);
        if (parseInt(cur, 2) > k) break;
        numStr = cur;
    }
    return n - m + numStr.split('').reduce((a, c) => a + Number(c), 0);
}
```

```ts
function longestSubsequence(s: string, k: number): number {
    const cs = s.split('');
    const n = s.length;
    let i = 0;
    while (parseInt(cs.join(''), 2) > k) {
        for (let j = i; j < n; j++) {
            if (cs[j] === '1') {
                cs[j] = '0';
                break;
            }
        }
        i++;
    }
    return n - i;
}
```

### **...**

```

```

<!-- tabs:end -->
