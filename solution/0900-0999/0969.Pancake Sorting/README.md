# [969. 煎饼排序](https://leetcode.cn/problems/pancake-sorting)

[English Version](/solution/0900-0999/0969.Pancake%20Sorting/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code> ，请使用 <strong>煎饼翻转</strong><em> </em>完成对数组的排序。</p>

<p>一次煎饼翻转的执行过程如下：</p>

<ul>
	<li>选择一个整数 <code>k</code> ，<code>1 <= k <= arr.length</code></li>
	<li>反转子数组 <code>arr[0...k-1]</code>（<strong>下标从 0 开始</strong>）</li>
</ul>

<p>例如，<code>arr = [3,2,1,4]</code> ，选择 <code>k = 3</code> 进行一次煎饼翻转，反转子数组 <code>[3,2,1]</code> ，得到 <code>arr = [<strong>1</strong>,<strong>2</strong>,<strong>3</strong>,4]</code> 。</p>

<p>以数组形式返回能使 <code>arr</code> 有序的煎饼翻转操作所对应的 <code>k</code> 值序列。任何将数组排序且翻转次数在 <code>10 * arr.length</code> 范围内的有效答案都将被判断为正确。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[3,2,4,1]
<strong>输出：</strong>[4,2,4,3]
<strong>解释：</strong>
我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
初始状态 arr = [3, 2, 4, 1]
第一次翻转后（k = 4）：arr = [<strong>1</strong>, <strong>4</strong>, <strong>2</strong>, <strong>3</strong>]
第二次翻转后（k = 2）：arr = [<strong>4</strong>, <strong>1</strong>, 2, 3]
第三次翻转后（k = 4）：arr = [<strong>3</strong>, <strong>2</strong>, <strong>1</strong>, <strong>4</strong>]
第四次翻转后（k = 3）：arr = [<strong>1</strong>, <strong>2</strong>, <strong>3</strong>, 4]，此时已完成排序。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[1,2,3]
<strong>输出：</strong>[]
<strong>解释：
</strong>输入已经排序，因此不需要翻转任何内容。
请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length <= 100</code></li>
	<li><code>1 <= arr[i] <= arr.length</code></li>
	<li><code>arr</code> 中的所有整数互不相同（即，<code>arr</code> 是从 <code>1</code> 到 <code>arr.length</code> 整数的一个排列）</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def pancakeSort(self, arr: List[int]) -> List[int]:
        def reverse(arr, j):
            i = 0
            while i < j:
                arr[i], arr[j] = arr[j], arr[i]
                i, j = i + 1, j - 1

        n = len(arr)
        ans = []
        for i in range(n - 1, 0, -1):
            j = i
            while j > 0 and arr[j] != i + 1:
                j -= 1
            if j < i:
                if j > 0:
                    ans.append(j + 1)
                    reverse(arr, j)
                ans.append(i + 1)
                reverse(arr, i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        int n = arr.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i > 0; --i) {
            int j = i;
            for (; j > 0 && arr[j] != i + 1; --j)
                ;
            if (j < i) {
                if (j > 0) {
                    ans.add(j + 1);
                    reverse(arr, j);
                }
                ans.add(i + 1);
                reverse(arr, i);
            }
        }
        return ans;
    }

    private void reverse(int[] arr, int j) {
        for (int i = 0; i < j; ++i, --j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }
}
```

### **TypeScript**

```ts
function pancakeSort(arr: number[]): number[] {
    let ans = [];
    for (let n = arr.length; n > 1; n--) {
        let index = 0;
        for (let i = 1; i < n; i++) {
            if (arr[i] >= arr[index]) {
                index = i;
            }
        }
        if (index == n - 1) continue;
        reverse(arr, index);
        reverse(arr, n - 1);
        ans.push(index + 1);
        ans.push(n);
    }
    return ans;
}

function reverse(nums: Array<number>, end: number): void {
    for (let i = 0, j = end; i < j; i++, j--) {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> pancakeSort(vector<int>& arr) {
        int n = arr.size();
        vector<int> ans;
        for (int i = n - 1; i > 0; --i) {
            int j = i;
            for (; j > 0 && arr[j] != i + 1; --j)
                ;
            if (j == i) continue;
            if (j > 0) {
                ans.push_back(j + 1);
                reverse(arr.begin(), arr.begin() + j + 1);
            }
            ans.push_back(i + 1);
            reverse(arr.begin(), arr.begin() + i + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func pancakeSort(arr []int) []int {
	var ans []int
	n := len(arr)
	reverse := func(j int) {
		for i := 0; i < j; i, j = i+1, j-1 {
			arr[i], arr[j] = arr[j], arr[i]
		}
	}
	for i := n - 1; i > 0; i-- {
		j := i
		for ; j > 0 && arr[j] != i+1; j-- {
		}
		if j < i {
			if j > 0 {
				ans = append(ans, j+1)
				reverse(j)
			}
			ans = append(ans, i+1)
			reverse(i)
		}
	}
	return ans
}
```

### **Rust**

```rust
impl Solution {
    pub fn pancake_sort(mut arr: Vec<i32>) -> Vec<i32> {
        let mut res = vec![];
        for n in (1..arr.len()).rev() {
            let mut max_idx = 0;
            for idx in 0..=n {
                if arr[max_idx] < arr[idx] {
                    max_idx = idx;
                }
            }
            if max_idx != n {
                if max_idx != 0 {
                    arr[..=max_idx].reverse();
                    res.push(max_idx as i32 + 1);
                }
                arr[..=n].reverse();
                res.push(n as i32 + 1);
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
