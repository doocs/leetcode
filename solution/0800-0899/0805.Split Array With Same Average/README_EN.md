# [805. Split Array With Same Average](https://leetcode.com/problems/split-array-with-same-average)

[中文文档](/solution/0800-0899/0805.Split%20Array%20With%20Same%20Average/README.md)

## Description

<p>You are given an integer array <code>nums</code>.</p>

<p>You should move each element of <code>nums</code> into one of the two arrays <code>A</code> and <code>B</code> such that <code>A</code> and <code>B</code> are non-empty, and <code>average(A) == average(B)</code>.</p>

<p>Return <code>true</code> if it is possible to achieve that and <code>false</code> otherwise.</p>

<p><strong>Note</strong> that for an array <code>arr</code>, <code>average(arr)</code> is the sum of all the elements of <code>arr</code> over the length of <code>arr</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7,8]
<strong>Output:</strong> true
<strong>Explanation:</strong> We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have an average of 4.5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 30</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def splitArraySameAverage(self, nums: List[int]) -> bool:
        n = len(nums)
        if n == 1:
            return False
        s = sum(nums)
        for i, v in enumerate(nums):
            nums[i] = v * n - s
        m = n >> 1
        vis = set()
        for i in range(1, 1 << m):
            t = sum(v for j, v in enumerate(nums[:m]) if i >> j & 1)
            if t == 0:
                return True
            vis.add(t)
        for i in range(1, 1 << (n - m)):
            t = sum(v for j, v in enumerate(nums[m:]) if i >> j & 1)
            if t == 0 or (i != (1 << (n - m)) - 1 and -t in vis):
                return True
        return False
```

### **Java**

```java
class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }
        int s = Arrays.stream(nums).sum();
        for (int i = 0; i < n; ++i) {
            nums[i] = nums[i] * n - s;
        }
        int m = n >> 1;
        Set<Integer> vis = new HashSet<>();
        for (int i = 1; i < 1 << m; ++i) {
            int t = 0;
            for (int j = 0; j < m; ++j) {
                if (((i >> j) & 1) == 1) {
                    t += nums[j];
                }
            }
            if (t == 0) {
                return true;
            }
            vis.add(t);
        }
        for (int i = 1; i < 1 << (n - m); ++i) {
            int t = 0;
            for (int j = 0; j < (n - m); ++j) {
                if (((i >> j) & 1) == 1) {
                    t += nums[m + j];
                }
            }
            if (t == 0 || (i != (1 << (n - m)) - 1) && vis.contains(-t)) {
                return true;
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
    bool splitArraySameAverage(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return false;
        int s = accumulate(nums.begin(), nums.end(), 0);
        for (int& v : nums) v = v * n - s;
        int m = n >> 1;
        unordered_set<int> vis;
        for (int i = 1; i < 1 << m; ++i) {
            int t = 0;
            for (int j = 0; j < m; ++j) if (i >> j & 1) t += nums[j];
            if (t == 0) return true;
            vis.insert(t);
        }
        for (int i = 1; i < 1 << (n - m); ++i) {
            int t = 0;
            for (int j = 0; j < (n - m); ++j) if (i >> j & 1) t += nums[m + j];
            if (t == 0 || (i != (1 << (n - m)) - 1 && vis.count(-t))) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func splitArraySameAverage(nums []int) bool {
	n := len(nums)
	if n == 1 {
		return false
	}
	s := 0
	for _, v := range nums {
		s += v
	}
	for i, v := range nums {
		nums[i] = v*n - s
	}
	m := n >> 1
	vis := map[int]bool{}
	for i := 1; i < 1<<m; i++ {
		t := 0
		for j, v := range nums[:m] {
			if (i >> j & 1) == 1 {
				t += v
			}
		}
		if t == 0 {
			return true
		}
		vis[t] = true
	}
	for i := 1; i < 1<<(n-m); i++ {
		t := 0
		for j, v := range nums[m:] {
			if (i >> j & 1) == 1 {
				t += v
			}
		}
		if t == 0 || (i != (1<<(n-m))-1 && vis[-t]) {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
