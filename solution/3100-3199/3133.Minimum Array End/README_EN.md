# [3133. Minimum Array End](https://leetcode.com/problems/minimum-array-end)

[中文文档](/solution/3100-3199/3133.Minimum%20Array%20End/README.md)

<!-- tags:Bit Manipulation -->

<!-- difficulty:Medium -->

## Description

<p>You are given two integers <code>n</code> and <code>x</code>. You have to construct an array of <strong>positive</strong> integers <code>nums</code> of size <code>n</code> where for every <code>0 &lt;= i &lt; n - 1</code>, <code>nums[i + 1]</code> is <strong>greater than</strong> <code>nums[i]</code>, and the result of the bitwise <code>AND</code> operation between all elements of <code>nums</code> is <code>x</code>.</p>

<p>Return the <strong>minimum</strong> possible value of <code>nums[n - 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, x = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p><code>nums</code> can be <code>[4,5,6]</code> and its last element is 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, x = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p><code>nums</code> can be <code>[7,15]</code> and its last element is 15.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, x &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy + Bit Manipulation

According to the problem description, to make the last element of the array as small as possible and the bitwise AND result of the elements in the array is $x$, the first element of the array must be $x$.

Assume the binary representation of $x$ is $\underline{1}00\underline{1}00$, then the array sequence is $\underline{1}00\underline{1}00$, $\underline{1}00\underline{1}01$, $\underline{1}00\underline{1}10$, $\underline{1}00\underline{1}11$...

If we ignore the underlined part, then the array sequence is $0000$, $0001$, $0010$, $0011$..., the first item is $0$, then the $n$-th item is $n-1$.

Therefore, the answer is to fill each bit of the binary of $n-1$ into the $0$ bit of the binary of $x$ based on $x$.

The time complexity is $O(\log x)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minEnd(self, n: int, x: int) -> int:
        n -= 1
        ans = x
        for i in range(31):
            if x >> i & 1 ^ 1:
                ans |= (n & 1) << i
                n >>= 1
        ans |= n << 31
        return ans
```

```java
class Solution {
    public long minEnd(int n, int x) {
        --n;
        long ans = x;
        for (int i = 0; i < 31; ++i) {
            if ((x >> i & 1) == 0) {
                ans |= (n & 1) << i;
                n >>= 1;
            }
        }
        ans |= (long) n << 31;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minEnd(int n, int x) {
        --n;
        long long ans = x;
        for (int i = 0; i < 31; ++i) {
            if (x >> i & 1 ^ 1) {
                ans |= (n & 1) << i;
                n >>= 1;
            }
        }
        ans |= (1LL * n) << 31;
        return ans;
    }
};
```

```go
func minEnd(n int, x int) (ans int64) {
	n--
	ans = int64(x)
	for i := 0; i < 31; i++ {
		if x>>i&1 == 0 {
			ans |= int64((n & 1) << i)
			n >>= 1
		}
	}
	ans |= int64(n) << 31
	return
}
```

```ts
function minEnd(n: number, x: number): number {
    --n;
    let ans: bigint = BigInt(x);
    for (let i = 0; i < 31; ++i) {
        if (((x >> i) & 1) ^ 1) {
            ans |= BigInt(n & 1) << BigInt(i);
            n >>= 1;
        }
    }
    ans |= BigInt(n) << BigInt(31);
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- end -->
