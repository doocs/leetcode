# [1134. 阿姆斯特朗数](https://leetcode.cn/problems/armstrong-number)

[English Version](/solution/1100-1199/1134.Armstrong%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code>&nbsp;，让你来判定他是否是<strong>&nbsp;</strong><strong>阿姆斯特朗数</strong>，是则返回 <code>true</code>，不是则返回 <code>false</code>。</p>

<p>假设存在一个 <code>k</code> 位数 <code>n</code>&nbsp;，其每一位上的数字的 <code>k</code> 次幂的总和也是 <code>n</code>&nbsp;，那么这个数是阿姆斯特朗数 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 153
<strong>输出：</strong>true
<strong>示例： </strong>
153 是一个 3 位数，且 153 = 1<sup>3</sup> + 5<sup>3</sup> + 3<sup>3</sup>。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 123
<strong>输出：</strong>false
<strong>解释：</strong>123 是一个 3 位数，且 123 != 1<sup>3</sup> + 2<sup>3</sup> + 3<sup>3</sup> = 36。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们可以先计算出数字的位数 $k$，然后计算每一位上的数字的 $k$ 次幂的总和 $s$，最后判断 $s$ 是否等于 $n$ 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为给定的数字。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
