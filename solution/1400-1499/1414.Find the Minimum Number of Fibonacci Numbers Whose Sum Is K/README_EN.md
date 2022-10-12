# [1414. Find the Minimum Number of Fibonacci Numbers Whose Sum Is K](https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k)

[中文文档](/solution/1400-1499/1414.Find%20the%20Minimum%20Number%20of%20Fibonacci%20Numbers%20Whose%20Sum%20Is%20K/README.md)

## Description

<p>Given an integer&nbsp;<code>k</code>, <em>return the minimum number of Fibonacci numbers whose sum is equal to </em><code>k</code>. The same Fibonacci number can be used multiple times.</p>

<p>The Fibonacci numbers are defined as:</p>

<ul>
	<li><code>F<sub>1</sub> = 1</code></li>
	<li><code>F<sub>2</sub> = 1</code></li>
	<li><code>F<sub>n</sub> = F<sub>n-1</sub> + F<sub>n-2</sub></code> for <code>n &gt; 2.</code></li>
</ul>
It is guaranteed that for the given constraints we can always find such Fibonacci numbers that sum up to <code>k</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 7
<strong>Output:</strong> 2 
<strong>Explanation:</strong> The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ... 
For k = 7 we can use 2 + 5 = 7.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 10
<strong>Output:</strong> 2 
<strong>Explanation:</strong> For k = 10 we can use 2 + 8 = 10.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 19
<strong>Output:</strong> 3 
<strong>Explanation:</strong> For k = 19 we can use 1 + 5 + 13 = 19.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMinFibonacciNumbers(self, k: int) -> int:
        def dfs(k):
            if k < 2:
                return k
            a = b = 1
            while b <= k:
                a, b = b, a + b
            return 1 + dfs(k - a)

        return dfs(k)
```

### **Java**

```java
class Solution {

    public int findMinFibonacciNumbers(int k) {
        if (k < 2) {
            return k;
        }
        int a = 1, b = 1;
        while (b <= k) {
            b = a + b;
            a = b - a;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
}
```

### **TypeScript**

```ts
const arr = [
    1836311903, 1134903170, 701408733, 433494437, 267914296, 165580141,
    102334155, 63245986, 39088169, 24157817, 14930352, 9227465, 5702887,
    3524578, 2178309, 1346269, 832040, 514229, 317811, 196418, 121393, 75025,
    46368, 28657, 17711, 10946, 6765, 4181, 2584, 1597, 987, 610, 377, 233, 144,
    89, 55, 34, 21, 13, 8, 5, 3, 2, 1,
];

function findMinFibonacciNumbers(k: number): number {
    let res = 0;
    for (const num of arr) {
        if (k >= num) {
            k -= num;
            res++;
            if (k === 0) {
                break;
            }
        }
    }
    return res;
}
```

### **Rust**

```rust
const FIB: [i32; 45] = [
    1836311903, 1134903170, 701408733, 433494437, 267914296, 165580141, 102334155, 63245986,
    39088169, 24157817, 14930352, 9227465, 5702887, 3524578, 2178309, 1346269, 832040, 514229,
    317811, 196418, 121393, 75025, 46368, 28657, 17711, 10946, 6765, 4181, 2584, 1597, 987, 610,
    377, 233, 144, 89, 55, 34, 21, 13, 8, 5, 3, 2, 1,
];

impl Solution {
    pub fn find_min_fibonacci_numbers(mut k: i32) -> i32 {
        let mut res = 0;
        for &i in FIB.into_iter() {
            if k >= i {
                k -= i;
                res += 1;
                if k == 0 {
                    break;
                }
            }
        }
        res
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMinFibonacciNumbers(int k) {
        if (k < 2) return k;
        int a = 1, b = 1;
        while (b <= k) {
            b = a + b;
            a = b - a;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
};
```

### **Go**

```go
func findMinFibonacciNumbers(k int) int {
	if k < 2 {
		return k
	}
	a, b := 1, 1
	for b <= k {
		a, b = b, a+b
	}
	return 1 + findMinFibonacciNumbers(k-a)
}
```

### **...**

```

```

<!-- tabs:end -->
