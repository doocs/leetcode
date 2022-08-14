# [1247. Minimum Swaps to Make Strings Equal](https://leetcode.com/problems/minimum-swaps-to-make-strings-equal)

[中文文档](/solution/1200-1299/1247.Minimum%20Swaps%20to%20Make%20Strings%20Equal/README.md)

## Description

<p>You are given two strings <code>s1</code> and <code>s2</code> of equal length consisting of letters <code>&quot;x&quot;</code> and <code>&quot;y&quot;</code> <strong>only</strong>. Your task is to make these two strings equal to each other. You can swap any two characters that belong to <strong>different</strong> strings, which means: swap <code>s1[i]</code> and <code>s2[j]</code>.</p>

<p>Return the minimum number of swaps required to make <code>s1</code> and <code>s2</code> equal, or return <code>-1</code> if it is impossible to do so.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;xx&quot;, s2 = &quot;yy&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> Swap s1[0] and s2[1], s1 = &quot;yx&quot;, s2 = &quot;yx&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;xy&quot;, s2 = &quot;yx&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Swap s1[0] and s2[0], s1 = &quot;yy&quot;, s2 = &quot;xx&quot;.
Swap s1[0] and s2[1], s1 = &quot;xy&quot;, s2 = &quot;xy&quot;.
Note that you cannot swap s1[0] and s1[1] to make s1 equal to &quot;yx&quot;, cause we can only swap chars in different strings.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;xx&quot;, s2 = &quot;xy&quot;
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 1000</code></li>
	<li><code>s1, s2</code> only contain <code>&#39;x&#39;</code> or <code>&#39;y&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public int minimumSwap(String s1, String s2) {
        int xy = 0, yx = 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] > c2[i]) {
                xy++;
            }
            if (c2[i] > c1[i]) {
                yx++;
            }
        }
        if ((xy + yx) % 2 != 0) {
            return -1;
        }
        return xy % 2 == 0 ? (xy + yx) / 2 : (xy + yx) / 2 + 1;
    }
}
```

### **Go**

```go
func minimumSwap(s1 string, s2 string) int {
	xy, yx := 0, 0
	for i, _ := range s1 {
		if s1[i] < s2[i] {
			xy++
		}
		if s1[i] > s2[i] {
			yx++
		}
	}
	if (xy+yx)%2 != 0 {
		return -1
	}
	if xy%2 == 0 {
		return (xy + yx) / 2
	}
	return (xy+yx)/2 + 1
}
```

### **...**

```

```

<!-- tabs:end -->
