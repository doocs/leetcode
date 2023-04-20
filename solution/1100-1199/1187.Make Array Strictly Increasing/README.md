# [1187. 使数组严格递增](https://leetcode.cn/problems/make-array-strictly-increasing)

[English Version](/solution/1100-1199/1187.Make%20Array%20Strictly%20Increasing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数数组&nbsp;<code>arr1</code> 和 <code>arr2</code>，返回使&nbsp;<code>arr1</code>&nbsp;严格递增所需要的最小「操作」数（可能为 0）。</p>

<p>每一步「操作」中，你可以分别从 <code>arr1</code> 和 <code>arr2</code> 中各选出一个索引，分别为&nbsp;<code>i</code> 和&nbsp;<code>j</code>，<code>0 &lt;=&nbsp;i &lt; arr1.length</code>&nbsp;和&nbsp;<code>0 &lt;= j &lt; arr2.length</code>，然后进行赋值运算&nbsp;<code>arr1[i] = arr2[j]</code>。</p>

<p>如果无法让&nbsp;<code>arr1</code>&nbsp;严格递增，请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
<strong>输出：</strong>1
<strong>解释：</strong>用 2 来替换 <code>5，之后</code> <code>arr1 = [1, 2, 3, 6, 7]</code>。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr1 = [1,5,3,6,7], arr2 = [4,3,1]
<strong>输出：</strong>2
<strong>解释：</strong>用 3 来替换 <code>5，然后</code>用 4 来替换 3<code>，得到</code> <code>arr1 = [1, 3, 4, 6, 7]</code>。
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre><strong>输入：</strong>arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
<strong>输出：</strong>-1
<strong>解释：</strong>无法使 <code>arr1 严格递增</code>。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 2000</code></li>
	<li><code>0 &lt;= arr1[i], arr2[i] &lt;= 10^9</code></li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示将 $arr1[0,..,i]$ 转换为严格递增数组，且 $arr1[i]$ 不替换的最小操作数。因此，我们在 $arr1$ 设置首尾两个哨兵 $-\infty$ 和 $\infty$。最后一个数一定是不替换，因此 $f[n-1]$ 即为答案。我们初始化 $f[0]=0$，其余 $f[i]=\infty$。

接下来我们对数组 $arr2$ 进行排序并去重，方便进行二分查找。

对于 $i=1,..,n-1$，我们考虑 $arr1[i-1]$ 是否替换。如果 $arr1[i-1] \lt arr1[i]$，那么 $f[i]$ 可以从 $f[i-1]$ 转移而来，即 $f[i] = f[i-1]$。然后，我们考虑 $arr[i-1]$ 替换的情况，显然 $arr[i-1]$ 应该替换成一个尽可能大的、且比 $arr[i]$ 小的数字，我们在数组 $arr2$ 中进行二分查找，找到第一个大于等于 $arr[i]$ 的下标 $j$。然后我们在 $k \in [1, min(i-1, j)]$ 的范围内枚举替换的个数，如果满足 $arr[i-k-1] \lt arr2[j-k]$，那么 $f[i]$ 可以从 $f[i-k-1]$ 转移而来，即 $f[i] = min(f[i], f[i-k-1] + k)$。

最后，如果 $f[n-1] \geq \infty$，说明无法转换为严格递增数组，返回 $-1$，否则返回 $f[n-1]$。

时间复杂度 $(n \times (\log m + \min(m, n))$，空间复杂度 $O(n)$。其中 $n$ 为 $arr1$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeArrayIncreasing(self, arr1: List[int], arr2: List[int]) -> int:
        arr2.sort()
        m = 0
        for x in arr2:
            if m == 0 or x != arr2[m - 1]:
                arr2[m] = x
                m += 1
        arr2 = arr2[:m]
        arr = [-inf] + arr1 + [inf]
        n = len(arr)
        f = [inf] * n
        f[0] = 0
        for i in range(1, n):
            if arr[i - 1] < arr[i]:
                f[i] = f[i - 1]
            j = bisect_left(arr2, arr[i])
            for k in range(1, min(i - 1, j) + 1):
                if arr[i - k - 1] < arr2[j - k]:
                    f[i] = min(f[i], f[i - k - 1] + k)
        return -1 if f[n - 1] >= inf else f[n - 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int m = 0;
        for (int x : arr2) {
            if (m == 0 || x != arr2[m - 1]) {
                arr2[m++] = x;
            }
        }
        final int inf = 1 << 30;
        int[] arr = new int[arr1.length + 2];
        arr[0] = -inf;
        arr[arr.length - 1] = inf;
        System.arraycopy(arr1, 0, arr, 1, arr1.length);
        int[] f = new int[arr.length];
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i - 1] < arr[i]) {
                f[i] = f[i - 1];
            }
            int j = search(arr2, arr[i], m);
            for (int k = 1; k <= Math.min(i - 1, j); ++k) {
                if (arr[i - k - 1] < arr2[j - k]) {
                    f[i] = Math.min(f[i], f[i - k - 1] + k);
                }
            }
        }
        return f[arr.length - 1] >= inf ? -1 : f[arr.length - 1];
    }

    private int search(int[] nums, int x, int n) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int makeArrayIncreasing(vector<int>& arr1, vector<int>& arr2) {
        sort(arr2.begin(), arr2.end());
        arr2.erase(unique(arr2.begin(), arr2.end()), arr2.end());
        const int inf = 1 << 30;
        arr1.insert(arr1.begin(), -inf);
        arr1.push_back(inf);
        int n = arr1.size();
        vector<int> f(n, inf);
        f[0] = 0;
        for (int i = 1; i < n; ++i) {
            if (arr1[i - 1] < arr1[i]) {
                f[i] = f[i - 1];
            }
            int j = lower_bound(arr2.begin(), arr2.end(), arr1[i]) - arr2.begin();
            for (int k = 1; k <= min(i - 1, j); ++k) {
                if (arr1[i - k - 1] < arr2[j - k]) {
                    f[i] = min(f[i], f[i - k - 1] + k);
                }
            }
        }
        return f[n - 1] >= inf ? -1 : f[n - 1];
    }
};
```

### **Go**

```go
func makeArrayIncreasing(arr1 []int, arr2 []int) int {
	sort.Ints(arr2)
	m := 0
	for _, x := range arr2 {
		if m == 0 || x != arr2[m-1] {
			arr2[m] = x
			m++
		}
	}
	arr2 = arr2[:m]
	const inf = 1 << 30
	arr1 = append([]int{-inf}, arr1...)
	arr1 = append(arr1, inf)
	n := len(arr1)
	f := make([]int, n)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0
	for i := 1; i < n; i++ {
		if arr1[i-1] < arr1[i] {
			f[i] = f[i-1]
		}
		j := sort.SearchInts(arr2, arr1[i])
		for k := 1; k <= min(i-1, j); k++ {
			if arr1[i-k-1] < arr2[j-k] {
				f[i] = min(f[i], f[i-k-1]+k)
			}
		}
	}
	if f[n-1] >= inf {
		return -1
	}
	return f[n-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function makeArrayIncreasing(arr1: number[], arr2: number[]): number {
    arr2.sort((a, b) => a - b);
    let m = 0;
    for (const x of arr2) {
        if (m === 0 || x !== arr2[m - 1]) {
            arr2[m++] = x;
        }
    }
    arr2 = arr2.slice(0, m);
    const inf = 1 << 30;
    arr1 = [-inf, ...arr1, inf];
    const n = arr1.length;
    const f: number[] = new Array(n).fill(inf);
    f[0] = 0;
    const search = (arr: number[], x: number): number => {
        let l = 0;
        let r = arr.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i < n; ++i) {
        if (arr1[i - 1] < arr1[i]) {
            f[i] = f[i - 1];
        }
        const j = search(arr2, arr1[i]);
        for (let k = 1; k <= Math.min(i - 1, j); ++k) {
            if (arr1[i - k - 1] < arr2[j - k]) {
                f[i] = Math.min(f[i], f[i - k - 1] + k);
            }
        }
    }
    return f[n - 1] >= inf ? -1 : f[n - 1];
}
```

### **...**

```

```

<!-- tabs:end -->
