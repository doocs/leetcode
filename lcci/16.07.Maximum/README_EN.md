---
comment: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.07.Maximum/README_EN.md
---

# [16.07. Maximum](https://leetcode.cn/problems/maximum-lcci)

[中文文档](/lcci/16.07.Maximum/README.md)

## Description

<p>Write a method that finds the maximum of two numbers. You should not use if-else or any other comparison operator.</p>
<p><strong>Example: </strong></p>
<pre>

<strong>Input: </strong> a = 1, b = 2

<strong>Output: </strong> 2

</pre>

## Solutions

### Solution 1: Bitwise Operation

We can extract the sign bit $k$ of $a-b$. If the sign bit is $1$, it means $a \lt b$; if the sign bit is $0$, it means $a \ge b$.

Then the final result is $a \times (k \oplus 1) + b \times k$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maximum(self, a: int, b: int) -> int:
        k = (int(((a - b) & 0xFFFFFFFFFFFFFFFF) >> 63)) & 1
        return a * (k ^ 1) + b * k
```

```java
class Solution {
    public int maximum(int a, int b) {
        int k = (int) (((long) a - (long) b) >> 63) & 1;
        return a * (k ^ 1) + b * k;
    }
}
```

```cpp
class Solution {
public:
    int maximum(int a, int b) {
        int k = ((static_cast<long long>(a) - static_cast<long long>(b)) >> 63) & 1;
        return a * (k ^ 1) + b * k;
    }
};
```

```go
func maximum(a int, b int) int {
	k := (a - b) >> 63 & 1
	return a*(k^1) + b*k
}
```

```ts
function maximum(a: number, b: number): number {
    const k: number = Number(((BigInt(a) - BigInt(b)) >> BigInt(63)) & BigInt(1));
    return a * (k ^ 1) + b * k;
}
```

```swift
class Solution {
    func maximum(_ a: Int, _ b: Int) -> Int {
        let diff = Int64(a) - Int64(b)
        let k = Int((diff >> 63) & 1)
        return a * (k ^ 1) + b * k
    }
}
```

<!-- tabs:end -->

<!-- end -->
