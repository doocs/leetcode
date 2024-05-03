# [10.11. Peaks and Valleys](https://leetcode.cn/problems/peaks-and-valleys-lcci)

[中文文档](/lcci/10.11.Peaks%20and%20Valleys/README.md)

## Description

<p>In an array of integers, a &quot;peak&quot; is an element which is greater than or equal to the adjacent integers and a &quot;valley&quot; is an element which is less than or equal to the adjacent inte&shy;gers. For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {5, 2} are valleys. Given an array of integers, sort the array into an alternating sequence of peaks and valleys.</p>
<p><strong>Example:</strong></p>
<pre>

<strong>Input: </strong>[5, 3, 1, 2, 3]

<strong>Output:</strong>&nbsp;[5, 1, 3, 2, 3]

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>nums.length &lt;= 10000</code></li>
</ul>

## Solutions

### Solution 1: Sorting

We first sort the array, and then traverse the array and swap the elements at even indices with their next element.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def wiggleSort(self, nums: List[int]) -> None:
        nums.sort()
        for i in range(0, len(nums), 2):
            nums[i : i + 2] = nums[i : i + 2][::-1]
```

```java
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i += 2) {
            int t = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = t;
        }
    }
}
```

```cpp
class Solution {
public:
    void wiggleSort(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        for (int i = 0; i < n - 1; i += 2) {
            swap(nums[i], nums[i + 1]);
        }
    }
};
```

```go
func wiggleSort(nums []int) {
	sort.Ints(nums)
	for i := 0; i < len(nums)-1; i += 2 {
		nums[i], nums[i+1] = nums[i+1], nums[i]
	}
}
```

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function wiggleSort(nums: number[]): void {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    for (let i = 0; i < n - 1; i += 2) {
        [nums[i], nums[i + 1]] = [nums[i + 1], nums[i]];
    }
}
```

```swift
class Solution {
    func wiggleSort(_ nums: inout [Int]) {
        nums.sort()

        let n = nums.count

        for i in stride(from: 0, to: n - 1, by: 2) {
            let temp = nums[i]
            nums[i] = nums[i + 1]
            nums[i + 1] = temp
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
