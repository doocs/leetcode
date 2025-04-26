---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2444.Count%20Subarrays%20With%20Fixed%20Bounds/README.md
rating: 2092
source: 第 315 场周赛 Q4
tags:
    - 队列
    - 数组
    - 滑动窗口
    - 单调队列
---

<!-- problem:start -->

# [2444. 统计定界子数组的数目](https://leetcode.cn/problems/count-subarrays-with-fixed-bounds)

[English Version](/solution/2400-2499/2444.Count%20Subarrays%20With%20Fixed%20Bounds/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和两个整数 <code>minK</code> 以及 <code>maxK</code> 。</p>

<p><code>nums</code> 的定界子数组是满足下述条件的一个子数组：</p>

<ul>
	<li>子数组中的 <strong>最小值</strong> 等于 <code>minK</code> 。</li>
	<li>子数组中的 <strong>最大值</strong> 等于 <code>maxK</code> 。</li>
</ul>

<p>返回定界子数组的数目。</p>

<p>子数组是数组中的一个连续部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,5,2,7,5], minK = 1, maxK = 5
<strong>输出：</strong>2
<strong>解释：</strong>定界子数组是 [1,3,5] 和 [1,3,5,2] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,1,1], minK = 1, maxK = 1
<strong>输出：</strong>10
<strong>解释：</strong>nums 的每个子数组都是一个定界子数组。共有 10 个子数组。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], minK, maxK &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举右端点

由题意，我们可以知道，定界子数组的所有元素都在区间 $[\textit{minK}, \textit{maxK}]$ 中，且最小值一定为 $\textit{minK}$，最大值一定为 $\textit{maxK}$。

我们遍历数组 $\textit{nums}$，统计以 $\textit{nums}[i]$ 为右端点的定界子数组的个数，然后将所有的个数相加即可。

具体实现逻辑如下：

1. 维护最近一个不在区间 $[\textit{minK}, \textit{maxK}]$ 中的元素的下标 $k$，初始值为 $-1$。那么当前元素 $\textit{nums}[i]$ 的左端点一定大于 $k$。
2. 维护最近一个值为 $\textit{minK}$ 的下标 $j_1$，最近一个值为 $\textit{maxK}$ 的下标 $j_2$，初始值均为 $-1$。那么当前元素 $\textit{nums}[i]$ 的左端点一定小于等于 $\min(j_1, j_2)$。
3. 综上可知，以当前元素为右端点的定界子数组的个数为 $\max\bigl(0,\ \min(j_1, j_2) - k\bigr)$。累加所有的个数即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        j1 = j2 = k = -1
        ans = 0
        for i, v in enumerate(nums):
            if v < minK or v > maxK:
                k = i
            if v == minK:
                j1 = i
            if v == maxK:
                j2 = i
            ans += max(0, min(j1, j2) - k)
        return ans
```

#### Java

```java
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                k = i;
            }
            if (nums[i] == minK) {
                j1 = i;
            }
            if (nums[i] == maxK) {
                j2 = i;
            }
            ans += Math.max(0, Math.min(j1, j2) - k);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, int minK, int maxK) {
        long long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < static_cast<int>(nums.size()); ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                k = i;
            }
            if (nums[i] == minK) {
                j1 = i;
            }
            if (nums[i] == maxK) {
                j2 = i;
            }
            ans += max(0, min(j1, j2) - k);
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int, minK int, maxK int) int64 {
	ans := 0
	j1, j2, k := -1, -1, -1
	for i, v := range nums {
		if v < minK || v > maxK {
			k = i
		}
		if v == minK {
			j1 = i
		}
		if v == maxK {
			j2 = i
		}
		ans += max(0, min(j1, j2)-k)
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[], minK: number, maxK: number): number {
    let ans = 0;
    let [j1, j2, k] = [-1, -1, -1];
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] < minK || nums[i] > maxK) k = i;
        if (nums[i] === minK) j1 = i;
        if (nums[i] === maxK) j2 = i;
        ans += Math.max(0, Math.min(j1, j2) - k);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, min_k: i32, max_k: i32) -> i64 {
        let mut ans: i64 = 0;
        let mut j1: i64 = -1;
        let mut j2: i64 = -1;
        let mut k: i64 = -1;
        for (i, &v) in nums.iter().enumerate() {
            let i = i as i64;
            if v < min_k || v > max_k {
                k = i;
            }
            if v == min_k {
                j1 = i;
            }
            if v == max_k {
                j2 = i;
            }
            let m = j1.min(j2);
            if m > k {
                ans += m - k;
            }
        }
        ans
    }
}
```

#### C

```c
long long countSubarrays(int* nums, int numsSize, int minK, int maxK) {
    long long ans = 0;
    int j1 = -1, j2 = -1, k = -1;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] < minK || nums[i] > maxK) k = i;
        if (nums[i] == minK) j1 = i;
        if (nums[i] == maxK) j2 = i;
        int m = j1 < j2 ? j1 : j2;
        if (m > k) ans += (long long) (m - k);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
