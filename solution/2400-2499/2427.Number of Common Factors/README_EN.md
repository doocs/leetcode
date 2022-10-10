# [2427. Number of Common Factors](https://leetcode.com/problems/number-of-common-factors)

[中文文档](/solution/2400-2499/2427.Number%20of%20Common%20Factors/README.md)

## Description

<p>Given two positive integers <code>a</code> and <code>b</code>, return <em>the number of <strong>common</strong> factors of </em><code>a</code><em> and </em><code>b</code>.</p>

<p>An integer <code>x</code> is a <strong>common factor</strong> of <code>a</code> and <code>b</code> if <code>x</code> divides both <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 12, b = 6
<strong>Output:</strong> 4
<strong>Explanation:</strong> The common factors of 12 and 6 are 1, 2, 3, 6.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 25, b = 30
<strong>Output:</strong> 2
<strong>Explanation:</strong> The common factors of 25 and 30 are 1, 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def commonFactors(self, a: int, b: int) -> int:
        return sum(a % i == 0 and b % i == 0 for i in range(1, 1001))
```

### **Java**

```java
class Solution {
    public int commonFactors(int a, int b) {
        int ans = 0, n = Math.min(a, b);
        for (int i = 1; i <= n; ++i) {
            if (a % i == 0 && b % i == 0) {
                ++ans;
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
    int commonFactors(int a, int b) {
        int ans = 0;
        int n = min(a, b);
        for (int i = 1; i <= n; ++i) {
            if (a % i == 0 && b % i == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func commonFactors(a int, b int) int {
	ans := 0
	for i := 1; i <= a && i <= b; i++ {
		if a%i == 0 && b%i == 0 {
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function commonFactors(a: number, b: number): number {
    const n = Math.min(a, b);
    let ans = 0;
    for (let i = 1; i <= n; i++) {
        if(a % i == 0 && b % i == 0) ans += 1;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
