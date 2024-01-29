# [3022. Minimize OR of Remaining Elements Using Operations](https://leetcode.com/problems/minimize-or-of-remaining-elements-using-operations)

[中文文档](/solution/3000-3099/3022.Minimize%20OR%20of%20Remaining%20Elements%20Using%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>In one operation, you can pick any index <code>i</code> of <code>nums</code> such that <code>0 &lt;= i &lt; nums.length - 1</code> and replace <code>nums[i]</code> and <code>nums[i + 1]</code> with a single occurrence of <code>nums[i] &amp; nums[i + 1]</code>, where <code>&amp;</code> represents the bitwise <code>AND</code> operator.</p>

<p>Return <em>the <strong>minimum</strong> possible value of the bitwise </em><code>OR</code><em> of the remaining elements of</em> <code>nums</code> <em>after applying <strong>at most</strong></em> <code>k</code> <em>operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,3,2,7], k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> Let&#39;s do the following operations:
1. Replace nums[0] and nums[1] with (nums[0] &amp; nums[1]) so that nums becomes equal to [1,3,2,7].
2. Replace nums[2] and nums[3] with (nums[2] &amp; nums[3]) so that nums becomes equal to [1,3,2].
The bitwise-or of the final array is 3.
It can be shown that 3 is the minimum possible value of the bitwise OR of the remaining elements of nums after applying at most k operations.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,3,15,14,2,8], k = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> Let&#39;s do the following operations:
1. Replace nums[0] and nums[1] with (nums[0] &amp; nums[1]) so that nums becomes equal to [3,15,14,2,8]. 
2. Replace nums[0] and nums[1] with (nums[0] &amp; nums[1]) so that nums becomes equal to [3,14,2,8].
3. Replace nums[0] and nums[1] with (nums[0] &amp; nums[1]) so that nums becomes equal to [2,2,8].
4. Replace nums[1] and nums[2] with (nums[1] &amp; nums[2]) so that nums becomes equal to [2,0].
The bitwise-or of the final array is 2.
It can be shown that 2 is the minimum possible value of the bitwise OR of the remaining elements of nums after applying at most k operations.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,7,10,3,9,14,9,4], k = 1
<strong>Output:</strong> 15
<strong>Explanation:</strong> Without applying any operations, the bitwise-or of nums is 15.
It can be shown that 15 is the minimum possible value of the bitwise OR of the remaining elements of nums after applying at most k operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>30</sup></code></li>
	<li><code>0 &lt;= k &lt; nums.length</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minOrAfterOperations(self, nums: List[int], k: int) -> int:
        ans = 0
        rans = 0
        for i in range(29, -1, -1):
            test = ans + (1 << i)
            cnt = 0
            val = 0
            for num in nums:
                if val == 0:
                    val = test & num
                else:
                    val &= test & num
                if val:
                    cnt += 1
            if cnt > k:
                rans += (1 << i)
            else:
                ans += (1 << i)
        return rans
```

```java
class Solution {
    public int minOrAfterOperations(int[] nums, int k) {
        int ans = 0, rans = 0;
        for (int i = 29; i >= 0; i--) {
            int test = ans + (1 << i);
            int cnt = 0;
            int val = 0;
            for (int num : nums) {
                if (val == 0) {
                    val = test & num;
                } else {
                    val &= test & num;
                }
                if (val != 0) {
                    cnt++;
                }
            }
            if (cnt > k) {
                rans += (1 << i);
            } else {
                ans += (1 << i);
            }
        }
        return rans;
    }
}
```

```cpp
class Solution {
public:
    int minOrAfterOperations(vector<int>& nums, int k) {
        int ans = 0, rans = 0;
        for (int i = 29; i >= 0; i--) {
            int test = ans + (1 << i);
            int cnt = 0;
            int val = 0;
            for (auto it : nums) {
                if (val == 0) {
                    val = test & it;
                } else {
                    val &= test & it;
                }
                if (val) {
                    cnt++;
                }
            }
            if (cnt > k) {
                rans += (1 << i);
            } else {
                ans += (1 << i);
            }
        }
        return rans;
    }
};
```

```go
func minOrAfterOperations(nums []int, k int) int {
    ans := 0
    rans := 0
    for i := 29; i >= 0; i-- {
        test := ans + (1 << i)
        cnt := 0
        val := 0
        for _, num := range nums {
            if val == 0 {
                val = test & num
            } else {
                val &= test & num
            }
            if val != 0 {
                cnt++
            }
        }
        if cnt > k {
            rans += (1 << i)
        } else {
            ans += (1 << i)
        }
    }
    return rans
}
```

<!-- tabs:end -->

<!-- end -->
