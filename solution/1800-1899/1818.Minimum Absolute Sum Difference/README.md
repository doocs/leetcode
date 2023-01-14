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

根据题意，我们可以先计算出在不进行替换的情况下，`nums1` 和 `nums2` 的绝对差值和，记为 $s$。

接下来，我们枚举 `nums1` 中的每个元素 $nums1[i]$，将其替换为与 $nums2[i]$ 最接近的元素，并且这个最接近的元素在 `nums1` 中。因此，我们可以在枚举之前，先复制一份 `nums1`，得到数组 `nums`，并将 `nums` 排序。接下来，就在 `nums` 中二分查找与 $nums2[i]$ 最接近的元素，记为 $nums[j]$，并计算 $|nums1[i] - nums2[i]| - |nums[j] - nums2[i]|$，更新差值 $mx$ 的最大值。

最后，我们将 $s$ 减去 $mx$，即为答案。注意取模操作。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums1` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        mod = 10**9 + 7
        nums = sorted(nums1)
        s = sum(abs(a - b) for a, b in zip(nums1, nums2)) % mod
        mx = 0
        for a, b in zip(nums1, nums2):
            d1, d2 = abs(a - b), inf
            i = bisect_left(nums, b)
            if i < len(nums):
                d2 = min(d2, abs(nums[i] - b))
            if i:
                d2 = min(d2, abs(nums[i - 1] - b))
            mx = max(mx, d1 - d2)
        return (s - mx + mod) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int mod = (int) 1e9 + 7;
        int[] nums = nums1.clone();
        Arrays.sort(nums);
        int s = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            s = (s + Math.abs(nums1[i] - nums2[i])) % mod;
        }
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d1 = Math.abs(nums1[i] - nums2[i]);
            int d2 = 1 << 30;
            int j = search(nums, nums2[i]);
            if (j < n) {
                d2 = Math.min(d2, Math.abs(nums[j] - nums2[i]));
            }
            if (j > 0) {
                d2 = Math.min(d2, Math.abs(nums[j - 1] - nums2[i]));
            }
            mx = Math.max(mx, d1 - d2);
        }
        return (s - mx + mod) % mod;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
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
        vector<int> nums(nums1);
        sort(nums.begin(), nums.end());
        int s = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            s = (s + abs(nums1[i] - nums2[i])) % mod;
        }
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d1 = abs(nums1[i] - nums2[i]);
            int d2 = 1 << 30;
            int j = lower_bound(nums.begin(), nums.end(), nums2[i]) - nums.begin();
            if (j < n) {
                d2 = min(d2, abs(nums[j] - nums2[i]));
            }
            if (j) {
                d2 = min(d2, abs(nums[j - 1] - nums2[i]));
            }
            mx = max(mx, d1 - d2);
        }
        return (s - mx + mod) % mod;
    }
};
```

### **Go**

```go
func minAbsoluteSumDiff(nums1 []int, nums2 []int) int {
	n := len(nums1)
	nums := make([]int, n)
	copy(nums, nums1)
	sort.Ints(nums)
	s, mx := 0, 0
	const mod int = 1e9 + 7
	for i, a := range nums1 {
		b := nums2[i]
		s = (s + abs(a-b)) % mod
	}
	for i, a := range nums1 {
		b := nums2[i]
		d1, d2 := abs(a-b), 1<<30
		j := sort.Search(n, func(k int) bool { return nums[k] >= b })
		if j < n {
			d2 = min(d2, abs(nums[j]-b))
		}
		if j > 0 {
			d2 = min(d2, abs(nums[j-1]-b))
		}
		mx = max(mx, d1-d2)
	}
	return (s - mx + mod) % mod
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

### **JavaScript**

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var minAbsoluteSumDiff = function (nums1, nums2) {
    const mod = 10 ** 9 + 7;
    const nums = [...nums1];
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s = (s + Math.abs(nums1[i] - nums2[i])) % mod;
    }
    let mx = 0;
    for (let i = 0; i < n; ++i) {
        const d1 = Math.abs(nums1[i] - nums2[i]);
        let d2 = 1 << 30;
        let j = search(nums, nums2[i]);
        if (j < n) {
            d2 = Math.min(d2, Math.abs(nums[j] - nums2[i]));
        }
        if (j) {
            d2 = Math.min(d2, Math.abs(nums[j - 1] - nums2[i]));
        }
        mx = Math.max(mx, d1 - d2);
    }
    return (s - mx + mod) % mod;
};

function search(nums, x) {
    let left = 0;
    let right = nums.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **TypeScript**

```ts
function minAbsoluteSumDiff(nums1: number[], nums2: number[]): number {
    const mod = 10 ** 9 + 7;
    const nums = [...nums1];
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s = (s + Math.abs(nums1[i] - nums2[i])) % mod;
    }
    let mx = 0;
    for (let i = 0; i < n; ++i) {
        const d1 = Math.abs(nums1[i] - nums2[i]);
        let d2 = 1 << 30;
        let j = search(nums, nums2[i]);
        if (j < n) {
            d2 = Math.min(d2, Math.abs(nums[j] - nums2[i]));
        }
        if (j) {
            d2 = Math.min(d2, Math.abs(nums[j - 1] - nums2[i]));
        }
        mx = Math.max(mx, d1 - d2);
    }
    return (s - mx + mod) % mod;
}

function search(nums: number[], x: number): number {
    let left = 0;
    let right = nums.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
