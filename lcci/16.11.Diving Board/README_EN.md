---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.11.Diving%20Board/README_EN.md
---

# [16.11. Diving Board](https://leetcode.cn/problems/diving-board-lcci)

[中文文档](/lcci/16.11.Diving%20Board/README.md)

## Description

<p>You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of planks, one of length <code>shorter</code> and one of length <code>longer</code>. You must use exactly <code>K</code> planks of wood. Write a method to generate all possible lengths for the diving board.</p>

<p>return all lengths in non-decreasing order.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>

shorter = 1

longer = 2

k = 3

<strong>Output: </strong> {3,4,5,6}

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li>0 &lt; shorter &lt;= longer</li>
	<li>0 &lt;= k &lt;= 100000</li>
</ul>

## Solutions

### Solution 1: Case Analysis

If $k=0$, there is no solution, and we can directly return an empty list.

If $shorter=longer$, we can only use a board with length $longer \times k$, so we directly return a list with length $longer \times k$.

Otherwise, we can use a board with length $shorter \times (k-i) + longer \times i$, where $0 \leq i \leq k$. We enumerate $i$ in the range $[0, k]$, and calculate the corresponding length. For different values of $i$, we will not get the same length, because if $0 \leq i \lt j \leq k$, then the difference in length is $(i - j) \times (longer - shorter) \lt 0$. Therefore, for different values of $i$, we will get different lengths.

The time complexity is $O(k)$, where $k$ is the number of boards. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def divingBoard(self, shorter: int, longer: int, k: int) -> List[int]:
        if k == 0:
            return []
        if longer == shorter:
            return [longer * k]
        ans = []
        for i in range(k + 1):
            ans.append(longer * i + shorter * (k - i))
        return ans
```

```java
class Solution {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (longer == shorter) {
            return new int[] {longer * k};
        }
        int[] ans = new int[k + 1];
        for (int i = 0; i < k + 1; ++i) {
            ans[i] = longer * i + shorter * (k - i);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> divingBoard(int shorter, int longer, int k) {
        if (k == 0) return {};
        if (longer == shorter) return {longer * k};
        vector<int> ans;
        for (int i = 0; i < k + 1; ++i)
            ans.push_back(longer * i + shorter * (k - i));
        return ans;
    }
};
```

```go
func divingBoard(shorter int, longer int, k int) []int {
	if k == 0 {
		return []int{}
	}
	if longer == shorter {
		return []int{longer * k}
	}
	var ans []int
	for i := 0; i < k+1; i++ {
		ans = append(ans, longer*i+shorter*(k-i))
	}
	return ans
}
```

```ts
function divingBoard(shorter: number, longer: number, k: number): number[] {
    if (k === 0) {
        return [];
    }
    if (longer === shorter) {
        return [longer * k];
    }
    const ans: number[] = [k + 1];
    for (let i = 0; i <= k; ++i) {
        ans[i] = longer * i + shorter * (k - i);
    }
    return ans;
}
```

```swift
class Solution {
    func divingBoard(_ shorter: Int, _ longer: Int, _ k: Int) -> [Int] {
        if k == 0 {
            return []
        }
        if shorter == longer {
            return [shorter * k]
        }

        var ans = [Int](repeating: 0, count: k + 1)
        for i in 0...k {
            ans[i] = longer * i + shorter * (k - i)
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
