# [2334. Subarray With Elements Greater Than Varying Threshold](https://leetcode.com/problems/subarray-with-elements-greater-than-varying-threshold)

[中文文档](/solution/2300-2399/2334.Subarray%20With%20Elements%20Greater%20Than%20Varying%20Threshold/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>threshold</code>.</p>

<p>Find any subarray of <code>nums</code> of length <code>k</code> such that <strong>every</strong> element in the subarray is <strong>greater</strong> than <code>threshold / k</code>.</p>

<p>Return<em> the <strong>size</strong> of <strong>any</strong> such subarray</em>. If there is no such subarray, return <code>-1</code>.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,4,3,1], threshold = 6
<strong>Output:</strong> 3
<strong>Explanation:</strong> The subarray [3,4,3] has a size of 3, and every element is greater than 6 / 3 = 2.
Note that this is the only valid subarray.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,6,5,8], threshold = 7
<strong>Output:</strong> 1
<strong>Explanation:</strong> The subarray [8] has a size of 1, and 8 &gt; 7 / 1 = 7. So 1 is returned.
Note that the subarray [6,5] has a size of 2, and every element is greater than 7 / 2 = 3.5. 
Similarly, the subarrays [6,5,6], [6,5,6,5], [6,5,6,5,8] also satisfy the given conditions.
Therefore, 2, 3, 4, or 5 may also be returned.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], threshold &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
