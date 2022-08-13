# [1588. 所有奇数长度子数组的和](https://leetcode.cn/problems/sum-of-all-odd-length-subarrays)

[English Version](/solution/1500-1599/1588.Sum%20of%20All%20Odd%20Length%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组&nbsp;<code>arr</code>&nbsp;，请你计算所有可能的奇数长度子数组的和。</p>

<p><strong>子数组</strong> 定义为原数组中的一个连续子序列。</p>

<p>请你返回 <code>arr</code>&nbsp;中 <strong>所有奇数长度子数组的和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,4,2,5,3]
<strong>输出：</strong>58
<strong>解释：</strong>所有奇数长度子数组和它们的和为：
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2]
<strong>输出：</strong>3
<strong>解释：</strong>总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [10,11,12]
<strong>输出：</strong>66
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<p>你可以设计一个 O(n) 时间复杂度的算法解决此问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“前缀和”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        n = len(arr)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + arr[i]

        res = 0
        for i in range(n):
            for j in range(0, n, 2):
                if i + j < n:
                    res += presum[i + j + 1] - presum[i]
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + arr[i];
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; i + j < n; j += 2) {
                res += presum[i + j + 1] - presum[i];
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOddLengthSubarrays(vector<int>& arr) {
        int n = arr.size();
        int presum[n + 1];
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + arr[i];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; i + j < n; j += 2) {
                res += presum[i + j + 1] - presum[i];
            }
        }
        return res;
    }
};
```

### **Go**

```go
func sumOddLengthSubarrays(arr []int) int {
	n := len(arr)
	presum := make([]int, n+1)
	for i := range arr {
		presum[i+1] = presum[i] + arr[i]
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := 0; i+j < n; j += 2 {
			res += presum[i+j+1] - presum[i]
		}
	}
	return res
}
```

### **TypeScript**

```ts
function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    let res = 0;
    for (let i = 1; i <= n; i += 2) {
        let sum = 0;
        for (let j = 0; j < i; j++) {
            sum += arr[j];
        }
        res += sum;
        for (let j = i; j < n; j++) {
            sum -= arr[j - i];
            sum += arr[j];
            res += sum;
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut res = 0;
        let mut i = 1;
        while i <= n {
            let mut sum: i32 = arr[0..i].iter().sum();
            res += sum;
            for j in i..n {
                sum -= arr[j - i];
                sum += arr[j];
                res += sum;
            }
            i += 2;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
