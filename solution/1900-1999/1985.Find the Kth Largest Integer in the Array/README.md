---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1985.Find%20the%20Kth%20Largest%20Integer%20in%20the%20Array/README.md
rating: 1414
source: 第 256 场周赛 Q2
tags:
    - 数组
    - 字符串
    - 分治
    - 快速选择
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [1985. 找出数组中的第 K 大整数](https://leetcode.cn/problems/find-the-kth-largest-integer-in-the-array)

[English Version](/solution/1900-1999/1985.Find%20the%20Kth%20Largest%20Integer%20in%20the%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>nums</code> 和一个整数 <code>k</code> 。<code>nums</code> 中的每个字符串都表示一个不含前导零的整数。</p>

<p>返回 <code>nums</code> 中表示第 <code>k</code> 大整数的字符串。</p>

<p><strong>注意：</strong>重复的数字在统计时会视为不同元素考虑。例如，如果 <code>nums</code> 是 <code>["1","2","2"]</code>，那么 <code>"2"</code> 是最大的整数，<code>"2"</code> 是第二大的整数，<code>"1"</code> 是第三大的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = ["3","6","7","10"], k = 4
<strong>输出：</strong>"3"
<strong>解释：</strong>
nums 中的数字按非递减顺序排列为 ["3","6","7","10"]
其中第 4 大整数是 "3"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = ["2","21","12","1"], k = 3
<strong>输出：</strong>"2"
<strong>解释：</strong>
nums 中的数字按非递减顺序排列为 ["1","2","12","21"]
其中第 3 大整数是 "2"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = ["0","0"], k = 2
<strong>输出：</strong>"0"
<strong>解释：</strong>
nums 中的数字按非递减顺序排列为 ["0","0"]
其中第 2 大整数是 "0"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 100</code></li>
	<li><code>nums[i]</code> 仅由数字组成</li>
	<li><code>nums[i]</code> 不含任何前导零</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序或快速选择

我们可以将 $\textit{nums}$ 数组中的字符串按照整数从大到小排序，然后取第 $k$ 个元素即可。也可以使用快速选择算法，找到第 $k$ 大的整数。

时间复杂度 $O(n \times \log n)$ 或 $O(n)$，其中 $n$ 是 $\textit{nums}$ 数组的长度。空间复杂度 $O(\log n)$ 或 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthLargestNumber(self, nums: List[str], k: int) -> str:
        return nlargest(k, nums, key=lambda x: int(x))[k - 1]
```

#### Java

```java
class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(
            nums, (a, b) -> a.length() == b.length() ? b.compareTo(a) : b.length() - a.length());
        return nums[k - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    string kthLargestNumber(vector<string>& nums, int k) {
        nth_element(nums.begin(), nums.begin() + k - 1, nums.end(), [](const string& a, const string& b) {
            return a.size() == b.size() ? a > b : a.size() > b.size();
        });
        return nums[k - 1];
    }
};
```

#### Go

```go
func kthLargestNumber(nums []string, k int) string {
	sort.Slice(nums, func(i, j int) bool {
		a, b := nums[i], nums[j]
		if len(a) == len(b) {
			return a > b
		}
		return len(a) > len(b)
	})
	return nums[k-1]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
