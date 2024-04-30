# [2537. Count the Number of Good Subarrays](https://leetcode.com/problems/count-the-number-of-good-subarrays)

[中文文档](/solution/2500-2599/2537.Count%20the%20Number%20of%20Good%20Subarrays/README.md)

<!-- tags:Array,Hash Table,Sliding Window -->

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the number of <strong>good</strong> subarrays of</em> <code>nums</code>.</p>

<p>A subarray <code>arr</code> is <strong>good</strong> if it there are <strong>at least </strong><code>k</code> pairs of indices <code>(i, j)</code> such that <code>i &lt; j</code> and <code>arr[i] == arr[j]</code>.</p>

<p>A <strong>subarray</strong> is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], k = 10
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only good subarray is the array nums itself.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,4,3,2,2,4], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Two Pointers

If a subarray contains $k$ pairs of identical elements, then an array that contains this subarray must contain at least $k$ pairs of identical elements.

We use a hash table $cnt$ to count the number of occurrences of each element in the window, use $cur$ to count the number of pairs of identical elements in the window, and use $i$ to maintain the left endpoint of the window.

We traverse the array $nums$, take the current element $x$ as the right endpoint, then the number of pairs of identical elements in the window will increase by $cnt[x]$, and the occurrence times of $x$ will be increased by one, i.e., $cnt[x] \leftarrow cnt[x] + 1$. Next, we loop to judge whether the number of pairs of identical elements in the window is greater than or equal to $k$ after removing the left endpoint. If it is greater than or equal to $k$, then we decrease the occurrence times of the left endpoint element by one, i.e., $cnt[nums[i]] \leftarrow cnt[nums[i]] - 1$, and decrease the number of pairs of identical elements in the window by $cnt[nums[i]]$, i.e., $cur \leftarrow cur - cnt[nums[i]]$, and move the left endpoint to the right, i.e., $i \leftarrow i + 1$. At this time, all elements to the left of the window left endpoint and the left endpoint itself can be used as the left endpoint of the current right endpoint, so the answer is increased by $i + 1$.

Finally, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def countGood(self, nums: List[int], k: int) -> int:
        cnt = Counter()
        ans = cur = 0
        i = 0
        for x in nums:
            cur += cnt[x]
            cnt[x] += 1
            while cur - cnt[nums[i]] + 1 >= k:
                cnt[nums[i]] -= 1
                cur -= cnt[nums[i]]
                i += 1
            if cur >= k:
                ans += i + 1
        return ans
```

```java
class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long ans = 0, cur = 0;
        int i = 0;
        for (int x : nums) {
            cur += cnt.getOrDefault(x, 0);
            cnt.merge(x, 1, Integer::sum);
            while (cur - cnt.get(nums[i]) + 1 >= k) {
                cur -= cnt.merge(nums[i++], -1, Integer::sum);
            }
            if (cur >= k) {
                ans += i + 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long countGood(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        long long ans = 0;
        long long cur = 0;
        int i = 0;
        for (int& x : nums) {
            cur += cnt[x]++;
            while (cur - cnt[nums[i]] + 1 >= k) {
                cur -= --cnt[nums[i++]];
            }
            if (cur >= k) {
                ans += i + 1;
            }
        }
        return ans;
    }
};
```

```go
func countGood(nums []int, k int) int64 {
	cnt := map[int]int{}
	ans, cur := 0, 0
	i := 0
	for _, x := range nums {
		cur += cnt[x]
		cnt[x]++
		for cur-cnt[nums[i]]+1 >= k {
			cnt[nums[i]]--
			cur -= cnt[nums[i]]
			i++
		}
		if cur >= k {
			ans += i + 1
		}
	}
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- end -->
