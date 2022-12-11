# [2499. 让数组不相等的最小总代价](https://leetcode.cn/problems/minimum-total-cost-to-make-arrays-unequal)

[English Version](/solution/2400-2499/2499.Minimum%20Total%20Cost%20to%20Make%20Arrays%20Unequal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，两者长度都为&nbsp;<code>n</code>&nbsp;。</p>

<p>每次操作中，你可以选择交换 <code>nums1</code>&nbsp;中任意两个下标处的值。操作的 <strong>开销</strong>&nbsp;为两个下标的 <strong>和</strong>&nbsp;。</p>

<p>你的目标是对于所有的 <code>0 &lt;= i &lt;= n - 1</code>&nbsp;，都满足&nbsp;<code>nums1[i] != nums2[i]</code>&nbsp;，你可以进行 <strong>任意次</strong>&nbsp;操作，请你返回达到这个目标的 <strong>最小</strong>&nbsp;总代价。</p>

<p>请你返回让<em>&nbsp;</em><code>nums1</code> 和&nbsp;<code>nums2</code><em>&nbsp;</em>满足上述条件的 <strong>最小总代价</strong> ，如果无法达成目标，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,3,4,5], nums2 = [1,2,3,4,5]
<b>输出：</b>10
<b>解释：</b>
实现目标的其中一种方法为：
- 交换下标为 0 和 3 的两个值，代价为 0 + 3 = 3 。现在 nums1 = [4,2,3,1,5] 。
- 交换下标为 1 和 2 的两个值，代价为 1 + 2 = 3 。现在 nums1 = [4,3,2,1,5] 。
- 交换下标为 0 和 4 的两个值，代价为 0 + 4 = 4 。现在 nums1 = [5,3,2,1,4] 。
最后，对于每个下标 i ，都有 nums1[i] != nums2[i] 。总代价为 10 。
还有别的交换值的方法，但是无法得到代价和小于 10 的方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums1 = [2,2,2,1,3], nums2 = [1,2,2,3,3]
<b>输出：</b>10
<b>解释：</b>
实现目标的一种方法为：
- 交换下标为 2 和 3 的两个值，代价为 2 + 3 = 5 。现在 nums1 = [2,2,1,2,3] 。
- 交换下标为 1 和 4 的两个值，代价为 1 + 4 = 5 。现在 nums1 = [2,3,1,2,2] 。
总代价为 10 ，是所有方案中的最小代价。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,2], nums2 = [1,2,2]
<b>输出：</b>-1
<b>解释：</b>
不管怎么操作，都无法满足题目要求。
所以返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们先同时遍历数组 `nums1` 和 `nums2`，统计相同位置上的值相同的个数 `same`，这些位置上的值必须交换，因此，将这些位置下标累加到答案中。另外，用数组或哈希表 `cnt` 统计这些相同值的出现次数。

如果所有相同值的出现次数均不超过 `same` 的一半，那么意味着，我们可以在其内部，通过两两交换，使得对应位置上的值不同，而这些交换，已经在上面累加下标时计入了答案中了，无需额外的代价。否则，如果某个值的出现次数超过 `same` 的一半，那么对于这个值就是多出的个数，我们需要在数组的其他位置上找到合适的，进行交换。这里我们可以直接遍历一遍数组得出。

如果最终还有剩余位置未能交换，说明无法达成目标，返回 $-1$ 即可，否则返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums1` 或 `nums2` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumTotalCost(self, nums1: List[int], nums2: List[int]) -> int:
        ans = same = 0
        cnt = Counter()
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            if a == b:
                same += 1
                ans += i
                cnt[a] += 1

        m = lead = 0
        for k, v in cnt.items():
            if v * 2 > same:
                m = v * 2 - same
                lead = k
                break
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            if m and a != b and a != lead and b != lead:
                ans += i
                m -= 1
        return -1 if m else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        long ans = 0;
        int same = 0;
        int n = nums1.length;
        int[] cnt = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            if (nums1[i] == nums2[i]) {
                ans += i;
                ++same;
                ++cnt[nums1[i]];
            }
        }
        int m = 0, lead = 0;
        for (int i = 0; i < cnt.length; ++i) {
            int t = cnt[i] * 2 - same;
            if (t > 0) {
                m = t;
                lead = i;
                break;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (m > 0 && nums1[i] != nums2[i] && nums1[i] != lead && nums2[i] != lead) {
                ans += i;
                --m;
            }
        }
        return m > 0 ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minimumTotalCost(vector<int>& nums1, vector<int>& nums2) {
        long long ans = 0;
        int same = 0;
        int n = nums1.size();
        int cnt[n + 1];
        memset(cnt, 0, sizeof cnt);
        for (int i = 0; i < n; ++i) {
            if (nums1[i] == nums2[i]) {
                ans += i;
                ++same;
                ++cnt[nums1[i]];
            }
        }
        int m = 0, lead = 0;
        for (int i = 0; i < n + 1; ++i) {
            int t = cnt[i] * 2 - same;
            if (t > 0) {
                m = t;
                lead = i;
                break;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (m > 0 && nums1[i] != nums2[i] && nums1[i] != lead && nums2[i] != lead) {
                ans += i;
                --m;
            }
        }
        return m > 0 ? -1 : ans;
    }
};
```

### **Go**

```go
func minimumTotalCost(nums1 []int, nums2 []int) (ans int64) {
	same, n := 0, len(nums1)
	cnt := make([]int, n+1)
	for i, a := range nums1 {
		b := nums2[i]
		if a == b {
			same++
			ans += int64(i)
			cnt[a]++
		}
	}
	var m, lead int
	for i, v := range cnt {
		if t := v*2 - same; t > 0 {
			m = t
			lead = i
			break
		}
	}
	for i, a := range nums1 {
		b := nums2[i]
		if m > 0 && a != b && a != lead && b != lead {
			ans += int64(i)
			m--
		}
	}
	if m > 0 {
		return -1
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
