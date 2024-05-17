---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.01.Swap%20Numbers/README_EN.md
---

<!-- problem:start -->

# [16.01. Swap Numbers](https://leetcode.cn/problems/swap-numbers-lcci)

[中文文档](/lcci/16.01.Swap%20Numbers/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bitwise Operation

We can use the XOR operation $\oplus$ to implement the swap of two numbers.

The XOR operation has the following three properties:

-   Any number XORed with $0$ remains unchanged, i.e., $a \oplus 0=a$.
-   Any number XORed with itself results in $0$, i.e., $a \oplus a=0$.
-   The XOR operation satisfies the commutative and associative laws, i.e., $a \oplus b \oplus a=b \oplus a \oplus a=b \oplus (a \oplus a)=b \oplus 0=b$.

Therefore, we can perform the following operations on two numbers $a$ and $b$ in the array $numbers$:

-   $a=a \oplus b$, now $a$ stores the XOR result of the two numbers;
-   $b=a \oplus b$, now $b$ stores the original value of $a$;
-   $a=a \oplus b$, now $a$ stores the original value of $b$;

In this way, we can swap two numbers without using a temporary variable.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def swapNumbers(self, numbers: List[int]) -> List[int]:
        numbers[0] ^= numbers[1]
        numbers[1] ^= numbers[0]
        numbers[0] ^= numbers[1]
        return numbers
```

#### Java

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

#### C++

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

#### Go

```go
func swapNumbers(numbers []int) []int {
	numbers[0] ^= numbers[1]
	numbers[1] ^= numbers[0]
	numbers[0] ^= numbers[1]
	return numbers
}
```

#### TypeScript

```ts
function swapNumbers(numbers: number[]): number[] {
    numbers[0] ^= numbers[1];
    numbers[1] ^= numbers[0];
    numbers[0] ^= numbers[1];
    return numbers;
}
```

#### Swift

```swift
class Solution {
    func swapNumbers(_ numbers: [Int]) -> [Int] {
        var numbers = numbers
        numbers[0] ^= numbers[1]
        numbers[1] ^= numbers[0]
        numbers[0] ^= numbers[1]
        return numbers
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
