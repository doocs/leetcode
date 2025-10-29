---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0760.Find%20Anagram%20Mappings/README.md
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [760. 找出变位映射 🔒](https://leetcode.cn/problems/find-anagram-mappings)

[English Version](/solution/0700-0799/0760.Find%20Anagram%20Mappings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>nums1</code> 和 <code>nums2</code>，其中 <code>nums2</code> 是 <code>nums1</code> 的一个<strong> 变位词 </strong>。两个数组都可能包含重复元素。</p>

<p>返回一个下标映射数组 <code>mapping</code>，它将 <code>nums1</code> 映射到 <code>nums2</code>，其中 <code>mapping[i] = j</code> 表示 <code>nums1</code> 中的第 <code>i</code> 个元素出现在 <code>nums2</code> 的第 <code>j</code> 个下标上。如果有多个答案，返回 <strong>任意一个 </strong>。</p>

<p>数组 <code>a</code> 是数组 <code>b</code> 的一个 <strong>变位词 </strong>意味着 <code>b</code> 是通过将 <code>a</code> 中元素的顺序随机打乱生成的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [12,28,46,32,50], nums2 = [50,12,32,46,28]
<strong>输出：</strong>[1,4,3,2,0]
<strong>解释：</strong>因为 nums1 中的第 0 个元素出现在 nums2[1] 上，所以 mapping[0] = 1，而 nums1 中的第 1 个元素出现在 nums2[4] 上，所以 mapping[1] = 4，以此类推。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [84,46], nums2 = [84,46]
<strong>输出：</strong>[0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 100</code></li>
	<li><code>nums2.length == nums1.length</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2</code> 是 <code>nums1</code> 的一个变位词。</li>
</ul>
<!-- 保持注释以帮助理解题目要求 -->

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{d}$ 来存储数组 $\textit{nums2}$ 中每个元素及其对应的下标。然后我们遍历数组 $\textit{nums1}$，对于每个元素 $\textit{nums1}[i]$，我们从哈希表 $\textit{d}$ 中获取其对应的下标并存入结果数组中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def anagramMappings(self, nums1: List[int], nums2: List[int]) -> List[int]:
        d = {x: i for i, x in enumerate(nums2)}
        return [d[x] for x in nums1]
```

#### Java

```java
class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Integer, Integer> d = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            d.put(nums2[i], i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = d.get(nums1[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> anagramMappings(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) {
            d[nums2[i]] = i;
        }
        vector<int> ans;
        for (int x : nums1) {
            ans.push_back(d[x]);
        }
        return ans;
    }
};
```

#### Go

```go
func anagramMappings(nums1 []int, nums2 []int) []int {
	d := map[int]int{}
	for i, x := range nums2 {
		d[x] = i
	}
	ans := make([]int, len(nums1))
	for i, x := range nums1 {
		ans[i] = d[x]
	}
	return ans
}
```

#### TypeScript

```ts
function anagramMappings(nums1: number[], nums2: number[]): number[] {
    const d: Map<number, number> = new Map();
    for (let i = 0; i < nums2.length; ++i) {
        d.set(nums2[i], i);
    }
    return nums1.map(num => d.get(num)!);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
