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

**方法一：贪心 + 排序**

我们用 $s_1$ 和 $s_2$ 分别表示数组 `nums1` 和 `nums2` 的和。

如果 $s_1 = s_2$，则不需要任何操作，直接返回 $0$。否则，我们不妨设 $s_1 \lt s_2$，即 $nums_1$ 中的元素和小于 $nums_2$ 中的元素和，那么两个数组元素和的差值 $d=s_2-s_1$。

要使得两个数组元素和相等，我们需要对 `nums1` 中的元素进行增大操作，对 `nums2` 中的元素进行减小操作。

对于 `nums1` 中的每个元素 $v$，我们最多可以将其增大到 $6$，那么 $v$ 可以增大的量为 $6-v$。对于 `nums2` 中的每个元素 $v$，我们最多可以将其减小到 $1$，那么 $v$ 可以减小的量为 $v-1$。

我们将每个元素的变化量放入数组 `arr` 中，然后对数组 `arr` 进行降序排列。

接下来，我们从数组 `arr` 的第一个元素开始，贪心地将 $d$ 减去每个元素的变化量，直到 $d \leq 0$，返回此时的操作次数即可。

遍历结束后，如果 $d \gt 0$，说明无法使得两个数组元素和相等，返回 $-1$。

时间复杂度 $O((m+n) \times \log (m + n))$，空间复杂度 $O(m+n)$。其中 $m$ 和 $n$ 分别为数组 `nums1` 和 `nums2` 的长度。

**方法二：贪心 + 计数排序**

方法一中，我们需要创建数组 `arr` 并进行排序，时空复杂度较高。由于数组 `arr` 中元素的范围为 $[0,..5]$，因此我们创建一个长度为 $6$ 的数组 `cnt`，用于统计数组 `arr` 中每个元素的数量，也即每个最大变化量的元素的数量。

接下来，我们从最大变化量 $i=5$ 开始，贪心地将 $d$ 减去最大变化量，直到 $d \leq 0$，返回此时的操作次数即可。

时间复杂度 $O(m+n)$，空间复杂度 $O(C)$。其中 $m$ 和 $n$ 分别为数组 `nums1` 和 `nums2` 的长度。本题中 $C=6$。

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
        arr = [6 - v for v in nums1] + [v - 1 for v in nums2]
        d = s2 - s1
        for i, v in enumerate(sorted(arr, reverse=True), 1):
            d -= v
            if d <= 0:
                return i
        return -1
```

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        s1, s2 = sum(nums1), sum(nums2)
        if s1 == s2:
            return 0
        if s1 > s2:
            return self.minOperations(nums2, nums1)
        cnt = Counter([6 - v for v in nums1] + [v - 1 for v in nums2])
        d = s2 - s1
        ans = 0
        for i in range(5, 0, -1):
            while cnt[i] and d > 0:
                d -= i
                cnt[i] -= 1
                ans += 1
        return ans if d <= 0 else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        if (s1 == s2) {
            return 0;
        }
        if (s1 > s2) {
            return minOperations(nums2, nums1);
        }
        int d = s2 - s1;
        int[] arr = new int[nums1.length + nums2.length];
        int k = 0;
        for (int v : nums1) {
            arr[k++] = 6 - v;
        }
        for (int v : nums2) {
            arr[k++] = v - 1;
        }
        Arrays.sort(arr);
        for (int i = 1, j = arr.length - 1; j >= 0; ++i, --j) {
            d -= arr[j];
            if (d <= 0) {
                return i;
            }
        }
        return -1;
    }
}
```

```java
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        if (s1 == s2) {
            return 0;
        }
        if (s1 > s2) {
            return minOperations(nums2, nums1);
        }
        int d = s2 - s1;
        int[] cnt = new int[6];
        for (int v : nums1) {
            ++cnt[6 - v];
        }
        for (int v : nums2) {
            ++cnt[v - 1];
        }
        int ans = 0;
        for (int i = 5; i > 0; --i) {
            while (cnt[i] > 0 && d > 0) {
                d -= i;
                --cnt[i];
                ++ans;
            }
        }
        return d <= 0 ? ans : -1;
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
        int d = s2 - s1;
        int arr[nums1.size() + nums2.size()];
        int k = 0;
        for (int& v : nums1) arr[k++] = 6 - v;
        for (int& v : nums2) arr[k++] = v - 1;
        sort(arr, arr + k, greater<>());
        for (int i = 0; i < k; ++i) {
            d -= arr[i];
            if (d <= 0) return i + 1;
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        if (s1 == s2) return 0;
        if (s1 > s2) return minOperations(nums2, nums1);
        int d = s2 - s1;
        int cnt[6] = {0};
        for (int& v : nums1) ++cnt[6 - v];
        for (int& v : nums2) ++cnt[v - 1];
        int ans = 0;
        for (int i = 5; i; --i) {
            while (cnt[i] && d > 0) {
                d -= i;
                --cnt[i];
                ++ans;
            }
        }
        return d <= 0 ? ans : -1;
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
	d := s2 - s1
	arr := []int{}
	for _, v := range nums1 {
		arr = append(arr, 6-v)
	}
	for _, v := range nums2 {
		arr = append(arr, v-1)
	}
	sort.Sort(sort.Reverse(sort.IntSlice(arr)))
	for i, v := range arr {
		d -= v
		if d <= 0 {
			return i + 1
		}
	}
	return -1
}

func sum(nums []int) (s int) {
	for _, v := range nums {
		s += v
	}
	return
}
```

```go
func minOperations(nums1 []int, nums2 []int) (ans int) {
	s1, s2 := sum(nums1), sum(nums2)
	if s1 == s2 {
		return 0
	}
	if s1 > s2 {
		return minOperations(nums2, nums1)
	}
	d := s2 - s1
	cnt := [6]int{}
	for _, v := range nums1 {
		cnt[6-v]++
	}
	for _, v := range nums2 {
		cnt[v-1]++
	}
	for i := 5; i > 0; i-- {
		for cnt[i] > 0 && d > 0 {
			d -= i
			cnt[i]--
			ans++
		}
	}
	if d <= 0 {
		return
	}
	return -1
}

func sum(nums []int) (s int) {
	for _, v := range nums {
		s += v
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
