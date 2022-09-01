# [1089. 复写零](https://leetcode.cn/problems/duplicate-zeros)

[English Version](/solution/1000-1099/1089.Duplicate%20Zeros/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度固定的整数数组&nbsp;<code>arr</code> ，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。</p>

<p>注意：请不要在超过该数组长度的位置写入元素。请对输入的数组&nbsp;<strong>就地&nbsp;</strong>进行上述修改，不要从函数返回任何东西。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,0,2,3,0,4,5,0]
<strong>输出：</strong>[1,0,0,2,3,0,0,4]
<strong>解释：</strong>调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3]
<strong>输出：</strong>[1,2,3]
<strong>解释：</strong>调用函数后，输入的数组将被修改为：[1,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于是原地修改，所以不能直接将 0 的后一位直接修改为 0，这会丢失元素数据。

若选择插入，则会导致元素位置调整，时间复杂度偏高。

**方法一：模拟**

开辟一个等长数组，将 `arr` 复刻一份，再进行简单模拟即可。

-   时间复杂度：$O(n)$。
-   空间复杂度：$O(n)$。

**方法二：双指针**

-   时间复杂度：$O(n)$。
-   空间复杂度：$O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def duplicateZeros(self, arr: List[int]) -> None:
        """
        Do not return anything, modify arr in-place instead.
        """
        n = len(arr)
        i, k = -1, 0
        while k < n:
            i += 1
            k += 1 if arr[i] else 2
        j = n - 1
        if k == n + 1:
            arr[j] = 0
            i, j = i - 1, j - 1
        while ~j:
            if arr[i] == 0:
                arr[j] = arr[j - 1] = arr[i]
                j -= 1
            else:
                arr[j] = arr[i]
            i, j = i - 1, j - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int i = -1, k = 0;
        while (k < n) {
            ++i;
            k += arr[i] > 0 ? 1 : 2;
        }
        int j = n - 1;
        if (k == n + 1) {
            arr[j--] = 0;
            --i;
        }
        while (j >= 0) {
            arr[j] = arr[i];
            if (arr[i] == 0) {
                arr[--j] = arr[i];
            }
            --i;
            --j;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void duplicateZeros(vector<int>& arr) {
        int n = arr.size();
        int i = -1, k = 0;
        while (k < n) {
            ++i;
            k += arr[i] ? 1 : 2;
        }
        int j = n - 1;
        if (k == n + 1) {
            arr[j--] = 0;
            --i;
        }
        while (~j) {
            arr[j] = arr[i];
            if (arr[i] == 0) arr[--j] = arr[i];
            --i;
            --j;
        }
    }
};
```

### **Go**

```go
func duplicateZeros(arr []int) {
	n := len(arr)
	i, k := -1, 0
	for k < n {
		i, k = i+1, k+1
		if arr[i] == 0 {
			k++
		}
	}
	j := n - 1
	if k == n+1 {
		arr[j] = 0
		i, j = i-1, j-1
	}
	for j >= 0 {
		arr[j] = arr[i]
		if arr[i] == 0 {
			j--
			arr[j] = arr[i]
		}
		i, j = i-1, j-1
	}
}
```

### **C**

```c
void duplicateZeros(int* arr, int arrSize){
    int i = 0;
    int j = 0;
    while (j < arrSize) {
        if (arr[i] == 0) {
            j++;
        }
        i++;
        j++;
    }
    i--;
    j--;
    while (i >= 0) {
        if (arr[i] == 0) {
            if (j < arrSize) {
                arr[j] = arr[i];
            }
            j--;
        }
        arr[j] = arr[i];
        i--;
        j--;
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn duplicate_zeros(arr: &mut Vec<i32>) {
        let n = arr.len();
        let mut i = 0;
        let mut j = 0;
        while j < n {
            if arr[i] == 0 {
                j += 1;
            }
            j += 1;
            i += 1;
        }
        while i > 0 {
            if arr[i - 1] == 0 {
                if j <= n {
                    arr[j - 1] = arr[i - 1];
                }
                j -= 1;
            }
            arr[j - 1] = arr[i - 1];
            i -= 1;
            j -= 1;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
