# [667. 优美的排列 II](https://leetcode.cn/problems/beautiful-arrangement-ii)

[English Version](/solution/0600-0699/0667.Beautiful%20Arrangement%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数 <code>n</code> 和 <code>k</code> ，请你构造一个答案列表 <code>answer</code> ，该列表应当包含从 <code>1</code> 到 <code>n</code> 的 <code>n</code> 个不同正整数，并同时满足下述条件：</p>

<ul>
	<li>假设该列表是 <code>answer = [a<sub>1</sub>, a<sub>2</sub>, a<sub>3</sub>, ... , a<sub>n</sub>]</code> ，那么列表 <code>[|a<sub>1</sub> - a<sub>2</sub>|, |a<sub>2</sub> - a<sub>3</sub>|, |a<sub>3</sub> - a<sub>4</sub>|, ... , |a<sub>n-1</sub> - a<sub>n</sub>|]</code> 中应该有且仅有 <code>k</code> 个不同整数。</li>
</ul>

<p>返回列表 <code>answer</code> 。如果存在多种答案，只需返回其中 <strong>任意一种</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 1
<strong>输出：</strong>[1, 2, 3]
<strong>解释：</strong>[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 2
<strong>输出：</strong>[1, 3, 2]
<strong>解释：</strong>[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k < n <= 10<sup>4</sup></code></li>
</ul>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：构造**

先按照 `1, n, 2, n-1, 3,...` 构造答案数据 `ans` 的前 $k$ 个数，共产生 $k-1$ 个不同的整数。然后根据 $k$ 的奇偶性确定从哪个数开始构造下一个数。

时间复杂度 $O(n)$，忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def constructArray(self, n: int, k: int) -> List[int]:
        l, r = 1, n
        ans = []
        for i in range(k):
            if i % 2 == 0:
                ans.append(l)
                l += 1
            else:
                ans.append(r)
                r -= 1
        for i in range(k, n):
            if k % 2 == 0:
                ans.append(r)
                r -= 1
            else:
                ans.append(l)
                l += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] constructArray(int n, int k) {
        int l = 1, r = n;
        int[] ans = new int[n];
        for (int i = 0; i < k; ++i) {
            ans[i] = i % 2 == 0 ? l++ : r--;
        }
        for (int i = k; i < n; ++i) {
            ans[i] = k % 2 == 0 ? r-- : l++;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> constructArray(int n, int k) {
        int l = 1, r = n;
        vector<int> ans(n);
        for (int i = 0; i < k; ++i) {
            ans[i] = i % 2 == 0 ? l++ : r--;
        }
        for (int i = k; i < n; ++i) {
            ans[i] = k % 2 == 0 ? r-- : l++;
        }
        return ans;
    }
};
```

### **Go**

```go
func constructArray(n int, k int) []int {
	l, r := 1, n
	ans := make([]int, n)
	for i := 0; i < k; i++ {
		if i%2 == 0 {
			ans[i] = l
			l++
		} else {
			ans[i] = r
			r--
		}
	}
	for i := k; i < n; i++ {
		if k%2 == 0 {
			ans[i] = r
			r--
		} else {
			ans[i] = l
			l++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function constructArray(n: number, k: number): number[] {
    let l = 1;
    let r = n;
    const ans = new Array(n);
    for (let i = 0; i < k; ++i) {
        ans[i] = i % 2 == 0 ? l++ : r--;
    }
    for (let i = k; i < n; ++i) {
        ans[i] = k % 2 == 0 ? r-- : l++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
