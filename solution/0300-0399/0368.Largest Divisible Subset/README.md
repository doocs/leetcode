# [368. 最大整除子集](https://leetcode.cn/problems/largest-divisible-subset)

[English Version](/solution/0300-0399/0368.Largest%20Divisible%20Subset/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个由 <strong>无重复</strong> 正整数组成的集合 <code>nums</code> ，请你找出并返回其中最大的整除子集 <code>answer</code> ，子集中每一元素对 <code>(answer[i], answer[j])</code> 都应当满足：

<ul>
	<li><code>answer[i] % answer[j] == 0</code> ，或</li>
	<li><code>answer[j] % answer[i] == 0</code></li>
</ul>

<p>如果存在多个有效解子集，返回其中任何一个均可。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[1,2]
<strong>解释：</strong>[1,3] 也会被视为正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,8]
<strong>输出：</strong>[1,2,4,8]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>1 <= nums[i] <= 2 * 10<sup>9</sup></code></li>
	<li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 动态规划**

我们先对数组进行排序，这样可以保证对于任意的 $i \lt j$，如果 $nums[i]$ 可以整除 $nums[j]$，那么 $nums[i]$ 一定在 $nums[j]$ 的左边。

接下来，我们定义 $f[i]$ 表示以 $nums[i]$ 为最大元素的最大整除子集的大小，初始时 $f[i]=1$。

对于每一个 $i$，我们从左往右枚举 $j$，如果 $nums[i]$ 可以被 $nums[j]$ 整除，那么 $f[i]$ 可以从 $f[j]$ 转移而来，我们更新 $f[i]=max(f[i], f[j]+1)$。过程中，我们记录 $f[i]$ 的最大值的下标 $k$ 以及对应的子集大小 $m$。

最后，我们从 $k$ 开始倒序遍历，如果 $nums[k]$ 可以被 $nums[i]$ 整除，且 $f[i]=m$，那么 $nums[i]$ 就是一个整除子集的元素，我们将 $nums[i]$ 加入答案，并将 $m$ 减 $1$，同时更新 $k=i$。继续倒序遍历，直到 $m=0$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        f = [1] * n
        k = 0
        for i in range(n):
            for j in range(i):
                if nums[i] % nums[j] == 0:
                    f[i] = max(f[i], f[j] + 1)
            if f[k] < f[i]:
                k = i
        m = f[k]
        i = k
        ans = []
        while m:
            if nums[k] % nums[i] == 0 and f[i] == m:
                ans.append(nums[i])
                k, m = i, m - 1
            i -= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int k = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] % nums[j] == 0) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            if (f[k] < f[i]) {
                k = i;
            }
        }
        int m = f[k];
        List<Integer> ans = new ArrayList<>();
        for (int i = k; m > 0; --i) {
            if (nums[k] % nums[i] == 0 && f[i] == m) {
                ans.add(nums[i]);
                k = i;
                --m;
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
    vector<int> largestDivisibleSubset(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int f[n];
        int k = 0;
        for (int i = 0; i < n; ++i) {
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] % nums[j] == 0) {
                    f[i] = max(f[i], f[j] + 1);
                }
            }
            if (f[k] < f[i]) {
                k = i;
            }
        }
        int m = f[k];
        vector<int> ans;
        for (int i = k; m > 0; --i) {
            if (nums[k] % nums[i] == 0 && f[i] == m) {
                ans.push_back(nums[i]);
                k = i;
                --m;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestDivisibleSubset(nums []int) (ans []int) {
	sort.Ints(nums)
	n := len(nums)
	f := make([]int, n)
	k := 0
	for i := 0; i < n; i++ {
		f[i] = 1
		for j := 0; j < i; j++ {
			if nums[i]%nums[j] == 0 {
				f[i] = max(f[i], f[j]+1)
			}
		}
		if f[k] < f[i] {
			k = i
		}
	}
	m := f[k]
	for i := k; m > 0; i-- {
		if nums[k]%nums[i] == 0 && f[i] == m {
			ans = append(ans, nums[i])
			k = i
			m--
		}
	}
	return
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
