# [面试题 16.16. 部分排序](https://leetcode.cn/problems/sub-sort-lcci)

[English Version](/lcci/16.15.Master%20Mind/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组，编写一个函数，找出索引<code>m</code>和<code>n</code>，只要将索引区间<code>[m,n]</code>的元素排好序，整个数组就是有序的。注意：<code>n-m</code>尽量最小，也就是说，找出符合条件的最短序列。函数返回值为<code>[m,n]</code>，若不存在这样的<code>m</code>和<code>n</code>（例如整个数组是有序的），请返回<code>[-1,-1]</code>。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> [1,2,4,7,10,11,7,12,6,7,16,18,19]
<strong>输出：</strong> [3,9]
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 <= len(array) <= 1000000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次遍历**

我们先从左到右遍历数组 $array$，用 $mx$ 记录遍历过的最大值，如果当前值 $x$ 小于 $mx$，则说明 $x$ 需要被排序，我们将 $x$ 的下标 $i$ 记录为 $right$；否则更新 $mx$。

同理，我们再从右到左遍历数组 $array$，用 $mi$ 记录遍历过的最小值，如果当前值 $x$ 大于 $mi$，则说明 $x$ 需要被排序，我们将 $x$ 的下标 $i$ 记录为 $left$；否则更新 $mi$。

最后返回 $[left, right]$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subSort(self, array: List[int]) -> List[int]:
        n = len(array)
        mi, mx = inf, -inf
        left = right = -1
        for i, x in enumerate(array):
            if x < mx:
                right = i
            else:
                mx = x
        for i in range(n - 1, -1, -1):
            if array[i] > mi:
                left = i
            else:
                mi = array[i]
        return [left, right]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] subSort(int[] array) {
        int n = array.length;
        int mi = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
        int left = -1, right = -1;
        for (int i = 0; i < n; ++i) {
            if (array[i] < mx) {
                right = i;
            } else {
                mx = array[i];
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (array[i] > mi) {
                left = i;
            } else {
                mi = array[i];
            }
        }
        return new int[] {left, right};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> subSort(vector<int>& array) {
        int n = array.size();
        int mi = INT_MAX, mx = INT_MIN;
        int left = -1, right = -1;
        for (int i = 0; i < n; ++i) {
            if (array[i] < mx) {
                right = i;
            } else {
                mx = array[i];
            }
        }
        for (int i = n - 1; ~i; --i) {
            if (array[i] > mi) {
                left = i;
            } else {
                mi = array[i];
            }
        }
        return {left, right};
    }
};
```

### **Go**

```go
func subSort(array []int) []int {
	n := len(array)
	mi, mx := math.MaxInt32, math.MinInt32
	left, right := -1, -1
	for i, x := range array {
		if x < mx {
			right = i
		} else {
			mx = x
		}
	}
	for i := n - 1; i >= 0; i-- {
		if array[i] > mi {
			left = i
		} else {
			mi = array[i]
		}
	}
	return []int{left, right}
}
```

### **TypeScript**

```ts
function subSort(array: number[]): number[] {
    const n = array.length;
    let [mi, mx] = [Infinity, -Infinity];
    let [left, right] = [-1, -1];
    for (let i = 0; i < n; ++i) {
        if (array[i] < mx) {
            right = i;
        } else {
            mx = array[i];
        }
    }
    for (let i = n - 1; ~i; --i) {
        if (array[i] > mi) {
            left = i;
        } else {
            mi = array[i];
        }
    }
    return [left, right];
}
```

### **...**

```

```

<!-- tabs:end -->
