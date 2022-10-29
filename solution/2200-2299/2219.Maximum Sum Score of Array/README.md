# [2219. 数组的最大总分](https://leetcode.cn/problems/maximum-sum-score-of-array)

[English Version](/solution/2200-2299/2219.Maximum%20Sum%20Score%20of%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，数组长度为 <code>n</code> 。</p>

<p><code>nums</code> 在下标 <code>i</code> （<code>0 &lt;= i &lt; n</code>）处的 <strong>总分</strong> 等于下面两个分数中的 <strong>最大值</strong> ：</p>

<ul>
	<li><code>nums</code><strong> 前</strong> <code>i + 1</code> 个元素的总和</li>
	<li><code>nums</code> <strong>后</strong> <code>n - i</code> 个元素的总和</li>
</ul>

<p>返回数组 <code>nums</code> 在任一下标处能取得的 <strong>最大总分</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,3,-2,5]
<strong>输出：</strong>10
<strong>解释：</strong>
下标 0 处的最大总分是 max(4, 4 + 3 + -2 + 5) = max(4, 10) = 10 。
下标 1 处的最大总分是 max(4 + 3, 3 + -2 + 5) = max(7, 6) = 7 。
下标 2 处的最大总分是 max(4 + 3 + -2, -2 + 5) = max(5, 3) = 5 。
下标 3 处的最大总分是 max(4 + 3 + -2 + 5, 5) = max(10, 5) = 10 。
nums 可取得的最大总分是 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-3,-5]
<strong>输出：</strong>-3
<strong>解释：</strong>
下标 0 处的最大总分是 max(-3, -3 + -5) = max(-3, -8) = -3 。
下标 1 处的最大总分是 max(-3 + -5, -5) = max(-8, -5) = -5 。
nums 可取得的最大总分是 -3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumSumScore(self, nums: List[int]) -> int:
        s = [0] + list(accumulate(nums))
        return max(max(s[i + 1], s[-1] - s[i]) for i in range(len(nums)))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maximumSumScore(int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, Math.max(s[i + 1], s[n] - s[i]));
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maximumSumScore(nums: number[]): number {
    const n = nums.length;
    let s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = -Infinity;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, Math.max(s[i + 1], s[n] - s[i]));
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    long long maximumSumScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        long long ans = INT_MIN;
        for (int i = 0; i < n; ++i) ans = max(ans, max(s[i + 1], s[n] - s[i]));
        return ans;
    }
};
```

### **Go**

```go
func maximumSumScore(nums []int) int64 {
	n := len(nums)
	s := make([]int64, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + int64(v)
	}
	var ans int64 = math.MinInt64
	for i := 0; i < n; i++ {
		ans = max(ans, max(s[i+1], s[n]-s[i]))
	}
	return ans
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumSumScore = function (nums) {
    const n = nums.length;
    let s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = -Infinity;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, Math.max(s[i + 1], s[n] - s[i]));
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
