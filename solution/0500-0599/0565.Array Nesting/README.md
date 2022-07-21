# [565. 数组嵌套](https://leetcode.cn/problems/array-nesting)

[English Version](/solution/0500-0599/0565.Array%20Nesting/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>索引从<code>0</code>开始长度为<code>N</code>的数组<code>A</code>，包含<code>0</code>到<code>N - 1</code>的所有整数。找到最大的集合<code>S</code>并返回其大小，其中 <code>S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }</code>且遵守以下的规则。</p>

<p>假设选择索引为<code>i</code>的元素<code>A[i]</code>为<code>S</code>的第一个元素，<code>S</code>的下一个元素应该是<code>A[A[i]]</code>，之后是<code>A[A[A[i]]]...</code> 以此类推，不断添加直到<code>S</code>出现重复的元素。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> A = [5,4,0,3,1,6,2]
<strong>输出:</strong> 4
<strong>解释:</strong> 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

其中一种最长的 S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; nums.length</code></li>
	<li><code>A</code>中不含有重复的元素。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：图**

嵌套数组最终一定会形成一个环，在枚举 $nums[i]$ 的过程中，可以用 $vis$ 数组剪枝，避免重复枚举同一个环。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

**方法二：原地标记**

由于 $nums$ 元素均在 $[0..n-1]$ 之间，因此，对于访问过的元素，我们可以令 $nums[i]=n$，从而省略 $vis$ 数组。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arrayNesting(self, nums: List[int]) -> int:
        n = len(nums)
        vis = [False] * n
        res = 0
        for i in range(n):
            if vis[i]:
                continue
            cur, m = nums[i], 1
            vis[cur] = True
            while nums[cur] != nums[i]:
                cur = nums[cur]
                m += 1
                vis[cur] = True
            res = max(res, m)
        return res
```

```python
class Solution:
    def arrayNesting(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        for i in range(n):
            cnt = 0
            while nums[i] != n:
                j = nums[i]
                nums[i] = n
                i = j
                cnt += 1
            ans = max(ans, cnt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        boolean[] vis = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                continue;
            }
            int cur = nums[i], m = 1;
            vis[cur] = true;
            while (nums[cur] != nums[i]) {
                cur = nums[cur];
                m++;
                vis[cur] = true;
            }
            res = Math.max(res, m);
        }
        return res;
    }
}
```

```java
class Solution {
    public int arrayNesting(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            int j = i;
            while (nums[j] < n) {
                int k = nums[j];
                nums[j] = n;
                j = k;
                ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int arrayNesting(vector<int>& nums) {
        int n = nums.size();
        vector<bool> vis(n);
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) continue;
            int cur = nums[i], m = 1;
            vis[cur] = true;
            while (nums[cur] != nums[i]) {
                cur = nums[cur];
                ++m;
                vis[cur] = true;
            }
            res = max(res, m);
        }
        return res;
    }
};
```

```cpp
class Solution {
public:
    int arrayNesting(vector<int>& nums) {
        int ans = 0, n = nums.size();
        for (int i = 0; i < n; ++i)
        {
            int cnt = 0;
            int j = i;
            while (nums[j] < n)
            {
                int k = nums[j];
                nums[j] = n;
                j = k;
                ++cnt;
            }
            ans = max(ans, cnt);
        }
        return ans;
    }
};
```

### **Go**

```go
func arrayNesting(nums []int) int {
	n := len(nums)
	vis := make([]bool, n)
	ans := 0
	for i := 0; i < n; i++ {
		if vis[i] {
			continue
		}
		cur, m := nums[i], 1
		vis[cur] = true
		for nums[cur] != nums[i] {
			cur = nums[cur]
			m++
			vis[cur] = true
		}
		if m > ans {
			ans = m
		}
	}
	return ans
}
```

```go
func arrayNesting(nums []int) int {
	ans, n := 0, len(nums)
	for i := range nums {
		cnt, j := 0, i
		for nums[j] != n {
			k := nums[j]
			nums[j] = n
			j = k
			cnt++
		}
		if ans < cnt {
			ans = cnt
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
