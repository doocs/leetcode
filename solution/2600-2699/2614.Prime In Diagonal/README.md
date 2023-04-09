# [2614. 对角线上的质数](https://leetcode.cn/problems/prime-in-diagonal)

[English Version](/solution/2600-2699/2614.Prime%20In%20Diagonal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>nums</code> 。</p>

<p>返回位于 <code>nums</code> 至少一条 <strong>对角线</strong> 上的最大 <strong>质数</strong> 。如果任一对角线上均不存在质数，返回<em> 0 。</em></p>

<p>注意：</p>

<ul>
	<li>如果某个整数大于 <code>1</code> ，且不存在除 <code>1</code> 和自身之外的正整数因子，则认为该整数是一个质数。</li>
	<li>如果存在整数 <code>i</code> ，使得&nbsp;<code>nums[i][i] = val</code> 或者&nbsp;<code>nums[i][nums.length - i - 1]= val</code> ，则认为整数 <code>val</code> 位于 <code>nums</code> 的一条对角线上。</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2614.Prime%20In%20Diagonal/images/screenshot-2023-03-06-at-45648-pm.png" style="width: 181px; height: 121px;" /></p>

<p>在上图中，一条对角线是 <strong>[1,5,9]</strong> ，而另一条对角线是<strong> [3,5,7]</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [[1,2,3],[5,6,7],[9,10,11]]
<strong>输出：</strong>11
<strong>解释：</strong>数字 1、3、6、9 和 11 是所有 "位于至少一条对角线上" 的数字。由于 11 是最大的质数，故返回 11 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [[1,2,3],[5,17,7],[9,11,10]]
<strong>输出：</strong>17
<strong>解释：</strong>数字 1、3、9、10 和 17 是所有满足"位于至少一条对角线上"的数字。由于 17 是最大的质数，故返回 17 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 300</code></li>
	<li><code>nums.length == nums<sub>i</sub>.length</code></li>
	<li><code>1 &lt;= nums<span style="">[i][j]</span>&nbsp;&lt;= 4*10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学 + 模拟**

我们实现一个函数 `is_prime`，判断一个数是否为质数。

然后遍历数组，判断对角线上的数是否为质数，如果是，更新答案。

时间复杂度 $O(n \times \sqrt{M})$，其中 $n$ 和 $M$ 分别为数组的行数和数组中的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def diagonalPrime(self, nums: List[List[int]]) -> int:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        n = len(nums)
        ans = 0
        for i, row in enumerate(nums):
            if is_prime(row[i]):
                ans = max(ans, row[i])
            if is_prime(row[n - i - 1]):
                ans = max(ans, row[n - i - 1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (isPrime(nums[i][i])) {
                ans = Math.max(ans, nums[i][i]);
            }
            if (isPrime(nums[i][n - i - 1])) {
                ans = Math.max(ans, nums[i][n - i - 1]);
            }
        }
        return ans;
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i <= x / i; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int diagonalPrime(vector<vector<int>>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (isPrime(nums[i][i])) {
                ans = max(ans, nums[i][i]);
            }
            if (isPrime(nums[i][n - i - 1])) {
                ans = max(ans, nums[i][n - i - 1]);
            }
        }
        return ans;
    }

    bool isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i <= x / i; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func diagonalPrime(nums [][]int) (ans int) {
	n := len(nums)
	for i, row := range nums {
		if isPrime(row[i]) {
			ans = max(ans, row[i])
		}
		if isPrime(row[n-i-1]) {
			ans = max(ans, row[n-i-1])
		}
	}
	return
}

func isPrime(x int) bool {
	if x < 2 {
		return false
	}
	for i := 2; i <= x/i; i++ {
		if x%i == 0 {
			return false
		}
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
