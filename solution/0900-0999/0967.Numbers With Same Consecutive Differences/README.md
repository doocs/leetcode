# [967. 连续差相同的数字](https://leetcode.cn/problems/numbers-with-same-consecutive-differences)

[English Version](/solution/0900-0999/0967.Numbers%20With%20Same%20Consecutive%20Differences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>返回所有长度为 <code>n</code> 且满足其每两个连续位上的数字之间的差的绝对值为 <code>k</code> 的<strong> 非负整数 </strong>。</p>

<p>请注意，<strong>除了 </strong>数字 <code>0</code> 本身之外，答案中的每个数字都 <strong>不能 </strong>有前导零。例如，<code>01</code> 有一个前导零，所以是无效的；但 <code>0</code>&nbsp;是有效的。</p>

<p>你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 7
<strong>输出：</strong>[181,292,707,818,929]
<strong>解释：</strong>注意，070 不是一个有效的数字，因为它有前导零。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, k = 1
<strong>输出：</strong>[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2, k = 0
<strong>输出：</strong>[11,22,33,44,55,66,77,88,99]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 2, k = 2
<strong>输出：</strong>[13,20,24,31,35,42,46,53,57,64,68,75,79,86,97]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 9</code></li>
	<li><code>0 &lt;= k &lt;= 9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        ans = []

        def dfs(n, k, t):
            if n == 0:
                ans.append(t)
                return
            last = t % 10
            if last + k <= 9:
                dfs(n - 1, k, t * 10 + last + k)
            if last - k >= 0 and k != 0:
                dfs(n - 1, k, t * 10 + last - k)

        for i in range(1, 10):
            dfs(n - 1, k, i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(n - 1, k, i, res);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private void dfs(int n, int k, int t, List<Integer> res) {
        if (n == 0) {
            res.add(t);
            return;
        }
        int last = t % 10;
        if (last + k <= 9) {
            dfs(n - 1, k, t * 10 + last + k, res);
        }
        if (last - k >= 0 && k != 0) {
            dfs(n - 1, k, t * 10 + last - k, res);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> ans;

    vector<int> numsSameConsecDiff(int n, int k) {
        for (int i = 1; i < 10; ++i)
            dfs(n - 1, k, i);
        return ans;
    }

    void dfs(int n, int k, int t) {
        if (n == 0) {
            ans.push_back(t);
            return;
        }
        int last = t % 10;
        if (last + k <= 9) dfs(n - 1, k, t * 10 + last + k);
        if (last - k >= 0 && k != 0) dfs(n - 1, k, t * 10 + last - k);
    }
};
```

### **Go**

```go
func numsSameConsecDiff(n int, k int) []int {
	var ans []int
	var dfs func(n, k, t int)
	dfs = func(n, k, t int) {
		if n == 0 {
			ans = append(ans, t)
			return
		}
		last := t % 10
		if last+k <= 9 {
			dfs(n-1, k, t*10+last+k)
		}
		if last-k >= 0 && k != 0 {
			dfs(n-1, k, t*10+last-k)
		}
	}

	for i := 1; i < 10; i++ {
		dfs(n-1, k, i)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
