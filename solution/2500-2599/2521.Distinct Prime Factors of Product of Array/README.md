# [2521. 数组乘积中的不同质因数数目](https://leetcode.cn/problems/distinct-prime-factors-of-product-of-array)

[English Version](/solution/2500-2599/2521.Distinct%20Prime%20Factors%20of%20Product%20of%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>nums</code> ，对 <code>nums</code> 所有元素求积之后，找出并返回乘积中 <strong>不同质因数</strong> 的数目。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><strong>质数</strong> 是指大于 <code>1</code> 且仅能被 <code>1</code> 及自身整除的数字。</li>
	<li>如果 <code>val2 / val1</code> 是一个整数，则整数 <code>val1</code> 是另一个整数 <code>val2</code> 的一个因数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,4,3,7,10,6]
<strong>输出：</strong>4
<strong>解释：</strong>
nums 中所有元素的乘积是：2 * 4 * 3 * 7 * 10 * 6 = 10080 = 2<sup>5</sup> * 3<sup>2</sup> * 5 * 7 。
共有 4 个不同的质因数，所以返回 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [2,4,8,16]
<strong>输出：</strong>1
<strong>解释：</strong>
nums 中所有元素的乘积是：2 * 4 * 8 * 16 = 1024 = 2<sup>10</sup> 。
共有 1 个不同的质因数，所以返回 1 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>2 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 质因数分解**

对于数组中的每个元素，先对其进行质因数分解，然后将分解出的质因数加入哈希表中。最后返回哈希表的大小即可。

时间复杂度 $O(n\sqrt{m})$，其中 $n$ 和 $m$ 分别是数组的长度和数组中元素的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distinctPrimeFactors(self, nums: List[int]) -> int:
        s = set()
        for n in nums:
            i = 2
            while i <= n // i:
                if n % i == 0:
                    s.add(i)
                    while n % i == 0:
                        n //= i
                i += 1
            if n > 1:
                s.add(n)
        return len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int n : nums) {
            for (int i = 2; i <= n / i; ++i) {
                if (n % i == 0) {
                    s.add(i);
                    while (n % i == 0) {
                        n /= i;
                    }
                }
            }
            if (n > 1) {
                s.add(n);
            }
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distinctPrimeFactors(vector<int>& nums) {
        unordered_set<int> s;
        for (int& n :nums) {
            for (int i = 2; i <= n / i; ++i) {
                if (n % i == 0) {
                    s.insert(i);
                    while (n % i == 0) {
                        n /= i;
                    }
                }
            }
            if (n > 1) {
                s.insert(n);
            }
        }
        return s.size();
    }
};
```

### **Go**

```go
func distinctPrimeFactors(nums []int) int {
	s := map[int]bool{}
	for _, n := range nums {
		for i := 2; i <= n/i; i++ {
			if n%i == 0 {
				s[i] = true
				for n%i == 0 {
					n /= i
				}
			}
		}
		if n > 1 {
			s[n] = true
		}
	}
	return len(s)
}
```

### **...**

```

```

<!-- tabs:end -->
