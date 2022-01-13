# [747. 至少是其他数字两倍的最大数](https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others)

[English Version](/solution/0700-0799/0747.Largest%20Number%20At%20Least%20Twice%20of%20Others/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个给定的数组<code>nums</code>中，总是存在一个最大元素 。</p>

<p>查找数组中的最大元素是否至少是数组中每个其他数字的两倍。</p>

<p>如果是，则返回最大元素的索引，否则返回-1。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums = [3, 6, 1, 0]
<strong>输出:</strong> 1
<strong>解释:</strong> 6是最大的整数, 对于数组中的其他整数,
6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
</pre>

<p>&nbsp;</p>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums = [1, 2, 3, 4]
<strong>输出:</strong> -1
<strong>解释:</strong> 4没有超过3的两倍大, 所以我们返回 -1.
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ol>
	<li><code>nums</code>&nbsp;的长度范围在<code>[1, 50]</code>.</li>
	<li>每个&nbsp;<code>nums[i]</code>&nbsp;的整数范围在&nbsp;<code>[0, 100]</code>.</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历数组找到最大值和次大值，最后判断是否满足条件即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def dominantIndex(self, nums: List[int]) -> int:
        mx = mid = 0
        ans = -1
        for i, v in enumerate(nums):
            if v > mx:
                mid, mx = mx, v
                ans = i
            elif v > mid:
                mid = v
        return ans if mx >= 2 * mid else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int dominantIndex(int[] nums) {
        int mx = 0, mid = 0;
        int ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > mx) {
                mid = mx;
                mx = nums[i];
                ans = i;
            } else if (nums[i] > mid) {
                mid = nums[i];
            }
        }
        return mx >= mid * 2 ? ans : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int mx = 0, mid = 0;
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i)
        {
            if (nums[i] > mx)
            {
                mid = mx;
                mx = nums[i];
                ans = i;
            }
            else if (nums[i] > mid) mid = nums[i];
        }
        return mx >= mid * 2 ? ans : -1;
    }
};
```

### **Go**

```go
func dominantIndex(nums []int) int {
	mx, mid := 0, 0
	ans := 0
	for i, v := range nums {
		if v > mx {
			mid, mx = mx, v
			ans = i
		} else if v > mid {
			mid = v
		}
	}
	if mx >= mid*2 {
		return ans
	}
	return -1
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var dominantIndex = function (nums) {
    let mx = 0,
        mid = 0;
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] > mx) {
            mid = mx;
            mx = nums[i];
            ans = i;
        } else if (nums[i] > mid) {
            mid = nums[i];
        }
    }
    return mx >= mid * 2 ? ans : -1;
};
```

### **...**

```

```

<!-- tabs:end -->
