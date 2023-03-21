# [1134. Armstrong Number](https://leetcode.com/problems/armstrong-number)

[中文文档](/solution/1100-1199/1134.Armstrong%20Number/README.md)

## Description

<p>Given an integer <code>n</code>, return <code>true</code> <em>if and only if it is an <strong>Armstrong number</strong></em>.</p>

<p>The <code>k</code>-digit number <code>n</code> is an Armstrong number if and only if the <code>k<sup>th</sup></code> power of each digit sums to <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 153
<strong>Output:</strong> true
<strong>Explanation:</strong> 153 is a 3-digit number, and 153 = 1<sup>3</sup> + 5<sup>3</sup> + 3<sup>3</sup>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 123
<strong>Output:</strong> false
<strong>Explanation:</strong> 123 is a 3-digit number, and 123 != 1<sup>3</sup> + 2<sup>3</sup> + 3<sup>3</sup> = 36.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isArmstrong(self, n: int) -> bool:
        k = len(str(n))
        s, x = 0, n
        while x:
            s += (x % 10)**k
            x //= 10
        return s == n
```

### **Java**

```java
class Solution {
    public boolean isArmstrong(int n) {
        int k = (n + "").length();
        int s = 0;
        for (int x = n; x > 0; x /= 10) {
            s += Math.pow(x % 10, k);
        }
        return s == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isArmstrong(int n) {
        int k = to_string(n).size();
        int s = 0;
        for (int x = n; x; x /= 10) {
            s += pow(x % 10, k);
        }
        return s == n;
    }
};
```

### **Go**

```go
func isArmstrong(n int) bool {
	k := 0
	for x := n; x > 0; x /= 10 {
		k++
	}
	s := 0
	for x := n; x > 0; x /= 10 {
		s += int(math.Pow(float64(x%10), float64(k)))
	}
	return s == n
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isArmstrong = function (n) {
    const k = String(n).length;
    let s = 0;
    for (let x = n; x; x = Math.floor(x / 10)) {
        s += Math.pow(x % 10, k);
    }
    return s == n;
};
```

### **TypeScript**

```ts
function isArmstrong(n: number): boolean {
    const k = String(n).length;
    let s = 0;
    for (let x = n; x; x = Math.floor(x / 10)) {
        s += Math.pow(x % 10, k);
    }
    return s == n;
}
```

### **...**

```

```

<!-- tabs:end -->
