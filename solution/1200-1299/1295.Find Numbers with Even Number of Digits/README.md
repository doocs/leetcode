# [1295. 统计位数为偶数的数字](https://leetcode.cn/problems/find-numbers-with-even-number-of-digits)

[English Version](/solution/1200-1299/1295.Find%20Numbers%20with%20Even%20Number%20of%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>，请你返回其中位数为&nbsp;<strong>偶数</strong>&nbsp;的数字的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [12,345,2,6,7896]
<strong>输出：</strong>2
<strong>解释：
</strong>12 是 2 位数字（位数为偶数）&nbsp;
345 是 3 位数字（位数为奇数）&nbsp;&nbsp;
2 是 1 位数字（位数为奇数）&nbsp;
6 是 1 位数字 位数为奇数）&nbsp;
7896 是 4 位数字（位数为偶数）&nbsp;&nbsp;
因此只有 12 和 7896 是位数为偶数的数字
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [555,901,482,1771]
<strong>输出：</strong>1 
<strong>解释： </strong>
只有 1771 是位数为偶数的数字。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

首先将数组元素转换为字符串,判断字符串长度是否为偶数即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        return sum(1 for num in nums if (len(str(num)) & 1) == 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findNumbers(int[] nums) {
        int s = 0;
        for (int num : nums) {
            if ((String.valueOf(num).length() & 1) == 0) {
                ++s;
            }
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findNumbers(vector<int>& nums) {
        int s = 0;
        for (int num : nums) {
            s += (to_string(num).size() & 1) == 0;
        }
        return s;
    }
};
```

### **Go**

```go
func findNumbers(nums []int) int {
	s := 0
	for _, num := range nums {
		if (len(strconv.Itoa(num)) & 1) == 0 {
			s++
		}
	}
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
