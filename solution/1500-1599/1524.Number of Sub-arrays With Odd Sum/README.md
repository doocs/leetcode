# [1524. 和为奇数的子数组数目](https://leetcode.cn/problems/number-of-sub-arrays-with-odd-sum)

[English Version](/solution/1500-1599/1524.Number%20of%20Sub-arrays%20With%20Odd%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>&nbsp;。请你返回和为 <strong>奇数</strong>&nbsp;的子数组数目。</p>

<p>由于答案可能会很大，请你将结果对&nbsp;<code>10^9 + 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,3,5]
<strong>输出：</strong>4
<strong>解释：</strong>所有的子数组为 [[1],[1,3],[1,3,5],[3],[3,5],[5]] 。
所有子数组的和为 [1,4,9,3,8,5].
奇数和包括 [1,9,3,5] ，所以答案为 4 。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre><strong>输入：</strong>arr = [2,4,6]
<strong>输出：</strong>0
<strong>解释：</strong>所有子数组为 [[2],[2,4],[2,4,6],[4],[4,6],[6]] 。
所有子数组和为 [2,6,12,4,10,6] 。
所有子数组和都是偶数，所以答案为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3,4,5,6,7]
<strong>输出：</strong>16
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [100,100,99,99]
<strong>输出：</strong>4
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [7]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
</ul>

## 解法

前缀和 + 计数器。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numOfSubarrays(self, arr: List[int]) -> int:
        MOD = int(1e9) + 7
        counter = [0] * 2
        s = ans = 0
        for v in arr:
            s += v
            counter[s % 2] += 1
            if s % 2 == 1:
                ans += 1 + counter[0]
            else:
                ans += counter[1]
        return ans % MOD
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numOfSubarrays(int[] arr) {
        int[] counter = new int[2];
        int s = 0, ans = 0;
        for (int v : arr) {
            s += v;
            ++counter[s % 2];
            if (s % 2 == 1) {
                ans = (ans + 1 + counter[0]) % MOD;
            } else {
                ans = (ans + counter[1]) % MOD;
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
    int numOfSubarrays(vector<int>& arr) {
        const int MOD = 1e9 + 7;
        vector<int> counter(2);
        int s = 0, ans = 0;
        for (int& v : arr) {
            s += v;
            ++counter[s % 2];
            if (s % 2 == 1)
                ans = (ans + 1 + counter[0]) % MOD;
            else
                ans = (ans + counter[1]) % MOD;
        }
        return ans;
    }
};
```

### **Go**

```go
func numOfSubarrays(arr []int) int {
	const MOD = 1e9 + 7
	counter := make([]int, 2)
	s, ans := 0, 0
	for _, v := range arr {
		s += v
		counter[s%2]++
		if s%2 == 1 {
			ans = (ans + 1 + counter[0]) % MOD
		} else {
			ans = (ans + counter[1]) % MOD
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
