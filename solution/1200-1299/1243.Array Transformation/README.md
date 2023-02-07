# [1243. 数组变换](https://leetcode.cn/problems/array-transformation)

[English Version](/solution/1200-1299/1243.Array%20Transformation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>首先，给你一个初始数组 <code>arr</code>。然后，每天你都要根据前一天的数组生成一个新的数组。</p>

<p>第&nbsp;<code>i</code>&nbsp;天所生成的数组，是由你对第&nbsp;<code>i-1</code>&nbsp;天的数组进行如下操作所得的：</p>

<ol>
	<li>假如一个元素小于它的左右邻居，那么该元素自增 <code>1</code>。</li>
	<li>假如一个元素大于它的左右邻居，那么该元素自减 <code>1</code>。</li>
	<li>首、尾元素 <strong>永不</strong>&nbsp;改变。</li>
</ol>

<p>过些时日，你会发现数组将会不再发生变化，请返回最终所得到的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[6,2,3,4]
<strong>输出：</strong>[6,3,3,4]
<strong>解释：</strong>
第一天，数组从 [6,2,3,4] 变为 [6,3,3,4]。
无法再对该数组进行更多操作。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[1,6,3,4,3,5]
<strong>输出：</strong>[1,4,4,4,4,5]
<strong>解释：</strong>
第一天，数组从 [1,6,3,4,3,5] 变为 [1,5,4,3,4,5]。
第二天，数组从 [1,5,4,3,4,5] 变为 [1,4,4,4,4,5]。
无法再对该数组进行更多操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

每次模拟一天，对于每个元素，如果它大于左右邻居，则自减 1，否则自增 1。如果数组在某一天不再发生变化，则返回该数组。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为数组长度和数组元素的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def transformArray(self, arr: List[int]) -> List[int]:
        f = True
        while f:
            f = False
            t = arr[:]
            for i in range(1, len(t) - 1):
                if t[i] > t[i - 1] and t[i] > t[i + 1]:
                    arr[i] -= 1
                    f = True
                if t[i] < t[i - 1] and t[i] < t[i + 1]:
                    arr[i] += 1
                    f = True
        return arr
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> transformArray(int[] arr) {
        boolean f = true;
        while (f) {
            f = false;
            int[] t = arr.clone();
            for (int i = 1; i < t.length - 1; ++i) {
                if (t[i] > t[i - 1] && t[i] > t[i + 1]) {
                    --arr[i];
                    f = true;
                }
                if (t[i] < t[i - 1] && t[i] < t[i + 1]) {
                    ++arr[i];
                    f = true;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : arr) {
            ans.add(x);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> transformArray(vector<int>& arr) {
        bool f = true;
        while (f) {
            f = false;
            vector<int> t = arr;
            for (int i = 1; i < arr.size() - 1; ++i) {
                if (t[i] > t[i - 1] && t[i] > t[i + 1]) {
                    --arr[i];
                    f = true;
                }
                if (t[i] < t[i - 1] && t[i] < t[i + 1]) {
                    ++arr[i];
                    f = true;
                }
            }
        }
        return arr;
    }
};
```

### **Go**

```go
func transformArray(arr []int) []int {
	f := true
	for f {
		f = false
		t := make([]int, len(arr))
		copy(t, arr)
		for i := 1; i < len(arr)-1; i++ {
			if t[i] > t[i-1] && t[i] > t[i+1] {
				arr[i]--
				f = true
			}
			if t[i] < t[i-1] && t[i] < t[i+1] {
				arr[i]++
				f = true
			}
		}
	}
	return arr
}
```

### **...**

```

```

<!-- tabs:end -->
