# [1013. 将数组分成和相等的三个部分](https://leetcode.cn/problems/partition-array-into-three-parts-with-equal-sum)

[English Version](/solution/1000-1099/1013.Partition%20Array%20Into%20Three%20Parts%20With%20Equal%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code>，只有可以将其划分为三个和相等的 <strong>非空</strong> 部分时才返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>形式上，如果可以找出索引 <code>i + 1 < j</code> 且满足 <code>(arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])</code> 就可以将数组三等分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,2,1,-6,6,-7,9,1,2,0,1]
<strong>输出：</strong>true
<strong>解释：</strong>0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,2,1,-6,6,7,9,-1,2,0,1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,3,6,5,-2,2,5,1,-9,4]
<strong>输出：</strong>true
<strong>解释：</strong>3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= arr.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> <= arr[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

先遍历数组 `arr`，得到数组所有元素的和，记为 `s`。如果 `s` 不能被 3 整除，那么数组 `arr` 不能被分成和相等的三个部分，直接返回 `false`。

接下来，利用双指针 `i`, `j` 找三等分和的边界，若成功找到，返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$，其中 $n$ 为数组 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canThreePartsEqualSum(self, arr: List[int]) -> bool:
        s = sum(arr)
        if s % 3 != 0:
            return False
        i, j = 0, len(arr) - 1
        a = b = 0
        while i < len(arr):
            a += arr[i]
            if a == s // 3:
                break
            i += 1
        while ~j:
            b += arr[j]
            if b == s // 3:
                break
            j -= 1
        return i < j - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int s = 0;
        for (int v : arr) {
            s += v;
        }
        if (s % 3 != 0) {
            return false;
        }
        int i = 0, j = arr.length - 1;
        int a = 0, b = 0;
        while (i < arr.length) {
            a += arr[i];
            if (a == s / 3) {
                break;
            }
            ++i;
        }
        while (j >= 0) {
            b += arr[j];
            if (b == s / 3) {
                break;
            }
            --j;
        }
        return i < j - 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canThreePartsEqualSum(vector<int>& arr) {
        int s = 0;
        for (int v : arr) s += v;
        if (s % 3) return false;
        int i = 0, j = arr.size() - 1;
        int a = 0, b = 0;
        while (i < arr.size()) {
            a += arr[i];
            if (a == s / 3) {
                break;
            }
            ++i;
        }
        while (~j) {
            b += arr[j];
            if (b == s / 3) {
                break;
            }
            --j;
        }
        return i < j - 1;
    }
};
```

### **Go**

```go
func canThreePartsEqualSum(arr []int) bool {
	s := 0
	for _, v := range arr {
		s += v
	}
	if s%3 != 0 {
		return false
	}
	i, j := 0, len(arr)-1
	a, b := 0, 0
	for i < len(arr) {
		a += arr[i]
		if a == s/3 {
			break
		}
		i++
	}
	for j >= 0 {
		b += arr[j]
		if b == s/3 {
			break
		}
		j--
	}
	return i < j-1
}
```

### **...**

```

```

<!-- tabs:end -->
