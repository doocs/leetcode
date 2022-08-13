# [剑指 Offer II 075. 数组相对排序](https://leetcode.cn/problems/0H97ZC)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个数组，<code>arr1</code> 和&nbsp;<code>arr2</code>，</p>

<ul>
	<li><code>arr2</code>&nbsp;中的元素各不相同</li>
	<li><code>arr2</code> 中的每个元素都出现在&nbsp;<code>arr1</code>&nbsp;中</li>
</ul>

<p>对 <code>arr1</code>&nbsp;中的元素进行排序，使 <code>arr1</code> 中项的相对顺序和&nbsp;<code>arr2</code>&nbsp;中的相对顺序相同。未在&nbsp;<code>arr2</code>&nbsp;中出现过的元素需要按照升序放在&nbsp;<code>arr1</code>&nbsp;的末尾。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
<strong>输出：</strong>[2,2,2,1,4,3,3,9,6,7,19]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr1[i], arr2[i] &lt;= 1000</code></li>
	<li><code>arr2</code>&nbsp;中的元素&nbsp;<code>arr2[i]</code>&nbsp;各不相同</li>
	<li><code>arr2</code> 中的每个元素&nbsp;<code>arr2[i]</code>&nbsp;都出现在&nbsp;<code>arr1</code>&nbsp;中</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 1122&nbsp;题相同：<a href="https://leetcode.cn/problems/relative-sort-array/">https://leetcode.cn/problems/relative-sort-array/</a>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于本题数据范围是 `[0, 1000]`，因此我们可以开辟一个长度为 1001 的数组 mp，记录 arr1 的元素以及出现的次数。

接着，遍历 arr2 中每个元素 x，若 `mp[x]` 大于 0，循环将 x 加入到答案数组 arr1 中，并且递减 `mp[x]`。遍历结束后，再从下标 `j = 0` 开始遍历 mp，若遇到 `mp[j]` 大于 0，将 `mp[j]` 个 j 加入到答案数组 arr1 中。

最后返回 arr1 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        mp = {num: i for i, num in enumerate(arr2)}
        arr1.sort(key=lambda x: (mp.get(x, 10000), x))
        return arr1
```

```python
class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        mp = [0] * 1001
        for x in arr1:
            mp[x] += 1
        i = 0
        for x in arr2:
            while mp[x] > 0:
                arr1[i] = x
                mp[x] -= 1
                i += 1
        for x, cnt in enumerate(mp):
            for _ in range(cnt):
                arr1[i] = x
                i += 1
        return arr1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] mp = new int[1001];
        for (int x : arr1) {
            ++mp[x];
        }
        int i = 0;
        for (int x : arr2) {
            while (mp[x]-- > 0) {
                arr1[i++] = x;
            }
        }
        for (int j = 0; j < mp.length; ++j) {
            while (mp[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        return arr1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> relativeSortArray(vector<int>& arr1, vector<int>& arr2) {
        vector<int> mp(1001);
        for (int x : arr1) ++mp[x];
        int i = 0;
        for (int x : arr2) {
            while (mp[x]-- > 0) arr1[i++] = x;
        }
        for (int j = 0; j < mp.size(); ++j) {
            while (mp[j]-- > 0) arr1[i++] = j;
        }
        return arr1;
    }
};
```

### **Go**

```go
func relativeSortArray(arr1 []int, arr2 []int) []int {
	mp := make([]int, 1001)
	for _, x := range arr1 {
		mp[x]++
	}
	i := 0
	for _, x := range arr2 {
		for mp[x] > 0 {
			arr1[i] = x
			mp[x]--
			i++
		}
	}
	for j, cnt := range mp {
		for cnt > 0 {
			arr1[i] = j
			i++
			cnt--
		}
	}
	return arr1
}
```

### **...**

```

```

<!-- tabs:end -->
