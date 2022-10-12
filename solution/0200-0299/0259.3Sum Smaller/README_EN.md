# [259. 3Sum Smaller](https://leetcode.com/problems/3sum-smaller)

[中文文档](/solution/0200-0299/0259.3Sum%20Smaller/README.md)

## Description

<p>Given an array of <code>n</code> integers <code>nums</code> and an integer&nbsp;<code>target</code>, find the number of index triplets <code>i</code>, <code>j</code>, <code>k</code> with <code>0 &lt;= i &lt; j &lt; k &lt; n</code> that satisfy the condition <code>nums[i] + nums[j] + nums[k] &lt; target</code>.</p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,0,1,3], target = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> Because there are two triplets which sums are less than 2:
[-2,0,1]
[-2,0,3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [], target = 0
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0], target = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>0 &lt;= n &lt;= 3500</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>-100 &lt;= target &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        nums.sort()
        ans, n = 0, len(nums)
        for i in range(n):
            j, k = i + 1, n - 1
            while j < k:
                s = nums[i] + nums[j] + nums[k]
                if s >= target:
                    k -= 1
                else:
                    ans += k - j
                    j += 1
        return ans
```

### **Java**

```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, n = nums.length; i < n; ++i) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int s = nums[i] + nums[j] + nums[k];
                if (s >= target) {
                    --k;
                } else {
                    ans += k - j;
                    ++j;
                }
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
    int threeSumSmaller(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int i = 0, n = nums.size(); i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int s = nums[i] + nums[j] + nums[k];
                if (s >= target)
                    --k;
                else {
                    ans += k - j;
                    ++j;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func threeSumSmaller(nums []int, target int) int {
	sort.Ints(nums)
	ans := 0
	for i, n := 0, len(nums); i < n; i++ {
		j, k := i+1, n-1
		for j < k {
			s := nums[i] + nums[j] + nums[k]
			if s >= target {
				k--
			} else {
				ans += k - j
				j++
			}
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumSmaller = function (nums, target) {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0, n = nums.length; i < n; ++i) {
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            s = nums[i] + nums[j] + nums[k];
            if (s >= target) {
                --k;
            } else {
                ans += k - j;
                ++j;
            }
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
