# [1979. 找出数组的最大公约数](https://leetcode.cn/problems/find-greatest-common-divisor-of-array)

[English Version](/solution/1900-1999/1979.Find%20Greatest%20Common%20Divisor%20of%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，返回数组中最大数和最小数的 <strong>最大公约数</strong> 。</p>

<p>两个数的&nbsp;<strong>最大公约数</strong> 是能够被两个数整除的最大正整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,5,6,9,10]
<strong>输出：</strong>2
<strong>解释：</strong>
nums 中最小的数是 2
nums 中最大的数是 10
2 和 10 的最大公约数是 2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [7,5,6,8,3]
<strong>输出：</strong>1
<strong>解释：</strong>
nums 中最小的数是 3
nums 中最大的数是 8
3 和 8 的最大公约数是 1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [3,3]
<strong>输出：</strong>3
<strong>解释：</strong>
nums 中最小的数是 3
nums 中最大的数是 3
3 和 3 的最大公约数是 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意模拟即可，即先找出数组 `nums` 中的最大值和最小值，然后求最大值和最小值的最大公约数。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findGCD(self, nums: List[int]) -> int:
        return gcd(max(nums), min(nums))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findGCD(int[] nums) {
        int a = 1, b = 1000;
        for (int x : nums) {
            a = Math.max(a, x);
            b = Math.min(b, x);
        }
        return gcd(a, b);
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
    int findGCD(vector<int>& nums) {
        int a = *max_element(nums.begin(), nums.end());
        int b = *min_element(nums.begin(), nums.end());
        return gcd(a, b);
    }
};
```

### **Go**

```go
func findGCD(nums []int) int {
	a, b := 1, 1000
	for _, x := range nums {
		if a < x {
			a = x
		}
		if b > x {
			b = x
		}
	}
	return gcd(a, b)
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
function findGCD(nums: number[]): number {
    let a = 1;
    let b = 1000;
    for (const x of nums) {
        a = Math.max(a, x);
        b = Math.min(b, x);
    }
    return gcd(a, b);
}

function gcd(a: number, b: number): number {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

### **...**

```

```

<!-- tabs:end -->
