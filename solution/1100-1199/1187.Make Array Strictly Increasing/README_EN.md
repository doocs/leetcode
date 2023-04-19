# [1187. Make Array Strictly Increasing](https://leetcode.com/problems/make-array-strictly-increasing)

[中文文档](/solution/1100-1199/1187.Make%20Array%20Strictly%20Increasing/README.md)

## Description

<p>Given two integer arrays&nbsp;<code>arr1</code> and <code>arr2</code>, return the minimum number of operations (possibly zero) needed&nbsp;to make <code>arr1</code> strictly increasing.</p>

<p>In one operation, you can choose two indices&nbsp;<code>0 &lt;=&nbsp;i &lt; arr1.length</code>&nbsp;and&nbsp;<code>0 &lt;= j &lt; arr2.length</code>&nbsp;and do the assignment&nbsp;<code>arr1[i] = arr2[j]</code>.</p>

<p>If there is no way to make&nbsp;<code>arr1</code>&nbsp;strictly increasing,&nbsp;return&nbsp;<code>-1</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]

<strong>Output:</strong> 1

<strong>Explanation:</strong> Replace <code>5</code> with <code>2</code>, then <code>arr1 = [1, 2, 3, 6, 7]</code>.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> arr1 = [1,5,3,6,7], arr2 = [4,3,1]

<strong>Output:</strong> 2

<strong>Explanation:</strong> Replace <code>5</code> with <code>3</code> and then replace <code>3</code> with <code>4</code>. <code>arr1 = [1, 3, 4, 6, 7]</code>.

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]

<strong>Output:</strong> -1

<strong>Explanation:</strong> You can&#39;t make <code>arr1</code> strictly increasing.</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= arr1.length, arr2.length &lt;= 2000</code></li>
    <li><code>0 &lt;= arr1[i], arr2[i] &lt;= 10^9</code></li>
</ul>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
