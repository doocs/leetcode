# [2447. 最大公因数等于 K 的子数组数目](https://leetcode.cn/problems/number-of-subarrays-with-gcd-equal-to-k)

[English Version](/solution/2400-2499/2447.Number%20of%20Subarrays%20With%20GCD%20Equal%20to%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code> ，请你统计并返回 <code>nums</code>&nbsp;的子数组中元素的最大公因数等于 <code>k</code>&nbsp;的子数组数目。</p>

<p><strong>子数组</strong> 是数组中一个连续的非空序列。</p>

<p><strong>数组的最大公因数</strong>&nbsp;是能整除数组中所有元素的最大整数。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre><b>输入：</b>nums = [9,3,1,2,6,3], k = 3
<b>输出：</b>4
<b>解释：</b>nums 的子数组中，以 3 作为最大公因数的子数组如下：
- [9,<strong><em>3</em></strong>,1,2,6,3]
- [9,3,1,2,6,<em><strong>3</strong></em>]
- [<strong><em>9,3</em></strong>,1,2,6,3]
- [9,3,1,2,<em><strong>6,3</strong></em>]
</pre>

<p><b>示例 2：</b></p>

<pre><b>输入：</b>nums = [4], k = 7
<b>输出：</b>0
<b>解释：</b>不存在以 7 作为最大公因数的子数组。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接枚举**

对于每个子数组，我们可以枚举其左右端点，计算出其最大公因数，然后判断是否等于 $k$ 即可。

时间复杂度 $O(n^2\times log M)$，其中 $n$ 和 $M$ 分别是数组 `nums` 的长度和数组 `nums` 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subarrayGCD(self, nums: List[int], k: int) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            x = nums[i]
            for j in range(i, n):
                x = gcd(x, nums[j])
                if x == k:
                    ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = i; j < n; ++j) {
                x = gcd(x, nums[j]);
                if (x == k) {
                    ++ans;
                }
            }
        }
        return ans;
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
    int subarrayGCD(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = i; j < n; ++j) {
                x = __gcd(x, nums[j]);
                ans += x == k;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func subarrayGCD(nums []int, k int) int {
	ans, n := 0, len(nums)
	for i, x := range nums {
		for j := i; j < n; j++ {
			x = gcd(x, nums[j])
			if x == k {
				ans++
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts
function subarrayGCD(nums: number[], k: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        let x = nums[i];
        for (let j = i; j < n; j++) {
            x = gcd(nums[j], x);
            if (x == k) ans += 1;
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    if (a > b) [a, b] = [b, a];
    if (a == 0) return b;
    return gcd(b % a, a);
}
```

### **...**

```

```

<!-- tabs:end -->
