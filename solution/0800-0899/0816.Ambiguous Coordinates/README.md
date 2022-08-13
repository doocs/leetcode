# [816. 模糊坐标](https://leetcode.cn/problems/ambiguous-coordinates)

[English Version](/solution/0800-0899/0816.Ambiguous%20Coordinates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有一些二维坐标，如&nbsp;<code>&quot;(1, 3)&quot;</code>&nbsp;或&nbsp;<code>&quot;(2, 0.5)&quot;</code>，然后我们移除所有逗号，小数点和空格，得到一个字符串<code>S</code>。返回所有可能的原始字符串到一个列表中。</p>

<p>原始的坐标表示法不会存在多余的零，所以不会出现类似于&quot;00&quot;, &quot;0.0&quot;, &quot;0.00&quot;, &quot;1.0&quot;, &quot;001&quot;, &quot;00.01&quot;或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现&ldquo;.1&rdquo;形式的数字。</p>

<p>最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。</p>

<p>&nbsp;</p>

<pre>
<strong>示例 1:</strong>
<strong>输入:</strong> &quot;(123)&quot;
<strong>输出:</strong> [&quot;(1, 23)&quot;, &quot;(12, 3)&quot;, &quot;(1.2, 3)&quot;, &quot;(1, 2.3)&quot;]
</pre>

<pre>
<strong>示例 2:</strong>
<strong>输入:</strong> &quot;(00011)&quot;
<strong>输出:</strong> &nbsp;[&quot;(0.001, 1)&quot;, &quot;(0, 0.011)&quot;]
<strong>解释:</strong> 
0.0, 00, 0001 或 00.01 是不被允许的。
</pre>

<pre>
<strong>示例 3:</strong>
<strong>输入:</strong> &quot;(0123)&quot;
<strong>输出:</strong> [&quot;(0, 123)&quot;, &quot;(0, 12.3)&quot;, &quot;(0, 1.23)&quot;, &quot;(0.1, 23)&quot;, &quot;(0.1, 2.3)&quot;, &quot;(0.12, 3)&quot;]
</pre>

<pre>
<strong>示例 4:</strong>
<strong>输入:</strong> &quot;(100)&quot;
<strong>输出:</strong> [(10, 0)]
<strong>解释:</strong> 
1.0 是不被允许的。
</pre>

<p>&nbsp;</p>

<p><strong>提示: </strong></p>

<ul>
	<li><code>4 &lt;= S.length &lt;= 12</code>.</li>
	<li><code>S[0]</code> = &quot;(&quot;, <code>S[S.length - 1]</code> = &quot;)&quot;, 且字符串&nbsp;<code>S</code>&nbsp;中的其他元素都是数字。</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

暴力模拟。

把 s 按照不同长度去做 x, y 两部分的拆分。

将拆分后的 x, y，分别检查是否满足以下条件：

-   左半部分开头不能是 0（除非是 0 本身）
-   右半部分不能以 0 为结尾

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def ambiguousCoordinates(self, s: str) -> List[str]:
        def convert(i, j):
            res = []
            for k in range(1, j - i + 1):
                left, right = s[i : i + k], s[i + k : j]
                valid = (
                    left == '0' or not left.startswith('0')
                ) and not right.endswith('0')
                if valid:
                    res.append(left + ('.' if k < j - i else '') + right)
            return res

        n = len(s)
        ans = []
        for i in range(2, n - 1):
            for x in convert(1, i):
                for y in convert(i, n - 1):
                    ans.append(f'({x}, {y})')
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        for (int i = 2; i < n - 1; ++i) {
            for (String x : convert(s, 1, i)) {
                for (String y : convert(s, i, n - 1)) {
                    ans.add(String.format("(%s, %s)", x, y));
                }
            }
        }
        return ans;
    }

    private List<String> convert(String s, int i, int j) {
        List<String> res = new ArrayList<>();
        for (int k = 1; k <= j - i; ++k) {
            String left = s.substring(i, i + k);
            String right = s.substring(i + k, j);
            // 左半部分开头不能是0（除非是0本身）
            // 右半部分不能以0为结尾
            boolean valid = ("0".equals(left) || !left.startsWith("0")) && !right.endsWith("0");
            if (valid) {
                res.add(left + (k < j - i ? "." : "") + right);
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
