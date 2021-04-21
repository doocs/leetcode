# [492. Construct the Rectangle](https://leetcode.com/problems/construct-the-rectangle)

[中文文档](/solution/0400-0499/0492.Construct%20the%20Rectangle/README.md)

## Description

<p>A web developer needs to know how to design a web page&#39;s size. So, given a specific rectangular web page&rsquo;s area, your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:</p>

<ol>
	<li>The area of the rectangular web page you designed must equal to the given target area.</li>
	<li>The width <code>W</code> should not be larger than the length <code>L</code>, which means <code>L &gt;= W</code>.</li>
	<li>The difference between length <code>L</code> and width <code>W</code> should be as small as possible.</li>
</ol>

<p>Return <em>an array <code>[L, W]</code> where <code>L</code> and <code>W</code> are the length and width of the&nbsp;web page you designed in sequence.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> area = 4
<strong>Output:</strong> [2,2]
<strong>Explanation:</strong> The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1]. 
But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> area = 37
<strong>Output:</strong> [37,1]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> area = 122122
<strong>Output:</strong> [427,286]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= area &lt;= 10<sup>7</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def constructRectangle(self, area: int) -> List[int]:
        sr = int(math.sqrt(area))
        l = w = sr
        while l <= area and w >= 1:
            s = l * w
            if s == area:
                break
            if s > area:
                w -= 1
            else:
                l += 1
        return [l, w]
```

### **Java**

```java
class Solution {
    public int[] constructRectangle(int area) {
        int sr = (int) Math.sqrt(area);
        int l = sr, w = sr;
        while (l <= area && w >= 1) {
            int s = l * w;
            if (s == area) break;
            if (s > area) --w;
            else ++l;
        }
        return new int[]{l, w};
    }
}
```

### **...**

```

```

<!-- tabs:end -->
