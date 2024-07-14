---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2198.Number%20of%20Single%20Divisor%20Triplets/README_EN.md
tags:
    - Math
---

<!-- problem:start -->

# [2198. Number of Single Divisor Triplets 🔒](https://leetcode.com/problems/number-of-single-divisor-triplets)

[中文文档](/solution/2100-2199/2198.Number%20of%20Single%20Divisor%20Triplets/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array of positive integers <code>nums</code>. A triplet of three <strong>distinct</strong> indices <code>(i, j, k)</code> is called a <strong>single divisor triplet</strong> of <code>nums</code> if <code>nums[i] + nums[j] + nums[k]</code> is divisible by <strong>exactly one</strong> of <code>nums[i]</code>, <code>nums[j]</code>, or <code>nums[k]</code>.</p>
Return <em>the number of <strong>single divisor triplets</strong> of </em><code>nums</code><em>.</em>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,6,7,3,2]
<strong>Output:</strong> 12
<strong>Explanation:
</strong>The triplets (0, 3, 4), (0, 4, 3), (3, 0, 4), (3, 4, 0), (4, 0, 3), and (4, 3, 0) have the values of [4, 3, 2] (or a permutation of [4, 3, 2]).
4 + 3 + 2 = 9 which is only divisible by 3, so all such triplets are single divisor triplets.
The triplets (0, 2, 3), (0, 3, 2), (2, 0, 3), (2, 3, 0), (3, 0, 2), and (3, 2, 0) have the values of [4, 7, 3] (or a permutation of [4, 7, 3]).
4 + 7 + 3 = 14 which is only divisible by 7, so all such triplets are single divisor triplets.
There are 12 single divisor triplets in total.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2]
<strong>Output:</strong> 6
<strong>Explanation:</strong>
The triplets (0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), (2, 0, 1), and (2, 1, 0) have the values of [1, 2, 2] (or a permutation of [1, 2, 2]).
1 + 2 + 2 = 5 which is only divisible by 1, so all such triplets are single divisor triplets.
There are 6 single divisor triplets in total.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no single divisor triplets.
Note that (0, 1, 2) is not a single divisor triplet because nums[0] + nums[1] + nums[2] = 3 and 3 is divisible by nums[0], nums[1], and nums[2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Enumeration

We notice that the range of elements in the array `nums` is $[1, 100]$. Therefore, we can enumerate three numbers $a, b, c$, where $a, b, c \in [1, 100]$, and then determine whether $a + b + c$ can only be divided by one of $a, b, c$. If so, we can calculate the number of single-factor triples with $a, b, c$ as elements. The specific calculation method is as follows:

-   If $a = b$, then the number of single-factor triples with $a, b, c$ as elements is $x \times (x - 1) \times z$, where $x$, $y$, $z$ represent the number of occurrences of $a$, $b$, $c$ in the array `nums` respectively.
-   If $a = c$, then the number of single-factor triples with $a, b, c$ as elements is $x \times (x - 1) \times y$.
-   If $b = c$, then the number of single-factor triples with $a, b, c$ as elements is $x \times y \times (y - 1)$.
-   If $a, b, c$ are all different, then the number of single-factor triples with $a, b, c$ as elements is $x \times y \times z$.

Finally, we add up the numbers of all single-factor triples.

The time complexity is $O(M^3)$, and the space complexity is $O(M)$. Where $M$ is the range of elements in the array `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleDivisorTriplet(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        ans = 0
        for a, x in cnt.items():
            for b, y in cnt.items():
                for c, z in cnt.items():
                    s = a + b + c
                    if sum(s % v == 0 for v in (a, b, c)) == 1:
                        if a == b:
                            ans += x * (x - 1) * z
                        elif a == c:
                            ans += x * (x - 1) * y
                        elif b == c:
                            ans += x * y * (y - 1)
                        else:
                            ans += x * y * z
        return ans
```

#### Java

```java
class Solution {
    public long singleDivisorTriplet(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        long ans = 0;
        for (int a = 1; a <= 100; ++a) {
            for (int b = 1; b <= 100; ++b) {
                for (int c = 1; c <= 100; ++c) {
                    int s = a + b + c;
                    int x = cnt[a], y = cnt[b], z = cnt[c];
                    int t = 0;
                    t += s % a == 0 ? 1 : 0;
                    t += s % b == 0 ? 1 : 0;
                    t += s % c == 0 ? 1 : 0;
                    if (t == 1) {
                        if (a == b) {
                            ans += 1L * x * (x - 1) * z;
                        } else if (a == c) {
                            ans += 1L * x * (x - 1) * y;
                        } else if (b == c) {
                            ans += 1L * x * y * (y - 1);
                        } else {
                            ans += 1L * x * y * z;
                        }
                    }
                }
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
    long long singleDivisorTriplet(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            ++cnt[x];
        }
        long long ans = 0;
        for (int a = 1; a <= 100; ++a) {
            for (int b = 1; b <= 100; ++b) {
                for (int c = 1; c <= 100; ++c) {
                    int s = a + b + c;
                    int x = cnt[a], y = cnt[b], z = cnt[c];
                    int t = (s % a == 0) + (s % b == 0) + (s % c == 0);
                    if (t == 1) {
                        if (a == b) {
                            ans += 1LL * x * (x - 1) * z;
                        } else if (a == c) {
                            ans += 1LL * x * (x - 1) * y;
                        } else if (b == c) {
                            ans += 1LL * x * y * (y - 1);
                        } else {
                            ans += 1LL * x * y * z;
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func singleDivisorTriplet(nums []int) (ans int64) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	f := func(a, b int) int {
		if a%b == 0 {
			return 1
		}
		return 0
	}
	for a := 1; a <= 100; a++ {
		for b := 1; b <= 100; b++ {
			for c := 1; c <= 100; c++ {
				s := a + b + c
				t := f(s, a) + f(s, b) + f(s, c)
				if t == 1 {
					if a == b {
						ans += int64(cnt[a] * (cnt[a] - 1) * cnt[c])
					} else if a == c {
						ans += int64(cnt[a] * (cnt[a] - 1) * cnt[b])
					} else if b == c {
						ans += int64(cnt[b] * (cnt[b] - 1) * cnt[a])
					} else {
						ans += int64(cnt[a] * cnt[b] * cnt[c])
					}
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function singleDivisorTriplet(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    let ans = 0;
    const f = (a: number, b: number) => (a % b === 0 ? 1 : 0);
    for (let a = 1; a <= 100; ++a) {
        for (let b = 1; b <= 100; ++b) {
            for (let c = 1; c <= 100; ++c) {
                const s = a + b + c;
                const t = f(s, a) + f(s, b) + f(s, c);
                if (t === 1) {
                    if (a === b) {
                        ans += cnt[a] * (cnt[a] - 1) * cnt[c];
                    } else if (a === c) {
                        ans += cnt[a] * (cnt[a] - 1) * cnt[b];
                    } else if (b === c) {
                        ans += cnt[b] * (cnt[b] - 1) * cnt[a];
                    } else {
                        ans += cnt[a] * cnt[b] * cnt[c];
                    }
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
