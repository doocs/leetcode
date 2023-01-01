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

**方法一：枚举**

枚举数组 `nums` 中的每个元素，将其转换为字符串，判断字符串长度是否为偶数，是则答案加一。

时间复杂度 $O(n \times \log_{10} m)$，空间复杂度 $O(\log_{10} m)$，其中 $n$ 和 $m$ 分别为数组 `nums` 的长度以及数组 `nums` 中的最大元素。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        return sum(len(str(v)) % 2 == 0 for v in nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int v : nums) {
            if (String.valueOf(v).length() % 2 == 0) {
                ++ans;
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
    int findNumbers(vector<int>& nums) {
        int ans = 0;
        for (int& v : nums) {
            ans += to_string(v).size() % 2 == 0;
        }
        return ans;
    }
};
```

### **Go**

```go
func findNumbers(nums []int) (ans int) {
	for _, v := range nums {
		if len(strconv.Itoa(v))%2 == 0 {
			ans++
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findNumbers = function (nums) {
    let ans = 0;
    for (const v of nums) {
        ans += String(v).length % 2 == 0;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
