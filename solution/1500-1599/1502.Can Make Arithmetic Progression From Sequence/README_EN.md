# [1502. Can Make Arithmetic Progression From Sequence](https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence)

[中文文档](/solution/1500-1599/1502.Can%20Make%20Arithmetic%20Progression%20From%20Sequence/README.md)

## Description

<p>A sequence of numbers is called an <strong>arithmetic progression</strong> if the difference between any two consecutive elements is the same.</p>

<p>Given an array of numbers <code>arr</code>, return <code>true</code> <em>if the array can be rearranged to form an <strong>arithmetic progression</strong>. Otherwise, return</em> <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,5,1]
<strong>Output:</strong> true
<strong>Explanation: </strong>We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,4]
<strong>Output:</strong> false
<strong>Explanation: </strong>There is no way to reorder the elements to obtain an arithmetic progression.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>-10<sup>6</sup> &lt;= arr[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting + Traversal

We can first sort the array `arr`, then traverse the array, and check whether the difference between adjacent items is equal.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array `arr`.

<!-- tabs:start -->

```python
class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        arr.sort()
        d = arr[1] - arr[0]
        return all(b - a == d for a, b in pairwise(arr))
```

```java
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int d = arr[1] - arr[0];
        for (int i = 2; i < arr.length; ++i) {
            if (arr[i] - arr[i - 1] != d) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool canMakeArithmeticProgression(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int d = arr[1] - arr[0];
        for (int i = 2; i < arr.size(); i++) {
            if (arr[i] - arr[i - 1] != d) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func canMakeArithmeticProgression(arr []int) bool {
	sort.Ints(arr)
	d := arr[1] - arr[0]
	for i := 2; i < len(arr); i++ {
		if arr[i]-arr[i-1] != d {
			return false
		}
	}
	return true
}
```

```ts
function canMakeArithmeticProgression(arr: number[]): boolean {
    arr.sort((a, b) => a - b);
    const n = arr.length;
    for (let i = 2; i < n; i++) {
        if (arr[i - 2] - arr[i - 1] !== arr[i - 1] - arr[i]) {
            return false;
        }
    }
    return true;
}
```

```rust
impl Solution {
    pub fn can_make_arithmetic_progression(mut arr: Vec<i32>) -> bool {
        arr.sort();
        let n = arr.len();
        for i in 2..n {
            if arr[i - 2] - arr[i - 1] != arr[i - 1] - arr[i] {
                return false;
            }
        }
        true
    }
}
```

```js
/**
 * @param {number[]} arr
 * @return {boolean}
 */
var canMakeArithmeticProgression = function (arr) {
    arr.sort((a, b) => a - b);
    for (let i = 1; i < arr.length - 1; i++) {
        if (arr[i] << 1 != arr[i - 1] + arr[i + 1]) {
            return false;
        }
    }
    return true;
};
```

```c
int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

bool canMakeArithmeticProgression(int* arr, int arrSize) {
    qsort(arr, arrSize, sizeof(int), cmp);
    for (int i = 2; i < arrSize; i++) {
        if (arr[i - 2] - arr[i - 1] != arr[i - 1] - arr[i]) {
            return 0;
        }
    }
    return 1;
}
```

<!-- tabs:end -->

### Solution 2: Hash Table + Mathematics

We first find the minimum value $a$ and the maximum value $b$ in the array $arr$. If the array $arr$ can be rearranged into an arithmetic sequence, then the common difference $d = \frac{b - a}{n - 1}$ must be an integer.

We can use a hash table to record all elements in the array $arr$, then traverse $i \in [0, n)$, and check whether $a + d \times i$ is in the hash table. If not, it means that the array $arr$ cannot be rearranged into an arithmetic sequence, and we return `false`. Otherwise, after traversing the array, we return `true`.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array `arr`.

<!-- tabs:start -->

```python
class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        a = min(arr)
        b = max(arr)
        n = len(arr)
        if (b - a) % (n - 1):
            return False
        d = (b - a) // (n - 1)
        s = set(arr)
        return all(a + d * i in s for i in range(n))
```

```java
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        int a = arr[0], b = arr[0];
        Set<Integer> s = new HashSet<>();
        for (int x : arr) {
            a = Math.min(a, x);
            b = Math.max(b, x);
            s.add(x);
        }
        if ((b - a) % (n - 1) != 0) {
            return false;
        }
        int d = (b - a) / (n - 1);
        for (int i = 0; i < n; ++i) {
            if (!s.contains(a + d * i)) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool canMakeArithmeticProgression(vector<int>& arr) {
        auto [a, b] = minmax_element(arr.begin(), arr.end());
        int n = arr.size();
        if ((*b - *a) % (n - 1) != 0) {
            return false;
        }
        int d = (*b - *a) / (n - 1);
        unordered_set<int> s(arr.begin(), arr.end());
        for (int i = 0; i < n; ++i) {
            if (!s.count(*a + d * i)) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func canMakeArithmeticProgression(arr []int) bool {
	a, b := slices.Min(arr), slices.Max(arr)
	n := len(arr)
	if (b-a)%(n-1) != 0 {
		return false
	}
	d := (b - a) / (n - 1)
	s := map[int]bool{}
	for _, x := range arr {
		s[x] = true
	}
	for i := 0; i < n; i++ {
		if !s[a+i*d] {
			return false
		}
	}
	return true
}
```

```ts
function canMakeArithmeticProgression(arr: number[]): boolean {
    const n = arr.length;
    const map = new Map<number, number>();
    let min = Infinity;
    let max = -Infinity;
    for (const num of arr) {
        map.set(num, (map.get(num) ?? 0) + 1);
        min = Math.min(min, num);
        max = Math.max(max, num);
    }
    if (max === min) {
        return true;
    }
    if ((max - min) % (arr.length - 1)) {
        return false;
    }
    const diff = (max - min) / (arr.length - 1);
    for (let i = min; i <= max; i += diff) {
        if (map.get(i) !== 1) {
            return false;
        }
    }
    return true;
}
```

```rust
use std::collections::HashMap;
impl Solution {
    pub fn can_make_arithmetic_progression(arr: Vec<i32>) -> bool {
        let n = arr.len() as i32;
        let mut min = i32::MAX;
        let mut max = i32::MIN;
        let mut map = HashMap::new();
        for &num in arr.iter() {
            *map.entry(num).or_insert(0) += 1;
            min = min.min(num);
            max = max.max(num);
        }
        if min == max {
            return true;
        }
        if (max - min) % (n - 1) != 0 {
            return false;
        }
        let diff = (max - min) / (n - 1);
        let mut k = min;
        while k <= max {
            if *map.get(&k).unwrap_or(&0) != 1 {
                return false;
            }
            k += diff;
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- end -->
