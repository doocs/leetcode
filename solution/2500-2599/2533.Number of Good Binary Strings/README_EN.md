# [2533. Number of Good Binary Strings](https://leetcode.com/problems/number-of-good-binary-strings)

[中文文档](/solution/2500-2599/2533.Number%20of%20Good%20Binary%20Strings/README.md)

## Description

<p>You are given four integers <code>minLength</code>, <code>maxLength</code>, <code>oneGroup</code> and <code>zeroGroup</code>.</p>

<p>A binary string is <strong>good</strong> if it satisfies the following conditions:</p>

<ul>
	<li>The length of the string is in the range <code>[minLength, maxLength]</code>.</li>
	<li>The size of each block of consecutive <code>1</code>&#39;s is a multiple of <code>oneGroup</code>.
	<ul>
		<li>For example in a binary string <code>00<u>11</u>0<u>1111</u>00</code> sizes of each block of consecutive ones are <code>[2,4]</code>.</li>
	</ul>
	</li>
	<li>The size of each block of consecutive <code>0</code>&#39;s is a multiple of <code>zeroGroup</code>.
	<ul>
		<li>For example, in a binary string <code><u>00</u>11<u>0</u>1111<u>00</u></code> sizes of each block of consecutive ones are <code>[2,1,2]</code>.</li>
	</ul>
	</li>
</ul>

<p>Return <em>the number of <strong>good</strong> binary strings</em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong> that <code>0</code> is considered a multiple of all the numbers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> minLength = 2, maxLength = 3, oneGroup = 1, zeroGroup = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 good binary strings in this example: &quot;00&quot;, &quot;11&quot;, &quot;001&quot;, &quot;100&quot;, and &quot;111&quot;.
It can be proven that there are only 5 good strings satisfying all conditions.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> minLength = 4, maxLength = 4, oneGroup = 4, zeroGroup = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only 1 good binary string in this example: &quot;1111&quot;.
It can be proven that there is only 1 good string satisfying all conditions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= minLength &lt;= maxLength &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= oneGroup, zeroGroup &lt;= maxLength</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def goodBinaryStrings(self, minLength: int, maxLength: int, oneGroup: int, zeroGroup: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * maxLength
        for i in range(1, len(f)):
            if i - oneGroup >= 0:
                f[i] += f[i - oneGroup]
            if i - zeroGroup >= 0:
                f[i] += f[i - zeroGroup]
            f[i] %= mod
        return sum(f[minLength:]) % mod
```

### **Java**

```java
class Solution {
    public int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[maxLength + 1];
        f[0] = 1;
        for (int i = 1; i <= maxLength; ++i) {
            if (i - oneGroup >= 0) {
                f[i] = (f[i] + f[i - oneGroup]) % mod;
            }
            if (i - zeroGroup >= 0) {
                f[i] = (f[i] + f[i - zeroGroup]) % mod;
            }
        }
        int ans = 0;
        for (int i = minLength; i <= maxLength; ++i) {
            ans = (ans + f[i]) % mod;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        const int mod = 1e9 + 7;
        int f[maxLength + 1];
        memset(f, 0, sizeof f);
        f[0] = 1;
        for (int i = 1; i <= maxLength; ++i) {
            if (i - oneGroup >= 0) {
                f[i] = (f[i] + f[i - oneGroup]) % mod;
            }
            if (i - zeroGroup >= 0) {
                f[i] = (f[i] + f[i - zeroGroup]) % mod;
            }
        }
        int ans = 0;
        for (int i = minLength; i <= maxLength; ++i) {
            ans = (ans + f[i]) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func goodBinaryStrings(minLength int, maxLength int, oneGroup int, zeroGroup int) (ans int) {
	const mod int = 1e9 + 7
	f := make([]int, maxLength+1)
	f[0] = 1
	for i := 1; i <= maxLength; i++ {
		if i-oneGroup >= 0 {
			f[i] += f[i-oneGroup]
		}
		if i-zeroGroup >= 0 {
			f[i] += f[i-zeroGroup]
		}
		f[i] %= mod
	}
	for _, v := range f[minLength:] {
		ans = (ans + v) % mod
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
