# [800. Similar RGB Color](https://leetcode.com/problems/similar-rgb-color)

[中文文档](/solution/0800-0899/0800.Similar%20RGB%20Color/README.md)

## Description

<p>The red-green-blue color <code>&quot;#AABBCC&quot;</code> can be written as <code>&quot;#ABC&quot;</code> in shorthand.</p>

<ul>
	<li>For example, <code>&quot;#15c&quot;</code> is shorthand for the color <code>&quot;#1155cc&quot;</code>.</li>
</ul>

<p>The similarity between the two colors <code>&quot;#ABCDEF&quot;</code> and <code>&quot;#UVWXYZ&quot;</code> is <code>-(AB - UV)<sup>2</sup> - (CD - WX)<sup>2</sup> - (EF - YZ)<sup>2</sup></code>.</p>

<p>Given a string <code>color</code> that follows the format <code>&quot;#ABCDEF&quot;</code>, return a string represents the color that is most similar to the given color and has a shorthand (i.e., it can be represented as some <code>&quot;#XYZ&quot;</code>).</p>

<p><strong>Any answer</strong> which has the same highest similarity as the best answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> color = &quot;#09f166&quot;
<strong>Output:</strong> &quot;#11ee66&quot;
<strong>Explanation:</strong> 
The similarity is -(0x09 - 0x11)<sup>2</sup> -(0xf1 - 0xee)<sup>2</sup> - (0x66 - 0x66)<sup>2</sup> = -64 -9 -0 = -73.
This is the highest among any shorthand color.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> color = &quot;#4e3fe1&quot;
<strong>Output:</strong> &quot;#5544dd&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>color.length == 7</code></li>
	<li><code>color[0] == &#39;#&#39;</code></li>
	<li><code>color[i]</code> is either digit or character in the range <code>[&#39;a&#39;, &#39;f&#39;]</code> for <code>i &gt; 0</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
