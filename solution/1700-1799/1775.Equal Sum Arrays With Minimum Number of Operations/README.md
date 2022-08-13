# [1775. 通过最少操作次数使数组的和相等](https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations)

[English Version](/solution/1700-1799/1775.Equal%20Sum%20Arrays%20With%20Minimum%20Number%20of%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度可能不等的整数数组 <code>nums1</code> 和 <code>nums2</code> 。两个数组中的所有值都在 <code>1</code> 到 <code>6</code> 之间（包含 <code>1</code> 和 <code>6</code>）。</p>

<p>每次操作中，你可以选择 <strong>任意</strong> 数组中的任意一个整数，将它变成 <code>1</code> 到 <code>6</code> 之间 <strong>任意</strong> 的值（包含 <code>1</code> 和 <code><span style="">6</span></code>）。</p>

<p>请你返回使 <code>nums1</code> 中所有数的和与 <code>nums2</code> 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
<b>输出：</b>3
<b>解释：</b>你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
- 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [<strong>6</strong>,1,2,2,2,2] 。
- 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,<strong>1</strong>], nums2 = [6,1,2,2,2,2] 。
- 将 nums1[2] 变为 2 。 nums1 = [1,2,<strong>2</strong>,4,5,1], nums2 = [6,1,2,2,2,2] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [1,1,1,1,1,1,1], nums2 = [6]
<b>输出：</b>-1
<b>解释：</b>没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums1 = [6,6], nums2 = [1]
<b>输出：</b>3
<b>解释：</b>你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
- 将 nums1[0] 变为 2 。 nums1 = [<strong>2</strong>,6], nums2 = [1] 。
- 将 nums1[1] 变为 2 。 nums1 = [2,<strong>2</strong>], nums2 = [1] 。
- 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [<strong>4</strong>] 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

贪心 + 计数排序。

设 s1, s2 分别表示数组 nums1, nums2 的和。不失一般性，可以设 `s1 < s2`。

直观上看，要想使得操作次数最小，我们应当尽可能增加 nums1 中元素的值，同时尽可能减少 nums2 中元素的值，因此：

-   nums1 中每个元素 x 可以增加的量为 `6-x ∈ [0,5]`
-   nums2 中每个元素 x 可以减少的量为 `x-1 ∈ [0,5]`

记 `diff = s2 - s1`，我们要选出最少的元素，使得**在 nums1 中元素的增加量**与**在 nums2 中元素的减少量**之和大于等于 diff。因此我们可以贪心地从 5 这个增加/减少量开始递减地选取即可。

在实际的代码编写中，我们只需要使用一个长度为 6 的数组，其下标为增加 / 减少量，值为对应的元素数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        s1, s2 = sum(nums1), sum(nums2)
        if s1 == s2:
            return 0
        if s1 > s2:
            return self.minOperations(nums2, nums1)
        freq = [0] * 6
        for x in nums1:
            freq[6 - x] += 1
        for x in nums2:
            freq[x - 1] += 1
        diff = s2 - s1
        ans, i = 0, 5
        while i > 0 and diff > 0:
            while freq[i] and diff > 0:
                diff -= i
                freq[i] -= 1
                ans += 1
            i -= 1
        return -1 if diff > 0 else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int s1 = sum(nums1);
        int s2 = sum(nums2);
        if (s1 == s2) {
            return 0;
        }
        if (s1 > s2) {
            return minOperations(nums2, nums1);
        }
        int[] freq = new int[6];
        for (int x : nums1) {
            ++freq[6 - x];
        }
        for (int x : nums2) {
            ++freq[x - 1];
        }
        int diff = s2 - s1;
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; --i) {
            while (freq[i] > 0 && diff > 0) {
                diff -= i;
                --freq[i];
                ++ans;
            }
        }
        return diff > 0 ? - 1 : ans;
    }

    private int sum(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        if (s1 == s2) return 0;
        if (s1 > s2) return minOperations(nums2, nums1);
        vector<int> freq(6);
        for (int x : nums1) ++freq[6 - x];
        for (int x : nums2) ++freq[x - 1];
        int diff = s2 - s1;
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; --i) {
            while (freq[i] && diff > 0) {
                diff -= i;
                --freq[i];
                ++ans;
            }
        }
        return diff > 0 ? -1 : ans;
    }
};
```

### **Go**

```go
func minOperations(nums1 []int, nums2 []int) int {
	s1, s2 := sum(nums1), sum(nums2)
	if s1 == s2 {
		return 0
	}
	if s1 > s2 {
		return minOperations(nums2, nums1)
	}
	freq := make([]int, 6)
	for _, x := range nums1 {
		freq[6-x]++
	}
	for _, x := range nums2 {
		freq[x-1]++
	}
	diff := s2 - s1
	ans := 0
	for i := 5; i > 0 && diff > 0; i-- {
		for freq[i] > 0 && diff > 0 {
			diff -= i
			freq[i]--
			ans++
		}
	}
	if diff > 0 {
		return -1
	}
	return ans
}

func sum(nums []int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
