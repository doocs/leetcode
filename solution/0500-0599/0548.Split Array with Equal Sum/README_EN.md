# [548. Split Array with Equal Sum](https://leetcode.com/problems/split-array-with-equal-sum)

[中文文档](/solution/0500-0599/0548.Split%20Array%20with%20Equal%20Sum/README.md)

## Description

<p>

Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

<ol>

<li> 0 < i, i + 1 < j, j + 1 < k < n - 1 </li>

<li> Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal. </li>

</ol>

where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.

</p>

<p><b>Example:</b><br />

<pre>

<b>Input:</b> [1,2,1,2,1,2,1]

<b>Output:</b> True

<b>Explanation:</b>

i = 1, j = 3, k = 5. 

sum(0, i - 1) = sum(0, 0) = 1

sum(i + 1, j - 1) = sum(2, 2) = 1

sum(j + 1, k - 1) = sum(4, 4) = 1

sum(k + 1, n - 1) = sum(6, 6) = 1

</pre>

</p>

<b>Note:</b>

<ol>

<li> 1 <= n <= 2000. </li>

<li> Elements in the given array will be in range [-1,000,000, 1,000,000]. </li>

</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def splitArray(self, nums: List[int]) -> bool:
        n = len(nums)
        s = [0] * (n + 1)
        for i, v in enumerate(nums):
            s[i + 1] = s[i] + v
        for j in range(3, n - 3):
            seen = set()
            for i in range(1, j - 1):
                if s[i] == s[j] - s[i + 1]:
                    seen.add(s[i])
            for k in range(j + 2, n - 1):
                if s[n] - s[k + 1] == s[k] - s[j + 1] and s[n] - s[k + 1] in seen:
                    return True
        return False
```

### **Java**

```java
class Solution {
    public boolean splitArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        for (int j = 3; j < n - 3; ++j) {
            Set<Integer> seen = new HashSet<>();
            for (int i = 1; i < j - 1; ++i) {
                if (s[i] == s[j] - s[i + 1]) {
                    seen.add(s[i]);
                }
            }
            for (int k = j + 2; k < n - 1; ++k) {
                if (s[n] - s[k + 1] == s[k] - s[j + 1] && seen.contains(s[n] - s[k + 1])) {
                    return true;
                }
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
    bool splitArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        for (int j = 3; j < n - 3; ++j)
        {
            unordered_set<int> seen;
            for (int i = 1; i < j - 1; ++i)
                if (s[i] == s[j] - s[i + 1])
                    seen.insert(s[i]);
            for (int k = j + 2; k < n - 1; ++k)
                if (s[n] - s[k + 1] == s[k] - s[j + 1] && seen.count(s[n] - s[k + 1]))
                    return true;
        }
        return false;
    }
};
```

### **Go**

```go
func splitArray(nums []int) bool {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	for j := 3; j < n-3; j++ {
		seen := map[int]bool{}
		for i := 1; i < j-1; i++ {
			if s[i] == s[j]-s[i+1] {
				seen[s[i]] = true
			}
		}
		for k := j + 2; k < n-1; k++ {
			if s[n]-s[k+1] == s[k]-s[j+1] && seen[s[n]-s[k+1]] {
				return true
			}
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
