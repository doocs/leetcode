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

We can use the hash table $m$ to store the array value and the corresponding subscript.

Traverse the array `nums`, when you find `target - nums[i]` in the hash table, it means that the target value is found, and the index of `target - nums[i]` and $i$ are returned.

The time complexity is $O(n)$ and the space complexity is $O(n)$. Where $n$ is the length of the array `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        m = {}
        for i, x in enumerate(nums):
            y = target - x
            if y in m:
                return [m[y], i]
            m[x] = i
```

#### Java

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.containsKey(y)) {
                return new int[] {m.get(y), i};
            }
            m.put(x, i);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> m;
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.count(y)) {
                return {m[y], i};
            }
            m[x] = i;
        }
    }
};
```

#### Go

```go
func twoSum(nums []int, target int) []int {
	m := map[int]int{}
	for i := 0; ; i++ {
		x := nums[i]
		y := target - x
		if j, ok := m[y]; ok {
			return []int{j, i}
		}
		m[x] = i
	}
}
```

#### TypeScript

```ts
function twoSum(nums: number[], target: number): number[] {
    const m: Map<number, number> = new Map();

    for (let i = 0; ; ++i) {
        const x = nums[i];
        const y = target - x;

        if (m.has(y)) {
            return [m.get(y)!, i];
        }

        m.set(x, i);
    }
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut m = HashMap::new();
        for (i, &x) in nums.iter().enumerate() {
            let y = target - x;
            if let Some(&j) = m.get(&y) {
                return vec![j as i32, i as i32];
            }
            m.insert(x, i as i32);
        }
        unreachable!()
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
    const m = new Map();
    for (let i = 0; ; ++i) {
        const x = nums[i];
        const y = target - x;
        if (m.has(y)) {
            return [m.get(y), i];
        }
        m.set(x, i);
    }
};
```

#### C#

```cs
public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        var m = new Dictionary<int, int>();
        for (int i = 0, j; ; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.TryGetValue(y, out j)) {
                return new [] {j, i};
            }
            if (!m.ContainsKey(x)) {
                m.Add(x, i);
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
        $m = [];
        foreach ($nums as $i => $x) {
            $y = $target - $x;
            if (isset($m[$y])) {
                return [$m[$y], $i];
            }
            $m[$x] = $i;
        }
    }
}
```

#### Scala

```scala
import scala.collection.mutable

object Solution {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    var map = new mutable.HashMap[Int, Int]()
    for (i <- 0 to nums.length) {
      if (map.contains(target - nums(i))) {
        return Array(map(target - nums(i)), i)
      } else {
        map += (nums(i) -> i)
      }
    }
    Array(0, 0)
  }
}
```

#### Swift

```swift
class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var m = [Int: Int]()
        var i = 0
        while true {
            let x = nums[i]
            let y = target - nums[i]
            if let j = m[target - nums[i]] {
                return [j, i]
            }
            m[nums[i]] = i
            i += 1
        }
    }
}
```

#### Ruby

```rb
# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[]}
def two_sum(nums, target)
  nums.each_with_index do |x, idx|
    if nums.include? target - x
      return [idx, nums.index(target - x)] if nums.index(target - x) != idx
    end
    next
  end
end
```

#### Nim

```nim
import std/enumerate

proc twoSum(nums: seq[int], target: int): seq[int] =
    var
        bal: int
        tdx: int
    for idx, val in enumerate(nums):
        bal = target - val
        if bal in nums:
            tdx = nums.find(bal)
            if idx != tdx:
                return @[idx, tdx]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
