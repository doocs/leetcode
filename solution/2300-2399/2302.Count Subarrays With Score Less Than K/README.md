---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2302.Count%20Subarrays%20With%20Score%20Less%20Than%20K/README.md
rating: 1808
source: 第 80 场双周赛 Q4
tags:
    - 数组
    - 二分查找
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [2302. 统计得分小于 K 的子数组数目](https://leetcode.cn/problems/count-subarrays-with-score-less-than-k)

[English Version](/solution/2300-2399/2302.Count%20Subarrays%20With%20Score%20Less%20Than%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个数组的 <strong>分数</strong>&nbsp;定义为数组之和 <strong>乘以</strong>&nbsp;数组的长度。</p>

<ul>
	<li>比方说，<code>[1, 2, 3, 4, 5]</code>&nbsp;的分数为&nbsp;<code>(1 + 2 + 3 + 4 + 5) * 5 = 75</code>&nbsp;。</li>
</ul>

<p>给你一个正整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回&nbsp;<code>nums</code>&nbsp;中分数&nbsp;<strong>严格小于&nbsp;</strong><code>k</code>&nbsp;的&nbsp;<strong>非空整数子数组数目</strong>。</p>

<p><strong>子数组</strong> 是数组中的一个连续元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,4,3,5], k = 10
<b>输出：</b>6
<strong>解释：</strong>
有 6 个子数组的分数小于 10 ：
- [2] 分数为 2 * 1 = 2 。
- [1] 分数为 1 * 1 = 1 。
- [4] 分数为 4 * 1 = 4 。
- [3] 分数为 3 * 1 = 3 。 
- [5] 分数为 5 * 1 = 5 。
- [2,1] 分数为 (2 + 1) * 2 = 6 。
注意，子数组 [1,4] 和 [4,3,5] 不符合要求，因为它们的分数分别为 10 和 36，但我们要求子数组的分数严格小于 10 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1], k = 5
<b>输出：</b>5
<strong>解释：</strong>
除了 [1,1,1] 以外每个子数组分数都小于 5 。
[1,1,1] 分数为 (1 + 1 + 1) * 3 = 9 ，大于 5 。
所以总共有 5 个子数组得分小于 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 二分查找

我们先计算出数组 $\textit{nums}$ 的前缀和数组 $s$，其中 $s[i]$ 表示数组 $\textit{nums}$ 前 $i$ 个元素的和。

接下来，我们枚举数组 $\textit{nums}$ 每个元素作为子数组的最后一个元素，对于每个元素，我们可以通过二分查找的方式找到最大的长度 $l$，使得 $s[i] - s[i - l] \times l < k$。那么以该元素为最后一个元素的子数组个数即为 $l$，我们将所有的 $l$ 相加即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        s = list(accumulate(nums, initial=0))
        ans = 0
        for i in range(1, len(s)):
            l, r = 0, i
            while l < r:
                mid = (l + r + 1) >> 1
                if (s[i] - s[i - mid]) * mid < k:
                    l = mid
                else:
                    r = mid - 1
            ans += l
        return ans
```

#### Java

```java
class Solution {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            int l = 0, r = i;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if ((s[i] - s[i - mid]) * mid < k) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            ans += l;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, long long k) {
        int n = nums.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long long ans = 0;
        for (int i = 1; i <= n; ++i) {
            int l = 0, r = i;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if ((s[i] - s[i - mid]) * mid < k) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            ans += l;
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int, k int64) (ans int64) {
	n := len(nums)
	s := make([]int64, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + int64(x)
	}
	for i := 1; i <= n; i++ {
		l, r := 0, i
		for l < r {
			mid := (l + r + 1) >> 1
			if (s[i]-s[i-mid])*int64(mid) < k {
				l = mid
			} else {
				r = mid - 1
			}
		}
		ans += int64(l)
	}
	return
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        let [l, r] = [0, i];
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            if ((s[i] - s[i - mid]) * mid < k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        ans += l;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, k: i64) -> i64 {
        let n = nums.len();
        let mut s = vec![0i64; n + 1];
        for i in 0..n {
            s[i + 1] = s[i] + nums[i] as i64;
        }
        let mut ans = 0i64;
        for i in 1..=n {
            let mut l = 0;
            let mut r = i;
            while l < r {
                let mid = (l + r + 1) / 2;
                let sum = s[i] - s[i - mid];
                if sum * (mid as i64) < k {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            ans += l as i64;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：双指针

我们可以使用双指针的方式，维护一个滑动窗口，使得窗口内的元素和小于 $k$。那么以当前元素为最后一个元素的子数组个数即为窗口的长度，我们将所有的窗口长度相加即为答案。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        ans = s = j = 0
        for i, x in enumerate(nums):
            s += x
            while s * (i - j + 1) >= k:
                s -= nums[j]
                j += 1
            ans += i - j + 1
        return ans
```

#### Java

```java
class Solution {
    public long countSubarrays(int[] nums, long k) {
        long ans = 0, s = 0;
        for (int i = 0, j = 0; i < nums.length; ++i) {
            s += nums[i];
            while (s * (i - j + 1) >= k) {
                s -= nums[j++];
            }
            ans += i - j + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, long long k) {
        long long ans = 0, s = 0;
        for (int i = 0, j = 0; i < nums.size(); ++i) {
            s += nums[i];
            while (s * (i - j + 1) >= k) {
                s -= nums[j++];
            }
            ans += i - j + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int, k int64) (ans int64) {
	s, j := 0, 0
	for i, x := range nums {
		s += x
		for int64(s*(i-j+1)) >= k {
			s -= nums[j]
			j++
		}
		ans += int64(i - j + 1)
	}
	return
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[], k: number): number {
    let [ans, s, j] = [0, 0, 0];
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i];
        while (s * (i - j + 1) >= k) {
            s -= nums[j++];
        }
        ans += i - j + 1;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, k: i64) -> i64 {
        let mut ans = 0i64;
        let mut s = 0i64;
        let mut j = 0;

        for i in 0..nums.len() {
            s += nums[i] as i64;
            while s * (i as i64 - j as i64 + 1) >= k {
                s -= nums[j] as i64;
                j += 1;
            }
            ans += i as i64 - j as i64 + 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
