# [1994. The Number of Good Subsets](https://leetcode.com/problems/the-number-of-good-subsets)

[中文文档](/solution/1900-1999/1994.The%20Number%20of%20Good%20Subsets/README.md)

## Description

<p>You are given an integer array <code>nums</code>. We call a subset of <code>nums</code> <strong>good</strong> if its product can be represented as a product of one or more <strong>distinct prime</strong> numbers.</p>

<ul>
	<li>For example, if <code>nums = [1, 2, 3, 4]</code>:
    <ul>
    	<li><code>[2, 3]</code>, <code>[1, 2, 3]</code>, and <code>[1, 3]</code> are <strong>good</strong> subsets with products <code>6 = 2*3</code>, <code>6 = 2*3</code>, and <code>3 = 3</code> respectively.</li>
    	<li><code>[1, 4]</code> and <code>[4]</code> are not <strong>good</strong> subsets with products <code>4 = 2*2</code> and <code>4 = 2*2</code> respectively.</li>
    </ul>
    </li>
</ul>

<p>Return <em>the number of different <strong>good</strong> subsets in </em><code>nums</code><em> <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>subset</strong> of <code>nums</code> is any array that can be obtained by deleting some (possibly none or all) elements from <code>nums</code>. Two subsets are different if and only if the chosen indices to delete are different.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The good subsets are:
- [1,2]: product is 2, which is the product of distinct prime 2.
- [1,2,3]: product is 6, which is the product of distinct primes 2 and 3.
- [1,3]: product is 3, which is the product of distinct prime 3.
- [2]: product is 2, which is the product of distinct prime 2.
- [2,3]: product is 6, which is the product of distinct primes 2 and 3.
- [3]: product is 3, which is the product of distinct prime 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,3,15]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The good subsets are:
- [2]: product is 2, which is the product of distinct prime 2.
- [2,3]: product is 6, which is the product of distinct primes 2 and 3.
- [2,15]: product is 30, which is the product of distinct primes 2, 3, and 5.
- [3]: product is 3, which is the product of distinct prime 3.
- [15]: product is 15, which is the product of distinct primes 3 and 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 30</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfGoodSubsets(self, nums: List[int]) -> int:
        counter = Counter(nums)
        mod = 10**9 + 7
        prime = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        n = len(prime)
        dp = [0] * (1 << n)
        dp[0] = 1
        for x in range(2, 31):
            if counter[x] == 0 or x % 4 == 0 or x % 9 == 0 or x % 25 == 0:
                continue
            mask = 0
            for i, p in enumerate(prime):
                if x % p == 0:
                    mask |= 1 << i
            for state in range(1 << n):
                if mask & state:
                    continue
                dp[mask | state] = (dp[mask | state] + counter[x] * dp[state]) % mod
        ans = 0
        for i in range(1, 1 << n):
            ans = (ans + dp[i]) % mod
        for i in range(counter[1]):
            ans = (ans << 1) % mod
        return ans
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfGoodSubsets(int[] nums) {
        int[] counter = new int[31];
        for (int x : nums) {
            ++counter[x];
        }
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int n = prime.length;
        long[] dp = new long[1 << n];
        dp[0] = 1;
        for (int x = 2; x <= 30; ++x) {
            if (counter[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) {
                continue;
            }
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                if (x % prime[i] == 0) {
                    mask |= (1 << i);
                }
            }
            for (int state = 0; state < 1 << n; ++state) {
                if ((mask & state) > 0) {
                    continue;
                }
                dp[mask | state] = (dp[mask | state] + counter[x] * dp[state]) % MOD;
            }
        }
        long ans = 0;
        for (int i = 1; i < 1 << n; ++i) {
            ans = (ans + dp[i]) % MOD;
        }
        while (counter[1]-- > 0) {
            ans = (ans << 1) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfGoodSubsets(vector<int>& nums) {
        vector<int> counter(31);
        for (int& x : nums) ++counter[x];
        vector<int> prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        const int MOD = 1e9 + 7;
        int n = prime.size();
        vector<long long> dp(1 << n);
        dp[0] = 1;
        for (int x = 2; x <= 30; ++x) {
            if (counter[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) continue;
            int mask = 0;
            for (int i = 0; i < n; ++i)
                if (x % prime[i] == 0)
                    mask |= (1 << i);
            for (int state = 0; state < 1 << n; ++state) {
                if ((mask & state) > 0) continue;
                dp[mask | state] = (dp[mask | state] + counter[x] * dp[state]) % MOD;
            }
        }
        long long ans = 0;
        for (int i = 1; i < 1 << n; ++i) ans = (ans + dp[i]) % MOD;
        while (counter[1]--) ans = (ans << 1) % MOD;
        return (int)ans;
    }
};
```

### **Go**

```go
func numberOfGoodSubsets(nums []int) int {
	counter := make([]int, 31)
	for _, x := range nums {
		counter[x]++
	}
	const mod int = 1e9 + 7
	prime := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}
	n := len(prime)
	dp := make([]int, 1<<n)
	dp[0] = 1
	for x := 2; x <= 30; x++ {
		if counter[x] == 0 || x%4 == 0 || x%9 == 0 || x%25 == 0 {
			continue
		}
		mask := 0
		for i, p := range prime {
			if x%p == 0 {
				mask |= (1 << i)
			}
		}
		for state := 0; state < 1<<n; state++ {
			if (mask & state) > 0 {
				continue
			}
			dp[mask|state] = (dp[mask|state] + counter[x]*dp[state]) % mod
		}
	}
	ans := 0
	for i := 1; i < 1<<n; i++ {
		ans = (ans + dp[i]) % mod
	}
	for counter[1] > 0 {
		ans = (ans << 1) % mod
		counter[1]--
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
