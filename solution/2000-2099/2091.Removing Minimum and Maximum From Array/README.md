# [2091. 从数组中移除最大值和最小值](https://leetcode.cn/problems/removing-minimum-and-maximum-from-array)

[English Version](/solution/2000-2099/2091.Removing%20Minimum%20and%20Maximum%20From%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，数组由若干 <strong>互不相同</strong> 的整数组成。</p>

<p><code>nums</code> 中有一个值最小的元素和一个值最大的元素。分别称为 <strong>最小值</strong> 和 <strong>最大值</strong> 。你的目标是从数组中移除这两个元素。</p>

<p>一次 <strong>删除</strong> 操作定义为从数组的 <strong>前面</strong> 移除一个元素或从数组的 <strong>后面</strong> 移除一个元素。</p>

<p>返回将数组中最小值和最大值 <strong>都</strong> 移除需要的最小删除次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,<em><strong>10</strong></em>,7,5,4,<em><strong>1</strong></em>,8,6]
<strong>输出：</strong>5
<strong>解释：</strong>
数组中的最小元素是 nums[5] ，值为 1 。
数组中的最大元素是 nums[1] ，值为 10 。
将最大值和最小值都移除需要从数组前面移除 2 个元素，从数组后面移除 3 个元素。
结果是 2 + 3 = 5 ，这是所有可能情况中的最小删除次数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,<em><strong>-4</strong></em>,<em><strong>19</strong></em>,1,8,-2,-3,5]
<strong>输出：</strong>3
<strong>解释：</strong>
数组中的最小元素是 nums[1] ，值为 -4 。
数组中的最大元素是 nums[2] ，值为 19 。
将最大值和最小值都移除需要从数组前面移除 3 个元素。
结果是 3 ，这是所有可能情况中的最小删除次数。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [<em><strong>101</strong></em>]
<strong>输出：</strong>1
<strong>解释：</strong>
数组中只有这一个元素，那么它既是数组中的最小值又是数组中的最大值。
移除它只需要 1 次删除操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> 中的整数 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先找出最小值和最大值的下标 mi, mx。如果 mi 下标大于 mx，则将 mx 与 mi 两数进行交换。

最小删除的次数，共有 3 种情况：

1. 从左侧往右依次删除 `nums[mi]` 和 `nums[mx]`
1. 从右侧往左依次删除 `nums[mx]` 和 `nums[mi]`
1. 从左侧往右删除 `nums[mi]`，从右侧往左删除 `nums[mx]`

求这 3 种情况的最小值即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumDeletions(self, nums: List[int]) -> int:
        mi = mx = 0
        for i, num in enumerate(nums):
            if num < nums[mi]:
                mi = i
            if num > nums[mx]:
                mx = i
        if mi > mx:
            mi, mx = mx, mi
        return min(mx + 1, len(nums) - mi, mi + 1 + len(nums) - mx)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumDeletions(int[] nums) {
        int mi = 0, mx = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < nums[mi]) {
                mi = i;
            }
            if (nums[i] > nums[mx]) {
                mx = i;
            }
        }
        if (mi > mx) {
            int t = mx;
            mx = mi;
            mi = t;
        }
        return Math.min(Math.min(mx + 1, n - mi), mi + 1 + n - mx);
    }
}
```

### **TypeScript**

```ts
function minimumDeletions(nums: number[]): number {
    const n = nums.length;
    if (n == 1) return 1;
    let i = nums.indexOf(Math.min(...nums));
    let j = nums.indexOf(Math.max(...nums));
    let left = Math.min(i, j);
    let right = Math.max(i, j);
    // 左右 left + 1 + n - right
    // 两个都是左边 left + 1 + right - left = right + 1
    // 都是右边 n - right + right - left = n - left
    return Math.min(left + 1 + n - right, right + 1, n - left);
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumDeletions(vector<int>& nums) {
        int mi = 0, mx = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (nums[i] < nums[mi]) mi = i;
            if (nums[i] > nums[mx]) mx = i;
        }
        if (mi > mx) {
            int t = mi;
            mi = mx;
            mx = t;
        }
        return min(min(mx + 1, n - mi), mi + 1 + n - mx);
    }
};
```

### **Go**

```go
func minimumDeletions(nums []int) int {
	mi, mx, n := 0, 0, len(nums)
	for i, num := range nums {
		if num < nums[mi] {
			mi = i
		}
		if num > nums[mx] {
			mx = i
		}
	}
	if mi > mx {
		mi, mx = mx, mi
	}
	return min(min(mx+1, n-mi), mi+1+n-mx)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
