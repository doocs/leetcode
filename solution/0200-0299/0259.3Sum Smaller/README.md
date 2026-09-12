---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0259.3Sum%20Smaller/README.md
tags:
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [259. 较小的三数之和 🔒](https://leetcode.cn/problems/3sum-smaller)

[English Version](/solution/0200-0299/0259.3Sum%20Smaller/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组和一个目标值 <code>target</code>&nbsp;，寻找能够使条件&nbsp;<code>nums[i] + nums[j] + nums[k] &lt; target</code>&nbsp;成立的三元组&nbsp; <code>i, j, k</code>&nbsp;个数（<code>0 &lt;= i &lt; j &lt; k &lt; n</code>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[-2,0,1,3]</code>, <em>target</em> = 2
<strong>输出: </strong>2 
<strong>解释: </strong>因为一共有两个三元组满足累加和小于 2:
&nbsp;    [-2,0,1]
     [-2,0,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[]</code>, <em>target</em> = 0
<strong>输出: </strong>0 </pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[0]</code>, <em>target</em> = 0
<strong>输出: </strong>0 </pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>0 &lt;= n &lt;= 3500</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>-100 &lt;= target &lt;= 100</code></li>
	<li>输入保证答案小于或等于 10<sup>9</sup>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 双指针 + 枚举

<!-- thinking:start -->

> **思考**
>
> 三元组顺序无关，可先排序。固定最小下标 $i$ 后，在右侧用双指针统计和小于 $\textit{target}$ 的对数。
>
> 若 $nums[i]+nums[j]+nums[k]<\textit{target}$，则 $j$ 与 $(j,k]$ 中每个 $k'$ 都合法，一次加上 $k-j$ 并右移 $j$；否则左移 $k$。

<!-- thinking:end -->

由于元素的顺序不影响结果，我们可以先对数组进行排序，然后使用双指针的方法来解决这个问题。

我们先将数组排序，然后枚举第一个元素 $\textit{nums}[i]$，并在 $\textit{nums}[i+1:n-1]$ 的区间内使用双指针分别指向 $\textit{nums}[j]$ 和 $\textit{nums}[k]$，其中 $j$ 是 $\textit{nums}[i]$ 的下一个元素，而 $k$ 是数组的最后一个元素。

- 如果 $\textit{nums}[i] + \textit{nums}[j] + \textit{nums}[k] < \textit{target}$，那么对于任意 $j \lt k' \leq k$ 的元素，都有 $\textit{nums}[i] + \textit{nums}[j] + \textit{nums}[k'] \lt \textit{target}$，一共有 $k - j$ 个这样的 $k'$，我们将 $k - j$ 累加到答案中。接下来，将 $j$ 右移一个位置，继续寻找下一个满足条件的 $k$，直到 $j \geq k$ 为止。
- 如果 $\textit{nums}[i] + \textit{nums}[j] + \textit{nums}[k] \geq \textit{target}$，那么对于任意 $j \leq j' \lt k$ 的元素，都不可能使得 $\textit{nums}[i] + \textit{nums}[j'] + \textit{nums}[k] \lt \textit{target}$，因此我们将 $k$ 左移一个位置，继续寻找下一个满足条件的 $k$，直到 $j \geq k$ 为止。

枚举完所有的 $i$ 后，我们就得到了满足条件的三元组的个数。

时间复杂度 $O(n^2)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        nums.sort()
        ans, n = 0, len(nums)
        for i in range(n - 2):
            j, k = i + 1, n - 1
            while j < k:
                x = nums[i] + nums[j] + nums[k]
                if x < target:
                    ans += k - j
                    j += 1
                else:
                    k -= 1
        return ans
```

#### Java

```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;
        for (int i = 0; i + 2 < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int x = nums[i] + nums[j] + nums[k];
                if (x < target) {
                    ans += k - j;
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int threeSumSmaller(vector<int>& nums, int target) {
        ranges::sort(nums);
        int ans = 0, n = nums.size();
        for (int i = 0; i + 2 < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int x = nums[i] + nums[j] + nums[k];
                if (x < target) {
                    ans += k - j;
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func threeSumSmaller(nums []int, target int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for i := 0; i < n-2; i++ {
		j, k := i+1, n-1
		for j < k {
			x := nums[i] + nums[j] + nums[k]
			if x < target {
				ans += k - j
				j++
			} else {
				k--
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function threeSumSmaller(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 2; ++i) {
        let [j, k] = [i + 1, n - 1];
        while (j < k) {
            const x = nums[i] + nums[j] + nums[k];
            if (x < target) {
                ans += k - j;
                ++j;
            } else {
                --k;
            }
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumSmaller = function (nums, target) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 2; ++i) {
        let [j, k] = [i + 1, n - 1];
        while (j < k) {
            const x = nums[i] + nums[j] + nums[k];
            if (x < target) {
                ans += k - j;
                ++j;
            } else {
                --k;
            }
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
