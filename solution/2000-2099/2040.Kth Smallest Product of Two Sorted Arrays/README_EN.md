---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2040.Kth%20Smallest%20Product%20of%20Two%20Sorted%20Arrays/README_EN.md
rating: 2517
source: Biweekly Contest 63 Q4
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [2040. Kth Smallest Product of Two Sorted Arrays](https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays)

[中文文档](/solution/2000-2099/2040.Kth%20Smallest%20Product%20of%20Two%20Sorted%20Arrays/README.md)

## Description

<!-- description:start -->

Given two <strong>sorted 0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code> as well as an integer <code>k</code>, return <em>the </em><code>k<sup>th</sup></code><em> (<strong>1-based</strong>) smallest product of </em><code>nums1[i] \* nums2[j]</code><em> where </em><code>0 &lt;= i &lt; nums1.length</code><em> and </em><code>0 &lt;= j &lt; nums2.length</code>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,5], nums2 = [3,4], k = 2
<strong>Output:</strong> 8
<strong>Explanation:</strong> The 2 smallest products are:
- nums1[0] * nums2[0] = 2 * 3 = 6
- nums1[0] * nums2[1] = 2 * 4 = 8
The 2<sup>nd</sup> smallest product is 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
<strong>Output:</strong> 0
<strong>Explanation:</strong> The 6 smallest products are:
- nums1[0] * nums2[1] = (-4) * 4 = -16
- nums1[0] * nums2[0] = (-4) * 2 = -8
- nums1[1] * nums2[1] = (-2) * 4 = -8
- nums1[1] * nums2[0] = (-2) * 2 = -4
- nums1[2] * nums2[0] = 0 * 2 = 0
- nums1[2] * nums2[1] = 0 * 4 = 0
The 6<sup>th</sup> smallest product is 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
<strong>Output:</strong> -6
<strong>Explanation:</strong> The 3 smallest products are:
- nums1[0] * nums2[4] = (-2) * 5 = -10
- nums1[0] * nums2[3] = (-2) * 4 = -8
- nums1[4] * nums2[0] = 2 * (-3) = -6
The 3<sup>rd</sup> smallest product is -6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums1.length * nums2.length</code></li>
	<li><code>nums1</code> and <code>nums2</code> are sorted.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We can use binary search to enumerate the value of the product $p$, defining the binary search interval as $[l, r]$, where $l = -\textit{max}(|\textit{nums1}[0]|, |\textit{nums1}[n - 1]|) \times \textit{max}(|\textit{nums2}[0]|, |\textit{nums2}[n - 1]|)$, $r = -l$.

For each $p$, we calculate the number of products less than or equal to $p$. If this number is greater than or equal to $k$, it means the $k$-th smallest product must be less than or equal to $p$, so we can reduce the right endpoint of the interval to $p$. Otherwise, we increase the left endpoint of the interval to $p + 1$.

The key to the problem is how to calculate the number of products less than or equal to $p$. We can enumerate each number $x$ in $\textit{nums1}$ and discuss in cases:

-   If $x > 0$, then $x \times \textit{nums2}[i]$ is monotonically increasing as $i$ increases. We can use binary search to find the smallest $i$ such that $x \times \textit{nums2}[i] > p$. Then, $i$ is the number of products less than or equal to $p$, which is accumulated into the count $\textit{cnt}$;
-   If $x < 0$, then $x \times \textit{nums2}[i]$ is monotonically decreasing as $i$ increases. We can use binary search to find the smallest $i$ such that $x \times \textit{nums2}[i] \leq p$. Then, $n - i$ is the number of products less than or equal to $p$, which is accumulated into the count $\textit{cnt}$;
-   If $x = 0$, then $x \times \textit{nums2}[i] = 0$. If $p \geq 0$, then $n$ is the number of products less than or equal to $p$, which is accumulated into the count $\textit{cnt}$.

This way, we can find the $k$-th smallest product through binary search.

The time complexity is $O(m \times \log n \times \log M)$, where $m$ and $n$ are the lengths of $\textit{nums1}$ and $\textit{nums2}$, respectively, and $M$ is the maximum absolute value in $\textit{nums1}$ and $\textit{nums2}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthSmallestProduct(self, nums1: List[int], nums2: List[int], k: int) -> int:
        def count(p: int) -> int:
            cnt = 0
            n = len(nums2)
            for x in nums1:
                if x > 0:
                    cnt += bisect_right(nums2, p / x)
                elif x < 0:
                    cnt += n - bisect_left(nums2, p / x)
                else:
                    cnt += n * int(p >= 0)
            return cnt

        mx = max(abs(nums1[0]), abs(nums1[-1])) * max(abs(nums2[0]), abs(nums2[-1]))
        return bisect_left(range(-mx, mx + 1), k, key=count) - mx
