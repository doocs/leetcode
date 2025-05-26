---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2009.Minimum%20Number%20of%20Operations%20to%20Make%20Array%20Continuous/README.md
rating: 2084
source: 第 61 场双周赛 Q4
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 滑动窗口
---

<!-- problem:start -->

# [2009. 使数组连续的最少操作数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous)

[English Version](/solution/2000-2099/2009.Minimum%20Number%20of%20Operations%20to%20Make%20Array%20Continuous/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。每一次操作中，你可以将&nbsp;<code>nums</code>&nbsp;中&nbsp;<strong>任意</strong>&nbsp;一个元素替换成 <strong>任意&nbsp;</strong>整数。</p>

<p>如果&nbsp;<code>nums</code>&nbsp;满足以下条件，那么它是 <strong>连续的</strong>&nbsp;：</p>

<ul>
	<li><code>nums</code>&nbsp;中所有元素都是 <b>互不相同</b>&nbsp;的。</li>
	<li><code>nums</code>&nbsp;中 <strong>最大</strong>&nbsp;元素与&nbsp;<strong>最小</strong>&nbsp;元素的差等于&nbsp;<code>nums.length - 1</code>&nbsp;。</li>
</ul>

<p>比方说，<code>nums = [4, 2, 5, 3]</code>&nbsp;是 <strong>连续的</strong>&nbsp;，但是&nbsp;<code>nums = [1, 2, 3, 5, 6]</code> <strong>不是连续的</strong>&nbsp;。</p>

<p>请你返回使 <code>nums</code>&nbsp;<strong>连续</strong>&nbsp;的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [4,2,5,3]
<b>输出：</b>0
<b>解释：</b>nums 已经是连续的了。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,5,6]
<b>输出：</b>1
<b>解释：</b>一个可能的解是将最后一个元素变为 4 。
结果数组为 [1,2,3,5,4] ，是连续数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1,10,100,1000]
<b>输出：</b>3
<b>解释：</b>一个可能的解是：
- 将第二个元素变为 2 。
- 将第三个元素变为 3 。
- 将第四个元素变为 4 。
结果数组为 [1,2,3,4] ，是连续数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 去重 + 二分查找

我们先将数组排序，去重。

然后遍历数组，枚举以当前元素 $nums[i]$ 作为连续数组的最小值，通过二分查找找到第一个大于 $nums[i] + n - 1$ 的位置 $j$，那么 $j-i$ 就是当前元素作为最小值时，连续数组的长度，更新答案，即 $ans = \min(ans, n - (j - i))$。

最后返回 $ans$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        ans = n = len(nums)
        nums = sorted(set(nums))
        for i, v in enumerate(nums):
            j = bisect_right(nums, v + n - 1)
            ans = min(ans, n - (j - i))
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int m = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int j = search(nums, nums[i] + n - 1, i, m);
            ans = Math.min(ans, n - (j - i));
        }
        return ans;
    }

    private int search(int[] nums, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int m = unique(nums.begin(), nums.end()) - nums.begin();
        int n = nums.size();
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int j = upper_bound(nums.begin() + i, nums.begin() + m, nums[i] + n - 1) - nums.begin();
            ans = min(ans, n - (j - i));
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	m := 1
	for i := 1; i < n; i++ {
		if nums[i] != nums[i-1] {
			nums[m] = nums[i]
			m++
		}
	}
	ans := n
	for i := 0; i < m; i++ {
		j := sort.Search(m, func(k int) bool { return nums[k] > nums[i]+n-1 })
		ans = min(ans, n-(j-i))
	}
	return ans
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    const n = nums.length;
    nums.sort((a, b) => a - b);
    let m = 1;
    for (let i = 1; i < n; ++i) {
        if (nums[i] !== nums[i - 1]) {
            nums[m++] = nums[i];
        }
    }
    let ans = n;
    for (let i = 0; i < m; ++i) {
        const j = search(nums, nums[i] + n - 1, i, m);
        ans = Math.min(ans, n - (j - i));
    }
    return ans;
}

function search(nums: number[], x: number, left: number, right: number): number {
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] > x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

#### Rust

```rust
use std::collections::BTreeSet;

impl Solution {
    #[allow(dead_code)]
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let nums = nums.into_iter().collect::<BTreeSet<i32>>();

        let m = nums.len();
        let nums = nums.into_iter().collect::<Vec<i32>>();

        let mut ans = n;

        for i in 0..m {
            let j = match nums.binary_search(&(nums[i] + (n as i32))) {
                Ok(idx) => idx,
                Err(idx) => idx,
            };
            ans = std::cmp::min(ans, n - (j - i));
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：排序 + 去重 + 双指针

与方法一类似，我们先将数组排序，去重。

然后遍历数组，枚举以当前元素 $nums[i]$ 作为连续数组的最小值，通过双指针找到第一个大于 $nums[i] + n - 1$ 的位置 $j$，那么 $j-i$ 就是当前元素作为最小值时，连续数组的长度，更新答案，即 $ans = \min(ans, n - (j - i))$。

最后返回 $ans$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        nums = sorted(set(nums))
        ans, j = n, 0
        for i, v in enumerate(nums):
            while j < len(nums) and nums[j] - v <= n - 1:
                j += 1
            ans = min(ans, n - (j - i))
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int m = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }
        int ans = n;
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < m && nums[j] - nums[i] <= n - 1) {
                ++j;
            }
            ans = Math.min(ans, n - (j - i));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int m = unique(nums.begin(), nums.end()) - nums.begin();
        int n = nums.size();
        int ans = n;
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < m && nums[j] - nums[i] <= n - 1) {
                ++j;
            }
            ans = min(ans, n - (j - i));
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	m := 1
	for i := 1; i < n; i++ {
		if nums[i] != nums[i-1] {
			nums[m] = nums[i]
			m++
		}
	}
	ans := n
	for i, j := 0, 0; i < m; i++ {
		for j < m && nums[j]-nums[i] <= n-1 {
			j++
		}
		ans = min(ans, n-(j-i))
	}
	return ans
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let m = 1;
    for (let i = 1; i < n; i++) {
        if (nums[i] !== nums[i - 1]) {
            nums[m] = nums[i];
            m++;
        }
    }
    let ans = n;
    for (let i = 0, j = 0; i < m; i++) {
        while (j < m && nums[j] - nums[i] <= n - 1) {
            j++;
        }
        ans = Math.min(ans, n - (j - i));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_operations(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        if n == 0 {
            return 0;
        }
        let mut m = 1usize;
        for i in 1..n {
            if nums[i] != nums[i - 1] {
                nums[m] = nums[i];
                m += 1;
            }
        }
        let mut ans = n as i32;
        let mut j = 0usize;
        for i in 0..m {
            while j < m && nums[j] - nums[i] <= n as i32 - 1 {
                j += 1;
            }
            ans = ans.min(n as i32 - (j as i32 - i as i32));
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
