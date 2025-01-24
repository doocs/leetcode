---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2229.Check%20if%20an%20Array%20Is%20Consecutive/README.md
tags:
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [2229. 检查数组是否连贯 🔒](https://leetcode.cn/problems/check-if-an-array-is-consecutive)

[English Version](/solution/2200-2299/2229.Check%20if%20an%20Array%20Is%20Consecutive/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，如果 <code>nums</code> 是一个 <strong>连贯数组</strong> ，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p><span style="">如果数组包含 </span><code>[x, x + n - 1]</code><span style=""> 范围内的所有数字（包括 <code>x</code> 和 <code>x + n - 1</code> ），则该数组为连贯数组；其中</span> <code>x</code><span style=""> 是数组中最小的数，</span><code>n</code> <span style="">是数组的长度。</span></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,4,2]
<strong>输出：</strong>true
<strong>解释：</strong>
最小值是 1 ，数组长度为 4 。
范围 [x, x + n - 1] 中的所有值都出现在 nums 中：[1, 1 + 4 - 1] = [1, 4] = (1, 2, 3, 4) 。
因此，nums 是一个连贯数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3]
<strong>输出：</strong>false
<strong>解释：
</strong>最小值是 1 ，数组长度为 2 。 
范围 [x, x + n - 1] 中的所有值没有都出现在 nums 中：[1, 1 + 2 - 1] = [1, 2] = (1, 2) 。 
因此，nums 不是一个连贯数组。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,5,4]
<strong>输出：</strong>true
<strong>解释：</strong>
最小值是 3 ，数组长度为 3 。
范围 [x, x + n - 1] 中的所有值都出现在 nums 中：[3, 3 + 3 - 1] = [3, 5] = (3，4，5) 。
因此，nums 是一个连贯数组。
</pre>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\textit{s}$ 来存储数组 $\textit{nums}$ 中的所有元素，用两个变量 $\textit{mi}$ 和 $\textit{mx}$ 分别表示数组中的最小值和最大值。

如果数组中的所有元素都不相同，且数组的长度等于最大值和最小值之间的差值加 $1$，那么数组就是连贯数组，返回 $\textit{true}$；否则返回 $\textit{false}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isConsecutive(self, nums: List[int]) -> bool:
        mi, mx = min(nums), max(nums)
        return len(set(nums)) == mx - mi + 1 == len(nums)
```

#### Java

```java
class Solution {
    public boolean isConsecutive(int[] nums) {
        int mi = nums[0], mx = 0;
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            if (!s.add(x)) {
                return false;
            }
            mi = Math.min(mi, x);
            mx = Math.max(mx, x);
        }
        return mx - mi + 1 == nums.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isConsecutive(vector<int>& nums) {
        unordered_set<int> s;
        int mi = nums[0], mx = 0;
        for (int x : nums) {
            if (s.contains(x)) {
                return false;
            }
            s.insert(x);
            mi = min(mi, x);
            mx = max(mx, x);
        }
        return mx - mi + 1 == nums.size();
    }
};
```

#### Go

```go
func isConsecutive(nums []int) bool {
	s := map[int]bool{}
	mi, mx := nums[0], 0
	for _, x := range nums {
		if s[x] {
			return false
		}
		s[x] = true
		mi = min(mi, x)
		mx = max(mx, x)
	}
	return mx-mi+1 == len(nums)
}
```

#### TypeScript

```ts
function isConsecutive(nums: number[]): boolean {
    let [mi, mx] = [nums[0], 0];
    const s = new Set<number>();
    for (const x of nums) {
        if (s.has(x)) {
            return false;
        }
        s.add(x);
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    return mx - mi + 1 === nums.length;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isConsecutive = function (nums) {
    let [mi, mx] = [nums[0], 0];
    const s = new Set();
    for (const x of nums) {
        if (s.has(x)) {
            return false;
        }
        s.add(x);
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    return mx - mi + 1 === nums.length;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
