---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1426.Counting%20Elements/README_EN.md
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1426. Counting Elements 🔒](https://leetcode.com/problems/counting-elements)

[中文文档](/solution/1400-1499/1426.Counting%20Elements/README.md)

## Description

<p>Given an integer array <code>arr</code>, count how many elements <code>x</code> there are, such that <code>x + 1</code> is also in <code>arr</code>. If there are duplicates in <code>arr</code>, count them separately.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 1 and 2 are counted cause 2 and 3 are in arr.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1,3,3,5,5,7,7]
<strong>Output:</strong> 0
<strong>Explanation:</strong> No numbers are counted, cause there is no 2, 4, 6, or 8 in arr.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use a hash table or array $cnt$ to record the frequency of each number in the array $arr$. Then, we traverse each number $x$ in $cnt$. If $x+1$ also exists in $cnt$, we add $cnt[x]$ to the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $arr$.

<!-- tabs:start -->

```python
class Solution:
    def countElements(self, arr: List[int]) -> int:
        cnt = Counter(arr)
        return sum(v for x, v in cnt.items() if cnt[x + 1])
```

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
