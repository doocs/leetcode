# [915. Partition Array into Disjoint Intervals](https://leetcode.com/problems/partition-array-into-disjoint-intervals)

[中文文档](/solution/0900-0999/0915.Partition%20Array%20into%20Disjoint%20Intervals/README.md)

## Description

<p>Given an integer array <code>nums</code>, partition it into two (contiguous) subarrays <code>left</code> and <code>right</code> so that:</p>

<ul>
	<li>Every element in <code>left</code> is less than or equal to every element in <code>right</code>.</li>
	<li><code>left</code> and <code>right</code> are non-empty.</li>
	<li><code>left</code> has the smallest possible size.</li>
</ul>

<p>Return <em>the length of </em><code>left</code><em> after such a partitioning</em>.</p>

<p>Test cases are generated such that partitioning exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,0,3,8,6]
<strong>Output:</strong> 3
<strong>Explanation:</strong> left = [5,0,3], right = [8,6]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,0,6,12]
<strong>Output:</strong> 4
<strong>Explanation:</strong> left = [1,1,1,0], right = [6,12]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li>There is at least one valid answer for the given input.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def partitionDisjoint(self, nums: List[int]) -> int:
        n = len(nums)
        mi = [inf] * (n + 1)
        for i in range(n - 1, -1, -1):
            mi[i] = min(nums[i], mi[i + 1])
        mx = 0
        for i, v in enumerate(nums, 1):
            mx = max(mx, v)
            if mx <= mi[i]:
                return i
```

### **Java**

```java
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] mi = new int[n + 1];
        mi[n] = nums[n - 1];
        for (int i = n - 1; i >= 0; --i) {
            mi[i] = Math.min(nums[i], mi[i + 1]);
        }
        int mx = 0;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            mx = Math.max(mx, v);
            if (mx <= mi[i]) {
                return i;
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int partitionDisjoint(vector<int>& nums) {
        int n = nums.size();
        vector<int> mi(n + 1, INT_MAX);
        for (int i = n - 1; ~i; --i) mi[i] = min(nums[i], mi[i + 1]);
        int mx = 0;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            mx = max(mx, v);
            if (mx <= mi[i]) return i;
        }
        return 0;
    }
};
```

### **Go**

```go
func partitionDisjoint(nums []int) int {
	n := len(nums)
	mi := make([]int, n+1)
	mi[n] = nums[n-1]
	for i := n - 1; i >= 0; i-- {
		mi[i] = min(nums[i], mi[i+1])
	}
	mx := 0
	for i := 1; i <= n; i++ {
		v := nums[i-1]
		mx = max(mx, v)
		if mx <= mi[i] {
			return i
		}
	}
	return 0
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
