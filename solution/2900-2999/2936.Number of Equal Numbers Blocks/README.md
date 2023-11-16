# [2936. Number of Equal Numbers Blocks](https://leetcode.cn/problems/number-of-equal-numbers-blocks)

[English Version](/solution/2900-2999/2936.Number%20of%20Equal%20Numbers%20Blocks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> array of integers, <code>nums</code>. The following property holds for <code>nums</code>:</p>

<ul>
	<li>All occurrences of a value are adjacent. In other words, if there are two indices <code>i &lt; j</code> such that <code>nums[i] == nums[j]</code>, then for every index <code>k</code> that <code>i &lt; k &lt; j</code>, <code>nums[k] == nums[i]</code>.</li>
</ul>

<p>Since <code>nums</code> is a very large array, you are given an instance of the class <code>BigArray</code> which has the following functions:</p>

<ul>
	<li><code>int at(long long index)</code>: Returns the value of <code>nums[i]</code>.</li>
	<li><code>void size()</code>: Returns <code>nums.length</code>.</li>
</ul>

<p>Let&#39;s partition the array into <strong>maximal</strong> blocks such that each block contains <strong>equal values</strong>. Return<em> the number of these blocks.</em></p>

<p><strong>Note</strong> that if you want to test your solution using a custom test, behavior for tests with <code>nums.length &gt; 10</code> is undefined.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3,3,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one block here which is the whole array (because all numbers are equal) and that is: [<u>3,3,3,3,3</u>]. So the answer would be 1. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,3,9,9,9,2,10,10]
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 blocks here:
Block number 1: [<u>1,1,1</u>,3,9,9,9,2,10,10]
Block number 2: [1,1,1,<u>3</u>,9,9,9,2,10,10]
Block number 3: [1,1,1,3,<u>9,9,9</u>,2,10,10]
Block number 4: [1,1,1,3,9,9,9,<u>2</u>,10,10]
Block number 5: [1,1,1,3,9,9,9,2,<u>10,10</u>]
So the answer would be 5.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Since all numbers are distinct, there are 7 blocks here and each element representing one block. So the answer would be 7. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that all equal values are adjacent.</li>
	<li>The sum of the elements of&nbsp;<code>nums</code>&nbsp;is at most&nbsp;<code>10<sup>15</sup></code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们可以使用二分查找来找到每个块的右边界。具体地，我们从左到右遍历数组，对于每个下标 $i$，我们使用二分查找找到最小的下标 $j$，使得 $[i,j)$ 之间的所有元素都等于 $nums[i]$。然后我们将 $i$ 更新为 $j$，并继续遍历数组，直到 $i$ 大于等于数组的长度。

时间复杂度 $O(m \times \log n)$，其中 $m$ 是数组 $num$ 中不同元素的个数，而 $n$ 是数组 $num$ 的长度。空间复杂度 $O(1)$。

**方法二：分治**

我们可以使用分治的方法来计算答案。具体地，我们将数组分成两个子数组，递归地计算每个子数组的答案，然后将答案合并起来。如果第一个子数组的最后一个元素和第二个子数组的第一个元素相等，那么我们需要将答案减一。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $num$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for BigArray.
# class BigArray:
#     def at(self, index: long) -> int:
#         pass
#     def size(self) -> long:
#         pass
class Solution(object):
    def countBlocks(self, nums: Optional["BigArray"]) -> int:
        i, n = 0, nums.size()
        ans = 0
        while i < n:
            ans += 1
            x = nums.at(i)
            if i + 1 < n and nums.at(i + 1) != x:
                i += 1
            else:
                i += bisect_left(range(i, n), True, key=lambda j: nums.at(j) != x)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for BigArray.
 * class BigArray {
 *     public BigArray(int[] elements);
 *     public int at(long index);
 *     public long size();
 * }
 */
class Solution {
    public int countBlocks(BigArray nums) {
        int ans = 0;
        for (long i = 0, n = nums.size(); i < n; ++ans) {
            i = search(nums, i, n);
        }
        return ans;
    }

    private long search(BigArray nums, long l, long n) {
        long r = n;
        int x = nums.at(l);
        while (l < r) {
            long mid = (l + r) >> 1;
            if (nums.at(mid) != x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```java
/**
 * Definition for BigArray.
 * class BigArray {
 *     public BigArray(int[] elements);
 *     public int at(long index);
 *     public long size();
 * }
 */
class Solution {
    public int countBlocks(BigArray nums) {
        return f(nums, 0, nums.size() - 1);
    }

    private int f(BigArray nums, long l, long r) {
        if (nums.at(l) == nums.at(r)) {
            return 1;
        }
        long mid = (l + r) >> 1;
        int a = f(nums, l, mid);
        int b = f(nums, mid + 1, r);
        return a + b - (nums.at(mid) == nums.at(mid + 1) ? 1 : 0);
    }
}
```

### **C++**

```cpp
/**
 * Definition for BigArray.
 * class BigArray {
 * public:
 *     BigArray(vector<int> elements);
 *     int at(long long index);
 *     long long size();
 * };
 */
class Solution {
public:
    int countBlocks(BigArray* nums) {
        int ans = 0;
        using ll = long long;
        ll n = nums->size();
        auto search = [&](ll l) {
            ll r = n;
            int x = nums->at(l);
            while (l < r) {
                ll mid = (l + r) >> 1;
                if (nums->at(mid) != x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        };
        for (long long i = 0; i < n; ++ans) {
            i = search(i);
        }
        return ans;
    }
};
```

```cpp
/**
 * Definition for BigArray.
 * class BigArray {
 * public:
 *     BigArray(vector<int> elements);
 *     int at(long long index);
 *     long long size();
 * };
 */
class Solution {
public:
    int countBlocks(BigArray* nums) {
        using ll = long long;
        function<int(ll, ll)> f = [&](ll l, ll r) {
            if (nums->at(l) == nums->at(r)) {
                return 1;
            }
            ll mid = (l + r) >> 1;
            int a = f(l, mid);
            int b = f(mid + 1, r);
            return a + b - (nums->at(mid) == nums->at(mid + 1));
        };
        return f(0, nums->size() - 1);
    }
};
```

### **TypeScript**

```ts
/**
 * Definition for BigArray.
 * class BigArray {
 *     constructor(elements: number[]);
 *     public at(index: number): number;
 *     public size(): number;
 * }
 */
function countBlocks(nums: BigArray | null): number {
    const n = nums.size();
    const search = (l: number): number => {
        let r = n;
        const x = nums.at(l);
        while (l < r) {
            const mid = l + Math.floor((r - l) / 2);
            if (nums.at(mid) !== x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };

    let ans = 0;
    for (let i = 0; i < n; ++ans) {
        i = search(i);
    }
    return ans;
}
```

```ts
/**
 * Definition for BigArray.
 * class BigArray {
 *     constructor(elements: number[]);
 *     public at(index: number): number;
 *     public size(): number;
 * }
 */
function countBlocks(nums: BigArray | null): number {
    const f = (l: number, r: number): number => {
        if (nums.at(l) === nums.at(r)) {
            return 1;
        }
        const mid = l + Math.floor((r - l) / 2);
        const a = f(l, mid);
        const b = f(mid + 1, r);
        return a + b - (nums.at(mid) === nums.at(mid + 1) ? 1 : 0);
    };
    return f(0, nums.size() - 1);
}
```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
