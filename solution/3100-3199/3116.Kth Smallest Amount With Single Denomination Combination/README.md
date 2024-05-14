---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3116.Kth%20Smallest%20Amount%20With%20Single%20Denomination%20Combination/README.md
rating: 2387
tags:
    - 位运算
    - 数组
    - 数学
    - 二分查找
    - 组合数学
    - 数论
---

# [3116. 单面值组合的第 K 小金额](https://leetcode.cn/problems/kth-smallest-amount-with-single-denomination-combination)

[English Version](/solution/3100-3199/3116.Kth%20Smallest%20Amount%20With%20Single%20Denomination%20Combination/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>coins</code> 表示不同面额的硬币，另给你一个整数 <code>k</code> 。</p>

<p>你有无限量的每种面额的硬币。但是，你<strong> 不能 </strong>组合使用不同面额的硬币。</p>

<p>返回使用这些硬币能制造的<strong> 第 </strong><code>k<sup>th</sup></code><strong> 小</strong> 金额。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;">coins = [3,6,9], k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;">9</span></p>

<p><strong>解释：</strong>给定的硬币可以制造以下金额：<br />
3元硬币产生3的倍数：3, 6, 9, 12, 15等。<br />
6元硬币产生6的倍数：6, 12, 18, 24等。<br />
9元硬币产生9的倍数：9, 18, 27, 36等。<br />
所有硬币合起来可以产生：3, 6, <u><strong>9</strong></u>, 12, 15等。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;">coins = [5,2], k = 7</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;">12</span></p>

<p><strong>解释：</strong>给定的硬币可以制造以下金额：<br />
5元硬币产生5的倍数：5, 10, 15, 20等。<br />
2元硬币产生2的倍数：2, 4, 6, 8, 10, 12等。<br />
所有硬币合起来可以产生：2, 4, 5, 6, 8, 10, <u><strong>12</strong></u>, 14, 15等。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 15</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 25</code></li>
	<li><code>1 &lt;= k &lt;= 2 * 10<sup>9</sup></code></li>
	<li><code>coins</code> 包含两两不同的整数。</li>
</ul>

## 解法

### 方法一：二分查找 + 容斥原理

我们可以将题目转化为：找到最小的正整数 $x$，使得小于等于 $x$ 的且满足条件的数的个数恰好为 $k$ 个。如果 $x$ 满足条件，那么对任意 $x' > x$ 的 $x'$ 也满足条件，这存在单调性，因此我们可以使用二分查找，找到最小的满足条件的 $x$。

我们定义一个函数 `check(x)`，用来判断小于等于 $x$ 的且满足条件的数的个数是否大于等于 $k$。我们需要计算有多少个数可以由 $coins$ 中的数组合得到。

假设 $coins$ 为 $[a, b]$，根据容斥原理，小于等于 $x$ 的满足条件的数的个数为：

$$
\left\lfloor \frac{x}{a} \right\rfloor + \left\lfloor \frac{x}{b} \right\rfloor - \left\lfloor \frac{x}{lcm(a, b)} \right\rfloor
$$

如果 $coins$ 为 $[a, b, c]$，小于等于 $x$ 的满足条件的数的个数为：

$$
\left\lfloor \frac{x}{a} \right\rfloor + \left\lfloor \frac{x}{b} \right\rfloor + \left\lfloor \frac{x}{c} \right\rfloor - \left\lfloor \frac{x}{lcm(a, b)} \right\rfloor - \left\lfloor \frac{x}{lcm(a, c)} \right\rfloor - \left\lfloor \frac{x}{lcm(b, c)} \right\rfloor + \left\lfloor \frac{x}{lcm(a, b, c)} \right\rfloor
$$

可以看到，我们需要累加所有任意奇数个数的情况，减去所有任意偶数个数的情况。

由于 $n \leq 15$，我们可以使用二进制枚举的方式，枚举所有的子集，计算满足条件的数的个数，我们记为 $cnt$。如果 $cnt \geq k$，那么我们需要找到最小的 $x$，使得 $check(x)$ 为真。

在二分查找开始时，我们定义二分查找的左边界 $l=1$，右边界 $r={10}^{11}$，然后我们不断地将中间值 $mid$ 代入 `check` 函数中，如果 `check(mid)` 为真，那么我们将右边界 $r$ 更新为 $mid$，否则我们将左边界 $l$ 更新为 $mid+1$。最终返回 $l$。

时间复杂度 $O(n \times 2^n \times \log (k \times M))$，其中 $n$ 是数组 $coins$ 的长度，而 $M$ 是数组 $coins$ 中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def findKthSmallest(self, coins: List[int], k: int) -> int:
        def check(mx: int) -> bool:
            cnt = 0
            for i in range(1, 1 << len(coins)):
                v = 1
                for j, x in enumerate(coins):
                    if i >> j & 1:
                        v = lcm(v, x)
                        if v > mx:
                            break
                m = i.bit_count()
                if m & 1:
                    cnt += mx // v
                else:
                    cnt -= mx // v
            return cnt >= k

        return bisect_left(range(10**11), True, key=check)
```

```java
class Solution {
    private int[] coins;
    private int k;

    public long findKthSmallest(int[] coins, int k) {
        this.coins = coins;
        this.k = k;
        long l = 1, r = (long) 1e11;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(long mx) {
        long cnt = 0;
        int n = coins.length;
        for (int i = 1; i < 1 << n; ++i) {
            long v = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    v = lcm(v, coins[j]);
                    if (v > mx) {
                        break;
                    }
                }
            }
            int m = Integer.bitCount(i);
            if (m % 2 == 1) {
                cnt += mx / v;
            } else {
                cnt -= mx / v;
            }
        }
        return cnt >= k;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

```cpp
class Solution {
public:
    long long findKthSmallest(vector<int>& coins, int k) {
        using ll = long long;
        ll l = 1, r = 1e11;
        int n = coins.size();

        auto check = [&](ll mx) {
            ll cnt = 0;
            for (int i = 1; i < 1 << n; ++i) {
                ll v = 1;
                for (int j = 0; j < n; ++j) {
                    if (i >> j & 1) {
                        v = lcm(v, coins[j]);
                        if (v > mx) {
                            break;
                        }
                    }
                }
                int m = __builtin_popcount(i);
                if (m & 1) {
                    cnt += mx / v;
                } else {
                    cnt -= mx / v;
                }
            }
            return cnt >= k;
        };

        while (l < r) {
            ll mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

```go
func findKthSmallest(coins []int, k int) int64 {
	var r int = 1e11
	n := len(coins)
	ans := sort.Search(r, func(mx int) bool {
		cnt := 0
		for i := 1; i < 1<<n; i++ {
			v := 1
			for j, x := range coins {
				if i>>j&1 == 1 {
					v = lcm(v, x)
					if v > mx {
						break
					}
				}
			}
			m := bits.OnesCount(uint(i))
			if m%2 == 1 {
				cnt += mx / v
			} else {
				cnt -= mx / v
			}
		}
		return cnt >= k
	})
	return int64(ans)
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a, b int) int {
	return a * b / gcd(a, b)
}
```

```ts
function findKthSmallest(coins: number[], k: number): number {
    let [l, r] = [1n, BigInt(1e11)];
    const n = coins.length;
    const check = (mx: bigint): boolean => {
        let cnt = 0n;
        for (let i = 1; i < 1 << n; ++i) {
            let v = 1n;
            for (let j = 0; j < n; ++j) {
                if ((i >> j) & 1) {
                    v = lcm(v, BigInt(coins[j]));
                    if (v > mx) {
                        break;
                    }
                }
            }
            const m = bitCount(i);
            if (m & 1) {
                cnt += mx / v;
            } else {
                cnt -= mx / v;
            }
        }
        return cnt >= BigInt(k);
    };
    while (l < r) {
        const mid = (l + r) >> 1n;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }
    return Number(l);
}

function gcd(a: bigint, b: bigint): bigint {
    return b === 0n ? a : gcd(b, a % b);
}

function lcm(a: bigint, b: bigint): bigint {
    return (a * b) / gcd(a, b);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- end -->
