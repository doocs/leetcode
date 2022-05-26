# [1945. 字符串转化后的各位数字之和](https://leetcode.cn/problems/sum-of-digits-of-string-after-convert)

[English Version](/solution/1900-1999/1945.Sum%20of%20Digits%20of%20String%20After%20Convert/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由小写字母组成的字符串 <code>s</code> ，以及一个整数 <code>k</code> 。</p>

<p>首先，用字母在字母表中的位置替换该字母，将 <code>s</code> <strong>转化</strong> 为一个整数（也就是，<code>'a'</code> 用 <code>1</code> 替换，<code>'b'</code> 用 <code>2</code> 替换，... <code>'z'</code> 用 <code>26</code> 替换）。接着，将整数 <strong>转换</strong> 为其 <strong>各位数字之和</strong> 。共重复 <strong>转换</strong> 操作 <strong><code>k</code> 次</strong> 。</p>

<p>例如，如果 <code>s = "zbax"</code> 且 <code>k = 2</code> ，那么执行下述步骤后得到的结果是整数 <code>8</code> ：</p>

<ul>
	<li><strong>转化：</strong><code>"zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124</code></li>
	<li><strong>转换 #1</strong>：<code>262124&nbsp;➝ 2 + 6 + 2 + 1 + 2 + 4&nbsp;➝ 17</code></li>
	<li><strong>转换 #2</strong>：<code>17 ➝ 1 + 7 ➝ 8</code></li>
</ul>

<p>返回执行上述操作后得到的结果整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "iiii", k = 1
<strong>输出：</strong>36
<strong>解释：</strong>操作如下：
- 转化："iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
- 转换 #1：9999 ➝ 9 + 9 + 9 + 9 ➝ 36
因此，结果整数为 36 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcode", k = 2
<strong>输出：</strong>6
<strong>解释：</strong>操作如下：
- 转化："leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545
- 转换 #1：12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33
- 转换 #2：33 ➝ 3 + 3 ➝ 6
因此，结果整数为 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getLucky(self, s: str, k: int) -> int:
        s = ''.join([str(ord(c) - ord('a') + 1) for c in s])
        for _ in range(k):
            t = 0
            for c in s:
                t += ord(c) - ord('0')
            s = str(t)
        return int(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c - 'a' + 1);
        }
        s = sb.toString();
        while (k-- > 0) {
            int t = 0;
            for (int i = 0; i < s.length(); ++i) {
                t += s.charAt(i) - '0';
            }
            s = String.valueOf(t);
        }
        return Integer.parseInt(s);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
