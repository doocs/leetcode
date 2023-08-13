# [2817. Minimum Absolute Difference Between Elements With Constraint](https://leetcode.com/problems/minimum-absolute-difference-between-elements-with-constraint)

[中文文档](/solution/2800-2899/2817.Minimum%20Absolute%20Difference%20Between%20Elements%20With%20Constraint/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>x</code>.</p>

<p>Find the <strong>minimum absolute difference</strong> between two elements in the array that are at least <code>x</code> indices apart.</p>

<p>In other words, find two indices <code>i</code> and <code>j</code> such that <code>abs(i - j) &gt;= x</code> and <code>abs(nums[i] - nums[j])</code> is minimized.</p>

<p>Return<em> an integer denoting the <strong>minimum</strong> absolute difference between two elements that are at least</em> <code>x</code> <em>indices apart</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,4], x = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> We can select nums[0] = 4 and nums[3] = 4. 
They are at least 2 indices apart, and their absolute difference is the minimum, 0. 
It can be shown that 0 is the optimal answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,3,2,10,15], x = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can select nums[1] = 3 and nums[2] = 2.
They are at least 1 index apart, and their absolute difference is the minimum, 1.
It can be shown that 1 is the optimal answer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], x = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can select nums[0] = 1 and nums[3] = 4.
They are at least 3 indices apart, and their absolute difference is the minimum, 3.
It can be shown that 3 is the optimal answer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= x &lt; nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedList


class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        sl = SortedList()
        ans = inf
        for i in range(x, len(nums)):
            sl.add(nums[i - x])
            p = bisect_left(sl, nums[i])
            if p < len(sl):
                ans = min(ans, abs(nums[i] - sl[p]))
            if p:
                ans = min(ans, abs(nums[i] - sl[p - 1]))
        return ans
```

### **Java**

```java
class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int ans = 1 << 30;
        for (int i = x; i < nums.size(); ++i) {
            tm.merge(nums.get(i - x), 1, Integer::sum);
            Integer key = tm.ceilingKey(nums.get(i));
            if (key != null) {
                ans = Math.min(ans, key - nums.get(i));
            }
            key = tm.floorKey(nums.get(i));
            if (key != null) {
                ans = Math.min(ans, nums.get(i) - key);
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
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int ans = 1 << 30;
        multiset<int> s;
        for (int i = x; i < nums.size(); ++i) {
            s.insert(nums[i - x]);
            auto it = s.lower_bound(nums[i]);
            if (it != s.end()) {
                ans = min(ans, *it - nums[i]);
            }
            if (it != s.begin()) {
                --it;
                ans = min(ans, nums[i] - *it);
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
