---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3550.Smallest%20Index%20With%20Digit%20Sum%20Equal%20to%20Index/README.md
---

<!-- problem:start -->

# [3550. 数位和等于下标的最小下标](https://leetcode.cn/problems/smallest-index-with-digit-sum-equal-to-index)

[English Version](/solution/3500-3599/3550.Smallest%20Index%20With%20Digit%20Sum%20Equal%20to%20Index/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>返回满足 <code>nums[i]</code>&nbsp;的数位和（每一位数字相加求和）等于 <code>i</code>&nbsp;的 <strong>最小</strong>&nbsp;下标&nbsp;<code>i</code> 。</p>

<p>如果不存在满足要求的下标，返回&nbsp;<code>-1</code> 。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,3,2]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<ul>
	<li><code>nums[2] = 2</code>，其数位和等于&nbsp;2 ，与其下标&nbsp;<code>i = 2</code>&nbsp;相等。因此，输出为&nbsp;2 。</li>
</ul>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,10,11]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<ul>
	<li><code>nums[1] = 10</code>，其数位和等于&nbsp;<code>1 + 0 = 1</code>，与其下标 <code>i = 1</code>&nbsp;相等。</li>
	<li><code>nums[2] = 11</code>，其数位和等于是 <code>1 + 1 = 2</code>，与其下标&nbsp;<code>i = 2</code>&nbsp;相等。</li>
	<li>由于下标 1 是满足要求的最小下标，输出为&nbsp;1 。</li>
</ul>
</div>

<p><b>示例 3：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3]</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><b>解释：</b></p>

<ul>
	<li>由于不存在满足要求的下标，输出为&nbsp;-1 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 数位和

我们可以从下标 $i = 0$ 开始，遍历数组中的每个元素 $x$，计算 $x$ 的数位和 $s$。如果 $s = i$，则返回下标 $i$。如果遍历完所有元素都没有找到满足条件的下标，则返回 -1。

时间复杂度 $o(n)$，其中 $n$ 是数组的长度。空间复杂度 $o(1)$，只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestIndex(self, nums: List[int]) -> int:
        for i, x in enumerate(nums):
            s = 0
            while x:
                s += x % 10
                x //= 10
            if s == i:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int s = 0;
            while (nums[i] != 0) {
                s += nums[i] % 10;
                nums[i] /= 10;
            }
            if (s == i) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestIndex(vector<int>& nums) {
        for (int i = 0; i < nums.size(); ++i) {
            int s = 0;
            while (nums[i]) {
                s += nums[i] % 10;
                nums[i] /= 10;
            }
            if (s == i) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func smallestIndex(nums []int) int {
	for i, x := range nums {
		s := 0
		for ; x > 0; x /= 10 {
			s += x % 10
		}
		if s == i {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function smallestIndex(nums: number[]): number {
    for (let i = 0; i < nums.length; ++i) {
        let s = 0;
        for (; nums[i] > 0; nums[i] = Math.floor(nums[i] / 10)) {
            s += nums[i] % 10;
        }
        if (s === i) {
            return i;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
