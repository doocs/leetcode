---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2165.Smallest%20Value%20of%20the%20Rearranged%20Number/README_EN.md
rating: 1361
source: Weekly Contest 279 Q2
tags:
    - Math
    - Sorting
---

<!-- problem:start -->

# [2165. Smallest Value of the Rearranged Number](https://leetcode.com/problems/smallest-value-of-the-rearranged-number)

[中文文档](/solution/2100-2199/2165.Smallest%20Value%20of%20the%20Rearranged%20Number/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>num.</code> <strong>Rearrange</strong> the digits of <code>num</code> such that its value is <strong>minimized</strong> and it does not contain <strong>any</strong> leading zeros.</p>

<p>Return <em>the rearranged number with minimal value</em>.</p>

<p>Note that the sign of the number does not change after rearranging the digits.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 310
<strong>Output:</strong> 103
<strong>Explanation:</strong> The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310. 
The arrangement with the smallest value that does not contain any leading zeros is 103.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = -7605
<strong>Output:</strong> -7650
<strong>Explanation:</strong> Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
The arrangement with the smallest value that does not contain any leading zeros is -7650.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>15</sup> &lt;= num &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We first use an array $\textit{cnt}$ to record the number of occurrences of each digit in $\textit{num}$.

If $\textit{num}$ is negative, the digits should be arranged in descending order. Therefore, we traverse $\textit{cnt}$ from $9$ to $0$ and arrange the digits in descending order according to their occurrences.

If $\textit{num}$ is positive, we first find the first non-zero digit and place it in the first position, then arrange the remaining digits in ascending order.

The time complexity is $O(\log n)$, where $n$ is the size of the number $\textit{num}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestNumber(self, num: int) -> int:
        neg = num < 0
        num = abs(num)
        cnt = [0] * 10
        while num:
            cnt[num % 10] += 1
            num //= 10
        ans = 0
        if neg:
            for i in reversed(range(10)):
                for _ in range(cnt[i]):
                    ans *= 10
                    ans += i
            return -ans
        if cnt[0]:
            for i in range(1, 10):
                if cnt[i]:
                    ans = i
                    cnt[i] -= 1
                    break
        for i in range(10):
            for _ in range(cnt[i]):
                ans *= 10
                ans += i
        return ans
```

#### Java

```java
class Solution {
    public long smallestNumber(long num) {
        boolean neg = num < 0;
        num = Math.abs(num);
        int[] cnt = new int[10];
        while (num > 0) {
            ++cnt[(int) (num % 10)];
            num /= 10;
        }
        long ans = 0;
        if (neg) {
            for (int i = 9; i >= 0; --i) {
                while (cnt[i] > 0) {
                    ans = ans * 10 + i;
                    --cnt[i];
                }
            }
            return -ans;
        }
        if (cnt[0] > 0) {
            for (int i = 1; i < 10; ++i) {
                if (cnt[i] > 0) {
                    --cnt[i];
                    ans = i;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; ++i) {
            while (cnt[i] > 0) {
                ans = ans * 10 + i;
                --cnt[i];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long smallestNumber(long long num) {
        bool neg = num < 0;
        num = abs(num);
        int cnt[10]{};
        while (num > 0) {
            ++cnt[num % 10];
            num /= 10;
        }
        long long ans = 0;
        if (neg) {
            for (int i = 9; i >= 0; --i) {
                while (cnt[i] > 0) {
                    ans = ans * 10 + i;
                    --cnt[i];
                }
            }
            return -ans;
        }
        if (cnt[0]) {
            for (int i = 1; i < 10; ++i) {
                if (cnt[i] > 0) {
                    --cnt[i];
                    ans = i;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; ++i) {
            while (cnt[i] > 0) {
                ans = ans * 10 + i;
                --cnt[i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func smallestNumber(num int64) (ans int64) {
	neg := num < 0
	num = max(num, -num)
	cnt := make([]int, 10)

	for num > 0 {
		cnt[num%10]++
		num /= 10
	}

	if neg {
		for i := 9; i >= 0; i-- {
			for cnt[i] > 0 {
				ans = ans*10 + int64(i)
				cnt[i]--
			}
		}
		return -ans
	}

	if cnt[0] > 0 {
		for i := 1; i < 10; i++ {
			if cnt[i] > 0 {
				cnt[i]--
				ans = int64(i)
				break
			}
		}
	}

	for i := 0; i < 10; i++ {
		for cnt[i] > 0 {
			ans = ans*10 + int64(i)
			cnt[i]--
		}
	}

	return ans
}
```

#### TypeScript

```ts
function smallestNumber(num: number): number {
    const neg = num < 0;
    num = Math.abs(num);
    const cnt = Array(10).fill(0);

    while (num > 0) {
        cnt[num % 10]++;
        num = Math.floor(num / 10);
    }

    let ans = 0;
    if (neg) {
        for (let i = 9; i >= 0; i--) {
            while (cnt[i] > 0) {
                ans = ans * 10 + i;
                cnt[i]--;
            }
        }
        return -ans;
    }

    if (cnt[0] > 0) {
        for (let i = 1; i < 10; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                ans = i;
                break;
            }
        }
    }

    for (let i = 0; i < 10; i++) {
        while (cnt[i] > 0) {
            ans = ans * 10 + i;
            cnt[i]--;
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_number(num: i64) -> i64 {
        let mut neg = num < 0;
        let mut num = num.abs();
        let mut cnt = vec![0; 10];

        while num > 0 {
            cnt[(num % 10) as usize] += 1;
            num /= 10;
        }

        let mut ans = 0;
        if neg {
            for i in (0..10).rev() {
                while cnt[i] > 0 {
                    ans = ans * 10 + i as i64;
                    cnt[i] -= 1;
                }
            }
            return -ans;
        }

        if cnt[0] > 0 {
            for i in 1..10 {
                if cnt[i] > 0 {
                    cnt[i] -= 1;
                    ans = i as i64;
                    break;
                }
            }
        }

        for i in 0..10 {
            while cnt[i] > 0 {
                ans = ans * 10 + i as i64;
                cnt[i] -= 1;
            }
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} num
 * @return {number}
 */
var smallestNumber = function (num) {
    const neg = num < 0;
    num = Math.abs(num);
    const cnt = Array(10).fill(0);

    while (num > 0) {
        cnt[num % 10]++;
        num = Math.floor(num / 10);
    }

    let ans = 0;
    if (neg) {
        for (let i = 9; i >= 0; i--) {
            while (cnt[i] > 0) {
                ans = ans * 10 + i;
                cnt[i]--;
            }
        }
        return -ans;
    }

    if (cnt[0] > 0) {
        for (let i = 1; i < 10; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                ans = i;
                break;
            }
        }
    }

    for (let i = 0; i < 10; i++) {
        while (cnt[i] > 0) {
            ans = ans * 10 + i;
            cnt[i]--;
        }
    }

    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
