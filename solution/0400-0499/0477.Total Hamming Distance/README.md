# [477. 汉明距离总和](https://leetcode.cn/problems/total-hamming-distance)

[English Version](/solution/0400-0499/0477.Total%20Hamming%20Distance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>两个整数的&nbsp;<a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E8%B7%9D%E7%A6%BB/475174?fr=aladdin">汉明距离</a> 指的是这两个数字的二进制数对应位不同的数量。</p>

<p>给你一个整数数组 <code>nums</code>，请你计算并返回 <code>nums</code> 中任意两个数之间 <strong>汉明距离的总和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,14,2]
<strong>输出：</strong>6
<strong>解释：</strong>在二进制表示中，4 表示为 0100 ，14 表示为 1110 ，2表示为 0010 。（这样表示是为了体现后四位之间关系）
所以答案为：
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,14,4]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>给定输入的对应答案符合 <strong>32-bit</strong> 整数范围</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def totalHammingDistance(self, nums: List[int]) -> int:
        ans = 0
        for i in range(31):
            a = b = 0
            for v in nums:
                t = (v >> i) & 1
                if t:
                    a += 1
                else:
                    b += 1
            ans += a * b
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 31; ++i) {
            int a = 0, b = 0;
            for (int v : nums) {
                int t = (v >> i) & 1;
                a += t;
                b += t ^ 1;
            }
            ans += a * b;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int totalHammingDistance(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < 31; ++i) {
            int a = 0, b = 0;
            for (int& v : nums) {
                int t = (v >> i) & 1;
                a += t;
                b += t ^ 1;
            }
            ans += a * b;
        }
        return ans;
    }
};
```

### **Go**

```go
func totalHammingDistance(nums []int) int {
	ans := 0
	for i := 0; i < 31; i++ {
		a, b := 0, 0
		for _, v := range nums {
			t := (v >> i) & 1
			a += t
			b += t ^ 1
		}
		ans += a * b
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
