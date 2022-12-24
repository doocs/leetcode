# [923. 三数之和的多种可能](https://leetcode.cn/problems/3sum-with-multiplicity)

[English Version](/solution/0900-0999/0923.3Sum%20With%20Multiplicity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组<meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;，以及一个整数&nbsp;<code>target</code>&nbsp;作为目标值，返回满足 <code>i &lt; j &lt; k</code> 且<meta charset="UTF-8" />&nbsp;<code>arr[i] + arr[j] + arr[k] == target</code>&nbsp;的元组&nbsp;<code>i, j, k</code>&nbsp;的数量。</p>

<p>由于结果会非常大，请返回 <code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;的模。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,2,2,3,3,4,4,5,5], target = 8
<strong>输出：</strong>20
<strong>解释：</strong>
按值枚举(arr[i], arr[j], arr[k])：
(1, 2, 5) 出现 8 次；
(1, 3, 4) 出现 8 次；
(2, 2, 4) 出现 2 次；
(2, 3, 3) 出现 2 次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,2,2,2,2], target = 5
<strong>输出：</strong>12
<strong>解释：</strong>
arr[i] = 1, arr[j] = arr[k] = 2 出现 12 次：
我们从 [1,1] 中选择一个 1，有 2 种情况，
从 [2,2,2,2] 中选出两个 2，有 6 种情况。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 3000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 100</code></li>
	<li><code>0 &lt;= target &lt;= 300</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

我们先用一个长度为 $101$ 的数组 `cnt` 记录数组 `arr` 中每个元素出现的次数。

然后枚举数组 `arr` 中的元素作为三元组的第二个元素 $b$，先让 `cnt` 减去其中一个 $b$。接着从数组 `arr` 的开头开始枚举第一个元素 $a$，那么第三个元素 $c$ 为 $target - a - b$。如果 $c$ 的值在数组 `cnt` 的范围内，那么答案加上 $cnt[c]$。

注意答案的取模操作。

时间复杂度 $O(n^2)$，空间复杂度 $O(C)$。其中 $n$ 为数组 `arr` 的长度，而 $C$ 为数组 `cnt` 的长度。本题中 $C = 101$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def threeSumMulti(self, arr: List[int], target: int) -> int:
        cnt = Counter(arr)
        ans = 0
        mod = 10**9 + 7
        for j, b in enumerate(arr):
            cnt[b] -= 1
            for i in range(j):
                a = arr[i]
                c = target - a - b
                ans = (ans + cnt[c]) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int threeSumMulti(int[] arr, int target) {
        int[] cnt = new int[101];
        for (int v : arr) {
            ++cnt[v];
        }
        long ans = 0;
        for (int j = 0; j < arr.length; ++j) {
            int b = arr[j];
            --cnt[b];
            for (int i = 0; i < j; ++i) {
                int a = arr[i];
                int c = target - a - b;
                if (c >= 0 && c <= 100) {
                    ans = (ans + cnt[c]) % MOD;
                }
            }
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int threeSumMulti(vector<int>& arr, int target) {
        int cnt[101] = {0};
        for (int& v : arr) {
            ++cnt[v];
        }
        long ans = 0;
        for (int j = 0; j < arr.size(); ++j) {
            int b = arr[j];
            --cnt[b];
            for (int i = 0; i < j; ++i) {
                int a = arr[i];
                int c = target - a - b;
                if (c >= 0 && c <= 100) {
                    ans += cnt[c];
                    ans %= mod;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func threeSumMulti(arr []int, target int) int {
	const mod int = 1e9 + 7
	cnt := [101]int{}
	for _, v := range arr {
		cnt[v]++
	}
	ans := 0
	for j, b := range arr {
		cnt[b]--
		for i := 0; i < j; i++ {
			a := arr[i]
			c := target - a - b
			if c >= 0 && c <= 100 {
				ans += cnt[c]
				ans %= mod
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
