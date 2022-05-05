# [1679. K 和数对的最大数目](https://leetcode.cn/problems/max-number-of-k-sum-pairs)

[English Version](/solution/1600-1699/1679.Max%20Number%20of%20K-Sum%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>每一步操作中，你需要从数组中选出和为 <code>k</code> 的两个整数，并将它们移出数组。</p>

<p>返回你可以对数组执行的最大操作数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], k = 5
<strong>输出：</strong>2
<strong>解释：</strong>开始时 nums = [1,2,3,4]：
- 移出 1 和 4 ，之后 nums = [2,3]
- 移出 2 和 3 ，之后 nums = []
不再有和为 5 的数对，因此最多执行 2 次操作。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,3,4,3], k = 6
<strong>输出：</strong>1
<strong>解释：</strong>开始时 nums = [3,1,3,4,3]：
- 移出前两个 3 ，之后nums = [1,4,3]
不再有和为 6 的数对，因此最多执行 1 次操作。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>9</sup></code></li>
	<li><code>1 <= k <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

对 nums 进行排序。然后 l, r 分别指向 nums 首尾元素，判断两整数之和 s 与 k 的大小关系。

-   若 `s == k`，说明找到了两个整数，满足和为 k，答案 ans 加 1，然后 l, r 向中间移动；
-   若 `s > k`，则 r 指针向左移动；
-   若 `s > k`，则 l 指针向右移动；
-   继续循环判断，直至 l >= r。

循环结束，返回 ans。

时间复杂度 O(nlogn)，其中 n 为 nums 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxOperations(self, nums: List[int], k: int) -> int:
        nums.sort()
        l, r, ans = 0, len(nums) - 1, 0
        while l < r:
            s = nums[l] + nums[r]
            if s == k:
                ans += 1
                l, r = l + 1, r - 1
            elif s > k:
                r -= 1
            else:
                l += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        int ans = 0;
        while (l < r) {
            int s = nums[l] + nums[r];
            if (s == k) {
                ++ans;
                ++l;
                --r;
            } else if (s > k) {
                --r;
            } else {
                ++l;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxOperations(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int cnt = 0;
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            if (nums[i] + nums[j] == k) {
                i++;
                j--;
                cnt++;
            } else if (nums[i] + nums[j] > k) {
                j--;
            } else {
                i++;
            }
        }
        return cnt;
    }
};
```

### **Go**

```go
func maxOperations(nums []int, k int) int {
	sort.Ints(nums)
	l, r, ans := 0, len(nums)-1, 0
	for l < r {
		s := nums[l] + nums[r]
		if s == k {
			ans++
			l++
			r--
		} else if s > k {
			r--
		} else {
			l++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
