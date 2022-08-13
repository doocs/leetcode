# [476. 数字的补数](https://leetcode.cn/problems/number-complement)

[English Version](/solution/0400-0499/0476.Number%20Complement/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对整数的二进制表示取反（<code>0</code> 变 <code>1</code> ，<code>1</code> 变 <code>0</code>）后，再转换为十进制表示，可以得到这个整数的补数。</p>

<ul>
	<li>例如，整数 <code>5</code> 的二进制表示是 <code>"101"</code> ，取反后得到 <code>"010"</code> ，再转回十进制表示得到补数 <code>2</code> 。</li>
</ul>

<p>给你一个整数 <code>num</code> ，输出它的补数。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 5
<strong>输出：</strong>2
<strong>解释：</strong>5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 1
<strong>输出：</strong>0
<strong>解释：</strong>1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt; 2<sup>31</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与 1009 <a href="https://leetcode.cn/problems/complement-of-base-10-integer/">https://leetcode.cn/problems/complement-of-base-10-integer/</a> 相同</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findComplement(self, num: int) -> int:
        ans = 0
        find = False
        for i in range(30, -1, -1):
            b = num & (1 << i)
            if not find and b == 0:
                continue
            find = True
            if b == 0:
                ans |= 1 << i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findComplement(int num) {
        int ans = 0;
        boolean find = false;
        for (int i = 30; i >= 0; --i) {
            int b = num & (1 << i);
            if (!find && b == 0) {
                continue;
            }
            find = true;
            if (b == 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findComplement(int num) {
        int full = pow(2, int(log2(num)) + 1) - 1;
        return full ^ num;
    }
};
```

```cpp
class Solution {
public:
    int findComplement(int num) {
        int ans = 0;
        bool find = false;
        for (int i = 30; i >= 0; --i)
        {
            int b = num & (1 << i);
            if (!find && b == 0) continue;
            find = true;
            if (b == 0) ans |= (1 << i);
        }
        return ans;
    }
};
```

### **Go**

```go
func findComplement(num int) int {
	ans := 0
	find := false
	for i := 30; i >= 0; i-- {
		b := num & (1 << i)
		if !find && b == 0 {
			continue
		}
		find = true
		if b == 0 {
			ans |= (1 << i)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
