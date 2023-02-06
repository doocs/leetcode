# [1806. Minimum Number of Operations to Reinitialize a Permutation](https://leetcode.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation)

[中文文档](/solution/1800-1899/1806.Minimum%20Number%20of%20Operations%20to%20Reinitialize%20a%20Permutation/README.md)

## Description

<p>You are given an <strong>even</strong> integer <code>n</code>​​​​​​. You initially have a permutation <code>perm</code> of size <code>n</code>​​ where <code>perm[i] == i</code>​ <strong>(0-indexed)</strong>​​​​.</p>

<p>In one operation, you will create a new array <code>arr</code>, and for each <code>i</code>:</p>

<ul>
	<li>If <code>i % 2 == 0</code>, then <code>arr[i] = perm[i / 2]</code>.</li>
	<li>If <code>i % 2 == 1</code>, then <code>arr[i] = perm[n / 2 + (i - 1) / 2]</code>.</li>
</ul>

<p>You will then assign <code>arr</code>​​​​ to <code>perm</code>.</p>

<p>Return <em>the minimum <strong>non-zero</strong> number of operations you need to perform on </em><code>perm</code><em> to return the permutation to its initial value.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> perm = [0,1] initially.
After the 1<sup>st</sup> operation, perm = [0,1]
So it takes only 1 operation.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> perm = [0,1,2,3] initially.
After the 1<sup>st</sup> operation, perm = [0,2,1,3]
After the 2<sup>nd</sup> operation, perm = [0,1,2,3]
So it takes only 2 operations.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>n</code>​​​​​​ is even.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reinitializePermutation(self, n: int) -> int:
        ans, i = 0, 1
        while 1:
            ans += 1
            if i < n >> 1:
                i <<= 1
            else:
                i = (i - (n >> 1)) << 1 | 1
            if i == 1:
                return ans
```

### **Java**

```java
class Solution {
    public int reinitializePermutation(int n) {
        int ans = 0;
        for (int i = 1;;) {
            ++ans;
            if (i < (n >> 1)) {
                i <<= 1;
            } else {
                i = (i - (n >> 1)) << 1 | 1;
            }
            if (i == 1) {
                return ans;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int reinitializePermutation(int n) {
        int ans = 0;
        for (int i = 1; ; ) {
            ++ans;
            if (i < (n >> 1)) {
                i <<= 1;
            } else {
                i = (i - (n >> 1)) << 1 | 1;
            }
            if (i == 1) {
                return ans;
            }
        }
    }
};
```

### **Go**

```go
func reinitializePermutation(n int) (ans int) {
	for i := 1; ; {
		ans++
		if i < (n >> 1) {
			i <<= 1
		} else {
			i = (i-(n>>1))<<1 | 1
		}
		if i == 1 {
			return ans
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
