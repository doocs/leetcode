# [1502. 判断能否形成等差数列](https://leetcode.cn/problems/can-make-arithmetic-progression-from-sequence)

[English Version](/solution/1500-1599/1502.Can%20Make%20Arithmetic%20Progression%20From%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数字数组 <code>arr</code> 。</p>

<p>如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 <strong>等差数列</strong> 。</p>

<p>如果可以重新排列数组形成等差数列，请返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [3,5,1]
<strong>输出：</strong>true
<strong>解释：</strong>对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,4]
<strong>输出：</strong>false
<strong>解释：</strong>无法通过重新排序得到等差数列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>-10^6 &lt;= arr[i] &lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 遍历**

我们可以先将数组 `arr` 排序，然后遍历数组，判断相邻两项的差是否相等即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        arr.sort()
        d = arr[1] - arr[0]
        return all(b - a == d for a, b in pairwise(arr))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