```

#### Java

```java
class Solution {
    private int[] nums1;
    private int[] nums2;

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        int m = nums1.length;
        int n = nums2.length;
        int a = Math.max(Math.abs(nums1[0]), Math.abs(nums1[m - 1]));
        int b = Math.max(Math.abs(nums2[0]), Math.abs(nums2[n - 1]));
        long r = (long) a * b;
        long l = (long) -a * b;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (count(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private long count(long p) {
        long cnt = 0;
        int n = nums2.length;
        for (int x : nums1) {
            if (x > 0) {
                int l = 0, r = n;
                while (l < r) {
                    int mid = (l + r) >> 1;
                    if ((long) x * nums2[mid] > p) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cnt += l;
            } else if (x < 0) {
                int l = 0, r = n;
                while (l < r) {
                    int mid = (l + r) >> 1;
                    if ((long) x * nums2[mid] <= p) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cnt += n - l;
            } else if (p >= 0) {
                cnt += n;
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long kthSmallestProduct(vector<int>& nums1, vector<int>& nums2, long long k) {
        int m = nums1.size(), n = nums2.size();
        int a = max(abs(nums1[0]), abs(nums1[m - 1]));
        int b = max(abs(nums2[0]), abs(nums2[n - 1]));
        long long r = 1LL * a * b;
        long long l = -r;
        auto count = [&](long long p) {
            long long cnt = 0;
            for (int x : nums1) {
                if (x > 0) {
                    int l = 0, r = n;
                    while (l < r) {
                        int mid = (l + r) >> 1;
                        if (1LL * x * nums2[mid] > p) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    cnt += l;
                } else if (x < 0) {
                    int l = 0, r = n;
                    while (l < r) {
                        int mid = (l + r) >> 1;
                        if (1LL * x * nums2[mid] <= p) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    cnt += n - l;
                } else if (p >= 0) {
                    cnt += n;
                }
            }
            return cnt;
        };
        while (l < r) {
            long long mid = (l + r) >> 1;
            if (count(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func kthSmallestProduct(nums1 []int, nums2 []int, k int64) int64 {
	m := len(nums1)
	n := len(nums2)
	a := max(abs(nums1[0]), abs(nums1[m-1]))
	b := max(abs(nums2[0]), abs(nums2[n-1]))
	r := int64(a) * int64(b)
	l := -r

	count := func(p int64) int64 {
		var cnt int64
		for _, x := range nums1 {
			if x > 0 {
				l, r := 0, n
				for l < r {
					mid := (l + r) >> 1
					if int64(x)*int64(nums2[mid]) > p {
						r = mid
					} else {
						l = mid + 1
					}
				}
				cnt += int64(l)
			} else if x < 0 {
				l, r := 0, n
				for l < r {
					mid := (l + r) >> 1
					if int64(x)*int64(nums2[mid]) <= p {
						r = mid
					} else {
						l = mid + 1
					}
				}
				cnt += int64(n - l)
			} else if p >= 0 {
				cnt += int64(n)
			}
		}
		return cnt
	}

	for l < r {
		mid := (l + r) >> 1
		if count(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function kthSmallestProduct(nums1: number[], nums2: number[], k: number): number {
    const m = nums1.length;
    const n = nums2.length;

    const a = BigInt(Math.max(Math.abs(nums1[0]), Math.abs(nums1[m - 1])));
    const b = BigInt(Math.max(Math.abs(nums2[0]), Math.abs(nums2[n - 1])));

    let l = -a * b;
    let r = a * b;

    const count = (p: bigint): bigint => {
        let cnt = 0n;
        for (const x of nums1) {
            const bx = BigInt(x);
            if (bx > 0n) {
                let l = 0,
                    r = n;
                while (l < r) {
                    const mid = (l + r) >> 1;
                    const prod = bx * BigInt(nums2[mid]);
                    if (prod > p) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cnt += BigInt(l);
            } else if (bx < 0n) {
                let l = 0,
                    r = n;
                while (l < r) {
                    const mid = (l + r) >> 1;
                    const prod = bx * BigInt(nums2[mid]);
                    if (prod <= p) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cnt += BigInt(n - l);
            } else if (p >= 0n) {
                cnt += BigInt(n);
            }
        }
        return cnt;
    };

    while (l < r) {
        const mid = (l + r) >> 1n;
        if (count(mid) >= BigInt(k)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }

    return Number(l);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
