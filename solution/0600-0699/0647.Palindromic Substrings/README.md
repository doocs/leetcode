# [647. 回文子串](https://leetcode-cn.com/problems/palindromic-substrings)

[English Version](/solution/0600-0699/0647.Palindromic%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。</p>

<p>具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;abc&quot;
<strong>输出：</strong>3
<strong>解释：</strong>三个回文子串: &quot;a&quot;, &quot;b&quot;, &quot;c&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;aaa&quot;
<strong>输出：</strong>6
<strong>解释：</strong>6个回文子串: &quot;a&quot;, &quot;a&quot;, &quot;a&quot;, &quot;aa&quot;, &quot;aa&quot;, &quot;aaa&quot;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>输入的字符串长度不会超过 1000 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

在 Manacher 算法的计算过程中，`p[i] - 1` 表示以第 `i` 位为中心的最大回文长度，`(p[i] - 1) / 2` **向上取整**即可得到以第 `i` 位为中心的回文串数量

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSubstrings(self, s: str) -> int:
        t = '^#' + '#'.join(s) + '#$'
        n = len(t)
        p = [0 for _ in range(n)]
        pos, maxRight = 0, 0
        ans = 0
        for i in range(1, n - 1):
            p[i] = min(maxRight - i, p[2 * pos - i]) if maxRight > i else 1
            while t[i - p[i]] == t[i + p[i]]:
                p[i] += 1
            if i + p[i] > maxRight:
                maxRight = i + p[i]
                pos = i
            ans += p[i] // 2
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countSubstrings(String s) {
        StringBuilder sb = new StringBuilder("^#");
        for (char ch : s.toCharArray()) {
            sb.append(ch).append('#');
        }
        String t = sb.append('$').toString();
        int n = t.length();
        int[] p = new int[n];
        int pos = 0, maxRight = 0;
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            p[i] = maxRight > i ? Math.min(maxRight - i, p[2 * pos - i]) : 1;
            while (t.charAt(i - p[i]) == t.charAt(i + p[i])) {
                p[i]++;
            }
            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                pos = i;
            }
            ans += p[i] / 2;
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
