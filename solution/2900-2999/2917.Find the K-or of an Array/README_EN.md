# [2917. Find the K-or of an Array](https://leetcode.com/problems/find-the-k-or-of-an-array)

[中文文档](/solution/2900-2999/2917.Find%20the%20K-or%20of%20an%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>, and an integer <code>k</code>.</p>

<p>The <strong>K-or</strong> of <code>nums</code> is a non-negative integer that satisfies the following:</p>

<ul>
	<li>The <code>i<sup>th</sup></code> bit is set in the K-or <strong>if and only if</strong> there are at least <code>k</code> elements of nums in which bit <code>i</code> is set.</li>
</ul>

<p>Return <em>the <strong> K-or</strong> of</em> <code>nums</code>.</p>

<p><strong>Note</strong> that a bit <code>i</code> is set in <code>x</code> if <code>(2<sup>i</sup> AND x) == 2<sup>i</sup></code>, where <code>AND</code> is the bitwise <code>AND</code> operator.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,12,9,8,9,15], k = 4
<strong>Output:</strong> 9
<strong>Explanation:</strong> Bit 0 is set at nums[0], nums[2], nums[4], and nums[5].
Bit 1 is set at nums[0], and nums[5].
Bit 2 is set at nums[0], nums[1], and nums[5].
Bit 3 is set at nums[1], nums[2], nums[3], nums[4], and nums[5].
Only bits 0 and 3 are set in at least k elements of the array, and bits i &gt;= 4 are not set in any of the array&#39;s elements. Hence, the answer is 2^0 + 2^3 = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,12,1,11,4,5], k = 6
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since k == 6 == nums.length, the 6-or of the array is equal to the bitwise AND of all its elements. Hence, the answer is 2 AND 12 AND 1 AND 11 AND 4 AND 5 = 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,8,5,9,11,6,8], k = 1
<strong>Output:</strong> 15
<strong>Explanation:</strong> Since k == 1, the 1-or of the array is equal to the bitwise OR of all its elements. Hence, the answer is 10 OR 8 OR 5 OR 9 OR 11 OR 6 OR 8 = 15.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>31</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## Solutions

**Solution 1: Enumeration**

We can enumerate each bit $i$ in the range $[0, 32)$, and count the number of numbers in the array $nums$ whose $i$-th bit is $1$, denoted as $cnt$. If $cnt \ge k$, we add $2^i$ to the answer.

After the enumeration, we return the answer.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length of the array $nums$ and the maximum value in $nums$, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findKOr(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(32):
            cnt = sum(x >> i & 1 for x in nums)
            if cnt >= k:
                ans |= 1 << i
        return ans
```

### **Java**

```java
class Solution {
    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int x : nums) {
                cnt += (x >> i & 1);
            }
            if (cnt >= k) {
                ans |= 1 << i;
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
    int findKOr(vector<int>& nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int x : nums) {
                cnt += (x >> i & 1);
            }
            if (cnt >= k) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findKOr(nums []int, k int) (ans int) {
	for i := 0; i < 32; i++ {
		cnt := 0
		for _, x := range nums {
			cnt += (x >> i & 1)
		}
		if cnt >= k {
			ans |= 1 << i
		}
	}
	return
}
```

### **TypeScript**

```ts
function findKOr(nums: number[], k: number): number {
    let ans = 0;
    for (let i = 0; i < 32; ++i) {
        let cnt = 0;
        for (const x of nums) {
            cnt += (x >> i) & 1;
        }
        if (cnt >= k) {
            ans |= 1 << i;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
