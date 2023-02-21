# [1318. Minimum Flips to Make a OR b Equal to c](https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c)

[中文文档](/solution/1300-1399/1318.Minimum%20Flips%20to%20Make%20a%20OR%20b%20Equal%20to%20c/README.md)

## Description

<p>Given 3 positives numbers <code>a</code>, <code>b</code> and <code>c</code>. Return the minimum flips required in some bits of <code>a</code> and <code>b</code> to make (&nbsp;<code>a</code> OR <code>b</code> == <code>c</code>&nbsp;). (bitwise OR operation).<br />

Flip operation&nbsp;consists of change&nbsp;<strong>any</strong>&nbsp;single bit 1 to 0 or change the bit 0 to 1&nbsp;in their binary representation.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1318.Minimum%20Flips%20to%20Make%20a%20OR%20b%20Equal%20to%20c/images/sample_3_1676.png" style="width: 260px; height: 87px;" /></p>

<pre>

<strong>Input:</strong> a = 2, b = 6, c = 5

<strong>Output:</strong> 3

<strong>Explanation: </strong>After flips a = 1 , b = 4 , c = 5 such that (<code>a</code> OR <code>b</code> == <code>c</code>)</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> a = 4, b = 2, c = 7

<strong>Output:</strong> 1

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> a = 1, b = 2, c = 3

<strong>Output:</strong> 0

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= a &lt;= 10^9</code></li>
    <li><code>1 &lt;= b&nbsp;&lt;= 10^9</code></li>
    <li><code>1 &lt;= c&nbsp;&lt;= 10^9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minFlips(self, a: int, b: int, c: int) -> int:
        ans = 0
        for i in range(30):
            x, y, z = a >> i & 1, b >> i & 1, c >> i & 1
            if x | y != z:
                ans += 2 if x == 1 and y == 1 else 1
        return ans
```

### **Java**

```java
class Solution {
    public int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 30; ++i) {
            int x = a >> i & 1, y = b >> i & 1, z = c >> i & 1;
            if ((x | y) != z) {
                ans += x == 1 && y == 1 ? 2 : 1;
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
    int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 30; ++i) {
            int x = a >> i & 1, y = b >> i & 1, z = c >> i & 1;
            if ((x | y) != z) {
                ans += x == 1 && y == 1 ? 2 : 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minFlips(a int, b int, c int) (ans int) {
	for i := 0; i < 30; i++ {
		x, y, z := a>>i&1, b>>i&1, c>>i&1
		if (x | y) != z {
			if x == 1 && y == 1 {
				ans += 2
			} else {
				ans++
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
