# [16.17. Contiguous Sequence](https://leetcode.cn/problems/contiguous-sequence-lcci)

[中文文档](/lcci/16.17.Contiguous%20Sequence/README.md)

## Description

<p>You are given an array of integers (both positive and negative). Find the contiguous sequence with the largest sum. Return the sum.</p>

<p><strong>Example: </strong></p>

<pre>



<strong>Input: </strong> [-2,1,-3,4,-1,2,1,-5,4]



<strong>Output: </strong> 6



<strong>Explanation: </strong> [4,-1,2,1] has the largest sum 6.



</pre>

<p><strong>Follow Up: </strong></p>

<p>If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.</p>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i]$ as the maximum sum of a continuous subarray that ends with $nums[i]$. The state transition equation is:

$$
f[i] = \max(f[i-1], 0) + nums[i]
$$

where $f[0] = nums[0]$.

The answer is $\max\limits_{i=0}^{n-1}f[i]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

We notice that $f[i]$ only depends on $f[i-1]$, so we can use a variable $f$ to represent $f[i-1]$, thus reducing the space complexity to $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans = f = -inf
        for x in nums:
            f = max(f, 0) + x
            ans = max(ans, f)
        return ans
```

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE, f = Integer.MIN_VALUE;
        for (int x : nums) {
            f = Math.max(f, 0) + x;
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
        int ans = INT_MIN, f = INT_MIN;
        for (int x : nums) {
            f = max(f, 0) + x;
            ans = max(ans, f);
        }
        return ans;
    }
};
```

```go
func maxSubArray(nums []int) int {
	ans, f := math.MinInt32, math.MinInt32
	for _, x := range nums {
		f = max(f, 0) + x
		ans = max(ans, f)
	}
	return ans
}
```

```ts
function maxSubArray(nums: number[]): number {
    let [ans, f] = [-Infinity, -Infinity];
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        ans = Math.max(ans, f);
    }
    return ans;
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let [ans, f] = [-Infinity, -Infinity];
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        ans = Math.max(ans, f);
    }
    return ans;
};
```

```swift
class Solution {
    func maxSubArray(_ nums: [Int]) -> Int {
        var ans = Int.min
        var f = Int.min

        for x in nums {
            f = max(f, 0) + x
            ans = max(ans, f)
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
