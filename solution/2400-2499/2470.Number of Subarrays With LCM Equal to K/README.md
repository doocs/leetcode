# [2470. 最小公倍数为 K 的子数组数目](https://leetcode.cn/problems/number-of-subarrays-with-lcm-equal-to-k)

[English Version](/solution/2400-2499/2470.Number%20of%20Subarrays%20With%20LCM%20Equal%20to%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你统计并返回 <code>nums</code> 的 <strong>子数组</strong> 中满足 <em>元素最小公倍数为 <code>k</code> </em>的子数组数目。</p>

<p><strong>子数组</strong> 是数组中一个连续非空的元素序列。</p>

<p><strong>数组的最小公倍数</strong> 是可被所有数组元素整除的最小正整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre><strong>输入：</strong>nums = [3,6,2,7,1], k = 6
<strong>输出：</strong>4
<strong>解释：</strong>以 6 为最小公倍数的子数组是：
- [<em><strong>3</strong></em>,<em><strong>6</strong></em>,2,7,1]
- [<em><strong>3</strong></em>,<em><strong>6</strong></em>,<em><strong>2</strong></em>,7,1]
- [3,<em><strong>6</strong></em>,2,7,1]
- [3,<em><strong>6</strong></em>,<em><strong>2</strong></em>,7,1]
</pre>

<p><strong>示例 2 ：</strong></p>

<pre><strong>输入：</strong>nums = [3], k = 2
<strong>输出：</strong>0
<strong>解释：</strong>不存在以 2 为最小公倍数的子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

枚举每个数作为子数组的第一个数，然后枚举每个数作为子数组的最后一个数，计算这个子数组的最小公倍数，如果最小公倍数等于 $k$，则答案加一。

时间复杂度 $O(n^2)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subarrayLCM(self, nums: List[int], k: int) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            a = nums[i]
            for b in nums[i:]:
                x = lcm(a, b)
                ans += x == k
                a = x
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int a = nums[i];
            for (int j = i; j < n; ++j) {
                int b = nums[j];
                int x = lcm(a, b);
                if (x == k) {
                    ++ans;
                }
                a = x;
            }
        }
        return ans;
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subarrayLCM(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int a = nums[i];
            for (int j = i; j < n; ++j) {
                int b = nums[j];
                int x = lcm(a, b);
                ans += x == k;
                a = x;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func subarrayLCM(nums []int, k int) (ans int) {
	for i, a := range nums {
		for _, b := range nums[i:] {
			x := lcm(a, b)
			if x == k {
				ans++
			}
			a = x
		}
	}
	return
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a, b int) int {
	return a * b / gcd(a, b)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
