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

**方法一：枚举 + 前缀和**

我们可以枚举子数组的起点 $i$ 和终点 $j$，其中 $i \leq j$，维护每个子数组的和，然后判断子数组的长度是否为奇数，如果是，则将子数组的和加入答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        ans, n = 0, len(arr)
        for i in range(n):
            s = 0
            for j in range(i, n):
                s += arr[j]
                if (j - i + 1) & 1:
                    ans += s
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += arr[j];
                if ((j - i + 1) % 2 == 1) {
                    ans += s;
                }
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
    int sumOddLengthSubarrays(vector<int>& arr) {
        int n = arr.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += arr[j];
                if ((j - i + 1) & 1) {
                    ans += s;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func sumOddLengthSubarrays(arr []int) (ans int) {
	n := len(arr)
	for i := range arr {
		s := 0
		for j := i; j < n; j++ {
			s += arr[j]
			if (j-i+1)%2 == 1 {
				ans += s
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let s = 0;
        for (let j = i; j < n; ++j) {
            s += arr[j];
            if ((j - i + 1) % 2 === 1) {
                ans += s;
            }
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut ans = 0;
        for i in 0..n {
            let mut s = 0;
            for j in i..n {
                s += arr[j];
                if (j - i + 1) % 2 == 1 {
                    ans += s;
                }
            }
        }
        ans
    }
}
```

### **C**

```c
int sumOddLengthSubarrays(int* arr, int arrSize){
    int ans = 0;
    for (int i = 0; i < arrSize; ++i) {
        int s = 0;
        for (int j = i; j < arrSize; ++j) {
            s += arr[j];
            if ((j - i + 1) % 2 == 1) {
                ans += s;
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
