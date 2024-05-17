---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1984.Minimum%20Difference%20Between%20Highest%20and%20Lowest%20of%20K%20Scores/README.md
rating: 1306
source: 第 256 场周赛 Q1
tags:
    - 数组
    - 排序
    - 滑动窗口
---

<!-- problem:start -->

# [1984. 学生分数的最小差值](https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores)

[English Version](/solution/1900-1999/1984.Minimum%20Difference%20Between%20Highest%20and%20Lowest%20of%20K%20Scores/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>下标从 0 开始</strong> 的整数数组 <code>nums</code> ，其中 <code>nums[i]</code> 表示第 <code>i</code> 名学生的分数。另给你一个整数 <code>k</code> 。</p>

<p>从数组中选出任意 <code>k</code> 名学生的分数，使这 <code>k</code> 个分数间 <strong>最高分</strong> 和 <strong>最低分</strong> 的 <strong>差值</strong> 达到<strong> 最小化</strong> 。</p>

<p>返回可能的 <strong>最小差值</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [90], k = 1
<strong>输出：</strong>0
<strong>解释：</strong>选出 1 名学生的分数，仅有 1 种方法：
- [<em><strong>90</strong></em>] 最高分和最低分之间的差值是 90 - 90 = 0
可能的最小差值是 0
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [9,4,1,7], k = 2
<strong>输出：</strong>2
<strong>解释：</strong>选出 2 名学生的分数，有 6 种方法：
- [<em><strong>9</strong></em>,<em><strong>4</strong></em>,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
- [<em><strong>9</strong></em>,4,<em><strong>1</strong></em>,7] 最高分和最低分之间的差值是 9 - 1 = 8
- [<em><strong>9</strong></em>,4,1,<em><strong>7</strong></em>] 最高分和最低分之间的差值是 9 - 7 = 2
- [9,<em><strong>4</strong></em>,<em><strong>1</strong></em>,7] 最高分和最低分之间的差值是 4 - 1 = 3
- [9,<em><strong>4</strong></em>,1,<em><strong>7</strong></em>] 最高分和最低分之间的差值是 7 - 4 = 3
- [9,4,<em><strong>1</strong></em>,<em><strong>7</strong></em>] 最高分和最低分之间的差值是 7 - 1 = 6
可能的最小差值是 2</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 滑动窗口

我们可以将学生的分数按照升序排序，然后使用滑动窗口的方法，每次取大小为 $k$ 的窗口，计算窗口内的最大值和最小值的差值，最后取所有窗口的差值的最小值。

为什么是取连续的 $k$ 个学生的分数呢？因为如果不连续，那么最大值和最小值的的差值可能不变，或者变大，但一定不会变小。因此，我们只需要考虑排序后的连续的 $k$ 个学生的分数。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是学生的数量。

<!-- tabs:start -->

```python
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        nums.sort()
        return min(nums[i + k - 1] - nums[i] for i in range(len(nums) - k + 1))
```

```java
class Solution {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 100000;
        for (int i = 0; i < nums.length - k + 1; ++i) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = 1e5;
        for (int i = 0; i < nums.size() - k + 1; ++i) {
            ans = min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
};
```

```go
func minimumDifference(nums []int, k int) int {
	sort.Ints(nums)
	ans := 100000
	for i := 0; i < len(nums)-k+1; i++ {
		ans = min(ans, nums[i+k-1]-nums[i])
	}
	return ans
}
```

```ts
function minimumDifference(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = nums[n - 1] - nums[0];
    for (let i = 0; i + k - 1 < n; i++) {
        ans = Math.min(nums[i + k - 1] - nums[i], ans);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn minimum_difference(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let k = k as usize;
        let mut res = i32::MAX;
        for i in 0..=nums.len() - k {
            res = res.min(nums[i + k - 1] - nums[i]);
        }
        res
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $k
     * @return Integer
     */
    function minimumDifference($nums, $k) {
        sort($nums);
        $ans = 10 ** 5;
        for ($i = 0; $i < count($nums) - $k + 1; $i++) {
            $ans = min($ans, $nums[$i + $k - 1] - $nums[$i]);
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
