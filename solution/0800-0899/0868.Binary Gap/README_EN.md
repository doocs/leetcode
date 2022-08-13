# [868. Binary Gap](https://leetcode.com/problems/binary-gap)

[中文文档](/solution/0800-0899/0868.Binary%20Gap/README.md)

## Description

<p>Given a positive integer <code>n</code>, find and return <em>the <strong>longest distance</strong> between any two <strong>adjacent</strong> </em><code>1</code><em>&#39;s in the binary representation of </em><code>n</code><em>. If there are no two adjacent </em><code>1</code><em>&#39;s, return </em><code>0</code><em>.</em></p>

<p>Two <code>1</code>&#39;s are <strong>adjacent</strong> if there are only <code>0</code>&#39;s separating them (possibly no <code>0</code>&#39;s). The <b>distance</b> between two <code>1</code>&#39;s is the absolute difference between their bit positions. For example, the two <code>1</code>&#39;s in <code>&quot;1001&quot;</code> have a distance of 3.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 22
<strong>Output:</strong> 2
<strong>Explanation:</strong> 22 in binary is &quot;10110&quot;.
The first adjacent pair of 1&#39;s is &quot;<u>1</u>0<u>1</u>10&quot; with a distance of 2.
The second adjacent pair of 1&#39;s is &quot;10<u>11</u>0&quot; with a distance of 1.
The answer is the largest of these two distances, which is 2.
Note that &quot;<u>1</u>01<u>1</u>0&quot; is not a valid pair since there is a 1 separating the two 1&#39;s underlined.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 8
<strong>Output:</strong> 0
<strong>Explanation:</strong> 8 in binary is &quot;1000&quot;.
There are not any adjacent pairs of 1&#39;s in the binary representation of 8, so we return 0.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> 5 in binary is &quot;101&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def binaryGap(self, n: int) -> int:
        ans, j = 0, -1
        for i in range(32):
            if n & 1:
                if j != -1:
                    ans = max(ans, i - j)
                j = i
            n >>= 1
        return ans
```

### **Java**

```java
class Solution {
    public int binaryGap(int n) {
        int ans = 0;
        for (int i = 0, j = -1; n != 0; ++i, n >>= 1) {
            if ((n & 1) == 1) {
                if (j != -1) {
                    ans = Math.max(ans, i - j);
                }
                j = i;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function binaryGap(n: number): number {
    let res = 0;
    let j = -1;
    for (let i = 0; n !== 0; i++) {
        if (n & 1) {
            if (j !== -1) {
                res = Math.max(res, i - j);
            }
            j = i;
        }
        n >>= 1;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn binary_gap(mut n: i32) -> i32 {
        let mut res = 0;
        let mut i = 0;
        let mut j = -1;
        while n != 0 {
            if n & 1 == 1 {
                if j != -1 {
                    res = res.max(i - j);
                }
                j = i;
            }
            n >>= 1;
            i += 1;
        }
        res
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int binaryGap(int n) {
        int ans = 0;
        for (int i = 0, j = -1; n; ++i, n >>= 1) {
            if (n & 1) {
                if (j != -1) ans = max(ans, i - j);
                j = i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func binaryGap(n int) int {
	ans := 0
	for i, j := 0, -1; n != 0; i, n = i+1, n>>1 {
		if (n & 1) == 1 {
			if j != -1 && ans < i-j {
				ans = i - j
			}
			j = i
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
