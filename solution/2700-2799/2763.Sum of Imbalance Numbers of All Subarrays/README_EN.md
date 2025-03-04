---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2763.Sum%20of%20Imbalance%20Numbers%20of%20All%20Subarrays/README_EN.md
rating: 2277
source: Weekly Contest 352 Q4
tags:
    - Array
    - Hash Table
    - Ordered Set
---

<!-- problem:start -->

# [2763. Sum of Imbalance Numbers of All Subarrays](https://leetcode.com/problems/sum-of-imbalance-numbers-of-all-subarrays)

[中文文档](/solution/2700-2799/2763.Sum%20of%20Imbalance%20Numbers%20of%20All%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>The <strong>imbalance number</strong> of a <strong>0-indexed</strong> integer array <code>arr</code> of length <code>n</code> is defined as the number of indices in <code>sarr = sorted(arr)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i &lt; n - 1</code>, and</li>
	<li><code>sarr[i+1] - sarr[i] &gt; 1</code></li>
</ul>

<p>Here, <code>sorted(arr)</code> is the function that returns the sorted version of <code>arr</code>.</p>

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, return <em>the <strong>sum of imbalance numbers</strong> of all its <strong>subarrays</strong></em>.</p>

<p>A <strong>subarray</strong> is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 subarrays with non-zero<strong> </strong>imbalance numbers:
- Subarray [3, 1] with an imbalance number of 1.
- Subarray [3, 1, 4] with an imbalance number of 1.
- Subarray [1, 4] with an imbalance number of 1.
The imbalance number of all other subarrays is 0. Hence, the sum of imbalance numbers of all the subarrays of nums is 3. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,3,3,5]
<strong>Output:</strong> 8
<strong>Explanation:</strong> There are 7 subarrays with non-zero imbalance numbers:
- Subarray [1, 3] with an imbalance number of 1.
- Subarray [1, 3, 3] with an imbalance number of 1.
- Subarray [1, 3, 3, 3] with an imbalance number of 1.
- Subarray [1, 3, 3, 3, 5] with an imbalance number of 2. 
- Subarray [3, 3, 3, 5] with an imbalance number of 1. 
- Subarray [3, 3, 5] with an imbalance number of 1.
- Subarray [3, 5] with an imbalance number of 1.
The imbalance number of all other subarrays is 0. Hence, the sum of imbalance numbers of all the subarrays of nums is 8. </pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Ordered Set

We can first enumerate the left endpoint $i$ of the subarray. For each $i$, we enumerate the right endpoint $j$ of the subarray from small to large, and maintain all the elements in the current subarray with an ordered list. We also use a variable $cnt$ to maintain the unbalanced number of the current subarray.

For each number $nums[j]$, we find the first element $nums[k]$ in the ordered list that is greater than or equal to $nums[j]$, and the last element $nums[h]$ that is less than $nums[j]$:

-   If $nums[k]$ exists, and the difference between $nums[k]$ and $nums[j]$ is more than $1$, the unbalanced number increases by $1$;
-   If $nums[h]$ exists, and the difference between $nums[j]$ and $nums[h]$ is more than $1$, the unbalanced number increases by $1$;
-   If both $nums[k]$ and $nums[h]$ exist, then inserting the element $nums[j]$ between $nums[h]$ and $nums[k]$ will reduce the unbalanced number by $1$.

Then, we add the unbalanced number of the current subarray to the answer, and continue the iteration until we finish iterating over all subarrays.

The time complexity is $O(n^2 \times \log n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumImbalanceNumbers(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            sl = SortedList()
            cnt = 0
            for j in range(i, n):
                k = sl.bisect_left(nums[j])
                h = k - 1
                if h >= 0 and nums[j] - sl[h] > 1:
                    cnt += 1
                if k < len(sl) and sl[k] - nums[j] > 1:
                    cnt += 1
                if h >= 0 and k < len(sl) and sl[k] - sl[h] > 1:
                    cnt -= 1
                sl.add(nums[j])
                ans += cnt
        return ans
```

#### Java

```java
class Solution {
    public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                Integer k = tm.ceilingKey(nums[j]);
                if (k != null && k - nums[j] > 1) {
                    ++cnt;
                }
                Integer h = tm.floorKey(nums[j]);
                if (h != null && nums[j] - h > 1) {
                    ++cnt;
                }
                if (h != null && k != null && k - h > 1) {
                    --cnt;
                }
                tm.merge(nums[j], 1, Integer::sum);
                ans += cnt;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumImbalanceNumbers(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            multiset<int> s;
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                auto it = s.lower_bound(nums[j]);
                if (it != s.end() && *it - nums[j] > 1) {
                    ++cnt;
                }
                if (it != s.begin() && nums[j] - *prev(it) > 1) {
                    ++cnt;
                }
                if (it != s.end() && it != s.begin() && *it - *prev(it) > 1) {
                    --cnt;
                }
                s.insert(nums[j]);
                ans += cnt;
            }
        }
        return ans;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
