# [2334. 元素值大于变化阈值的子数组](https://leetcode.cn/problems/subarray-with-elements-greater-than-varying-threshold)

[English Version](/solution/2300-2399/2334.Subarray%20With%20Elements%20Greater%20Than%20Varying%20Threshold/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>threshold</code>&nbsp;。</p>

<p>找到长度为 <code>k</code>&nbsp;的&nbsp;<code>nums</code>&nbsp;子数组，满足数组中&nbsp;<strong>每个</strong>&nbsp;元素都 <strong>大于</strong>&nbsp;<code>threshold / k</code>&nbsp;。</p>

<p>请你返回满足要求的 <strong>任意</strong>&nbsp;子数组的 <strong>大小</strong>&nbsp;。如果没有这样的子数组，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><strong>子数组</strong> 是数组中一段连续非空的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,3,4,3,1], threshold = 6
<b>输出：</b>3
<b>解释：</b>子数组 [3,4,3] 大小为 3 ，每个元素都大于 6 / 3 = 2 。
注意这是唯一合法的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [6,5,6,5,8], threshold = 7
<b>输出：</b>1
<b>解释：</b>子数组 [8] 大小为 1 ，且 8 &gt; 7 / 1 = 7 。所以返回 1 。
注意子数组 [6,5] 大小为 2 ，每个元素都大于 7 / 2 = 3.5 。
类似的，子数组 [6,5,6] ，[6,5,6,5] ，[6,5,6,5,8] 都是符合条件的子数组。
所以返回 2, 3, 4 和 5 都可以。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], threshold &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：并查集**

考虑**从大到小遍历**数组 $nums$ 中的每个元素 $v$，用并查集来维护以 $v$ 作为子数组最小值的连通块。

遍历过程中：

$v$ 在数组 $nums$ 中的下标为 $i$，若下标 $i-1$ 对应的元素遍历过，可以将 $i-1$ 与 $i$ 进行合并，同理，若下标 $i+1$ 对应的元素也遍历过了，将 $i$ 与 $i+1$ 合并。合并过程中更新连通块的大小。

$v$ 作为当前连通块的最小值，当前连通块的大小为 $size[find(i)]$，若 $v>\frac{\text{threshold}}{size[find(i)]}$，说明找到了满足条件的子数组，返回 $true$。

否则遍历结束，返回 $-1$。

时间复杂度 $O(nlogn)$。

相似题目：[1562. 查找大小为 M 的最新分组](/solution/1500-1599/1562.Find%20Latest%20Group%20of%20Size%20M/README.md)

**方法二：单调栈**

利用单调栈，得到以当前元素 $nums[i]$ 作为最小元素的左右边界 $left[i]$（左边第一个比 $nums[i]$ 小的元素的位置）, $right[i]$（右边第一个比 $nums[i]$ 小的元素的位置）。

那么对于当前元素 $nums[i]$，有 $k=right[i]-left[i]-1$，若 $nums[i]>\frac{\text{threshold}}{k}$，说明找到了满足条件的子数组，返回 $true$。

否则遍历结束，返回 $-1$。

时间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validSubarraySize(self, nums: List[int], threshold: int) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def merge(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]

        n = len(nums)
        p = list(range(n))
        size = [1] * n
        arr = sorted(zip(nums, range(n)), reverse=True)
        vis = [False] * n
        for v, i in arr:
            if i and vis[i - 1]:
                merge(i, i - 1)
            if i < n - 1 and vis[i + 1]:
                merge(i, i + 1)
            if v > threshold // size[find(i)]:
                return size[find(i)]
            vis[i] = True
        return -1
```

```python
class Solution:
    def validSubarraySize(self, nums: List[int], threshold: int) -> int:
        n = len(nums)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, v in enumerate(nums):
            while stk and nums[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] >= nums[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        for i, v in enumerate(nums):
            k = right[i] - left[i] - 1
            if v > threshold // k:
                return k
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private int[] size;

    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        boolean[] vis = new boolean[n];
        for (int[] e : arr) {
            int v = e[0], i = e[1];
            if (i > 0 && vis[i - 1]) {
                merge(i, i - 1);
            }
            if (i < n - 1 && vis[i + 1]) {
                merge(i, i + 1);
            }
            if (v > threshold / size[find(i)]) {
                return size[find(i)];
            }
            vis[i] = true;
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        p[pa] = pb;
        size[pb] += size[pa];
    }
}
```

```java
class Solution {
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] >= v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] >= v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            int k = right[i] - left[i] - 1;
            if (v > threshold / k) {
                return k;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    vector<int> p;
    vector<int> size;

    int validSubarraySize(vector<int>& nums, int threshold) {
        int n = nums.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        size.assign(n, 1);
        vector<pii> arr(n);
        for (int i = 0; i < n; ++i) arr[i] = {nums[i], i};
        sort(arr.begin(), arr.end());
        vector<bool> vis(n);
        for (int j = n - 1; ~j; --j) {
            int v = arr[j].first, i = arr[j].second;
            if (i && vis[i - 1]) merge(i, i - 1);
            if (j < n - 1 && vis[i + 1]) merge(i, i + 1);
            if (v > threshold / size[find(i)]) return size[find(i)];
            vis[i] = true;
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return;
        p[pa] = pb;
        size[pb] += size[pa];
    }
};
```

```cpp
class Solution {
public:
    int validSubarraySize(vector<int>& nums, int threshold) {
        int n = nums.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i)
        {
            int v = nums[i];
            while (!stk.empty() && nums[stk.top()] >= v) stk.pop();
            if (!stk.empty()) left[i] = stk.top();
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; ~i; --i)
        {
            int v = nums[i];
            while (!stk.empty() && nums[stk.top()] >= v) stk.pop();
            if (!stk.empty()) right[i] = stk.top();
            stk.push(i);
        }
        for (int i = 0; i < n; ++i)
        {
            int v = nums[i];
            int k = right[i] - left[i] - 1;
            if (v > threshold / k) return k;
        }
        return -1;
    }
};
```

### **Go**

```go
func validSubarraySize(nums []int, threshold int) int {
	n := len(nums)
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	merge := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size[pb] += size[pa]
	}

	arr := make([][]int, n)
	for i, v := range nums {
		arr[i] = []int{v, i}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][0] > arr[j][0]
	})
	vis := make([]bool, n)
	for _, e := range arr {
		v, i := e[0], e[1]
		if i > 0 && vis[i-1] {
			merge(i, i-1)
		}
		if i < n-1 && vis[i+1] {
			merge(i, i+1)
		}
		if v > threshold/size[find(i)] {
			return size[find(i)]
		}
		vis[i] = true
	}
	return -1
}
```

```go
func validSubarraySize(nums []int, threshold int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	var stk []int
	for i, v := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		v := nums[i]
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	for i, v := range nums {
		k := right[i] - left[i] - 1
		if v > threshold/k {
			return k
		}
	}
	return -1
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
