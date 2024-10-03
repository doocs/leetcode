---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1331.Rank%20Transform%20of%20an%20Array/README.md
rating: 1355
source: 第 18 场双周赛 Q1
tags:
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [1331. 数组序号转换](https://leetcode.cn/problems/rank-transform-of-an-array)

[English Version](/solution/1300-1399/1331.Rank%20Transform%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>arr</code> ，请你将数组中的每个元素替换为它们排序后的序号。</p>

<p>序号代表了一个元素有多大。序号编号的规则如下：</p>

<ul>
	<li>序号从 1 开始编号。</li>
	<li>一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。</li>
	<li>每个数字的序号都应该尽可能地小。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [40,10,20,30]
<strong>输出：</strong>[4,1,2,3]
<strong>解释：</strong>40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [100,100,100]
<strong>输出：</strong>[1,1,1]
<strong>解释：</strong>所有元素有相同的序号。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [37,12,28,9,100,56,80,5,12]
<strong>输出：</strong>[5,3,4,2,8,6,7,1,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：离散化

我们先复制一个数组 $t$，然后对其进行排序并去重，得到一个长度为 $m$ 且严格单调递增的数组。

然后我们遍历原数组 $arr$，对于其中的每个元素 $x$，我们利用二分查找得到 $x$ 在 $t$ 中的位置，那么该位置加一就是 $x$ 的排名。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $arr$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        t = sorted(set(arr))
        return [bisect_right(t, x) for x in arr]
```

#### Java

```java
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] t = arr.clone();
        Arrays.sort(t);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || t[i] != t[i - 1]) {
                t[m++] = t[i];
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Arrays.binarySearch(t, 0, m, arr[i]) + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        vector<int> t = arr;
        sort(t.begin(), t.end());
        t.erase(unique(t.begin(), t.end()), t.end());
        vector<int> ans;
        for (int x : arr) {
            ans.push_back(upper_bound(t.begin(), t.end(), x) - t.begin());
        }
        return ans;
    }
};
```

#### Go

```go
func arrayRankTransform(arr []int) (ans []int) {
	t := make([]int, len(arr))
	copy(t, arr)
	sort.Ints(t)
	m := 0
	for i, x := range t {
		if i == 0 || x != t[i-1] {
			t[m] = x
			m++
		}
	}
	t = t[:m]
	for _, x := range arr {
		ans = append(ans, sort.SearchInts(t, x)+1)
	}
	return
}
```

#### TypeScript

```ts
function arrayRankTransform(arr: number[]): number[] {
    const t = [...arr].sort((a, b) => a - b);
    let m = 0;
    for (let i = 0; i < t.length; ++i) {
        if (i === 0 || t[i] !== t[i - 1]) {
            t[m++] = t[i];
        }
    }
    const search = (t: number[], right: number, x: number) => {
        let left = 0;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (t[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const ans: number[] = [];
    for (const x of arr) {
        ans.push(search(t, m, x));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：排序 + 哈希表

<!-- tabs:start -->

#### TypeScript

```ts
function arrayRankTransform(arr: number[]): number[] {
    const sorted = [...new Set(arr)].sort((a, b) => a - b);
    const map = new Map<number, number>();
    let c = 1;

    for (const x of sorted) {
        map.set(x, c++);
    }

    return arr.map(x => map.get(x)!);
}
```

#### JavaScript

```js
function arrayRankTransform(arr) {
    const sorted = [...new Set(arr)].sort((a, b) => a - b);
    const map = new Map();
    let c = 1;

    for (const x of sorted) {
        map.set(x, c++);
    }

    return arr.map(x => map.get(x));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
