# [2652. Sum Multiples](https://leetcode.com/problems/sum-multiples)

[中文文档](/solution/2600-2699/2652.Sum%20Multiples/README.md)

## Description

<p>Given a positive integer <code>n</code>, find the sum of all integers in the range <code>[1, n]</code> <strong>inclusive</strong> that are divisible by <code>3</code>, <code>5</code>, or <code>7</code>.</p>

<p>Return <em>an integer denoting the sum of all numbers in the given range satisfying&nbsp;the constraint.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> 21
<strong>Explanation:</strong> Numbers in the range [1, 7] that are divisible by , 5, or 7 are 3, 5, 6, 7. The sum of these numbers is 21.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 40
<strong>Explanation:</strong> Numbers in the range [1, 10] that are< divisible by 3, 5, or 7 are 3, 5, 6, 7, 9, 10. The sum of these numbers is 40.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 9
<strong>Output:</strong> 30
<strong>Explanation:</strong> Numbers in the range [1, 9] that are divisible by 3, 5, or 7 are 3, 5, 6, 7, 9. The sum of these numbers is 30.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>3</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumOfMultiples(self, n: int) -> int:
        return sum(x for x in range(1, n + 1) if x % 3 == 0 or x % 5 == 0 or x % 7 == 0)
```

### **Java**

```java
class Solution {
    public int sumOfMultiples(int n) {
        int ans = 0;
        for (int x = 1; x <= n; ++x) {
            if (x % 3 == 0 || x % 5 == 0 || x % 7 == 0) {
                ans += x;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOfMultiples(int n) {
        int ans = 0;
        for (int x = 1; x <= n; ++x) {
            if (x % 3 == 0 || x % 5 == 0 || x % 7 == 0) {
                ans += x;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func sumOfMultiples(n int) (ans int) {
	for x := 1; x <= n; x++ {
		if x%3 == 0 || x%5 == 0 || x%7 == 0 {
			ans += x
		}
	}
	return
}
```

### **TypeScript**

```ts
function sumOfMultiples(n: number): number {
    let ans = 0;
    for (let x = 1; x <= n; ++x) {
        if (x % 3 === 0 || x % 5 === 0 || x % 7 === 0) {
            ans += x;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn sum_of_multiples(n: i32) -> i32 {
        let mut ans = 0;

        for i in 1..=n {
            if i % 3 == 0 || i % 5 == 0 || i % 7 == 0 {
                ans += i;
            }
        }

        ans
    }
}
```

```rust
impl Solution {
    pub fn sum_of_multiples(n: i32) -> i32 {
        (1..=n)
            .filter(|&i| i % 3 == 0 || i % 5 == 0 || i % 7 == 0)
            .sum()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
