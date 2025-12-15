---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2348.Number%20of%20Zero-Filled%20Subarrays/README.md
rating: 1315
source: 第 83 场双周赛 Q2
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [2348. 全 0 子数组的数目](https://leetcode.cn/problems/number-of-zero-filled-subarrays)

[English Version](/solution/2300-2399/2348.Number%20of%20Zero-Filled%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，返回全部为&nbsp;<code>0</code>&nbsp;的&nbsp;<strong>子数组</strong>&nbsp;数目。</p>

<p><strong>子数组</strong>&nbsp;是一个数组中一段连续非空元素组成的序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,3,0,0,2,0,0,4]
<b>输出：</b>6
<b>解释：</b>
子数组 [0] 出现了 4 次。
子数组 [0,0] 出现了 2 次。
不存在长度大于 2 的全 0 子数组，所以我们返回 6 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [0,0,0,2,0,0]
<b>输出：</b>9
<strong>解释：
</strong>子数组 [0] 出现了 5 次。
子数组 [0,0] 出现了 3 次。
子数组 [0,0,0] 出现了 1 次。
不存在长度大于 3 的全 0 子数组，所以我们返回 9 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [2,10,2019]
<b>输出：</b>0
<b>解释：</b>没有全 0 子数组，所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历计数

我们遍历数组 $\textit{nums}$，用变量 $\textit{cnt}$ 记录当前连续的 $0$ 的个数。那么对于当前遍历到的元素 $x$，如果 $x$ 为 $0$，则 $\textit{cnt}$ 自增 $1$，以当前 $x$ 为结尾的全 $0$ 子数组的个数为 $\textit{cnt}$，将其累加到答案中。否则，我们将 $\textit{cnt}$ 置为 $0$。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

相似题目：

- [413. 等差数列划分](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0413.Arithmetic%20Slices/README.md)
- [1513. 仅含 1 的子串数](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1513.Number%20of%20Substrings%20With%20Only%201s/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def zeroFilledSubarray(self, nums: List[int]) -> int:
        ans = cnt = 0
        for x in nums:
            if x == 0:
                cnt += 1
                ans += cnt
            else:
                cnt = 0
        return ans
```

#### Java

```java
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        int cnt = 0;
        for (int x : nums) {
            if (x == 0) {
                ans += ++cnt;
            } else {
                cnt = 0;
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
    long long zeroFilledSubarray(vector<int>& nums) {
        long long ans = 0;
        int cnt = 0;
        for (int x : nums) {
            if (x == 0) {
                ans += ++cnt;
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func zeroFilledSubarray(nums []int) (ans int64) {
	cnt := 0
	for _, x := range nums {
		if x == 0 {
			cnt++
			ans += int64(cnt)
		} else {
			cnt = 0
		}
	}
	return
}
```

#### TypeScript

```ts
function zeroFilledSubarray(nums: number[]): number {
    let [ans, cnt] = [0, 0];
    for (const x of nums) {
        if (!x) {
            ans += ++cnt;
        } else {
            cnt = 0;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn zero_filled_subarray(nums: Vec<i32>) -> i64 {
        let mut ans: i64 = 0;
        let mut cnt: i64 = 0;
        for x in nums {
            if x == 0 {
                cnt += 1;
                ans += cnt;
            } else {
                cnt = 0;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
