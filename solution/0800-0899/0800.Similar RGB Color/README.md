# [800. 相似 RGB 颜色](https://leetcode.cn/problems/similar-rgb-color)

[English Version](/solution/0800-0899/0800.Similar%20RGB%20Color/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>RGB 颜色 <code>"#AABBCC"</code>&nbsp;可以简写成&nbsp;<code>"#ABC"</code> 。</p>

<ul>
	<li>例如，<code>"#15c"</code>&nbsp;其实是&nbsp;<code>"#1155cc"</code> 的简写。</li>
</ul>

<p>现在，假如我们分别定义两个颜色 <code>"#ABCDEF"</code>&nbsp;和&nbsp;<code>"#UVWXYZ"</code>，则他们的相似度可以通过这个表达式&nbsp;<code>-(AB - UV)^2 -&nbsp;(CD - WX)^2 -&nbsp;(EF - YZ)^2</code>&nbsp;来计算。</p>

<p>那么给你一个按 <code>"#ABCDEF"</code> 形式定义的字符串 <code>color</code> 表示 RGB 颜色，请你以字符串形式，返回一个与它相似度最大且可以简写的颜色。（比如，可以表示成类似 <code>"#XYZ"</code> 的形式）</p>

<p><strong>任何</strong> 具有相同的（最大）相似度的答案都会被视为正确答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>color = "#09f166"
<strong>输出：</strong>"#11ee66"
<strong>解释：</strong> 
因为相似度计算得出 -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73
这已经是所有可以简写的颜色中最相似的了
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>color = "#4e3fe1"
<strong>输出：</strong>"#5544dd"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>color.length == 7</code></li>
	<li><code>color[0] == '#'</code></li>
	<li>对于任何 <code>i &gt; 0</code>，<code>color[i]</code> 都是一个在范围 <code>['0', 'f']</code> 内的 16 进制数</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def similarRGB(self, color: str) -> str:
        def f(x):
            y, z = divmod(int(x, 16), 17)
            if z > 8:
                y += 1
            return '{:02x}'.format(17 * y)

        a, b, c = color[1:3], color[3:5], color[5:7]
        return f'#{f(a)}{f(b)}{f(c)}'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String similarRGB(String color) {
        String a = color.substring(1, 3), b = color.substring(3, 5), c = color.substring(5, 7);
        return "#" + f(a) + f(b) + f(c);
    }

    private String f(String x) {
        int q = Integer.parseInt(x, 16);
        q = q / 17 + (q % 17 > 8 ? 1 : 0);
        return String.format("%02x", 17 * q);
    }
}
```

### **Go**

```go
func similarRGB(color string) string {
	f := func(x string) string {
		q, _ := strconv.ParseInt(x, 16, 64)
		if q%17 > 8 {
			q = q/17 + 1
		} else {
			q = q / 17
		}
		return fmt.Sprintf("%02x", 17*q)

	}
	a, b, c := color[1:3], color[3:5], color[5:7]
	return "#" + f(a) + f(b) + f(c)
}
```

### **...**

```

```

<!-- tabs:end -->
