# [2420. 找到所有好下标](https://leetcode.cn/problems/find-all-good-indices)

[English Version](/solution/2400-2499/2420.Find%20All%20Good%20Indices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个正整数&nbsp;<code>k</code>&nbsp;。</p>

<p>对于&nbsp;<code>k &lt;= i &lt; n - k</code>&nbsp;之间的一个下标&nbsp;<code>i</code>&nbsp;，如果它满足以下条件，我们就称它为一个&nbsp;<strong>好</strong>&nbsp;下标：</p>

<ul>
	<li>下标 <code>i</code> <strong>之前</strong> 的 <code>k</code>&nbsp;个元素是 <strong>非递增的</strong>&nbsp;。</li>
	<li>下标 <code>i</code> <strong>之后</strong>&nbsp;的 <code>k</code>&nbsp;个元素是 <strong>非递减的</strong>&nbsp;。</li>
</ul>

<p>按 <strong>升序</strong>&nbsp;返回所有好下标。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,1,1,3,4,1], k = 2
<b>输出：</b>[2,3]
<b>解释：</b>数组中有两个好下标：
- 下标 2 。子数组 [2,1] 是非递增的，子数组 [1,3] 是非递减的。
- 下标 3 。子数组 [1,1] 是非递增的，子数组 [3,4] 是非递减的。
注意，下标 4 不是好下标，因为 [4,1] 不是非递减的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,1,2], k = 2
<b>输出：</b>[]
<b>解释：</b>数组中没有好下标。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n / 2</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递推**

定义两个数组 `decr` 和 `incr`，分别表示从左到右和从右到左的非递增和非递减的最长子数组长度。

遍历数组，更新 `decr` 和 `incr` 数组。

然后顺序遍历下标 $i$（其中 $k\le i \lt n - k$），若 `decr[i] >= k && incr[i] >= k`，则 `i` 为好下标。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def goodIndices(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        decr = [1] * (n + 1)
        incr = [1] * (n + 1)
        for i in range(2, n - 1):
            if nums[i - 1] <= nums[i - 2]:
                decr[i] = decr[i - 1] + 1
        for i in range(n - 3, -1, -1):
            if nums[i + 1] <= nums[i + 2]:
                incr[i] = incr[i + 1] + 1
        return [i for i in range(k, n - k) if decr[i] >= k and incr[i] >= k]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        int[] decr = new int[n];
        int[] incr = new int[n];
        Arrays.fill(decr, 1);
        Arrays.fill(incr, 1);
        for (int i = 2; i < n - 1; ++i) {
            if (nums[i - 1] <= nums[i - 2]) {
                decr[i] = decr[i - 1] + 1;
            }
        }
        for (int i = n - 3; i >= 0; --i) {
            if (nums[i + 1] <= nums[i + 2]) {
                incr[i] = incr[i + 1] + 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = k; i < n - k; ++i) {
            if (decr[i] >= k && incr[i] >= k) {
                ans.add(i);
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
    vector<int> goodIndices(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> decr(n, 1);
        vector<int> incr(n, 1);
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] <= nums[i - 2]) {
                decr[i] = decr[i - 1] + 1;
            }
        }
        for (int i = n - 3; ~i; --i) {
            if (nums[i + 1] <= nums[i + 2]) {
                incr[i] = incr[i + 1] + 1;
            }
        }
        vector<int> ans;
        for (int i = k; i < n - k; ++i) {
            if (decr[i] >= k && incr[i] >= k) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func goodIndices(nums []int, k int) []int {
	n := len(nums)
	decr := make([]int, n)
	incr := make([]int, n)
	for i := range decr {
		decr[i] = 1
		incr[i] = 1
	}
	for i := 2; i < n; i++ {
		if nums[i-1] <= nums[i-2] {
			decr[i] = decr[i-1] + 1
		}
	}
	for i := n - 3; i >= 0; i-- {
		if nums[i+1] <= nums[i+2] {
			incr[i] = incr[i+1] + 1
		}
	}
	ans := []int{}
	for i := k; i < n-k; i++ {
		if decr[i] >= k && incr[i] >= k {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
