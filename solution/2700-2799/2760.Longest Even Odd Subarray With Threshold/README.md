---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2760.Longest%20Even%20Odd%20Subarray%20With%20Threshold/README.md
rating: 1420
source: 第 352 场周赛 Q1
tags:
    - 数组
    - 滑动窗口
---

<!-- problem:start -->

# [2760. 最长奇偶子数组](https://leetcode.cn/problems/longest-even-odd-subarray-with-threshold)

[English Version](/solution/2700-2799/2760.Longest%20Even%20Odd%20Subarray%20With%20Threshold/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 和一个整数 <code>threshold</code> 。</p>

<p>请你从 <code>nums</code> 的子数组中找出以下标 <code>l</code> 开头、下标 <code>r</code> 结尾 <code>(0 &lt;= l &lt;= r &lt; nums.length)</code> 且满足以下条件的 <strong>最长子数组</strong> ：</p>

<ul>
	<li><code>nums[l] % 2 == 0</code></li>
	<li>对于范围&nbsp;<code>[l, r - 1]</code> 内的所有下标 <code>i</code> ，<code>nums[i] % 2 != nums[i + 1] % 2</code></li>
	<li>对于范围&nbsp;<code>[l, r]</code> 内的所有下标 <code>i</code> ，<code>nums[i] &lt;= threshold</code></li>
</ul>

<p>以整数形式返回满足题目要求的最长子数组的长度。</p>

<p><strong>注意：子数组</strong> 是数组中的一个连续非空元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,2,5,4], threshold = 5
<strong>输出：</strong>3
<strong>解释：</strong>在这个示例中，我们选择从 l = 1 开始、到 r = 3 结束的子数组 =&gt; [2,5,4] ，满足上述条件。
因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2], threshold = 2
<strong>输出：</strong>1
<strong>解释：</strong>
在这个示例中，我们选择从 l = 1 开始、到 r = 1 结束的子数组 =&gt; [2] 。
该子数组满足上述全部条件。可以证明 1 是满足题目要求的最大长度。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [2,3,4,5], threshold = 4
<strong>输出：</strong>3
<strong>解释：</strong>
在这个示例中，我们选择从 l = 0 开始、到 r = 2 结束的子数组 =&gt; [2,3,4] 。 
该子数组满足上述全部条件。
因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100 </code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100 </code></li>
	<li><code>1 &lt;= threshold &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们在 $[0,..n-1]$ 范围内枚举所有 $l$，如果 $nums[l]$ 满足 $nums[l] \bmod 2 = 0$ 并且 $nums[l] \leq threshold$，那么我们就从 $l+1$ 开始，查找第一个不满足条件的 $r$，那么此时以 $nums[l]$ 作为左端点的最长奇偶子数组的长度为 $r - l$，取所有 $r - l$ 的最大值作为答案即可。

时间复杂度 $O(n^2)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestAlternatingSubarray(self, nums: List[int], threshold: int) -> int:
        ans, n = 0, len(nums)
        for l in range(n):
            if nums[l] % 2 == 0 and nums[l] <= threshold:
                r = l + 1
                while r < n and nums[r] % 2 != nums[r - 1] % 2 and nums[r] <= threshold:
                    r += 1
                ans = max(ans, r - l)
        return ans
```

#### Java

```java
class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int ans = 0, n = nums.length;
        for (int l = 0; l < n; ++l) {
            if (nums[l] % 2 == 0 && nums[l] <= threshold) {
                int r = l + 1;
                while (r < n && nums[r] % 2 != nums[r - 1] % 2 && nums[r] <= threshold) {
                    ++r;
                }
                ans = Math.max(ans, r - l);
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
    int longestAlternatingSubarray(vector<int>& nums, int threshold) {
        int ans = 0, n = nums.size();
        for (int l = 0; l < n; ++l) {
            if (nums[l] % 2 == 0 && nums[l] <= threshold) {
                int r = l + 1;
                while (r < n && nums[r] % 2 != nums[r - 1] % 2 && nums[r] <= threshold) {
                    ++r;
                }
                ans = max(ans, r - l);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestAlternatingSubarray(nums []int, threshold int) (ans int) {
	n := len(nums)
	for l := range nums {
		if nums[l]%2 == 0 && nums[l] <= threshold {
			r := l + 1
			for r < n && nums[r]%2 != nums[r-1]%2 && nums[r] <= threshold {
				r++
			}
			ans = max(ans, r-l)
		}
	}
	return
}
```

#### TypeScript

```ts
function longestAlternatingSubarray(nums: number[], threshold: number): number {
    const n = nums.length;
    let ans = 0;
    for (let l = 0; l < n; ++l) {
        if (nums[l] % 2 === 0 && nums[l] <= threshold) {
            let r = l + 1;
            while (r < n && nums[r] % 2 !== nums[r - 1] % 2 && nums[r] <= threshold) {
                ++r;
            }
            ans = Math.max(ans, r - l);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：枚举优化

我们注意到，题目实际上会把数组划分成不相交的若干个满足条件的子数组，我们只需要找到这些子数组中最长的一个即可。因此，在枚举 $l$ 和 $r$ 时，我们不需要回退，只需要从左往右遍历一遍即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestAlternatingSubarray(self, nums: List[int], threshold: int) -> int:
        ans, l, n = 0, 0, len(nums)
        while l < n:
            if nums[l] % 2 == 0 and nums[l] <= threshold:
                r = l + 1
                while r < n and nums[r] % 2 != nums[r - 1] % 2 and nums[r] <= threshold:
                    r += 1
                ans = max(ans, r - l)
                l = r
            else:
                l += 1
        return ans
```

#### Java

```java
class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int ans = 0;
        for (int l = 0, n = nums.length; l < n;) {
            if (nums[l] % 2 == 0 && nums[l] <= threshold) {
                int r = l + 1;
                while (r < n && nums[r] % 2 != nums[r - 1] % 2 && nums[r] <= threshold) {
                    ++r;
                }
                ans = Math.max(ans, r - l);
                l = r;
            } else {
                ++l;
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
    int longestAlternatingSubarray(vector<int>& nums, int threshold) {
        int ans = 0;
        for (int l = 0, n = nums.size(); l < n;) {
            if (nums[l] % 2 == 0 && nums[l] <= threshold) {
                int r = l + 1;
                while (r < n && nums[r] % 2 != nums[r - 1] % 2 && nums[r] <= threshold) {
                    ++r;
                }
                ans = max(ans, r - l);
                l = r;
            } else {
                ++l;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestAlternatingSubarray(nums []int, threshold int) (ans int) {
	for l, n := 0, len(nums); l < n; {
		if nums[l]%2 == 0 && nums[l] <= threshold {
			r := l + 1
			for r < n && nums[r]%2 != nums[r-1]%2 && nums[r] <= threshold {
				r++
			}
			ans = max(ans, r-l)
			l = r
		} else {
			l++
		}
	}
	return
}
```

#### TypeScript

```ts
function longestAlternatingSubarray(nums: number[], threshold: number): number {
    const n = nums.length;
    let ans = 0;
    for (let l = 0; l < n; ) {
        if (nums[l] % 2 === 0 && nums[l] <= threshold) {
            let r = l + 1;
            while (r < n && nums[r] % 2 !== nums[r - 1] % 2 && nums[r] <= threshold) {
                ++r;
            }
            ans = Math.max(ans, r - l);
            l = r;
        } else {
            ++l;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
