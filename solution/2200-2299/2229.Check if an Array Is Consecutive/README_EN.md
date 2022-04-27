# [2229. Check if an Array Is Consecutive](https://leetcode.com/problems/check-if-an-array-is-consecutive)

[中文文档](/solution/2200-2299/2229.Check%20if%20an%20Array%20Is%20Consecutive/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <code>true</code> <em>if </em><code>nums</code><em> is <strong>consecutive</strong>, otherwise return </em><code>false</code><em>.</em></p>

<p>An array is <strong>consecutive </strong>if it contains every number in the range <code>[x, x + n - 1]</code> (<strong>inclusive</strong>), where <code>x</code> is the minimum number in the array and <code>n</code> is the length of the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,4,2]
<strong>Output:</strong> true
<strong>Explanation:</strong>
The minimum value is 1 and the length of nums is 4.
All of the values in the range [x, x + n - 1] = [1, 1 + 4 - 1] = [1, 4] = (1, 2, 3, 4) occur in nums.
Therefore, nums is consecutive.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3]
<strong>Output:</strong> false
<strong>Explanation:</strong>
The minimum value is 1 and the length of nums is 2.
The value 2 in the range [x, x + n - 1] = [1, 1 + 2 - 1], = [1, 2] = (1, 2) does not occur in nums.
Therefore, nums is not consecutive.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,4]
<strong>Output:</strong> true
<strong>Explanation:</strong>
The minimum value is 3 and the length of nums is 3.
All of the values in the range [x, x + n - 1] = [3, 3 + 3 - 1] = [3, 5] = (3, 4, 5) occur in nums.
Therefore, nums is consecutive.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isConsecutive(self, nums: List[int]) -> bool:
        mi, mx = min(nums), max(nums)
        n = len(nums)
        return len(set(nums)) == n and mx == mi + n - 1
```

### **Java**

```java
class Solution {
    public boolean isConsecutive(int[] nums) {
        int mi = nums[0];
        int mx = nums[0];
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
            s.add(v);
        }
        int n = nums.length;
        return s.size() == n && mx == mi + n - 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int mi = *min_element(nums.begin(), nums.end());
        int mx = *max_element(nums.begin(), nums.end());
        int n = nums.size();
        return s.size() == n && mx == mi + n - 1;
    }
};
```

### **Go**

```go
func isConsecutive(nums []int) bool {
	s := make(map[int]bool)
	mi, mx := nums[0], nums[0]
	for _, v := range nums {
		s[v] = true
		mi = min(mi, v)
		mx = max(mx, v)
	}
	return len(s) == len(nums) && mx == mi+len(nums)-1
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
