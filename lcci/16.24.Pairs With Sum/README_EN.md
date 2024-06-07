---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.24.Pairs%20With%20Sum/README_EN.md
---

<!-- problem:start -->

# [16.24. Pairs With Sum](https://leetcode.cn/problems/pairs-with-sum-lcci)

## Description

<!-- description:start -->

<p>Design an algorithm to find all pairs of integers within an array which sum to a specified value.</p>
<p><strong>Example 1:</strong></p>
<pre>

<strong>Input:</strong> nums = [5,6,5], target = 11

<strong>Output: </strong>[[5,6]]</pre>

<p><strong>Example 2:</strong></p>
<pre>

<strong>Input:</strong> nums = [5,6,5,6], target = 11

<strong>Output: </strong>[[5,6],[5,6]]</pre>

<p><strong>Note: </strong></p>
<ul>
	<li><code>nums.length &lt;= 100000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table to store the elements in the array, with the keys being the elements in the array and the values being the number of times the element appears.

We traverse the array, and for each element $x$, we calculate $y = target - x$. If $y$ exists in the hash table, it means that there is a pair of numbers $(x, y)$ that add up to the target, and we add it to the answer and reduce the count of $y$ by $1$. If $y$ does not exist in the hash table, it means that there is no such pair of numbers, and we increase the count of $x$ by $1$.

After the traversal, we can obtain the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

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
