---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2762.Continuous%20Subarrays/README_EN.md
rating: 1940
source: Weekly Contest 352 Q3
tags:
    - Queue
    - Array
    - Ordered Set
    - Sliding Window
    - Monotonic Queue
    - Heap (Priority Queue)
---

# [2762. Continuous Subarrays](https://leetcode.com/problems/continuous-subarrays)

[中文文档](/solution/2700-2799/2762.Continuous%20Subarrays/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. A subarray of <code>nums</code> is called <strong>continuous</strong> if:</p>

<ul>
	<li>Let <code>i</code>, <code>i + 1</code>, ..., <code>j</code><sub> </sub>be the indices in the subarray. Then, for each pair of indices <code>i &lt;= i<sub>1</sub>, i<sub>2</sub> &lt;= j</code>, <code><font face="monospace">0 &lt;=</font> |nums[i<sub>1</sub>] - nums[i<sub>2</sub>]| &lt;= 2</code>.</li>
</ul>

<p>Return <em>the total number of <strong>continuous</strong> subarrays.</em></p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,2,4]
<strong>Output:</strong> 8
<strong>Explanation:</strong> 
Continuous subarray of size 1: [5], [4], [2], [4].
Continuous subarray of size 2: [5,4], [4,2], [2,4].
Continuous subarray of size 3: [4,2,4].
Thereare no subarrys of size 4.
Total continuous subarrays = 4 + 3 + 1 = 8.
It can be shown that there are no more continuous subarrays.
</pre>

<p>&nbsp;</p>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
Continuous subarray of size 1: [1], [2], [3].
Continuous subarray of size 2: [1,2], [2,3].
Continuous subarray of size 3: [1,2,3].
Total continuous subarrays = 3 + 2 + 1 = 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Ordered List + Two Pointers

We can use two pointers, $i$ and $j$, to maintain the left and right endpoints of the current subarray, and use an ordered list to maintain all elements in the current subarray.

Iterate through the array $nums$. For the current number $nums[i]$ we're iterating over, we add it to the ordered list. If the difference between the maximum and minimum values in the ordered list is greater than $2$, we then loop to move the pointer $i$ to the right, continuously removing $nums[i]$ from the ordered list, until the list is empty or the maximum difference between elements in the ordered list is not greater than $2$. At this point, the number of uninterrupted subarrays is $j - i + 1$, which we add to the answer.

After the iteration, return the answer.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


class Solution:
    def continuousSubarrays(self, nums: List[int]) -> int:
        ans = i = 0
        sl = SortedList()
        for x in nums:
            sl.add(x)
            while sl[-1] - sl[0] > 2:
                sl.remove(nums[i])
                i += 1
            ans += len(sl)
        return ans
```

```java
class Solution {
    public long continuousSubarrays(int[] nums) {
        long ans = 0;
        int i = 0, n = nums.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int j = 0; j < n; ++j) {
            tm.merge(nums[j], 1, Integer::sum);
            while (tm.lastEntry().getKey() - tm.firstEntry().getKey() > 2) {
                tm.merge(nums[i], -1, Integer::sum);
                if (tm.get(nums[i]) == 0) {
                    tm.remove(nums[i]);
                }
                ++i;
            }
            ans += j - i + 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long continuousSubarrays(vector<int>& nums) {
        long long ans = 0;
        int i = 0, n = nums.size();
        multiset<int> s;
        for (int j = 0; j < n; ++j) {
            s.insert(nums[j]);
            while (*s.rbegin() - *s.begin() > 2) {
                s.erase(s.find(nums[i++]));
            }
            ans += j - i + 1;
        }
        return ans;
    }
};
```

```go
func continuousSubarrays(nums []int) (ans int64) {
	i := 0
	tm := treemap.NewWithIntComparator()
	for j, x := range nums {
		if v, ok := tm.Get(x); ok {
			tm.Put(x, v.(int)+1)
		} else {
			tm.Put(x, 1)
		}
		for {
			a, _ := tm.Min()
			b, _ := tm.Max()
			if b.(int)-a.(int) > 2 {
				if v, _ := tm.Get(nums[i]); v.(int) == 1 {
					tm.Remove(nums[i])
				} else {
					tm.Put(nums[i], v.(int)-1)
				}
				i++
			} else {
				break
			}
		}
		ans += int64(j - i + 1)
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
