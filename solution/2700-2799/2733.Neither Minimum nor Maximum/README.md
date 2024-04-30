# [2733. 既不是最小值也不是最大值](https://leetcode.cn/problems/neither-minimum-nor-maximum)

[English Version](/solution/2700-2799/2733.Neither%20Minimum%20nor%20Maximum/README_EN.md)

<!-- tags:数组,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，数组由 <strong>不同正整数</strong> 组成，请你找出并返回数组中 <strong>任一</strong> 既不是 <strong>最小值</strong> 也不是 <strong>最大值</strong> 的数字，如果不存在这样的数字，返回 <strong><code>-1</code></strong> 。</p>

<p>返回所选整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,2,1,4]
<strong>输出：</strong>2
<strong>解释：</strong>在这个示例中，最小值是 1 ，最大值是 4 。因此，2 或 3 都是有效答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2]
<strong>输出：</strong>-1
<strong>解释：</strong>由于不存在既不是最大值也不是最小值的数字，我们无法选出满足题目给定条件的数字。因此，不存在答案，返回 -1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [2,1,3]
<strong>输出：</strong>2
<strong>解释：</strong>2 既不是最小值，也不是最大值，这个示例只有这一个有效答案。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> 中的所有数字互不相同</li>
</ul>

## 解法

### 方法一：模拟

我们可以先找到数组中的最小值和最大值，分别记为 $mi$ 和 $mx$。然后遍历数组，找到第一个不等于 $mi$ 且不等于 $mx$ 的数字，返回即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findNonMinOrMax(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        return next((x for x in nums if x != mi and x != mx), -1)
```

```java
class Solution {
    public int findNonMinOrMax(int[] nums) {
        int mi = 100, mx = 0;
        for (int x : nums) {
            mi = Math.min(mi, x);
            mx = Math.max(mx, x);
        }
        for (int x : nums) {
            if (x != mi && x != mx) {
                return x;
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int findNonMinOrMax(vector<int>& nums) {
        auto [mi, mx] = minmax_element(nums.begin(), nums.end());
        for (int x : nums) {
            if (x != *mi && x != *mx) {
                return x;
            }
        }
        return -1;
    }
};
```

```go
func findNonMinOrMax(nums []int) int {
	mi, mx := slices.Min(nums), slices.Max(nums)
	for _, x := range nums {
		if x != mi && x != mx {
			return x
		}
	}
	return -1
}
```

```rust
impl Solution {
    pub fn find_non_min_or_max(nums: Vec<i32>) -> i32 {
        let mut mi = 100;
        let mut mx = 0;

        for &ele in nums.iter() {
            if ele < mi {
                mi = ele;
            }
            if ele > mx {
                mx = ele;
            }
        }

        for &ele in nums.iter() {
            if ele != mi && ele != mx {
                return ele;
            }
        }

        -1
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def findNonMinOrMax(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        for x in nums:
            if x != mi and x != mx:
                return x
        return -1
```

<!-- tabs:end -->

<!-- end -->
