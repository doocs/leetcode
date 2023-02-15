# [2563. 统计公平数对的数目](https://leetcode.cn/problems/count-the-number-of-fair-pairs)

[English Version](/solution/2500-2599/2563.Count%20the%20Number%20of%20Fair%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数数组&nbsp;<code>nums</code>&nbsp;，和两个整数&nbsp;<code>lower</code> 和&nbsp;<code>upper</code> ，返回 <strong>公平数对的数目</strong> 。</p>

<p>如果&nbsp;<code>(i, j)</code>&nbsp;数对满足以下情况，则认为它是一个 <strong>公平数对</strong>&nbsp;：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; n</code>，且</li>
	<li><code>lower &lt;= nums[i] + nums[j] &lt;= upper</code></li>
</ul>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>nums = [0,1,7,4,4,5], lower = 3, upper = 6
<b>输出：</b>6
<b>解释：</b>共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
</pre>

<p><b>示例 2：</b></p>

<pre>
<b>输入：</b>nums = [1,7,9,2,5], lower = 11, upper = 11
<b>输出：</b>1
<b>解释：</b>只有单个公平数对：(2,3) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums.length == n</code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= lower &lt;= upper &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

我们先对数组 `nums` 按照升序排序，然后枚举 `nums[i]`，对于每个 `nums[i]`，我们通过二分查找找到 `nums[j]` 的下界 `j`，即第一个满足 `nums[j] >= lower - nums[i]` 的下标，然后再通过二分查找找到 `nums[k]` 的下界 `k`，即第一个满足 `nums[k] >= upper - nums[i] + 1` 的下标，那么 `[j, k)` 即为 `nums[j]` 满足 `lower <= nums[i] + nums[j] <= upper` 的下标范围，这些下标对应的 `nums[j]` 的个数即为 `k - j`，将其累加到答案中即可。注意 $j \gt i$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countFairPairs(self, nums: List[int], lower: int, upper: int) -> int:
        nums.sort()
        ans = 0
        for i, x in enumerate(nums):
            j = bisect_left(nums, lower-x, lo=i + 1)
            k = bisect_left(nums, upper-x+1, lo=i + 1)
            ans += k - j
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = search(nums, lower - nums[i], i + 1);
            int k = search(nums, upper - nums[i] + 1, i + 1);
            ans += k - j;
        }
        return ans;
    }

    private int search(int[] nums, int x, int left) {
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countFairPairs(vector<int>& nums, int lower, int upper) {
        long long ans = 0;
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size(); ++i) {
            auto j = lower_bound(nums.begin() + i + 1, nums.end(), lower - nums[i]);
            auto k = lower_bound(nums.begin() + i + 1, nums.end(), upper - nums[i] + 1);
            ans += k - j;
        }
        return ans;
    }
};
```

### **Go**

```go
func countFairPairs(nums []int, lower int, upper int) (ans int64) {
	sort.Ints(nums)
	for i, x := range nums {
		j := sort.Search(len(nums), func(h int) bool { return h > i && nums[h] >= lower-x })
		k := sort.Search(len(nums), func(h int) bool { return h > i && nums[h] >= upper-x+1 })
		ans += int64(k - j)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
