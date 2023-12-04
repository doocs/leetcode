# [1426. 数元素](https://leetcode.cn/problems/counting-elements)

[English Version](/solution/1400-1499/1426.Counting%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>， 对于元素 <code>x</code> ，只有当 <code>x + 1</code> 也在数组&nbsp;<code>arr</code> 里时，才能记为 <code>1</code> 个数。</p>

<p>如果数组&nbsp;<code>arr</code> 里有重复的数，每个重复的数单独计算。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>1 和 2 被计算次数因为 2 和 3 在数组 arr 里。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,3,3,5,5,7,7]
<strong>输出：</strong>0
<strong>解释：</strong>所有的数都不算, 因为数组里没有 2、4、6、8。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们可以用一个哈希表或数组 $cnt$ 记录数组 $arr$ 中的每个数出现的次数，然后遍历 $cnt$ 中的每个数 $x$，如果 $x+1$ 也在 $cnt$ 中，那么就将 $cnt[x]$ 加到答案中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countElements(self, arr: List[int]) -> int:
        cnt = Counter(arr)
        return sum(v for x, v in cnt.items() if cnt[x + 1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countElements(int[] arr) {
        int[] cnt = new int[1001];
        for (int x : arr) {
            ++cnt[x];
        }
        int ans = 0;
        for (int x = 0; x < 1000; ++x) {
            if (cnt[x + 1] > 0) {
                ans += cnt[x];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countElements(vector<int>& arr) {
        int cnt[1001]{};
        for (int x : arr) {
            ++cnt[x];
        }
        int ans = 0;
        for (int x = 0; x < 1000; ++x) {
            if (cnt[x + 1]) {
                ans += cnt[x];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countElements(arr []int) (ans int) {
	mx := slices.Max(arr)
	cnt := make([]int, mx+1)
	for _, x := range arr {
		cnt[x]++
	}
	for x := 0; x < mx; x++ {
		if cnt[x+1] > 0 {
			ans += cnt[x]
		}
	}
	return
}
```

### **TypeScript**

```ts
function countElements(arr: number[]): number {
    const mx = Math.max(...arr);
    const cnt = Array(mx + 1).fill(0);
    for (const x of arr) {
        ++cnt[x];
    }
    let ans = 0;
    for (let i = 0; i < mx; ++i) {
        if (cnt[i + 1] > 0) {
            ans += cnt[i];
        }
    }
    return ans;
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var countElements = function (arr) {
    const mx = Math.max(...arr);
    const cnt = Array(mx + 1).fill(0);
    for (const x of arr) {
        ++cnt[x];
    }
    let ans = 0;
    for (let i = 0; i < mx; ++i) {
        if (cnt[i + 1] > 0) {
            ans += cnt[i];
        }
    }
    return ans;
};
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn count_elements(arr: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        for &num in &arr {
            *cnt.entry(num).or_insert(0) += 1;
        }
        cnt.iter()
            .filter(|(&x, _)| cnt.contains_key(&(x + 1)))
            .map(|(_, &v)| v)
            .sum()
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function countElements($arr) {
        $cnt = array_count_values($arr);
        $ans = 0;
        foreach ($cnt as $x => $v) {
            if (isset($cnt[$x + 1])) {
                $ans += $v;
            }
        }
        return $ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
