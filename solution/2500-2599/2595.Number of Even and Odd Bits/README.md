# [2595. 奇偶位数](https://leetcode.cn/problems/number-of-even-and-odd-bits)

[English Version](/solution/2500-2599/2595.Number%20of%20Even%20and%20Odd%20Bits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>正</strong> 整数 <code>n</code> 。</p>

<p>用 <code>even</code> 表示在 <code>n</code> 的二进制形式（下标从 <strong>0</strong> 开始）中值为 <code>1</code> 的偶数下标的个数。</p>

<p>用 <code>odd</code> 表示在 <code>n</code> 的二进制形式（下标从 <strong>0</strong> 开始）中值为 <code>1</code> 的奇数下标的个数。</p>

<p>返回整数数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer = [even, odd]</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 17
<strong>输出：</strong>[2,0]
<strong>解释：</strong>17 的二进制形式是 10001 。 
下标 0 和 下标 4 对应的值为 1 。 
共有 2 个偶数下标，0 个奇数下标。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>[0,1]
<strong>解释：</strong>2 的二进制形式是 10 。 
下标 1 对应的值为 1 。 
共有 0 个偶数下标，1 个奇数下标。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

我们根据题意，枚举 $n$ 的二进制表示中从低位到高位的每一位，如果该位为 $1$，则根据该位的下标是奇数还是偶数，将对应的计数器加 $1$ 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为给定的整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def evenOddBit(self, n: int) -> List[int]:
        ans = [0, 0]
        i = 0
        while n:
            ans[i] += n & 1
            i ^= 1
            n >>= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for (int i = 0; n > 0; n >>= 1, i ^= 1) {
            ans[i] += n & 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> evenOddBit(int n) {
        vector<int> ans(2);
        for (int i = 0; n > 0; n >>= 1, i ^= 1) {
            ans[i] += n & 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func evenOddBit(n int) []int {
	ans := make([]int, 2)
	for i := 0; n != 0; n, i = n>>1, i^1 {
		ans[i] += n & 1
	}
	return ans
}
```

### **TypeScript**

```ts
function evenOddBit(n: number): number[] {
    const ans = new Array(2).fill(0);
    for (let i = 0; n > 0; n >>= 1, i ^= 1) {
        ans[i] += n & 1;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
