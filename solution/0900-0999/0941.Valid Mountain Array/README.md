# [941. 有效的山脉数组](https://leetcode.cn/problems/valid-mountain-array)

[English Version](/solution/0900-0999/0941.Valid%20Mountain%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>arr</code>，如果它是有效的山脉数组就返回&nbsp;<code>true</code>，否则返回 <code>false</code>。</p>

<p>让我们回顾一下，如果 <code>arr</code>&nbsp;满足下述条件，那么它是一个山脉数组：</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>在&nbsp;<code>0 &lt; i&nbsp;&lt; arr.length - 1</code>&nbsp;条件下，存在&nbsp;<code>i</code>&nbsp;使得：
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... arr[i-1] &lt; arr[i] </code></li>
		<li><code>arr[i] &gt; arr[i+1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0941.Valid%20Mountain%20Array/images/hint_valid_mountain_array.png" style="height: 316px; width: 500px;" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,5,5]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,3,2,1]
<strong>输出：</strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validMountainArray(self, arr: List[int]) -> bool:
        n = len(arr)
        if n < 3:
            return False
        l, r = 0, n - 1
        while l + 1 < n - 1 and arr[l] < arr[l + 1]:
            l += 1
        while r - 1 > 0 and arr[r] < arr[r - 1]:
            r -= 1
        return l == r
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return false;
        }
        int l = 0, r = n - 1;
        while (l + 1 < n - 1 && arr[l] < arr[l + 1]) {
            ++l;
        }
        while (r - 1 > 0 && arr[r] < arr[r - 1]) {
            --r;
        }
        return l == r;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    bool validMountainArray(vector<int>& arr) {
        int n = arr.size();
        if (n < 3) return 0;
        int l = 0, r = n - 1;
        while (l + 1 < n - 1 && arr[l] < arr[l + 1]) ++l;
        while (r - 1 > 0 && arr[r] < arr[r - 1]) --r;
        return l == r;
    }
};
```

### **Go**

```go
func validMountainArray(arr []int) bool {
	n := len(arr)
	if n < 3 {
		return false
	}
	l, r := 0, n-1
	for l+1 < n-1 && arr[l] < arr[l+1] {
		l++
	}
	for r-1 > 0 && arr[r] < arr[r-1] {
		r--
	}
	return l == r
}
```

### **...**

```

```

<!-- tabs:end -->
