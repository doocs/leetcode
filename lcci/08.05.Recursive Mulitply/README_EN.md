---
comment: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.05.Recursive%20Mulitply/README_EN.md
---

# [08.05. Recursive Mulitply](https://leetcode.cn/problems/recursive-mulitply-lcci)

[中文文档](/lcci/08.05.Recursive%20Mulitply/README.md)

## Description

<p>Write a recursive function to multiply two positive integers without using the * operator. You can use addition, subtraction, and bit shifting, but you should minimize the number of those operations.</p>
<p><strong>Example 1:</strong></p>
<pre>

<strong> Input</strong>: A = 1, B = 10

<strong> Output</strong>: 10

</pre>
<p><strong>Example 2:</strong></p>
<pre>

<strong> Input</strong>: A = 3, B = 4

<strong> Output</strong>: 12

</pre>
<p><strong>Note:</strong></p>
<ol>
	<li>The result will not overflow.</li>
</ol>

## Solutions

### Solution 1: Recursion + Bit Manipulation

First, we check if $B$ is $1$. If it is, we directly return $A$.

Otherwise, we check if $B$ is an odd number. If it is, we can right shift $B$ by one bit, then recursively call the function, and finally left shift the result by one bit and add $A$. If not, we can right shift $B$ by one bit, then recursively call the function, and finally left shift the result by one bit.

The time complexity is $O(\log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the size of $B$.

<!-- tabs:start -->

```python
class Solution:
    def multiply(self, A: int, B: int) -> int:
        if B == 1:
            return A
        if B & 1:
            return (self.multiply(A, B >> 1) << 1) + A
        return self.multiply(A, B >> 1) << 1
```

```java
class Solution {
    public int multiply(int A, int B) {
        if (B == 1) {
            return A;
        }
        if ((B & 1) == 1) {
            return (multiply(A, B >> 1) << 1) + A;
        }
        return multiply(A, B >> 1) << 1;
    }
}
```

```cpp
class Solution {
public:
    int multiply(int A, int B) {
        if (B == 1) {
            return A;
        }
        if ((B & 1) == 1) {
            return (multiply(A, B >> 1) << 1) + A;
        }
        return multiply(A, B >> 1) << 1;
    }
};
```

```go
func multiply(A int, B int) int {
	if B == 1 {
		return A
	}
	if B&1 == 1 {
		return (multiply(A, B>>1) << 1) + A
	}
	return multiply(A, B>>1) << 1
}
```

```ts
function multiply(A: number, B: number): number {
    if (B === 1) {
        return A;
    }
    if ((B & 1) === 1) {
        return (multiply(A, B >> 1) << 1) + A;
    }
    return multiply(A, B >> 1) << 1;
}
```

```rust
impl Solution {
    pub fn multiply(a: i32, b: i32) -> i32 {
        if b == 1 {
            return a;
        }
        if (b & 1) == 1 {
            return (Self::multiply(a, b >> 1) << 1) + a;
        }
        Self::multiply(a, b >> 1) << 1
    }
}
```

```swift
class Solution {
    func multiply(_ A: Int, _ B: Int) -> Int {
        if B == 1 {
            return A
        }
        if (B & 1) == 1 {
            return (multiply(A, B >> 1) << 1) + A
        }
        return multiply(A, B >> 1) << 1
    }
}
```

<!-- tabs:end -->

<!-- end -->
