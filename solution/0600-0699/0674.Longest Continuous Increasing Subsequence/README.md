# [674. 最长连续递增序列](https://leetcode.cn/problems/longest-continuous-increasing-subsequence)

[English Version](/solution/0600-0699/0674.Longest%20Continuous%20Increasing%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个未经排序的整数数组，找到最长且<strong> 连续递增的子序列</strong>，并返回该序列的长度。</p>

<p><strong>连续递增的子序列</strong> 可以由两个下标 <code>l</code> 和 <code>r</code>（<code>l < r</code>）确定，如果对于每个 <code>l <= i < r</code>，都有 <code>nums[i] < nums[i + 1]</code> ，那么子序列 <code>[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]</code> 就是连续递增子序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,5,4,7]
<strong>输出：</strong>3
<strong>解释：</strong>最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2,2,2]
<strong>输出：</strong>1
<strong>解释：</strong>最长连续递增序列是 [2], 长度为1。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

设 f(i) 表示将数组第 i 项作为最长连续递增子序列的最后一项时，子序列的长度。

那么，当 `nums[i - 1] < nums[i]`，即 `f(i) = f(i - 1)` + 1，否则 `f(i) = 1`。问题转换为求 f(i) (`i ∈ [0 ,n - 1]`) 的最大值。

由于 f(i) 只与前一项 f(i - 1) 有关联，故不需要用一个数组存储。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

双指针：

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
