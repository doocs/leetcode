# [164. 最大间距](https://leetcode.cn/problems/maximum-gap)

[English Version](/solution/0100-0199/0164.Maximum%20Gap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个无序的数组&nbsp;<code>nums</code>，返回 <em>数组在排序之后，相邻元素之间最大的差值</em> 。如果数组元素个数小于 2，则返回 <code>0</code> 。</p>

<p>您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> nums = [3,6,9,1]
<strong>输出:</strong> 3
<strong>解释:</strong> 排序后的数组是 [1,3,6,9]<strong><em>, </em></strong>其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [10]
<strong>输出:</strong> 0
<strong>解释:</strong> 数组元素个数小于 2，因此返回 0。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**前言**

一种容易想到的解法是将数组排序后得到相邻元素之间最大的差值，时间复杂度 $O(n \log n)$，不符合题目要求。

只有使用不基于比较的的排序算法才能在线性时间复杂度解决。

**方法一：桶排序**

假设数组 $nums$ 有 $n$ 个元素，所有元素从小到大依次是 $nums_0$ 到 $nums_{n - 1}$，最大间距是 $maxGap$。考虑数组中的最大元素和最小元素之差。

$$
nums_{n - 1} - nums_0 = \sum_{i = 1}^{n - 1} (nums_i - nums_{i - 1}) \le{maxGap} \times (n - 1)
$$

因此 $maxGap \ge \dfrac{nums_{n - 1} - nums_0}{n - 1}$，即最大间距至少为 $\dfrac{nums_{n - 1} - nums_0}{n - 1}$。

可以利用桶排序的思想，设定桶的大小（即每个桶最多包含的不同元素个数）为 $\dfrac{nums_{n - 1} - nums_0}{n - 1}$，将元素按照元素值均匀分布到各个桶内，则同一个桶内的任意两个元素之差小于 ${maxGap}$，差为 ${maxGap}$ 的两个元素一定在两个不同的桶内。对于每个桶，维护桶内的最小值和最大值，初始时每个桶内的最小值和最大值分别是正无穷和负无穷，表示桶内没有元素。

遍历数组 ${nums}$ 中的所有元素。对于每个元素，根据该元素与最小元素之差以及桶的大小计算该元素应该分到的桶的编号，可以确保编号小的桶内的元素都小于编号大的桶内的元素，使用元素值更新元素所在的桶内的最小值和最大值。

遍历数组结束之后，每个非空的桶内的最小值和最大值都可以确定。按照桶的编号从小到大的顺序依次遍历每个桶，当前的桶的最小值和上一个非空的桶的最大值是排序后的相邻元素，计算两个相邻元素之差，并更新最大间距。遍历桶结束之后即可得到最大间距。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumGap(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return 0
        mi, mx = min(nums), max(nums)
        bucket_size = max(1, (mx - mi) // (n - 1))
        bucket_count = (mx - mi) // bucket_size + 1
        buckets = [[inf, -inf] for _ in range(bucket_count)]
        for v in nums:
            i = (v - mi) // bucket_size
            buckets[i][0] = min(buckets[i][0], v)
            buckets[i][1] = max(buckets[i][1], v)
        ans = 0
        prev = inf
        for curmin, curmax in buckets:
            if curmin > curmax:
                continue
            ans = max(ans, curmin - prev)
            prev = curmax
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int inf = 0x3f3f3f3f;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
        }
        int bucketSize = Math.max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][2];
        for (var bucket : buckets) {
            bucket[0] = inf;
            bucket[1] = -inf;
        }
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i][0] = Math.min(buckets[i][0], v);
            buckets[i][1] = Math.max(buckets[i][1], v);
        }
        int prev = inf;
        int ans = 0;
        for (var bucket : buckets) {
            if (bucket[0] > bucket[1]) {
                continue;
            }
            ans = Math.max(ans, bucket[0] - prev);
            prev = bucket[1];
        }
        return ans;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    const int inf = 0x3f3f3f3f;
    int maximumGap(vector<int>& nums) {
        int n = nums.size();
        if (n < 2) return 0;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = min(mi, v);
            mx = max(mx, v);
        }
        int bucketSize = max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        vector<pii> buckets(bucketCount, {inf, -inf});
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i].first = min(buckets[i].first, v);
            buckets[i].second = max(buckets[i].second, v);
        }
        int ans = 0;
        int prev = inf;
        for (auto [curmin, curmax] : buckets) {
            if (curmin > curmax) continue;
            ans = max(ans, curmin - prev);
            prev = curmax;
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumGap(nums []int) int {
	n := len(nums)
	if n < 2 {
		return 0
	}
	inf := 0x3f3f3f3f
	mi, mx := inf, -inf
	for _, v := range nums {
		mi = min(mi, v)
		mx = max(mx, v)
	}
	bucketSize := max(1, (mx-mi)/(n-1))
	bucketCount := (mx-mi)/bucketSize + 1
	buckets := make([][]int, bucketCount)
	for i := range buckets {
		buckets[i] = []int{inf, -inf}
	}
	for _, v := range nums {
		i := (v - mi) / bucketSize
		buckets[i][0] = min(buckets[i][0], v)
		buckets[i][1] = max(buckets[i][1], v)
	}
	ans := 0
	prev := inf
	for _, bucket := range buckets {
		if bucket[0] > bucket[1] {
			continue
		}
		ans = max(ans, bucket[0]-prev)
		prev = bucket[1]
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C#**

```cs
using System;
using System.Linq;

public class Solution {
    public int MaximumGap(int[] nums) {
        if (nums.Length < 2) return 0;
        var max = nums.Max();
        var min = nums.Min();
        var bucketSize = Math.Max(1, (max - min) / (nums.Length - 1));
        var buckets = new Tuple<int, int>[(max - min) / bucketSize + 1];
        foreach (var num in nums)
        {
            var index = (num - min) / bucketSize;
            if (buckets[index] == null)
            {
                buckets[index] = Tuple.Create(num, num);
            }
            else
            {
                buckets[index] = Tuple.Create(Math.Min(buckets[index].Item1, num), Math.Max(buckets[index].Item2, num));
            }
        }

        var result = 0;
        Tuple<int, int> lastBucket = null;
        for (var i = 0; i < buckets.Length; ++i)
        {
            if (buckets[i] != null)
            {
                if (lastBucket != null)
                {
                    result = Math.Max(result, buckets[i].Item1 - lastBucket.Item2);
                }
                lastBucket = buckets[i];
            }
        }
        return result;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
