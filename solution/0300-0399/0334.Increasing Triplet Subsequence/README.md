# [334. 递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence)

[English Version](/solution/0300-0399/0334.Increasing%20Triplet%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，判断这个数组中是否存在长度为 <code>3</code> 的递增子序列。</p>

<p>如果存在这样的三元组下标 <code>(i, j, k)</code> 且满足 <code>i < j < k</code> ，使得 <code>nums[i] < nums[j] < nums[k]</code> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,5]
<strong>输出：</strong>true
<strong>解释：</strong>任何 i < j < k 的三元组都满足题意
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,3,2,1]
<strong>输出：</strong>false
<strong>解释：</strong>不存在满足题意的三元组</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,5,0,4,6]
<strong>输出：</strong>true
<strong>解释：</strong>三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能实现时间复杂度为 <code>O(n)</code> ，空间复杂度为 <code>O(1)</code> 的解决方案吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

用 min, mid 记录遍历过程中遇到的最小值以及中间值，若出现 num > mid，说明找到了满足题目的三元组，返回 true；否则遍历结束返回 false。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        mi, mid = float('inf'), float('inf')
        for num in nums:
            if num > mid:
                return True
            if num <= mi:
                mi = num
            else:
                mid = num
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] lmi = new int[n];
        int[] rmx = new int[n];
        lmi[0] = Integer.MAX_VALUE;
        rmx[n - 1] = Integer.MIN_VALUE;
        for (int i = 1; i < n; ++i) {
            lmi[i] = Math.min(lmi[i - 1], nums[i - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            rmx[i] = Math.max(rmx[i + 1], nums[i + 1]);
        }
        for (int i = 0; i < n; ++i) {
            if (lmi[i] < nums[i] && nums[i] < rmx[i]) {
                return true;
            }
        }
        return false;
    }
}
```

空间优化：

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > mid) {
                return true;
            }
            if (num <= min) {
                min = num;
            } else {
                mid = num;
            }
        }
        return false;
    }
}
```

### **TypeScript**

```ts
function increasingTriplet(nums: number[]): boolean {
    let n = nums.length;
    if (n < 3) return false;
    let min = nums[0],
        mid = Number.MAX_SAFE_INTEGER;
    for (let num of nums) {
        if (num <= min) {
            min = num;
        } else if (num <= mid) {
            mid = num;
        } else if (num > mid) {
            return true;
        }
    }
    return false;
}
```

### **C++**

```cpp
class Solution {
public:
    bool increasingTriplet(vector<int>& nums) {
        int mi = INT_MAX, mid = INT_MAX;
        for (int num : nums)
        {
            if (num > mid) return true;
            if (num <= mi) mi = num;
            else mid = num;
        }
        return false;
    }
};
```

### **Go**

```go
func increasingTriplet(nums []int) bool {
	min, mid := math.MaxInt32, math.MaxInt32
	for _, num := range nums {
		if num > mid {
			return true
		}
		if num <= min {
			min = num
		} else {
			mid = num
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
