# [3116. Kth Smallest Amount With Single Denomination Combination](https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination)

[中文文档](/solution/3100-3199/3116.Kth%20Smallest%20Amount%20With%20Single%20Denomination%20Combination/README.md)

<!-- tags:Bit Manipulation,Array,Math,Binary Search,Combinatorics,Number Theory -->

## Description

<p>You are given an integer array <code>coins</code> representing coins of different denominations and an integer <code>k</code>.</p>

<p>You have an infinite number of coins of each denomination. However, you are <strong>not allowed</strong> to combine coins of different denominations.</p>

<p>Return the <code>k<sup>th</sup></code> <strong>smallest</strong> amount that can be made using these coins.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">coins = [3,6,9], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> 9</span></p>

<p><strong>Explanation:</strong> The given coins can make the following amounts:<br />
Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.<br />
Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.<br />
Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.<br />
All of the coins combined produce: 3, 6, <u><strong>9</strong></u>, 12, 15, etc.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> coins = [5,2], k = 7</span></p>

<p><strong>Output:</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> 12 </span></p>

<p><strong>Explanation:</strong> The given coins can make the following amounts:<br />
Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.<br />
Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.<br />
All of the coins combined produce: 2, 4, 5, 6, 8, 10, <u><strong>12</strong></u>, 14, 15, etc.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 15</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 25</code></li>
	<li><code>1 &lt;= k &lt;= 2 * 10<sup>9</sup></code></li>
	<li><code>coins</code> contains pairwise distinct integers.</li>
</ul>

## Solutions

### Solution 1: Binary Search + Inclusion-Exclusion Principle

We can transform the problem into: find the smallest positive integer $x$ such that the number of numbers less than or equal to $x$ and satisfying the condition is exactly $k$. If $x$ satisfies the condition, then for any $x' > x$, $x'$ also satisfies the condition. This shows monotonicity, so we can use binary search to find the smallest $x$ that satisfies the condition.

We define a function `check(x)` to determine whether the number of numbers less than or equal to $x$ and satisfying the condition is greater than or equal to $k$. We need to calculate how many numbers can be obtained from the array $coins$.

Suppose $coins = [a, b]$, according to the inclusion-exclusion principle, the number of numbers less than or equal to $x$ and satisfying the condition is:

$$
\left\lfloor \frac{x}{a} \right\rfloor + \left\lfloor \frac{x}{b} \right\rfloor - \left\lfloor \frac{x}{lcm(a, b)} \right\rfloor
$$

If $coins = [a, b, c]$, the number of numbers less than or equal to $x$ and satisfying the condition is:

$$
\left\lfloor \frac{x}{a} \right\rfloor + \left\lfloor \frac{x}{b} \right\rfloor + \left\lfloor \frac{x}{c} \right\rfloor - \left\lfloor \frac{x}{lcm(a, b)} \right\rfloor - \left\lfloor \frac{x}{lcm(a, c)} \right\rfloor - \left\lfloor \frac{x}{lcm(b, c)} \right\rfloor + \left\lfloor \frac{x}{lcm(a, b, c)} \right\rfloor
$$

As you can see, we need to add all cases with an odd number of elements and subtract all cases with an even number of elements.

Since $n \leq 15$, we can use binary enumeration to enumerate all subsets and calculate the number of numbers that satisfy the condition, denoted as $cnt$. If $cnt \geq k$, then we need to find the smallest $x$ such that `check(x)` is true.

At the start of the binary search, we define the left boundary $l=1$ and the right boundary $r={10}^{11}$. Then we continuously substitute the middle value $mid$ into the `check` function. If `check(mid)` is true, then we update the right boundary $r$ to $mid$, otherwise we update the left boundary $l$ to $mid+1$. Finally, we return $l$.

The time complexity is $O(n \times 2^n \times \log (k \times M))$, where $n$ is the length of the array $coins$, and $M$ is the maximum value in the array.

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
