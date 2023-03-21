# [1999. Smallest Greater Multiple Made of Two Digits](https://leetcode.com/problems/smallest-greater-multiple-made-of-two-digits)

[中文文档](/solution/1900-1999/1999.Smallest%20Greater%20Multiple%20Made%20of%20Two%20Digits/README.md)

## Description

<p>Given three integers, <code>k</code>, <code>digit1</code>, and <code>digit2</code>, you want to find the <strong>smallest</strong> integer that is:</p>

<ul>
	<li><strong>Larger</strong> than <code>k</code>,</li>
	<li>A <strong>multiple</strong> of <code>k</code>, and</li>
	<li>Comprised of <strong>only</strong> the digits <code>digit1</code> and/or <code>digit2</code>.</li>
</ul>

<p>Return <em>the <strong>smallest</strong> such integer. If no such integer exists or the integer exceeds the limit of a signed 32-bit integer (</em><code>2<sup>31</sup> - 1</code><em>), return </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 2, digit1 = 0, digit2 = 2
<strong>Output:</strong> 20
<strong>Explanation:</strong>
20 is the first integer larger than 2, a multiple of 2, and comprised of only the digits 0 and/or 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 3, digit1 = 4, digit2 = 2
<strong>Output:</strong> 24
<strong>Explanation:</strong>
24 is the first integer larger than 3, a multiple of 3, and comprised of only the digits 4 and/or 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 2, digit1 = 0, digit2 = 0
<strong>Output:</strong> -1
<strong>Explanation:
</strong>No integer meets the requirements so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>0 &lt;= digit1 &lt;= 9</code></li>
	<li><code>0 &lt;= digit2 &lt;= 9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findInteger(self, k: int, digit1: int, digit2: int) -> int:
        if digit1 == 0 and digit2 == 0:
            return -1
        if digit1 > digit2:
            return self.findInteger(k, digit2, digit1)
        q = deque([0])
        while 1:
            x = q.popleft()
            if x > 2**31 - 1:
                return -1
            if x > k and x % k == 0:
                return x
            q.append(x * 10 + digit1)
            if digit1 != digit2:
                q.append(x * 10 + digit2)
```

### **Java**

```java
class Solution {
    public int findInteger(int k, int digit1, int digit2) {
        if (digit1 == 0 && digit2 == 0) {
            return -1;
        }
        if (digit1 > digit2) {
            return findInteger(k, digit2, digit1);
        }
        Deque<Long> q = new ArrayDeque<>();
        q.offer(0L);
        while (true) {
            long x = q.poll();
            if (x > Integer.MAX_VALUE) {
                return -1;
            }
            if (x > k && x % k == 0) {
                return (int) x;
            }
            q.offer(x * 10 + digit1);
            if (digit1 != digit2) {
                q.offer(x * 10 + digit2);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findInteger(int k, int digit1, int digit2) {
        if (digit1 == 0 && digit2 == 0) {
            return -1;
        }
        if (digit1 > digit2) {
            swap(digit1, digit2);
        }
        queue<long long> q{{0}};
        while (1) {
            long long x = q.front();
            q.pop();
            if (x > INT_MAX) {
                return -1;
            }
            if (x > k && x % k == 0) {
                return x;
            }
            q.emplace(x * 10 + digit1);
            if (digit1 != digit2) {
                q.emplace(x * 10 + digit2);
            }
        }
    }
};
```

### **Go**

```go
func findInteger(k int, digit1 int, digit2 int) int {
	if digit1 == 0 && digit2 == 0 {
		return -1
	}
	if digit1 > digit2 {
		digit1, digit2 = digit2, digit1
	}
	q := []int{0}
	for {
		x := q[0]
		q = q[1:]
		if x > math.MaxInt32 {
			return -1
		}
		if x > k && x%k == 0 {
			return x
		}
		q = append(q, x*10+digit1)
		if digit1 != digit2 {
			q = append(q, x*10+digit2)
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
