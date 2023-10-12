# [162. 寻找峰值](https://leetcode.cn/problems/find-peak-element)

[English Version](/solution/0100-0199/0162.Find%20Peak%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>峰值元素是指其值严格大于左右相邻值的元素。</p>

<p>给你一个整数数组&nbsp;<code>nums</code>，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 <strong>任何一个峰值</strong> 所在位置即可。</p>

<p>你可以假设&nbsp;<code>nums[-1] = nums[n] = -∞</code> 。</p>

<p>你必须实现时间复杂度为 <code>O(log n)</code><em> </em>的算法来解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = <code>[1,2,3,1]</code>
<strong>输出：</strong>2
<strong>解释：</strong>3 是峰值元素，你的函数应该返回其索引 2。</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = <code>[</code>1,2,1,3,5,6,4]
<strong>输出：</strong>1 或 5 
<strong>解释：</strong>你的函数可以返回索引 1，其峰值元素为 2；
&nbsp;    或者返回索引 5， 其峰值元素为 6。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>对于所有有效的 <code>i</code> 都有 <code>nums[i] != nums[i + 1]</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们定义二分查找的左边界 $left=0$，右边界 $right=n-1$，其中 $n$ 是数组的长度。在每一步二分查找中，我们找到当前区间的中间元素 $mid$，然后比较 $mid$ 与其右边元素 $mid+1$ 的值：

-   如果 $mid$ 的值大于 $mid+1$ 的值，则左侧存在峰值元素，我们将右边界 $right$ 更新为 $mid$；
-   否则，右侧存在峰值元素，我们将左边界 $left$ 更新为 $mid+1$。
-   最后，当左边界 $left$ 与右边界 $right$ 相等时，我们就找到了数组的峰值元素。

时间复杂度 $O(\log n)$，其中 $n$ 是数组 $nums$ 的长度。每一步二分查找可以将搜索区间减少一半，因此时间复杂度为 $O(\log n)$。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            if nums[mid] > nums[mid + 1]:
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findPeakElement(vector<int>& nums) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func findPeakElement(nums []int) int {
	left, right := 0, len(nums)-1
	for left < right {
		mid := (left + right) >> 1
		if nums[mid] > nums[mid+1] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **TypeScript**

```ts
function findPeakElement(nums: number[]): number {
    let [left, right] = [0, nums.length - 1];
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] > nums[mid + 1]) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
