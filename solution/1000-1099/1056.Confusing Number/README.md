# [1056. 易混淆数](https://leetcode.cn/problems/confusing-number)

[English Version](/solution/1000-1099/1056.Confusing%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数字 <code>N</code>，当它满足以下条件的时候返回 <code>true</code>：</p>

<p>原数字旋转 180&deg; 以后可以得到新的数字。</p>

<p>如 0, 1, 6, 8, 9 旋转 180&deg; 以后，得到了新的数字 0, 1, 9, 8, 6 。</p>

<p>2, 3, 4, 5, 7 旋转 180&deg; 后，得到的<strong>不是</strong>数字。</p>

<p>易混淆数&nbsp;(confusing number) 在旋转180&deg;以后，可以得到和原来<strong>不同</strong>的数，且新数字的每一位都是有效的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_1.png" style="height: 90px; width: 180px;"></p>

<pre><strong>输入：</strong>6
<strong>输出：</strong>true
<strong>解释： 
</strong>把 6 旋转 180&deg; 以后得到 9，9 是有效数字且 9!=6 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_2.png" style="height: 90px; width: 180px;"></p>

<pre><strong>输入：</strong>89
<strong>输出：</strong>true
<strong>解释: 
</strong>把 89 旋转 180&deg; 以后得到 68，<code>86</code> 是有效数字且 86!=89 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_3.png" style="height: 121px; width: 301px;"></p>

<pre><strong>输入：</strong>11
<strong>输出：</strong>false
<strong>解释：
</strong>把 11 旋转 180&deg; 以后得到 11，11 是有效数字但是值保持不变，所以 11 不是易混淆数字。 
</pre>

<p><strong>示例 4：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1056.Confusing%20Number/images/1268_4.png" style="height: 90px; width: 180px;"></p>

<pre><strong>输入：</strong>25
<strong>输出：</strong>false
<strong>解释：</strong>
把 25 旋转 180&deg; 以后得到的不是数字。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= N &lt;= 10^9</code></li>
	<li>可以忽略掉旋转后得到的前导零，例如，如果我们旋转后得到 <code>0008</code> 那么该数字就是 <code>8</code> 。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

我们先用一个长度为 $10$ 的数组 $d$ 记录每个数字旋转 180° 后对应的数字，在这道题中，数字 $[0,1,6,8,9]$ 旋转后得到的数字是 $[0,1,9,8,6]$，其余数字旋转后得到的不是数字，我们将对应的数字置为 $-1$。

将 $n$ 的值赋给 $x$。然后遍历数字 $x$ 的每一位数字 $v$，如果 $d[v] \lt 0$，说明 $x$ 不是易混淆数，直接返回 `false`。否则，我们将数字 $v$ 对应的旋转数字 $d[v]$ 加入到 $y$ 中。最后，判断 $y$ 和 $n$ 是否相等，若不相等，则说明 $n$ 是易混淆数，返回 `true`。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

相似题目：[788. 旋转数字](/solution/0700-0799/0788.Rotated%20Digits/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def confusingNumber(self, n: int) -> bool:
        x, y = n, 0
        d = [0, 1, -1, -1, -1, -1, 9, -1, 8, 6]
        while x:
            x, v = divmod(x, 10)
            if d[v] < 0:
                return False
            y = y * 10 + d[v]
        return y != n
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean confusingNumber(int n) {
        int[] d = new int[] {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        int x = n, y = 0;
        while (x > 0) {
            int v = x % 10;
            if (d[v] < 0) {
                return false;
            }
            y = y * 10 + d[v];
            x /= 10;
        }
        return y != n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool confusingNumber(int n) {
        vector<int> d = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        int x = n, y = 0;
        while (x) {
            int v = x % 10;
            if (d[v] < 0) {
                return false;
            }
            y = y * 10 + d[v];
            x /= 10;
        }
        return y != n;
    }
};
```

### **Go**

```go
func confusingNumber(n int) bool {
	d := []int{0, 1, -1, -1, -1, -1, 9, -1, 8, 6}
	x, y := n, 0
	for x > 0 {
		v := x % 10
		if d[v] < 0 {
			return false
		}
		y = y*10 + d[v]
		x /= 10
	}
	return y != n
}
```

### **...**

```

```

<!-- tabs:end -->
