---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1885.Count%20Pairs%20in%20Two%20Arrays/README.md
tags:
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [1885. 统计数对 🔒](https://leetcode.cn/problems/count-pairs-in-two-arrays)

[English Version](/solution/1800-1899/1885.Count%20Pairs%20in%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度为 <code>n</code> 的整数数组 <code>nums1</code>&nbsp;和&nbsp;<code>nums2</code> ，找出所有满足 <code>i &lt; j</code> 且 <code>nums1[i] + nums1[j] &gt; nums2[i] + nums2[j]</code>&nbsp;的数对 <code>(i, j)</code> 。</p>

<p>返回满足条件数对的<strong> 个数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,1,2,1], nums2 = [1,2,1,2]
<strong>输出：</strong>1
<strong>解释：</strong>满足条件的数对有 1 个：(0, 2) ，因为 nums1[0] + nums1[2] = 2 + 2 &gt; nums2[0] + nums2[2] = 1 + 1</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,10,6,2], nums2 = [1,4,1,5]
<strong>输出：</strong>5
<strong>解释：</strong>以下数对满足条件：
- (0, 1) 因为 nums1[0] + nums1[1] = 1 + 10 &gt; nums2[0] + nums2[1] = 1 + 4
- (0, 2) 因为 nums1[0] + nums1[2] = 1 + 6 &gt; nums2[0] + nums2[2] = 1 + 1
- (1, 2) 因为 nums1[1] + nums1[2] = 10 + 6 &gt; nums2[1] + nums2[2] = 4 + 1
- (1, 3) 因为 nums1[1] + nums1[3] = 10 + 2 &gt; nums2[1] + nums2[3] = 4 + 5
- (2, 3) 因为 nums1[2] + nums1[3] = 6 + 2 &gt; nums2[2] + nums2[3] = 1 + 5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 双指针

我们可以将题目的不等式转化为 $\textit{nums1}[i] - \textit{nums2}[i] + \textit{nums1}[j] - \textit{nums2}[j] > 0$，即 $\textit{nums}[i] + \textit{nums}[j] > 0$，其中 $\textit{nums}[i] = \textit{nums1}[i] - \textit{nums2}[i]$。

即对于数组 $\textit{nums}$，我们要找到所有满足 $\textit{nums}[i] + \textit{nums}[j] > 0$ 的数对 $(i, j)$。

我们不妨对数组 $\textit{nums}$ 进行排序，然后使用双指针的方法，初始化左指针 $l = 0$，右指针 $r = n - 1$。每一次，我们判断 $\textit{nums}[l] + \textit{nums}[r]$ 是否小于等于 $0$，如果是，我们循环将左指针右移，直到 $\textit{nums}[l] + \textit{nums}[r] > 0$，此时，以 $l$, $l + 1$, $l + 2$, $\cdots$, $r - 1$ 为左指针，且 $r$ 为右指针的所有数对都满足条件，共有 $r - l$ 个数对，我们将其加入答案中。然后将右指针左移，继续进行上述操作，直到 $l \ge r$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairs(self, nums1: List[int], nums2: List[int]) -> int:
        nums = [a - b for a, b in zip(nums1, nums2)]
        nums.sort()
        l, r = 0, len(nums) - 1
        ans = 0
        while l < r:
            while l < r and nums[l] + nums[r] <= 0:
                l += 1
            ans += r - l
            r -= 1
        return ans
```

#### Java

```java
class Solution {
    public long countPairs(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(nums);
        int l = 0, r = n - 1;
        long ans = 0;
        while (l < r) {
            while (l < r && nums[l] + nums[r] <= 0) {
                ++l;
            }
            ans += r - l;
            --r;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countPairs(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> nums(n);
        for (int i = 0; i < n; ++i) {
            nums[i] = nums1[i] - nums2[i];
        }
        ranges::sort(nums);
        int l = 0, r = n - 1;
        long long ans = 0;
        while (l < r) {
            while (l < r && nums[l] + nums[r] <= 0) {
                ++l;
            }
            ans += r - l;
            --r;
        }
        return ans;
    }
};
```

#### Go

```go
func countPairs(nums1 []int, nums2 []int) (ans int64) {
	n := len(nums1)
	nums := make([]int, n)
	for i, x := range nums1 {
		nums[i] = x - nums2[i]
	}
	sort.Ints(nums)
	l, r := 0, n-1
	for l < r {
		for l < r && nums[l]+nums[r] <= 0 {
			l++
		}
		ans += int64(r - l)
		r--
	}
	return
}
```

#### TypeScript

```ts
function countPairs(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const nums: number[] = [];
    for (let i = 0; i < n; ++i) {
        nums.push(nums1[i] - nums2[i]);
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    let [l, r] = [0, n - 1];
    while (l < r) {
        while (l < r && nums[l] + nums[r] <= 0) {
            ++l;
        }
        ans += r - l;
        --r;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_pairs(nums1: Vec<i32>, nums2: Vec<i32>) -> i64 {
        let mut nums: Vec<i32> = nums1.iter().zip(nums2.iter()).map(|(a, b)| a - b).collect();
        nums.sort();
        let mut l = 0;
        let mut r = nums.len() - 1;
        let mut ans = 0;
        while l < r {
            while l < r && nums[l] + nums[r] <= 0 {
                l += 1;
            }
            ans += (r - l) as i64;
            r -= 1;
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var countPairs = function (nums1, nums2) {
    const n = nums1.length;
    const nums = [];
    for (let i = 0; i < n; ++i) {
        nums.push(nums1[i] - nums2[i]);
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    let [l, r] = [0, n - 1];
    while (l < r) {
        while (l < r && nums[l] + nums[r] <= 0) {
            ++l;
        }
        ans += r - l;
        --r;
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
