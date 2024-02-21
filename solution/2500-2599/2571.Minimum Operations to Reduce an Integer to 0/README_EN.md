# [2571. Minimum Operations to Reduce an Integer to 0](https://leetcode.com/problems/minimum-operations-to-reduce-an-integer-to-0)

[中文文档](/solution/2500-2599/2571.Minimum%20Operations%20to%20Reduce%20an%20Integer%20to%200/README.md)

<!-- tags:Greedy,Bit Manipulation,Dynamic Programming -->

## Description

<p>You are given a positive integer <code>n</code>, you can do the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Add or subtract a <strong>power</strong> of <code>2</code> from <code>n</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations to make </em><code>n</code><em> equal to </em><code>0</code>.</p>

<p>A number <code>x</code> is power of <code>2</code> if <code>x == 2<sup>i</sup></code>&nbsp;where <code>i &gt;= 0</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 39
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can do the following operations:
- Add 2<sup>0</sup> = 1 to n, so now n = 40.
- Subtract 2<sup>3</sup> = 8 from n, so now n = 32.
- Subtract 2<sup>5</sup> = 32 from n, so now n = 0.
It can be shown that 3 is the minimum number of operations we need to make n equal to 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 54
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can do the following operations:
- Add 2<sup>1</sup> = 2 to n, so now n = 56.
- Add 2<sup>3</sup> = 8 to n, so now n = 64.
- Subtract 2<sup>6</sup> = 64 from n, so now n = 0.
So the minimum number of operations is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy + Bitwise Operation

We convert the integer $n$ to binary, starting from the lowest bit:

-   If the current bit is 1, we accumulate the current number of consecutive 1s;
-   If the current bit is 0, we check whether the current number of consecutive 1s is greater than 0. If it is, we check whether the current number of consecutive 1s is 1. If it is, it means that we can eliminate 1 through one operation; if it is greater than 1, it means that we can reduce the number of consecutive 1s to 1 through one operation.

Finally, we also need to check whether the current number of consecutive 1s is 1. If it is, it means that we can eliminate 1 through one operation; if it is greater than 1, we can eliminate the consecutive 1s through two operations.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$. Here, $n$ is the given integer in the problem.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, n: int) -> int:
        ans = cnt = 0
        while n:
            if n & 1:
                cnt += 1
            elif cnt:
                ans += 1
                cnt = 0 if cnt == 1 else 1
            n >>= 1
        if cnt == 1:
            ans += 1
        elif cnt > 1:
            ans += 2
        return ans
```

```java
class Solution {
    public int minOperations(int n) {
        int ans = 0, cnt = 0;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ++cnt;
            } else if (cnt > 0) {
                ++ans;
                cnt = cnt == 1 ? 0 : 1;
            }
        }
        ans += cnt == 1 ? 1 : 0;
        ans += cnt > 1 ? 2 : 0;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(int n) {
        int ans = 0, cnt = 0;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ++cnt;
            } else if (cnt > 0) {
                ++ans;
                cnt = cnt == 1 ? 0 : 1;
            }
        }
        ans += cnt == 1 ? 1 : 0;
        ans += cnt > 1 ? 2 : 0;
        return ans;
    }
};
```

```go
func minOperations(n int) (ans int) {
	cnt := 0
	for ; n > 0; n >>= 1 {
		if n&1 == 1 {
			cnt++
		} else if cnt > 0 {
			ans++
			if cnt == 1 {
				cnt = 0
			} else {
				cnt = 1
			}
		}
	}
	if cnt == 1 {
		ans++
	} else if cnt > 1 {
		ans += 2
	}
	return
}
```

```ts
function minOperations(n: number): number {
    let [ans, cnt] = [0, 0];
    for (; n; n >>= 1) {
        if (n & 1) {
            ++cnt;
        } else if (cnt) {
            ++ans;
            cnt = cnt === 1 ? 0 : 1;
        }
    }
    if (cnt === 1) {
        ++ans;
    } else if (cnt > 1) {
        ans += 2;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
