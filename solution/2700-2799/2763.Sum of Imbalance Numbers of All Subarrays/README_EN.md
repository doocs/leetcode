# [2763. Sum of Imbalance Numbers of All Subarrays](https://leetcode.com/problems/sum-of-imbalance-numbers-of-all-subarrays)

[中文文档](/solution/2700-2799/2763.Sum%20of%20Imbalance%20Numbers%20of%20All%20Subarrays/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedList


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

### **Java**

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

### **C++**

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

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
