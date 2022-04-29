# [16.01. Swap Numbers](https://leetcode.cn/problems/swap-numbers-lcci)

[中文文档](/lcci/16.01.Swap%20Numbers/README.md)

## Description

<p>Write a function to swap a number in place (that is, without temporary vari&shy; ables).</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input:</strong> numbers = [1,2]

<strong>Output:</strong> [2,1]

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>numbers.length == 2</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def swapNumbers(self, numbers: List[int]) -> List[int]:
        numbers[0] ^= numbers[1]
        numbers[1] ^= numbers[0]
        numbers[0] ^= numbers[1]
        return numbers
```

### **Java**

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

## **TypeScript**

```ts
function swapNumbers(numbers: number[]): number[] {
    numbers[0] ^= numbers[1];
    numbers[1] ^= numbers[0];
    numbers[0] ^= numbers[1];
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
