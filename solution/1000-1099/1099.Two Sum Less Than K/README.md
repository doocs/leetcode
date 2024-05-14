# [1099. 小于 K 的两数之和 🔒](https://leetcode.cn/problems/two-sum-less-than-k)

[English Version](/solution/1000-1099/1099.Two%20Sum%20Less%20Than%20K/README_EN.md)

<!-- tags:数组,双指针,二分查找,排序 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和整数 <code>k</code> ，返回最大和 <code>sum</code> ，满足存在 <code>i < j</code> 使得 <code>nums[i] + nums[j] = sum</code> 且 <code>sum < k</code> 。如果没有满足此等式的 <code>i,j</code> 存在，则返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [34,23,1,24,75,33,54,8], k = 60
<strong>输出：</strong>58
<strong>解释：</strong>
34 和 24 相加得到 58，58 小于 60，满足题意。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,20,30], k = 15
<strong>输出：</strong>-1
<strong>解释：</strong>
我们无法找到和小于 15 的两个元素。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 100</code></li>
	<li><code>1 <= nums[i] <= 1000</code></li>
	<li><code>1 <= k <= 2000</code></li>
</ul>

## 解法

### 方法一：排序 + 二分查找

我们可以先对数组 $nums$ 进行排序，初始化答案为 $-1$。

接下来，我们枚举数组中的每个元素 $nums[i]$，并在数组中寻找满足 $nums[j] + nums[i] \lt k$ 的最大的 $nums[j]$。这里我们可以使用二分查找来加速寻找过程。如果找到了这样的 $nums[j]$，那么我们就可以更新答案，即 $ans = \max(ans, nums[i] + nums[j])$。

枚举结束后，返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = -1
        for i, x in enumerate(nums):
            j = bisect_left(nums, k - x, lo=i + 1) - 1
            if i < j:
                ans = max(ans, x + nums[j])
        return ans
```

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = search(nums, k - nums[i], i + 1, n) - 1;
            if (i < j) {
                ans = Math.max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }

    private int search(int[] nums, int x, int l, int r) {
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1, n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = lower_bound(nums.begin() + i + 1, nums.end(), k - nums[i]) - nums.begin() - 1;
            if (i < j) {
                ans = max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }
};
```

```go
func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	ans := -1
	for i, x := range nums {
		j := sort.SearchInts(nums[i+1:], k-x) + i
		if v := nums[i] + nums[j]; i < j && ans < v {
			ans = v
		}
	}
	return ans
}
```

```ts
function twoSumLessThanK(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = -1;
    for (let i = 0, j = nums.length - 1; i < j; ) {
        const s = nums[i] + nums[j];
        if (s < k) {
            ans = Math.max(ans, s);
            ++i;
        } else {
            --j;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二：排序 + 双指针

与方法一类似，我们可以先对数组 $nums$ 进行排序，初始化答案为 $-1$。

接下来，我们使用双指针 $i$ 和 $j$ 分别指向数组的左右两端，每次判断 $s = nums[i] + nums[j]$ 是否小于 $k$，如果小于 $k$，那么我们就可以更新答案，即 $ans = \max(ans, s)$，并将 $i$ 右移一位，否则将 $j$ 左移一位。

枚举结束后，返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        nums.sort()
        i, j = 0, len(nums) - 1
        ans = -1
        while i < j:
            if (s := nums[i] + nums[j]) < k:
                ans = max(ans, s)
                i += 1
            else:
                j -= 1
        return ans
```

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        for (int i = 0, j = nums.length - 1; i < j;) {
            int s = nums[i] + nums[j];
            if (s < k) {
                ans = Math.max(ans, s);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1;
        for (int i = 0, j = nums.size() - 1; i < j;) {
            int s = nums[i] + nums[j];
            if (s < k) {
                ans = max(ans, s);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
};
```

```go
func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	ans := -1
	for i, j := 0, len(nums)-1; i < j; {
		if s := nums[i] + nums[j]; s < k {
			ans = max(ans, s)
			i++
		} else {
			j--
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
