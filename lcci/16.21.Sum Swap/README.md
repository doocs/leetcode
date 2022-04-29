# [面试题 16.21. 交换和](https://leetcode.cn/problems/sum-swap-lcci)

[English Version](/lcci/16.21.Sum%20Swap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。</p>

<p>返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
<strong>输出:</strong> [1, 3]
</pre>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> array1 = <code>[1, 2, 3], array2 = [4, 5, 6]</code>
<strong>输出: </strong>[]</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= array1.length, array2.length &lt;= 100000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先计算两个数组的差值 `diff`，若 `diff` 为奇数，则说明无满足条件的数值，返回空数组。否则，将 `array2` 转为 `set`。然后遍历 `array1` 中的每个数 `a`，若值 `a - diff` 在 `set` 中，则说明找到满足条件的数值对。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findSwapValues(self, array1: List[int], array2: List[int]) -> List[int]:
        diff = sum(array1) - sum(array2)
        if diff & 1:
            return []
        diff >>= 1
        s = set(array2)
        for a in array1:
            b = a - diff
            if b in s:
                return [a, b]
        return []
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findSwapValues(int[] array1, int[] array2) {
        int s1 = 0, s2 = 0;
        Set<Integer> s = new HashSet<>();
        for (int a : array1) {
            s1 += a;
        }
        for (int b : array2) {
            s.add(b);
            s2 += b;
        }
        int diff = s1 - s2;
        if ((diff & 1) == 1) {
            return new int[]{};
        }
        diff >>= 1;
        for (int a : array1) {
            int b = a - diff;
            if (s.contains(b)) {
                return new int[]{a, b};
            }
        }
        return new int[]{};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findSwapValues(vector<int>& array1, vector<int>& array2) {
        int s1 = 0, s2 = 0;
        unordered_set<int> s;
        for (int a : array1) s1 += a;
        for (int b : array2) {
            s2 += b;
            s.insert(b);
        }
        int diff = s1 - s2;
        if (diff & 1) {
            return {};
        }
        diff >>= 1;
        for (int a : array1) {
            int b = a - diff;
            if (s.count(b)) {
                return {a, b};
            }
        }
        return {};
    }
};
```

### **Go**

```go
func findSwapValues(array1 []int, array2 []int) []int {
	s1, s2 := 0, 0
	for _, a := range array1 {
		s1 += a
	}
	s := make(map[int]bool)
	for _, b := range array2 {
		s2 += b
		s[b] = true
	}
	diff := s1 - s2
	if (diff & 1) == 1 {
		return []int{}
	}
	diff >>= 1
	for _, a := range array1 {
		b := a - diff
		if s[b] {
			return []int{a, b}
		}
	}
	return []int{}
}
```

### **...**

```

```

<!-- tabs:end -->
