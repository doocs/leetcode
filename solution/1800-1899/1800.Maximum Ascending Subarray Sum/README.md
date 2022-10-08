# [1800. 最大升序子数组和](https://leetcode.cn/problems/maximum-ascending-subarray-sum)

[English Version](/solution/1800-1899/1800.Maximum%20Ascending%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数组成的数组 <code>nums</code> ，返回 <code>nums</code> 中一个 <strong>升序 </strong>子数组的最大可能元素和。</p>

<p>子数组是数组中的一个连续数字序列。</p>

<p>已知子数组 <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> ，若对所有 <code>i</code>（<code>l <= i < r</code>），<code>nums<sub>i </sub> < nums<sub>i+1</sub></code> 都成立，则称这一子数组为 <strong>升序</strong> 子数组。注意，大小为 <code>1</code> 的子数组也视作 <strong>升序</strong> 子数组。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,20,30,5,10,50]
<strong>输出：</strong>65
<strong>解释：</strong>[5,10,50] 是元素和最大的升序子数组，最大元素和为 65 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,20,30,40,50]
<strong>输出：</strong>150
<strong>解释：</strong>[10,20,30,40,50] 是元素和最大的升序子数组，最大元素和为 150 。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [12,17,15,13,10,11,12]
<strong>输出：</strong>33
<strong>解释：</strong>[10,11,12] 是元素和最大的升序子数组，最大元素和为 33 。 
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums = [100,10,1]
<strong>输出：</strong>100
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 100</code></li>
	<li><code>1 <= nums[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接模拟**

我们用变量 `t` 记录当前升序子数组的和，用变量 `ans` 记录最大的升序子数组和。

遍历数组 `nums`：

如果当前元素是数组的第一个元素，或者当前元素大于前一个元素，那么将当前元素加入到当前升序子数组的和，即 `t += nums[i]`，并且更新最大升序子数组和 `ans = max(ans, t)`；否则，当前元素不满足升序子数组的条件，那么将当前升序子数组的和 `t` 重置为当前元素，即 `t = nums[i]`。

遍历结束，返回最大升序子数组和 `ans`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxAscendingSum(self, nums: List[int]) -> int:
        ans = t = 0
        for i, v in enumerate(nums):
            if i == 0 or v > nums[i - 1]:
                t += v
                ans = max(ans, t)
            else:
                t = v
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxAscendingSum(int[] nums) {
        int ans = 0, t = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                t += nums[i];
                ans = Math.max(ans, t);
            } else {
                t = nums[i];
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
    int maxAscendingSum(vector<int>& nums) {
        int ans = 0, t = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                t += nums[i];
                ans = max(ans, t);
            } else {
                t = nums[i];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxAscendingSum(nums []int) int {
	ans, t := 0, 0
	for i, v := range nums {
		if i == 0 || v > nums[i-1] {
			t += v
			if ans < t {
				ans = t
			}
		} else {
			t = v
		}
	}
	return ans
}
```

### **C**

```c
#define max(a,b) (((a) > (b)) ? (a) : (b))

int maxAscendingSum(int* nums, int numsSize){
    int res = nums[0];
    int sum = nums[0];
    for (int i = 1; i < numsSize; i++) {
        if (nums[i - 1] >= nums[i]) {
            res = max(res, sum);
            sum = 0;
        }
        sum += nums[i];
    }
    return max(res, sum);
}
```

### **TypeScript**

```ts
function maxAscendingSum(nums: number[]): number {
    const n = nums.length;
    let res = nums[0];
    let sum = nums[0];
    for (let i = 1; i < n; i++) {
        if (nums[i] <= nums[i - 1]) {
            res = Math.max(res, sum);
            sum = 0;
        }
        sum += nums[i];
    }
    return Math.max(res, sum);
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_ascending_sum(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = nums[0];
        let mut sum = nums[0];
        for i in 1..n {
            if nums[i - 1] >= nums[i] {
                res = res.max(sum);
                sum = 0;
            }
            sum += nums[i];
        }
        res.max(sum)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
