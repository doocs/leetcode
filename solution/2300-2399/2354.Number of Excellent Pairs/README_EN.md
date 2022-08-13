# [2354. Number of Excellent Pairs](https://leetcode.com/problems/number-of-excellent-pairs)

[中文文档](/solution/2300-2399/2354.Number%20of%20Excellent%20Pairs/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> positive integer array <code>nums</code> and a positive integer <code>k</code>.</p>

<p>A pair of numbers <code>(num1, num2)</code> is called <strong>excellent</strong> if the following conditions are satisfied:</p>

<ul>
	<li><strong>Both</strong> the numbers <code>num1</code> and <code>num2</code> exist in the array <code>nums</code>.</li>
	<li>The sum of the number of set bits in <code>num1 OR num2</code> and <code>num1 AND num2</code> is greater than or equal to <code>k</code>, where <code>OR</code> is the bitwise <strong>OR</strong> operation and <code>AND</code> is the bitwise <strong>AND</strong> operation.</li>
</ul>

<p>Return <em>the number of <strong>distinct</strong> excellent pairs</em>.</p>

<p>Two pairs <code>(a, b)</code> and <code>(c, d)</code> are considered distinct if either <code>a != c</code> or <code>b != d</code>. For example, <code>(1, 2)</code> and <code>(2, 1)</code> are distinct.</p>

<p><strong>Note</strong> that a pair <code>(num1, num2)</code> such that <code>num1 == num2</code> can also be excellent if you have at least <strong>one</strong> occurrence of <code>num1</code> in the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> The excellent pairs are the following:
- (3, 3). (3 AND 3) and (3 OR 3) are both equal to (11) in binary. The total number of set bits is 2 + 2 = 4, which is greater than or equal to k = 3.
- (2, 3) and (3, 2). (2 AND 3) is equal to (10) in binary, and (2 OR 3) is equal to (11) in binary. The total number of set bits is 1 + 2 = 3.
- (1, 3) and (3, 1). (1 AND 3) is equal to (01) in binary, and (1 OR 3) is equal to (11) in binary. The total number of set bits is 1 + 2 = 3.
So the number of excellent pairs is 5.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,1,1], k = 10
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no excellent pairs for this array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 60</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countExcellentPairs(self, nums: List[int], k: int) -> int:
        s = set(nums)
        ans = 0
        cnt = Counter()
        for v in s:
            cnt[v.bit_count()] += 1
        for v in s:
            t = v.bit_count()
            for i, x in cnt.items():
                if t + i >= k:
                    ans += x
        return ans
```

### **Java**

```java
class Solution {
    public long countExcellentPairs(int[] nums, int k) {
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        long ans = 0;
        int[] cnt = new int[32];
        for (int v : s) {
            int t = Integer.bitCount(v);
            ++cnt[t];
        }
        for (int v : s) {
            int t = Integer.bitCount(v);
            for (int i = 0; i < 32; ++i) {
                if (t + i >= k) {
                    ans += cnt[i];
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countExcellentPairs(vector<int>& nums, int k) {
        unordered_set<int> s(nums.begin(), nums.end());
        vector<int> cnt(32);
        for (int v : s) ++cnt[__builtin_popcount(v)];
        long long ans = 0;
        for (int v : s) {
            int t = __builtin_popcount(v);
            for (int i = 0; i < 32; ++i) {
                if (t + i >= k) {
                    ans += cnt[i];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countExcellentPairs(nums []int, k int) int64 {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	cnt := make([]int, 32)
	for v := range s {
		t := bits.OnesCount(uint(v))
		cnt[t]++
	}
	ans := 0
	for v := range s {
		t := bits.OnesCount(uint(v))
		for i, x := range cnt {
			if t+i >= k {
				ans += x
			}
		}
	}
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
