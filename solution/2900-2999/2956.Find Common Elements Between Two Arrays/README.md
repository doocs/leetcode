# [2956. 找到两个数组中的公共元素](https://leetcode.cn/problems/find-common-elements-between-two-arrays)

[English Version](/solution/2900-2999/2956.Find%20Common%20Elements%20Between%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，它们分别含有 <code>n</code>&nbsp;和 <code>m</code>&nbsp;个元素。</p>

<p>请你计算以下两个数值：</p>

<ul>
	<li>统计&nbsp;<code>0 &lt;= i &lt; n</code>&nbsp;中的下标&nbsp;<code>i</code>&nbsp;，满足&nbsp;<code>nums1[i]</code>&nbsp;在 <code>nums2</code>&nbsp;中 <strong>至少</strong>&nbsp;出现了一次。</li>
	<li>统计&nbsp;<code>0 &lt;= i &lt; m</code>&nbsp;中的下标&nbsp;<code>i</code>&nbsp;，满足&nbsp;<code>nums2[i]</code>&nbsp;在 <code>nums1</code>&nbsp;中 <strong>至少</strong>&nbsp;出现了一次。</li>
</ul>

<p>请你返回一个长度为 <code>2</code>&nbsp;的整数数组<em>&nbsp;</em><code>answer</code>&nbsp;，<strong>按顺序</strong>&nbsp;分别为以上两个数值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [4,3,2,3,1], nums2 = [2,2,5,2,3,6]
<b>输出：</b>[3,4]
<b>解释：</b>分别计算两个数值：
- nums1 中下标为 1 ，2 和 3 的元素在 nums2 中至少出现了一次，所以第一个值为 3 。
- nums2 中下标为 0 ，1 ，3 和 4 的元素在 nums1 中至少出现了一次，所以第二个值为 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums1 = [3,4,2,3], nums2 = [1,5]
<b>输出：</b>[0,0]
<b>解释：</b>两个数组中没有公共元素，所以两个值都为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>m == nums2.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 100</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：哈希表或数组

我们可以用两个哈希表或数组 $s1$ 和 $s2$ 分别记录两个数组中出现的元素。

接下来，我们创建一个长度为 $2$ 的数组 $ans$，其中 $ans[0]$ 表示 $nums1$ 中出现在 $s2$ 中的元素个数，$ans[1]$ 表示 $nums2$ 中出现在 $s1$ 中的元素个数。

然后，我们遍历数组 $nums1$ 中的每个元素 $x$，如果 $x$ 在 $s2$ 中出现过，则将 $ans[0]$ 加一。接着，我们遍历数组 $nums2$ 中的每个元素 $x$，如果 $x$ 在 $s1$ 中出现过，则将 $ans[1]$ 加一。

最后，我们返回数组 $ans$ 即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是数组 $nums1$ 和 $nums2$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def findIntersectionValues(self, nums1: List[int], nums2: List[int]) -> List[int]:
        s1, s2 = set(nums1), set(nums2)
        return [sum(x in s2 for x in nums1), sum(x in s1 for x in nums2)]
```

```java
class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] s1 = new int[101];
        int[] s2 = new int[101];
        for (int x : nums1) {
            s1[x] = 1;
        }
        for (int x : nums2) {
            s2[x] = 1;
        }
        int[] ans = new int[2];
        for (int x : nums1) {
            ans[0] += s2[x];
        }
        for (int x : nums2) {
            ans[1] += s1[x];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findIntersectionValues(vector<int>& nums1, vector<int>& nums2) {
        int s1[101]{};
        int s2[101]{};
        for (int& x : nums1) {
            s1[x] = 1;
        }
        for (int& x : nums2) {
            s2[x] = 1;
        }
        vector<int> ans(2);
        for (int& x : nums1) {
            ans[0] += s2[x];
        }
        for (int& x : nums2) {
            ans[1] += s1[x];
        }
        return ans;
    }
};
```

```go
func findIntersectionValues(nums1 []int, nums2 []int) []int {
	s1 := [101]int{}
	s2 := [101]int{}
	for _, x := range nums1 {
		s1[x] = 1
	}
	for _, x := range nums2 {
		s2[x] = 1
	}
	ans := make([]int, 2)
	for _, x := range nums1 {
		ans[0] += s2[x]
	}
	for _, x := range nums2 {
		ans[1] += s1[x]
	}
	return ans
}
```

```ts
function findIntersectionValues(nums1: number[], nums2: number[]): number[] {
    const s1: number[] = Array(101).fill(0);
    const s2: number[] = Array(101).fill(0);
    for (const x of nums1) {
        s1[x] = 1;
    }
    for (const x of nums2) {
        s2[x] = 1;
    }
    const ans: number[] = Array(2).fill(0);
    for (const x of nums1) {
        ans[0] += s2[x];
    }
    for (const x of nums2) {
        ans[1] += s1[x];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
