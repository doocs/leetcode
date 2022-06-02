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

<!-- 这里可写通用的实现逻辑 -->

异或运算。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def swapNumbers(self, numbers: List[int]) -> List[int]:
        numbers[0] ^= numbers[1]
        numbers[1] ^= numbers[0]
        numbers[0] ^= numbers[1]
        return numbers
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **TypeScript**

```ts
function swapNumbers(numbers: number[]): number[] {
    numbers[0] ^= numbers[1];
    numbers[1] ^= numbers[0];
    numbers[0] ^= numbers[1];
    return numbers;
}
```

```ts
function swapNumbers(numbers: number[]): number[] {
    [numbers[0], numbers[1]] = [numbers[1], numbers[0]];
    return numbers;
}
```

### **C++**

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

### **Go**

```go
func swapNumbers(numbers []int) []int {
	numbers[0] ^= numbers[1]
	numbers[1] ^= numbers[0]
	numbers[0] ^= numbers[1]
	return numbers
}
```

### **...**

```

```

<!-- tabs:end -->
