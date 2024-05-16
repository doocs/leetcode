---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/05.01.Insert%20Into%20Bits/README_EN.md
---

<!-- problem:start -->

# [05.01. Insert Into Bits](https://leetcode.cn/problems/insert-into-bits-lcci)

[中文文档](/lcci/05.01.Insert%20Into%20Bits/README.md)

## Description

<!-- description:start -->

<p>You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to insert M into N such that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space to fit all of M. That is, if M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: N = 10000000000, M = 10011, i = 2, j = 6

<strong> Output</strong>: N = 10001001100

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: N = 0, M = 11111, i = 0, j = 4

<strong> Output</strong>: N = 11111

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

First, we clear the bits from the $i$-th to the $j$-th in $N$, then we left shift $M$ by $i$ bits, and finally perform a bitwise OR operation on $M$ and $N$.

The time complexity is $O(\log n)$, where $n$ is the size of $N$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def insertBits(self, N: int, M: int, i: int, j: int) -> int:
        for k in range(i, j + 1):
            N &= ~(1 << k)
        return N | M << i
```

```java
class Solution {
    public int insertBits(int N, int M, int i, int j) {
        for (int k = i; k <= j; ++k) {
            N &= ~(1 << k);
        }
        return N | M << i;
    }
}
```

```cpp
class Solution {
public:
    int insertBits(int N, int M, int i, int j) {
        for (int k = i; k <= j; ++k) {
            N &= ~(1 << k);
        }
        return N | M << i;
    }
};
```

```go
func insertBits(N int, M int, i int, j int) int {
	for k := i; k <= j; k++ {
		N &= ^(1 << k)
	}
	return N | M<<i
}
```

```ts
function insertBits(N: number, M: number, i: number, j: number): number {
    for (let k = i; k <= j; ++k) {
        N &= ~(1 << k);
    }
    return N | (M << i);
}
```

```swift
class Solution {
    func insertBits(_ N: Int, _ M: Int, _ i: Int, _ j: Int) -> Int {
        var result = N

        for k in i...j {
            result &= ~(1 << k)
        }

        return result | (M << i)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
