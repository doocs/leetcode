# [1862. Sum of Floored Pairs](https://leetcode.com/problems/sum-of-floored-pairs)

[中文文档](/solution/1800-1899/1862.Sum%20of%20Floored%20Pairs/README.md)

## Description

<p>Given an integer array <code>nums</code>, return the sum of <code>floor(nums[i] / nums[j])</code> for all pairs of indices <code>0 &lt;= i, j &lt; nums.length</code> in the array. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>The <code>floor()</code> function returns the integer part of the division.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,5,9]
<strong>Output:</strong> 10
<strong>Explanation:</strong>
floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
floor(5 / 2) = 2
floor(9 / 2) = 4
floor(9 / 5) = 1
We calculate the floor of the division for every pair of indices in the array then sum them up.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,7,7,7,7,7,7]
<strong>Output:</strong> 49
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Prefix Sum of Value Range + Optimized Enumeration

First, we count the occurrences of each element in the array $nums$ and record them in the array $cnt$. Then, we calculate the prefix sum of the array $cnt$ and record it in the array $s$, i.e., $s[i]$ represents the count of elements less than or equal to $i$.

Next, we enumerate the denominator $y$ and the quotient $d$. Using the prefix sum array, we can calculate the count of the numerator $s[\min(mx, d \times y + y - 1)] - s[d \times y - 1]$, where $mx$ represents the maximum value in the array $nums$. Then, we multiply the count of the numerator by the count of the denominator $cnt[y]$, and then multiply by the quotient $d$. This gives us the value of all fractions that meet the conditions. By summing these values, we can get the answer.

The time complexity is $O(M \times \log M)$, and the space complexity is $O(M)$. Here, $M$ is the maximum value in the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def sumOfFlooredPairs(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        cnt = Counter(nums)
        mx = max(nums)
        s = [0] * (mx + 1)
        for i in range(1, mx + 1):
            s[i] = s[i - 1] + cnt[i]
        ans = 0
        for y in range(1, mx + 1):
            if cnt[y]:
                d = 1
                while d * y <= mx:
                    ans += cnt[y] * d * (s[min(mx, d * y + y - 1)] - s[d * y - 1])
                    ans %= mod
                    d += 1
        return ans
```

```java
class Solution {
    public int sumOfFlooredPairs(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        int[] s = new int[mx + 1];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int i = 1; i <= mx; ++i) {
            s[i] = s[i - 1] + cnt[i];
        }
        long ans = 0;
        for (int y = 1; y <= mx; ++y) {
            if (cnt[y] > 0) {
                for (int d = 1; d * y <= mx; ++d) {
                    ans += 1L * cnt[y] * d * (s[Math.min(mx, d * y + y - 1)] - s[d * y - 1]);
                    ans %= mod;
                }
            }
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int sumOfFlooredPairs(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int mx = *max_element(nums.begin(), nums.end());
        vector<int> cnt(mx + 1);
        vector<int> s(mx + 1);
        for (int x : nums) {
            ++cnt[x];
        }
        for (int i = 1; i <= mx; ++i) {
            s[i] = s[i - 1] + cnt[i];
        }
        long long ans = 0;
        for (int y = 1; y <= mx; ++y) {
            if (cnt[y]) {
                for (int d = 1; d * y <= mx; ++d) {
                    ans += 1LL * cnt[y] * d * (s[min(mx, d * y + y - 1)] - s[d * y - 1]);
                    ans %= mod;
                }
            }
        }
        return ans;
    }
};
```

```go
func sumOfFlooredPairs(nums []int) (ans int) {
	mx := slices.Max(nums)
	cnt := make([]int, mx+1)
	s := make([]int, mx+1)
	for _, x := range nums {
		cnt[x]++
	}
	for i := 1; i <= mx; i++ {
		s[i] = s[i-1] + cnt[i]
	}
	const mod int = 1e9 + 7
	for y := 1; y <= mx; y++ {
		if cnt[y] > 0 {
			for d := 1; d*y <= mx; d++ {
				ans += d * cnt[y] * (s[min((d+1)*y-1, mx)] - s[d*y-1])
				ans %= mod
			}
		}
	}
	return
}
```

```ts
function sumOfFlooredPairs(nums: number[]): number {
    const mx = Math.max(...nums);
    const cnt: number[] = Array(mx + 1).fill(0);
    const s: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let i = 1; i <= mx; ++i) {
        s[i] = s[i - 1] + cnt[i];
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (let y = 1; y <= mx; ++y) {
        if (cnt[y]) {
            for (let d = 1; d * y <= mx; ++d) {
                ans += cnt[y] * d * (s[Math.min((d + 1) * y - 1, mx)] - s[d * y - 1]);
                ans %= mod;
            }
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn sum_of_floored_pairs(nums: Vec<i32>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut mx = 0;
        for &x in nums.iter() {
            mx = mx.max(x);
        }

        let mut cnt = vec![0; (mx + 1) as usize];
        let mut s = vec![0; (mx + 1) as usize];

        for &x in nums.iter() {
            cnt[x as usize] += 1;
        }

        for i in 1..=mx as usize {
            s[i] = s[i - 1] + cnt[i];
        }

        let mut ans = 0;
        for y in 1..=mx as usize {
            if cnt[y] > 0 {
                let mut d = 1;
                while d * y <= (mx as usize) {
                    ans +=
                        ((cnt[y] as i64) *
                            (d as i64) *
                            (s[std::cmp::min(mx as usize, d * y + y - 1)] - s[d * y - 1])) %
                        (MOD as i64);
                    ans %= MOD as i64;
                    d += 1;
                }
            }
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
