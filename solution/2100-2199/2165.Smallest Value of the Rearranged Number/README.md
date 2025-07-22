---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2165.Smallest%20Value%20of%20the%20Rearranged%20Number/README.md
rating: 1361
source: 第 279 场周赛 Q2
tags:
    - 数学
    - 排序
---

<!-- problem:start -->

# [2165. 重排数字的最小值](https://leetcode.cn/problems/smallest-value-of-the-rearranged-number)

[English Version](/solution/2100-2199/2165.Smallest%20Value%20of%20the%20Rearranged%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>num</code> 。<strong>重排</strong> <code>num</code> 中的各位数字，使其值 <strong>最小化</strong> 且不含 <strong>任何</strong> 前导零。</p>

<p>返回不含前导零且值最小的重排数字。</p>

<p>注意，重排各位数字后，<code>num</code> 的符号不会改变。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 310
<strong>输出：</strong>103
<strong>解释：</strong>310 中各位数字的可行排列有：013、031、103、130、301、310 。
不含任何前导零且值最小的重排数字是 103 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = -7605
<strong>输出：</strong>-7650
<strong>解释：</strong>-7605 中各位数字的部分可行排列为：-7650、-6705、-5076、-0567。
不含任何前导零且值最小的重排数字是 -7650 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>15</sup> &lt;= num &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们首先用一个数组 $\textit{cnt}$ 记录 $\textit{num}$ 中每个数字的出现次数。

如果 $\textit{num}$ 是负数，那么数字应该按照从大到小的顺序排列，因此我们从 $9$ 到 $0$ 遍历 $\textit{cnt}$，将数字按照出现次数从大到小的顺序排列。

如果是正数，我们首先找到第一个非 $0$ 的数字，将其放在第一位，然后按照从小到大的顺序排列剩余的数字。

时间复杂度 $O(\log n)$，其中 $n$ 为数字 $\textit{num}$ 的大小。空间复杂度 $O(1)$。

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
