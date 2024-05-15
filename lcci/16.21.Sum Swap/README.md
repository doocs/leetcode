---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.21.Sum%20Swap/README.md
---

# [面试题 16.21. 交换和](https://leetcode.cn/problems/sum-swap-lcci)

[English Version](/lcci/16.21.Sum%20Swap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。</p>

<p>返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
<strong>输出:</strong> [1, 3]
</pre>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> array1 = <code>[1, 2, 3], array2 = [4, 5, 6]</code>
<strong>输出: </strong>[]</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= array1.length, array2.length &lt;= 100000</code></li>
</ul>

## 解法

### 方法一：哈希表

我们先求出两个数组的和，然后计算两个数组和的差值 $diff$。如果 $diff$ 为奇数，则说明两个数组的和不可能相等，直接返回空数组。

如果 $diff$ 为偶数，那么我们可以遍历其中一个数组，假设当前遍历到的元素为 $a$，则另一个数组中需要找到一个元素 $b$，使得 $a - b = diff / 2$，即 $b = a - diff / 2$。我们可以使用哈希表来快速查找 $b$ 是否存在。如果存在，则说明找到了一对符合条件的元素，直接返回即可。

时间复杂度 $O(m + n)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别为两个数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def findSwapValues(self, array1: List[int], array2: List[int]) -> List[int]:
        diff = sum(array1) - sum(array2)
        if diff & 1:
            return []
        diff >>= 1
        s = set(array2)
        for a in array1:
            if (b := (a - diff)) in s:
                return [a, b]
        return []
```

```java
class Solution {
    public int[] findSwapValues(int[] array1, int[] array2) {
        long s1 = 0, s2 = 0;
        Set<Integer> s = new HashSet<>();
        for (int x : array1) {
            s1 += x;
        }
        for (int x : array2) {
            s2 += x;
            s.add(x);
        }
        long diff = s1 - s2;
        if (diff % 2 != 0) {
            return new int[0];
        }
        diff /= 2;
        for (int a : array1) {
            int b = (int) (a - diff);
            if (s.contains(b)) {
                return new int[] {a, b};
            }
        }
        return new int[0];
    }
}
```

```cpp
class Solution {
public:
    vector<int> findSwapValues(vector<int>& array1, vector<int>& array2) {
        long long s1 = accumulate(array1.begin(), array1.end(), 0LL);
        long long s2 = accumulate(array2.begin(), array2.end(), 0LL);
        long long diff = s1 - s2;
        if (diff & 1) {
            return {};
        }
        diff >>= 1;
        unordered_set<int> s(array2.begin(), array2.end());
        for (int x : array1) {
            int y = x - diff;
            if (s.count(y)) {
                return {x, y};
            }
        }
        return {};
    }
};
```

```go
func findSwapValues(array1 []int, array2 []int) []int {
	s1, s2 := 0, 0
	s := map[int]bool{}
	for _, a := range array1 {
		s1 += a
	}
	for _, b := range array2 {
		s2 += b
		s[b] = true
	}
	diff := s1 - s2
	if (diff & 1) == 1 {
		return []int{}
	}
	diff >>= 1
	for _, a := range array1 {
		if b := a - diff; s[b] {
			return []int{a, b}
		}
	}
	return []int{}
}
```

```ts
function findSwapValues(array1: number[], array2: number[]): number[] {
    const s1 = array1.reduce((a, b) => a + b, 0);
    const s2 = array2.reduce((a, b) => a + b, 0);
    let diff = s1 - s2;
    if (diff & 1) {
        return [];
    }
    diff >>= 1;
    const s: Set<number> = new Set(array2);
    for (const x of array1) {
        const y = x - diff;
        if (s.has(y)) {
            return [x, y];
        }
    }
    return [];
}
```

```swift
class Solution {
    func findSwapValues(_ array1: [Int], _ array2: [Int]) -> [Int] {
        var s1 = 0, s2 = 0
        var set = Set<Int>()

        for x in array1 {
            s1 += x
        }
        for x in array2 {
            s2 += x
            set.insert(x)
        }

        let diff = s1 - s2
        if diff % 2 != 0 {
            return []
        }
        let target = diff / 2

        for a in array1 {
            let b = a - target
            if set.contains(b) {
                return [a, b]
            }
        }
        return []
    }
}
```

<!-- tabs:end -->

<!-- end -->
