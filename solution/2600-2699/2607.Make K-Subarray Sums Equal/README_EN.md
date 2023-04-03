# [2607. Make K-Subarray Sums Equal](https://leetcode.com/problems/make-k-subarray-sums-equal)

[中文文档](/solution/2600-2699/2607.Make%20K-Subarray%20Sums%20Equal/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>arr</code> and an integer <code>k</code>. The array <code>arr</code> is circular. In other words, the first element of the array is the next element of the last element, and the last element of the array is the previous element of the first element.</p>

<p>You can do the following operation any number of times:</p>

<ul>
	<li>Pick any element from <code>arr</code> and increase or decrease it by <code>1</code>.</li>
</ul>

<p>Return <em>the minimum number of operations such that the sum of each <strong>subarray</strong> of length </em><code>k</code><em> is equal</em>.</p>

<p>A <strong>subarray</strong> is a contiguous part of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,4,1,3], k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> we can do one operation on index 1 to make its value equal to 3.
The array after the operation is [1,3,1,3]
- Subarray starts at index 0 is [1, 3], and its sum is 4 
- Subarray starts at index 1 is [3, 1], and its sum is 4 
- Subarray starts at index 2 is [1, 3], and its sum is 4 
- Subarray starts at index 3 is [3, 1], and its sum is 4 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,5,5,7], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> we can do three operations on index 0 to make its value equal to 5 and two operations on index 3 to make its value equal to 5.
The array after the operations is [5,5,5,5]
- Subarray starts at index 0 is [5, 5, 5], and its sum is 15
- Subarray starts at index 1 is [5, 5, 5], and its sum is 15
- Subarray starts at index 2 is [5, 5, 5], and its sum is 15
- Subarray starts at index 3 is [5, 5, 5], and its sum is 15 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def makeSubKSumEqual(self, arr: List[int], k: int) -> int:
        n = len(arr)
        g = gcd(n, k)
        ans = 0
        for i in range(g):
            t = sorted(arr[i:n:g])
            mid = t[len(t) >> 1]
            ans += sum(abs(x - mid) for x in t)
        return ans
```

### **Java**

```java
class Solution {
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        int g = gcd(n, k);
        long ans = 0;
        for (int i = 0; i < g; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = i; j < n; j += g) {
                t.add(arr[j]);
            }
            t.sort((a, b) -> a - b);
            int mid = t.get(t.size() >> 1);
            for (int x : t) {
                ans += Math.abs(x - mid);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long makeSubKSumEqual(vector<int>& arr, int k) {
        int n = arr.size();
        int g = gcd(n, k);
        long long ans = 0;
        for (int i = 0; i < g; ++i) {
            vector<int> t;
            for (int j = i; j < n; j += g) {
                t.push_back(arr[j]);
            }
            sort(t.begin(), t.end());
            int mid = t[t.size() / 2];
            for (int x : t) {
                ans += abs(x - mid);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func makeSubKSumEqual(arr []int, k int) (ans int64) {
	n := len(arr)
	g := gcd(n, k)
	for i := 0; i < g; i++ {
		t := []int{}
		for j := i; j < n; j += g {
			t = append(t, arr[j])
		}
		sort.Ints(t)
		mid := t[len(t)/2]
		for _, x := range t {
			ans += int64(abs(x - mid))
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts
function makeSubKSumEqual(arr: number[], k: number): number {
    const n = arr.length;
    const g = gcd(n, k);
    let ans = 0;
    for (let i = 0; i < g; ++i) {
        const t: number[] = [];
        for (let j = i; j < n; j += g) {
            t.push(arr[j]);
        }
        t.sort((a, b) => a - b);
        const mid = t[t.length >> 1];
        for (const x of t) {
            ans += Math.abs(x - mid);
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    if (b === 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

### **...**

```

```

<!-- tabs:end -->
