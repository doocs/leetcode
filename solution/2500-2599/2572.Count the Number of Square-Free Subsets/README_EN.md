# [2572. Count the Number of Square-Free Subsets](https://leetcode.com/problems/count-the-number-of-square-free-subsets)

[中文文档](/solution/2500-2599/2572.Count%20the%20Number%20of%20Square-Free%20Subsets/README.md)

## Description

<p>You are given a positive integer <strong>0-indexed</strong>&nbsp;array <code>nums</code>.</p>

<p>A subset of the array <code>nums</code> is <strong>square-free</strong> if the product of its elements is a <strong>square-free integer</strong>.</p>

<p>A <strong>square-free integer</strong> is an integer that is divisible by no square number other than <code>1</code>.</p>

<p>Return <em>the number of square-free non-empty subsets of the array</em> <strong>nums</strong>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>non-empty</strong>&nbsp;<strong>subset</strong> of <code>nums</code> is an array that can be obtained by deleting some (possibly none but not all) elements from <code>nums</code>. Two subsets are different if and only if the chosen indices to delete are different.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,4,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 square-free subsets in this example:
- The subset consisting of the 0<sup>th</sup> element [3]. The product of its elements is 3, which is a square-free integer.
- The subset consisting of the 3<sup>rd</sup> element [5]. The product of its elements is 5, which is a square-free integer.
- The subset consisting of 0<sup>th</sup> and 3<sup>rd</sup> elements [3,5]. The product of its elements is 15, which is a square-free integer.
It can be proven that there are no more than 3 square-free subsets in the given array.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is 1 square-free subset in this example:
- The subset consisting of the 0<sup>th</sup> element [1]. The product of its elements is 1, which is a square-free integer.
It can be proven that there is no more than 1 square-free subset in the given array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 30</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def squareFreeSubsets(self, nums: List[int]) -> int:
        primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        cnt = Counter(nums)
        mod = 10**9 + 7
        n = len(primes)
        f = [0] * (1 << n)
        f[0] = pow(2, cnt[1])
        for x in range(2, 31):
            if cnt[x] == 0 or x % 4 == 0 or x % 9 == 0 or x % 25 == 0:
                continue
            mask = 0
            for i, p in enumerate(primes):
                if x % p == 0:
                    mask |= 1 << i
            for state in range((1 << n) - 1, 0, -1):
                if state & mask == mask:
                    f[state] = (f[state] + cnt[x] * f[state ^ mask]) % mod
        return sum(v for v in f) % mod - 1
```

### **Java**

```java
class Solution {
    public int squareFreeSubsets(int[] nums) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int[] cnt = new int[31];
        for (int x : nums) {
            ++cnt[x];
        }
        final int mod = (int) 1e9 + 7;
        int n = primes.length;
        long[] f = new long[1 << n];
        f[0] = 1;
        for (int i = 0; i < cnt[1]; ++i) {
            f[0] = (f[0] * 2) % mod;
        }
        for (int x = 2; x < 31; ++x) {
            if (cnt[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) {
                continue;
            }
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                if (x % primes[i] == 0) {
                    mask |= 1 << i;
                }
            }
            for (int state = (1 << n) - 1; state > 0; --state) {
                if ((state & mask) == mask) {
                    f[state] = (f[state] + cnt[x] * f[state ^ mask]) % mod;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 1 << n; ++i) {
            ans = (ans + f[i]) % mod;
        }
        ans -= 1;
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int squareFreeSubsets(vector<int>& nums) {
        int primes[10] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int cnt[31]{};
        for (int& x : nums) {
            ++cnt[x];
        }
        int n = 10;
        const int mod = 1e9 + 7;
        vector<long long> f(1 << n);
        f[0] = 1;
        for (int i = 0; i < cnt[1]; ++i) {
            f[0] = f[0] * 2 % mod;
        }
        for (int x = 2; x < 31; ++x) {
            if (cnt[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) {
                continue;
            }
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                if (x % primes[i] == 0) {
                    mask |= 1 << i;
                }
            }
            for (int state = (1 << n) - 1; state; --state) {
                if ((state & mask) == mask) {
                    f[state] = (f[state] + 1LL * cnt[x] * f[state ^ mask]) % mod;
                }
            }
        }
        long long ans = -1;
        for (int i = 0; i < 1 << n; ++i) {
            ans = (ans + f[i]) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func squareFreeSubsets(nums []int) (ans int) {
	primes := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}
	cnt := [31]int{}
	for _, x := range nums {
		cnt[x]++
	}
	const mod int = 1e9 + 7
	n := 10
	f := make([]int, 1<<n)
	f[0] = 1
	for i := 0; i < cnt[1]; i++ {
		f[0] = f[0] * 2 % mod
	}
	for x := 2; x < 31; x++ {
		if cnt[x] == 0 || x%4 == 0 || x%9 == 0 || x%25 == 0 {
			continue
		}
		mask := 0
		for i, p := range primes {
			if x%p == 0 {
				mask |= 1 << i
			}
		}
		for state := 1<<n - 1; state > 0; state-- {
			if state&mask == mask {
				f[state] = (f[state] + f[state^mask]*cnt[x]) % mod
			}
		}
	}
	ans = -1
	for _, v := range f {
		ans = (ans + v) % mod
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
