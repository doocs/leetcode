---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3011.Find%20if%20Array%20Can%20Be%20Sorted/README.md
rating: 1496
tags:
    - 位运算
    - 数组
    - 排序
---

# [3011. 判断一个数组是否可以变为有序](https://leetcode.cn/problems/find-if-array-can-be-sorted)

[English Version](/solution/3000-3099/3011.Find%20if%20Array%20Can%20Be%20Sorted/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始且全是 <strong>正</strong>&nbsp;整数的数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>一次 <b>操作</b>&nbsp;中，如果两个 <strong>相邻</strong>&nbsp;元素在二进制下数位为 <strong>1</strong>&nbsp;的数目 <strong>相同</strong>&nbsp;，那么你可以将这两个元素交换。你可以执行这个操作 <strong>任意次</strong>&nbsp;（<strong>也可以 0 次</strong>）。</p>

<p>如果你可以使数组变有序，请你返回&nbsp;<code>true</code> ，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [8,4,2,30,15]
<b>输出：</b>true
<b>解释：</b>我们先观察每个元素的二进制表示。 2 ，4 和 8 分别都只有一个数位为 1 ，分别为 "10" ，"100" 和 "1000" 。15 和 30 分别有 4 个数位为 1 ："1111" 和 "11110" 。
我们可以通过 4 个操作使数组有序：
- 交换 nums[0] 和 nums[1] 。8 和 4 分别只有 1 个数位为 1 。数组变为 [4,8,2,30,15] 。
- 交换 nums[1] 和 nums[2] 。8 和 2 分别只有 1 个数位为 1 。数组变为 [4,2,8,30,15] 。
- 交换 nums[0] 和 nums[1] 。4 和 2 分别只有 1 个数位为 1 。数组变为 [2,4,8,30,15] 。
- 交换 nums[3] 和 nums[4] 。30 和 15 分别有 4 个数位为 1 ，数组变为 [2,4,8,15,30] 。
数组变成有序的，所以我们返回 true 。
注意我们还可以通过其他的操作序列使数组变得有序。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>true
<b>解释：</b>数组已经是有序的，所以我们返回 true 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,16,8,4,2]
<b>输出：</b>false
<b>解释：</b>无法通过操作使数组变为有序。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2<sup>8</sup></code></li>
</ul>

## 解法

### 方法一：双指针

我们可以使用双指针，将数组 $nums$ 分成若干个子数组，每个子数组中的元素的二进制表示中 $1$ 的个数相同。对于每个子数组，我们只需要关注它的最大值和最小值，如果最小值比上一个子数组的最大值小，那么就无法通过交换使得数组有序。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def canSortArray(self, nums: List[int]) -> bool:
        pre_mx = -inf
        i, n = 0, len(nums)
        while i < n:
            j = i + 1
            cnt = nums[i].bit_count()
            mi = mx = nums[i]
            while j < n and nums[j].bit_count() == cnt:
                mi = min(mi, nums[j])
                mx = max(mx, nums[j])
                j += 1
            if pre_mx > mi:
                return False
            pre_mx = mx
            i = j
        return True
```

```java
class Solution {
    public boolean canSortArray(int[] nums) {
        int preMx = -300;
        int i = 0, n = nums.length;
        while (i < n) {
            int j = i + 1;
            int cnt = Integer.bitCount(nums[i]);
            int mi = nums[i], mx = nums[i];
            while (j < n && Integer.bitCount(nums[j]) == cnt) {
                mi = Math.min(mi, nums[j]);
                mx = Math.max(mx, nums[j]);
                j++;
            }
            if (preMx > mi) {
                return false;
            }
            preMx = mx;
            i = j;
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool canSortArray(vector<int>& nums) {
        int preMx = -300;
        int i = 0, n = nums.size();
        while (i < n) {
            int j = i + 1;
            int cnt = __builtin_popcount(nums[i]);
            int mi = nums[i], mx = nums[i];
            while (j < n && __builtin_popcount(nums[j]) == cnt) {
                mi = min(mi, nums[j]);
                mx = max(mx, nums[j]);
                j++;
            }
            if (preMx > mi) {
                return false;
            }
            preMx = mx;
            i = j;
        }
        return true;
    }
};
```

```go
func canSortArray(nums []int) bool {
	preMx := -300
	i, n := 0, len(nums)
	for i < n {
		j := i + 1
		cnt := bits.OnesCount(uint(nums[i]))
		mi, mx := nums[i], nums[i]
		for j < n && bits.OnesCount(uint(nums[j])) == cnt {
			mi = min(mi, nums[j])
			mx = max(mx, nums[j])
			j++
		}
		if preMx > mi {
			return false
		}
		preMx = mx
		i = j
	}
	return true
}
```

```ts
function canSortArray(nums: number[]): boolean {
    let preMx = -300;
    const n = nums.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        const cnt = bitCount(nums[i]);
        let [mi, mx] = [nums[i], nums[i]];
        while (j < n && bitCount(nums[j]) === cnt) {
            mi = Math.min(mi, nums[j]);
            mx = Math.max(mx, nums[j]);
            j++;
        }
        if (preMx > mi) {
            return false;
        }
        preMx = mx;
        i = j;
    }
    return true;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- end -->
