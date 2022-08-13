# [724. Find Pivot Index](https://leetcode.com/problems/find-pivot-index)

[中文文档](/solution/0700-0799/0724.Find%20Pivot%20Index/README.md)

## Description

<p>Given an array of integers <code>nums</code>, calculate the <strong>pivot index</strong> of this array.</p>

<p>The <strong>pivot index</strong> is the index where the sum of all the numbers <strong>strictly</strong> to the left of the index is equal to the sum of all the numbers <strong>strictly</strong> to the index&#39;s right.</p>

<p>If the index is on the left edge of the array, then the left sum is <code>0</code> because there are no elements to the left. This also applies to the right edge of the array.</p>

<p>Return <em>the <strong>leftmost pivot index</strong></em>. If no such index exists, return -1.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,7,3,6,5,6]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong>
There is no index that satisfies the conditions in the problem statement.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as&nbsp;1991:&nbsp;<a href="https://leetcode.com/problems/find-the-middle-index-in-array/" target="_blank">https://leetcode.com/problems/find-the-middle-index-in-array/</a></p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        s, presum = sum(nums), 0
        for i, v in enumerate(nums):
            if (presum << 1) == s - v:
                return i
            presum += v
        return -1
```

```python
class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        l, r = 0, sum(nums)
        for i, v in enumerate(nums):
            r -= v
            if l == r:
                return i
            l += v
        return -1
```

### **Java**

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length, s = 0;
        for (int e : nums) {
            s += e;
        }
        int presum = 0;
        for (int i = 0; i < n; ++i) {
            // presum == sums - nums[i] - presum
            if (presum << 1 == s - nums[i]) {
                return i;
            }
            presum += nums[i];
        }
        return -1;
    }
}
```

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int l = 0, r = 0;
        for (int v : nums) {
            r += v;
        }
        for (int i = 0; i < nums.length; ++i) {
            r -= nums[i];
            if (l == r) {
                return i;
            }
            l += nums[i];
        }
        return -1;
    }
}
```

### **TypeScript**

```ts
function pivotIndex(nums: number[]): number {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    for (let i = 0; i < nums.length; ++i) {
        r -= nums[i];
        if (l == r) {
            return i;
        }
        l += nums[i];
    }
    return -1;
}
```

### **C++**

```cpp
class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        int s = 0;
        for (int e : nums)
            s += e;
        int presum = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (presum * 2 == s - nums[i])
                return i;
            presum += nums[i];
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        int l = 0, r = 0;
        for (int& v : nums) r += v;
        for (int i = 0; i < nums.size(); ++i)
        {
            r -= nums[i];
            if (l == r) return i;
            l += nums[i];
        }
        return -1;
    }
};
```

### **Go**

```go
func pivotIndex(nums []int) int {
	s := 0
	for _, e := range nums {
		s += e
	}
	presum := 0
	for i, e := range nums {
		if presum<<1 == s-e {
			return i
		}
		presum += e
	}
	return -1
}
```

```go
func pivotIndex(nums []int) int {
	l, r := 0, 0
	for _, v := range nums {
		r += v
	}
	for i, v := range nums {
		r -= v
		if l == r {
			return i
		}
		l += v
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
