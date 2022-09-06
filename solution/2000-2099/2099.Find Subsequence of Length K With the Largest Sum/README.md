# [2099. 找到和最大的长度为 K 的子序列](https://leetcode.cn/problems/find-subsequence-of-length-k-with-the-largest-sum)

[English Version](/solution/2000-2099/2099.Find%20Subsequence%20of%20Length%20K%20With%20the%20Largest%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。你需要找到&nbsp;<code>nums</code>&nbsp;中长度为 <code>k</code>&nbsp;的 <strong>子序列</strong>&nbsp;，且这个子序列的&nbsp;<strong>和最大&nbsp;</strong>。</p>

<p>请你返回 <strong>任意</strong> 一个长度为&nbsp;<code>k</code>&nbsp;的整数子序列。</p>

<p><strong>子序列</strong>&nbsp;定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,1,3,3], k = 2
<b>输出：</b>[3,3]
<strong>解释：</strong>
子序列有最大和：3 + 3 = 6 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [-1,-2,3,4], k = 3
<b>输出：</b>[-1,3,4]
<b>解释：</b>
子序列有最大和：-1 + 3 + 4 = 6 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [3,4,3,3], k = 2
<b>输出：</b>[3,4]
<strong>解释：</strong>
子序列有最大和：3 + 4 = 7 。
另一个可行的子序列为 [4, 3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

两次排序。

先按照数字大小**对下标进行排序**，然后取出倒数 k 个下标（对应的数字是数组中前 k 个数字），对下标进行排序。最后将排序后的下标依次映射成数字，得到结果数组。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSubsequence(self, nums: List[int], k: int) -> List[int]:
        idx = list(range(len(nums)))
        idx.sort(key=lambda i: nums[i])
        return [nums[i] for i in sorted(idx[-k:])]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int[] ans = new int[k];
        List<Integer> idx = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            idx.add(i);
        }
        idx.sort(Comparator.comparingInt(i -> - nums[i]));
        int[] t = new int[k];
        for (int i = 0; i < k; ++i) {
            t[i] = idx.get(i);
        }
        Arrays.sort(t);
        for (int i = 0; i < k; ++i) {
            ans[i] = nums[t[i]];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> maxSubsequence(vector<int>& nums, int k) {
        int n = nums.size();
        vector<pair<int, int>> vals;
        for (int i = 0; i < n; ++i) vals.push_back({i, nums[i]});
        sort(vals.begin(), vals.end(), [&](auto x1, auto x2) {
            return x1.second > x2.second;
        });
        sort(vals.begin(), vals.begin() + k);
        vector<int> ans;
        for (int i = 0; i < k; ++i) ans.push_back(vals[i].second);
        return ans;
    }
};
```

### **Go**

```go
func maxSubsequence(nums []int, k int) []int {
	idx := make([]int, len(nums))
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return nums[idx[i]] > nums[idx[j]] })
	sort.Ints(idx[:k])
	ans := make([]int, k)
	for i, j := range idx[:k] {
		ans[i] = nums[j]
	}
	return ans
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
