# [1952. 三除数](https://leetcode.cn/problems/three-divisors)

[English Version](/solution/1900-1999/1952.Three%20Divisors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> 。如果 <code>n</code> <strong>恰好有三个正除数</strong> ，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code> 。</p>

<p>如果存在整数 <code>k</code> ，满足 <code>n = k * m</code> ，那么整数 <code>m</code> 就是 <code>n</code> 的一个 <strong>除数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>false
<strong>解释：</strong>2 只有两个除数：1 和 2 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 4
<strong>输出：</strong>true
<strong>解释：</strong>4 有三个除数：1、2 和 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isThree(self, n: int) -> bool:
        return sum(n % i == 0 for i in range(2, n)) == 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isThree(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isThree(int n) {
        int cnt = 0;
        for (int i = 2; i < n; ++i)
            cnt += n % i == 0;
        return cnt == 1;
    }
};
```

### **Go**

```go
func isThree(n int) bool {
	cnt := 0
	for i := 2; i < n; i++ {
		if n%i == 0 {
			cnt++
		}
	}
	return cnt == 1
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isThree = function (n) {
    let cnt = 0;
    for (let i = 2; i < n; ++i) {
        if (n % i == 0) {
            ++cnt;
        }
    }
    return cnt == 1;
};
```

### **...**

```

```

<!-- tabs:end -->
