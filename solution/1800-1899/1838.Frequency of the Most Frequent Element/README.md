# [1838. 最高频元素的频数](https://leetcode.cn/problems/frequency-of-the-most-frequent-element)

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

**方法一：排序 + 滑动窗口**

我们可以先对数组 $nums$ 进行排序，然后枚举每个数作为最高频元素，用滑动窗口维护下标 $l$ 到 $r$ 的数都增加到 $nums[r]$ 的操作次数。如果操作次数大于 $k$，则窗口左端右移，直到操作次数小于等于 $k$。这样，我们就可以求出以每个数为最高频元素的最大频数。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $nums$ 的长度。

**方法二：排序 + 前缀和 + 二分查找**

我们观察发现，如果一个区间长度 $cnt$ 满足条件，那么区间长度小于 $cnt$ 的也一定满足条件。因此，我们可以使用二分查找的方法，找到最大的且满足条件的区间长度。

在二分查找之前，我们需要对数组 $nums[r]$ 进行排序，然后计算出数组 $nums[r]$ 的前缀和数组 $s$，其中 $s[i]$ 表示数组 $nums[r]$ 前 $i$ 个数的和。这样，我们就可以在 $O(1)$ 的时间内求出区间 $[i, j]$ 的和为 $s[j + 1] - s[i]$。

接下来，我们定义二分的左边界 $left=1$, $right=n$。然后二分枚举区间长度 $mid$，如果当前区间长度 $mid$ 满足条件，那么我们就更新二分的左边界为 $mid$，否则更新二分的右边界为 $mid-1$。最后，我们返回二分的左边界即可。

问题转换为如何判断区间长度为 $cnt$ 的区间是否满足条件。我们在 $[0,..n-cnt]$ 范围内枚举左端点 $i$，那么此时区间的右端点 $j = i + cnt - 1$。要把区间内的所有数都增加到 $nums[j]$，需要的操作次数为 $nums[j] \times cnt - (s[j + 1] - s[i])$。如果这个操作次数小于等于 $k$，那么说明区间长度为 $cnt$ 的区间满足条件，返回 `true`。否则枚举结束，返回 `false`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        l, r, n = 0, 1, len(nums)
        ans, window = 1, 0
        while r < n:
            window += (nums[r] - nums[r - 1]) * (r - l)
            while window > k:
                window -= nums[r] - nums[l]
                l += 1
            r += 1
            ans = max(ans, r - l)
        return ans
```

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        def check(cnt):
            for i in range(n + 1 - cnt):
                j = i + cnt - 1
                if nums[j] * cnt - (s[j + 1] - s[i]) <= k:
                    return True
            return False

        nums.sort()
        s = list(accumulate(nums, initial=0))
        n = len(nums)
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
        int n = nums.length;
        int ans = 1, window = 0;
        for (int l = 0, r = 1; r < n; ++r) {
            window += (nums[r] - nums[r - 1]) * (r - l);
            while (window > k) {
                window -= (nums[r] - nums[l++]);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    private long[] s;
    private int[] nums;
    private int n;
    private int k;

    public int maxFrequency(int[] nums, int k) {
        n = nums.length;
        Arrays.sort(nums);
        this.nums = nums;
        this.s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        this.k = k;
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int cnt) {
        for (int i = 0; i < n + 1 - cnt; ++i) {
            int j = i + cnt - 1;
            if (1L * nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int ans = 1;
        long long window = 0;
        for (int l = 0, r = 1; r < n; ++r) {
            window += 1LL * (nums[r] - nums[r - 1]) * (r - l);
            while (window > k) {
                window -= (nums[r] - nums[l++]);
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int left = 1, right = n;
        auto check = [&](int cnt) {
            for (int i = 0; i < n + 1 - cnt; ++i) {
                int j = i + cnt - 1;
                if (1LL * nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                    return true;
                }
            }
            return false;
        };
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
};
```

### **Go**

```go
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	ans, window := 1, 0
	for l, r := 0, 1; r < len(nums); r++ {
		window += (nums[r] - nums[r-1]) * (r - l)
		for window > k {
			window -= nums[r] - nums[l]
			l++
		}
		ans = max(ans, r-l+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	left, right := 1, n
	check := func(cnt int) bool {
		for i := 0; i < n+1-cnt; i++ {
			j := i + cnt - 1
			if nums[j]*cnt-(s[j+1]-s[i]) <= k {
				return true
			}
		}
		return false
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maxFrequency = function (nums, k) {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let window = 0;
    const n = nums.length;
    for (let l = 0, r = 1; r < n; ++r) {
        window += (nums[r] - nums[r - 1]) * (r - l);
        while (window > k) {
            window -= nums[r] - nums[l++];
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maxFrequency = function (nums, k) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    const check = cnt => {
        for (let i = 0; i < n + 1 - cnt; ++i) {
            const j = i + cnt - 1;
            if (nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    };
    let left = 1;
    let right = n;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
};
```

### **TypeScript**

```ts
function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let window = 0;
    const n = nums.length;
    for (let l = 0, r = 1; r < n; ++r) {
        window += (nums[r] - nums[r - 1]) * (r - l);
        while (window > k) {
            window -= nums[r] - nums[l++];
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

```ts
function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    const check = (cnt: number) => {
        for (let i = 0; i < n + 1 - cnt; ++i) {
            const j = i + cnt - 1;
            if (nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    };
    let left = 1;
    let right = n;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
