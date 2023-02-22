# [89. 格雷编码](https://leetcode.cn/problems/gray-code)

[English Version](/solution/0000-0099/0089.Gray%20Code/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<strong>n 位格雷码序列</strong> 是一个由 <code>2<sup>n</sup></code> 个整数组成的序列，其中：

<ul>
	<li>每个整数都在范围 <code>[0, 2<sup>n</sup> - 1]</code> 内（含 <code>0</code> 和 <code>2<sup>n</sup> - 1</code>）</li>
	<li>第一个整数是 <code>0</code></li>
	<li>一个整数在序列中出现 <strong>不超过一次</strong></li>
	<li>每对 <strong>相邻</strong> 整数的二进制表示 <strong>恰好一位不同</strong> ，且</li>
	<li><strong>第一个</strong> 和 <strong>最后一个</strong> 整数的二进制表示 <strong>恰好一位不同</strong></li>
</ul>

<p>给你一个整数 <code>n</code> ，返回任一有效的 <strong>n 位格雷码序列</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>[0,1,3,2]
<strong>解释：</strong>
[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
- 0<strong><em>0</em></strong> 和 0<em><strong>1</strong></em> 有一位不同
- <em><strong>0</strong></em>1 和 <em><strong>1</strong></em>1 有一位不同
- 1<em><strong>1</strong></em> 和 1<em><strong>0</strong></em> 有一位不同
- <em><strong>1</strong></em>0 和 <em><strong>0</strong></em>0 有一位不同
[0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
- <em><strong>0</strong></em>0 和 <em><strong>1</strong></em>0 有一位不同
- 1<em><strong>0</strong></em> 和 1<em><strong>1</strong></em> 有一位不同
- <em><strong>1</strong></em>1 和 <em><strong>0</strong></em>1 有一位不同
- 0<em><strong>1</strong></em> 和 0<em><strong>0</strong></em> 有一位不同
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>[0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 16</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二进制码转格雷码**

格雷码是我们在工程中常会遇到的一种编码方式，它的基本的特点就是任意两个相邻的代码只有一位二进制数不同。

二进制码转换成二进制格雷码，其法则是保留二进制码的最高位作为格雷码的最高位，而次高位格雷码为二进制码的高位与次高位相异或，而格雷码其余各位与次高位的求法相类似。

假设某个二进制数表示为 $B_{n-1}B_{n-2}...B_2B_1B_0$，其格雷码表示为 $G_{n-1}G_{n-2}...G_2G_1G_0$。最高位保留，所以 $G_{n-1} = B_{n-1}$；而其它各位 $G_i = B_{i+1} \oplus B_{i}$，其中 $i=0,1,2..,n-2$。

因此，对于一个整数 $x$，我们可以用函数 $gray(x)$ 得到其格雷码：

```java
int gray(x) {
    return x ^ (x >> 1);
}
```

我们直接将 $[0,..2^n - 1]$ 这些整数映射成对应的格雷码，即可得到答案数组。

时间复杂度 $O(2^n)$，其中 $n$ 为题目给定的整数。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def grayCode(self, n: int) -> List[int]:
        return [i ^ (i >> 1) for i in range(1 << n)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> ans;
        for (int i = 0; i < 1 << n; ++i) {
            ans.push_back(i ^ (i >> 1));
        }
        return ans;
    }
};
```

### **Go**

```go
func grayCode(n int) (ans []int) {
	for i := 0; i < 1<<n; i++ {
		ans = append(ans, i^(i>>1))
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var grayCode = function (n) {
    const ans = [];
    for (let i = 0; i < 1 << n; ++i) {
        ans.push(i ^ (i >> 1));
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
