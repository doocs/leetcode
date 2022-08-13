# [1395. 统计作战单位数](https://leetcode.cn/problems/count-number-of-teams)

[English Version](/solution/1300-1399/1395.Count%20Number%20of%20Teams/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p> <code>n</code> 名士兵站成一排。每个士兵都有一个 <strong>独一无二</strong> 的评分 <code>rating</code> 。</p>

<p>每 <strong>3</strong> 个士兵可以组成一个作战单位，分组规则如下：</p>

<ul>
	<li>从队伍中选出下标分别为 <code>i</code>、<code>j</code>、<code>k</code> 的 3 名士兵，他们的评分分别为 <code>rating[i]</code>、<code>rating[j]</code>、<code>rating[k]</code></li>
	<li>作战单位需满足： <code>rating[i] < rating[j] < rating[k]</code> 或者 <code>rating[i] > rating[j] > rating[k]</code> ，其中  <code>0 <= i < j < k < n</code></li>
</ul>

<p>请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rating = [2,5,3,4,1]
<strong>输出：</strong>3
<strong>解释：</strong>我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rating = [2,1,3]
<strong>输出：</strong>0
<strong>解释：</strong>根据题目条件，我们无法组建作战单位。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rating = [1,2,3,4]
<strong>输出：</strong>4
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == rating.length</code></li>
	<li><code>3 <= n <= 1000</code></li>
	<li><code>1 <= rating[i] <= 10^5</code></li>
	<li><code>rating</code> 中的元素都是唯一的</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

外层循环枚举中间节点，内层循环各枚举左右节点，统计个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numTeams(self, rating: List[int]) -> int:
        n, ans = len(rating), 0
        for j in range(1, n - 1):
            ia = ib = ka = kb = 0
            for i in range(j):
                if rating[i] < rating[j]:
                    ia += 1
                elif rating[i] > rating[j]:
                    ib += 1
            for k in range(j + 1, n):
                if rating[j] < rating[k]:
                    ka += 1
                elif rating[j] > rating[k]:
                    kb += 1
            ans += ia * ka + ib * kb
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int ans = 0;
        for (int j = 1; j < n - 1; ++j) {
            int ia = 0;
            int ib = 0;
            int ka = 0;
            int kb = 0;
            for (int i = 0; i < j; ++i) {
                if (rating[i] < rating[j]) {
                    ++ia;
                } else if (rating[i] > rating[j]) {
                    ++ib;
                }
            }
            for (int k = j + 1; k < n; ++k) {
                if (rating[j] < rating[k]) {
                    ++ka;
                } else if (rating[j] > rating[k]) {
                    ++kb;
                }
            }
            ans += ia * ka + ib * kb;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numTeams(vector<int>& rating) {
        int n = rating.size(), ans = 0;
        for (int j = 1; j < n - 1; ++j) {
            int ia = 0, ib = 0, ka = 0, kb = 0;
            for (int i = 0; i < j; ++i) {
                if (rating[i] < rating[j])
                    ++ia;
                else if (rating[i] > rating[j])
                    ++ib;
            }
            for (int k = j + 1; k < n; ++k) {
                if (rating[j] < rating[k])
                    ++ka;
                else if (rating[j] > rating[k])
                    ++kb;
            }
            ans += ia * ka + ib * kb;
        }
        return ans;
    }
};
```

### **Go**

```go
func numTeams(rating []int) int {
	n, ans := len(rating), 0
	for j := 1; j < n-1; j++ {
		ia, ib, ka, kb := 0, 0, 0, 0
		for i := 0; i < j; i++ {
			if rating[i] < rating[j] {
				ia++
			} else if rating[i] > rating[j] {
				ib++
			}
		}
		for k := j + 1; k < n; k++ {
			if rating[j] < rating[k] {
				ka++
			} else if rating[j] > rating[k] {
				kb++
			}
		}
		ans += ia*ka + ib*kb
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
