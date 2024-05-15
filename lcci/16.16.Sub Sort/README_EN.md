---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.16.Sub%20Sort/README_EN.md
---

# [16.16. Sub Sort](https://leetcode.cn/problems/sub-sort-lcci)

[中文文档](/lcci/16.15.Master%20Mind/README.md)

## Description

<p>Given an array of integers, write a method to find indices m and n such that if you sorted&nbsp;elements m through n, the entire array would be sorted. Minimize <code>n - m</code> (that is, find the smallest such sequence).</p>
<p>Return <code>[m,n]</code>. If there are no such m and n (e.g. the array is already sorted), return [-1, -1].</p>
<p><strong>Example: </strong></p>
<pre>

<strong>Input: </strong> [1,2,4,7,10,11,7,12,6,7,16,18,19]

<strong>Output: </strong> [3,9]

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>0 &lt;= len(array) &lt;= 1000000</code></li>
</ul>

## Solutions

### Solution 1: Two Passes

We first traverse the array $array$ from left to right, and use $mx$ to record the maximum value encountered so far. If the current value $x$ is less than $mx$, it means that $x$ needs to be sorted, and we record the index $i$ of $x$ as $right$; otherwise, update $mx$.

Similarly, we traverse the array $array$ from right to left, and use $mi$ to record the minimum value encountered so far. If the current value $x$ is greater than $mi$, it means that $x$ needs to be sorted, and we record the index $i$ of $x$ as $left$; otherwise, update $mi$.

Finally, return $[left, right]$.

The time complexity is $O(n)$, where $n$ is the length of the array $array$. The space complexity is $O(1)$.

<!-- tabs:start -->

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

```swift
class Solution {
    func subSort(_ array: [Int]) -> [Int] {
        let n = array.count
        var mi = Int.max, mx = Int.min
        var left = -1, right = -1

        for i in 0..<n {
            if array[i] < mx {
                right = i
            } else {
                mx = array[i]
            }
        }

        for i in stride(from: n - 1, through: 0, by: -1) {
            if array[i] > mi {
                left = i
            } else {
                mi = array[i]
            }
        }

        return [left, right]
    }
}
```

<!-- tabs:end -->

<!-- end -->
