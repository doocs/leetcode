# [1646. 获取生成数组中的最大值](https://leetcode.cn/problems/get-maximum-in-generated-array)

[English Version](/solution/1600-1699/1646.Get%20Maximum%20in%20Generated%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> 。按下述规则生成一个长度为 <code>n + 1</code> 的数组 <code>nums</code> ：</p>

<ul>
	<li><code>nums[0] = 0</code></li>
	<li><code>nums[1] = 1</code></li>
	<li>当 <code>2 <= 2 * i <= n</code> 时，<code>nums[2 * i] = nums[i]</code></li>
	<li>当 <code>2 <= 2 * i + 1 <= n</code> 时，<code>nums[2 * i + 1] = nums[i] + nums[i + 1]</code></li>
</ul>

<p>返回生成数组 <code>nums</code> 中的 <strong>最大</strong> 值。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 7
<strong>输出：</strong>3
<strong>解释：</strong>根据规则：
  nums[0] = 0
  nums[1] = 1
  nums[(1 * 2) = 2] = nums[1] = 1
  nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
  nums[(2 * 2) = 4] = nums[2] = 1
  nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
  nums[(3 * 2) = 6] = nums[3] = 2
  nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>1
<strong>解释：</strong>根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>2
<strong>解释：</strong>根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= n <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

直接模拟生成 nums 数组，然后求 nums 中元素的最大值即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getMaximumGenerated(self, n: int) -> int:
        if n == 0:
            return 0
        nums = [0] * (n + 1)
        nums[1] = 1
        for i in range(2, n + 1):
            nums[i] = nums[i >> 1] if i % 2 == 0 else nums[i >> 1] + nums[(i >> 1) + 1]
        return max(nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        for (int i = 2; i < n + 1; ++i) {
            nums[i] = i % 2 == 0 ? nums[i >> 1] : nums[i >> 1] + nums[(i >> 1) + 1];
        }
        return Arrays.stream(nums).max().getAsInt();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getMaximumGenerated(int n) {
        if (n == 0) return 0;
        vector<int> ans(n + 1, 0);
        ans[1] = 1;
        for (int i = 2; i < n + 1; ++i)
            ans[i] = i % 2 == 0 ? ans[i >> 1] : ans[i >> 1] + ans[(i >> 1) + 1];
        return *max_element(ans.begin(), ans.end());
    }
};
```

### **Go**

```go
func getMaximumGenerated(n int) int {
	if n == 0 {
		return 0
	}
	nums := make([]int, n+1)
	nums[1] = 1
	for i := 2; i <= n; i++ {
		if i%2 == 0 {
			nums[i] = nums[i>>1]
		} else {
			nums[i] = nums[i>>1] + nums[(i>>1)+1]
		}
	}
	var ans int
	for _, num := range nums {
		ans = max(ans, num)
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

### **...**

```

```

<!-- tabs:end -->
