---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1283.Find%20the%20Smallest%20Divisor%20Given%20a%20Threshold/README.md
rating: 1541
source: 第 166 场周赛 Q3
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [1283. 使结果不超过阈值的最小除数](https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold)

[English Version](/solution/1200-1299/1283.Find%20the%20Smallest%20Divisor%20Given%20a%20Threshold/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code> 和一个正整数&nbsp;<code>threshold</code> &nbsp;，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。</p>

<p>请你找出能够使上述结果小于等于阈值&nbsp;<code>threshold</code>&nbsp;的除数中 <strong>最小</strong> 的那个。</p>

<p>每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。</p>

<p>题目保证一定有解。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,5,9], threshold = 6
<strong>输出：</strong>5
<strong>解释：</strong>如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,5,7,11], threshold = 11
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [19], threshold = 5
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10^4</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^6</code></li>
	<li><code>nums.length &lt;=&nbsp;threshold &lt;= 10^6</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，对于一个数字 $v$，如果将 $nums$ 中的每个数字都除以 $v$ 的结果之和小于等于 $threshold$，那么所有大于 $v$ 的值都满足条件。这存在着单调性，因此我们可以使用二分查找的方法找到最小的满足条件的 $v$。

我们定义二分查找的左边界 $l=1$, $r=\max(nums)$，每次取 $mid=(l+r)/2$，计算 $nums$ 中每个数字除以 $mid$ 的结果之和 $s$，如果 $s$ 小于等于 $threshold$，那么说明 $mid$ 满足条件，我们将 $r$ 更新为 $mid$，否则将 $l$ 更新为 $mid+1$。

最后返回 $l$ 即可。

时间复杂度 $O(n \times \log M)$，其中 $n$ 是数组 $nums$ 的长度，而 $M$ 是数组 $nums$ 中的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        def f(v: int) -> bool:
            v += 1
            return sum((x + v - 1) // v for x in nums) <= threshold

        return bisect_left(range(max(nums)), True, key=f) + 1
```

#### Java

```java
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1, r = 1000000;
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            for (int x : nums) {
                s += (x + mid - 1) / mid;
            }
            if (s <= threshold) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestDivisor(vector<int>& nums, int threshold) {
        int l = 1;
        int r = *max_element(nums.begin(), nums.end());
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            for (int x : nums) {
                s += (x + mid - 1) / mid;
            }
            if (s <= threshold) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func smallestDivisor(nums []int, threshold int) int {
	return sort.Search(slices.Max(nums), func(v int) bool {
		v++
		s := 0
		for _, x := range nums {
			s += (x + v - 1) / v
		}
		return s <= threshold
	}) + 1
}
```

#### TypeScript

```ts
function smallestDivisor(nums: number[], threshold: number): number {
    let l = 1;
    let r = Math.max(...nums);
    while (l < r) {
        const mid = (l + r) >> 1;
        const s = nums.reduce((acc, x) => acc + Math.ceil(x / mid), 0);
        if (s <= threshold) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_divisor(nums: Vec<i32>, threshold: i32) -> i32 {
        let mut l = 1;
        let mut r = *nums.iter().max().unwrap();
        while l < r {
            let mid = (l + r) / 2;
            let s: i32 = nums.iter().map(|&x| (x + mid - 1) / mid).sum();
            if s <= threshold {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} threshold
 * @return {number}
 */
var smallestDivisor = function (nums, threshold) {
    let l = 1;
    let r = Math.max(...nums);
    while (l < r) {
        const mid = (l + r) >> 1;
        const s = nums.reduce((acc, x) => acc + Math.ceil(x / mid), 0);
        if (s <= threshold) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
};
```

#### C#

```cs
public class Solution {
    public int SmallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = nums.Max();
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            foreach (int x in nums) {
                s += (x + mid - 1) / mid;
            }
            if (s <= threshold) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
