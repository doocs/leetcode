# [面试题 66. 构建乘积数组](https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>A[0,1,…,n-1]</code>，请构建一个数组 <code>B[0,1,…,n-1]</code>，其中 <code>B[i]</code> 的值是数组 <code>A</code> 中除了下标 <code>i</code> 以外的元素的积, 即 <code>B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]</code>。不能使用除法。</p>

<p> </p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入:</strong> [1,2,3,4,5]
<strong>输出:</strong> [120,60,40,30,24]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>所有元素乘积之和不会溢出 32 位整数</li>
	<li><code>a.length <= 100000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次遍历**

我们先创建一个长度为 $n$ 的答案数组 $ans$。

接下来，我们从左到右遍历数组 $a$，过程中维护一个变量 $left$，表示当前元素左边所有元素的乘积，初始时 $left=1$。当遍历到 $a[i]$ 时，我们将 $left$ 赋值给 $ans[i]$，然后 $left$ 乘以 $a[i]$，即 $left \leftarrow left \times a[i]$。

然后，我们从右到左遍历数组 $a$，过程中维护一个变量 $right$，表示当前元素右边所有元素的乘积，初始时 $right=1$。当遍历到 $a[i]$ 时，我们将 $ans[i]$ 乘上 $right$，然后 $right$ 乘以 $a[i]$，即 $right \leftarrow right \times a[i]$。

最终，数组 $ans$ 即为所求的答案。

时间复杂度 $O(n)$，其中 $n$ 为数组 $a$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def constructArr(self, a: List[int]) -> List[int]:
        n = len(a)
        ans = [0] * n
        left = right = 1
        for i in range(n):
            ans[i] = left
            left *= a[i]
        for i in range(n - 1, -1, -1):
            ans[i] *= right
            right *= a[i]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] ans = new int[n];
        for (int i = 0, left = 1; i < n; ++i) {
            ans[i] = left;
            left *= a[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            ans[i] *= right;
            right *= a[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> constructArr(vector<int>& a) {
        int n = a.size();
        vector<int> ans(n);
        for (int i = 0, left = 1; i < n; ++i) {
            ans[i] = left;
            left *= a[i];
        }
        for (int i = n - 1, right = 1; ~i; --i) {
            ans[i] *= right;
            right *= a[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func constructArr(a []int) []int {
	n := len(a)
	ans := make([]int, n)
	for i, left := 0, 1; i < n; i++ {
		ans[i] = left
		left *= a[i]
	}
	for i, right := n-1, 1; i >= 0; i-- {
		ans[i] *= right
		right *= a[i]
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} a
 * @return {number[]}
 */
var constructArr = function (a) {
    const n = a.length;
    const ans = new Array(n);
    for (let i = 0, left = 1; i < n; ++i) {
        ans[i] = left;
        left *= a[i];
    }
    for (let i = n - 1, right = 1; ~i; --i) {
        ans[i] *= right;
        right *= a[i];
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public int[] ConstructArr(int[] a) {
        int n = a.Length;
        int[] ans = new int[n];
        int left = 1, right = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = left;
            left *= a[i];
        }
        for (int i = n - 1; i > -1; i--) {
            ans[i] *= right;
            right *= a[i];
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
