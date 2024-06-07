---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.16.The%20Masseuse/README_EN.md
---

<!-- problem:start -->

# [17.16. The Masseuse](https://leetcode.cn/problems/the-masseuse-lcci)

[中文文档](/lcci/17.16.The%20Masseuse/README.md)

## Description

<!-- description:start -->

<p>A popular masseuse receives a sequence of back-to-back appointment requests and is debating which ones to accept. She needs a break between appointments and therefore she cannot accept any adjacent requests. Given a sequence of back-to-back appoint&shy; ment requests, find the optimal (highest total booked minutes) set the masseuse can honor. Return the number of minutes.</p>

<p><b>Note:&nbsp;</b>This problem is slightly different from the original one in the book.</p>

<p>&nbsp;</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong> [1,2,3,1]

<strong>Output: </strong> 4

<strong>Explanation: </strong> Accept request 1 and 3, total minutes = 1 + 3 = 4

</pre>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong> [2,7,9,3,1]

<strong>Output: </strong> 12

<strong>Explanation: </strong> Accept request 1, 3 and 5, total minutes = 2 + 9 + 1 = 12

</pre>

<p><strong>Example 3: </strong></p>

<pre>

<strong>Input: </strong> [2,1,4,5,3,1,1,3]

<strong>Output: </strong> 12

<strong>Explanation: </strong> Accept request 1, 3, 5 and 8, total minutes = 2 + 4 + 3 + 3 = 12

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def massage(self, nums: List[int]) -> int:
        f = g = 0
        for x in nums:
            f, g = g + x, max(f, g)
        return max(f, g)
```

#### Java

```java
class Solution {
    public int massage(int[] nums) {
        int f = 0, g = 0;
        for (int x : nums) {
            int ff = g + x;
            int gg = Math.max(f, g);
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int massage(vector<int>& nums) {
        int f = 0, g = 0;
        for (int& x : nums) {
            int ff = g + x;
            int gg = max(f, g);
            f = ff;
            g = gg;
        }
        return max(f, g);
    }
};
```

#### Go

```go
func massage(nums []int) int {
	f, g := 0, 0
	for _, x := range nums {
		f, g = g+x, max(f, g)
	}
	return max(f, g)
}
```

#### TypeScript

```ts
function massage(nums: number[]): number {
    let f = 0,
        g = 0;
    for (const x of nums) {
        const ff = g + x;
        const gg = Math.max(f, g);
        f = ff;
        g = gg;
    }
    return Math.max(f, g);
}
```

#### Swift

```swift
class Solution {
    func massage(_ nums: [Int]) -> Int {
        var f = 0
        var g = 0

        for x in nums {
            let ff = g + x
            let gg = max(f, g)
            f = ff
            g = gg
        }

        return max(f, g)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
