# [484. 寻找排列](https://leetcode.cn/problems/find-permutation)

[English Version](/solution/0400-0499/0484.Find%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>由范围 <code>[1,n]</code> 内所有整数组成的 <code>n</code> 个整数的排列&nbsp;<code>perm</code>&nbsp;可以表示为长度为 <code>n - 1</code> 的字符串 <code>s</code> ，其中:</p>

<ul>
	<li>如果 <code>perm[i] &lt; perm[i + 1]</code> ，那么 <code>s[i] == ' i '</code></li>
	<li>如果&nbsp;<code>perm[i] &gt; perm[i + 1]</code>&nbsp;，那么 <code>s[i] == 'D'</code>&nbsp;。</li>
</ul>

<p>给定一个字符串 <code>s</code> ，重构字典序上最小的排列&nbsp;<code>perm</code>&nbsp;并返回它。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> s = "I"
<strong>输出：</strong> [1,2]
<strong>解释：</strong> [1,2] 是唯一合法的可以生成秘密签名 "I" 的特定串，数字 1 和 2 构成递增关系。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong> s = "DI"
<strong>输出：</strong> [2,1,3]
<strong>解释：</strong> [2,1,3] 和 [3,1,2] 可以生成秘密签名 "DI"，
但是由于我们要找字典序最小的排列，因此你需要输出 [2,1,3]。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;只会包含字符 <code>'D'</code> 和 <code>'I'</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

先初始化结果数组 `ans` 为 `[1, 2, 3, ..., n+1]`。

假定某个连续 `D` 子数组区间为 `[i, j)`，那么只要翻转 `ans[i: j + 1]` 即可。

因此，遍历字符串 `s`，找出所有的连续 `D` 子数组区间，将其翻转。

时间复杂度 $O(n)$，其中 $n$ 表示字符串 `s` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findPermutation(self, s: str) -> List[int]:
        n = len(s)
        ans = list(range(1, n + 2))
        i = 0
        while i < n:
            j = i
            while j < n and s[j] == 'D':
                j += 1
            ans[i: j + 1] = ans[i: j + 1][::-1]
            i = max(i + 1, j)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findPermutation(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            ans[i] = i + 1;
        }
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == 'D') {
                ++j;
            }
            reverse(ans, i, j);
            i = Math.max(i + 1, j);
        }
        return ans;
    }

    private void reverse(int[] arr, int i, int j) {
        for (; i < j; ++i, --j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findPermutation(string s) {
        int n = s.size();
        vector<int> ans(n + 1);
        iota(ans.begin(), ans.end(), 1);
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s[j] == 'D') {
                ++j;
            }
            reverse(ans.begin() + i, ans.begin() + j + 1);
            i = max(i + 1, j);
        }
        return ans;
    }
};
```

### **Go**

```go
func findPermutation(s string) []int {
	n := len(s)
	ans := make([]int, n+1)
	for i := range ans {
		ans[i] = i + 1
	}
	i := 0
	for i < n {
		j := i
		for ; j < n && s[j] == 'D'; j++ {
		}
		reverse(ans, i, j)
		i = max(i+1, j)
	}
	return ans
}

func reverse(arr []int, i, j int) {
	for ; i < j; i, j = i+1, j-1 {
		arr[i], arr[j] = arr[j], arr[i]
	}
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
