---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [面试题 16.24. 数对和](https://leetcode.cn/problems/pairs-with-sum-lcci)

[中文文档](/lcci/16.24.Pairs%20With%20Sum/README.md)

## 题目描述

<!-- description:start -->

<p>设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。</p>
<p><strong>示例 1:</strong></p>
<pre><strong>输入:</strong> nums = [5,6,5], target = 11
<strong>输出: </strong>[[5,6]]</pre>
<p><strong>示例 2:</strong></p>
<pre><strong>输入:</strong> nums = [5,6,5,6], target = 11
<strong>输出: </strong>[[5,6],[5,6]]</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>nums.length &lt;= 100000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

<!-- thinking:start -->

> **思考**
>
> 找出和为 $target$ 的数对，每个元素至多用一次。排序双指针可以，但要处理重复与「用过即删」。
>
> 一边扫描一边用计数表记住尚未配对的值：若 $target-x$ 仍有存量，则配成一对并减一，否则把 $x$ 入表。
>
> 这样保证先出现的补数优先配对，且不会把同一个下标用两次。一次遍历即可。

<!-- thinking:end -->

我们可以使用哈希表来存储数组中的元素，键为数组中的元素，值为该元素出现的次数。

遍历数组，对于每个元素 $x$，我们计算 $y = target - x$，如果哈希表中存在 $y$，则说明存在一对数 $(x, y)$，我们将其加入答案，并减少 $y$ 的出现次数。如果哈希表中不存在 $y$，则说明不存在这样的数对，我们将 $x$ 的出现次数加 $1$。

遍历结束后，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pairSums(self, nums: List[int], target: int) -> List[List[int]]:
        cnt = Counter()
        ans = []
        for x in nums:
            y = target - x
            if cnt[y]:
                cnt[y] -= 1
                ans.append([x, y])
            else:
                cnt[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        Map<Integer, Integer> cnt = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int x : nums) {
            int y = target - x;
            if (cnt.containsKey(y)) {
                ans.add(List.of(x, y));
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            } else {
                cnt.merge(x, 1, Integer::sum);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> pairSums(vector<int>& nums, int target) {
        unordered_map<int, int> cnt;
        vector<vector<int>> ans;
        for (int x : nums) {
            int y = target - x;
            if (cnt[y]) {
                --cnt[y];
                ans.push_back({x, y});
            } else {
                ++cnt[x];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func pairSums(nums []int, target int) (ans [][]int) {
	cnt := map[int]int{}
	for _, x := range nums {
		y := target - x
		if cnt[y] > 0 {
			cnt[y]--
			ans = append(ans, []int{x, y})
		} else {
			cnt[x]++
		}
	}
	return
}
```

#### TypeScript

```ts
function pairSums(nums: number[], target: number): number[][] {
    const cnt = new Map();
    const ans: number[][] = [];
    for (const x of nums) {
        const y = target - x;
        if (cnt.has(y)) {
            ans.push([x, y]);
            const yCount = cnt.get(y) - 1;
            if (yCount === 0) {
                cnt.delete(y);
            } else {
                cnt.set(y, yCount);
            }
        } else {
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func pairSums(_ nums: [Int], _ target: Int) -> [[Int]] {
        var countMap = [Int: Int]()
        var ans = [[Int]]()

        for x in nums {
            let y = target - x
            if let yCount = countMap[y], yCount > 0 {
                ans.append([x, y])
                countMap[y] = yCount - 1
                if countMap[y] == 0 {
                    countMap.removeValue(forKey: y)
                }
            } else {
                countMap[x, default: 0] += 1
            }
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
