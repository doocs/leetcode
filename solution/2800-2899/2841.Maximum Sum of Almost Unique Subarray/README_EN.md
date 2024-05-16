---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2841.Maximum%20Sum%20of%20Almost%20Unique%20Subarray/README_EN.md
rating: 1545
source: Biweekly Contest 112 Q3
tags:
    - Array
    - Hash Table
    - Sliding Window
---

# [2841. Maximum Sum of Almost Unique Subarray](https://leetcode.com/problems/maximum-sum-of-almost-unique-subarray)

[中文文档](/solution/2800-2899/2841.Maximum%20Sum%20of%20Almost%20Unique%20Subarray/README.md)

## Description

<p>You are given an integer array <code>nums</code> and two positive integers <code>m</code> and <code>k</code>.</p>

<p>Return <em>the <strong>maximum sum</strong> out of all <strong>almost unique</strong> subarrays of length </em><code>k</code><em> of</em> <code>nums</code>. If no such subarray exists, return <code>0</code>.</p>

<p>A subarray of <code>nums</code> is <strong>almost unique</strong> if it contains at least <code>m</code> distinct elements.</p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,6,7,3,1,7], m = 3, k = 4
<strong>Output:</strong> 18
<strong>Explanation:</strong> There are 3 almost unique subarrays of size <code>k = 4</code>. These subarrays are [2, 6, 7, 3], [6, 7, 3, 1], and [7, 3, 1, 7]. Among these subarrays, the one with the maximum sum is [2, 6, 7, 3] which has a sum of 18.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,9,9,2,4,5,4], m = 1, k = 3
<strong>Output:</strong> 23
<strong>Explanation:</strong> There are 5 almost unique subarrays of size k. These subarrays are [5, 9, 9], [9, 9, 2], [9, 2, 4], [2, 4, 5], and [4, 5, 4]. Among these subarrays, the one with the maximum sum is [5, 9, 9] which has a sum of 23.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2,1,2,1], m = 3, k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no subarrays of size <code>k = 3</code> that contain at least <code>m = 3</code> distinct elements in the given array [1,2,1,2,1,2,1]. Therefore, no almost unique subarrays exist, and the maximum sum is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m &lt;= k &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Sliding Window + Hash Table

We can traverse the array $nums$, maintain a window of size $k$, use a hash table $cnt$ to count the occurrence of each element in the window, and use a variable $s$ to sum all elements in the window. If the number of different elements in $cnt$ is greater than or equal to $m$, then we update the answer $ans = \max(ans, s)$.

After the traversal ends, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(k)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def maxSum(self, nums: List[int], m: int, k: int) -> int:
        cnt = Counter(nums[:k])
        s = sum(nums[:k])
        ans = s if len(cnt) >= m else 0
        for i in range(k, len(nums)):
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            s += nums[i] - nums[i - k]
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
            if len(cnt) >= m:
                ans = max(ans, s)
        return ans
```

```java
class Solution {
    public long maxSum(List<Integer> nums, int m, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = nums.size();
        long s = 0;
        for (int i = 0; i < k; ++i) {
            cnt.merge(nums.get(i), 1, Integer::sum);
            s += nums.get(i);
        }
        long ans = cnt.size() >= m ? s : 0;
        for (int i = k; i < n; ++i) {
            cnt.merge(nums.get(i), 1, Integer::sum);
            if (cnt.merge(nums.get(i - k), -1, Integer::sum) == 0) {
                cnt.remove(nums.get(i - k));
            }
            s += nums.get(i) - nums.get(i - k);
            if (cnt.size() >= m) {
                ans = Math.max(ans, s);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maxSum(vector<int>& nums, int m, int k) {
        unordered_map<int, int> cnt;
        long long s = 0;
        int n = nums.size();
        for (int i = 0; i < k; ++i) {
            cnt[nums[i]]++;
            s += nums[i];
        }
        long long ans = cnt.size() >= m ? s : 0;
        for (int i = k; i < n; ++i) {
            cnt[nums[i]]++;
            if (--cnt[nums[i - k]] == 0) {
                cnt.erase(nums[i - k]);
            }
            s += nums[i] - nums[i - k];
            if (cnt.size() >= m) {
                ans = max(ans, s);
            }
        }
        return ans;
    }
};
```

```go
func maxSum(nums []int, m int, k int) int64 {
	cnt := map[int]int{}
	var s int64
	for _, x := range nums[:k] {
		cnt[x]++
		s += int64(x)
	}
	var ans int64
	if len(cnt) >= m {
		ans = s
	}
	for i := k; i < len(nums); i++ {
		cnt[nums[i]]++
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			delete(cnt, nums[i-k])
		}
		s += int64(nums[i]) - int64(nums[i-k])
		if len(cnt) >= m {
			ans = max(ans, s)
		}
	}
	return ans
}
```

```ts
function maxSum(nums: number[], m: number, k: number): number {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    let s = 0;
    for (let i = 0; i < k; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) || 0) + 1);
        s += nums[i];
    }
    let ans = cnt.size >= m ? s : 0;
    for (let i = k; i < n; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) || 0) + 1);
        cnt.set(nums[i - k], cnt.get(nums[i - k])! - 1);
        if (cnt.get(nums[i - k]) === 0) {
            cnt.delete(nums[i - k]);
        }
        s += nums[i] - nums[i - k];
        if (cnt.size >= m) {
            ans = Math.max(ans, s);
        }
    }
    return ans;
}
```

```cs
public class Solution {
    public long MaxSum(IList<int> nums, int m, int k) {
        Dictionary<int, int> cnt = new Dictionary<int, int>();
        int n = nums.Count;
        long s = 0;

        for (int i = 0; i < k; ++i) {
            if (!cnt.ContainsKey(nums[i])) {
                cnt[nums[i]] = 1;
            }
            else {
                cnt[nums[i]]++;
            }
            s += nums[i];
        }

        long ans = cnt.Count >= m ? s : 0;

        for (int i = k; i < n; ++i) {
            if (!cnt.ContainsKey(nums[i])) {
                cnt[nums[i]] = 1;
            }
            else {
                cnt[nums[i]]++;
            }
            if (cnt.ContainsKey(nums[i - k])) {
                cnt[nums[i - k]]--;
                if (cnt[nums[i - k]] == 0) {
                    cnt.Remove(nums[i - k]);
                }
            }

            s += nums[i] - nums[i - k];

            if (cnt.Count >= m) {
                ans = Math.Max(ans, s);
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
