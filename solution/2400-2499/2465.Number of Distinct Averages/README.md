# [2465. 不同的平均值数目](https://leetcode.cn/problems/number-of-distinct-averages)

[English Version](/solution/2400-2499/2465.Number%20of%20Distinct%20Averages/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <strong>偶数</strong>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>只要&nbsp;<code>nums</code> <strong>不是</strong>&nbsp;空数组，你就重复执行以下步骤：</p>

<ul>
	<li>找到&nbsp;<code>nums</code>&nbsp;中的最小值，并删除它。</li>
	<li>找到&nbsp;<code>nums</code>&nbsp;中的最大值，并删除它。</li>
	<li>计算删除两数的平均值。</li>
</ul>

<p>两数 <code>a</code>&nbsp;和 <code>b</code>&nbsp;的 <strong>平均值</strong>&nbsp;为&nbsp;<code>(a + b) / 2</code>&nbsp;。</p>

<ul>
	<li>比方说，<code>2</code>&nbsp;和&nbsp;<code>3</code>&nbsp;的平均值是&nbsp;<code>(2 + 3) / 2 = 2.5</code>&nbsp;。</li>
</ul>

<p>返回上述过程能得到的 <strong>不同</strong>&nbsp;平均值的数目。</p>

<p><strong>注意</strong>&nbsp;，如果最小值或者最大值有重复元素，可以删除任意一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [4,1,4,0,3,5]
<b>输出：</b>2
<strong>解释：</strong>
1. 删除 0 和 5 ，平均值是 (0 + 5) / 2 = 2.5 ，现在 nums = [4,1,4,3] 。
2. 删除 1 和 4 ，平均值是 (1 + 4) / 2 = 2.5 ，现在 nums = [4,3] 。
3. 删除 3 和 4 ，平均值是 (3 + 4) / 2 = 3.5 。
2.5 ，2.5 和 3.5 之中总共有 2 个不同的数，我们返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,100]
<b>输出：</b>1
<strong>解释：</strong>
删除 1 和 100 后只有一个平均值，所以我们返回 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length</code>&nbsp;是偶数。</li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 哈希表**

我们先对数组进行排序，然后遍历数组，将 $nums[i]$ 与 $nums[n-i-1]$ 求和后放入哈希表中，最后返回哈希表的大小即可。

时间复杂度 $O(n\times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distinctAverages(self, nums: List[int]) -> int:
        n = len(nums)
        nums.sort()
        return len(set(nums[i] + nums[n - i - 1] for i in range(n >> 1)))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i<n> > 1; ++i) {
            s.add(nums[i] + nums[n - i - 1]);
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        unordered_set<int> s;
        for (int i = 0; i < n >> 1; ++i) s.insert(nums[i] + nums[n - i - 1]);
        return s.size();
    }
};
```

### **Go**

```go
func distinctAverages(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	s := map[int]struct{}{}
	for i := 0; i < n>>1; i++ {
		s[nums[i]+nums[n-i-1]] = struct{}{}
	}
	return len(s)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
