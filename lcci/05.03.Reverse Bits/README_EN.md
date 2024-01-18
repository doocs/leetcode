# [05.03. Reverse Bits](https://leetcode.cn/problems/reverse-bits-lcci)

[中文文档](/lcci/05.03.Reverse%20Bits/README.md)

## Description

<p>You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest sequence of 1s you could create.</p>
<p><strong>Example 1: </strong></p>
<pre>

<strong>Input:</strong> <code>num</code> = 1775(11011101111<sub>2</sub>)

<strong>Output:</strong> 8

</pre>
<p><strong>Example 2: </strong></p>
<pre>

<strong>Input:</strong> <code>num</code> = 7(0111<sub>2</sub>)

<strong>Output:</strong> 4

</pre>

## Solutions

### Solution 1: Two Pointers

We can use two pointers $i$ and $j$ to maintain a sliding window, where $i$ is the right pointer and $j$ is the left pointer. Each time the right pointer $i$ moves one bit to the right, if the number of $0$s in the window exceeds $1$, then the left pointer $j$ moves one bit to the right, until the number of $0$s in the window does not exceed $1$. Then calculate the length of the window at this time, compare it with the current maximum length, and take the larger value as the current maximum length.

Finally, return the maximum length.

The time complexity is $O(\log M)$, and the space complexity is $O(1)$. Here, $M$ is the maximum value of a 32-bit integer.

<!-- tabs:start -->

```python
class Solution:
    def reverseBits(self, num: int) -> int:
        ans = cnt = j = 0
        for i in range(32):
            cnt += num >> i & 1 ^ 1
            while cnt > 1:
                cnt -= num >> j & 1 ^ 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int reverseBits(int num) {
        int ans = 0, cnt = 0;
        for (int i = 0, j = 0; i < 32; ++i) {
            cnt += num >> i & 1 ^ 1;
            while (cnt > 1) {
                cnt -= num >> j & 1 ^ 1;
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int reverseBits(int num) {
        int ans = 0, cnt = 0;
        for (int i = 0, j = 0; i < 32; ++i) {
            cnt += num >> i & 1 ^ 1;
            while (cnt > 1) {
                cnt -= num >> j & 1 ^ 1;
                ++j;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func reverseBits(num int) (ans int) {
	var cnt, j int
	for i := 0; i < 32; i++ {
		cnt += num>>i&1 ^ 1
		for cnt > 1 {
			cnt -= num>>j&1 ^ 1
			j++
		}
		ans = max(ans, i-j+1)
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
