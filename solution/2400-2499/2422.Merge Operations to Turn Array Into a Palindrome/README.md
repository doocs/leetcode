---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2422.Merge%20Operations%20to%20Turn%20Array%20Into%20a%20Palindrome/README.md
tags:
    - 贪心
    - 数组
    - 双指针
---

# [2422. 使用合并操作将数组转换为回文序列 🔒](https://leetcode.cn/problems/merge-operations-to-turn-array-into-a-palindrome)

[English Version](/solution/2400-2499/2422.Merge%20Operations%20to%20Turn%20Array%20Into%20a%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个由&nbsp;<strong>正整数&nbsp;</strong>组成的数组 <code>nums</code>。</p>

<p>可以对阵列执行如下操作，<strong>次数不限</strong>:</p>

<ul>
	<li>选择任意两个&nbsp;<strong>相邻&nbsp;</strong>的元素并用它们的&nbsp;<strong>和</strong>&nbsp;<strong>替换&nbsp;</strong>它们。

    <ul>
    	<li>例如，如果 <code>nums = [1,<u>2,3</u>,1]</code>，则可以应用一个操作使其变为 <code>[1,5,1]</code>。</li>
    </ul>
    </li>

</ul>

<p>返回<em>将数组转换为&nbsp;<strong>回文序列&nbsp;</strong>所需的&nbsp;<strong>最小&nbsp;</strong>操作数。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,3,2,1,2,3,1]
<strong>输出:</strong> 2
<strong>解释:</strong> 我们可以通过以下 2 个操作将数组转换为回文:
- 在数组的第 4 和第 5 个元素上应用该操作，nums 将等于 [4,3,2,<strong><u>3</u></strong>,3,1].
- 在数组的第 5 和第 6 个元素上应用该操作，nums 将等于 [4,3,2,3,<strong><u>4</u></strong>].
数组 [4,3,2,3,4] 是一个回文序列。
可以证明，2 是所需的最小操作数。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4]
<strong>输出:</strong> 3
<strong>解释:</strong> 我们在任意位置进行 3 次运算，最后得到数组 [10]，它是一个回文序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：贪心 + 双指针

定义两个指针 $i$ 和 $j$，分别指向数组的首尾，用变量 $a$ 和 $b$ 分别表示首尾两个元素的值，变量 $ans$ 表示操作次数。

如果 $a \lt b$，我们将指针 $i$ 向右移动一位，即 $i \leftarrow i + 1$，然后将 $a$ 加上指针 $i$ 指向的元素的值，即 $a \leftarrow a + nums[i]$，同时将操作次数加一，即 $ans \leftarrow ans + 1$。

如果 $a \gt b$，我们将指针 $j$ 向左移动一位，即 $j \leftarrow j - 1$，然后将 $b$ 加上指针 $j$ 指向的元素的值，即 $b \leftarrow b + nums[j]$，同时将操作次数加一，即 $ans \leftarrow ans + 1$。

否则，说明 $a = b$，此时我们将指针 $i$ 向右移动一位，即 $i \leftarrow i + 1$，将指针 $j$ 向左移动一位，即 $j \leftarrow j - 1$，并且更新 $a$ 和 $b$ 的值，即 $a \leftarrow nums[i]$ 以及 $b \leftarrow nums[j]$。

循环上述过程，直至指针 $i \ge j$，返回操作次数 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        i, j = 0, len(nums) - 1
        a, b = nums[i], nums[j]
        ans = 0
        while i < j:
            if a < b:
                i += 1
                a += nums[i]
                ans += 1
            elif b < a:
                j -= 1
                b += nums[j]
                ans += 1
            else:
                i, j = i + 1, j - 1
                a, b = nums[i], nums[j]
        return ans
```

```java
class Solution {
    public int minimumOperations(int[] nums) {
        int i = 0, j = nums.length - 1;
        long a = nums[i], b = nums[j];
        int ans = 0;
        while (i < j) {
            if (a < b) {
                a += nums[++i];
                ++ans;
            } else if (b < a) {
                b += nums[--j];
                ++ans;
            } else {
                a = nums[++i];
                b = nums[--j];
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        int i = 0, j = nums.size() - 1;
        long a = nums[i], b = nums[j];
        int ans = 0;
        while (i < j) {
            if (a < b) {
                a += nums[++i];
                ++ans;
            } else if (b < a) {
                b += nums[--j];
                ++ans;
            } else {
                a = nums[++i];
                b = nums[--j];
            }
        }
        return ans;
    }
};
```

```go
func minimumOperations(nums []int) int {
	i, j := 0, len(nums)-1
	a, b := nums[i], nums[j]
	ans := 0
	for i < j {
		if a < b {
			i++
			a += nums[i]
			ans++
		} else if b < a {
			j--
			b += nums[j]
			ans++
		} else {
			i, j = i+1, j-1
			a, b = nums[i], nums[j]
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
