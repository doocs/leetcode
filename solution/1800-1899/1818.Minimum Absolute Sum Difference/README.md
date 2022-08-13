# [1818. 绝对差值和](https://leetcode.cn/problems/minimum-absolute-sum-difference)

[English Version](/solution/1800-1899/1818.Minimum%20Absolute%20Sum%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数数组 <code>nums1</code> 和 <code>nums2</code> ，数组的长度都是 <code>n</code> 。</p>

<p>数组 <code>nums1</code> 和 <code>nums2</code> 的 <strong>绝对差值和</strong> 定义为所有 <code>|nums1[i] - nums2[i]|</code>（<code>0 <= i < n</code>）的 <strong>总和</strong>（<strong>下标从 0 开始</strong>）。</p>

<p>你可以选用 <code>nums1</code> 中的 <strong>任意一个</strong> 元素来替换 <code>nums1</code> 中的 <strong>至多</strong> 一个元素，以 <strong>最小化</strong> 绝对差值和。</p>

<p>在替换数组 <code>nums1</code> 中最多一个元素 <strong>之后</strong> ，返回最小绝对差值和。因为答案可能很大，所以需要对 <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>后返回。</p>

<p><code>|x|</code> 定义为：</p>

<ul>
	<li>如果 <code>x >= 0</code> ，值为 <code>x</code> ，或者</li>
	<li>如果 <code>x <= 0</code> ，值为 <code>-x</code></li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,7,5], nums2 = [2,3,5]
<strong>输出：</strong>3
<strong>解释：</strong>有两种可能的最优方案：
- 将第二个元素替换为第一个元素：[1,<strong>7</strong>,5] => [1,<strong>1</strong>,5] ，或者
- 将第二个元素替换为第三个元素：[1,<strong>7</strong>,5] => [1,<strong>5</strong>,5]
两种方案的绝对差值和都是 <code>|1-2| + (|1-3| 或者 |5-3|) + |5-5| = </code>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
<strong>输出：</strong>0
<strong>解释：</strong>nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
</pre>

<p><strong>示例 3</strong><strong>：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
<strong>输出：</strong>20
<strong>解释：</strong>将第一个元素替换为第二个元素：[<strong>1</strong>,10,4,4,2,7] => [<strong>10</strong>,10,4,4,2,7]
绝对差值和为 <code>|10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20</code>
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>n == nums2.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums1[i], nums2[i] <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

时间复杂度 O(nlogn)，空间复杂度 O(n)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]
        mod = 10**9 + 7
        s = sum(diff)
        if s == 0:
            return 0
        nums1.sort()
        n = len(nums1)
        mx = 0
        for i, b in enumerate(nums2):
            d = diff[i]
            if d == 0:
                continue
            idx = bisect_left(nums1, b)
            a1 = a2 = 10**6
            if idx != n:
                a1 = nums1[idx]
            if idx:
                a2 = nums1[idx - 1]
            c = min(abs(b - a1), abs(b - a2))
            mx = max(mx, d - c)
        return (s - mx) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int s = 0;
        for (int i = 0; i < n; ++i) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            s = (s + diff[i]) % MOD;
        }
        if (s == 0) {
            return 0;
        }
        Arrays.sort(nums1);
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d = diff[i];
            if (d == 0) {
                continue;
            }
            int b = nums2[i];
            int idx = search(nums1, b);
            int a1 = 1000000, a2 = 1000000;
            if (idx != n) {
                a1 = nums1[idx];
            }
            if (idx != 0) {
                a2 = nums1[idx - 1];
            }
            int c = Math.min(Math.abs(b - a1), Math.abs(b - a2));
            mx = Math.max(mx, d - c);
        }
        return (s - mx + MOD) % MOD;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
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
    int minAbsoluteSumDiff(vector<int>& nums1, vector<int>& nums2) {
        const int mod = 1e9 + 7;
        int n = nums1.size();
        vector<int> diff(n);
        int s = 0;
        for (int i = 0; i < n; ++i) {
            diff[i] = abs(nums1[i] - nums2[i]);
            s = (s + diff[i]) % mod;
        }
        if (s == 0) return 0;
        sort(nums1.begin(), nums1.end());
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d = diff[i];
            if (d == 0) continue;
            int b = nums2[i];
            int idx = lower_bound(nums1.begin(), nums1.end(), b) - nums1.begin();
            int a1 = 1e6, a2 = 1e6;
            if (idx != n) a1 = nums1[idx];
            if (idx != 0) a2 = nums1[idx - 1];
            int c = min(abs(b - a1), abs(b - a2));
            mx = max(mx, d - c);
        }
        return (s - mx + mod) % mod;
    }
};
```

### **Go**

```go
func minAbsoluteSumDiff(nums1 []int, nums2 []int) int {
	mod := int(1e9) + 7
	n := len(nums1)
	diff := make([]int, n)
	s := 0
	for i := 0; i < n; i++ {
		diff[i] = abs(nums1[i] - nums2[i])
		s = (s + diff[i]) % mod
	}
	if s == 0 {
		return 0
	}
	sort.Ints(nums1)
	mx := 0
	for i, b := range nums2 {
		d := diff[i]
		if d == 0 {
			continue
		}
		idx := search(nums1, b)
		a1, a2 := 1000000, 1000000
		if idx != n {
			a1 = nums1[idx]
		}
		if idx != 0 {
			a2 = nums1[idx-1]
		}
		c := min(abs(b-a1), abs(b-a2))
		mx = max(mx, d-c)
	}
	return (s - mx + mod) % mod
}

func search(nums []int, x int) int {
	left, right := 0, len(nums)
	for left < right {
		mid := (left + right) >> 1
		if nums[mid] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
