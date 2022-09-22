# [1640. 能否连接形成数组](https://leetcode.cn/problems/check-array-formation-through-concatenation)

[English Version](/solution/1600-1699/1640.Check%20Array%20Formation%20Through%20Concatenation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code> ，数组中的每个整数 <strong>互不相同</strong> 。另有一个由整数数组构成的数组 <code>pieces</code>，其中的整数也 <strong>互不相同</strong> 。请你以 <strong>任意顺序</strong> 连接 <code>pieces</code> 中的数组以形成 <code>arr</code> 。但是，<strong>不允许</strong> 对每个数组 <code>pieces[i]</code> 中的整数重新排序。</p>

<p>如果可以连接<em> </em><code>pieces</code> 中的数组形成 <code>arr</code> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [15,88], pieces = [[88],[15]]
<strong>输出：</strong>true
<strong>解释：</strong>依次连接 <code>[15]</code> 和 <code>[88]</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [49,18,16], pieces = [[16,18,49]]
<strong>输出：</strong>false
<strong>解释：</strong>即便数字相符，也不能重新排列 pieces[0]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
<strong>输出：</strong>true
<strong>解释：</strong>依次连接 <code>[91]</code>、<code>[4,64]</code> 和 <code>[78]</code></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pieces.length &lt;= arr.length &lt;= 100</code></li>
	<li><code>sum(pieces[i].length) == arr.length</code></li>
	<li><code>1 &lt;= pieces[i].length &lt;= arr.length</code></li>
	<li><code>1 &lt;= arr[i], pieces[i][j] &lt;= 100</code></li>
	<li><code>arr</code> 中的整数 <strong>互不相同</strong></li>
	<li><code>pieces</code> 中的整数 <strong>互不相同</strong>（也就是说，如果将 <code>pieces</code> 扁平化成一维数组，数组中的所有整数互不相同）</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

遍历 `arr`，在 `pieces` 中找到首元素等于当前 `arr[i]` 的数组项，如果找不到，直接返回 `false`。

如果找到了，我们记数组项为 `pieces[k]`，然后继续往后遍历 `arr[i]` 和 `pieces[k]`，直至 `pieces[k]` 遍历完或者元素不等。

遍历结束，返回 `true`。

**方法二：哈希表**

创建一个哈希表，键为 `pieces` 中每个数组项的首元素，值为数组项。

遍历 `arr`，如果当前元素在哈希表中不存在，直接返回 `false`；否则，取出哈希表中对应的数组项，判断其与 `arr` 中的元素是否相等，如果不相等，直接返回 `false`。

否则，遍历结束，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canFormArray(self, arr: List[int], pieces: List[List[int]]) -> bool:
        i = 0
        while i < len(arr):
            k = 0
            while k < len(pieces) and pieces[k][0] != arr[i]:
                k += 1
            if k == len(pieces):
                return False
            j = 0
            while j < len(pieces[k]) and arr[i] == pieces[k][j]:
                i, j = i + 1, j + 1
        return True
```

```python
class Solution:
    def canFormArray(self, arr: List[int], pieces: List[List[int]]) -> bool:
        d = {p[0]: p for p in pieces}
        i, n = 0, len(arr)
        while i < n:
            if arr[i] not in d:
                return False
            p = d[arr[i]]
            if arr[i: i + len(p)] != p:
                return False
            i += len(p)
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        for (int i = 0; i < arr.length;) {
            int k = 0;
            while (k < pieces.length && pieces[k][0] != arr[i]) {
                ++k;
            }
            if (k == pieces.length) {
                return false;
            }
            int j = 0;
            while (j < pieces[k].length && arr[i] == pieces[k][j]) {
                ++i;
                ++j;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> d = new HashMap<>();
        for (var p : pieces) {
            d.put(p[0], p);
        }
        for (int i = 0; i < arr.length;) {
            if (!d.containsKey(arr[i])) {
                return false;
            }
            for (int v : d.get(arr[i])) {
                if (arr[i++] != v) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### **C++**

### **C++**

```cpp
class Solution {
public:
    bool canFormArray(vector<int>& arr, vector<vector<int>>& pieces) {
        for (int i = 0; i < arr.size();) {
            int k = 0;
            while (k < pieces.size() && pieces[k][0] != arr[i]) {
                ++k;
            }
            if (k == pieces.size()) {
                return false;
            }
            int j = 0;
            while (j < pieces[k].size() && arr[i] == pieces[k][j]) {
                ++i;
                ++j;
            }
        }
        return true;
    }
};
```

```cpp
class Solution {
public:
    bool canFormArray(vector<int>& arr, vector<vector<int>>& pieces) {
        unordered_map<int, vector<int>> d;
        for (auto& p : pieces) {
            d[p[0]] = p;
        }
        for (int i = 0; i < arr.size();) {
            if (!d.count(arr[i])) {
                return false;
            }
            for (int& v : d[arr[i]]) {
                if (arr[i++] != v) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

### **Go**

```go
func canFormArray(arr []int, pieces [][]int) bool {
	for i := 0; i < len(arr); {
		k := 0
		for k < len(pieces) && pieces[k][0] != arr[i] {
			k++
		}
		if k == len(pieces) {
			return false
		}
		j := 0
		for j < len(pieces[k]) && arr[i] == pieces[k][j] {
			i, j = i+1, j+1
		}
	}
	return true
}
```

```go
func canFormArray(arr []int, pieces [][]int) bool {
	d := map[int][]int{}
	for _, p := range pieces {
		d[p[0]] = p
	}
	for i := 0; i < len(arr); {
		p, ok := d[arr[i]]
		if !ok {
			return false
		}
		for _, v := range p {
			if arr[i] != v {
				return false
			}
			i++
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @param {number[][]} pieces
 * @return {boolean}
 */
var canFormArray = function (arr, pieces) {
    const d = new Map();
    for (const p of pieces) {
        d.set(p[0], p);
    }
    for (let i = 0; i < arr.length; ) {
        if (!d.has(arr[i])) {
            return false;
        }
        const p = d.get(arr[i]);
        for (const v of p) {
            if (arr[i++] != v) {
                return false;
            }
        }
    }
    return true;
};
```

### **TypeScript**

```ts
function canFormArray(arr: number[], pieces: number[][]): boolean {
    const n = arr.length;
    let i = 0;
    while (i < n) {
        const target = arr[i];
        const items = pieces.find(v => v[0] === target);
        if (items == null) {
            return false;
        }
        for (const item of items) {
            if (item !== arr[i]) {
                return false;
            }
            i++;
        }
    }
    return true;
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn can_form_array(arr: Vec<i32>, pieces: Vec<Vec<i32>>) -> bool {
        let n = arr.len();
        let mut map = HashMap::new();
        for (i, v) in pieces.iter().enumerate() {
            map.insert(v[0], i);
        }
        let mut i = 0;
        while i < n {
            match map.get(&arr[i]) {
                None => return false,
                Some(&j) => {
                    for &item in pieces[j].iter() {
                        if item != arr[i] {
                            return false;
                        }
                        i += 1;
                    }
                }
            }
        }
        true
    }
}
```

### **...**

```

```

<!-- tabs:end -->
