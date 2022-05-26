# [152. 乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray)

[English Version](/solution/0100-0199/0152.Maximum%20Product%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>&nbsp;，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。</p>

<p>测试用例的答案是一个&nbsp;<strong>32-位</strong> 整数。</p>

<p><strong>子数组</strong> 是数组的连续子序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,-2,4]
<strong>输出:</strong> <code>6</code>
<strong>解释:</strong>&nbsp;子数组 [2,3] 有最大乘积 6。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [-2,0,-1]
<strong>输出:</strong> 0
<strong>解释:</strong>&nbsp;结果不能为 2, 因为 [-2,-1] 不是子数组。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li><code>nums</code> 的任何前缀或后缀的乘积都 <strong>保证</strong>&nbsp;是一个 <strong>32-位</strong> 整数</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

考虑当前位置 i：

-   如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样可以负负得正，并且我们希望这个积尽可能「负得更多」，即尽可能小。
-   如果是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。

因此，分别维护 fmax 和 fmin。

-   `fmax(i) = max(nums[i], fmax(i - 1) * nums[i], fmin(i - 1) * nums[i])`
-   `fmin(i) = min(nums[i], fmax(i - 1) * nums[i], fmin(i - 1) * nums[i])`
-   `res = max(fmax(i)), i∈[0, n)`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        maxf = minf = res = nums[0]
        for num in nums[1:]:
            m, n = maxf, minf
            maxf = max(num, m * num, n * num)
            minf = min(num, m * num, n * num)
            res = max(res, maxf)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxProduct(int[] nums) {
        int maxf = nums[0], minf = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int m = maxf, n = minf;
            maxf = Math.max(nums[i], Math.max(m * nums[i], n * nums[i]));
            minf = Math.min(nums[i], Math.min(m * nums[i], n * nums[i]));
            res = Math.max(res, maxf);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function maxProduct(nums: number[]): number {
    let n = nums.length;
    let preMax = nums[0],
        preMin = nums[0],
        ans = nums[0];
    for (let i = 1; i < n; ++i) {
        let cur = nums[i];
        let x = preMax,
            y = preMin;
        preMax = Math.max(x * cur, y * cur, cur);
        preMin = Math.min(x * cur, y * cur, cur);
        ans = Math.max(preMax, ans);
    }
    return ans;
}
```

### **C#**

```cs
public class Solution {
    public int MaxProduct(int[] nums) {
        int maxf = nums[0], minf = nums[0], res = nums[0];
        for (int i = 1; i < nums.Length; ++i)
        {
            int m = maxf, n = minf;
            maxf = Math.Max(nums[i], Math.Max(nums[i] * m, nums[i] * n));
            minf = Math.Min(nums[i], Math.Min(nums[i] * m, nums[i] * n));
            res = Math.Max(res, maxf);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int maxf = nums[0], minf = nums[0], res = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int m = maxf, n = minf;
            maxf = max(nums[i], max(nums[i] * m, nums[i] * n));
            minf = min(nums[i], min(nums[i] * m, nums[i] * n));
            res = max(res, maxf);
        }
        return res;
    }
};
```

### **Go**

```go
func maxProduct(nums []int) int {
	maxf, minf, res := nums[0], nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		m, n := maxf, minf
		maxf = max(nums[i], max(nums[i]*m, nums[i]*n))
		minf = min(nums[i], min(nums[i]*m, nums[i]*n))
		res = max(res, maxf)
	}
	return res
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
```

### **Rust**

```rust
impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut min = nums[0];
        let mut max = nums[0];
        let mut res = nums[0];
        for &num in nums.iter().skip(1) {
            let (pre_min, pre_max) = (min, max);
            min = num.min(num * pre_min).min(num * pre_max);
            max = num.max(num * pre_min).max(num * pre_max);
            res = res.max(max);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
