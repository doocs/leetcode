# [2640. 一个数组所有前缀的分数](https://leetcode.cn/problems/find-the-score-of-all-prefixes-of-an-array)

[English Version](/solution/2600-2699/2640.Find%20the%20Score%20of%20All%20Prefixes%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>定义一个数组 <code>arr</code>&nbsp;的 <strong>转换数组</strong>&nbsp;<code>conver</code>&nbsp;为：</p>

<ul>
	<li><code>conver[i] = arr[i] + max(arr[0..i])</code>，其中&nbsp;<code>max(arr[0..i])</code>&nbsp;是满足 <code>0 &lt;= j &lt;= i</code>&nbsp;的所有&nbsp;<code>arr[j]</code>&nbsp;中的最大值。</li>
</ul>

<p>定义一个数组 <code>arr</code>&nbsp;的 <strong>分数</strong>&nbsp;为 <code>arr</code>&nbsp;转换数组中所有元素的和。</p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，请你返回一个长度为 <code>n</code>&nbsp;的数组<em>&nbsp;</em><code>ans</code>&nbsp;，其中&nbsp;<code>ans[i]</code>是前缀&nbsp;<code>nums[0..i]</code>&nbsp;的分数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,3,7,5,10]
<b>输出：</b>[4,10,24,36,56]
<b>解释：</b>
对于前缀 [2] ，转换数组为 [4] ，所以分数为 4 。
对于前缀 [2, 3] ，转换数组为 [4, 6] ，所以分数为 10 。
对于前缀 [2, 3, 7] ，转换数组为 [4, 6, 14] ，所以分数为 24 。
对于前缀 [2, 3, 7, 5] ，转换数组为 [4, 6, 14, 12] ，所以分数为 36 。
对于前缀 [2, 3, 7, 5, 10] ，转换数组为 [4, 6, 14, 12, 20] ，所以分数为 56 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,1,2,4,8,16]
<b>输出：</b>[2,4,8,16,32,64]
<b>解释：</b>
对于前缀 [1] ，转换数组为 [2] ，所以分数为 2 。
对于前缀 [1, 1]，转换数组为 [2, 2] ，所以分数为 4 。
对于前缀 [1, 1, 2]，转换数组为 [2, 2, 4] ，所以分数为 8 。
对于前缀 [1, 1, 2, 4]，转换数组为 [2, 2, 4, 8] ，所以分数为 16 。
对于前缀 [1, 1, 2, 4, 8]，转换数组为 [2, 2, 4, 8, 16] ，所以分数为 32 。
对于前缀 [1, 1, 2, 4, 8, 16]，转换数组为 [2, 2, 4, 8, 16, 32] ，所以分数为 64 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和**

我们用变量 $mx$ 记录数组 $nums$ 中前 $i$ 个元素的最大值，用数组 $ans[i]$ 记录数组 $nums$ 中前 $i$ 个元素的分数。

接下来，遍历数组 $nums$，对于每个元素 $nums[i]$，我们更新 $mx$，即 $mx = max(mx, nums[i])$，然后更新 $ans[i]$，如果 $i = 0$，则 $ans[i] = nums[i] + mx$，否则 $ans[i] = nums[i] + mx + ans[i - 1]$。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findPrefixScore(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [0] * n
        mx = 0
        for i, x in enumerate(nums):
            mx = max(mx, x)
            ans[i] = x + mx + (0 if i == 0 else ans[i - 1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, nums[i]);
            ans[i] = nums[i] + mx + (i == 0 ? 0 : ans[i - 1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<long long> findPrefixScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> ans(n);
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, nums[i]);
            ans[i] = nums[i] + mx + (i == 0 ? 0 : ans[i - 1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func findPrefixScore(nums []int) []int64 {
	n := len(nums)
	ans := make([]int64, n)
	mx := 0
	for i, x := range nums {
		mx = max(mx, x)
		ans[i] = int64(x + mx)
		if i > 0 {
			ans[i] += ans[i-1]
		}
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

### **TypeScript**

```ts
function findPrefixScore(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = new Array(n);
    let mx: number = 0;
    for (let i = 0; i < n; ++i) {
        mx = Math.max(mx, nums[i]);
        ans[i] = nums[i] + mx + (i === 0 ? 0 : ans[i - 1]);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
