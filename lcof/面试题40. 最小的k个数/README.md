# [面试题 40. 最小的 k 个数](https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/)

## 题目描述

<p>输入整数数组 <code>arr</code> ，找出其中最小的 <code>k</code> 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [3,2,1], k = 2
<strong>输出：</strong>[1,2] 或者 [2,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [0,1,2,1], k = 1
<strong>输出：</strong>[0]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= arr.length &lt;= 10000</code></li>
	<li><code>0 &lt;= arr[i]&nbsp;&lt;= 10000</code></li>
</ul>

## 解法

**方法一：排序**

我们可以直接对数组 `arr` 按从小到大排序，然后取前 $k$ 个数即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `arr` 的长度。

**方法二：优先队列（大根堆）**

我们可以用优先队列（大根堆）维护最小的 $k$ 个数。

遍历数组 `arr`，对于当前遍历到的数 $x$，我们先将其加入优先队列中，然后判断优先队列的大小是否超过 $k$，如果超过了，就将堆顶元素弹出。最后将优先队列中的数存入数组并返回即可。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(k)$。其中 $n$ 为数组 `arr` 的长度。

**方法三：快排思想**

我们可以利用快速排序的思想，每次划分后判断划分点的位置是否为 $k$，如果是，就直接返回划分点左边的数即可，否则根据划分点的位置决定下一步划分的区间。

时间复杂度 $O(n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getLeastNumbers(self, arr: List[int], k: int) -> List[int]:
        arr.sort()
        return arr[:k]
```

```python
class Solution:
    def getLeastNumbers(self, arr: List[int], k: int) -> List[int]:
        h = []
        for x in arr:
            heappush(h, -x)
            if len(h) > k:
                heappop(h)
        return [-x for x in h]
```

```python
class Solution:
    def getLeastNumbers(self, arr: List[int], k: int) -> List[int]:
        def quick_sort(l, r):
            i, j = l, r
            while i < j:
                while i < j and arr[j] >= arr[l]:
                    j -= 1
                while i < j and arr[i] <= arr[l]:
                    i += 1
                arr[i], arr[j] = arr[j], arr[i]
            arr[i], arr[l] = arr[l], arr[i]
            if k < i:
                return quick_sort(l, i - 1)
            if k > i:
                return quick_sort(i + 1, r)
            return arr[:k]

        n = len(arr)
        return arr if k == n else quick_sort(0, n - 1)
```

### **Java**

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int x : arr) {
            q.offer(x);
            if (q.size() > k) {
                q.poll();
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = q.poll();
        }
        return ans;
    }
}
```

```java
class Solution {
    private int[] arr;
    private int k;

    public int[] getLeastNumbers(int[] arr, int k) {
        int n = arr.length;
        this.arr = arr;
        this.k = k;
        return k == n ? arr : quickSort(0, n - 1);
    }

    private int[] quickSort(int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) {
                --j;
            }
            while (i < j && arr[i] <= arr[l]) {
                ++i;
            }
            swap(i, j);
        }
        swap(i, l);
        if (k < i) {
            return quickSort(l, i - 1);
        }
        if (k > i) {
            return quickSort(i + 1, r);
        }
        return Arrays.copyOf(arr, k);
    }

    private void swap(int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        sort(arr.begin(), arr.end());
        return vector<int>(arr.begin(), arr.begin() + k);
    }
};
```

```cpp
class Solution {
public:
    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        priority_queue<int> q;
        for (int& x : arr) {
            q.push(x);
            if (q.size() > k) {
                q.pop();
            }
        }
        vector<int> ans(k);
        for (int i = 0; i < k; ++i) {
            ans[i] = q.top();
            q.pop();
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        int n = arr.size();
        function<vector<int>(int, int)> quickSort = [&](int l, int r) -> vector<int> {
            int i = l, j = r;
            while (i < j) {
                while (i < j && arr[j] >= arr[l]) {
                    --j;
                }
                while (i < j && arr[i] <= arr[l]) {
                    ++i;
                }
                swap(arr[i], arr[j]);
            }
            swap(arr[i], arr[l]);
            if (k < i) {
                return quickSort(l, i - 1);
            }
            if (k > i) {
                return quickSort(i + 1, r);
            }
            return vector<int>(arr.begin(), arr.begin() + k);
        };
        return k == n ? arr : quickSort(0, n - 1);
    }
};
```

### **Go**

```go
func getLeastNumbers(arr []int, k int) []int {
	sort.Ints(arr)
	return arr[:k]
}
```

```go
func getLeastNumbers(arr []int, k int) (ans []int) {
	q := hp{}
	for _, x := range arr {
		heap.Push(&q, x)
		if q.Len() > k {
			heap.Pop(&q)
		}
	}
	for i := 0; i < k; i++ {
		ans = append(ans, heap.Pop(&q).(int))
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

```go
func getLeastNumbers(arr []int, k int) []int {
	n := len(arr)
	if k == n {
		return arr
	}
	var quickSort func(l, r int) []int
	quickSort = func(l, r int) []int {
		i, j := l, r
		for i < j {
			for i < j && arr[j] >= arr[l] {
				j--
			}
			for i < j && arr[i] <= arr[l] {
				i++
			}
			arr[i], arr[j] = arr[j], arr[i]
		}
		arr[i], arr[l] = arr[l], arr[i]
		if k < i {
			return quickSort(l, i-1)
		}
		if k > i {
			return quickSort(i+1, r)
		}
		return arr[:k]
	}
	return quickSort(0, n-1)
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @param {number} k
 * @return {number[]}
 */
var getLeastNumbers = function (arr, k) {
    // 排序
    // return arr.sort((a,b)=>a-b).slice(0,k)
    // ==========================================
    // 快排思想
    let left = 0;
    let right = arr.length - 1;
    while (left < right) {
        let i = partition(left, right);
        if (i <= k) {
            left = i + 1;
        }
        if (i >= k) {
            right = i - 1;
        }
    }
    function partition(left, right) {
        let pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
    return arr.slice(0, k);
};
```

### **TypeScript**

```ts
function getLeastNumbers(arr: number[], k: number): number[] {
    let start = 0;
    let end = arr.length;
    while (start < end && end > k) {
        const index = start + Math.floor(Math.random() * (end - start));
        [arr[start], arr[index]] = [arr[index], arr[start]];
        const num = arr[start];
        let mark = start;
        for (let i = start + 1; i < end; i++) {
            if (arr[i] < num) {
                mark++;
                [arr[i], arr[mark]] = [arr[mark], arr[i]];
            }
        }
        [arr[start], arr[mark]] = [arr[mark], arr[start]];

        if (mark >= k) {
            end = mark;
        } else {
            start = mark + 1;
        }
    }
    return arr.slice(0, k);
}
```

### **Rust**

```rust
impl Solution {
    pub fn get_least_numbers(mut arr: Vec<i32>, k: i32) -> Vec<i32> {
        let k = k as usize;
        let mut start = 0;
        let mut end = arr.len();
        while start < end && end > k {
            let num = arr[start];
            let mut mark = start;
            for i in (start + 1)..end {
                if arr[i] < num {
                    mark += 1;
                    arr.swap(i, mark);
                }
            }
            arr.swap(start, mark);

            if mark <= k {
                start = mark + 1;
            } else {
                end = mark
            }
        }
        arr[0..k].to_vec()
    }
}
```

### **C#**

```cs
public class Solution {
    public int[] GetLeastNumbers(int[] arr, int k) {
        Array.Sort(arr);
        return arr[..k];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
