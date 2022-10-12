# [2169. Count Operations to Obtain Zero](https://leetcode.com/problems/count-operations-to-obtain-zero)

[中文文档](/solution/2100-2199/2169.Count%20Operations%20to%20Obtain%20Zero/README.md)

## Description

<p>You are given two <strong>non-negative</strong> integers <code>num1</code> and <code>num2</code>.</p>

<p>In one <strong>operation</strong>, if <code>num1 &gt;= num2</code>, you must subtract <code>num2</code> from <code>num1</code>, otherwise subtract <code>num1</code> from <code>num2</code>.</p>

<ul>
	<li>For example, if <code>num1 = 5</code> and <code>num2 = 4</code>, subtract <code>num2</code> from <code>num1</code>, thus obtaining <code>num1 = 1</code> and <code>num2 = 4</code>. However, if <code>num1 = 4</code> and <code>num2 = 5</code>, after one operation, <code>num1 = 4</code> and <code>num2 = 1</code>.</li>
</ul>

<p>Return <em>the <strong>number of operations</strong> required to make either</em> <code>num1 = 0</code> <em>or</em> <code>num2 = 0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = 2, num2 = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
- Operation 1: num1 = 2, num2 = 3. Since num1 &lt; num2, we subtract num1 from num2 and get num1 = 2, num2 = 3 - 2 = 1.
- Operation 2: num1 = 2, num2 = 1. Since num1 &gt; num2, we subtract num2 from num1.
- Operation 3: num1 = 1, num2 = 1. Since num1 == num2, we subtract num2 from num1.
Now num1 = 0 and num2 = 1. Since num1 == 0, we do not need to perform any further operations.
So the total number of operations required is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = 10, num2 = 10
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
- Operation 1: num1 = 10, num2 = 10. Since num1 == num2, we subtract num2 from num1 and get num1 = 10 - 10 = 0.
Now num1 = 0 and num2 = 10. Since num1 == 0, we are done.
So the total number of operations required is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num1, num2 &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countOperations(self, num1: int, num2: int) -> int:
        ans = 0
        while num1 and num2:
            if num1 >= num2:
                num1, num2 = num2, num1
            num2 -= num1
            ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
            ++ans;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countOperations(num1: number, num2: number): number {
    let ans = 0;
    while (num1 && num2) {
        [num1, num2] = [Math.min(num1, num2), Math.abs(num1 - num2)];
        ans++;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 && num2) {
            if (num1 > num2) swap(num1, num2);
            num2 -= num1;
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countOperations(num1 int, num2 int) int {
	ans := 0
	for num1 != 0 && num2 != 0 {
		if num1 > num2 {
			num1, num2 = num2, num1
		}
		num2 -= num1
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
