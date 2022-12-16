# [2505. Bitwise OR of All Subsequence Sums](https://leetcode.com/problems/bitwise-or-of-all-subsequence-sums)

[中文文档](/solution/2500-2599/2505.Bitwise%20OR%20of%20All%20Subsequence%20Sums/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the value of the bitwise </em><strong>OR</strong><em> of the sum of all possible <strong>subsequences</strong> in the array</em>.</p>

<p>A <strong>subsequence</strong> is a sequence that can be derived from another sequence by removing zero or more elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,0,3]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All possible subsequence sums that we can have are: 0, 1, 2, 3, 4, 5, 6.
And we have 0 OR 1 OR 2 OR 3 OR 4 OR 5 OR 6 = 7, so we return 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> 0 is the only possible subsequence sum we can have, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subsequenceSumOr(self, nums: List[int]) -> int:
        cnt = [0] * 64
        ans = 0
        for v in nums:
            for i in range(31):
                if (v >> i) & 1:
                    cnt[i] += 1
        for i in range(63):
            if cnt[i]:
                ans |= 1 << i
            cnt[i + 1] += cnt[i] // 2
        return ans
```

### **Java**

```java
class Solution {
    public long subsequenceSumOr(int[] nums) {
        long[] cnt = new long[64];
        long ans = 0;
        for (int v : nums) {
            for (int i = 0; i < 31; ++i) {
                if (((v >> i) & 1) == 1) {
                    ++cnt[i];
                }
            }
        }
        for (int i = 0; i < 63; ++i) {
            if (cnt[i] > 0) {
                ans |= 1l << i;
            }
            cnt[i + 1] += cnt[i] / 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long subsequenceSumOr(vector<int>& nums) {
        vector<long long> cnt(64);
        long long ans = 0;
        for (int v : nums) {
            for (int i = 0; i < 31; ++i) {
                if (v >> i & 1) {
                    ++cnt[i];
                }
            }
        }
        for (int i = 0; i < 63; ++i) {
            if (cnt[i]) {
                ans |= 1ll << i;
            }
            cnt[i + 1] += cnt[i] / 2;
        }
        return ans;
    }
};
```

### **Go**

```go
func subsequenceSumOr(nums []int) int64 {
	cnt := make([]int, 64)
	ans := 0
	for _, v := range nums {
		for i := 0; i < 31; i++ {
			if v>>i&1 == 1 {
				cnt[i]++
			}
		}
	}
	for i := 0; i < 63; i++ {
		if cnt[i] > 0 {
			ans |= 1 << i
		}
		cnt[i+1] += cnt[i] / 2
	}
	return int64(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
