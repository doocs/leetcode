---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0001.Two%20Sum/README_EN.md
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1. Two Sum](https://leetcode.com/problems/two-sum)

[中文文档](/solution/0000-0099/0001.Two%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code>&nbsp;and an integer <code>target</code>, return <em>indices of the two numbers such that they add up to <code>target</code></em>.</p>

<p>You may assume that each input would have <strong><em>exactly</em> one solution</strong>, and you may not use the <em>same</em> element twice.</p>

<p>You can return the answer in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,11,15], target = 9
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> Because nums[0] + nums[1] == 9, we return [0, 1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4], target = 6
<strong>Output:</strong> [1,2]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3], target = 6
<strong>Output:</strong> [0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><strong>Only one valid answer exists.</strong></li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:&nbsp;</strong>Can you come up with an algorithm that is less than <code>O(n<sup>2</sup>)</code><font face="monospace">&nbsp;</font>time complexity?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{d}$ to store each element and its corresponding index.

Traverse the array $\textit{nums}$, for the current element $\textit{nums}[i]$, we first check if $\textit{target} - \textit{nums}[i]$ is in the hash table $\textit{d}$. If it is in $\textit{d}$, it means the $\textit{target}$ value has been found, and we return the indices of $\textit{target} - \textit{nums}[i]$ and $i$.

Time complexity is $O(n)$, and space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        d = {}
        for i, x in enumerate(nums):
            if (y := target - x) in d:
                return [d[y], i]
            d[x] = i
```

#### Java

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (d.containsKey(y)) {
                return new int[] {d.get(y), i};
            }
            d.put(x, i);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> d;
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (d.contains(y)) {
                return {d[y], i};
            }
            d[x] = i;
        }
    }
};
```

#### Go

```go
func twoSum(nums []int, target int) []int {
	d := map[int]int{}
	for i := 0; ; i++ {
		x := nums[i]
		y := target - x
		if j, ok := d[y]; ok {
			return []int{j, i}
		}
		d[x] = i
	}
}
```

#### TypeScript

```ts
function twoSum(nums: number[], target: number): number[] {
    const d = new Map<number, number>();
    for (let i = 0; ; ++i) {
        const x = nums[i];
        const y = target - x;
        if (d.has(y)) {
            return [d.get(y)!, i];
        }
        d.set(x, i);
    }
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut d = HashMap::new();
        for (i, &x) in nums.iter().enumerate() {
            let y = target - x;
            if let Some(&j) = d.get(&y) {
                return vec![j as i32, i as i32];
            }
            d.insert(x, i);
        }
        vec![]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    const d = new Map();
    for (let i = 0; ; ++i) {
        const x = nums[i];
        const y = target - x;
        if (d.has(y)) {
            return [d.get(y), i];
        }
        d.set(x, i);
    }
};
```

#### C#

```cs
public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        var d = new Dictionary<int, int>();
        for (int i = 0, j; ; ++i) {
            int x = nums[i];
            int y = target - x;
            if (d.TryGetValue(y, out j)) {
                return new [] {j, i};
            }
            if (!d.ContainsKey(x)) {
                d.Add(x, i);
            }
        }
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function twoSum($nums, $target) {
        $d = [];
        foreach ($nums as $i => $x) {
            $y = $target - $x;
            if (isset($d[$y])) {
                return [$d[$y], $i];
            }
            $d[$x] = $i;
        }
    }
}
```

#### Scala

```scala
import scala.collection.mutable

object Solution {
    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
        val d = mutable.Map[Int, Int]()
        var ans: Array[Int] = Array()
        for (i <- nums.indices if ans.isEmpty) {
            val x = nums(i)
            val y = target - x
            if (d.contains(y)) {
                ans = Array(d(y), i)
            } else {
                d(x) = i
            }
        }
        ans
    }
}
```

#### Swift

```swift
class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var d = [Int: Int]()
        for (i, x) in nums.enumerated() {
            let y = target - x
            if let j = d[y] {
                return [j, i]
            }
            d[x] = i
        }
        return []
    }
}
```

#### Ruby

```rb
# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[]}
def two_sum(nums, target)
    d = {}
    nums.each_with_index do |x, i|
      y = target - x
      if d.key?(y)
        return [d[y], i]
      end
      d[x] = i
    end
end
```

#### Kotlin

```kotlin
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val m = mutableMapOf<Int, Int>()
        nums.forEachIndexed { i, x ->
            val y = target - x
            val j = m.get(y)
            if (j != null) {
                return intArrayOf(j, i)
            }
            m[x] = i
        }
        return intArrayOf()
    }
}
```

#### Nim

```nim
import std/enumerate
import std/tables

proc twoSum(nums: seq[int], target: int): seq[int] =
  var d = initTable[int, int]()
  for i, x in nums.pairs():
    let y = target - x
    if d.hasKey(y):
      return @[d[y], i]
    d[x] = i
  return @[]
```

#### Cangjie

```cj
class Solution {
    func twoSum(nums: Array<Int64>, target: Int64): Array<Int64> {
        let d = HashMap<Int64, Int64>()
        for (i in 0..nums.size) {
            if (d.contains(target - nums[i])) {
                return [d[target - nums[i]], i]
            }
            d[nums[i]] = i
        }
        []
    }
}
```

#### C

```c
#include <stdlib.h>

int* twoSum(int* nums, int numsSize, int target, int* returnSize) {
    int capacity = 1;
    while (capacity < numsSize * 2) capacity <<= 1;
    int* keys = malloc(capacity * sizeof(int));
    int* vals = malloc(capacity * sizeof(int));
    char* used = calloc(capacity, sizeof(char));
    if (!keys || !vals || !used) {
        free(keys);
        free(vals);
        free(used);
        *returnSize = 0;
        return NULL;
    }
    for (int i = 0; i < numsSize; ++i) {
        int x = nums[i];
        int y = target - x;
        unsigned int h = (unsigned int) y & (capacity - 1);
        while (used[h]) {
            if (keys[h] == y) {
                int* res = malloc(2 * sizeof(int));
                res[0] = vals[h];
                res[1] = i;
                *returnSize = 2;
                free(keys);
                free(vals);
                free(used);
                return res;
            }
            h = (h + 1) & (capacity - 1);
        }
        unsigned int h2 = (unsigned int) x & (capacity - 1);
        while (used[h2]) h2 = (h2 + 1) & (capacity - 1);
        used[h2] = 1;
        keys[h2] = x;
        vals[h2] = i;
    }
    *returnSize = 0;
    free(keys);
    free(vals);
    free(used);
    return NULL;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
