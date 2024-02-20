# [2958. Length of Longest Subarray With at Most K Frequency](https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency)

[中文文档](/solution/2900-2999/2958.Length%20of%20Longest%20Subarray%20With%20at%20Most%20K%20Frequency/README.md)

<!-- tags:Array,Hash Table,Sliding Window -->

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>The <strong>frequency</strong> of an element <code>x</code> is the number of times it occurs in an array.</p>

<p>An array is called <strong>good</strong> if the frequency of each element in this array is <strong>less than or equal</strong> to <code>k</code>.</p>

<p>Return <em>the length of the <strong>longest</strong> <strong>good</strong> subarray of</em> <code>nums</code><em>.</em></p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1,2,3,1,2], k = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> The longest possible good subarray is [1,2,3,1,2,3] since the values 1, 2, and 3 occur at most twice in this subarray. Note that the subarrays [2,3,1,2,3,1] and [3,1,2,3,1,2] are also good.
It can be shown that there are no good subarrays with length more than 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2,1,2,1,2], k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest possible good subarray is [1,2] since the values 1 and 2 occur at most once in this subarray. Note that the subarray [2,1] is also good.
It can be shown that there are no good subarrays with length more than 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,5,5,5,5,5,5], k = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest possible good subarray is [5,5,5,5] since the value 5 occurs 4 times in this subarray.
It can be shown that there are no good subarrays with length more than 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## Solutions

### Solution 1: Two Pointers

We can use two pointers $j$ and $i$ to represent the left and right endpoints of the subarray, initially both pointers point to the first element of the array.

Next, we iterate over each element $x$ in the array $nums$. For each element $x$, we increment the occurrence count of $x$, then check if the current subarray meets the requirements. If the current subarray does not meet the requirements, we move the pointer $j$ one step to the right, and decrement the occurrence count of $nums[j]$, until the current subarray meets the requirements. Then we update the answer $ans = \max(ans, i - j + 1)$. Continue the iteration until $i$ reaches the end of the array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def maxSubarrayLength(self, nums: List[int], k: int) -> int:
        cnt = defaultdict(int)
        ans = j = 0
        for i, x in enumerate(nums):
            cnt[x] += 1
            while cnt[x] > k:
                cnt[nums[j]] -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < nums.length; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
            while (cnt.get(nums[i]) > k) {
                cnt.merge(nums[j++], -1, Integer::sum);
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxSubarrayLength(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (int i = 0, j = 0; i < nums.size(); ++i) {
            ++cnt[nums[i]];
            while (cnt[nums[i]] > k) {
                --cnt[nums[j++]];
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func maxSubarrayLength(nums []int, k int) (ans int) {
	cnt := map[int]int{}
	for i, j, n := 0, 0, len(nums); i < n; i++ {
		cnt[nums[i]]++
		for ; cnt[nums[i]] > k; j++ {
			cnt[nums[j]]--
		}
		ans = max(ans, i-j+1)
	}
	return
}
```

```ts
function maxSubarrayLength(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let i = 0, j = 0; i < nums.length; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        for (; cnt.get(nums[i])! > k; ++j) {
            cnt.set(nums[j], cnt.get(nums[j])! - 1);
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
