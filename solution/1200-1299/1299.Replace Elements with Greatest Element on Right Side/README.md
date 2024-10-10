---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1299.Replace%20Elements%20with%20Greatest%20Element%20on%20Right%20Side/README.md
rating: 1219
source: 第 16 场双周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [1299. 将每个元素替换为右侧最大元素](https://leetcode.cn/problems/replace-elements-with-greatest-element-on-right-side)

[English Version](/solution/1200-1299/1299.Replace%20Elements%20with%20Greatest%20Element%20on%20Right%20Side/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>arr</code> ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 <code>-1</code> 替换。</p>

<p>完成所有替换操作后，请你返回这个数组。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [17,18,5,4,6,1]
<strong>输出：</strong>[18,6,6,6,1,-1]
<strong>解释：</strong>
- 下标 0 的元素 --> 右侧最大元素是下标 1 的元素 (18)
- 下标 1 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 2 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 3 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 4 的元素 --> 右侧最大元素是下标 5 的元素 (1)
- 下标 5 的元素 --> 右侧没有其他元素，替换为 -1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [400]
<strong>输出：</strong>[-1]
<strong>解释：</strong>下标<strong> </strong>0 的元素右侧没有其他元素。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length <= 10<sup>4</sup></code></li>
	<li><code>1 <= arr[i] <= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逆序遍历

我们用一个变量 $mx$ 记录当前位置右侧的最大值，初始时 $mx = -1$。

然后我们从右向左遍历数组，对于每个位置 $i$，我们记当前位置的值为 $x$，将当前位置的值更新为 $mx$，然后更新 $mx = \max(mx, x)$。

最后返回原数组即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def replaceElements(self, arr: List[int]) -> List[int]:
        mx = -1
        for i in reversed(range(len(arr))):
            x = arr[i]
            arr[i] = mx
            mx = max(mx, x)
        return arr
```

#### Java

```java
class Solution {
    public int[] replaceElements(int[] arr) {
        for (int i = arr.length - 1, mx = -1; i >= 0; --i) {
            int x = arr[i];
            arr[i] = mx;
            mx = Math.max(mx, x);
        }
        return arr;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> replaceElements(vector<int>& arr) {
        for (int i = arr.size() - 1, mx = -1; ~i; --i) {
            int x = arr[i];
            arr[i] = mx;
            mx = max(mx, x);
        }
        return arr;
    }
};
```

#### Go

```go
func replaceElements(arr []int) []int {
	for i, mx := len(arr)-1, -1; i >= 0; i-- {
		x := arr[i]
		arr[i] = mx
		mx = max(mx, x)
	}
	return arr
}
```

#### TypeScript

```ts
function replaceElements(arr: number[]): number[] {
    for (let i = arr.length - 1, mx = -1; ~i; --i) {
        const x = arr[i];
        arr[i] = mx;
        mx = Math.max(mx, x);
    }
    return arr;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
