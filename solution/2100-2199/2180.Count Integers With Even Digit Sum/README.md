# [2180. 统计各位数字之和为偶数的整数个数](https://leetcode.cn/problems/count-integers-with-even-digit-sum)

[English Version](/solution/2100-2199/2180.Count%20Integers%20With%20Even%20Digit%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>num</code> ，请你统计并返回 <strong>小于或等于</strong> <code>num</code> 且各位数字之和为 <strong>偶数</strong> 的正整数的数目。</p>

<p>正整数的 <strong>各位数字之和</strong> 是其所有位上的对应数字相加的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 4
<strong>输出：</strong>2
<strong>解释：</strong>
只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。    
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 30
<strong>输出：</strong>14
<strong>解释：</strong>
只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是： 
2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

一种最简单直接的方法是枚举 $[1,..num]$ 的所有整数 $x$，判断 $x$ 各位数字之和是否为偶数，是则答案加一。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(1)$。其中 $n$ 为 $num$ 的值。

**方法二：数学**

我们观察发现，在 $[0,..x]$ 的所有数中，每 $10$ 个数中，就有 $5$ 个数的各位数字之和为偶数。例如，在 $[0,..9]$ 中，每 $10$ 个数中，就有 $5$ 个数的各位数字之和为偶数，分别是 $0,2,4,6,8$。

因此，我们可以先算出 $num$ 中有多少个 $10$ 的倍数，然后乘以 $5$ 再减去 $1$（排除 $0$ 这个偶数），可以得到初始答案 $ans=\left\lfloor \frac{num}{10} \right\rfloor \times 5 - 1$。

接下来，我们还需要考虑剩下的 $num \% 10 + 1$ 个数字中，有多少个数的各位数字之和为偶数。这些数字是否是偶数，跟数字的前面数字之和有关，因此，我们可以算出 $num$ 的前面数字之和 $s$，那么剩余的数字中，还有 $\left\lfloor \frac{num \% 10 + 2 - (s \& 1)}{2} \right\rfloor$ 个数的各位数字之和为偶数。累加到答案 $ans$ 中即可。

我们不妨举个例子，假设 $num$ 为 $123$，那么前面 $[0,..119]$ 中一共有 $12$ 个 $10$ 的倍数，每个 $10$ 的倍数中有 $5$ 个数的各位数字之和为偶数，因此，初始答案为 $ans=12 \times 5 - 1=59$。

剩下的数字分别是 $120,121,122,123$，每个数字的前两位数字之和为 $s = 1+2=3$，是奇数，因此，剩下的数字中，只有 $2$ 个数的各位数字之和为偶数，累加到答案 $ans$ 中，最终答案为 $ans+2=61$。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为 $num$ 的值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countEven(self, num: int) -> int:
        ans = 0
        for x in range(1, num + 1):
            s = 0
            while x:
                s += x % 10
                x //= 10
            ans += s % 2 == 0
        return ans
```

```python
class Solution:
    def countEven(self, num: int) -> int:
        ans = num // 10 * 5 - 1
        x, s = num // 10, 0
        while x:
            s += x % 10
            x //= 10
        ans += (num % 10 + 2 - (s & 1)) >> 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int s = 0;
            for (int x = i; x > 0; x /= 10) {
                s += x % 10;
            }
            if (s % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int countEven(int num) {
        int ans = num / 10 * 5 - 1;
        int s = 0;
        for (int x = num / 10; x > 0; x /= 10) {
            s += x % 10;
        }
        ans += (num % 10 + 2 - (s & 1)) >> 1;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int s = 0;
            for (int x = i; x; x /= 10) {
                s += x % 10;
            }
            ans += s % 2 == 0;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countEven(int num) {
        int ans = num / 10 * 5 - 1;
        int s = 0;
        for (int x = num / 10; x > 0; x /= 10) {
            s += x % 10;
        }
        ans += (num % 10 + 2 - (s & 1)) >> 1;
        return ans;
    }
};
```

### **Go**

```go
func countEven(num int) (ans int) {
	for i := 1; i <= num; i++ {
		s := 0
		for x := i; x > 0; x /= 10 {
			s += x % 10
		}
		if s%2 == 0 {
			ans++
		}
	}
	return
}
```

```go
func countEven(num int) (ans int) {
	ans = num/10*5 - 1
	s := 0
	for x := num / 10; x > 0; x /= 10 {
		s += x % 10
	}
	ans += (num%10 + 2 - (s & 1)) >> 1
	return
}
```

### **TypeScript**

```ts
function countEven(num: number): number {
    let ans = 0;
    for (let i = 1; i <= num; ++i) {
        let s = 0;
        for (let x = i; x; x = Math.floor(x / 10)) {
            s += x % 10;
        }
        if (s % 2 == 0) {
            ++ans;
        }
    }
    return ans;
}
```

```ts
function countEven(num: number): number {
    let ans = Math.floor(num / 10) * 5 - 1;
    let s = 0;
    for (let x = Math.floor(num / 10); x; x = Math.floor(x / 10)) {
        s += x % 10;
    }
    ans += ((num % 10) + 2 - (s & 1)) >> 1;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
