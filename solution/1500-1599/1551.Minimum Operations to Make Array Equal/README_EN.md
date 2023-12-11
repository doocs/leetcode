# [1551. Minimum Operations to Make Array Equal](https://leetcode.com/problems/minimum-operations-to-make-array-equal)

[中文文档](/solution/1500-1599/1551.Minimum%20Operations%20to%20Make%20Array%20Equal/README.md)

## Description

<p>You have an array <code>arr</code> of length <code>n</code> where <code>arr[i] = (2 * i) + 1</code> for all valid values of <code>i</code> (i.e.,&nbsp;<code>0 &lt;= i &lt; n</code>).</p>

<p>In one operation, you can select two indices <code>x</code> and <code>y</code> where <code>0 &lt;= x, y &lt; n</code> and subtract <code>1</code> from <code>arr[x]</code> and add <code>1</code> to <code>arr[y]</code> (i.e., perform <code>arr[x] -=1 </code>and <code>arr[y] += 1</code>). The goal is to make all the elements of the array <strong>equal</strong>. It is <strong>guaranteed</strong> that all the elements of the array can be made equal using some operations.</p>

<p>Given an integer <code>n</code>, the length of the array, return <em>the minimum number of operations</em> needed to make all the elements of arr equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> arr = [1, 3, 5]
First operation choose x = 2 and y = 0, this leads arr to be [2, 3, 4]
In the second operation choose x = 2 and y = 0 again, thus arr = [3, 3, 3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

**Solution 1: Mathematics**

According to the problem description, the array $arr$ is an arithmetic sequence with the first term as $1$ and the common difference as $2$. Therefore, the sum of the first $n$ terms of the array is:

$$
\begin{aligned}
S_n &= \frac{n}{2} \times (a_1 + a_n) \\
&= \frac{n}{2} \times (1 + (2n - 1)) \\
&= n^2
\end{aligned}
$$

Since in one operation, one number is decreased by one and another number is increased by one, the sum of all elements in the array remains unchanged. Therefore, when all elements in the array are equal, the value of each element is $S_n / n = n$. Hence, the minimum number of operations required to make all elements in the array equal is:

$$
\sum_{i=0}^{\frac{n}{2}} (n - (2i + 1))
$$

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, n: int) -> int:
        return sum(n - (i << 1 | 1) for i in range(n >> 1))
```

### **Java**

```java
class Solution {
    public int minOperations(int n) {
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            ans += n - (i << 1 | 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(int n) {
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            ans += n - (i << 1 | 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func minOperations(n int) (ans int) {
	for i := 0; i < n>>1; i++ {
		ans += n - (i<<1 | 1)
	}
	return
}
```

### **TypeScript**

```ts
function minOperations(n: number): number {
    let ans = 0;
    for (let i = 0; i < n >> 1; ++i) {
        ans += n - ((i << 1) | 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
