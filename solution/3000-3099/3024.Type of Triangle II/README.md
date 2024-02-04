# [3024. 三角形类型 II](https://leetcode.cn/problems/type-of-triangle-ii)

[English Version](/solution/3000-3099/3024.Type%20of%20Triangle%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>3</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，需要用它们来构造三角形。</p>

<ul>
	<li>如果一个三角形的所有边长度相等，那么这个三角形称为&nbsp;<strong>equilateral</strong>&nbsp;。</li>
	<li>如果一个三角形恰好有两条边长度相等，那么这个三角形称为&nbsp;<strong>isosceles</strong>&nbsp;。</li>
	<li>如果一个三角形三条边的长度互不相同，那么这个三角形称为&nbsp;<strong>scalene</strong>&nbsp;。</li>
</ul>

<p>如果这个数组无法构成一个三角形，请你返回字符串&nbsp;<code>"none"</code>&nbsp;，否则返回一个字符串表示这个三角形的类型。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,3,3]
<b>输出：</b>"equilateral"
<b>解释：</b>由于三条边长度相等，所以可以构成一个等边三角形，返回 "equilateral" 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [3,4,5]
<b>输出：</b>"scalene"
<b>解释：</b>
nums[0] + nums[1] = 3 + 4 = 7 ，大于 nums[2] = 5<code>&nbsp;。</code>
nums[0] + nums[2] = 3 + 5 = 8 ，大于 nums[1] = 4 。
nums[1] + nums[2] = 4 + 5 = 9 ，大于 nums[0] = 3 。
由于任意两边纸盒都大于第三边，所以可以构成一个三角形。
因为三条边的长度互不相等，所以返回 "scalene" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == 3</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：排序 + 分类讨论

我们先对数组进行排序，然后根据三角形的定义进行分类讨论即可。

-   如果最小的两个数之和小于等于最大的数，那么无法构成三角形，返回 "none"。
-   如果最小的数等于最大的数，那么是等边三角形，返回 "equilateral"。
-   如果最小的数等于中间的数或者中间的数等于最大的数，那么是等腰三角形，返回 "isosceles"。
-   否则，返回 "scalene"。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def triangleType(self, nums: List[int]) -> str:
        nums.sort()
        if nums[0] + nums[1] <= nums[2]:
            return "none"
        if nums[0] == nums[2]:
            return "equilateral"
        if nums[0] == nums[1] or nums[1] == nums[2]:
            return "isosceles"
        return "scalene"
```

```java
class Solution {
    public String triangleType(int[] nums) {
        Arrays.sort(nums);
        if (nums[0] + nums[1] <= nums[2]) {
            return "none";
        }
        if (nums[0] == nums[2]) {
            return "equilateral";
        }
        if (nums[0] == nums[1] || nums[1] == nums[2]) {
            return "isosceles";
        }
        return "scalene";
    }
}
```

```cpp
class Solution {
public:
    string triangleType(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        if (nums[0] + nums[1] <= nums[2]) {
            return "none";
        }
        if (nums[0] == nums[2]) {
            return "equilateral";
        }
        if (nums[0] == nums[1] || nums[1] == nums[2]) {
            return "isosceles";
        }
        return "scalene";
    }
};
```

```go
func triangleType(nums []int) string {
	sort.Ints(nums)
	if nums[0]+nums[1] <= nums[2] {
		return "none"
	}
	if nums[0] == nums[2] {
		return "equilateral"
	}
	if nums[0] == nums[1] || nums[1] == nums[2] {
		return "isosceles"
	}
	return "scalene"
}
```

```ts
function triangleType(nums: number[]): string {
    nums.sort((a, b) => a - b);
    if (nums[0] + nums[1] <= nums[2]) {
        return 'none';
    }
    if (nums[0] === nums[2]) {
        return 'equilateral';
    }
    if (nums[0] === nums[1] || nums[1] === nums[2]) {
        return 'isosceles';
    }
    return 'scalene';
}
```

<!-- tabs:end -->

<!-- end -->
