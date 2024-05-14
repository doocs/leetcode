# [2425. Bitwise XOR of All Pairings](https://leetcode.com/problems/bitwise-xor-of-all-pairings)

[中文文档](/solution/2400-2499/2425.Bitwise%20XOR%20of%20All%20Pairings/README.md)

<!-- tags:Bit Manipulation,Brainteaser,Array -->

<!-- difficulty:Medium -->

## Description

<p>You are given two <strong>0-indexed</strong> arrays, <code>nums1</code> and <code>nums2</code>, consisting of non-negative integers. There exists another array, <code>nums3</code>, which contains the bitwise XOR of <strong>all pairings</strong> of integers between <code>nums1</code> and <code>nums2</code> (every integer in <code>nums1</code> is paired with every integer in <code>nums2</code> <strong>exactly once</strong>).</p>

<p>Return<em> the <strong>bitwise XOR</strong> of all integers in </em><code>nums3</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,1,3], nums2 = [10,2,5,0]
<strong>Output:</strong> 13
<strong>Explanation:</strong>
A possible nums3 array is [8,0,7,2,11,3,4,1,9,1,6,3].
The bitwise XOR of all these numbers is 13, so we return 13.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2], nums2 = [3,4]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
All possible pairs of bitwise XORs are nums1[0] ^ nums2[0], nums1[0] ^ nums2[1], nums1[1] ^ nums2[0],
and nums1[1] ^ nums2[1].
Thus, one possible nums3 array is [2,5,1,6].
2 ^ 5 ^ 1 ^ 6 = 0, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Quick Thinking + Bit Manipulation

Since each element of the array will be XORed with each element of another array, we know that the result remains the same when the same number is XORed twice, i.e., $a \oplus a = 0$. Therefore, we only need to count the length of the array to know how many times each element is XORed with each element of another array.

If the length of the `nums2` array is odd, it means that each element in `nums1` has been XORed an odd number of times with each element in `nums2`, so the final XOR result of the `nums1` array is the XOR result of all elements in the `nums1` array. If it is even, it means that each element in `nums1` has been XORed an even number of times with each element in `nums2`, so the final XOR result of the `nums1` array is 0.

Similarly, we can know the final XOR result of the `nums2` array.

Finally, XOR the two results again to get the final result.

The time complexity is $O(m+n)$. Where $m$ and $n$ are the lengths of the `nums1` and `nums2` arrays, respectively.

<!-- tabs:start -->

```python
class Solution:
    def xorAllNums(self, nums1: List[int], nums2: List[int]) -> int:
        ans = 0
        if len(nums2) & 1:
            for v in nums1:
                ans ^= v
        if len(nums1) & 1:
            for v in nums2:
                ans ^= v
        return ans
```

```java
class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int ans = 0;
        if (nums2.length % 2 == 1) {
            for (int v : nums1) {
                ans ^= v;
            }
        }
        if (nums1.length % 2 == 1) {
            for (int v : nums2) {
                ans ^= v;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int xorAllNums(vector<int>& nums1, vector<int>& nums2) {
        int ans = 0;
        if (nums2.size() % 2 == 1) {
            for (int v : nums1) {
                ans ^= v;
            }
        }
        if (nums1.size() % 2 == 1) {
            for (int v : nums2) {
                ans ^= v;
            }
        }
        return ans;
    }
};
```

```go
func xorAllNums(nums1 []int, nums2 []int) int {
	ans := 0
	if len(nums2)%2 == 1 {
		for _, v := range nums1 {
			ans ^= v
		}
	}
	if len(nums1)%2 == 1 {
		for _, v := range nums2 {
			ans ^= v
		}
	}
	return ans
}
```

```ts
function xorAllNums(nums1: number[], nums2: number[]): number {
    let ans = 0;
    if (nums2.length % 2 != 0) {
        ans ^= nums1.reduce((a, c) => a ^ c, 0);
    }
    if (nums1.length % 2 != 0) {
        ans ^= nums2.reduce((a, c) => a ^ c, 0);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
