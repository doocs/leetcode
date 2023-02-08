# [1234. 替换子串得到平衡字符串](https://leetcode.cn/problems/replace-the-substring-for-balanced-string)

[English Version](/solution/1200-1299/1234.Replace%20the%20Substring%20for%20Balanced%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个只含有&nbsp;<code>'Q', 'W', 'E',&nbsp;'R'</code>&nbsp;四种字符，且长度为 <code>n</code>&nbsp;的字符串。</p>

<p>假如在该字符串中，这四个字符都恰好出现&nbsp;<code>n/4</code>&nbsp;次，那么它就是一个「平衡字符串」。</p>

<p>&nbsp;</p>

<p>给你一个这样的字符串 <code>s</code>，请通过「替换一个子串」的方式，使原字符串 <code>s</code> 变成一个「平衡字符串」。</p>

<p>你可以用和「待替换子串」长度相同的&nbsp;<strong>任何</strong> 其他字符串来完成替换。</p>

<p>请返回待替换子串的最小可能长度。</p>

<p>如果原字符串自身就是一个平衡字符串，则返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "QWER"
<strong>输出：</strong>0
<strong>解释：</strong>s 已经是平衡的了。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "QQWE"
<strong>输出：</strong>1
<strong>解释：</strong>我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "QQQW"
<strong>输出：</strong>2
<strong>解释：</strong>我们可以把前面的 "QQ" 替换成 "ER"。 
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "QQQQ"
<strong>输出：</strong>3
<strong>解释：</strong>我们可以替换后 3 个 'Q'，使 s = "QWER"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s.length</code>&nbsp;是&nbsp;<code>4</code>&nbsp;的倍数</li>
	<li><code>s</code>&nbsp;中只含有&nbsp;<code>'Q'</code>, <code>'W'</code>, <code>'E'</code>,&nbsp;<code>'R'</code>&nbsp;四种字符</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 双指针**

我们先用一个哈希表或数组 `cnt` 统计字符串 $s$ 中每个字符的数量，如果所有字符的数量都不超过 $n/4$，那么字符串 $s$ 就是平衡字符串，直接返回 $0$。

否则，我们使用双指针 $j$ 和 $i$ 分别维护窗口的左右边界，初始时 $j = 0$。

接下来，从左到右遍历字符串 $s$，每次遍历到一个字符，就将该字符的数量减 $1$，然后判断当前窗口是否满足条件，即窗口外的字符数量都不超过 $n/4$。如果满足条件，那么就更新答案，然后将窗口的左边界右移，直到不满足条件为止。

最后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 $s$ 的长度；而 $C$ 是字符集的大小，本题中 $C = 4$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def balancedString(self, s: str) -> int:
        cnt = Counter(s)
        n = len(s)
        if all(v <= n // 4 for v in cnt.values()):
            return 0
        ans, j = n, 0
        for i, c in enumerate(s):
            cnt[c] -= 1
            while j <= i and all(v <= n // 4 for v in cnt.values()):
                ans = min(ans, i - j + 1)
                cnt[s[j]] += 1
                j += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int balancedString(String s) {
        int[] cnt = new int[4];
        String t = "QWER";
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            cnt[t.indexOf(s.charAt(i))]++;
        }
        int m = n / 4;
        if (cnt[0] == m && cnt[1] == m && cnt[2] == m && cnt[3] == m) {
            return 0;
        }
        int ans = n;
        for (int i = 0, j = 0; i < n; ++i) {
            cnt[t.indexOf(s.charAt(i))]--;
            while (j <= i && cnt[0] <= m && cnt[1] <= m && cnt[2] <= m && cnt[3] <= m) {
                ans = Math.min(ans, i - j + 1);
                cnt[t.indexOf(s.charAt(j++))]++;
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
    int balancedString(string s) {
        int cnt[4]{};
        string t = "QWER";
        int n = s.size();
        for (char& c : s) {
            cnt[t.find(c)]++;
        }
        int m = n / 4;
        if (cnt[0] == m && cnt[1] == m && cnt[2] == m && cnt[3] == m) {
            return 0;
        }
        int ans = n;
        for (int i = 0, j = 0; i < n; ++i) {
            cnt[t.find(s[i])]--;
            while (j <= i && cnt[0] <= m && cnt[1] <= m && cnt[2] <= m && cnt[3] <= m) {
                ans = min(ans, i - j + 1);
                cnt[t.find(s[j++])]++;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func balancedString(s string) int {
	cnt := [4]int{}
	t := "QWER"
	n := len(s)
	for i := range s {
		cnt[strings.IndexByte(t, s[i])]++
	}
	m := n / 4
	if cnt[0] == m && cnt[1] == m && cnt[2] == m && cnt[3] == m {
		return 0
	}
	ans := n
	for i, j := 0, 0; i < n; i++ {
		cnt[strings.IndexByte(t, s[i])]--
		for j <= i && cnt[0] <= m && cnt[1] <= m && cnt[2] <= m && cnt[3] <= m {
			ans = min(ans, i-j+1)
			cnt[strings.IndexByte(t, s[j])]++
			j++
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
