# [3107. 使数组中位数等于 K 的最少操作数](https://leetcode.cn/problems/minimum-operations-to-make-median-of-array-equal-to-k)

[English Version](/solution/3100-3199/3107.Minimum%20Operations%20to%20Make%20Median%20of%20Array%20Equal%20to%20K/README_EN.md)

<!-- tags:贪心,数组,排序 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>非负</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。一次操作中，你可以选择任一元素&nbsp;加&nbsp;<code>1</code>&nbsp;或者减&nbsp;<code>1</code>&nbsp;。</p>

<p>请你返回将 <code>nums</code>&nbsp;<strong>中位数</strong>&nbsp;变为 <code>k</code>&nbsp;所需要的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>一个数组的中位数指的是数组按非递减顺序排序后最中间的元素。如果数组长度为偶数，我们选择中间两个数的较大值为中位数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,5,6,8,5], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b>我们将&nbsp;<code>nums[1]</code> 和&nbsp;<code>nums[4]</code>&nbsp;减 <code>1</code>&nbsp;得到&nbsp;<code>[2, 4, 6, 8, 4]</code>&nbsp;。现在数组的中位数等于&nbsp;<code>k</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,5,6,8,5], k = 7</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><b>解释：</b>我们将&nbsp;<code>nums[1]</code>&nbsp;增加 1 两次，并且将&nbsp;<code>nums[2]</code>&nbsp;增加 1 一次，得到&nbsp;<code>[2, 7, 7, 8, 5]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4,5,6], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b>数组中位数已经等于 <code>k</code>&nbsp;了。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：贪心 + 排序

我们首先对数组 $nums$ 进行排序，然后找到中位数的位置 $m$，那么初始时我们需要的操作次数就是 $|nums[m] - k|$。

接下来，我们分情况讨论：

-   如果 $nums[m] \gt k$，那么 $m$ 右侧的元素都大于等于 $k$，我们只需要将 $m$ 左侧的元素中，大于 $k$ 的元素减小到 $k$ 即可。
-   如果 $nums[m] \le k$，那么 $m$ 左侧的元素都小于等于 $k$，我们只需要将 $m$ 右侧的元素中，小于 $k$ 的元素增大到 $k$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minOperationsToMakeMedianK(self, nums: List[int], k: int) -> int:
        nums.sort()
        n = len(nums)
        m = n >> 1
        ans = abs(nums[m] - k)
        if nums[m] > k:
            for i in range(m - 1, -1, -1):
                if nums[i] <= k:
                    break
                ans += nums[i] - k
        else:
            for i in range(m + 1, n):
                if nums[i] >= k:
                    break
                ans += k - nums[i]
        return ans
```

```java
class Solution {
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = n >> 1;
        long ans = Math.abs(nums[m] - k);
        if (nums[m] > k) {
            for (int i = m - 1; i >= 0 && nums[i] > k; --i) {
                ans += nums[i] - k;
            }
        } else {
            for (int i = m + 1; i < n && nums[i] < k; ++i) {
                ans += k - nums[i];
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minOperationsToMakeMedianK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int m = n >> 1;
        long long ans = abs(nums[m] - k);
        if (nums[m] > k) {
            for (int i = m - 1; i >= 0 && nums[i] > k; --i) {
                ans += nums[i] - k;
            }
        } else {
            for (int i = m + 1; i < n && nums[i] < k; ++i) {
                ans += k - nums[i];
            }
        }
        return ans;
    }
};
```

```go
func minOperationsToMakeMedianK(nums []int, k int) (ans int64) {
	sort.Ints(nums)
	n := len(nums)
	m := n >> 1
	ans = int64(abs(nums[m] - k))
	if nums[m] > k {
		for i := m - 1; i >= 0 && nums[i] > k; i-- {
			ans += int64(nums[i] - k)
		}
	} else {
		for i := m + 1; i < n && nums[i] < k; i++ {
			ans += int64(k - nums[i])
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function minOperationsToMakeMedianK(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const m = n >> 1;
    let ans = Math.abs(nums[m] - k);
    if (nums[m] > k) {
        for (let i = m - 1; i >= 0 && nums[i] > k; --i) {
            ans += nums[i] - k;
        }
    } else {
        for (let i = m + 1; i < n && nums[i] < k; ++i) {
            ans += k - nums[i];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
