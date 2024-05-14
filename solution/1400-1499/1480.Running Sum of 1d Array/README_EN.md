# [1480. Running Sum of 1d Array](https://leetcode.com/problems/running-sum-of-1d-array)

[中文文档](/solution/1400-1499/1480.Running%20Sum%20of%201d%20Array/README.md)

<!-- tags:Array,Prefix Sum -->

<!-- difficulty:Easy -->

## Description

<p>Given an array <code>nums</code>. We define a running sum of an array as&nbsp;<code>runningSum[i] = sum(nums[0]&hellip;nums[i])</code>.</p>

<p>Return the running sum of <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> [1,3,6,10]
<strong>Explanation:</strong> Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1]
<strong>Output:</strong> [1,2,3,4,5]
<strong>Explanation:</strong> Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,2,10,1]
<strong>Output:</strong> [3,4,6,16,17]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10^6&nbsp;&lt;= nums[i] &lt;=&nbsp;10^6</code></li>
</ul>

## Solutions

### Solution 1: Prefix Sum

We directly traverse the array. For the current element $nums[i]$, we add it with the prefix sum $nums[i-1]$ to get the prefix sum $nums[i]$ of the current element.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def runningSum(self, nums: List[int]) -> List[int]:
        return list(accumulate(nums))
```

```java
class Solution {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}
```

```cpp
class Solution {
public:
    vector<int> runningSum(vector<int>& nums) {
        for (int i = 1; i < nums.size(); ++i) nums[i] += nums[i - 1];
        return nums;
    }
};
```

```go
func runningSum(nums []int) []int {
	for i := 1; i < len(nums); i++ {
		nums[i] += nums[i-1]
	}
	return nums
}
```

```ts
function runningSum(nums: number[]): number[] {
    for (let i = 1; i < nums.length; ++i) {
        nums[i] += nums[i - 1];
    }
    return nums;
}
```

```cs
public class Solution {
    public int[] RunningSum(int[] nums) {
        for (int i = 1; i < nums.Length; ++i) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function runningSum($nums) {
        for ($i = 1; $i < count($nums); $i++) {
            $nums[$i] += $nums[$i - 1];
        }
        return $nums;
    }
}
```

<!-- tabs:end -->

<!-- end -->
