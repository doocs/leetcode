# [1414. 和为 K 的最少斐波那契数字数目](https://leetcode.cn/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k)

[English Version](/solution/1400-1499/1414.Find%20the%20Minimum%20Number%20of%20Fibonacci%20Numbers%20Whose%20Sum%20Is%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你数字 <code>k</code>&nbsp;，请你返回和为&nbsp;<code>k</code>&nbsp;的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。</p>

<p>斐波那契数字定义为：</p>

<ul>
	<li>F<sub>1</sub> = 1</li>
	<li>F<sub>2</sub> = 1</li>
	<li>F<sub>n</sub> = F<sub>n-1</sub> + F<sub>n-2</sub>&nbsp;， 其中 n &gt; 2 。</li>
</ul>

<p>数据保证对于给定的 <code>k</code>&nbsp;，一定能找到可行解。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>k = 7
<strong>输出：</strong>2 
<strong>解释：</strong>斐波那契数字为：1，1，2，3，5，8，13，&hellip;&hellip;
对于 k = 7 ，我们可以得到 2 + 5 = 7 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>k = 10
<strong>输出：</strong>2 
<strong>解释：</strong>对于 k = 10 ，我们可以得到 2 + 8 = 10 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>k = 19
<strong>输出：</strong>3 
<strong>解释：</strong>对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于斐波那契数特点，数字重用在此题中是一个烟雾弹。举例推导：`k = 288`，数列（局部）`1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377`，可以由两个 144，或 `233 + 55` 组成；`k = 10`，可以由两个 5 或 `8 + 2` 组成。

由此可以使用贪心策略，逆向遍历斐波那契数列，进行暴力查找：

```txt
FIND-MIN-FIBONACCI-NUMBERS(k)
    r = 0
    for n in f
        if k >= n
            k -= n
            r++
            if k === 0
                return res
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMinFibonacciNumbers(self, k: int) -> int:
        def dfs(k):
            if k < 2:
                return k
            a = b = 1
            while b <= k:
                a, b = b, a + b
            return 1 + dfs(k - a)

        return dfs(k)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int findMinFibonacciNumbers(int k) {
        if (k < 2) {
            return k;
        }
        int a = 1, b = 1;
        while (b <= k) {
            b = a + b;
            a = b - a;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
}

```

### **TypeScript**

```ts
const arr = [
    1836311903, 1134903170, 701408733, 433494437, 267914296, 165580141,
    102334155, 63245986, 39088169, 24157817, 14930352, 9227465, 5702887,
    3524578, 2178309, 1346269, 832040, 514229, 317811, 196418, 121393, 75025,
    46368, 28657, 17711, 10946, 6765, 4181, 2584, 1597, 987, 610, 377, 233, 144,
    89, 55, 34, 21, 13, 8, 5, 3, 2, 1,
];

function findMinFibonacciNumbers(k: number): number {
    let res = 0;
    for (const num of arr) {
        if (k >= num) {
            k -= num;
            res++;
            if (k === 0) {
                break;
            }
        }
    }
    return res;
}
```

### **Rust**

```rust
const FIB: [i32; 45] = [
    1836311903, 1134903170, 701408733, 433494437, 267914296, 165580141, 102334155, 63245986,
    39088169, 24157817, 14930352, 9227465, 5702887, 3524578, 2178309, 1346269, 832040, 514229,
    317811, 196418, 121393, 75025, 46368, 28657, 17711, 10946, 6765, 4181, 2584, 1597, 987, 610,
    377, 233, 144, 89, 55, 34, 21, 13, 8, 5, 3, 2, 1,
];

impl Solution {
    pub fn find_min_fibonacci_numbers(mut k: i32) -> i32 {
        let mut res = 0;
        for &i in FIB.into_iter() {
            if k >= i {
                k -= i;
                res += 1;
                if k == 0 {
                    break;
                }
            }
        }
        res
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMinFibonacciNumbers(int k) {
        if (k < 2) return k;
        int a = 1, b = 1;
        while (b <= k) {
            b = a + b;
            a = b - a;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
};
```

### **Go**

```go
func findMinFibonacciNumbers(k int) int {
	if k < 2 {
		return k
	}
	a, b := 1, 1
	for b <= k {
		a, b = b, a+b
	}
	return 1 + findMinFibonacciNumbers(k-a)
}
```

### **...**

```

```

<!-- tabs:end -->
