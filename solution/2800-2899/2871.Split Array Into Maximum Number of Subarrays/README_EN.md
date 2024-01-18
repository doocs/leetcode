# [2871. Split Array Into Maximum Number of Subarrays](https://leetcode.com/problems/split-array-into-maximum-number-of-subarrays/)

[中文文档](/solution/2800-2899/2871.Split%20Array%20Into%20Maximum%20Number%20of%20Subarrays/README.md)

## Description

<p>You are given an array <code>nums</code> consisting of <strong>non-negative</strong> integers.</p>

<p>We define the score of subarray <code>nums[l..r]</code> such that <code>l &lt;= r</code> as <code>nums[l] AND nums[l + 1] AND ... AND nums[r]</code> where <strong>AND</strong> is the bitwise <code>AND</code> operation.</p>

<p>Consider splitting the array into one or more subarrays such that the following conditions are satisfied:</p>

<ul>
	<li><strong>E</strong><strong>ach</strong> element of the array belongs to <strong>exactly</strong> one subarray.</li>
	<li>The sum of scores of the subarrays is the <strong>minimum</strong> possible.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of subarrays in a split that satisfies the conditions above.</em></p>

<p>A <strong>subarray</strong> is a contiguous part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,2,0,1,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can split the array into the following subarrays:
- [1,0]. The score of this subarray is 1 AND 0 = 0.
- [2,0]. The score of this subarray is 2 AND 0 = 0.
- [1,2]. The score of this subarray is 1 AND 2 = 0.
The sum of scores is 0 + 0 + 0 = 0, which is the minimum possible score that we can obtain.
It can be shown that we cannot split the array into more than 3 subarrays with a total score of 0. So we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,7,1,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can split the array into one subarray: [5,7,1,3] with a score of 1, which is the minimum possible score that we can obtain.
It can be shown that we cannot split the array into more than 1 subarray with a total score of 1. So we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy + Bitwise Operation

We initialize a variable $score$ to record the score of the current subarray, and set $score=-1$ initially. Then we traverse the array, for each element $num$, we perform a bitwise AND operation between $score$ and $num$, and assign the result to $score$. If $score=0$, it means the score of the current subarray is 0, so we can split the current subarray and reset $score$ to $-1$. Finally, we return the number of split subarrays.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maxSubarrays(self, nums: List[int]) -> int:
        score, ans = -1, 1
        for num in nums:
            score &= num
            if score == 0:
                score = -1
                ans += 1
        return 1 if ans == 1 else ans - 1
```

```java
class Solution {
    public int maxSubarrays(int[] nums) {
        int score = -1;
        int ans = 1;
        for (int num : nums) {
            score &= num;
            if (score == 0) {
                ans++;
                score = -1;
            }
        }
        return ans == 1 ? 1 : ans - 1;
    }
}
```

```cpp
class Solution {
public:
    int maxSubarrays(vector<int>& nums) {
        int score = -1, ans = 1;
        for (int num : nums) {
            score &= num;
            if (score == 0) {
                --score;
                ++ans;
            }
        }
        return ans == 1 ? 1 : ans - 1;
    }
};
```

```go
func maxSubarrays(nums []int) int {
	ans, score := 1, -1
	for _, num := range nums {
		score &= num
		if score == 0 {
			score--
			ans++
		}
	}
	if ans == 1 {
		return 1
	}
	return ans - 1
}
```

```ts
function maxSubarrays(nums: number[]): number {
    let [ans, score] = [1, -1];
    for (const num of nums) {
        score &= num;
        if (score === 0) {
            --score;
            ++ans;
        }
    }
    return ans == 1 ? 1 : ans - 1;
}
```

<!-- tabs:end -->

<!-- end -->
