# [面试题 10.03. 搜索旋转数组](https://leetcode.cn/problems/search-rotate-array-lcci)

[English Version](/lcci/10.03.Search%20Rotate%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。</p>
<p><strong>示例1:</strong></p>
<pre><strong> 输入</strong>: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
<strong> 输出</strong>: 8（元素5在该数组中的索引）
</pre>
<p><strong>示例2:</strong></p>
<pre><strong> 输入</strong>：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
<strong> 输出</strong>：-1 （没有找到）
</pre>
<p><strong>提示:</strong></p>
<ol>
	<li>arr 长度范围在[1, 1000000]之间</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们定义二分查找的左边界 $l=0$，右边界 $r=n-1$，其中 $n$ 为数组的长度。

每次在二分查找的过程中，我们会得到当前的中点 $mid=(l+r)/2$。

-   如果 $nums[mid] \gt nums[r]$，说明 $[l,mid]$ 是有序的，此时如果 $nums[l] \le target \le nums[mid]$，说明 $target$ 位于 $[l,mid]$，否则 $target$ 位于 $[mid+1,r]$。
-   如果 $nums[mid] \lt nums[r]$，说明 $[mid+1,r]$ 是有序的，此时如果 $nums[mid] \lt target \le nums[r]$，说明 $target$ 位于 $[mid+1,r]$，否则 $target$ 位于 $[l,mid]$。
-   如果 $nums[mid] = nums[r]$，说明元素 $nums[mid]$ 和 $nums[r]$ 相等，此时无法判断 $target$ 位于哪个区间，我们只能将 $r$ 减少 $1$。

二分查找结束后，如果 $nums[l] = target$，则说明数组中存在目标值 $target$，否则说明不存在。

注意，如果一开始 $nums[l] = nums[r]$，我们循环将 $r$ 减少 $1$，直到 $nums[l] \ne nums[r]$。

时间复杂度近似 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

相似题目：

-   [81. 搜索旋转排序数组 II](/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def search(self, arr: List[int], target: int) -> int:
        l, r = 0, len(arr) - 1
        while arr[l] == arr[r]:
            r -= 1
        while l < r:
            mid = (l + r) >> 1
            if arr[mid] > arr[r]:
                if arr[l] <= target <= arr[mid]:
                    r = mid
                else:
                    l = mid + 1
            elif arr[mid] < arr[r]:
                if arr[mid] < target <= arr[r]:
                    l = mid + 1
                else:
                    r = mid
            else:
                r -= 1
        return l if arr[l] == target else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int search(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (arr[l] == arr[r]) {
            --r;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] > arr[r]) {
                if (arr[l] <= target && target <= arr[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (arr[mid] < arr[r]) {
                if (arr[mid] < target && target <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return arr[l] == target ? l : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int search(vector<int>& arr, int target) {
        int l = 0, r = arr.size() - 1;
        while (arr[l] == arr[r]) {
            --r;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] > arr[r]) {
                if (arr[l] <= target && target <= arr[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (arr[mid] < arr[r]) {
                if (arr[mid] < target && target <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return arr[l] == target ? l : -1;
    }
};
```

### **Go**

```go
func search(arr []int, target int) int {
	l, r := 0, len(arr)-1
	for arr[l] == arr[r] {
		r--
	}
	for l < r {
		mid := (l + r) >> 1
		if arr[mid] > arr[r] {
			if arr[l] <= target && target <= arr[mid] {
				r = mid
			} else {
				l = mid + 1
			}
		} else if arr[mid] < arr[r] {
			if arr[mid] < target && target <= arr[r] {
				l = mid + 1
			} else {
				r = mid
			}
		} else {
			r--
		}
	}
	if arr[l] == target {
		return l
	}
	return -1
}
```

### **TypeScript**

```ts
function search(arr: number[], target: number): number {
    let [l, r] = [0, arr.length - 1];
    while (arr[l] === arr[r]) {
        --r;
    }
    while (l < r) {
        const mid = (l + r) >> 1;
        if (arr[mid] > arr[r]) {
            if (arr[l] <= target && target <= arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        } else if (arr[mid] < arr[r]) {
            if (arr[mid] < target && target <= arr[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        } else {
            --r;
        }
    }
    return arr[l] === target ? l : -1;
}
```

### **...**

```

```

<!-- tabs:end -->
