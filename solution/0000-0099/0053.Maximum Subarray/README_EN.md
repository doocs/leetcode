# [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray)

[中文文档](/solution/0000-0099/0053.Maximum%20Subarray/README.md)

<!-- tags:Array,Divide and Conquer,Dynamic Programming -->

## Description

<p>Given an integer array <code>nums</code>, find the <span data-keyword="subarray-nonempty">subarray</span> with the largest sum, and return <em>its sum</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The subarray [4,-1,2,1] has the largest sum 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The subarray [1] has the largest sum 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,-1,7,8]
<strong>Output:</strong> 23
<strong>Explanation:</strong> The subarray [5,4,-1,7,8] has the largest sum 23.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution using the <strong>divide and conquer</strong> approach, which is more subtle.</p>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i]$ to represent the maximum sum of the continuous subarray ending with the element $nums[i]$. Initially, $f[0] = nums[0]$. The final answer we are looking for is $\max_{0 \leq i < n} f[i]$.

Consider $f[i]$, where $i \geq 1$, its state transition equation is:

$$
f[i] = \max \{ f[i - 1] + nums[i], nums[i] \}
$$

Which is also:

$$
f[i] = \max \{ f[i - 1], 0 \} + nums[i]
$$

Since $f[i]$ is only related to $f[i - 1]$, we can use a single variable $f$ to maintain the current value of $f[i]$, and then perform state transition. The answer is $\max_{0 \leq i < n} f$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. We only need to traverse the array once to get the answer. The space complexity is $O(1)$, we only need constant space to store several variables.

<!-- tabs:start -->

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans = f = nums[0]
        for x in nums[1:]:
            f = max(f, 0) + x
            ans = max(ans, f)
        return ans
```

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        for (int i = 1, f = nums[0]; i < nums.length; ++i) {
            f = Math.max(f, 0) + nums[i];
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int ans = nums[0], f = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            f = max(f, 0) + nums[i];
            ans = max(ans, f);
        }
        return ans;
    }
};
```

```go
func maxSubArray(nums []int) int {
	ans, f := nums[0], nums[0]
	for _, x := range nums[1:] {
		f = max(f, 0) + x
		ans = max(ans, f)
	}
	return ans
}
```

```ts
function maxSubArray(nums: number[]): number {
    let [ans, f] = [nums[0], nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        f = Math.max(f, 0) + nums[i];
        ans = Math.max(ans, f);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = nums[0];
        let mut f = nums[0];
        for i in 1..n {
            f = f.max(0) + nums[i];
            ans = ans.max(f);
        }
        ans
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let [ans, f] = [nums[0], nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        f = Math.max(f, 0) + nums[i];
        ans = Math.max(ans, f);
    }
    return ans;
};
```

```cs
public class Solution {
    public int MaxSubArray(int[] nums) {
        int ans = nums[0], f = nums[0];
        for (int i = 1; i < nums.Length; ++i) {
            f = Math.Max(f, 0) + nums[i];
            ans = Math.Max(ans, f);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        def crossMaxSub(nums, left, mid, right):
            lsum = rsum = 0
            lmx = rmx = -inf
            for i in range(mid, left - 1, -1):
                lsum += nums[i]
                lmx = max(lmx, lsum)
            for i in range(mid + 1, right + 1):
                rsum += nums[i]
                rmx = max(rmx, rsum)
            return lmx + rmx

        def maxSub(nums, left, right):
            if left == right:
                return nums[left]
            mid = (left + right) >> 1
            lsum = maxSub(nums, left, mid)
            rsum = maxSub(nums, mid + 1, right)
            csum = crossMaxSub(nums, left, mid, right)
            return max(lsum, rsum, csum)

        left, right = 0, len(nums) - 1
        return maxSub(nums, left, right)
```

```java
class Solution {
    public int maxSubArray(int[] nums) {
        return maxSub(nums, 0, nums.length - 1);
    }

    private int maxSub(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) >>> 1;
        int lsum = maxSub(nums, left, mid);
        int rsum = maxSub(nums, mid + 1, right);
        return Math.max(Math.max(lsum, rsum), crossMaxSub(nums, left, mid, right));
    }

    private int crossMaxSub(int[] nums, int left, int mid, int right) {
        int lsum = 0, rsum = 0;
        int lmx = Integer.MIN_VALUE, rmx = Integer.MIN_VALUE;
        for (int i = mid; i >= left; --i) {
            lsum += nums[i];
            lmx = Math.max(lmx, lsum);
        }
        for (int i = mid + 1; i <= right; ++i) {
            rsum += nums[i];
            rmx = Math.max(rmx, rsum);
        }
        return lmx + rmx;
    }
}
```

<!-- tabs:end -->

<!-- end -->
