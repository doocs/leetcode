# [2310. 个位数字为 K 的整数之和](https://leetcode.cn/problems/sum-of-numbers-with-units-digit-k)

[English Version](/solution/2300-2399/2310.Sum%20of%20Numbers%20With%20Units%20Digit%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数 <code>num</code> 和 <code>k</code> ，考虑具有以下属性的正整数多重集：</p>

<ul>
	<li>每个整数个位数字都是 <code>k</code> 。</li>
	<li>所有整数之和是 <code>num</code> 。</li>
</ul>

<p>返回该多重集的最小大小，如果不存在这样的多重集，返回<em> </em><code>-1</code> 。</p>

<p>注意：</p>

<ul>
	<li>多重集与集合类似，但多重集可以包含多个同一整数，空多重集的和为 <code>0</code> 。</li>
	<li><strong>个位数字</strong> 是数字最右边的数位。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 58, k = 9
<strong>输出：</strong>2
<strong>解释：</strong>
多重集 [9,49] 满足题目条件，和为 58 且每个整数的个位数字是 9 。
另一个满足条件的多重集是 [19,39] 。
可以证明 2 是满足题目条件的多重集的最小长度。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 37, k = 2
<strong>输出：</strong>-1
<strong>解释：</strong>个位数字为 2 的整数无法相加得到 37 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = 0, k = 7
<strong>输出：</strong>0
<strong>解释：</strong>空多重集的和为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 3000</code></li>
	<li><code>0 &lt;= k &lt;= 9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学 + 枚举**

符合拆分条件的每个数都可以表示成 $10x_i+k$，若总共有 $n$ 个数，那么 $num-n*k$ 必然是 $10$ 的倍数。

我们从小到达枚举 $n$，找到第一个满足 $num-n*k$ 是 $10$ 的倍数的 $n$。由于 $n$ 不会超过 $num$，因此 $n$ 最大枚举至 $num$。

也可以只考虑个位，个位满足，高位随意。

**方法二：记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        if num == 0:
            return 0
        for i in range(1, num + 1):
            if (t := num - k * i) >= 0 and t % 10 == 0:
                return i
        return -1
```

```python
class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        if num == 0:
            return 0
        for i in range(1, 11):
            if (k * i) % 10 == num % 10 and k * i <= num:
                return i
        return -1
```

```python
class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        @cache
        def dfs(v):
            if v == 0:
                return 0
            if v < 10 and v % k:
                return inf
            i = 0
            t = inf
            while (x := i * 10 + k) <= v:
                t = min(t, dfs(v - x))
                i += 1
            return t + 1

        if num == 0:
            return 0
        if k == 0:
            return -1 if num % 10 else 1
        ans = dfs(num)
        return -1 if ans >= inf else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        for (int i = 1; i <= num; ++i) {
            int t = num - k * i;
            if (t >= 0 && t % 10 == 0) {
                return i;
            }
        }
        return -1;
    }
}
```

```java
class Solution {
    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        for (int i = 1; i <= 10; ++i) {
            if ((k * i) % 10 == num % 10 && k * i <= num) {
                return i;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumNumbers(int num, int k) {
        if (num == 0) return 0;
        for (int i = 1; i <= num; ++i) {
            int t = num - k * i;
            if (t >= 0 && t % 10 == 0) return i;
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int minimumNumbers(int num, int k) {
        if (!num) return 0;
        for (int i = 1; i <= 10; ++i)
            if ((k * i) % 10 == num % 10 && k * i <= num)
                return i;
        return -1;
    }
};
```

### **Go**

```go
func minimumNumbers(num int, k int) int {
	if num == 0 {
		return 0
	}
	for i := 1; i <= num; i++ {
		t := num - k*i
		if t >= 0 && t%10 == 0 {
			return i
		}
	}
	return -1
}
```

```go
func minimumNumbers(num int, k int) int {
	if num == 0 {
		return 0
	}
	for i := 1; i <= 10; i++ {
		if (k*i)%10 == num%10 && k*i <= num {
			return i
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function minimumNumbers(num: number, k: number): number {
    if (!num) return 0;
    let digit = num % 10;
    for (let i = 1; i < 11; i++) {
        let target = i * k;
        if (target <= num && target % 10 == digit) return i;
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
