---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.05.Factorial%20Zeros/README_EN.md
---

<!-- problem:start -->

# [16.05. Factorial Zeros](https://leetcode.cn/problems/factorial-zeros-lcci)

[中文文档](/lcci/16.05.Factorial%20Zeros/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

The problem is actually asking for the number of factors of $5$ in $[1,n]$.

Let's take $130$ as an example:

1. Divide $130$ by $5$ for the first time, and get $26$, which means there are $26$ numbers containing a factor of $5$.
2. Divide $26$ by $5$ for the second time, and get $5$, which means there are $5$ numbers containing a factor of $5^2$.
3. Divide $5$ by $5$ for the third time, and get $1$, which means there is $1$ number containing a factor of $5^3$.
4. Add up all the counts to get the total number of factors of $5$ in $[1,n]$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trailingZeroes(self, n: int) -> int:
        ans = 0
        while n:
            n //= 5
            ans += n
        return ans
```

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

```ts
function trailingZeroes(n: number): number {
    let ans = 0;
    while (n) {
        n = Math.floor(n / 5);
        ans += n;
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func trailingZeroes(_ n: Int) -> Int {
        var count = 0
        var number = n
        while number > 0 {
            number /= 5
            count += number
        }
        return count
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
