# [220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii)

[中文文档](/solution/0200-0299/0220.Contains%20Duplicate%20III/README.md)

## Description

<p>You are given an integer array <code>nums</code> and two integers <code>indexDiff</code> and <code>valueDiff</code>.</p>

<p>Find a pair of indices <code>(i, j)</code> such that:</p>

<ul>
	<li><code>i != j</code>,</li>
	<li><code>abs(i - j) &lt;= indexDiff</code>.</li>
	<li><code>abs(nums[i] - nums[j]) &lt;= valueDiff</code>, and</li>
</ul>

<p>Return <code>true</code><em> if such pair exists or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
<strong>Output:</strong> true
<strong>Explanation:</strong> We can choose (i, j) = (0, 3).
We satisfy the three conditions:
i != j --&gt; 0 != 3
abs(i - j) &lt;= indexDiff --&gt; abs(0 - 3) &lt;= 3
abs(nums[i] - nums[j]) &lt;= valueDiff --&gt; abs(1 - 1) &lt;= 0
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= indexDiff &lt;= nums.length</code></li>
	<li><code>0 &lt;= valueDiff &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedSet


class Solution:
    def containsNearbyAlmostDuplicate(self, nums: List[int], indexDiff: int, valueDiff: int) -> bool:
        s = SortedSet()
        for i, v in enumerate(nums):
            j = s.bisect_left(v - valueDiff)
            if j < len(s) and s[j] <= v + valueDiff:
                return True
            s.add(v)
            if i >= indexDiff:
                s.remove(nums[i - indexDiff])
        return False
```

### **Java**

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            Long x = ts.ceiling((long) nums[i] - (long) valueDiff);
            if (x != null && x <= (long) nums[i] + (long) valueDiff) {
                return true;
            }
            ts.add((long) nums[i]);
            if (i >= indexDiff) {
                ts.remove((long) nums[i - indexDiff]);
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
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int indexDiff, int valueDiff) {
        set<long> s;
        for (int i = 0; i < nums.size(); ++i) {
            auto it = s.lower_bound((long) nums[i] - valueDiff);
            if (it != s.end() && *it <= (long) nums[i] + valueDiff) return true;
            s.insert((long) nums[i]);
            if (i >= indexDiff) s.erase((long) nums[i - indexDiff]);
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

### **C#**

```cs
public class Solution {
    public bool ContainsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0 || t < 0) return false;
        var index = new SortedList<int, object>();
        for (int i = 0; i < nums.Length; ++i) {
            if (index.ContainsKey(nums[i])) {
                return true;
            }
            index.Add(nums[i], null);
            var j = index.IndexOfKey(nums[i]);
            if (j > 0 && (long)nums[i] - index.Keys[j - 1] <= t) {
                return true;
            }
            if (j < index.Count - 1 && (long)index.Keys[j + 1] - nums[i] <= t) {
                return true;
            }
            if (index.Count > k) {
                index.Remove(nums[i - k]);
            }
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
