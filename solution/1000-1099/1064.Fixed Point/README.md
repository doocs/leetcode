---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1064.Fixed%20Point/README.md
rating: 1307
tags:
    - 数组
    - 二分查找
---

# [1064. 不动点 🔒](https://leetcode.cn/problems/fixed-point)

[English Version](/solution/1000-1099/1064.Fixed%20Point/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定已经按 <strong>升序</strong> 排列、由不同整数组成的数组 <code>arr</code>，返回满足 <code>arr[i] == i</code> 的最小索引 <code>i</code>。如果不存在这样的 <code>i</code>，返回 <code>-1</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [-10,-5,0,3,7]
<strong>输出：</strong>3
<strong>解释：</strong>对于给定的数组，<code>arr[0] = -10，arr[1] = -5，arr[2] = 0，arr[3] = 3</code>，因此输出为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,2,5,8,17]
<strong>输出：</strong>0
<strong>解释：</strong><code>arr[0] = 0</code>，因此输出为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [-10,-5,3,4,7,9]
<strong>输出：</strong>-1
<strong>解释：</strong>不存在这样的 i 满足 <code>arr[i] = i</code>，因此输出为 -1 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length < 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> <= arr[i] <= 10<sup>9</sup></code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>时间复杂度为 <code>O(n)</code> 的解决方案很直观也很简单。你可以设计更优的解决方案吗？</p>

## 解法

### 方法一：二分查找

题目给定的数组是按升序排列的，因此我们可以使用二分查找的方法找出最小的满足 $arr[i]$ 等于 $i$ 的下标 $i$。

我们定义二分查找的左边界 $left=0$，右边界 $right=n-1$。每一次，我们找到当前的中间位置 $mid$，如果中间位置满足 $arr[mid] \geq mid$，那么我们就确定了最小的不动点 🔒 的位置一定不会出现在下标大于 $mid$ 的位置，因此我们令 $right=mid$；如果中间位置满足 $arr[mid] \lt mid$，那么最小的不动点 🔒 一定出现在下标大于 $mid$ 的位置，因此我们令 $left=mid+1$。

最后，如果我们没有找到最小的不动点 🔒，那么我们返回 $-1$。

时间复杂度 $O(\log n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def fixedPoint(self, arr: List[int]) -> int:
        left, right = 0, len(arr) - 1
        while left < right:
            mid = (left + right) >> 1
            if arr[mid] >= mid:
                right = mid
            else:
                left = mid + 1
        return left if arr[left] == left else -1
```

```java
class Solution {
    public int fixedPoint(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] == left ? left : -1;
    }
}
```

```cpp
class Solution {
public:
    int fixedPoint(vector<int>& arr) {
        int left = 0, right = arr.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (arr[mid] >= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] == left ? left : -1;
    }
};
```

```go
func fixedPoint(arr []int) int {
	left, right := 0, len(arr)-1
	for left < right {
		mid := (left + right) >> 1
		if arr[mid] >= mid {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if arr[left] == left {
		return left
	}
	return -1
}
```

```ts
function fixedPoint(arr: number[]): number {
    let left = 0;
    let right = arr.length - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (arr[mid] >= mid) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return arr[left] === left ? left : -1;
}
```

<!-- tabs:end -->

<!-- end -->
