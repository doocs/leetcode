# [1053. 交换一次的先前排列](https://leetcode.cn/problems/previous-permutation-with-one-swap)

[English Version](/solution/1000-1099/1053.Previous%20Permutation%20With%20One%20Swap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>arr</code>（可能存在重复的元素），请你返回可在&nbsp;<strong>一次交换</strong>（交换两数字 <code>arr[i]</code> 和 <code>arr[j]</code> 的位置）后得到的、按字典序排列小于 <code>arr</code> 的最大排列。</p>

<p>如果无法这么操作，就请返回原数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,2,1]
<strong>输出：</strong>[3,1,2]
<strong>解释：</strong>交换 2 和 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,5]
<strong>输出：</strong>[1,1,5]
<strong>解释：</strong>已经是最小排列
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,9,4,6,7]
<strong>输出：</strong>[1,7,4,6,9]
<strong>解释：</strong>交换 9 和 7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们先从右到左遍历数组，找到第一个满足 $arr[i - 1] \gt arr[i]$ 的下标 $i$，此时 $arr[i - 1]$ 就是我们要交换的数字，我们再从右到左遍历数组，找到第一个满足 $arr[j] \lt arr[i - 1]$ 且 $arr[j] \neq arr[j - 1]$ 的下标 $j$，此时我们交换 $arr[i - 1]$ 和 $arr[j]$ 后返回即可。

如果遍历完数组都没有找到满足条件的下标 $i$，说明数组已经是最小排列，直接返回原数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def prevPermOpt1(self, arr: List[int]) -> List[int]:
        n = len(arr)
        for i in range(n - 1, 0, -1):
            if arr[i - 1] > arr[i]:
                for j in range(n - 1, i - 1, -1):
                    if arr[j] < arr[i - 1] and arr[j] != arr[j - 1]:
                        arr[i - 1], arr[j] = arr[j], arr[i - 1]
                        return arr
        return arr
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i > 0; --i) {
            if (arr[i - 1] > arr[i]) {
                for (int j = n - 1; j > i - 1; --j) {
                    if (arr[j] < arr[i - 1] && arr[j] != arr[j - 1]) {
                        int t = arr[i - 1];
                        arr[i - 1] = arr[j];
                        arr[j] = t;
                        return arr;
                    }
                }
            }
        }
        return arr;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> prevPermOpt1(vector<int>& arr) {
        int n = arr.size();
        for (int i = n - 1; i > 0; --i) {
            if (arr[i - 1] > arr[i]) {
                for (int j = n - 1; j > i - 1; --j) {
                    if (arr[j] < arr[i - 1] && arr[j] != arr[j - 1]) {
                        swap(arr[i - 1], arr[j]);
                        return arr;
                    }
                }
            }
        }
        return arr;
    }
};
```

### **Go**

```go
func prevPermOpt1(arr []int) []int {
	n := len(arr)
	for i := n - 1; i > 0; i-- {
		if arr[i-1] > arr[i] {
			for j := n - 1; j > i-1; j-- {
				if arr[j] < arr[i-1] && arr[j] != arr[j-1] {
					arr[i-1], arr[j] = arr[j], arr[i-1]
					return arr
				}
			}
		}
	}
	return arr
}
```

### **TypeScript**

```ts
function prevPermOpt1(arr: number[]): number[] {
    const n = arr.length;
    for (let i = n - 1; i > 0; --i) {
        if (arr[i - 1] > arr[i]) {
            for (let j = n - 1; j > i - 1; --j) {
                if (arr[j] < arr[i - 1] && arr[j] !== arr[j - 1]) {
                    const t = arr[i - 1];
                    arr[i - 1] = arr[j];
                    arr[j] = t;
                    return arr;
                }
            }
        }
    }
    return arr;
}
```

### **...**

```

```

<!-- tabs:end -->
