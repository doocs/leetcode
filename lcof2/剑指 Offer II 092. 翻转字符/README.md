# [剑指 Offer II 092. 翻转字符](https://leetcode.cn/problems/cyJERH)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个由&nbsp;<code>&#39;0&#39;</code> 和 <code>&#39;1&#39;</code>&nbsp;组成的字符串，是以一些 <code>&#39;0&#39;</code>（可能没有 <code>&#39;0&#39;</code>）后面跟着一些 <code>&#39;1&#39;</code>（也可能没有 <code>&#39;1&#39;</code>）的形式组成的，那么该字符串是&nbsp;<strong>单调递增&nbsp;</strong>的。</p>

<p>我们给出一个由字符 <code>&#39;0&#39;</code> 和 <code>&#39;1&#39;</code>&nbsp;组成的字符串 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="caret-color: rgb(199, 37, 78); font-size: 12.600000381469727px; background-color: rgb(249, 242, 244);">s</span></font>，我们可以将任何&nbsp;<code>&#39;0&#39;</code> 翻转为&nbsp;<code>&#39;1&#39;</code>&nbsp;或者将&nbsp;<code>&#39;1&#39;</code>&nbsp;翻转为&nbsp;<code>&#39;0&#39;</code>。</p>

<p>返回使 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="caret-color: rgb(199, 37, 78); font-size: 12.600000381469727px; background-color: rgb(249, 242, 244);">s</span></font>&nbsp;<strong>单调递增&nbsp;</strong>的最小翻转次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s =<strong> </strong>&quot;00110&quot;
<strong>输出：</strong>1
<strong>解释：</strong>我们翻转最后一位得到 00111.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s =<strong> </strong>&quot;010110&quot;
<strong>输出：</strong>2
<strong>解释：</strong>我们翻转得到 011111，或者是 000111。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s =<strong> </strong>&quot;00011000&quot;
<strong>输出：</strong>2
<strong>解释：</strong>我们翻转得到 00000000。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20000</code></li>
	<li><font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="caret-color: rgb(199, 37, 78); font-size: 12.600000381469727px; background-color: rgb(249, 242, 244);">s</span></font> 中只包含字符&nbsp;<code>&#39;0&#39;</code>&nbsp;和&nbsp;<code>&#39;1&#39;</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 926&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/flip-string-to-monotone-increasing/">https://leetcode.cn/problems/flip-string-to-monotone-increasing/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

我们需要找到一个分界点 `i`，使 `[:i]` 全为 0，`[i:]` 全为 1，并且翻转次数最少，问题就转换成计算 `i` 的左右两侧的翻转次数，可以用前缀和进行优化

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        n = len(s)
        left, right = [0] * (n + 1), [0] * (n + 1)
        ans = 0x3F3F3F3F
        for i in range(1, n + 1):
            left[i] = left[i - 1] + (1 if s[i - 1] == '1' else 0)
        for i in range(n - 1, -1, -1):
            right[i] = right[i + 1] + (1 if s[i] == '0' else 0)
        for i in range(0, n + 1):
            ans = min(ans, left[i] + right[i])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            left[i] = left[i - 1] + (s.charAt(i - 1) == '1' ? 1 : 0);
        }
        for (int i = n - 1; i >= 0; i--) {
            right[i] = right[i + 1] + (s.charAt(i) == '0' ? 1 : 0);
        }
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, left[i] + right[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minFlipsMonoIncr(string s) {
        int n = s.size();
        vector<int> left(n + 1, 0), right(n + 1, 0);
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) {
            left[i] = left[i - 1] + (s[i - 1] == '1');
        }
        for (int i = n - 1; i >= 0; --i) {
            right[i] = right[i + 1] + (s[i] == '0');
        }
        for (int i = 0; i <= n; i++) {
            ans = min(ans, left[i] + right[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func minFlipsMonoIncr(s string) int {
	n := len(s)
	left, right := make([]int, n+1), make([]int, n+1)
	ans := math.MaxInt32
	for i := 1; i <= n; i++ {
		left[i] = left[i-1]
		if s[i-1] == '1' {
			left[i]++
		}
	}
	for i := n - 1; i >= 0; i-- {
		right[i] = right[i+1]
		if s[i] == '0' {
			right[i]++
		}
	}
	for i := 0; i <= n; i++ {
		ans = min(ans, left[i]+right[i])
	}
	return ans
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

### **...**

```

```

<!-- tabs:end -->
