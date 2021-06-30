# [633. 平方数之和](https://leetcode-cn.com/problems/sum-of-square-numbers)

[English Version](/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数&nbsp;<code>c</code>&nbsp;，你要判断是否存在两个整数 <code>a</code> 和 <code>b</code>，使得&nbsp;<code>a<sup>2</sup> + b<sup>2</sup> = c</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>c = 5
<strong>输出：</strong>true
<strong>解释：</strong>1 * 1 + 2 * 2 = 5
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>c = 3
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>c = 4
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>c = 2
<strong>输出：</strong>true
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>c = 1
<strong>输出：</strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= c &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

![](https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/images/table.png)

上图为 a，b，c 之间的关系，这题其实就是在这张“表”里查找 c

从表的右上角看，不难发现它类似一棵二叉查找树，所以只需从右上角开始，按照二叉查找树的规律进行搜索

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
