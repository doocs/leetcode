# [633. Sum of Square Numbers](https://leetcode.com/problems/sum-of-square-numbers)

[中文文档](/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/README.md)

## Description

<p>Given a non-negative integer <code>c</code>, decide whether there&#39;re two integers <code>a</code> and <code>b</code> such that <code>a<sup>2</sup> + b<sup>2</sup> = c</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> c = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> 1 * 1 + 2 * 2 = 5
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> c = 3
<strong>Output:</strong> false
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> c = 4
<strong>Output:</strong> true
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> c = 2
<strong>Output:</strong> true
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> c = 1
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= c &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

![](https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/images/table.png)

The picture above shows the relationship between `a`, `b`, and `c`. This question is actually looking up `c` in this table

From the upper right corner of the table, it is not difficult to find that it is similar to a binary search tree, so just start from the upper right corner and search according to the law of the binary search tree

<!-- tabs:start -->

### **Python3**

```python
class Solution(object):
    def judgeSquareSum(self, c):
        i, j = 0, int(math.sqrt(c))
        while i <= j:
            s = i * i + j * j
            if s < c:
                i += 1
            elif s > c:
                j -= 1
            else:
                return True
        return False
```

### **Java**

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        int i = 0, j = (int) Math.sqrt(c);
        while (i <= j) {
            int s = i * i + j * j;
            if (s < c) {
                ++i;
            } else if (s > c) {
                --j;
            } else {
                return true;
            }
        }
        return false;
    }
}
```

### **TypeScript**

```ts
function judgeSquareSum(c: number): boolean {
    let a = 0, b = Math.floor(Math.sqrt(c));
    while (a <= b) {
        let sum = a ** 2 + b ** 2;
        if (sum == c) return true;
        if (sum < c) {
            ++a;
        } else {
            --b;
        }
    }
    return false;
};
```

### **C++**

```cpp
class Solution {
public:
    bool judgeSquareSum(int c) {
        long i = 0, j = (long) sqrt(c);
        while (i <= j) {
            long s = i * i + j * j;
            if (s < c) ++i;
            else if (s > c) --j;
            else return true;
        }
        return false;
    }
};
```

### **Go**

```go
func judgeSquareSum(c int) bool {
	i, j := 0, int(math.Sqrt(float64(c)))
	for i <= j {
		s := i*i + j*j
		if s < c {
			i++
		} else if s > c {
			j--
		} else {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
