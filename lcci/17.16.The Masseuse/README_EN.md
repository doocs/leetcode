# [17.16. The Masseuse](https://leetcode-cn.com/problems/the-masseuse-lcci)

## Description
<p>A popular masseuse receives a sequence of back-to-back appointment requests and is debating which ones to accept. She needs a break between appointments and therefore she cannot accept any adjacent requests. Given a sequence of back-to-back appoint&shy; ment requests, find the optimal (highest total booked minutes) set the masseuse can honor. Return the number of minutes.</p>



<p><b>Note:&nbsp;</b>This problem is slightly different from the original one in the book.</p>



<p>&nbsp;</p>



<p><strong>Example 1: </strong></p>



<pre>

<strong>Input: </strong> [1,2,3,1]

<strong>Output: </strong> 4

<strong>Explanation: </strong> Accept request 1 and 3, total minutes = 1 + 3 = 4

</pre>



<p><strong>Example 2: </strong></p>



<pre>

<strong>Input: </strong> [2,7,9,3,1]

<strong>Output: </strong> 12

<strong>Explanation: </strong> Accept request 1, 3 and 5, total minutes = 2 + 9 + 1 = 12

</pre>



<p><strong>Example 3: </strong></p>



<pre>

<strong>Input: </strong> [2,1,4,5,3,1,1,3]

<strong>Output: </strong> 12

<strong>Explanation: </strong> Accept request 1, 3, 5 and 8, total minutes = 2 + 4 + 3 + 3 = 12

</pre>




## Solutions


### Python3

```python
class Solution:
    def massage(self, nums: List[int]) -> int:
        if not nums:
            return 0
        n = len(nums)
        if n < 2:
            return nums[0]
        dp = [0 for _ in range(n)]
        dp[0], dp[1] = nums[0], max(nums[0], nums[1])
        for i in range(2, n):
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
        return dp[n - 1]
```

### Java

```java
class Solution {
    public int massage(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return n == 0 ? 0 : nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; ++i) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }
}
```

### ...
```

```
