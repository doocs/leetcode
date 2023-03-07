# [1100. 长度为 K 的无重复字符子串](https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters)

[English Version](/solution/1100-1199/1100.Find%20K-Length%20Substrings%20With%20No%20Repeated%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>S</code>，找出所有长度为&nbsp;<code>K</code>&nbsp;且不含重复字符的子串，请你返回全部满足要求的子串的&nbsp;<strong>数目</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>S = &quot;havefunonleetcode&quot;, K = 5
<strong>输出：</strong>6
<strong>解释：</strong>
这里有 6 个满足题意的子串，分别是：&#39;havef&#39;,&#39;avefu&#39;,&#39;vefun&#39;,&#39;efuno&#39;,&#39;etcod&#39;,&#39;tcode&#39;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>S = &quot;home&quot;, K = 5
<strong>输出：</strong>0
<strong>解释：</strong>
注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 10^4</code></li>
	<li><code>S</code> 中的所有字符均为小写英文字母</li>
	<li><code>1 &lt;= K &lt;= 10^4</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针 + 计数器**

我们观察发现，字符均为小写字母，也即最多有 $26$ 种不同的字符。因此，如果 $k \gt 26$ 或者 $k \gt n$，则无法找到任何长度为 $k$ 且不含重复字符的子串，直接返回 $0$ 即可。

接下来，我们用双指针 $j$ 和 $i$ 维护一个滑动窗口，其中 $j$ 是滑动窗口的左端点，$i$ 是滑动窗口的右端点，用一个计数器 $cnt$ 统计滑动窗口中每个字符出现的次数。

遍历字符串 $s$，每次将 $s[i]$ 加入滑动窗口，即 $cnt[s[i]]++$，如果此时 $cnt[s[i]] \gt 1$ 或者 $i - j + 1 \gt k$，则循环将 $s[j]$ 从滑动窗口中移除，即 $cnt[s[j]]--$，并将 $j$ 右移。如果 $j$ 右移结束后，窗口大小 $i - j + 1$ 恰好等于 $k$，则说明滑动窗口中的字符串是一个符合题意的子串，将结果加一。

遍历结束后，即可得到所有符合题意的子串的个数。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度；而 $C$ 为字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numKLenSubstrNoRepeats(self, s: str, k: int) -> int:
        n = len(s)
        if k > n or k > 26:
            return 0
        ans = j = 0
        cnt = Counter()
        for i, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 1 or i - j + 1 > k:
                cnt[s[j]] -= 1
                j += 1
            ans += i - j + 1 == k
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int n = s.length();
        if (k > n || k > 26) {
            return 0;
        }
        int[] cnt = new int[128];
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            ++cnt[s.charAt(i)];
            while (cnt[s.charAt(i)] > 1 || i - j + 1 > k) {
                cnt[s.charAt(j++)]--;
            }
            ans += i - j + 1 == k ? 1 : 0;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numKLenSubstrNoRepeats(string s, int k) {
        int n = s.size();
        if (k > n || k > 26) {
            return 0;
        }
        int cnt[128]{};
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            ++cnt[s[i]];
            while (cnt[s[i]] > 1 || i - j + 1 > k) {
                --cnt[s[j++]];
            }
            ans += i - j + 1 == k;
        }
        return ans;
    }
};
```

### **Go**

```go
func numKLenSubstrNoRepeats(s string, k int) (ans int) {
	if k > len(s) || k > 26 {
		return 0
	}
	cnt := [128]int{}
	for i, j := 0, 0; i < len(s); i++ {
		cnt[s[i]]++
		for cnt[s[i]] > 1 || i-j+1 > k {
			cnt[s[j]]--
			j++
		}
		if i-j+1 == k {
			ans++
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
