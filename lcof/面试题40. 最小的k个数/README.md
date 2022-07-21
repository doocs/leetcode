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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getLeastNumbers(self, arr: List[int], k: int) -> List[int]:
        if k == 0:
            return []
        heap = []
        for e in arr:
            heappush(heap, e)
        return nsmallest(k, heap)
```

### **Java**

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> bigRoot = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int e : arr) {
            if (bigRoot.size() < k) {
                bigRoot.offer(e);
            } else if (e < bigRoot.peek()) {
                bigRoot.poll();
                bigRoot.offer(e);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = bigRoot.poll();
        }
        return res;
    }
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

### **C++**

```cpp
class Solution {
public:
    int partition(vector<int>& arr, int begin, int end) {
        int l = begin;
        int r = end;
        int povit = arr[begin];

        while (l < r) {
            // 从右边开始，找到第一个小于povit的数字（用于交换）
            while (l < r && arr[r] >= povit) { r--; }
            while (l < r && arr[l] <= povit) { l++; }
            if (l < r) { swap(arr[l], arr[r]); }
        }

        swap(arr[begin], arr[l]);
        return l;
    }

    void partSort(vector<int>& arr, int begin, int end, int target) {
        if (begin >= end) {
            return;
        }

        // 思路类似快排，这样做比堆排序时间复杂度低
        // C++中，stl提供partial_sort()方法，就是这种实现方式
        int mid = partition(arr, begin, end);
        if (mid == target) {
            return;
        } else if (target < mid) {
            partSort(arr, begin, mid - 1, target);
        } else {
            partSort(arr, mid + 1, end, target);
        }

        return;
    }

    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        partSort(arr, 0, arr.size() - 1, k - 1);
        vector<int> ret(arr.begin(), arr.begin() + k);
        return ret;
    }
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
