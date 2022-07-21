# [1304. 和为零的 N 个不同整数](https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero)

[English Version](/solution/1300-1399/1304.Find%20N%20Unique%20Integers%20Sum%20up%20to%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>，请你返回 <strong>任意&nbsp;</strong>一个由 <code>n</code>&nbsp;个 <strong>各不相同&nbsp;</strong>的整数组成的数组，并且这 <code>n</code> 个数相加和为 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 5
<strong>输出：</strong>[-7,-1,1,3,4]
<strong>解释：</strong>这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>[-1,0,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumZero(self, n: int) -> List[int]:
        presum = 0
        res = []
        for i in range(1, n):
            res.append(i)
            presum += i
        res.append(-presum)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] sumZero(int n) {
        int preSum = 0;
        int[] ret = new int[n];
        for (int i = 1; i < n; ++i) {
            ret[i - 1] = i;
            preSum += i;
        }
        ret[n - 1] = -preSum;
        return ret;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> sumZero(int n) {
        int presum = 0;
        vector<int> res;
        for (int i = 1; i < n; ++i) {
            res.push_back(i);
            presum += i;
        }
        res.push_back(-presum);
        return res;
    }
};
```

### **Go**

```go
func sumZero(n int) []int {
	presum := 0
	var res []int
	for i := 1; i < n; i++ {
		res = append(res, i)
		presum += i
	}
	res = append(res, -presum)
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
