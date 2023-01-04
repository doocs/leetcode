# [2220. Minimum Bit Flips to Convert Number](https://leetcode.com/problems/minimum-bit-flips-to-convert-number)

[中文文档](/solution/2200-2299/2220.Minimum%20Bit%20Flips%20to%20Convert%20Number/README.md)

## Description

<p>A <strong>bit flip</strong> of a number <code>x</code> is choosing a bit in the binary representation of <code>x</code> and <strong>flipping</strong> it from either <code>0</code> to <code>1</code> or <code>1</code> to <code>0</code>.</p>

<ul>
	<li>For example, for <code>x = 7</code>, the binary representation is <code>111</code> and we may choose any bit (including any leading zeros not shown) and flip it. We can flip the first bit from the right to get <code>110</code>, flip the second bit from the right to get <code>101</code>, flip the fifth bit from the right (a leading zero) to get <code>10111</code>, etc.</li>
</ul>

<p>Given two integers <code>start</code> and <code>goal</code>, return<em> the <strong>minimum</strong> number of <strong>bit flips</strong> to convert </em><code>start</code><em> to </em><code>goal</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> start = 10, goal = 7
<strong>Output:</strong> 3
<strong>Explanation:</strong> The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
- Flip the first bit from the right: 101<u>0</u> -&gt; 101<u>1</u>.
- Flip the third bit from the right: 1<u>0</u>11 -&gt; 1<u>1</u>11.
- Flip the fourth bit from the right: <u>1</u>111 -&gt; <u>0</u>111.
It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> start = 3, goal = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> The binary representation of 3 and 4 are 011 and 100 respectively. We can convert 3 to 4 in 3 steps:
- Flip the first bit from the right: 01<u>1</u> -&gt; 01<u>0</u>.
- Flip the second bit from the right: 0<u>1</u>0 -&gt; 0<u>0</u>0.
- Flip the third bit from the right: <u>0</u>00 -&gt; <u>1</u>00.
It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= start, goal &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minBitFlips(self, start: int, goal: int) -> int:
        t = start ^ goal
        ans = 0
        while t:
            ans += t & 1
            t >>= 1
        return ans
```

### **Java**

```java
class Solution {
    public int minBitFlips(int start, int goal) {
        int t = start ^ goal;
        int ans = 0;
        while (t != 0) {
            ans += t & 1;
            t >>= 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minBitFlips(int start, int goal) {
        int t = start ^ goal;
        int ans = 0;
        while (t) {
            ans += t & 1;
            t >>= 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func minBitFlips(start int, goal int) int {
	t := start ^ goal
	ans := 0
	for t != 0 {
		ans += t & 1
		t >>= 1
	}
	return ans
}
```

### **TypeScript**

```ts
function minBitFlips(start: number, goal: number): number {
    let tmp = start ^ goal;
    let ans = 0;
    while (tmp !== 0) {
        ans += tmp & 1;
        tmp >>= 1;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_bit_flips(start: i32, goal: i32) -> i32 {
        let mut tmp = start ^ goal;
        let mut ans = 0;
        while tmp != 0 {
            ans += tmp & 1;
            tmp >>= 1;
        }
        ans
    }
}
```

### **C**

```c
int minBitFlips(int start, int goal) {
    int tmp = start ^ goal;
    int ans = 0;
    while (tmp) {
        ans += tmp & 1;
        tmp >>= 1;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
