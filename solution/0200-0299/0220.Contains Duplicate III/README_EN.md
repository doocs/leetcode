# [220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii)

[中文文档](/solution/0200-0299/0220.Contains%20Duplicate%20III/README.md)

## Description

<p>Given an integer array <code>nums</code> and two integers <code>k</code> and <code>t</code>, return <code>true</code> if there are <strong>two distinct indices</strong> <code>i</code> and <code>j</code> in the array such that <code>abs(nums[i] - nums[j]) &lt;= t</code> and <code>abs(i - j) &lt;= k</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3,1], k = 3, t = 0
<strong>Output:</strong> true
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,0,1,1], k = 1, t = 2
<strong>Output:</strong> true
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1,5,9,1,5,9], k = 2, t = 3
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= t &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedSet


class Solution:
    def containsNearbyAlmostDuplicate(self, nums: List[int], k: int, t: int) -> bool:
        s = SortedSet()
        for i, num in enumerate(nums):
            idx = s.bisect_left(num - t)
            if 0 <= idx < len(s) and s[idx] <= num + t:
                return True
            s.add(num)
            if i >= k:
                s.remove(nums[i - k])
        return False
```

### **Java**

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            Long x = ts.ceiling((long) nums[i] - (long) t);
            if (x != null && x <= (long) nums[i] + (long) t) {
                return true;
            }
            ts.add((long) nums[i]);
            if (i >= k) {
                ts.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int k, int t) {
        set<long> s;
        for (int i = 0; i < nums.size(); ++i) {
            auto it = s.lower_bound((long)nums[i] - t);
            if (it != s.end() && *it <= (long)nums[i] + t) return true;
            s.insert((long)nums[i]);
            if (i >= k) s.erase((long)nums[i - k]);
        }
        return false;
    }
};
```

### **Go**

```go
func containsNearbyAlmostDuplicate(nums []int, k int, t int) bool {
	n := len(nums)
	left, right := 0, 0
	rbt := redblacktree.NewWithIntComparator()
	for right < n {
		cur := nums[right]
		right++
		if p, ok := rbt.Floor(cur); ok && cur-p.Key.(int) <= t {
			return true
		}
		if p, ok := rbt.Ceiling(cur); ok && p.Key.(int)-cur <= t {
			return true
		}
		rbt.Put(cur, struct{}{})
		if right-left > k {
			rbt.Remove(nums[left])
			left++
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
