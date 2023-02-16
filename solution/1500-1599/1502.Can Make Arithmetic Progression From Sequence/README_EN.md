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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        arr.sort()
        d = arr[1] - arr[0]
        return all(b - a == d for a, b in pairwise(arr))
```

### **Java**

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

### **C++**

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

### **Go**

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

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {boolean}
 */
var canMakeArithmeticProgression = function (arr) {
    arr.sort((a, b) => a - b);
    for (let i = 1; i < arr.length - 1; i++) {
        if (arr[i] << 1 != arr[i - 1] + arr[i + 1]) return false;
    }
    return true;
};
```

### **TypeScript**

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

### **Rust**

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

### **C**

```c
int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

bool canMakeArithmeticProgression(int *arr, int arrSize) {
    qsort(arr, arrSize, sizeof(int), cmp);
    for (int i = 2; i < arrSize; i++) {
        if (arr[i - 2] - arr[i - 1] != arr[i - 1] - arr[i]) {
            return 0;
        }
    }
    return 1;
}
```

### **...**

```

```

<!-- tabs:end -->
