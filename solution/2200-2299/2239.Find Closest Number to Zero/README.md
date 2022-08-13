# [2239. 找到最接近 0 的数字](https://leetcode.cn/problems/find-closest-number-to-zero)

[English Version](/solution/2200-2299/2239.Find%20Closest%20Number%20to%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，请你返回 <code>nums</code>&nbsp;中最 <strong>接近</strong>&nbsp;<code>0</code>&nbsp;的数字。如果有多个答案，请你返回它们中的 <strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [-4,-2,1,4,8]
<b>输出：</b>1
<strong>解释：</strong>
-4 到 0 的距离为 |-4| = 4 。
-2 到 0 的距离为 |-2| = 2 。
1 到 0 的距离为 |1| = 1 。
4 到 0 的距离为 |4| = 4 。
8 到 0 的距离为 |8| = 8 。
所以，数组中距离 0 最近的数字为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,-1,1]
<b>输出：</b>1
<b>解释：</b>1 和 -1 都是距离 0 最近的数字，所以返回较大值 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findClosestNumber(self, nums: List[int]) -> int:
        ans, d = 0, 1000000
        for v in nums:
            if (t := abs(v)) < d or (t == d and v > ans):
                ans, d = v, t
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findClosestNumber(int[] nums) {
        int ans = 0, d = 1000000;
        for (int v : nums) {
            int t = Math.abs(v);
            if (t < d || (t == d && v > ans)) {
                ans = v;
                d = t;
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
    int findClosestNumber(vector<int>& nums) {
        int ans = 0, d = 1e6;
        for (int& v : nums) {
            int t = abs(v);
            if (t < d || (t == d && v > ans)) {
                ans = v;
                d = t;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findClosestNumber(nums []int) int {
	ans, d := 0, 1000000
	for _, v := range nums {
		t := abs(v)
		if t < d || (t == d && v > ans) {
			ans, d = v, t
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
