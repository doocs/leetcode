# [1838. 最高频元素的频数](https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element)

[English Version](/solution/1800-1899/1838.Frequency%20of%20the%20Most%20Frequent%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>元素的 <strong>频数</strong> 是该元素在一个数组中出现的次数。</p>

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。在一步操作中，你可以选择 <code>nums</code> 的一个下标，并将该下标对应元素的值增加 <code>1</code> 。</p>

<p>执行最多 <code>k</code> 次操作后，返回数组中最高频元素的 <strong>最大可能频数</strong> <em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4], k = 5
<strong>输出：</strong>3<strong>
解释：</strong>对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
4 是数组中最高频元素，频数是 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,4,8,13], k = 5
<strong>输出：</strong>2
<strong>解释：</strong>存在多种最优解决方案：
- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,9,6], k = 2
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>5</sup></code></li>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

排序后，用滑动窗口维护下标 `l` 到 `r` 的数都增加到 `nums[r]` 的操作次数。

也可以先排序，计算前缀和，然后二分枚举频数，找到符合条件的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = 1
        window = 0
        l, r, n = 0, 1, len(nums)
        while r < n:
            window += (nums[r] - nums[r - 1]) * (r - l)
            r += 1
            while window > k:
                window -= nums[r - 1] - nums[l]
                l += 1
            ans = max(ans, r - l)
        return ans
```

排序 + 前缀和 + 二分：

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        n = len(nums)
        presum = [0] * (n + 1)
        for i in range(1, n + 1):
            presum[i] = presum[i - 1] + nums[i - 1]

        def check(count):
            for i in range(n - count + 1):
                j = i + count - 1
                if nums[j] * count - (presum[j + 1] - presum[i]) <= k:
                    return True
            return False

        left, right = 1, n
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int window = 0;
        int l = 0, r = 1, n = nums.length;
        while (r < n) {
            window += (nums[r] - nums[r - 1]) * (r++ - l);
            while (window > k) {
                window -= nums[r - 1] - nums[l];
                l++;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
```

排序 + 前缀和 + 二分：

```java
class Solution {
    private int[] nums;
    private int k;
    private int n;
    private int[] presum;

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        this.nums = nums;
        this.k = k;
        n = nums.length;
        presum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int count) {
        for (int i = 0; i < n - count + 1; ++i) {
            int j = i + count - 1;
            if (nums[j] * count - (presum[j + 1] - presum[i]) <= k) {
                return true;
            }
        }
        return false;
    }
}
```

### **Go**

```go
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	ans := 1
	window := 0
	l, r, n := 0, 1, len(nums)
	for r < n {
		window += (nums[r] - nums[r-1]) * (r - l)
		r++
		for window > k {
			window -= nums[r-1] - nums[l]
			l++
		}
		ans = max(ans, r-l)
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nums;
    int k;
    vector<long long> presum;
    int n;

    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        n = nums.size();
        this->k = k;
        this->nums = nums;
        presum = vector<long long>(n + 1);
        for (int i = 1; i <= n; ++i) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        int left = 1, right = n;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(mid)) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    bool check(int count) {
        for (int i = 0; i < n - count + 1; ++i) {
            int j = i + count - 1;
            if ((long long) nums[j] * count - (presum[j + 1] - presum[i]) <= k) return true;
        }
        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
