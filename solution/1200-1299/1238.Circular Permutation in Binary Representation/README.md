# [1238. 循环码排列](https://leetcode.cn/problems/circular-permutation-in-binary-representation)

[English Version](/solution/1200-1299/1238.Circular%20Permutation%20in%20Binary%20Representation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数&nbsp;<code>n</code> 和 <code>start</code>。你的任务是返回任意 <code>(0,1,2,,...,2^n-1)</code> 的排列 <code>p</code>，并且满足：</p>

<ul>
	<li><code>p[0] = start</code></li>
	<li><code>p[i]</code> 和 <code>p[i+1]</code>&nbsp;的二进制表示形式只有一位不同</li>
	<li><code>p[0]</code> 和 <code>p[2^n -1]</code>&nbsp;的二进制表示形式也只有一位不同</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2, start = 3
<strong>输出：</strong>[3,2,0,1]
<strong>解释：</strong>这个排列的二进制表示是 (11,10,00,01)
     所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, start = 2
<strong>输出：</strong>[2,6,7,5,4,0,1,3]
<strong>解释：</strong>这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 16</code></li>
	<li><code>0 &lt;= start&nbsp;&lt;&nbsp;2^n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二进制码转格雷码**

我们观察题目中的排列，可以发现，它的二进制表示中，任意两个（包括首尾）相邻的数只有一位二进制数不同。这种编码方式就是格雷码，它是我们在工程中会遇到的一种编码方式。

二进制码转换成二进制格雷码，其法则是保留二进制码的最高位作为格雷码的最高位，而次高位格雷码为二进制码的高位与次高位相异或，而格雷码其余各位与次高位的求法相类似。

假设某个二进制数表示为 $B_{n-1}B_{n-2}...B_2B_1B_0$，其格雷码表示为 $G_{n-1}G_{n-2}...G_2G_1G_0$。最高位保留，所以 $G_{n-1} = B_{n-1}$；而其它各位 $G_i = B_{i+1} \oplus B_{i}$，其中 $i=0,1,2..,n-2$。

因此，对于一个整数 $x$，我们可以用函数 $gray(x)$ 得到其格雷码：

```java
int gray(x) {
    return x ^ (x >> 1);
}
```

我们可以直接将 $[0,..2^n - 1]$ 这些整数转换成对应的格雷码数组，然后找到 $start$ 在格雷码数组中的位置，将格雷码数组从该位置开始截取，再将截取的部分拼接到格雷码数组的前面，就得到了题目要求的排列。

时间复杂度 $O(2^n)$，空间复杂度 $O(2^n)$。其中 $n$ 为题目给定的整数。

**方法二：转换优化**

由于 $gray(0) = 0$，那么 $gray(0) \oplus start = start$，而 $gray(i)$ 与 $gray(i-1)$ 只有一个二进制位不同，所以 $gray(i) \oplus start$ 与 $gray(i-1) \oplus start$ 也只有一个二进制位不同。

因此，我们也可以直接将 $[0,..2^n - 1]$ 这些整数转换成对应的 $gray(i) \oplus start$，即可得到首项为 $start$ 的格雷码排列。

时间复杂度 $O(2^n)$，其中 $n$ 为题目给定的整数。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def circularPermutation(self, n: int, start: int) -> List[int]:
        g = [i ^ (i >> 1) for i in range(1 << n)]
        j = g.index(start)
        return g[j:] + g[:j]
```

```python
class Solution:
    def circularPermutation(self, n: int, start: int) -> List[int]:
        return [i ^ (i >> 1) ^ start for i in range(1 << n)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        int[] g = new int[1 << n];
        int j = 0;
        for (int i = 0; i < 1 << n; ++i) {
            g[i] = i ^ (i >> 1);
            if (g[i] == start) {
                j = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = j; i < j + (1 << n); ++i) {
            ans.add(g[i % (1 << n)]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            ans.add(i ^ (i >> 1) ^ start);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> circularPermutation(int n, int start) {
        int g[1 << n];
        int j = 0;
        for (int i = 0; i < 1 << n; ++i) {
            g[i] = i ^ (i >> 1);
            if (g[i] == start) {
                j = i;
            }
        }
        vector<int> ans;
        for (int i = j; i < j + (1 << n); ++i) {
            ans.push_back(g[i % (1 << n)]);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> circularPermutation(int n, int start) {
        vector<int> ans(1 << n);
        for (int i = 0; i < 1 << n; ++i) {
            ans[i] = i ^ (i >> 1) ^ start;
        }
        return ans;
    }
};
```

### **Go**

```go
func circularPermutation(n int, start int) []int {
	g := make([]int, 1<<n)
	j := 0
	for i := range g {
		g[i] = i ^ (i >> 1)
		if g[i] == start {
			j = i
		}
	}
	return append(g[j:], g[:j]...)
}
```

```go
func circularPermutation(n int, start int) (ans []int) {
	for i := 0; i < 1<<n; i++ {
		ans = append(ans, i^(i>>1)^start)
	}
	return
}
```

### **TypeScript**

```ts
function circularPermutation(n: number, start: number): number[] {
    const ans: number[] = [];
    for (let i = 0; i < 1 << n; ++i) {
        ans.push(i ^ (i >> 1) ^ start);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
