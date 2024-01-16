# [1122. 数组的相对排序](https://leetcode.cn/problems/relative-sort-array)

[English Version](/solution/1100-1199/1122.Relative%20Sort%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个数组，<code>arr1</code> 和&nbsp;<code>arr2</code>，<code>arr2</code>&nbsp;中的元素各不相同，<code>arr2</code> 中的每个元素都出现在&nbsp;<code>arr1</code>&nbsp;中。</p>

<p>对 <code>arr1</code>&nbsp;中的元素进行排序，使 <code>arr1</code> 中项的相对顺序和&nbsp;<code>arr2</code>&nbsp;中的相对顺序相同。未在&nbsp;<code>arr2</code>&nbsp;中出现过的元素需要按照升序放在&nbsp;<code>arr1</code>&nbsp;的末尾。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
<strong>输出：</strong>[2,2,2,1,4,3,3,9,6,7,19]
</pre>

<p><strong>示例 &nbsp;2:</strong></p>

<pre>
<strong>输入：</strong>arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
<strong>输出：</strong>[22,28,8,6,17,44]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr1[i], arr2[i] &lt;= 1000</code></li>
	<li><code>arr2</code>&nbsp;中的元素&nbsp;<code>arr2[i]</code>&nbsp;&nbsp;<strong>各不相同</strong>&nbsp;</li>
	<li><code>arr2</code> 中的每个元素&nbsp;<code>arr2[i]</code>&nbsp;都出现在&nbsp;<code>arr1</code>&nbsp;中</li>
</ul>

## 解法

### 方法一：自定义排序

我们先用哈希表 $pos$ 记录数组 $arr2$ 中每个元素的位置。然后，我们将数组 $arr1$ 中的每个元素映射成一个二元组 $(pos.get(x, 1000 + x), x)$，并对二元组进行排序。最后我们取出所有二元组的第二个元素并返回即可。

时间复杂度 $O(n \times \log n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是数组 $arr1$ 和 $arr2$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        pos = {x: i for i, x in enumerate(arr2)}
        return sorted(arr1, key=lambda x: pos.get(x, 1000 + x))
```

```java
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> pos = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; ++i) {
            pos.put(arr2[i], i);
        }
        int[][] arr = new int[arr1.length][0];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = new int[] {arr1[i], pos.getOrDefault(arr1[i], arr2.length + arr1[i])};
        }
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        for (int i = 0; i < arr.length; ++i) {
            arr1[i] = arr[i][0];
        }
        return arr1;
    }
}
```

```cpp
class Solution {
public:
    vector<int> relativeSortArray(vector<int>& arr1, vector<int>& arr2) {
        unordered_map<int, int> pos;
        for (int i = 0; i < arr2.size(); ++i) {
            pos[arr2[i]] = i;
        }
        vector<pair<int, int>> arr;
        for (int i = 0; i < arr1.size(); ++i) {
            int j = pos.count(arr1[i]) ? pos[arr1[i]] : arr2.size();
            arr.emplace_back(j, arr1[i]);
        }
        sort(arr.begin(), arr.end());
        for (int i = 0; i < arr1.size(); ++i) {
            arr1[i] = arr[i].second;
        }
        return arr1;
    }
};
```

```go
func relativeSortArray(arr1 []int, arr2 []int) []int {
	pos := map[int]int{}
	for i, x := range arr2 {
		pos[x] = i
	}
	arr := make([][2]int, len(arr1))
	for i, x := range arr1 {
		if p, ok := pos[x]; ok {
			arr[i] = [2]int{p, x}
		} else {
			arr[i] = [2]int{len(arr2), x}
		}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][0] < arr[j][0] || arr[i][0] == arr[j][0] && arr[i][1] < arr[j][1]
	})
	for i, x := range arr {
		arr1[i] = x[1]
	}
	return arr1
}
```

```ts
function relativeSortArray(arr1: number[], arr2: number[]): number[] {
    const pos: Map<number, number> = new Map();
    for (let i = 0; i < arr2.length; ++i) {
        pos.set(arr2[i], i);
    }
    const arr: number[][] = [];
    for (const x of arr1) {
        const j = pos.get(x) ?? arr2.length;
        arr.push([j, x]);
    }
    arr.sort((a, b) => a[0] - b[0] || a[1] - b[1]);
    return arr.map(a => a[1]);
}
```

<!-- tabs:end -->

<!-- end -->
