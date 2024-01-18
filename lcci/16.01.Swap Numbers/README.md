# [面试题 16.01. 交换数字](https://leetcode.cn/problems/swap-numbers-lcci)

[English Version](/lcci/16.01.Swap%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>编写一个函数，不用临时变量，直接交换<code>numbers = [a, b]</code>中<code>a</code>与<code>b</code>的值。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入:</strong> numbers = [1,2]
<strong>输出:</strong> [2,1]
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>numbers.length == 2</code></li>
</ul>

## 解法

### 方法一：位运算

我们可以使用异或运算 $\oplus$ 来实现两个数的交换。

异或运算有以下三个性质。

-   任何数和 $0$ 做异或运算，结果仍然是原来的数，即 $a \oplus 0=a$。
-   任何数和其自身做异或运算，结果是 $0$，即 $a \oplus a=0$。
-   异或运算满足交换律和结合律，即 $a \oplus b \oplus a=b \oplus a \oplus a=b \oplus (a \oplus a)=b \oplus 0=b$。

因此，我们可以对 $numbers$ 中的两个数 $a$ 和 $b$ 进行如下操作：

-   $a=a \oplus b$，此时 $a$ 中存储了两个数的异或结果；
-   $b=a \oplus b$，此时 $b$ 中存储了原来 $a$ 的值；
-   $a=a \oplus b$，此时 $a$ 中存储了原来 $b$ 的值；

这样，我们就可以实现在不使用临时变量的情况下对两个数进行交换。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def swapNumbers(self, numbers: List[int]) -> List[int]:
        numbers[0] ^= numbers[1]
        numbers[1] ^= numbers[0]
        numbers[0] ^= numbers[1]
        return numbers
```

```java
class Solution {
    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }
}
```

```cpp
class Solution {
public:
    vector<int> swapNumbers(vector<int>& numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }
};
```

```go
func swapNumbers(numbers []int) []int {
	numbers[0] ^= numbers[1]
	numbers[1] ^= numbers[0]
	numbers[0] ^= numbers[1]
	return numbers
}
```

```ts
function swapNumbers(numbers: number[]): number[] {
    numbers[0] ^= numbers[1];
    numbers[1] ^= numbers[0];
    numbers[0] ^= numbers[1];
    return numbers;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```ts
function swapNumbers(numbers: number[]): number[] {
    [numbers[0], numbers[1]] = [numbers[1], numbers[0]];
    return numbers;
}
```

<!-- tabs:end -->

<!-- end -->
