# [16.06. Smallest Difference](https://leetcode.cn/problems/smallest-difference-lcci)

[中文文档](/lcci/16.06.Smallest%20Difference/README.md)

## Description

<p>Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative) difference. Return the difference.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}

<strong>Output: </strong> 3, the pair (11, 8)

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 100000</code></li>
	<li><code>-2147483648 &lt;= a[i], b[i] &lt;= 2147483647</code></li>
	<li>The result is in the range [-2147483648, 2147483647]</li>
</ul>

## Solutions

### Solution 1: Sorting + Binary Search

We can sort the array $b$, and for each element $x$ in array $a$, perform a binary search in array $b$ to find the element $y$ closest to $x$. Then, the absolute difference between $x$ and $y$ is the absolute difference between $x$ and the closest element in $b$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of array $b$.

<!-- tabs:start -->

```python
class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        b.sort()
        ans = inf
        n = len(b)
        for x in a:
            j = bisect_left(b, x)
            if j < n:
                ans = min(ans, b[j] - x)
            if j:
                ans = min(ans, x - b[j - 1])
        return ans
```

```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(b);
        long ans = Long.MAX_VALUE;
        for (int x : a) {
            int j = search(b, x);
            if (j < b.length) {
                ans = Math.min(ans, (long) b[j] - x);
            }
            if (j > 0) {
                ans = Math.min(ans, (long) x - b[j - 1]);
            }
        }
        return (int) ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
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

```cpp
class Solution {
public:
    int smallestDifference(vector<int>& a, vector<int>& b) {
        sort(b.begin(), b.end());
        long long ans = LONG_LONG_MAX;
        for (int x : a) {
            auto it = lower_bound(b.begin(), b.end(), x);
            if (it != b.end()) {
                ans = min(ans, (long long) *it - x);
            }
            if (it != b.begin()) {
                ans = min(ans, x - (long long) *prev(it));
            }
        }
        return ans;
    }
};
```

```go
func smallestDifference(a []int, b []int) int {
	sort.Ints(b)
	var ans int = 1e18
	for _, x := range a {
		i := sort.SearchInts(b, x)
		if i < len(b) {
			ans = min(ans, b[i]-x)
		}
		if i > 0 {
			ans = min(ans, x-b[i-1])
		}
	}
	return ans
}
```

```ts
function smallestDifference(a: number[], b: number[]): number {
    b.sort((a, b) => a - b);
    let ans = Infinity;
    const search = (nums: number[], x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (const x of a) {
        const j = search(b, x);
        if (j < b.length) {
            ans = Math.min(ans, b[j] - x);
        }
        if (j > 0) {
            ans = Math.min(ans, x - b[j - 1]);
        }
    }
    return ans;
}
```

```swift
class Solution {
    func smallestDifference(_ a: [Int], _ b: [Int]) -> Int {
        let sortedB = b.sorted()
        var ans = Int.max
        
        for x in a {
            let j = search(sortedB, x)
            if j < sortedB.count {
                ans = min(ans, abs(sortedB[j] - x))
            }
            if j > 0 {
                ans = min(ans, abs(x - sortedB[j - 1]))
            }
        }
        
        return ans
    }
    
    private func search(_ nums: [Int], _ x: Int) -> Int {
        var l = 0
        var r = nums.count
        while l < r {
            let mid = (l + r) / 2
            if nums[mid] >= x {
                r = mid
            } else {
                l = mid + 1
            }
        }
        return l
    }
}
```

<!-- tabs:end -->

### Solution 2: Sorting + Two Pointers

We can sort both arrays $a$ and $b$, and use two pointers $i$ and $j$ to maintain the current positions in the two arrays. Initially, $i$ and $j$ point to the beginning of arrays $a$ and $b$, respectively. At each step, we calculate the absolute difference between $a[i]$ and $b[j]$, and update the answer. If one of the elements pointed to by $i$ and $j$ is smaller than the other, we move the pointer pointing to the smaller element forward by one step. The traversal ends when at least one of the pointers goes beyond the array range.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of arrays $a$ and $b$.

<!-- tabs:start -->

```python
class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        a.sort()
        b.sort()
        i = j = 0
        ans = inf
        while i < len(a) and j < len(b):
            ans = min(ans, abs(a[i] - b[j]))
            if a[i] < b[j]:
                i += 1
            else:
                j += 1
        return ans
```

```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = 0;
        long ans = Long.MAX_VALUE;
        while (i < a.length && j < b.length) {
            ans = Math.min(ans, Math.abs((long) a[i] - (long) b[j]));
            if (a[i] < b[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int smallestDifference(vector<int>& a, vector<int>& b) {
        sort(a.begin(), a.end());
        sort(b.begin(), b.end());
        int i = 0, j = 0;
        long long ans = LONG_LONG_MAX;
        while (i < a.size() && j < b.size()) {
            ans = min(ans, abs(1LL * a[i] - 1LL * b[j]));
            if (a[i] < b[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans;
    }
};
```

```go
func smallestDifference(a []int, b []int) int {
	sort.Ints(a)
	sort.Ints(b)
	i, j := 0, 0
	var ans int = 1e18
	for i < len(a) && j < len(b) {
		ans = min(ans, abs(a[i]-b[j]))
		if a[i] < b[j] {
			i++
		} else {
			j++
		}
	}
	return ans
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}
```

```ts
function smallestDifference(a: number[], b: number[]): number {
    a.sort((a, b) => a - b);
    b.sort((a, b) => a - b);
    let [i, j] = [0, 0];
    let ans = Infinity;
    while (i < a.length && j < b.length) {
        ans = Math.min(ans, Math.abs(a[i] - b[j]));
        if (a[i] < b[j]) {
            ++i;
        } else {
            ++j;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
