# [2439. 最小化数组中的最大值](https://leetcode.cn/problems/minimize-maximum-of-array)

[English Version](/solution/2400-2499/2439.Minimize%20Maximum%20of%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums</code>&nbsp;，它含有&nbsp;<code>n</code>&nbsp;个非负整数。</p>

<p>每一步操作中，你需要：</p>

<ul>
	<li>选择一个满足&nbsp;<code>1 &lt;= i &lt; n</code>&nbsp;的整数 <code>i</code>&nbsp;，且&nbsp;<code>nums[i] &gt; 0</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[i]</code>&nbsp;减 1 。</li>
	<li>将&nbsp;<code>nums[i - 1]</code>&nbsp;加 1 。</li>
</ul>

<p>你可以对数组执行 <strong>任意</strong>&nbsp;次上述操作，请你返回可以得到的 <code>nums</code>&nbsp;数组中<b>&nbsp;最大值</b>&nbsp;<strong>最小</strong> 为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,7,1,6]
<b>输出：</b>5
<strong>解释：</strong>
一串最优操作是：
1. 选择 i = 1 ，nums 变为 [4,6,1,6] 。
2. 选择 i = 3 ，nums 变为 [4,6,2,5] 。
3. 选择 i = 1 ，nums 变为 [5,5,2,5] 。
nums 中最大值为 5 。无法得到比 5 更小的最大值。
所以我们返回 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [10,1]
<b>输出：</b>10
<strong>解释：</strong>
最优解是不改动 nums ，10 是最大值，所以返回 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

最小化数组的最大值，容易想到二分查找。我们二分枚举数组的最大值 $mx$，找到一个满足题目要求的、且值最小的 $mx$ 即可。

时间复杂度 $O(n\times \log M)$，其中 $n$ 为数组的长度，而 $M$ 为数组中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimizeArrayValue(self, nums: List[int]) -> int:
        def check(mx):
            d = 0
            for x in nums[:0:-1]:
                d = max(0, d + x - mx)
            return nums[0] + d <= mx

        left, right = 0, max(nums)
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] nums;

    public int minimizeArrayValue(int[] nums) {
        this.nums = nums;
        int left = 0, right = max(nums);
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int mx) {
        long d = 0;
        for (int i = nums.length - 1; i > 0; --i) {
            d = Math.max(0, d + nums[i] - mx);
        }
        return nums[0] + d <= mx;
    }

    private int max(int[] nums) {
        int v = nums[0];
        for (int x : nums) {
            v = Math.max(v, x);
        }
        return v;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimizeArrayValue(vector<int>& nums) {
        int left = 0, right = *max_element(nums.begin(), nums.end());
        auto check = [&](int mx) {
            long d = 0;
            for (int i = nums.size() - 1; i; --i) {
                d = max(0l, d + nums[i] - mx);
            }
            return nums[0] + d <= mx;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }
};
```

### **Go**

```go
func minimizeArrayValue(nums []int) int {
	left, right := 0, 0
	for _, x := range nums {
		right = max(right, x)
	}
	check := func(mx int) bool {
		d := 0
		for i := len(nums) - 1; i > 0; i-- {
			d = max(0, nums[i]+d-mx)
		}
		return nums[0]+d <= mx
	}
	for left < right {
		mid := (left + right) >> 1
		if check(mid) {
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
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
