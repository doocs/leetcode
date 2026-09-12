---
comments: true
difficulty: Easy
rating: 1257
source: Weekly Contest 281 Q1
tags:
    - Math
    - Simulation
---

<!-- problem:start -->

# [2180. Count Integers With Even Digit Sum](https://leetcode.com/problems/count-integers-with-even-digit-sum)

[中文文档](/solution/2100-2199/2180.Count%20Integers%20With%20Even%20Digit%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given a positive integer <code>num</code>, return <em>the number of positive integers <strong>less than or equal to</strong></em> <code>num</code> <em>whose digit sums are <strong>even</strong></em>.</p>

<p>The <strong>digit sum</strong> of a positive integer is the sum of all its digits.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The only integers less than or equal to 4 whose digit sums are even are 2 and 4.    
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 30
<strong>Output:</strong> 14
<strong>Explanation:</strong>
The 14 integers less than or equal to 30 whose digit sums are even are
2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, and 28.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Count integers in $[1,\textit{num}]$ whose digits sum to an even number. $\textit{num}\le 1000$, so we may sum digits of every value.
>
> Repeatedly add $x\bmod 10$ and increment when the sum is even.
>
> The extra factor is the number of digits.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countEven(self, num: int) -> int:
        ans = 0
        for x in range(1, num + 1):
            s = 0
            while x:
                s += x % 10
                x //= 10
            ans += s % 2 == 0
        return ans
```

#### Java

```java
class Solution {
    public int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int s = 0;
            for (int x = i; x > 0; x /= 10) {
                s += x % 10;
            }
            if (s % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int s = 0;
            for (int x = i; x; x /= 10) {
                s += x % 10;
            }
            ans += s % 2 == 0;
        }
        return ans;
    }
};
```

#### Go

```go
func countEven(num int) (ans int) {
	for i := 1; i <= num; i++ {
		s := 0
		for x := i; x > 0; x /= 10 {
			s += x % 10
		}
		if s%2 == 0 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countEven(num: number): number {
    let ans = 0;
    for (let i = 1; i <= num; ++i) {
        let s = 0;
        for (let x = i; x; x = Math.floor(x / 10)) {
            s += x % 10;
        }
        if (s % 2 == 0) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 is linear in $\textit{num}$. Among every ten consecutive integers exactly five have an even digit sum, so full decades can be closed in $O(1)$.
>
> Decades contribute $\lfloor\textit{num}/10\rfloor\times 5$, minus one to drop $0$. The leftover units depend on the parity of the higher digit sum $s$, which shifts the closed-form count.
>
> Only the digits of $\textit{num}/10$ are walked.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countEven(self, num: int) -> int:
        ans = num // 10 * 5 - 1
        x, s = num // 10, 0
        while x:
            s += x % 10
            x //= 10
        ans += (num % 10 + 2 - (s & 1)) >> 1
        return ans
```

#### Java

```java
class Solution {
    public int countEven(int num) {
        int ans = num / 10 * 5 - 1;
        int s = 0;
        for (int x = num / 10; x > 0; x /= 10) {
            s += x % 10;
        }
        ans += (num % 10 + 2 - (s & 1)) >> 1;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countEven(int num) {
        int ans = num / 10 * 5 - 1;
        int s = 0;
        for (int x = num / 10; x > 0; x /= 10) {
            s += x % 10;
        }
        ans += (num % 10 + 2 - (s & 1)) >> 1;
        return ans;
    }
};
```

#### Go

```go
func countEven(num int) (ans int) {
	ans = num/10*5 - 1
	s := 0
	for x := num / 10; x > 0; x /= 10 {
		s += x % 10
	}
	ans += (num%10 + 2 - (s & 1)) >> 1
	return
}
```

#### TypeScript

```ts
function countEven(num: number): number {
    let ans = Math.floor(num / 10) * 5 - 1;
    let s = 0;
    for (let x = Math.floor(num / 10); x; x = Math.floor(x / 10)) {
        s += x % 10;
    }
    ans += ((num % 10) + 2 - (s & 1)) >> 1;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
