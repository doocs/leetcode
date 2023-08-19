# [16.05. Factorial Zeros](https://leetcode.cn/problems/factorial-zeros-lcci)

[中文文档](/lcci/16.05.Factorial%20Zeros/README.md)

## Description

<p>Write an algorithm which computes the number of trailing zeros in n factorial.</p>
<p><strong>Example 1:</strong></p>
<pre>

<strong>Input:</strong> 3

<strong>Output:</strong> 0

<strong>Explanation:</strong>&nbsp;3! = 6, no trailing zero.</pre>

<p><strong>Example&nbsp;2:</strong></p>
<pre>

<strong>Input:</strong> 5

<strong>Output:</strong> 1

<strong>Explanation:</strong>&nbsp;5! = 120, one trailing zero.</pre>

<p><b>Note:&nbsp;</b>Your solution should be in logarithmic time complexity.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def trailingZeroes(self, n: int) -> int:
        ans = 0
        while n:
            n //= 5
            ans += n
        return ans
```

### **Java**

```java
class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int trailingZeroes(int n) {
        int ans = 0;
        while (n) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
};
```

### **Go**

```go
func trailingZeroes(n int) int {
	ans := 0
	for n > 0 {
		n /= 5
		ans += n
	}
	return ans
}
```

### **TypeScript**

```ts
function trailingZeroes(n: number): number {
    let ans = 0;
    while (n > 0) {
        n = Math.floor(n / 5);
        ans += n;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
