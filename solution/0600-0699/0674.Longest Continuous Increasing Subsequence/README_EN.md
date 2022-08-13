# [674. Longest Continuous Increasing Subsequence](https://leetcode.com/problems/longest-continuous-increasing-subsequence)

[中文文档](/solution/0600-0699/0674.Longest%20Continuous%20Increasing%20Subsequence/README.md)

## Description

<p>Given an unsorted array of integers <code>nums</code>, return <em>the length of the longest <strong>continuous increasing subsequence</strong> (i.e. subarray)</em>. The subsequence must be <strong>strictly</strong> increasing.</p>

<p>A <strong>continuous increasing subsequence</strong> is defined by two indices <code>l</code> and <code>r</code> (<code>l &lt; r</code>) such that it is <code>[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]</code> and for each <code>l &lt;= i &lt; r</code>, <code>nums[i] &lt; nums[i + 1]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,4,7]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
increasing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        res, n = 1, len(nums)
        i = 0
        while i < n:
            j = i + 1
            while j < n and nums[j] > nums[j - 1]:
                j += 1
            res = max(res, j - i)
            i = j
        return res
```

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        n = len(nums)
        res = f = 1
        for i in range(1, n):
            f = 1 + (f if nums[i - 1] < nums[i] else 0)
            res = max(res, f)
        return res
```

### **Java**

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        for (int i = 1, f = 1; i < nums.length; ++i) {
            f = 1 + (nums[i - 1] < nums[i] ? f : 0);
            res = Math.max(res, f);
        }
        return res;
    }
}
```

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        for (int i = 0, n = nums.length; i < n;) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) {
                ++j;
            }
            res = Math.max(res, j - i);
            i = j;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        int res = 1;
        for (int i = 1, f = 1; i < nums.size(); ++i) {
            f = 1 + (nums[i - 1] < nums[i] ? f : 0);
            res = max(res, f);
        }
        return res;
    }
};
```

### **Go**

```go
func findLengthOfLCIS(nums []int) int {
	res, f := 1, 1
	for i := 1; i < len(nums); i++ {
		if nums[i-1] < nums[i] {
			f += 1
			res = max(res, f)
		} else {
			f = 1
		}
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function findLengthOfLCIS(nums: number[]): number {
    const n = nums.length;
    let res = 1;
    let i = 0;
    for (let j = 1; j < n; j++) {
        if (nums[j - 1] >= nums[j]) {
            res = Math.max(res, j - i);
            i = j;
        }
    }
    return Math.max(res, n - i);
}
```

### **...**

```

```

<!-- tabs:end -->
