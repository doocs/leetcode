# [413. Arithmetic Slices](https://leetcode.com/problems/arithmetic-slices)

[中文文档](/solution/0400-0499/0413.Arithmetic%20Slices/README.md)

## Description

<p>An integer array is called arithmetic if it consists of <strong>at least three elements</strong> and if the difference between any two consecutive elements is the same.</p>

<ul>
	<li>For example, <code>[1,3,5,7,9]</code>, <code>[7,7,7,7]</code>, and <code>[3,-1,-5,-9]</code> are arithmetic sequences.</li>
</ul>

<p>Given an integer array <code>nums</code>, return <em>the number of arithmetic <strong>subarrays</strong> of</em> <code>nums</code>.</p>

<p>A <strong>subarray</strong> is a contiguous subsequence of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        ans, cnt = 0, 2
        d = 3000
        for a, b in pairwise(nums):
            if b - a == d:
                cnt += 1
            else:
                d = b - a
                cnt = 2
            ans += max(0, cnt - 2)
        return ans
```

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        ans = cnt = 0
        d = 3000
        for a, b in pairwise(nums):
            if b - a == d:
                cnt += 1
            else:
                d = b - a
                cnt = 0
            ans += cnt
        return ans
```

### **Java**

```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int ans = 0, cnt = 0;
        int d = 3000;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i + 1] - nums[i] == d) {
                ++cnt;
            } else {
                d = nums[i + 1] - nums[i];
                cnt = 0;
            }
            ans += cnt;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int ans = 0, cnt = 0;
        int d = 3000;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i + 1] - nums[i] == d) {
                ++cnt;
            } else {
                d = nums[i + 1] - nums[i];
                cnt = 0;
            }
            ans += cnt;
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfArithmeticSlices(nums []int) (ans int) {
	cnt, d := 0, 3000
	for i, b := range nums[1:] {
		a := nums[i]
		if b-a == d {
			cnt++
		} else {
			d = b - a
			cnt = 0
		}
		ans += cnt
	}
	return
}
```

### **TypeScript**

```ts
function numberOfArithmeticSlices(nums: number[]): number {
    let ans = 0;
    let cnt = 0;
    let d = 3000;
    for (let i = 0; i < nums.length - 1; ++i) {
        const a = nums[i];
        const b = nums[i + 1];
        if (b - a == d) {
            ++cnt;
        } else {
            d = b - a;
            cnt = 0;
        }
        ans += cnt;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
