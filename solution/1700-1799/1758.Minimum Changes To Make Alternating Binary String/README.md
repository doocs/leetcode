# [1758. 生成交替二进制字符串的最少操作数](https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string)

[English Version](/solution/1700-1799/1758.Minimum%20Changes%20To%20Make%20Alternating%20Binary%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由字符 <code>'0'</code> 和 <code>'1'</code> 组成的字符串 <code>s</code> 。一步操作中，你可以将任一 <code>'0'</code> 变成 <code>'1'</code> ，或者将 <code>'1'</code> 变成 <code>'0'</code> 。</p>

<p><strong>交替字符串</strong> 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 <code>"010"</code> 是交替字符串，而字符串 <code>"0100"</code> 不是。</p>

<p>返回使 <code>s</code> 变成 <strong>交替字符串</strong> 所需的 <strong>最少</strong> 操作数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "0100"
<strong>输出：</strong>1
<strong>解释：</strong>如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "10"
<strong>输出：</strong>0
<strong>解释：</strong>s 已经是交替字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = "1111"
<strong>输出：</strong>2
<strong>解释：</strong>需要 2 步操作得到 "0101" 或 "1010" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s[i]</code> 是 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, s: str) -> int:
        cnt = 0
        for i, c in enumerate(s):
            cnt += c == '01'[i & 1]
        return min(cnt, len(s) - cnt)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(String s) {
        int cnt = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            cnt += (s.charAt(i) == "01".charAt(i & 1) ? 1 : 0);
        }
        return Math.min(cnt, n - cnt);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(string s) {
        int cnt = 0, n = s.size();
        for (int i = 0; i < n; ++i) {
            cnt += s[i] == "01"[i & 1];
        }
        return min(cnt, n - cnt);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
