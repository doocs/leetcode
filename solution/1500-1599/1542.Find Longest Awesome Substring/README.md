# [1542. 找出最长的超赞子字符串](https://leetcode.cn/problems/find-longest-awesome-substring)

[English Version](/solution/1500-1599/1542.Find%20Longest%20Awesome%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 。请返回 <code>s</code> 中最长的 <strong>超赞子字符串</strong> 的长度。</p>

<p>「超赞子字符串」需满足满足下述两个条件：</p>

<ul>
	<li>该字符串是 <code>s</code> 的一个非空子字符串</li>
	<li>进行任意次数的字符交换后，该字符串可以变成一个回文字符串</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;3242415&quot;
<strong>输出：</strong>5
<strong>解释：</strong>&quot;24241&quot; 是最长的超赞子字符串，交换其中的字符后，可以得到回文 &quot;24142&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;12345678&quot;
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;213123&quot;
<strong>输出：</strong>6
<strong>解释：</strong>&quot;213123&quot; 是最长的超赞子字符串，交换其中的字符后，可以得到回文 &quot;231132&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;00&quot;
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code> 仅由数字组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + 前缀和思想**

根据题目描述，“超赞子字符串”中的字符可以通过交换得到回文字符串，因此，“超赞子字符串”中最多有一个数字字符出现奇数次，其余数字字符出现偶数次。

我们可以用一个整数 $st$ 来表示当前前缀字符串中数字字符出现的奇偶性，其中 $st$ 的第 $i$ 位表示数字字符 $i$ 出现的奇偶性，即 $st$ 的第 $i$ 位为 $1$ 表示数字字符 $i$ 出现奇数次，为 $0$ 表示数字字符 $i$ 出现偶数次。

而如果子字符串 $s[j,..i]$ 是“超赞字符串”，那么前缀字符串 $s[0,..i]$ 的状态 $st$ 与前缀字符串 $s[0,..j-1]$ 的状态 $st'$ 的二进制位中，最多只有一位不同。这是因为，二进制位不同，表示奇偶性不同，而奇偶性不同，就意味着子字符串 $s[j,..i]$ 中该数字出现的次数为奇数次。

所以，我们可以用哈希表或数组记录所有状态 $st$ 第一次出现的位置。若当前前缀字符串的状态 $st$ 在哈希表中已经存在，那么说明当前前缀字符串的状态 $st$ 与前缀字符串 $s[0,..j-1]$ 的状态 $st'$ 的二进制位中，所有位都相同，即子字符串 $s[j,..i]$ 是“超赞字符串”，更新答案的最大值。或者，我们可以枚举每一位，将当前前缀字符串的状态 $st$ 的第 $i$ 位取反，即 $st \oplus (1 << i)$，然后判断 $st \oplus (1 << i)$ 是否在哈希表中，若在，那么说明当前前缀字符串的状态 $st$ 与前缀字符串 $s[0,..j-1]$ 的状态 $st' \oplus (1 << i)$ 的二进制位中，只有第 $i$ 位不同，即子字符串 $s[j,..i]$ 是“超赞字符串”，更新答案的最大值。

最后，返回答案即可。

时间复杂度 $O(n \times C)$，空间复杂度 $O(2^C)$。其中 $n$ 和 $C$ 分别为字符串 $s$ 的长度和数字字符的种类数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestAwesome(self, s: str) -> int:
        st = 0
        d = {0: -1}
        ans = 1
        for i, c in enumerate(s):
            v = int(c)
            st ^= 1 << v
            if st in d:
                ans = max(ans, i - d[st])
            else:
                d[st] = i
            for v in range(10):
                if st ^ (1 << v) in d:
                    ans = max(ans, i - d[st ^ (1 << v)])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestAwesome(String s) {
        int[] d = new int[1024];
        int st = 0, ans = 1;
        Arrays.fill(d, -1);
        d[0] = 0;
        for (int i = 1; i <= s.length(); ++i) {
            int v = s.charAt(i - 1) - '0';
            st ^= 1 << v;
            if (d[st] >= 0) {
                ans = Math.max(ans, i - d[st]);
            } else {
                d[st] = i;
            }
            for (v = 0; v < 10; ++v) {
                if (d[st ^ (1 << v)] >= 0) {
                    ans = Math.max(ans, i - d[st ^ (1 << v)]);
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
    int longestAwesome(string s) {
        vector<int> d(1024, -1);
        d[0] = 0;
        int st = 0, ans = 1;
        for (int i = 1; i <= s.size(); ++i) {
            int v = s[i - 1] - '0';
            st ^= 1 << v;
            if (~d[st]) {
                ans = max(ans, i - d[st]);
            } else {
                d[st] = i;
            }
            for (v = 0; v < 10; ++v) {
                if (~d[st ^ (1 << v)]) {
                    ans = max(ans, i - d[st ^ (1 << v)]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestAwesome(s string) int {
	d := [1024]int{}
	d[0] = 1
	st, ans := 0, 1
	for i, c := range s {
		i += 2
		st ^= 1 << (c - '0')
		if d[st] > 0 {
			ans = max(ans, i-d[st])
		} else {
			d[st] = i
		}
		for v := 0; v < 10; v++ {
			if d[st^(1<<v)] > 0 {
				ans = max(ans, i-d[st^(1<<v)])
			}
		}
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

### **...**

```

```

<!-- tabs:end -->
